/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.DiffrIntegrSep.FuncName;
import static Calculus.DiffrIntegrSep.ParanExprInProgressFlag;
import static Calculus.DiffrIntegrSep.StrDiff;
import static Calculus.DiffrIntegrSep.StrIntegr;
import static Calculus.DiffrIntegrSep.StrLADiff;
import static Calculus.DiffrIntegrSep.chDiff;
import static Calculus.DiffrIntegrSep.chIntegr;
import static Calculus.DiffrIntegrSep.chLADiff;
import static Calculus.DiffrIntegrSep.nextCharDiff;
import static Calculus.DiffrIntegrSep.nextCharIntegr;
import static Calculus.DiffrIntegrSep.posDiff;
import static Calculus.DiffrIntegrSep.posIntegr;
import static Calculus.DiffrIntegrSep.posLADiff;
import static Calculus.DiffrIntegrSep.prevCharDiff;
import static Calculus.DiffrIntegrSep.prevCharIntegr;
import static Calculus.UsageCalculus.DiffWithRespTo;
import static Calculus.UsageCalculus.Order;
import static Calculus.UsageCalculus.StageMapping;
import static Calculus.UsageCalculus.TestCase;
import static Calculus.UsageCalculus.mainRegressionIntegrTest;
import static Calculus.UsageCalculus.mainRegresssionDiffTest;
import static Calculus.UsageCalculus.mainAlgebraUser;
import static Calculus.UsageCalculus.mainRegresssionHghLvlExprODETest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Rajesh V Pai
 */
/*
 * Version :70 (Backup Version:Ver 70)
 * Fully Functional Simple Expression Mapping & Differentiation
 * 
 * Notes:
 * Constants, Factors (x alone term) and Polynomials of x
 * Simple Expression reduces x^0.0 & x^1.0, 5^1.0 , 5^0.0
 * Whole Program (Simple Expression & Differentiation)Unit Test Cases are passing
 * 1)Known Issues:
 * a)(Constant in braces)*Function does not work for Simple Expression & DiffExpr
 * ://String Exprn = "7.0*x^6.0+5.0*x^4.0+2.0*x^1.0+(21.0)*sin(21.0*x)+ 25.0";
 * b)0.0*Function is not being reduced in Simple Expression & DiffExpr
 * ://String Exprn = "7.0*x^6.0+5.0*x^4.0+2.0*x^1.0+0.0*cos(37.0)+7.0";
 * c)Higher Order cos function fails as there is a (-1.0)* to sin function in 
 * the mapping Hash Map variablesDiff (line 149) in function (MyFuncDiff()).
 * ://String Exprn = "+0.0*(-1.0)*sin(37.0)";
 * d)Addition in function argument does not work
 * ://String Exprn = "x^7 + x^5 + x^2 - sin(37+7.0*x) + 25.0";
 * 
 * 
 * 2) Release Notes:
 * a) Simple Expression does not work with Trailing Constants
 * b) Functions work if not at the end of String Expression else we get a Parse Error
 *    due to "\n\r" mapping at the end of a string (pos will be <= str.length)
 * c) Public interface ConstAddSubExprn has been added to remove trailing 0.0 or 1.0 etc.
 *    These need to be expanded to Generic Aggragators of like exponent Terms 
 *    (e.g add all the x terms , all the constants etc placed any where in the Equation)
 * d) Does not work with Palindromic Equations:
 * String Exprn = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
 * Power of x has to decrease from Left to Right with Constant at the end.
 * Same Equation with Terms Re-arranged works.
String Exprn = " x^8 - x^8 + x^5 -x^5 + x^4 - x^4 - x + x - 25.0 + 25.0";
 * e) Equations cannot start with "-" Term as first term.
String Exprn = " -x^8 + x^8 + x^5 -x^5 + x^4 - x^4 - x + x - 25.0 + 25.0";
 * same Equation works with First Term Positive:
String Exprn = " +x^8 - x^8 + x^5 -x^5 + x^4 - x^4 - x + x - 25.0 + 25.0";
 * f) Palindromic Equations can have just 1 constant Term:Below is not allowed
String Exprn = " x^8 - x^8 + x^5 -x^5 + x^4 - x^4 - x + x - 25.0 + 20.0";
Below Palindromic Equation works:(Constants must be pre calculated and then put in the equation)
String Exprn = " x^8 - x^8 + x^5 -x^5 + x^4 - x^4 - x + x - 5.0";
 * 3) Untested:
 * a) Independent Variable (e.g z,a,c,xyz)
 * b) Functions (Sine, Cos etc)
 * c) Parenthesis
 *
 * 4) Discovery:
 * a) First Order Diff for Functions & Parenthesis works.
 * b) Simple Expression drops constants in Parenthesis e.g:sin(1+2.0*x)=sin(2.0*x)
 * c) Diff needs Simple Expression to work with Trailing Constants (sin(2x)=cos(2x)*2: F'(u)= F'(x)*du/dx
 */
// Taken from stackoverflow.com (Expression):Evaluating a math 
// expression with variables (java 8):Created by StackOverflow User:Boann 
// Taken from stackoverflow.com:Evaluating a math 
// expression given in string form [closed]
//http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
// Taken from stackoverflow.com (Expression):Evaluating a math 
// expression with variables (java 8) Created by User :Mike Scholtes
//http://stackoverflow.com/questions/40975678/evaluating-a-math-expression-with-variables-java-8
public class MathsContxtLAv {

    public static Logger log = Logger.getLogger(MathsContxtLAv.class.getName());
    static Map<String, Double> variables = new HashMap<>();
    public static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    static String BC_SplitterDiff = "[\\^\\(\\/\\*\\-\\+\\)]";
    public static Map<String, String> functionsSimple = new HashMap<>();

    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumFractionDigits = 25;
    // Maximum number of Integer Digits displayed in Equation
    private static int MaximumIntegerDigits = 50;

    public static enum LogLevel {
        All(7), Debug(6), Info(5), Warn(4), Error(3), Fatal(2), Off(1);
        int level = 0;

        LogLevel(int level1) {
            this.level = level1;
        }
    }

    // Expression Term character Index and Character Variable    
    public static int posExpr = -1, chExpr;
    public static String StrExpr;

    // Simple Expression & Simple Term character Index and Character Variable    
    public static int pos = -1, ch;
    public static int chTerm, posTerm = -1;
    public static String Str;

    // PUBLIC VARIABLES USED IN OTHER FILES
    //IsAConstant Return Type 
    public static String StrIsAConstant = "";

    public static boolean NormalExprnFlag = true;
    public static int ParenthesisHighLvlNum = 0;
    public static int ParenthesisCurrNum = 0;

    // Algebra & AlgebraTerm character Index and Character Variable    
    public static int posFunF = -1, chFunF, posFunFLocal = -1;
    public static int posFunG = -1, chFunG, posFunGLocal = -1;
    public static String StrFunF;
    public static String StrFunG;
    public static String[] StrAlgeTermFunF;
    public static String[] StrAlgeTermFunG;

    public static boolean ParenthesisFlag = false;
    public static boolean NormalNotPareFlag = true;
    public static int SavedOperator = 0;
    public static int ParenthesisOperator = 0;

    private static int posLA = -1, chLA;
    private static int posVar = -1, chVar;
    private static int posSign = -1, chSign, chTermSign;
    private static int posOrder = -1, chOrder;
    public static int posParen = -1, chParen;
    private static int posIndConst = -1, chIndConst;

    public static int LookAheadOperator = 0;

    private static String StrTerm;
    private static String StrSign;
    private static String StrLA;
    private static String StrVar;
    private static String StrIndConst;
    private static String StrOrder;
    private static String StrParen;

    public static int NumCoeff = 0;
    private static int CoeffCoeff = 0;

    //Global Common Flags for each Interface
    public static boolean FuncArgFlag = false;
    public static boolean ParanExprFlag = false;
    // SimpleTerm Interface Variables 
    public static boolean ComplexAssignFlag = false;
    public static boolean GotVariableFlag = false;
    public static int GotVariablePos = 0;
    public static boolean GotExpntNumFlag = false;
    private static int TermOperatorPos = -1;
    private static double TermOutput = 0.0;
    public static double TermOutputIndConst = 0.0;
    public static boolean GotExtremityFlag = false;
    public static boolean GotIndConstFlag = false;
    public static int GotIndConstPos = 0;
    private static String TempIndConstBase = "";
    private static boolean TempIndConstStrSet = false;
    private static boolean TrailConstantFlag = false;
    private static int TrailConstantOperator = -1;
    private static int timesLoop = 0;
    public static boolean ContainsXFlag = false;
    public static boolean ContainsIndConstFlag = false;
    public static boolean TermOutputSetFlag = false;
    private static int TermOutputOperator = -1;
    private static int LATermOperatorBraces = -1;
    private static int LAPreTermOperator = -1;
    private static int LATermOperator = -1;
    private static int LAVarTermOperator = -1;
    private static int LAPreIndConstOperator = -1;
    private static int LAIndConstOperator = -1;
    private static int LAMAPOperator = -1;
    private static int LADyYByDxMAPOperator = -1;
    private static boolean LAVarSimpleMapFlag = false;
    private static boolean LAVarComplexMapFlag = false;
    public static int DiffTermOperator = -1;
    public static int LADiffFactorOperator = -1;
    public static int LADiffIndConstOperator = -1;
    private static String LastInvokedFlag = "";
    private static int LastInvokedPos = -1;
    public static int LastLADiffIndConstAidPos = -1;
    public static int LastLADiffExtremityAidPos = -1;
    public static int LastLADiffConstCoeffAidPos = -1;
    private static boolean ParseMapposModFlag = false;
    private static boolean ZeroTermSignFlag = false;
    // Maximum Order of the Term or Expression
    private static double MaxTermOutput = 0.0;
    private static String FunctionStr = "";
    private static boolean ContainsFuncFlag = false;
    private static boolean LAConstSimpleMapFlag = false;
    private static boolean LAConstComplexMapFlag = false;

    //Function Argument Flag
    private static boolean GotBracesVarFlag = false;
    public static int GotParenPos = -1;
    private static int GotBracesCount = -1;
    private static int GotBracesCountCurr = -1;
    // Diff Interface Variables 
    public static String FactorsExpr = "";
    public static String FactorsDiffExpr = "";
    public static String FunctionExpr = "";
    public static String FunctionDiffExpr = "";
    public static String FunctionName = "";
    public static String ParanExpr = "";
    public static String ParanDiffExpr = "";
    public static boolean EnteredLAConstFlag = false;
    private static boolean DiffConstSimpleMapFlag = false;
    private static boolean DiffConstComplexMapFlag = false;

    public static void MyFuncExpress() {
        functions.put("sqrt", x -> Math.sqrt(x));
        functions.put("sin", x -> Math.sin(Math.toRadians(x)));
        functions.put("cos", x -> Math.cos(Math.toRadians(x)));
        functions.put("tan", x -> Math.tan(Math.toRadians(x)));
        functions.put("round", x -> Math.round(x));
        functions.put("abs", x -> Math.abs(x));
        functions.put("ceil", x -> Math.ceil(x));
        functions.put("floor", x -> Math.floor(x));
        functions.put("log", x -> Math.log10(x));
        functions.put("ln", x -> Math.log(x));
        functions.put("exp", x -> Math.exp(x));
        // This is a bug workaround for CONFORMAL MAPPING
        // As we are doing Str.replaceall(DiffWithRespTo,"y")
        functions.put("eyp", x -> Math.exp(x));
        //TODO:More Unary Functions to be added
    }

    public static void MyFuncSimple() {
        functionsSimple.put("sin", "sin");
        functionsSimple.put("cos", "cos");
        functionsSimple.put("tan", "tan");
        functionsSimple.put("log", "log");
        functionsSimple.put("ln", "ln");
        functionsSimple.put("exp", "exp");
        //TODO:More Simple Unary Functions to be added
    }

    static void nextChar() {
        ++pos;
        ch = (pos < Str.length()) ? Str.charAt(pos) : -1;
        if ((ch == -1) && (pos >= Str.length())) {
            pos = Str.length();
        } else if ((ch == -1) || ((ch == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "nextChar:nextChar:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + Str.substring(pos, Str.length()));
        }
    }

    static void prevChar() {
        --pos;
        ch = (pos >= 0) ? Str.charAt(pos) : -1;
        if ((ch == -1) && (pos < 0)) 
            ; else if ((ch == -1) || ((ch == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "prevChar:prevChar:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + Str.substring(pos, Str.length()));
        }
    }

    static boolean eat(int CharToEat) {
        while (ch == ' ') {
            nextChar();
        }
        if (ch == CharToEat) {
            nextChar();
            return true;
        }
        return false;
    }

    public static String eatAll(String Temp, int CharToEat) {
        String MyTempString = "";
        //if (Temp.equalsIgnoreCase("")) {
        //    Temp = Str;
        //}
        for (int i = 0; (i < Temp.length());) {
            if (CharToEat == Temp.charAt(i)) {
                i++;
            } else {
                MyTempString = MyTempString + Temp.charAt(i);
                i++;
            }
        }
        return MyTempString;
    }

    void SyncChar() {
        if ((pos < Str.length())
                && (Str.charAt(pos) != ch)) {
            if (Str.charAt(pos + 1) == ch) {
                pos++;
            } else if (Str.charAt(pos - 1) == ch) {
                pos--;
            }
        }
    }

    static boolean FatalCharSet(int CurrChar, String CharSet) {
        CharSequence Temp = "" + CurrChar;
        if (!CharSet.contains(Temp)) {
            return true;
        }
        return false;
    }

    public static int Max(int a, int b) {
        if ((a < -1) && (b < -1)) {
            return -1;
        }
        if (a >= b) {
            return a;
        } else {
            return b;
        }

    }

    public static void ParseMap(String AlgoName, int strlength, int StartPos, int DiffLength, String DiffWithRespTo) {
        if (AlgoName.equalsIgnoreCase("LookAheadVar:parseVar")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if ((TrailConstantFlag == false) && (ParenthesisFlag == false)) {
                if (posVar < (StrVar.length())) {
                    posTerm = posVar + 1;
                    chTerm = chVar;
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }
                log.info("ParseMap:Var:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            } else if ((ParenthesisFlag == false) && (ContainsFuncFlag == false)) {
                if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
//                    posLA = GotVariablePos;
//                    if (posLA < StrVar.length()) {
//                        posTerm = posLA;
//                        chTerm = StrTerm.charAt(posTerm);
//                        chLA = chTerm;
//                    } else {
//                        posTerm = -1;
//                        chTerm = -1;
//                    }
                }
                log.info("ParseMap:Var:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
                ContainsFuncFlag = false;
            }
//            else {
//                StrParen = Str;
//                posParen = pos;
//                chParen = StrParen.charAt(posParen);
//                log.info("ParseMap:Var:Braces:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posParen=" + posParen + " chParen=" + (char) chParen);
//            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);

        } else if (AlgoName.equalsIgnoreCase("LookAheadConst:parseLA")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if ((GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                posVar = GotVariablePos;
                if ((posVar >= 0) && (posVar < StrLA.length())) {
                    chTerm = StrLA.charAt(posVar);
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }
                log.info("ParseMap:GotVariableFlag:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            } else if ((TrailConstantFlag == true) && (ContainsIndConstFlag == false)) {
                if (ComplexAssignFlag == true) {
                    if (posLA < StrLA.length()) {
                        posTerm = posLA + 1;
                        chTerm = StrTerm.charAt(posTerm);
                        chLA = chTerm;
                    } else {
                        posTerm = -1;
                        chTerm = -1;
                    }
                }
                //TrailConstantFlag=false;
                log.info("ParseMap:ContainsIndConstFlag:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            } else if ((TrailConstantFlag == true) && (ContainsIndConstFlag == true)) {
                if (posLA < StrLA.length()) {
                    posTerm = pos;
                    StrTerm = Str;
                    chTerm = StrTerm.charAt(posTerm);
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }
                log.info("ParseMap:TrailConstantFlag:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            } else if (ContainsIndConstFlag == true) {
                if (posLA < StrLA.length()) {
                    posTerm = pos;
                    StrTerm = Str;
                    chTerm = StrTerm.charAt(posTerm);
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }
                log.info("ParseMap:ContainsIndConstFlag:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            } else if (chLA == -1) {
                posTerm = -1;
                chTerm = -1;
                pos = -1;
                ch = -1;
                Str = "";
                StrTerm = "";
                log.info("ParseMap:Last Char:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);

        } else if (AlgoName.equalsIgnoreCase("LookAheadConst:parseLA:LAAlone")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if ((StrLA != null) && (StrLA.equalsIgnoreCase("")) && (posLA < (StrLA.length() - 1)) && (chLA != -1) && (timesLoop == 0)) {
                posTerm = posLA + 1;
                chTerm = StrLA.charAt(posTerm);
            } else if ((chLA == -1) || (posLA == -1)) {
                posTerm = -1;
                chTerm = -1;
            } else if ((timesLoop == 2) && ((chVar != -1) && (posVar != -1))) {
                posTerm = posVar + 1;
                chTerm = StrTerm.charAt(posTerm);
                log.info("ParseMap:Initial:AlgoName=" + AlgoName + " Reached posVar=" + posVar + " chVar=" + (char) chVar + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
            } else if ((ContainsIndConstFlag == false) && (ContainsXFlag == false)) {
                posTerm = posLA + 1;
                chTerm = StrLA.charAt(posTerm);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
        } else if (AlgoName.equalsIgnoreCase("LookAheadParen:parseParen")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posParen=" + posParen + " chParen=" + (char) chParen);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if (posSign == -1) {
                posTerm = posTerm + posParen + 1;
            } else {
                posTerm = posTerm + posParen;
            }
            if ((posTerm < StrTerm.length()) && ((StrTerm.charAt(posTerm)) == ')')) {
                posTerm++;
            }
            if (posTerm < StrTerm.length()) {
                chTerm = StrTerm.charAt(posTerm);
            }
            log.info("ParseMap:Paren:TrailConstantFlag:false:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posParen=" + posParen + " chParen=" + (char) chParen + " posLA=" + posLA + " chLA=" + (char) chLA + " posParen=" + posParen + " chParen=" + (char) chParen);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
        } else if (AlgoName.equalsIgnoreCase("SimpleTerm:Max:parseSimple")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign + " posParen=" + posParen + " chParen=" + (char) chParen);
            int MyposMax = -1;
            boolean Flag = false;
            MyposMax = Max(posSign, posLA);
            if (MyposMax >= -1) {
                MyposMax = Max(MyposMax, posVar);
            }
            if (MyposMax >= -1) {
                MyposMax = Max(MyposMax, posIndConst);
            }
//            if ((MyposMax >= -1) && (posParen > 0) && (posParen > MyposMax)) {
//                {
//                    Flag = true;
//                    MyposMax = MyposMax + 2;
//                }
//            }
            if (MyposMax > -1) {
                if ((pos > -1) && (pos < Str.length())) {
                    if ((ContainsIndConstFlag == true) && (MyposMax >= posSign)) {
                        pos = pos + posSign;
                        log.info("ParseMap:Last Term Mapping:if:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else {
                        pos = pos + MyposMax;
                        log.info("ParseMap:Middle:if:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                        if ((pos > -1) && (pos < Str.length())) {
                            ch = Str.charAt(pos);
                            log.info("ParseMap:Middle:if:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                            if ((Str.length() - pos) < 2) {
                                pos = -1;
                                ch = -1;
                                log.info("ParseMap:Middle:if:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                                ChaosPreventionLowLevel();
                                log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                                return;
                            }
                        } else {
                            pos = -1;
                            ch = -1;
                            log.info("ParseMap:Middle:if:AlgoName=" + AlgoName + " Str.length()=" + Str.length() + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                            ChaosPreventionLowLevel();
                            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                            return;
                        }
                    }
                    log.info("ParseMap:Else:End:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                } else if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                    log.info("ParseMap:Middle:Else if:AlgoName=" + AlgoName + " MyposMax=" + MyposMax + " StrTerm=" + StrTerm.substring(posTerm));
                    log.info("ParseMap:Middle:Else if:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                    Str = StrTerm.substring(posTerm);
                    posTerm = MyposMax;
                    pos = posTerm;
                    log.info("ParseMap:Middle:Else if:AlgoName=" + AlgoName + " MyposMax=" + MyposMax + " Str=" + Str);
                    log.info("ParseMap:Middle:Else if:AlgoName=" + AlgoName + " StrTerm=" + StrTerm);
                    if ((pos > -1) && (pos < Str.length())) {
                        ch = Str.charAt(pos);
                    } else if ((MyposMax > -1) && (MyposMax < Str.length())) {
                        posTerm = MyposMax;
                        pos = posTerm;
                        ch = Str.charAt(pos);
                        log.info("ParseMap:Middle:Else if:AlgoName=" + AlgoName + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else {
                        log.info("ParseMap:Middle:Else:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                        ch = -1;
                    }
                } else {
                    log.info("ParseMap:Middle:Else:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                    ch = -1;
                }
                //BAD TWEAK for ln Term
                if (ch == ')') {
                    pos++;
                    if ((pos > -1) && (pos < Str.length())) {
                        ch = Str.charAt(pos);
                    }
                }
            } else {
                pos = -1;
                ch = -1;
                ChaosPreventionLowLevel();
                log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                return;
            }
            log.info("ParseMap:Middle:Last:AlgoName=" + AlgoName + " Flag=" + Flag + " Str=" + Str);
            log.info("ParseMap:Middle:Last:AlgoName=" + AlgoName + " Flag=" + Flag + " MyposMax=" + MyposMax + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            if (Flag == true) {
                pos--;
                posTerm = pos;
                StrTerm = Str;
                if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                    chTerm = StrTerm.charAt(posTerm);
                }
            }
            if (((posTerm > -1) && (posTerm < StrTerm.length())) && (chTerm != 0) && (chTerm != -1) && (chTerm != '+') && (chTerm != '-') && (!IsVariable(chTerm)) && (!IsNumber(chTerm)) && (chTerm != '(') && (chTerm != ')')) {
                int TempTerm = StrTerm.charAt(posTerm);
                for (TempTerm = StrTerm.charAt(posTerm); (posTerm > -1) && (posTerm < StrTerm.length()) && (TempTerm != 0) && (TempTerm != -1) && (TempTerm != '+') && (TempTerm != '-');) {
                    pos++;
                    posTerm = pos;
                    StrTerm = Str;
                    if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                        chTerm = StrTerm.charAt(posTerm);
                    }
                    TempTerm = chTerm;
                }
                log.info("ParseMap:Pre-Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            } else if (((posTerm > -1) && (posTerm < StrTerm.length())) && (chTerm != 0) && (chTerm != -1) && (chTerm != '+') && (chTerm != '-') && ((IsVariable(chTerm)) || (IsNumber(chTerm))) && (ch != 0) && (ch != -1) && (ch != '+') && (ch != '-') && (chTerm != '(') && (chTerm != ')')) {
                int TempTerm = StrTerm.charAt(posTerm);
                log.info("ParseMap:TempTerm:AlgoName=" + AlgoName + " TempTerm=" + (char) TempTerm + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                for (TempTerm = StrTerm.charAt(posTerm); (posTerm > -1) && (posTerm < StrTerm.length()) && (TempTerm != 0) && (TempTerm != -1) && (TempTerm != '+') && (TempTerm != '-');) {
                    pos++;
                    posTerm = pos;
                    StrTerm = Str;
                    if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                        chTerm = StrTerm.charAt(posTerm);
                    }
                    TempTerm = chTerm;
                }
                log.info("ParseMap:Pre-Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            }
            ChaosPreventionLowLevel();
            log.info("ParseMap:Final:Full String:AlgoName=" + AlgoName + " Flag=" + Flag + " Str=" + Str);
            log.info("ParseMap:Final:Full String:AlgoName=" + AlgoName + " Flag=" + Flag + " StrTerm=" + StrTerm);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
        } else if (AlgoName.equalsIgnoreCase("LookAheadParen:parseTermParen")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign + " posParen=" + posParen + " chParen=" + (char) chParen);
            int MyposMax = -1;
            MyposMax = Max(MyposMax, posParen);
            if (MyposMax >= -1) {
                MyposMax = Max(MyposMax, posTerm);
            }
            if (MyposMax > -1) {
                posTerm = posTerm + MyposMax;
                if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                    chTerm = StrTerm.charAt(posTerm);
                } else {
                    chTerm = -1;
                }
                log.info("ParseMap:Middle:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
                //BAD TWEAK for ln Term
                if (chTerm == ')') {
                    posTerm++;
                    if ((posTerm > -1) && (posTerm < StrTerm.length())) {
                        chTerm = StrTerm.charAt(posTerm);
                    }
                }
            } else {
                posTerm = -1;
                chTerm = -1;
            }
            ch = chTerm;
            pos = posTerm;
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
        } else if (AlgoName.equalsIgnoreCase("LookAheadSign:parseSign")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            if ((strlength == -1) && (chTermSign == 0) && (chTerm == '+')) {
                chTermSign = '-';
                log.info("ParseMap:Rule1:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 1) && (chTermSign == 0) && (chTerm == '+')) {
                chTermSign = '+';
                log.info("ParseMap:Rule2:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 0) && (chSign == '+')) {
                chTermSign = '+';
                log.info("ParseMap:Rule3:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 0) && (chSign == '-')) {
                chTermSign = '-';
                log.info("ParseMap:Rule4:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } //First Term Code for Sign Computation
            else if ((strlength == 1) && (chTermSign == 0) && (pos == 0) && (chTerm == '-')) {
                chTermSign = '-';
                log.info("ParseMap:Rule5.1:First Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 1) && (chTermSign == 0) && (pos == 0)) {
                chTermSign = '+';
                log.info("ParseMap:Rule5.2:First Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } //Last Term Code for Sign Computation
            else if ((strlength == 1) && (chTermSign == 0) && (chTerm == '-')) {
                chTermSign = '-';
                log.info("ParseMap:Rule6:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if (((chLA != 0) && (chLA != -1)) && (StrSign != null) && (!StrSign.equalsIgnoreCase("")) && (StartPos > 0) && ((StrSign.charAt(0) != '+') && (StrSign.charAt(0) != '-'))) {
                chTermSign = chLA;
                log.info("ParseMap:Rule7:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if (((chVar != 0) && (chVar != -1)) && (StrSign != null) && (!StrSign.equalsIgnoreCase("")) && (StartPos > 0) && ((StrSign.charAt(0) != '+') && (StrSign.charAt(0) != '-'))) {
                chTermSign = chVar;
                log.info("ParseMap:Rule8:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } //Middle Terms Code for Sign Computation
            else if ((strlength == 1) && (chTermSign == 0) && (chTerm == '-') && (chSign != 0) & (chSign != -1)) {
                chTermSign = '-';
                log.info("ParseMap:Rule9:Middle Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 1) && (chTermSign == 0) && (chTerm == '+')) {
                chTermSign = '+';
                log.info("ParseMap:Rule10:Middle Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == -1) && (chTermSign == 0) && (ch == '-') && (pos == 0)) {
                chTermSign = '+';
                log.info("ParseMap:Rule11:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == -1) && (chTermSign == 0) && (ch == '-') && (chTerm == '-')) {
                chTermSign = '+';
                log.info("ParseMap:Rule12:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == -1) && (chTermSign == 0) && (chTerm == '-')) {
                chTermSign = '+';
                log.info("ParseMap:Rule13:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == -1) && (chTermSign == 0)) {
                chTermSign = '-';
                log.info("ParseMap:Rule14:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 1) && (chTerm == '-')) {
                chTermSign = '-';
                log.info("ParseMap:Rule15:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            }
            if ((chTermSign == 0) && (strlength == -1)) {
                chTermSign = '-';
                log.info("ParseMap:Rule16:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((chTermSign == 0) && (strlength == 1)) {
                chTermSign = '+';
                if ((posTerm == -1) && (pos < Str.length()) && (pos > -1)) {
                    pos = pos - 1;
                    chTermSign = Str.charAt(pos);
                }
                log.info("ParseMap:Rule17:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);

        } else if (AlgoName.equalsIgnoreCase("LookAheadDiffConst:parseLADiff:Diff")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff));
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff) + " ContainsIndConstFlag=" + ContainsIndConstFlag + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffExtremityAidPos=" + LastLADiffExtremityAidPos + " GotExtremityFlag=" + GotExtremityFlag);
            if ((ContainsIndConstFlag == true) && (GotExpntNumFlag == true) && (posDiff < StrDiff.length()) && (LastLADiffIndConstAidPos >= 0)) {
                posDiff = posDiff + LastLADiffIndConstAidPos;
                while ((posDiff < StrDiff.length()) && (StrDiff.charAt(posDiff) != '*') && (StrDiff.charAt(posDiff) != '/')) {
                    if ((GotVariablePos == -1) && (GotVariableFlag == false)) {
                        nextCharDiff();
                    } else {
                        prevCharDiff();
                    }
                }
                if (posDiff < StrDiff.length()) {
                    chDiff = StrDiff.charAt(posDiff);
                } else {
                    posDiff = StrDiff.length();
                    chDiff = -1;
                    log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                }
                log.info("ParseMap:Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
            } else if ((GotExtremityFlag == true) && (posDiff < StrDiff.length()) && (LastLADiffExtremityAidPos >= 0)) {
                StrDiff = StrLADiff;
                posDiff = StrDiff.indexOf(DiffWithRespTo, 0) - 1;
                if (posDiff == -1) {
                    posDiff = StrDiff.length();
                    log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                }
                log.info("ParseMap:Before Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffExtremityAidPos=" + LastLADiffExtremityAidPos + " StrDiff.length()=" + StrDiff.length());
                if ((posDiff >= 0) && (posDiff < StrDiff.length())) {
                    chDiff = StrDiff.charAt(posDiff);
                } else {
                    posDiff = StrDiff.length();
                    chDiff = -1;
                    log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                }
                log.info("ParseMap:Outside Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffExtremityAidPos=" + LastLADiffExtremityAidPos + " StrDiff.length()=" + StrDiff.length());
            } else if ((ContainsIndConstFlag == true) && (posDiff < StrDiff.length()) && (GotVariablePos >= 0) && (LastLADiffIndConstAidPos >= 0)) {
                if (chLADiff != -1) {
                    posDiff = posDiff + LastLADiffIndConstAidPos - 1;
                    log.info("ParseMap:Outside Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                    log.info("ParseMap:Outside Loop:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));
                    if ((posDiff >= 0) && (posDiff < StrDiff.length())) {
                        chDiff = StrDiff.charAt(posDiff);
                    } else {
                        posDiff = StrDiff.length();
                        chDiff = -1;
                        log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                        log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));

                    }
                    log.info("ParseMap:Outside Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                    log.info("ParseMap:Outside Loop:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));
                } else {
                    posDiff = StrDiff.length();
                    chDiff = -1;
                    log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                }
            } else if ((ContainsIndConstFlag == true) && (GotExpntNumFlag == false) && (posDiff < StrDiff.length()) && (LastLADiffIndConstAidPos >= 0)) {
                posDiff = posDiff + LastLADiffIndConstAidPos;
                while ((posDiff < StrDiff.length()) && (StrDiff.charAt(posDiff) != '+') && (StrDiff.charAt(posDiff) != '-')) {
                    nextCharDiff();
                }
                if (posDiff < StrDiff.length()) {
                    chDiff = StrDiff.charAt(posDiff);
                } else {
                    posDiff = StrDiff.length();
                    chDiff = -1;
                    log.info("ParseMap:Terminating:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
                }
                log.info("ParseMap:Loop:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " StrDiff.length()=" + StrDiff.length());
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos);
        } else if (AlgoName.equalsIgnoreCase("LookAheadDiffConst:parseLADiff:Integration")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLADiff=" + posLADiff + " chLA=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotExpntNumFlag=" + GotExpntNumFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag + " ContainsXFlag=" + ContainsXFlag);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
            if ((ContainsIndConstFlag == true) && (posIntegr < StrIntegr.length()) && (LastLADiffIndConstAidPos >= 0)) {
                if (ComplexAssignFlag == false) {
                    posIntegr = posIntegr + LastLADiffIndConstAidPos - 1;
                    if ((posIntegr >= 0) && (posIntegr < StrIntegr.length())) {
                        log.info("ParseMap:ContainsIndConstFlag:ComplexAssignFlag:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                    }
                } else if ((ComplexAssignFlag == true) && (GotVariablePos >= 0)) {
                    posIntegr = posIntegr + GotVariablePos - 1;
                    if ((posIntegr >= 0) && (posIntegr < StrIntegr.length())) {
                        log.info("ParseMap:ContainsIndConstFlag:ComplexAssignFlag:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                    }
                }
                while ((posIntegr < StrIntegr.length()) && (!IsOperator(StrIntegr.charAt(posIntegr)))) {
                    prevCharIntegr();
                    log.info("ParseMap:Loop:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
                }
                if (posIntegr < StrIntegr.length()) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                } else {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                }
                if (posIntegr < StrIntegr.length()) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                }
                ContainsIndConstFlag = false;
                log.info("ParseMap:ContainsIndConstFlag == true:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                log.info("ParseMap:ContainsIndConstFlag == true:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            } else if ((LastLADiffConstCoeffAidPos >= 0)) {
                posIntegr++;
                while ((posIntegr >= 0) && (posIntegr < StrIntegr.length())
                        && (!IsOperator(StrIntegr.charAt(posIntegr)))) {
                    nextCharIntegr();
                }
                if ((posIntegr >= 0) && (posIntegr < StrIntegr.length())) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                    log.info("ParseMap:LastLADiffConstCoeffAidPos>0:If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else if ((posIntegr == -1)) {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                    log.info("ParseMap:LastLADiffConstCoeffAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else if (posIntegr >= StrIntegr.length()) {
                    log.info("ParseMap:LastLADiffConstCoeffAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else {
                    log.info("ParseMap:LastLADiffConstCoeffAidPos>0:Else:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    chIntegr = StrIntegr.charAt(posIntegr);
                    log.info("ParseMap:LastLADiffConstCoeffAidPos>0:Else:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                }
                ParseMapposModFlag = true;
                log.info("ParseMap:LastLADiffConstCoeffAidPos>0:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                log.info("ParseMap:LastLADiffConstCoeffAidPos:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            } else if ((LastLADiffIndConstAidPos >= 0)) {
                if ((posIntegr >= 0) && (posIntegr < StrIntegr.length())) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else if ((posIntegr == -1)) {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else if (posIntegr >= StrIntegr.length()) {
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else If:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                } else {
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    chIntegr = StrIntegr.charAt(posIntegr);
                    log.info("ParseMap:LastLADiffIndConstAidPos>0:Else:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                }
                ParseMapposModFlag = true;
                log.info("ParseMap:LastLADiffIndConstAidPos>0:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                log.info("ParseMap:LastLADiffIndConstAidPos:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            } else {
                if (posIntegr < StrIntegr.length()) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                } else {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                }
                log.info("ParseMap:Else:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                log.info("ParseMap:Else:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotExpntNumFlag=" + GotExpntNumFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        } else if (AlgoName.equalsIgnoreCase("LookAheadDiffConst:Max:parseLADiff:Integration")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLADiff=" + posLADiff + " chLA=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotExpntNumFlag=" + GotExpntNumFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag + " ContainsXFlag=" + ContainsXFlag);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
            int MyposMax = -1;
            boolean Flag = false;
            MyposMax = Max(posLADiff, posIntegr);
            if (MyposMax > -1) {
                posIntegr = posIntegr + MyposMax;
                if ((posIntegr > -1) && (posIntegr < StrIntegr.length())) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                } else {
                    chIntegr = -1;
                }
            } else {
                posIntegr = -1;
                chIntegr = -1;
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotExpntNumFlag=" + GotExpntNumFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        } else if (AlgoName.equalsIgnoreCase("Integr:parseTermIntegr:")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLADiff=" + posLADiff + " chLA=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrIntegr(posIntegr)=" + StrIntegr.substring(posIntegr));
            if (posIntegr == -1) {
                chIntegr = StrIntegr.charAt(0);
            } else {
                chIntegr = StrIntegr.charAt(posIntegr - 1);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrIntegr(posIntegr)=" + StrIntegr.substring(posIntegr) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        } else if (AlgoName.equalsIgnoreCase("SimpleAlgebra:parseSimple:StrFunF")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            pos = posFunF;
            Str = StrFunF;
            if ((pos >= 0) && (pos < Str.length())) {
                ch = Str.charAt(pos);
            } else {
                ch = -1;
            }
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
        } else if (AlgoName.equalsIgnoreCase("SimpleAlgebra:parseSimple:StrFunF-SteadyState")) {
            log.fatal("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            pos = posFunF;
            Str = StrFunF;
            if ((pos >= 0) && (pos < Str.length())) {
                ch = Str.charAt(pos);
            } else {
                ch = -1;
            }
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " StrFunF=" + StrFunF + " pos=" + pos + " posFunF=" + posFunF + " posFunFLocal=" + posFunFLocal);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " StrFunG=" + StrFunG + " posFunG=" + posFunG);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
        } else if (AlgoName.equalsIgnoreCase("SimpleAlgebra:parseSimple:StrFunG")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((posFunG == StrFunG.length() - 1) && (posFunF == StrFunF.length() - 1)) {
                pos = -1;
                posFunF = -1;
                posFunG = -1;
                Str = "";
            } else {
                pos = posFunG;
                Str = StrFunG;
            }
            if ((pos >= 0) && (pos < Str.length())) {
                ch = Str.charAt(pos);
            } else if (!Str.equalsIgnoreCase("")) {
                ch = Str.charAt(0);
            }
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
        }
    }

    public static void ParseStore(String AlgoName) {
        if (AlgoName.equalsIgnoreCase("StrFunF")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            if ((pos != -1) && (posFunF >= 0) && ((pos >= 0) && (pos < Str.length()))) {
                chFunF = StrFunF.charAt(0);
                Str = StrFunF;
                posFunF = pos;
                ch = Str.charAt(pos);
            } else {
                chFunF = StrFunF.charAt(0);
                Str = StrFunF;
                pos = 0;
                posFunF = 0;
                ch = Str.charAt(0);
            }
            StrFunF = Str;
            chFunF = Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        } else if (AlgoName.equalsIgnoreCase("StrFunG")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos != -1) && (posFunG >= 0) && ((pos >= 0) && (pos < Str.length()))) {
                chFunG = StrFunG.charAt(0);
                Str = StrFunG;
                posFunG = pos;
                ch = Str.charAt(pos);
            } else {
                if (!StrFunG.equalsIgnoreCase("")) {
                    chFunG = StrFunG.charAt(0);
                }
                if (!StrFunG.equalsIgnoreCase("")) {
                    Str = StrFunG;
                }
                pos = 0;
                posFunG = 0;
                if (!Str.equalsIgnoreCase("")) {
                    ch = Str.charAt(pos);
                }
            }
            StrFunG = Str;
            if (!Str.equalsIgnoreCase("")) {
                chFunG = Str.charAt(pos);
            }
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        } else if (AlgoName.equalsIgnoreCase("StrFunF-Inf")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            if ((pos != -1) && (posFunF >= 0) && ((pos >= 0) && (pos < Str.length()))) {
                chFunF = StrFunF.charAt(0);
                Str = StrFunF;
                pos = posFunF;
                ch = Str.charAt(pos);
            } else {
                chFunF = StrFunF.charAt(0);
                Str = StrFunF;
                pos = posFunF;
                ch = Str.charAt(pos);
            }
            StrFunF = Str;
            chFunF = Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        } else if (AlgoName.equalsIgnoreCase("StrFunG-Inf")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos != -1) && (posFunG >= 0) && ((pos >= 0) && (pos < Str.length()))) {
                chFunG = StrFunG.charAt(0);
                Str = StrFunG;
                pos = posFunG;
                ch = Str.charAt(pos);
            } else {
                if (!StrFunG.equalsIgnoreCase("")) {
                    chFunG = StrFunG.charAt(0);
                }
                Str = StrFunG;
                pos = posFunG;
                if (!Str.equalsIgnoreCase("")) {
                    ch = Str.charAt(pos);
                }
            }
            StrFunG = Str;
            if (!Str.equalsIgnoreCase("")) {
                chFunG = Str.charAt(pos);
            }
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        } else if (AlgoName.equalsIgnoreCase("StrFunG-Inf-SteadyState")) {
            log.fatal("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos != -1) && (posFunG >= 0) && ((pos >= 0) && (pos < StrFunG.length()))) {
                log.fatal("ParseStore:Initial:AlgoName=" + AlgoName + "Rule1");
                chFunG = StrFunG.charAt(posFunG);
                Str = StrFunG;
                posFunG = pos;
                ch = Str.charAt(pos);
            } else if (pos == -1) {
                log.fatal("ParseStore:Initial:AlgoName=" + AlgoName + "Rule2");
                Str = StrFunG;
                ch = Str.charAt(posFunG);
                pos = posFunG;
            } else if ((posFunG == -1) && (pos != -1)) {
                log.fatal("ParseStore:Initial:AlgoName=" + AlgoName + "Rule3");
                Str = StrFunG;
                ch = Str.charAt(pos);
                posFunG = pos;
                chFunG = StrFunG.charAt(posFunG);
            } else {
                pos = 0;
                posFunG = 0;
                Str = StrFunG;
            }
            log.fatal("ParseStore:Final:AlgoName=" + AlgoName + " Str=" + Str.substring(pos));
            log.fatal("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        }
    }

    public static String RemoveEDbl(double Number) {
        String StrNumber = "" + Number;
        if ((StrNumber.contains("E")) || (StrNumber.contains("e"))) {
            NumberFormat formatter = new DecimalFormat();
            formatter.setMaximumFractionDigits(MaximumFractionDigits);
            formatter.setMaximumIntegerDigits(MaximumIntegerDigits);
            log.info("RemoveEDbl:Number=" + Number);
            log.info("RemoveEDbl:Formatted Number=" + formatter.format(Number));
            return formatter.format(Number);
        }
        return (StrNumber);
    }

    public static String StrRemoveEDbl(String Number) {
        if ((Number.contains("E")) || (Number.contains("e"))) {
            NumberFormat formatter = new DecimalFormat("#.##");
            formatter.setMaximumFractionDigits(MaximumFractionDigits);
            formatter.setMaximumIntegerDigits(MaximumIntegerDigits);
            log.info("StrRemoveEDbl:Number=" + Number);
            log.info("StrRemoveEDbl:Formatted Number=" + formatter.format(Number));
            return formatter.format(Number);
        }
        return Number;
    }

    static boolean IsAConstant(String StrX1, String DiffWithRespTo) {

        try {
            StrIsAConstant = "Unknown";
            String StrX = StrX1;
            //log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
            log.info("IsAConstant:StrX=" + StrX);
            log.info("IsAConstant:DiffWithRespTo=" + DiffWithRespTo);
//            if ((StrX != null)
//                    && (!StrX.equalsIgnoreCase(""))
//                    && (!StrX.contains(DiffWithRespTo))
//                    && (StrX.matches("[-+]+"))) {
//                StrIsAConstant = "Number";
//                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
//                return true;
//            } else 
            if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[\\-\\+]*[0-9\\.]+"))
                    //&& (!StrX.matches("[\\-\\+]*[a-zA-Z0-9]+"))
                    && (Double.isFinite(Double.valueOf(StrX)))) {
                StrIsAConstant = "Number";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return true;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[\\+\\-\\*/]*[a-zA-Z0-9]+"))) {
                StrIsAConstant = "IndConstant";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[\\-\\+\\*/]*[0-9\\.]+"))) {
                StrIsAConstant = "NumberExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return true;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.equalsIgnoreCase(DiffWithRespTo))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[\\+\\-\\*/]*[a-zA-Z0-9\\.]+[\\^]*[0-9\\.]+"))) {
                StrIsAConstant = "IndConstantExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.equalsIgnoreCase(DiffWithRespTo))
                    && (StrX.matches("[\\+\\-\\*\\^/]*[a-zA-Z0-9]+"))) {
                StrIsAConstant = "Var";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && ((StrX.equalsIgnoreCase(DiffWithRespTo)) //|| ((StrX.contains(DiffWithRespTo)) && ((!StrX.contains(DiffWithRespTo + "^"))))
                    )
                    && (StrX.matches("[\\+\\-\\*\\^/]*[a-zA-Z0-9]+"))) {
                StrIsAConstant = "Var";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.contains(DiffWithRespTo))
                    && (StrX.equalsIgnoreCase(DiffWithRespTo))) {
                StrIsAConstant = "VarExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrIsAConstant.equalsIgnoreCase("Unknown")) && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && ((StrX.contains("E-")) || (StrX.contains("E+")) || (StrX.contains("e+")) || (StrX.contains("e-")))
                    && (Double.isFinite(Double.valueOf(StrX)))) {
                StrIsAConstant = "Number";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return true;
            } else if (StrIsAConstant.equalsIgnoreCase("Unknown") && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.matches("[\\+\\-]*[a-zA-Z0-9\\.]+"))
                    && (StrX.matches(DiffWithRespTo))) {
                StrIsAConstant = "VarExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && ((StrX.matches("[\\+\\-]*[0-9\\.\\+\\-\\*\\^/]+[0-9\\.\\+\\-*\\^/]*"))
                    || (StrX.matches("[\\+\\-]*[0-9\\.\\*/]*\\^[0-9\\.\\*\\^/]+")))) {
                StrIsAConstant = "NumberExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return true;
            } else if ((StrIsAConstant.equalsIgnoreCase("Unknown") && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && ((StrX.matches("[\\+\\-]*[a-zA-Z0-9\\.\\*\\^/]+[\\+\\-]*[0-9\\.]*"))
                    || (StrX.matches("[\\+\\-]*[0-9\\.\\*/]*\\^[a-zA-Z0-9\\.\\*\\^/]+"))))) {
                StrIsAConstant = "IndConstantExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            }
        } catch (NumberFormatException ConstExp) {
            log.info("IsAConstant:Exception:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
        }
        log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
        return false;
    }

    public static boolean IsExtremity(String StrX1) {
        if ((!StrX1.equalsIgnoreCase("Infinity"))
                && (!StrX1.equalsIgnoreCase("-Infinity"))
                && (!StrX1.equalsIgnoreCase("+Infinity"))
                && (!StrX1.equalsIgnoreCase("NaN"))
                && (!StrX1.equalsIgnoreCase("-NaN"))
                && (!StrX1.equalsIgnoreCase("+NaN"))) {
            return false;
        }
        return true;
    }

    public static boolean IsNumber(int Mych) {
        return ((((Mych >= '0') && (Mych <= '9')) || (Mych == '.')));
    }

    public static boolean IsVariable(int Mych) {
        return (((Mych >= 'a') && (Mych <= 'z')) || ((Mych >= 'A') && (Mych <= 'Z')));
    }

    public static boolean IsIndConstant(int Mych) {
        return ((((Mych >= 'a') && (Mych <= 'z')) || ((Mych >= 'A') && (Mych <= 'Z'))) && (variables.containsKey(Mych)));
    }

    public static boolean IsNotAVariable(int Mych) {
        return (((Mych < 'a') && (Mych > 'z')) && ((Mych < 'A') && (Mych > 'Z')));
    }

    public static boolean IsOperator(int Mych) {
        return (((Mych == '+') || (Mych == '-') || (Mych == '*') || (Mych == '/') || (Mych == '^')));
    }

    public static boolean IsNotANumber(int Mych) {
        return (((Mych < '0') && (Mych > '9')) && (Mych != '.'));
    }

    public static boolean IsNotAnOperator(int Mych) {
        return (((Mych != '+') && (Mych != '-') && (Mych != '*') && (Mych != '/') && (Mych != '^')));
    }

    public static boolean IsLogicalOperator(int Mych) {
        return (((Mych == '>') || (Mych == '<') || (Mych == '=') || (Mych == '&') || (Mych == '|') || (Mych == '!')));
    }

    public static boolean IsNullEmpty(String MyString) {
        return ((MyString == null) || (MyString.equalsIgnoreCase("")) || (MyString.equalsIgnoreCase("0.0")) || (MyString.equalsIgnoreCase("+0.0")) || (MyString.equalsIgnoreCase("-0.0")));
    }

    public static boolean IsNullEmptyZero(String MyString) {
        return ((MyString == null) || (MyString.equalsIgnoreCase("")) || (MyString.equalsIgnoreCase("0.0")) || (MyString.equalsIgnoreCase("+0.0")) || (MyString.equalsIgnoreCase("-0.0")));
    }

    public static boolean IsNotNullAndEmptyAndZero(String MyString) {
        return ((MyString != null) && (!MyString.equalsIgnoreCase("")) && (!MyString.equalsIgnoreCase("0.0")) && (!MyString.equalsIgnoreCase("+0.0")) && (!MyString.equalsIgnoreCase("-0.0")));
    }

    public static boolean IsHighLvlNumberExprn(String MyString, String DiffWithRespTo) {
        int startPos = 0;
        int Mych = 0;
        if (MyString.length() >= 1) {
            Mych = MyString.charAt(startPos);
        } else if ((MyString == null) || (MyString.isEmpty())) {
            Mych = -1;
        }
        int i = 0;
        double xx = 1.0;
        boolean EnteredLoopFlag = false;
        //numbers
        while ((MyString != null) && (!MyString.equalsIgnoreCase("")) && (MyString.length() > 1) && (i < MyString.length()) && (Mych != -1) && (Mych != 0) && (IsNumber(Mych))) {
            Mych = MyString.charAt(i);
            i++;
            EnteredLoopFlag = true;
        }
        if ((EnteredLoopFlag == true) && (MyString != null) && (!MyString.equalsIgnoreCase("")) && (!MyString.equalsIgnoreCase("NaN")) && (i == MyString.length())) {
            //xx = Double.parseDouble(MyString);

            if ((MyString.contains("-")) || (MyString.contains("+")) || (MyString.contains("*")) || (MyString.contains("/"))) {
                return true;
            }
        } else if (IsAConstant(MyString, DiffWithRespTo)) {
            return false;
        }
        return false;
    }

    public static void ResetVars(String MyExpr) {
        if (MyExpr.equalsIgnoreCase("ResetSimpleExpr")) {
            //Simple Expression
            pos = -1;
            ch = -1;
            NumCoeff = 0;
            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            ContainsFuncFlag = false;
            ContainsXFlag = false;
            ContainsXFlag = false;
            ContainsIndConstFlag = false;
            GotIndConstFlag = false;
        } else if (MyExpr.equalsIgnoreCase("ResetSimpleTerm")) {
            //Simple SimpleTerm
            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            ComplexAssignFlag = false;
            TrailConstantFlag = false;
            timesLoop = 0;
            GotVariableFlag = false;
            GotExpntNumFlag = false;
            LastInvokedFlag = "";
            GotExtremityFlag = false;
            TrailConstantOperator = -1;
            LATermOperatorBraces = -1;
            LATermOperator = -1;
            LAVarTermOperator = -1;
            LAIndConstOperator = -1;
            LAPreTermOperator = -1;
            LAMAPOperator = -1;
            LADyYByDxMAPOperator = -1;
            ContainsFuncFlag = false;
            ContainsXFlag = false;
            ContainsIndConstFlag = false;
            GotIndConstFlag = false;
        } else if (MyExpr.equalsIgnoreCase("DiffExpr")) {
            //Diff Expression
            posDiff = 0;
            chDiff = 0;
            NumCoeff = 0;
            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            ParenthesisOperator = 0;
            DiffTermOperator = -1;
            LastLADiffIndConstAidPos = -1;
            LastLADiffConstCoeffAidPos = -1;
            ContainsIndConstFlag = false;
            GotExtremityFlag = false;
            ContainsFuncFlag = false;
            ContainsXFlag = false;
        } else if (MyExpr.equalsIgnoreCase("IntegrExpr")) {
            //Integre Expression
            posIntegr = 0;
            chIntegr = 0;
            NumCoeff = 0;

            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            DiffTermOperator = -1;
            LastLADiffIndConstAidPos = -1;
            LastLADiffConstCoeffAidPos = -1;
            ContainsIndConstFlag = false;
            GotExtremityFlag = false;
            GotExpntNumFlag = false;
            ParseMapposModFlag = false;
            ContainsFuncFlag = false;
            ContainsXFlag = false;
        } else if (MyExpr.equalsIgnoreCase("ResetControlVars")) {
            //End Of Simple Term Reset;
            ComplexAssignFlag = false;
            TrailConstantFlag = false;
            timesLoop = 0;
            GotVariableFlag = false;
            GotExpntNumFlag = false;
            LastInvokedFlag = "";
            GotExtremityFlag = false;
            TrailConstantOperator = -1;
            LATermOperatorBraces = -1;
            LATermOperator = -1;
            LAVarTermOperator = -1;
            LAIndConstOperator = -1;
            LAPreTermOperator = -1;
            LAMAPOperator = -1;
            LADyYByDxMAPOperator = -1;
            StrParen = "";
            ContainsFuncFlag = false;
        }
    }

    @FunctionalInterface
    public interface Expression {

        Double eval();
    }

    //Author:StackOverflow User:Boann & User :Mike Scholtes 
    // Taken from stackoverflow.com:Evaluating a math 
    // expression given in string form [closed]
    //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public static Expression parse(String str, Map<String, Double> variables, String DiffWithRespTo, String Silent, LogLevel MyLogLevel) {

        return new Object() {
            void nextCharExpr() {
                ++posExpr;
                chExpr = (posExpr < StrExpr.length()) ? StrExpr.charAt(posExpr) : -1;
                if ((chExpr == -1) && (posExpr >= StrExpr.length())) {
                    posExpr = StrExpr.length();
                } else if ((chExpr == -1) || ((chExpr == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "nextChar:nextChar:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                }
            }

            void prevCharExpr() {
                --posExpr;
                chExpr = (posExpr >= 0) ? StrExpr.charAt(posExpr) : -1;
                if ((chExpr == -1) && (posExpr < 0)) 
            ; else if ((chExpr == -1) || ((chExpr == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "prevChar:prevChar:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                }
            }

            boolean eatExpr(int CharToEat) {
                while (chExpr == ' ') {
                    nextCharExpr();
                }
                if (chExpr == CharToEat) {
                    nextCharExpr();
                    return true;
                }
                return false;
            }

            boolean IsAConstantNumber(String StrX1, String DiffWithRespTo) {

                try {
                    StrIsAConstant = "Unknown";
                    String StrX = StrX1;
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        return IsAConstant(StrX1, DiffWithRespTo);
                    } else if ((StrX != null)
                            && (!StrX.equalsIgnoreCase(""))
                            && (!StrX.contains(DiffWithRespTo))
                            && (StrX.matches("[\\-\\+]*[0-9\\.]+"))
                            //&& (!StrX.matches("[\\-\\+]*[a-zA-Z0-9]+"))
                            && (Double.isFinite(Double.valueOf(StrX)))) {
                        StrIsAConstant = "Number";
                        return true;
                    }
                } catch (NumberFormatException ConstExp) {
                    log.info("IsAConstant:Exception:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                }
                return false;
            }

            Expression parse() {
                StrExpr=eatAll(str,' ');
                Expression x = (() -> 0.0);
                if (StrExpr == null) {
                    return x;
                } else if (StrExpr.length() >= 1) {
                    posExpr = 0;
                    chExpr = StrExpr.charAt(0);
                } else if (StrExpr.length() <= 0) {
                    return x;
                }
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                ZeroTermSignFlag = false;
                if (MyLogLevel.level >= LogLevel.Info.level) {
                    log.info("Expression:parseExpression:Initial:StrExpr=" + StrExpr);
                }
                if ((StrExpr != null) && (StrExpr.length() > 0)
                        && (StrExpr.length() < 2)
                        && ((chExpr == '*')
                        || (chExpr == '/')
                        || (chExpr == '^'))) {
                    return x;
                } else if ((chExpr != 0) && (chExpr != -1)) {
                    x = parseExpression();
                }
                return x;
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            Expression parseExpression() {
                Expression x = (() -> 0.0);
                int ExprStartPos = posExpr;
                int ExprEndPos = -1;
                if (posExpr < 0) {
                    ExprStartPos = 0;
                }

                x = parseTerm();
                if (MyLogLevel.level >= LogLevel.Info.level) {
                    log.info("Expression:parseExpression:Initial:x.eval=" + x.eval());
                }
                for (;;) {
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expr:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chExpr=" + (char) chExpr + " pos=" + posExpr);
                    }
                    if (eatExpr(')')) {
                        return x;
                    } else if ((eatExpr('+'))) {// addition
                        if (eatExpr('(')) {
                            Expression a = x, b = parseExpression();
                            x = (() -> a.eval() + b.eval());
                            eatExpr(')');
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                            }
                        } else {
                            Expression a = x, b = parseTerm();
                            if (ZeroTermSignFlag == true) {
                                if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                    x = (() -> -1.0 * a.eval() + b.eval());
                                } else {
                                    x = (() -> Double.POSITIVE_INFINITY);
                                }
                                ZeroTermSignFlag = false;
                            } else {
                                if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                    x = (() -> a.eval() + b.eval());
                                } else {
                                    x = (() -> Double.POSITIVE_INFINITY);
                                }
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                            }
                        }
                    } else if ((eatExpr('-'))) { // subtraction
                        if (eatExpr('(')) {
                            Expression a = x, b = parseExpression();
                            x = (() -> a.eval() - b.eval());
                            eatExpr(')');
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                            }
                        } else {
                            Expression a = x, b = parseTerm();
                            if (ZeroTermSignFlag == true) {
                                if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                    x = (() -> -1.0 * a.eval() - b.eval());
                                } else {
                                    x = (() -> Double.POSITIVE_INFINITY);
                                }
                                ZeroTermSignFlag = false;
                            } else {
                                if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                    x = (() -> a.eval() - b.eval());
                                } else {
                                    x = (() -> Double.POSITIVE_INFINITY);
                                }
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                            }
                        }

                    } else {
                        if ((posExpr >= 0) && (posExpr < StrExpr.length())) {
                            ExprEndPos = posExpr;
                        } else {
                            ExprEndPos = StrExpr.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = StrExpr.length();
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + StrExpr.substring(ExprStartPos, ExprEndPos));
                        }
                        if (MyLogLevel.level >= LogLevel.Warn.level) {
                            log.warn("Expression:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + StrExpr.substring(ExprStartPos, ExprEndPos));
                        }
                        return x;
                    }
                }
            }

            Expression parseTerm() {
                int TermStartPos = posExpr;
                int TermEndPos = -1;
                if (posExpr < 0) {
                    TermStartPos = 0;
                }
                Expression x = parseFactor();
                if (MyLogLevel.level >= LogLevel.Info.level) {
                    log.info("Expression:parseTerm:Initial:x.eval=" + x.eval());
                }
                for (;;) {
                    Expression a = x;
                    if (eatExpr('*')) { // multiplication
                        if (eatExpr('(')) {
                            Expression b = parseExpression();
                            x = (() -> a.eval() * b.eval());
                            eatExpr(')');
                        } else {
                            Expression b = parseFactor();
                            if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                x = (() -> a.eval() * b.eval());
                            } else {
                                x = (() -> Double.POSITIVE_INFINITY);
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:*:a.eval=" + a.eval());
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:*:b.eval=" + b.eval());
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:*:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "* b.eval()=" + b.eval());
                            }
                            int temp = chExpr;
                            nextCharExpr();
                            if (((chExpr == '+') || (chExpr == '-')) && (temp != '*') && (temp != '/')) {
                                posExpr--;
                                chExpr = temp;
                                return x;
                            } else if ((chExpr != -1) && (chExpr != 0)) {
                                posExpr--;
                                chExpr = temp;
                            }
                        }
                    } else if (eatExpr('/')) { // division
                        if (eatExpr('(')) {
                            Expression b = parseExpression();
                            x = (() -> a.eval() / b.eval());
                            eatExpr(')');
                        } else {
                            Expression b = parseFactor();
                            if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                x = (() -> a.eval() / b.eval());
                            } else {
                                x = (() -> Double.POSITIVE_INFINITY);
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:/:a.eval=" + a.eval());
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:/:b.eval=" + b.eval());
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseTerm:/:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "/ b.eval()=" + b.eval());
                            }
                            int temp = chExpr;
                            nextCharExpr();
                            if (((chExpr == '+') || (chExpr == '-')) && (temp != '*') && (temp != '/')) {
                                posExpr--;
                                chExpr = temp;
                                return x;
                            } else if ((chExpr != -1) && (chExpr != 0)) {
                                posExpr--;
                                chExpr = temp;
                            }
                        }
                    } else if (eatExpr('%')) { // Modulus
                        Expression b = parseFactor();
                        if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                            x = (() -> a.eval() % b.eval());
                        } else {
                            x = (() -> Double.POSITIVE_INFINITY);
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseTerm:%:a.eval=" + a.eval());
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseTerm:%:b.eval=" + b.eval());
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseTerm:%:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "% b.eval()=" + b.eval());
                        }
                        int temp = chExpr;
                        nextCharExpr();
                        if (((chExpr == '+') || (chExpr == '-')) && (temp != '*') && (temp != '/')) {
                            posExpr--;
                            chExpr = temp;
                            return x;
                        } else if ((chExpr != -1) && (chExpr != 0)) {
                            posExpr--;
                            chExpr = temp;
                        }
                    } else {
                        if ((posExpr >= 0) && (posExpr < StrExpr.length())) {
                            TermEndPos = posExpr;
                        } else {
                            TermEndPos = StrExpr.length();
                        }
                        if ((TermStartPos > TermEndPos)) {
                            TermEndPos = StrExpr.length();
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + StrExpr.substring(TermStartPos, TermEndPos));
                        }
                        if (MyLogLevel.level >= LogLevel.Warn.level) {
                            log.warn("Expression:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + StrExpr.substring(TermStartPos, TermEndPos));
                        }
                        return x;
                    }
                }
            }

            Expression parseFactor() {
                int FactorStartPos = posExpr;
                int FactorEndPos = -1;
                if (posExpr < 0) {
                    FactorStartPos = 0;
                }
                Expression x = parseBase();
                for (;;) {
                    if (eatExpr('^')) { // exponentiation
                        if (eatExpr('(')) {
                            Expression a = x, b = parseExpression();
                            x = (() -> a.eval() * b.eval());
                            eatExpr(')');
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseFactor:x.eval=" + x.eval() + " Math.pow(a.eval()=" + a.eval() + "^ b.eval()=" + b.eval() + ")");
                            }
                        } else {
                            Expression a = x, b = parseBase();
                            if ((Double.isFinite(a.eval())) && (Double.isFinite(b.eval()))) {
                                x = (() -> Math.pow(a.eval(), b.eval()));
                            } else {
                                x = (() -> Double.POSITIVE_INFINITY);
                            }
                            if (MyLogLevel.level >= LogLevel.Info.level) {
                                log.info("Expression:parseFactor:x.eval=" + x.eval() + " Math.pow(a.eval()=" + a.eval() + "^ b.eval()=" + b.eval() + ")");
                            }
                        }
                    } else {
                        if ((posExpr >= 0) && (posExpr < StrExpr.length())) {
                            FactorEndPos = posExpr;
                        } else {
                            FactorEndPos = StrExpr.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = StrExpr.length();
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + StrExpr.substring(FactorStartPos, FactorEndPos));
                        }
                        if (MyLogLevel.level >= LogLevel.Warn.level) {
                            log.warn("Expression:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + StrExpr.substring(FactorStartPos, FactorEndPos));
                        }
                        return x;
                    }
                }
            }

            Expression parseBase() {
                int startPos = posExpr;
                Expression x = (() -> 0.0);
                if (posExpr >= StrExpr.length()) {
                    posExpr = -1;
                    chExpr = -1;
                    return x;
                }
                if ((eatExpr('-')) && (startPos != 0)) { // unary minus
                    Expression b = parseBase();
                    x = (() -> (-1.0) * b.eval());
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:-:Return Value:x.eval=" + x.eval() + " startPos=" + startPos);
                    }
                    return x;
                } else if (eatExpr('+')) { // unary plus
                    x = parseBase();
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:+:Return Value:x.eval=" + x.eval());
                    }
                    return x;
                }

                if (eatExpr('(')) { //parentheses
                    //Code to handle Expressions:
                    int POpos = posExpr;
                    do {
                        posExpr--;
                    } while ((posExpr >= 0) && (posExpr < StrExpr.length()) && (!IsOperator(StrExpr.charAt(posExpr))));
                    if ((posExpr >= 0) && (posExpr < StrExpr.length())) {
                        ParenthesisOperator = StrExpr.charAt(posExpr);
                    } else {
                        ParenthesisOperator = '+';
                    }
                    posExpr = POpos;
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    x = parseExpression();
                    eatExpr(')');
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:(:Return Value:x.eval=" + x.eval());
                    }
                    return x;
                } else if (IsNumber(chExpr)) { //numbers
                    while (IsNumber(chExpr)) {
                        nextCharExpr();
                        if ((chExpr == 'E') || (chExpr == 'e')) {
                            nextCharExpr();
                            if ((chExpr == '-') || (chExpr == '+')) {
                                nextCharExpr();
                            }
                        }
                    }
                    double xx = 1.0;
                    if ((StrExpr != null) && (!StrExpr.equalsIgnoreCase("")) && (!StrExpr.equalsIgnoreCase("NaN")) && (posExpr <= StrExpr.length()) && (startPos <= (StrExpr.length() - 1))) {
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseBase: Number=" + StrExpr.substring(startPos, posExpr));
                        }
                        if ((IsAConstantNumber(StrExpr.substring(startPos, posExpr), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            xx = Double.parseDouble(StrExpr.substring(startPos, posExpr));
                        }
                    } else {
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseBase:NaN:Exp Form: Number=" + StrExpr.substring(startPos, posExpr));
                        }
                        //xx=Double.parseDouble(String.format("%.12f"+StrRemoveEDbl(Str.substring(startPos, pos)) ) );
                        xx = Double.NaN;
                        posExpr = -1;
                        chExpr = -1;
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:Number:Return Value:x.eval=" + x.eval() + " xx=" + xx);
                    }
                    final Double XX = xx;
                    x = () -> XX;
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:Number:Return Value:x.eval=" + x.eval() + " xx=" + xx);
                    }
                    return x;
                } else if (IsVariable(chExpr)) {//functions & variables
                    while ((IsVariable(chExpr)) || (IsNumber(chExpr))) {
                        nextCharExpr();
                    }
                    if ((startPos == 0) && (StrExpr.charAt(startPos) == '-')) {
                        startPos++;
                        ZeroTermSignFlag = true;
                    }
                    String name = StrExpr.substring(startPos, posExpr).trim();
                    if (functions.containsKey(name)) {
                        DoubleUnaryOperator func = functions.get(name);
                        nextCharExpr();//get open braces
                        Expression arg = parseExpression();
                        x = () -> func.applyAsDouble(arg.eval());
                        if (chExpr == ')') {
                            nextCharExpr();
                        }
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("Expression:parseBase: Function: name=" + name + " x.eval()=" + x.eval() + " arg.eval()=" + arg.eval() + " startPos=" + startPos);
                        }
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (variables.get(name) != null)) {
                            x = () -> variables.get(name);
                        } else if (name.equalsIgnoreCase("Infinity")) {
                            x = () -> Double.POSITIVE_INFINITY;
                        } else if (name.equalsIgnoreCase("Nan")) {
                            x = () -> Double.NaN;
                        }
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase: Variable: name=" + name + " x.eval()=" + x.eval() + " startPos=" + startPos);
                    }
                    return x;
                } else if (chExpr == ')') {
                    nextCharExpr();
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:):Return Value:x.eval=" + x.eval());
                    }
                    return x;
                } else {
                    if (((IsNotAnOperator(chExpr)) && ((!IsNumber(chExpr)) && (!IsVariable(chExpr)))) && (chExpr != 0) && (chExpr != -1) && (chExpr != '(')) {
                        if ((StrExpr != null) && (startPos < StrExpr.length()) && (startPos < posExpr) && (chExpr != -1) && (chExpr != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Expression:parseBase:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                        } else if ((chExpr != -1) && (chExpr != 0) && (posExpr >= 1) && (posExpr < StrExpr.length()) && (IsNumber(chExpr))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Expression:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                        } else if ((chExpr != -1) && (chExpr != 0) && (posExpr >= 1) && (posExpr < StrExpr.length()) && (IsOperator(chExpr))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Expression:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                        } else if ((chExpr != -1) && (chExpr != 0) && (posExpr >= 1) && (posExpr < StrExpr.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Expression:parseBase:Unexpected: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Expression:parseBase:Unexpected char: " + (char) chExpr + " int=" + (int) chExpr + " Cannot Format:" + StrExpr.substring(posExpr, StrExpr.length()));
                        }
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("Expression:parseBase:Exception:Return Value:x.eval=" + x.eval());
                    }
                    return x;
                }
            }
        }.parse();
    }

//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
    @FunctionalInterface
    public interface LookAheadSign {

        int eval();
    }

    public static LookAheadSign parseSign(String str, int StartPos, String DiffWithRespTo) {

        return new Object() {
            void nextCharSign() {
                ++posSign;
                chSign = (posSign < StrSign.length()) ? StrSign.charAt(posSign) : -1;
                if ((chSign == -1) && (posSign >= StrSign.length())) {
                    posSign = StrSign.length();
                } else if ((chSign == -1) || ((chSign == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:nextCharSign:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                }
            }

            boolean eatSign(int CharToEat) {
                while (chSign == ' ') {
                    nextCharSign();
                }
                if (chSign == CharToEat) {
                    nextCharSign();
                    return true;
                }
                return false;
            }

            void SyncSign() {
                if ((posSign < StrSign.length())
                        && (StrSign.charAt(posSign) != chSign)) {
                    if (StrSign.charAt(posSign + 1) == chSign) {
                        posSign++;
                    } else if (StrSign.charAt(posSign - 1) == chSign) {
                        posSign--;
                    }
                }
            }

            LookAheadSign parseSign() {
                LookAheadSign x = (() -> -1);
                StrSign = str;
                chTermSign = 0;
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                GotBracesVarFlag = false;

                if (posTerm == -1) {
                    posSign = 0;
                    if (StrSign.length() >= 1) {
                        chSign = StrSign.charAt(posSign);
                    }
                } else if ((pos == -1)) {
                    posSign = 0;
                    chSign = StrSign.charAt(posSign);
                } else if ((StrSign.length() > 0) && (pos > 0)) {
                    posSign = 0;
                    chSign = StrSign.charAt(posSign);
                } else {
                    posSign = 0;
                    chSign = StrSign.charAt(posSign);
                }
                int startpos = posSign;
                if ((chSign == '+') || (chSign == '-')) {
                    nextCharSign();
                }
                if (StrSign.substring(posSign).isEmpty()) {
                    return x;
                }
                log.info("LookAheadSign:parseSign:StartPos=" + StartPos + " startpos=" + startpos);
                ParseLog("LookAheadSign", "parseSign", "Initial", "StrSign", StrSign);
                if ((StrSign != null) && (StrSign.length() > 0)
                        && (StrSign.length() < 2)
                        && ((chSign == '*')
                        || (chSign == '/')
                        || (chSign == '^'))) {
                    return x;
                } else {
                    x = parseTerm();
                }
                log.info("LookAheadSign:parseSign:Number:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                ParseLog("LookAheadSign", "parseSign", "BeforeMap", "StrSign", StrSign);
                ParseMap("LookAheadSign:parseSign", x.eval(), startpos, 0, DiffWithRespTo);
                ParseLog("LookAheadSign", "parseSign", "Final", "StrSign", StrSign);
                return x;
            }

            LookAheadSign parseTerm() {
                LookAheadSign x = (() -> 1);
                x = parseFactor();
                for (;;) {
                    if (eatSign('*')) { // multiplication
                        LookAheadSign a = x, b = parseFactor();
                        log.info("LookAheadSign:parseTerm:b.eval()=" + b.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                        x = (() -> (a.eval() * b.eval()));
                        log.info("LookAheadSign:parseTerm:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else if (eatSign('/')) { // division
                        LookAheadSign a = x, b = parseFactor();
                        log.info("LookAheadSign:parseTerm:b.eval()=" + b.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                        x = (() -> a.eval() / b.eval());
                        log.info("LookAheadSign:parseTerm:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else {
                        log.info("LookAheadSign:parseTerm:Return:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                        return x;
                    }
                }
            }

            LookAheadSign parseFactor() {
                LookAheadSign x = (() -> 1);
                x = parseBase();
                log.info("LookAheadSign:parseFactor:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                for (;;) {
                    if (eatSign('^')) { // exponentiation
                        LookAheadSign b = parseBase();
                        log.info("LookAheadSign:parseFactor:b.eval()=" + b.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                        //x=b;
                        log.info("LookAheadSign:parseFactor:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else {
                        return x;
                    }
                }
            }

            LookAheadSign parseBase() {
                int startPos = posSign;
                log.info("LookAheadSign:parseBase:Entered parseBase:posSign=" + posSign + " startPos=" + startPos + " chSign=" + (char) chSign);
                LookAheadSign x = (() -> 1);
                if (chSign == '(') {
                    //x = parseBase();
                    if (chSign == '(') {
                        eatSign('(');
                    } else {
                        while ((chSign > -1) && (chSign != '(')) {
                            nextCharSign();
                        }
                        eatSign('(');
                    }
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posSign;
                    log.info("LookAheadSign:parseBase:(: Expression=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    while ((posSign < StrSign.length()) && ((chSign != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharSign();
                        if (chSign == '(') {
                            openBracesCount++;
                        }
                        if (chSign == ')') {
                            closeBracesCount++;
                        }
                    }
                    eatSign(')');
                    log.info("LookAheadSign:parseBase:(: Expression=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    log.info("LookAheadSign:parseBase:(: Expression=" + StrSign.substring(startcount, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    String TempExprn = StrSign.substring(startcount, posSign);
                    if (TempExprn.startsWith("-")) {
                        x = () -> -1;
                    } else if (TempExprn.startsWith("+")) {
                        x = () -> 1;
                    } else {
                        x = () -> 1;
                    }
                    log.info("LookAheadSign:parseBase:(: Expression=" + StrSign.substring(startcount, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    return x;
                }
                if (eatSign('-')) { // unary minus
                    if ((IsNumber(chSign))) { //numbers
                        log.info("LookAheadSign:parseBase:-:Entered Numbers:posSign=" + posSign + " startPos=" + startPos + " chSign=" + (char) chSign);
                        while ((IsNumber(chSign)) && (chSign != -1)) {
                            nextCharSign();
                            if ((chSign == 'E') || (chSign == 'e')) {
                                nextCharSign();
                                if ((chSign == '-') || (chSign == '+')) {
                                    nextCharSign();
                                }
                            }
                        }
                        x = () -> -1;
                        log.info("LookAheadSign:parseBase:-:Number=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else if (IsVariable(chSign)) {//functions & variables
                        while (((IsVariable(chSign)) || (IsNumber(chSign))) && (chSign != -1)) {
                            nextCharSign();
                        }
                        x = () -> -1;
                        String FuncStr = StrSign.substring(startPos, posSign);
                        if (functionsSimple.containsKey(FuncStr)) {
                            x = parseBase();
                        }
                        log.info("LookAheadSign:parseBase:-:Variable=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    }
                    return x;
                } else if ((eatSign('+')) || (chSign == '+') || (IsNumber(chSign)) || (IsVariable(chSign))) { // unary plus
                    if ((IsNumber(chSign))) { //numbers
                        log.info("LookAheadSign:parseBase:+:Entered Numbers:posSign=" + posSign + " startPos=" + startPos + " chSign=" + (char) chSign);
                        while ((IsNumber(chSign)) && (chSign != -1)) {
                            nextCharSign();
                            if ((chSign == 'E') || (chSign == 'e')) {
                                nextCharSign();
                                if ((chSign == '-') || (chSign == '+')) {
                                    nextCharSign();
                                }
                            }
                        }
                        x = () -> 1;
                        log.info("LookAheadSign:parseBase:+:Number=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    } else if (IsVariable(chSign)) {//functions & variables
                        while (((IsVariable(chSign)) || (IsNumber(chSign))) && (chSign != -1)) {
                            nextCharSign();
                        }
                        x = () -> 1;
                        String FuncStr = StrSign.substring(startPos, posSign);
                        if (functionsSimple.containsKey(FuncStr)) {
                            x = parseBase();
                        }
                        log.info("LookAheadSign:parseBase:+:Variable=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    }
                    return x;
                } else if (eatSign('*')) { // * Operator
                    return x;
                } else if (eatSign('/')) { // / Operator
                    return x;
                } else if (eatSign('^')) { // ^ Operator
                    return x;
                } else {
                    log.info("LookAheadSign:parseBase:Exception Entered:parseBase:posSign=" + posSign + " startPos=" + startPos + " chSign=" + (char) chSign);
                    if (((IsNotAnOperator(chSign)) && ((!IsNumber(chSign)) && (!IsVariable(chSign)))) && (chSign != 0) && (chSign != -1) && (chSign != '(')) {
                        log.info("LookAheadSign:parseBase:Exception Entered:Inner:parseBase:posSign=" + posSign + " startPos=" + startPos + " chSign=" + (char) chSign);
                        if ((str != null) && (startPos < StrSign.length()) && (startPos < posSign) && (chSign != -1) && (chSign != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:parseBase:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                        } else if ((chSign != -1) && (chSign != 0) && (posSign >= 1) && (posSign < StrSign.length()) && (IsNumber(chSign))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                        } else if ((chSign != -1) && (chSign != 0) && (posSign >= 1) && (posSign < StrSign.length()) && (IsOperator(chSign))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:parseBase:Please Check Equation Operators:Should not reach Here:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                        } else if ((chSign != -1) && (chSign != 0) && (posSign >= 1) && (posSign < StrSign.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:parseBase:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadSign:parseBase:Unexpected char: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                        }
                    }
                    return x;
                }
            }
        }.parseSign();
    }
//Created by Rajesh Pai
// Converts Braces in a Term to a Single Expression

    @FunctionalInterface
    public interface LookAheadParen {

        String eval();
    }

    public static LookAheadParen parseParen(String str, int startPos1, String DiffWithRespTo, boolean MapFlag) {

        return new Object() {
            void nextCharParen() {
                ++posParen;
                chParen = (posParen < StrParen.length()) ? StrParen.charAt(posParen) : -1;
                if ((chParen == -1) && (posParen >= StrParen.length())) {
                    posParen = StrParen.length();
                } else if ((chParen == -1) || ((chParen == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadParan:nextCharParan:Unexpected: " + (char) chParen + " int=" + (int) chParen + " Cannot Format:" + StrParen.substring(posParen, StrParen.length()));
                }
            }

            boolean eatParen(int CharToEat) {
                while (chParen == ' ') {
                    nextCharParen();
                }
                if (chParen == CharToEat) {
                    nextCharParen();
                    return true;
                }
                return false;
            }

            void SyncParen() {
                if ((posParen < StrParen.length())
                        && (StrParen.charAt(posParen) != chParen)) {
                    if (StrParen.charAt(posParen + 1) == chParen) {
                        posParen++;
                    } else if (StrParen.charAt(posParen - 1) == chParen) {
                        posParen--;
                    }
                }
            }

            String ConformalMapParen(String Exprn) {
                LookAheadParen x = (() -> Exprn);
                LookAheadConst MyMappedArg = (() -> 0.0);
                if ((IsAConstant(Exprn, DiffWithRespTo))
                        && (StrIsAConstant.equalsIgnoreCase("Number"))) {

                    final Double MyDouble = Double.parseDouble(Exprn);
                    x = (() -> "" + MyDouble);
                } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                        && (StrIsAConstant.equalsIgnoreCase("Var"))) {

                    final String MyString = Exprn;
                    x = (() -> "" + MyString);
                } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                        && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                    final String MyString = Exprn;
                    x = (() -> "" + MyString);
                } else if ((IsAConstant(Exprn, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("NumberExpression"))
                        || (StrIsAConstant.equalsIgnoreCase("Number")))) {
                    String ParenExpr = Exprn;
                    log.info("LookAheadParen:parseTermParen:NumberExpression:Calling Simple:x.eval()=" + x.eval() + " Exprn=" + Exprn + " StrIsAConstant=" + StrIsAConstant + " posParen=" + posParen + " chParen=" + (char) chParen);
                    log.info("LookAheadParen:parseBase:NumberExpression:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                    ParseLog("LookAheadParen", "parseTermParen", "InitialSimple", "StrParen", StrParen);
                    Map<String, String> variablesBackupState = new HashMap<>();
                    BackupStateAll(variablesBackupState);
                    ChaosPrevention();
                    Expression MyMappedSimple = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                    ParseLog("LookAheadParen", "parseTermParen", "AfterParseSimple", "StrParen", StrParen);

                    RestoreStateParen(variablesBackupState);
                    RestoreOtherStateParen(variablesBackupState);
                    RestoreControlStateVar(variablesBackupState);
                    ParseLog("LookAheadParen", "parseTermParen", "BeforeMap", "StrParen", StrParen);
                    //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                    //ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);

                    ParseLog("LookAheadParen", "parseTermParen", "AfterRestore", "StrParen", StrParen);
                    final String MySimpleExprn = "" + MyMappedSimple.eval();
                    log.info("LookAheadParen:parseBase:NumberExpression:MySimpleExprn=" + MySimpleExprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                    if (((Exprn.contains("+")) || (Exprn.contains("-"))) && (IsAConstant(MySimpleExprn, DiffWithRespTo))) {
                        log.info("LookAheadParen:parseTermParen:NumberExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        x = (() -> MySimpleExprn);
                    } else if (((Exprn.contains("+")) || (Exprn.contains("-"))) && (MySimpleExprn.equalsIgnoreCase(Exprn))) {
                        log.info("LookAheadParen:parseTermParen:NumberExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        x = (() -> Exprn);
                    } else {
                        x = (() -> MySimpleExprn);
                    }
                    log.info("LookAheadParen:parseTermParen:NumberExpression:Complete Function String:x.eval()=" + x.eval() + " Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                } else if ((!IsAConstant(Exprn, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                    String ParenExpr = Exprn;
                    log.info("LookAheadParen:parseTermParen:VarExpression:Calling Simple:x.eval()=" + x.eval() + " Exprn=" + Exprn + " StrIsAConstant=" + StrIsAConstant + " posParen=" + posParen + " chParen=" + (char) chParen);
                    log.info("LookAheadParen:parseBase:VarExpression:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                    ParseLog("LookAheadParen", "parseTermParen", "InitialSimple", "StrParen", StrParen);
                    Map<String, String> variablesBackupState = new HashMap<>();
                    BackupStateAll(variablesBackupState);
                    ChaosPrevention();
                    SimpleExpression MyMappedSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                    ParseLog("LookAheadParen", "parseTermParen", "AfterParseSimple", "StrParen", StrParen);

                    RestoreStateParen(variablesBackupState);
                    RestoreOtherStateParen(variablesBackupState);
                    RestoreControlStateVar(variablesBackupState);
                    ParseLog("LookAheadParen", "parseTermParen", "BeforeMap", "StrParen", StrParen);
                    //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                    //ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);

                    ParseLog("LookAheadParen", "parseTermParen", "AfterRestore", "StrParen", StrParen);
                    final String MySimpleExprn = MyMappedSimple.SimpleExpr();
                    log.info("LookAheadParen:parseBase:VarExpression:MySimpleExprn=" + MySimpleExprn + " posParen=" + posParen + " chParen=" + (char) chParen);

                    if (((Exprn.contains("+")) || (Exprn.contains("-"))) && ((!MySimpleExprn.contains("+")) && (!MySimpleExprn.contains("-")))) {
                        log.info("LookAheadParen:parseTermParen:VarExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        x = (() -> MySimpleExprn);
                    } else if (((Exprn.contains("+")) || (Exprn.contains("-"))) && (MySimpleExprn.equalsIgnoreCase(Exprn))) {
                        log.info("LookAheadParen:parseTermParen:VarExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        x = (() -> Exprn);
                    } else {
                        x = (() -> MySimpleExprn);
                    }
                    log.info("LookAheadParen:parseTermParen:VarExpression:Complete Function String:x.eval()=" + x.eval() + " Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                } else if ((!IsAConstant(Exprn, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))) {
                    String ParenExpr = Exprn;
                    log.info("LookAheadParen:parseTermParen:IndConstantExpression:Calling Simple:x.eval()=" + x.eval() + " Exprn=" + Exprn + " StrIsAConstant=" + StrIsAConstant + " posParen=" + posParen + " chParen=" + (char) chParen);
                    log.info("LookAheadParen:parseTermParen:IndConstantExpression:Calling Simple:x.eval()=" + x.eval() + " Exprn=" + Exprn + " StrIsAConstant=" + StrIsAConstant + " posParen=" + posParen + " chParen=" + (char) chParen);
                    log.info("LookAheadParen:parseBase:IndConstantExpression:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                    ParseLog("LookAheadParen", "parseTermParen", "InitialSimple", "StrParen", StrParen);
                    Map<String, String> variablesBackupState = new HashMap<>();
                    BackupStateAll(variablesBackupState);
                    ChaosPrevention();
                    SimpleExpression MyMappedSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                    ParseLog("LookAheadParen", "parseTermParen", "AfterParseSimple", "StrParen", StrParen);
                    RestoreStateParen(variablesBackupState);
                    RestoreOtherStateParen(variablesBackupState);
                    RestoreControlStateVar(variablesBackupState);
                    ParseLog("LookAheadParen", "parseTermParen", "BeforeMap", "StrParen", StrParen);
                    //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                    //ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);
                    ParseLog("LookAheadParen", "parseTermParen", "AfterRestore", "StrParen", StrParen);
                    final String MySimpleExprn = MyMappedSimple.SimpleExpr();
                    log.info("LookAheadParen:parseBase:IndConstantExpression:MySimpleExprn=" + MySimpleExprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                    if ((Exprn.contains("+")) || (Exprn.contains("-"))) {
                        log.info("LookAheadParen:parseTermParen:IndConstantExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        x = (() -> Exprn);
                    } else {
                        x = (() -> Exprn);
                    }
                    log.info("LookAheadParen:parseTermParen:IndConstantExpression:Complete Function String:x.eval()=" + x.eval() + " Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                } else {
                    final String MyString = Exprn;
                    if ((StrParen != null) && (posParen < (StrParen.length() - 1)) && (chParen != -1) && (chParen != 0)) {
                        x = (() -> MyString);
                    } else if ((StrParen != null) && (posParen < (StrParen.length() - 1))) {
                        x = (() -> MyString);
                    }
                }
                return x.eval();
            }

            LookAheadParen parseParen() {
                StrParen = str;
                log.info("LookAheadParen:parseParan:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posParen=" + posParen + " chParen=" + (char) chParen + " ComplexAssignFlag=" + ComplexAssignFlag + " StrParen=" + StrParen);
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                GotBracesVarFlag = false;
                LookAheadParen x = (() -> "");
                TermOutput = 0.0;
                TermOperatorPos = -1;

                if (StrParen == null) {
                    return x;
                } else if ((StrParen.length() >= 1) && (GotParenPos < StrParen.length()) && (GotParenPos >= 0)) {
                    posParen = GotParenPos;
                    chParen = StrParen.charAt(posParen);
                } else if ((StrParen.length() >= 1) && (GotParenPos == -1)) {
                    posParen = 0;
                    chParen = StrParen.charAt(0);
                } else if (StrParen.length() <= 0) {
                    return x;
                } else {
                    posParen = 0;
                    chParen = StrParen.charAt(0);
                }
                int StartPos = posParen;
                log.info("LookAheadParen:parseParan:StartPos=" + StartPos + " posParen=" + posParen + " chParen=" + (char) chParen + " ComplexAssignFlag=" + ComplexAssignFlag);
                ParseLog("LookAheadParen", "parseParen", "Initial", "StrParen", StrParen);
                if ((StrParen != null) && (StrParen.length() > 0)
                        && (StrParen.length() < 2)
                        && ((chParen == '*')
                        || (chParen == '/')
                        || (chParen == '^'))) {
                    return x;
                } else {
                    x = parseExpression();//parseTerm();//parseExpression();
                }
                log.info("LookAheadParen:parseParen:Paren:x.eval()=" + x.eval() + " ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag);
                log.info("LookAheadParen:parseParen:posParen=" + posParen + " chParen=" + (char) chParen);

                return x;
            }

            LookAheadParen parseExpression() {
                LookAheadParen x = (() -> "");
                if (chParen == '(') {
                    eatParen('(');
                } else {
                    while ((chParen > -1) && (chParen != '(')) {
                        nextCharParen();
                    }
                    eatParen('(');
                }
                int openBracesCount = 1;
                int closeBracesCount = 0;
                int startcount = posParen;
                if ((LATermOperatorBraces == -1) && (posParen >= 2) && (StrParen.charAt(posParen - 1) == '(') && ((StrParen.charAt(posParen - 2) == '*') || (StrParen.charAt(posParen - 2) == '/'))) {
                    LATermOperatorBraces = StrParen.charAt(posParen - 2);
                } else if ((LATermOperatorBraces == -1) && (posParen > 0) && (StrParen.charAt(posParen - 1) == '(')) {
                    LATermOperatorBraces = '*';
                } else if ((LATermOperatorBraces == -1) && ((posParen == 0) || (posParen == -1)) && (StrParen.charAt(0) == '(')) {
                    LATermOperatorBraces = '*';
                } else {
                    LATermOperatorBraces = '*';
                    chParen = StrParen.charAt(StrParen.length() - 1);
                }
                while ((posParen < StrParen.length()) && ((chParen != ')') || (openBracesCount != closeBracesCount))) {
                    nextCharParen();
                    if (chParen == '(') {
                        openBracesCount++;
                    }
                    if (chParen == ')') {
                        closeBracesCount++;
                    }
                }
//                if ((chParen == ')') && (StrParen.substring(startcount, posParen).contains(")"))) {
//                    posParen--;
//                }
                String Exprn = StrParen.substring(startcount, posParen);
                eatParen(')');
                //if((Exprn.contains("("))&&(Exprn.contains(")")))
                x = parseTermParen(Exprn);
                //else
                //    x=(()-> Exprn);
                log.info("LookAheadParen:parseExpression:Paren:Exprn=" + Exprn);
                log.info("LookAheadParen:parseExpression:Paren:x.eval()=" + x.eval() + " ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag);
                log.info("LookAheadParen:parseExpression:posParen=" + posParen + " chParen=" + (char) chParen);

                return x;
            }

            LookAheadParen parseTermParen(String Exprn) {
                LookAheadParen x = (() -> "");
                log.info("LookAheadParen:parseTermParen:Paren:Exprn=" + Exprn);
                log.info("LookAheadParen:parseTermParen:Paren:x.eval()=" + x.eval());
                //if (MapFlag == true) {
                ParseLog("LookAheadParen", "parseTermParen", "BeforeConditions", "StrParen", StrParen);
                Exprn = ConformalMapParen(Exprn);
                //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                log.info("LookAheadParen:parseBase:posParen=" + posParen + " chParen=" + (char) chParen);
                ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);
                //posTerm = posParen + 1;
                //chTerm = StrTerm.charAt(posTerm);
                //}
                log.info("LookAheadParen:parseTermParen:Paren:Exprn=" + Exprn);
                log.info("LookAheadParen:parseTermParen:Paren:x.eval()=" + x.eval() + " ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag);
                log.info("LookAheadParen:parseTermParen:posParen=" + posParen + " chParen=" + (char) chParen);
                final String Exprn1 = Exprn;
                x = (() -> Exprn1);
                return x;
            }

        }.parseParen();
    }
//Created by Rajesh Pai
// Gets Maximum Exponent of a  Variables of a Term for Integration by Parts

    @FunctionalInterface
    public interface OrderVar {

        String eval();
    }

    public static OrderVar parseOrder(String str, String DiffWithRespTo) {

        return new Object() {
            void nextCharOrder() {
                ++posOrder;
                chOrder = (posOrder < StrOrder.length()) ? StrOrder.charAt(posOrder) : -1;
                if ((chOrder == -1) && (posOrder >= StrOrder.length())) {
                    posOrder = StrOrder.length();
                } else if ((chOrder == -1) || ((chOrder == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:nextCharOrder:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                }
            }

            boolean eatOrder(int CharToEat) {
                while (chOrder == ' ') {
                    nextCharOrder();
                }
                if (chOrder == CharToEat) {
                    nextCharOrder();
                    return true;
                }
                return false;
            }

            void SyncOrder() {
                if ((posOrder < StrOrder.length())
                        && (StrOrder.charAt(posOrder) != chOrder)) {
                    if (StrOrder.charAt(posOrder + 1) == chOrder) {
                        posOrder++;
                    } else if (StrOrder.charAt(posOrder - 1) == chOrder) {
                        posOrder--;
                    }
                }
            }

            OrderVar parseOrder() {
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                StrOrder = str;
                posOrder = 0;
                OrderVar x = (() -> "");
                if ((StrOrder != null) && (!StrOrder.equalsIgnoreCase(""))) {
                    chOrder = StrOrder.charAt(posOrder);
                } else {
                    return x;
                }
                MaxTermOutput = 0;
                int StartPos = posOrder;
                log.info("OrderVar:parseVar:StartPos=" + StartPos + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                ParseLog("OrderVar", "parseOrder", "Initial", "StrOrder", StrOrder);
                if ((StrOrder != null) && (StrOrder.length() > 0)
                        && (StrOrder.length() < 2)
                        && ((chOrder == '*')
                        || (chOrder == '/')
                        || (chOrder == '^'))) {
                    return x;
                } else {
                    x = parseExpression();
                }
                if ((MaxTermOutput != 1.0) && (MaxTermOutput != 0.0)) {
                    x = (() -> RemoveEDbl(MaxTermOutput));
                } else {
                    x = (() -> "" + 1);
                }
                log.info("OrderVar:parseOrder:x.eval()=" + x.eval());
                log.info("OrderVar:parseOrder:posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                ParseLog("OrderVar", "parseOrder", "Final", "StrOrder", StrOrder);
                return x;
            }

            OrderVar parseExpression() {
                OrderVar x = (() -> "");
                for (;;) {
                    log.info("OrderVar:parseExpression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chOrder=" + (char) chOrder + " x.eval()=" + x.eval());
                    if ((posOrder >= 1) && (posOrder < StrOrder.length())) {
                        log.info("OrderVar:parseExpression:StrOrder=" + StrOrder.substring(posOrder));
                    }
                    if (eatOrder(')')) {
                        return x;
                    } else if (((eatOrder('+')) || ((NormalNotPareFlag == true) && (ParenthesisFlag == true))) || (posOrder == 0)) {// addition
                        //if (ParenthesisFlag == false) {
                        TermOutput = 0.0;
                        //}
                        OrderVar a = x, b = parseTerm();
                        x = b;
                        log.info("OrderVar:parseExpression:MaxTermOutput=" + MaxTermOutput + " TermOutput=" + TermOutput);
                        log.info("OrderVar:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                    } else if ((eatOrder('-')) || ((NormalNotPareFlag == true) && (ParenthesisFlag == true))) { // subtraction
                        //if (ParenthesisFlag == false) {
                        TermOutput = 0.0;
                        //}
                        OrderVar a = x, b = parseTerm();
                        x = b;
                        log.info("OrderVar:parseExpression:MaxTermOutput=" + MaxTermOutput + " TermOutput=" + TermOutput);
                        log.info("OrderVar:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '+')) {
                        OrderVar a = x, b = parseTerm();
                        x = b;
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '-')) {
                        OrderVar a = x, b = parseTerm();
                        x = b;
                    } else {
                        return x;
                    }
                }
            }

            OrderVar parseTerm() {
                OrderVar x = (() -> "");
                x = parseFactor();
                log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                for (;;) {
                    if ((posOrder >= 1) && (posOrder < StrOrder.length())) {
                        log.info("OrderVar:parseTerm:StrOrder=" + StrOrder.substring(posOrder));
                    }
                    if ((eatOrder('*')) || (posOrder == 0)) { // multiplication
                        OrderVar a = x, b = parseFactor();
                        if ((TermOutputSetFlag == false) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput += 1.0;
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                            }
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        } else if (!b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput += 1.0;
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                            }
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        }
                        log.info("OrderVar:parseTerm:Multiply:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                        int temp = chOrder;
                        nextCharOrder();
                        if (((chOrder == '+') || (chOrder == '-')) && (temp != '*') && (temp != '/')) {
                            posOrder--;
                            chOrder = temp;
                            return x;
                        } else if ((chOrder != -1) && (chOrder != 0)) {
                            posOrder--;
                            chOrder = temp;
                        } else if ((chOrder == -1) || (chOrder == 0)) {
                            break;
                        }
                    } else if (eatOrder('/')) { // division
                        OrderVar a = x, b = parseFactor();
                        if ((TermOutputSetFlag == false) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput -= 1.0;
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                            }
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        } else if (!b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput -= 1.0;
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                            }
                            x = b;
                            log.info("OrderVar:parseTerm:x.eval()=" + x.eval());
                        }
                        log.info("OrderVar:parseTerm:Divide:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                        int temp = chOrder;
                        nextCharOrder();
                        if ((chOrder == '+') || (chOrder == '-')) {
                            posOrder--;
                            chOrder = temp;
                            return x;
                        } else if ((chOrder != -1) && (chOrder != 0)) {
                            posOrder--;
                            chOrder = temp;
                        } else if ((chOrder == -1) || (chOrder == 0)) {
                            break;
                        }
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '*')) {
                        OrderVar a = x, b = parseFactor();
                        x = (() -> a.eval() + "*" + b.eval());
                        log.info("OrderVar:parseTerm:ParenthesisFlag:*:x.eval()=" + x.eval());
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '/')) {
                        OrderVar a = x, b = parseFactor();
                        x = (() -> a.eval() + "/" + b.eval());
                        log.info("OrderVar:parseTerm:ParenthesisFlag:/:x.eval()=" + x.eval());
                    } else {
                        return x;
                    }
                }
                return x;
            }

            OrderVar parseFactor() {
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                if ((posOrder >= 1) && (posOrder < StrOrder.length())) {
                    log.info("OrderVar:parseFactor:Initial:StrOrder=" + StrOrder.substring(posOrder));
                }
                OrderVar x = parseBase();
                log.info("OrderVar:parseFactor:x.eval()=" + x.eval());
                for (;;) {
                    if ((posOrder >= 1) && (posOrder < StrOrder.length())) {
                        log.info("OrderVar:parseFactor:Loop:StrOrder=" + StrOrder.substring(posOrder));
                    }
                    if (eatOrder('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        OrderVar a = x, b = parseBase();
                        GotExpntNumFlag = false;
                        if (!b.eval().equalsIgnoreCase("")) {
                            if (IsAConstant(b.eval(), DiffWithRespTo)) {
                                if (Double.parseDouble(b.eval()) >= TermOutput) {
                                    TermOutput = Double.parseDouble(b.eval());
                                } else {
                                    TermOutput += Double.parseDouble(b.eval());
                                }
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                                TermOutputSetFlag = true;
                            }
                        } else {
                            x = a;
                        }
                        log.info("OrderVar:parseFactor:Exponent:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '^')) {
                        OrderVar a = x, b = parseBase();
                        if (!b.eval().equalsIgnoreCase("")) {
                            if (IsAConstant(b.eval(), DiffWithRespTo)) {
                                TermOutput = Double.parseDouble(b.eval());
                                if ((TermOutput >= 0) && (MaxTermOutput < TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                } else if ((TermOutput <= 0) && (MaxTermOutput > TermOutput)) {
                                    MaxTermOutput = TermOutput;
                                }
                                TermOutputSetFlag = true;
                            }
                        } else {
                            x = a;
                        }
                        log.info("OrderVar:parseFactor:ParenthesisFlag:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                    } else {
                        return x;
                    }
                }
            }

            OrderVar parseBase() {
                int startPos = posOrder;
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                OrderVar x = (() -> "");
                if (eatOrder('-')) { // unary minus
                    OrderVar b = parseBase();
                    if (GotExpntNumFlag == true) {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "-" + (RemoveEDbl(Double.parseDouble(b.eval()))));
                        } else {
                            x = (() -> "-" + b.eval());
                        }
                        log.info("OrderVar:parseBase:Number:x.eval()=" + x.eval() + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    } else {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "" + (RemoveEDbl(Double.parseDouble(b.eval()))));
                        } else {
                            x = (() -> b.eval());
                        }
                        log.info("OrderVar:parseBase:Number:x.eval()=" + x.eval() + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    }
                    return x;
                } else if (eatOrder('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chOrder))) { //numbers
                    while ((IsNumber(chOrder)) && (chOrder != -1)) {
                        nextCharOrder();
                        if ((chOrder == 'E') || (chOrder == 'e')) {
                            nextCharOrder();
                            if ((chOrder == '-') || (chOrder == '+')) {
                                nextCharOrder();
                            }
                        }
                    }
                    double xx = 1.0;
                    if ((GotExpntNumFlag == true) && (posOrder != -1) && (StrOrder != null) && (!StrOrder.equalsIgnoreCase("")) && (!StrOrder.equalsIgnoreCase("NaN"))) {
                        log.info("OrderVar:parseBase:Expnt Num:Number:xx=" + xx + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                        xx = Double.parseDouble(StrOrder.substring(startPos, posOrder));
                    } else if ((GotExpntNumFlag == true) && (StrOrder != null)
                            && (!StrOrder.equalsIgnoreCase(""))
                            && (!StrOrder.equalsIgnoreCase("NaN"))) {
                        log.info("OrderVar:parseBase:Expnt Num:Number:xx=" + xx + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                        xx = Double.parseDouble(StrOrder.substring(startPos));
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false)
                            && (timesLoop == 0) && (startPos != posOrder) && (posOrder != -1)
                            && (StrOrder != null) && (!StrOrder.equalsIgnoreCase(""))
                            && (!StrOrder.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrOrder.substring(startPos, posOrder));
                        log.info("OrderVar:parseBase:Trailing Constant:Number:xx=" + xx + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    } else {
                        xx = 1.0;
                    }
                    log.info("OrderVar:parseBase:Number:xx=" + xx + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    final Double XX = xx;
                    if (XX >= 1.0) {
                        final String MyDBL = RemoveEDbl(XX);
                        x = () -> MyDBL;
                    } else if (XX > 0.0) {
                        final String MyDBL = RemoveEDbl(XX);
                        x = () -> MyDBL;
                    } else if ((XX < 0.0) && (XX > -1)) {
                        final String MyDBL = RemoveEDbl(XX);
                        x = () -> MyDBL;
                    } else {
                        x = () -> "";
                    }
                    if ((chOrder == ')') && (posOrder == (StrOrder.length() - 1))) {
                        chOrder = -1;
                        posOrder++;
                        ParenthesisOperator = 0;
                        ParenthesisFlag = false;
                    } else if (chOrder == ')') {
                        nextCharOrder();
                        ParenthesisOperator = 0;
                        ParenthesisFlag = false;
                    }
                    log.info("OrderVar:parseBase:Number:xx=" + xx + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    return x;
                } else if (IsVariable(chOrder)) {//functions & variables
                    while (((IsVariable(chOrder)) || (IsNumber(chOrder))) && (chOrder != -1)) {
                        nextCharOrder();
                    }
                    String name = "";
                    if (posOrder != -1) {
                        name = StrOrder.substring(startPos, posOrder).trim();
                    } else {
                        name = StrOrder.substring(startPos).trim();
                    }
                    log.info("OrderVar:parseBase:Variable:name=" + name + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN"))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                    }
                    if ((chOrder == ')') && (posOrder == (StrOrder.length() - 1))) {
                        chOrder = -1;
                        posOrder++;
                        ParenthesisOperator = 0;
                        ParenthesisFlag = false;
                    } else if (chOrder == ')') {
                        nextCharOrder();
                        ParenthesisOperator = 0;
                        ParenthesisFlag = false;
                    }
                    log.info("OrderVar:parseBase:Variable:name=" + name + " posOrder=" + posOrder + " chOrder=" + (char) chOrder);
                    return x;
                } else if (chOrder == '(') {
                    ParenthesisFlag = true;
                    nextCharOrder();
                    ParenthesisOperator = chOrder;
                    x = parseExpression();//parseBase();
                    return x;
                } else if (chOrder == ')') {
                    ParenthesisOperator = 0;
                    ParenthesisFlag = false;
                    nextCharOrder();
                    return x;
                } else {
                    if (((IsNotAnOperator(chOrder)) && ((!IsNumber(chOrder)) && (!IsVariable(chOrder)))) && (chOrder != 0) && (chOrder != -1) && (chOrder != '(')) {
                        if ((StrOrder != null) && (startPos < StrOrder.length()) && (startPos < posOrder) && (chOrder != -1) && (chOrder != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:parseBase:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                        } else if ((chOrder != -1) && (chOrder != 0) && (posOrder >= 1) && (posOrder < StrOrder.length()) && (IsNumber(chOrder))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                        } else if ((chOrder != -1) && (chOrder != 0) && (posOrder >= 1) && (posOrder < StrOrder.length()) && (IsOperator(chOrder))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                        } else if ((chOrder != -1) && (chOrder != 0) && (posOrder >= 1) && (posOrder < StrOrder.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:parseBase:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OrderVar:parseBase:Unexpected char: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                        }
                    }
                    return x;
                }
            }
        }.parseOrder();
    }

//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
    @FunctionalInterface
    public interface LookAheadConst {

        double eval();
    }

    public static LookAheadConst parseLA(String str, int StartPos, Map<String, Double> variables, String DiffWithRespTo, boolean MapFlag) {

        return new Object() {
            void nextCharLA() {
                ++posLA;
                chLA = (posLA < StrLA.length()) ? StrLA.charAt(posLA) : -1;
                if ((chLA == -1) && (posLA >= StrLA.length())) {
                    posLA = StrLA.length();
                } else if ((chLA == -1) || ((chLA == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:nextCharLA:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA, StrLA.length()));
                }
            }

            boolean eatLA(int CharToEat) {
                while (chLA == ' ') {
                    nextCharLA();
                }
                if (chLA == CharToEat) {
                    nextCharLA();
                    return true;
                }
                return false;
            }

            void SyncLA() {
                if ((posLA < StrLA.length())
                        && (StrLA.charAt(posLA) != chLA)) {
                    if (StrLA.charAt(posLA + 1) == chLA) {
                        posLA++;
                    } else if (StrLA.charAt(posLA - 1) == chLA) {
                        posLA--;
                    }
                }
            }

            LookAheadConst parseLAExpr() {
                StrLA = str;
                LookAheadConst x = (() -> 0.0);
                LAConstSimpleMapFlag = false;
                LAConstComplexMapFlag = false;
                if (ComplexAssignFlag == true) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    StrLA = StrVar;
                    posLA = GotVariablePos;
                    chLA = StrLA.charAt(GotVariablePos);
                    if ((posLA > 0) && (StrLA.charAt(posLA - 1) != '/')) {
                        LAPreTermOperator = -1;
                        log.info("LookAheadConst:parseLA:StartPos=" + StartPos + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag);
                    } else if (posLA > 0) {
                        LAPreTermOperator = '/';
                        log.info("LookAheadConst:parseLA:StartPos=" + StartPos + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag);
                    }
                } else if ((pos == -1)) {
                    GotVariableFlag = false;
                    GotIndConstFlag = false;
                    posLA = -1;
                    if (StrLA.length() >= 1) {
                        chLA = StrLA.charAt(0);
                    } else {
                        chLA = -1;
                    }
                } else {
                    //posTerm=StartPos;
                    posLA = -1;
                    if (StrLA.length() >= 1) {
                        chLA = StrLA.charAt(0);
                    } else {
                        chLA = -1;
                    }
                }
                int startpos = posLA;
                log.info("LookAheadConst:parseLA:StartPos=" + StartPos + " startpos=" + startpos + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag);
                ParseLog("LookAheadConst", "parseLA", "Initial", "StrLA", StrLA);
                if ((StrLA != null) && (StrLA.length() > 0)
                        && (StrLA.length() < 2)
                        && ((chLA == '*')
                        || (chLA == '/')
                        || (chLA == '^'))) {
                    return x;
                } else if ((StrLA != null) && (StrLA.length() >= 0)
                        && (StrLA.length() <= 1)) {
                    return x;
                } else {
                    x = parseTerm();
                }

                log.info("LookAheadConst:parseLA:Overall Number:x.eval()=" + x.eval() + " LAPreTermOperator=" + (char) LAPreTermOperator + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                if (MapFlag == true) {
                    ParseLog("LookAheadConst", "parseLA", "BeforeMap", "StrLA", StrLA);
                    ParseMap("LookAheadConst:parseLA", StrLA.length(), 0, 0, DiffWithRespTo);
                    ParseLog("LookAheadConst", "parseLA", "Final", "StrLA", StrLA);
                }
//                if ((posLA != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(posLA, str.length()).equalsIgnoreCase(""))&&(posLA <= str.length())) {
//                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parse:Unexpected: " + (char) chLA + " int=" + (int) chLA + " posLA=" + posLA + " str=" + str.substring(posLA, str.length()));
//                }
                return x;
            }

            LookAheadConst parseTerm() {
                double Output = 1.0;
                int StartPos = posLA;
                boolean InitialTermOp = true;
                boolean InitialHighLvlMapOp = false;
                boolean InitialHighLvlDyDxMapOp = false;
                double HighLvlMapOutputNum = 1.0;
                double HighLvlMapOutputFirstDeno = 1.0;
                double HighLvlMapOutputDeno = 1.0;
                double HighLvlMapOutput = 1.0;
                LookAheadConst x = (() -> 1.0);
                x = parseFactor();
                int HPStartPos1 = posLA;
                log.info("LookAheadConst:parseTerm:Initial:0:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                if ((InitialTermOp == true) && (StartPos >= 1) && (StrLA.charAt(StartPos - 1) == '/')) {
                    log.info("LookAheadConst:parseTerm:Divide:HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    HighLvlMapOutputFirstDeno = x.eval();
                    HighLvlMapOutput = x.eval();
                    final Double Temp = x.eval();
                    x = (() -> 1.0 / Temp);
                    InitialTermOp = false;
                    InitialHighLvlMapOp = true;
                    InitialHighLvlDyDxMapOp = true;
                    log.info("LookAheadConst:parseTerm:Divide:HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                }
                log.info("LookAheadConst:parseTerm:Initial:1:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                int LATermOperatorInternal = -1;
                for (;;) {
                    GotIndConstFlag = false;
                    GotVariableFlag = false;
                    HaltingProblem("LookAheadConst:parseTerm:", posLA - HPStartPos1, chLA, "StrLA", StrLA, posLA, HPStartPos1);
                    if (eatLA('*') || (chLA == ')')) { // multiplication

                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        HPStartPos1 = posLA;
                        log.info("LookAheadConst:parseTerm:Multiply:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:Multiply:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (b.eval() != 1.0) {
                            HighLvlMapOutputNum = HighLvlMapOutputNum * b.eval();
                            log.info("LookAheadConst:parseTerm:Divide:a.eval()=" + a.eval() + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            log.info("LookAheadConst:parseTerm:Divide:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        }
                        if (GotVariableFlag == true) {
                            LATermOperator = '*';
                            GotVariableFlag = false;
                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((a.eval() != 0.0) && (IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            if (LATermOperatorInternal == -1) {
                                x = (() -> (a.eval() * b.eval()));
                                log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            } else if (LATermOperatorInternal == '/') {
                                if (b.eval() != 1.0) {
                                    HighLvlMapOutputNum = b.eval();
                                    x = (() -> b.eval() / a.eval());
                                    LATermOperatorInternal = -1;
                                    LAConstSimpleMapFlag = false;
                                    LAMAPOperator = -1;
                                    LAPreTermOperator = '*';
                                    log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " b.eval()=" + b.eval() + " a.eval()=" + a.eval() + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                } else if ((b.eval() == 1.0) && (LAConstSimpleMapFlag == true)) {
                                    x = (() -> a.eval());
                                    log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                }
                            }
                            InitialHighLvlMapOp = false;
                            log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                ;//LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((GotBracesVarFlag == false) && ((chLA == '+') || (chLA == '-'))) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Multiply:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    log.info("LookAheadConst:parseTerm:Multiply:Pre-Final:x.eval()=" + x.eval() + " a.eval()=" + a.eval() + " b.eval()=" + b.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                    //final Double Temp1 = 1.0 / x.eval();
                                    final Double Temp1 = HighLvlMapOutputFirstDeno * HighLvlMapOutputDeno;
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Multiply:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if ((GotBracesVarFlag == true) && ((chLA == '+') || (chLA == '-'))) {
                            log.info("LookAheadConst:parseTerm:Multiply:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            log.info("LookAheadConst:parseTerm:Multiply:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            return x;
                        } else if (chLA == ')') {
                            nextCharLA();
                            if ((chLA == '+') || (chLA == '-')) {
                                log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            } else {
                                SyncLA();
                                continue;
                            }
                        } else if ((chLA != -1) && (chLA != 0)) {
                            SyncLA();
                        }
                        log.info("LookAheadConst:parseTerm:Multiply:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if (eatLA('/') || (chLA == ')')) { // division
                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        HPStartPos1 = posLA;
                        log.info("LookAheadConst:parseTerm:Divide:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:Divide:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (b.eval() != 1.0) {
                            HighLvlMapOutputDeno = HighLvlMapOutputDeno * b.eval();
                            log.info("LookAheadConst:parseTerm:Divide:a.eval()=" + a.eval() + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            log.info("LookAheadConst:parseTerm:Divide:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        }
                        if (GotVariableFlag == true) {
                            if (b.eval() != 1.0) {
                                LATermOperator = '/';
                            }
                            GotVariableFlag = false;

                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((LATermOperatorInternal == '/') && (b.eval() != 1.0)) {
                            x = (() -> (a.eval() * b.eval()));
                            log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        } else if ((a.eval() != 0.0) && ((a.eval() != 1.0)) && (IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> a.eval() / b.eval());
                            InitialHighLvlMapOp = false;
                            log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        } else if (((a.eval() == 1.0) && (IsAConstant("" + b.eval(), DiffWithRespTo)) && (b.eval() != 0.0)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> b.eval());
                            if ((b.eval() != 1.0)) {
                                LAConstSimpleMapFlag = true;
                                LAPreTermOperator = '/';
                                LATermOperatorInternal = '/';
                            }
                            log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " LAConstSimpleMapFlag=" + LAConstSimpleMapFlag + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LATermOperatorInternal=" + LATermOperatorInternal + " posLA=" + posLA + " chLA=" + (char) chLA);
                        } else if ((a.eval() == 1.0) && (b.eval() == 0.0)) {
                            x = (() -> Double.POSITIVE_INFINITY);
                        } else if ((a.eval() == -1.0) && (b.eval() == 0.0)) {
                            x = (() -> Double.NEGATIVE_INFINITY);
                        } else if ((a.eval() == 0.0) && (b.eval() == 0.0)) {
                            x = (() -> Double.NaN);
                        }

                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1) && (LAPreTermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                //LAPreTermOperator = chLA;
                                log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            }
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((GotBracesVarFlag == false) && ((chLA == '+') || (chLA == '-'))) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Divide:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Divide:Final:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if ((GotBracesVarFlag == true) && ((chLA == '+') || (chLA == '-'))) {
                            log.info("LookAheadConst:parseTerm:Divide:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            log.info("LookAheadConst:parseTerm:Divide:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                            return x;
                        } else if (chLA == ')') {
                            nextCharLA();
                            SyncLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {
                            SyncLA();
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if (((GotBracesVarFlag == true) && (eatLA('+'))) || (chLA == ')')) { // addition in braces
                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        log.info("LookAheadConst:parseTerm:Divide:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:Divide:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (GotVariableFlag == true) {
                            LATermOperator = '/';
                            GotVariableFlag = false;

                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> a.eval() + b.eval());
                            InitialHighLvlMapOp = false;
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((chLA == '+') || (chLA == '-')) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Divide:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Divide:Final:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if (chLA == ')') {
                            nextCharLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {
                            SyncLA();
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if (((GotBracesVarFlag == true) && (eatLA('-'))) || (chLA == ')')) {// subtraction in braces
                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        log.info("LookAheadConst:parseTerm:Divide:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:Divide:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (GotVariableFlag == true) {
                            LATermOperator = '/';
                            GotVariableFlag = false;

                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> a.eval() - b.eval());
                            InitialHighLvlMapOp = false;
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((chLA == '+') || (chLA == '-')) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Divide:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Divide:Final:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if (chLA == ')') {
                            nextCharLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {
                            SyncLA();
                        }
                        log.info("LookAheadConst:parseTerm:Divide:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if (((GotBracesVarFlag == true) && (eatLA('^'))) || (chLA == ')')) {// exponent in braces
                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        log.info("LookAheadConst:parseTerm:Exponent:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:Exponent:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (GotVariableFlag == true) {
                            LATermOperator = '/';
                            GotVariableFlag = false;

                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> Math.pow(a.eval(), b.eval()));
                            InitialHighLvlMapOp = false;
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:Exponent:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((chLA == '+') || (chLA == '-')) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Exponent:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Exponent:Final:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if (chLA == ')') {
                            nextCharLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {
                            SyncLA();
                        }
                        log.info("LookAheadConst:parseTerm:Exponent:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else {
                        log.info("LookAheadConst:parseTerm:Else:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                            final Double Temp1 = HighLvlMapOutput;
                            x = (() -> Temp1);
                        } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false)) {
                            final Double Temp1 = 1.0 / x.eval();
                            x = (() -> Temp1);
                        }
                        log.info("LookAheadConst:parseTerm:Else:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutputNum=" + HighLvlMapOutputNum + " HighLvlMapOutput=" + HighLvlMapOutput + " HighLvlMapOutputFirstDeno=" + HighLvlMapOutputFirstDeno + " HighLvlMapOutputDeno=" + HighLvlMapOutputDeno + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        return x;
                    }
                }
            }

            LookAheadConst parseFactor() {
                int StartPos1 = posLA;
                LookAheadConst x = (() -> 1.0);
                GotIndConstFlag = false;
                GotVariableFlag = false;
                x = parseBase();
                log.info("LookAheadConst:parseFactor:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " posLA=" + posLA + " chLA=" + (char) chLA);
                for (;;) {
                    HaltingProblem("LookAheadConst:parseFactor:", posLA - StartPos1, chLA, "StrLA", StrLA, posLA, StartPos1);
                    if (eatLA('^')) { // exponentiation
                        LookAheadConst a = x, b = parseBase();
                        StartPos1 = posLA;
                        log.info("LookAheadConst:parseFactor:b.eval()=" + b.eval() + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if (GotVariableFlag == true) {
                            x = (() -> 1.0);

                        } else if (GotIndConstFlag == true) {
                            x = (() -> 1.0);

                        } else {
                            //2^3
                            x = (() -> Math.pow(a.eval(), b.eval()));
                        }
                        log.info("LookAheadConst:parseFactor:x.eval()=" + x.eval() + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else {
                        if (GotIndConstFlag == true) {
                            x = (() -> 1.0);
                        }
                        log.info("LookAheadConst:parseFactor:Return Value:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " posLA=" + posLA + " chLA=" + (char) chLA);
                        return x;
                    }
                }
            }

            LookAheadConst parseBase() {
                int startPos = posLA;
                LookAheadConst x = (() -> 1.0);
                if (eatLA('-')) { // unary minus
                    LookAheadConst b = parseBase();
                    x = (() -> b.eval());
                    return x;
                } else if (eatLA('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chLA))) { //numbers
                    log.info("LookAheadConst:parseBase:Entered Numbers:posLA=" + posLA + " startPos=" + startPos + " chLA=" + (char) chLA);
                    int startPos1 = posLA;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while ((IsNumber(chLA)) && (chLA != -1)) {
                        nextCharLA();
                        if ((chLA == 'E') || (chLA == 'e')) {
                            nextCharLA();
                            if ((chLA == '-') || (chLA == '+')) {
                                nextCharLA();
                            }
                        }
                    }
                    double xx = 1.0;
                    if ((posLA != -1) && (chLA != -1)) {
                        xx = Double.parseDouble(StrLA.substring(startPos1, posLA));
                        log.info("LookAheadConst:parseBase:if:Number:x.eval()=" + x.eval() + " xx=" + xx + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else {
                        xx = Double.parseDouble(StrLA.substring(startPos1));
                        log.info("LookAheadConst:parseBase:Else:Number:x.eval()=" + x.eval() + " xx=" + xx + " posLA=" + posLA + " chLA=" + (char) chLA);
                    }

                    log.info("LookAheadConst:parseBase:0:Number:x.eval()=" + x.eval() + " xx=" + xx + " posLA=" + posLA + " chLA=" + (char) chLA);
                    final Double XX = xx;
                    x = () -> XX;
                    log.info("LookAheadConst:parseBase:1:Number:x.eval()=" + x.eval() + " xx=" + xx + " posLA=" + posLA + " chLA=" + (char) chLA);
                    return x;
                } else if (IsVariable(chLA)) {//functions & variables
                    int startPos1 = posLA;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while (((IsVariable(chLA)) || (IsNumber(chLA))) && (chLA != -1)) {
                        nextCharLA();
                    }
                    String TempVar = StrLA.substring(startPos1, posLA);
                    log.info("LookAheadConst:parseBase:TempVar:TempVar=" + TempVar);
                    if ((!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (variables.containsKey(TempVar)) && (GotIndConstFlag == false) && (GotIndConstPos == -1)) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = posLA;
                        if ((startPos1 > 0) && ((StrLA.charAt(startPos1 - 1) == '+') || (StrLA.charAt(startPos1 - 1) == '-'))) {
                            TermOperatorPos = startPos1 - 2;
                            log.info("LookAheadConst:parseBase:TempVar:startPos1 - 2:TermOperatorPos=" + TermOperatorPos + " GotIndConstPos=" + GotIndConstPos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                        } else if (startPos1 > 0) {
                            TermOperatorPos = startPos1 - 1;
                            log.info("LookAheadConst:parseBase:TempVar:startPos1 - 1:TermOperatorPos=" + TermOperatorPos + " GotIndConstPos=" + GotIndConstPos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                        }
                        log.info("LookAheadConst:parseBase:TempVar:TermOperatorPos=" + TermOperatorPos + " GotIndConstPos=" + GotIndConstPos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    } else if (ComplexAssignFlag == false) {
                        GotVariablePos = startPos1;
                        if ((startPos1 > 0) && ((StrLA.charAt(startPos1 - 1) == '+') || (StrLA.charAt(startPos1 - 1) == '-'))) {
                            TermOperatorPos = startPos1 - 2;
                        } else if (startPos1 > 0) {
                            TermOperatorPos = startPos1 - 1;
                        }
                        ComplexAssignFlag = true;
                        log.info("LookAheadConst:parseBase:TempVar:TermOperatorPos=" + TermOperatorPos + " GotIndConstPos=" + GotIndConstPos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    }
                    log.info("LookAheadConst:parseBase:Variable:x.eval()=" + x.eval() + " TempVar=" + TempVar + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    if (TempVar.equalsIgnoreCase(DiffWithRespTo)) {
                        GotVariableFlag = true;
                        x = () -> 1.0;
                        log.info("LookAheadConst:parseBase:Variable:x.eval()=" + x.eval() + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    } else if ((TempVar.equalsIgnoreCase("Infinity")) || (TempVar.equalsIgnoreCase("NaN"))) {
                        // Extremities
                        //ProLambdaExtr y=parseExpr("LookAheadVar",  TempVar, "1.0",  "ProductRule",  DiffWithRespTo,  '*', "LowLevelRules");
                        //final String MyStr = y.StrExpr();
                        //x = () -> Double.parseDouble(MyStr);
                        x = (() -> 1.0);
                        log.info("LookAheadConst:parseBase:Variable:NaN:name=" + TempVar + " x.eval()=" + x.eval());
                    } else {
                        //Independent Constant
                        x = () -> 1.0;
                    }
                    log.info("LookAheadConst:parseBase:Variable:x.eval()=" + x.eval() + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    return x;
                } else if (chLA == '(') {
                    eatLA('(');
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posLA;
                    while ((posLA < StrLA.length()) && ((chLA != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharLA();
                        if (chLA == '(') {
                            openBracesCount++;
                        }
                        if (chLA == ')') {
                            closeBracesCount++;
                        }
                    }
                    if ((chLA == ')') && (StrLA.substring(startcount, posLA).contains(")"))) {
                        posLA--;
                    }
                    String Exprn = StrLA.substring(startcount, posLA);
                    eatLA(')');
                    final String MyStr = Exprn;
                    log.info("LookAheadConst:parseBase:Paran:posLA=" + posLA + " chLA=" + (char) chLA);
                    if ((IsAConstant(Exprn, DiffWithRespTo))
                            && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                        x = (() -> Double.parseDouble(MyStr));
                        log.info("LookAheadConst:parseBase:Paran:Constant:MyStr=" + MyStr + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                            && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                        log.info("LookAheadConst:parseBase:Paran:Var:arg.eval()=" + MyStr + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if ((IsAConstant(Exprn, DiffWithRespTo))
                            && (StrIsAConstant.equalsIgnoreCase("NumberExpression"))) {
                        log.info("LookAheadConst:parseBase:Paran:Calling LookAheadConst:MyStr=" + MyStr + " posLA=" + posLA + " chLA=" + (char) chLA);
                        Map<String, String> variablesBackupState = new HashMap<>();
                        BackupStateAll(variablesBackupState);
                        //SimpleExpression MyMappedArg = parseSimple(StrLA.substring(startcount, posLA), variables, variablesDiff, DiffWithRespTo);
                        LookAheadConst MyMappedArg = parseLA(StrLA.substring(startcount, posLA), startcount, variables, DiffWithRespTo, true);
                        RestoreStateAll(variablesBackupState);
                        final Double MySimpleExprn = MyMappedArg.eval();
                        log.info("LookAheadConst:parseBase:Paran:arg.eval()=" + MyStr + " MySimpleExprn=" + MySimpleExprn + " posLA=" + posLA + " chLA=" + (char) chLA);
                        x = (() -> MySimpleExprn);
                        log.info("LookAheadConst:parseBase:Complete Paran String:x.eval()=" + x.eval() + " posLA=" + posLA + " chLA=" + (char) chLA);
                    }
                    return x;
                } else {
                    if (((IsNotAnOperator(chLA)) && ((!IsNumber(chLA)) && (!IsVariable(chLA)))) && (chLA != 0) && (chLA != -1) && (chLA != '(')) {
                        if ((StrLA != null) && (startPos < StrLA.length()) && (startPos < posLA) && (chLA != -1) && (chLA != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA, StrLA.length()));
                        } else if ((chLA != -1) && (chLA != 0) && (posLA >= 1) && (posLA < StrLA.length()) && (IsNumber(chLA))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA, StrLA.length()));
                        } else if ((chLA != -1) && (chLA != 0) && (IsOperator(chLA)) && (posLA >= 0) && (posLA < StrLA.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA));
                        } else if ((chLA != -1) && (chLA != 0) && (posLA >= 0) && (posLA < StrLA.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Unexpected char: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA));
                        }
                    }
                    return x;
                }
            }
        }.parseLAExpr();
    }

//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
    @FunctionalInterface
    public interface LookAheadVar {

        String eval();
    }

    public static LookAheadVar parseVar(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {
            void nextCharVar() {
                ++posVar;
                chVar = (posVar < StrVar.length()) ? StrVar.charAt(posVar) : -1;
                if ((chVar == -1) && (posVar >= StrVar.length())) {
                    posVar = StrVar.length();
                } else if ((chVar == -1) || ((chVar == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:nextCharVar:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                }
            }

            boolean eatVar(int CharToEat) {
                while (chVar == ' ') {
                    nextCharVar();
                }
                if (chVar == CharToEat) {
                    nextCharVar();
                    return true;
                }
                return false;
            }

            void SyncVar() {
                if ((posVar < StrVar.length())
                        && (StrVar.charAt(posVar) != chVar)) {
                    if (StrVar.charAt(posVar + 1) == chVar) {
                        posVar++;
                    } else if (StrVar.charAt(posVar - 1) == chVar) {
                        posVar--;
                    }
                }
            }

            LookAheadVar parseVar() {
                LookAheadVar x = (() -> "");
                LookAheadVar y = (() -> "");
                StrVar = str;
                FunctionStr = "";
                LAVarTermOperator = -1;
                LATermOperator = -1;
                LAVarSimpleMapFlag = false;
                LAVarComplexMapFlag = false;
                ContainsXFlag = false;
                TempIndConstStrSet = false;
                if (ComplexAssignFlag == false) {
                    GotIndConstFlag = false;
                    GotIndConstPos = -1;
                }
                ParseLog("LookAheadVar", "parseVar", "Initial0", "StrVar", StrVar);
                if ((ComplexAssignFlag == true) && (StrLA.charAt(GotVariablePos - 1) != '^')) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    StrVar = StrLA;
                    posVar = GotVariablePos;
                    chVar = StrVar.charAt(GotVariablePos);
                    //TermOperatorPos = GotVariablePos - 1;
                    TermOutput = 0.0;
                    log.info("LookAheadVar:parseVar: posVar=" + posVar + " chVar=" + (char) chVar + " ComplexAssignFlag=" + ComplexAssignFlag);
                } else if ((ComplexAssignFlag == true) && (StrLA.charAt(GotVariablePos - 1) == '^')) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    posVar = 0;
                    StrVar = StrLA;
                    chVar = StrVar.charAt(posVar);
                    TermOutput = 0.0;
                    log.info("LookAheadVar:parseVar:posVar=" + posVar + " chVar=" + (char) chVar + " ComplexAssignFlag=" + ComplexAssignFlag);
                } else {
                    posVar = 0;
                    chVar = StrVar.charAt(0);
                    TermOperatorPos = -1;
                    TermOutput = 0.0;
                    log.info("LookAheadVar:parseVar:posVar=" + posVar + " chVar=" + (char) chVar + " TermOperatorPos=" + TermOperatorPos + " ComplexAssignFlag=" + ComplexAssignFlag);
                }
                int StartPos = posVar;
                log.info("LookAheadVar:parseVar:StartPos=" + StartPos + " posVar=" + posVar + " chVar=" + (char) chVar + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (TermOperatorPos != -1) {
                    log.info("LookAheadVar:parseVar:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrVar.charAt(TermOperatorPos) + " chVar=" + (char) chVar);
                }
                ParseLog("LookAheadVar", "parseVar", "Initial", "StrVar", StrVar);
                if ((StrVar != null) && (StrVar.length() > 0)
                        && (StrVar.length() < 2)
                        && ((chVar == '*')
                        || (chVar == '/')
                        || (chVar == '^'))) {
                    return x;
                } else {
                    x = parseTerm();
                }
                y = x;
                if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                    ParseLog("LookAheadVar", "parseVar", "BeforeMap", "StrVar", StrVar);
                    ParseMap("LookAheadVar:parseVar", StrVar.length(), 0, 0, DiffWithRespTo);
                }
                log.info("LookAheadVar:parseVar:Variable:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                log.info("LookAheadVar:parseVar:Initial :LATermOperator=" + (char) LATermOperator);
                log.info("LookAheadVar:parseVar:Initial :LATermOperatorBraces=" + (char) LATermOperatorBraces);
                log.info("LookAheadVar:parseVar:Initial :LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
                log.info("LookAheadVar:parseVar:Initial :LAIndConstOperator=" + (char) LAIndConstOperator);
                log.info("LookAheadVar:parseVar:Initial :LAVarTermOperator=" + (char) LAVarTermOperator);
                log.info("LookAheadVar:parseVar:Initial :LAPreTermOperator=" + (char) LAPreTermOperator);
                log.info("LookAheadVar:parseVar:Initial :LAMAPOperator=" + (char) LAMAPOperator);
                log.info("LookAheadVar:parseVar:Initial :LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                log.info("LookAheadVar:parseVar:Initial :LAVarSimpleMapFlag=" + LAVarSimpleMapFlag);
                log.info("LookAheadVar:parseVar:Initial :LAVarComplexMapFlag=" + LAVarComplexMapFlag);
                if (((x.eval().contains("(")) || (x.eval().contains(DiffWithRespTo + "^"))) && (TermOutput != 0)) {
                    FunctionStr = x.eval();//Do Nothing as eval already is finished with function & Argument
                    final String FuncStr = x.eval();
                    if ((x.eval().contains(DiffWithRespTo + "^")) && (ContainsIndConstFlag == true)) {
                        ContainsIndConstFlag = false;
                    }
                    if ((x.eval().equalsIgnoreCase("Infinity")) || (x.eval().equalsIgnoreCase("NaN"))) {
                        ;//Do Nothing as e.eval() is already mapped 
                    } else if (TermOutput > 1.0) {
                        x = (() -> DiffWithRespTo + "^" + TermOutput);
                        if (LAVarTermOperator == '/') {
                            LAVarTermOperator = '*';
                        }
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } //FormSimpleTermParse:VarConstantRules:Mapping : No negative Exponent
                    else if ((TermOutput == -1.0) && ((LATermOperator == -1)) && (LAVarTermOperator == -1) && (LAMAPOperator == -1)) {
                        //Mapping /x to OR *x^-1 to *x OR /x  : No negative Exponent
                        x = (() -> "1.0" + "/" + DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((TermOutput == -1.0) && (LAVarSimpleMapFlag == true) && ((LATermOperator == '*') || (LADyYByDxMAPOperator == -1) || (LATermOperator == -1)) && (LAMAPOperator == -1)) {
                        //Mapping /x to *x OR x^-1 to *x
                        //FormSimpleTermParse:DIV:Rules:Mapping:Div Rule:2.1 to 2.8 
                        x = (() -> DiffWithRespTo);
                        if ((LATermOperator == '*')) {
                            LAVarTermOperator = '/';
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } else if ((LATermOperator == -1) && (LAVarTermOperator == '/')) {
                            LAVarTermOperator = '/';
                            LATermOperator = '/';
                            LAMAPOperator = '*';
                            LADyYByDxMAPOperator = '*';
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } else if ((LATermOperator == -1) && (LAVarTermOperator == '*')) {
                            LAVarTermOperator = '*';
                            LATermOperator = '*';
                            LAMAPOperator = '*';
                            LADyYByDxMAPOperator = '/';
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        }
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } //FormSimpleTermParse:DIV:Rules:Mapping:Div Rule:2.9 to 2.13 : With negative Exponent
                    else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LATermOperator == '/') && (LAMAPOperator == -1)) {
                        //Mapping /x to *x OR x^-1 to *x
                        LAMAPOperator = '/';
                        LADyYByDxMAPOperator = '/';
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LAMAPOperator == 0) && (LADyYByDxMAPOperator != -1)) {
                        //Mapping /x to *x OR x^-1 to *x
                        if ((LATermOperator == '*')) {
                            LAVarTermOperator = '/';
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } //Straight Case Mapping
                        else if ((LATermOperator == -1) && (LAVarTermOperator == '/') && (LAMAPOperator != -1) && (LADyYByDxMAPOperator != -1)) {
                            LAVarTermOperator = '/';
                            LATermOperator = '/';
                            LAMAPOperator = '*';
                            LADyYByDxMAPOperator = '*';
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } //1st Order DyByDx Mapping Rule
                        else if ((LATermOperator == -1) && (LAVarTermOperator == '*')) {
                            LAVarTermOperator = '*';
                            LATermOperator = '/';
                            LAMAPOperator = -1;
                            LADyYByDxMAPOperator = -1;
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } else {
                            LAVarTermOperator = '*';
                            LATermOperator = '/';
                            LAMAPOperator = -1;
                            LADyYByDxMAPOperator = '/';
                            LAPreTermOperator = -1;
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        }
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4A:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == -1.0) && (LAVarSimpleMapFlag == true) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == -1) && (LATermOperator == -1)) {
                        //Mapping of x^-1 term alone (with No Operand)
                        x = (() -> 1.0 + "/" + DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4B:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == -1.0) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator != '/')) {
                        x = (() -> DiffWithRespTo);
                        LAVarSimpleMapFlag = true;
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4C:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == '/') && (LATermOperator == -1)) {
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4D:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == -1.0) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == '/')) {
                        x = (() -> 1.0 + "/" + DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4E:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                    } else if ((TermOutput == 1.0) && ((LATermOperator == '*') || (LATermOperator == -1))) {
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((TermOutput == 1.0) && (LATermOperator == '/')) {
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((TermOutput == 0.0) && ((x.eval().equalsIgnoreCase(DiffWithRespTo)))) {
                        x = (() -> DiffWithRespTo);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((TermOutput == 0.0) && (!x.eval().equalsIgnoreCase(DiffWithRespTo)) && (!x.eval().equalsIgnoreCase(""))) {
                        if ((TermOutput == 0.0) && (!x.eval().equalsIgnoreCase("1.0"))) {
                            x = (() -> "");
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule8.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        } else if (ContainsXFlag == true) {
                            x = (() -> DiffWithRespTo + "^0.0");
                            log.info("LookAheadVar:parseVar:Variable Mapping:Rule8.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                        }
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if (TermOutput < -1.0) {
                        TermOutput = -1.0 * TermOutput;
                        x = (() -> DiffWithRespTo + "^" + TermOutput);
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule9:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    }
                    final String FuncStr1 = x.eval();
                    x = () -> FuncStr1;
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule A:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((x.eval().contains("(")) || (x.eval().contains(DiffWithRespTo + "^"))) {
                    FunctionStr = x.eval();//Do Nothing as eval already is finished with function & Argument
                    if ((x.eval().contains(DiffWithRespTo + "^")) && (ContainsIndConstFlag == true)) {
                        ContainsIndConstFlag = false;
                    }
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule A:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((x.eval().equalsIgnoreCase("Infinity")) || (x.eval().equalsIgnoreCase("NaN"))) {
                    ;//Do Nothing as e.eval() is already mapped 
                } else if (TermOutput > 1.0) {
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    if (LAVarTermOperator == '/') {
                        LAVarTermOperator = '*';
                    }
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } //FormSimpleTermParse:VarConstantRules:Mapping : No negative Exponent
                else if ((TermOutput == -1.0) && ((LATermOperator == -1)) && (LAVarTermOperator == -1) && (LAMAPOperator == -1)) {
                    //Mapping /x to OR *x^-1 to *x OR /x  : No negative Exponent
                    x = (() -> "1.0" + "/" + DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((TermOutput == -1.0) && (LAVarSimpleMapFlag == true) && ((LATermOperator == '*') || (LADyYByDxMAPOperator == -1) || (LATermOperator == -1)) && (LAMAPOperator == -1)) {
                    //Mapping /x to *x OR x^-1 to *x
                    //FormSimpleTermParse:DIV:Rules:Mapping:Div Rule:2.1 to 2.8 
                    x = (() -> DiffWithRespTo);
                    if ((LATermOperator == '*')) {
                        LAVarTermOperator = '/';
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((LATermOperator == -1) && (LAVarTermOperator == '/')) {
                        LAVarTermOperator = '/';
                        LATermOperator = '/';
                        LAMAPOperator = '*';
                        LADyYByDxMAPOperator = '*';
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if ((LATermOperator == -1) && (LAVarTermOperator == '*')) {
                        LAVarTermOperator = '*';
                        LATermOperator = '*';
                        LAMAPOperator = '*';
                        LADyYByDxMAPOperator = '/';
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule3.3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    }
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } //FormSimpleTermParse:DIV:Rules:Mapping:Div Rule:2.9 to 2.13 : With negative Exponent
                else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LATermOperator == '/') && (LAMAPOperator == -1)) {
                    //Mapping /x to *x OR x^-1 to *x
                    LAMAPOperator = '/';
                    LADyYByDxMAPOperator = '/';
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LAMAPOperator == 0) && (LADyYByDxMAPOperator != -1)) {
                    //Mapping /x to *x OR x^-1 to *x
                    if ((LATermOperator == '*')) {
                        LAVarTermOperator = '/';
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } //Straight Case Mapping
                    else if ((LATermOperator == -1) && (LAVarTermOperator == '/') && (LAMAPOperator != -1) && (LADyYByDxMAPOperator != -1)) {
                        LAVarTermOperator = '/';
                        LATermOperator = '/';
                        LAMAPOperator = '*';
                        LADyYByDxMAPOperator = '*';
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } //1st Order DyByDx Mapping Rule
                    else if ((LATermOperator == -1) && (LAVarTermOperator == '*')) {
                        LAVarTermOperator = '*';
                        LATermOperator = '/';
                        LAMAPOperator = -1;
                        LADyYByDxMAPOperator = -1;
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else {
                        LAVarTermOperator = '*';
                        LATermOperator = '/';
                        LAMAPOperator = -1;
                        LADyYByDxMAPOperator = '/';
                        LAPreTermOperator = -1;
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule4.4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    }
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4A:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LAVarSimpleMapFlag == true) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == -1) && (LATermOperator == -1)) {
                    //Mapping of x^-1 term alone (with No Operand)
                    x = (() -> 1.0 + "/" + DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4B:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator != '/')) {
                    x = (() -> DiffWithRespTo);
                    LAVarSimpleMapFlag = true;
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4C:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LAVarComplexMapFlag == true) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == '/') && (LATermOperator == -1)) {
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4D:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == '/')) {
                    x = (() -> 1.0 + "/" + DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4E:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == 1.0) && ((LATermOperator == '*') || (LATermOperator == -1))) {
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((TermOutput == 1.0) && (LATermOperator == '/')) {
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((TermOutput == 0.0) && ((x.eval().equalsIgnoreCase(DiffWithRespTo)))) {
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((TermOutput == 0.0) && (!x.eval().equalsIgnoreCase(DiffWithRespTo)) && (!x.eval().equalsIgnoreCase(""))) {
                    if ((TermOutput == 0.0) && (!x.eval().equalsIgnoreCase("1.0"))) {
                        x = (() -> "");
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule8.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if (ContainsXFlag == true) {
                        x = (() -> DiffWithRespTo + "^0.0");
                        log.info("LookAheadVar:parseVar:Variable Mapping:Rule8.1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    }
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if (TermOutput < -1.0) {
                    if ((LAVarTermOperator == '*') && (LAVarComplexMapFlag == false)) {
                        LAVarSimpleMapFlag = true;
                        if ((LAMAPOperator == -1) || (LAMAPOperator == '*')) {
                            LAMAPOperator = '/';
                        } else if (LAMAPOperator == '/') {
                            LAMAPOperator = '*';
                        }
                    }
                    TermOutput = -1.0 * TermOutput;
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule9:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if (TermOutput > 0.0) {
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if (TermOutput < 0.0) {
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                }
                log.info("LookAheadVar:parseVar:Final:Variable:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                log.info("LookAheadVar:parseVar:Final :LATermOperator=" + (char) LATermOperator);
                log.info("LookAheadVar:parseVar:Final :LATermOperatorBraces=" + (char) LATermOperatorBraces);
                log.info("LookAheadVar:parseVar:Final :LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
                log.info("LookAheadVar:parseVar:Final :LAIndConstOperator=" + (char) LAIndConstOperator);
                log.info("LookAheadVar:parseVar:Final :LAVarTermOperator=" + (char) LAVarTermOperator);
                log.info("LookAheadVar:parseVar:Final :LAPreTermOperator=" + (char) LAPreTermOperator);
                log.info("LookAheadVar:parseVar:Final :LAMAPOperator=" + (char) LAMAPOperator);
                log.info("LookAheadVar:parseVar:Final :LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                log.info("LookAheadVar:parseVar:Final :LAVarSimpleMapFlag=" + LAVarSimpleMapFlag);
                log.info("LookAheadVar:parseVar:Final :LAVarComplexMapFlag=" + LAVarComplexMapFlag);
                if (((y.eval().contains("(")) || (y.eval().contains("^" + DiffWithRespTo))) && (!y.eval().equalsIgnoreCase(x.eval()))) {
                    if ((LAVarTermOperator == -1) && (x.eval().equalsIgnoreCase("0.0"))) {
                        FunctionStr = y.eval();//Do Nothing as eval already is finished with function & Argument
                    } else if (!x.eval().equalsIgnoreCase("")) {
                        //Do Nothing as eval already is finished with function & Argument
                        if (LAVarTermOperator == -1) {
                            FunctionStr = x.eval() + (char) '*' + y.eval();
                        } else {
                            FunctionStr = x.eval() + (char) LAVarTermOperator + y.eval();
                        }
                        log.info("LookAheadVar:parseVar:FunctionStr:0:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                    } else if (x.eval().equalsIgnoreCase("")) {
                        FunctionStr = y.eval();//Do Nothing 
                    }
                    if ((y.eval().contains("^" + DiffWithRespTo)) && (ContainsIndConstFlag == true)) {
                        ContainsIndConstFlag = false;
                    }
                    log.info("LookAheadVar:parseVar:FunctionStr:0:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((!y.eval().equalsIgnoreCase(x.eval())) && (!FunctionStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1.0")) && (!x.eval().equalsIgnoreCase(DiffWithRespTo)) && (!x.eval().contains(DiffWithRespTo + "^")) && (!x.eval().contains("("))) {
                    FunctionStr = FunctionStr + "*" + x.eval();
                    log.info("LookAheadVar:parseVar:FunctionStr:1:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((!FunctionStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1.0")) && (x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    FunctionStr = FunctionStr;
                    log.info("LookAheadVar:parseVar:FunctionStr:2:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((!FunctionStr.equalsIgnoreCase("")) && (x.eval().equalsIgnoreCase("1.0"))) {
                    FunctionStr = FunctionStr;
                    log.info("LookAheadVar:parseVar:FunctionStr:3:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((x.eval().equalsIgnoreCase(DiffWithRespTo)) && (TermOutput == 1.0)) {
                    FunctionStr = "1.0";
                    log.info("LookAheadVar:parseVar:FunctionStr:4:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else {
                    FunctionStr = x.eval();
//                    //Needs better Logic & processing :TODO
//                    if ((TermOutput > 1.0) && (ContainsFuncFlag == true)) {
//                        x = (() -> DiffWithRespTo + "^" + TermOutput);
//                        FunctionStr = FunctionStr + "*" + x.eval();
//                    }
                    log.info("LookAheadVar:parseVar:FunctionStr:5:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                }
                log.info("LookAheadVar:parseVar:FINAL:1:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                final String MyString = FunctionStr;
                x = (() -> MyString);
                if ((!IsAConstant(MyString, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                    ContainsXFlag = true;
                }
                log.info("LookAheadVar:parseVar:FINAL:2:Variable:x.eval()=" + x.eval() + " FunctionStr=" + FunctionStr + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                ParseLog("LookAheadVar", "parseVar", "Final", "StrVar", StrVar);
                return x;
            }

            LookAheadVar parseTerm() {
                int StartPos = posVar;
                LookAheadVar x = (() -> "");
                log.info("LookAheadVar:parseTerm:TermOutput=" + TermOutput);
                for (;;) {
                    if ((eatVar('*') || (TermOperatorPos == -1) || (StrVar.charAt(TermOperatorPos) == '*')) || (StrVar.charAt(TermOperatorPos) == '^')) { // multiplication
                        log.info("LookAheadVar:parseTerm:Multiply:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar + " StrVar.length=" + StrVar.length());
                        TermOutputOperator = '*';
                        LookAheadVar a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutput == -1.0) {
                                LAVarSimpleMapFlag = true;
                            }
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                if ((!b.eval().contains("("))
                                        && (((posVar + 1) < StrVar.length())
                                        && ((StrVar.charAt(posVar + 1) == '*')
                                        || (StrVar.charAt(posVar + 1) == '/')))) {
                                    TermOutput += 1.0;
                                    TermOutputSetFlag = true;
                                }
                            }
                            x = b;
                            log.info("LookAheadVar:parseTerm:Multiply:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } // Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo)) && ((b.eval().equalsIgnoreCase("-1.0")) || (b.eval().equalsIgnoreCase("1.0")))) {
                            x = a;
                            log.info("LookAheadVar:parseTerm:Multiply:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = a;
                            log.info("LookAheadVar:parseTerm:Multiply:Rule3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput += 1.0;
                                if (GotBracesVarFlag == true) {
                                    GotBracesVarFlag = false;
                                    nextCharVar();
                                }
                            }
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) || (a.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                x = (() -> DiffWithRespTo);
                            } else {
                                x = a;
                            }
                            log.info("LookAheadVar:parseTerm:Multiply:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            log.info("LookAheadVar:parseTerm:Multiply:Rule5:a.eval()=" + a.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            if ((!a.eval().equalsIgnoreCase("0.0")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> (a.eval() + "*" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))
                                    && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                //ln(2.0)*2^x
                                x = (() -> (a.eval() + "*" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            } else {
                                x = b;
                            }
                            log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
//                            if ((IsAConstant(a.eval(), DiffWithRespTo)) || (a.eval().equalsIgnoreCase(DiffWithRespTo))) {
//                                if ((IsAConstant(b.eval(), DiffWithRespTo)) || (b.eval().equalsIgnoreCase(DiffWithRespTo))) {
//                                    x = b;
//                                } else {
//                                    x = (() -> (a.eval() + "*" + b.eval()));
//                                }
//                                //x = (() -> b.eval());
//                            } else {
//                                x = b;
//                            }
                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        if ((x.eval().contains("(")) && (FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = x.eval();
                            log.info("LookAheadVar:parseTerm:Multiply:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if ((x.eval().contains("(")) && (!FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = FunctionStr + "*" + x.eval();
                            log.info("LookAheadVar:parseTerm:Multiply:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        }
                        log.info("LookAheadVar:parseTerm:Multiply:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        if ((chVar == '+') || (chVar == '-')) {
                            if ((LAMAPOperator == 0) && (TermOutput != -1.0)) {
                                LAMAPOperator = -1;
                            }
                            log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVar=" + (char) chVar);
                            return x;
                        } else if ((chVar != -1) && (chVar != 0)) {
                            log.info("LookAheadVar:parseTerm:Multiply:Sync:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar + " StrVar.length=" + StrVar.length());
                            SyncVar();
                        }
                    } else if ((eatVar('/') || (StrVar.charAt(TermOperatorPos) == '/'))) { // division
                        log.info("LookAheadVar:parseTerm:Divide:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar + " StrVar.length=" + StrVar.length());
                        TermOutputOperator = '/';
                        LookAheadVar a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                if ((!b.eval().contains("("))
                                        && ((posVar < StrVar.length())
                                        && ((StrVar.charAt(posVar + 1) == '*')
                                        || (StrVar.charAt(posVar + 1) == '/')))) {
                                    TermOutput -= 1.0;
                                    TermOutputSetFlag = true;
                                }
                            }
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) || (a.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                x = (() -> DiffWithRespTo);
                            } else {
                                x = a;
                            }
                            //x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule1:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        }// Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo)) && ((b.eval().equalsIgnoreCase("-1.0")) || (b.eval().equalsIgnoreCase("1.0")))) {
                            x = (() -> a.eval());
                            LAVarComplexMapFlag = true;
                            log.info("LookAheadVar:parseTerm:Divide:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule3:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutput == 1.0) {
                                TermOutput = -1.0;
                                LAVarSimpleMapFlag = true;
                            } else if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput -= 1.0;
                                if (GotBracesVarFlag == true) {
                                    GotBracesVarFlag = false;
                                    nextCharVar();
                                }
                            }
                            x = (() -> DiffWithRespTo);
                            log.info("LookAheadVar:parseTerm:Divide:Rule4:DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if ((!a.eval().equalsIgnoreCase("0.0")) && ((!IsAConstant(b.eval(), DiffWithRespTo))) && (!StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> (a.eval() + "/" + b.eval()));
                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))
                                    && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                //ln(2.0)/2^x
                                x = (() -> (a.eval() + "/" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Divide:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            } else {
                                x = (() -> b.eval());
                            }
                            log.info("LookAheadVar:parseTerm:Divide:Rule5:!DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
//                            if ((IsAConstant(a.eval(), DiffWithRespTo)) || (a.eval().equalsIgnoreCase(DiffWithRespTo))) {
//                                if ((IsAConstant(b.eval(), DiffWithRespTo)) || (b.eval().equalsIgnoreCase(DiffWithRespTo))) {
//                                    x = b;
//                                } else {
//                                    x = (() -> (a.eval() + "/" + b.eval()));
//                                }
//                                //x = (() -> b.eval());
//                            } else {
//                                x = b;
//                            }
                            x = (() -> b.eval());
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        log.info("LookAheadVar:parseTerm:Divide:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        if ((x.eval().contains("(")) && (FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = x.eval();
                            log.info("LookAheadVar:parseTerm:Divide:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if ((x.eval().contains("(")) && (!FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = FunctionStr + "/" + x.eval();
                            log.info("LookAheadVar:parseTerm:Divide:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        }
                        if ((chVar == '+') || (chVar == '-')) {
                            if ((TermOutput != -1.0) && (LAMAPOperator == 0)) {
                                LAMAPOperator = -1;
                            }
                            log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVar=" + (char) chVar);
                            return x;
                        } else if ((chVar != -1) && (chVar != 0)) {
                            log.info("LookAheadVar:parseTerm:Divide:Sync:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar + " StrVar.length=" + StrVar.length());
                            SyncVar();
                        }
                    } else {
                        // Unset MapOperator if Overall TerOutput for the Term is not -1
                        if ((LAMAPOperator == 0) && (TermOutput != -1.0)) {
                            LAMAPOperator = -1;
                        }
                        log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVar=" + (char) chVar);
                        return x;
                    }
                }
            }

            LookAheadVar parseFactor() {
                int StartPos = posVar;
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                LookAheadVar x = parseBase();
                log.info("LookAheadVar:parseFactor:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                for (;;) {
                    HaltingProblem("LookAheadVar:parseFactor:", posVar - StartPos, chVar, "StrVar", StrVar, posVar, StartPos);
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadVar a = x, b = parseBase();
                        StartPos = posVar;
                        log.info("LookAheadVar:parseFactor:Exponent:a.eval()=" + a.eval() + " TermOutput=" + TermOutput);
                        log.info("LookAheadVar:parseFactor:Exponent:b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                        if (!b.eval().equalsIgnoreCase("")) {
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // 2^3
                                x = (() -> "1.0");
                                log.info("LookAheadVar:parseFactor:2^3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // 2^a
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = true;
                                log.info("LookAheadVar:parseFactor:2^a:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // 2^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                                log.info("LookAheadVar:parseFactor:2^x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // a^b
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = true;
                                log.info("LookAheadVar:parseFactor:a^b:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = true;
                                log.info("LookAheadVar:parseFactor:x^b:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = true;
                                log.info("LookAheadVar:parseFactor:a^x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // a^2
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = true;
                                log.info("LookAheadVar:parseFactor:a^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((TermOutput == 0.0) && (!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("0.0"))) {
                                //x^2  
                                if ((Double.parseDouble(b.eval()) == -1) || (Double.parseDouble(b.eval()) == 1)) {
                                    if ((TermOutputOperator == '*') && (Double.parseDouble(b.eval()) == -1)) {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '*') && (Double.parseDouble(b.eval()) == 1)) {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '/') && (Double.parseDouble(b.eval()) == -1)) {
                                        TermOutput -= Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '/') && (Double.parseDouble(b.eval()) == 1)) {
                                        TermOutput -= Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    }
                                    TermOutputSetFlag = true;
                                } else if ((Double.parseDouble(b.eval()) < -1) && (TermOutputOperator == '/')) {
                                    TermOutput -= Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) < -1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 1) && (TermOutputOperator == '/')) {
                                    TermOutput -= Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > -1) && (Double.parseDouble(b.eval()) < 0)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 0) && (Double.parseDouble(b.eval()) < 1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > -1) && (Double.parseDouble(b.eval()) < 0) && (TermOutputOperator == '/')) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 0) && (Double.parseDouble(b.eval()) < 1) && (TermOutputOperator == '/')) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else {
                                    x = b;
                                    log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                }
                                // Needs to be mapped set
                                if (TermOutput == -1.0) {
                                    LAMAPOperator = 0;
                                }
                                log.info("LookAheadVar:parseFactor:x^2:TermOutput 0.0:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("0.0"))) {
                                //x^2  
                                if ((Double.parseDouble(b.eval()) == -1) || (Double.parseDouble(b.eval()) == 1)) {
                                    if ((TermOutputOperator == '*') && (Double.parseDouble(b.eval()) == -1)) {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '*') && (Double.parseDouble(b.eval()) == 1)) {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '/') && (Double.parseDouble(b.eval()) == -1)) {
                                        TermOutput -= Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else if ((TermOutputOperator == '/') && (Double.parseDouble(b.eval()) == 1)) {
                                        TermOutput -= Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    } else {
                                        TermOutput += Double.parseDouble(b.eval());
                                        log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                    }
                                    TermOutputSetFlag = true;
                                } else if ((Double.parseDouble(b.eval()) < -1) && (TermOutputOperator == '/')) {
                                    TermOutput -= Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) < -1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 1) && (TermOutputOperator == '/')) {
                                    TermOutput -= Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else if ((Double.parseDouble(b.eval()) > 1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                    log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                } else {
                                    x = b;
                                    log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " TermOutputOperator=" + TermOutputOperator + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                                }
                                // Needs to be mapped set
                                if (TermOutput == -1.0) {
                                    LAMAPOperator = 0;
                                }
                                log.info("LookAheadVar:parseFactor:x^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                                x = b;
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:x=b:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else {
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:x=1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            }

                        } else {
                            x = a;
                            log.info("LookAheadVar:parseFactor:x=a:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        }
                        log.info("LookAheadVar:parseFactor:Exponent:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator);
                    } else {
                        log.info("LookAheadVar:parseFactor:x=x:x.eval()=" + x.eval() + " chVar=" + (char) chVar + " chVar=" + chVar + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        if ((GotExpntNumFlag == false)
                                && (x.eval().equalsIgnoreCase(DiffWithRespTo))
                                && (TermOutput == 0.0)
                                && ((posVar < StrVar.length())
                                && ((StrVar.charAt(posVar) == '*')
                                || (StrVar.charAt(posVar) == '/'))
                                || (chVar == -1) || (chVar == '-') || (chVar == '+'))) {
                            TermOutput = 1.0;
                            TermOutputSetFlag = true;
                            x = (() -> "1.0");
                            log.info("LookAheadVar:parseFactor:x=x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        }
                        // Needs to be mapped set
                        if ((TermOutput == -1.0) || ((TermOutput == 0.0) && (StartPos >= 1) && (StrVar.charAt(StartPos - 1) == '/'))) {
                            LAMAPOperator = 0;
                        }
                        log.info("LookAheadVar:parseFactor:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        GotExpntNumFlag = false;
                        return x;
                    }
                }
            }

            LookAheadVar parseBase() {
                int startPos = posVar;
                if ((posVar >= 0) && (posVar < StrVar.length())) {
                    log.info("LookAheadVar:parseBase:Entered:posVar=" + posVar + " chVar=" + (char) chVar + " StrVar=" + StrVar.substring(startPos));
                }
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                LookAheadVar x = (() -> "");
                if (eatVar('-')) { // unary minus
                    LookAheadVar b = parseBase();
                    if (GotExpntNumFlag == true) {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "-" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> "-" + b.eval());
                        }
                        log.info("LookAheadVar:parseBase:Number:x.eval()=" + x.eval() + " posVar=" + posVar + " chVar=" + (char) chVar);
                    } else {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> b.eval());
                        }
                        log.info("LookAheadVar:parseBase:Number:x.eval()=" + x.eval() + " posVar=" + posVar + " chVar=" + (char) chVar);
                    }
                    return x;
                } else if (eatVar('+')) { // unary plus
                }
                if ((IsNumber(chVar))) { //numbers
                    log.info("LookAheadVar:parseBase:Exponent:Number:posVar=" + posVar + " chVar=" + (char) chVar + " StrVar=" + StrVar.substring(startPos));
                    while ((IsNumber(chVar)) && (chVar != -1)) {
                        nextCharVar();
                        if ((chVar == 'E') || (chVar == 'e')) {
                            nextCharVar();
                            if ((chVar == '-') || (chVar == '+')) {
                                nextCharVar();
                            }
                        }

                    }
                    double xx = 0.0;
                    if ((GotExpntNumFlag == true) && (posVar != -1) && (StrVar != null) && (!StrVar.equalsIgnoreCase("")) && (!StrVar.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadVar:parseBase:Expnt Num:Number:xx=" + xx + " posVar=" + posVar + " chVar=" + (char) chVar + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrVar.substring(startPos, posVar));
                    } else if ((GotExpntNumFlag == true) && (StrVar != null) && (!StrVar.equalsIgnoreCase("")) && (!StrVar.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadVar:parseBase:Expnt Num:Number:xx=" + xx + " posVar=" + posVar + " chVar=" + (char) chVar + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrVar.substring(startPos));
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (posVar != -1) && (StrVar != null) && (!StrVar.equalsIgnoreCase("")) && (!StrVar.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrVar.substring(startPos, posVar));
                        log.info("LookAheadVar:parseBase:Exponent:Number:xx=" + xx + " posVar=" + posVar + " chVar=" + (char) chVar + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        TrailConstantFlag = true;
                        GotVariablePos = startPos;
                        GotVariableFlag = true;
                        if ((startPos >= 1) && ((StrVar.charAt(startPos - 1) == '*') || (StrVar.charAt(startPos - 1) == '/'))) {
                            TrailConstantOperator = StrVar.charAt(startPos - 1);
                        }
                        if (ComplexAssignFlag == false) {
                            ComplexAssignFlag = true;
                        }
                    } else {
                        xx = Double.parseDouble(StrVar.substring(startPos, posVar));
                    }
                    log.info("LookAheadVar:parseBase:Number:xx=" + xx + " posVar=" + posVar + " chVar=" + (char) chVar + " GotExpntNumFlag=" + GotExpntNumFlag + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " TrailConstantOperator=" + (char) TrailConstantOperator);
                    final Double XX = xx;
                    if (XX >= 0.0) {
                        x = () -> "" + XX;
                    } else {
                        x = () -> "";
                    }
                    log.info("LookAheadVar:parseBase:Number:xx=" + xx + " posVar=" + posVar + " chVar=" + (char) chVar + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " TrailConstantOperator=" + (char) TrailConstantOperator);
                    return x;
                } else if (IsVariable(chVar)) {//functions & variables
                    while (((IsVariable(chVar)) || (IsNumber(chVar))) && (chVar != -1)) {
                        nextCharVar();
                    }
                    String name = "";
                    if (posVar != -1) {
                        name = StrVar.substring(startPos, posVar).trim();
                        if ((name.equalsIgnoreCase(DiffWithRespTo)) && (startPos >= 1) && ((StrVar.charAt(startPos - 1) == '*') || (StrVar.charAt(startPos - 1) == '/'))) {
                            LAVarTermOperator = StrVar.charAt(startPos - 1);
                        } else if ((name.equalsIgnoreCase(DiffWithRespTo)) && (startPos >= 2) && ((StrVar.charAt(startPos - 1) == '+') || (StrVar.charAt(startPos - 1) == '-'))) {
                            LAVarTermOperator = StrVar.charAt(startPos - 2);
                        }
                    } else {
                        name = StrVar.substring(startPos).trim();
                    }

                    log.info("LookAheadVar:parseBase:Variable:name=" + name + " posVar=" + posVar + " chVar=" + (char) chVar);

                    if ((functionsSimple.containsKey(name)) && (!name.equalsIgnoreCase(DiffWithRespTo))) {
                        LookAheadVar arg = (() -> "");
                        log.info("LookAheadVar:parseBase:Function:name=" + name + " posVar=" + posVar + " chVar=" + (char) chVar);
                        //nextCharVar();
                        //arg = parseBase();
                        //nextCharVar();
                        eatVar('(');
                        int openBracesCount = 1;
                        int closeBracesCount = 0;
                        int startcount = posVar;
                        while ((posVar < StrVar.length()) && ((chVar != ')') || (openBracesCount != closeBracesCount))) {
                            nextCharVar();
                            if (chVar == '(') {
                                openBracesCount++;
                            }
                            if (chVar == ')') {
                                closeBracesCount++;
                            }
                        }
                        if ((chVar == ')') && (StrVar.substring(startcount, posVar).contains(")"))) {
                            posVar--;
                        }
                        String Exprn = StrVar.substring(startcount, posVar);

                        arg = (() -> Exprn);
                        eatVar(')');
                        final String MyFnStr = name;
                        final String MyStr = arg.eval();
                        log.info("LookAheadVar:parseBase:Function:arg.eval()=" + arg.eval() + " posVar=" + posVar + " chVar=" + (char) chVar);
                        if ((IsAConstant(Exprn, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Number"))
                                || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                            //x = (() -> MyFnStr + "(" + MyStr + ")");
                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            ChaosPrevention();
                            Expression MyMappedSimple = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                            ParseLog("LookAheadVar", "parseBase", "AfterParseExpr", "StrParen", StrParen);
                            RestoreStateParen(variablesBackupState);
                            RestoreOtherStateParen(variablesBackupState);
                            RestoreControlStateVar(variablesBackupState);
                            ParseLog("LookAheadVar", "parseBase", "BeforeMap", "StrParen", StrParen);
                            //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                            //ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);
                            ParseLog("LookAheadVar", "parseBase", "AfterRestore", "StrParen", StrParen);
                            x = (() -> MyFnStr + "(" + MyMappedSimple.eval() + ")");
                            log.info("LookAheadVar:parseBase:Function:Constant:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            //GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            x = (() -> MyFnStr + "(" + MyStr + ")");
                            log.info("LookAheadVar:parseBase:Function:Var:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            //GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else {
                            log.info("LookAheadVar:parseBase:Function:Calling SimpleExpression:Exprn=" + Exprn + " x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            SimpleExpression MyMappedArg = parseSimple(Exprn, variables, DiffWithRespTo);
                            RestoreStateAll(variablesBackupState);
                            final String MySimpleExprn = MyMappedArg.SimpleExpr();
                            log.info("LookAheadVar:parseBase:Function:Else:arg.eval()=" + MyStr + " MySimpleExprn=" + MySimpleExprn + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            x = (() -> MyFnStr + "(" + MySimpleExprn + ")");
                            log.info("LookAheadVar:parseBase:Finished Calling SimpleExpression:Function String:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            //GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        }
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (name.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                        ContainsXFlag = true;
                        log.info("LookAheadVar:parseBase:Variable:DiffWithRespTo:name=" + name + " x.eval()=" + x.eval());
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity")) && (GotIndConstFlag == false) && (GotIndConstPos == -1)) {
                        //ProLambdaExtr y=parseExpr("LookAheadVar",  name, "1.0",  "ProductRule",  DiffWithRespTo,  '*', "LowLevelRules");
                        //final String MyStr = y.StrExpr();
                        x = () -> "1.0";
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos;
                        log.info("LookAheadVar:parseBase:Variable:Extremity:name=" + name + " x.eval()=" + x.eval());
                    } else if ((!name.equalsIgnoreCase(DiffWithRespTo)) && (GotIndConstFlag == false) && (GotIndConstPos == -1)) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos;
                        final String MyStr = name;
                        x = (() -> MyStr);
                        log.info("LookAheadVar:parseBase:Variable:Independent Constant:name=" + name + " x.eval()=" + x.eval() + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    }
                    return x;
                } else if (chVar == '(') {
                    GotParenPos = posVar;
                    eatVar('(');
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posVar;
                    while ((posVar < StrVar.length()) && ((chVar != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharVar();
                        if (chVar == '(') {
                            openBracesCount++;
                        }
                        if (chVar == ')') {
                            closeBracesCount++;
                        }
                    }
                    if ((chVar == ')') && (StrVar.substring(startcount, posVar).contains(")"))) {
                        posVar--;
                    }
                    String Exprn = StrVar.substring(startcount, posVar);
                    eatVar(')');
                    log.info("LookAheadVar:parseBase:Parenthesis:Function:Exprn=" + Exprn + " posVar=" + posVar + " chVar=" + (char) chVar);
                    if ((IsAConstant(Exprn, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("Number"))
                            || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                        //x = (() -> Exprn);
                        log.info("LookAheadVar:parseBase:NumberExpression:Calling Simple:x.eval()=" + x.eval() + " Exprn=" + Exprn + " StrIsAConstant=" + StrIsAConstant + " posParen=" + posParen + " chParen=" + (char) chParen);
                        log.info("LookAheadVar:parseBase:NumberExpression:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        ParseLog("LookAheadVar", "parseBase", "InitialSimple", "StrParen", StrParen);
                        Map<String, String> variablesBackupState = new HashMap<>();
                        BackupStateAll(variablesBackupState);
                        ChaosPrevention();
                        Expression MyMappedSimple = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                        ParseLog("LookAheadVar", "parseBase", "AfterParseExpr", "StrParen", StrParen);

                        RestoreStateParen(variablesBackupState);
                        RestoreOtherStateParen(variablesBackupState);
                        RestoreControlStateVar(variablesBackupState);
                        ParseLog("LookAheadVar", "parseBase", "BeforeMap", "StrParen", StrParen);
                        //ParseMap("LookAheadParen:parseTermParen", StrParen.length(), 0, 0, DiffWithRespTo);
                        //ParseLog("LookAheadParen", "parseTermParen", "Final", "StrParen", StrParen);

                        ParseLog("LookAheadVar", "parseBase", "AfterRestore", "StrParen", StrParen);
                        final String MySimpleExprn = "" + MyMappedSimple.eval();
                        log.info("LookAheadVar:parseBase:NumberExpression:MySimpleExprn=" + MySimpleExprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        if (((Exprn.contains("+")) || (Exprn.contains("-"))) && (IsAConstant(MySimpleExprn, DiffWithRespTo))) {
                            log.info("LookAheadVar:parseBase:NumberExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                            x = (() -> MySimpleExprn);
                        } else if (((Exprn.contains("+")) || (Exprn.contains("-"))) && (MySimpleExprn.equalsIgnoreCase(Exprn))) {
                            log.info("LookAheadVar:parseBase:NumberExpression:Function:Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                            x = (() -> Exprn);
                        } else {
                            x = (() -> MySimpleExprn);
                        }
                        log.info("LookAheadVar:parseBase:NumberExpression:Complete Function String:x.eval()=" + x.eval() + " Exprn=" + Exprn + " posParen=" + posParen + " chParen=" + (char) chParen);
                        log.info("LookAheadVar:parseBase:Parenthesis:Function:Constant:Exprn=" + Exprn + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        if (timesLoop == 0) {
                            GotBracesVarFlag = true;
                        }
                    } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                            && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                        x = (() -> Exprn);
                        log.info("LookAheadVar:parseBase:Parenthesis:Function:Var:Exprn=" + Exprn + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        GotBracesVarFlag = true;
                    } else {
                        log.info("LookAheadVar:parseBase:Parenthesis:Else:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        //GotBracesVarFlag = true;
                    }
                    GotVariablePos = startPos;
                    TrailConstantFlag = true;
                    if ((startPos >= 1) && ((StrVar.charAt(startPos - 1) == '*') || (StrVar.charAt(startPos - 1) == '/'))) {
                        TrailConstantOperator = StrVar.charAt(startPos - 1);
                    }
                    if (ComplexAssignFlag == false) {
                        ComplexAssignFlag = true;
                    }
                    log.info("LookAheadVar:parseBase:BraceExpression:Exprn=" + Exprn + " x.eval()=" + x.eval() + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " TermOutput=" + TermOutput + " chVar=" + (char) chVar + " posVar=" + posVar);
                    return x;
                } else {
                    if (((IsNotAnOperator(chVar)) && ((!IsNumber(chVar)) || (!IsVariable(chVar)))) && (chVar != 0) && (chVar != -1) && (chVar != '(')) {
                        if ((StrVar != null) && (startPos < StrVar.length()) && (startPos < posVar) && (chVar != -1) && (chVar != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parseBase:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                        } else if ((chVar != -1) && (chVar != 0) && (posVar >= 1) && (posVar < StrVar.length()) && (IsNumber(chVar))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                        } else if ((chVar != -1) && (chVar != 0) && (posVar >= 1) && (posVar < StrVar.length()) && (IsOperator(chVar))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                        } else if ((chVar != -1) && (chVar != 0) && (posVar >= 1) && (posVar < StrVar.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parseBase:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parseBase:Unexpected char: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                        }
                    }
                    return x;
                }
            }
        }.parseVar();
    }
//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value

    @FunctionalInterface
    public interface LookAheadIndConst {

        String eval();
    }

    public static LookAheadIndConst parseIndConst(String str, String DiffWithRespTo) {

        return new Object() {
            void nextCharIndConst() {
                ++posIndConst;
                chIndConst = (posIndConst < StrIndConst.length()) ? StrIndConst.charAt(posIndConst) : -1;
                if ((chIndConst == -1) && (posIndConst >= StrIndConst.length())) {
                    posIndConst = StrIndConst.length();
                } else if ((chIndConst == -1) || ((chIndConst == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:nextCharIndConst:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                }
            }

            boolean eatVar(int CharToEat) {
                while (chIndConst == ' ') {
                    nextCharIndConst();
                }
                if (chIndConst == CharToEat) {
                    nextCharIndConst();
                    return true;
                }
                return false;
            }

            void SyncIndConst() {
                if ((posIndConst < StrIndConst.length())
                        && (StrIndConst.charAt(posIndConst) != chIndConst)) {
                    if (StrIndConst.charAt(posIndConst + 1) == chIndConst) {
                        posIndConst++;
                    } else if (StrIndConst.charAt(posIndConst - 1) == chIndConst) {
                        posIndConst--;
                    }
                }
            }

            LookAheadIndConst parseIndConst() {
                GotBracesVarFlag = false;
                LookAheadIndConst x = (() -> "");
                if (Str.equalsIgnoreCase("")) {
                    return x;
                } else {
                    StrIndConst = Str.substring(pos);
                }
                log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotIndConstPos=" + GotIndConstPos);
                ParseLog("LookAheadIndConst", "parseIndConst", "Initial", "StrIndConst", StrIndConst);
                String CompleteStr = "";
                LAIndConstOperator = '*';
                if ((ContainsIndConstFlag == true) || ((GotIndConstFlag == true) && (GotIndConstPos > 0))) {
                    log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
                    log.info("LookAheadIndConst:parseIndConst:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                    ContainsIndConstFlag = false;
                    GotIndConstFlag = false;
                    StrIndConst = Str.substring(pos);
                    posIndConst = GotIndConstPos;
                    if (posIndConst < StrIndConst.length()) {
                        if ((GotIndConstPos >= 0) && (posIndConst >= 0)) {
                            chIndConst = StrIndConst.charAt(posIndConst);
                        }
                        log.info("LookAheadIndConst:parseIndConst:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        if ((chIndConst == '-') || (chIndConst == '+')) {
                            nextCharIndConst();
                        }
                        if (posIndConst > 2) {
                            int TempVar = StrIndConst.charAt(posIndConst - 1);
                            if ((TempVar == '*') || (TempVar == '/')) {
                                LAPreIndConstOperator = TempVar;
                                log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TempVar=" + (char) TempVar);
                            } else if ((TempVar != '*') && (TempVar != '/')) {
                                LAPreIndConstOperator = StrIndConst.charAt(posIndConst);
                                if ((LAPreIndConstOperator != '*') && (LAPreIndConstOperator != '/') && ((IsVariable(LAPreIndConstOperator)) || (IsNumber(LAPreIndConstOperator))) && (posIndConst >= 1)) {
                                    LAPreIndConstOperator = StrIndConst.charAt(posIndConst - 1);
                                }
                                log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TempVar=" + (char) TempVar);
                                if ((LAPreIndConstOperator == '-') || (LAPreIndConstOperator == '+') && (posIndConst >= 2)) {
                                    LAPreIndConstOperator = StrIndConst.charAt(posIndConst - 2);
                                }
                                log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TempVar=" + (char) TempVar);
                            } else {
                                LAPreIndConstOperator = '*';
                                log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TempVar=" + (char) TempVar);
                            }
                        } else {
                            LAPreIndConstOperator = '*';
                            log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
                        }
                    }
                    TermOperatorPos = -1;
                    TermOutputIndConst = 0.0;
                    TempIndConstBase = "";
                    log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
                    log.info("LookAheadIndConst:parseIndConst:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                } else {
                    log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
                    log.info("LookAheadIndConst:parseIndConst:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                    posIndConst = GotIndConstPos;
                    if (StrIndConst.length() > 1) {
                        chIndConst = StrIndConst.charAt(posIndConst);
                        if ((chIndConst == '-') || (chIndConst == '+')) {
                            nextCharIndConst();
                        }
                    }
                    TermOperatorPos = -1;
                    TermOutputIndConst = 0.0;
                    TempIndConstBase = "";
                    log.info("LookAheadIndConst:parseIndConst:GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
                    log.info("LookAheadIndConst:parseIndConst:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                }
                int StartPos = posIndConst;
                log.info("LookAheadIndConst:parseIndConst:StartPos=" + StartPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                if ((TermOperatorPos > -1) && (TermOperatorPos < StrIndConst.length())) {
                    log.info("LookAheadIndConst:parseIndConst:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrIndConst.charAt(TermOperatorPos) + " chIndConst=" + (char) chIndConst);
                }
                ParseLog("LookAheadIndConst", "parseIndConst", "Initial", "StrIndConst", StrIndConst);
                if ((StrIndConst != null) && (StrIndConst.length() > 0)
                        && (StrIndConst.length() < 2)
                        && ((chIndConst == '*')
                        || (chIndConst == '/')
                        || (chIndConst == '^'))) {
                    log.info("LookAheadIndConst:parseIndConst:Before parseTerm:Final:x.eval()=" + x.eval());
                    return x;
                } else {
                    x = parseTerm();
                }
                //if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                //    ParseLog("LookAheadIndConst","parseIndConst", "BeforeMap","StrIndConst", StrIndConst);
                //    ParseMap("LookAheadIndConst:parseIndConst", StrIndConst.length(), 0, DiffWithRespTo);
                //}
                log.info("LookAheadIndConst:parseIndConst:Variable:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreTermOperator=" + (char) LAPreTermOperator);
                if (x.eval().contains("(")) {
                    CompleteStr = x.eval();//Do Nothing as eval already is finished with function & Argument
                }
                if ((x.eval().equalsIgnoreCase("Infinity")) || (x.eval().equalsIgnoreCase("NaN"))) {
                    ;//Do Nothing as e.eval() is already mapped 
                }
                if (x.eval().startsWith("1.0\\*")) {
                    final String TempStr = x.eval().replaceFirst("1.0\\*", "");
                    x = (() -> TempStr);
                }
                log.info("LookAheadIndConst:parseIndConst:PreFinal:Variable:x.eval()=" + x.eval());
                if ((!CompleteStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1.0")) && (!CompleteStr.equalsIgnoreCase(x.eval()))) {
                    CompleteStr = CompleteStr + "*" + x.eval();
                } else if ((!CompleteStr.equalsIgnoreCase("")) && (x.eval().equalsIgnoreCase("1.0"))) {
                    CompleteStr = CompleteStr;
                } else {
                    CompleteStr = x.eval();
                }
                log.info("LookAheadIndConst:parseIndConst:Final:CompleteStr=" + CompleteStr + " x.eval()=" + x.eval());
                final String MyString = CompleteStr;
                if ((posIndConst < (StrIndConst.length() - 1)) && (chIndConst != -1) && (chIndConst != 0)) {
                    x = (() -> MyString);
                } else if (posIndConst < (StrIndConst.length() - 1)) {
                    x = (() -> MyString);
                } else {
                    x = (() -> MyString);
                }
                log.info("LookAheadIndConst:parseIndConst:Final:x.eval()=" + x.eval());
                ParseLog("LookAheadIndConst", "parseIndConst", "Final", "StrIndConst", StrIndConst);
//                if ((posVar != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(posVar, str.length()).equalsIgnoreCase(""))&&(posVar <= str.length())) {
//                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadVar:parse:Unexpected: " + (char) chVar + " int=" + (int) chVar + " posVar=" + posVar + " str=" + str.substring(posVar, str.length()));
//                }
                ComplexAssignFlag = false;
                return x;
            }

            LookAheadIndConst parseTerm() {
                int StartPos = posIndConst;
                LookAheadIndConst x = (() -> "");
                log.info("LookAheadIndConst:parseTerm:Initial:Initial Value:TermOutputIndConst=" + TermOutputIndConst + " TermOperatorPos=" + TermOperatorPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                if ((posIndConst >= 1) && (IsVariable(StrIndConst.charAt(posIndConst - 1))) && (!DiffWithRespTo.equalsIgnoreCase("" + StrIndConst.charAt(posIndConst - 1)))) {
                    posIndConst--;
                    chIndConst = StrIndConst.charAt(posIndConst);
                }
                log.info("LookAheadIndConst:parseTerm:Initial:Initial Value:TermOutputIndConst=" + TermOutputIndConst + " TermOperatorPos=" + TermOperatorPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                for (;;) {
                    if ((eatVar('*')) || ((TermOperatorPos == -1) && (IsVariable(chIndConst)))) { // multiplication
                        log.info("LookAheadIndConst:parseTerm:Multiply:Initial Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst + " TermOperatorPos=" + TermOperatorPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        LookAheadIndConst a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst += 1.0;
                            }
                            if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                                x = (() -> "1.0");
                            } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase(""))) {
                                x = (() -> "1.0");
                            } else {
                                x = (() -> b.eval());
                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                    log.info("LookAheadIndConst:parseTerm:Multiply:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                                }
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                            x = (() -> "1.0");
                            log.info("LookAheadIndConst:parseTerm:Multiply:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst += 1.0;
                            }
                            x = (() -> a.eval());

                            log.info("LookAheadIndConst:parseTerm:Multiply:DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst += 1.0;
                            }
                            if (a.eval().equalsIgnoreCase("1.0")) {
                                x = (() -> (b.eval()));

                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (!StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                x = (() -> (a.eval() + "*" + b.eval()));

                            } else if ((!a.eval().equalsIgnoreCase("")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> a.eval());

                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                x = (() -> a.eval());
                            } else {
                                x = (() -> b.eval());

                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                }
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> (a.eval() + "*" + b.eval()));
                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                }
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());

                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        GotVariablePos = -1;
                        log.info("LookAheadIndConst:parseTerm:Multiply:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        if ((chIndConst == '+') || (chIndConst == '-')) {
                            log.info("LookAheadIndConst:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                            return x;
                        } else if ((chIndConst != -1) && (chIndConst != 0)) {
                            SyncIndConst();
                        }
                    } else if ((eatVar('/'))) { // division
                        log.info("LookAheadIndConst:parseTerm:Divide:Initial Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst + " TermOperatorPos=" + TermOperatorPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        LookAheadIndConst a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst -= 1.0;
                            }
                            if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                                x = (() -> "1.0");
                            } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase(""))) {
                                x = (() -> "1.0");
                            } else {
                                x = (() -> b.eval());
                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                    log.info("LookAheadIndConst:parseTerm:Divide:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                                }
                            }
                            log.info("LookAheadIndConst:parseTerm:Divide:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                            x = (() -> "1.0");
                            log.info("LookAheadIndConst:parseTerm:Divide:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst -= 1.0;
                            }
                            x = (() -> a.eval());

                            log.info("LookAheadIndConst:parseTerm:Divide:DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst -= 1.0;
                            }
                            if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> (a.eval() + "/" + b.eval()));

                            } else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                                x = (() -> a.eval());

                            } else {
                                x = (() -> b.eval());

                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                }
                            }
                            log.info("LookAheadIndConst:parseTerm:Divide:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());

                        }
                        TermOperatorPos = 0;//Should never Enter again
                        GotVariablePos = -1;
                        log.info("LookAheadIndConst:parseTerm:Divide:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        if ((chIndConst == '+') || (chIndConst == '-')) {
                            log.info("LookAheadIndConst:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                            return x;
                        } else if ((chIndConst != -1) && (chIndConst != 0)) {
                            SyncIndConst();
                        }
                    } else {
                        log.info("LookAheadIndConst:parseTerm:Return Value:Initial Value:TermOperatorPos=" + TermOperatorPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        log.info("LookAheadIndConst:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                        return x;
                    }
                }
            }

            LookAheadIndConst parseFactor() {
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                TempIndConstBase = "";
                int StartPos = posIndConst;
                LookAheadIndConst x = parseBase();
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval());
                if ((!x.eval().equalsIgnoreCase(""))
                        && (!x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    TempIndConstBase = x.eval();
                }
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval() + " TempIndConstBase=" + TempIndConstBase);
                for (;;) {
                    HaltingProblem("LookAheadIndConst:parseFactor:", posIndConst - StartPos, chIndConst, "StrIndConst", StrIndConst, posIndConst, StartPos);
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadIndConst a = x, b = parseBase();
                        StartPos = posIndConst;
                        GotExpntNumFlag = false;
                        log.info("LookAheadIndConst:parseFactor:Exponent:b.eval()=" + b.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                        if ((!b.eval().equalsIgnoreCase("")) && (!TempIndConstBase.equalsIgnoreCase(""))) {
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // 2^3
                                x = (() -> "1.0");
                            } //                            else if ((IsAConstant(a.eval(), DiffWithRespTo))&&(IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("0.0"))) {
                            //                                TermOutputIndConst += Double.parseDouble(b.eval());
                            //                                TermOutputSetFlag = true;
                            //                                x = b;
                            //                            } 
                            else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // 2^a
                                TermOutputSetFlag = true;
                                x = (() -> a.eval() + "^" + b.eval());
                                TempIndConstStrSet = true;
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // 2^x
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // a^b
                                TermOutputSetFlag = true;
                                x = (() -> a.eval() + "^" + b.eval());
                                TempIndConstStrSet = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // a^2
                                TermOutputIndConst += Double.parseDouble(b.eval());
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                            } else {
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            }

                        } else {
                            x = a;
                        }
                        log.info("LookAheadIndConst:parseFactor:Exponent:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                    } else {
                        log.info("LookAheadIndConst:parseFactor:Return Value:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                        return x;
                    }
                }
            }

            LookAheadIndConst parseBase() {
                int startPos = posIndConst;
                if ((posIndConst >= 0) && (posIndConst < StrIndConst.length())) {
                    log.info("LookAheadIndConst:parseBase:Entered:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " StrIndConst=" + StrIndConst.substring(startPos));
                }
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                LookAheadIndConst x = (() -> "");
                if (eatVar('-')) { // unary minus
                    LookAheadIndConst b = parseBase();
                    if (GotExpntNumFlag == true) {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "-" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> "-" + b.eval());
                        }
                        log.info("LookAheadIndConst:parseBase:Number:x.eval()=" + x.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                    } else {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> b.eval());
                        }
                        log.info("LookAheadIndConst:parseBase:Number:x.eval()=" + x.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                    }
                    return x;
                } else if (eatVar('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chIndConst))) { //numbers
                    log.info("LookAheadIndConst:parseBase:Exponent:Number:posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " StrIndConst=" + StrIndConst.substring(startPos));
                    while ((IsNumber(chIndConst)) && (chIndConst != -1)) {
                        nextCharIndConst();
                        if ((chIndConst == 'E') || (chIndConst == 'e')) {
                            nextCharIndConst();
                            if ((chIndConst == '-') || (chIndConst == '+')) {
                                nextCharIndConst();
                            }
                        }

                    }
                    double xx = 1.0;
                    if ((GotExpntNumFlag == true) && (!TempIndConstBase.equalsIgnoreCase("")) && (posIndConst != -1) && (StrIndConst != null) && (!StrIndConst.equalsIgnoreCase("")) && (!StrIndConst.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadIndConst:parseBase:Expnt Num:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrIndConst.substring(startPos, posIndConst));
                    } else if ((GotExpntNumFlag == true) && (!TempIndConstBase.equalsIgnoreCase("")) && (StrIndConst != null) && (!StrIndConst.equalsIgnoreCase("")) && (!StrIndConst.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadIndConst:parseBase:Expnt Num:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrIndConst.substring(startPos));
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (timesLoop == 0) && (startPos != posIndConst) && (posIndConst != -1) && (StrIndConst != null) && (!StrIndConst.equalsIgnoreCase("")) && (!StrIndConst.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrIndConst.substring(startPos, posIndConst));
                        log.info("LookAheadIndConst:parseBase:Trailing Constant:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (TempIndConstStrSet == false) && (startPos != posIndConst) && (posIndConst != -1) && (StrIndConst != null) && (!StrIndConst.equalsIgnoreCase("")) && (!StrIndConst.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrIndConst.substring(startPos, posIndConst));
                        log.info("LookAheadIndConst:parseBase:Base^:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    } else {
                        xx = 1.0;
                    }
                    log.info("LookAheadIndConst:parseBase:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " GotExpntNumFlag=" + GotExpntNumFlag + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    final Double XX = xx;
                    if (XX >= 1.0) {
                        x = () -> "" + XX;
                    } else {
                        x = () -> "";
                    }
                    log.info("LookAheadIndConst:parseBase:Number:xx=" + xx + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    return x;
                } else if (IsVariable(chIndConst)) {//functions & variables
                    int startPos1 = posIndConst;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while (((IsVariable(chIndConst)) || (IsNumber(chIndConst))) && (chIndConst != -1)) {
                        nextCharIndConst();
                    }
                    String name = "";
                    if (posIndConst != -1) {
                        name = StrIndConst.substring(startPos, posIndConst).trim();
                    } else {
                        name = StrIndConst.substring(startPos).trim();
                    }

                    log.info("LookAheadIndConst:parseBase:Variable:name=" + name + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                    if ((functionsSimple.containsKey(name)) && (!name.equalsIgnoreCase(DiffWithRespTo))) {
                        LookAheadIndConst arg = (() -> "");
                        log.info("LookAheadIndConst:parseBase:Function:name=" + name + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        eatVar('(');
                        int openBracesCount = 1;
                        int closeBracesCount = 0;
                        int startcount = posIndConst;
                        while ((posIndConst < StrIndConst.length()) && ((chIndConst != ')') || (openBracesCount != closeBracesCount))) {
                            nextCharIndConst();
                            if (chIndConst == '(') {
                                openBracesCount++;
                            }
                            if (chIndConst == ')') {
                                closeBracesCount++;
                            }
                        }
                        if ((chIndConst == ')') && (StrIndConst.substring(startcount, posIndConst).contains(")"))) {
                            posIndConst--;
                        }
                        String Exprn = StrIndConst.substring(startcount, posIndConst);
                        arg = (() -> Exprn);
                        eatVar(')');
                        final String MyFnStr = name;
                        final String MyStr = arg.eval();
                        log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + arg.eval() + " posVar=" + posVar + " chVar=" + (char) chVar);
                        if ((IsAConstant(Exprn, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Number"))
                                || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                            //x = (() -> MyFnStr + "(" + MyStr + ")");

                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            ChaosPrevention();
                            Expression MyMappedSimple = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                            ParseLog("LookAheadIndConst", "parseBase", "AfterParseExpr", "StrParen", StrParen);
                            RestoreStateParen(variablesBackupState);
                            RestoreOtherStateParen(variablesBackupState);
                            RestoreControlStateVar(variablesBackupState);
                            ParseLog("LookAheadIndConst", "parseBase", "BeforeMap", "StrParen", StrParen);
                            //ParseMap("LookAheadIndConst:parseBase", StrParen.length(), 0, 0, DiffWithRespTo);
                            //ParseLog("LookAheadIndConst", "parseBase", "Final", "StrParen", StrParen);
                            ParseLog("LookAheadIndConst", "parseBase", "AfterRestore", "StrParen", StrParen);
                            x = (() -> MyFnStr + "(" + MyMappedSimple.eval() + ")");
                            log.info("LookAheadIndConst:parseBase:Function:Constant:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            //GotBracesVarFlag = true;
                            //ContainsFuncFlag = true;
                        } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {

                            log.info("LookAheadIndConst:parseBase:Function:Var:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                            //GotBracesVarFlag = true;
                            //ContainsFuncFlag = true;
                        } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + arg.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            SimpleExpression MyMappedArg = parseSimple(StrIndConst.substring(startcount, posIndConst), variables, DiffWithRespTo);
                            RestoreStateAll(variablesBackupState);
                            final String MySimpleExprn = MyMappedArg.SimpleExpr();
                            log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + MyStr + " MySimpleExprn=" + MySimpleExprn + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                            if (!MyStr.equalsIgnoreCase("")) {
                                x = (() -> MyFnStr + "(" + MySimpleExprn + ")");
                            }
                            log.info("LookAheadIndConst:parseBase:Complete Function String:x.eval()=" + x.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                            //GotBracesVarFlag = true;
                            //ContainsFuncFlag = true;
                        }
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (!name.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity"))) {
                        ProLambdaExtr y = parseExpr("LookAheadIndConst", name, "1.0", "ProductRule", DiffWithRespTo, '*', "LowLevelRules");
                        final String MyStr = y.StrExpr();
                        x = () -> MyStr;
                    }
                    if ((!name.equalsIgnoreCase(DiffWithRespTo)) && (variables.containsKey(name))) {
                        //ContainsIndConstFlag = true;
                        //GotIndConstFlag = true;
                        //GotIndConstPos = startPos1;
                    } else if (ComplexAssignFlag == false) {
                        GotVariablePos = startPos1;
                        if ((startPos1 >= 2) && ((StrIndConst.charAt(startPos1 - 1) == '+') || (StrIndConst.charAt(startPos1 - 1) == '-'))) {
                            TermOperatorPos = startPos1 - 2;
                        } else if (startPos1 >= 1) {
                            TermOperatorPos = startPos1 - 1;
                        }
                        GotVariableFlag = true;
                        ComplexAssignFlag = true;
                    }
                    return x;
                } else {
                    if (((IsNotAnOperator(chIndConst)) && ((!IsNumber(chIndConst)) && (!IsVariable(chIndConst)))) && (chIndConst != 0) && (chIndConst != -1) && (chIndConst != '(')) {
                        if ((StrIndConst != null) && (startPos < StrIndConst.length()) && (startPos < posIndConst) && (chIndConst != -1) && (chIndConst != 0)) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                        } else if ((chIndConst != -1) && (chIndConst != 0) && (posIndConst >= 1) && (posIndConst < StrIndConst.length()) && (IsNumber(chIndConst))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                        } else if ((chIndConst != -1) && (chIndConst != 0) && (posIndConst >= 1) && (posIndConst < StrIndConst.length()) && (IsOperator(chIndConst))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                        } else if ((chIndConst != -1) && (chIndConst != 0) && (posIndConst >= 1) && (posIndConst < StrIndConst.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadIndConst:parseBase:Unexpected char: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                        }
                    }
                    return x;
                }
            }
        }.parseIndConst();
    }
//Created by Rajesh Pai
// Simplifies Terms with multiplication by Zero: x*0.0=0 OR/And One:x*1.0 = x And/OR addition of Zero: x+0.0=x
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SimpleExpression {

        String SimpleExpr();
    }

    public static SimpleExpression parseSimple(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            SimpleExpression parseSimple() {
                ResetVars("ResetSimpleExpr");
                Str = str;
                pos = -1;
                ch = -1;
                if ((pos >= 0) && (pos < Str.length())) {
                    ch = Str.charAt(pos);
                }
                NumCoeff = 0;

                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                SavedOperator = 0;
                SimpleExpression x = (() -> "");
                if ((str == null) || (str.equalsIgnoreCase(""))) {
                    return x;
                }
                if ((ch == 0) || (ch == -1)) {
                    ch = str.charAt(0);
                    pos = 0;
                }
                String OutputExprn = "";
                int startpos = pos;
                if ((Str != null) && (Str.length() > 0)
                        && (Str.length() < 2)
                        && ((ch == '*')
                        || (ch == '/')
                        || (ch == '^'))) {
                    return x;
                } else {
                    x = parseTermSimple();
                }
                log.info("Simple:parseSimple:x.SimpleExpr()=" + x.SimpleExpr());
                if (x.SimpleExpr().contains("--")) {
                    OutputExprn = x.SimpleExpr().replaceAll("--", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.SimpleExpr().contains("++")) {
                    OutputExprn = x.SimpleExpr().replaceAll("\\+\\+", "+");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.SimpleExpr().contains("+-")) {
                    OutputExprn = x.SimpleExpr().replaceAll("\\+-", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.SimpleExpr().startsWith("+")) {
                    OutputExprn = x.SimpleExpr().replaceFirst("\\+", "");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.SimpleExpr().contains(" ")) {
                    OutputExprn = x.SimpleExpr().replaceAll("\\s", "");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                log.info("Simple:parseSimple:x.SimpleExpr()=" + x.SimpleExpr());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                return x;
            }

            //Grammar:
            //Parenthesis = '(' expression ')' | function '(' expression ')'
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            SimpleExpression parseTermSimple() {
                log.info("SimpleExpression:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                SimpleExpression x = (() -> "");
                String xExprn = "";
                SimpleExpression a = (() -> "");
                SimpleExpression b = (() -> "");
                SimpleTerm u = (() -> "");

                for (;;) {
                    if ((ch != -1) && (ch != 0)) {
                        a = x;
                        if ((pos >= 0) && (pos < Str.length())) {
                            log.info("Simple:parseTermSimple:Begining:Str=" + Str.substring(pos) + " pos=" + pos + " ch=" + (char) ch);
                        }
                        String Exprn = "";
                        if ((pos >= 0) && (pos < Str.length())) {
                            Exprn = Str.substring(pos, Str.length());
                        }
                        u = parseSimpleTerm(Exprn, variables, DiffWithRespTo);
                        final String Term1 = a.SimpleExpr();
                        final String Term2 = u.SimpleTerm();
                        log.info("Simple:parseTermSimple:a.SimpleTerm()=" + Term1);
                        log.info("Simple:parseTermSimple:u.SimpleTerm()=" + Term2);
                        if ((Term1.equalsIgnoreCase("")) || (Term1.equalsIgnoreCase("0.0")) || (Term1.equalsIgnoreCase("0")) || (Term1.equalsIgnoreCase("-0.0")) || (Term1.equalsIgnoreCase("-0")) || (Term1.equalsIgnoreCase("+0.0")) || (Term1.equalsIgnoreCase("+0"))) {
                            if ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0"))) {
                                x = (() -> Term2);
                            } else {
                                x = (() -> "");
                            }
                            log.info("Simple:parseTermSimple:if:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " SavedOperator=" + (char) SavedOperator);
                        } else if (((!Term1.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("0.0")) && (!Term1.equalsIgnoreCase("0")) && (!Term1.equalsIgnoreCase("-0.0")) && (!Term1.equalsIgnoreCase("-0")) && (!Term1.equalsIgnoreCase("+0.0")) && (!Term1.equalsIgnoreCase("+0")))
                                && ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0")))) {
                            x = (() -> Term1 + Term2);
                            log.info("Simple:parseTermSimple:elseif:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " SavedOperator=" + (char) SavedOperator);
                        }
//                        //Prevent Infinite Loops
//                        if ((Term2.equalsIgnoreCase("")) && (ch != -1)) {
//                            ch = -1;
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:xExprn=" + xExprn);
//                        } else if ((pos >= 0) && (pos <= Str.length()) && (Str.substring(pos, Str.length()).isEmpty())) {
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:xExprn=" + xExprn);
//                            return x;
//                        } else if (pos < -1) {
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
//                            log.info("Simple:parseTermSimple:Infinite Loop Check Invoked:Return Value:xExprn=" + xExprn);
//                            return x;
//                        }
                        log.info("Simple:parseTermSimple:Return From Inside Loop parseTermSimple:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " SavedOperator=" + (char) SavedOperator);
                        log.info("Simple:parseTermSimple:Return From Inside Loop parseTermSimple:Term1=" + Term1);
                        log.info("Simple:parseTermSimple:Return From Inside Loop parseTermSimple:Term2=" + Term2);
                    } else {
                        log.info("Simple:parseTermSimple:Return Value:x.SimpleExpr()=" + x.SimpleExpr() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
                        log.info("Simple:parseTermSimple:Return Value:xExprn=" + xExprn);
                        return x;
                    }
                }
            }

        }.parseSimple();
    }
//Created by Rajesh Pai
// Simplifies Terms with multiplication/division :
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SimpleTerm {

        String SimpleTerm();
    }

    public static SimpleTerm parseSimpleTerm(String strTerm, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {
            public void nextCharTerm() {
                ++posTerm;
                chTerm = (posTerm < StrTerm.length()) ? StrTerm.charAt(posTerm) : -1;
                if ((chTerm == -1) && (posTerm >= StrTerm.length())) {
                    posTerm = StrTerm.length();
                } else if ((chTerm == -1) || ((chTerm == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm:nextCharTerm:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + StrTerm.substring(posTerm, StrTerm.length()));
                }
            }

            boolean eatTerm(int CharToEat) {
                while (chTerm == ' ') {
                    nextCharTerm();
                }
                if (chTerm == CharToEat) {
                    nextCharTerm();
                    return true;
                }
                return false;
            }

            String eatTermAll(String Temp, int CharToEat) {
                String MyTempString = "";
                //if (Temp.equalsIgnoreCase("")) {
                //    Temp = strTerm;
                //}
                for (int i = 0; (i < Temp.length());) {
                    if (CharToEat == Temp.charAt(i)) {
                        i++;
                    } else {
                        MyTempString = MyTempString + Temp.charAt(i);
                        i++;
                    }
                }
                return MyTempString;
            }

            SimpleTerm parseSimpleTerm() {
                ResetVars("ResetSimpleTerm");
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                SavedOperator = 0;
                ComplexAssignFlag = false;
                TrailConstantFlag = false;
                timesLoop = 0;
                StrTerm = strTerm;
                log.info("SimpleTerm:parseSimpleTerm:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " StrTerm=" + StrTerm);
                String OutputExprn = "";
                int startpos = pos;
                SimpleTerm x = (() -> "");
                if ((Str != null) && (pos < Str.length())) {
                    posTerm = -1;
                    if (strTerm.length() >= 1) {
                        chTerm = strTerm.charAt(0);
                    }
                    posVar = -1;
                    chVar = -1;
                    posLA = -1;
                    chLA = -1;
                    GotVariablePos = -1;
                    GotIndConstPos = -1;
                    GotBracesVarFlag = false;
                    GotIndConstFlag = false;
                    TrailConstantFlag = false;
                    ContainsIndConstFlag = false;
                } else {
                    posTerm = -1;
                    chTerm = -1;
                    pos = -1;
                    ch = -1;
                    posVar = -1;
                    chVar = -1;
                    posLA = -1;
                    chLA = -1;
                    GotVariablePos = -1;
                    GotIndConstPos = -1;
                    GotBracesVarFlag = false;
                    GotIndConstFlag = false;
                    TrailConstantFlag = false;
                    ContainsIndConstFlag = false;
                    x = (() -> "");
                    return x;
                }
                if (posTerm >= 0) {
                    log.info("SimpleTerm:parseSimple:StrTerm=" + StrTerm.substring(posTerm) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                }
                log.info("SimpleTerm:parseSimpleTerm:Before LookAheadSign:pos=" + pos + " ch=" + (char) ch + " ch=" + ch + " StrTerm=" + StrTerm);
                ParseLog("SimpleTerm", "parseSimple", "Initial", "StrTerm", StrTerm);
                LookAheadSign y = parseSign(StrTerm, startpos, DiffWithRespTo);
                ParseLog("SimpleTerm", "parseSimple", "Initial", "StrTerm", StrTerm);
                if ((StrTerm != null) && (StrTerm.length() > 0)
                        && (StrTerm.length() < 2)
                        && ((chTerm == '*')
                        || (chTerm == '/')
                        || (chTerm == '^'))) {
                    return x;
                } else {
                    x = parseTermSimple();
                }
                log.info("SimpleTerm:parseSimple:x.SimpleExpr()=" + x.SimpleTerm());
                ParseLog("SimpleTerm", "parseSimple", "BeforeMap", "StrTerm", StrTerm);
                ParseMap("SimpleTerm:Max:parseSimple", StrTerm.length(), 0, 0, DiffWithRespTo);
                ParseLog("SimpleTerm", "parseSimple", "Final", "StrTerm", StrTerm);
                if ((startpos == 0) && (chTermSign == '+') && (!x.SimpleTerm().equalsIgnoreCase(""))) {
                    final String FinalOutput = x.SimpleTerm();
                    x = (() -> FinalOutput);
                } else if (!x.SimpleTerm().equalsIgnoreCase("")) {
                    final String FinalOutput = x.SimpleTerm();
                    if ((chTermSign != -1) && (chTermSign != 0) && ((chTermSign == '+') || (chTermSign == '-'))) {
                        x = (() -> (char) chTermSign + FinalOutput);
                    } else if ((chTermSign == -1) || (chTermSign == 0)) {
                        x = (() -> "+" + FinalOutput);
                    }//Need Better logic to handle '*' && '/' in chTermSign
                    else {
                        x = (() -> "");
                    }
                } else {
                    x = (() -> "");
                }
                GotVariablePos = -1;
                GotIndConstPos = -1;
                TrailConstantFlag = false;
                GotIndConstFlag = false;
                GotVariableFlag = false;
                ContainsIndConstFlag = false;
                log.info("SimpleTerm:parseSimple:With Sign:x.SimpleTerm()=" + x.SimpleTerm());
//                if (x.SimpleTerm().contains("--")) {
//                    OutputExprn = x.SimpleTerm().replaceAll("--", "-");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
//                if (x.SimpleTerm().contains("++")) {
//                    OutputExprn = x.SimpleTerm().replaceAll("\\+\\+", "+");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
                if ((chTerm == ')') && (GotBracesVarFlag == true)) {
                    nextCharTerm();
                    GotBracesVarFlag = false;
                }
                log.info("SimpleTerm:parseSimple:Final:posTerm=" + posTerm + " startpos=" + startpos + " chTerm=" + (char) chTerm + " ComplexAssignFlag=" + ComplexAssignFlag);
                log.info("SimpleTerm:parseSimple:Final:pos=" + pos + " ch=" + (char) ch);
                log.info("SimpleTerm:parseSimple:x.SimpleTerm()=" + x.SimpleTerm());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm:Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                return x;
            }

            SimpleTerm parseExpressionSimple() {
                log.info("SimpleTerm:parseExpressionSimple:Topmost:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                String xExprn = "";
                String OutputExprn = "";
                log.info("SimpleTerm:parseExpressionSimple:Initial:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                SimpleTerm x = (() -> "");
                x = parseTermSimple();
                log.info("SimpleTerm:parseExpressionSimple:x.SimpleTerm()=" + x.SimpleTerm());
                log.info("SimpleTerm:parseExpressionSimple:Return From Inside Loop parseExpressionSimple:x.SimpleTerm()=" + x.SimpleTerm() + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                log.info("SimpleTerm:parseExpressionSimple:Return From Inside Loop parseExpressionSimple:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chTerm=" + (char) chTerm);
                log.info("SimpleTerm:parseExpressionSimple:Return From Inside Loop parseExpressionSimple:x.SimpleTerm()=" + x.SimpleTerm() + " xExprn=" + xExprn);
                return x;
            }

            SimpleTerm parseTermSimple() {
                log.info("SimpleTerm:parseTermSimple:Topmost:posTerm=" + posTerm + " ch=" + (char) chTerm + " chTerm=" + chTerm);
                int TermStartPos = -1;
                int TermEndPos = -1;
                if (posTerm >= 0) {
                    TermStartPos = posTerm;
                } else if (posTerm < 0) {
                    TermStartPos = 0;
                }
                SimpleTerm x = (() -> "");
                SimpleTerm a = (() -> "");
                SimpleTerm b = (() -> "");
                x = parseBaseSimple();
                log.info("SimpleTerm:parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                String xExprn = "";
                log.info("SimpleTerm:parseTermSimple:Top:pos=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                log.info("SimpleTerm:parseTermSimple:Initial Call to FactorSimple:xExprn=" + xExprn + " posTerm=" + posTerm);
                log.info("SimpleTerm:parseTermSimple:Initial Call to FactorSimple:OutputExprn=" + "" + " posTerm=" + posTerm);
                for (;;) {
                    log.info("SimpleTerm:parseTermSimple:Top:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                    if ((TermStartPos < posTerm) && (TermStartPos > 0)) {
                        HaltingProblem("LookAheadParen:SimpleTerm:parseTermSimple:", posTerm - TermStartPos, chTerm, "StrTerm", StrTerm, posTerm, TermStartPos);
                    }
                    if (eatTerm('*')) { // multiplication
                        log.info("SimpleTerm:parseTermSimple:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                        a = x;
                        b = parseBaseSimple();
                        log.info("SimpleTerm:parseTermSimple:a.SimpleTerm()=" + a.SimpleTerm());
                        log.info("SimpleTerm:parseTermSimple:b.SimpleTerm()=" + b.SimpleTerm());
                        log.info("SimpleTerm:parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                        if ((!b.SimpleTerm().equalsIgnoreCase("")) && (IsAConstant(b.SimpleTerm(), DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermSimple1", "", a, "", b, "", b.SimpleTerm(), "", "*", "ConstantRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple1", "", a, "", b, "", b.SimpleTerm(), "", "*", "ConstantRule", DiffWithRespTo).trim();
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop ConstantRule: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop ConstantRule: parseTermSimple:xExprn=" + xExprn);
                        } else {
                            x = ApplyFuncRules("parseTermSimple2", xExprn, a, "", b, b.SimpleTerm(), "", "", "*", "MappedProductRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple2", xExprn, a, "", b, b.SimpleTerm(), "", "", "*", "MappedProductRule", DiffWithRespTo).trim();
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop Mapped: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop Mapped: parseTermSimple:xExprn=" + xExprn);
                        }
                        int temp = chTerm;
                        nextCharTerm();
                        if (((chTerm == '+') || (chTerm == '-')) && (temp != '*') && (temp != '/')) {

                            if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                                TermEndPos = posTerm;
                            } else {
                                TermEndPos = StrTerm.length();
                            }
                            posTerm--;
                            chTerm = temp;
                            log.info("SimpleTerm:parseTermSimple:Return From Inside Loop parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm() + " xExprn=" + xExprn + " Simplified Term=" + StrTerm.substring(TermStartPos, TermEndPos) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                            return x;
                        } else if ((chTerm != -1) && (chTerm != 0)) {
                            posTerm--;
                            chTerm = temp;
                        }
                    } else if (eatTerm('/')) { // division
                        log.info("SimpleTerm:parseTermSimple:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " ch=" + chTerm);
                        a = x;
                        b = parseBaseSimple();
                        if ((!b.SimpleTerm().equalsIgnoreCase("")) && (IsAConstant(b.SimpleTerm(), DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermSimple3", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "ConstantRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple3", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "ConstantRule", DiffWithRespTo).trim();
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop ConstantRule: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop ConstantRule: parseTermSimple:xExprn=" + xExprn);
                        } else {
                            x = ApplyFuncRules("parseTermSimple4", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "MappedQuotientRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple4", "", a, "", b, b.SimpleTerm(), "", "", "/", "MappedQuotientRule", DiffWithRespTo);
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop Mapped: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop Mapped: parseTermSimple:xExprn=" + xExprn);
                        }
                        int temp = chTerm;
                        nextCharTerm();
                        if (((chTerm == '+') || (chTerm == '-')) && (temp != '*') && (temp != '/')) {
                            if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                                TermEndPos = posTerm;
                            } else {
                                TermEndPos = StrTerm.length() - 1;
                            }
                            posTerm--;
                            chTerm = temp;
                            log.info("SimpleTerm:parseTermSimple:Return From Inside Loop parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm() + " xExprn=" + xExprn + " Simplified Term=" + StrTerm.substring(TermStartPos, TermEndPos) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                            return x;
                        } else if ((chTerm != -1) && (chTerm != 0)) {
                            posTerm--;
                            chTerm = temp;
                        }
                    } else {
                        if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                            TermEndPos = posTerm;
                        } else {
                            TermEndPos = StrTerm.length();
                        }
                        log.info("SimpleTerm:parseTermSimple:Return From Inside Loop parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm() + " xExprn=" + xExprn + " Original Term=" + StrTerm.substring(TermStartPos, TermEndPos) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                        return x;
                    }
                }
            }

            SimpleTerm parseBaseSimple() {
                log.info("SimpleTerm:parseBaseSimple:Topmost:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                int BaseStartPos = -1;
                int BaseEndPos = -1;
                if (posTerm >= 0) {
                    BaseStartPos = posTerm;
                } else if (posTerm < 0) {
                    BaseStartPos = 0;
                }
                int startPos = posTerm;
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                boolean ConstantFlag = false;
                boolean VariableFlag = false;
                boolean IndConstFlag = false;
                boolean BracesFlag = false;
                SimpleTerm x = (() -> "0.0");
                SimpleTerm Const = (() -> "0.0");
                SimpleTerm Variable = (() -> "0.0");
                SimpleTerm IndConst = (() -> "0.0");
                SimpleTerm Braces = (() -> "0.0");

                if ((chTerm == '-')) { // unary minus
                    if (posTerm == -1) {
                        log.info("SimpleTerm:parseBaseSimple:-:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
                        nextCharTerm();
                        nextCharTerm();
                        log.info("SimpleTerm:parseBaseSimple:-:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);

                    } else {
                        log.info("SimpleTerm:parseBaseSimple:-:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
                        nextCharTerm();

                    }
                    log.info("SimpleTerm:parseBaseSimple:-:chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm);
                    SimpleTerm b = (() -> "0.0");
                    b = parseBaseSimple();
                    final String MyStr = b.SimpleTerm();
                    if ((IsAConstant(MyStr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                        x = (() -> "" + (Double.parseDouble(MyStr)));
                    } else {
                        x = (() -> MyStr);
                    }
                    log.info("SimpleTerm:parseBaseSimple:-:startPos=" + startPos + " x.SimpleTerm()=" + x.SimpleTerm() + " chTerm=" + (char) chTerm + " posTerm=" + posTerm);
                    return x;
                } else if ((chTerm == '+')) { // unary plus
                    log.info("SimpleTerm:parseBaseSimple:+:chTerm=" + (char) chTerm + " posTerm=" + posTerm);
                    if (posTerm == -1) {
                        log.info("SimpleTerm:parseBaseSimple:+:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
                        nextCharTerm();
                        nextCharTerm();
                        log.info("SimpleTerm:parseBaseSimple:+:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
                    } else {
                        log.info("SimpleTerm:parseBaseSimple:+:BracesFlag=" + BracesFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
                        nextCharTerm();
                    }
                    log.info("SimpleTerm:parseBaseSimple:+:chTerm=" + (char) chTerm + " posTerm=" + posTerm);
                }
                String LastExpr = "";
                timesLoop = 0;
                log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Number:timesLoop=" + timesLoop + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());
                while ((timesLoop <= 3) && ((ConstantFlag == false) || (VariableFlag == false) || (BracesFlag == false))) {
                    ParseLog("SimpleTerm", "parseBaseSimple", "InsideLoop", "StrTerm", StrTerm);
                    if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                        log.info("SimpleTerm:parseBaseSimple:Loop Begining:StrTerm(posTerm)=" + StrTerm.substring(posTerm) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                        log.info("SimpleTerm:parseBaseSimple:Loop Begining:chTerm=" + (char) chTerm + " posTerm=" + posTerm + " timesLoop=" + timesLoop + " BracesFlag=" + BracesFlag + " ConstantFlag=" + ConstantFlag + " VariableFlag=" + VariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " TrailConstantFlag=" + TrailConstantFlag);
                    }
                    if ((timesLoop == 0) && (chTerm == '(')) {
                        nextCharTerm();
                    }
                    if (IsOperator(chTerm)) {
                        nextCharTerm();
                    }
                    //Numbers -> LookAheadConst
                    if (((IsNumber(chTerm)) && (timesLoop == 0) && (posTerm >= -1) && (chTerm == '(') && (IsNumber(StrTerm.charAt(posTerm + 1))) && (ParenthesisFlag == false)
                            && (ConstantFlag == false) && (VariableFlag == false))
                            || ((IsNumber(chTerm)) && (timesLoop == 0) && (ParenthesisFlag == false) && (ConstantFlag == false) && (VariableFlag == false))
                            || ((IsNumber(chTerm)) && (timesLoop == 0) && (ParenthesisFlag == false)
                            && ((ComplexAssignFlag == true) && (ConstantFlag == false) && (VariableFlag == true)))
                            || ((timesLoop == 1) && (TrailConstantFlag == true) && (posTerm >= 1) && (posTerm < StrTerm.length()) && (IsNumber(StrTerm.charAt(posTerm + 1)))
                            && (ConstantFlag == false) && (VariableFlag == true) && (ParenthesisFlag == false))
                            || ((ConstantFlag == false) && (timesLoop == 1) && (IsNumber(chTerm))
                            && (BracesFlag == true))
                            || ((timesLoop == 2) && (TrailConstantFlag == true)
                            && (ConstantFlag == false) && (VariableFlag == true) && (ParenthesisFlag == false))
                            || ((timesLoop == 2) && (TrailConstantFlag == true)
                            && (ConstantFlag == false) && (ParenthesisFlag == false))
                            //|| ((timesLoop == 2) && (((IsNumber(chTerm)) && (posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) != '('))
                            //    || ((BracesFlag == true) && ((StrParen == null) || (StrParen.equalsIgnoreCase(""))))))

                            || ((ConstantFlag == false) && (IsNumber(chTerm)) && (timesLoop == 3) && (posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) != '('))) { //numbers
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        //if((startPos1 > -1)&&(startPos1 < strTerm.length())){
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Number:StrTerm=" + StrTerm.substring(startPos1) + " startPos1=" + startPos1 + " startPos=" + startPos + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());

                        log.info("SimpleTerm:parseBaseSimple:Number:xx=" + StrTerm.substring(startPos1) + " startPos1=" + startPos1 + " posTerm=" + posTerm);
                        //}
                        LookAheadConst MyCoeff;
                        MyCoeff = parseLA(StrTerm.substring(startPos1), startPos1, variables, DiffWithRespTo, true);
                        String xConst = "" + MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:Number:MyCoeff=" + xConst + " timesLoop=" + timesLoop + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                        final String ConsolidatedConstTerm = xConst;
                        Const = () -> ConsolidatedConstTerm;
                        log.info("SimpleTerm:parseBaseSimple:Number:Constant:Const.SimpleTerm()=" + Const.SimpleTerm() + " LATermOperator=" + (char) LATermOperator + " pos=" + pos + " ch=" + (char) ch + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        ConstantFlag = true;
                        LastExpr = "LookAheadConst";
                    } //Variables -> LookAheadVar
                    else if ((((IsVariable(chTerm)) && (timesLoop == 0) && (VariableFlag == false) && (ContainsIndConstFlag == false)
                            && (posTerm > 1) && (StrTerm.charAt(posTerm - 1) != '(') && (chTerm != '('))
                            || ((timesLoop == 1) && (ConstantFlag == false) && (IsVariable(chTerm)) && (StrTerm.length() > 1) && (StrTerm.charAt(0) == '('))
                            || ((timesLoop == 0) && (posTerm != 0) && (StrTerm.length() > 1) && (StrTerm.charAt(0) == '('))
                            || ((IsVariable(chTerm)) && (timesLoop == 1) && (VariableFlag == false) && (ContainsIndConstFlag == false)
                            && (ConstantFlag == false) && (StrTerm.length() > 1) && (StrTerm.charAt(0) != '(')
                            && ((Str.length() > 1) && (Str.charAt(0) != '+') && (Str.charAt(0) != '-'))
                            && (BracesFlag == true)))
                            || ((timesLoop == 1) && (VariableFlag == false) && (ContainsIndConstFlag == false)
                            && ((ComplexAssignFlag == true) && (ConstantFlag == true)))
                            || ((timesLoop == 1) && (VariableFlag == false) && (ContainsIndConstFlag == true)
                            && ((ComplexAssignFlag == true) && (ConstantFlag == true)))
                            || ((IsVariable(chTerm)) && (timesLoop == 1) && (VariableFlag == false) && (ContainsIndConstFlag == false)
                            && (ConstantFlag == false))
                            || ((IsVariable(chTerm)) && (timesLoop == 1) && (VariableFlag == false)
                            && (ConstantFlag == true) && (ContainsIndConstFlag == false) && ((ComplexAssignFlag == false)))
                            || ((IsVariable(chTerm)) && (timesLoop == 2) && (VariableFlag == false) && (ConstantFlag == true) && (BracesFlag == true) && (ContainsIndConstFlag == false)
                            && (ComplexAssignFlag == true))
                            || ((IsVariable(chTerm)) && (timesLoop == 3) && (VariableFlag == false) && (ConstantFlag == true) && (BracesFlag == true) && (ContainsIndConstFlag == false)
                            && (ComplexAssignFlag == true))) {//functions & variables
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Variable:StrTerm=" + StrTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());
                        log.info("SimpleTerm:parseBaseSimple:Variable:StrTerm=" + StrTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());

                        LookAheadVar MyCoeff;
                        MyCoeff = parseVar(StrTerm.substring(startPos1), variables, DiffWithRespTo);
                        String name = MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:Variable:name=" + name + " timesLoop=" + timesLoop + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        Variable = () -> (name);
                        log.info("SimpleTerm:parseBaseSimple:Variable:Variable.SimpleTerm()=" + Variable.SimpleTerm() + " LATermOperator=" + (char) LATermOperator + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        if ((ContainsXFlag == true) || (name.contains(DiffWithRespTo))) {
                            VariableFlag = true;
                        }
                        LastExpr = "LookAheadVar";
                    } //Independent Constant -> LookAheadIndConst
                    else if (((timesLoop == 1) && (ContainsIndConstFlag == true) && (IndConstFlag == false))
                            || ((timesLoop == 2) && (ContainsIndConstFlag == true) && (IndConstFlag == false))
                            || ((timesLoop == 3) && (ContainsIndConstFlag == true) && (IndConstFlag == false))) {//Independent Constant variables
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:IndConst:StrTerm=" + StrTerm.substring(startPos1) + " startPos1=" + startPos1 + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());

                        log.info("SimpleTerm:parseBaseSimple:IndConst:StrTerm=" + StrTerm.substring(startPos1) + " startPos1=" + startPos1 + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());

                        LookAheadIndConst MyCoeff;
                        //variables.put(DiffWithRespTo, DiffWithRespTo);
                        MyCoeff = parseIndConst(StrTerm.substring(startPos1), DiffWithRespTo);
                        String name = MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:IndConst:name=" + name + " timesLoop=" + timesLoop + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        IndConst = () -> (name);
                        IndConstFlag = true;
                        LastExpr = "LookAheadIndConst";
                        log.info("SimpleTerm:parseBaseSimple:IndConst:IndConst.SimpleTerm()=" + IndConst.SimpleTerm() + " LAIndConstOperator=" + (char) LAIndConstOperator + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " ContainsIndConstFlag=" + ContainsIndConstFlag + " IndConstFlag=" + IndConstFlag);
                    } //Braces Expression -> LookAheadBraces
                    else if ((((GotBracesVarFlag == true) || (timesLoop == 0))
                            && (((posTerm >= 1) && (StrTerm != null) && (StrTerm.length() > 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) == '('))
                            || ((posTerm == 0) && (StrTerm != null) && (StrTerm.length() > 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm) == '(')))
                            || ((GotBracesVarFlag == true) && (VariableFlag == true)))) {//Braces
                        int posTemp = -1;
                        String StrTemp = "";
                        if ((posLA >= 1) && (StrLA != null) && (posLA < StrLA.length()) && (StrLA.charAt(posLA - 1) == '(')) {
                            posTemp = posLA;
                            StrTemp = StrLA;
                        } else if ((posVar >= 1) && (StrVar != null) && (posVar < StrVar.length()) && (StrVar.charAt(posVar - 1) == '(')) {
                            posTemp = posVar;
                            StrTemp = StrVar;
                        } else if (StrTerm != null) {
                            posTemp = posTerm;
                            StrTemp = StrTerm;
                        }
                        if (posTemp >= 1) {
                            posTemp--;
                        }
                        int startPos1 = posTemp;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        ParenthesisFlag = true;
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Variable:StrTerm=" + StrTerm.substring(startPos) + " LATermOperatorBraces=" + (char) LATermOperatorBraces + " startPos=" + startPos + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());
                        log.info("SimpleTerm:parseBaseSimple:Braces:StrTerm=" + StrTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " StrTerm.length()=" + StrTerm.length());
                        Braces = (() -> "0.0");
                        LookAheadParen MyCoeff = (() -> "");
                        if ((startPos1 >= 0) && (startPos1 < StrTemp.length())) {
                            MyCoeff = parseParen(StrTemp.substring(startPos1), startPos1, DiffWithRespTo, false);
                        }
                        String name = MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:Braces:name=" + name + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        Braces = () -> (name);
                        log.info("SimpleTerm:parseBaseSimple:Braces:Braces.SimpleTerm()=" + Braces.SimpleTerm() + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        BracesFlag = true;
                        ParenthesisFlag = true;
                        LastInvokedFlag = "LookAheadParen";
                        LastInvokedPos = posParen;
                        LastExpr = "LookAheadParen";
                        if ((timesLoop == 0) && (IndConstFlag == false) && (ConstantFlag == false) && (VariableFlag == false)) {
                            posTerm = posParen;
                            chTerm = StrTerm.charAt(posTerm);
                            log.info("SimpleTerm:parseBaseSimple:Map posParen:name=" + name + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posParen=" + posParen + " chParen=" + (char) chParen);
                        }
                        if (chTerm == ')') {
                            nextCharTerm();
                        }
                    }
                    if (IsOperator(chTerm)) {
                        nextCharTerm();
                    }
                    timesLoop++;
                }
                String ConstStr = "0.0";
                if (ConstantFlag) {
                    ConstStr = Const.SimpleTerm();
                }
                String VarStr = "0.0";
                if (VariableFlag) {
                    VarStr = Variable.SimpleTerm();
                }
                String IndConstStr = "0.0";
                if (IndConstFlag) {
                    IndConstStr = IndConst.SimpleTerm();
                }
                String BracesStr = "0.0";
                if (BracesFlag) {
                    BracesStr = Braces.SimpleTerm();
                }
                //Not Sure of Order of ConstStr && BraceStr:Conditions have to improve
                if ((BracesFlag == true) && (IsAConstant(BracesStr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (LATermOperatorBraces == '*') && (ConstantFlag == true)) {
                    ConstStr = "" + (Double.parseDouble(ConstStr) * Double.parseDouble(BracesStr));
                    BracesStr = "";
                    LATermOperatorBraces = -1;
                    BracesFlag = false;
                } else if ((BracesFlag == true) && (IsAConstant(BracesStr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (LATermOperatorBraces == '/') && (ConstantFlag == true)) {
                    ConstStr = "" + (Double.parseDouble(ConstStr) / Double.parseDouble(BracesStr));
                    BracesStr = "";
                    LATermOperatorBraces = -1;
                    BracesFlag = false;
                } else if ((BracesFlag == true) && (IsAConstant(BracesStr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (ConstantFlag == false)) {
                    ConstStr = "" + (Double.parseDouble(BracesStr));
                    BracesStr = "";
                    LATermOperatorBraces = -1;
                    BracesFlag = false;
                    ConstantFlag = true;
                } else if (StrIsAConstant.equalsIgnoreCase("NumberExpression")) {
                    log.info("SimpleTerm:parseBaseSimple:Calling LookAheadConst:posParen=" + posParen + " chParen=" + (char) chParen);
                    Map<String, String> variablesBackupState = new HashMap();
                    BackupStateAll(variablesBackupState);
                    LookAheadConst MyMappedArg = parseLA(BracesStr, 0, variables, DiffWithRespTo, false);
                    RestoreStateAll(variablesBackupState);
                    ConstStr = "" + (Double.parseDouble("" + MyMappedArg.eval()));
                    BracesStr = "";
                    LATermOperatorBraces = -1;
                    BracesFlag = false;
                    ConstantFlag = true;
                }
                final String CompleteString = FormTermLambda("parseBaseSimple", IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag);
                x = (() -> CompleteString);
                log.info("SimpleTerm:parseBaseSimple:Formed Complete Term:parseBaseSimple:Normal Return:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                ResetVars("ResetControlVars");
                if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                    BaseEndPos = posTerm;
                } else {
                    BaseEndPos = StrTerm.length();
                }
                //log.info("SimpleTerm:parseBaseSimple:Original Term=" + StrTerm.substring(BaseStartPos, BaseEndPos));

//                if ((IsNotAnOperator(chTerm) ) && (chTerm != 0) && (chTerm != -1) && (chTerm != '(')&& (posTerm != -1)) {
//                    if ((strTerm != null) && (startPos < StrTerm.length()) && (startPos > posTerm) && (chTerm != -1) && (chTerm != 0)) {
//                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm::parseBase:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
//                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')') && (posTerm >= 1) && (posTerm < strTerm.length()) && (IsNumber(chTerm))) {
//                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm::parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
//                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')') && (posTerm >= 1) && (posTerm < strTerm.length()) && (IsOperator(chTerm))) {
//                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm::parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
//                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')') && (posTerm >= 1) && (posTerm < strTerm.length())) {
//                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "SimpleTerm::parseBase:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
//                    }
//                    log.info("SimpleTerm:parseBaseSimple:Return From Inside Loop parseBaseSimple:Junk:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
//                    return x;
//                }
                log.info("SimpleTerm:parseBaseSimple:Return From Inside Loop parseBaseSimple:Valid Return:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                return x;
            }
        }.parseSimpleTerm();
    }

    public static SimpleTerm ApplyFuncRules(String AlgoName, String Term1, SimpleTerm Diff1, String Term2, SimpleTerm Diff2, String Term3, String Term4, String Term5, String StrOperator, String Rule, String DiffWithRespTo) {
        log.info("SimpleTerm:ApplyFuncRules:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Term1=" + Term1);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Diff1.SimpleTerm()=" + Diff1.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRules:Initial :Term2=" + Term2);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Diff2.SimpleTerm()=" + Diff2.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRules:Initial :Term3=" + Term3);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Term4=" + Term4);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Term5=" + Term5);
        log.info("SimpleTerm:ApplyFuncRules:Initial :StrOperator=" + StrOperator);
        log.info("SimpleTerm:ApplyFuncRules:Initial :Rule=" + Rule);
        log.info("SimpleTerm:ApplyFuncRules:Initial :DiffWithRespTo=" + DiffWithRespTo);
        log.info("SimpleTerm:ApplyFuncRules:Initial : End Context:pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        SimpleTerm x = (() -> "");
        final String Diff1SimpleTerm = Diff1.SimpleTerm();
        final String Diff2SimpleTerm = Diff2.SimpleTerm();
        if (Rule.equalsIgnoreCase("NoExponentRule")) {
            if (Term1.equalsIgnoreCase(Term4)) {
                x = (() -> Term1 + "*" + Term3 + "^" + Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:NoExponentRule:Rule 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else {
                x = (() -> Term1 + Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:NoExponentRule:Rule 2:x.SimpleTerm()=" + x.SimpleTerm());
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                if (Diff2SimpleTerm.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> "1.0");
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule A 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Diff2SimpleTerm.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule A 2:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = (() -> DiffWithRespTo + StrOperator + Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule A 3:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> (Diff1SimpleTerm + StrOperator + Diff2SimpleTerm));
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule A 4:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term4.equalsIgnoreCase("1.0"))) {
                x = (() -> Term4);
                log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule B 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))/*&&(!Term4.equalsIgnoreCase("1.0"))*/) {
                if (Term2.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> Term4);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule C 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Term2.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Term4 + "*" + Term1);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule C 2:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> Term4 + "*" + Term1 + "^" + Term2);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule C 3:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                if (Diff2SimpleTerm.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> ("1.0"));
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule D 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Diff2SimpleTerm.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule D 2:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (!Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = (() -> (Diff1SimpleTerm + StrOperator + Diff2SimpleTerm));
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule D 3:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = (() -> DiffWithRespTo + StrOperator + Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule D 4:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else {
                x = (() -> Term1 + "^" + Term5);
                log.info("SimpleTerm:ApplyFuncRules:CoeffExponentRule:Rule E 1:x.SimpleTerm()=" + x.SimpleTerm());
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase(Diff2SimpleTerm)) && (!(Term1.equalsIgnoreCase(DiffWithRespTo))) && (!Term1.contains("^")) && (!StrOperator.equalsIgnoreCase("^"))) {
                x = (() -> Term1 + StrOperator + Term2);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule A 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = (() -> Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule A 2:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && ((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && ((Diff2SimpleTerm.equalsIgnoreCase("")) || (Diff2SimpleTerm.equalsIgnoreCase("0.0")))) {
                x = (() -> Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule A 3:x.SimpleTerm()=" + x.SimpleTerm());
            } //            else if ((!Diff1SimpleTerm).equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm).equalsIgnoreCase("0.0"))) {
            //                x = (()->"0.0");
            //            } 
            else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0") && (Term4.equalsIgnoreCase("0.0")))) {
                x = (() -> Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule B 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm)) == 0.0) { // + 0.0
                    x = (() -> "");
                    log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule B 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm))));
                    log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule B 2:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm)) == 0.0) { // - 0.0
                    x = (() -> "");
                    log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule C 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm))));
                    log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule C 2:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) * Double.parseDouble(Diff2SimpleTerm))));
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule D 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))
                    && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                x = (() -> "0.0");
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule E 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo))
                    && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) / Double.parseDouble(Diff2SimpleTerm))));
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule F 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((!Term4.equalsIgnoreCase(Diff2SimpleTerm)) && (Term1.equalsIgnoreCase(Diff1SimpleTerm)) && (IsAConstant(Term1, DiffWithRespTo)) && ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = (() -> Diff1SimpleTerm + StrOperator + Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule G 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((!Term4.equalsIgnoreCase(Diff2SimpleTerm)) && (Term1.equalsIgnoreCase(Diff1SimpleTerm)) && (IsAConstant(Term1, DiffWithRespTo)) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = (() -> Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule H 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = (() -> Diff1SimpleTerm + StrOperator + Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:ConstantRule:Rule I 1:x.SimpleTerm()=" + x.SimpleTerm());
            }
        } else if ((Rule.equalsIgnoreCase("MappedSumRule"))
                || (Rule.equalsIgnoreCase("MappedSubtractionRule"))
                || (Rule.equalsIgnoreCase("MappedProductRule"))
                || (Rule.equalsIgnoreCase("MappedQuotientRule"))) {
            if ((StrOperator.equalsIgnoreCase("*")) && (Term1.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase("")) && (Diff1SimpleTerm.contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = (() -> Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule A 0:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = (() -> Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule A 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.equalsIgnoreCase("1.0"))) {
                x = (() -> Diff1SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule A 2:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && ((Diff1SimpleTerm.equalsIgnoreCase("0.0")) || (Diff2SimpleTerm.equalsIgnoreCase("0.0")))) {
                x = (() -> "");
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule A 3:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff1SimpleTerm.equalsIgnoreCase("1.0"))) {
                x = (() -> Diff2SimpleTerm);
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule A 4:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm)) == 0.0) { //  + 0.0
                    x = (() -> "");
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule B 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm))));
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule B 2:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm)) == 0.0) { //  - 0.0
                    x = (() -> "");
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule B 3:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm))));
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule B 4:x.SimpleTerm()=" + x.SimpleTerm());
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) * Double.parseDouble(Diff2SimpleTerm))));
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule C 1:x.SimpleTerm()=" + x.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("/")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1SimpleTerm) / Double.parseDouble(Diff2SimpleTerm))));
                log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule C 2:x.SimpleTerm()=" + x.SimpleTerm());
            } else {
                if ((StrOperator.equalsIgnoreCase("+")) && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 1:x.SimpleTerm()=" + x.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 2:x.SimpleTerm()=" + x.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 3:x.SimpleTerm()=" + x.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 4:x.SimpleTerm()=" + x.SimpleTerm());
                } else if (Diff2SimpleTerm.equalsIgnoreCase("")) {
                    // For Diffrentiation Last Term
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Diffrentiation:Rule D 5:x.SimpleTerm()=" + x.SimpleTerm());
                } else if ((Diff2SimpleTerm.equalsIgnoreCase(Term3)) && (Term1.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase("")) && (!Diff2SimpleTerm.contains(Diff1SimpleTerm))) {
                    // For Integration Last Term
                    x = (() -> Diff1SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Integration:Rule D 6:x.SimpleTerm()=" + x.SimpleTerm());
                } else if ((Diff2SimpleTerm.equalsIgnoreCase(Term3)) && (Term1.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(""))) {
                    // For Integration Last Term
                    x = (() -> Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Integration:Rule D 6:x.SimpleTerm()=" + x.SimpleTerm());
                } else {
                    x = (() -> Diff1SimpleTerm + StrOperator + Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 7:x.SimpleTerm()=" + x.SimpleTerm());
                }
            }
        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            x = x = (() -> Term1 + "*" + Term3 + "^" + Term2);
            log.info("SimpleTerm:ApplyFuncRules:ExponentRule:Rule A 1:x.SimpleTerm()=" + x.SimpleTerm());
        } else if (Rule.equalsIgnoreCase("MappedExponentRule")) {
            x = (() -> Term4 + "*" + Term3 + "^" + Diff2SimpleTerm);
            log.info("SimpleTerm:ApplyFuncRules:MappedExponentRule:Rule A 1:x.SimpleTerm()=" + x.SimpleTerm());
        }
        final String OperatorPrefixed = x.SimpleTerm();
        x = (() -> OperatorPrefixed);
        log.info("SimpleTerm:ApplyFuncRules:Final:x.SimpleTerm()=" + x.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRules:Final: End Context:pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        return x;
    }

    public static String ApplyFuncRulesString(String AlgoName, String Term1, SimpleTerm Diff1, String Term2, SimpleTerm Diff2, String Term3, String Term4, String Term5, String StrOperator, String Rule, String DiffWithRespTo) {
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Term1=" + Term1);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Diff1.SimpleTerm()=" + Diff1.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Term2=" + Term2);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Diff2.SimpleTerm()=" + Diff2.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Term3=" + Term3);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Term4=" + Term4);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Term5=" + Term5);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :StrOperator=" + StrOperator);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :Rule=" + Rule);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial :DiffWithRespTo=" + DiffWithRespTo);
        log.info("SimpleTerm:ApplyFuncRulesString:Initial : End Context:pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        String x = "";
        final String Diff1SimpleTerm = Diff1.SimpleTerm();
        final String Diff2SimpleTerm = Diff2.SimpleTerm();
        if (Rule.equalsIgnoreCase("NoExponentRule")) {
            if (Term1.equalsIgnoreCase(Term4)) {
                x = Term1 + "*" + Term3 + "^" + Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:NoExponentRule:Rule 1:x=" + x);
            } else {
                x = Term1 + Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:NoExponentRule:Rule 2:x=" + x);
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                if (Diff2SimpleTerm.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = "1.0";
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule A 1:x=" + x);
                } else if (Diff2SimpleTerm.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule A 2:x=" + x);
                } else if (Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = DiffWithRespTo + StrOperator + Diff2SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule A 3:x=" + x);
                } else {
                    x = (Diff1SimpleTerm + StrOperator + Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule A 4:x=" + x);
                }
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term4.equalsIgnoreCase("1.0"))) {
                x = Term4;
                log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule B 1:x=" + x);
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))/*&&(!Term4.equalsIgnoreCase("1.0"))*/) {
                if (Term2.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = Term4;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule C 1:x=" + x);
                } else if (Term2.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Term4 + "*" + Term1;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule C 2:x=" + x);
                } else {
                    x = Term4 + "*" + Term1 + "^" + Term2;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule C 3:x=" + x);
                }
            } else if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                if (Diff2SimpleTerm.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = "1.0";
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule D 1:x=" + x);
                } else if (Diff2SimpleTerm.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule D 2:x=" + x);
                } else if (!Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = (Diff1SimpleTerm + StrOperator + Diff2SimpleTerm);
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule D 3:x=" + x);
                } else if (Diff1SimpleTerm.equalsIgnoreCase("0.0")) {
                    x = DiffWithRespTo + StrOperator + Diff2SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule D 4:x=" + x);
                }
            } else {
                x = Term1 + "^" + Term5;
                log.info("SimpleTerm:ApplyFuncRulesString:CoeffExponentRule:Rule E 1:x=" + x);
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase(Diff2.SimpleTerm())) && (!(Term1.equalsIgnoreCase(DiffWithRespTo))) && (!Term1.contains("^")) && (!StrOperator.equalsIgnoreCase("^"))) {
                x = Term1 + StrOperator + Term2;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule A 1:x=" + x);
            } else if ((Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule A 2:x=" + x);
            } else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && ((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && ((Diff2SimpleTerm.equalsIgnoreCase("")) || (Diff2SimpleTerm.equalsIgnoreCase("0.0")))) {
                x = Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule A 3:x=" + x);
            } //            else if ((!Diff1SimpleTerm).equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm).equalsIgnoreCase("0.0"))) {
            //                x = (()->"0.0");
            //            } 
            else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0") && (Term4.equalsIgnoreCase("0.0")))) {
                x = Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule B 1:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm)) == 0.0) { // + 0.0
                    x = "";
                    log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule B 1:x=" + x);
                } else {
                    x = "" + (Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm));
                    log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule B 2:x=" + x);
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm)) == 0.0) { // - 0.0
                    x = "";
                    log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule C 1:x=" + x);
                } else {
                    x = ("" + (Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2.SimpleTerm())));
                    log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule C 2:x=" + x);
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = ("" + (Double.parseDouble(Diff1SimpleTerm) * Double.parseDouble(Diff2SimpleTerm)));
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule D 1:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))
                    && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                x = "0.0";
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule E 1:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo))
                    && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = ("" + (Double.parseDouble(Diff1SimpleTerm) / Double.parseDouble(Diff2SimpleTerm)));
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule F 1:x=" + x);
            } else if ((!Term4.equalsIgnoreCase(Diff2SimpleTerm)) && (Term1.equalsIgnoreCase(Diff1SimpleTerm)) && (IsAConstant(Term1, DiffWithRespTo)) && ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = Diff1SimpleTerm + StrOperator + Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule G 1:x=" + x);
            } else if ((!Term4.equalsIgnoreCase(Diff2SimpleTerm)) && (Term1.equalsIgnoreCase(Diff1SimpleTerm)) && (IsAConstant(Term1, DiffWithRespTo)) && (!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule H 1:x=" + x);
            } else if ((!Diff1SimpleTerm.equalsIgnoreCase("")) && (!Diff2SimpleTerm.equalsIgnoreCase(""))) {
                x = Diff1SimpleTerm + StrOperator + Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:ConstantRule:Rule I 1:x=" + x);
            }
        } else if ((Rule.equalsIgnoreCase("MappedSumRule"))
                || (Rule.equalsIgnoreCase("MappedSubtractionRule"))
                || (Rule.equalsIgnoreCase("MappedProductRule"))
                || (Rule.equalsIgnoreCase("MappedQuotientRule"))) {
            if ((StrOperator.equalsIgnoreCase("*")) && (Term1.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase("")) && (Diff1SimpleTerm.contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule A 0:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule A 1:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2SimpleTerm.equalsIgnoreCase("1.0"))) {
                x = Diff1SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule A 2:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("*")) && ((Diff1SimpleTerm.equalsIgnoreCase("0.0")) || (Diff2SimpleTerm.equalsIgnoreCase("0.0")))) {
                x = "";
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule A 3:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff1SimpleTerm.equalsIgnoreCase("1.0"))) {
                x = Diff2SimpleTerm;
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule A 4:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2SimpleTerm)) == 0.0) { //  + 0.0
                    x = "";
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule B 1:x=" + x);
                } else {
                    x = ("" + (Double.parseDouble(Diff1SimpleTerm) + Double.parseDouble(Diff2.SimpleTerm())));
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule B 2:x=" + x);
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm)) == 0.0) { //  - 0.0
                    x = "";
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule B 3:x=" + x);
                } else {
                    x = ("" + (Double.parseDouble(Diff1SimpleTerm) - Double.parseDouble(Diff2SimpleTerm)));
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule B 4:x=" + x);
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = "" + (Double.parseDouble(Diff1SimpleTerm) * Double.parseDouble(Diff2SimpleTerm));
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule C 1:x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("/")) && (IsAConstant(Diff1SimpleTerm, DiffWithRespTo)) && (IsAConstant(Diff2SimpleTerm, DiffWithRespTo))) {
                x = ("" + (Double.parseDouble(Diff1SimpleTerm) / Double.parseDouble(Diff2SimpleTerm)));
                log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule C 2:x=" + x);
            } else {
                if ((StrOperator.equalsIgnoreCase("+")) && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = Diff2.SimpleTerm();
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule D 1:x=" + x);
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff1SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = Diff2SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule D 2:x=" + x);
                } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule D 3:x=" + x);
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2SimpleTerm.equalsIgnoreCase("0.0"))) {
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule D 4:x=" + x);
                } else if (Diff2SimpleTerm.equalsIgnoreCase("")) {
                    // For Diffrentiation Last Term
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRulesString:MappedSumRule:Rule D 5:x=" + x);
                } else if ((Diff2SimpleTerm.equalsIgnoreCase(Term3)) && (Term1.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(""))) {
                    // For Integration Last Term
                    x = Diff1SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 6:x=" + x);
                } else {
                    x = Diff1SimpleTerm + StrOperator + Diff2SimpleTerm;
                    log.info("SimpleTerm:ApplyFuncRules:MappedSumRule:Rule D 7:x=" + x);
                }
            }
        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            x = Term1 + "*" + Term3 + "^" + Term2;
            log.info("SimpleTerm:ApplyFuncRulesString:ExponentRule:Rule A 1:x=" + x);
        } else if (Rule.equalsIgnoreCase("MappedExponentRule")) {
            x = Term4 + "*" + Term3 + "^" + Diff2SimpleTerm;
            log.info("SimpleTerm:ApplyFuncRulesString:MappedExponentRule:Rule A 1:x=" + x);
        }
        log.info("SimpleTerm:ApplyFuncRulesString:Final:String:x=" + x);
        log.info("SimpleTerm:ApplyFuncRulesString:Final: End Context:pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        return x;
    }

    /**
     * ************************************************************
     */
    /**
     * ************* Lambda Low Level Mapping Functions *****
     */
    /**
     * ************************************************************
     */
    public static String FormTermLambda(String AlgoName, String IndConstStr, String BracesStr, String VarStr, String ConstStr, String DiffWithRespTo, boolean BracesFlag, boolean VariableFlag, boolean ConstantFlag, boolean IndConstFlag) {
        log.info("SimpleTerm:FormTermLambda:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("SimpleTerm:FormTermLambda:Initial :IndConstStr=" + IndConstStr);
        log.info("SimpleTerm:FormTermLambda:Initial :BracesStr=" + BracesStr);
        log.info("SimpleTerm:FormTermLambda:Initial :VarStr=" + VarStr);
        log.info("SimpleTerm:FormTermLambda:Initial :ConstStr=" + ConstStr);
        log.info("SimpleTerm:FormTermLambda:Initial :DiffWithRespTo=" + DiffWithRespTo);
        log.info("SimpleTerm:FormTermLambda:Initial :BracesFlag=" + BracesFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :VariableFlag=" + VariableFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :ConstantFlag=" + ConstantFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :IndConstFlag=" + IndConstFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :TrailConstantFlag=" + TrailConstantFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :TrailConstantOperator=" + (char) TrailConstantOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LATermOperator=" + (char) LATermOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LATermOperatorBraces=" + (char) LATermOperatorBraces);
        log.info("SimpleTerm:FormTermLambda:Initial :LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LAIndConstOperator=" + (char) LAIndConstOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LAVarTermOperator=" + (char) LAVarTermOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LAPreTermOperator=" + (char) LAPreTermOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LAMAPOperator=" + (char) LAMAPOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
        log.info("SimpleTerm:FormTermLambda:Initial :LAConstSimpleMapFlag=" + LAConstSimpleMapFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :LAConstComplexMapFlag=" + LAConstComplexMapFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :LAVarSimpleMapFlag=" + LAVarSimpleMapFlag);
        log.info("SimpleTerm:FormTermLambda:Initial :LAVarComplexMapFlag=" + LAVarComplexMapFlag);
        String CompleteStr = "";
        OperatorLambda Operator = (() -> 0);
        if ((IndConstFlag == true) && (!IndConstStr.equalsIgnoreCase(""))) {
            Operator = parseOper("LAIndConstOperator", Operator.Operator(), CompleteStr, IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag, ' ');
            if ((Operator.Operator() != 0) && (((VariableFlag == true) && (!VarStr.equalsIgnoreCase(""))) || (ConstantFlag == true) || (BracesFlag == true))) {
                if ((VariableFlag == true) && (ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '/')) {
                    CompleteStr = IndConstStr + (char) Operator.Operator();
                    log.info("SimpleTerm:FormTermLambda:IndConst Mapping:Rule:1.1.1:CompleteStr=" + CompleteStr);
                } else if ((VariableFlag == true) && (ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '*')) {
                    CompleteStr = IndConstStr + (char) Operator.Operator();
                    log.info("SimpleTerm:FormTermLambda:IndConst Mapping:Rule:1.1.2:CompleteStr=" + CompleteStr);
                } else if (((VariableFlag == true)) && ((Operator.Operator() == LAVarTermOperator))) {
                    CompleteStr = IndConstStr + (char) Operator.Operator();
                    log.info("SimpleTerm:FormTermLambda:IndConst Mapping:Rule:1.1.3:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = IndConstStr + "*";
                    log.info("SimpleTerm:FormTermLambda:IndConst Mapping:Rule:1.1.4:CompleteStr=" + CompleteStr);
                }
            } else {
                CompleteStr = IndConstStr;
            }
            log.info("SimpleTerm:FormTermLambda:IndConst Mapping:Rule:1:CompleteStr=" + CompleteStr);
        }
        if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase(""))) {
            Operator = parseOper("PreConstOperator", Operator.Operator(), CompleteStr, IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag, ' ');
            if ((Operator.Operator() != 0) && (Operator.Operator() == '/')) {
                if (!CompleteStr.equalsIgnoreCase("")) {
                    if ((ConstStr.equalsIgnoreCase("1.0")) && (LAPreIndConstOperator == '*')) {
                        CompleteStr = IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.1:CompleteStr=" + CompleteStr);
                    } else if ((ConstStr.equalsIgnoreCase("1.0")) && (LAPreIndConstOperator == '/')) {
                        CompleteStr = "1.0" + "/" + IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.2:CompleteStr=" + CompleteStr);
                    } else if ((!ConstStr.equalsIgnoreCase("1.0")) && (LAPreIndConstOperator == '*') && (LAPreTermOperator == '*')) {
                        CompleteStr = ConstStr + "*" + IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.3:CompleteStr=" + CompleteStr);
                    } else if ((!ConstStr.equalsIgnoreCase("1.0")) && (LAPreIndConstOperator == '*') && (LAPreTermOperator == '/')) {
                        CompleteStr = IndConstStr + "/" + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.4:CompleteStr=" + CompleteStr);
                    } else if ((!ConstStr.equalsIgnoreCase("1.0")) && (LAPreIndConstOperator == '/')) {
                        CompleteStr = ConstStr + "/" + IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.5:CompleteStr=" + CompleteStr);
                    } else if (!ConstStr.equalsIgnoreCase("1.0")) {
                        CompleteStr = IndConstStr + "/" + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1.1.6:CompleteStr=" + CompleteStr);
                    }
                    log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:1:CompleteStr=" + CompleteStr);
                } else {
                    if ((IndConstFlag == true) && (ConstStr.equalsIgnoreCase("1.0"))) {
                        CompleteStr = IndConstStr + "*";
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.1:CompleteStr=" + CompleteStr);
                    } else if ((IndConstFlag == false) && (ConstStr.equalsIgnoreCase("1.0"))) {
                        CompleteStr = ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.2:CompleteStr=" + CompleteStr);
                    } else if ((TrailConstantFlag == true) && (ConstStr.equalsIgnoreCase("1.0"))) {
                        CompleteStr = ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.3:CompleteStr=" + CompleteStr);
                    } else if ((TrailConstantFlag == true) && (LATermOperator != -1) && (LAPreTermOperator != -1) && (!ConstStr.equalsIgnoreCase("1.0")) && (TrailConstantOperator == Operator.Operator()) && (TrailConstantOperator == '*')) {
                        CompleteStr = ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.4:CompleteStr=" + CompleteStr);
                    } else if ((TrailConstantFlag == true) && (LATermOperator == -1) && (LAPreTermOperator == -1) && (!ConstStr.equalsIgnoreCase("1.0")) && (TrailConstantOperator == Operator.Operator()) && (TrailConstantOperator == '/')) {
                        // Tweak in Code for Unable to map 
                        CompleteStr = CompleteStr + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.5:CompleteStr=" + CompleteStr);
                    } else if ((TrailConstantFlag == true) && (LATermOperator == -1) && (LAPreTermOperator == '*') && (!ConstStr.equalsIgnoreCase("1.0")) && (TrailConstantOperator == Operator.Operator()) && (TrailConstantOperator == '/')) {
                        // Tweak in Code for Unable to map 
                        CompleteStr = CompleteStr + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.5:CompleteStr=" + CompleteStr);
                    } else {
                        CompleteStr = CompleteStr + "1.0" + (char) Operator.Operator() + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2.6:CompleteStr=" + CompleteStr);
                    }
                    log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:2:CompleteStr=" + CompleteStr);
                }
            } else if ((Operator.Operator() != 0)) {
                if (!CompleteStr.equalsIgnoreCase("")) {
                    if ((!ConstStr.equalsIgnoreCase("1.0")) && (CompleteStr.endsWith("" + (char) Operator.Operator()))) {
                        CompleteStr = CompleteStr + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.1.1:CompleteStr=" + CompleteStr);
                    } else if ((ConstStr.equalsIgnoreCase("1.0")) && (CompleteStr.endsWith("" + (char) Operator.Operator()))) {
                        CompleteStr = IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.1.2:CompleteStr=" + CompleteStr);
                    } else if ((ConstStr.equalsIgnoreCase("1.0") || (ConstStr.equalsIgnoreCase("0.0"))) && (ConstantFlag == true)) {
                        CompleteStr = IndConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.1.3:CompleteStr=" + CompleteStr);
                    } else {
                        CompleteStr = IndConstStr + (char) Operator.Operator() + ConstStr;
                        log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.1.3:CompleteStr=" + CompleteStr);
                    }
                    log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.1:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = ConstStr;
                    log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3.2:CompleteStr=" + CompleteStr);
                }
                log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:3:CompleteStr=" + CompleteStr);
            } else {
                CompleteStr = CompleteStr + ConstStr;
                log.info("SimpleTerm:FormTermLambda:Constant Mapping:Rule:4:CompleteStr=" + CompleteStr);
            }
            log.info("SimpleTerm:FormTermLambda:Constant Mapping:CompleteStr=" + CompleteStr);
        }
        if ((VariableFlag == true) && (!VarStr.equalsIgnoreCase(""))) {
            Operator = parseOper("PreVarOperator", Operator.Operator(), CompleteStr, IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag, ' ');
            if ((Operator.Operator() != 0)) {
                boolean Flag = false;
                if (CompleteStr.equalsIgnoreCase("1.0") && (Operator.Operator() == '*')) {
                    if (VarStr.equalsIgnoreCase("1.0")) {
                        CompleteStr = DiffWithRespTo;
                    } else {
                        CompleteStr = VarStr;
                    }
                    log.info("SimpleTerm:FormTermLambda:Var Mapping:CompleteStr=" + CompleteStr);
                } else {
                    if ((CompleteStr.endsWith("*")) || (CompleteStr.endsWith("/"))) {
                        if (VarStr.equalsIgnoreCase("1.0")) {
                            CompleteStr = CompleteStr + DiffWithRespTo;
                        } else {
                            CompleteStr = CompleteStr + VarStr;
                        }
                        log.info("SimpleTerm:FormTermLambda:Var Mapping:CompleteStr=" + CompleteStr);
                    } else {
                        if (VarStr.equalsIgnoreCase("1.0")) {
                            CompleteStr = CompleteStr + (char) Operator.Operator() + DiffWithRespTo;
                        } else {
                            CompleteStr = CompleteStr + (char) Operator.Operator() + VarStr;
                        }
                        log.info("SimpleTerm:FormTermLambda:Var Mapping:CompleteStr=" + CompleteStr);
                    }
                    log.info("SimpleTerm:FormTermLambda:Var Mapping:CompleteStr=" + CompleteStr);
                }
            } else {
                if (VarStr.equalsIgnoreCase("1.0")) {
                    CompleteStr = CompleteStr + DiffWithRespTo;
                } else if (VarStr.equalsIgnoreCase(DiffWithRespTo + "^0.0")) {
                    CompleteStr = CompleteStr + "1.0";
                } else {
                    CompleteStr = CompleteStr + VarStr;
                }
            }
            log.info("SimpleTerm:FormTermLambda:Var Mapping:CompleteStr=" + CompleteStr);
        }
        if ((BracesFlag == true) && (!BracesStr.equalsIgnoreCase(""))) {
            Operator = parseOper("BracesPre", Operator.Operator(), CompleteStr, IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag, LATermOperatorBraces);
            if ((Operator.Operator() != 0)) {
                if ((BracesStr.contains("+")) || (BracesStr.contains("-"))) {
                    CompleteStr = CompleteStr + (char) Operator.Operator() + "(" + BracesStr + ")";
                } else {
                    CompleteStr = CompleteStr + (char) Operator.Operator() + BracesStr;
                }
            } else if ((BracesFlag == true) && (!BracesStr.equalsIgnoreCase(""))) {
                CompleteStr = CompleteStr + BracesStr;
            }
            log.info("SimpleTerm:FormTermLambda:Braces Mapping:CompleteStr=" + CompleteStr);
        }
        log.info("SimpleTerm:FormTermLambda:Pre Final:CompleteStr=" + CompleteStr);
        if (CompleteStr.startsWith("/")) {
            CompleteStr = "1.0" + CompleteStr;
        } else if (CompleteStr.startsWith("\\*")) {
            CompleteStr = CompleteStr.replaceFirst("*", "");
        }
        log.info("SimpleTerm:FormTermLambda:Final:CompleteStr=" + CompleteStr);
        return CompleteStr;
    }

    @FunctionalInterface
    public interface OperatorLambda {

        int Operator();
    }

    public static OperatorLambda parseOper(String str, int LastOper, String CompleteStr, String IndConstStr, String BracesStr, String VarStr, String ConstStr, String DiffWithRespTo, boolean BracesFlag, boolean VariableFlag, boolean ConstantFlag, boolean IndConstFlag, int NullOperator) {
        return new Object() {

            OperatorLambda parseOper() {
                OperatorLambda x = (() -> '*');
                if ((str.equalsIgnoreCase("LAIndConstOperator"))
                        && (IndConstFlag == true) && (!IndConstStr.equalsIgnoreCase(""))) {
                    x = parseIndConstant();
                    log.info("OperatorLambda:parseOper:IndConstantRules:x.Operator()=" + (char) x.Operator());
                } else if ((str.equalsIgnoreCase("PreConstOperator"))
                        && (ConstantFlag == true) && (!ConstStr.equalsIgnoreCase(""))) {
                    x = parsePreConstant();
                    log.info("OperatorLambda:parseOper:PreConstantRules:x.Operator()=" + (char) x.Operator());
                } else if ((str.equalsIgnoreCase("PreVarOperator"))
                        && (VariableFlag == true) && (!VarStr.equalsIgnoreCase(""))) {
                    x = parsePreVar();
                    log.info("OperatorLambda:parseOper:ConstantPostRules:x.Operator()=" + (char) x.Operator());
                } else if ((str.equalsIgnoreCase("BracesPre"))
                        && (BracesFlag == true) && (!BracesStr.equalsIgnoreCase(""))) {
                    x = parseBracesOper();
                    log.info("OperatorLambda:parseOper:ConstantPostRules:x.Operator()=" + (char) x.Operator());
                }

                return x;
            }

            OperatorLambda parseIndConstant() {
                OperatorLambda x = (() -> '*');
                if (LAPreIndConstOperator == '/') {
                    if (IndConstStr.startsWith("1.0")) {
                        x = (() -> LAIndConstOperator);
                    } else {
                        x = (() -> LAIndConstOperator);
                    }
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule1:x.Operator()=" + (char) x.Operator());
                } else if (((LAIndConstOperator == '*') && (LATermOperator == -1)) || ((LAPreTermOperator == -1) && (IndConstFlag == true))) {
                    x = (() -> LAIndConstOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule2:x.Operator()=" + (char) x.Operator());
                } else if ((LAIndConstOperator == '/') && (LATermOperator == -1) && (LAPreTermOperator == '*')) {
                    x = (() -> LAPreTermOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule3:x.Operator()=" + (char) x.Operator());
                } else if ((LAIndConstOperator == '/') && (LATermOperator == -1) && (LAPreIndConstOperator == '/')) {
                    x = (() -> '*');
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule4.1:Map:x.Operator()=" + (char) x.Operator());
                } else if ((LAIndConstOperator == '/') && (LAPreIndConstOperator == '/')) {
                    x = (() -> LAIndConstOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule4.2:x.Operator()=" + (char) x.Operator());
                } else if ((LAIndConstOperator == '/') && (LAPreIndConstOperator == '*')) {
                    x = (() -> LAIndConstOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule4.3:x.Operator()=" + (char) x.Operator());
                } else if (LAPreIndConstOperator == -1) {
                    x = (() -> LAIndConstOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule4.5:x.Operator()=" + (char) x.Operator());
                } else {
                    x = (() -> LATermOperator);
                    log.info("OperatorLambda:parseIndConstant:IndConstantRules:Rule4.6:x.Operator()=" + (char) x.Operator());
                }
                log.info("OperatorLambda:parseIndConstant:Final:x.Operator()=" + (char) x.Operator());
                return x;
            }

            OperatorLambda parsePreConstant() {
                OperatorLambda x = (() -> '*');
                boolean DivMapFlag = false;
                if ((LATermOperator != LAVarTermOperator) && (LATermOperator != -1) && (LAVarTermOperator != -1)) {
                    if (VarStr.equalsIgnoreCase("1.0")) {
                        x = (() -> LAVarTermOperator);
                    } else {
                        x = (() -> LAVarTermOperator);
                    }
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.3::x.Operator()=" + (char) x.Operator());
                } else if ((LATermOperator == LAVarTermOperator) && (LATermOperator != -1) && (LAVarTermOperator != -1) && (LAIndConstOperator == '/')) {
                    x = (() -> LAIndConstOperator);
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.4.1:x.Operator()=" + (char) x.Operator());
                } else if ((LATermOperator == LAVarTermOperator) && (LATermOperator != -1) && (LAVarTermOperator != -1)) {
                    if (VarStr.equalsIgnoreCase("1.0")) {
                        x = (() -> LATermOperator);
                    } else {
                        x = (() -> LATermOperator);
                    }
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.4.2:x.Operator()=" + (char) x.Operator());
                } else if ((LATermOperator == -1) && (TrailConstantFlag == true) && (TrailConstantOperator == '*')) {
                    if (VarStr.equalsIgnoreCase("1.0")) {
                        x = (() -> TrailConstantOperator);
                    } else {
                        x = (() -> TrailConstantOperator);
                    }
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.5:x.Operator()=" + (char) x.Operator());
                } else if ((LATermOperator == -1) && (TrailConstantFlag == true) && (TrailConstantOperator == '/')) {
                    // COMMENT:BE CAREFUL OF ROUNDING UP OF DOUBLE
                    if (CompleteStr.endsWith("/")) {
                        x = (() -> '/');
                        log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.1:x.Operator()=" + (char) x.Operator());
                    } else if (CompleteStr.endsWith("*")) {
                        x = (() -> '*');
                        DivMapFlag = true;
                        log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.1:x.Operator()=" + (char) x.Operator());
                    } else if (!CompleteStr.equalsIgnoreCase("")) {
                        x = (() -> '/');
                        //DivMapFlag = true;
                        log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.2:x.Operator()=" + (char) x.Operator());
                    } else if ((CompleteStr.equalsIgnoreCase("")) && (VarStr.equalsIgnoreCase("1.0"))) {
                        x = (() -> '/');
                        log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.3:x.Operator()=" + (char) x.Operator());
                    } else {
                        x = (() -> '/');
                        DivMapFlag = true;
                        log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.4:x.Operator()=" + (char) x.Operator());
                    }
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (((LAPreTermOperator == '/') && (LAConstSimpleMapFlag == true) && (LATermOperator == -1)) || ((LAVarSimpleMapFlag == false) && (LAMAPOperator == '/')))) {
                    x = (() -> '/');
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.5:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (LAPreTermOperator == '/') && (LAVarSimpleMapFlag == false) && (LATermOperator == -1)) {
                    x = (() -> '/');
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.6:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (LAPreTermOperator == '/') && (LAVarSimpleMapFlag == false)) {
                    x = (() -> NullOperator);
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.7:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (IndConstStr.equalsIgnoreCase(""))) {
                    x = (() -> NullOperator);
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.8:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (!IndConstStr.equalsIgnoreCase("")) && (LAPreIndConstOperator != -1) && (LAPreIndConstOperator == '*') && (LAIndConstOperator != '*')) {
                    x = (() -> LAIndConstOperator);
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.8:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (!ConstStr.equalsIgnoreCase("")) && (!IndConstStr.equalsIgnoreCase("")) && (LAPreIndConstOperator != -1) && (IndConstFlag == true)) {
                    x = (() -> LAPreIndConstOperator);
                    DivMapFlag = true;
                    log.info("OperatorLambda:parsePreConstant:VarConstantRules:Rule1.6.8:x.Operator()=" + (char) x.Operator());
                } else {
                    DivMapFlag = true;
                }
                if ((LastOper > 0) && (LastOper != x.Operator()) && (DivMapFlag == false) && (x.Operator() != NullOperator) && (LastOper != NullOperator)) {
                    int temp = x.Operator();
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OperatorLambda:parsePreConstant:Unexpected LastOper: " + (char) LastOper + " NullOperator=" + (char) NullOperator + " int=" + (int) LastOper + " Pre and Post Operator Not Matching:" + (char) temp);
                }
                log.info("OperatorLambda:parsePreConstant:Final:x.Operator()=" + (char) x.Operator());
                return x;
            }

            OperatorLambda parseConstantPost() {
                OperatorLambda x = (() -> '*');

                return x;
            }

            OperatorLambda parsePreVar() {
                OperatorLambda x = (() -> '*');
                boolean DivMapFlag = false;
                if ((LATermOperator == -1) && (LAVarTermOperator != '/') && (VariableFlag == true) && (ConstantFlag == true)) {
                    //CompleteStr = CompleteStr + ConstStr + "*" + VarStr;
                    if ((LAMAPOperator == -1) && (LADyYByDxMAPOperator == -1)) {
                        x = (() -> '*');
                        log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.7.1:x.Operator()=" + (char) x.Operator());
                    } else {
                        x = (() -> LAVarTermOperator);
                        log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.7.2:x.Operator()=" + (char) x.Operator());
                    }
                    log.info("OperatorLambda:parsePreVar:VarConstantRules:Rule1.7:x.Operator()=" + (char) x.Operator());
                } else if ((LATermOperator == -1) && (LAVarTermOperator == '/') && (VariableFlag == true) && (ConstantFlag == true)) {
                    if ((LAMAPOperator == -1) && (LADyYByDxMAPOperator == -1)) {
                        if ((!ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '/')) {
                            x = (() -> '/');
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8.2:x.Operator()=" + (char) x.Operator());
                        } else if ((!CompleteStr.equalsIgnoreCase("")) && (ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '/')) {
                            x = (() -> LAVarTermOperator);
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8.3:x.Operator()=" + (char) x.Operator());
                        } else if ((CompleteStr.equalsIgnoreCase("")) && (ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '/')) {
                            x = (() -> '/');
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8.4:x.Operator()=" + (char) x.Operator());
                        } else if ((CompleteStr.equalsIgnoreCase("")) && (ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator == '/')) {
                            x = (() -> '*');
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8.5:x.Operator()=" + (char) x.Operator());
                        } else {
                            x = (() -> NullOperator);
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8.6:x.Operator()=" + (char) x.Operator());
                        }

                        log.info("OperatorLambda:parsePreVar:VarConstantRules:Normal:Rule1.8:x.Operator()=" + (char) x.Operator());
                    } else {
                        if ((VarStr.startsWith("1.0")) && (ConstStr.equalsIgnoreCase("1.0"))) {
                            x = (() -> NullOperator);
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.9:x.Operator()=" + (char) x.Operator());
                        } else if (LAPreTermOperator != -1) {
                            String Temp = VarStr.replaceFirst("1.0", "");
                            if (Temp.startsWith("/")) {
                                x = (() -> LAPreTermOperator);
                                log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.10:x.Operator()=" + (char) x.Operator());
                            } else if (LAVarComplexMapFlag == true) {
                                x = (() -> '*');
                                log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.11:x.Operator()=" + (char) x.Operator());
                            } else if (LAVarSimpleMapFlag == true) {
                                x = (() -> LAVarTermOperator);
                                log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.11.1:x.Operator()=" + (char) x.Operator());
                            } else {
                                x = (() -> LAPreTermOperator);
                                log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.12:x.Operator()=" + (char) x.Operator());
                            }
                        } else {
                            if (VarStr.startsWith("1.0")) {
                                String Temp = VarStr.replaceFirst("1.0", "");
                                x = (() -> NullOperator);
                            } else {
                                x = (() -> '/');
                            }
                            log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.13:x.Operator()=" + (char) x.Operator());
                        }
                        log.info("OperatorLambda:parsePreVar:VarConstantRules:MAP-:Rule1.9:x.Operator()=" + (char) x.Operator());
                    }
                } else if ((LATermOperator == -1) && (LAVarTermOperator == '*') && (VariableFlag == true) && (ConstantFlag == true)) {
                    if ((ConstStr.equalsIgnoreCase("1.0"))) {
                        x = (() -> NullOperator);
                        log.info("OperatorLambda:parsePreVar:Normal:Rule1:x.Operator()=" + x.Operator());
                    }
                } else if ((TrailConstantFlag != true) && (VariableFlag == true) && (!VarStr.equalsIgnoreCase("")) && (!VarStr.equalsIgnoreCase("1.0")) && (!ConstStr.equalsIgnoreCase("0.0"))) {
                    log.info("OperatorLambda:parsePreVar:Normal Variable:Final:End Context:ConstStr=" + ConstStr + " TrailConstantOperator=" + (char) TrailConstantOperator + " VarStr=" + VarStr);
                    x = (() -> '*');
                    log.info("OperatorLambda:parsePreVar:End:Rule11.3:Final:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) && (ConstStr.equalsIgnoreCase("0.0"))) {
                    x = (() -> NullOperator);
                    log.info("OperatorLambda:parsePreVar:End:Rule11.4:Final:x.Operator()=" + (char) x.Operator());
                } else if ((ConstantFlag == true) || ((VariableFlag == true) && ((LAPreTermOperator == '/') || (LAMAPOperator == '/')))) {
                    if ((VariableFlag == true) && (LAPreTermOperator == '/') && (LAVarSimpleMapFlag == false)) {
                        x = (() -> '*');
                    } else if ((VariableFlag == true) && (LAMAPOperator == '/') && (LAVarSimpleMapFlag == true)) {
                        x = (() -> LAMAPOperator);
                    } else if ((VariableFlag == true) && (LAMAPOperator == '*') && (LAVarSimpleMapFlag == true)) {
                        x = (() -> LAMAPOperator);
                    } else {
                        x = (() -> LAVarTermOperator);
                    }
                    log.info("OperatorLambda:parsePreVar:End:Rule11.5:Final:x.Operator()=" + (char) x.Operator());
                } else if (VariableFlag == true) {
                    x = (() -> NullOperator);
                    log.info("OperatorLambda:parsePreVar:End:Rule11.5:Final:x.Operator()=" + (char) x.Operator());
                }
                log.info("OperatorLambda:parsePreVar:Final:x.Operator()=" + (char) x.Operator());
                return x;
            }

            OperatorLambda parseBracesOper() {
                OperatorLambda x = (() -> '*');
                boolean DivMapFlag = false;
                if ((IsAConstant(BracesStr, DiffWithRespTo)) || (StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 1:x.Operator()=" + (char) x.Operator());//LookAheadVar would have taken care
                } else if (((!CompleteStr.equalsIgnoreCase("1.0")) && ((LATermOperatorBraces == '*'))) && (!BracesStr.contains("+")) && (!BracesStr.contains("-"))) {
                    x = (() -> LATermOperatorBraces);
                    DivMapFlag = true;
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 2:x.Operator()=" + (char) x.Operator());
                } else if (((!CompleteStr.equalsIgnoreCase("1.0")) || ((LATermOperatorBraces == '/'))) && (!BracesStr.contains("+")) && (!BracesStr.contains("-"))) {
                    x = (() -> NullOperator);
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 2:x.Operator()=" + (char) x.Operator());
                } else if (((!CompleteStr.equalsIgnoreCase("1.0")) || ((LATermOperatorBraces == '/'))) && (!BracesStr.contains("+")) && (!BracesStr.contains("-"))) {
                    x = (() -> LATermOperatorBraces);
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 3:x.Operator()=" + (char) x.Operator());
                } else if ((!CompleteStr.equalsIgnoreCase("1.0")) || ((LATermOperatorBraces == '/'))) {
                    x = (() -> LATermOperatorBraces);
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 4:x.Operator()=" + (char) x.Operator());
                } else if ((CompleteStr.equalsIgnoreCase("1.0")) && (LATermOperatorBraces == '*')) {
                    x = (() -> '*');
                    log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Rule 5:x.Operator()=" + (char) x.Operator());
                }
                if ((LastOper != x.Operator()) && (DivMapFlag == false) && (x.Operator() != NullOperator) && (LastOper != NullOperator)) {
                    int temp = x.Operator();
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "OperatorLambda:parsePreConstant:Unexpected LastOper: " + (char) LastOper + " int=" + (int) LastOper + " Pre and Post Operator Not Matching:" + (char) temp);
                }
                log.info("OperatorLambda:parseBracesOper:BraceStr Mapping:Final:x.Operator()=" + (char) x.Operator());
                return x;
            }
        }.parseOper();
    }

    @FunctionalInterface
    public interface ProLambdaExtr {

        String StrExpr();
    }

    public static ProLambdaExtr parseExpr(String AlgoName, String Diff1, String Diff2, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        return new Object() {

            ProLambdaExtr parseExtreme() {
                ProLambdaExtr x = (() -> "");
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    x = parseProductRule();
                    log.info("ProExtr:String:parseIntegrStr:NoExponentRuleTerm:x.StrExpr()=" + x.StrExpr());
                } else if (Rule.equalsIgnoreCase("QuotientRule")) {
                    x = parseQuotientRule();
                    log.info("ProExtr:String:parseIntegrStr:NoExponentRuleDiff:x.StrExpr()=" + x.StrExpr());
                }

                log.info("ProExtr:parseExtreme:Final:x.StrExpr()=" + x.StrExpr());
                return x;
            }

            ProLambdaExtr parseProductRule() {
                ProLambdaExtr x = (() -> "");
                final String Diff1Expr = Diff1;
                final String Diff2Expr = Diff2;
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    if (TermOperator == '*') {
                        if ((IsAConstant(Diff1Expr, DiffWithRespTo)
                                && (StrIsAConstant.equalsIgnoreCase("Number")))
                                && ((Diff2Expr.equalsIgnoreCase("Infinity"))
                                || (Diff2Expr.equalsIgnoreCase("NaN")))) {
                            if ((Diff1Expr.equalsIgnoreCase("0.0"))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //0.0*Infinity
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule1:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("0.0"))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //0.0*NaN 
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule2:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("1.0"))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //1.0*Infinity 
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule3:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("1.0"))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //1.0*NaN
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule4:x.StrExpr()=" + x.StrExpr());
                            } else if ((!Diff1Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff1Expr)))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //25.0*Infinity
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule5:x.StrExpr()=" + x.StrExpr());
                            } else if ((!Diff1Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff1Expr)))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //25.0*NaN
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule6:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((!IsAConstant(Diff1Expr, DiffWithRespTo))
                                && ((Diff2Expr.equalsIgnoreCase("Infinity"))
                                || (Diff2Expr.equalsIgnoreCase("NaN")))) {
                            //String * Extr
                            if (Diff2Expr.equalsIgnoreCase("NaN")) {
                                //String * NaN
                                x = (() -> Diff1Expr + "*" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule7:x.StrExpr()=" + x.StrExpr());
                            } else if (Diff2Expr.equalsIgnoreCase("Infinity")) {
                                //String * Infinity
                                x = (() -> Diff1Expr + "*" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule8:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((IsAConstant(Diff2Expr, DiffWithRespTo)
                                && (StrIsAConstant.equalsIgnoreCase("Number")))
                                && ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                || (Diff1Expr.equalsIgnoreCase("NaN")))) {
                            if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && (Diff2Expr.equalsIgnoreCase("0.0"))) {
                                //Infinity*0.0
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule9:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && (Diff2Expr.equalsIgnoreCase("0.0"))) {
                                //NaN*0.0 
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule10:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && (Diff2Expr.equalsIgnoreCase("1.0"))) {
                                //Infinity*1.0 
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule11:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && (Diff2Expr.equalsIgnoreCase("1.0"))) {
                                //NaN*1.0
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule12:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && ((!Diff2Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff2Expr))))) {
                                //Infinity*25.0
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseProductRule:Rule13:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && ((!Diff2Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff2Expr))))) {
                                //NaN*25.0
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseProductRule:Rule14:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((!IsAConstant(Diff2Expr, DiffWithRespTo))
                                && ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                || (Diff1Expr.equalsIgnoreCase("NaN")))) {
                            //Extr * String
                            if (Diff1Expr.equalsIgnoreCase("NaN")) {
                                //NaN *String
                                x = (() -> "" + Double.NaN + "*" + Diff2Expr);
                                log.info("ProExtr:parseProductRule:Rule15:x.StrExpr()=" + x.StrExpr());
                            } else if (Diff1Expr.equalsIgnoreCase("Infinity")) {
                                //Infinity * String
                                x = (() -> "" + Double.POSITIVE_INFINITY + "*" + Diff2Expr);
                                log.info("ProExtr:parseProductRule:Rule16:x.StrExpr()=" + x.StrExpr());
                            }
                        } // Positive Test Casee
                        // Non Extremity
                        else {
                            if (!Diff1Expr.endsWith("*0.0")) {
                                x = (() -> Diff1Expr + "*" + Diff2Expr);
                            } else {
                                x = (() -> Diff1Expr);
                            }
                            log.info("ProExtr:parseProductRule:Rule17:x.StrExpr()=" + x.StrExpr());
                        }
                    }
                    log.info("ProExtr:parseProductRule:TermOperator=" + (char) TermOperator);
                    log.info("ProExtr:parseProductRule:Diff1Expr=" + Diff1Expr);
                    log.info("ProExtr:parseProductRule:Diff2Expr=" + Diff2Expr);
                    log.info("ProExtr:parseProductRule:x.StrExpr()=" + x.StrExpr());
                }
                log.info("ProExtr:parseExtreme:Final:x.StrExpr()=" + x.StrExpr());
                return x;
            }

            ProLambdaExtr parseQuotientRule() {
                ProLambdaExtr x = (() -> "");
                final String Diff1Expr = Diff1;
                final String Diff2Expr = Diff2;
                if (Rule.equalsIgnoreCase("QuotientRule")) {
                    if (TermOperator == '/') {
                        if ((IsAConstant(Diff1Expr, DiffWithRespTo)
                                && (StrIsAConstant.equalsIgnoreCase("Number")))
                                && ((Diff2Expr.equalsIgnoreCase("Infinity"))
                                || (Diff2Expr.equalsIgnoreCase("NaN")))) {
                            if ((Diff1Expr.equalsIgnoreCase("0.0"))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //0.0/Infinity
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule1:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("0.0"))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //0.0/NaN 
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule2:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("1.0"))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //1.0/Infinity 
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseQuotientRule:Rule3:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("1.0"))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //1.0/NaN
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule4:x.StrExpr()=" + x.StrExpr());
                            } else if ((!Diff1Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff1Expr)))
                                    && (Diff2Expr.equalsIgnoreCase("Infinity"))) {
                                //25.0/Infinity
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseQuotientRule:Rule5:x.StrExpr()=" + x.StrExpr());
                            } else if ((!Diff1Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff1Expr)))
                                    && (Diff2Expr.equalsIgnoreCase("NaN"))) {
                                //25.0/NaN
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule6:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((!IsAConstant(Diff1Expr, DiffWithRespTo))
                                && ((Diff2Expr.equalsIgnoreCase("Infinity"))
                                || (Diff2Expr.equalsIgnoreCase("NaN")))) {
                            //String / Extr
                            if (Diff2Expr.equalsIgnoreCase("NaN")) {
                                //String / NaN
                                x = (() -> Diff1Expr + "/" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule7:x.StrExpr()=" + x.StrExpr());
                            } else if (Diff2Expr.equalsIgnoreCase("Infinity")) {
                                //String / Infinity
                                x = (() -> Diff1Expr + "/" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseQuotientRule:Rule8:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((IsAConstant(Diff2Expr, DiffWithRespTo)
                                && (StrIsAConstant.equalsIgnoreCase("Number")))
                                && ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                || (Diff1Expr.equalsIgnoreCase("NaN")))) {
                            if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && (Diff2Expr.equalsIgnoreCase("0.0"))) {
                                //Infinity/0.0
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule9:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && (Diff2Expr.equalsIgnoreCase("0.0"))) {
                                //NaN/0.0 
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule10:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && (Diff2Expr.equalsIgnoreCase("1.0"))) {
                                //Infinity/1.0 
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseQuotientRule:Rule11:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && (Diff2Expr.equalsIgnoreCase("1.0"))) {
                                //NaN/1.0
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule12:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                    && ((!Diff2Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff2Expr))))) {
                                //Infinity/25.0
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                                log.info("ProExtr:parseQuotientRule:Rule13:x.StrExpr()=" + x.StrExpr());
                            } else if ((Diff1Expr.equalsIgnoreCase("NaN"))
                                    && ((!Diff2Expr.equalsIgnoreCase("1.0")) && (Double.isFinite(Double.parseDouble(Diff2Expr))))) {
                                //NaN/25.0
                                x = (() -> "" + Double.NaN);
                                log.info("ProExtr:parseQuotientRule:Rule14:x.StrExpr()=" + x.StrExpr());
                            }
                        } else if ((!IsAConstant(Diff2Expr, DiffWithRespTo))
                                && ((Diff1Expr.equalsIgnoreCase("Infinity"))
                                || (Diff1Expr.equalsIgnoreCase("NaN")))) {
                            //Extr / String
                            if (Diff1Expr.equalsIgnoreCase("NaN")) {
                                //NaN / String
                                x = (() -> "" + Double.NaN + "/" + Diff2Expr);
                                log.info("ProExtr:parseQuotientRule:Rule15:x.StrExpr()=" + x.StrExpr());
                            } else if (Diff1Expr.equalsIgnoreCase("Infinity")) {
                                //Infinity / String
                                x = (() -> "" + Double.POSITIVE_INFINITY + "/" + Diff2Expr);
                                log.info("ProExtr:parseQuotientRule:Rule16:x.StrExpr()=" + x.StrExpr());
                            }
                        } // Positive Test Casee
                        // Non Extremity
                        else {
                            x = (() -> Diff1Expr + "/" + Diff2Expr);
                            log.info("ProExtr:parseQuotientRule:Rule17:x.StrExpr()=" + x.StrExpr());
                        }
                    }
                    log.info("ProExtr:parseQuotientRule:x.StrExpr()=" + x.StrExpr());
                }
                log.info("ProExtr:parseQuotientRule:TermOperator=" + (char) TermOperator);
                log.info("ProExtr:parseQuotientRule:Diff1Expr=" + Diff1Expr);
                log.info("ProExtr:parseQuotientRule:Diff2Expr=" + Diff2Expr);
                log.info("ProExtr:parseQuotientRule:Final:x.StrExpr()=" + x.StrExpr());
                return x;
            }
        }.parseExtreme();
    }

    /**
     * ************************************************************
     */
    /**
     * ************* Lambda Low Level Mapping Functions End*****
     */
    /**
     * ************************************************************
     */
    public static void BackupStateAll(Map<String, String> variablesBackupState) {
        BackupparserState(variablesBackupState);
        BackupControlStateVar(variablesBackupState);
    }

    public static void RestoreStateAll(Map<String, String> variablesBackupState) {
        RestoreParserState(variablesBackupState);
        RestoreControlStateVar(variablesBackupState);
    }

    public static void BackupControlStateVar(Map<String, String> variablesBackupState) {
        // Boolean Variable Flags
        variablesBackupState.put("ContainsIndConstFlag", "" + ContainsIndConstFlag);
        variablesBackupState.put("ComplexAssignFlag", "" + ComplexAssignFlag);
        variablesBackupState.put("ContainsXFlag", "" + ContainsXFlag);
        variablesBackupState.put("TrailConstantFlag", "" + TrailConstantFlag);
        variablesBackupState.put("GotVariableFlag", "" + GotVariableFlag);
        variablesBackupState.put("GotIndConstFlag", "" + GotIndConstFlag);
        variablesBackupState.put("GotExpntNumFlag", "" + GotExpntNumFlag);
        variablesBackupState.put("TempIndConstStrSet", "" + TempIndConstStrSet);
        //Integer Variables
        variablesBackupState.put("GotVariablePos", "" + GotVariablePos);
        variablesBackupState.put("GotIndConstPos", "" + GotIndConstPos);
        variablesBackupState.put("TermOperatorPos", "" + TermOperatorPos);
        variablesBackupState.put("TermOutputOperator", "" + TermOutputOperator);
        variablesBackupState.put("LATermOperator", "" + LATermOperator);
        variablesBackupState.put("LATermOperatorBraces", "" + LATermOperatorBraces);
        variablesBackupState.put("LAIndConstOperator", "" + LAIndConstOperator);
        variablesBackupState.put("LAPreIndConstOperator", "" + LAPreIndConstOperator);
        variablesBackupState.put("LAVarTermOperator", "" + LAVarTermOperator);
        variablesBackupState.put("TrailConstantOperator", "" + TrailConstantOperator);
        variablesBackupState.put("LAPreTermOperator", "" + LAPreTermOperator);
        variablesBackupState.put("LAMAPOperator", "" + LAMAPOperator);
        variablesBackupState.put("LADyYByDxMAPOperator", "" + LADyYByDxMAPOperator);
        variablesBackupState.put("DiffTermOperator", "" + DiffTermOperator);
        variablesBackupState.put("LADiffFactorOperator", "" + LADiffFactorOperator);
        variablesBackupState.put("LADiffIndConstOperator", "" + LADiffIndConstOperator);
        variablesBackupState.put("LAConstSimpleMapFlag", "" + LAConstSimpleMapFlag);
        variablesBackupState.put("LAConstComplexMapFlag", "" + LAConstComplexMapFlag);
        variablesBackupState.put("LAVarSimpleMapFlag", "" + LAVarSimpleMapFlag);
        variablesBackupState.put("LAVarComplexMapFlag", "" + LAVarComplexMapFlag);
        variablesBackupState.put("DiffConstSimpleMapFlag", "" + DiffConstSimpleMapFlag);
        variablesBackupState.put("DiffConstComplexMapFlag", "" + DiffConstComplexMapFlag);
        variablesBackupState.put("LastLADiffIndConstAidPos", "" + LastLADiffIndConstAidPos);
        variablesBackupState.put("LastLADiffConstCoeffAidPos", "" + LastLADiffConstCoeffAidPos);
        variablesBackupState.put("GotBracesVarFlag", "" + GotBracesVarFlag);
        variablesBackupState.put("posIndConst", "" + posIndConst);
        variablesBackupState.put("ContainsFuncFlag", "" + ContainsFuncFlag);
        variablesBackupState.put("FunctionStr", FunctionStr);
        variablesBackupState.put("ParenthesisFlag", "" + ParenthesisFlag);
        variablesBackupState.put("GotBracesCount", "" + GotBracesCount);
        variablesBackupState.put("GotBracesCountCurr", "" + GotBracesCountCurr);

        variablesBackupState.put("timesLoop", "" + timesLoop);
        variablesBackupState.put("TermOutput", "" + TermOutput);
        variablesBackupState.put("TermOutputIndConst", "" + TermOutputIndConst);

    }

    public static void RestoreControlStateVar(Map<String, String> variablesBackupState) {
        // Boolean Variable Flags
        ContainsIndConstFlag = Boolean.parseBoolean(variablesBackupState.get("ContainsIndConstFlag"));
        ComplexAssignFlag = Boolean.parseBoolean(variablesBackupState.get("ComplexAssignFlag"));
        ContainsXFlag = Boolean.parseBoolean(variablesBackupState.get("ContainsXFlag"));
        TrailConstantFlag = Boolean.parseBoolean(variablesBackupState.get("TrailConstantFlag"));
        GotVariableFlag = Boolean.parseBoolean(variablesBackupState.get("GotVariableFlag"));
        GotIndConstFlag = Boolean.parseBoolean(variablesBackupState.get("GotIndConstFlag"));
        GotExpntNumFlag = Boolean.parseBoolean(variablesBackupState.get("GotExpntNumFlag"));
        TempIndConstStrSet = Boolean.parseBoolean(variablesBackupState.get("TempIndConstStrSet"));
        //Integer Variables
        GotVariablePos = Integer.parseInt(variablesBackupState.get("GotVariablePos"));
        GotIndConstPos = Integer.parseInt(variablesBackupState.get("GotIndConstPos"));
        TermOperatorPos = Integer.parseInt(variablesBackupState.get("TermOperatorPos"));
        TermOutputOperator = Integer.parseInt(variablesBackupState.get("TermOutputOperator"));
        LATermOperator = Integer.parseInt(variablesBackupState.get("LATermOperator"));
        LATermOperatorBraces = Integer.parseInt(variablesBackupState.get("LATermOperatorBraces"));
        LAIndConstOperator = Integer.parseInt(variablesBackupState.get("LAIndConstOperator"));
        LAPreIndConstOperator = Integer.parseInt(variablesBackupState.get("LAPreIndConstOperator"));
        LAVarTermOperator = Integer.parseInt(variablesBackupState.get("LAVarTermOperator"));
        TrailConstantOperator = Integer.parseInt(variablesBackupState.get("TrailConstantOperator"));
        LAPreTermOperator = Integer.parseInt(variablesBackupState.get("LAPreTermOperator"));
        LAMAPOperator = Integer.parseInt(variablesBackupState.get("LAMAPOperator"));
        LADyYByDxMAPOperator = Integer.parseInt(variablesBackupState.get("LADyYByDxMAPOperator"));
        DiffTermOperator = Integer.parseInt(variablesBackupState.get("DiffTermOperator"));
        LADiffFactorOperator = Integer.parseInt(variablesBackupState.get("LADiffFactorOperator"));
        LADiffIndConstOperator = Integer.parseInt(variablesBackupState.get("LADiffIndConstOperator"));

        LAConstSimpleMapFlag = Boolean.parseBoolean(variablesBackupState.get("LAConstSimpleMapFlag"));
        LAConstComplexMapFlag = Boolean.parseBoolean(variablesBackupState.get("LAConstComplexMapFlag"));
        LAVarSimpleMapFlag = Boolean.parseBoolean(variablesBackupState.get("LAVarSimpleMapFlag"));
        LAVarComplexMapFlag = Boolean.parseBoolean(variablesBackupState.get("LAVarComplexMapFlag"));
        DiffConstSimpleMapFlag = Boolean.parseBoolean(variablesBackupState.get("DiffConstSimpleMapFlag"));
        DiffConstComplexMapFlag = Boolean.parseBoolean(variablesBackupState.get("DiffConstComplexMapFlag"));

        LastLADiffIndConstAidPos = Integer.parseInt(variablesBackupState.get("LastLADiffIndConstAidPos"));
        LastLADiffConstCoeffAidPos = Integer.parseInt(variablesBackupState.get("LastLADiffConstCoeffAidPos"));

        GotBracesVarFlag = Boolean.parseBoolean(variablesBackupState.get("GotBracesVarFlag"));
        posIndConst = Integer.parseInt(variablesBackupState.get("posIndConst"));
        ContainsFuncFlag = Boolean.parseBoolean(variablesBackupState.get("ContainsFuncFlag"));;
        FunctionStr = variablesBackupState.get("FunctionStr");
        ParenthesisFlag = Boolean.parseBoolean(variablesBackupState.get("ParenthesisFlag"));

        GotBracesCount = Integer.parseInt(variablesBackupState.get("GotBracesCount"));
        GotBracesCountCurr = Integer.parseInt(variablesBackupState.get("GotBracesCountCurr"));

        timesLoop = Integer.parseInt(variablesBackupState.get("timesLoop"));
        TermOutput = Double.parseDouble(variablesBackupState.get("TermOutput"));
        TermOutputIndConst = Double.parseDouble(variablesBackupState.get("TermOutputIndConst"));
    }

    public static void BackupparserState(Map<String, String> variablesBackupState) {
        //Backup Expression
        variablesBackupState.put("posExpr", "" + posExpr);
        variablesBackupState.put("chExpr", "" + chExpr);
        variablesBackupState.put("StrExpr", StrExpr);
        //Backup Simple Expression
        variablesBackupState.put("pos", "" + pos);
        variablesBackupState.put("ch", "" + ch);
        variablesBackupState.put("Str", Str);

        //Backup Simple Term
        variablesBackupState.put("posTerm", "" + posTerm);
        variablesBackupState.put("chTerm", "" + chTerm);
        variablesBackupState.put("StrTerm", StrTerm);

        //Backup OrderVar
        variablesBackupState.put("posOrder", "" + posOrder);
        variablesBackupState.put("chOrder", "" + chOrder);
        variablesBackupState.put("StrOrder", StrOrder);

        //Backup LookAheadSign
        variablesBackupState.put("posSign", "" + posSign);
        variablesBackupState.put("chSign", "" + chSign);
        variablesBackupState.put("chTermSign", "" + chTermSign);
        variablesBackupState.put("StrSign", StrSign);

        //Backup LoohAheadConst
        variablesBackupState.put("posLA", "" + posLA);
        variablesBackupState.put("chLA", "" + chLA);
        variablesBackupState.put("StrLA", StrLA);

        //Backup LoohAheadVar
        variablesBackupState.put("posVar", "" + posVar);
        variablesBackupState.put("chVar", "" + chVar);
        variablesBackupState.put("StrVar", StrVar);

        //Backup LookAheadIndConst
        variablesBackupState.put("posIndConst", "" + posIndConst);
        variablesBackupState.put("chIndConst", "" + chIndConst);
        variablesBackupState.put("StrIndConst", StrIndConst);

        //Backup LookAheadParan
        variablesBackupState.put("posParen", "" + posParen);
        variablesBackupState.put("chParen", "" + chParen);
        variablesBackupState.put("StrParen", StrParen);

        variablesBackupState.put("timesLoop", "" + timesLoop);
    }

    public static void RestoreParserState(Map<String, String> variablesBackupState) {
        //Restore Expression
        posExpr = Integer.parseInt(variablesBackupState.get("posExpr"));
        chExpr = Integer.parseInt(variablesBackupState.get("chExpr"));
        StrExpr = variablesBackupState.get("StrExpr");

        //Restore Simple Expression
        pos = Integer.parseInt(variablesBackupState.get("pos"));
        ch = Integer.parseInt(variablesBackupState.get("ch"));
        Str = variablesBackupState.get("Str");

        //Restore Simple Term
        posTerm = Integer.parseInt(variablesBackupState.get("posTerm"));
        chTerm = Integer.parseInt(variablesBackupState.get("chTerm"));
        StrTerm = variablesBackupState.get("StrTerm");

        //Restore OrderVar
        posOrder = Integer.parseInt(variablesBackupState.get("posOrder"));
        chOrder = Integer.parseInt(variablesBackupState.get("chOrder"));
        StrOrder = variablesBackupState.get("StrOrder");

        //Restore LookAheadSign
        posSign = Integer.parseInt(variablesBackupState.get("posSign"));
        chSign = Integer.parseInt(variablesBackupState.get("chSign"));
        chTermSign = Integer.parseInt(variablesBackupState.get("chTermSign"));
        StrSign = variablesBackupState.get("StrSign");

        //Restore LoohAheadConst
        posLA = Integer.parseInt(variablesBackupState.get("posLA"));
        chLA = Integer.parseInt(variablesBackupState.get("chLA"));
        StrLA = variablesBackupState.get("StrLA");

        //Restore LoohAheadVar
        posVar = Integer.parseInt(variablesBackupState.get("posVar"));
        chVar = Integer.parseInt(variablesBackupState.get("chVar"));
        StrVar = variablesBackupState.get("StrVar");

        //Restore LookAheadIndConst
        posIndConst = Integer.parseInt(variablesBackupState.get("posIndConst"));
        chIndConst = Integer.parseInt(variablesBackupState.get("chIndConst"));
        StrIndConst = variablesBackupState.get("StrIndConst");

        //Restore LookAheadParan
        posParen = Integer.parseInt(variablesBackupState.get("posParen"));
        chParen = Integer.parseInt(variablesBackupState.get("chParen"));
        StrParen = variablesBackupState.get("StrParen");

        timesLoop = Integer.parseInt(variablesBackupState.get("timesLoop"));
    }

    public static void ChaosPrevention() {
        // Need to Debug which variable is causing 
        // the unpredictability
        //ChaosDebuger("ChaosPrevention", "ChaosPrevention", "Initial", "Str", Str);
        InitParserState();
        //ChaosDebuger("ChaosPrevention", "ChaosPrevention", "FinalParserState", "Str", Str); 
        InitTestCase();
    }

    public static void InitParserState() {
        //  Expression character Index and Character Variable    
        posExpr = -1;
        chExpr = -1;
        StrExpr = "";

        // Simple Expression & Simple Term character Index and Character Variable    
        pos = -1;
        ch = -1;
        chTerm = -1;
        posTerm = -1;
        Str = "";
        // LookAheadConst character Index and Character Variable    
        posLA = -1;
        chLA = -1;
        // LookAheadVar character Index and Character Variable 
        posVar = -1;
        chVar = -1;
        // Sign character Index and Character Variable 
        posSign = -1;
        chSign = -1;
        chTermSign = -1;
        // Order character Index and Character Variable
        posOrder = -1;
        chOrder = -1;
        // LookAheadParen character Index and Character Variable
        posParen = -1;
        chParen = -1;
        // LookAheadIndConst character Index and Character Variable
        posIndConst = -1;
        chIndConst = -1;
        LookAheadOperator = 0;

        StrTerm = "";
        StrSign = "";
        StrLA = "";
        StrVar = "";
        StrIndConst = "";
        StrOrder = "";
        StrParen = "";

        //LAConstDiff Expression character Index and Character Variable
        posLADiff = -1;
        chLADiff = -1;
        StrLADiff = "";
        //Diff Expression character Index and Character Variable
        posDiff = -1;
        chDiff = -1;
        StrDiff = "";
        //Diff Expression character Index and Character Variable
        posIntegr = -1;
        chIntegr = -1;
        StrIntegr = "";
        //Parse State Variables
        // PUBLIC VARIABLES USED IN OTHER FILES
        //IsAConstant Return Type 
        StrIsAConstant = "";
    }

    public static void InitTestCase() {

        NormalExprnFlag = true;
        ParenthesisHighLvlNum = 0;
        ParenthesisCurrNum = 0;

        ParenthesisFlag = false;
        NormalNotPareFlag = true;
        SavedOperator = 0;
        ParenthesisOperator = 0;
        NumCoeff = 0;
        CoeffCoeff = 0;

        //Global Common Flags for each Interface
        FuncArgFlag = false;
        ParanExprFlag = false;

        // SimpleTerm Interface Variables 
        ComplexAssignFlag = false;
        GotVariableFlag = false;
        GotVariablePos = 0;
        GotExpntNumFlag = false;
        TermOperatorPos = -1;
        TermOutputOperator = -1;
        TermOutput = 0.0;
        TermOutputIndConst = 0.0;
        GotExtremityFlag = false;
        GotIndConstFlag = false;
        GotIndConstPos = 0;
        TempIndConstBase = "";
        TempIndConstStrSet = false;
        TrailConstantFlag = false;
        TrailConstantOperator = -1;
        timesLoop = 0;
        ContainsXFlag = false;
        ContainsIndConstFlag = false;
        TermOutputSetFlag = false;
        TermOutputOperator = -1;
        LATermOperatorBraces = -1;
        LAPreTermOperator = -1;
        LATermOperator = -1;
        LAVarTermOperator = -1;
        LAPreIndConstOperator = -1;
        LAIndConstOperator = -1;
        LAMAPOperator = -1;
        LADyYByDxMAPOperator = -1;
        LAVarSimpleMapFlag = false;
        LAVarComplexMapFlag = false;
        DiffTermOperator = -1;
        LADiffFactorOperator = -1;
        LADiffIndConstOperator = -1;
        LastInvokedFlag = "";
        LastInvokedPos = -1;
        LastLADiffIndConstAidPos = -1;
        LastLADiffExtremityAidPos = -1;
        LastLADiffConstCoeffAidPos = -1;
        ParseMapposModFlag = false;
        ZeroTermSignFlag = false;
        // Maximum Order of the Term or Expression
        MaxTermOutput = 0.0;
        FunctionStr = "";
        ContainsFuncFlag = false;
        LAConstSimpleMapFlag = false;
        LAConstComplexMapFlag = false;

        //Function Argument Flag
        GotBracesVarFlag = false;
        GotParenPos = -1;
        GotBracesCount = -1;
        GotBracesCountCurr = -1;
        // Diff Interface Variables 
        FactorsExpr = "";
        FactorsDiffExpr = "";
        FunctionExpr = "";
        FunctionDiffExpr = "";
        ParanExpr = "";
        ParanDiffExpr = "";
        EnteredLAConstFlag = false;
        DiffConstSimpleMapFlag = false;
        DiffConstComplexMapFlag = false;
    }

    public static void ParseLog(String ExprDesc, String HighLevelCallFunc, String InitialORFinal, String StrDesc, String MyStr) {
        if ((pos > 0) && (pos < Str.length()) && (Str != null) && (!Str.equalsIgnoreCase(""))) {
            log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":" + "Str" + "=" + Str.substring(pos));
        }
        if ((posTerm > 0) && (posTerm < StrTerm.length()) && (StrTerm != null) && (!StrTerm.equalsIgnoreCase(""))) {
            log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":" + "StrTerm" + "=" + StrTerm.substring(posTerm));
        }
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":" + StrDesc + "=" + MyStr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":StrExpr=" + StrExpr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":StrLA=" + StrLA);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":StrVar=" + StrVar);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":StrIndConst=" + StrIndConst);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posSign=" + posSign + " chSign=" + (char) chSign + " chSign=" + chSign + " chTermSign=" + (char) chTermSign + " chTermSign=" + chTermSign);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posLA=" + posLA + " chLA=" + (char) chLA);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posVar=" + posVar + " chVar=" + (char) chVar + " TermOutputOperator=" + (char) TermOutputOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posParen=" + posParen + " chParen=" + (char) chParen);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posTerm=" + posTerm + " chTerm=" + (char) chTerm);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posExpr=" + posExpr + " chExpr=" + (char) chExpr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":pos=" + pos + " ch=" + (char) ch);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posDiff=" + posDiff + " chDiff=" + (char) chDiff);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":ContainsIndConstFlag=" + ContainsIndConstFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsXFlag=" + ContainsXFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":TrailConstantFlag=" + TrailConstantFlag + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotExpntNumFlag=" + GotExpntNumFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":TempIndConstStrSet=" + TempIndConstStrSet);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":LATermOperator=" + (char) LATermOperator + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " TrailConstantOperator=" + (char) TrailConstantOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAPreTermOperator=" + (char) LAPreTermOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " DiffTermOperator=" + (char) DiffTermOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAConstSimpleMapFlag=" + LAConstSimpleMapFlag + " LAConstComplexMapFlag=" + LAConstComplexMapFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAVarSimpleMapFlag=" + LAVarSimpleMapFlag + " LAVarComplexMapFlag=" + LAVarComplexMapFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " DiffConstSimpleMapFlag=" + DiffConstSimpleMapFlag + " DiffConstComplexMapFlag=" + DiffConstComplexMapFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " GotBracesVarFlag=" + GotBracesVarFlag + " ParenthesisFlag=" + ParenthesisFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
        // Integer Parser && Control State
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " FuncArgFlag=" + FuncArgFlag + " ParanExprFlag=" + ParanExprFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " ParanExprFlag=" + ParanExprFlag + " ParanExprInProgressFlag=" + ParanExprInProgressFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " FuncName=" + FuncName + " timesLoop=" + timesLoop);
    }

    public static void RestoreStateParen(Map<String, String> variablesBackupState) {
        //Restore LookAheadParan
        posParen = Integer.parseInt(variablesBackupState.get("posParen"));
        chParen = Integer.parseInt(variablesBackupState.get("chParen"));
        StrParen = variablesBackupState.get("StrParen");

        //Restore Simple Expression
        pos = Integer.parseInt(variablesBackupState.get("pos"));
        ch = Integer.parseInt(variablesBackupState.get("ch"));
        Str = variablesBackupState.get("Str");

        //Restore Simple Term
        posTerm = Integer.parseInt(variablesBackupState.get("posTerm"));
        chTerm = Integer.parseInt(variablesBackupState.get("chTerm"));
        StrTerm = variablesBackupState.get("StrTerm");

    }

    public static void RestoreOtherStateParen(Map<String, String> variablesBackupState) {
        //Restore OrderVar
        posOrder = Integer.parseInt(variablesBackupState.get("posOrder"));
        chOrder = Integer.parseInt(variablesBackupState.get("chOrder"));
        StrOrder = variablesBackupState.get("StrOrder");

        //Restore LookAheadSign
        posSign = Integer.parseInt(variablesBackupState.get("posSign"));
        chSign = Integer.parseInt(variablesBackupState.get("chSign"));
        chTermSign = Integer.parseInt(variablesBackupState.get("chTermSign"));
        StrSign = variablesBackupState.get("StrSign");

        //Restore LoohAheadConst
        posLA = Integer.parseInt(variablesBackupState.get("posLA"));
        chLA = Integer.parseInt(variablesBackupState.get("chLA"));
        StrLA = variablesBackupState.get("StrLA");

        //Restore LoohAheadVar
        posVar = Integer.parseInt(variablesBackupState.get("posVar"));
        chVar = Integer.parseInt(variablesBackupState.get("chVar"));
        StrVar = variablesBackupState.get("StrVar");

        //Restore LookAheadIndConst
        posIndConst = Integer.parseInt(variablesBackupState.get("posIndConst"));
        chIndConst = Integer.parseInt(variablesBackupState.get("chIndConst"));
        StrIndConst = variablesBackupState.get("StrIndConst");

    }

    public static void ChaosDebuger(String ExprDesc, String HighLevelCallFunc, String InitialORFinal, String StrDesc, String MyStr) {
        if ((pos > 0) && (pos < Str.length()) && (Str != null) && (!Str.equalsIgnoreCase(""))) {
            System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":" + "Str" + "=" + Str.substring(pos));
        }
        if ((posTerm > 0) && (posTerm < StrTerm.length()) && (StrTerm != null) && (!StrTerm.equalsIgnoreCase(""))) {
            System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":" + "StrTerm" + "=" + StrTerm.substring(posTerm));
        }
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":" + StrDesc + "=" + MyStr);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":StrExpr=" + StrExpr);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posSign=" + posSign + " chSign=" + (char) chSign + " chSign=" + chSign + " chTermSign=" + (char) chTermSign + " chTermSign=" + chTermSign);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posLA=" + posLA + " chLA=" + (char) chLA);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posVar=" + posVar + " chVar=" + (char) chVar);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posParen=" + posParen + " chParen=" + (char) chParen);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posTerm=" + posTerm + " chTerm=" + (char) chTerm);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posExpr=" + posExpr + " chExpr=" + (char) chExpr);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":pos=" + pos + " ch=" + (char) ch);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posDiff=" + posDiff + " chDiff=" + (char) chDiff);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":ContainsIndConstFlag=" + ContainsIndConstFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsXFlag=" + ContainsXFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":TrailConstantFlag=" + TrailConstantFlag + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotExpntNumFlag=" + GotExpntNumFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":LATermOperator=" + (char) LATermOperator + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + ":LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " TrailConstantOperator=" + (char) TrailConstantOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LAPreTermOperator=" + (char) LAPreTermOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " DiffTermOperator=" + (char) DiffTermOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LAConstSimpleMapFlag=" + LAConstSimpleMapFlag + " LAConstComplexMapFlag=" + LAConstComplexMapFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LAVarSimpleMapFlag=" + LAVarSimpleMapFlag + " LAVarComplexMapFlag=" + LAVarComplexMapFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " DiffConstSimpleMapFlag=" + DiffConstSimpleMapFlag + " DiffConstComplexMapFlag=" + DiffConstComplexMapFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " GotBracesVarFlag=" + GotBracesVarFlag + " ParenthesisFlag=" + ParenthesisFlag);
        System.out.println(ExprDesc + ":" + HighLevelCallFunc + ":ChaosDebuger:" + InitialORFinal + " GotBracesCount=" + GotBracesCount + " GotBracesCountCurr=" + GotBracesCountCurr);
    }

    public static void ChaosPreventionLowLevel() {
        posLA = -1;
        posVar = -1;
        posIndConst = -1;
        posParen = -1;
        chLA = -1;
        chVar = -1;
        chIndConst = -1;
        chParen = -1;
        GotIndConstPos = -1;
    }

    public static boolean HaltingProblem(String AlgoName, int DifferencePosTerm, int Mych, String StrDesc, String MyStr, int MyStrStart, int OldStart) {
        if (OldStart == -1) {
            OldStart = 0;
        }
        if (MyStrStart == -1) {
            MyStrStart = 0;
        }
        if ((DifferencePosTerm <= 1) && (IsNotAnOperator(Mych)) && (Mych > -1) && (Mych != ')') && (Mych != '(') && (!MyStr.equalsIgnoreCase(""))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + " : " + AlgoName + " HaltingProblem:Infinite Loop:DifferencePosTerm=" + DifferencePosTerm + " Unexpected: " + (char) Mych + " int=" + (int) Mych + " Cannot Format:" + StrDesc + " : " + MyStr.substring(MyStrStart, MyStr.length()) + " at position=" + MyStrStart + " OldStart=" + OldStart);
        }
        return false;
    }

    public static void xmain(String... args) {
        //Uncomment Below Lines for Production Or Release version
        mainProdUser(args);
    }

    public static void mainHghRegression() {
        String StartMyTestCase = "1000";//Test Case Number : Options :All or a Single Number
        String EndMyTestCase = "110023";//Test Case Number : Options :All or a Single Number
        int MaxDiffOrder = 5; // Maximum Differentiation Order (with Answers): 5 :Integration Order (with Answers): 5;
        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        TestHarnessClass MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        System.out.println(ConsoleColors.BLUE + "Starting Test Case:100 End TestCase 110007 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Sanity Test Cases: 100 to 125 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Unit Test Cases: 5019 to 5042 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Names Test Cases: 10001 to 10005 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Coefficients Test Case: 20001 to 20092 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Exponent Test Case: 30001 to 30011 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "SignedX Case: 40001 to 40034 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Independent Constants Case: 50001 to 50044 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Parenthesis Test Case: 80001 to 80012 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Function Test Case: 90001 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Function Argument Test Case: 100001 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Software Engg Test Case: 110001 to 110007" + ConsoleColors.RESET);
        //Uncomment Below Lines for Stand Alone Differentiation Regression Suite
        System.out.println(ConsoleColors.BLUE + "Regression Test Suite (Differentiation)" + ConsoleColors.RESET);
        //mainRegresssionDiffTest(MaxDiffOrder, MyTestData);

        //Uncomment Below Lines for Stand Alone Integration Regression Suite
        System.out.println(ConsoleColors.BLUE + "Regression Test Suite(Integration)" + ConsoleColors.RESET);
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        //mainRegressionIntegrTest(MaxDiffOrder, MyTestData);
        System.out.println(ConsoleColors.BLUE + "Regression Test Suite(Algebra)" + ConsoleColors.RESET);
        StartMyTestCase = "100";
        EndMyTestCase = "225";
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        //mainRegresssionAlgebra(MyTestData);
        StartMyTestCase = "100";
        EndMyTestCase = "225";
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        mainRegresssionHghLvlExprODETest(MaxDiffOrder, MyTestData);
    }

    public static void mainProdUser(String... args) {
        System.out.println(ConsoleColors.BLUE + "User Equation Demo(Differentiation)" + ConsoleColors.RESET);
        UsageCalculus.UserEquation = "p/9.0*xyc1^9.0 + p*6.0*xyc1^6.0 + p/5.0*xyc1^5.0 + p*2.0*xyc1^2.0 + p*25.0*xyc1 + p*50.0";
        UsageCalculus.MaxDiffOrder = 6;
        UsageCalculus.MaxIntegreOrder = 6;
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.mainDiff(args);
        System.out.println(ConsoleColors.BLUE + "User Equation Demo(Integration)" + ConsoleColors.RESET);
        UsageCalculus.mainIntegral(args);

        //Uncomment Below Lines for User String UsageCalculus
        System.out.println(ConsoleColors.BLUE + "User Equation Demo(Interactive)" + ConsoleColors.RESET);
        DiffWithRespTo = "x";
        UsageCalculus.mainUser(args);
        //System.out.println(ConsoleColors.BLUE + "User Equation Demo(Algebra & FoG)" + ConsoleColors.RESET);
        //mainAlgebraUser(args);

        System.out.println(ConsoleColors.BLUE + "User Equation Demo(Predicate)" + ConsoleColors.RESET);
        UsageCalculus.UserTestPredicate = "(x^2+y^2 >= 4)";
        UsageCalculus.mainPredicate(args);

        System.out.println(ConsoleColors.BLUE + "User Basic ODE Solver Demo" + ConsoleColors.RESET);
        CalculusApplication.SchrodingerEquationMethod1();
        System.out.println(ConsoleColors.BLUE + "User Basic ODE Solver Demo" + ConsoleColors.RESET);
        CalculusApplication.SchrodingerEquationMethod2();
    }
}
