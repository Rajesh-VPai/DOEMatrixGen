/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.DiffrIntegrSep.IsAConstant;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.NormalNotPareFlag;
import static Calculus.MathsContxtLAv.ParenthesisFlag;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.MathsContxtLAv.parseSimple;
import static Calculus.PredicateSep.parsePredicate;
import static Calculus.UsageCalculus.DiffWithRespTo;
import static Calculus.MathsContxtLAv.LogLevel.Off;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class CalculusApplication {

    public static Logger log = Logger.getLogger(DiffrIntegrSep.class.getName());
    private static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();

    private static int posHGH = -1, chHGH;
    private static int chNextHGH;
    private static String StrHGH;

    public static Long[] PrimeArray = new Long[10000];
    public static int LastIndex = 1;

    static void nextCharHGH() {
        ++posHGH;
        chHGH = (posHGH < StrHGH.length()) ? StrHGH.charAt(posHGH) : -1;
        if ((chHGH == -1) && (posHGH >= StrHGH.length())) {
            posHGH = StrHGH.length();
        } else if ((chHGH == -1) || ((chHGH == 0))) {
            throw new RuntimeException("nextChar:nextChar:Unexpected: " + (char) chHGH + " int=" + (int) chHGH + " Cannot Format:" + StrHGH.substring(posHGH, StrHGH.length()));
        }
    }

    static void prevCharHGH() {
        --posHGH;
        chHGH = (posHGH >= 0) ? StrHGH.charAt(posHGH) : -1;
        if ((chHGH == -1) && (posHGH < 0)) 
            ; else if ((chHGH == -1) || ((chHGH == 0))) {
            throw new RuntimeException("prevChar:prevChar:Unexpected: " + (char) chHGH + " int=" + (int) chHGH + " Cannot Format:" + StrHGH.substring(posHGH, StrHGH.length()));
        }
    }

    static boolean eatHGH(int CharToEat) {
        while (chHGH == ' ') {
            nextCharHGH();
        }
        if (chHGH == CharToEat) {
            nextCharHGH();
            return true;
        }
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

    public static String eatAllHGH(String Temp, int CharToEat) {
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
        if ((posHGH < StrHGH.length())
                && (StrHGH.charAt(posHGH) != chHGH)) {
            if (StrHGH.charAt(posHGH + 1) == chHGH) {
                posHGH++;
            } else if (StrHGH.charAt(posHGH - 1) == chHGH) {
                posHGH--;
            }
        }
    }

    //Created by Rajesh Pai
// Implements Solutions  for Differential Equations Expressions
//by computing the Differential OR the Integral Term as a String
    @FunctionalInterface
    public interface HghLvlExprODE {

        String Solution();
    }

    public static HghLvlExprODE parseHghLvlExprODE(String HighLvlDiffEquation, Map<String, Double> variables, String DiffWithRespTo, String OutputVar, String HighLvlEqn, String HumanReadODE) {

        posHGH = -1;
        chHGH = 0;
        StrHGH = HighLvlDiffEquation;
        ParenthesisFlag = false;
        NormalNotPareFlag = true;

        return new Object() {

            HghLvlExprODE parseHghLvlExprODE() {
                nextCharHGH();
                HghLvlExprODE x = parseExpression();
                log.info("HghLvlExpr:parseHghLvlExpr:Initial Str=" + StrHGH);
                log.info("HghLvlExpr:parseHghLvlExpr:Final Equation=" + x.Solution());
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(pos, str.length()).equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Expression:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                MathsContxtLAv.Expression expValue;

                expValue = MathsContxtLAv.parse(x.Solution(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                if (expValue.eval() == 0) {
                    System.out.println("parseHghLvlExpr:Full ODE=" + x.Solution() + " DiffWithRespTo=" + DiffWithRespTo);
                    System.out.println("parseHghLvlExpr:ODE Value=" + expValue.eval());
                    System.out.println("parseHghLvlExpr:The Equation =" + HighLvlEqn + " i.e for ODE=" + HumanReadODE + " is MOSTLY a Probable Solution.");
                    return x;
                } else {
                    System.out.println("parseHghLvlExpr:Full ODE=" + x.Solution()+ " DiffWithRespTo=" + DiffWithRespTo);
                    System.out.println("parseHghLvlExpr:ODE Value=" + expValue.eval());
                    System.out.println("parseHghLvlExpr:The Equation =" + HighLvlEqn + " i.e for ODE=" + HumanReadODE + " is NOT a Probable Solution.");
                    return (() -> "");
                }
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            HghLvlExprODE parseExpression() {
                HghLvlExprODE x = parseTerm();
                log.info("parseExpression:Initial=" + x.Solution());
                for (;;) {
                    //log.warn("Expr:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch);
                    log.info("parseExpression:Loop: ODE=" + x.Solution() + " chHGH=" + (char) chHGH + " posHGH=" + posHGH);
                    if (eatHGH(')')) {
                        return x;
                    } else if (eatHGH('+')) {// addition
                        //nextChar();
                        HghLvlExprODE a = x, b = parseTerm();
                        x = (() -> a.Solution() + "+" + b.Solution());
                        log.info("parseExpression:ODE=" + x.Solution());
                    } else if (eatHGH('-')) { // subtraction
                        //nextChar();
                        HghLvlExprODE a = x, b = parseTerm();
                        x = (() -> a.Solution() + "-" + b.Solution());
                        log.info("parseExpression:ODE=" + x.Solution());
                    } else {
                        log.info("parseExpression:Return Value ODE=" + x.Solution() + " chHGH=" + (char) chHGH + " posHGH=" + posHGH);
                        return x;
                    }
                }
            }

            HghLvlExprODE parseTerm() {
                HghLvlExprODE x = parseFactor();
                for (;;) {
                    if (eatHGH('*')) { // multiplication
                        //nextChar();
                        HghLvlExprODE a = x, b = parseFactor();
                        x = (() -> a.Solution() + "*" + b.Solution());
                    } else if (eatHGH('/')) { // division
                        //nextChar();
                        HghLvlExprODE a = x, b = parseFactor();
                        x = (() -> a.Solution() + "/" + b.Solution());
                    } else {
                        return x;
                    }
                }
            }

            HghLvlExprODE parseFactor() {
                HghLvlExprODE x = parseBase();
                for (;;) {
                    if (eatHGH('^')) { // exponentiation
                        //nextChar();
                        HghLvlExprODE a = x, b = parseBase();
                        x = (() -> a.Solution() + "^" + b.Solution());
                    } else {
                        return x;
                    }
                }
            }

            HghLvlExprODE parseMapping() {
                HghLvlExprODE x = (() -> "");
                for (;;) {
                    if (chHGH == '@') { // Input
                        //nextChar();
                        HghLvlExprODE a = x, b = parseBase();
                        x = (() -> a.Solution() + b.Solution());
                    } else if (chHGH == '_') { // Output
                        HghLvlExprODE a = x, b = parseBase();
                        x = (() -> a.Solution() + b.Solution());
                    } else {
                        return x;
                    }
                }
            }

            HghLvlExprODE parseBase() {
                int startPos = posHGH;
                HghLvlExprODE x = (() -> "");
                if (eatHGH('-')) { // unary minus
                    HghLvlExprODE b = parseBase();
                    x = (() -> (-1.0) + "*" + b.Solution());
                    return x;
                } else if (eatHGH('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if (eatHGH('(')) { //parentheses
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    x = parseExpression();
                    eatHGH(')');
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    return x;
                } else if ((chHGH == '@') 
                        && ((StrHGH.charAt(posHGH + 1) == 'S') 
                        || (StrHGH.charAt(posHGH + 1) == 's'))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while ((chHGH != '@') && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    MathsContxtLAv.SimpleExpression expSimple;
                    String Exprn = StrHGH.substring(startPos, posHGH).trim();
                    expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
            
                    //Assign the correct Equation
                    String MySymbolicEqn = expSimple.SimpleExpr();
                    log.info("HghLvlExpr:parseBase:Simplified:Equation=" + MySymbolicEqn);
                    final String Equationfin = MySymbolicEqn;
                    x = (() -> Equationfin);
                    return x;
                } else if ((chHGH == '@') 
                        && ((StrHGH.charAt(posHGH + 1) == 'F') 
                        || (StrHGH.charAt(posHGH + 1) == 'f'))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while ((chHGH != '@') && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    String Diff = StrHGH.substring(startPos, posHGH).trim();
                    log.info("HghLvlExpr:parseBase:Expr:Diff=" + Diff);
                    //Assign the correct Equation
                    String MySymbolicEqn = HighLvlEqn;
                    log.info("HghLvlExpr:parseBase:Expr:Equation=" + MySymbolicEqn);
                    final String Equationfin = MySymbolicEqn;
                    x = (() -> Equationfin);
                    return x;
                } else if (((chHGH == '@')
                        && ((StrHGH.charAt(posHGH + 1) == 'D') 
                        || (StrHGH.charAt(posHGH + 1) == 'd')))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while (((chHGH != '@')) && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    String Diff = StrHGH.substring(startPos, posHGH).trim();
                    log.info("HghLvlExpr:parseBase:Diff:Diff=" + Diff + " chHGH=" + (char) chHGH + " posHGH=" + posHGH);
                    log.info("HghLvlExpr:parseBase:Diff:Diff=" + Diff);
                    //Process Diff:Order Number
                    String[] TempDataOrder = Diff.split("~");
                    int Order = -1;
                    log.info("HghLvlExpr:parseBase:Diff:Order:TempDataOrder[1]=" + TempDataOrder[1]);
                    if ((TempDataOrder.length > 1) && (TempDataOrder[1] != null) && (!TempDataOrder[1].equalsIgnoreCase(""))) {
                        Order = Integer.parseInt(TempDataOrder[1]);
                    }
                    //Assign the correct Equation
                    MathsContxtLAv.SimpleExpression Fx;
                    DiffrIntegrSep.DiffExpr FDash;
                    String MySymbolicEqn = HighLvlEqn;
                    String SimpleMySymbolicEqn = "";
                    for (int i = 1; i < (Order + 1); i++) {
                        System.out.println("parseEqn:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, DiffWithRespTo);
                        SimpleMySymbolicEqn = Fx.SimpleExpr();
                        System.out.println("parseEqn:Simple Fx=" + SimpleMySymbolicEqn);
                        FDash = DiffrIntegrSep.parseDiff(SimpleMySymbolicEqn, variables, DiffWithRespTo);
                        MySymbolicEqn = FDash.DiffExpr();
                        System.out.println("parseEqn:RAW:Diff Expression F(" + i + "th) Order=" + MySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, DiffWithRespTo);
                        SimpleMySymbolicEqn = Fx.SimpleExpr();
                        System.out.println("parseEqn:Diff Expression F(" + i + "th) Order=" + SimpleMySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                    }
                    System.out.println("HghLvlExpr:parseBase:Diff:Equation=" + MySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                    System.out.println("HghLvlExpr:parseBase:Diff:Equation=" + SimpleMySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                    final String Equationfin = SimpleMySymbolicEqn;
                    if (Equationfin == null) {
                        x = (() -> "");
                    } else {
                        x = (() -> Equationfin);
                    }
                    System.out.println("HghLvlExpr:parseBase:Diff:x.Solution()=" + x.Solution() + " chHGH=" + (char) chHGH + " pos=" + posHGH);
                    return x;
                } else if (((chHGH == '@')
                        && ((StrHGH.charAt(posHGH + 1) == 'I') 
                        || (StrHGH.charAt(posHGH + 1) == 'i')))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while (((chHGH != '@')) && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    String Integral = StrHGH.substring(startPos, posHGH).trim();
                    log.info("HghLvlExpr:parseBase:Integral:Integral=" + Integral);
                    //Process Integral:Order Number
                    String[] TempDataOrder = Integral.split("~");
                    int Order = -1;
                    log.info("HghLvlExpr:parseBase:Integral:Order:TempDataOrder[1]=" + TempDataOrder[1]);
                    if ((TempDataOrder.length > 1) && (TempDataOrder[1] != null) && (!TempDataOrder[1].equalsIgnoreCase(""))) {
                        Order = Integer.parseInt(TempDataOrder[1]);
                    }
                    //Assign the correct Equation
                    MathsContxtLAv.SimpleExpression Fx;
                    DiffrIntegrSep.IntegrExpr FIntegral;
                    String MySymbolicEqn = HighLvlEqn;
                    String SimpleMySymbolicEqn = "";
                    for (int i = 1; i < (Order + 1); i++) {
                        System.out.println("parseEqn:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + DiffWithRespTo);
                        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, DiffWithRespTo);
                        SimpleMySymbolicEqn = Fx.SimpleExpr();
                        log.info("parseEqn:Simple Fx=" + SimpleMySymbolicEqn);
                        FIntegral = DiffrIntegrSep.parseIntegr(SimpleMySymbolicEqn, variables, DiffWithRespTo);
                        MySymbolicEqn = FIntegral.IntegrExpr();
                        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, DiffWithRespTo);
                        SimpleMySymbolicEqn = Fx.SimpleExpr();
                        log.info("parseEqn:Integral Expression F(" + i + "th) Order=" + MySymbolicEqn);
                    }
                    log.info("HghLvlExpr:parseBase:Integral:Equation=" + MySymbolicEqn);
                    log.info("HghLvlExpr:parseBase:Integral:Equation=" + MySymbolicEqn);
                    final String Equationfin = SimpleMySymbolicEqn;
                    if (Equationfin == null) {
                        x = (() -> "");
                    } else {
                        x = (() -> Equationfin);
                    }
                    return x;
                } else if ((chHGH == '@') 
                        && ((StrHGH.charAt(posHGH + 1) == 'V') 
                        || (StrHGH.charAt(posHGH + 1) == 'v'))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while ((chHGH != '@') && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    String Exprn = StrHGH.substring(startPos, posHGH).trim();
                    
                    //Assign the correct Equation
                    String MySymbolicEqn = Exprn;
                    log.info("HghLvlExpr:parseBase:Variable:DiffWithRespTo=" + MySymbolicEqn);
                    final String Equationfin = MySymbolicEqn;
                    x = (() -> Equationfin);
                    return x;
                } else if ((chHGH == '@') 
                        && ((StrHGH.charAt(posHGH + 1) == 'P') 
                        || (StrHGH.charAt(posHGH + 1) == 'p'))) {
                    if (chHGH == '@') {
                        nextCharHGH();
                    }
                    startPos = posHGH;
                    nextCharHGH();
                    while ((chHGH != '@') && (chHGH != -1)) {
                        nextCharHGH();
                    }
                    if (chHGH != -1) {
                        nextCharHGH();
                    }
                    PredicateSep.PredicateBool expPredicate;
                    String Exprn = StrHGH.substring(startPos, posHGH).trim();
                    expPredicate = parsePredicate(Exprn, variables, "Silent", MathsContxtLAv.LogLevel.Off);
                    //Assign the correct Equation
                    log.info("HghLvlExpr:parseBase:Predicate:Exprn=" + Exprn);
                    Boolean MySymbolicBool = expPredicate.eval();
                    log.info("HghLvlExpr:parseBase:Predicate:Boolean Value=" + MySymbolicBool);
                    final Boolean Equationfin = MySymbolicBool;
                    x = (() -> ""+ Equationfin);
                    return x;
                } else if (IsNumber(chHGH)) { //numbers
                    while (IsNumber(chHGH)) {
                        nextCharHGH();
                    }
                    double xx = 1.0;
                    if ((StrHGH != null) && (!StrHGH.equalsIgnoreCase("")) && (!StrHGH.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrHGH.substring(startPos, posHGH));
                    }
                    final Double XX = xx;
                    x = () -> "" + XX;
                    if ((chHGH == ')') && (posHGH == (StrHGH.length() - 1))) {
                        chHGH = -1;
                        posHGH++;
                    } else if (chHGH == ')') {
                        //nextCharHGH();
                    }
                    log.info("HghLvlExpr:parseBase:Diff:x.eval()=" + x.Solution() + " chHGH=" + (char) chHGH + " posHGH=" + posHGH);

                    return x;
                } else if (IsVariable(chHGH)) {//functions & variables
                    while (IsVariable(chHGH)) {
                        nextCharHGH();
                    }
                    String name = StrHGH.substring(startPos, posHGH).trim();
                    log.info("HghLvlExpr:parseBase:name=" + name);
                    if (functions.containsKey(name)) {
                        HghLvlExprODE arg = parseExpression();
                        x = () -> name + arg.Solution();
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                            x = () -> "" + name;
                        }
                        log.info("HghLvlExpr:parseBase:x.Solution()=" + x.Solution());
                    }
                    if ((chHGH == ')') && (posHGH == (StrHGH.length() - 1))) {
                        chHGH = -1;
                        posHGH++;
                    } else if (chHGH == ')') {
                        //nextCharHGH();
                    }
                    log.info("HghLvlExpr:parseBase:Diff:x.Solution()=" + x.Solution() + " chHGH=" + (char) chHGH + " posHGH=" + posHGH);
                    return x;
                } else {
                    if ((startPos < StrHGH.length()) && (startPos < posHGH)) {
                        throw new RuntimeException("Expression:parseBase:Unexpected: " + (char) chHGH + " int=" + (int) chHGH + " Cannot Format:" + StrHGH.substring(posHGH, StrHGH.length()));
                    } else if (chHGH != -1) {
                        throw new RuntimeException("Expression:parseBase:Unexpected: " + (char) chHGH + " int=" + (int) chHGH + " Cannot Format:" + StrHGH.substring(posHGH, StrHGH.length()));
                    }
                    return x;
                }
            }
        }.parseHghLvlExprODE();
    }

    public static String LimitFByG(String f, String g, int Operator, String DiffWithRespTo, int HighDerv, Double ValueVar) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MathsContxtLAv.SimpleExpression expSimpleF;
        MathsContxtLAv.SimpleExpression expSimpleG;
        DiffrIntegrSep.DiffExpr expF;
        DiffrIntegrSep.DiffExpr expG;
        MathsContxtLAv.Expression expValueF;
        MathsContxtLAv.Expression expValueG;
        String CurrentF = f;
        String CurrentG = g;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        Double FValue = 0.0;
        Double GValue = 0.0;
        int i = 0;
        variables.put(DiffWithRespTo, ValueVar);
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
        for (i = 0; i < HighDerv; i++) {
            CurrentF = eatAll(CurrentF, ' ');
            CurrentG = eatAll(CurrentG, ' ');
            System.out.println("LimitFByG:CurrentgnNum=" + CurrentF);
            System.out.println("LimitFByG:CurrentgnDen=" + CurrentG);

            expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
            expSimpleG = parseSimple(CurrentG, variables, DiffWithRespTo);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FValue = expValueF.eval();
            expValueG = parse(expSimpleG.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            GValue = expValueG.eval();
            //L'Hospitals's Rule
            //0/0
            if (((((FValue.doubleValue() == 0.0) && (GValue.doubleValue() == 0.0))
                    || (GValue.doubleValue() == 0.0))
                    && (Operator == '/'))
                    || //+-Infinity/+-Infinity
                    (((((Double.isInfinite(FValue)) && (Double.isInfinite(GValue)))
                    || (Double.isInfinite(GValue)))
                    && (Operator == '/')))) {
                expF = DiffrIntegrSep.parseDiff(expSimpleF.SimpleExpr(), variables, DiffWithRespTo);
                expG = DiffrIntegrSep.parseDiff(expSimpleG.SimpleExpr(), variables, DiffWithRespTo);
                CurrentF = expF.DiffExpr();
                CurrentG = expG.DiffExpr();
            } else {
                if (Operator == '/') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue / GValue));
                } else if (Operator == '*') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s OrderLi=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue * GValue));
                } else if (Operator == '+') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue + GValue));
                } else if (Operator == '-') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue - GValue));
                }
            }
        }
        System.out.println("LimitFByG:Empty:" + (char) Operator + ":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
        return "";
    }

    public static Double Fact(long n, Double Result) {
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

    // Monotonic Test
    public static String IsMonotonic(String f, String DiffWithRespTo, int HighDerv, Double ValueVarA, Double ValueVarB) {
        String Result = "";
        MathsContxtLAv.SimpleExpression expSimpleF;
        DiffrIntegrSep.DiffExpr expF;
        MathsContxtLAv.Expression expValueF;
        String CurrentF = f;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        Double FDiffValueA = 0.0;
        Double FDiffValueB = 0.0;
        Double FValueA = 0.0;
        Double FValueB = 0.0;
        System.out.println("IsMonotonic:DiffWithRespTo=" + DiffWithRespTo + " ValueVarA=" + ValueVarA);
        System.out.println("IsMonotonic:DiffWithRespTo=" + DiffWithRespTo + " ValueVarB=" + ValueVarB);
        for (int i = 0; i < HighDerv; i++) {
            //Get f(A), F(B)
            variables.put(DiffWithRespTo, ValueVarA);
            CurrentF = eatAll(CurrentF, ' ');
            expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FValueA = expValueF.eval();
            variables.put(DiffWithRespTo, ValueVarB);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FValueB = expValueF.eval();
            //Get dy/dx Equation
            expF = DiffrIntegrSep.parseDiff(expSimpleF.SimpleExpr(), variables, DiffWithRespTo);
            CurrentF = expF.DiffExpr();
            expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
            //1st Interval dy/dx
            //Get dy/dx(A)
            variables.put(DiffWithRespTo, ValueVarA);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FDiffValueA = expValueF.eval();
            //2nd Interval dy/dx
            //Get dy/dx(B)
            variables.put(DiffWithRespTo, ValueVarB);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FDiffValueB = expValueF.eval();
            if ((FDiffValueA.doubleValue() == 0.0) || (FDiffValueB.doubleValue() == 0.0)
                    || //+-Infinity/+-Infinity
                    Double.isInfinite(FDiffValueA)
                    || Double.isInfinite(FDiffValueB)
                    || //+-Infinity/+-Infinity
                    Double.isNaN(FDiffValueA)
                    || Double.isNaN(FDiffValueB)) {
                Result = "NotMonotonic" + ":FDiffValueA:" + FDiffValueA + ":FDiffValueB:" + FDiffValueB;
            } else if ((Double.isFinite(FDiffValueA)) && (Double.isFinite(FDiffValueB))) {
                if ((FValueA.doubleValue() < FValueB.doubleValue())) {
                    Result = "MonotonicInc" + ":FValueA:" + FValueA + ":FValueB:" + FValueB;
                } else if ((FValueA.doubleValue() > FValueB.doubleValue())) {
                    Result = "MonotonicDec" + ":FValueA:" + FValueA + ":FValueB:" + FValueB;
                } else if ((FValueA.doubleValue() == FValueB.doubleValue())) {
                    Result = "NotMonotonic" + ":FValueA:" + FValueA + ":FValueB:" + FValueB;
                } else {
                    //Should not come here Logically due to missing .doubleValue code
                    //May come here Mathematically due to missing .doubleValue code
                    Result = "Unknown" + ":FValueA:" + FValueA + ":FValueB:" + FValueB + ":FDiffValueA:" + FDiffValueA + ":FDiffValueB:" + FDiffValueB;
                }
            } else {
                Result = "Unknown" + ":FValueA:" + FValueA + ":FValueB:" + FValueB + ":FDiffValueA:" + FDiffValueA + ":FDiffValueB:" + FDiffValueB;
            }
        }
        System.out.println("IsMonotonic:Result=" + Result);
        return Result;
    }

    // Dirichlet's Test for Analiticity of functions & Inverse function
    public static String DirichletTest(String f, String DiffWithRespTo, int HighDerv, Double ValueVarA, Double ValueVarB) {
        String Result = "";
        Double FLimitValueA = 0.0;
        Double FLimitValueB = 0.0;
        String ValueLimitSum = "";
        String ValueDAlembert = "";
        String Monotonic = IsMonotonic(f, DiffWithRespTo, HighDerv, ValueVarA, ValueVarB);
        if ((Monotonic.contains("MonotonicInc"))
                || (Monotonic.contains("MonotonicDec"))) {
            FLimitValueA = Double.parseDouble(LimitFByGLagrange(f, "1.0", '*', DiffWithRespTo, HighDerv, ValueVarA));
            FLimitValueB = Double.parseDouble(LimitFByGLagrange(f, "1.0", '*', DiffWithRespTo, HighDerv, ValueVarB));
            ValueLimitSum = CnvgnceLimitSumLagrange(f, DiffWithRespTo, HighDerv, ValueVarA, ValueVarB);
            String nVariable = "n";
            String nthTerm = f.replaceAll(DiffWithRespTo, nVariable);
            ValueDAlembert = CnvgnceRatioLagrange(nthTerm, nVariable, DiffWithRespTo, HighDerv, ValueVarA, ValueVarB);
            if (((FLimitValueA.doubleValue() == 0.0) || (FLimitValueB.doubleValue() == 0.0)) && (!ValueLimitSum.contains("Divergent"))) {
                Result = "Analytic" + ":" + Monotonic;
            } else if ((ValueLimitSum.contains("Inconclusive")) && (ValueDAlembert.contains("Convergent"))) {
                Result = "Convergent" + ":FLimitValueA:" + FLimitValueA + ":FLimitValueB:" + FLimitValueB + ":ValueLimitSum:" + ValueLimitSum + ":ValueDAlembert:" + ValueDAlembert;
            } else if ((ValueLimitSum.contains("Divergent")) && (ValueDAlembert.contains("Convergent"))) {
                Result = "Convergent" + ":FLimitValueA:" + FLimitValueA + ":FLimitValueB:" + FLimitValueB + ":ValueLimitSum:" + ValueLimitSum + ":ValueDAlembert:" + ValueDAlembert;
            } else if ((ValueLimitSum.contains("Divergent")) && (ValueDAlembert.contains("Divergent"))) {
                Result = "Divergent" + ":FLimitValueA:" + FLimitValueA + ":FLimitValueB:" + FLimitValueB + ":ValueLimitSum:" + ValueLimitSum + ":ValueDAlembert:" + ValueDAlembert;
            } else {
                Result = "NotAnalytic" + ":FLimitValueA:" + FLimitValueA + ":FLimitValueB:" + FLimitValueB + ":ValueLimitSum:" + ValueLimitSum;
            }
        } else {
            Result = "NotAnalytic" + ":" + Monotonic + ":FLimitValueA:" + FLimitValueA + ":FLimitValueB:" + FLimitValueB + ":ValueLimitSum:" + ValueLimitSum;
        }
        System.out.println("DirichletTest:Result=" + Result);
        return Result;
    }

    // Inverse of a Function
    //Convergence Tests
    //Limit of Summand
    public static String CnvgnceLimitSum(String f, String DiffWithRespTo, int HighDerv, Double ValueVarA, Double ValueVarB) {
        String Result = "";
        Double Value = 0.0;
        Value = Double.parseDouble(LimitFByG(f, "1.0", '*', DiffWithRespTo, HighDerv, ValueVarA));
        if ((Value.doubleValue() != 0.0) || (Value.doubleValue() == Double.NaN)) {
            Result = "Divergent" + ":Value:" + Value;
        } else if (Value.doubleValue() == 0.0) {
            Result = "Inconclusive" + ":Value:" + Value;
        }
        return Result;
    }

    //Ratio Test OR D'Alembert's criterion
    public static String CnvgnceRatio(String nthTerm, String nVariable, String DiffWithRespTo, int HighDerv, Double ValueVar, Double MaxTerm) {
        String Result = "";
        String TempResult = "";
        Double Value = 0.0;
        for (double n = 1.0; n < MaxTerm; n += 1.0) {
            variables.put(nVariable, n);
            nthTerm = nthTerm.replaceAll(DiffWithRespTo, nVariable);
            String nplus1thTerm = nthTerm.replaceAll(nVariable, ("(" + nVariable + "+" + 1.0 + ")"));
            TempResult = LimitFByG(nplus1thTerm, nthTerm, '/', nVariable, HighDerv, n);
            System.out.println("CnvgnceRatio:Limit:Result=" + TempResult);
            Value = Double.parseDouble(TempResult);
            if ((Value.doubleValue() < 1.0) && (Value.doubleValue() > 0.0)) {
                Result = "Convergent" + ":Value:" + Value;
            } else if (Value.doubleValue() > 1.0) {
                Result = "Divergent" + ":Value:" + Value;
            } else if (Value.doubleValue() == 0.0) {
                Result = "Inconclusive;MayConverge" + ":Value:" + Value;
            }
        }
        return Result;
    }

    // Inverse of a Function
    public static String FInverseLagrangeBurmannLowLevel(String f, String DiffWithRespTo, int HighDerv, Double ValueVarA, Double ValueVarB, String kthTermNum, String kthTermDen, int Operator, int MaxTerm) {
        String FuncType = "";
        String Result = "" + ValueVarA;
        String gn = "";
        String CurrentgnNum = "";
        String CurrentgnDen = "";

        MathsContxtLAv.SimpleExpression expSimpleF = (() -> "");;
        DiffrIntegrSep.DiffExpr expF;
        MathsContxtLAv.SimpleExpression expSimpleNum = (() -> "");
        MathsContxtLAv.SimpleExpression expSimpleDen = (() -> "");
        DiffrIntegrSep.DiffExpr expNum = (() -> "");
        DiffrIntegrSep.DiffExpr expDen = (() -> "");
        MathsContxtLAv.Expression expValueF;
        SimpleAlgebraSep.SimpleAlgebra F;
        String CurrentF = f;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        Double ValueA = 0.0;
        Double FValueA = 0.0;
        Double FDiffValueA = 0.0;
        String TempGn = "";
        String Temp = "";
        String Temp1 = "";
        String Origf = eatAll(f, ' ');
        if ((Result.equalsIgnoreCase("")) && (ValueVarA.doubleValue() == 0.0)) {
            Result = "0.0";
        }
        CurrentF = eatAll(CurrentF, ' ');
        FuncType = DirichletTest(CurrentF, DiffWithRespTo, HighDerv, ValueVarA, ValueVarB);
        System.out.println("FInverseLagrangeBurmannLowLevel:FuncType=" + FuncType);
        if ((FuncType.equalsIgnoreCase("Analytic")) || (FuncType.contains("Convergent"))) {
            variables.put(DiffWithRespTo, ValueVarA);
            expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
            expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            FValueA = expValueF.eval();
            f = f.replaceAll(DiffWithRespTo, "y");
            // Caution : Derivative w.r.t y
            for (int k = 1; k < MaxTerm; k++) {
                expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
                expF = DiffrIntegrSep.parseDiff(expSimpleF.SimpleExpr(), variables, DiffWithRespTo);
                CurrentF = expF.DiffExpr();
                expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
                expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                FDiffValueA = expValueF.eval();
                if (k == 1) {
                    CurrentgnNum = "y" + "-" + (Math.abs(ValueVarA));
                    CurrentgnDen = f + "-" + (Math.abs(FValueA));
                    TempGn = "(" + CurrentgnNum + ")" + "/" + "(" + CurrentgnDen + ")";
                    System.out.println("FInverseLagrangeBurmannLowLevel:1:k=" + k + ":TempGn=" + TempGn);
                    gn = LimitFByGLagrange(CurrentgnNum, CurrentgnDen, '/', "y", HighDerv, ValueA);
                    if ((!gn.equalsIgnoreCase("")) && (!gn.contains("Infinity")) && (!gn.contains("NaN"))) {
                        Result = Result + "+" + Math.abs(Double.parseDouble(gn)) + "*" + "(" + "y" + "-" + (Math.abs(FValueA) + ")");
                    }
                    System.out.println("FInverseLagrangeBurmannLowLevel:1:k=" + k + " TempGn=" + TempGn);
                    System.out.println("FInverseLagrangeBurmannLowLevel:1:k=" + k + " gn=" + gn + " at x=" + ValueVarA);
                    System.out.println("FInverseLagrangeBurmannLowLevel:1:k=" + k + " Result=" + Result);
                } else {
                    CurrentgnNum = "y" + "-" + (Math.abs(ValueVarA));
                    for (int d = 2; d <= k; d++) {
                        Temp = ("y" + "-" + (Math.abs(ValueVarA)));
                        expSimpleNum = parseSimple(CurrentgnNum, variables, "y");
                        CurrentgnNum = expSimpleNum.SimpleExpr();
                        expSimpleNum = parseSimple(Temp, variables, "y");
                        Temp = expSimpleNum.SimpleExpr();
                        System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + " d=" + d + ":CurrentgnNum=" + CurrentgnNum + " Temp=" + Temp);
                        F = SimpleAlgebraSep.parseAlgebra(CurrentgnNum, Temp, "*", variables, "y");
                        System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + " d=" + d + ":F.Algebra()=" + F.Algebra());
                        expSimpleNum = parseSimple(F.Algebra(), variables, "y");
                        System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + " d=" + d + ":expSimpleNum.SimpleExpr()=" + expSimpleNum.SimpleExpr());
                        CurrentgnNum = expSimpleNum.SimpleExpr();
                    }
                    System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + ":EquationNum=" + CurrentgnNum);
                    CurrentgnDen = f + "-" + (Math.abs(FValueA));
                    for (int d = 2; d <= k; d++) {
                        Temp = (f + "-" + (Math.abs(FValueA)));
                        expSimpleDen = parseSimple(CurrentgnDen, variables, "y");
                        CurrentgnDen = expSimpleDen.SimpleExpr();
                        expSimpleDen = parseSimple(Temp, variables, "y");
                        Temp = expSimpleDen.SimpleExpr();
                        System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + " d=" + d + ":CurrentgnDen=" + CurrentgnDen + " Temp=" + Temp);
                        F = SimpleAlgebraSep.parseAlgebra(CurrentgnDen, Temp, "*", variables, "y");
                        System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + " d=" + d + ":F.Algebra()=" + F.Algebra());
                        expSimpleNum = parseSimple(F.Algebra(), variables, "y");
                        CurrentgnDen = expSimpleNum.SimpleExpr();
                    }
                    System.out.println("FInverseLagrangeBurmannLowLevel:else:k=" + k + ":EquationDen=" + CurrentgnDen);
                    for (int n = 1; n <= k; n++) {
                        expSimpleNum = parseSimple(CurrentgnNum, variables, "y");
                        expNum = DiffrIntegrSep.parseDiff(expSimpleNum.SimpleExpr(), variables, "y");
                        expSimpleDen = parseSimple(CurrentgnDen, variables, "y");
                        expDen = DiffrIntegrSep.parseDiff(expSimpleDen.SimpleExpr(), variables, "y");
                        //Vu' - uV'
                        if (!expSimpleNum.SimpleExpr().equalsIgnoreCase("")) {
                            F = SimpleAlgebraSep.parseAlgebra(expSimpleNum.SimpleExpr(), expDen.DiffExpr(), "*", variables, "y");
                            expSimpleNum = parseSimple(F.Algebra(), variables, "y");
                            Temp = expSimpleNum.SimpleExpr();
                            Temp = eatAll(Temp, ' ');
                        }
                        if (!expSimpleDen.SimpleExpr().equalsIgnoreCase("")) {
                            F = SimpleAlgebraSep.parseAlgebra(expSimpleDen.SimpleExpr(), expNum.DiffExpr(), "*", variables, "y");
                            expSimpleNum = parseSimple(F.Algebra(), variables, "y");
                            Temp1 = expSimpleNum.SimpleExpr();
                            Temp1 = eatAll(Temp1, ' ');
                            CurrentgnNum = Temp + "-" + Temp1;
                        }
                        //V*V
                        F = SimpleAlgebraSep.parseAlgebra(expSimpleDen.SimpleExpr(), expSimpleDen.SimpleExpr(), "*", variables, "y");
                        expSimpleNum = parseSimple(F.Algebra(), variables, "y");
                        Temp1 = expSimpleNum.SimpleExpr();
                        CurrentgnDen = Temp1;
                    }
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " CurrentgnNum=" + CurrentgnNum);
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " CurrentgnDen=" + CurrentgnDen);
                    gn = LimitFByGLagrange(CurrentgnNum, CurrentgnDen, '/', "y", HighDerv, ValueA);
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " gn=" + gn + " at x=" + ValueVarA);
                    if ((!gn.equalsIgnoreCase("")) && (!gn.contains("Infinity")) && (!gn.contains("NaN"))) {
                        Result = Result + "+" + Math.abs(Double.parseDouble(gn)) + "*" + "(" + ("y" + "-" + Math.abs(FValueA)) + ")" + "^" + k + "/" + Fact(k, 1.0);
                    }
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " Equation=" + "(" + CurrentgnNum + ")" + "/" + "(" + CurrentgnDen + ")");
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " gn=" + gn + " at x=" + ValueVarA);
                    System.out.println("FInverseLagrangeBurmannLowLevel:k=" + k + " Result=" + Result);

                }
            }
            boolean ConfirmFlag = false;
            ConfirmFlag = ConfirmConformalMap(Origf, Result, ValueVarA);
            if (ConfirmFlag == true) {
                System.out.println(ConsoleColors.BLUE + "FInverseLagrangeBurmann:Final:FInverse=" + Result + " Passed Confirmatory Test" + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + "FInverseLagrangeBurmann:Final:FInverse=" + Result + " Failed Confirmatory Test" + ConsoleColors.RESET);
            }

        } else {
            Result = FuncType;
        }
        return Result;
    }

    public static String LimitFByGLagrange(String f, String g, int Operator, String DiffWithRespTo, int HighDerv, Double ValueVar) {
        Map<String, Double> variables = new HashMap<>();
        MathsContxtLAv.SimpleExpression expSimpleF;
        MathsContxtLAv.SimpleExpression expSimpleG;
        DiffrIntegrSep.DiffExpr expF;
        DiffrIntegrSep.DiffExpr expG = (() -> "1.0");
        MathsContxtLAv.Expression expValueF;
        MathsContxtLAv.Expression expValueG;
        String CurrentF = f;
        String CurrentG = g;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        Double FValue = 0.0;
        Double GValue = 0.0;
        int i = 0;
        variables.put(DiffWithRespTo, ValueVar);
        for (i = 0; i < HighDerv; i++) {
            CurrentF = eatAll(CurrentF, ' ');
            CurrentG = eatAll(CurrentG, ' ');

            expSimpleF = parseSimple(CurrentF, variables, DiffWithRespTo);
            expSimpleG = parseSimple(CurrentG, variables, DiffWithRespTo);
            if (!expSimpleF.SimpleExpr().equalsIgnoreCase("")) {
                expValueF = parse(expSimpleF.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                FValue = expValueF.eval();
                expValueG = parse(expSimpleG.SimpleExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                GValue = expValueG.eval();
            }

            //L'Hospitals's Rule
            //0/0
            if (((((FValue.doubleValue() == 0.0) && (GValue.doubleValue() == 0.0))
                    || (GValue.doubleValue() == 0.0))
                    && (Operator == '/'))
                    || //+-Infinity/+-Infinity
                    (((((Double.isInfinite(FValue)) && (Double.isInfinite(GValue)))
                    || (Double.isInfinite(GValue)))
                    && (Operator == '/')))) {
                expF = DiffrIntegrSep.parseDiff(expSimpleF.SimpleExpr(), variables, DiffWithRespTo);
                if (!expSimpleG.SimpleExpr().equalsIgnoreCase("1.0")) {
                    expG = DiffrIntegrSep.parseDiff(expSimpleG.SimpleExpr(), variables, DiffWithRespTo);
                } else {
                    expValueF = parse(expF.DiffExpr(), variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                    FValue = expValueF.eval();
                    System.out.println("LimitFByGLagrange:End:CurrentG=1.0:L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return "" + FValue;
                }
                CurrentF = expF.DiffExpr();
                CurrentG = expG.DiffExpr();
            } else if ((FValue.doubleValue() != 0.0) && (GValue.doubleValue() != 0.0)) {
                if (Operator == '/') {
                    //System.out.println("LimitFByGLagrange:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue / GValue));
                } else if (Operator == '*') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s OrderLi=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue * GValue));
                } else if (Operator == '+') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue + GValue));
                } else if (Operator == '-') {
                    //System.out.println("LimitFByG:"+(char)Operator+":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                    return ("" + (FValue - GValue));
                }
            } else if ((FValue.doubleValue() != 0.0) && (GValue.doubleValue() == 0.0)) {
                System.out.println("LimitFByGLagrange:" + (char) Operator + ":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                return "" + Double.POSITIVE_INFINITY;
            } else if ((FValue.doubleValue() == 0.0) && (GValue.doubleValue() != 0.0)) {
                System.out.println("LimitFByGLagrange:" + (char) Operator + ":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
                return "0.0";
            }
        }
        System.out.println("LimitFByGLagrange:End:" + (char) Operator + ":L\'Hospitals\'s Order:i=" + i + ":FValue=" + FValue + ":GValue:" + GValue);
        if ((FValue.doubleValue() == 0.0) && (GValue.doubleValue() == 0.0)) {
            return "" + Double.POSITIVE_INFINITY;
        }
        return "";
    }

    //Ratio Test OR D'Alembert's criterion
    public static String CnvgnceRatioLagrange(String nthTerm, String nVariable, String DiffWithRespTo, int HighDerv, Double ValueVar, Double MaxTerm) {
        String Result = "";
        String TempResult = "";
        Double Value = 0.0;
        for (double n = 1.0; n < MaxTerm; n += 1.0) {
            variables.put(nVariable, n);
            nthTerm = nthTerm.replaceAll(DiffWithRespTo, nVariable);
            String nplus1thTerm = nthTerm.replaceAll(nVariable, ("(" + nVariable + "+" + 1.0 + ")"));
            TempResult = LimitFByGLagrange(nplus1thTerm, nthTerm, '/', nVariable, HighDerv, n);
            System.out.println("CnvgnceRatio:Limit:Result=" + TempResult);
            Value = Double.parseDouble(TempResult);
            if ((Value.doubleValue() < 1.0) && (Value.doubleValue() > 0.0)) {
                Result = "Convergent" + ":Value:" + Value;
            } else if (Value.doubleValue() > 1.0) {
                Result = "Divergent" + ":Value:" + Value;
            } else if (Value.doubleValue() == 0.0) {
                Result = "Inconclusive;MayConverge" + ":Value:" + Value;
            }
        }
        return Result;
    }

    //Limit of Summand
    public static String CnvgnceLimitSumLagrange(String f, String DiffWithRespTo, int HighDerv, Double ValueVarA, Double ValueVarB) {
        String Result = "";
        Double Value = 0.0;
        Value = Double.parseDouble(LimitFByGLagrange(f, "1.0", '*', DiffWithRespTo, HighDerv, ValueVarA));
        if ((Value.doubleValue() != 0.0) || (Value.doubleValue() == Double.NaN)) {
            Result = "Divergent" + ":Value:" + Value;
        } else if (Value.doubleValue() == 0.0) {
            Result = "Inconclusive" + ":Value:" + Value;
        }
        return Result;
    }

    public static boolean ConfirmConformalMap(String Origf, String Lagrangef, Double ValueVarA) {
        boolean ResultMap = false;
        int Order = 0;
        Double y = 0.0;
        Double w = 0.0;
        Double ValueVarB = 10.0;
        Double ValueVarBYMax = 0.0;
        MathsContxtLAv.Expression expValueX;
        MathsContxtLAv.Expression expValueY;
        Map<String, Double> variables = new HashMap<>();
        System.out.println("ConfirmConformalMap:Given y=" + Origf + " DiffWithRespTo=" + DiffWithRespTo);
        System.out.println("ConfirmConformalMap:Inverse Lagrangef x=" + Lagrangef);
        for (Double x = 0.0; Order < 10; x += (ValueVarB - ValueVarA) / 10, Order++) {
            // Analytic Check for both functions 
            String CurrentF = eatAll(Origf, ' ');
            //String Monotonic = IsMonotonic(CurrentF, DiffWithRespTo, 5, ValueVarA, ValueVarB);
            //System.out.println("ConfirmConformalMap:Origf:Monotonic=" + Monotonic);
            variables.put(DiffWithRespTo, x);
            expValueX = parse(Origf, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            y = expValueX.eval();
            variables.put(DiffWithRespTo, ValueVarB);
            expValueX = parse(Origf, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            ValueVarBYMax = expValueX.eval();
            variables.put(DiffWithRespTo, x);
            variables.put("y", y);
            String CurrentG = eatAll(Lagrangef, ' ');
            //Monotonic = IsMonotonic(CurrentF, "y", 5, y, ValueVarBYMax);
            //System.out.println("ConfirmConformalMap:Lagrangef:Monotonic=" + Monotonic);

            variables.put("y", y);
            expValueY = parse(Lagrangef, variables, "y", "Silent", MathsContxtLAv.LogLevel.Off);
            w = expValueY.eval();
            System.out.println("ConfirmConformalMap:Given x=" + x + " Forward y=" + y + " Reverse x=" + w);
            if (x.doubleValue() == w.doubleValue()) {
                ResultMap = true;
            } else {
                ResultMap = false;
            }
        }
        return ResultMap;
    }

    public static String BrownianMotionEquation(String N, String ETA, String r, String R, String T, String x, String t, String RandVar) {
        String Exprn = N + "*" + "sqrt((3*" + N + "*" + ETA + "*" + r + ")/(2*" + R + "*" + T + "))" + "*" + "exp((-3*" + Math.PI + "*" + ETA + "*" + N + "*" + r + "*" + x + "^2)/(2*" + R + "*" + T + "*" + t + "))";
        Exprn = eatAll(Exprn, ' ');
        return Exprn;
    }

    public static void xmain(String... args) {
        Map<Integer, String> variablesProbableSolutions = new HashMap<>();
        Map<String, Double> variables = new HashMap<>();
        MathsContxtLAv.MyFuncExpress();
        DiffrIntegrSep.MyFuncDiff();
        MathsContxtLAv.MyFuncSimple();
        DiffrIntegrSep.MyFuncIntegr();
        String DiffWithRespTo = "x";
        String Constant = "C";
        variables.put(DiffWithRespTo, 1.0);
        MathsContxtLAv.SimpleExpression Fx;
        DiffrIntegrSep.DiffExpr FDash;
        DiffrIntegrSep.IntegrExpr FIntegral;
        HghLvlExprODE FHighLVLEqn;
        MathsContxtLAv.Expression expValue;
        String StrexpSimpleExpressMap1 = "";
        double DblexpValueMap1 = 0;
        String[] DiffEquationODE = new String[3];
        String[] DiffEquationODEHumanRead = new String[3];
        // ODE in the Form "LHS - RHS=0" Left hand Side - Right Hand Side=0
        // dy/dx in the form @D:~1~@ 
        // d^2/dx^2=@D:~2~@ 
        // d^3/dx^3=@D:~3~@ and so on
        // Single Integral in the form @I:~1~@ 
        // Double Integral=@I:~2~@ 
        // Triple Integral=@I:~3~@ and so on
        DiffEquationODE[0] = "@D:~1~@-x^2 +3";
        DiffEquationODEHumanRead[0] = "dy/dx=x^2 -3";
        DiffEquationODE[1] = "@D:~1~@-5*x+3";
        DiffEquationODEHumanRead[1] = "dy/dx=5x -3";
        DiffEquationODE[2] = "@D:~1~@-7*y^2*x^3";
        DiffEquationODEHumanRead[2] = "dy/dx=7*y^2*x^3";
        variablesProbableSolutions.put(0, eatAllHGH("m*x+c", ' '));
        variablesProbableSolutions.put(1, eatAllHGH("1/3*x^3-3*x", ' '));
        //variablesProbableSolutions.put(2, eatAllHGH("exp(5*x) + 3/5", ' '));
        //variablesProbableSolutions.put(3, eatAllHGH("m*x+c", ' '));
        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < 2; i++) {
                String Exprn = eatAllHGH(DiffEquationODE[j], ' ');
                variables.put("y", 1.0);
                System.out.println("main:DiffEquationODE=" + DiffEquationODEHumanRead[j] + " Trying Probable Solution=" + variablesProbableSolutions.get(i));
                FHighLVLEqn = parseHghLvlExprODE(Exprn, variables, DiffWithRespTo, "y", variablesProbableSolutions.get(i), DiffEquationODEHumanRead[j]);
                String MySymbolicEqn = FHighLVLEqn.Solution();
                Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = Fx.SimpleExpr();
                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main:DiffEquationODE=" + DiffEquationODEHumanRead[j] + " Solution=" + MySymbolicEqn + " StrexpSimpleExpressMap1=" + StrexpSimpleExpressMap1 + " DblexpValueMap1=" + DblexpValueMap1);
            }
        }
        System.out.println("main:Completed");
    }

    public static void SchrodingerEquationMethod1() {
        //public static void main(String... args) {
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.println("SchrodingerEquation:Starting:SchrodingerEquationMethod1:Trial && Error from List of Probable Solutions");
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        Map<Integer, String> variablesProbableSolutions = new HashMap<>();
        Map<String, Double> variables = new HashMap<>();
        MathsContxtLAv.MyFuncExpress();
        DiffrIntegrSep.MyFuncDiff();
        MathsContxtLAv.MyFuncSimple();
        DiffrIntegrSep.MyFuncIntegr();
        String DiffWithRespTo = "x";
        String Constant = "C";
        variables.put(DiffWithRespTo, 1.0);
        MathsContxtLAv.SimpleExpression Fx;
        DiffrIntegrSep.DiffExpr FDash;
        DiffrIntegrSep.IntegrExpr FIntegral;
        HghLvlExprODE FHighLVLEqn0;
        HghLvlExprODE FHighLVLEqn1;
        HghLvlExprODE FHighLVLEqn2;
        MathsContxtLAv.Expression expValue;
        String StrexpSimpleExpressMap1 = "";
        double DblexpValueMap1 = 0;

        // ODE in the Form "LHS - RHS=0" Left hand Side - Right Hand Side=0
        // dy/dx in the form @D:~1~@ 
        // d^2/dx^2=@D:~2~@ 
        // d^3/dx^3=@D:~3~@ and so on
        // Single Integral in the form @I:~1~@ 
        // Double Integral=@I:~2~@ 
        // Triple Integral=@I:~3~@ and so on
        String Psi_x_t = "5*x^2*exp(-i*E1*t)";//Wave Function in :Parameter :complex Number x and t: Time
        String V_x_t = "x";//Potential in :Parameter :complex Number x and t: Time
        Double hBar = Double.NaN;//Reduced Planck's Constant
        Double mass = Double.NaN;//mass
        Double t = 1.0;//time
        Double x = 1.0;//x:position
        Double c = 0.0;//x:position
        /* ************************ ODE SchrodingerEquation *********** */
        int NumOfODE = 1;
        String[] DiffEquationODE = new String[NumOfODE];
        String[] DiffEquationODEHumanRead = new String[NumOfODE];
        //DiffEquationODE[0] = "i*hBar*@D:~1~@ +hBar^2/2/m*D2/dx2*Phi_x_t - V_x_t*Phi_x_t";
        DiffEquationODEHumanRead[0] = "i*hBar*dPsi_x_t/dt=-hBar^2/2/mass*D2/dx2*Psi_x_t + V_x_t*Psi_x_t";

        /* ************************ ODE SchrodingerEquation *********** */
 /* ************************ Probable Solution *********** */
        int NumOfSolutions = 3;
        variablesProbableSolutions.put(0, eatAllHGH(Psi_x_t, ' '));
        variablesProbableSolutions.put(1, eatAllHGH("m*x+c", ' '));
        variablesProbableSolutions.put(2, eatAllHGH(Psi_x_t, ' '));
        /* ************************ Probable Solution *********** */
 /* ************************ Parts of ODE *********** */
        int NumOfParts = 3;
        String[] Parts = new String[NumOfParts];
        Parts[0] = eatAllHGH("hBar*@D:~1~@",' ');//i*hBar*dPsi_x_t/dt
        Parts[1] = eatAllHGH("hBar^2/2/mass*@D:~2~@",' ');//hBar^2/2/m*D2/dx2*Psi_x_t
        Parts[2] = eatAllHGH(V_x_t + " * " + Psi_x_t,' ');//V_x_t*Psi_x_t
        String[] PartsDiffWithRespTo = new String[NumOfParts];
        PartsDiffWithRespTo[0] = "t";
        PartsDiffWithRespTo[1] = "x";
        PartsDiffWithRespTo[2] = "y";
        /* ************************ Parts of ODE *********** */
        System.out.println("SchrodingerEquation:Starting:DiffEquationODE=" + DiffEquationODEHumanRead[0]);
        System.out.println("SchrodingerEquation:Starting:Wave Function:Psi_x_t=" + Psi_x_t);
        System.out.println("SchrodingerEquation:Starting:Potential:V_x_t=" + V_x_t);
        System.out.println("SchrodingerEquation:Starting:Reduced Planck's Constant:hBar=" + hBar);
        System.out.println("SchrodingerEquation:Starting:Imaginary:i=" + "i");
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int j = 0; j < NumOfODE; j++) {
            DiffEquationODE[j] = Parts[0] + " + " + Parts[1] + " - " + Parts[2];
            System.out.println("SchrodingerEquation:Solution Loop:DiffEquationODE=" + DiffEquationODE[0]);
            for (int i = 0; i < NumOfSolutions; i++) {
                System.out.println("SchrodingerEquation:Starting:Trial " + i);
                variables.put("z", 1.0);
                variables.put("hBar", hBar);
                variables.put("mass", mass);
                variables.put("m", 1.0);
                variables.put("c", c);
                variables.put(PartsDiffWithRespTo[0], t);
                variables.put(PartsDiffWithRespTo[1], x);
                System.out.println("SchrodingerEquation:DiffEquationODEHumanRead=" + DiffEquationODEHumanRead[j] + " Trying Probable Solution=" + variablesProbableSolutions.get(i));
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                String Exprn0 = eatAllHGH(Parts[0], ' ');
                FHighLVLEqn0 = parseHghLvlExprODE(Exprn0, variables, PartsDiffWithRespTo[0], "z", variablesProbableSolutions.get(i), DiffEquationODEHumanRead[j]);
                String MySymbolicEqn0 = FHighLVLEqn0.Solution();
                String Exprn1 = eatAllHGH(Parts[1], ' ');
                FHighLVLEqn1 = parseHghLvlExprODE(Exprn1, variables, PartsDiffWithRespTo[1], "z", variablesProbableSolutions.get(i), DiffEquationODEHumanRead[j]);
                String MySymbolicEqn1 = FHighLVLEqn1.Solution();
                String Exprn2 = eatAllHGH(Parts[2], ' ');
                FHighLVLEqn2 = parseHghLvlExprODE(Exprn2, variables, PartsDiffWithRespTo[2], "z", variablesProbableSolutions.get(i), DiffEquationODEHumanRead[j]);
                String MySymbolicEqn2 = FHighLVLEqn2.Solution();

                String MySymbolicEqn = MySymbolicEqn0 + " + " + MySymbolicEqn1 + " - " + MySymbolicEqn2;
                String HGHSol = eatAllHGH(MySymbolicEqn, ' ');

                Fx = MathsContxtLAv.parseSimple(HGHSol, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = Fx.SimpleExpr();
                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("SchrodingerEquation:DiffEquationODE=" + DiffEquationODEHumanRead[j] + " Solution=" + MySymbolicEqn + " StrexpSimpleExpressMap1=" + StrexpSimpleExpressMap1 + " DblexpValueMap1=" + DblexpValueMap1);
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                System.out.println("SchrodingerEquation:Ending:Trial " + i);
                
            }
        }
    }

    public static void SchrodingerEquationMethod2(){
    //public static void main(String... args) {
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.println("SchrodingerEquation:Starting:SchrodingerEquationMethod2: From known Functions");
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        Map<Integer, String> variablesProbableSolutions = new HashMap<>();
        Map<String, Double> variables = new HashMap<>();
        MathsContxtLAv.MyFuncExpress();
        DiffrIntegrSep.MyFuncDiff();
        MathsContxtLAv.MyFuncSimple();
        DiffrIntegrSep.MyFuncIntegr();
        String Constant = "C";
        variables.put(DiffWithRespTo, 1.0);
        MathsContxtLAv.SimpleExpression Fx;
        DiffrIntegrSep.DiffExpr FDash;
        DiffrIntegrSep.IntegrExpr FIntegral;
        HghLvlExprODE FHighLVLEqn0;
        HghLvlExprODE FHighLVLEqn1;
        HghLvlExprODE FHighLVLEqn2;
        MathsContxtLAv.Expression expValue;
        String StrexpSimpleExpressMap1 = "";
        double DblexpValueMap1 = 0;

        // ODE in the Form "LHS - RHS=0" Left hand Side - Right Hand Side=0
        // dy/dx in the form @D:~1~@ 
        // d^2/dx^2=@D:~2~@ 
        // d^3/dx^3=@D:~3~@ and so on
        // Single Integral in the form @I:~1~@ 
        // Double Integral=@I:~2~@ 
        // Triple Integral=@I:~3~@ and so on
        String Psi_x_t = "5*x^2*exp(-i*E1*t)";//Wave Function in :Parameter :complex Number x and t: Time
        String V_x_t = "x";//Potential in :Parameter :complex Number x and t: Time
        Double hBar = Double.NaN;//Reduced Planck's Constant
        Double mass = Double.NaN;//mass
        Double t = 1.0;//time
        Double x = 1.0;//x:position
        Double c = 0.0;//x:position
        Double E1 = 1.0;//E1
        /* ************************ ODE SchrodingerEquation *********** */
        int NumOfODE = 1;
        String[] DiffEquationODE = new String[NumOfODE];
        String[] DiffEquationODEHumanRead = new String[NumOfODE];
        //DiffEquationODE[0] = "i*hBar*@D:~1~@ +hBar^2/2/m*D2/dx2*Phi_x_t - V_x_t*Phi_x_t";
        DiffEquationODEHumanRead[0] = "i*hBar*dPsi_x_t/dt=-hBar^2/2/mass*D2/dx2*Psi_x_t + V_x_t*Psi_x_t";

        /* ************************ ODE SchrodingerEquation *********** */
 /* ************************ Probable Solution *********** */
        int NumOfSolutions = 3;
        variablesProbableSolutions.put(0, eatAllHGH("m*t+c", ' '));
        variablesProbableSolutions.put(1, eatAllHGH("m*x+c", ' '));
        variablesProbableSolutions.put(2, eatAllHGH(Psi_x_t, ' '));
        System.out.println("SchrodingerEquation:Starting:DiffEquationODE:" + DiffEquationODEHumanRead[0]);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
        System.out.println("Trying with Probable Solution:Fx=" + variablesProbableSolutions.get(2) );        
 /* ************************ Probable Solution *********** */
 /* ************************ Parts of ODE *********** */
        int NumOfParts = 3;
        String[] Parts = new String[NumOfParts];
        Parts[0] = "hBar*@D:~1~@";//i*hBar*dPsi_x_t/dt
        Parts[1] = "hBar^2/2/mass*@D:~2~@";//hBar^2/2/m*D2/dx2*Psi_x_t
        Parts[2] = V_x_t + " * " + Psi_x_t;//V_x_t*Psi_x_t
        String[] PartsDiffWithRespTo = new String[NumOfParts];
        PartsDiffWithRespTo[0] = "t";
        PartsDiffWithRespTo[1] = "x";
        PartsDiffWithRespTo[2] = "y";
        String[] PartsSolution = new String[NumOfParts];
        PartsSolution[0] = "";//i*hBar*dPsi_x_t/dt
        PartsSolution[1] = "";//hBar^2/2/m*D2/dx2*Psi_x_t
        PartsSolution[2] = "";//V_x_t*Psi_x_t
        System.out.println("Trying with Probable Solution:Parts[0]=" + Parts[0] + " DiffWithRespTo[0]=" + PartsDiffWithRespTo[0]);
        System.out.println("Trying with Probable Solution:Parts[1]=" + Parts[1] + " DiffWithRespTo[1]=" + PartsDiffWithRespTo[1]);       
        System.out.println("Trying with Probable Solution:Parts[2]=" + Parts[2] + " DiffWithRespTo[2]=" + PartsDiffWithRespTo[2]);        
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
        /* ************************ Parts of ODE *********** */
        System.out.println("SchrodingerEquation:Starting:Wave Function:Psi_x_t=" + Psi_x_t);
        System.out.println("SchrodingerEquation:Starting:Potential:V_x_t=" + V_x_t);
        System.out.println("SchrodingerEquation:Starting:Reduced Planck's Constant:hBar=" + hBar);
        System.out.println("SchrodingerEquation:Starting:Imaginary:i=" + "i");
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        variables.put("hBar", hBar);
        variables.put("mass", mass);
        variables.put("m", 1.0);
        variables.put("c", c);
        variables.put("E1", E1);
        variables.put(PartsDiffWithRespTo[0], t);
        variables.put(PartsDiffWithRespTo[1], x);
        // PARTS[0]DiffWithRespTo t
        //Assign the correct Equation
        String MySymbolicEqn = eatAllHGH(variablesProbableSolutions.get(2), ' ');
        String SimpleMySymbolicEqn = "";
        System.out.println("PARTS0:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + PartsDiffWithRespTo[0]);
        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, PartsDiffWithRespTo[0]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        System.out.println("PARTS0:Simple Fx=" + SimpleMySymbolicEqn);
        FDash = DiffrIntegrSep.parseDiff(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[0]);
        SimpleMySymbolicEqn = FDash.DiffExpr();
        System.out.println("PARTS0:RAW:Diff Expression F(" + 1 + "th) Order=" + SimpleMySymbolicEqn);
        Fx = MathsContxtLAv.parseSimple(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[0]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        PartsSolution[0]="i*hBar*" + SimpleMySymbolicEqn;
        System.out.println("PARTS0:Diff Expression F(" + 1 + "th) Order=" + SimpleMySymbolicEqn);
        System.out.println("PARTS0:Diff:Equation=" + SimpleMySymbolicEqn);
        
        System.out.println("Diff:PARTS0:1st Order:DiffWithRespTo t:SimpleMySymbolicEqn=" + SimpleMySymbolicEqn);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
        // PARTS[1] DiffWithRespTo x
        //Assign the correct Equation
        MySymbolicEqn = eatAllHGH(variablesProbableSolutions.get(2), ' ');
        System.out.println("PARTS1:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + PartsDiffWithRespTo[1]);
        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        System.out.println("PARTS1:Simple Fx=" + SimpleMySymbolicEqn);
        FDash = DiffrIntegrSep.parseDiff(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = FDash.DiffExpr();
        System.out.println("PARTS1:RAW:Diff Expression F(" + 1 + "th) Order=" + SimpleMySymbolicEqn);
        Fx = MathsContxtLAv.parseSimple(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        System.out.println("Diff Expression F(" + 1 + "th) Order=" + SimpleMySymbolicEqn);
        System.out.println("Diff:PARTS1:1st Order:DiffWithRespTo x:Equation=" + SimpleMySymbolicEqn);

        //PARTS[1] 2nd Order DiffWithRespTo x
        MySymbolicEqn = SimpleMySymbolicEqn;
        System.out.println("PARTS1:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + PartsDiffWithRespTo[1]);
        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        System.out.println("PARTS1:Simple Fx=" + SimpleMySymbolicEqn);
        FDash = DiffrIntegrSep.parseDiff(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = FDash.DiffExpr();
        System.out.println("PARTS1:RAW:Diff Expression F(" + 2 + "th) Order=" + SimpleMySymbolicEqn);
        Fx = MathsContxtLAv.parseSimple(SimpleMySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        System.out.println("Diff Expression F(" + 2 + "th) Order=" + SimpleMySymbolicEqn);
        System.out.println("Diff:Equation=" + SimpleMySymbolicEqn);
        PartsSolution[1]="hBar^2/2/m*"+ SimpleMySymbolicEqn;
        System.out.println("Diff:PARTS1:2nd Order:DiffWithRespTo x:SimpleMySymbolicEqn=" + SimpleMySymbolicEqn );
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
//        // PARTS[2]
        //Assign the correct Equation
        MySymbolicEqn = eatAllHGH(Parts[2],' ');
        System.out.println("parseEqn:MySymbolicEqn: Fx=" + MySymbolicEqn + " DiffWithRespTo=" + PartsDiffWithRespTo[1]);
        Fx = MathsContxtLAv.parseSimple(MySymbolicEqn, variables, PartsDiffWithRespTo[1]);
        SimpleMySymbolicEqn = Fx.SimpleExpr();
        PartsSolution[2]=MySymbolicEqn;
        System.out.println("Simple Fx=" + SimpleMySymbolicEqn);
        System.out.println("PARTS2:SimpleMySymbolicEqn=" + SimpleMySymbolicEqn );
        
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.println("Part Solution:PartsSolution[0]=" + PartsSolution[0] + " Differentiated " + Psi_x_t + " w.r.t time" );
        System.out.println("Part Solution:PartsSolution[1]=" + PartsSolution[1] + " Twice Differentiated " + Psi_x_t + " w.r.t x(position)" );      
        System.out.println("Part Solution:PartsSolution[2]=" + PartsSolution[2] + " Product Function" );        
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        // Solution
        String Overall =PartsSolution[0] + "=" + PartsSolution[1] + "+" + PartsSolution[2];
        System.out.println("SchrodingerEquation:Overall ODE:Overall:" + Overall );
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
       
    }
    
}
