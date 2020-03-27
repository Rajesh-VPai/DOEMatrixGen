/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import static DOEMatrixGen.Usage.mainRegresssionDiffTest;
import static DOEMatrixGen.Usage.mainRegressionIntegrTest;
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
 * Version :6.0 (Backup Version:Ver 20.0.3)
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
public class MathsContxtLAv0_1_Prod {

    public static Logger log = Logger.getLogger( MathsContxtLAv0_1_Prod.class.getName());
    static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    private static Long[] PrimeArray = new Long[10000];
    private static int LastIndex = 1;
    static String BC_SplitterDiff = "[\\^\\(\\/\\*\\-\\+\\)]";
    static Map<String, String> variablesDiff = new HashMap<>();
    static Map<String, String> variablesIntegr = new HashMap<>();
    private static Map<String, String> functionsDiff = new HashMap<>();
    private static Map<String, String> functionsSimple = new HashMap<>();
    private static Map<String, String> functionsIntegr = new HashMap<>();

    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumFractionDigits = 25;
    // Maximum number of Integer Digits displayed in Equation
    private static int MaximumIntegerDigits = 50;

    private static enum HighLevelContext {
        Null, Expression, Operand, Factor, Base, End, Sign, Function, Variable, FunctionArgument, Numbers, ParaExpression, ParenthesisOpen, ParenthesisClose, DiffWithRespTo
    }

    private static enum LowLevelOperator {
        Null, Plus, Minus, Mult, Div, Expnt, End, ParenthesisOpen, ParenthesisClose, SignMinus, SignPlus, FuncNvar, Numbers, Function, Variable, Compact
    }

    private static enum MixedLevelDiffContext {
        Null, Diff, StringTerm, Constant, End, Sign,
    }

    private static enum HighLevelRules {
        Null, End,
        ConstantRule,// (af)' = af'
        SumRule, // (f + g)' = f' + g'
        SubractionRule, // (f-g)' = f' - g'
        ProductRule, //(f(x)*(g(x))' = f'*g(x) + f(x)*g'(x)
        QuotientRule, // (f/g)'= (f'g -g'f)/(g*g):Can be derived from Product Rule
        ExponentRule, // a*x^n = a*n*x^(n-1)
        ParenthesisOpen,
        ParenthesisClose,
        FunctionRule,
        TermRule
    }

    private static enum LowLevelRules {
        Null, Plus, Minus, Mult, Div, Expnt, End, ParenthesisOpen, ParenthesisClose, SignMinus, SignPlus, FuncNvar, Numbers, Function, Variable
    }

    private static int LookNum = 5;
    private static String[] LookAheadString = new String[LookNum * 5];
    private static HighLevelContext[] LookAheadCntxt = new HighLevelContext[LookNum];
    private static LowLevelOperator[] LookAheadOp = new LowLevelOperator[LookNum];
    private static MixedLevelDiffContext[] LookAheadDiffCntxt = new MixedLevelDiffContext[LookNum];
    private static HighLevelRules[] LookAheadHighRules = new HighLevelRules[LookNum];
    private static LowLevelRules[] LookAheadLowRules = new LowLevelRules[LookNum];

    static int LookAhead_Oppos = 0;
    static int LookAhead_Stringpos = 0;
    static int LookAhead_Cntxtpos = 0;
    static int LookAhead_LowRulespos = 0;
    static int LookAhead_HighRulespos = 0;

    // Simple Expression & Simple Term character Index and Character Variable    
    private static int pos = -1, ch;
    private static int chNext, chTerm, posTerm = -1;
    private static String Str;
    //LAConstDiff Expression character Index and Character Variable
    private static int posLADiff = -1, chLADiff;
    private static String StrLADiff;
    //Diff Expression character Index and Character Variable
    private static int posDiff = -1, chDiff;
    private static String StrDiff;
    //Diff Expression character Index and Character Variable
    private static int posIntegr = -1, chIntegr;
    private static String StrIntegr;

    // Algebra & AlgebraTerm character Index and Character Variable    
    private static int posFunF = -1, chFunF,posFunFLocal=-1;
    private static int posFunG = -1, chFunG,posFunGLocal=-1;
    private static int chAlgeTerm, posAlgeTerm = -1;
    private static String StrFunF;
    private static String StrFunG;
    private static String StrAlgeTerm;

    private static int posLA = -1, chLA;
    private static int posVar = -1, chVar;
    private static int posSign = -1, chSign, chTermSign;
    private static int posOrder = -1, chOrder;
    private static int posBraces = -1, chBraces;
    private static int posIndConst = -1, chIndConst;
    private static int SavedOperator = 0;
    private static int ParenthesisOperator = 0;
    private static int LookAheadOperator = 0;

    private static int loadstringpos = 0;
    private static int loadoppos = 0;
    private static int loadCntxtpos = 0;
    private static int LookAheadVariable = 0;

    private static String StrTerm;
    private static String StrSign;
    private static String StrLA;
    private static String StrVar;
    private static String StrIndConst;
    private static String StrOrder;
    private static String StrBraces;

    private static int NumCoeff = 0;
    private static int CoeffCoeff = 0;

    //Global Common Flags for each Interface
    private static boolean ParenthesisFlag = false;
    private static boolean NormalNotPareFlag = true;
    private static boolean FuncArgFlag = false;
    private static boolean ParanExprFlag = false;
    private static boolean NormalExprnFlag = true;
    private static int ParenthesisHighLvlNum = 0;
    private static int ParenthesisCurrNum = 0;
    private static boolean NegateGotFlag = false;

    // SimpleTerm Interface Variables 
    private static boolean ComplexAssignFlag = false;
    private static boolean GotVariableFlag = false;
    private static int GotVariablePos = 0;
    private static boolean GotExpntNumFlag = false;
    private static int TermOperatorPos = -1;
    private static double TermOutput = 0.0;
    private static double TermOutputIndConst = 0.0;
    private static boolean GotIndConstFlag = false;
    private static int GotIndConstPos = 0;
    private static String TempIndConstBase = "";
    private static boolean TempIndConstStrSet = false;
    private static boolean TrailConstantFlag = false;
    private static int TrailConstantOperator = -1;
    private static int timesLoop = 0;
    private static boolean ContainsIndConstFlag = false;
    private static boolean TermOutputSetFlag = false;
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
    private static int LADiffTermOperator = -1;
    private static int LADiffFactorOperator = -1;
    private static int LADiffIndConstOperator = -1;
    private static String LastInvokedFlag = "";
    private static int LastInvokedPos = -1;
    private static int LastLADiffIndConstAidPos = -1;
    private static int LastLADiffConstCoeffAidPos = -1;
    private static boolean ParseMapposModFlag = false;
    private static boolean ZeroTermSignFlag = false;
    // Maximum Order of the Term or Expression
    private static double MaxTermOutput = 0.0;
    private static String FunctionStr = "";
    //IsAConstant Return Type 
    private static String StrIsAConstant = "";
    //Function Argument Flag
    private static boolean GotBracesVarFlag = false;
    // Diff Interface Variables 
    private static String FactorsExpr = "";
    private static String FactorsDiffExpr = "";
    private static String FunctionExpr = "";
    private static String FunctionDiffExpr = "";
    private static String ParanExpr = "";
    private static String ParanDiffExpr = "";

    public static void MyFuncExpress() {
        functions.put("sqrt", x -> Math.sqrt(x));
        functions.put("sin", x -> Math.sin(Math.toRadians(x)));
        functions.put("cos", x -> Math.cos(Math.toRadians(x)));
        functions.put("tan", x -> Math.tan(Math.toRadians(x)));
        functions.put("round", x -> Math.round(x));
        functions.put("abs", x -> Math.abs(x));
        functions.put("ceil", x -> Math.ceil(x));
        functions.put("floor", x -> Math.floor(x));
        functions.put("log", x -> Math.log(x));
        functions.put("exp", x -> Math.exp(x));
        //TODO:More Unary Functions to be added
    }

    public static void MyFuncDiff() {
        functionsDiff.put("sin", "cos");
        functionsDiff.put("cos", "-sin");
        functionsDiff.put("tan", "sec^2.0");
        functionsDiff.put("log", "log");
        functionsDiff.put("exp", "exp");
        //TODO:More Differential Unary Functions to be added
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

    public static void MyFuncIntegr() {
        functionsIntegr.put("sin", "-cos");
        functionsIntegr.put("cos", "sin");
        //functionsIntegr.put("tan", "sec^2.0");
        //functionsIntegr.put("log", "log");
        functionsIntegr.put("exp", "exp");
        //TODO:More Differential Unary Functions to be added
    }

    static void nextChar() {
        ++pos;
        ch = (pos < Str.length()) ? Str.charAt(pos) : -1;
        //if (ch == -1) pos=Str.length();

    }

    static void prevChar() {
        --pos;
        ch = (pos >= 0) ? Str.charAt(pos) : -1;
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

    static String eatAll(String Temp, int CharToEat) {
        String MyTempString = "";
        if (Temp.equalsIgnoreCase("")) {
            Temp = Str;
        }
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

    static void nextCharDiff() {
        ++posDiff;
        chDiff = (posDiff < StrDiff.length()) ? StrDiff.charAt(posDiff) : -1;
        //if (chDiff == -1) posDiff=Str.length();

    }

    static void prevCharDiff() {
        --posDiff;
        chDiff = (posDiff >= 0) ? StrDiff.charAt(posDiff) : -1;
    }

    static boolean eatDiff(int CharToEat) {
        while (chDiff == ' ') {
            nextCharDiff();
        }
        if (chDiff == CharToEat) {
            nextCharDiff();
            return true;
        }
        return false;
    }

    static void nextCharIntegr() {
        ++posIntegr;
        chIntegr = (posIntegr < StrIntegr.length()) ? StrIntegr.charAt(posIntegr) : -1;
        //if (chDiff == -1) posDiff=Str.length();

    }

    static void prevCharIntegr() {
        --posIntegr;
        chIntegr = (posIntegr >= 0) ? StrIntegr.charAt(posIntegr) : -1;
    }

    static boolean eatIntegr(int CharToEat) {
        while (chIntegr == ' ') {
            nextCharIntegr();
        }
        if (chIntegr == CharToEat) {
            nextCharIntegr();
            return true;
        }
        return false;
    }

    static boolean FatalCharSet(int CurrChar, String CharSet) {
        CharSequence Temp = "" + CurrChar;
        if (!CharSet.contains(Temp)) {
            return true;
        }
        return false;
    }

    static boolean loadparams(int mypos) {
        int i = 0;
        for (i = 0; i < LookAhead_Stringpos; i += 5) {
            if (LookAheadString[i + 1].equalsIgnoreCase("" + mypos)) {
                loadstringpos = i;
                //loadoppos = Integer.parseInt(LookAheadString[i + 3]);
                //loadCntxtpos = Integer.parseInt(LookAheadString[i + 4]);
                return true;
            }
        }
        return false;
    }

    static void ParseMap(String AlgoName, int strlength, int StartPos, int DiffLength, String DiffWithRespTo) {
        if (AlgoName.equalsIgnoreCase("LookAheadVar:parseVar")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if (TrailConstantFlag == false) {
                if (posVar < (StrVar.length())) {
                    posTerm = posVar + 1;
                    chTerm = chVar;
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }

            } else {
                if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                    posLA = GotVariablePos;
                    if (posLA < StrVar.length()) {
                        posTerm = posLA;
                        chTerm = StrTerm.charAt(posTerm);
                        chLA = chTerm;
                    } else {
                        posTerm = -1;
                        chTerm = -1;
                    }
                }
            }
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
            } else if (TrailConstantFlag == true) {
                if (posLA < StrVar.length()) {
                    posTerm = posLA;
                    chTerm = StrTerm.charAt(posTerm);
                    chLA = chTerm;
                } else {
                    posTerm = -1;
                    chTerm = -1;
                }
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);

        } else if (AlgoName.equalsIgnoreCase("SimpleTerm:parseSimple")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            if (((posSign) <= strlength) && (GotBracesVarFlag == false)) {
                if (pos < 0) {
                    pos = (posSign);
                    ch = Str.charAt(pos);
                    log.info("ParseMap:Sign:Sign:<0:AlgoName=" + AlgoName);
                } else if (pos + (posSign) < Str.length()) {
                    pos = pos + (posSign);
                    ch = Str.charAt(pos);
                    log.info("ParseMap:Sign:Sign:AlgoName=" + AlgoName);
                } else if (((posVar >= 0) && (StrVar != null)) && (pos + (posVar) < Str.length())) {
                    pos = pos + (posVar);
                    ch = Str.charAt(pos);
                    log.info("ParseMap:Sign:LA:AlgoName=" + AlgoName);
                } else if (((posVar >= 0) && (StrVar != null)) && (pos + (posVar) < Str.length())) {
                    pos = pos + (posVar);
                    ch = Str.charAt(pos);
                    log.info("ParseMap:Sign:Var:AlgoName=" + AlgoName);
                } else {
                    pos = -1;
                    ch = -1;
                }
                chSign = ch;
                chTerm = ch;
                log.info("ParseMap:Sign:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            } else if ((pos + (posVar) < Str.length()) && (GotBracesVarFlag == true)) {
                pos = pos + (posVar);
                ch = Str.charAt(pos);
                posTerm = -1;
                chTerm = -1;
                log.info("ParseMap:Var:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            } else {
                pos = -1;
                ch = -1;
                posTerm = -1;
                chTerm = -1;
                log.info("ParseMap:Else:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posSign=" + posSign + " chSign=" + (char) chSign);
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
            } else if ((strlength == -1) && (chTermSign == 0)) {
                chTermSign = '-';
                log.info("ParseMap:Rule13:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((strlength == 1) && (chTerm == '-')) {
                chTermSign = '-';
                log.info("ParseMap:Rule14:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            }
            if ((chTermSign == 0) && (strlength == -1)) {
                chTermSign = '-';
                log.info("ParseMap:Rule15:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            } else if ((chTermSign == 0) && (strlength == 1)) {
                chTermSign = '+';
                if ((chSign == -1) || (chSign == 0)) {
                    ch = -1;
                    chTerm = -1;
                    posTerm = -1;
                }
                log.info("ParseMap:Rule16:Last Term Rule:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " chTermSign=" + (char) chTermSign + " chSign=" + (char) chSign + " strlength=" + strlength);

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
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " LATermOperator=" + (char) LATermOperator);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);

        } else if (AlgoName.equalsIgnoreCase("LookAheadBraces:parseBraces")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
            if (posBraces < StrBraces.length()) {
                posTerm = posBraces + 1;
                chTerm = StrTerm.charAt(posTerm);
            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag);
        } else if (AlgoName.equalsIgnoreCase("LookAheadConst:parseLADiff:Diff")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff));
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));
            if ((ContainsIndConstFlag == true) && (posDiff < StrDiff.length()) && (LastLADiffIndConstAidPos >= 0)) {
                posDiff = posDiff + LastLADiffIndConstAidPos;
                while ((posDiff < StrDiff.length()) && (StrDiff.charAt(posDiff) != '*') && (StrDiff.charAt(posDiff) != '/')) {
                    prevCharDiff();
                }
                if (posDiff < StrDiff.length()) {
                    chDiff = StrDiff.charAt(posDiff);
                } else {
                    posDiff = StrDiff.length();
                    chDiff = -1;
                }

            }
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrLADiff=" + StrLADiff.substring(posLADiff));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " GotVariableFlag=" + GotVariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariablePos=" + GotVariablePos + " TrailConstantFlag=" + TrailConstantFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " StrDiff=" + StrDiff.substring(posDiff));
            log.info("ParseMap:Final:AlgoName=" + AlgoName + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos);
        } else if (AlgoName.equalsIgnoreCase("LookAheadConst:parseLADiff:Integration")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLADiff=" + posLADiff + " chLA=" + (char) chLADiff + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " GotExpntNumFlag=" + GotExpntNumFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
            if ((ContainsIndConstFlag == true) && (posIntegr < StrIntegr.length()) && (LastLADiffIndConstAidPos >= 0)) {
                posIntegr = posIntegr + LastLADiffIndConstAidPos - 1;
                while ((posIntegr < StrIntegr.length()) && (StrIntegr.charAt(posIntegr) != '*') && (StrIntegr.charAt(posIntegr) != '/')) {
                    prevCharIntegr();
                }
                if (posIntegr < StrIntegr.length()) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                } else {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                }
                chIntegr = StrIntegr.charAt(posIntegr);
            } else if (LastLADiffConstCoeffAidPos >= 0) {
                if (LastLADiffConstCoeffAidPos < StrIntegr.length()) {
                    posIntegr = LastLADiffConstCoeffAidPos - DiffLength;
                }
                while ((posIntegr < StrIntegr.length()) && (StrIntegr.charAt(posIntegr) != '*') && (StrIntegr.charAt(posIntegr) != '/')) {
                    prevCharIntegr();
                }
                if (posIntegr < StrIntegr.length()) {
                    chIntegr = StrIntegr.charAt(posIntegr);
                } else {
                    posIntegr = StrIntegr.length();
                    chIntegr = -1;
                }
                ParseMapposModFlag = true;
                log.info("ParseMap:LastLADiffConstCoeffAidPos>0:AlgoName=" + AlgoName + " StrIntegr=" + StrIntegr.substring(posIntegr));
                log.info("ParseMap:LastLADiffConstCoeffAidPos:AlgoName=" + AlgoName + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " posLA=" + posLA + " chLA=" + (char) chLA + " GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " DiffLength=" + DiffLength + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);
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
            pos=posFunF;
            Str=StrFunF;
            if ((pos >=0)&&(pos < Str.length()))
                    ch=Str.charAt(pos);
            else ch=-1;
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            }
        else if (AlgoName.equalsIgnoreCase("SimpleAlgebra:parseSimple:StrFunF-SteadyState")) {
            log.fatal("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            pos=posFunF;
            Str=StrFunF;
            ch=Str.charAt(pos);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " StrFunF=" + StrFunF + " pos=" + pos + " posFunF=" + posFunF + " posFunFLocal=" + posFunFLocal);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " StrFunG=" + StrFunG + " posFunG=" + posFunG);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            }            
        else if (AlgoName.equalsIgnoreCase("SimpleAlgebra:parseSimple:StrFunG")) {
            log.info("ParseMap:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            pos=posFunG;
            Str=StrFunG;
            if ((pos >=0)&&(pos < Str.length()))
                    ch=Str.charAt(pos);
            else ch=Str.charAt(0);
            log.fatal("ParseMap:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            }
    }
    static void ParseStore(String AlgoName) {
        if (AlgoName.equalsIgnoreCase("StrFunF")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            if ((pos!=-1)&&(posFunF >= 0)&&((pos >=0)&&(pos < Str.length()) ) ) {
                chFunF=StrFunF.charAt(0);
                Str=StrFunF;
                posFunF=pos;
                ch=Str.charAt(pos);
            }else {
                chFunF=StrFunF.charAt(0);
                Str=StrFunF;
                pos=0;
                posFunF=0;
                ch=Str.charAt(0);
            }
            StrFunF=Str;
            chFunF=Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName +" Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        }else if (AlgoName.equalsIgnoreCase("StrFunG")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos!=-1)&&(posFunG >= 0)&&((pos >=0)&&(pos < Str.length()) ) ) {
                chFunG=StrFunG.charAt(0);
                Str=StrFunG;
                posFunG=pos;
                ch=Str.charAt(pos);
            }
            else {
                chFunG=StrFunG.charAt(0);
                Str=StrFunG;
                pos=0;
                posFunG=0;
                ch=Str.charAt(pos);
            }
            StrFunG=Str;
            chFunG=Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName +" Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        }else if (AlgoName.equalsIgnoreCase("StrFunF-Inf")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF);
            if ((pos!=-1)&&(posFunF >= 0)&&((pos >=0)&&(pos < Str.length()) ) ) {
                chFunF=StrFunF.charAt(0);
                Str=StrFunF;
                pos=posFunF;
                ch=Str.charAt(pos);
            }else {
                chFunF=StrFunF.charAt(0);
                Str=StrFunF;
                pos=posFunF;
                ch=Str.charAt(pos);
            }
            StrFunF=Str;
            chFunF=Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName +" Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        }else if (AlgoName.equalsIgnoreCase("StrFunG-Inf")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos!=-1)&&(posFunG >= 0)&&((pos >=0)&&(pos < Str.length()) ) ) {
                chFunG=StrFunG.charAt(0);
                Str=StrFunG;
                pos=posFunG;
                ch=Str.charAt(pos);
            }
            else {
                chFunG=StrFunG.charAt(0);
                Str=StrFunG;
                pos=posFunG;
                ch=Str.charAt(pos);
            }
            StrFunG=Str;
            chFunG=Str.charAt(pos);
            log.info("ParseStore:Final:AlgoName=" + AlgoName +" Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
        }else if (AlgoName.equalsIgnoreCase("StrFunG-Inf-SteadyState")) {
            log.info("ParseStore:Initial:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG);
            if ((pos!=-1)&&(posFunG >= 0)&&((pos >=0)&&(pos < StrFunG.length()) ) ) {
                log.info("ParseStore:Initial:AlgoName=" + AlgoName + "Rule1");
                chFunG=StrFunG.charAt(posFunG);
                Str=StrFunG;
                posFunG=pos;
                ch=Str.charAt(pos);
            }else if (pos==-1){
                log.info("ParseStore:Initial:AlgoName=" + AlgoName + "Rule2");
                Str=StrFunG;
                ch=Str.charAt(posFunG);
                pos=posFunG;
            }else if ((posFunG == -1 )&&(pos !=-1)){
                log.info("ParseStore:Initial:AlgoName=" + AlgoName + "Rule3");
                Str=StrFunG;
                ch=Str.charAt(pos);
                posFunG=pos;
                chFunG=StrFunG.charAt(posFunG);
            }else{
                pos=0;
                posFunG=0;
                Str=StrFunG;
            }
            log.info("ParseStore:Final:AlgoName=" + AlgoName +" Str=" + Str.substring(pos));
            log.info("ParseStore:Final:AlgoName=" + AlgoName + " pos=" + pos + " ch=" + (char) ch + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " posVar=" + posVar + " chVar=" + (char) chVar + " posLA=" + posLA + " chLA=" + (char) chLA);
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

    static void LookAheadFunc_Opr(int i, int mypos, String CallingExpr) {

        int j = 0;
        int mych = 0;
        if (CallingExpr.equalsIgnoreCase("")) {
            mych = ch;
        } else if (CallingExpr.equalsIgnoreCase("Diff")) {
            mych = chDiff;
        } else if (CallingExpr.equalsIgnoreCase("Integr")) {
            mych = chIntegr;
        }
        LookAheadOp[j] = LowLevelOperator.Null;
        j++;
        for (; ((j <= i) && (j < LookAheadOp.length)); j++) {
            while (mych == ' ') {
                if (CallingExpr.equalsIgnoreCase("")) {
                    nextChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    nextCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    nextCharIntegr();
                    mych = chIntegr;
                }
            }
            switch (mych) {
                case '+':
                    LookAheadOp[j] = LowLevelOperator.Plus;
                    break;
                case '-':
                    LookAheadOp[j] = LowLevelOperator.Minus;
//                    if ((LookAheadOp[j - 1] == LowLevelOperator.Mult)
//                            || (LookAheadOp[j - 1] == LowLevelOperator.Div)
//                            || (LookAheadOp[j - 1] == LowLevelOperator.Null)) {
//                        LookAheadOp[j] = LowLevelOperator.Compact;
//                    }
                    break;
                case '*':
                    LookAheadOp[j] = LowLevelOperator.Mult;
                    break;
                case '/':
                    LookAheadOp[j] = LowLevelOperator.Div;
                    break;
                case '^':
                    LookAheadOp[j] = LowLevelOperator.Expnt;
                    break;
                case '(':
                    LookAheadOp[j] = LowLevelOperator.ParenthesisOpen;
                    break;
                case ')':
                    LookAheadOp[j] = LowLevelOperator.ParenthesisClose;
                    break;
                case 0:
                    LookAheadOp[j] = LowLevelOperator.Null;
                    break;
                case -1:
                    LookAheadOp[j] = LowLevelOperator.End;
                    break;
            }
            if (IsNumber(mych)) { //numbers
                LookAheadOp[j] = LowLevelOperator.Numbers;
                while (IsNumber(mych)) {
                    if (CallingExpr.equalsIgnoreCase("")) {
                        nextChar();
                        mych = ch;
                    } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                        nextCharDiff();
                        mych = chDiff;
                    } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                        nextCharIntegr();
                        mych = chIntegr;
                    }
                    if ((mych == 'E') || (mych == 'e')) {
                        if (CallingExpr.equalsIgnoreCase("")) {
                            nextChar();
                            mych = ch;
                        } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                            nextCharDiff();
                            mych = chDiff;
                        } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                            nextCharIntegr();
                            mych = chIntegr;
                        }
                        if ((mych == '-') || (mych == '+')) {
                            if (CallingExpr.equalsIgnoreCase("")) {
                                nextChar();
                                mych = ch;
                            } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                                nextCharDiff();
                                mych = chDiff;
                            } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                                nextCharIntegr();
                                mych = chIntegr;
                            }
                        }
                    }
                }
                if (CallingExpr.equalsIgnoreCase("")) {
                    prevChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    prevCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    prevCharIntegr();
                    mych = chIntegr;
                }
            } else if (IsVariable(mych)) {
                LookAheadOp[j] = LowLevelOperator.FuncNvar;
                while ((IsVariable(mych)) || (IsNumber(mych))) {
                    if (CallingExpr.equalsIgnoreCase("")) {
                        nextChar();
                        mych = ch;
                    } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                        nextCharDiff();
                        mych = chDiff;
                    } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                        nextCharIntegr();
                        mych = chIntegr;
                    }
                }
                if (CallingExpr.equalsIgnoreCase("")) {
                    prevChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    prevCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    prevCharIntegr();
                    mych = chIntegr;
                }
            }
            if ((mych != -1) && (mych != 0)) {
                if (CallingExpr.equalsIgnoreCase("")) {
                    nextChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    nextCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    nextCharIntegr();
                    mych = chIntegr;
                }
            } else if (mych == 0) {
                if (CallingExpr.equalsIgnoreCase("")) {
                    mych = Str.charAt(pos);
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    mych = StrDiff.charAt(posDiff);
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    mych = StrIntegr.charAt(posIntegr);
                }
            } else if (mych == -1) {
                break;//Exit For Loop
            }
        }
        LookAheadOp[j++] = LowLevelOperator.End;
        LookAhead_Oppos = j;
        for (int k = 0; k < j; k++) {
            log.info("LookAheadFunc_Opr:LookAheadOp[" + k + "]=" + LookAheadOp[k]);
        }

    }

    static void LookAheadFunc_String(int i, int mypos, String CallingExpr) {
        boolean CompactorFlag = false;
        int startXPos = 0;
        int Xpos = 0;
        int mych = 0;
        LookAhead_Stringpos = 0;
        int j = 0, x = 2, k = 0;
        for (; j <= i; j++) {
            while (mych == ' ') {
                if (CallingExpr.equalsIgnoreCase("")) {
                    nextChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    nextCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    nextCharIntegr();
                    mych = chIntegr;
                }
            }
            if (IsNumber(mych)) { //numbers
                int startpos = pos;
                while (IsNumber(mych)) {
                    if (CallingExpr.equalsIgnoreCase("")) {
                        nextChar();
                        mych = ch;
                    } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                        nextCharDiff();
                        mych = chDiff;
                    } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                        nextCharIntegr();
                        mych = chIntegr;
                    }
                    if ((mych == 'E') || (mych == 'e')) {
                        if (CallingExpr.equalsIgnoreCase("")) {
                            nextChar();
                            mych = ch;
                        } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                            nextCharDiff();
                            mych = chDiff;
                        } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                            nextCharIntegr();
                            mych = chIntegr;
                        }
                        if ((mych == '-') || (mych == '+')) {
                            if (CallingExpr.equalsIgnoreCase("")) {
                                nextChar();
                                mych = ch;
                            } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                                nextCharDiff();
                                mych = chDiff;
                            } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                                nextCharIntegr();
                                mych = chIntegr;
                            }
                        }
                    }
                }
                int endpos = pos;
                if ((startpos >= 1) && (Str.charAt((startpos - 1)) == '-')) {
                    LookAheadString[LookAhead_Stringpos] = "-" + Str.substring(startpos, endpos);
                    CompactorFlag = true;
                } else {
                    LookAheadString[LookAhead_Stringpos] = Str.substring(startpos, endpos);
                }
                LookAheadString[LookAhead_Stringpos + 1] = "" + startpos;
                LookAheadString[LookAhead_Stringpos + 2] = "" + endpos;
                LookAheadString[LookAhead_Stringpos + 3] = "" + startXPos;
                LookAheadString[LookAhead_Stringpos + 4] = "" + Xpos;
                LookAhead_Stringpos += 5;

            } else if (IsVariable(mych)) {
                int startpos = pos;
                while ((IsVariable(mych)) || (IsNumber(mych))) {
                    if (CallingExpr.equalsIgnoreCase("")) {
                        nextChar();
                        mych = ch;
                    } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                        nextCharDiff();
                        mych = chDiff;
                    } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                        nextCharIntegr();
                        mych = chIntegr;
                    }
                }

                int endpos = pos;
                if ((startpos >= 1) && (Str.charAt((startpos - 1)) == '-')) {
                    LookAheadString[LookAhead_Stringpos] = "-" + Str.substring(startpos, endpos);
                    CompactorFlag = true;
                } else {
                    LookAheadString[LookAhead_Stringpos] = Str.substring(startpos, endpos);
                }
                LookAheadString[LookAhead_Stringpos + 1] = "" + startpos;
                LookAheadString[LookAhead_Stringpos + 2] = "" + endpos;
                LookAheadString[LookAhead_Stringpos + 3] = "" + startXPos;
                LookAheadString[LookAhead_Stringpos + 4] = "" + Xpos;
                LookAhead_Stringpos += 5;
            } else if ((IsOperator(mych))
                    && ((LookAheadOp[x] == LowLevelOperator.Plus)
                    || (LookAheadOp[x] == LowLevelOperator.Minus)
                    || (LookAheadOp[x] == LowLevelOperator.Mult)
                    || (LookAheadOp[x] == LowLevelOperator.Div)
                    || (LookAheadOp[x] == LowLevelOperator.Expnt)
                    || (LookAheadOp[x] == LowLevelOperator.Null)
                    || (LookAheadOp[x] == LowLevelOperator.End))) {
                startXPos = x;
                x++;
                Xpos = x;
            } else if ((IsOperator(mych))
                    && ((LookAheadOp[x] == LowLevelOperator.FuncNvar)
                    || (LookAheadOp[x] == LowLevelOperator.Numbers)
                    || (LookAheadOp[x] == LowLevelOperator.Variable)
                    || (LookAheadOp[x] == LowLevelOperator.Function))) {
                startXPos = x;
                x++;
                Xpos = x;
            }
            if ((mych != -1) && (mych != 0)) {
                if (CallingExpr.equalsIgnoreCase("")) {
                    nextChar();
                    mych = ch;
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    nextCharDiff();
                    mych = chDiff;
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    nextCharIntegr();
                    mych = chIntegr;
                }
            } else if (mych == 0) {
                if (CallingExpr.equalsIgnoreCase("")) {
                    mych = Str.charAt(pos);
                } else if (CallingExpr.equalsIgnoreCase("Diff")) {
                    if (posDiff < StrDiff.length()) {
                        mych = StrDiff.charAt(posDiff);
                    }
                } else if (CallingExpr.equalsIgnoreCase("Integr")) {
                    if (posIntegr < StrIntegr.length()) {
                        mych = StrIntegr.charAt(posIntegr);
                    }
                }
            } else if (mych == -1) {
                break;//Exit For Loop
            }
        }
        if (CompactorFlag == true) {
            k = 0;
            int dummyy = 0;
            for (i = 0; (LookAheadOp[k] != null) && (k < LookAhead_Oppos); k++) {
                LookAheadOp[dummyy++] = LookAheadOp[k];
                if ((k > 0) && (dummyy > 0)
                        && (LookAheadOp[k] == LowLevelOperator.Compact)) {
                    LookAheadOp[--dummyy] = LookAheadOp[k + 1];
                }
            }
            LookAheadOp[dummyy++] = LowLevelOperator.End;
            LookAheadOp[dummyy] = null;
            CompactorFlag = false;
            LookAhead_Oppos = dummyy;
        }
        for (int z = 0; z < LookAhead_Stringpos; z++) {
            log.info("LookAheadFunc_String:LookAheadString[" + z + "]=" + LookAheadString[z]);
        }
        for (int y = 0; y < LookAhead_Oppos; y++) {
            log.info("LookAheadFunc_Opr:LookAheadOp[" + y + "]=" + LookAheadOp[y]);
        }
    }

    static void LookAheadFunc_Context(int i, int mypos, String CallingExpr) {
        int CurrSavedPos = LookAhead_Cntxtpos;
        int j = 0, x = 0;
        for (j = 0; (LookAheadOp != null) && (LookAheadOp[j] != null) && (j < LookAheadOp.length - 1); j++) {
            switch (LookAheadOp[j]) {
                case Plus:
                    LookAheadCntxt[x] = HighLevelContext.Operand;
                    x++;
                    break;
                case Minus:
                    LookAheadCntxt[x] = HighLevelContext.Operand;
                    x++;
                    break;
                case Mult:
                    LookAheadCntxt[x] = HighLevelContext.Operand;
                    x++;
                    break;
                case Div:
                    LookAheadCntxt[x] = HighLevelContext.Operand;
                    x++;
                    break;
                case Expnt:
                    LookAheadCntxt[x] = HighLevelContext.Factor;
                    x++;
                    LookAheadCntxt[x] = HighLevelContext.Operand;
                    x++;
                    break;
                case FuncNvar:
                    if (LookAheadOp[j + 1] == LowLevelOperator.ParenthesisOpen) {
                        LookAheadCntxt[x] = HighLevelContext.Function;
                        x++;
                    } else {
                        LookAheadCntxt[x] = HighLevelContext.Variable;
                        x++;
                    }
                    if ((LookAheadOp[j + 1] != LowLevelOperator.Expnt)
                            && ((LookAheadOp[j + 1] == LowLevelOperator.Plus)
                            || (LookAheadOp[j + 1] == LowLevelOperator.Minus)
                            || (LookAheadOp[j + 1] == LowLevelOperator.Div)
                            || (LookAheadOp[j + 1] == LowLevelOperator.Mult)
                            || (LookAheadOp[j + 1] == LowLevelOperator.ParenthesisClose)
                            || (LookAheadOp[j + 1] == LowLevelOperator.End))) {
                        LookAheadCntxt[x] = HighLevelContext.DiffWithRespTo;
                        x++;
                    }
                    break;
                case Numbers:
                    LookAheadCntxt[x] = HighLevelContext.Numbers;
                    x++;
                    break;
                case Null:
                    LookAheadCntxt[x] = HighLevelContext.Null;
                    x++;
                    break;
                case End:
                    LookAheadCntxt[x] = HighLevelContext.End;
                    x++;
                    break;
                case ParenthesisOpen:
                    LookAheadCntxt[x] = HighLevelContext.ParenthesisOpen;
                    x++;
                    LookAheadCntxt[x] = HighLevelContext.ParaExpression;
                    x++;
                    break;
                case ParenthesisClose:
                    //LookAheadCntxt[x] = HighLevelContext.ParaExpression;
                    //x++;
                    LookAheadCntxt[x] = HighLevelContext.ParenthesisClose;
                    x++;
                    break;
            }
            if (j >= 1) {
                switch (LookAheadOp[j - 1]) {
                    case ParenthesisOpen:
                        log.info("LookAheadFunc_Context:LookAheadOp[j-2]=" + LookAheadOp[j - 2] + " j=" + j);
                        if ((j >= 2) && (LookAheadOp[j - 2] == LowLevelOperator.FuncNvar)) {
                            LookAheadCntxt[j] = HighLevelContext.FunctionArgument;
                        } else if ((j >= 2) && ((LookAheadOp[j - 2] == LowLevelOperator.Mult) || (LookAheadOp[j - 2] == LowLevelOperator.Div) || (LookAheadOp[j - 2] == LowLevelOperator.Plus) || (LookAheadOp[j - 2] == LowLevelOperator.Minus) || (LookAheadOp[j - 2] == LowLevelOperator.Expnt))) {
                            LookAheadCntxt[j] = HighLevelContext.Variable;
                        }
                        break;
                    case ParenthesisClose:
                        //To Do Code
                        break;
                }
            }
            if (LookAheadOp[j] == LowLevelOperator.End) {
                break;//Exit For Loop
            }
        }
        LookAhead_Cntxtpos = x;
        for (int k = 0; k < x; k++) {
            log.info("LookAheadFunc_Context:LookAheadCntxt[" + k + "]=" + LookAheadCntxt[k]);
        }
    }

    static void SyncStringOpAndContext(int i) {
        int CurrSavedPos = pos;
        int CurrSavedch = ch;
        int j = 0, x = 0;
        int k = 0;
        boolean FoundOpFlag = false;
        boolean FoundCntxtFlag = false;
        for (;;) {
            for (; (j < LookAhead_Oppos);) {
                FoundOpFlag = false;
                switch (LookAheadOp[j]) {
                    case Numbers:
                        FoundOpFlag = true;
                        break;
                    case FuncNvar:
                        FoundOpFlag = true;
                        break;
                }
                if (FoundOpFlag == true) {
                    break;
                } else {
                    j++;
                }
            }
            for (; (x < LookAhead_Cntxtpos);) {
                FoundCntxtFlag = false;
                switch (LookAheadCntxt[x]) {
                    case Numbers:
                        FoundCntxtFlag = true;
                        break;
                    case Variable:
                        FoundCntxtFlag = true;
                        break;
                }
                if (FoundCntxtFlag == true) {
                    break;
                } else {
                    x++;
                }
            }
            if (IsNumber(ch)) { //numbers
                int startpos = pos;
                while (IsNumber(ch)) {
                    nextChar();
                    if ((ch == 'E') || (ch == 'e')) {
                        nextChar();
                        if ((ch == '-') || (ch == '+')) {
                            nextChar();
                        }
                    }
                }
                int endpos = pos;
                if ((LookAheadString[k] != null) && (Str != null) && (LookAheadString[k].equalsIgnoreCase(Str.substring(startpos, endpos)))
                        && (LookAheadString[k + 3].equalsIgnoreCase("-1"))
                        && (LookAheadString[k + 4].equalsIgnoreCase("-1"))) {
                    LookAheadString[k + 3] = "" + (j);
                    LookAheadString[k + 4] = "" + (x);
                    k += 5;
                    j++;
                    x++;
                }

            } else if (IsVariable(ch)) {
                int startpos = pos;
                while ((IsVariable(ch)) || (IsNumber(ch))) {
                    nextChar();
                }
                int endpos = pos;
                if ((LookAheadString[k] != null) && (Str != null) && (LookAheadString[k].equalsIgnoreCase(Str.substring(startpos, endpos)))
                        && (LookAheadString[k + 3].equalsIgnoreCase("-1"))
                        && (LookAheadString[k + 4].equalsIgnoreCase("-1"))) {
                    LookAheadString[k + 3] = "" + (j);
                    LookAheadString[k + 4] = "" + (x);
                    k += 5;
                    j++;
                    x++;
                }
            }
            if ((ch != -1) && (ch != 0)) {
                nextChar();
            } else if ((ch == 0) && (Str != null) && (pos >= 0)) {
                ch = Str.charAt(pos);
            } else if (ch == -1) {
                break;//Exit For Loop
            } else {
                break;//Exit For Loop
            }
        }
//        for (k = 0; k <= LookAhead_Stringpos; k++) {
//            log.info("SyncStringOpAndContext:LookAheadString[" + k + "]=" + LookAheadString[k]);
//        }
        pos = CurrSavedPos;
        ch = CurrSavedch;
    }

    static void LookAheadFunc_LowRules(int i) {
        int CurrSavedPos = LookAhead_LowRulespos;
        int j = 0, x = 0;
        for (j = 0; ((LookAheadOp[j] != null) && (j < LookAheadOp.length - 1)); j++) {
            switch (LookAheadOp[j]) {
                case Plus:
                    LookAheadLowRules[x] = LowLevelRules.Plus;
                    x++;
                    break;
                case Minus:
                    LookAheadLowRules[x] = LowLevelRules.Minus;
                    x++;
                    break;
                case Mult:
                    LookAheadLowRules[x] = LowLevelRules.Mult;
                    x++;
                    break;
                case Div:
                    LookAheadLowRules[x] = LowLevelRules.Div;
                    x++;
                    break;
                case Expnt:
                    LookAheadLowRules[x] = LowLevelRules.Expnt;
                    x++;
                    break;
                case FuncNvar:
                    if ((j >= 1) && ((LookAheadOp[j + 1] == LowLevelOperator.ParenthesisOpen))) {
                        LookAheadLowRules[x] = LowLevelRules.Function;
                        x++;
                        LookAheadLowRules[x] = LowLevelRules.FuncNvar;
                        x++;
                    } else {
                        LookAheadLowRules[x] = LowLevelRules.Variable;
                        x++;
                    }
                    break;
                case Numbers:
                    LookAheadLowRules[x] = LowLevelRules.Numbers;
                    x++;
                    break;
                case Null:
                    LookAheadLowRules[x] = LowLevelRules.Null;
                    x++;
                    break;
                case End:
                    LookAheadLowRules[x] = LowLevelRules.End;
                    x++;
                    break;
                case ParenthesisOpen:
                    LookAheadLowRules[x] = LowLevelRules.ParenthesisOpen;
                    x++;
                    break;
                case ParenthesisClose:
                    LookAheadLowRules[x] = LowLevelRules.ParenthesisClose;
                    x++;
                    break;
            }
            if (LookAheadOp[j] == LowLevelOperator.End) {
                break;//Exit For Loop
            }
        }
        LookAhead_LowRulespos = x;
//                for (int k = 0; k < x; k++) {
//                    log.info("LookAheadFunc_LowRules:LookAheadLowRules[" + k + "]=" + LookAheadLowRules[k]);
//                }

    }

    static void LookAheadFunc_HighRules(int i) {
        int CurrSavedPos = LookAhead_HighRulespos;
        int j = 0, x = 0;
        for (j = 0; ((LookAheadLowRules != null) && (LookAheadLowRules[j] != null) && (j < LookAheadLowRules.length - 1)); j++) {
            switch (LookAheadLowRules[j]) {
                case Plus:
                    LookAheadHighRules[x] = HighLevelRules.SumRule;
                    x++;
                    break;
                case Minus:
                    LookAheadHighRules[x] = HighLevelRules.SubractionRule;
                    x++;
                    break;
                case Mult:
                    LookAheadHighRules[x] = HighLevelRules.ProductRule;
                    x++;
                    break;
                case Div:
                    LookAheadHighRules[x] = HighLevelRules.QuotientRule;
                    x++;
                    break;
                case Null:
                    LookAheadHighRules[x] = HighLevelRules.Null;
                    x++;
                    break;
                case End:
                    LookAheadHighRules[x] = HighLevelRules.End;
                    x++;
                    break;
                case Expnt:
                    LookAheadHighRules[x] = HighLevelRules.ExponentRule;
                    x++;
                    break;
                case Numbers:
                    LookAheadHighRules[x] = HighLevelRules.ConstantRule;
                    x++;
                    break;
                case ParenthesisOpen:
                    LookAheadHighRules[x] = HighLevelRules.ParenthesisOpen;
                    x++;
                    break;
                case ParenthesisClose:
                    LookAheadHighRules[x] = HighLevelRules.ParenthesisClose;
                    x++;
                    break;
                case Function:
                    LookAheadHighRules[x] = HighLevelRules.FunctionRule;
                    x++;
                    break;
                case Variable:
                    LookAheadHighRules[x] = HighLevelRules.TermRule;
                    x++;
                    break;
            }
            if (LookAheadLowRules[j] == LowLevelRules.End) {
                break;//Exit For Loop
            }
        }
        LookAhead_HighRulespos = x;
//                for (int k = 0; k < x; k++) {
//                    log.info("LookAheadFunc_HighRules:LookAheadHighRules[" + k + "]=" + LookAheadHighRules[k]);
//                }

    }

    public static void InitLookFunc(String str, int mypos, String CallingExpr) {

        str = str.trim();
        if (str.length() > 5) {
            LookNum = (str.length() - 1) * 3;
        } else if ((str.length() > 1) && (str.length() <= 5)) {
            LookNum = (str.length() - 1) * 5;
        } else {
            LookNum = 6;
        }

        LookAheadOp = new LowLevelOperator[LookNum];
        LookAheadString = new String[LookNum * 5];
        LookAheadCntxt = new HighLevelContext[LookNum];
        LookAheadDiffCntxt = new MixedLevelDiffContext[LookNum];
        LookAheadHighRules = new HighLevelRules[LookNum];
        LookAheadLowRules = new LowLevelRules[LookNum];

        LookAhead_Oppos = 0;
        LookAhead_Stringpos = 0;
        LookAhead_Cntxtpos = 0;
        LookAhead_LowRulespos = 0;
        LookAhead_HighRulespos = 0;
        if ((str == null) || (str.equalsIgnoreCase(""))) {
            return;
        }
        LookAheadFunc_Opr(LookNum, mypos, CallingExpr);
        LookAheadFunc_String(LookNum, mypos, CallingExpr);
        LookAheadFunc_Context(LookNum, mypos, CallingExpr);
        LookAheadFunc_LowRules(LookNum);
        LookAheadFunc_HighRules(LookNum);
        SyncStringOpAndContext(LookNum);

    }

    static boolean IsAConstant(String StrX1, String DiffWithRespTo) {

        try {
            StrIsAConstant = "Unknown";
            String StrX = StrX1;
            //log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
            log.info("IsAConstant:StrX=" + StrX);
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
                    && (StrX.matches("[-+]*[0-9\\.]+"))
                    && (Double.isFinite(Double.valueOf(StrX)))) {
                StrIsAConstant = "Number";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return true;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[a-zA-Z0-9]+"))) {
                StrIsAConstant = "IndConstant";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.equalsIgnoreCase(DiffWithRespTo))
                    && (!StrX.contains(DiffWithRespTo))
                    && (StrX.matches("[\\+\\-\\*^/]*[a-zA-Z0-9\\.]+[\\+\\-\\*^/]*"))) {
                StrIsAConstant = "IndConstantExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.equalsIgnoreCase(DiffWithRespTo))
                    && (StrX.matches("[a-zA-Z0-9]+"))) {
                StrIsAConstant = "Var";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && ((StrX.equalsIgnoreCase(DiffWithRespTo))
                    || ((StrX.contains(DiffWithRespTo)) && ((!StrX.contains(DiffWithRespTo + "^")))))
                    && (StrX.matches("[\\+\\-\\*^/]*[a-zA-Z0-9]+"))) {
                StrIsAConstant = "Var";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.contains(DiffWithRespTo))) {
                StrIsAConstant = "VarExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrIsAConstant.equalsIgnoreCase("Unknown")) && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (!StrX.contains(DiffWithRespTo))
                    && ((StrX.contains("E-")) || (StrX.contains("E+")) || (StrX.contains("e+")) || (StrX.contains("e-")))
                    && (Double.isFinite(Double.valueOf(StrX)))) {
                StrIsAConstant = "Number";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return true;
            } else if (StrIsAConstant.equalsIgnoreCase("Unknown") && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.matches("[a-zA-Z0-9\\.]+"))
                    && (StrX.matches(DiffWithRespTo))) {
                StrIsAConstant = "VarExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            } else if ((StrIsAConstant.equalsIgnoreCase("Unknown") && (StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.matches("[a-zA-Z0-9\\.\\*^/]+")))) {
                StrIsAConstant = "IndConstantExpression";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
                return false;
            }
        } catch (NumberFormatException ConstExp) {
            log.info("IsAConstant:Exception:StrIsAConstant=" + StrIsAConstant);
        }
        log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant);
        return false;
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
        if (MyExpr.equalsIgnoreCase("SimpleExpr")) {
            //Simple Expression
            pos = -1;
            ch = -1;
            NumCoeff = 0;
            loadCntxtpos = 0;
            loadoppos = 0;
            loadstringpos = 0;
            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
        } else if (MyExpr.equalsIgnoreCase("SimpleTerm")) {
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
            ContainsIndConstFlag = false;
            TrailConstantOperator = -1;
            LATermOperatorBraces = -1;
            LATermOperator = -1;
            LAVarTermOperator = -1;
            LAIndConstOperator = -1;
            LAPreTermOperator = -1;
            LAMAPOperator = -1;
            LADyYByDxMAPOperator = -1;
        } else if (MyExpr.equalsIgnoreCase("DiffExpr")) {
            //Diff Expression
            posDiff = 0;
            chDiff = 0;
            NumCoeff = 0;
            loadCntxtpos = 0;
            loadoppos = 0;
            loadstringpos = 0;
            LookAhead_Stringpos = 0;
            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            ParenthesisOperator = 0;
            LADiffTermOperator = -1;
            LastLADiffIndConstAidPos = -1;
            LastLADiffConstCoeffAidPos = -1;
            ContainsIndConstFlag = false;
        } else if (MyExpr.equalsIgnoreCase("IntegrExpr")) {
            //Integre Expression
            posIntegr = 0;
            chIntegr = 0;
            NumCoeff = 0;
            loadCntxtpos = 0;
            loadoppos = 0;
            loadstringpos = 0;

            ParenthesisFlag = false;
            NormalNotPareFlag = true;
            SavedOperator = 0;
            LADiffTermOperator = -1;
            LastLADiffIndConstAidPos = -1;
            LastLADiffConstCoeffAidPos = -1;
            ContainsIndConstFlag = false;
            GotExpntNumFlag = false;
            ParseMapposModFlag = false;
        } else if (MyExpr.equalsIgnoreCase("OverallReset")) {
            //End Of Simple Term Reset;
            ComplexAssignFlag = false;
            TrailConstantFlag = false;
            timesLoop = 0;
            GotVariableFlag = false;
            GotExpntNumFlag = false;
            LastInvokedFlag = "";
            ContainsIndConstFlag = false;
            TrailConstantOperator = -1;
            LATermOperatorBraces = -1;
            LATermOperator = -1;
            LAVarTermOperator = -1;
            LAIndConstOperator = -1;
            LAPreTermOperator = -1;
            LAMAPOperator = -1;
            LADyYByDxMAPOperator = -1;
        }
    }

    @FunctionalInterface
    public interface Expression {

        double eval();
    }

    //Author:StackOverflow User:Boann & User :Mike Scholtes 
    // Taken from stackoverflow.com:Evaluating a math 
    // expression given in string form [closed]
    //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public static Expression parse(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            Expression parse() {
                LookAhead_Stringpos = 0;
                Str = str;
                Expression x = (() -> 0.0);
                if (Str.length() >= 1) {
                    pos = 0;
                    ch = Str.charAt(0);
                } else if (Str.length() <= 0) {
                    return x;
                }
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                ZeroTermSignFlag = false;
                log.info("Expression:parseExpression:Initial:Str=" + Str);
                x = parseExpression();
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(pos, str.length()).equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Expression:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
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
                int ExprStartPos = pos;
                int ExprEndPos = -1;
                if (pos < 0) {
                    ExprStartPos = 0;
                }

                x = parseTerm();
                log.info("Expression:parseExpression:Initial:x.eval=" + x.eval());
                for (;;) {
                    log.info("Expr:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch + " pos=" + pos);
                    if (eat(')')) {
                        return x;
                    } else if ((eat('+'))) {// addition
                        Expression a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = (() -> -1.0 * a.eval() + b.eval());
                            ZeroTermSignFlag = false;
                        } else {
                            x = (() -> a.eval() + b.eval());
                        }
                        log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                    } else if ((eat('-'))) { // subtraction
                        Expression a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = (() -> -1.0 * a.eval() - b.eval());
                            ZeroTermSignFlag = false;
                        } else {
                            x = (() -> a.eval() - b.eval());
                        }
                        log.info("Expression:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            ExprEndPos = pos;
                        } else {
                            ExprEndPos = Str.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = Str.length();
                        }
                        log.info("Expression:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        log.warn("Expression:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        return x;
                    }
                }
            }

            Expression parseTerm() {
                int TermStartPos = pos;
                int TermEndPos = -1;
                if (pos < 0) {
                    TermStartPos = 0;
                }
                Expression x = parseFactor();
                log.info("Expression:parseTerm:Initial:x.eval=" + x.eval());
                for (;;) {
                    Expression a = x;
                    if (eat('*')) { // multiplication
                        Expression b = parseFactor();

                        x = (() -> a.eval() * b.eval());
                        log.info("Expression:parseTerm:*:a.eval=" + a.eval());
                        log.info("Expression:parseTerm:*:b.eval=" + b.eval());
                        log.info("Expression:parseTerm:*:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "* b.eval()=" + b.eval());
                        int temp = ch;
                        nextChar();
                        if (((ch == '+') || (ch == '-')) && (temp != '*') && (temp != '/')) {
                            pos--;
                            ch = temp;
                            return x;
                        } else if ((ch != -1) && (ch != 0)) {
                            pos--;
                            ch = temp;
                        }
                    } else if (eat('/')) { // division
                        Expression b = parseFactor();
                        x = (() -> a.eval() / b.eval());
                        log.info("Expression:parseTerm:/:a.eval=" + a.eval());
                        log.info("Expression:parseTerm:/:b.eval=" + b.eval());
                        log.info("Expression:parseTerm:/:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "/ b.eval()=" + b.eval());
                        int temp = ch;
                        nextChar();
                        if (((ch == '+') || (ch == '-')) && (temp != '*') && (temp != '/')) {
                            pos--;
                            ch = temp;
                            return x;
                        } else if ((ch != -1) && (ch != 0)) {
                            pos--;
                            ch = temp;
                        }
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            TermEndPos = pos;
                        } else {
                            TermEndPos = Str.length();
                        }
                        if ((TermStartPos > TermEndPos)) {
                            TermEndPos = Str.length();
                        }
                        log.info("Expression:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        log.warn("Expression:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        return x;
                    }
                }
            }

            Expression parseFactor() {
                int FactorStartPos = pos;
                int FactorEndPos = -1;
                if (pos < 0) {
                    FactorStartPos = 0;
                }
                Expression x = parseBase();
                for (;;) {
                    if (eat('^')) { // exponentiation
                        //nextChar();
                        Expression a = x, b = parseBase();
                        x = (() -> Math.pow(a.eval(), b.eval()));
                        log.info("Expression:parseFactor:x.eval=" + x.eval() + " Math.pow(a.eval()=" + a.eval() + "^ b.eval()=" + b.eval() + ")");
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            FactorEndPos = pos;
                        } else {
                            FactorEndPos = Str.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = Str.length();
                        }
                        log.info("Expression:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                        log.info("Expression:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        log.warn("Expression:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        return x;
                    }
                }
            }

            Expression parseBase() {
                int startPos = pos;
                Expression x = (() -> 0.0);
                if (pos >= str.length()) {
                    pos = -1;
                    ch = -1;
                    return x;
                }
                if ((eat('-')) && (startPos != 0)) { // unary minus
                    Expression b = parseBase();
                    x = (() -> (-1.0) * b.eval());
                    log.info("Expression:parseBase:-:Return Value:x.eval=" + x.eval() + " startPos=" + startPos);
                    return x;
                } else if (eat('+')) { // unary plus
                    x = parseBase();
                    log.info("Expression:parseBase:+:Return Value:x.eval=" + x.eval());
                    return x;
                }

                if (eat('(')) { //parentheses
                    //Code to handle Expressions:
                    int POpos = pos;
                    do {
                        pos--;
                    } while ((pos >= 0) && (pos < str.length()) && (!IsOperator(str.charAt(pos))));
                    if ((pos >= 0) && (pos < str.length())) {
                        ParenthesisOperator = str.charAt(pos);
                    } else {
                        ParenthesisOperator = '+';
                    }
                    pos = POpos;
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    x = parseExpression();
                    eat(')');
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    log.info("Expression:parseBase:(:Return Value:x.eval=" + x.eval());
                    return x;
                } else if (IsNumber(ch)) { //numbers
                    while (IsNumber(ch)) {
                        nextChar();
                        if ((ch == 'E') || (ch == 'e')) {
                            nextChar();
                            if ((ch == '-') || (ch == '+')) {
                                nextChar();
                            }
                        }
                    }
                    double xx = 1.0;
                    if ((Str != null) && (!Str.equalsIgnoreCase("")) && (!Str.equalsIgnoreCase("NaN")) && (pos <= Str.length()) && (startPos <= (Str.length() - 1))) {
                        log.info("Expression:parseBase: Number=" + Str.substring(startPos, pos));
                        xx = Double.parseDouble(Str.substring(startPos, pos));
                    } else {
                        log.info("Expression:parseBase:NaN:Exp Form: Number=" + Str.substring(startPos, pos));
                        //xx=Double.parseDouble(String.format("%.12f"+StrRemoveEDbl(Str.substring(startPos, pos)) ) );
                        xx = Double.NaN;
                        pos = -1;
                        ch = -1;
                    }
                    final Double XX = xx;
                    x = () -> XX;
                    log.info("Expression:parseBase:Number:Return Value:x.eval=" + x.eval());
                    return x;
                } else if (IsVariable(ch)) {//functions & variables
                    while ((IsVariable(ch)) || (IsNumber(ch))) {
                        nextChar();
                    }
                    if ((startPos == 0) && (str.charAt(startPos) == '-')) {
                        startPos++;
                        ZeroTermSignFlag = true;
                    }
                    String name = str.substring(startPos, pos).trim();
                    if (functions.containsKey(name)) {
                        DoubleUnaryOperator func = functions.get(name);
                        Expression arg = parseExpression();
                        x = () -> func.applyAsDouble(arg.eval());
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                            x = () -> variables.get(name);
                        }
                    }
                    log.info("Expression:parseBase: Variable: name=" + name + " x.eval()=" + x.eval() + " startPos=" + startPos);
                    return x;
                } else if (ch == ')') {
                    nextChar();
                    log.info("Expression:parseBase:):Return Value:x.eval=" + x.eval());
                    return x;
                } else {
                    if ((startPos < str.length()) && (startPos < pos)) {
                        throw new RuntimeException("Expression:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (IsNumber(ch))) {
                        throw new RuntimeException("Expression:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (IsOperator(ch))) {
                        throw new RuntimeException("Expression:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0)) {
                        throw new RuntimeException("Expression:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    }
                    log.info("Expression:parseBase:Exception:Return Value:x.eval=" + x.eval());
                    return x;
                }
            }
        }.parse();
    }
//Created by Rajesh Pai
// Implements Predicates(Conditions) for F(x) Expressions

    @FunctionalInterface
    public interface Predicate {

        boolean eval();
    }

    // Taken from stackoverflow.com:Evaluating a math 
    // expression given in string form [closed]
    //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public static Predicate parsePredicate(String str, Map<String, Double> variables) {

        return new Object() {

            void nextStrChar() {
                ++pos;
                ch = (pos < Str.length()) ? Str.charAt(pos) : -1;
                if (pos < (Str.length() - 1)) {
                    chNext = Str.charAt(pos + 1);
                } else {
                    chNext = -1;
                }
            }

            void prevStrChar() {
                --pos;
                ch = (pos < Str.length()) ? Str.charAt(pos) : -1;
                if (pos < (Str.length() - 1)) {
                    chNext = Str.charAt(pos + 1);
                } else {
                    chNext = -1;
                }
            }

            boolean eatStrChar(int CharToEat, int CharToEatNxt) {
                while (ch == ' ') {
                    nextStrChar();
                }
                if ((chNext != '\0') && (ch == CharToEat) && (chNext == CharToEatNxt)) {
                    nextStrChar();
                    return true;
                } else if ((chNext == '\0') && (ch == CharToEat)) {
                    nextStrChar();
                    return true;
                }
                return false;
            }

            String eatStrAll(String Temp, int CharToEat) {
                String MyTempString = "";
                if (Temp.equalsIgnoreCase("")) {
                    Temp = Str;
                }
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

            Predicate parsePredicate() {
                Str = str;
                pos = -1;
                ch = 0;
                chNext = 0;
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                nextStrChar();
                Predicate x = parseExpression();
                return x;
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            Predicate parseExpression() {
                Predicate x = parseFactorPredicate();
                for (;;) {
                    log.info("Predicate:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch);
                    if (ch == ')') {
                        nextStrChar();
                        log.info("Predicate:parseExpression:):Return Value:x.eval()=" + x.eval());
                        //return x;
                    } else if (ch == '(') {
                        //nextStrChar();
                        log.info("Predicate:parseExpression:):(:NegateGotFlag=" + NegateGotFlag);
                        ch = chNext;
                    } else if ((ch == '&') && (chNext == '&')) {// Logical AND

                        Predicate a = x, b = parseFactorPredicate();
                        x = (() -> (a.eval() && b.eval()));
                        log.info("Predicate:parseExpression:AND:a.eval()=" + a.eval());
                        log.info("Predicate:parseExpression:AND:b.eval()=" + b.eval());
                        log.info("Predicate:parseExpression:AND:x.eval()=" + x.eval());
                    } else if ((ch == '|') && (chNext == '|')) { // Logical OR

                        Predicate a = x, b = parseFactorPredicate();
                        x = (() -> (a.eval() || b.eval()));
                        log.info("Predicate:parseExpression:OR:a.eval()=" + a.eval());
                        log.info("Predicate:parseExpression:OR:b.eval()=" + b.eval());
                        log.info("Predicate:parseExpression:OR:x.eval()=" + x.eval());
                    } else {
                        log.info("Predicate:parseExpression:Return Value:x.eval()=" + x.eval());
                        return x;
                    }
                }
            }

            Predicate parseFactorPredicate() {
                Predicate x = parseTermPredicate();
                log.info("Predicate:parseFactorPredicate:Initial Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                if ((ch == '!') || (NegateGotFlag == true)) { // exponentiation
                    final boolean Temp = x.eval();
                    x = (() -> (!Temp));
                }
                log.info("Predicate:parseFactorPredicate:Return Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                return x;
            }

            Predicate parseTermPredicate() {
                Double x = parseBasePredicate();
                Predicate y = (() -> false);
                boolean TempInit = true;
                for (;;) {
                    if (ch == ')') {
                        log.info("Predicate:parseTerm:):Initial Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        if (NegateGotFlag == true) {
                            final boolean Temp = y.eval();
                            y = (() -> !Temp);
                        }
                        NegateGotFlag = false;
                        log.info("Predicate:parseTerm:):Return Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        return y;
                    } else if (ch == '(') {
                        //nextStrChar();
                        log.info("Predicate:parseTerm:(:y.eval()=" + y.eval());
                    } else if ((ch == '<') && (chNext == '=')) { // GreaterThanOREqualTo
                        nextStrChar();
                        Double a = x;
                        Double b = parseBasePredicate();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a <= b));
                        } else {
                            y = (() -> (a <= b));
                        }
                        TempInit = false;
                        x = b;
                        log.info("Predicate:parseTerm:<=:a=" + a);
                        log.info("Predicate:parseTerm:<=:b=" + b);
                        log.info("Predicate:parseTerm:<=:y.eval()=" + y.eval());
                    } else if ((ch == '<') && (chNext != '=')) { // GreaterThan
                        ch = chNext;
                        pos++;
                        Double a = x;
                        Double b = parseBasePredicate();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a < b));
                        } else {
                            y = (() -> (a < b));
                        }
                        TempInit = false;
                        x = b;
                        log.info("Predicate:parseTerm:<:a=" + a);
                        log.info("Predicate:parseTerm:<:b=" + b);
                        log.info("Predicate:parseTerm:<:y.eval()=" + y.eval());
                    } else if ((ch == '>') && (chNext == '=')) { // LessThan
                        nextStrChar();
                        Double a = x;
                        Double b = parseBasePredicate();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a >= b));
                        } else {
                            y = (() -> (a >= b));
                        }
                        TempInit = false;
                        x = b;
                        log.info("Predicate:parseTerm:>=:a=" + a);
                        log.info("Predicate:parseTerm:>=:b=" + b);
                        log.info("Predicate:parseTerm:>=:y.eval()=" + y.eval());
                    } else if ((ch == '>') && (chNext != '=')) { // LessThanOREqualTo
                        ch = chNext;
                        pos++;
                        Double a = x;
                        Double b = parseBasePredicate();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a > b));
                        } else {
                            y = (() -> (a > b));
                        }
                        TempInit = false;
                        x = b;
                        log.info("Predicate:parseTerm:>:a=" + a);
                        log.info("Predicate:parseTerm:>:b=" + b);
                        log.info("Predicate:parseTerm:>:y.eval()=" + y.eval());
                    } else if ((ch == '=')) { // EqualTo
                        Double a = x;
                        Double b = parseBasePredicate();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a == b));
                        } else {
                            y = (() -> (a == b));
                        }
                        TempInit = false;
                        x = b;
                        log.info("Predicate:parseTerm:==:a=" + a);
                        log.info("Predicate:parseTerm:==:b=" + b);
                        log.info("Predicate:parseTerm:==:y.eval()=" + y.eval());
                    } else {
                        log.info("Predicate:parseTerm:Return Value:y.eval()=" + y.eval());
                        return y;
                    }
                }
            }

            Double parseBasePredicate() {
                int startPos = pos;
                Double x;
                if (ch == '(') { //parentheses
                    nextStrChar();
                    startPos = pos;
                    log.info("Predicate:parseBasePredicate:(:ch=" + (char) ch + " chNext=" + (char) chNext);
                    if (ch == '!') {
                        NegateGotFlag = true;
                    }
                    x = parseBasePredicate();
                    return x;
                }
                Double xx = 1.0;
                if (IsNumber(ch)) { //numbers
                    while (IsNumber(ch)) {
                        nextStrChar();
                        if ((ch == 'E') || (ch == 'e')) {
                            nextStrChar();
                            if ((ch == '-') || (ch == '+')) {
                                nextStrChar();
                            }
                        }
                    }

                    if ((str != null) && (!str.equalsIgnoreCase("")) && (!str.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(str.substring(startPos, pos));
                    }
                    log.info("Predicate:parseBasePredicate:Number:ch=" + (char) ch + " chNext=" + (char) chNext + " xx=" + xx);
                    return xx;
                }
                if (ch == '=') {
                    //nextStrChar();
                }
                if ((ch == ')') && (pos == (str.length() - 1))) {
                    ch = -1;
                    pos++;
                } else if (ch == ')') {
                    nextChar();
                }
                Double xx2 = 1.0;
                startPos = pos;
                if (IsVariable(ch)) {//functions & variables
                    while ((IsVariable(ch)) || (IsNumber(ch))) {
                        nextStrChar();
                    }
                    String name = str.substring(startPos, pos).trim();
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                        xx2 = variables.get(name);
                    }
                    log.info("Predicate:parseBasePredicate:Variable:ch=" + (char) ch + " chNext=" + (char) chNext + " xx2=" + xx2 + " name=" + name);
                    if (ch == '=') {
                        //nextStrChar();
                    }
                    if ((ch == ')') && (pos == (str.length() - 1))) {
                        ch = -1;
                        pos++;
                    } else if (ch == ')') {
                        nextChar();
                    }
                    return xx2;
                } else if ((IsLogicalOperator(ch)) && (IsLogicalOperator(chNext))) {
                    nextStrChar();
                    nextStrChar();
                    xx = parseBasePredicate();
                    return xx;
                } else if ((IsLogicalOperator(ch))) {
                    nextStrChar();
                    xx = parseBasePredicate();
                    return xx;
                } else {
                    if ((startPos < str.length()) && (startPos < pos)) {
                        throw new RuntimeException("Predicate:parseBasePredicate:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if (ch != -1) {
                        throw new RuntimeException("Predicate:parseBasePredicate:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    }
                    return xx;
                }
            }
        }.parsePredicate();
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
                    chSign = StrSign.charAt(posSign);
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
                x = parseTerm();
                log.info("LookAheadSign:parseSign:Number:x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                ParseLog("LookAheadSign", "parseSign", "BeforeMap", "StrSign", StrSign);
                ParseMap("LookAheadSign:parseSign", x.eval(), startpos, 0, DiffWithRespTo);
                ParseLog("LookAheadSign", "parseSign", "Final", "StrSign", StrSign);
                return x;
            }

            LookAheadSign parseTerm() {
                double Output = 1.0;
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
                    if (posSign == -1) {
                        nextCharSign();
                        nextCharSign();
                    } else {
                        nextCharSign();
                    }
                    ParenthesisOperator = chSign;
                    ParenthesisFlag = true;
                    x = parseBase();
                    if (chSign == ')') {
                        if (posSign == -1) {
                            nextCharSign();
                            nextCharSign();
                        } else {
                            nextCharSign();
                        }
                    }
                    ParenthesisFlag = false;
                    log.info("LookAheadSign:parseBase:(: Expression=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);
                    return x;
                } else if (chSign == ')') {
                    ParenthesisOperator = 0;
                    if (posSign == -1) {
                        nextCharSign();
                        nextCharSign();
                    } else {
                        nextCharSign();
                    }
                    ParenthesisFlag = false;
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
                        log.info("LookAheadSign:parseBase:+:Variable=" + StrSign.substring(startPos, posSign) + " :x.eval()=" + x.eval() + " posSign=" + posSign + " chSign=" + (char) chSign);

                    }
                    return x;
                } else {
                    if ((startPos < StrSign.length()) && (startPos < posSign)) {
                        throw new RuntimeException("LookAheadSign:parseBase:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                    } else if ((chSign != -1) && (chSign != 0) && (IsNumber(chSign))) {
                        throw new RuntimeException("LookAheadSign:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                    } else if ((chSign != -1) && (chSign != 0) && (IsOperator(chSign))) {
                        throw new RuntimeException("LookAheadSign:parseBase:Please Check Equation Operators:Should not reach Here:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                    } else if ((chSign != -1) && (chSign != 0)) {
                        throw new RuntimeException("LookAheadSign:parseBase:Unexpected: " + (char) chSign + " int=" + (int) chSign + " Cannot Format:" + StrSign.substring(posSign, StrSign.length()));
                    }
                    return x;
                }
            }
        }.parseSign();
    }
//Created by Rajesh Pai
// Converts Braces in a Term to a Single Expression

    @FunctionalInterface
    public interface LookAheadBraces {

        String eval();
    }

    public static LookAheadBraces parseBraces(String str, String DiffWithRespTo) {

        return new Object() {
            void nextCharBraces() {
                ++posBraces;
                chBraces = (posBraces < StrBraces.length()) ? StrBraces.charAt(posBraces) : -1;
            }

            boolean eatBraces(int CharToEat) {
                while (chBraces == ' ') {
                    nextCharBraces();
                }
                if (chBraces == CharToEat) {
                    nextCharBraces();
                    return true;
                }
                return false;
            }

            LookAheadBraces parseBraces() {
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                GotBracesVarFlag = false;
                StrBraces = str;
                posBraces = 1;
                if (StrBraces.length() >= 1) {
                    chBraces = StrBraces.charAt(1);
                }
                TermOutput = 0.0;
                TermOperatorPos = -1;
                int StartPos = posBraces;
                log.info("LookAheadBraces:parseBraces:StartPos=" + StartPos + " posBraces=" + posBraces + " chBraces=" + (char) chBraces + " ComplexAssignFlag=" + ComplexAssignFlag);
                ParseLog("LookAheadBraces", "parseBraces", "Initial", "StrBraces", StrBraces);
                LookAheadBraces x = parseExpression();//parseTerm();//parseExpression();
                log.info("LookAheadBraces:parseBraces:Braces:x.eval()=" + x.eval() + " ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag);
                log.info("LookAheadBraces:parseBraces:posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                ParseLog("LookAheadBraces", "parseBraces", "AfterParse", "StrBraces", StrBraces);
                final String MyString = x.eval();
                if ((posBraces < (StrBraces.length() - 1)) && (chBraces != -1) && (chBraces != 0)) {
                    x = (() -> MyString);
                } else if (posBraces < (StrBraces.length() - 1)) {
                    x = (() -> MyString);
                }
                log.info("LookAheadBraces:parseBraces:posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                ParseLog("LookAheadBraces", "parseBraces", "Final", "StrBraces", StrBraces);
                return x;
            }

            LookAheadBraces parseExpression() {
                LookAheadBraces x = (() -> "");
                x = parseTerm();
                for (;;) {
                    TermOutput = 0.0;
                    log.info("LookAheadBraces:parseExpression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " posBraces=" + posBraces + " chBraces=" + (char) chBraces + " x.eval()=" + x.eval() + " TermOperatorPos=" + TermOperatorPos);
                    if ((posBraces >= 1) && (posBraces < StrBraces.length())) {
                        log.info("LookAheadBraces:parseExpression:StrBraces=" + StrBraces.substring(posBraces) + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                    }
                    if (eatBraces(')')) {
                        ParenthesisFlag = false;
                        return x;
                    } else if (((eatBraces('+')) || (TermOperatorPos != -1) || ((ParenthesisFlag == true))) || (posBraces == 0)) {// addition

                        LookAheadBraces a = x, b = parseTerm();
                        if ((TermOutput != 1.0) && (TermOutput != 0.0)) {
                            x = (() -> DiffWithRespTo + "^" + RemoveEDbl(TermOutput));
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.eval() + "+" + b.eval());
                        } else if ((TermOutput == 1.0) && (IsAConstant(b.eval(), DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            x = (() -> a.eval() + "+" + DiffWithRespTo);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            x = (() -> a.eval() + "+" + b.eval());
                        } else if (TermOutput == 0.0) {
                            x = (() -> "1.0");
                        }
                        log.info("LookAheadBraces:parseExpression: TermOutput=" + TermOutput);
                        log.info("LookAheadBraces:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                        if (ParenthesisFlag == false) {
                            return x;
                        }
                    } else if ((eatBraces('-')) || (TermOperatorPos != -1) || ((ParenthesisFlag == true))) { // subtraction

                        LookAheadBraces a = x, b = parseTerm();
                        if ((TermOutput != 1.0) && (TermOutput != 0.0)) {
                            x = (() -> DiffWithRespTo + "^" + RemoveEDbl(TermOutput));
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.eval() + "-" + b.eval());
                        } else if ((TermOutput == 1.0) && (IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            x = (() -> a.eval() + "-" + DiffWithRespTo);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            x = (() -> a.eval() + "-" + b.eval());
                        } else if (TermOutput == 0.0) {
                            x = (() -> "1.0");
                        }
                        log.info("LookAheadBraces:parseExpression:TermOutput=" + TermOutput);
                        log.info("LookAheadBraces:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                        if (ParenthesisFlag == false) {
                            return x;
                        }
                    } else {
                        return x;
                    }
                }
            }

            LookAheadBraces parseTerm() {
                LookAheadBraces x = (() -> "");
                x = parseFactor();
                log.info("LookAheadBraces:parseTerm:x.eval()=" + x.eval());
                for (;;) {
                    if ((eatBraces('*')) || (TermOperatorPos == -1) || ((TermOperatorPos >= 1) && (StrBraces.charAt(TermOperatorPos) == '*'))) { // multiplication
                        double tempTermOutput = TermOutput;
                        LookAheadBraces a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutputSetFlag == false) {
                                if (!b.eval().equalsIgnoreCase("0.0")) {
                                    TermOutput += 1.0;
                                }
                            }
                            if (!b.eval().equalsIgnoreCase("")) {
                                x = (() -> a.eval() + "*" + b.eval());
                            } else {
                                x = (() -> a.eval());
                            }
                            LATermOperatorBraces = '*';
                            log.info("LookAheadBraces:parseTerm:TermOperatorPos:-1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Numbers"))) {
                            x = (() -> a.eval() + "*" + b.eval());
                            log.info("LookAheadBraces:parseTerm:Constant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput += 1.0;
                            }
                            x = (() -> a.eval() + "*" + b.eval());
                            if ((TermOutputSetFlag == true) && (tempTermOutput / TermOutput) <= 0.0) {
                                if (tempTermOutput == 0.0) {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '/') {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '*') {
                                    LATermOperatorBraces = '/';
                                }
                            }
                            log.info("LookAheadBraces:parseTerm:DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
                        } else if (!b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput += 1.0;
                            }
                            if (a.eval().equalsIgnoreCase("")) {
                                x = (() -> (b.eval()));
                            } else {
                                x = (() -> (a.eval() + "*" + b.eval()));
                            }
                            if ((TermOutputSetFlag == true) && (tempTermOutput / TermOutput) <= 0.0) {
                                if (tempTermOutput == 0.0) {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '/') {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '*') {
                                    LATermOperatorBraces = '/';
                                }
                            }
                            log.info("LookAheadBraces:parseTerm:!DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
                        }
                        TermOperatorPos = 0;//Should never Enter again

                        log.info("LookAheadBraces:parseTerm:Multiply:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);

                        if ((chBraces == '+') || (chBraces == '-')) {
                            return x;
                        } else if ((chBraces != -1) && (chBraces != 0)) {

                        } else if ((chBraces == -1) || (chBraces == 0)) {
                            break;
                        }
                    } else if ((eatBraces('/')) || ((TermOperatorPos >= 1) && (StrBraces.charAt(TermOperatorPos) == '/'))) { // division
                        double tempTermOutput = TermOutput;
                        LookAheadBraces a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutputSetFlag == false) {
                                if (!b.eval().equalsIgnoreCase("0.0")) {
                                    TermOutput -= 1.0;
                                }
                            }
                            if (!b.eval().equalsIgnoreCase("")) {
                                x = (() -> a.eval() + "/" + b.eval());
                            } else {
                                x = (() -> a.eval());
                            }
                            LATermOperatorBraces = '/';
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Numbers"))) {
                            if (b.eval().equalsIgnoreCase("0.0")) {
                                x = (() -> b.eval());
                            } else {
                                x = (() -> (a.eval() + "/" + b.eval()));
                            }
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput -= 1.0;
                            }
                            x = (() -> (a.eval() + "/" + b.eval()));
                            if ((TermOutputSetFlag == true) && (tempTermOutput / TermOutput) <= 0.0) {
                                if (tempTermOutput == 0.0) {
                                    LATermOperatorBraces = '/';
                                } else if (LATermOperatorBraces == '/') {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '*') {
                                    LATermOperatorBraces = '/';
                                }
                            }
                        } else if (!b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutput -= 1.0;
                            }
                            if (a.eval().equalsIgnoreCase("")) {
                                x = (() -> (b.eval()));
                            } else {
                                x = (() -> (a.eval() + "/" + b.eval()));
                            }
                            if ((TermOutputSetFlag == true) && (tempTermOutput / TermOutput) <= 0.0) {
                                if (tempTermOutput == 0.0) {
                                    LATermOperatorBraces = '/';
                                } else if (LATermOperatorBraces == '/') {
                                    LATermOperatorBraces = '*';
                                } else if (LATermOperatorBraces == '*') {
                                    LATermOperatorBraces = '/';
                                }
                            }
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        log.info("LookAheadBraces:parseTerm:Divide:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " tempTermOutput=" + tempTermOutput + " TermOutputSetFlag=" + TermOutputSetFlag + " LATermOperatorBraces=" + (char) LATermOperatorBraces);

                        if ((chBraces == '+') || (chBraces == '-')) {
                            return x;
                        } else if ((chBraces != -1) && (chBraces != 0)) {

                        } else if ((chBraces == -1) || (chBraces == 0)) {
                            break;
                        }
                    } else {
                        return x;
                    }
                }
                return x;
            }

            LookAheadBraces parseFactor() {
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                LookAheadBraces x = parseBase();
                log.info("LookAheadBraces:parseFactor:x.eval()=" + x.eval());
                for (;;) {
                    if (eatBraces('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadBraces a = x, b = parseBase();
                        log.info("LookAheadBraces:parseFactor:Exponent:x.eval()=" + x.eval() + " a.eval()=" + a.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                        GotExpntNumFlag = false;
                        if ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            TermOutput += Double.parseDouble(b.eval());
                            if (TermOutput != 0.0) {
                                TermOutputSetFlag = true;
                                x = a;
                                log.info("LookAheadBraces:parseFactor:Exponent:Constant if:x.eval()=" + x.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                            } else {
                                x = (() -> "0.0");
                                log.info("LookAheadBraces:parseFactor:Exponent:Constant else:x.eval()=" + x.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                            }
                        } else if ((IsAConstant(a.eval(), DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("Var"))
                                && (IsAConstant(b.eval(), DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            x = (() -> DiffWithRespTo + "^" + b.eval());
                            log.info("LookAheadBraces:parseFactor:Exponent:IndConstant:x.eval()=" + x.eval() + " a.eval()=" + a.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                        }
                        log.info("LookAheadBraces:parseFactor:Exponent:x.eval()=" + x.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput + " StrIsAConstant=" + StrIsAConstant);
                    } else if ((ParenthesisFlag == true) && (ParenthesisOperator == '^')) {
                        LookAheadBraces a = x, b = parseBase();
                        if ((!b.eval().equalsIgnoreCase("")) || (b.eval().equalsIgnoreCase("0.0"))) {
                            if (IsAConstant(b.eval(), DiffWithRespTo)) {
                                TermOutput += Double.parseDouble(b.eval());
                                if (TermOutput != 0.0) {
                                    TermOutputSetFlag = true;
                                    x = a;
                                } else {
                                    x = (() -> "0.0");
                                }
                            }
                        } else {
                            x = a;
                        }
                        log.info("LookAheadBraces:parseFactor:ParenthesisFlag:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                    } else {
                        return x;
                    }
                }
            }

            LookAheadBraces parseBase() {
                int startPos = posBraces;
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                LookAheadBraces x = (() -> "");
                if (eatBraces('-')) { // unary minus
                    LookAheadBraces b = parseBase();
                    x = (() -> "-" + b.eval());
                    return x;
                } else if (eatBraces('+')) { // unary plus
                    LookAheadBraces b = parseBase();
                    x = (() -> "-" + b.eval());
                    return x;
                }
                if ((IsNumber(chBraces))) { //numbers
                    while ((IsNumber(chBraces)) && (chBraces != -1)) {
                        nextCharBraces();
                        if ((chBraces == 'E') || (chBraces == 'e')) {
                            nextCharBraces();
                            if ((chBraces == '-') || (chBraces == '+')) {
                                nextCharBraces();
                            }
                        }
                    }
                    double xx = 1.0;
                    xx = Double.parseDouble(StrBraces.substring(startPos, posBraces));
                    log.info("LookAheadBraces:parseBase:Number:xx=" + xx + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                    final Double XX = xx;
                    if (XX >= 1.0) {
                        final String MyDBL = RemoveEDbl(XX);
                        x = () -> MyDBL;
                    } else {
                        x = () -> "";
                    }

                    log.info("LookAheadBraces:parseBase:Number:xx=" + xx + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                    return x;
                } else if (IsVariable(chBraces)) {//functions & variables
                    while (((IsVariable(chBraces)) || (IsNumber(chBraces))) && (chBraces != -1)) {
                        nextCharBraces();
                    }
                    String name = "";
                    if (posBraces != -1) {
                        name = StrBraces.substring(startPos, posBraces).trim();
                    } else {
                        name = StrBraces.substring(startPos).trim();
                    }
                    log.info("LookAheadBraces:parseBase:parseBase:name=" + name + " posBraces=" + posBraces + " chBraces=" + (char) chBraces);
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN"))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                    }
                    log.info("LookAheadBraces:parseBase:parseBase:x.eval()=" + x.eval());
                    return x;
                } else if (chBraces == '(') {
                    ParenthesisFlag = true;
                    nextCharBraces();
                    ParenthesisOperator = chBraces;
                    x = parseBase();
                    return x;
                } else if (chBraces == ')') {
                    ParenthesisFlag = false;
                    ParenthesisOperator = 0;
                    nextCharBraces();
                } else {
                    if ((startPos < StrBraces.length()) && (startPos < posBraces)) {
                        throw new RuntimeException("LookAheadBraces:parseBase:Unexpected: " + (char) chBraces + " int=" + (int) chBraces + " Cannot Format:" + StrBraces.substring(posBraces, StrBraces.length()));
                    } else if ((chBraces != -1) && (chBraces != 0) && (IsNumber(chBraces))) {
                        throw new RuntimeException("LookAheadBraces:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chVar + " int=" + (int) chBraces + " Cannot Format:" + StrBraces.substring(posBraces, StrBraces.length()));
                    } else if ((chBraces != -1) && (chBraces != 0) && (IsOperator(chBraces))) {
                        throw new RuntimeException("LookAheadBraces:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chBraces + " int=" + (int) chBraces + " Cannot Format:" + StrBraces.substring(posBraces, StrBraces.length()));
                    } else if ((chBraces != -1) && (chBraces != 0)) {
                        throw new RuntimeException("LookAheadBraces:parseBase:Unexpected: " + (char) chBraces + " int=" + (int) chBraces + " Cannot Format:" + StrBraces.substring(posBraces, StrBraces.length()));
                    }
                    return x;
                }
                return x;
            }
        }.parseBraces();
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

                x = parseExpression();
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
                        if (ParenthesisFlag == false) {
                            TermOutput = 0.0;
                        }
                        OrderVar a = x, b = parseTerm();
                        x = b;
                        log.info("OrderVar:parseExpression:MaxTermOutput=" + MaxTermOutput + " TermOutput=" + TermOutput);
                        log.info("OrderVar:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                    } else if ((eatOrder('-')) || ((NormalNotPareFlag == true) && (ParenthesisFlag == true))) { // subtraction
                        if (ParenthesisFlag == false) {
                            TermOutput = 0.0;
                        }
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
                                TermOutput += Double.parseDouble(b.eval());
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
                    if ((startPos < StrOrder.length()) && (startPos < posOrder)) {
                        throw new RuntimeException("OrderVar:parseBase:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                    } else if ((chOrder != -1) && (chOrder != 0) && (IsNumber(chOrder))) {
                        throw new RuntimeException("OrderVar:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                    } else if ((chOrder != -1) && (chOrder != 0) && (IsOperator(chOrder))) {
                        throw new RuntimeException("OrderVar:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                    } else if ((chOrder != -1) && (chOrder != 0)) {
                        throw new RuntimeException("OrderVar:parseBase:Unexpected: " + (char) chOrder + " int=" + (int) chOrder + " Cannot Format:" + StrOrder.substring(posOrder, StrOrder.length()));
                    }
                    return x;
                }
            }
        }.parseOrder();
    }
//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
//Used only in Differentiation & Integration

    @FunctionalInterface
    public interface LookAheadDiffConst {

        String eval();
    }

    public static LookAheadDiffConst parseLADiff(String str, int StartPos, int DiffLength, String MathsOpType, String DiffWithRespTo) {

        return new Object() {
            void nextCharLADiff() {
                ++posLADiff;
                chLADiff = (posLADiff < StrLADiff.length()) ? StrLADiff.charAt(posLADiff) : -1;
            }

            boolean eatLADiff(int CharToEat) {
                while (chLADiff == ' ') {
                    nextCharLADiff();
                }
                if (chLADiff == CharToEat) {
                    nextCharLADiff();
                    return true;
                }
                return false;
            }

            LookAheadDiffConst parseLADiff() {
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ParenthesisOperator = 0;
                ComplexAssignFlag = false;
                GotVariableFlag = false;
                GotVariablePos = -1;
                StrLADiff = str;
                LastLADiffIndConstAidPos = -1;
                LastLADiffConstCoeffAidPos = -1;
                log.info("LookAheadDiffConst:parseLADiff:StrLADiff=" + StrLADiff + " LADiffTermOperator=" + (char) LADiffTermOperator);
                if (ComplexAssignFlag == true) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    posLADiff = GotVariablePos;
                    chLADiff = StrLADiff.charAt(GotVariablePos);
                } else if ((pos == -1)) {
                    posLADiff = 0;
                    chLADiff = StrLADiff.charAt(0);
                } else {
                    posLADiff = 0;
                    chLADiff = StrLADiff.charAt(0);
                }
                int startpos = posLADiff;
                log.info("LookAheadDiffConst:parseLADiff:StartPos=" + StartPos + " LADiffTermOperator=" + (char) LADiffTermOperator + " startpos=" + startpos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (MathsOpType.equalsIgnoreCase("Differentiation")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Initial", "StrLADiff", StrLADiff);
                } else if (MathsOpType.equalsIgnoreCase("Integration")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Initial", "StrLADiff", StrLADiff);
                }
                LookAheadDiffConst x = parseTerm();
                log.info("LookAheadDiffConst:parseLADiff:Number:x.eval()=" + x.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                if (MathsOpType.equalsIgnoreCase("Differentiation")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "BeforeMap", "StrLADiff", StrLADiff);
                    ParseMap("LookAheadConst:parseLADiff:Diff", StrDiff.length(), 0, DiffLength, DiffWithRespTo);
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Final", "StrDiff", StrDiff);
                } else if (MathsOpType.equalsIgnoreCase("Integration")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "BeforeMap", "StrLADiff", StrLADiff);
                    ParseMap("LookAheadConst:parseLADiff:Integration", StrIntegr.length(), 0, DiffLength, DiffWithRespTo);
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Final", "StrIntegr", StrIntegr);
                }

                return x;
            }

            LookAheadDiffConst parseTerm() {
                double Output = 1.0;
                GotVariableFlag = false;
                GotIndConstFlag = false;
                LookAheadDiffConst x = (() -> "");
                x = parseFactor();
                for (;;) {
                    if (eatLADiff('*')) { // multiplication
                        LookAheadDiffConst a = x, b = parseFactor();
                        log.info("LookAheadDiffConst:parseTerm:b.eval()=" + b.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if (GotVariableFlag == true) {
                            LADiffFactorOperator = '*';
                        } else if (GotIndConstFlag == true) {
                            LADiffIndConstOperator = '*';
                        }
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            x = (() -> "" + (Double.parseDouble(a.eval()) * Double.parseDouble(b.eval())));
                            LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                        } else if (!b.eval().equalsIgnoreCase("1.0")) {
                            x = (() -> a.eval() + "*" + b.eval());
                        }
                        if ((chLADiff == '+') || (chLADiff == '-')) {
                            return x;
                        } else if ((chLADiff != -1) && (chLADiff != 0)) {

                        }
                        log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else if (eatLADiff('/')) { // division
                        LookAheadDiffConst a = x, b = parseFactor();
                        log.info("LookAheadDiffConst:parseTerm:b.eval()=" + b.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if (GotVariableFlag == true) {
                            LADiffFactorOperator = '/';
                        } else if (GotIndConstFlag == true) {
                            LADiffIndConstOperator = '/';
                        }
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            x = (() -> "" + (Double.parseDouble(a.eval()) / Double.parseDouble(b.eval())));
                            LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                        } else if (!b.eval().equalsIgnoreCase("1.0")) {
                            x = (() -> a.eval() + "/" + b.eval());
                        }
                        if ((chLADiff == '+') || (chLADiff == '-')) {
                            return x;
                        } else if ((chLADiff != -1) && (chLADiff != 0)) {

                        }
                        log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {

                        return x;
                    }
                }
            }

            LookAheadDiffConst parseFactor() {
                LookAheadDiffConst x = (() -> "1.0");
                x = parseBase();
                log.info("LookAheadDiffConst:parseFactor:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                for (;;) {
                    if (eatLADiff('^')) { // exponentiation
                        LookAheadDiffConst a = x, b = parseBase();
                        log.info("LookAheadDiffConst:parseFactor:b.eval()=" + b.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            // 2^3
                            x = (() -> "" + (Math.pow(Double.parseDouble(a.eval()), Double.parseDouble(b.eval()))));
                        } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // 2^a
                            x = (() -> a.eval() + "^" + b.eval());
                        } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // 2^x
                            //x = (() -> "1");

                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // a^b
                            x = (() -> a.eval() + "^" + b.eval());
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // x^b
                            //x = (() -> "1");
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // a^x
                            //x = (() -> "1");
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            // a^2
                            TermOutputIndConst += Double.parseDouble(b.eval());
                            x = (() -> a.eval() + "^" + b.eval());
                            TermOutputSetFlag = true;
                        }
                        log.info("LookAheadDiffConst:parseFactor:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {
                        log.info("LookAheadDiffConst:parseFactor:ReturnValue:x.eval()=" + x.eval() + " LADiffTermOperator=" + (char) LADiffTermOperator + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        return x;
                    }
                }
            }

            LookAheadDiffConst parseBase() {
                int startPos = posLADiff;

                LookAheadDiffConst x = (() -> "1.0");
                if (eatLADiff('-')) { // unary minus
                    LookAheadDiffConst b = parseBase();
                    x = (() -> b.eval());
                    return x;
                } else if (eatLADiff('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chLADiff))) { //numbers
                    log.info("LookAheadDiffConst:parseBase:Entered Numbers:posLADiff=" + posLADiff + " startPos=" + startPos + " chLADiff=" + (char) chLADiff);
                    int startPos1 = posLADiff;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while ((IsNumber(chLADiff)) && (chLADiff != -1)) {
                        nextCharLADiff();
                        if ((chLADiff == 'E') || (chLADiff == 'e')) {
                            nextCharLADiff();
                            if ((chLADiff == '-') || (chLADiff == '+')) {
                                nextCharLADiff();
                            }
                        }
                    }
                    double xx = 1.0;
                    if ((posLADiff != -1) && (chLADiff != -1)) {
                        xx = Double.parseDouble(StrLADiff.substring(startPos1, posLADiff));
                    } else {
                        xx = Double.parseDouble(StrLADiff.substring(startPos1));
                    }
                    final Double XX = xx;
                    x = () -> "" + XX;
                    log.info("LookAheadDiffConst:parseBase:Number:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    return x;
                } else if (IsVariable(chLADiff)) {//functions & variables
                    int startPos1 = posLADiff;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while (((IsVariable(chLADiff)) || (IsNumber(chLADiff))) && (chLADiff != -1)) {
                        nextCharLADiff();
                    }
                    String TempVar = StrLADiff.substring(startPos1, posLADiff);
                    if ((!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (!functionsSimple.containsKey(TempVar))) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos1;
                    } else if ((ComplexAssignFlag == false)) {
                        GotVariablePos = startPos1;
                        GotVariableFlag = true;
                        ComplexAssignFlag = true;
                    }
                    if ((TempVar != null) && (!TempVar.equalsIgnoreCase("")) && (!TempVar.equalsIgnoreCase("NaN")) && (!TempVar.equalsIgnoreCase("Infinity")) && (!TempVar.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = TempVar;
                        GotIndConstFlag = true;
                        //x = (() -> MyStr);
                        x = () -> "1.0";
                        log.info("LookAheadDiffConst:parseBase:Variable:TempVar=" + TempVar + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {
                        if ((TempVar.equalsIgnoreCase(DiffWithRespTo)) && (LastLADiffIndConstAidPos == -1)) {
                            LastLADiffIndConstAidPos = startPos1 - 1;
                        }
                        x = () -> "1.0";
                    }
                    log.info("LookAheadDiffConst:parseBase:Variable:x.eval()=" + x.eval() + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag);
                    return x;
                } else if (chLADiff == '(') {
                    nextCharLADiff();
                    ParenthesisOperator = chLADiff;
                    return x;
                } else if (chLADiff == ')') {
                    ParenthesisOperator = 0;
                    nextCharLADiff();
                    return x;
                } else {
                    if ((startPos < StrLADiff.length()) && (startPos < posLADiff)) {
                        throw new RuntimeException("LookAheadConst:parseBase:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0) && (IsNumber(chLADiff))) {
                        throw new RuntimeException("LookAheadConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0) && (IsOperator(chLADiff))) {
                        throw new RuntimeException("LookAheadConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0)) {
                        throw new RuntimeException("LookAheadConst:parseBase:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    }
                    return x;
                }
            }
        }.parseLADiff();
    }

//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
    @FunctionalInterface
    public interface LookAheadConst {

        double eval();
    }

    public static LookAheadConst parseLA(String str, int StartPos, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {
            void nextCharLA() {
                ++posLA;
                chLA = (posLA < StrLA.length()) ? StrLA.charAt(posLA) : -1;
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

            LookAheadConst parseLA() {
                GotBracesVarFlag = false;
                StrLA = str;
                if (ComplexAssignFlag == true) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    StrLA = StrVar;
                    posLA = GotVariablePos;
                    chLA = StrLA.charAt(GotVariablePos);
                } else if ((pos == -1)) {
                    GotVariableFlag = false;
                    GotIndConstFlag = false;
                    posLA = -1;
                    chLA = StrLA.charAt(0);
                    LAPreTermOperator = -1;
                } else {
                    //posTerm=StartPos;
                    posLA = -1;
                    chLA = StrLA.charAt(0);
                }
                int startpos = posLA;
                log.info("LookAheadConst:parseLA:StartPos=" + StartPos + " startpos=" + startpos + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                ParseLog("LookAheadConst", "parseLA", "Initial", "StrLA", StrLA);
                LookAheadConst x = parseTerm();

                log.info("LookAheadConst:parseLA:Overall Number:x.eval()=" + x.eval() + " LAPreTermOperator=" + (char) LAPreTermOperator + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                ParseLog("LookAheadConst", "parseLA", "BeforeMap", "StrLA", StrLA);
                ParseMap("LookAheadConst:parseLA", StrLA.length(), 0, 0, DiffWithRespTo);
                ParseLog("LookAheadConst", "parseLA", "Final", "StrLA", StrLA);

//                if ((posLA != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(posLA, str.length()).equalsIgnoreCase(""))&&(posLA <= str.length())) {
//                    throw new RuntimeException("LookAheadConst:parse:Unexpected: " + (char) chLA + " int=" + (int) chLA + " posLA=" + posLA + " str=" + str.substring(posLA, str.length()));
//                }
                return x;
            }

            LookAheadConst parseTerm() {
                double Output = 1.0;
                int StartPos = posLA;
                boolean InitialTermOp = true;
                boolean InitialHighLvlMapOp = false;
                boolean InitialHighLvlDyDxMapOp = false;
                double HighLvlMapOutput = 1.0;
                LookAheadConst x = (() -> 1.0);
                x = parseFactor();
                log.info("LookAheadConst:parseTerm:Initial:0:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                if ((InitialTermOp == true) && (StartPos >= 1) && (StrLA.charAt(StartPos - 1) == '/')) {
                    HighLvlMapOutput = x.eval();
                    final Double Temp = x.eval();
                    x = (() -> 1.0 / Temp);
                    InitialTermOp = false;
                    InitialHighLvlMapOp = true;
                    InitialHighLvlDyDxMapOp = true;
                }
                log.info("LookAheadConst:parseTerm:Initial:1:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                for (;;) {
                    GotIndConstFlag = false;
                    GotVariableFlag = false;
                    if (eatLA('*') || (chLA == ')')) { // multiplication

                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        log.info("LookAheadConst:parseTerm:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (GotVariableFlag == true) {
                            LATermOperator = '*';
                            GotVariableFlag = false;
                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> (a.eval() * b.eval()));
                            InitialHighLvlMapOp = false;
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((chLA == '+') || (chLA == '-')) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutput=" + HighLvlMapOutput + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if (chLA == ')') {
                            nextCharLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {
                        }
                        log.info("LookAheadConst:parseTerm:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else if (eatLA('/') || (chLA == ')')) { // division
                        if (chLA == ')') {
                            nextCharLA();
                            continue;
                        }
                        LookAheadConst a = x, b = parseFactor();
                        log.info("LookAheadConst:parseTerm:a.eval()=" + a.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        log.info("LookAheadConst:parseTerm:b.eval()=" + b.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        String Temp = "" + b.eval();
                        if (GotVariableFlag == true) {
                            LATermOperator = '/';
                            GotVariableFlag = false;

                        } else if (GotIndConstFlag == true) {
                            GotIndConstFlag = false;
                        } else if ((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) {
                            x = (() -> a.eval() / b.eval());
                            InitialHighLvlMapOp = false;
                        }
                        if (((IsAConstant("" + b.eval(), DiffWithRespTo)) || (Temp.contains("E-")) || (Temp.contains("E+"))) && (timesLoop == 0) && (LATermOperator == -1)) {
                            if ((chLA == '*') || (chLA == '/')) {
                                LAPreTermOperator = chLA;
                            }
                        }
                        log.info("LookAheadConst:parseTerm:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((chLA == '+') || (chLA == '-')) {
                            if ((posLA < (StrLA.length() - 1)) && (!IsNumber(StrLA.charAt(posLA + 1)))) {
                                log.info("LookAheadConst:parseTerm:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutput=" + HighLvlMapOutput + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                                    final Double Temp1 = HighLvlMapOutput;
                                    x = (() -> Temp1);
                                } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false) && (IsAConstant("" + b.eval(), DiffWithRespTo))) {
                                    final Double Temp1 = 1.0 / x.eval();
                                    x = (() -> Temp1);
                                }
                                log.info("LookAheadConst:parseTerm:Final:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutput=" + HighLvlMapOutput + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                                return x;
                            }
                        } else if (chLA == ')') {
                            nextCharLA();
                            continue;
                        } else if ((chLA != -1) && (chLA != 0)) {

                        }
                        log.info("LookAheadConst:parseTerm:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " GotVariableFlag=" + GotVariableFlag + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                    } else {
                        log.info("LookAheadConst:parseTerm:Pre-Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutput=" + HighLvlMapOutput + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if ((InitialHighLvlMapOp == true) && (x.eval() == (1.0 / HighLvlMapOutput))) {
                            final Double Temp1 = HighLvlMapOutput;
                            x = (() -> Temp1);
                        } else if ((InitialHighLvlDyDxMapOp == true) && (InitialHighLvlMapOp == false)) {
                            final Double Temp1 = 1.0 / x.eval();
                            x = (() -> Temp1);
                        }
                        log.info("LookAheadConst:parseTerm:Final:x.eval()=" + x.eval() + " InitialHighLvlMapOp=" + InitialHighLvlMapOp + " InitialHighLvlDyDxMapOp=" + InitialHighLvlDyDxMapOp + " HighLvlMapOutput=" + HighLvlMapOutput + " LATermOperator=" + (char) LATermOperator + " LAPreTermOperator=" + (char) LAPreTermOperator + " posLA=" + posLA + " chLA=" + (char) chLA);
                        return x;
                    }
                }
            }

            LookAheadConst parseFactor() {
                LookAheadConst x = (() -> 1.0);
                GotIndConstFlag = false;
                GotVariableFlag = false;
                x = parseBase();
                log.info("LookAheadConst:parseFactor:x.eval()=" + x.eval() + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " posLA=" + posLA + " chLA=" + (char) chLA);
                for (;;) {
                    if (eatLA('^')) { // exponentiation
                        LookAheadConst a = x, b = parseBase();
                        log.info("LookAheadConst:parseFactor:b.eval()=" + b.eval() + " posLA=" + posLA + " chLA=" + (char) chLA);
                        if (GotVariableFlag == true) 
                            ; else if (GotIndConstFlag == true)
                            ; else {
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
                    } else {
                        xx = Double.parseDouble(StrLA.substring(startPos1));
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
                    if ((!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (variables.containsKey(TempVar))) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos1;
                    } else if (ComplexAssignFlag == false) {
                        GotVariablePos = startPos1;
                        if ((StrLA.charAt(startPos1 - 1) == '+') || (StrLA.charAt(startPos1 - 1) == '-')) {
                            TermOperatorPos = startPos1 - 2;
                        } else {
                            TermOperatorPos = startPos1 - 1;
                        }
                        ComplexAssignFlag = true;
                    }
                    if (TempVar.equalsIgnoreCase(DiffWithRespTo)) {
                        GotVariableFlag = true;
                    }
                    x = () -> 1.0;
                    log.info("LookAheadConst:parseBase:Variable:x.eval()=" + x.eval() + " posLA=" + posLA + " chLA=" + (char) chLA + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    return x;
                } else if (chLA == '(') {
                    nextCharLA();
                    x = parseBase();
                    return x;
                } else if (chLA == ')') {
                    nextCharLA();
                    return x;
                } else {
                    if ((startPos < StrLA.length()) && (startPos < posLA)) {
                        throw new RuntimeException("LookAheadConst:parseBase:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA, StrLA.length()));
                    } else if ((chLA != -1) && (chLA != 0) && (IsNumber(chLA))) {
                        throw new RuntimeException("LookAheadConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA, StrLA.length()));
                    } else if ((chLA != -1) && (chLA != 0) && (IsOperator(chLA)) && (posLA >= 0) && (posLA < StrLA.length())) {
                        throw new RuntimeException("LookAheadConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA));
                    } else if ((chLA != -1) && (chLA != 0) && (posLA >= 0) && (posLA < StrLA.length())) {
                        throw new RuntimeException("LookAheadConst:parseBase:Unexpected: " + (char) chLA + " int=" + (int) chLA + " Cannot Format:" + StrLA.substring(posLA));
                    }
                    return x;
                }
            }
        }.parseLA();
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

            LookAheadVar parseVar() {
                GotBracesVarFlag = false;
                StrVar = str;
                FunctionStr = "";
                LAVarTermOperator = -1;
                LATermOperator = -1;
                LAVarSimpleMapFlag=false;
                LAVarComplexMapFlag=false;
                ParseLog("LookAheadVar", "parseVar", "Initial0", "StrVar", StrVar);
                if (ComplexAssignFlag == true) {
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    StrVar = StrLA;
                    posVar = GotVariablePos;
                    chVar = StrVar.charAt(GotVariablePos);
                    //TermOperatorPos = GotVariablePos - 1;
                    TermOutput = 0.0;
                } else {
                    posVar = 0;
                    chVar = StrVar.charAt(0);
                    TermOperatorPos = -1;
                    TermOutput = 0.0;
                }
                int StartPos = posVar;
                log.info("LookAheadVar:parseVar:StartPos=" + StartPos + " posVar=" + posVar + " chVar=" + (char) chVar + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (TermOperatorPos != -1) {
                    log.info("LookAheadVar:parseVar:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrVar.charAt(TermOperatorPos) + " chVar=" + (char) chVar);
                }
                ParseLog("LookAheadVar", "parseVar", "Initial", "StrVar", StrVar);
                LookAheadVar x = parseTerm();
                if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                    ParseLog("LookAheadVar", "parseVar", "BeforeMap", "StrVar", StrVar);
                    ParseMap("LookAheadVar:parseVar", StrVar.length(), 0, 0, DiffWithRespTo);
                }
                log.info("LookAheadVar:parseVar:Variable:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                if (x.eval().contains("(")) {
                    FunctionStr = x.eval();//Do Nothing as eval already is finished with function & Argument
                }
                log.info("LookAheadVar:parseVar:Initial :LATermOperator=" + (char) LATermOperator);
                log.info("LookAheadVar:parseVar:Initial :LATermOperatorBraces=" + (char) LATermOperatorBraces);
                log.info("LookAheadVar:parseVar:Initial :LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
                log.info("LookAheadVar:parseVar:Initial :LAIndConstOperator=" + (char) LAIndConstOperator);
                log.info("LookAheadVar:parseVar:Initial :LAVarTermOperator=" + (char) LAVarTermOperator);
                log.info("LookAheadVar:parseVar:Initial :LAPreTermOperator=" + (char) LAPreTermOperator);
                log.info("LookAheadVar:parseVar:Initial :LAMAPOperator=" + (char) LAMAPOperator);
                log.info("LookAheadVar:parseVar:Initial :LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                log.info("LookAheadVar:parseVar:Initial :LAVarSimpleMapFlag=" +  LAVarSimpleMapFlag);
                log.info("LookAheadVar:parseVar:Initial :LAVarComplexMapFlag=" + LAVarComplexMapFlag);

                if ((x.eval().equalsIgnoreCase("Infinity")) || (x.eval().equalsIgnoreCase("NaN"))) {
                    ;//Do Nothing as e.eval() is already mapped 
                } else if (TermOutput > 1.0) {
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } //FormSimpleTermParse:VarConstantRules:Mapping : No negative Exponent
                else if ((TermOutput == -1.0) && ((LATermOperator == -1)) && (LAVarTermOperator == -1) && (LAMAPOperator == -1)) {
                    //Mapping /x to OR *x^-1 to *x OR /x  : No negative Exponent
                    x = (() -> "1.0" + "/" + DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if ((TermOutput == -1.0) &&(LAVarSimpleMapFlag==true) && ((LATermOperator == '*') || (LADyYByDxMAPOperator == -1) || (LATermOperator == -1)) && (LAMAPOperator == -1)) {
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
                else if ((TermOutput == -1.0) &&(LAVarComplexMapFlag==true)&& (LATermOperator == '/') && (LAMAPOperator == -1)) {
                    //Mapping /x to *x OR x^-1 to *x
                    LAMAPOperator = '/';
                    LADyYByDxMAPOperator = '/';
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) &&(LAVarComplexMapFlag==true)&& (LAMAPOperator == 0) && (LADyYByDxMAPOperator != -1)) {
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
                } else if ((TermOutput == -1.0) &&(LAVarSimpleMapFlag==true)&& (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == -1) &&(LATermOperator == -1)) {
                    //Mapping of x^-1 term alone (with No Operand)
                    x = (() -> 1.0 + "/" + DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4B:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) && (LADyYByDxMAPOperator == -1) && (LAPreTermOperator != '/')) {
                    x = (() -> DiffWithRespTo);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule4C:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
                } else if ((TermOutput == -1.0) &&(LAVarComplexMapFlag==true)&& (LADyYByDxMAPOperator == -1) && (LAPreTermOperator == '/')&&(LATermOperator==-1) ) {
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
                    x = (() -> "1");
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
                } else if (TermOutput < -1.0) {
                    x = (() -> DiffWithRespTo + "^" + TermOutput);
                    log.info("LookAheadVar:parseVar:Variable Mapping:Rule9:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LATermOperator=" + (char) LATermOperator + " LAVarTermOperator=" + (char) LAVarTermOperator);
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
                log.info("LookAheadVar:parseVar:Final :LAVarSimpleMapFlag=" +  LAVarSimpleMapFlag);
                log.info("LookAheadVar:parseVar:Final :LAVarComplexMapFlag=" + LAVarComplexMapFlag);    
                if ((!FunctionStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1")) && (!x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    FunctionStr = FunctionStr + "*" + x.eval();
                } else if ((!FunctionStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1")) && (x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    FunctionStr = FunctionStr;
                } else if ((!FunctionStr.equalsIgnoreCase("")) && (x.eval().equalsIgnoreCase("1"))) {
                    FunctionStr = FunctionStr;
                } else {
                    FunctionStr = x.eval();
                }
                final String MyString = FunctionStr;
                if ((posVar < (StrVar.length() - 1)) && (chVar != -1) && (chVar != 0)) {
                    x = (() -> MyString);
                } else if (posVar < (StrVar.length() - 1)) {
                    x = (() -> MyString);
                }
                ParseLog("LookAheadVar", "parseVar", "Final", "StrVar", StrVar);
//                if ((posVar != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(posVar, str.length()).equalsIgnoreCase(""))&&(posVar <= str.length())) {
//                    throw new RuntimeException("LookAheadVar:parse:Unexpected: " + (char) chVar + " int=" + (int) chVar + " posVar=" + posVar + " str=" + str.substring(posVar, str.length()));
//                }
                return x;
            }

            LookAheadVar parseTerm() {
                LookAheadVar x = (() -> "");
                for (;;) {
                    if ((eatVar('*') || (TermOperatorPos == -1) || (StrVar.charAt(TermOperatorPos) == '*'))) { // multiplication
                        LookAheadVar a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutput == -1.0) {
                                LAVarSimpleMapFlag=true;
                            }
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                if ((!b.eval().contains("("))
                                        && ((posVar < StrVar.length())
                                        && ((StrVar.charAt(posVar + 1) == '*')
                                        || (StrVar.charAt(posVar + 1) == '/')))) {
                                    TermOutput += 1.0;
                                    TermOutputSetFlag = true;
                                }
                            }

                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } // Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo))&&((b.eval().equalsIgnoreCase("-1.0"))||(b.eval().equalsIgnoreCase("1.0"))) ) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput += 1.0;
                            }
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput += 1.0;
                            }
                            x = (() -> (a.eval() + "*" + b.eval()));
                            log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
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
                        }
                    } else if ((eatVar('/') || (StrVar.charAt(TermOperatorPos) == '/'))) { // division
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

                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule1:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        }// Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo))&&((b.eval().equalsIgnoreCase("-1.0"))||(b.eval().equalsIgnoreCase("1.0"))) ) {
                            x = (() -> a.eval());
                            LAVarComplexMapFlag=true;
                            log.info("LookAheadVar:parseTerm:Divide:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule3:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutput == 1.0) {
                                TermOutput = -1.0;
                                LAVarSimpleMapFlag=true;
                            } else if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput -= 1.0;
                            }
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule4:DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if ((TermOutputSetFlag == false) || ((posVar < StrVar.length()) && ((StrVar.charAt(posVar + 1) == '*') || (StrVar.charAt(posVar + 1) == '/')))) {
                                TermOutput -= 1.0;
                            }
                            x = (() -> (a.eval() + "/" + b.eval()));
                            log.info("LookAheadVar:parseTerm:Divide:Rule5:!DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVar=" + (char) chVar);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {

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
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadVar a = x, b = parseBase();
                        log.info("LookAheadVar:parseFactor:Exponent:a.eval()=" + a.eval() + " TermOutput=" + TermOutput);
                        log.info("LookAheadVar:parseFactor:Exponent:b.eval()=" + b.eval() + " TermOutput=" + TermOutput);
                        if (!b.eval().equalsIgnoreCase("")) {
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // 2^3
                                x = (() -> "1");
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // 2^a
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // 2^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // a^b
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // a^2
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("0.0"))) {
                                //x^2
                                TermOutput += Double.parseDouble(b.eval());
                                TermOutputSetFlag = true;
                                x = b;
                                // Needs to be mapped set
                                if (TermOutput == -1.0) {
                                    LAMAPOperator = 0;
                                }
                            } else {
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                            }

                        } else {
                            x = a;
                        }
                        log.info("LookAheadVar:parseFactor:Exponent:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator);
                    } else {
                        if ((GotExpntNumFlag == false)
                                && (x.eval().equalsIgnoreCase(DiffWithRespTo))
                                && (TermOutput == 0.0)
                                && ((posVar < StrVar.length())
                                && ((StrVar.charAt(posVar) == '*')
                                || (StrVar.charAt(posVar) == '/')))) {
                            TermOutput = 1.0;
                            TermOutputSetFlag = true;
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
                    x = parseBase();
                    return x;
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
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (timesLoop == 0) && (posVar != -1) && (StrVar != null) && (!StrVar.equalsIgnoreCase("")) && (!StrVar.equalsIgnoreCase("NaN"))) {
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
                        xx = 0.0;
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
                        if ((startPos >= 1) && ((StrVar.charAt(startPos - 1) == '*') || (StrVar.charAt(startPos - 1) == '/'))) {
                            LAVarTermOperator = StrVar.charAt(startPos - 1);
                        } else if ((startPos >= 2) && ((StrVar.charAt(startPos - 1) == '+') || (StrVar.charAt(startPos - 1) == '-'))) {
                            LAVarTermOperator = StrVar.charAt(startPos - 2);
                        }
                    } else {
                        name = StrVar.substring(startPos).trim();
                    }

                    log.info("LookAheadVar:parseBase:Variable:name=" + name + " posVar=" + posVar + " chVar=" + (char) chVar);

                    if (functionsSimple.containsKey(name)) {
                        LookAheadVar arg = (() -> "");
                        log.info("LookAheadVar:parseBase:Function:name=" + name + " posVar=" + posVar + " chVar=" + (char) chVar);
                        nextCharVar();
                        arg = parseBase();
                        nextCharVar();
                        final String MyFnStr = name;
                        final String MyStr = arg.eval();
                        log.info("LookAheadVar:parseBase:Function:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        x = (() -> MyFnStr + "(" + MyStr + ")");
                        log.info("LookAheadVar:parseBase:Complete Function String:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVar=" + posVar + " chVar=" + (char) chVar);
                        GotBracesVarFlag = true;
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (name.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                        log.info("LookAheadVar:parseBase:Variable:DiffWithRespTo:name=" + name + " x.eval()=" + x.eval());
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity"))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                        log.info("LookAheadVar:parseBase:Variable:NaN:name=" + name + " x.eval()=" + x.eval());
                    } else if (!name.equalsIgnoreCase(DiffWithRespTo)) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos;
                        x = (() -> "");
                        log.info("LookAheadVar:parseBase:Variable:Independent Constant:name=" + name + " x.eval()=" + x.eval() + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    }
                    return x;
                } else if (chVar == '(') {
                    ParenthesisFlag = true;
                    nextCharVar();
                    ParenthesisOperator = chVar;
                    return x;
                } else if (chVar == ')') {
                    ParenthesisOperator = 0;
                    ParenthesisFlag = false;
                    nextCharVar();
                    return x;
                } else {
                    if ((startPos < StrVar.length()) && (startPos < posVar)) {
                        throw new RuntimeException("LookAheadVar:parseBase:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                    } else if ((chVar != -1) && (chVar != 0) && (IsNumber(chVar))) {
                        throw new RuntimeException("LookAheadVar:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                    } else if ((chVar != -1) && (chVar != 0) && (IsOperator(chVar))) {
                        throw new RuntimeException("LookAheadVar:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
                    } else if ((chVar != -1) && (chVar != 0)) {
                        throw new RuntimeException("LookAheadVar:parseBase:Unexpected: " + (char) chVar + " int=" + (int) chVar + " Cannot Format:" + StrVar.substring(posVar, StrVar.length()));
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

            LookAheadIndConst parseIndConst() {
                GotBracesVarFlag = false;
                StrIndConst = str;
                String CompleteStr = "";
                LAIndConstOperator = '*';
                if (ContainsIndConstFlag == true) {
                    ContainsIndConstFlag = false;
                    GotIndConstFlag = false;
                    StrIndConst = StrLA;
                    posIndConst = GotIndConstPos;
                    chIndConst = StrIndConst.charAt(GotIndConstPos);
                    TermOperatorPos = GotIndConstPos - 1;
                    TermOutputIndConst = 0.0;
                    TempIndConstBase = "";
                } else {
                    posIndConst = 0;
                    chIndConst = StrIndConst.charAt(0);
                    TermOperatorPos = -1;
                    TermOutputIndConst = 0.0;
                    TempIndConstBase = "";
                }
                int StartPos = posIndConst;
                log.info("LookAheadIndConst:parseIndConst:StartPos=" + StartPos + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (TermOperatorPos != -1) {
                    log.info("LookAheadIndConst:parseIndConst:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrIndConst.charAt(TermOperatorPos) + " chIndConst=" + (char) chIndConst);
                }
                ParseLog("LookAheadIndConst", "parseIndConst", "Initial", "StrIndConst", StrIndConst);
                LookAheadIndConst x = parseTerm();
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
                if ((!CompleteStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1"))) {
                    CompleteStr = CompleteStr + "*" + x.eval();
                } else if ((!CompleteStr.equalsIgnoreCase("")) && (x.eval().equalsIgnoreCase("1"))) {
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
//                    throw new RuntimeException("LookAheadVar:parse:Unexpected: " + (char) chVar + " int=" + (int) chVar + " posVar=" + posVar + " str=" + str.substring(posVar, str.length()));
//                }
                ComplexAssignFlag = false;
                return x;
            }

            LookAheadIndConst parseTerm() {
                LookAheadIndConst x = (() -> "");
                TempIndConstStrSet = false;
                //x = parseFactor();
                //log.info("LookAheadVar:parseTerm:x.eval()=" + x.eval());
                for (;;) {
                    if ((eatVar('*') || (TermOperatorPos == -1) || (StrIndConst.charAt(TermOperatorPos) == '*'))) { // multiplication
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
                                }
                                LAPreIndConstOperator = '*';
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

                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> (a.eval() + "*" + b.eval()));

                            } else if ((!a.eval().equalsIgnoreCase("")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> a.eval());

                            } else {
                                x = (() -> b.eval());

                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConst == '*') || (chIndConst == '/')) {
                                    LAIndConstOperator = chIndConst;
                                }
                                LAPreIndConstOperator = '*';
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
                            return x;
                        } else if ((chIndConst != -1) && (chIndConst != 0)) {
                        }
                    } else if ((eatVar('/') || (StrIndConst.charAt(TermOperatorPos) == '/'))) { // division

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
                                }
                                LAPreIndConstOperator = '*';
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
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Divide:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());

                        }
                        TermOperatorPos = 0;//Should never Enter again
                        GotVariablePos = -1;
                        log.info("LookAheadIndConst:parseTerm:Divide:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConst=" + (char) chIndConst);
                        if ((chIndConst == '+') || (chIndConst == '-')) {
                            return x;
                        } else if ((chIndConst != -1) && (chIndConst != 0)) {
                        }
                    } else {
                        return x;
                    }
                }
            }

            LookAheadIndConst parseFactor() {
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                TempIndConstBase = "";
                LookAheadIndConst x = parseBase();
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval());
                if ((!x.eval().equalsIgnoreCase(""))
                        && (!x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    TempIndConstBase = x.eval();
                }
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval() + " TempIndConstBase=" + TempIndConstBase);
                for (;;) {
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadIndConst a = x, b = parseBase();
                        GotExpntNumFlag = false;
                        log.info("LookAheadIndConst:parseFactor:Exponent:b.eval()=" + b.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                        if (!b.eval().equalsIgnoreCase("")) {
                            if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // 2^3
                                x = (() -> "1");
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
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // a^b
                                TermOutputSetFlag = true;
                                x = (() -> a.eval() + "^" + b.eval());
                                TempIndConstStrSet = true;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // a^2
                                TermOutputIndConst += Double.parseDouble(b.eval());
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                            } else {
                                x = (() -> "1");
                                TermOutputSetFlag = true;
                                TempIndConstStrSet = false;
                            }

                        } else {
                            x = a;
                        }
                        log.info("LookAheadIndConst:parseFactor:Exponent:x.eval()=" + x.eval() + " TermOutputIndConst=" + TermOutputIndConst);
                    } else {
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
                    if (XX > 1.0) {
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
                    if (functionsSimple.containsKey(name)) {
                        LookAheadIndConst arg = (() -> "");
                        log.info("LookAheadIndConst:parseBase:Function:name=" + name + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        nextCharIndConst();
                        arg = parseBase();
                        nextCharIndConst();
                        log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + arg.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                        log.info("LookAheadIndConst:parseBase:Complete Function String:x.eval()=" + x.eval() + " posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (!name.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity"))) {
                        x = () -> "1";
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
                } else if (chIndConst == '(') {
                    ParenthesisFlag = true;
                    nextCharIndConst();
                    ParenthesisOperator = chIndConst;
                    return x;
                } else if (chIndConst == ')') {
                    ParenthesisOperator = 0;
                    ParenthesisFlag = false;
                    nextCharIndConst();
                    return x;
                } else {
                    if ((startPos < StrIndConst.length()) && (startPos < posIndConst)) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                    } else if ((chIndConst != -1) && (chIndConst != 0) && (IsNumber(chIndConst))) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                    } else if ((chIndConst != -1) && (chIndConst != 0) && (IsOperator(chIndConst))) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
                    } else if ((chIndConst != -1) && (chIndConst != 0)) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConst + " int=" + (int) chIndConst + " Cannot Format:" + StrIndConst.substring(posIndConst, StrIndConst.length()));
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

    public static SimpleExpression parseSimple(String str, Map<String, Double> variables, Map<String, String> variablesSimple, String DiffWithRespTo) {

        return new Object() {

            SimpleExpression parseSimple() {
                ResetVars("SimpleExpr");
                Str = str;
                pos = -1;
                ch = -1;
                if ((pos >= 0) && (pos < Str.length())) {
                    ch = Str.charAt(pos);
                }
                LookAhead_Stringpos = 0;
                NumCoeff = 0;
                loadCntxtpos = 0;
                loadoppos = 0;
                loadstringpos = 0;

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
                x = parseTermSimple();//parseNormalSimple();
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
                log.info("Simple:parseSimple:x.SimpleExpr()=" + x.SimpleExpr());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
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
                        final String Term1 = a.SimpleExpr();
                        log.info("Simple:parseTermSimple:Begining:str=" + str.substring(pos));

                        if (str.substring(pos).isEmpty()) {
                            return x;
                        }

                        u = parseSimpleTerm(str.substring(pos), variables, DiffWithRespTo);
                        final String Term2 = u.SimpleTerm();
                        log.info("Simple:parseTermSimple:u.SimpleTerm()=" + Term2);
                        if ((Term1.equalsIgnoreCase("")) || (Term1.equalsIgnoreCase("0.0")) || (Term1.equalsIgnoreCase("0")) || (Term1.equalsIgnoreCase("-0.0")) || (Term1.equalsIgnoreCase("-0")) || (Term1.equalsIgnoreCase("+0.0")) || (Term1.equalsIgnoreCase("+0"))) {
                            if ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0"))) {
                                x = (() -> Term2);
                            } else {
                                x = (() -> "");
                            }
                        } else if (((!Term1.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("0.0")) && (!Term1.equalsIgnoreCase("0")) && (!Term1.equalsIgnoreCase("-0.0")) && (!Term1.equalsIgnoreCase("-0")) && (!Term1.equalsIgnoreCase("+0.0")) && (!Term1.equalsIgnoreCase("+0")))
                                && ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0")))) {
                            x = (() -> Term1 + Term2);
                        }
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
            void nextCharTerm() {
                ++posTerm;
                chTerm = (posTerm < StrTerm.length()) ? StrTerm.charAt(posTerm) : -1;
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
                if (Temp.equalsIgnoreCase("")) {
                    Temp = strTerm;
                }
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
                ResetVars("SimpleTerm");
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                SavedOperator = 0;
                ComplexAssignFlag = false;
                TrailConstantFlag = false;
                timesLoop = 0;
                StrTerm = strTerm;
                String OutputExprn = "";
                int startpos = pos;
                SimpleTerm x = (() -> "");
                if (pos < Str.length()) {
                    posTerm = -1;
                    chTerm = strTerm.charAt(0);
                    posVar = -1;
                    chVar = -1;
                    posLA = -1;
                    chLA = -1;
                    GotVariablePos = -1;
                    GotIndConstPos = -1;
                    GotBracesVarFlag = false;
                    TrailConstantFlag = false;
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
                    TrailConstantFlag = false;
                    x = (() -> "");
                    return x;
                }
                ParseLog("SimpleTerm", "parseSimple", "Initial", "StrTerm", StrTerm);
                LookAheadSign y = parseSign(StrTerm, startpos, DiffWithRespTo);

                ParseLog("SimpleTerm", "parseSimple", "Initial", "StrTerm", StrTerm);
                x = parseTermSimple();
                log.info("SimpleTerm:parseSimple:x.SimpleExpr()=" + x.SimpleTerm());
                ParseLog("SimpleTerm", "parseSimple", "BeforeMap", "StrTerm", StrTerm);
                ParseMap("SimpleTerm:parseSimple", StrTerm.length(), 0, 0, DiffWithRespTo);
                ParseLog("SimpleTerm", "parseSimple", "Final", "StrTerm", StrTerm);
                if ((startpos == 0) && (chTermSign == '+') && (!x.SimpleTerm().equalsIgnoreCase(""))) {
                    final String FinalOutput = x.SimpleTerm();
                    x = (() -> FinalOutput);
                } else if (!x.SimpleTerm().equalsIgnoreCase("")) {
                    final String FinalOutput = x.SimpleTerm();
                    if ((chTermSign != -1)&&(chTermSign != 0)) 
                        x = (() -> (char) chTermSign + FinalOutput);
                    else 
                        x = (() -> "+" + FinalOutput);
                } else {
                    x = (() -> "");
                }
                GotVariablePos = -1;
                GotIndConstPos = -1;
                TrailConstantFlag = false;
                GotIndConstFlag = false;
                GotVariableFlag = false;
                log.info("SimpleTerm:parseSimple:With Sign:x.SimpleExpr()=" + x.SimpleTerm());
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
                log.info("SimpleTerm:parseSimple:Final:posTerm=" + posTerm + " startpos=" + startpos + " chTerm=" + (char) chTerm + " ComplexAssignFlag=" + ComplexAssignFlag);
                log.info("SimpleTerm:parseSimple:Final:pos=" + pos + " ch=" + (char) ch);
                log.info("SimpleTerm:parseSimple:x.SimpleExpr()=" + x.SimpleTerm());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("SimpleTerm:Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
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
                x = parseBaseSimple();
                log.info("SimpleTerm:parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                String xExprn = "";
                log.info("SimpleTerm:parseTermSimple:Top:pos=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                log.info("SimpleTerm:parseTermSimple:Initial Call to FactorSimple:xExprn=" + xExprn + " posTerm=" + posTerm);
                log.info("SimpleTerm:parseTermSimple:Initial Call to FactorSimple:OutputExprn=" + "" + " posTerm=" + posTerm);
                for (;;) {
                    log.info("SimpleTerm:parseTermSimple:Top:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                    if (eatTerm('*')) { // multiplication
                        log.info("SimpleTerm:parseTermSimple:posTerm=" + posTerm + " chTerm=" + (char) chTerm + " chTerm=" + chTerm);
                        SimpleTerm a = x, b = parseBaseSimple();
                        if ((!b.SimpleTerm().equalsIgnoreCase("")) && (IsAConstant(b.SimpleTerm(), DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermSimple", "", a, "", b, "", b.SimpleTerm(), "", "*", "ConstantRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple", "", a, "", b, "", b.SimpleTerm(), "", "*", "ConstantRule", DiffWithRespTo).trim();
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop ConstantRule: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:*:Inside Loop ConstantRule: parseTermSimple:xExprn=" + xExprn);
                        } else {
                            x = ApplyFuncRules("parseTermSimple", xExprn, a, "", b, b.SimpleTerm(), "", "", "*", "MappedProductRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple", xExprn, a, "", b, b.SimpleTerm(), "", "", "*", "MappedProductRule", DiffWithRespTo).trim();
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
                        SimpleTerm a = x, b = parseBaseSimple();
                        if ((!b.SimpleTerm().equalsIgnoreCase("")) && (IsAConstant(b.SimpleTerm(), DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermSimple", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "ConstantRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "ConstantRule", DiffWithRespTo).trim();
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop ConstantRule: parseTermSimple:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseTermSimple:/:Inside Loop ConstantRule: parseTermSimple:xExprn=" + xExprn);
                        } else {
                            x = ApplyFuncRules("parseTermSimple", xExprn, a, "", b, b.SimpleTerm(), "", "", "/", "MappedQuotientRule", DiffWithRespTo);
                            xExprn = ApplyFuncRulesString("parseTermSimple", "", a, "", b, b.SimpleTerm(), "", "", "/", "MappedQuotientRule", DiffWithRespTo);
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
                if ((chTerm == '(')) {
                    if (posTerm == -1) {
                        nextCharTerm();
                        nextCharTerm();
                    } else {
                        nextCharTerm();
                    }
                }
                if ((chTerm == '-')) { // unary minus
                    if (posTerm == -1) {
                        nextCharTerm();
                        nextCharTerm();
                    } else {
                        nextCharTerm();
                    }
                    log.info("SimpleTerm:parseBaseSimple:-:chTerm=" + (char) chTerm + " startPos=" + startPos + " posTerm=" + posTerm);
                    SimpleTerm b = parseBaseSimple();
                    if (IsAConstant(b.SimpleTerm(), DiffWithRespTo)) {
                        x = (() -> "" + (Double.parseDouble(b.SimpleTerm())));
                    } else {
                        x = (() -> b.SimpleTerm());
                    }
                    log.info("SimpleTerm:parseBaseSimple:-:startPos=" + startPos + " x.SimpleTerm()=" + x.SimpleTerm());
                    return x;
                } else if ((chTerm == '+')) { // unary plus
                    log.info("SimpleTerm:parseBaseSimple:+:chTerm=" + (char) chTerm + " posTerm=" + posTerm);
                    if (posTerm == -1) {
                        nextCharTerm();
                        nextCharTerm();
                    } else {
                        nextCharTerm();
                    }
                    log.info("SimpleTerm:parseBaseSimple:+:chTerm=" + (char) chTerm + " posTerm=" + posTerm);
                }
                String LastExpr = "";
                timesLoop = 0;
                while ((timesLoop <= 2) && ((ConstantFlag == false) || (VariableFlag == false) || (BracesFlag == false))) {
//                    if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
//                        ParseLog("SimpleTerm", "parseBaseSimple", "Loop", "StrTerm(posTerm)", StrTerm.substring(posTerm));
//                    } else {
//                        ParseLog("SimpleTerm:posTerm:-ve", "parseBaseSimple", "Loop", "StrTerm(posTerm)", StrTerm);
//                    }
                    if (posTerm >= 0) {
                        log.info("SimpleTerm:parseBaseSimple:Loop Begining:StrTerm(posTerm)=" + StrTerm.substring(posTerm) + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                        log.info("SimpleTerm:parseBaseSimple:Loop Begining:chTerm=" + (char) chTerm + " timesLoop=" + timesLoop + " ConstantFlag=" + ConstantFlag + " VariableFlag=" + VariableFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " TrailConstantFlag=" + TrailConstantFlag);
                    }
                    //Numbers -> LookAheadConst
                    if ((((timesLoop >= 0) && (IsNumber(chTerm))
                            || ((timesLoop == 0) && ((posTerm >= -1) && (chTerm == '(') && (IsNumber(StrTerm.charAt(posTerm + 2)))))) && (ConstantFlag == false) && (VariableFlag == false))
                            || (timesLoop == 1) && (IsNumber(chTerm))
                            && ((ComplexAssignFlag == true) && (ConstantFlag == false) && (VariableFlag == true))
                            || ((timesLoop == 1) && (TrailConstantFlag == true)
                            && (ConstantFlag == false) && (VariableFlag == true))
                            || ((timesLoop == 2) && ((posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) != '('))
                            && (IsNumber(posTerm)))) { //numbers
                        //SignNVeFlag=false;
                        //SignPVeFlag=false;
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
//                        if ((TrailConstantFlag == true) && (GotExpntNumFlag == false)) {
//                            TrailConstantOperator = strTerm.charAt(startPos1 - 1);
//                        } else {
//                            TrailConstantOperator = -1;
//                        }
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Number:strTerm=" + strTerm.substring(startPos1) + " startPos1=" + startPos1 + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());

                        log.info("SimpleTerm:parseBaseSimple:Number:xx=" + strTerm.substring(startPos1) + " startPos1=" + startPos1 + " posTerm=" + posTerm);

                        LookAheadConst MyCoeff;
                        //variables.put(DiffWithRespTo, DiffWithRespTo);

                        MyCoeff = parseLA(strTerm.substring(startPos1), startPos1, variables, DiffWithRespTo);
                        String xConst = "" + MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:Number:MyCoeff=" + xConst + " timesLoop=" + timesLoop + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                        final String ConsolidatedConstTerm = xConst;
                        Const = () -> ConsolidatedConstTerm;
                        log.info("SimpleTerm:parseBaseSimple:Number:Constant:Const.SimpleTerm()=" + Const.SimpleTerm() + " LATermOperator=" + (char) LATermOperator + " pos=" + pos + " ch=" + (char) ch + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        ConstantFlag = true;
                        LastExpr = "LookAheadConst";
                    } //Variables -> LookAheadVar
                    else if (((timesLoop >= 0) && (IsVariable(chTerm)) && (VariableFlag == false) && (ContainsIndConstFlag == false))
                            || ((timesLoop == 1) && (IsVariable(chTerm)) && (VariableFlag == false) && (ContainsIndConstFlag == false)
                            && ((ComplexAssignFlag == true) && (ConstantFlag == true)))
                            || ((timesLoop == 1) && (IsVariable(chTerm)) && (VariableFlag == false) && (ConstantFlag == true) && (ContainsIndConstFlag == false)
                            && ((ComplexAssignFlag == false) && (ConstantFlag == true)))
                            || ((timesLoop == 2) && (IsVariable(chTerm) && (VariableFlag == false) && (ConstantFlag == true) && (BracesFlag == true) && (ContainsIndConstFlag == false))
                            && (ComplexAssignFlag == true))) {//functions & variables
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Variable:strTerm=" + strTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());
                        log.info("SimpleTerm:parseBaseSimple:Variable:strTerm=" + strTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());

                        LookAheadVar MyCoeff;
                        //variables.put(DiffWithRespTo, DiffWithRespTo);
                        MyCoeff = parseVar(strTerm.substring(startPos), variables, DiffWithRespTo);
                        String name = MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:Variable:name=" + name + " timesLoop=" + timesLoop + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        if (functionsSimple.containsKey(name)) {
                            NormalExprnFlag = true;
                            log.info("SimpleTerm:parseBaseSimple:ParenthesisFlag=" + ParenthesisFlag + " NormalExprnFlag=" + NormalExprnFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                            log.info("SimpleTerm:parseBaseSimple:Invoking parseFuncPara()");
                            SimpleTerm arg = parseExpressionSimple();
                            log.info("SimpleTerm:parseBaseSimple:Returned from parseFuncPara()");
                            Variable = () -> name + "(" + arg.SimpleTerm() + ")";
                            log.info("SimpleTerm:parseBaseSimple:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chTerm=" + (char) chTerm);
                            log.info("SimpleTerm:parseBaseSimple:Function:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseBaseSimple:Function:arg.SimpleTerm()=" + arg.SimpleTerm());
                        } else {
                            Variable = () -> (name);
                        }
                        log.info("SimpleTerm:parseBaseSimple:Variable:Variable.SimpleTerm()=" + Variable.SimpleTerm() + " LATermOperator=" + (char) LATermOperator + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        VariableFlag = true;
                        LastExpr = "LookAheadVar";
                    } //Independent Constant -> LookAheadIndConst
                    else if (((timesLoop >= 0) && (ContainsIndConstFlag == true) && (IndConstFlag == false))
                            || ((timesLoop == 1) && (ContainsIndConstFlag == true) && (IndConstFlag == false))
                            || ((timesLoop == 2) && (ContainsIndConstFlag == true) && (IndConstFlag == false))) {//Independent Constant variables
                        ContainsIndConstFlag = false;
                        int startPos1 = posTerm;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:IndConst:strTerm=" + strTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());

                        log.info("SimpleTerm:parseBaseSimple:IndConst:strTerm=" + strTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());

                        LookAheadIndConst MyCoeff;
                        //variables.put(DiffWithRespTo, DiffWithRespTo);
                        MyCoeff = parseIndConst(strTerm.substring(startPos), DiffWithRespTo);
                        String name = MyCoeff.eval();
                        log.info("SimpleTerm:parseBaseSimple:IndConst:name=" + name + " timesLoop=" + timesLoop + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        if (functionsSimple.containsKey(name)) {
                            NormalExprnFlag = true;
                            log.info("SimpleTerm:parseBaseSimple:ParenthesisFlag=" + ParenthesisFlag + " NormalExprnFlag=" + NormalExprnFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator);
                            log.info("SimpleTerm:parseBaseSimple:Invoking parseFuncPara()");
                            SimpleTerm arg = parseExpressionSimple();
                            log.info("SimpleTerm:parseBaseSimple:Returned from parseFuncPara()");
                            Variable = () -> name + "(" + arg.SimpleTerm() + ")";
                            log.info("SimpleTerm:parseBaseSimple:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chTerm=" + (char) chTerm);
                            log.info("SimpleTerm:parseBaseSimple:Function:x.SimpleTerm()=" + x.SimpleTerm());
                            log.info("SimpleTerm:parseBaseSimple:Function:arg.SimpleTerm()=" + arg.SimpleTerm());
                        } else {
                            IndConst = () -> (name);
                        }
                        log.info("SimpleTerm:parseBaseSimple:IndConst:IndConst.SimpleTerm()=" + IndConst.SimpleTerm() + " LAIndConstOperator=" + (char) LAIndConstOperator + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                        IndConstFlag = true;

                        LastExpr = "LookAheadIndConst";
                        timesLoop--;
                    } //Braces Expression -> LookAheadBraces
                    else if ((BracesFlag == false)
                            && ((((timesLoop == 0) && ((posLA >= 1) && (posLA < StrLA.length()) && (StrLA.charAt(posLA - 1) == '(')))
                            || ((timesLoop == 0) && ((posLA >= 1) && (posLA < StrLA.length()) && (StrLA.charAt(posLA + 1) == '(')))
                            || ((timesLoop == 1) && ((posLA >= 1) && (posLA < StrLA.length()) && (StrLA.charAt(posLA - 1) == '(')))
                            || ((timesLoop == 2) && ((posLA >= 1) && (posLA < StrLA.length()) && (StrLA.charAt(posLA - 1) == '('))
                            && ((ConstantFlag == true))))
                            || (((timesLoop == 0) && ((posVar >= 1) && (posVar < StrVar.length()) && (StrVar.charAt(posVar - 1) == '(')))
                            || ((timesLoop == 1) && ((posVar >= 1) && (posVar < StrVar.length()) && (StrVar.charAt(posVar - 1) == '(')))
                            || ((timesLoop == 2) && ((posVar >= 1) && (posVar < StrVar.length()) && (StrVar.charAt(posVar - 1) == '('))
                            && (VariableFlag == true)))
                            || ((timesLoop == 0) && ((posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) == '(')))
                            || ((timesLoop == 1) && ((posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) == '(')))
                            || ((timesLoop == 2) && ((posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) == '('))
                            && ((ConstantFlag == true) || (VariableFlag == true))))) {//Braces
                        int posTemp = -1;
                        String StrTemp = "";
                        int chTemp = 0;
                        if ((posLA >= 1) && (posLA < StrLA.length()) && (StrLA.charAt(posLA - 1) == '(')) {
                            posTemp = posLA;
                            StrTemp = StrLA;
                        } else if ((posVar >= 1) && (posVar < StrVar.length()) && (StrVar.charAt(posVar - 1) == '(')) {
                            posTemp = posVar;
                            StrTemp = StrVar;
                        } else if ((posTerm >= 1) && (posTerm < StrTerm.length()) && (StrTerm.charAt(posTerm - 1) == '(')) {
                            posTemp = posTerm;
                            StrTemp = StrTerm;
                        }
                        if (posTemp >= 1) {
                            posTemp--;
                            chTemp = StrTemp.charAt(posTemp);
                        }
                        int startPos1 = posTemp;
                        if (startPos1 >= 0) ; else {
                            startPos1 = 0;
                        }
                        ParenthesisFlag = true;
                        log.info("SimpleTerm:parseBaseSimple:Begining while Loop:Variable:strTerm=" + strTerm.substring(startPos) + " LATermOperatorBraces=" + (char) LATermOperatorBraces + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());
                        log.info("SimpleTerm:parseBaseSimple:Braces:strTerm=" + strTerm.substring(startPos) + " startPos=" + startPos + " posTerm=" + posTerm + " strTerm.length()=" + strTerm.length());
                        Braces = (() -> "0.0");
                        LookAheadBraces MyCoeff;
                        //variables.put(DiffWithRespTo, DiffWithRespTo);
                        MyCoeff = parseBraces(StrTemp.substring(startPos1), DiffWithRespTo);
                        String name = MyCoeff.eval();

                        log.info("SimpleTerm:parseBaseSimple:Braces:name=" + name + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " LookAheadOperator=" + (char) LookAheadOperator + " ComplexAssignFlag=" + ComplexAssignFlag);
                        Braces = () -> (name);
                        log.info("SimpleTerm:parseBaseSimple:Braces:Braces.SimpleTerm()=" + Braces.SimpleTerm() + " posTerm=" + posTerm + " chTerm=" + (char) chTerm + " SavedOperator=" + (char) SavedOperator + " ComplexAssignFlag=" + ComplexAssignFlag);

                        BracesFlag = true;
                        ParenthesisFlag = false;
                        LastInvokedFlag = "LookAheadBraces";
                        LastInvokedPos = posBraces;
                    }
                    timesLoop++;
                }

//                if (((ConstantFlag == true) && (VariableFlag == false)) || ((LastExpr.equalsIgnoreCase("LookAheadConst")) && (timesLoop == 2) && (ConstantFlag == true) && (VariableFlag == true))) {
//                    // Constant Term Alone OR Constant Expression
//                    ParseLog("SimpleTerm", "parseBaseSimple:parseLA:LAAlone", "BeforeMap", "StrVar", StrVar);
//                    ParseMap("LookAheadConst:parseLA:LAAlone", StrLA.length(), 0, 0,DiffWithRespTo);
//                    ParseLog("SimpleTerm", "parseBaseSimple", "Final", "StrVar", StrVar);
//                } else 
                if ((LastExpr.equalsIgnoreCase("LookAheadVar")) && (TrailConstantFlag == false)) {
                    ParseLog("SimpleTerm", "parseBaseSimple:parseVar", "BeforeMap", "StrVar", StrVar);
                    ParseMap("LookAheadVar:parseVar", StrVar.length(), 0, 0, DiffWithRespTo);
                    ParseLog("SimpleTerm", "parseBaseSimple:parseVar", "Final", "StrVar", StrVar);
                    ComplexAssignFlag = false;
                    GotVariableFlag = false;
                    GotExpntNumFlag = false;
                    TrailConstantFlag = false;
                    GotVariablePos = -1;
                } else if ((BracesFlag == true) && ((LastExpr.equalsIgnoreCase("LookAheadBraces")))) {
                    ParseLog("LookAheadBraces", "parseBraces", "BeforeMap", "StrBraces", StrBraces);
                    ParseMap("LookAheadBraces:parseBraces", StrBraces.length(), 0, 0, DiffWithRespTo);
                }

                final String ConstStr = Const.SimpleTerm();
                final String VarStr = Variable.SimpleTerm();
                final String IndConstStr = IndConst.SimpleTerm();
                final String BracesStr = Braces.SimpleTerm();
                final String CompleteString = FormSimpleTermParse("parseBaseSimple", IndConstStr, BracesStr, VarStr, ConstStr, DiffWithRespTo, BracesFlag, VariableFlag, ConstantFlag, IndConstFlag, LATermOperator, LATermOperatorBraces, LAIndConstOperator, LAVarTermOperator);
                x = (() -> CompleteString);
                log.info("SimpleTerm:parseBaseSimple:Formed Complete Term:parseBaseSimple:Normal Return:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                ResetVars("OverallReset");
                if ((posTerm >= 0) && (posTerm < StrTerm.length())) {
                    BaseEndPos = posTerm;
                } else {
                    BaseEndPos = StrTerm.length();
                }
                log.info("SimpleTerm:parseBaseSimple:Original Term=" + StrTerm.substring(BaseStartPos, BaseEndPos));
                if (chTerm == ')') {
                    nextCharTerm();
                    return x;
                } else if ((IsNotAnOperator(chTerm) && (IsNotANumber(chTerm))) && (chTerm != 0) && (chTerm != -1) && (chTerm != '(')) {
                    if ((startPos < Str.length()) && (startPos > posTerm)) {
                        throw new RuntimeException("SimpleTerm::parseBase:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')') && (IsNumber(chTerm))) {
                        throw new RuntimeException("SimpleTerm::parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')') && (IsOperator(chTerm))) {
                        throw new RuntimeException("SimpleTerm::parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
                    } else if ((chTerm != -1) && (chTerm != 0) && (chTerm != ')')) {
                        throw new RuntimeException("SimpleTerm::parseBase:Unexpected: " + (char) chTerm + " int=" + (int) chTerm + " Cannot Format:" + strTerm.substring(posTerm, strTerm.length()));
                    }
                    log.info("SimpleTerm:parseBaseSimple:Return From Inside Loop parseBaseSimple:Junk:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                    return x;
                }
                log.info("SimpleTerm:parseBaseSimple:Return From Inside Loop parseBaseSimple:Valid Return:CompleteString=" + CompleteString + " VarStr=" + VarStr + " ConstStr=" + ConstStr + " posTerm=" + posTerm + " chTerm=" + (char) chTerm);
                return x;
            }
        }.parseSimpleTerm();
    }

    //Created by Rajesh Pai
// Expands (Multiplies) Or Divides Terms with multiplication by Zero: x*0.0=0 OR/And One:x*1.0 = x And/OR addition of Zero: x+0.0=x
//Copyright (c) 2018 by Rajesh V. Pai
    @FunctionalInterface
    public interface SimpleAlgebra {

        String Algebra();
    }

    public static SimpleAlgebra parseAlgebra(String FunctionF, String FunctionG, String Operator, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            SimpleAlgebra parseAlgebra() {
                ResetVars("Algebra");
                Str = FunctionF;
                StrFunF=Str;
                posFunF=0;
                chFunF=FunctionF.charAt(posFunF);
                StrFunG=FunctionG;
                posFunG=0;
                chFunG=FunctionG.charAt(posFunG);
                
                pos = 0;
                ch = chFunF;
                
                LookAhead_Stringpos = 0;
                NumCoeff = 0;
                loadCntxtpos = 0;
                loadoppos = 0;
                loadstringpos = 0;

                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                SavedOperator = 0;
                SimpleAlgebra x = (() -> "");
                if ((Str == null) || (Str.equalsIgnoreCase(""))) {
                    return x;
                }
                if ((ch == 0) || (ch == -1)) {
                    ch = Str.charAt(0);
                    pos = 0;
                }
                
                String OutputExprn = "";
                int startpos = pos;
                x = parseTermSimple();//parseNormalSimple();
                log.fatal("SimpleAlgebra:parseSimple:x.SimpleExpr()=" + x.Algebra());
                if (x.Algebra().contains("--")) {
                    OutputExprn = x.Algebra().replaceAll("--", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Algebra().contains("++")) {
                    OutputExprn = x.Algebra().replaceAll("\\+\\+", "+");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Algebra().contains("+-")) {
                    OutputExprn = x.Algebra().replaceAll("\\+-", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Algebra().startsWith("+")) {
                    OutputExprn = x.Algebra().replaceFirst("\\+", "");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                log.info("SimpleAlgebra:parseSimple:x.Algebra()=" + x.Algebra());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
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
            SimpleAlgebra parseTermSimple() {
                log.fatal("SimpleAlgebra:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                SimpleAlgebra x = (() -> "");
                SimpleAlgebra a = (() -> "");
                SimpleAlgebra b = (() -> "");
                SimpleTerm u = (() -> "");
                SimpleTerm v = (() -> "");
                
                int i=0;
                // Avoid Function F Infinite Loop
                int j=0;
                // Avoid Function G Infinite Loop
                int k=-1;
                int l=0;
                boolean Flag=false;
                for (;;) {
                    if (l==0)
                        ParseMap("SimpleAlgebra:parseSimple:StrFunF", Str.length(), 0, 0, DiffWithRespTo);                   
                    else {
                        posFunF=posFunFLocal;
                        ParseMap("SimpleAlgebra:parseSimple:StrFunF-SteadyState", Str.length(), 0, 0, DiffWithRespTo);
                        if ((pos==0)&&(posFunFLocal==0))
                            break;
                    }
                    v = parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                    final String TermF = v.SimpleTerm();
                    ParseStore("StrFunF");
                    posFunFLocal=posFunF;
                    log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:x.SimpleExpr()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch);
                    log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                    for (;;) {
                        a = x;
                        //Initial First Time
                        if ((i == 0) && (j == 0)) {
                            ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            ParseStore("StrFunG");
                        }else if (j == 0) {
                            ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            ParseStore("StrFunG-Inf");
                            j=1;
                        }else {
                            posFunG=posFunGLocal;
                            log.fatal("Simple:parseTermSimple:StrFunG-Inf-SteadyState:Entered");
                            ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            ParseStore("StrFunG-Inf-SteadyState");
                            k = 0;
                        }
                        u = parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                        final String Term2 = u.SimpleTerm();
                        ParseStore("StrFunG");
                        posFunGLocal=posFunG;
                        if ((TermF.equalsIgnoreCase("")) || (TermF.equalsIgnoreCase("0.0")) || (TermF.equalsIgnoreCase("0")) || (TermF.equalsIgnoreCase("-0.0")) || (TermF.equalsIgnoreCase("-0")) || (TermF.equalsIgnoreCase("+0.0")) || (TermF.equalsIgnoreCase("+0"))) {
                            if ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0"))) {
                                x = (() -> Term2);
                            } else {
                                x = (() -> "");
                            }
                        } else if (((!TermF.equalsIgnoreCase("")) && (!TermF.equalsIgnoreCase("0.0")) && (!TermF.equalsIgnoreCase("0")) && (!TermF.equalsIgnoreCase("-0.0")) && (!TermF.equalsIgnoreCase("-0")) && (!TermF.equalsIgnoreCase("+0.0")) && (!TermF.equalsIgnoreCase("+0")))
                                && ((!Term2.equalsIgnoreCase("0.0")) && (!Term2.equalsIgnoreCase("-0.0")) && (!Term2.equalsIgnoreCase("+0.0")) && (!Term2.equalsIgnoreCase("0")) && (!Term2.equalsIgnoreCase("-0")) && (!Term2.equalsIgnoreCase("+0")))) {
                            final String Temp = a.Algebra();
                            if ((TermF.startsWith("+"))||(TermF.startsWith("-")) )
                                x = (() -> Temp  + TermF + Operator + Term2);
                            else x = (() -> Temp  + "+" + TermF + Operator + Term2);
                        }
                        //Avoid Infinite Loop
                        i = 1;
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:x.SimpleExpr()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch);
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:Term2=" + Term2 + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posFunGLocal=" + posFunGLocal);
                        if (k==0) break;
                    }
                        l=1;
                }
                return x;
                }
        }.parseAlgebra();
    }

    //Created by Rajesh Pai
// Expands (Multiplies) Or Divides Terms with multiplication by Zero: x*0.0=0 OR/And One:x*1.0 = x And/OR addition of Zero: x+0.0=x
//Copyright (c) 2018 by Rajesh V. Pai
    @FunctionalInterface
    public interface FoG {

        String Substitute();
    }

    public static FoG parseFoG(String FunctionF, String FunctionG, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            FoG parseFoG() {
                ResetVars("FoG");
                Str = FunctionF;
                StrFunF=Str;
                posFunF=0;
                chFunF=FunctionF.charAt(posFunF);
                StrFunG=FunctionG;
                posFunG=0;
                chFunG=FunctionG.charAt(posFunG);
                
                pos = -1;
                ch = -1;
                if ((pos >= 0) && (pos < Str.length())) {
                    ch = Str.charAt(pos);
                }
                LookAhead_Stringpos = 0;
                NumCoeff = 0;
                loadCntxtpos = 0;
                loadoppos = 0;
                loadstringpos = 0;

                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                SavedOperator = 0;
                FoG x = (() -> "");
                if ((Str == null) || (Str.equalsIgnoreCase(""))) {
                    return x;
                }
                if ((ch == 0) || (ch == -1)) {
                    ch = Str.charAt(0);
                    pos = 0;
                }
                
                String OutputExprn = "";
                int startpos = pos;
                x = parseTermSimple();//parseNormalSimple();
                log.fatal("FoG:parseSimple:x.Substitute()=" + x.Substitute());
                if (x.Substitute().contains("--")) {
                    OutputExprn = x.Substitute().replaceAll("--", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Substitute().contains("++")) {
                    OutputExprn = x.Substitute().replaceAll("\\+\\+", "+");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Substitute().contains("+-")) {
                    OutputExprn = x.Substitute().replaceAll("\\+-", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.Substitute().startsWith("+")) {
                    OutputExprn = x.Substitute().replaceFirst("\\+", "");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                log.info("FoG:parseSimple:x.Substitute()=" + x.Substitute());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Simple:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
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
            FoG parseTermSimple() {
                log.fatal("FoG:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                FoG x = (() -> "");
                FoG a = (() -> "");
                SimpleTerm u = (() -> "");
                SimpleTerm v = (() -> "");
                
                log.fatal("FoG:parseTermSimple:parseTermSimple:StrFunF=" + StrFunF);
                for (;;) {
                    log.fatal("FoG:parseTermSimple:FunF Loop:pos=" + pos + " ch=" + (char) ch + " ch=" + ch );
                    if ((ch != -1) && (ch != 0)) {
                        log.fatal("FoG:parseTermSimple:Begining:str=" + Str.substring(pos));
                        v = parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                        final String TermF = v.SimpleTerm();
                        log.fatal("FoG:parseTermSimple:u.TermF=" + TermF + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                        a=x;
                        
                          if (((!TermF.equalsIgnoreCase("")) 
                                        && (!TermF.equalsIgnoreCase("0.0")) 
                                        && (!TermF.equalsIgnoreCase("0")) 
                                        && (!TermF.equalsIgnoreCase("-0.0")) 
                                        && (!TermF.equalsIgnoreCase("-0")) 
                                        && (!TermF.equalsIgnoreCase("+0.0")) 
                                        && (!TermF.equalsIgnoreCase("+0")))
                                        ) {
                                    String Temp=TermF.replaceAll(DiffWithRespTo, "(" + StrFunG + ")" );
                                    final String Output=a.Substitute();
                                    x = (() -> Output + Temp);
                                }
                                log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:x.Substitute()=" + x.Substitute() + " pos=" + pos + " ch=" + (char) ch );
                                log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF);
                                log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:StrFunG=" + StrFunG);
                    } else {
                        log.fatal("FoG:parseTermSimple:Return Value:x.Substitute()=" + x.Substitute() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch );
                        return x;
                    }
                }
                }
        }.parseFoG();
    }    
    
//Created by Rajesh Pai
// Simplifies Terms with multiplication by Zero: x*0.0=0 OR/And One:x*1.0 = x And/OR addition of Zero: x+0.0=x
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface DiffExpr {

        String DiffExpr();
    }

    public static DiffExpr parseDiff(String str, Map<String, Double> variables, Map<String, String> variablesDiff, String DiffWithRespTo) {

        return new Object() {

            boolean ParanExprInProgressFlag = false;

            DiffExpr parseDiff() {
                DiffExpr x = (() -> "");
                ResetVars("DiffExpr");
                StrDiff = str;
                InitLookFunc(StrDiff, posDiff, "Diff");
                if ((StrDiff == null) || (StrDiff.equalsIgnoreCase("")) || (StrDiff.contains("NaN"))) {
                    return x;
                }
                posDiff = 0;
                chDiff = StrDiff.charAt(0);
                int oldch = chDiff;
                x = parseExpressionDiff();//parseParaDiff();
                log.info("Diff:parseDiff:Final:x.DiffExpr()=" + x.DiffExpr());
                if (oldch != '-')
                    ; else if (!x.DiffExpr().equalsIgnoreCase("")) {
                    final String FinalOutput = x.DiffExpr();
                    x = (() -> (char) oldch + FinalOutput);
                }
                if (x.DiffExpr().contains("--")) {
                    String OutputExprn = x.DiffExpr().replaceAll("\\--", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.DiffExpr().contains("+-")) {
                    String OutputExprn = x.DiffExpr().replaceAll("\\+-", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                return x;
            }

            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            DiffExpr parseParaParaDiff() {
                DiffExpr x = (() -> "");
                DiffExpr a = (() -> "");
                DiffExpr b = (() -> "");
                String OutputExprn = "";
                String xExprn = "";
                log.info("Diff:parseParaParaDiff:Initial Call to parseParaParaDiff:xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                log.info("Diff:parseParaParaDiff:Initial Call to parseParaParaDiff:OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                for (;;) {
                    log.info("Diff:parseParaParaDiff:Top:Inside Loop:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chDiff=" + (char) chDiff);
                    if (eatDiff('(')) { // ParenthesisOpen
                        NormalExprnFlag = false;
                        ParenthesisCurrNum = ParenthesisHighLvlNum;
                        ParenthesisHighLvlNum++;
                        a = x;
                        final String Term1 = x.DiffExpr();
                        b = parseExpressionDiff();
                        log.info("Diff:parseParaParaDiff:(:Inside Loop:a.DiffExpr()(Term1)=" + Term1 + " chDiff=" + (char) chDiff + " chDiff=" + chDiff + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
                        log.info("Diff:parseParaParaDiff:(:Inside Loop:b.DiffExpr()=" + b.DiffExpr());
                        if ((IsHighLvlNumberExprn(b.DiffExpr(), DiffWithRespTo)) && (SavedOperator != ')') && (SavedOperator != -1) && (SavedOperator != 0)) {
                            final String Term3 = b.DiffExpr();
                            b = (() -> Term3 + (char) SavedOperator);
                        }
                        if (b.DiffExpr().equalsIgnoreCase("0.0")) {
                            b = (() -> "");
                        }
                        if ((SavedOperator == ')') && (ParenthesisFlag == true) && (ParenthesisCurrNum == (ParenthesisHighLvlNum - 1))) {
                            final String Term4 = b.DiffExpr();
                            //Kept for compatibility with parseNormalPara Function
                            if ((chDiff != -1)) {
                                b = (() -> Term4);
                            } else {
                                b = (() -> Term4);
                            }
                            SavedOperator = 0;
                        }
                        final String Term2 = b.DiffExpr();
                        log.info("Diff:parseParaParaDiff:(:Inside Loop:Term1=" + Term1);
                        log.info("Diff:parseParaParaDiff:(:Inside Loop:Term2=" + Term2 + " chDiff=" + (char) chDiff + " ch=Diff" + chDiff + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
                        if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2))
                                && (IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term1 + Term2);
                            xExprn = Term1 + Term2;
                        } else if ((IsNullEmptyZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2)) && (!IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term2);
                            xExprn = Term2;
                        } else if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNullEmptyZero(Term2))) {
                            x = (() -> Term1);
                            xExprn = Term1;
                        } else if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2)) && (!IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term1 + Term2);
                            xExprn = Term1 + Term2;
                        } else if ((IsNullEmptyZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2))) {
                            x = (() -> Term2);
                            xExprn = Term2;
                        }
                        log.info("Diff:parseParaParaDiff:(:Inside Loop parseParaParaDiff:(:b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseParaParaDiff:(:Inside Loop parseParaParaDiff:(:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseParaParaDiff:(:Inside Loop parseParaParaDiff:(:xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        log.info("Diff:parseParaParaDiff:(:Inside Loop parseParaParaDiff:(:OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                    } else if (eatDiff(')')) { // ParenthesisClose
                        NormalExprnFlag = true;
                        if (ParenthesisHighLvlNum == 0) {
                            NormalExprnFlag = true;
                        }
                        log.info("Diff:parseParaParaDiff:):Inside Loop parseParaParaDiff:):ParenthesisNum=" + ParenthesisHighLvlNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " NormalExprnFlag=" + NormalExprnFlag);
                        log.info("Diff:parseParaParaDiff:):Inside Loop parseParaParaDiff:):x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseParaParaDiff:):Inside Loop parseParaParaDiff:):xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        log.info("Diff:parseParaParaDiff:):Inside Loop parseParaParaDiff:):OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        log.info("Diff:parseParaParaDiff:):Return From Inside Loop parseParaParaDiff:x.DiffExpr()=" + x.DiffExpr() + " xExprn=" + xExprn);
                        return x;
                    } else {
                        if (ParenthesisHighLvlNum != ParenthesisCurrNum) {
                            log.info("Diff:parseParaParaDiff:Return From parseParaParaDiff:else:Parenthesis Mismatch:ParenthesisNum=" + ParenthesisHighLvlNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " NormalExprnFlag=" + NormalExprnFlag + " StrDiff=" + StrDiff.substring(posDiff));
                        }
                        log.info("Diff:parseParaParaDiff:else:Return From Inside Loop parseParaParaDiff:else:ParenthesisNum=" + ParenthesisHighLvlNum + " NormalExprnFlag=" + NormalExprnFlag);
                        log.info("Diff:parseParaParaDiff:else:Return From Inside Loop parseParaParaDiff:else:x.DiffExpr()=" + x.DiffExpr() + " xExprn=" + xExprn + " chDiff=" + (char) chDiff);
                        return x;
                    }
                }
            }

            DiffExpr parseExpressionDiff() {
                int ExprStartPos = posDiff;
                int ExprEndPos = -1;
                if ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Null) && (LookAheadOp[loadoppos] == LowLevelOperator.Null)) {
                    loadCntxtpos += 1;
                    loadoppos += 2;
                } else {
                    loadparams(posDiff);
                }
                int startpos = posDiff;
                LADiffTermOperator = -1;
                DiffExpr x = parseTermDiff();
                int endpos = posDiff;
                String xExprn = "";
                String OutputExprn = "";
                NumCoeff = 0;
                if ((startpos != -1) && (endpos != -1) && (startpos < endpos) && (startpos <= StrDiff.length()) && (startpos < StrDiff.length()) && (endpos <= StrDiff.length())) {
                    xExprn = StrDiff.substring(startpos, endpos);
                }
                OutputExprn = x.DiffExpr();
                log.info("Diff:parseExpressionDiff:Initial Call to parseTermDiff:OutputExprn=" + OutputExprn);
                if ((IsAConstant(OutputExprn, DiffWithRespTo)) && (IsAConstant(xExprn, DiffWithRespTo)) && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    log.info("Diff:parseExpressionDiff:Mapping 0.0:Got a Constant Value from parseTermDiff:OutputExprn=" + OutputExprn);
                    OutputExprn = "0.0";
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                } else if ((OutputExprn.equalsIgnoreCase(DiffWithRespTo)) || (OutputExprn.equalsIgnoreCase("1.0")) && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    OutputExprn = "1.0";
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                } else {
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                }
                log.info("Diff:parseExpressionDiff:Initial Call to parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " chDiff=" + (char) chDiff + " chDiff=" + chDiff + " posDiff=" + posDiff);
                for (;;) {
                    NumCoeff = 0;
                    LADiffTermOperator = -1;
                    if ((eatDiff('+')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) {// addition
                        loadCntxtpos += 1;
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Plus) {
                            loadoppos += 2;
                        }
                        log.info("Diff:parseExpressionDiff:+ Entered");
                        int bstartpos = posDiff;
                        DiffExpr a = x, b = parseTermDiff();
                        String bExprn = "";
                        int bendpos = posDiff;
                        if ((bstartpos != -1) && (bendpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length()) && (bendpos <= StrDiff.length())) {
                            bExprn = StrDiff.substring(bstartpos, bendpos);
                        }
                        log.info("Diff:parseExpressionDiff:b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff:bExprn=" + bExprn);
                        if ((IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if (!(IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if ((!b.DiffExpr().equalsIgnoreCase(bExprn)) && ((b.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.DiffExpr().equalsIgnoreCase("1.0"))))
                            ;//Do Nothing as b.DiffExpr() has the value(the Coeff of x)
                        else if ((b.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.DiffExpr().equalsIgnoreCase("1.0"))) {
                            b = (() -> "1.0");
                            bExprn = "1.0";
                        }
                        log.info("Diff:parseExpressionDiff:b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff:bExprn=" + bExprn);
                        log.info("Diff:parseExpressionDiff:xExprn=" + xExprn);
                        if (a.DiffExpr().contains("Parenthesis")) {
                            Expr_Func_Split(a.DiffExpr(), DiffWithRespTo);
                            final String ParaExprA = ParanExpr;
                            final String ParaExprDiffExprA = ParanDiffExpr;
                            a = (() -> ParaExprDiffExprA);
                        }
                        if (b.DiffExpr().contains("Parenthesis")) {
                            Expr_Func_Split(b.DiffExpr(), DiffWithRespTo);
                            final String ParaExprB = ParanExpr;
                            final String ParaExprDiffExprB = ParanDiffExpr;
                            b = (() -> ParaExprDiffExprB);
                        }
                        LADiffTermOperator=-1;
                        x = ApplyFuncRules("parseExpressionDiff", xExprn, a, "", b, "", "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        xExprn = ApplyFuncRulesString("parseExpressionDiff", xExprn, a, "", b, "", "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        log.info("Diff:parseExpressionDiff::a.DiffExpr()=" + a.DiffExpr());
                        log.info("Diff:parseExpressionDiff::b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff::x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseExpressionDiff::xExprn=" + xExprn);
                    } else if ((eatDiff('-')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) { // subtraction
                        loadCntxtpos += 1;
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Minus) {
                            loadoppos += 2;
                        }
                        log.info("Diff:parseExpressionDiff:- Entered");
                        int bstartpos = posDiff;
                        DiffExpr a = x, b = parseTermDiff();
                        int bendpos = posDiff;
                        String bExprn = "";
                        if ((bstartpos != -1) && (bendpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length()) && (bendpos <= StrDiff.length())) {
                            bExprn = StrDiff.substring(bstartpos, bendpos).trim();
                        }
                        log.info("Diff:parseExpressionDiff:b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff:bExprn=" + bExprn);
                        log.info("Diff:parseExpressionDiff:xExprn=" + xExprn);
                        if ((IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if (!(IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if ((!b.DiffExpr().equalsIgnoreCase(bExprn)) && ((b.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.DiffExpr().equalsIgnoreCase("1.0"))))
                            ;//Do Nothing
                        else if ((b.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.DiffExpr().equalsIgnoreCase("1.0"))) {
                            b = (() -> "1.0");
                            bExprn = "1.0";
                        }
                        log.info("Diff:parseExpressionDiff:b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff:bExprn=" + bExprn);
                        log.info("Diff:parseExpressionDiff:xExprn=" + xExprn);
                        if (a.DiffExpr().contains("Parenthesis")) {
                            Expr_Func_Split(a.DiffExpr(), DiffWithRespTo);
                            final String ParaExprA = ParanExpr;
                            final String ParaExprDiffExprA = ParanDiffExpr;
                            a = (() -> ParaExprDiffExprA);
                        }
                        if (b.DiffExpr().contains("Parenthesis")) {
                            Expr_Func_Split(b.DiffExpr(), DiffWithRespTo);
                            final String ParaExprB = ParanExpr;
                            final String ParaExprDiffExprB = ParanDiffExpr;
                            b = (() -> ParaExprDiffExprB);
                        }
                        LADiffTermOperator=-1;
                        x = ApplyFuncRules("parseExpressionDiff", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        xExprn = ApplyFuncRulesString("parseExpressionDiff", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        log.info("Diff:parseExpressionDiff::a.DiffExpr()=" + a.DiffExpr());
                        log.info("Diff:parseExpressionDiff::b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff::x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseExpressionDiff::xExprn=" + xExprn);

                    } else {
                        if (posDiff < StrDiff.length()) {
                            ExprEndPos = posDiff;
                        } else {
                            ExprEndPos = StrDiff.length();
                        }
                        if (x.DiffExpr().equalsIgnoreCase(xExprn)) {
                            log.info("Diff:parseExpressionDiff:Equal:Return Value parseExpressionDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Expr=" + StrDiff.substring(ExprStartPos, ExprEndPos));
                            log.info("Diff:parseExpressionDiff:Equal:Return Value parseExpressionDiff:xExprn=" + xExprn);
                            return x;
                        } else {
                            final String Temp = xExprn;
                            x = (() -> Temp);
                        }
                        log.warn("Diff:parseExpressionDiff:String:Mapped State Not Equal:Return Value parseExpressionDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Expr=" + StrDiff.substring(ExprStartPos, ExprEndPos));
                        log.warn("Diff:parseExpressionDiff:String:Mapped State Not Equal:Return Value parseExpressionDiff:xExprn=" + xExprn);
                        return x;
                    }
                }
            }

            DiffExpr parseTermDiff() {
                int TermStartPos = posDiff;
                int TermEndPos = -1;
                int startpos = posDiff;
                DiffExpr x = parseFactorDiff();
                int endpos = posDiff;
                String OutputExprn = "";
                String MappedProduct = "";
                String aMappedProduct = "";
                String xExprn = "";

                if ((startpos != -1) && (endpos != -1) && (startpos < endpos) && (startpos <= StrDiff.length()) && (startpos < StrDiff.length()) && (endpos < StrDiff.length())) {
                    xExprn = StrDiff.substring(startpos, endpos);
                }
                String xConst = "";
                OutputExprn = x.DiffExpr();
                log.info("Diff:parseTermDiff:Initial Call to FactorDiff:OutputExprn=" + OutputExprn);
                if ((xConst.equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "NoExponentRuleDiff", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "NoExponentRuleDiff", DiffWithRespTo, LADiffTermOperator);
                } else if ((xConst.equalsIgnoreCase(OutputExprn)) && (!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                } else if ((!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, LADiffTermOperator);
                } else if (x.DiffExpr().equalsIgnoreCase("0.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "0.0";
                } else if ((x.DiffExpr().equalsIgnoreCase("")) && (!IsAConstant(xExprn, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermDiff", xExprn, x, x.DiffExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                }
                log.info("Diff:parseTermDiff:Initial Call to FactorDiff:aMappedProduct=" + aMappedProduct);
                log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr());
                log.info("Diff:parseTermDiff:xExprn=" + xExprn + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                for (;;) {
                    if (eatDiff('*')) { // multiplication
                        log.info("Diff:parseTermDiff:Multiply Entered" + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '*';
                        }
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                            loadoppos += 2;
                        }
                        int bstartpos = posDiff;
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr());

                        DiffExpr a = x, b = parseFactorDiff();
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '*';
                        }
                        int bendpos = posDiff;
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length())) {
                            MappedProduct = StrDiff.substring(bstartpos, bendpos);
                        }

                        log.info("Diff:parseTermDiff:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:a.DiffExpr()=" + a.DiffExpr());
                        OutputExprn = b.DiffExpr();
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseTermDiff:aMappedProduct=" + aMappedProduct);
                        log.info("Diff:parseTermDiff:MappedProduct=" + MappedProduct);
                        log.info("Diff:parseTermDiff:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:b.DiffExpr()=" + b.DiffExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        log.info("Diff:parseTermDiff:a.DiffExpr()=" + a.DiffExpr());
                        if (((b.DiffExpr() == null) || (b.DiffExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyFuncRules("parseTermDiff", OutputExprn, a, x.DiffExpr(), b, "", "", "*", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", "", "+", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if (OutputExprn.equalsIgnoreCase(DiffWithRespTo)) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((a.DiffExpr().contains("Parenthesis")) || (b.DiffExpr().contains("Parenthesis"))) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((aMappedProduct.contains(DiffWithRespTo)) && (b.DiffExpr().contains(DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else {
                            x = ApplyFuncRules("parseTermDiff", OutputExprn, a, OutputExprn, b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, a, OutputExprn, b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        }
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        log.info("Diff:parseTermDiff:xExprn=" + xExprn);
                        log.info("Diff:parseTermDiff:aMappedProduct=" + aMappedProduct);
                        if ((chDiff == '+') || (chDiff == '-')) {

                        } else if ((chDiff != -1) && (chDiff != 0)) {
                        }
                    } else if (eatDiff('/')) { // division
                        log.info("Diff:parseTermDiff:Divide Entered" + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '/';
                        }
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                            loadoppos += 2;
                        }
                        int bstartpos = posDiff;
                        DiffExpr a = x, b = parseFactorDiff();
                        int bendpos = posDiff;
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '/';
                        }
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length())) {
                            MappedProduct = StrDiff.substring(bstartpos, bendpos);
                        }
                        OutputExprn = b.DiffExpr();
                        log.info("Diff:parseTermDiff:b.DiffExpr()=" + b.DiffExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        if (((b.DiffExpr() == null) || (b.DiffExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyFuncRules("parseTermDiff", OutputExprn, a, a.DiffExpr(), b, "", "", "*", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", "", "+", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if (OutputExprn.equalsIgnoreCase(DiffWithRespTo)) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, b.DiffExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((a.DiffExpr().contains("Parenthesis")) || (b.DiffExpr().contains("Parenthesis"))) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((aMappedProduct.contains(DiffWithRespTo)) && (b.DiffExpr().contains(DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((!IsAConstant(OutputExprn, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            x = ApplyFuncRules("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", OutputExprn, "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", OutputExprn, "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else {
                            x = ApplyFuncRules("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermDiff", OutputExprn, a, b.DiffExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        }
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        if ((chDiff == '+') || (chDiff == '-')) {

                        } else if ((chDiff != -1) && (chDiff != 0)) {
                        }
                    } else {
                        if (posDiff < StrDiff.length()) {
                            TermEndPos = posDiff;
                        } else {
                            TermEndPos = StrDiff.length();
                        }
                        log.info("Diff:parseTermDiff:Return Value parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Term Expr=" + StrDiff.substring(TermStartPos, TermEndPos) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                        log.info("Diff:parseTermDiff:Return Value parseTermDiff:xExprn=" + xExprn);
                        return x;
                    }
                }
            }

            DiffExpr parseFactorDiff() {
                String Exprn = "";
                int mychNext = chDiff;
                int myposNext = posDiff;
                int FactorStartPos = posDiff;
                int FactorEndPos = -1;
                DiffExpr x = parseBaseDiff();
                log.info("Diff:parseFactorDiff:Initial:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                Split(x.DiffExpr(), DiffWithRespTo);
                Expr_Func_Split(x.DiffExpr(), DiffWithRespTo);
                if (x.DiffExpr().contains("@Function@")) {
                    if ((FunctionExpr.equalsIgnoreCase("")) && (!FactorsExpr.equalsIgnoreCase("")) && (IsAConstant(FactorsExpr, DiffWithRespTo))) {
                        x = (() -> FunctionExpr);
                        Exprn = FunctionExpr;
                        log.info("Diff:parseFactorDiff:@Function@:FunctionExpr=" + FunctionExpr);
                        log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);

                    } else if ((FunctionDiffExpr != null) && (!FunctionDiffExpr.equalsIgnoreCase(""))) {
                        x = (() -> FunctionDiffExpr);
                        Exprn = FunctionDiffExpr;
                        log.info("Diff:parseFactorDiff:@Function@:FunctionDiffExpr=" + FunctionDiffExpr);
                        log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);
                    } else {
                        x = (() -> "Unknown");
                        Exprn = "Unknown";
                        log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);

                    }
                    log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                    log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);
                } else if (x.DiffExpr().contains("@Polynomial@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                } else if (x.DiffExpr().contains("@Factor@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                } else if (x.DiffExpr().contains("@IndependentFactor@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                    log.info("Diff:parseFactorDiff:Initial Call:x.DiffExpr()=" + x.DiffExpr());
                    log.info("Diff:parseFactorDiff:Initial Call:Exprn=" + Exprn);
                    if ((chDiff == '*') || (chDiff == '/') || (chDiff == -1) || (chDiff == 0)) {
                        return x;
                    }
                } else if ((x.DiffExpr().contains("@Constant@")) && ((chDiff == -1) || (chDiff == ')') || (((myposNext == 0) || (mychNext == '+') || (mychNext == '-')) && ((chDiff == '+') || (chDiff == '-'))))) {
                    x = (() -> "0.0");
                    Exprn = "0.0";
                } else if (x.DiffExpr().contains("@Constant@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                }
                log.info("Diff:parseFactorDiff:Initial Call:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                log.info("Diff:parseFactorDiff:Initial Call:Exprn=" + Exprn);
                for (;;) {
                    if (eatDiff('^')) { // exponentiation
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                            loadoppos += 2;
                        }
                        DiffExpr a = x, b = parseBaseDiff();
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Diff:parseFactorDiff:Loop:b.DiffExpr()=" + b.DiffExpr() + " a.DiffExpr()=" + a.DiffExpr() + " Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                        if (Exprn.equalsIgnoreCase(DiffWithRespTo)) {
                            Split(b.DiffExpr(), DiffWithRespTo);
                            Expr_Func_Split(b.DiffExpr(), DiffWithRespTo);
                            String bExpr = FactorsExpr;
                            String bDiffExpr = FactorsDiffExpr;
                            x = ApplyFuncRules("parseFactorDiff", Exprn, a, DiffWithRespTo, b, bExpr, Exprn, "^", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                            Exprn = ApplyFuncRulesString("parseFactorDiff", Exprn, a, DiffWithRespTo, b, bExpr, Exprn, "^", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
                        } //Independent Variables encountered
                        else if (variables.containsKey(Exprn)) {//Is a valid Independent Constant
                            log.info("Diff:parseFactorDiff:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                            if ((SavedOperator == '+') || (SavedOperator == '-')) {
                                x = (() -> "0.0");
                                Exprn = "0.0";
                            } else if ((SavedOperator == '*') || (SavedOperator == '/')) {
                                Split(b.DiffExpr(), DiffWithRespTo);
                                final String MyTerm = Exprn + "^" + FactorsExpr;
                                x = (() -> MyTerm);
                                Exprn = MyTerm;
                            }
                        }
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Diff:parseFactorDiff:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseFactorDiff:Exprn=" + Exprn);

                    } else {
                        if (posDiff < StrDiff.length()) {
                            FactorEndPos = posDiff;
                        } else {
                            FactorEndPos = StrDiff.length();
                        }
                        if (ParanExprInProgressFlag == true) {
                            log.info("Diff:parseFactorDiff:ParanExprInProgressFlag:Return Value parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Factor Expr=" + StrDiff.substring(FactorStartPos, FactorEndPos));
                            log.info("Diff:parseFactorDiff:ParanExprInProgressFlag:Return Value parseFactorDiff:Exprn=" + Exprn);
                            final String ParaOutput = Exprn;
                            DiffExpr y = (() -> ParaOutput);
                            return y;
                        } else {
                            log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Factor Expr=" + StrDiff.substring(FactorStartPos, FactorEndPos));
                            log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:Exprn=" + Exprn);
                            return x;
                        }

                    }
                }
            }

            DiffExpr parseBaseDiff() {
                int startPos = posDiff;
                boolean ConfirmedConstFlag = false;
                loadCntxtpos++;
                loadoppos++;
                DiffExpr x = (() -> "");
                if (eatDiff('-')) { // unary minus
                    if (LookAheadOp[loadoppos] == LowLevelOperator.Minus) {
                        loadoppos += 2;
                    }
                    DiffExpr b = parseBaseDiff();
                    //x = () -> "-" + xx;
                    if (startPos == 0) {
                        x = (() -> "-" + b.DiffExpr());
                    } else {
                        x = (() -> "-" + b.DiffExpr());
                    }
                    return x;
                } else if (eatDiff('+')) { // unary plus
                    if (LookAheadOp[loadoppos] == LowLevelOperator.Plus) {
                        loadoppos += 2;
                    }
                    x = parseBaseDiff();
                    return x;
                }
                if (chDiff == '(') { //parenthesis
                    //Code to handle Expressions:
                    int POpos = posDiff;
                    do {
                        posDiff--;
                    } while ((posDiff >= 0) && (posDiff < StrDiff.length()) && (!IsOperator(StrDiff.charAt(posDiff))));
                    if ((posDiff >= 0) && (posDiff < StrDiff.length())) {
                        ParenthesisOperator = StrDiff.charAt(posDiff);
                    } else {
                        ParenthesisOperator = '+';
                    }
                    posDiff = POpos;
                    // Set ParanExprFlag only if not a function argument
                    NormalExprnFlag = false;
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    ParanExprInProgressFlag = true;

                    int ParastartPos = posDiff;
                    DiffExpr arg = parseParaParaDiff();
                    eatDiff(')');
                    log.info("Diff:parseBaseDiffExpr:Parenthesis: ParanDiffExpr=" + ParanDiffExpr);
                    log.info("Diff:parseBaseDiffExpr:Parenthesis: ParanExpr=" + ParanExpr);
                    log.info("Diff:parseBaseDiffExpr:Parenthesis: arg.DiffExpr()=" + arg.DiffExpr());
                    log.info("Diff:parseBaseDiffExpr:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chDiff=" + (char) chDiff);
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    ParanExprInProgressFlag = false;
                    int ParaEndpos = posDiff;
                    //Code to handle Expressions
                    // Unset ParanExprFlag only if not a function argument
                    String name = "";
                    if ((ParastartPos != -1) && (ParastartPos < ParaEndpos) && (ParastartPos <= StrDiff.length()) && (ParastartPos < StrDiff.length())) {
                        name = StrDiff.substring(ParastartPos, ParaEndpos).trim();
                    }
                    final String MyTemp = name;

                    log.info("Diff:parseBaseDiff:Aligning Pararenthesis:Complete:name=" + name);
                    if (FuncArgFlag == true) {
                        {
                            FuncArgFlag = false;
                            ParanExprFlag = false;
                            //Dummy Call
                            final String MyTemp1 = arg.DiffExpr();
                            x = (() -> (MyTemp + "@" + "FunctionArgument" + "@" + "Factor" + "@" + MyTemp + "@" + "FactorDiff" + "@" + MyTemp1));
                            Split(x.DiffExpr(), DiffWithRespTo);
                            Expr_Func_Split(x.DiffExpr(), DiffWithRespTo);
                            log.info("Diff:parseBaseDiffExpr:name=" + name + " FactorsExpr=" + FactorsExpr + " x.DiffExpr()=" + x.DiffExpr() + " arg.DiffExpr()=" + MyTemp1);
                            x = (() -> (MyTemp + "@" + "FunctionArgument" + "@" + "Factor" + "@" + FactorsExpr + "@" + MyTemp1));
                            log.info("Diff:parseBaseDiffExpr:Function: ParanDiffExpr=" + ParanDiffExpr);
                            log.info("Diff:parseBaseDiffExpr:Function: ParanExpr=" + ParanExpr);
                            log.info("Diff:parseBaseDiffExpr:Function: x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseBaseDiffExpr:Function=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chDiff=" + (char) chDiff);

                            return x;
                        }
                    } else {
                        ParanExprFlag = false;
                        FuncArgFlag = false;
                        //Dummy Call
                        if (arg.DiffExpr().equalsIgnoreCase("")) {
                            x = (() -> (MyTemp + "@" + "Parenthesis" + "@" + "Factor" + "@" + MyTemp + "@" + "FactorDiff" + "@" + 0.0));
                            arg = (() -> "0.0");
                        } else {
                            final String MyTemp1 = arg.DiffExpr();
                            x = (() -> (MyTemp + "@" + "Parenthesis" + "@" + "Factor" + "@" + MyTemp + "@" + "FactorDiff" + "@" + MyTemp1));
                        }
                        Split(x.DiffExpr(), DiffWithRespTo);
                        Expr_Func_Split(x.DiffExpr(), DiffWithRespTo);
                        final String ParaExpr = MyTemp + "@" + "Parenthesis" + "@" + "(" + arg.DiffExpr() + ")";
                        x = (() -> ParaExpr);
                        log.info("Diff:parseBaseDiffExpr:Parenthesis: ParanDiffExpr=" + ParanDiffExpr);
                        log.info("Diff:parseBaseDiffExpr:Parenthesis: arg.DiffExpr()=" + arg.DiffExpr());
                        log.info("Diff:parseBaseDiffExpr:Parenthesis: ParanExpr=" + ParanExpr);
                        log.info("Diff:parseBaseDiffExpr:Parenthesis: x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseBaseDiffExpr:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chDiff=" + (char) chDiff);

                        return x;
                    }
                } else if (IsNumber(chDiff)) { //numbers
                    log.info("Diff:parseBaseDiff:Entered Numbers:chDiff=" + (char) chDiff);
                    while (IsNumber(chDiff)) {
                        nextCharDiff();
                        if ((chDiff == 'E') || (chDiff == 'e')) {
                            nextCharDiff();
                            if ((chDiff == '-') || (chDiff == '+')) {
                                nextCharDiff();
                            }
                        }
                    }
                    String xConst = "";
                    String xx = "" + Double.parseDouble(StrDiff.substring(startPos, posDiff));
                    log.info("Diff:parseBaseDiffExpr:Numbers: xx=" + xx);
                    LookAheadDiffConst MyCoeff;
                    int DiffIntLength = posDiff - startPos;
                    if ((IsAConstant(xx, DiffWithRespTo)) && ((chDiff == '*') || (chDiff == '/') || (chDiff == -1))) {
                        log.info("Diff:parseBaseDiffExpr:Entered LookAheadDiffConst Number:MyCoeff=" + xConst);
                        loadparams(startPos);
                        MyCoeff = parseLADiff(StrDiff.substring(startPos), startPos, DiffIntLength, "Differentiation", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                        }
                    } else {
                        loadparams(startPos);
                        MyCoeff = parseLADiff(StrDiff.substring(startPos), startPos, DiffIntLength, "Differentiation", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                        }
                        log.info("Diff:parseBaseDiffExpr:IndConst Expressiont:xConst=" + xConst);
                    }
                    log.info("Diff:parseBaseDiffExpr:Number:MyCoeff=" + xConst);

                    if (chDiff != -1) {
                        SavedOperator = chDiff;
                        if ((SavedOperator == ')') && (posDiff + 1) < StrDiff.length()) {
                            LookAheadOperator = StrDiff.charAt(posDiff + 1);
                            ParenthesisOperator = 0;
                        }
                        if (IsNotAnOperator(LookAheadOperator)) {
                            LookAheadOperator = 0;
                        }
                    } else {
                        SavedOperator = 0;
                    }
                    if (ConfirmedConstFlag == true) {
                        final String ConsolidatedConstTerm = xx;
                        x = () -> (ConsolidatedConstTerm + "@" + "Constant" + "@" + "0.0");
                        log.info("Diff:parseBaseDiffExpr:NumberORConstant:x.DiffExpr()=" + x.DiffExpr());
                        return x;
                    } else {
                        final String ConsolidatedIndConstTerm = xConst;
                        x = () -> (ConsolidatedIndConstTerm + "@" + "IndependentFactor" + "@" + "0.0");
                        log.info("Diff:parseBaseDiffExpr:IndConstExpression:x.DiffExpr()=" + x.DiffExpr());
                        return x;
                    }
                } else if (IsVariable(chDiff)) {//functions & variables
                    log.info("Diff:parseBaseDiff:Entered Functions & Variable:chDiff=" + (char) chDiff);
                    while ((IsVariable(chDiff)) || (IsNumber(chDiff))) {
                        nextCharDiff();
                    }
                    String name = StrDiff.substring(startPos, posDiff).trim();
                    //Code to handle Function
                    if (functionsDiff.containsKey(name)) {
                        //String func = functionsDiff.get(name);
                        String func = name;
                        FuncArgFlag = true;
                        ParanExprFlag = false;
                        int argstartpos = posDiff;
                        DiffExpr arg = parseParaParaDiff();
                        int argEndpos = posDiff;
                        FuncArgFlag = false;
                        ParanExprFlag = false;
                        ParenthesisFlag = false;
                        eatDiff(')');
                        log.info("Diff:parseBaseDiff:Function Argument:arg.DiffExpr()=" + arg.DiffExpr());
                        ParanExprInProgressFlag = false;
                        log.info("Diff:parseBaseDiff:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " Str.length()=" + StrDiff.length());
                        String Factor = StrDiff.substring(argstartpos, argEndpos).trim();
                        log.info("Diff:parseBaseDiff:Function Argument:Factor=" + Factor + " String=" + StrDiff.substring(argstartpos, argEndpos));
                        log.info("Diff:parseBaseDiff:Aligning Pararenthesis:Started");
                        String[] TempStart = new String[4];
                        TempStart = Factor.split("\\(\\)");
                        log.info("Diff:parseBaseDiffExpr:TempStart[1]=" + TempStart[0] + " Factor=" + Factor);
                        if ((TempStart.length >= 1) && (TempStart[0] != null) && (!TempStart[0].equalsIgnoreCase(""))) {
                            String[] TempStart1 = new String[4];
                            TempStart1 = TempStart[0].split("\\(\\)");
                            if ((TempStart1.length >= 1) && (TempStart[0] != null) && (!TempStart[0].equalsIgnoreCase(""))) {
                                log.info("Diff:parseBaseDiffExpr: TempStart1.length=" + TempStart1.length /*+ " TempStart1[1]=" + TempStart1[1]*/);
                                final String Factor1 = TempStart1[0];
                                log.info("Diff:parseBaseDiff:Aligning Parenthesis:Complete");
                                log.info("Diff:parseBaseDiff:Function Argument:Factor=" + Factor + " Factor1=" + Factor1);
                                log.info("Diff:parseBaseDiff:arg.DiffExpr()=" + arg.DiffExpr());
                                //Dummy Call
                                x = (() -> (name + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + Factor1) + "@" + arg.DiffExpr());
                                Split(x.DiffExpr(), DiffWithRespTo);
                                Expr_Func_Split(x.DiffExpr(), DiffWithRespTo);
                                log.info("Diff:parseBaseDiff:arg.DiffExpr()=" + arg.DiffExpr());
                                log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                                log.info("Diff:parseBaseDiff:FunctionDiffExpr=" + FunctionDiffExpr);
                                //Code to handle Function Arguments
                                if ((!arg.DiffExpr().equalsIgnoreCase("")) && ((FunctionDiffExpr == null) || (FunctionDiffExpr.equalsIgnoreCase("")))) {
                                    x = (() -> (name + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + arg.DiffExpr()));
                                } else if ((FunctionDiffExpr != null) && (!FunctionDiffExpr.equalsIgnoreCase(""))) {
                                    x = (() -> (name + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + "(" + FunctionDiffExpr + ")"));
                                }
                            } else {
                                x = (() -> (name + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + "(" + "Unknown" + ")"));
                            }
                            log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseBaseDiff:FunctionDiffExpr=" + FunctionDiffExpr);
                            log.info("Diff:parseBaseDiff:arg.DiffExpr()=" + arg.DiffExpr());
                        } else {
                            x = () -> (name + "@" + "Unknown" + "@" + (name));
                        }
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo))) {
                        log.info("Diff:parseBaseDiff:Polynomial:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        x = () -> (name + "@" + "Polynomial" + "@" + (name));
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo)) && ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Variable) && (LookAheadCntxt[loadCntxtpos + 1] == HighLevelContext.DiffWithRespTo) && (!name.contains("^")))) {
                        log.info("Diff:parseBaseDiff:Factor:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        x = () -> (name + "@" + "Factor" + "@" + (name));
                    } else if (!name.equalsIgnoreCase(DiffWithRespTo)) {//Independent Variable
                        log.info("Diff:parseBaseDiff:IndependentFactor:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posDiff=" + posDiff);
                        x = () -> (name + "@" + "IndependentFactor" + "@" + (name));
                    }
                    log.info("Diff:parseBaseDiffExpr:Variable/Function:name=" + name + " x.DiffExpr()=" + x.DiffExpr());
                    return x;
                } else if (chDiff == ')') {
                    nextCharDiff();
                    ParenthesisOperator = 0;
                    return x;
                } else if ((chDiff != -1) && (chDiff != 0) && (IsNumber(chDiff))) {
                    throw new RuntimeException("Diff:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                } else if ((chDiff != -1) && (chDiff != 0) && (IsOperator(chDiff))) {
                    throw new RuntimeException("Diff:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                } else if ((chDiff != -1) && (chDiff != 0)) {
                    throw new RuntimeException("Diff:parseBase:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                }
                return x;
            }
        }.parseDiff();
    }
//Created by Rajesh Pai
// Integrates F(x)
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface IntegrExpr {

        String IntegrExpr();
    }

    public static IntegrExpr parseIntegr(String str, Map<String, Double> variables, Map<String, String> variablesDiff, String DiffWithRespTo) {

        return new Object() {

            boolean ParanExprInProgressFlag = false;

            IntegrExpr parseIntegr() {
                IntegrExpr x = (() -> "");
                StrIntegr = str;
                ResetVars("IntegrExpr");
                InitLookFunc(str, posIntegr, "Integr");
                if ((StrIntegr == null) || (StrIntegr.equalsIgnoreCase("")) || (StrIntegr.contains("NaN"))) {
                    return x;
                }
                posIntegr = 0;
                chIntegr = StrIntegr.charAt(0);
                SavedOperator = 0;
                int oldch = chIntegr;
                x = parseExpressionIntegr();//parseParaIntegr();
                log.info("Integr:parseIntegr:Final:x.IntegrExpr()=" + x.IntegrExpr());
                if (oldch != '-')
                    ; else if (!x.IntegrExpr().equalsIgnoreCase("")) {
                    final String FinalOutput = x.IntegrExpr();
                    x = (() -> (char) oldch + FinalOutput);
                }
                if (x.IntegrExpr().contains("--")) {
                    String OutputExprn = x.IntegrExpr().replaceAll("\\--", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
                if (x.IntegrExpr().contains("+-")) {
                    String OutputExprn = x.IntegrExpr().replaceAll("\\+-", "-");
                    final String FinalOutput = OutputExprn;
                    x = (() -> FinalOutput);
                }
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Diff:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                return x;
            }

            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            IntegrExpr parseParaParaIntegr() {
                IntegrExpr x = (() -> "");
                IntegrExpr a = (() -> "");
                IntegrExpr b = (() -> "");
                String OutputExprn = "";
                String xExprn = "";
                log.info("Integr:parseParaParaIntegr:Initial Call to parseParaParaDiff:xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                log.info("Integr:parseParaParaIntegr:Initial Call to parseParaParaDiff:OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                for (;;) {
                    log.info("Integr:parseParaParaIntegr:Top:Inside Loop:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chIntegr=" + (char) chIntegr);
                    if (eatIntegr('(')) { // ParenthesisOpen
                        NormalExprnFlag = false;
                        ParenthesisCurrNum = ParenthesisHighLvlNum;
                        ParenthesisHighLvlNum++;
                        a = x;
                        final String Term1 = x.IntegrExpr();
                        b = parseExpressionIntegr();
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop:a.IntegrExpr()(Term1)=" + Term1 + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop:b.IntegrExpr()=" + b.IntegrExpr());
                        if ((IsHighLvlNumberExprn(b.IntegrExpr(), DiffWithRespTo)) && (SavedOperator != ')') && (SavedOperator != -1) && (SavedOperator != 0)) {
                            final String Term3 = b.IntegrExpr();
                            b = (() -> Term3 + (char) SavedOperator);
                        }
                        if (b.IntegrExpr().equalsIgnoreCase("0.0")) {
                            b = (() -> "");
                        }
                        if ((SavedOperator == ')') && (ParenthesisFlag == true) && (ParenthesisCurrNum == (ParenthesisHighLvlNum - 1))) {
                            final String Term4 = b.IntegrExpr();
                            //Kept for compatibility with parseNormalPara Function
                            if ((chIntegr != -1)) {
                                b = (() -> Term4);
                            } else {
                                b = (() -> Term4);
                            }
                            SavedOperator = 0;
                        }
                        final String Term2 = b.IntegrExpr();
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop:Term1=" + Term1);
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop:Term2=" + Term2 + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr + " SavedOperator=" + (char) SavedOperator + " SavedOperator=" + SavedOperator);
                        if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2))
                                && (IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term1 + Term2);
                            xExprn = Term1 + Term2;
                        } else if ((IsNullEmptyZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2)) && (!IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term2);
                            xExprn = Term2;
                        } else if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNullEmptyZero(Term2))) {
                            x = (() -> Term1);
                            xExprn = Term1;
                        } else if ((IsNotNullAndEmptyAndZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2)) && (!IsAConstant(Term2, DiffWithRespTo))) {
                            x = (() -> Term1 + Term2);
                            xExprn = Term1 + Term2;
                        } else if ((IsNullEmptyZero(Term1)) && (IsNotNullAndEmptyAndZero(Term2))) {
                            x = (() -> Term2);
                            xExprn = Term2;
                        }
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop parseParaParaDiff:(:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop parseParaParaDiff:(:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop parseParaParaDiff:(:xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        log.info("Integr:parseParaParaIntegr:(:Inside Loop parseParaParaDiff:(:OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                    } else if (eatIntegr(')')) { // ParenthesisClose
                        NormalExprnFlag = true;
                        if (ParenthesisHighLvlNum == 0) {
                            NormalExprnFlag = true;
                        }
                        log.info("Integr:parseParaParaIntegr:):Inside Loop parseParaParaDiff:):ParenthesisNum=" + ParenthesisHighLvlNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " NormalExprnFlag=" + NormalExprnFlag);
                        log.info("Integr:parseParaParaIntegr:):Inside Loop parseParaParaDiff:):x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseParaParaIntegr:):Inside Loop parseParaParaDiff:):xExprn=" + xExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        log.info("Integr:parseParaParaIntegr:):Inside Loop parseParaParaDiff:):OutputExprn=" + OutputExprn + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        log.info("Integr:parseParaParaIntegr:):Return From Inside Loop parseParaParaDiff:x.IntegrExpr()=" + x.IntegrExpr() + " xExprn=" + xExprn);
                        return x;
                    } else {
                        if (ParenthesisHighLvlNum != ParenthesisCurrNum) {
                            log.info("Integr:parseParaParaIntegr:Return From parseParaParaDiff:else:Parenthesis Mismatch:ParenthesisNum=" + ParenthesisHighLvlNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " ParenthesisCurrNum=" + ParenthesisCurrNum + " NormalExprnFlag=" + NormalExprnFlag + " StrIntegr=" + StrIntegr.substring(posIntegr));
                        }
                        log.info("Integr:parseParaParaIntegr:else:Return From Inside Loop parseParaParaDiff:else:ParenthesisNum=" + ParenthesisHighLvlNum + " NormalExprnFlag=" + NormalExprnFlag);
                        log.info("Integr:parseParaParaIntegr:else:Return From Inside Loop parseParaParaDiff:else:x.IntegrExpr()=" + x.IntegrExpr() + " xExprn=" + xExprn + " chIntegr=" + (char) chIntegr);
                        return x;
                    }
                }
            }

            IntegrExpr parseExpressionIntegr() {
                log.info("Integr:parseExpressionIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                int ExprStartPos = posIntegr;
                int ExprEndPos = -1;
                if ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Null) && (LookAheadOp[loadoppos] == LowLevelOperator.Null)) {
                    loadCntxtpos += 1;
                    loadoppos += 2;
                } else {
                    loadparams(posIntegr);
                }
                int startpos = posIntegr;
                LADiffTermOperator = -1;
                SavedOperator = 0;
                IntegrExpr x = parseTermIntegr();
                int endpos = posIntegr;
                String xExprn = "";
                String OutputExprn = "";
                NumCoeff = 0;
                if ((startpos != -1) && (endpos != -1) && (startpos < endpos) && (startpos <= StrIntegr.length()) && (startpos < StrIntegr.length()) && (endpos <= StrIntegr.length())) {
                    xExprn = StrIntegr.substring(startpos, endpos);
                }
                String xConst = "";
                OutputExprn = x.IntegrExpr();
                log.info("Integr:parseExpressionIntegr:Initial Call to parseTermIntegr:OutputExprn=" + OutputExprn);
                if ((IsAConstant(OutputExprn, DiffWithRespTo)) && (IsAConstant(xExprn, DiffWithRespTo)) && ((chIntegr == '+') || (chIntegr == '-') || (chIntegr == -1))) {
                    log.info("Integr:parseExpressionIntegr:Mapping 0.0:Got a Constant Value from parseTermIntegr:OutputExprn=" + OutputExprn);
                    OutputExprn = xExprn;
                    final String ConstCoeff = xExprn;
                    x = (() -> ConstCoeff + "*" + DiffWithRespTo);
                    xExprn = ConstCoeff + "*" + DiffWithRespTo;
                } else if ((OutputExprn.equalsIgnoreCase(DiffWithRespTo)) || (OutputExprn.equalsIgnoreCase("1.0")) && ((chIntegr == '+') || (chIntegr == '-') || (chIntegr == -1))) {
                    OutputExprn = "1.0/2.0*" + DiffWithRespTo + "^2.0";
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                } else {
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                }
                for (;;) {
                    NumCoeff = 0;
                    LADiffTermOperator = -1;
                    if ((eatIntegr('+')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) {// addition
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Plus) {
                            loadoppos += 2;
                            loadCntxtpos += 1;
                        }
                        int bstartpos = posIntegr;
                        IntegrExpr a = x, b = parseTermIntegr();
                        String bExprn = "";
                        int bendpos = posIntegr;
                        if ((bstartpos != -1) && (bendpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrIntegr.length()) && (bstartpos < StrIntegr.length()) && (bendpos <= StrIntegr.length())) {
                            bExprn = StrIntegr.substring(bstartpos, bendpos);
                        }
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        if ((IsAConstant(b.IntegrExpr(), DiffWithRespTo)) && (b.IntegrExpr().equalsIgnoreCase(bExprn))) {
                            final String ConstCoeff = bExprn;
                            b = (() -> ConstCoeff + "*" + DiffWithRespTo);
                        } else if ((!b.IntegrExpr().equalsIgnoreCase(bExprn)) && ((b.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.IntegrExpr().equalsIgnoreCase("1.0"))))
                            ;//Do Nothing as b.DiffExpr() has the value(the Coeff of x)
                        else if ((b.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.IntegrExpr().equalsIgnoreCase("1.0"))) {
                            b = (() -> "1.0/2.0*" + DiffWithRespTo + "^2.0");
                        }
                        log.info("Integr:parseExpressionIntegr:a.IntegrExpr()=" + a.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:xExprn=" + xExprn);
                        if (a.IntegrExpr().contains("Parenthesis")) {
                            Expr_Func_Split_Integr(a.IntegrExpr(), DiffWithRespTo);
                            final String ParaExprA = ParanExpr;
                            final String ParaExprDiffExprA = ParanDiffExpr;
                            a = (() -> ParaExprDiffExprA);
                        }
                        if (b.IntegrExpr().contains("Parenthesis")) {
                            Expr_Func_Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                            final String ParaExprB = ParanExpr;
                            final String ParaExprDiffExprB = ParanDiffExpr;
                            b = (() -> ParaExprDiffExprB);
                        }
                        //if(bExprn.contains(DiffWithRespTo+"^")) {
                        x = ApplyFuncRules("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        xExprn = ApplyFuncRulesString("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        //}else if(!bExprn.contains(DiffWithRespTo+"^")) {
                        //    x = ApplyFuncRules("parseExpressionIntegr1", xExprn, a, bExprn, b, bExprn, "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        //    xExprn = ApplyFuncRulesString("parseExpressionIntegr1", xExprn, a, bExprn, b, bExprn, "", "+", "SumRule", DiffWithRespTo, LADiffTermOperator);
                        //}
                        log.info("Integr:parseExpressionIntegr:a.IntegrExpr()=" + a.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:xExprn=" + xExprn);
                    } else if ((eatIntegr('-')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) { // subtraction
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Minus) {
                            loadoppos += 2;
                            loadCntxtpos += 1;

                        }
                        int bstartpos = posIntegr;
                        IntegrExpr a = x, b = parseTermIntegr();
                        int bendpos = posIntegr;
                        String bExprn = "";
                        if ((bstartpos != -1) && (bendpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrIntegr.length()) && (bstartpos < StrIntegr.length()) && (bendpos <= StrIntegr.length())) {
                            bExprn = StrIntegr.substring(bstartpos, bendpos).trim();
                        }
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        if ((IsAConstant(b.IntegrExpr(), DiffWithRespTo)) && (b.IntegrExpr().equalsIgnoreCase(bExprn))) {
                            final String ConstCoeff = bExprn;
                            b = (() -> ConstCoeff + "*" + DiffWithRespTo);
                        } else if ((!b.IntegrExpr().equalsIgnoreCase(bExprn)) && ((b.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.IntegrExpr().equalsIgnoreCase("1.0"))))
                            ;//Do Nothing
                        else if ((b.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) || (b.IntegrExpr().equalsIgnoreCase("1.0"))) {
                            b = (() -> "1.0/2.0*" + DiffWithRespTo + "^2.0");
                        }
                        log.info("Integr:parseExpressionIntegr:a.IntegrExpr()=" + a.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:xExprn=" + xExprn);
                        if (a.IntegrExpr().contains("Parenthesis")) {
                            Expr_Func_Split_Integr(a.IntegrExpr(), DiffWithRespTo);
                            final String ParaExprA = ParanExpr;
                            final String ParaExprDiffExprA = ParanDiffExpr;
                            a = (() -> ParaExprDiffExprA);
                        }
                        if (b.IntegrExpr().contains("Parenthesis")) {
                            Expr_Func_Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                            final String ParaExprB = ParanExpr;
                            final String ParaExprDiffExprB = ParanDiffExpr;
                            b = (() -> ParaExprDiffExprB);
                        }
                        //if(bExprn.contains(DiffWithRespTo+"^")) {
                        x = ApplyFuncRules("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        xExprn = ApplyFuncRulesString("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        //}else if(!bExprn.contains(DiffWithRespTo+"^")) {
                        //    x = ApplyFuncRules("parseExpressionIntegr1", xExprn, a, bExprn, b, bExprn, "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        //    xExprn = ApplyFuncRulesString("parseExpressionIntegr1", xExprn, a, bExprn, b, bExprn, "", "-", "SubtractionRule", DiffWithRespTo, LADiffTermOperator);
                        //}
                        log.info("Integr:parseExpressionIntegr:a.IntegrExpr()=" + a.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:xExprn=" + xExprn);
                    } else {
                        if (posIntegr < StrIntegr.length()) {
                            ExprEndPos = posIntegr;
                        } else {
                            ExprEndPos = StrIntegr.length();
                        }
                        log.info("Integr:parseExpressionIntegr:Return Value parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(ExprStartPos, ExprEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseExpressionIntegr:Return Value parseExpressionIntegr:xExprn=" + xExprn);
                        if (x.IntegrExpr().equalsIgnoreCase(xExprn)) {
                            return x;
                        } else {
                            final String Temp = xExprn;
                            x = (() -> Temp);
                        }
                        return x;
                    }
                }
            }

            IntegrExpr parseTermIntegr() {
                log.info("Integr:parseTermIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                int TermStartPos = posIntegr;
                int TermEndPos = -1;
                int startpos = posIntegr;
                GotExpntNumFlag = false;
                IntegrExpr a = (() -> "");
                IntegrExpr b = (() -> "");
                IntegrExpr x = (() -> "");
                x = parseFactorIntegr();
                int endpos = posIntegr;
                String OutputExprn = "";
                String MappedProduct = "";
                String aMappedProduct = "";
                String xExprn = "";

                String xConst = "";
                OutputExprn = x.IntegrExpr();
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:OutputExprn=" + OutputExprn);
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:xExprn=" + xExprn + " StrIntegr.length()=" + StrIntegr.length());
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:x.IntegrExpr()=" + x.IntegrExpr() + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr);
                if ((xConst.equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0")) && (!OutputExprn.equalsIgnoreCase("1.0")) && (!x.IntegrExpr().equalsIgnoreCase("1.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "NoExponentRuleDiff", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "NoExponentRuleDiff", DiffWithRespTo, LADiffTermOperator);
                } else if ((xConst.equalsIgnoreCase(OutputExprn)) && (!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0")) && (!OutputExprn.equalsIgnoreCase("1.0")) && (!x.IntegrExpr().equalsIgnoreCase("1.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "NoExponentRuleTerm", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "NoExponentRuleTerm", DiffWithRespTo, LADiffTermOperator);
                } else if ((!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermIntegr", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, LADiffTermOperator);
                } else if (x.IntegrExpr().equalsIgnoreCase("1.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "1.0";
                } else if (x.IntegrExpr().equalsIgnoreCase("0.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "0.0";
                } else if ((x.IntegrExpr().equalsIgnoreCase("")) && (!IsAConstant(xExprn, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))) {
                    aMappedProduct = xExprn;
                    x = ApplyFuncRules("parseTermIntegrInit", xExprn, x, x.IntegrExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                    xExprn = ApplyFuncRulesString("parseTermIntegrInit", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                    log.info("Integr:parseTermIntegr:parseTermIntegrInit:x.IntegrExpr()=" + x.IntegrExpr());
                    log.info("Integr:parseTermIntegr:parseTermIntegrInit:xExprn=" + xExprn);

                }
                log.info("Integr:parseTermIntegr:After Co-efficient:x.IntegrExpr()=" + x.IntegrExpr());
                log.info("Integr:parseTermIntegr:After Co-efficient:xExprn=" + xExprn);
                log.info("Integr:parseTermIntegr:After Co-efficient:aMappedProduct=" + aMappedProduct);
                for (;;) {
                    if (eatIntegr('*')) { // multiplication
                        log.info("Integr:parseTermIntegr:Multiply:Entered" + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                            loadoppos += 2;
                        }
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '*';
                        }
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        int bstartpos = posIntegr;
                        a = x;
                        b = parseFactorIntegr();
                        int bendpos = posIntegr;
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrIntegr.length()) && (bstartpos < StrIntegr.length()) && (bendpos < StrIntegr.length())) {
                            MappedProduct = StrIntegr.substring(bstartpos, bendpos);
                        }
                        log.info("Integr:parseTermIntegr:Multiply:a.IntegrExpr()=" + a.IntegrExpr() + " xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Multiply:b.IntegrExpr()=" + b.IntegrExpr());
                        OutputExprn = b.IntegrExpr();
                        log.info("Integr:parseTermIntegr:Multiply:aMappedProduct=" + aMappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:MappedProduct=" + MappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:xConst=" + xConst);
                        log.info("Integr:parseTermIntegr:Multiply:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Multiply:a.IntegrExpr()=" + a.IntegrExpr());
                        if (((b.IntegrExpr() == null) || (b.IntegrExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyFuncRules("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, "", "", "+", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if (xConst.equalsIgnoreCase(OutputExprn)) {
                            x = ApplyFuncRules("parseTermIntegr1", OutputExprn, a, b.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr1", OutputExprn, a, b.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((a.IntegrExpr().contains("Parenthesis")) || (b.IntegrExpr().contains("Parenthesis"))) {
                            x = ApplyFuncRules("parseTermIntegr", OutputExprn, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr", OutputExprn, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermIntegr0", aMappedProduct, a, b.IntegrExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr0", aMappedProduct, a, b.IntegrExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else {
                            x = ApplyFuncRules("parseTermIntegr1", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr1", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "*", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        }
                        log.info("Integr:parseTermIntegr:Multiply:aMappedProduct=" + aMappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        log.info("Integr:parseTermIntegr:Multiply:xExprn=" + xExprn + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        if ((chIntegr == '+') || (chIntegr == '-')) {

                        } else if ((chIntegr != -1) && (chIntegr != 0)) {
                        }
                    } else if (eatIntegr('/')) { // division
                        log.info("Integr:parseTermIntegr:Divide:Entered" + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr());
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                            loadoppos += 2;
                        }
                        if (LADiffTermOperator == -1) {
                            LADiffTermOperator = '/';
                        }
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        int bstartpos = posIntegr;
                        a = x;
                        b = parseFactorIntegr();
                        int bendpos = posIntegr;
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrIntegr.length()) && (bstartpos < StrIntegr.length()) && (bendpos < StrIntegr.length())) {
                            MappedProduct = StrIntegr.substring(bstartpos, bendpos);
                        }
                        log.info("Integr:parseTermIntegr:Divide:a.IntegrExpr()=" + a.IntegrExpr() + " xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Divide:b.IntegrExpr()=" + b.IntegrExpr());
                        OutputExprn = b.IntegrExpr();
                        log.info("Integr:parseTermIntegr:Divide:aMappedProduct=" + aMappedProduct);
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Divide:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseTermIntegr::Divide:a.IntegrExpr()=" + a.IntegrExpr());
                        if (((b.IntegrExpr() == null) || (b.IntegrExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyFuncRules("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if (xConst.equalsIgnoreCase(OutputExprn)) {
                            x = ApplyFuncRules("parseTermIntegr1", OutputExprn, a, b.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr1", OutputExprn, a, b.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((a.IntegrExpr().contains("Parenthesis")) || (b.IntegrExpr().contains("Parenthesis"))) {
                            x = ApplyFuncRules("parseTermIntegr", OutputExprn, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr", OutputExprn, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            x = ApplyFuncRules("parseTermIntegr0", aMappedProduct, a, b.IntegrExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr0", aMappedProduct, a, b.IntegrExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else {
                            x = ApplyFuncRules("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            xExprn = ApplyFuncRulesString("parseTermIntegr0", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "/", "ConstantRule", DiffWithRespTo, LADiffTermOperator);
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        }

                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:aMappedProduct=" + aMappedProduct);
                        if ((chIntegr == '+') || (chIntegr == '-')) {

                        } else if ((chIntegr != -1) && (chIntegr != 0)) {
                        }
                    } else {
                        if (posIntegr < StrIntegr.length()) {
                            TermEndPos = posIntegr;
                        } else {
                            TermEndPos = StrIntegr.length();
                        }
                        log.info("Integr:parseTermIntegr:Return Value parseTermDiff:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(TermStartPos, TermEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseTermIntegr:Return Value parseTermDiff:xExprn=" + xExprn);
                        return x;
                    }
                }
            }

            IntegrExpr parseFactorIntegr() {
                log.info("Integr:parseFactorIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                int StartPos = posIntegr;
                int aStartPos = -1;
                int bStartPos = -1;
                int FactorStartPos = posIntegr;
                int FactorEndPos = -1;
                String Exprn = "";
                IntegrExpr x = parseBaseIntegr();
                if (aStartPos == -1) {
                    aStartPos = StartPos;
                }
                log.info("Integr:parseFactorIntegr:Initial:x.IntegrExpr()=" + x.IntegrExpr());
                Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                Expr_Func_Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                if (x.IntegrExpr().contains("@Function@")) {
                    if ((FunctionExpr.equalsIgnoreCase("")) && (!FactorsExpr.equalsIgnoreCase("")) && (IsAConstant(FactorsExpr, DiffWithRespTo))) {
                        x = (() -> FunctionExpr);
                        Exprn = FunctionExpr;
                        log.info("Integr:parseFactorIntegr:@Function@:FunctionExpr=" + FunctionExpr);
                        log.info("Integr:parseFactorIntegr:@Function@:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseFactorIntegr:@Function@:Exprn=" + Exprn);

                    } else if ((FunctionDiffExpr != null) && (!FunctionDiffExpr.equalsIgnoreCase(""))) {
                        x = (() -> FunctionDiffExpr);
                        Exprn = FunctionDiffExpr;
                        log.info("Integr:parseFactorIntegr:@Function@:FunctionDiffExpr=" + FunctionDiffExpr);
                        log.info("Integr:parseFactorIntegr:@Function@:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseFactorIntegr:@Function@:Exprn=" + Exprn);
                    } else {
                        x = (() -> "Unknown");
                        Exprn = "Unknown";
                        log.info("Integr:parseFactorIntegr:@Function@:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseFactorIntegr:@Function@:Exprn=" + Exprn);

                    }
                    log.info("Integr:parseFactorIntegr:@Function@:x.IntegrExpr()=" + x.IntegrExpr());
                    log.info("Integr:parseFactorIntegr:@Function@:Exprn=" + Exprn);
                } else if (x.IntegrExpr().contains("@Polynomial@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                } else if (x.IntegrExpr().contains("@Factor@")) {
                    //Integral of x alone
                    x = (() -> "1.0/2.0*" + FactorsExpr);
                    Exprn = "1.0/2.0*" + FactorsExpr;
                } else if (x.IntegrExpr().contains("@IndependentFactor@")) {
                    String TempIndConsExpr = FactorsExpr;
                    String TempIndConstant = FactorsExpr;
                    log.info("Integr:parseFactorIntegr:Initial Call:TempIndConsExpr=" + TempIndConsExpr);
                    log.info("Integr:parseFactorIntegr:Initial Call:TempIndConstant=" + TempIndConstant);
                    log.info("Integr:parseFactorIntegr:Initial Call:FactorsDiffExpr=" + FactorsDiffExpr);
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                } else if ((x.IntegrExpr().contains("@Constant@")) && ((chIntegr == -1) || (chIntegr == ')'))) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                } else if (x.IntegrExpr().contains("@Constant@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                }
                log.info("Integr:parseFactorIntegr:Initial Call:x.IntegrExpr()=" + x.IntegrExpr());
                log.info("Integr:parseFactorIntegr:Initial Call:Exprn=" + Exprn);
                for (;;) {
                    int aEndPos = posIntegr;
                    if (eatIntegr('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                            loadoppos += 2;
                        }
                        if (bStartPos == -1) {
                            bStartPos = posIntegr;
                        }
                        IntegrExpr a = x, b = parseBaseIntegr();
                        int bEndPos = posIntegr;
                        GotExpntNumFlag = false;
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Integr:parseFactorIntegr:Loop:b.IntegrExpr()=" + b.IntegrExpr() + " a.IntegrExpr()=" + a.IntegrExpr() + " SavedOperator=" + (char) SavedOperator);
                        log.info("Integr:parseFactorIntegr:Loop:Exprn=" + Exprn);
                        log.info("Integr:parseFactorIntegr:Loop:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                        log.info("Integr:parseFactorIntegr:Loop:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));

//                        if (Exprn.equalsIgnoreCase(DiffWithRespTo)) {
//                            Split_Integr(b.IntegrExpr(), DiffWithRespTo);
//                            Expr_Func_Split_Integr(b.IntegrExpr(), DiffWithRespTo);
//                            String bExpr = FactorsExpr;
//                            String bDiffExpr = FactorsDiffExpr;
//                            x = ApplyFuncRules("parseFactorIntegr", Exprn, a, DiffWithRespTo, b, bExpr, Exprn, "^", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
//                            Exprn = ApplyFuncRulesString("parseFactorIntegr", Exprn, a, DiffWithRespTo, b, bExpr, Exprn, "^", "ExponentRule", DiffWithRespTo, LADiffTermOperator);
//                        }
                        //Copied from LADiffConst
                        if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // 2^3
                            //x = (() -> "" + (Math.pow(Double.parseDouble(a.eval()), Double.parseDouble(b.eval()))));
                            log.info("Integr:parseFactorIntegr:2^3:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^3:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if (b.IntegrExpr().contains("@Constant@")) {
                                //(IsAConstant(x.IntegrExpr(),DiffWithRespTo)) 
                                log.info("Integr:parseFactorIntegr:Exponential Constant:Exprn=" + Exprn + " x.IntegrExpr()=" + x.IntegrExpr());
                                Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                log.info("Integr:parseFactorIntegr:Loop:FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " LADiffTermOperator=" + (char) LADiffTermOperator);
                                String Temp = "";

                                if (LADiffTermOperator == -1) {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                } else {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffTermOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                }
                                log.info("Integr:parseFactorIntegr:Temp=" + Temp + " FactorsExpr=" + FactorsExpr + " LADiffTermOperator=" + (char) LADiffTermOperator);
                                final String MyTerm = Temp;
                                x = (() -> MyTerm);
                                Exprn = MyTerm;
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        } else if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // 2^a
                            //x = (() -> a.eval() + "^" + b.eval());
                            log.info("Integr:parseFactorIntegr:2^a:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^a:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if (variables.containsKey(StrIntegr.substring(bStartPos, bEndPos))) {//Is a valid Independent Constant
                                log.info("Integr:parseFactorIntegr:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                                if ((SavedOperator == '+') || (SavedOperator == '-')) {
                                    x = (() -> "0.0");
                                    Exprn = "0.0";
                                } else if ((SavedOperator == '*') || (SavedOperator == '/')) {
                                    Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                    final String MyTerm = Exprn + "^" + FactorsExpr;
                                    x = (() -> MyTerm);
                                    Exprn = MyTerm;
                                }
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        } else if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // 2^x
                            //x = (() -> "1");
                            log.info("Integr:parseFactorIntegr:2^x:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^x:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // a^b
                            //x = (() -> a.eval() + "^" + b.eval());if (variables.containsKey(Exprn)) {//Is a valid Independent Constant
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                            log.info("Integr:parseFactorIntegr:a^b:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Integr:parseFactorIntegr:a^b:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if ((variables.containsKey(StrIntegr.substring(aStartPos, aEndPos))) && (variables.containsKey(StrIntegr.substring(bStartPos, bEndPos)))) {//Is a valid Independent Constant
                                if ((SavedOperator == '+') || (SavedOperator == '-')) {
                                    x = (() -> "0.0");
                                    Exprn = "0.0";
                                } else if ((SavedOperator == '*') || (SavedOperator == '/')) {
                                    Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                    final String MyTerm = StrIntegr.substring(aStartPos, aEndPos) + "^" + FactorsExpr;
                                    x = (() -> MyTerm);
                                    Exprn = MyTerm;
                                }
                                log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                                log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                            } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                //x = (() -> "1");
                                if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                    LADiffFactorOperator = '*';
                                } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                    LADiffFactorOperator = '/';
                                }
                                log.info("Integr:parseFactorIntegr:x^b:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                                log.info("Integr:parseFactorIntegr:x^b:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                //x = (() -> "1");
                                if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                    LADiffIndConstOperator = '*';
                                } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                    LADiffIndConstOperator = '/';
                                }
                                log.info("Integr:parseFactorIntegr:a^x:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                                log.info("Integr:parseFactorIntegr:a^x:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                                // a^2
                                //x = (() -> StrIntegr.substring(aStartPos, aEndPos) + "^" + StrIntegr.substring(bStartPos, bEndPos));
                                if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                    LADiffIndConstOperator = '*';
                                } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                    LADiffIndConstOperator = '/';
                                }
                                log.info("Integr:parseFactorIntegr:a^2:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                                log.info("Integr:parseFactorIntegr:a^2:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                                if (b.IntegrExpr().contains("@IndependentFactor@")) {//Is a valid ^ Independent Constant
                                    log.info("Integr:parseFactorIntegr:Exponential Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                                    if ((SavedOperator == '+') || (SavedOperator == '-')) {
                                        x = (() -> "0.0");
                                        Exprn = "0.0";
                                    } else if ((SavedOperator == '*') || (SavedOperator == '/') || (SavedOperator == '^')) {
                                        Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                        final String MyTerm = Exprn + "^" + FactorsExpr;
                                        x = (() -> MyTerm);
                                        Exprn = MyTerm;
                                    }
                                }
                                log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                                log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                            }
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // x^2
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffFactorOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffFactorOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:x^2:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                            log.info("Integr:parseFactorIntegr:x^2:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if (b.IntegrExpr().contains("@Constant@")) {
                                //(IsAConstant(x.IntegrExpr(),DiffWithRespTo)) 
                                log.info("Integr:parseFactorIntegr:Exponential Constant:Exprn=" + Exprn + " x.IntegrExpr()=" + x.IntegrExpr());
                                Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                log.info("Integr:parseFactorIntegr:Loop:FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " LADiffTermOperator=" + (char) LADiffTermOperator);
                                String Temp = "";
                                if (LADiffTermOperator == -1) {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                } else if (LADiffFactorOperator == '*') {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) > 0)) {
                                    Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) < 0)) {
                                    Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (-(Double.parseDouble(FactorsExpr) - 1.0));
                                }
                                //else if (LADiffTermOperator=='/') Temp="1.0/" + (Double.parseDouble(FactorsExpr) - 1.0) + (char)LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                //else Temp="1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char)LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                log.info("Integr:parseFactorIntegr:Temp=" + Temp + " FactorsExpr=" + FactorsExpr + " LADiffTermOperator=" + (char) LADiffTermOperator + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                                final String MyTerm = Temp;
                                x = (() -> MyTerm);
                                Exprn = "" + MyTerm;
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        }
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " LADiffTermOperator=" + (char) LADiffTermOperator);
                        log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);

                    } else {
                        if (posIntegr < StrIntegr.length()) {
                            FactorEndPos = posIntegr;
                        } else {
                            FactorEndPos = StrIntegr.length();
                        }
                        if (ParanExprInProgressFlag == true) {
                            log.info("Integr:parseFactorIntegr:ParanExprInProgressFlag:Return Value parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(FactorStartPos, FactorEndPos));
                            log.info("Integr:parseFactorIntegr:ParanExprInProgressFlag:Return Value parseFactorIntegr:Exprn=" + Exprn);
                            final String ParaOutput = Exprn;
                            IntegrExpr y = (() -> ParaOutput);
                            return y;
                        } else {
                            log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(FactorStartPos, FactorEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:Exprn=" + Exprn);
                            return x;
                        }

                    }
                }
            }

            IntegrExpr parseBaseIntegr() {
                boolean ConfirmedConstFlag = false;
                int startPos1 = posIntegr;
                loadCntxtpos++;
                loadoppos++;
                IntegrExpr x = (() -> "");
                log.info("Integr:parseBaseIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                if (eatIntegr('-')) { // unary minus
                    if (LookAheadOp[loadoppos] == LowLevelOperator.Minus) {
                        loadoppos += 2;
                    }
                    IntegrExpr b = parseBaseIntegr();
                    //x = () -> "-" + xx;
                    x = (() -> "-" + b.IntegrExpr());
                    return x;
                } else if (eatIntegr('+')) { // unary plus
                    if (LookAheadOp[loadoppos] == LowLevelOperator.Plus) {
                        loadoppos += 2;
                    }
                    //x = parseBaseIntegr();
                    //return x;
                }
                if (chIntegr == '(') { //parenthesis
                    //Code to handle Expressions:
                    // Set ParanExprFlag only if not a function argument
                    NormalExprnFlag = false;
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    ParanExprInProgressFlag = true;

                    int ParastartPos = posIntegr;
                    IntegrExpr arg = parseParaParaIntegr();
                    eatIntegr(')');
                    log.info("Integr:parseBaseIntegr:Parenthesis: ParanDiffExpr=" + ParanDiffExpr);
                    log.info("Integr:parseBaseIntegr:Parenthesis: ParanExpr=" + ParanExpr);
                    log.info("Integr:parseBaseIntegr:Parenthesis: arg.IntegrExpr()=" + arg.IntegrExpr());
                    log.info("Integr:parseBaseIntegr:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chIntegr=" + (char) chIntegr);
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    ParanExprInProgressFlag = false;
                    int ParaEndpos = posIntegr;
                    //Code to handle Expressions
                    // Unset ParanExprFlag only if not a function argument
                    String name = "";
                    if ((ParastartPos != -1) && (ParastartPos < ParaEndpos) && (ParastartPos <= StrIntegr.length()) && (ParastartPos < StrIntegr.length())) {
                        name = StrIntegr.substring(ParastartPos, ParaEndpos).trim();
                    }
                    final String MyTemp = name;

                    log.info("Integr:parseBaseIntegr:Aligning Pararenthesis:Complete:name=" + name);
                    if (FuncArgFlag == true) {
                        {
                            FuncArgFlag = false;
                            ParanExprFlag = false;
                            //final String MyTemp=name;
                            //Dummy Call
                            x = (() -> (MyTemp + "@" + "FunctionArgument" + "@" + "Factor" + "@" + MyTemp + "@" + "FactorDiff" + "@" + arg.IntegrExpr()));
                            Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                            Expr_Func_Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                            log.info("Integr:parseBaseIntegr:name=" + name + " FactorsExpr=" + FactorsExpr + " x.IntegrExpr()=" + x.IntegrExpr() + " arg.IntegrExpr()=" + arg.IntegrExpr());
                            x = (() -> (MyTemp + "@" + "FunctionArgument" + "@" + "Factor" + "@" + FactorsExpr + "@" + arg.IntegrExpr()));
                            log.info("Integr:parseBaseIntegr:Function: ParanDiffExpr=" + ParanDiffExpr);
                            log.info("Integr:parseBaseIntegr:Function: ParanExpr=" + ParanExpr);
                            log.info("Integr:parseBaseIntegr:Function: x.IntegrExpr()=" + x.IntegrExpr());
                            log.info("Integr:parseBaseIntegr:Function=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chIntegr=" + (char) chIntegr);

                            return x;
                        }
                    } else {
                        ParanExprFlag = false;
                        FuncArgFlag = false;
                        //Dummy Call
                        //final String MyTemp=name;
                        x = (() -> (MyTemp + "@" + "Parenthesis" + "@" + "Factor" + "@" + MyTemp + "@" + "FactorDiff" + "@" + arg.IntegrExpr()));
                        Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                        Expr_Func_Split_Integr(x.IntegrExpr(), DiffWithRespTo);
                        final String ParaExpr = MyTemp + "@" + "Parenthesis" + "@" + "(" + arg.IntegrExpr() + ")";
                        //x = (() -> (name + "@" + "Parenthesis" + "@" + ParaExpr));
                        //x = (() -> "(" + arg.DiffExpr() + ")" );
                        x = (() -> ParaExpr);
                        log.info("Integr:parseBaseIntegr:Parenthesis: ParanDiffExpr=" + ParanDiffExpr);
                        log.info("Integr:parseBaseIntegr:Parenthesis: arg.IntegrExpr()=" + arg.IntegrExpr());
                        log.info("Integr:parseBaseIntegr:Parenthesis: ParanExpr=" + ParanExpr);
                        log.info("Integr:parseBaseIntegr:Parenthesis: x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseBaseIntegr:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chIntegr=" + (char) chIntegr);

                        return x;
                    }
                } else if (IsNumber(chIntegr)) { //numbers
                    int startPos = posIntegr;
                    while (IsNumber(chIntegr)) {
                        nextCharIntegr();
                        if ((chIntegr == 'E') || (chIntegr == 'e')) {
                            nextCharIntegr();
                            if ((chIntegr == '-') || (chIntegr == '+')) {
                                nextCharIntegr();
                            }
                        }
                    }
                    if ((startPos1 >= 0) && (posIntegr >= 0)) {
                        log.info("Integr:parseBaseIntegr:Numbers: xx=" + StrIntegr.substring(startPos1, posIntegr));
                    }

                    String xConst = "";
                    String xx = "";
                    if ((startPos1 >= 0) && (posIntegr >= 0)) {
                        xx = "" + Double.parseDouble(StrIntegr.substring(startPos1, posIntegr));
                    }
                    log.info("Integr:parseBaseIntegr:Numbers: xx=" + xx + " LADiffTermOperator=" + (char) LADiffTermOperator + " GotExpntNumFlag=" + GotExpntNumFlag + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " StrIntegr(posIntegr)=" + StrIntegr.substring(posIntegr));
                    if (GotExpntNumFlag == false) {
                        LookAheadDiffConst MyCoeff;
                        int DiffIntLength = xx.length();
                        log.info("Integr:parseBaseIntegr:Entered LookAheadDiffConst Number:xConst=" + xConst + " xx=" + xx + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        MyCoeff = parseLADiff(StrIntegr.substring(startPos1), startPos, DiffIntLength, "Integration", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                        }
                        if (LADiffTermOperator == '/') {
                            xConst = "1.0/" + xConst;
                        } else {
                            xConst = xConst;
                        }
                    } else {
                        xConst = xx;
                        ConfirmedConstFlag = true;
                        LastLADiffConstCoeffAidPos = -1;
                        ParseLog("Integr", "parseBaseIntegr", "BeforeMap", "StrIntegr", StrIntegr);
                        ParseMap("LookAheadConst:parseLADiff:Integration", StrIntegr.length(), 0, 0, DiffWithRespTo);
                        ParseLog("Integr", "parseBaseIntegr", "Final", "StrIntegr", StrIntegr);
                    }
                    log.info("Integr:parseBaseIntegr:NumberORConstant:xConst=" + xConst + " LADiffTermOperator=" + (char) LADiffTermOperator + " GotExpntNumFlag=" + GotExpntNumFlag + " ConfirmedConstFlag=" + ConfirmedConstFlag + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    if (chIntegr != -1) {
                        //SavedOperator = chIntegr;
                        if ((SavedOperator == ')') && (posIntegr + 1) < StrIntegr.length()) {
                            LookAheadOperator = StrIntegr.charAt(posIntegr + 1);
                        }
                        if (IsNotAnOperator(LookAheadOperator)) {
                            LookAheadOperator = 0;
                        }
                    } else {
                        SavedOperator = 0;
                    }
                    if (ConfirmedConstFlag == true) {
                        final String ConsolidatedConstTerm = xConst;
                        x = () -> (ConsolidatedConstTerm + "@" + "Constant" + "@" + ConsolidatedConstTerm);
                        log.info("Integr:parseBaseIntegr:NumberORConstant:x.IntegrExpr()=" + x.IntegrExpr());
                        return x;
                    } else {
                        final String ConsolidatedIndConstTerm = xConst;
                        x = () -> (ConsolidatedIndConstTerm + "@" + "IndependentFactor" + "@" + ConsolidatedIndConstTerm);
                        log.info("Integr:parseBaseIntegr:IndConstExpression:x.IntegrExpr()=" + x.IntegrExpr());
                        return x;
                    }
                } else if (IsVariable(chIntegr)) {//functions & variables
                    while ((IsVariable(chIntegr)) || (IsNumber(chIntegr))) {
                        nextCharIntegr();
                    }
                    String name = "";
                    if ((startPos1 >= 0) && (posIntegr >= 0)) {
                        name = StrIntegr.substring(startPos1, posIntegr).trim();
                    }
                    if (name.equalsIgnoreCase("")) {
                        log.fatal("Integr:parseBaseIntegr:name null:Name=" + name);
                    }
                    //Code to handle Function
                    if (functionsIntegr.containsKey(name)) {
                        //String func = functionsDiff.get(name);
                        log.info("Integr:parseBaseIntegr:Function Name=" + name);
                        String func = name;
                        FuncArgFlag = true;
                        ParanExprFlag = false;
                        ParenthesisFlag = true;
                        int argstartpos = posIntegr;
                        IntegrExpr arg = parseParaParaIntegr();
                        int argEndpos = posIntegr;
                        FuncArgFlag = false;
                        ParanExprFlag = false;
                        ParenthesisFlag = false;
                        eatIntegr(')');
                        log.info("Integr:parseBaseIntegr:Function Argument:arg.IntegrExpr()=" + arg.IntegrExpr());
                        ParanExprInProgressFlag = false;
                        log.info("Integr:parseBaseIntegr:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " StrIntegr.length()=" + StrIntegr.length());
                        String Factor = StrIntegr.substring(argstartpos, argEndpos).trim();
                        log.info("Integr:parseBaseIntegr:Function Argument:Factor=" + Factor + " String=" + StrIntegr.substring(argstartpos, argEndpos));
                        log.info("Integr:parseBaseIntegr:Aligning Pararenthesis:Started");
                        String[] TempStart = new String[4];
                        TempStart = Factor.split("\\(\\)");
                        log.info("Integr:parseBaseIntegr:TempStart[1]=" + TempStart[0] + " Factor=" + Factor);
                        if ((TempStart.length >= 1) && (TempStart[0] != null) && (!TempStart[0].equalsIgnoreCase(""))) {
                            String[] TempStart1 = new String[4];
                            TempStart1 = TempStart[0].split("\\(\\)");
                            if ((TempStart1.length >= 1) && (TempStart[0] != null) && (!TempStart[0].equalsIgnoreCase(""))) {
                                log.info("Integr:parseBaseIntegr: TempStart1.length=" + TempStart1.length /*+ " TempStart1[1]=" + TempStart1[1]*/);
                                final String Factor1 = TempStart1[0];
                                log.info("Integr:parseBaseIntegr:Aligning Parenthesis:Complete");
                                log.info("Integr:parseBaseIntegr:Function Argument:Factor=" + Factor + " Factor1=" + Factor1);
                                log.info("Integr:parseBaseIntegr:arg.IntegrExpr()=" + arg.IntegrExpr());
                                //Dummy Call
                                final String name1 = name;
                                x = (() -> (name1 + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + Factor1));
                                Split_Integr(arg.IntegrExpr(), DiffWithRespTo);
                                Expr_Func_Split_Integr(arg.IntegrExpr(), DiffWithRespTo);
                                log.info("Integr:parseBaseIntegr:arg.IntegrExpr()=" + arg.IntegrExpr());
                                log.info("Integr:parseBaseIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                                log.info("Integr:parseBaseIntegr:FunctionDiffExpr=" + FunctionDiffExpr);
                                //Code to handle Function Arguments
                                if ((!arg.IntegrExpr().equalsIgnoreCase("")) && ((FunctionDiffExpr == null) || (FunctionDiffExpr.equalsIgnoreCase("")))) {
                                    final String name2 = name;
                                    x = (() -> (name2 + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + arg.IntegrExpr()));
                                } else if ((FunctionDiffExpr != null) && (!FunctionDiffExpr.equalsIgnoreCase(""))) {
                                    final String name2 = name;
                                    x = (() -> (name2 + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + "(" + FunctionDiffExpr + ")"));
                                }
                            } else {
                                final String name3 = name;
                                x = (() -> (name3 + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + "(" + "Unknown" + ")"));
                            }
                            log.info("Integr:parseBaseIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                            log.info("Integr:parseBaseIntegr:FunctionDiffExpr=" + FunctionDiffExpr);
                            log.info("Integr:parseBaseIntegr:arg.IntegrExpr()=" + arg.IntegrExpr());
                        } else {
                            final String name4 = name;
                            x = () -> (name4 + "@" + "Unknown" + "@" + (name4));
                        }
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo))) {
                        log.info("Integr:parseBaseIntegr:Polynomial:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        final String name5 = name;
                        x = () -> (name5 + "@" + "Polynomial" + "@" + (name5));
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo)) && ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Variable) && (LookAheadCntxt[loadCntxtpos + 1] == HighLevelContext.DiffWithRespTo) && (!name.contains("^")))) {
                        log.info("Integr:parseBaseIntegr:Factor:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        final String name6 = name;
                        x = () -> (name6 + "@" + "Factor" + "@" + (name6));
                    } else if (!name.equalsIgnoreCase(DiffWithRespTo)) {//Independent Variable
                        log.info("Integr:parseBaseIntegr:IndependentFactor:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " loadCntxtpos=" + loadCntxtpos + " loadoppos=" + loadoppos + " loadstringpos=" + loadstringpos + " posIntegr=" + posIntegr);
                        final String name7 = name;
                        x = () -> (name7 + "@" + "IndependentFactor" + "@" + (name7));
                    }
                    log.info("Integr:parseBaseIntegr:Variable/Function:name=" + name + " x.IntegrExpr()=" + x.IntegrExpr());
                    return x;
                } else if (chIntegr == ')') {
                    eatIntegr(')');
                    return x;
                } else if ((chIntegr != -1) && (chIntegr != 0) && (IsNumber(chIntegr))) {
                    throw new RuntimeException("Integr:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                } else if ((chIntegr != -1) && (chIntegr != 0) && (chIntegr != '^') && (IsOperator(chIntegr))) {
                    throw new RuntimeException("Integr:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                } else if ((chIntegr != -1) && (chIntegr != 0) && (chIntegr != '^')) {
                    throw new RuntimeException("Integr:parseBase:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                }
                return x;
            }
        }.parseIntegr();
    }

    static void Split(String MyString, String DiffWithRespTo) {
        if ((loadoppos + 2) < LookAhead_Oppos) {
            log.info("Diff:Split:Initial LookAhead Stack: Start Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos]
                    + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos]
                    + " LookAheadOp[loadoppos+2=" + (loadoppos + 2) + "]=" + LookAheadOp[loadoppos + 2]
                    + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos]
                    + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        }
        int Operator = 0;
        String Temp = "";
        String[] Factors = MyString.split("@");

        if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Polynomial"))) {
            FactorsExpr = Factors[2];
            FactorsDiffExpr = Factors[0];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Factor"))) {
            //FactorsExpr = Factors[0];
            FactorsExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
            //FactorsDiffExpr = Factors[2];
            //Diff of x-> "1"
            FactorsDiffExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))
                && ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Numbers)
                && ((LookAheadOp[loadoppos - 1] == LowLevelOperator.Plus)
                || (LookAheadOp[loadoppos - 1] == LowLevelOperator.Minus))
                && ((LookAheadOp[loadoppos + 2] == LowLevelOperator.Plus)
                || (LookAheadOp[loadoppos + 2] == LowLevelOperator.Minus)))
                || ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Null)
                && (LookAheadOp[loadoppos] == LowLevelOperator.Null))) {
            //Diff of constant-> "1"
            //FactorsExpr = Factors[0];
            FactorsExpr = Factors[0] + "*" + DiffWithRespTo;
            //FactorsDiffExpr = Factors[2];
            FactorsDiffExpr = Factors[0] + "*" + DiffWithRespTo;
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
            log.info("Diff:Split:Generic Constant: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);

        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("IndependentFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        }
    }

    static void Expr_Func_Split(String MyString, String DiffWithRespTo) {

        String[] Factors = MyString.split("@");
        int Operator = 0;
        String Temp = "";
        log.info("Diff:Expr_Func_Split:@Input@:split:MyString=" + MyString);
        if ((Factors.length == 6) && (Factors[1].equalsIgnoreCase("FunctionArgument"))) {
            //(name + "@" + "FunctionArgument" + "@" + "Factor" + "@" + name + "@" + "FactorDiff" + "@" +arg.evalDiffExpr()));
            String MyFactor = Factors[0];
            String MyFactorDiff = Factors[5];
            FactorsExpr = MyFactor;
            FactorsDiffExpr = MyFactorDiff;
            FunctionDiffExpr = MyFactorDiff;
        } else if ((Factors.length == 6) && (Factors[1].equalsIgnoreCase("Function"))) {
            //name + "@" + "Function" + "@" + "Factor" + "@" + FactorsExpr + "@" + FunctionDiffExpr));
            //sin@Function@Factor@(2.0*x)@cos(2.0*x)*2.0
            FactorsExpr = Factors[3];
            FunctionExpr = functionsDiff.get(Factors[0]) + Factors[3];
            FactorsDiffExpr = Factors[5];
            String[] StrMyTemp = new String[4];
            if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.contains("\\(")) && (!FactorsDiffExpr.contains("\\)"))) {
                StrMyTemp = FactorsDiffExpr.split("[\\(\\)]");
                log.info("Diff:Expr_Func_Split:@Function@:split:FactorsDiffExpr=" + FactorsDiffExpr + " StrMyTemp.length=" + StrMyTemp.length);
                log.info("Diff:Expr_Func_Split:@Function@:split:MyString=" + MyString);
            }
            if ((StrMyTemp.length >= 2) && (StrMyTemp[1] != null) && (!StrMyTemp[1].contains("\\(")) && (!StrMyTemp[1].contains("\\)"))) {
                FactorsDiffExpr = StrMyTemp[1];
            }
            if (IsAConstant(FactorsDiffExpr, DiffWithRespTo)) {
                if (FactorsDiffExpr.equalsIgnoreCase("1.0")) {
                    FunctionDiffExpr = FunctionExpr;
                } else {
                    FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
                }
                if (FunctionDiffExpr.contains("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replace("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr);
            } else {
                FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;

                if (FunctionDiffExpr.contains("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replace("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Diff:Expr_Func_Split:@Function@:else:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
            }
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Parenthesis"))) {
            //(name + "@" + "Paranthesis" + "@" + ParanDiffExpr) );
            FactorsExpr = Factors[0];
            ParanExpr = Factors[0];
            ParanDiffExpr = Factors[2];
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:FactorsExpr=" + FactorsExpr);
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:ParanExpr=" + ParanExpr);
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:ParanDiffExpr=" + ParanDiffExpr);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        }
    }

    static void Split_Integr(String MyString, String DiffWithRespTo) {
        log.info("Integr:Split:Initial LookAhead Stack: Start Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        log.info("Integr:Split:Initial LookAhead Stack: Start Context:LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos]);
        log.info("Integr:Split:Initial LookAhead Stack: Start Context:LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos]);
        int Operator = 0;
        String Temp = "";
        String[] Factors = MyString.split("@");

        if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Polynomial"))) {
            FactorsExpr = Factors[2];
            FactorsDiffExpr = Factors[0];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Factor"))) {
            //FactorsExpr = Factors[0];
            FactorsExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
            //FactorsDiffExpr = Factors[2];
            //Diff of x-> "1"
            FactorsDiffExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))
                && ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Numbers)
                && ((LookAheadOp[loadoppos - 1] == LowLevelOperator.Plus)
                || (LookAheadOp[loadoppos - 1] == LowLevelOperator.Minus))
                && ((LookAheadOp[loadoppos + 2] == LowLevelOperator.Plus)
                || (LookAheadOp[loadoppos + 2] == LowLevelOperator.Minus)))
                || ((LookAheadCntxt[loadCntxtpos] == HighLevelContext.Null)
                && (LookAheadOp[loadoppos] == LowLevelOperator.Null))) {
            log.info("Integr:Split_Integr:Diff Constant: Start Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
            //Diff of constant-> "1"
            //FactorsExpr = Factors[0];
            FactorsExpr = Factors[0] + "*" + DiffWithRespTo;
            //FactorsDiffExpr = Factors[2];
            FactorsDiffExpr = Factors[0] + "*" + DiffWithRespTo;
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))) {
            log.info("Integr:Split_Integr:Generic Constant: Start Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("IndependentFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        }
    }

    static void Expr_Func_Split_Integr(String MyString, String DiffWithRespTo) {

        String[] Factors = MyString.split("@");
        int Operator = 0;
        String Temp = "";
        if ((Factors.length == 6) && (Factors[1].equalsIgnoreCase("FunctionArgument"))) {
            //(name + "@" + "FunctionArgument" + "@" + "Factor" + "@" + name + "@" + "FactorDiff" + "@" +arg.evalDiffExpr()));
            String MyFactor = Factors[0];
            String MyFactorDiff = Factors[5];
            FactorsExpr = MyFactor;
            FactorsDiffExpr = MyFactorDiff;
            FunctionDiffExpr = MyFactorDiff;
        } else if ((Factors.length == 5) && (Factors[1].equalsIgnoreCase("Function"))) {
            //name + "@" + "Function" + "@" + "Factor" + "@" + FactorsExpr + "@" + FunctionDiffExpr));
            //sin@Function@Factor@(2.0*x)@cos(2.0*x)*2.0
            FactorsExpr = Factors[3];
            FunctionExpr = functionsDiff.get(Factors[0]) + Factors[3];
            String[] StrMyTemp = new String[4];
            if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.contains("\\(")) && (!FactorsDiffExpr.contains("\\)"))) {
                StrMyTemp = FactorsDiffExpr.split("[\\(\\)]");
                log.info("Integr:Expr_Func_Split_Integr:@Function@:split:FactorsDiffExpr=" + FactorsDiffExpr + " StrMyTemp.length=" + StrMyTemp.length);
            }
            if ((StrMyTemp.length >= 2) && (StrMyTemp[1] != null) && (!StrMyTemp[1].contains("\\(")) && (!StrMyTemp[1].contains("\\)"))) {
                FactorsDiffExpr = StrMyTemp[1];
            }
            if (IsAConstant(FactorsDiffExpr, DiffWithRespTo)) {
                FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
                if (FunctionDiffExpr.contains("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replace("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAConstant:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr);
            } else {
                if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.equalsIgnoreCase(""))) {
                    FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
                } else {
                    log.info("Integr:Expr_Func_Split_Integr:@Function@:else:Cannot Integr:Function=" + FunctionExpr);
                    FunctionDiffExpr = FunctionExpr;
                }
                if (FunctionDiffExpr.contains("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replace("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
            }
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Parenthesis"))) {
            //(name + "@" + "Paranthesis" + "@" + ParanDiffExpr) );
            FactorsExpr = Factors[0];
            ParanExpr = Factors[0];
            ParanDiffExpr = Factors[2];
            log.info("Integr:Expr_Func_Split_Integr:@Parenthesis@:else:FactorsExpr=" + FactorsExpr);
            log.info("Integr:Expr_Func_Split_Integr:@Parenthesis@:else:ParanExpr=" + ParanExpr);
            log.info("Integr:Expr_Func_Split_Integr:@Parenthesis@:else:ParanDiffExpr=" + ParanDiffExpr);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        }
    }

    public static DiffExpr ApplyFuncRules(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator) {
        log.info("Diff:ApplyFuncRules:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Diff:ApplyFuncRules:Initial :Term1=" + Term1);
        log.info("Diff:ApplyFuncRules:Initial :Diff1.DiffExpr()=" + Diff1.DiffExpr());
        log.info("Diff:ApplyFuncRules:Initial :Term2=" + Term2);
        log.info("Diff:ApplyFuncRules:Initial :Diff2.DiffExpr()=" + Diff2.DiffExpr());
        log.info("Diff:ApplyFuncRules:Initial :Term3=" + Term3);
        log.info("Diff:ApplyFuncRules:Initial :Term4=" + Term4);
        log.info("Diff:ApplyFuncRules:Initial :StrOperator=" + StrOperator);
        log.info("Diff:ApplyFuncRules:Initial :Rule=" + Rule);
        log.info("Diff:ApplyFuncRules:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Diff:ApplyFuncRules:Initial :TermOperator=" + TermOperator);
        log.info("Diff:ApplyFuncRules:Initial :LADiffTermOperator=" + (char) LADiffTermOperator);
        log.info("Diff:ApplyFuncRules:Initial :LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos]);
        if (loadoppos >= 2) {
            log.info("Diff:ApplyFuncRules:Initial :LookAheadOp[(loadoppos-2)=" + (loadoppos - 2) + "]=" + LookAheadOp[(loadoppos - 2)]);
        }
        if (loadoppos >= 4) {
            log.info("Diff:ApplyFuncRules:Initial :LookAheadOp[(loadoppos-4)=" + (loadoppos - 4) + "]=" + LookAheadOp[(loadoppos - 4)]);
        }
        final String Diff1DiffExpr=Diff1.DiffExpr();
        final String Diff2DiffExpr=Diff2.DiffExpr();
        DiffExpr x = (() -> "");
        boolean loadposDecrFlag = false;
        if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
            x = (() -> Term1);
        } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
            if (Term1.equalsIgnoreCase("")) {
                x = (() -> Diff1DiffExpr);
            } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && (IsAConstant(Term1, DiffWithRespTo)) && ((IsAConstant(Diff1.DiffExpr(), DiffWithRespTo)))) {
                x = (() -> "0.0");
            } else if (!Term1.contains("Parenthesis")) {
                x = (() -> Term1);
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if (!Term4.equalsIgnoreCase("")) {
                x = (() -> Term4 + "*" + Term3 + "^" + Term2);
            } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                x = (() -> Term3 + "^" + Term2);
            } else {
                x = (() -> Term1);
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                    && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                    && (Term1.equalsIgnoreCase(Term2))
                    && (Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                    && (Term1.equalsIgnoreCase(Diff1DiffExpr))
                    && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRules:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                x = (() -> "1.0");
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRules:Rule2:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                x = (() -> Term1);
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRules:Rule3:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                x = (() -> Term1 + StrOperator + Term2);
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRules:Rule4:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                if (StrOperator.equalsIgnoreCase("/")) //Map Sign with "-"
                {
                    x = (() -> "-" + Term1 + StrOperator + DiffWithRespTo + "^2.0");
                } else if (StrOperator.equalsIgnoreCase("\\*")) {
                    x = (() -> Term1 + StrOperator + DiffWithRespTo + "^2.0");
                }
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/"))) {
                log.info("Diff:ApplyFuncRules:Rule5:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                x = (() -> Term1 + StrOperator + Diff1.DiffExpr());
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                log.info("Diff:ApplyFuncRules:Rule6:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                if (!Diff2.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                    x = (() -> Diff1DiffExpr + StrOperator + Diff2DiffExpr);
                } else {
                    x = (() -> Diff1DiffExpr);
                }
            } else {
                log.info("Diff:ApplyFuncRules:Rule7:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                x = (() -> Term2);
            }
        } else if ((Rule.equalsIgnoreCase("SumRule"))
                || (Rule.equalsIgnoreCase("SubtractionRule"))) {
            if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRules:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                if ((StrOperator.equalsIgnoreCase("+")) && ((!Diff2DiffExpr.startsWith("-")) && (!Diff2DiffExpr.startsWith("-")))) {
                    x = (() -> Diff1DiffExpr + StrOperator + Diff2DiffExpr);
                } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2DiffExpr.startsWith("-"))) {
                    x = (() -> Diff1DiffExpr + Diff2DiffExpr);
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2DiffExpr.startsWith("-"))) {
                    if (Diff2DiffExpr.length() > 1) {
                        //Mapped Sign
                        final String TempData = Diff2DiffExpr.substring(1);
                        x = (() -> Diff1DiffExpr + "+" + TempData);
                    }
                } else {
                    x = (() -> Diff1DiffExpr + StrOperator + Diff2DiffExpr);
                }
            } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRules:Got Term Expr:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                x = (() -> Term1 + StrOperator + Term2);
            } else if (((Term2 == null) || (Term2.equalsIgnoreCase(""))) || (Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRules:Got a Constant:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                x = (() -> Term1 + StrOperator + Diff2DiffExpr);
            } else {
                log.info("Diff:ApplyFuncRules:Else:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2DiffExpr + " Diff1.DiffExpr=" + Diff1DiffExpr);
                x = (() -> Diff1DiffExpr);
            }

        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            int j = 0;
            if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                if (loadoppos == 0) {
                    loadoppos++;
                }
                loadposDecrFlag = true;
            } else {
                j = loadoppos;
            }
            log.info("Diff:ApplyFuncRules:ExponentRule :j=" + j + " loadoppos=" + loadoppos + " LookAheadOp[j]=" + LookAheadOp[j]);
            if (j >= 4) {
                log.info("Diff:ApplyFuncRules:ExponentRule :j=" + j + " LookAheadOp[j-2=" + (j - 2) + "]=" + LookAheadOp[j - 2] + " LookAheadOp[j-4=" + (j - 4) + "]=" + LookAheadOp[j - 4]);
            }
            if ((IsAConstant(Term3, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                if ((LookAheadOp[j] == LowLevelOperator.Mult) && (Diff2DiffExpr.contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = (() -> Term3 + "*" + Term2 + "^" + RemoveEDbl((Double.parseDouble(Term3) - 1)));
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule1:x.DiffExpr()=" + x.DiffExpr());
                } else if ((LookAheadOp[j] == LowLevelOperator.Div) && (Diff2DiffExpr.contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = (() -> "-" + Term3 + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1));
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule2:x.DiffExpr()=" + x.DiffExpr());
                } else if ((Diff2DiffExpr.contains("Parenthesis")) && (Double.parseDouble(Term3) > 2.0)) {
                    x = (() -> Term3 + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1));
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule3:x.DiffExpr()=" + x.DiffExpr());
                } else if ((Double.parseDouble(Term3) - 1.0) == 0.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + 1.0);
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = (() -> StrRemoveEDbl(Term3) + "/" + 1.0);
                    }
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule4:x.DiffExpr()=" + x.DiffExpr());
                } else if (Double.parseDouble(Term3) == 2.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2);
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = (() -> StrRemoveEDbl(Term3) + "/" + Term2);
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2);
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2);
                    }
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule5:x.DiffExpr()=" + x.DiffExpr());
                } else {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1) || (j == 0)) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2 + "^" + (Double.parseDouble(Term3) - 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule6:x.DiffExpr()=" + x.DiffExpr());
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = (() -> "-" + StrRemoveEDbl(Term3) + "/" + Term2 + "^" + (Double.parseDouble(Term3) + 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule7:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2 + "^" + (Double.parseDouble(Term3) - 1));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule8:x.DiffExpr()=" + x.DiffExpr());
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = (() -> StrRemoveEDbl(Term3) + "*" + Term2 + "^" + (Double.parseDouble(Term3) - 1));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block1:Rule9:x.DiffExpr()=" + x.DiffExpr());
                    }
                    log.info("Diff:ApplyFuncRules:ExponentRule:Block1:else:Rule1:x.DiffExpr()=" + x.DiffExpr());
                }
                if ((StrOperator.equalsIgnoreCase("^")) && (j >= 4)) {
                    if ((LookAheadOp[j - 4] == LowLevelOperator.Div)) {
                        x = (() -> "-" + (StrRemoveEDbl(Term3)) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule1:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Mult)) {
                        x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule2:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Plus)) {
                        x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule3:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Minus)) {
                        x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule4:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Mult)) {
                        x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule5:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Div)) {
                        if (Double.parseDouble(StrRemoveEDbl(Term3)) > 0) {
                            x = (() -> ("-" + StrRemoveEDbl(Term3)) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        } else if (Double.parseDouble(StrRemoveEDbl(Term3)) < 0) // Mapping "(Diff of /x^-2 maps to *x^2 which in turn maps to to /x^-1
                        {
                            x = (() -> (StrRemoveEDbl(Term3)) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule6:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar)) {
                        if (LADiffTermOperator == -1) {
                            x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule7:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.FuncNvar)) {
                        if (LADiffTermOperator == -1) {
                            x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule8:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Null)) {
                        if (LADiffTermOperator == -1) {
                            x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule9:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[loadoppos] == LowLevelOperator.Numbers)) {
                        if ((Double.parseDouble(Term3) > 0) && (LADiffTermOperator == '*')) {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else if ((Double.parseDouble(Term3) <= 0) && (LADiffTermOperator == '/')) {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        } else {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule10:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.Numbers)) {
                        if ((Double.parseDouble(Term3) > 0) && (LADiffTermOperator == '*')) {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else if ((Double.parseDouble(Term3) <= 0) && (LADiffTermOperator == '/')) {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        } else {
                            if (LADiffTermOperator == -1) {
                                x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                            } else {
                                x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                            }
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule11:x.DiffExpr()=" + x.DiffExpr());
                    } else if ((LookAheadOp[loadoppos] == null)) {
                        if (LADiffTermOperator == -1) {
                            x = (() -> (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        } else {
                            x = (() -> (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0));
                        }
                        log.info("Diff:ApplyFuncRules:ExponentRule:Block2:Rule12:x.DiffExpr()=" + x.DiffExpr());
                    }
                }
                log.info("Diff:ApplyFuncRules:ExponentRule:Block2:x.DiffExpr()=" + x.DiffExpr());
            } else if ((IsAConstant(Term3, DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                if (LADiffTermOperator == -1) {
                    x = (() -> Term3 + "*" + Term2 + "^" + "(" + Term3 + "-" + 1.0 + ")");
                } else {
                    x = (() -> Term3 + (char) LADiffTermOperator + Term2 + "^" + "(" + Term3 + "-" + 1.0 + ")");
                }
                log.info("Diff:ApplyFuncRules:ExponentRule:Block3:Rule1:x.DiffExpr()=" + x.DiffExpr());
            }
        } else if (Rule.equalsIgnoreCase("MappedProductRule")) {
            if ((!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("*"))) {
                String Ux = "";
                String UDashx = "";
                String Vx = "";
                String VDashx = "";
                // Set Ux Parameters
                if (Diff1DiffExpr.contains("Parenthesis")) {
                    Expr_Func_Split(Diff1DiffExpr, DiffWithRespTo);
                    Ux = ParanExpr;
                    UDashx = ParanDiffExpr;

                } else if ((!Diff1DiffExpr.contains("Parenthesis")) && (!IsAConstant(Diff1DiffExpr, DiffWithRespTo))) {
                    Ux = Term1;
                    UDashx = Diff1DiffExpr;
                } else if ((!Diff1DiffExpr.contains("Parenthesis")) && (IsAConstant(Diff1DiffExpr, DiffWithRespTo))) {
                    Ux = Term1;
                    UDashx = Diff1DiffExpr;
                }
                // Set Vx Parameters
                if (Diff2DiffExpr.contains("Parenthesis")) {
                    Expr_Func_Split(Diff2DiffExpr, DiffWithRespTo);
                    Vx = ParanExpr;
                    VDashx = ParanDiffExpr;

                } else if ((!Diff2DiffExpr.contains("Parenthesis")) && (!IsAConstant(Diff2DiffExpr, DiffWithRespTo))) {
                    Vx = Term2;
                    VDashx = Diff2DiffExpr;
                } else if ((!Diff2DiffExpr.contains("Parenthesis")) && (IsAConstant(Diff2DiffExpr, DiffWithRespTo))) {
                    Vx = Term2;
                    VDashx = Diff2DiffExpr;
                }
                final String Vx1 = Vx;
                final String UDashx1 = UDashx;
                final String VDashx1 = VDashx;
                final String Ux1 = Ux;
                if ((IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (Diff2DiffExpr.contains("Parenthesis"))) {
                    x = (() -> UDashx1 + "*" + VDashx1);
                } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                    x = (() -> Ux1 + "*" + VDashx1);
                } else {
                    x = (() -> Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1);
                }
            }
        } else if (Rule.equalsIgnoreCase("ProductRule")) {
            if (!Term3.equalsIgnoreCase("")) {
                x = (() -> Term3 + "*" + Diff1DiffExpr + "+" + Term1 + "*" + Diff2DiffExpr);
            } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = (() -> Diff2DiffExpr);
            } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = (() -> Diff1DiffExpr);
            } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && ((!Diff1DiffExpr.equalsIgnoreCase("1.0")))) {
                if (!Diff2DiffExpr.equalsIgnoreCase("0.0")) {
                    x = (() -> Diff1DiffExpr + "*" + Diff2DiffExpr);
                } else {
                    x = (() -> "0.0");
                }
            } else if ((Diff1DiffExpr.equalsIgnoreCase("")) || ((Diff1DiffExpr.equalsIgnoreCase("1.0")))) {
                x = (() -> Diff2DiffExpr);
            }
        } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
            if ((!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/"))) {
                String Ux = "";
                String UDashx = "";
                String Vx = "";
                String VDashx = "";
                // Set Ux Parameters
                if (Diff1DiffExpr.contains("Parenthesis")) {
                    Expr_Func_Split(Diff1DiffExpr, DiffWithRespTo);
                    Ux = ParanExpr;
                    UDashx = ParanDiffExpr;

                } else if (!Diff1DiffExpr.contains("Parenthesis")) {
                    Ux = Term1;
                    UDashx = Diff1DiffExpr;
                }
                // Set Vx Parameters
                if (Diff2DiffExpr.contains("Parenthesis")) {
                    Expr_Func_Split(Diff2DiffExpr, DiffWithRespTo);
                    Vx = ParanExpr;
                    VDashx = ParanDiffExpr;

                } else if (!Diff2DiffExpr.contains("Parenthesis")) {
                    Vx = Term2;
                    VDashx = Diff2DiffExpr;
                }
                final String Vx1 = Vx;
                final String UDashx1 = UDashx;
                final String VDashx1 = VDashx;
                final String Ux1 = Ux;
                if ((IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (Diff2DiffExpr.contains("Parenthesis"))) {
                    x = (() -> UDashx1 + "/" + Vx1 + "^2" + "*" + VDashx1);
                } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                    x = (() -> Ux1 + "/" + Vx1 + "^2" + "*" + VDashx1);
                } else {
                    x = (() -> "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1);
                }
            }
        } else if (Rule.equalsIgnoreCase("QuotientRule")) {
            if (!Term3.equalsIgnoreCase("")) {
                x = (() -> ("(" + Term2 + "*" + Diff1DiffExpr + "-" + Term1 + "*" + Diff2DiffExpr) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")");
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule1:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if ((!Term2.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff1.DiffExpr().equalsIgnoreCase("1.0"))) {
                x = (() -> 1.0 + "/" + DiffWithRespTo + "^2.0");
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule2:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if ((!Term1.equalsIgnoreCase("")) && (Diff1DiffExpr.equalsIgnoreCase("1.0"))) {
                if (StrOperator.equalsIgnoreCase("*")) {
                    x = (() -> Term1);
                } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term2.equalsIgnoreCase(Term1))) {
                    x = (() -> "1.0" + StrOperator + Term1);
                } else if ((StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(Term1)) && (Diff2DiffExpr.equalsIgnoreCase(Term2)) && (!Term4.equalsIgnoreCase(""))) {
                    x = (() -> "1.0" + StrOperator + Term1);
                } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term4.equalsIgnoreCase(""))) {
                    x = (() -> "1.0" + StrOperator + Term1);
                } else if ((StrOperator.equalsIgnoreCase("/"))) {
                    x = (() -> Term1);
                }
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule3:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = (() -> Diff2DiffExpr);
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule4:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of 1/x term:
                x = (() -> Diff1DiffExpr + "/" + DiffWithRespTo + "^2.0");
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule5:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                if (!Diff2DiffExpr.equalsIgnoreCase("")) {
                    x = (() -> Diff1DiffExpr + "*" + Diff2DiffExpr);
                }
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule6:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if ((!Diff1DiffExpr.equalsIgnoreCase(""))) {
                x = (() -> Diff1DiffExpr + "/" + Diff2DiffExpr);
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule7:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            } else if ((Diff1DiffExpr.equalsIgnoreCase(""))) {
                x = (() -> Diff2DiffExpr);
                log.info("Diff:ApplyFuncRules:QuotientRule:Rule8:x.DiffExpr()=" + x.DiffExpr() + " Term2=" + Term2 + " Diff2.DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
            }
        }

        log.info("Diff:ApplyFuncRules:Final:x.DiffExpr()=" + x.DiffExpr());
        return x;
    }

    public static String ApplyFuncRulesString(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator) {
        log.info("Diff:ApplyFuncRulesString:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Diff:ApplyFuncRulesString:Initial :Term1=" + Term1);
        log.info("Diff:ApplyFuncRulesString:Initial :Diff1.DiffExpr()=" + Diff1.DiffExpr());
        log.info("Diff:ApplyFuncRulesString:Initial :Term2=" + Term2);
        log.info("Diff:ApplyFuncRulesString:Initial :Diff2.DiffExpr()=" + Diff2.DiffExpr());
        log.info("Diff:ApplyFuncRulesString:Initial :Term3=" + Term3);
        log.info("Diff:ApplyFuncRulesString:Initial :Term4=" + Term4);
        log.info("Diff:ApplyFuncRulesString:Initial :StrOperator=" + StrOperator);
        log.info("Diff:ApplyFuncRulesString:Initial :Rule=" + Rule);
        log.info("Diff:ApplyFuncRulesString:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Diff:ApplyFuncRulesString:Initial :TermOperator=" + TermOperator);
        log.info("Diff:ApplyFuncRulesString:Initial :LADiffTermOperator=" + (char) LADiffTermOperator);
        log.info("Diff:ApplyFuncRulesString:Initial :LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos]);
        if (loadoppos >= 2) {
            log.info("Diff:ApplyFuncRulesString:Initial :LookAheadOp[(loadoppos-2)=" + (loadoppos - 2) + "]=" + LookAheadOp[(loadoppos - 2)]);
        }
        if (loadoppos >= 4) {
            log.info("Diff:ApplyFuncRulesString:Initial :LookAheadOp[(loadoppos-4)=" + (loadoppos - 4) + "]=" + LookAheadOp[(loadoppos - 4)]);
        }
        String x = "";
        boolean loadposDecrFlag = false;
        if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
            if (Term1.equalsIgnoreCase("")) {
                x = Diff1.DiffExpr();
            } else {
                x = Term1;
            }
        } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
            if (Term1.equalsIgnoreCase("")) {
                x = Diff1.DiffExpr();
            } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && (IsAConstant(Term1, DiffWithRespTo)) && ((IsAConstant(Diff1.DiffExpr(), DiffWithRespTo)))) {
                x = "0.0";
            } else if (!Term1.contains("Parenthesis")) {
                x = Term1;
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if (!Term4.equalsIgnoreCase("")) {
                if (Term2.equalsIgnoreCase("0.0")) // x^0.0 -> 1.0
                {
                    x = Term4 + "*" + 1.0;
                } else if (Term2.equalsIgnoreCase("1.0")) {
                    x = Term4 + "*" + Term3;// x^1.0 -> x
                } else {
                    x = Term4 + "*" + Term3 + "^" + Term2;
                }
            } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                if (Term2.equalsIgnoreCase("0.0")) {
                    x = "1.0";
                } else if (Term2.equalsIgnoreCase("1.0")) {
                    x = Term3;
                } else {
                    x = Term3 + "^" + Term2;
                }
            } else {
                x = Term1;
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                    && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                    && (Term1.equalsIgnoreCase(Term2))
                    && (Diff1.DiffExpr().equalsIgnoreCase(Diff2.DiffExpr()))
                    && (Term1.equalsIgnoreCase(Diff1.DiffExpr()))
                    && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRulesString:Rule1:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                x = "1.0";
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRulesApplyFuncRulesString:Rule2:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                x = Term1;
            } else if (((Term1 != null) || (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRulesString:Rule3:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                x = Term1 + StrOperator + Term2;
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2.DiffExpr())) && (StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                log.info("Diff:ApplyFuncRulesString:Rule4:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                if (StrOperator.equalsIgnoreCase("/")) //Map Sign with "-"
                {
                    x = "-" + Term1 + StrOperator + DiffWithRespTo + "^2.0";
                } else if (StrOperator.equalsIgnoreCase("*")) {
                    x = Term1 + StrOperator + DiffWithRespTo + "^2.0";
                }
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2.DiffExpr())) && (StrOperator.equalsIgnoreCase("/"))) {
                log.info("Diff:ApplyFuncRulesString:Rule5:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                if (!Diff2.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                    x = Diff1.DiffExpr() + StrOperator + Diff2.DiffExpr();
                } else {
                    x = Diff1.DiffExpr();
                }
            } else {
                log.info("Diff:ApplyFuncRulesString:Rule6:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                x = Term2;
            }
        } else if ((Rule.equalsIgnoreCase("SumRule"))
                || (Rule.equalsIgnoreCase("SubtractionRule"))) {
            if ((!Diff1.DiffExpr().equalsIgnoreCase("")) && (!Diff2.DiffExpr().equalsIgnoreCase("")) && (!Diff2.DiffExpr().equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRulesString:Rule1:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                if ((StrOperator.equalsIgnoreCase("+")) && ((!Diff2.DiffExpr().startsWith("-")) && (!Diff2.DiffExpr().startsWith("-")))) {
                    x = Diff1.DiffExpr() + StrOperator + Diff2.DiffExpr();
                } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2.DiffExpr().startsWith("-"))) {
                    x = Diff1.DiffExpr() + Diff2.DiffExpr();
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2.DiffExpr().startsWith("-"))) {
                    if (Diff2.DiffExpr().length() > 1) {
                        //Mapped Sign
                        final String TempData = Diff2.DiffExpr().substring(1);
                        x = Diff1.DiffExpr() + "+" + TempData;
                    }
                } else {
                    x = Diff1.DiffExpr() + StrOperator + Diff2.DiffExpr();
                }
            } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2.DiffExpr().equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRulesString:Got Term Expr:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr());
                x = Term1 + StrOperator + Term2;
            } else if (((Term2 == null) || (Term2.equalsIgnoreCase(""))) || (Diff2.DiffExpr().equalsIgnoreCase("0.0"))) {
                log.info("Diff:ApplyFuncRulesString:Got a Constant:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr());
                x = Term1 + StrOperator + Diff2.DiffExpr();
            } else {
                log.info("Diff:ApplyFuncRulesString:Else:Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
                x = Diff1.DiffExpr();
            }
        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            int j = 0;
            if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                if (loadoppos == 0) {
                    loadoppos++;
                }
                loadposDecrFlag = true;
            } else {
                j = loadoppos;
            }
            log.info("Diff:ApplyFuncRulesString:ExponentRule :j=" + j + " loadoppos=" + loadoppos);
            if (j >= 4) {
                log.info("Diff:ApplyFuncRulesString:ExponentRule :j=" + j + " LookAheadOp[j-2=" + (j - 2) + "]=" + LookAheadOp[j - 2] + " LookAheadOp[j-4=" + (j - 4) + "]=" + LookAheadOp[j - 4]);
            }
            if ((IsAConstant(Term3, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                if ((LookAheadOp[j] == LowLevelOperator.Mult) && (Diff2.DiffExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = Term3 + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1);
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule1:x=" + x);
                } else if ((LookAheadOp[j] == LowLevelOperator.Div) && (Diff2.DiffExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = "-" + Term3 + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1);
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule2:x=" + x);
                } else if ((Diff2.DiffExpr().contains("Parenthesis")) && (Double.parseDouble(Term3) > 2.0)) {
                    x = Term3 + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1);
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule3:x=" + x);
                } else if ((Double.parseDouble(Term3) - 1.0) == 0.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = StrRemoveEDbl(Term3) + "*" + 1.0;
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = StrRemoveEDbl(Term3) + "/" + 1.0;
                    }
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule4:x=" + x);
                } else if (Double.parseDouble(Term3) == 2.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2;
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = StrRemoveEDbl(Term3) + "/" + Term2;
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2;
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2;
                    }
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule5:x=" + x);
                } else {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (loadoppos == 1) || (j == 0)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule6:x=" + x);
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = "-" + StrRemoveEDbl(Term3) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule7:x=" + x);
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule8:x=" + x);
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:Rule9:x=" + x);
                    }
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block1:else:Rule1:x=" + x);
                }
                if ((StrOperator.equalsIgnoreCase("^")) && (j >= 4)) {
                    if ((LookAheadOp[j - 4] == LowLevelOperator.Div)) {
                        x = "-" + StrRemoveEDbl(Term3) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule1:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Mult)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule2:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Plus)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule3:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Minus)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule4:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Mult)) {
                        x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule5:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Div)) {
                        if (Double.parseDouble(StrRemoveEDbl(Term3)) > 0) {
                            x = ("-" + StrRemoveEDbl(Term3)) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        } else if (Double.parseDouble(StrRemoveEDbl(Term3)) < 0) // Mapping "(Diff of /x^-2 maps to *x^2 which in turn maps to to /x^-1
                        {
                            x = (StrRemoveEDbl(Term3)) + "/" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule6:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar)) {
                        if (LADiffTermOperator == -1) {
                            x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else {
                            x = StrRemoveEDbl(Term3) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule7:x=" + x);
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.FuncNvar)) {
                        if (LADiffTermOperator == -1) {
                            x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else {
                            x = StrRemoveEDbl(Term3) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule8:x=" + x);
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Null)) {
                        if (LADiffTermOperator == -1) {
                            x = StrRemoveEDbl(Term3) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else {
                            x = StrRemoveEDbl(Term3) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule9:x=" + x);
                    } else if ((LookAheadOp[loadoppos] == LowLevelOperator.Numbers)) {
                        if ((Double.parseDouble(Term3) > 0) && (LADiffTermOperator == '*')) {
                            x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else if ((Double.parseDouble(Term3) <= 0) && (LADiffTermOperator == '/')) {
                            x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        } else {
                            if (LADiffTermOperator == -1) {
                                x = (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                            } else {
                                x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                            }
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule10:x=" + x);
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.Numbers)) {
                        if ((Double.parseDouble(Term3) > 0) && (LADiffTermOperator == '*')) {
                            x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else if ((Double.parseDouble(Term3) <= 0) && (LADiffTermOperator == '/')) {
                            x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        } else {
                            if (LADiffTermOperator == -1) {
                                x = (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                            } else {
                                x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                            }
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule11:x=" + x);
                    } else if ((LookAheadOp[loadoppos] == null)) {
                        if (LADiffTermOperator == -1) {
                            x = (StrRemoveEDbl(Term3)) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        } else {
                            x = (StrRemoveEDbl(Term3)) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) - 1.0);
                        }
                        log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:Rule12:x=" + x);
                    }
                    log.info("Diff:ApplyFuncRulesString:ExponentRule:Block2:x=" + x);
                }
                if ((loadposDecrFlag == true) && (LookAheadOp[loadoppos + 2] == LowLevelOperator.Expnt)) {
                    loadoppos += 2;
                }
            } else if ((IsAConstant(Term3, DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                if (LADiffTermOperator == -1) {
                    x = Term3 + "*" + Term2 + "^" + "(" + Term3 + "-" + 1.0 + ")";
                } else {
                    x = Term3 + (char) LADiffTermOperator + Term2 + "^" + "(" + Term3 + "-" + 1.0 + ")";
                }
                log.info("Diff:ApplyFuncRulesString:ExponentRule:Block3:Rule1:x=" + x);
            }
        } else if (Rule.equalsIgnoreCase("MappedProductRule")) {
            if ((!Diff1.DiffExpr().equalsIgnoreCase(Diff2.DiffExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                String Ux = "";
                String UDashx = "";
                String Vx = "";
                String VDashx = "";
                // Set Ux Parameters
                if (Diff1.DiffExpr().contains("Parenthesis")) {
                    Expr_Func_Split(Diff1.DiffExpr(), DiffWithRespTo);
                    Ux = ParanExpr;
                    UDashx = ParanDiffExpr;

                } else if ((!Diff1.DiffExpr().contains("Parenthesis")) && (!IsAConstant(Diff1.DiffExpr(), DiffWithRespTo))) {
                    Ux = Term1;
                    UDashx = Diff1.DiffExpr();
                } else if ((!Diff1.DiffExpr().contains("Parenthesis")) && (IsAConstant(Diff1.DiffExpr(), DiffWithRespTo))) {
                    Ux = Term1;
                    UDashx = Diff1.DiffExpr();
                }
                // Set Vx Parameters
                if (Diff2.DiffExpr().contains("Parenthesis")) {
                    Expr_Func_Split(Diff2.DiffExpr(), DiffWithRespTo);
                    Vx = ParanExpr;
                    VDashx = ParanDiffExpr;

                } else if ((!Diff2.DiffExpr().contains("Parenthesis")) && (!IsAConstant(Diff2.DiffExpr(), DiffWithRespTo))) {
                    Vx = Term2;
                    VDashx = Diff2.DiffExpr();
                } else if ((!Diff2.DiffExpr().contains("Parenthesis")) && (IsAConstant(Diff2.DiffExpr(), DiffWithRespTo))) {
                    Vx = Term2;
                    VDashx = Diff2.DiffExpr();
                }
                final String Vx1 = Vx;
                final String UDashx1 = UDashx;
                final String VDashx1 = VDashx;
                final String Ux1 = Ux;
                if ((IsAConstant(Diff1.DiffExpr(), DiffWithRespTo)) && (Diff2.DiffExpr().contains("Parenthesis"))) {
                    x = UDashx1 + "*" + VDashx1;
                } else if ((IsAConstant(Diff2.DiffExpr(), DiffWithRespTo)) && (Diff1.DiffExpr().contains("Parenthesis"))) {
                    x = Ux1 + "*" + VDashx1;
                } else {
                    x = Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1;
                }
            }
        } else if (Rule.equalsIgnoreCase("ProductRule")) {
            if (!Term3.equalsIgnoreCase("")) {
                x = Term3 + "*" + Diff1.DiffExpr() + "+" + Term1 + "*" + Diff2.DiffExpr();
            } else if (Diff1.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = Diff2.DiffExpr();
            } else if (Diff2.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = Diff1.DiffExpr();
            } else if ((!Diff1.DiffExpr().equalsIgnoreCase("")) && ((!Diff1.DiffExpr().equalsIgnoreCase("1.0")))) {
                if (!Diff2.DiffExpr().equalsIgnoreCase("0.0")) {
                    x = Diff1.DiffExpr() + "*" + Diff2.DiffExpr();
                } else {
                    x = "0.0";
                }
            } else if ((Diff1.DiffExpr().equalsIgnoreCase("")) || ((Diff1.DiffExpr().equalsIgnoreCase("1.0")))) {
                x = Diff2.DiffExpr();
            }
        } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
            if ((!Diff1.DiffExpr().equalsIgnoreCase(Diff2.DiffExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                String Ux = "";
                String UDashx = "";
                String Vx = "";
                String VDashx = "";
                // Set Ux Parameters
                if (Diff1.DiffExpr().contains("Parenthesis")) {
                    Expr_Func_Split(Diff1.DiffExpr(), DiffWithRespTo);
                    Ux = ParanExpr;
                    UDashx = ParanDiffExpr;

                } else if (!Diff1.DiffExpr().contains("Parenthesis")) {
                    Ux = Term1;
                    UDashx = Diff1.DiffExpr();
                }
                // Set Vx Parameters
                if (Diff2.DiffExpr().contains("Parenthesis")) {
                    Expr_Func_Split(Diff2.DiffExpr(), DiffWithRespTo);
                    Vx = ParanExpr;
                    VDashx = ParanDiffExpr;

                } else if (!Diff2.DiffExpr().contains("Parenthesis")) {
                    Vx = Term2;
                    VDashx = Diff2.DiffExpr();
                }
                final String Vx1 = Vx;
                final String UDashx1 = UDashx;
                final String VDashx1 = VDashx;
                final String Ux1 = Ux;
                if ((IsAConstant(Diff1.DiffExpr(), DiffWithRespTo)) && (Diff2.DiffExpr().contains("Parenthesis"))) {
                    x = UDashx1 + "/" + Vx1 + "^2" + "*" + VDashx1;
                } else if ((IsAConstant(Diff2.DiffExpr(), DiffWithRespTo)) && (Diff1.DiffExpr().contains("Parenthesis"))) {
                    x = Ux1 + "/" + Vx1 + "^2" + "*" + VDashx1;
                } else {
                    x = "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1;
                }
            }
        } else if (Rule.equalsIgnoreCase("QuotientRule")) {
            if (!Term3.equalsIgnoreCase("")) {
                x = ("(" + Term2 + "*" + Diff1.DiffExpr() + "-" + Term1 + "*" + Diff2.DiffExpr()) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")";
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule1:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if ((!Term2.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff1.DiffExpr().equalsIgnoreCase("1.0"))) {
                x = 1.0 + "/" + DiffWithRespTo + "^2.0";
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule2:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if ((!Term1.equalsIgnoreCase("")) && (Diff1.DiffExpr().equalsIgnoreCase("1.0"))) {
                if (StrOperator.equalsIgnoreCase("*")) {
                    x = Term1;
                } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term2.equalsIgnoreCase(Term1))) {
                    x = "1.0" + StrOperator + Term1;
                } else if ((StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(Term1)) && (Diff2.DiffExpr().equalsIgnoreCase(Term2)) && (!Term4.equalsIgnoreCase(""))) {
                    x = "1.0" + StrOperator + Term1;
                } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term4.equalsIgnoreCase(""))) {
                    x = "1.0" + StrOperator + Term1;
                } else if ((StrOperator.equalsIgnoreCase("/"))) {
                    x = Term1;
                }
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule3:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if (Diff1.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = Diff2.DiffExpr();
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule4:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if (Diff2.DiffExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of 1/x term:
                x = Diff1.DiffExpr() + "/" + DiffWithRespTo + "^2.0";
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule5:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if ((!Diff1.DiffExpr().equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(Diff2.DiffExpr()))) {
                if (!Diff2.DiffExpr().equalsIgnoreCase("")) {
                    x = Diff1.DiffExpr() + "*" + Diff2.DiffExpr();
                }
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule6:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if ((!Diff1.DiffExpr().equalsIgnoreCase(""))) {
                x = Diff1.DiffExpr() + "/" + Diff2.DiffExpr();
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule7:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            } else if ((Diff1.DiffExpr().equalsIgnoreCase(""))) {
                x = Diff2.DiffExpr();
                log.info("Diff:ApplyFuncRulesString:QuotientRule:Rule8:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr()=" + Diff2.DiffExpr() + " Diff1.DiffExpr()=" + Diff1.DiffExpr());
            }
        }
        log.info("Diff:ApplyFuncRulesString:Final:String:x=" + x);
        return x;
    }

    public static IntegrExpr ApplyFuncRules(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator) {
        log.info("Integr:ApplyFuncRules:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Integr:ApplyFuncRules:Initial :Term1=" + Term1);
        log.info("Integr:ApplyFuncRules:Initial :Diff1.IntegrExpr()=" + Diff1.IntegrExpr());
        log.info("Integr:ApplyFuncRules:Initial :Term2=" + Term2);
        log.info("Integr:ApplyFuncRules:Initial :Diff2.IntegrExpr()=" + Diff2.IntegrExpr());
        log.info("Integr:ApplyFuncRules:Initial :Term3=" + Term3);
        log.info("Integr:ApplyFuncRules:Initial :Term4=" + Term4);
        log.info("Integr:ApplyFuncRules:Initial :StrOperator=" + StrOperator);
        log.info("Integr:ApplyFuncRules:Initial :Rule=" + Rule);
        log.info("Integr:ApplyFuncRules:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Integr:ApplyFuncRules:Initial :TermOperator=" + (char) TermOperator);
        log.info("Integr:ApplyFuncRules:Initial :LADiffTermOperator=" + (char) LADiffTermOperator);
        log.info("Integr:ApplyFuncRules:Initial :LookAheadOp[" + loadoppos + "]=" + LookAheadOp[loadoppos]);
        if (loadoppos >= 2) {
            log.info("Integr:ApplyFuncRules:Initial :LookAheadOp[(loadoppos-2)=" + (loadoppos - 2) + "]=" + LookAheadOp[(loadoppos - 2)]);
        }
        if (loadoppos >= 4) {
            log.info("Integr:ApplyFuncRules:Initial :LookAheadOp[(loadoppos-4)=" + (loadoppos - 4) + "]=" + LookAheadOp[(loadoppos - 4)]);
        }
        IntegrExpr x = (() -> "");
        boolean loadposDecrFlag = false;
        int Supported = 1;
        if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
            x = (() -> Term1);
        } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
            if (Term1.equalsIgnoreCase("")) {
                x = (() -> Diff1.IntegrExpr());
                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule1 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((Term1.contains(DiffWithRespTo)) && (Term1.contains("ln"))) {
                x = (() -> Term1);
                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule2 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (LADiffTermOperator == '*')) {
                x = (() -> "1.0/2.0" + StrOperator + Term1 + "^2.0");
                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule3 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (LADiffTermOperator == '/')) {
                x = (() -> "ln(" + Term1 + ")");

                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule4 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((StrOperator.equalsIgnoreCase("/")) && (LADiffTermOperator == '/')) {
                x = (() -> Term1);
                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule5 :x=" + x);
            } else {
                x = (() -> Term1);
                log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule6 :x.IntegrExpr()=" + x.IntegrExpr() + " loadoppos=" + loadoppos);
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if (!Term4.equalsIgnoreCase("")) {
                x = (() -> Term4 + StrOperator + Term3 + "^" + Term2);
            } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                x = (() -> Term3 + "^" + Term2);
            } else {
                x = (() -> Term1);
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                    && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                    && (Term1.equalsIgnoreCase(Term2))
                    && (Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr()))
                    && (Term1.equalsIgnoreCase(Diff1.IntegrExpr()))
                    && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                x = (() -> "1.0");
                log.info("Integr:ApplyFuncRules:ConstantRule :Rule1 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                if (Term3.equalsIgnoreCase("")) {
                    log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.1 :SavedOperator=" + (char) SavedOperator);
                    if (((SavedOperator == '*') || (SavedOperator == 0) || (SavedOperator == -1)) && (!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                        if ((SavedOperator == '*')) {
                            x = (() -> Term1 + (char) SavedOperator + Term2);
                            log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.1.1 :x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                            if (StrOperator.equalsIgnoreCase("*")) {
                                x = (() -> Term2 + "/2.0" + "*" + Term1 + "^2.0");
                            } else {
                                if (Term2.equalsIgnoreCase("1.0")) {
                                    x = (() -> "ln(" + Term1 + ")");
                                } else {
                                    x = (() -> Term2 + "*" + "ln(" + Term1 + ")");
                                }
                            }
                            log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.1.2 :x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            if (Term1.startsWith("1.0")) {
                                final String Temp = Term1.replaceFirst("1.0", "");
                                x = (() -> Term2 + Temp);
                            } else {
                                x = (() -> Term2 + "*" + Term1);
                            }
                            log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.1.3 :x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                        x = (() -> Term1 + StrOperator + Term2);
                    }
                    log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.1 :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (Term2.equalsIgnoreCase(DiffWithRespTo)) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + "^2.0*1.0/2.0" + Term3);
                    log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.2 :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (Diff1.IntegrExpr().equalsIgnoreCase(Term2)) {
                    if ((Term1.equalsIgnoreCase(Diff2.IntegrExpr())) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                        x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr() + "^2.0*1.0/2.0");
                        log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.3.1 :x.IntegrExpr()=" + x.IntegrExpr());
                    } else {
                        x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                        log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.3.2 :x.IntegrExpr()=" + x.IntegrExpr());
                    }
                } else {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + Term3);
                    log.info("Integr:ApplyFuncRules:ConstantRule :Rule2.4 :x.IntegrExpr()=" + x.IntegrExpr());
                }
                log.info("Integr:ApplyFuncRules:ConstantRule :Rule2 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2.IntegrExpr()))) {
                x = (() -> Diff1.IntegrExpr());
                log.info("Integr:ApplyFuncRules:ConstantRule :Rule3 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (IsAConstant(Term1, DiffWithRespTo)) {
                x = (() -> Term1 + StrOperator + Term2);
                log.info("Integr:ApplyFuncRules:ConstantRule :Rule4 :x.IntegrExpr()=" + x.IntegrExpr());
            } else {
                x = (() -> Term2);
                log.info("Integr:ApplyFuncRules:ConstantRule :Rule5 :x.IntegrExpr()=" + x.IntegrExpr());
            }
        } else if ((Rule.equalsIgnoreCase("SumRule"))
                || (Rule.equalsIgnoreCase("SubtractionRule"))) {
            if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule1 :x.IntegrExpr()=" + x.IntegrExpr() + " loadoppos=" + loadoppos);
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = (() -> Term1 + StrOperator + Term2);
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule2 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = (() -> Diff1.IntegrExpr());
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule3 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End)) {
                if ((Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term2.contains(DiffWithRespTo)) && (!Term3.contains(DiffWithRespTo + "^"))) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + "^2.0" + "*1/2.0");
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule4A :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (!Term2.equalsIgnoreCase("")) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo);
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule4B :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (Term2.equalsIgnoreCase("")) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule4C :x.IntegrExpr()=" + x.IntegrExpr());
                }
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule4 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!Term2.contains(DiffWithRespTo)) && (IsAConstant(Term2, DiffWithRespTo))) {
                x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule5 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                if (Diff2.IntegrExpr().equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo);
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule6A :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (Diff2.IntegrExpr().equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo + "^2.0" + "*1.0/2.0");
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule6B :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (!Diff2.IntegrExpr().contains(DiffWithRespTo)) {
                    x = (() -> Term1 + StrOperator + Diff2.IntegrExpr() + "*" + DiffWithRespTo);
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule6C :x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                    log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule6D :x.IntegrExpr()=" + x.IntegrExpr());
                }
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule6 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))) {
                x = (() -> Term1 + StrOperator + Term2);
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule7 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End)) {
                x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:SumRule or SubtractionRule :Rule8 :x.IntegrExpr()=" + x.IntegrExpr());
            }
            //Drops Term2 && Diff2.IntegrExpr()

        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            int j = 0;
            if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                if (loadoppos == 0) {
                    loadoppos++;
                }
                loadposDecrFlag = true;
            } else {
                j = loadoppos;
            }
            log.info("Integr:ApplyFuncRules:j=" + j + " loadoppos=" + loadoppos + " LookAheadOp[j]=" + LookAheadOp[j]);
            if (j >= 4) {
                log.info("Integr:ApplyFuncRules:j=" + j + " LookAheadOp[j - 4]=" + LookAheadOp[j - 4]);
            }
            if ((IsAConstant(Term3, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                if ((LookAheadOp[j] == LowLevelOperator.Mult) && (Diff2.IntegrExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1));
                    log.info("Integr:ApplyFuncRules:ExponentRule1:x.IntegrExpr()=" + x.IntegrExpr());
                } else if ((LookAheadOp[j] == LowLevelOperator.Div) && (Diff2.IntegrExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = (() -> "-1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1));
                    log.info("Integr:ApplyFuncRules:ExponentRule2:x.IntegrExpr()=" + x.IntegrExpr());
                } else if ((Diff2.IntegrExpr().contains("Parenthesis")) && (Double.parseDouble(Term3) > 2.0)) {
                    x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1));
                    log.info("Integr:ApplyFuncRules:ExponentRule3:x.IntegrExpr()=" + x.IntegrExpr());
                } else if ((Double.parseDouble(Term3) - 1.0) == 0.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + 1.0);
                        log.info("Integr:ApplyFuncRules:ExponentRule4.1:x.IntegrExpr()=" + x.IntegrExpr());
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "/" + 1.0);
                        log.info("Integr:ApplyFuncRules:ExponentRule4.2:x.IntegrExpr()=" + x.IntegrExpr());
                    }
                    log.info("Integr:ApplyFuncRules:ExponentRule4:x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1) || (j == 0)) {
                        x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                        log.info("Integr:ApplyFuncRules:ExponentRule5.1:x.IntegrExpr()=" + x.IntegrExpr());
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = (() -> "-1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                        log.info("Integr:ApplyFuncRules:ExponentRule5.2:x.IntegrExpr()=" + x.IntegrExpr());
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:ExponentRule5.3:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1));
                            log.info("Integr:ApplyFuncRules:ExponentRule5.4:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1));
                        log.info("Integr:ApplyFuncRules:ExponentRule5.5:x.IntegrExpr()=" + x.IntegrExpr());
                    }
                    log.info("Integr:ApplyFuncRules:ExponentRule5:x.IntegrExpr()=" + x.IntegrExpr());
                }
                if ((j >= 4) && (StrOperator.equalsIgnoreCase("^"))) {
                    if ((LookAheadOp[j - 4] == LowLevelOperator.Div)) {
                        if ((-Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule1:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if ((-Double.parseDouble(Term3) + 1) < 0.0) {
                            //Maps Div to Mult Operator
                            x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule2:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Mult)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule3:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule4:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Null)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule5:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule6:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j] == null)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule7:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule8:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Plus)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule9:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule10:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Minus)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule11:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule12:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Mult)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule13:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule14:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Div)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule15:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule16:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule17:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '*') {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule18:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '/') {
                            x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule19:x.IntegrExpr()=" + x.IntegrExpr());
                        } else {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule20:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                        log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rules17-20:x.IntegrExpr()=" + x.IntegrExpr());
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule21:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '*') {
                            x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule22:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '/') {
                            x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule23:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Expnt)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = (() -> "ln(" + Term2 + ")");
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule24:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '*') {
                            //Mapping '/'(Division) to a '*' (Multiplication)
                            if (TermOperator == '/') {
                                x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            } else if (TermOperator == '*') {
                                x = (() -> "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0));
                            }
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule25:x.IntegrExpr()=" + x.IntegrExpr());
                        } else if (LADiffTermOperator == '/') {
                            x = (() -> "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0));
                            log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule26:x.IntegrExpr()=" + x.IntegrExpr());
                        }
                    }
                }
                if ((loadposDecrFlag == true) && (LookAheadOp[loadoppos + 2] == LowLevelOperator.Expnt)) {
                    loadoppos += 2;
                }
            } else if ((IsAConstant(Term3, DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                x = x = (() -> "1.0/" + Term3 + "+" + 1.0 + "*" + Term2 + "^" + Term3 + "+" + 1.0);
                log.info("Integr:ApplyFuncRules:Exponent:LookAheadOp Block:Rule27:x.IntegrExpr()=" + x.IntegrExpr());
            }
        } else if (Rule.equalsIgnoreCase("MappedProductRule")) {
            if (Supported == 1) {
                if ((!Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                    String Ux = "";
                    String UDashx = "";
                    String Vx = "";
                    String VDashx = "";
                    // Determine Order ParanExpr
                    OrderVar expOrder = parseOrder(ParanExpr, DiffWithRespTo);
                    String myOrderUx = expOrder.eval();
                    log.info("main: " + DiffWithRespTo + "=" + x + " ParanExpr=" + ParanExpr + " myOrderUx=" + myOrderUx);
                    // Determine Order Term1
                    expOrder = parseOrder(Term1, DiffWithRespTo);
                    String myOrderTerm1 = expOrder.eval();
                    log.info("main: " + DiffWithRespTo + "=" + x + " Term1=" + Term1 + " myOrderTerm1=" + myOrderTerm1);
                    // Determine Order Term2
                    expOrder = parseOrder(Term2, DiffWithRespTo);
                    String myOrderTerm2 = expOrder.eval();
                    log.info("main: " + DiffWithRespTo + "=" + x + " Term1=" + Term1 + " myOrderTerm2=" + myOrderTerm2);

                    //Choose Ux 
                    // Choose Vx
                    // Set Ux Parameters
                    if (Diff1.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        Ux = ParanExpr;
                        UDashx = ParanDiffExpr;

                    } else if (!Diff1.IntegrExpr().contains("Parenthesis")) {
                        Ux = Term1;
                        UDashx = Diff2.IntegrExpr();
                    }
                    // Set Vx Parameters
                    if (Diff2.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff2.IntegrExpr(), DiffWithRespTo);
                        Vx = ParanExpr;
                        VDashx = ParanDiffExpr;

                    } else if (!Diff2.IntegrExpr().contains("Parenthesis")) {
                        Vx = Term2;
                        VDashx = Diff2.IntegrExpr();
                    }
                    final String Vx1 = Vx;
                    final String UDashx1 = UDashx;
                    final String VDashx1 = VDashx;
                    final String Ux1 = Ux;
                    x = (() -> Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1);
                }
            }

        } else if (Rule.equalsIgnoreCase("ProductRule")) {
            if (!Term3.equalsIgnoreCase("")) {
                ;//x = (() -> Term3 + "*" + Diff1.IntegrExpr() + "+" + Term1 + "*" + Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:ProductRule:Maths OR This Software does not support :" + Term3);
                log.info("Integr:ApplyFuncRules:Rule1 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (Diff1.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = (() -> Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:Rule2 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))) {
                //Differentiation of x term:
                x = (() -> Diff1.IntegrExpr() + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0");
                log.info("Integr:ApplyFuncRules:Rule3 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))) {
                //Differentiation of x term with Independent Constant:
                x = (() -> Diff1.IntegrExpr() + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0");
                //x = (() -> Diff1.IntegrExpr());
                log.info("Integr:ApplyFuncRules:Rule4 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (!Diff1.IntegrExpr().equalsIgnoreCase("")) {
                if ((((IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))
                        || ((!IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))
                        && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))
                        || ((!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))) {
                    if (Diff1.IntegrExpr().contains("@IndependentFactor@")) {
                        Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        final String TempStrDiff1 = FactorsDiffExpr;
                        x = (() -> TempStrDiff1);
                    } else if (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                        x = (() -> Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr() + "^2.0");
                    } else {
                        x = (() -> Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr() + "*" + DiffWithRespTo);
                    }
                    log.info("Integr:ApplyFuncRules:Rule5A :x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    if (Diff2.IntegrExpr().startsWith("1.0")) {
                        String Temp = Diff2.IntegrExpr().replaceFirst("1.0", "");
                        if ((Diff1.IntegrExpr().endsWith("/")) && (Term1.startsWith("/"))) {
                            String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = (() -> Temp1 + Term1 + Temp);
                            log.info("Integr:ApplyFuncRules:Rule5B.1 :x.IntegrExpr())=" + x.IntegrExpr());
                        } else if (Diff1.IntegrExpr().endsWith("/")) {
                            String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = (() -> Temp1 + Temp);
                            log.info("Integr:ApplyFuncRules:Rule5B.2 :x.IntegrExpr()=" + x.IntegrExpr());
                        } //Need to uncomment below code
                        else {
                            //String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = (() -> Diff1.IntegrExpr() + Temp);
                            log.info("Integr:ApplyFuncRules:Rule5B.3 :x.IntegrExpr()=" + x.IntegrExpr());
                        }
                        log.info("Integr:ApplyFuncRules:Rule5B :x.IntegrExpr()=" + x.IntegrExpr());
                    } else {
                        x = (() -> Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr());
                        log.info("Integr:ApplyFuncRules:Rule5C :x.IntegrExpr()=" + x.IntegrExpr());
                    }
                }
                log.info("Integr:ApplyFuncRules:Rule5 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if (Diff1.IntegrExpr().equalsIgnoreCase("")) {
                x = (() -> Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:Rule6 :x.IntegrExpr()=" + x.IntegrExpr());
            }
        } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
            if (Supported == 1) {
                if ((!Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr())) && (StrOperator.equalsIgnoreCase("/"))) {
                    String Ux = "";
                    String UDashx = "";
                    String Vx = "";
                    String VDashx = "";
                    // Set Ux Parameters
                    if (Diff1.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        Ux = ParanExpr;
                        UDashx = ParanDiffExpr;

                    } else if (!Diff1.IntegrExpr().contains("Parenthesis")) {
                        Ux = Term1;
                        UDashx = Diff2.IntegrExpr();
                    }
                    // Set Vx Parameters
                    if (Diff2.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff2.IntegrExpr(), DiffWithRespTo);
                        Vx = ParanExpr;
                        VDashx = ParanDiffExpr;

                    } else if (!Diff2.IntegrExpr().contains("Parenthesis")) {
                        Vx = Term2;
                        VDashx = Diff2.IntegrExpr();
                    }
                    final String Vx1 = Vx;
                    final String UDashx1 = UDashx;
                    final String VDashx1 = VDashx;
                    final String Ux1 = Ux;
                    x = (() -> "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1);
                }
            } else {
                log.info("Integr:ApplyFuncRules:MappedQuotientRule:Maths OR This Software does not support :(" + Diff1.IntegrExpr() + ") / (" + Diff2.IntegrExpr() + ")");
            }
        } else if (Rule.equalsIgnoreCase("QuotientRule")) {

            if (!Term3.equalsIgnoreCase("")) {
                x = (() -> ("(" + Term2 + "*" + Diff1.IntegrExpr() + "-" + Term1 + "*" + Diff2.IntegrExpr()) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")");
                log.info("Integr:ApplyFuncRules:QuotientRule:Rule1 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo))) {
                if (Diff1.IntegrExpr().equalsIgnoreCase("1.0")) {
                    x = (() -> "ln(" + DiffWithRespTo + ")");
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule2A :x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    x = (() -> Diff1.IntegrExpr() + "*" + "ln(" + DiffWithRespTo + ")");
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule2B :x.IntegrExpr()=" + x.IntegrExpr());
                }
                log.info("Integr:ApplyFuncRules:QuotientRule:Rule2 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff1.IntegrExpr().equalsIgnoreCase("1.0"))) {
                if (Diff2.IntegrExpr().startsWith("1.0")) {
                    String Temp = Diff2.IntegrExpr().replaceFirst("1.0", "");
                    x = (() -> Diff1.IntegrExpr() + Temp);
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule3A :x.IntegrExpr()=" + x.IntegrExpr());
                } else if (!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) {
                    x = (() -> Diff1.IntegrExpr() + Diff2.IntegrExpr());
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule3B :x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    x = (() -> Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr());
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule3C :x.IntegrExpr()=" + x.IntegrExpr());
                }
                log.info("Integr:ApplyFuncRules:QuotientRule:Rule3 :x.IntegrExpr()=" + x.IntegrExpr());
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().contains(Diff1.IntegrExpr() + "/"))) {
                if (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                    x = (() -> "ln(" + DiffWithRespTo + ")");
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule4A :x.IntegrExpr()=" + x.IntegrExpr());
                } else {
                    x = (() -> Diff1.IntegrExpr() + "/" + Diff2.IntegrExpr());
                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule4B :x.IntegrExpr()=" + x.IntegrExpr());
                }
//                else {
//                    x = (() -> Diff2.IntegrExpr());
//                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule4C :x.IntegrExpr()=" + x.IntegrExpr());
//                }
            } else {
                x = (() -> Diff2.IntegrExpr());
                log.info("Integr:ApplyFuncRules:QuotientRule:Rule4 :x.IntegrExpr()=" + x.IntegrExpr());
            }
        }

        log.info("Integr:ApplyFuncRules:Final:x.IntegrExpr()=" + x.IntegrExpr());
        return x;
    }

    public static String ApplyFuncRulesString(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator) {
        log.info("Integr:ApplyFuncRulesString:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Integr:ApplyFuncRulesString:Initial :Term1=" + Term1);
        log.info("Integr:ApplyFuncRulesString:Initial :Diff1.IntegrExpr()=" + Diff1.IntegrExpr());
        log.info("Integr:ApplyFuncRulesString:Initial :Term2=" + Term2);
        log.info("Integr:ApplyFuncRulesString:Initial :Diff2.IntegrExpr()=" + Diff2.IntegrExpr());
        log.info("Integr:ApplyFuncRulesString:Initial :Term3=" + Term3);
        log.info("Integr:ApplyFuncRulesString:Initial :Term4=" + Term4);
        log.info("Integr:ApplyFuncRulesString:Initial :StrOperator=" + StrOperator);
        log.info("Integr:ApplyFuncRulesString:Initial :Rule=" + Rule);
        log.info("Integr:ApplyFuncRulesString:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Integr:ApplyFuncRulesString:Initial :TermOperator=" + (char) TermOperator);
        log.info("Integr:ApplyFuncRulesString:Initial :LADiffTermOperator=" + (char) LADiffTermOperator);
        log.info("Integr:ApplyFuncRulesString:Initial :LookAheadOp[" + loadoppos + "]=" + LookAheadOp[loadoppos]);
        if (loadoppos >= 2) {
            log.info("Integr:ApplyFuncRulesString:Initial :LookAheadOp[(loadoppos-2)=" + (loadoppos - 2) + "]=" + LookAheadOp[(loadoppos - 2)]);
        }
        if (loadoppos >= 4) {
            log.info("Integr:ApplyFuncRulesString:Initial :LookAheadOp[(loadoppos-4)=" + (loadoppos - 4) + "]=" + LookAheadOp[(loadoppos - 4)]);
        }
        String x = "";
        boolean loadposDecrFlag = false;
        int Supported = 0;
        if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
            if (Term1.equalsIgnoreCase("")) {
                x = Diff1.IntegrExpr();
            } else {
                x = Term1;
            }
        } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
            if (Term1.equalsIgnoreCase("")) {
                x = Diff1.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule1 :x=" + x);
            } else if ((Term1.contains(DiffWithRespTo)) && (Term1.contains("ln"))) {
                x = Term1;
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule2 :x=" + x);
            } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (LADiffTermOperator == '*')) {
                x = "1.0/2.0" + StrOperator + Term1 + "^2.0";
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule3 :x=" + x);
            } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (LADiffTermOperator == '/')) {
                x = "ln(" + Term1 + ")";
                //LADiffTermOperator="*";
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule4 :x=" + x);
            } else if ((StrOperator.equalsIgnoreCase("/")) && (LADiffTermOperator == '/')) {
                x = Term1;
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule5 :x=" + x);
            } else {
                x = Term1;
                log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule6 :x=" + x + " loadoppos=" + loadoppos);
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if (!Term4.equalsIgnoreCase("")) {
                if (Term2.equalsIgnoreCase("0.0")) // x^0.0 -> 1.0
                {
                    x = Term4 + StrOperator + 1.0;
                } else if (Term2.equalsIgnoreCase("1.0")) {
                    x = Term4 + StrOperator + Term3;// x^1.0 -> x
                } else {
                    x = Term4 + StrOperator + Term3 + "^" + Term2;
                }
            } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                if (Term2.equalsIgnoreCase("0.0")) {
                    x = "1.0";
                } else if (Term2.equalsIgnoreCase("1.0")) {
                    x = Term3;
                } else {
                    x = Term3 + "^" + Term2;
                }
            } else {
                x = Term1;
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                    && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                    && (Term1.equalsIgnoreCase(Term2))
                    && (Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr()))
                    && (Term1.equalsIgnoreCase(Diff1.IntegrExpr()))
                    && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                x = "1.0";
                log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule1 :x=" + x);
            } else if (((Term1 != null) || (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                if (Term3.equalsIgnoreCase("")) {
                    log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.1 :SavedOperator=" + (char) SavedOperator);
                    if (((SavedOperator == '*') || (SavedOperator == 0) || (SavedOperator == -1)) && (!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                        if ((SavedOperator == '*')) {
                            x = Term1 + (char) SavedOperator + Term2;
                            log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.1.1 :x=" + x);
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                            if (StrOperator.equalsIgnoreCase("*")) {
                                x = Term2 + "/2.0" + "*" + Term1 + "^2.0";
                            } else {
                                if (Term2.equalsIgnoreCase("1.0")) {
                                    x = "ln(" + Term1 + ")";
                                } else {
                                    x = Term2 + "*" + "ln(" + Term1 + ")";
                                }
                            }
                            log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.1.2 :x=" + x);
                        } else {
                            if (Term1.startsWith("1.0")) {
                                final String Temp = Term1.replaceFirst("1.0", "");
                                x = Term2 + Temp;
                            } else {
                                x = Term2 + "*" + Term1;
                            }
                            log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.1.3 :x=" + x);
                        }
                    } else if ((!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                        x = Term1 + StrOperator + Term2;
                    }
                    log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.1 :x=" + x);
                } else if (Term2.equalsIgnoreCase(DiffWithRespTo)) {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + "^2.0*1.0/2.0" + Term3;
                    log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.2 :x=" + x);
                } else if (Diff1.IntegrExpr().equalsIgnoreCase(Term2)) {
                    if ((Term1.equalsIgnoreCase(Diff2.IntegrExpr())) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                        x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr() + "^2.0*1.0/2.0";
                        log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.3.1 :x=" + x);
                    } else {
                        x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                        log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.3.2 :x=" + x);
                    }
                } else {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + Term3;
                    log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2.4 :x=" + x);
                }
                log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule2 :x=" + x);
            } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2.IntegrExpr()))) {
                x = Diff1.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule3 :x=" + x);
            } else if (IsAConstant(Term1, DiffWithRespTo)) {
                x = Term1 + StrOperator + Term2;
                log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule4 :x=" + x);
            } else {
                x = Term2;
                log.info("Integr:ApplyFuncRulesString:ConstantRule :Rule5 :x=" + x);
            }
        } else if ((Rule.equalsIgnoreCase("SumRule"))
                || (Rule.equalsIgnoreCase("SubtractionRule"))) {
            if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule1 :x=" + x + " loadoppos=" + loadoppos);
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = Term1 + StrOperator + Term2;
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule2 :x=" + x);
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End) && (!Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                x = Diff1.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule3 :x=" + x);
            } else if ((LookAheadOp[loadoppos] != LowLevelOperator.End)) {
                if ((Diff2.IntegrExpr().equalsIgnoreCase(Term2)) && (Term2.contains(DiffWithRespTo)) && (!Term3.contains(DiffWithRespTo + "^"))) {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + "^2.0" + "*1/2.0";
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule4A :x=" + x);
                } else if (!Term2.equalsIgnoreCase("")) {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo;
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule4B :x=" + x);
                } else if (Term2.equalsIgnoreCase("")) {
                    x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr() + "*" + DiffWithRespTo;
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule4B :x=" + x);
                }
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule4 :x=" + x);
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!Term2.contains(DiffWithRespTo)) && (IsAConstant(Term2, DiffWithRespTo))) {
                x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule5 :x=" + x);
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                if (Diff2.IntegrExpr().equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo;
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule6A :x=" + x);
                } else if (Diff2.IntegrExpr().equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                    x = Diff1.IntegrExpr() + StrOperator + Term2 + "*" + DiffWithRespTo + "^2.0" + "*1.0/2.0";
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule6B :x=" + x);
                } else if (!Diff2.IntegrExpr().contains(DiffWithRespTo)) {
                    x = Term1 + StrOperator + Diff2.IntegrExpr() + "*" + DiffWithRespTo;
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule6C :x=" + x);
                } else {
                    x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                    log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule6D :x=" + x);
                }
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule6 :x=" + x);
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))) {
                x = Term1 + StrOperator + Term2;
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule7 :x=" + x);
            } else if ((LookAheadOp[loadoppos] == LowLevelOperator.End)) {
                x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:SumRule or SubtractionRule :Rule8 :x=" + x);
            }
            //Drops Term2 && Diff2.IntegrExpr()
        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            int j = 0;
            if (LookAheadOp[loadoppos] == LowLevelOperator.Div) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Mult) {
                j = loadoppos;
            } else if (LookAheadOp[loadoppos] == LowLevelOperator.Expnt) {
                if (loadoppos == 0) {
                    loadoppos++;
                }
                loadposDecrFlag = true;
            } else {
                j = loadoppos;
            }
            log.info("Integr:ApplyFuncRulesString:j=" + j + " loadoppos=" + loadoppos + " LookAheadOp[j]=" + LookAheadOp[j]);
            if (j >= 4) {
                log.info("Integr:ApplyFuncRulesString:j=" + j + " LookAheadOp[j - 4]=" + LookAheadOp[j - 4]);
            }
            if ((IsAConstant(Term3, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                if ((LookAheadOp[j] == LowLevelOperator.Mult) && (Diff2.IntegrExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1);
                    log.info("Integr:ApplyFuncRulesString:ExponentRule1:x=" + x);
                } else if ((LookAheadOp[j] == LowLevelOperator.Div) && (Diff2.IntegrExpr().contains("Constant") && (Double.parseDouble(Term3) > 2.0))) {
                    x = "-1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1);
                    log.info("Integr:ApplyFuncRulesString:ExponentRule2:x=" + x);
                } else if ((Diff2.IntegrExpr().contains("Parenthesis")) && (Double.parseDouble(Term3) > 2.0)) {
                    x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1);
                    log.info("Integr:ApplyFuncRulesString:ExponentRule3:x=" + x);
                } else if ((Double.parseDouble(Term3) - 1.0) == 0.0) {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1)) {
                        x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + 1.0;
                        log.info("Integr:ApplyFuncRulesString:ExponentRule4.1:x=" + x);
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "/" + 1.0;
                        log.info("Integr:ApplyFuncRulesString:ExponentRule4.2:x=" + x);
                    }
                    log.info("Integr:ApplyFuncRulesString:ExponentRule4:x=" + x);
                } else {
                    if ((LookAheadOp[j] == LowLevelOperator.Mult) || (j == 1) || (j == 0)) {
                        x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                        log.info("Integr:ApplyFuncRulesString:ExponentRule5.1:x=" + x);
                    } else if (LookAheadOp[j] == LowLevelOperator.Div) {
                        x = "-1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                        log.info("Integr:ApplyFuncRulesString:ExponentRule5.2:x=" + x);
                    } else if ((LookAheadOp[j] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:ExponentRule5.3:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1);
                            log.info("Integr:ApplyFuncRulesString:ExponentRule5.4:x=" + x);
                        }
                    } else if (LookAheadOp[j] == LowLevelOperator.ParenthesisClose) {
                        x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1);
                        log.info("Integr:ApplyFuncRulesString:ExponentRule5.5:x=" + x);
                    }
                    log.info("Integr:ApplyFuncRulesString:ExponentRule5:x=" + x);
                }
                if ((j >= 4) && (StrOperator.equalsIgnoreCase("^"))) {
                    if ((LookAheadOp[j - 4] == LowLevelOperator.Div)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule1:x=" + x);
                        } else if ((-Double.parseDouble(Term3) + 1) < 0.0) {
                            //Maps Div to Mult Operator
                            x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule2:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Mult)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule3:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule4:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Null)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule5:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule6:x=" + x);
                        }
                    } else if ((LookAheadOp[j] == null)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule7:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule8:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Plus)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule9:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule10:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Minus)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule11:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule12:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Mult)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule13:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule14:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar) && (LookAheadOp[j - 5] == LowLevelOperator.Div)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule15:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule16:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule17:x=" + x);
                        } else if (LADiffTermOperator == '*') {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule18:x=" + x);
                        } else if (LADiffTermOperator == '/') {
                            x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule19:x=" + x);
                        } else {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule20:x=" + x);
                        }
                        log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rules17-20:x=" + x);
                    } else if ((LookAheadOp[j - 2] == LowLevelOperator.FuncNvar)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule21:x=" + x);
                        } else if (LADiffTermOperator == '*') {
                            x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule22:x=" + x);
                        } else if (LADiffTermOperator == '/') {
                            x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule23:x=" + x);
                        }
                    } else if ((LookAheadOp[j - 4] == LowLevelOperator.Expnt)) {
                        if ((Double.parseDouble(Term3) + 1) == 0.0) {
                            x = "ln(" + Term2 + ")";
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule24:x=" + x);
                        } else if (LADiffTermOperator == '*') {
                            //Mapping '/'(Division) to a '*' (Multiplication)
                            if (TermOperator == '/') {
                                x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            } else if (TermOperator == '*') {
                                x = "1.0/" + RemoveEDbl(Double.parseDouble(Term3) + 1) + (char) LADiffTermOperator + Term2 + "^" + RemoveEDbl(Double.parseDouble(Term3) + 1.0);
                            }
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule25:x=" + x);
                        } else if (LADiffTermOperator == '/') {
                            x = "1.0/" + RemoveEDbl(-Double.parseDouble(Term3) + 1) + "*" + Term2 + "^" + RemoveEDbl(-Double.parseDouble(Term3) + 1.0);
                            log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule26:x=" + x);
                        }
                    }
                }
                if ((loadposDecrFlag == true) && (LookAheadOp[loadoppos + 2] == LowLevelOperator.Expnt)) {
                    loadoppos += 2;
                }
            } else if ((IsAConstant(Term3, DiffWithRespTo) == false) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                x = "1.0/" + Term3 + "+" + 1.0 + "*" + Term2 + "^" + Term3 + "+" + 1.0;
                log.info("Integr:ApplyFuncRulesString:Exponent:LookAheadOp Block:Rule27:x=" + x);
            }
        } else if (Rule.equalsIgnoreCase("MappedProductRule")) {
            if (Supported == 1) {
                if ((!Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                    String Ux = "";
                    String UDashx = "";
                    String Vx = "";
                    String VDashx = "";
                    // Set Ux Parameters
                    if (Diff1.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        Ux = ParanExpr;
                        UDashx = ParanDiffExpr;

                    } else if (!Diff1.IntegrExpr().contains("Parenthesis")) {
                        Ux = Term1;
                        UDashx = Diff2.IntegrExpr();
                    }
                    // Set Vx Parameters
                    if (Diff2.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff2.IntegrExpr(), DiffWithRespTo);
                        Vx = ParanExpr;
                        VDashx = ParanDiffExpr;

                    } else if (!Diff2.IntegrExpr().contains("Parenthesis")) {
                        Vx = Term2;
                        VDashx = Diff2.IntegrExpr();
                    }
                    final String Vx1 = Vx;
                    final String UDashx1 = UDashx;
                    final String VDashx1 = VDashx;
                    final String Ux1 = Ux;
                    x = Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1;
                }
            } else {
                log.info("Integr:ApplyFuncRulesString:MappedProductRule:Maths OR This Software does not support :(" + Diff1.IntegrExpr() + ") * (" + Diff2.IntegrExpr() + ")");
            }

        } else if (Rule.equalsIgnoreCase("ProductRule")) {

            if (!Term3.equalsIgnoreCase("")) {
                ;//x = Term3 + "*" + Diff1.IntegrExpr() + "+" + Term1 + "*" + Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:ProductRule:Maths OR This Software does not support :" + Term3);
                log.info("Integr:ApplyFuncRulesString:Rule1 :x=" + x);
            } else if (Diff1.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                //Differentiation of x term:
                x = Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:Rule2 :x=" + x);
            } else if ((Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))) {
                //Differentiation of x term:
                x = Diff1.IntegrExpr() + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0";
                log.info("Integr:ApplyFuncRules:Rule3 :x=" + x);
            } else if ((Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))) {
                //Differentiation of x term with Independent Constant:
                x = Diff1.IntegrExpr() + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0";
                //x = Diff1.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:Rule4 :x=" + x);
            } else if (!Diff1.IntegrExpr().equalsIgnoreCase("")) {
                if ((((IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo))
                        || ((!IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))
                        && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))
                        || ((!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))) {
                    if (Diff1.IntegrExpr().contains("@IndependentFactor@")) {
                        Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        final String TempStrDiff1 = FactorsDiffExpr;
                        x = TempStrDiff1;
                    } else if (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                        x = Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr() + "^2.0";
                    } else {
                        x = Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr() + "*" + DiffWithRespTo;
                    }
                    log.info("Integr:ApplyFuncRulesString:Rule5A :x=" + x);
                } else {
                    if (Diff2.IntegrExpr().startsWith("1.0")) {
                        String Temp = Diff2.IntegrExpr().replaceFirst("1.0", "");
                        if ((Diff1.IntegrExpr().endsWith("/")) && (Term1.startsWith("/"))) {
                            String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = Temp1 + Term1 + Temp;
                            log.info("Integr:ApplyFuncRulesString:Rule5B.1 :x)=" + x);
                        } else if (Diff1.IntegrExpr().endsWith("/")) {
                            String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = Temp1 + Temp;
                            log.info("Integr:ApplyFuncRulesString:Rule5B.2 :x=" + x);
                        } //Need to uncomment the below code
                        else {
                            //String Temp1 = Diff1.IntegrExpr().replaceFirst("/", "");
                            x = Diff1.IntegrExpr() + Temp;
                            log.info("Integr:ApplyFuncRulesString:Rule5B.3 :x=" + x);
                        }
                    } else {
                        x = Diff1.IntegrExpr() + "*" + Diff2.IntegrExpr();
                        log.info("Integr:ApplyFuncRulesString:Rule5C :x=" + x);
                    }
                }
                log.info("Integr:ApplyFuncRulesString:Rule5 :x=" + x);
            }
        } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
            if (Supported == 1) {
                if ((!Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                    String Ux = "";
                    String UDashx = "";
                    String Vx = "";
                    String VDashx = "";
                    // Set Ux Parameters
                    if (Diff1.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff1.IntegrExpr(), DiffWithRespTo);
                        Ux = ParanExpr;
                        UDashx = ParanDiffExpr;

                    } else if (!Diff1.IntegrExpr().contains("Parenthesis")) {
                        Ux = Term1;
                        UDashx = Diff2.IntegrExpr();
                    }
                    // Set Vx Parameters
                    if (Diff2.IntegrExpr().contains("Parenthesis")) {
                        Expr_Func_Split(Diff2.IntegrExpr(), DiffWithRespTo);
                        Vx = ParanExpr;
                        VDashx = ParanDiffExpr;

                    } else if (!Diff2.IntegrExpr().contains("Parenthesis")) {
                        Vx = Term2;
                        VDashx = Diff2.IntegrExpr();
                    }
                    final String Vx1 = Vx;
                    final String UDashx1 = UDashx;
                    final String VDashx1 = VDashx;
                    final String Ux1 = Ux;

                    x = "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1;
                }
            } else {
                log.info("Integr:ApplyFuncRulesString:MappedQuotientRule:Maths OR This Software does not support :(" + Diff1.IntegrExpr() + ") / (" + Diff2.IntegrExpr() + ")");
            }
        } else if (Rule.equalsIgnoreCase("QuotientRule")) {

            if (!Term3.equalsIgnoreCase("")) {
                x = "(" + Term2 + "*" + Diff1.IntegrExpr() + "-" + Term1 + "*" + Diff2.IntegrExpr() + "/" + "(" + Term2 + "*" + Term2 + ")" + ")";
                log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule1 :x=" + x);
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo))) {
                if (Diff1.IntegrExpr().equalsIgnoreCase("1.0")) {
                    x = "ln(" + DiffWithRespTo + ")";
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule2A :x)=" + x);
                } else {
                    x = Diff1.IntegrExpr() + "*" + "ln(" + DiffWithRespTo + ")";
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule2B :x=" + x);
                }
                log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule2 :x=" + x);
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff1.IntegrExpr().equalsIgnoreCase("1.0"))) {
                if (Diff2.IntegrExpr().startsWith("1.0")) {
                    String Temp = Diff2.IntegrExpr().replaceFirst("1.0", "");
                    x = Diff1.IntegrExpr() + Temp;
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule3A :x=" + x);
                } else if (!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) {
                    x = Diff1.IntegrExpr() + Diff2.IntegrExpr();
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule3B :x=" + x);
                } else {
                    x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule3C :x=" + x);
                }
                log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule3 :x=" + x);
            } else if ((!Diff1.IntegrExpr().equalsIgnoreCase("")) && (!Diff2.IntegrExpr().contains(Diff1.IntegrExpr() + "/"))) {
                if (Diff2.IntegrExpr().equalsIgnoreCase(DiffWithRespTo)) {
                    x = "ln(" + DiffWithRespTo + ")";
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule4A :x=" + x);
                } else {
                    x = Diff1.IntegrExpr() + "/" + Diff2.IntegrExpr();
                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule4B :x=" + x);
                }
//                else {
//                    x =  Diff2.IntegrExpr();
//                    log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule4C :x=" + x);
//                }
            } else {
                x = Diff2.IntegrExpr();
                log.info("Integr:ApplyFuncRulesString:QuotientRule:Rule5 :x=" + x);
            }
        }

        log.info("Integr:ApplyFuncRulesString:Final:String:x=" + x);
        return x;
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
        log.info("SimpleTerm:ApplyFuncRules:Initial : End Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        SimpleTerm x = (() -> "");
        if (Rule.equalsIgnoreCase("NoExponentRule")) {
            if (Term1.equalsIgnoreCase(Term4)) {
                x = (() -> Term1 + "*" + Term3 + "^" + Diff1.SimpleTerm());
            } else {
                x = (() -> Term1 + Diff1.SimpleTerm());
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                if (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> "1.0");
                } else if (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Diff1.SimpleTerm());
                } else if (Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = (() -> DiffWithRespTo + StrOperator + Diff2.SimpleTerm());
                } else {
                    x = (() -> (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm()));
                }
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term4.equalsIgnoreCase("1.0"))) {
                x = (() -> Term4);
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))/*&&(!Term4.equalsIgnoreCase("1.0"))*/) {
                if (Term2.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> Term4);
                } else if (Term2.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Term4 + "*" + Term1);
                } else {
                    x = (() -> Term4 + "*" + Term1 + "^" + Term2);
                }
            } else if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                if (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> ("1.0"));
                } else if (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Diff1.SimpleTerm());
                } else if (!Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = (() -> (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm()));
                } else if (Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = (() -> DiffWithRespTo + StrOperator + Diff2.SimpleTerm());
                }
            } else if ((LookAheadString[loadstringpos] != null) && (Term5.equalsIgnoreCase(""))) {
                if (LookAheadString[loadstringpos].equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = (() -> "1.0");
                } else if (LookAheadString[loadstringpos].equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = (() -> Term3);
                } else {
                    x = (() -> Term3 + "^" + LookAheadString[loadstringpos]);
                }
            } else {
                x = (() -> Term1 + "^" + Term5);
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase(Diff2.SimpleTerm())) && (!(Term1.equalsIgnoreCase(DiffWithRespTo))) && (!Term1.contains("^")) && (!StrOperator.equalsIgnoreCase("^"))) {
                x = (() -> Term1 + StrOperator + Term2);
            } else if ((Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (() -> Diff2.SimpleTerm());
            } else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && ((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && ((Diff2.SimpleTerm().equalsIgnoreCase("")) || (Diff2.SimpleTerm().equalsIgnoreCase("0.0")))) {
                x = (() -> Diff1.SimpleTerm());
            } //            else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
            //                x = (()->"0.0");
            //            } 
            else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0") && (Term4.equalsIgnoreCase("0.0")))) {
                x = (() -> Diff1.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { // + 0.0
                    x = (() -> "");
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm()))));
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { // - 0.0
                    x = (() -> "");
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm()))));
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) * Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (Diff1.SimpleTerm().equalsIgnoreCase("0.0"))
                    && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
                x = (() -> "0.0");
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo))
                    && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) / Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((!Term4.equalsIgnoreCase(Diff2.SimpleTerm())) && (Term1.equalsIgnoreCase(Diff1.SimpleTerm())) && (IsAConstant(Term1, DiffWithRespTo)) && ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (() -> Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
            } else if ((!Term4.equalsIgnoreCase(Diff2.SimpleTerm())) && (Term1.equalsIgnoreCase(Diff1.SimpleTerm())) && (IsAConstant(Term1, DiffWithRespTo)) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (() -> Diff2.SimpleTerm());
            } else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (() -> Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
            }
        } else if ((Rule.equalsIgnoreCase("MappedSumRule"))
                || (Rule.equalsIgnoreCase("MappedSubtractionRule"))
                || (Rule.equalsIgnoreCase("MappedProductRule"))
                || (Rule.equalsIgnoreCase("MappedQuotientRule"))) {
            if ((StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = (() -> Diff2.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))) {
                x = (() -> Diff1.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && ((Diff1.SimpleTerm().equalsIgnoreCase("0.0")) || (Diff2.SimpleTerm().equalsIgnoreCase("0.0")))) {
                x = (() -> "");
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff1.SimpleTerm().equalsIgnoreCase("1.0"))) {
                x = (() -> Diff2.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { //  + 0.0
                    x = (() -> "");
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm()))));
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { //  - 0.0
                    x = (() -> "");
                } else {
                    x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm()))));
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) * Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((StrOperator.equalsIgnoreCase("/")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (() -> ("" + (Double.parseDouble(Diff1.SimpleTerm()) / Double.parseDouble(Diff2.SimpleTerm()))));
            } else {
                if ((StrOperator.equalsIgnoreCase("+")) && (Diff1.SimpleTerm().equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff2.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff1.SimpleTerm().equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff2.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff1.SimpleTerm());
                } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
                    x = (() -> Diff1.SimpleTerm());
                } else {
                    x = (() -> Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
                }
            }
        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            x = x = (() -> Term1 + "*" + Term3 + "^" + Term2);
        } else if (Rule.equalsIgnoreCase("MappedExponentRule")) {
            x = (() -> Term4 + "*" + Term3 + "^" + Diff2.SimpleTerm());
        }
        final String OperatorPrefixed = x.SimpleTerm();
        x = (() -> OperatorPrefixed);
        log.info("SimpleTerm:ApplyFuncRules:Final:x.SimpleTerm()=" + x.SimpleTerm());
        log.info("SimpleTerm:ApplyFuncRules:Final: End Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
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
        log.info("SimpleTerm:ApplyFuncRulesString:Initial : End Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        String x = "";
        if (Rule.equalsIgnoreCase("NoExponentRule")) {
            if (Term1.equalsIgnoreCase(Term4)) {
                x = Term1 + "*" + Term3 + "^" + Diff1.SimpleTerm();
            } else {
                x = Term1 + Diff1.SimpleTerm();
            }
        } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
            if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                if (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = "1.0";
                } else if (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Diff1.SimpleTerm();
                } else if (Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = DiffWithRespTo + StrOperator + Diff2.SimpleTerm();
                } else {
                    x = Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm();
                }
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term4.equalsIgnoreCase("1.0"))) {
                x = Term4;
            } else if ((!Term4.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))/*&&(!Term4.equalsIgnoreCase("1.0"))*/) {
                if (Term2.equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = Term4;
                } else if (Term2.equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Term4 + "*" + Term1;
                } else {
                    x = Term4 + "*" + Term1 + "^" + Term2;
                }
            } else if ((StrOperator.equalsIgnoreCase("^")) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                if (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = "1.0";
                } else if (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Diff1.SimpleTerm();
                } else if (!Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
                } else if (Diff1.SimpleTerm().equalsIgnoreCase("0.0")) {
                    x = DiffWithRespTo + StrOperator + Diff2.SimpleTerm();
                }
            } else if ((LookAheadString[loadstringpos] != null) && (Term5.equalsIgnoreCase(""))) {
                if (LookAheadString[loadstringpos].equalsIgnoreCase("0.0"))// x^0.0 -> 1.0
                {
                    x = "1.0";
                } else if (LookAheadString[loadstringpos].equalsIgnoreCase("1.0"))// x^1.0 -> x
                {
                    x = Term3;
                } else {
                    x = Term3 + "^" + LookAheadString[loadstringpos];
                }
            } else {
                x = Term1 + "^" + Term5;
            }
        } else if (Rule.equalsIgnoreCase("ConstantRule")) {
            if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Term1.equalsIgnoreCase(Diff2.SimpleTerm())) && (!(Term1.equalsIgnoreCase(DiffWithRespTo))) && (!Term1.contains("^")) && (!StrOperator.equalsIgnoreCase("^"))) {
                x = Term1 + StrOperator + Term2;
            } else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && ((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && ((Diff2.SimpleTerm().equalsIgnoreCase("")) || (Diff2.SimpleTerm().equalsIgnoreCase("0.0")))) {
                x = Diff1.SimpleTerm();
            } //            else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
            //                x = "0.0";
            //            } 
            else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("0.0") && (Term4.equalsIgnoreCase("0.0")))) {
                x = Diff1.SimpleTerm();
            } else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && ((Diff2.SimpleTerm().equalsIgnoreCase("")) || (Diff2.SimpleTerm().equalsIgnoreCase("0.0")))) {
                x = (Diff1.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { // + 0.0
                    x = "";
                } else {
                    x = ("" + (Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())));
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { // - 0.0
                    x = "";
                } else {
                    x = ("" + (Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())));
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (("" + (Double.parseDouble(Diff1.SimpleTerm()) * Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (Diff1.SimpleTerm().equalsIgnoreCase("0.0"))
                    && (Diff2.SimpleTerm().equalsIgnoreCase("0.0"))) {
                x = "0.0";
            } else if ((StrOperator.equalsIgnoreCase("/"))
                    && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo))
                    && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (("" + (Double.parseDouble(Diff1.SimpleTerm()) / Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((!Term4.equalsIgnoreCase(Diff2.SimpleTerm())) && (Term1.equalsIgnoreCase(Diff1.SimpleTerm())) && (IsAConstant(Term1, DiffWithRespTo)) && ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
            } else if ((!Term4.equalsIgnoreCase(Diff2.SimpleTerm())) && (Term1.equalsIgnoreCase(Diff1.SimpleTerm())) && (IsAConstant(Term1, DiffWithRespTo)) && (!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (Diff2.SimpleTerm());
            } else if ((!Diff1.SimpleTerm().equalsIgnoreCase("")) && (!Diff2.SimpleTerm().equalsIgnoreCase(""))) {
                x = (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
            }
        } else if ((Rule.equalsIgnoreCase("MappedSumRule"))
                || (Rule.equalsIgnoreCase("MappedSubtractionRule"))
                || (Rule.equalsIgnoreCase("MappedProductRule"))
                || (Rule.equalsIgnoreCase("MappedQuotientRule"))) {
            if ((StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().contains("^")) && (Rule.equalsIgnoreCase("MappedProductRule"))) {
                x = (Diff2.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff2.SimpleTerm().equalsIgnoreCase("1.0"))) {
                x = (Diff1.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("*")) && ((Diff1.SimpleTerm().equalsIgnoreCase("0.0")) || (Diff2.SimpleTerm().equalsIgnoreCase("0.0")))) {
                x = ("");
            } else if ((StrOperator.equalsIgnoreCase("*")) && (Diff1.SimpleTerm().equalsIgnoreCase("1.0"))) {
                x = (Diff2.SimpleTerm());
            } else if ((StrOperator.equalsIgnoreCase("+")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { //  + 0.0
                    x = "";
                } else {
                    x = ("" + (Double.parseDouble(Diff1.SimpleTerm()) + Double.parseDouble(Diff2.SimpleTerm())));
                }
            } else if ((StrOperator.equalsIgnoreCase("-")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                if ((Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())) == 0.0) { //  - 0.0
                    x = "";
                } else {
                    x = ("" + (Double.parseDouble(Diff1.SimpleTerm()) - Double.parseDouble(Diff2.SimpleTerm())));
                }
            } else if ((StrOperator.equalsIgnoreCase("*")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (("" + (Double.parseDouble(Diff1.SimpleTerm()) * Double.parseDouble(Diff2.SimpleTerm()))));
            } else if ((StrOperator.equalsIgnoreCase("/")) && (IsAConstant(Diff1.SimpleTerm(), DiffWithRespTo)) && (IsAConstant(Diff2.SimpleTerm(), DiffWithRespTo))) {
                x = (("" + (Double.parseDouble(Diff1.SimpleTerm()) / Double.parseDouble(Diff2.SimpleTerm()))));
            } else {
                x = (Diff1.SimpleTerm() + StrOperator + Diff2.SimpleTerm());
            }

        } else if (Rule.equalsIgnoreCase("ExponentRule")) {
            if ((Term1.equalsIgnoreCase(Term3))) {
                x = Term4 + "*" + Term3 + "^" + Term2;
            } else {
                x = Term1 + "*" + Term3 + "^" + Term2;
            }
        } else if (Rule.equalsIgnoreCase("MappedExponentRule")) {
            if (Term1.equalsIgnoreCase(Term3)) {
                x = Term4 + "*" + Term3 + "^" + Term2;
            } else {
                x = Term4 + "*" + Term3 + "^" + Diff2.SimpleTerm();
            }
        }
        log.info("SimpleTerm:ApplyFuncRulesString:Final:String:x=" + x);
        log.info("SimpleTerm:ApplyFuncRulesString:Final: End Context:LookAheadCntxt[loadCntxtpos=" + loadCntxtpos + "]=" + LookAheadCntxt[loadCntxtpos] + " LookAheadOp[loadoppos=" + loadoppos + "]=" + LookAheadOp[loadoppos] + " LookAheadString[loadstringpos=" + loadstringpos + "]=" + LookAheadString[loadstringpos] + " pos=" + pos + " ch=" + (char) ch + " NumCoeff=" + NumCoeff);
        return x;
    }

    public static String FormSimpleTermParse(String AlgoName, String IndConstStr, String BracesStr, String VarStr, String ConstStr, String DiffWithRespTo, boolean BracesFlag, boolean VariableFlag, boolean ConstantFlag, boolean IndConstFlag, int TermOperator, int TermOperatorBraces, int TermOperatorIndConst, int TermVarOperator) {
        log.info("SimpleTerm:FormSimpleTermParse:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :IndConstStr=" + IndConstStr);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :BracesStr=" + BracesStr);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :VarStr=" + VarStr);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :ConstStr=" + ConstStr);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :DiffWithRespTo=" + DiffWithRespTo);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :BracesFlag=" + BracesFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :VariableFlag=" + VariableFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :ConstantFlag=" + ConstantFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :IndConstFlag=" + IndConstFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TrailConstantFlag=" + TrailConstantFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TrailConstantOperator=" + (char) TrailConstantOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TermOperator=" + (char) TermOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TermOperatorBraces=" + (char) TermOperatorBraces);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TermOperatorIndConst=" + (char) TermOperatorIndConst);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :TermVarOperator=" + (char) TermVarOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LATermOperator=" + (char) LATermOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LATermOperatorBraces=" + (char) LATermOperatorBraces);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAPreIndConstOperator=" + (char) LAPreIndConstOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAIndConstOperator=" + (char) LAIndConstOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAVarTermOperator=" + (char) LAVarTermOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAPreTermOperator=" + (char) LAPreTermOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAMAPOperator=" + (char) LAMAPOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAVarSimpleMapFlag=" + LAVarSimpleMapFlag);
        log.info("SimpleTerm:FormSimpleTermParse:Initial :LAVarComplexMapFlag=" + LAVarComplexMapFlag);
        String CompleteStr = "";
        if (IndConstFlag == true) {
            if (LAPreIndConstOperator == '/') {
                if (IndConstStr.startsWith("1.0")) {
                    CompleteStr = IndConstStr + (char) LAIndConstOperator;
                } else {
                    CompleteStr = "1.0/" + IndConstStr + (char) LAIndConstOperator;
                }
                log.info("SimpleTerm:FormSimpleTermParse:IndConstantRules:Rule1:CompleteStr=" + CompleteStr);
            } else if (((LAIndConstOperator == '*') && (LATermOperator == -1)) || ((LAPreTermOperator == -1) && (IndConstFlag == true))) {
                CompleteStr = IndConstStr + (char) LAIndConstOperator;
                log.info("SimpleTerm:FormSimpleTermParse:IndConstantRules:Rule2:CompleteStr=" + CompleteStr);
            } else if ((LAIndConstOperator == '/') && (LATermOperator == -1) && (LAPreTermOperator == '*')) {
                CompleteStr = IndConstStr + (char) LAPreTermOperator;
                log.info("SimpleTerm:FormSimpleTermParse:IndConstantRules:Rule3:CompleteStr=" + CompleteStr);
            } else if ((LAIndConstOperator == '/')) {
                CompleteStr = IndConstStr + (char) LAIndConstOperator;
                log.info("SimpleTerm:FormSimpleTermParse:IndConstantRules:Rule4:CompleteStr=" + CompleteStr);
            } else {
                CompleteStr = IndConstStr + (char) LATermOperator;
                log.info("SimpleTerm:FormSimpleTermParse:IndConstantRules:Rule4:CompleteStr=" + CompleteStr);
            }
        }
        if ((VariableFlag == true) && ((VarStr.startsWith("\\*")) || (TermOperator == '*') || (TermOperator == -1)) && (VarStr.contains(DiffWithRespTo))
                && (!VarStr.equalsIgnoreCase("0.0"))
                && (!ConstStr.equalsIgnoreCase("0.0"))) {
            if ((ConstStr.equalsIgnoreCase("1.0")) && (LAVarTermOperator != '/')) {
                CompleteStr = CompleteStr + VarStr;
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.1:CompleteStr=" + CompleteStr);
            } else if (VarStr.startsWith("\\*")) {
                CompleteStr = CompleteStr + (ConstStr + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.2:CompleteStr=" + CompleteStr);
            } // parseVar :Rule 3 : No Negative Exponent
            else if ((TermOperator != LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) LAVarTermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.3:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) TermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.4:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == -1) && (TrailConstantFlag == true) && (TrailConstantOperator == '*')) {
                CompleteStr = CompleteStr + (ConstStr + (char) TrailConstantOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.5:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == -1) && (TrailConstantFlag == true) && (TrailConstantOperator == '/')) {
                // COMMENT:BE CAREFUL OF ROUNDING UP OF DOUBLE
                if (CompleteStr.endsWith("/")) {
                    CompleteStr = CompleteStr + (ConstStr) + "*" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.6.1:CompleteStr=" + CompleteStr);
                } else if (!CompleteStr.equalsIgnoreCase("")) {
                    CompleteStr = CompleteStr + ("/" + ConstStr) + "*" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.6.2:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = CompleteStr + "1.0" + "/" + ConstStr + "*" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.6.3:CompleteStr=" + CompleteStr);
                }
            } else if ((LATermOperator == -1) && (LAVarTermOperator != '/') && (VariableFlag == true) && (ConstantFlag == true)) {
                //CompleteStr = CompleteStr + ConstStr + "*" + VarStr;
                if ((LAMAPOperator == -1) && (LADyYByDxMAPOperator == -1)) {
                    CompleteStr = CompleteStr + ConstStr + "*" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Normal:Rule1.7.1:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = CompleteStr + ConstStr + "/" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.7.2:CompleteStr=" + CompleteStr);
                }
                log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Rule1.7:CompleteStr=" + CompleteStr);
            } else if ((LATermOperator == -1) && (LAVarTermOperator == '/') && (VariableFlag == true) && (ConstantFlag == true)) {
                if ((LAMAPOperator == -1) && (LADyYByDxMAPOperator == -1)) {
                    CompleteStr = CompleteStr + ConstStr + "/" + VarStr;
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:Normal:Rule1.8:CompleteStr=" + CompleteStr);
                } else {
                    if ((VarStr.startsWith("1.0")) && (ConstStr.equalsIgnoreCase("1.0"))) {
                        CompleteStr = CompleteStr + VarStr;
                        log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.9:CompleteStr=" + CompleteStr);
                    } else if (LAPreTermOperator != -1) {
                        String Temp = VarStr.replaceFirst("1.0", "");
                        if (Temp.startsWith("/")) {
                            String Temp1 = Temp.replaceFirst("/", "");
                            CompleteStr = CompleteStr + ConstStr + (char) LAPreTermOperator + Temp1;
                            log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.10:CompleteStr=" + CompleteStr);
                        } else if (LAVarComplexMapFlag==true){
                            CompleteStr = CompleteStr + ConstStr + "*" + VarStr;
                            log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.11:CompleteStr=" + CompleteStr);
                        } else {
                            CompleteStr = CompleteStr + ConstStr + (char) LAPreTermOperator + VarStr;
                            log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.12:CompleteStr=" + CompleteStr);
                        }
                    } else {
                        CompleteStr = CompleteStr + ConstStr + "/" + VarStr;
                        log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.13:CompleteStr=" + CompleteStr);
                    }
                    log.info("SimpleTerm:FormSimpleTermParse:VarConstantRules:MAP-:Rule1.9:CompleteStr=" + CompleteStr);
                }
            }
            log.info("SimpleTerm:FormSimpleTermParse:Rule1:Final:CompleteStr=" + CompleteStr);
        } // parseVar :Rule 4 : With Negative Exponent
        else if ((VariableFlag == true) && ((VarStr.startsWith("/") || (TermOperator == '/')))
                && (VarStr.contains(DiffWithRespTo))
                && (!VarStr.equalsIgnoreCase("0.0"))
                && (!ConstStr.equalsIgnoreCase("0.0"))) {
            if (VarStr.startsWith("/")) {
                CompleteStr = CompleteStr + (ConstStr + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:Rule2.1:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (TermOperator == '/') && (LAVarTermOperator == '/')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LATermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.2:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (LAPreTermOperator == '*') && (LADyYByDxMAPOperator == '/')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LADyYByDxMAPOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.3:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator == '/') && (LAPreTermOperator == -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) LAVarTermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.4:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator == -1) && (LAVarTermOperator != '/') && (LAPreTermOperator == -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) LAVarTermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.5:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != '/') && (LAPreTermOperator == '/') && (LAMAPOperator == '*')) {
                if ((LADyYByDxMAPOperator == -1) && (LAMAPOperator == '/')) {
                    CompleteStr = CompleteStr + (ConstStr + (char) LAMAPOperator + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.6.1:Final:CompleteStr=" + CompleteStr);
                } else if ((LADyYByDxMAPOperator == '*') && (LAMAPOperator == '*') && (LATermOperator == '/')) {
                    CompleteStr = CompleteStr + (ConstStr + (char) LAMAPOperator + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.6.2:Final:CompleteStr=" + CompleteStr);
                } else if ((LADyYByDxMAPOperator == -1) && (LAMAPOperator == '*')) {
                    CompleteStr = CompleteStr + (ConstStr + (char) LAMAPOperator + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.6.3:Final:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = CompleteStr + (ConstStr + "/" + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.6.4:Final:CompleteStr=" + CompleteStr);
                }
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.6:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator != LAVarTermOperator) && (TermOperator == '*') && (LAVarTermOperator == '*') && (LAPreTermOperator == -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) LATermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.7:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LADyYByDxMAPOperator == -1) && (LAVarTermOperator != -1)) {
                CompleteStr = CompleteStr + (ConstStr + (char) TermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:Rule2.8:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAMAPOperator != -1) && (LAMAPOperator == '/') && (LAVarTermOperator != -1) && (LADyYByDxMAPOperator != -1) && (LADyYByDxMAPOperator == '*')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LADyYByDxMAPOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.9:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (LAVarTermOperator != '/') && (LADyYByDxMAPOperator != -1) && (LADyYByDxMAPOperator == '*')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LADyYByDxMAPOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.10:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (LADyYByDxMAPOperator != -1) && (LADyYByDxMAPOperator != '*')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LAVarTermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:Rule2.11:Final:CompleteStr=" + CompleteStr);
            } else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (LAMAPOperator == '*') && (LADyYByDxMAPOperator != -1) && (LADyYByDxMAPOperator == '/')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LATermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.12:Final:CompleteStr=" + CompleteStr);
            } //1st Order DyByDx Mapping Rule
            else if ((TermOperator == LAVarTermOperator) && (TermOperator != -1) && (LAVarTermOperator != -1) && (LADyYByDxMAPOperator != -1) && (LADyYByDxMAPOperator == '*')) {
                CompleteStr = CompleteStr + (ConstStr + (char) LAMAPOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.13:Final:CompleteStr=" + CompleteStr);
            } else {
                if ((LAPreTermOperator == -1) && (LATermOperator != '/')) {
                    CompleteStr = CompleteStr + (ConstStr + "/" + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.14.1:Final:CompleteStr=" + CompleteStr);
                } else if ((LADyYByDxMAPOperator != '/') && (LAPreTermOperator != -1) && (LATermOperator != '/')) {
                    CompleteStr = CompleteStr + (ConstStr + "/" + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.14.2:Final:CompleteStr=" + CompleteStr);
                } else {
                    CompleteStr = CompleteStr + (ConstStr + "*" + VarStr);
                    log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.14.3:Final:CompleteStr=" + CompleteStr);
                }
                log.info("SimpleTerm:FormSimpleTermParse:Div:MAP:Rule2.14:Final:CompleteStr=" + CompleteStr);
            }
            log.info("SimpleTerm:FormSimpleTermParse:Div:Rule2:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("0.0"))
                && ((VarStr.equalsIgnoreCase(""))
                || (VarStr.equalsIgnoreCase("0.0"))
                || (VarStr.startsWith("\\*"))
                || (VarStr.startsWith("/")))) {
            CompleteStr = CompleteStr + ("0.0");
            log.info("SimpleTerm:FormSimpleTermParse:Zero:Rule3:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("1.0"))
                && ((!VarStr.equalsIgnoreCase("0.0")))) {
            CompleteStr = CompleteStr + (VarStr);
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=1.0:Rule4:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("0.0"))
                && ((VarStr.equalsIgnoreCase("1")))) {
            CompleteStr = CompleteStr + ("1.0");
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=0.0:Rule5:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("1.0"))
                && ((!VarStr.equalsIgnoreCase("0.0")))) {
            CompleteStr = CompleteStr + ("1.0/" + VarStr);
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=1.0:Rule6:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("-1.0"))
                && ((!VarStr.equalsIgnoreCase("0.0")))) {
            CompleteStr = CompleteStr + (VarStr);
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=-1.0:Rule7:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("-1.0")) && ((!VarStr.equalsIgnoreCase("0.0")))) {
            CompleteStr = CompleteStr + (VarStr);
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=-1.0:Rule8:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("1.0")) && (VariableFlag == true) && ((VarStr.equalsIgnoreCase("")) || (VarStr.equalsIgnoreCase("0.0")))) {
            if (chLA != -1) {
                CompleteStr = CompleteStr + (ConstStr);
            } else {
                CompleteStr = CompleteStr + (ConstStr);
            }
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=-1.0:Rule9:Final:CompleteStr=" + CompleteStr);
        } else if ((ConstStr.equalsIgnoreCase("-1.0")) && ((VarStr.equalsIgnoreCase("")) || (VarStr.equalsIgnoreCase("0.0")))) {
            if (chLA != -1) {
                CompleteStr = CompleteStr + (ConstStr);
            } else {
                CompleteStr = CompleteStr + (ConstStr);
            }
            log.info("SimpleTerm:FormSimpleTermParse:ConstStr=-1.0:Rule10:Final:CompleteStr=" + CompleteStr);
        } else if ((VarStr.contains(DiffWithRespTo)) || (VariableFlag == true)) {
            if ((VarStr.equalsIgnoreCase("1.0")) && (VariableFlag == true)) {
                VarStr = (DiffWithRespTo);
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.1:Final:CompleteStr=" + CompleteStr + " VarStr=" + VarStr);
            } else if ((TrailConstantFlag == true)
                    && ((TrailConstantOperator == '*')
                    || (TrailConstantOperator == '/'))) {
                log.info("SimpleTerm:FormSimpleTermParse:Trailing Constant:Final:End Context:ConstStr=" + ConstStr + " TrailConstantOperator=" + (char) TrailConstantOperator + " VarStr=" + VarStr);
                String TempVarStr = "1.0";
                if (((VarStr.equalsIgnoreCase("1.0")) || (VarStr.equalsIgnoreCase("1"))) && (VariableFlag == true)) {
                    TempVarStr = "";
                }
                if ((!VarStr.equalsIgnoreCase("")) && (!TempVarStr.equalsIgnoreCase(""))) {
                    //CompleteStr = CompleteStr + (ConstStr + (char) TrailConstantOperator + VarStr);
                    if ((TrailConstantOperator != LAVarTermOperator) && (TrailConstantOperator != -1) && (LAVarTermOperator != -1)) {
                        CompleteStr = CompleteStr + (ConstStr + (char) TrailConstantOperator + VarStr);
                        log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.2.1:Final:CompleteStr=" + CompleteStr);
                    } else if ((TrailConstantOperator == LAVarTermOperator) && (TrailConstantOperator != -1) && (LAVarTermOperator != -1)) {
                        CompleteStr = CompleteStr + (ConstStr + (char) TrailConstantOperator + VarStr);
                        log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.2.2:Final:CompleteStr=" + CompleteStr);
                    } else if (TrailConstantOperator != -1) {
                        CompleteStr = CompleteStr + (ConstStr + (char) TrailConstantOperator + VarStr);
                        log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.2.3:Final:CompleteStr=" + CompleteStr);
                    }
                } else {
                    CompleteStr = CompleteStr + ConstStr;
                }
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.2:Final:CompleteStr=" + CompleteStr);
            } else if ((TrailConstantFlag != true) && (VariableFlag == true) && (!VarStr.equalsIgnoreCase("")) && (!VarStr.equalsIgnoreCase("1.0")) && (!ConstStr.equalsIgnoreCase("0.0"))) {
                log.info("SimpleTerm:FormSimpleTermParse:Normal Variable:Final:End Context:ConstStr=" + ConstStr + " TrailConstantOperator=" + (char) TrailConstantOperator + " VarStr=" + VarStr);
                CompleteStr = CompleteStr + (ConstStr + "*" + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.3:Final:CompleteStr=" + CompleteStr);
            } else if ((ConstantFlag == true) && (ConstStr.equalsIgnoreCase("0.0"))) {
                CompleteStr = CompleteStr + ("0.0");
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.4:Final:CompleteStr=" + CompleteStr);
            } else if ((ConstantFlag == true) || ((VariableFlag == true) && (LAPreTermOperator == '/'))) {
                if ((VariableFlag == true) && (LAPreTermOperator == '/')) {
                    CompleteStr = CompleteStr + (ConstStr) + "*" + DiffWithRespTo;
                } else {
                    CompleteStr = CompleteStr + (ConstStr);
                }
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.5:Final:CompleteStr=" + CompleteStr);
            } else if (VariableFlag == true) {
                CompleteStr = CompleteStr + VarStr;
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.5:Final:CompleteStr=" + CompleteStr);
            }
            if (CompleteStr.equalsIgnoreCase("")) {
                CompleteStr = VarStr;
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.6:Final:CompleteStr=" + CompleteStr);
            } else if ((ConstantFlag == true) && (VariableFlag == true) && (!ConstStr.equalsIgnoreCase("0.0")) && (!VarStr.equalsIgnoreCase("0.0")) && (!VarStr.equalsIgnoreCase(""))) {
                CompleteStr = CompleteStr + (ConstStr + (char) TermOperator + VarStr);
                log.info("SimpleTerm:FormSimpleTermParse:End:Rule11.7:Final:CompleteStr=" + CompleteStr);
            }
            log.info("SimpleTerm:FormSimpleTermParse:End:Rule11:Final:CompleteStr=" + CompleteStr);
        } else {
            if (!ConstStr.equalsIgnoreCase("1.0") ) {
                CompleteStr = CompleteStr + (ConstStr);
                log.info("SimpleTerm:FormSimpleTermParse:Else:Rule12:Final:CompleteStr=" + CompleteStr);
            } else if ((ConstStr.equalsIgnoreCase("1.0"))&&((CompleteStr.endsWith("*"))||(CompleteStr.endsWith("/")) ) ) {
                String Temp=CompleteStr.substring(0, (CompleteStr.length()-1));
                log.info("SimpleTerm:FormSimpleTermParse:Else:Rule13:Final:CompleteStr=" + CompleteStr);
                CompleteStr = Temp ;
            }else {
                CompleteStr = CompleteStr + (ConstStr);
                log.info("SimpleTerm:FormSimpleTermParse:Else:Rule14:Final:CompleteStr=" + CompleteStr);
            }
            log.info("SimpleTerm:FormSimpleTermParse:Else:Rule15:Final:CompleteStr=" + CompleteStr);
        }
        if (BracesFlag == true) {
            if ((!CompleteStr.equalsIgnoreCase("1.0")) || ((LATermOperatorBraces == '/'))) {
                CompleteStr = CompleteStr + (char) LATermOperatorBraces + "(" + BracesStr + ")";
            } else if ((CompleteStr.equalsIgnoreCase("1.0")) && (LATermOperatorBraces == '*')) {
                CompleteStr = "(" + BracesStr + ")";
            }
        }

        log.info("SimpleTerm:FormSimpleTermParse:Final:End Context:CompleteStr=" + CompleteStr);

        return CompleteStr;
    }

    public static void ParseLog(String ExprDesc, String HighLevelCallFunc, String InitialORFinal, String StrDesc, String MyStr) {
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":" + StrDesc + "=" + MyStr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posSign=" + posSign + " chSign=" + (char) chSign + " chTermSign=" + (char) chTermSign);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posLA=" + posLA + " chLA=" + (char) chLA);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posVar=" + posVar + " chVar=" + (char) chVar);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posIndConst=" + posIndConst + " chIndConst=" + (char) chIndConst);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posTerm=" + posTerm + " chTerm=" + (char) chTerm);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":pos=" + pos + " ch=" + (char) ch);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":posDiff=" + posDiff + " chDiff=" + (char) chDiff);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":ContainsIndConstFlag=" + ContainsIndConstFlag + " ComplexAssignFlag=" + ComplexAssignFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":TrailConstantFlag=" + TrailConstantFlag + " GotVariableFlag=" + GotVariableFlag + " GotIndConstFlag=" + GotIndConstFlag + " GotExpntNumFlag=" + GotExpntNumFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":GotVariablePos=" + GotVariablePos + " GotIndConstPos=" + GotIndConstPos + " TermOperatorPos=" + TermOperatorPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":LATermOperator=" + (char) LATermOperator + " LATermOperatorBraces=" + (char) LATermOperatorBraces);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + ":LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " LAVarTermOperator=" + (char) LAVarTermOperator + " TrailConstantOperator=" + (char) TrailConstantOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAPreTermOperator=" + (char) LAPreTermOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADyYByDxMAPOperator=" + (char) LADyYByDxMAPOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADiffTermOperator=" + (char) LADiffTermOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAMAPOperator=" + (char) LAMAPOperator + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLog:" + InitialORFinal + " LAVarSimpleMapFlag=" + LAVarSimpleMapFlag + " LAVarComplexMapFlag=" + LAVarComplexMapFlag);
    }

    public static String LimitFByG(String f, String g, int Operator, String DiffWithRespTo, int HighDerv, Double ValueVar) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        SimpleExpression expSimpleF;
        SimpleExpression expSimpleG;
        DiffExpr expF;
        DiffExpr expG;
        Expression expValueF;
        Expression expValueG;
        String CurrentF = f;
        String CurrentG = g;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        Double FValue = 0.0;
        Double GValue = 0.0;
        variables.put(DiffWithRespTo, ValueVar);
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
        for (int i = 0; i < HighDerv; i++) {
            CurrentF = eatAll(CurrentF, ' ');
            CurrentG = eatAll(CurrentG, ' ');

            expSimpleF = parseSimple(CurrentF, variables, variablesDiff, DiffWithRespTo);
            expSimpleG = parseSimple(CurrentG, variables, variablesDiff, DiffWithRespTo);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo);
            FValue = expValueF.eval();
            expValueG = parse(expSimpleG.SimpleExpr(), variables, DiffWithRespTo);
            GValue = expValueG.eval();
            //L'Hospitals's Rule
            //0/0
            if ((((FValue == 0.0) && (GValue == 0.0)
                    || (GValue == 0.0))
                    && (Operator == '/'))
                    || //+-Infinity/+-Infinity
                    ((((Double.isInfinite(FValue)) && (Double.isInfinite(GValue))
                    || (Double.isInfinite(GValue)))
                    && (Operator == '/')))) {
                expF = parseDiff(expSimpleF.SimpleExpr(), variables, variablesDiff, DiffWithRespTo);
                expG = parseDiff(expSimpleG.SimpleExpr(), variables, variablesDiff, DiffWithRespTo);
                CurrentF = expF.DiffExpr();
                CurrentG = expG.DiffExpr();
            } else {
                if (Operator == '/') {
                    return ("" + (FValue / GValue));
                } else if (Operator == '*') {
                    return ("" + (FValue * GValue));
                } else if (Operator == '+') {
                    return ("" + (FValue + GValue));
                } else if (Operator == '-') {
                    return ("" + (FValue - GValue));
                }
            }
        }
        return "";
    }

    public Double Fact(long n, Double Result) {
        Double TempResult = 1.0;
        if (n > 1) {
            if (Result > 100000) {
                log.info("Fact:Result=" + Result);
                return (double) 100000;
            } else {
                TempResult = Result * n;
                log.info("Fact:Result=" + Result);
                if ((TempResult > 100000) && (Result < 100000)) {
                    return Result;
                } else {
                    return Fact(n - 1, TempResult);
                }
            }
        } else {
            return Result;
        }
    }
//Function to generate Prime Number just below MaxValue

    public static long PrimeNumber(long MaxValue) {
        long Prime = 0;
        long number = 1;
        PrimeArray[0] = new Long(2);
        int i = 0;
        for (number = 3; number < MaxValue; number += 2) {
            for (i = 0; i < LastIndex;) {
                if ((number % PrimeArray[i]) == 0) {
                    break;
                } else if (i > (6 * Math.sqrt(number))) {
                    break;
                } else {
                    i++;
                }
            }
            if (i == LastIndex) {
                PrimeArray[LastIndex++] = new Long(number);
            }
        }
        Prime = PrimeArray[LastIndex - 1];
        return Prime;
    }

    public static long GetPrimeNumber(long MaxValue, int j) {
        long Prime = 0;
        for (int i = LastIndex - 1; (i >= 0); i--) {
            if (MaxValue <= PrimeArray[i]) {
                Prime = PrimeArray[i - 1];
            }
        }
        return Prime;
    }

    public static void main(String... args) {
        //Starting Test Case :21 to 34, 39,40 , 101 to 104, 201 to 209
        String StartMyTestCase = "All";//Test Case Number : Optiona :All or a Single Number
        String EndMyTestCase = "110007";//Test Case Number : Optiona :All or a Single Number
        int MaxDiffOrder = 5; // Maximum Differentiation Order : 7 :Integration Order : 5;
        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        TestHarness MyTestData = new TestHarness(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        System.out.println(ConsoleColors.BLUE + "Starting Test Case: 196 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Sanity Test Cases: 100 to 104 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Unit Test Cases: 5019 to 5042 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Names Test Cases: 10001 to 10005 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Coefficients Test Case: 20001 to 20092 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Exponent Test Case: 30001 to 30011 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "SignedX Case: 40001 to 40034 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Independent Constants Case: 50001 to 50017 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Parenthesis Test Case: 80001 to 80012 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Function Test Case: 90001 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Function Argument Test Case: 100001 " + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Software Engg Test Case: 110001 " + ConsoleColors.RESET);
        //Uncomment Below Line for Stand Alone Differentiation Regression Suite
        mainRegresssionDiffTest(MaxDiffOrder, MyTestData);

        //Uncomment Below Line for Stand Alone Integration Regression Suite
        mainRegressionIntegrTest(MaxDiffOrder, MyTestData);

        //Uncomment Below Line for Differentiation Integration Mapping
        //mainDiffIntegreTest(FailingTestHarnessDiff(TestCase), MaxDiffOrder, MyTestData);
        //Uncomment Below Line for Integration Differentiation  Mapping
        //mainIntegreDiffTest(FailingTestHarnessDiff(TestCase), MaxDiffOrder, MyTestData);
        //Uncomment Below Line for User String Usage
        //mainUser(args);
        //mainAlgebraUser(args);
    }
}
