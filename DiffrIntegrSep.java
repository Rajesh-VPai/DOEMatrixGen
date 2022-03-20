/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.MathsContxtLAv.ComplexAssignFlag;
import static Calculus.MathsContxtLAv.LookAheadOperator;
import static Calculus.MathsContxtLAv.GotExpntNumFlag;
import static Calculus.MathsContxtLAv.EnteredLAConstFlag;
import static Calculus.MathsContxtLAv.FunctionDiffExpr;
import static Calculus.MathsContxtLAv.FunctionExpr;
import static Calculus.MathsContxtLAv.FunctionName;
import static Calculus.MathsContxtLAv.FactorsDiffExpr;
import static Calculus.MathsContxtLAv.FactorsExpr;
import static Calculus.MathsContxtLAv.ParanDiffExpr;
import static Calculus.MathsContxtLAv.ParanExpr;
import static Calculus.MathsContxtLAv.ParanExprFlag;
import static Calculus.MathsContxtLAv.FuncArgFlag;
import static Calculus.MathsContxtLAv.ContainsXFlag;
import static Calculus.MathsContxtLAv.ParenthesisHighLvlNum;
import static Calculus.MathsContxtLAv.ParenthesisCurrNum;
import static Calculus.MathsContxtLAv.NormalExprnFlag;
import static Calculus.MathsContxtLAv.LastLADiffIndConstAidPos;
import static Calculus.MathsContxtLAv.GotIndConstPos;
import static Calculus.MathsContxtLAv.TermOutputSetFlag;
import static Calculus.MathsContxtLAv.LADiffIndConstOperator;
import static Calculus.MathsContxtLAv.LADiffFactorOperator;
import static Calculus.MathsContxtLAv.GotIndConstFlag;
import static Calculus.MathsContxtLAv.LastLADiffExtremityAidPos;
import static Calculus.MathsContxtLAv.GotExtremityFlag;
import static Calculus.MathsContxtLAv.ContainsIndConstFlag;
import static Calculus.MathsContxtLAv.ContainsXFlag;
import static Calculus.MathsContxtLAv.DiffTermOperator;
import static Calculus.MathsContxtLAv.FuncArgFlag;
import static Calculus.MathsContxtLAv.GotExpntNumFlag;
import static Calculus.MathsContxtLAv.GotIndConstFlag;
import static Calculus.MathsContxtLAv.GotIndConstPos;
import static Calculus.MathsContxtLAv.GotVariableFlag;
import static Calculus.MathsContxtLAv.GotVariablePos;
import static Calculus.MathsContxtLAv.LADiffFactorOperator;
import static Calculus.MathsContxtLAv.LADiffIndConstOperator;
import static Calculus.MathsContxtLAv.LastLADiffConstCoeffAidPos;
import static Calculus.MathsContxtLAv.LastLADiffIndConstAidPos;
import static Calculus.MathsContxtLAv.NormalNotPareFlag;
import static Calculus.MathsContxtLAv.ParanExprFlag;
import static Calculus.MathsContxtLAv.ParenthesisFlag;
import static Calculus.MathsContxtLAv.ParseLog;
import static Calculus.MathsContxtLAv.ParseMap;
import static Calculus.MathsContxtLAv.ResetVars;
import static Calculus.MathsContxtLAv.SavedOperator;
import static Calculus.MathsContxtLAv.Str;
import static Calculus.MathsContxtLAv.StrIsAConstant;
import static Calculus.MathsContxtLAv.TermOutputIndConst;
import static Calculus.MathsContxtLAv.ch;
import static Calculus.MathsContxtLAv.chTerm;
import static Calculus.MathsContxtLAv.log;
import static Calculus.MathsContxtLAv.pos;
import static Calculus.MathsContxtLAv.posParen;
import static Calculus.MathsContxtLAv.posTerm;
import static Calculus.UsageCalculus.Order;
import static Calculus.UsageCalculus.StageMapping;
import static Calculus.UsageCalculus.TestCase;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class DiffrIntegrSep {

    public static Logger log = Logger.getLogger(DiffrIntegrSep.class.getName());
    //Important Mapping Variables
    private static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    public static Map<String, String> functionsDiff = new HashMap<>();
    public static Map<String, String> functionsIntegr = new HashMap<>();
    private static Map<Integer, String> variablesEqnPartialSol = new HashMap<>();

    //LAConstDiff Expression character Index and Character Variable
    public static int posLADiff = -1, chLADiff;
    public static String StrLADiff;
    //Diff Expression character Index and Character Variable
    public static int posDiff = -1, chDiff;
    public static String StrDiff;
    //Diff Expression character Index and Character Variable
    public static int posIntegr = -1, chIntegr;
    public static String StrIntegr;

    // Global Variables && Functions
    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumFractionDigits = 25;
    // Maximum number of Integer Digits displayed in Equation
    private static int MaximumIntegerDigits = 50;

    private static boolean ZeroTermSignFlag = false;
    public static boolean ParanExprInProgressFlag = false;
    public static String FuncName = "";

    public static void MyFuncDiff() {
        functionsDiff.put("sin", "cos");
        functionsDiff.put("cos", "-sin");
        functionsDiff.put("tan", "sec^2.0");
        functionsDiff.put("log", "log");
        functionsDiff.put("ln", "ln");
        functionsDiff.put("exp", "exp");
        //TODO:More Differential Unary Functions to be added
    }

    public static void MyFuncIntegr() {
        functionsIntegr.put("sin", "-cos");
        functionsIntegr.put("cos", "sin");
        //functionsIntegr.put("tan", "sec^2.0");
        //functionsIntegr.put("log", "log");
        //functionsIntegr.put("ln", "ln");
        functionsIntegr.put("exp", "exp");
        //TODO:More Differential Unary Functions to be added
    }

    static void nextCharDiff() {
        ++posDiff;
        chDiff = (posDiff < StrDiff.length()) ? StrDiff.charAt(posDiff) : -1;
        if ((chDiff == -1) && (posDiff >= StrDiff.length())) {
            posDiff = StrDiff.length();
        } else if ((chDiff == -1) || ((chDiff == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "nextCharDiff:nextCharDiff:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " Cannot Format:" + StrDiff.substring(posDiff, StrDiff.length()));
        }
    }

    static void prevCharDiff() {
        --posDiff;
        chDiff = (posDiff >= 0) ? StrDiff.charAt(posDiff) : -1;
        if ((chDiff == -1) && (posDiff < 0)) 
            ; else if ((chDiff == -1) || ((chDiff == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "prevCharDiff:prevCharDiff:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " Cannot Format:" + StrDiff.substring(posDiff, StrDiff.length()));
        }
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

    static void SyncDiff() {
        if ((posDiff < StrDiff.length())
                && (StrDiff.charAt(posDiff) != chDiff)) {
            if (StrDiff.charAt(posDiff + 1) == chDiff) {
                posDiff++;
            } else if (StrDiff.charAt(posDiff - 1) == chDiff) {
                posDiff--;
            }
        }
    }

    static void nextCharIntegr() {
        ++posIntegr;
        chIntegr = (posIntegr < StrIntegr.length()) ? StrIntegr.charAt(posIntegr) : -1;
        if ((chIntegr == -1) && (posIntegr >= StrIntegr.length())) {
            posIntegr = StrIntegr.length();
        } else if ((chIntegr == -1) || ((chIntegr == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "nextCharIntegr:nextCharIntegr:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " Cannot Format:" + StrIntegr.substring(posIntegr, StrIntegr.length()));
        }

    }

    static void prevCharIntegr() {
        --posIntegr;
        chIntegr = (posIntegr >= 0) ? StrIntegr.charAt(posIntegr) : -1;
        if ((chIntegr == -1) && (posIntegr < 0)) 
            ; else if ((chIntegr == -1) || ((chIntegr == 0))) {
            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "prevCharIntegr:prevCharIntegr:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " Cannot Format:" + StrIntegr.substring(posIntegr, StrIntegr.length()));
        }
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

    static void SyncIntegr() {
        if ((posIntegr < StrIntegr.length())
                && (StrIntegr.charAt(posIntegr) != chIntegr)) {
            if (StrIntegr.charAt(posIntegr + 1) == chIntegr) {
                posIntegr++;
            } else if (StrIntegr.charAt(posIntegr - 1) == chIntegr) {
                posIntegr--;
            }
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
                    && ((StrX.equalsIgnoreCase(DiffWithRespTo))
                    || ((StrX.contains(DiffWithRespTo)) && ((!StrX.contains(DiffWithRespTo + "^")))))
                    && (StrX.matches("[\\+\\-\\*\\^/]*[a-zA-Z0-9]+"))) {
                StrIsAConstant = "Var";
                log.info("IsAConstant:StrIsAConstant=" + StrIsAConstant + " DiffWithRespTo=" + DiffWithRespTo);
                return false;
            } else if ((StrX != null)
                    && (!StrX.equalsIgnoreCase(""))
                    && (StrX.contains(DiffWithRespTo))
                    && (!StrX.contains("exp"))) {
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
                    && ((StrX.matches("[\\+\\-]*[0-9\\.\\*\\^/]+[0-9\\.]*"))
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
        return ((((Mych >= 'a') && (Mych <= 'z')) || ((Mych >= 'A') && (Mych <= 'Z'))));
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
                if ((chLADiff == -1) && (posLADiff >= StrLADiff.length())) {
                    posLADiff = StrLADiff.length();
                } else if ((chLADiff == -1) || ((chLADiff == 0))) {
                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadDiffConst:parseBase:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                }
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

            void SyncLADiff() {
                if ((posLADiff < StrLADiff.length())
                        && (StrLADiff.charAt(posLADiff) != chLADiff)) {
                    if (StrLADiff.charAt(posLADiff + 1) == chLADiff) {
                        posLADiff++;
                    } else if (StrLADiff.charAt(posLADiff - 1) == chLADiff) {
                        posLADiff--;
                    }
                }
            }

            LookAheadDiffConst parseLADiff() {
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ComplexAssignFlag = false;
                ContainsIndConstFlag = false;
                GotExtremityFlag = false;
                GotVariableFlag = false;
                GotVariablePos = -1;
                StrLADiff = str;
                //LastLADiffIndConstAidPos = -1;
                //LastLADiffConstCoeffAidPos = -1;
                LastLADiffExtremityAidPos = -1;
                LookAheadDiffConst x = (() -> "");
                log.info("LookAheadDiffConst:parseLADiff:StrLADiff=" + StrLADiff + " DiffTermOperator=" + (char) DiffTermOperator);
//                if (ComplexAssignFlag == true) {
//                    ComplexAssignFlag = false;
//                    GotVariableFlag = false;
//                    posLADiff = GotVariablePos;
//                    chLADiff = StrLADiff.charAt(GotVariablePos);
//                } else 
                posLADiff = 0;
                chLADiff = StrLADiff.charAt(0);
                int startpos = posLADiff;
                log.info("LookAheadDiffConst:parseLADiff:StartPos=" + StartPos + " DiffTermOperator=" + (char) DiffTermOperator + " startpos=" + startpos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (MathsOpType.equalsIgnoreCase("Differentiation")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Initial", "StrLADiff", StrLADiff);
                } else if (MathsOpType.equalsIgnoreCase("Integration")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Initial", "StrLADiff", StrLADiff);
                }
                if ((StrLADiff != null) && (StrLADiff.length() > 0)
                        && (StrLADiff.length() < 2)
                        && ((chLADiff == '*')
                        || (chLADiff == '/')
                        || (chLADiff == '^'))) {
                    return x;
                } else {
                    x = parseTerm();
                }
                log.info("LookAheadDiffConst:parseLADiff:Number:x.eval()=" + x.eval() + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                if (MathsOpType.equalsIgnoreCase("Differentiation")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "BeforeMap", "StrLADiff", StrLADiff);
                    ParseMap("LookAheadDiffConst:parseLADiff:Diff", StrDiff.length(), 0, DiffLength, DiffWithRespTo);
                    ParseLog("LookAheadDiffConst", "parseLADiff", "Final", "StrDiff", StrDiff);
                } else if (MathsOpType.equalsIgnoreCase("Integration")) {
                    ParseLog("LookAheadDiffConst", "parseLADiff", "BeforeMap", "StrLADiff", StrLADiff);
                    ParseMap("LookAheadDiffConst:parseLADiff:Integration", StrIntegr.length(), 0, DiffLength, DiffWithRespTo);
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
                        log.info("LookAheadDiffConst:parseTerm:b.eval()=" + b.eval() + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if (GotVariableFlag == true) {
                            LADiffFactorOperator = '*';
                        } else if (GotIndConstFlag == true) {
                            LADiffIndConstOperator = '*';
                        }
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            x = (() -> "" + (Double.parseDouble(a.eval()) * Double.parseDouble(b.eval())));
                            if (MathsOpType.equalsIgnoreCase("Integration")) {
                                LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                            }
//                            else if (MathsOpType.equalsIgnoreCase("Differentiation")) {
//                                LastLADiffConstCoeffAidPos = posDiff + posLADiff;
//                            }
                            log.info("LookAheadDiffConst:parseTerm:Mult:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if (!b.eval().equalsIgnoreCase("1.0")) {
                            x = (() -> a.eval() + "*" + b.eval());
                            if (MathsOpType.equalsIgnoreCase("Integration")) {
                                LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                            }
                            log.info("LookAheadDiffConst:parseTerm:Mult:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        }
                        if ((chLADiff == '+') || (chLADiff == '-')) {
                            log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                            return x;
                        } else if ((chLADiff != -1) && (chLADiff != 0)) {
                            SyncLADiff();
                        }
                        log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else if (eatLADiff('/')) { // division
                        LookAheadDiffConst a = x, b = parseFactor();
                        log.info("LookAheadDiffConst:parseTerm:b.eval()=" + b.eval() + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if (GotVariableFlag == true) {
                            LADiffFactorOperator = '/';
                        } else if (GotIndConstFlag == true) {
                            LADiffIndConstOperator = '/';
                        }
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                            x = (() -> "" + (Double.parseDouble(a.eval()) / Double.parseDouble(b.eval())));
                            if (MathsOpType.equalsIgnoreCase("Integration")) {
                                LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                            }
//                            else if (MathsOpType.equalsIgnoreCase("Differentiation")) {
//                                LastLADiffConstCoeffAidPos = posDiff + posLADiff;
//                            }
                            log.info("LookAheadDiffConst:parseTerm:Div:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if (!b.eval().equalsIgnoreCase("1.0")) {
                            x = (() -> a.eval() + "/" + b.eval());
                            if (MathsOpType.equalsIgnoreCase("Integration")) {
                                LastLADiffConstCoeffAidPos = posIntegr + posLADiff;
                            }
                            log.info("LookAheadDiffConst:parseTerm:Div:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        }
                        if ((chLADiff == '+') || (chLADiff == '-')) {
                            log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                            return x;
                        } else if ((chLADiff != -1) && (chLADiff != 0)) {
                            SyncLADiff();
                        }
                        log.info("LookAheadDiffConst:parseTerm:x.eval()=" + x.eval() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {

                        return x;
                    }
                }
            }

            LookAheadDiffConst parseFactor() {
                LookAheadDiffConst x = (() -> "1.0");
                x = parseBase();
                log.info("LookAheadDiffConst:parseFactor:After Initial Call:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                for (;;) {
                    if (eatLADiff('^')) { // exponentiation
                        LookAheadDiffConst a = x, b = parseBase();
                        log.info("LookAheadDiffConst:parseFactor:b.eval()=" + b.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!a.eval().equalsIgnoreCase("1.0"))
                                && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("1.0"))) {
                            // 2^3
                            x = (() -> "" + (Math.pow(Double.parseDouble(a.eval()), Double.parseDouble(b.eval()))));
                            log.info("LookAheadDiffConst:parseFactor:Rule1:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!a.eval().equalsIgnoreCase("1.0"))
                                && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // 2^a
                            x = (() -> a.eval() + "^" + b.eval());
                            log.info("LookAheadDiffConst:parseFactor:Rule2:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!a.eval().equalsIgnoreCase("1.0"))
                                && ((!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                || ((IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (b.eval().equalsIgnoreCase("1.0")))) {
                            // 2^x
                            x = (() -> "1.0");
                            log.info("LookAheadDiffConst:parseFactor:Rule3:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo))
                                && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // a^b
                            x = (() -> a.eval() + "^" + b.eval());
                            log.info("LookAheadDiffConst:parseFactor:Rule4:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))
                                && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // x^b
                            //x = (() -> "1");
                            log.info("LookAheadDiffConst:parseFactor:Rule5:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // a^x
                            //x = (() -> "1");
                            log.info("LookAheadDiffConst:parseFactor:Rule6:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("1.0"))) {
                            // a^2
                            TermOutputIndConst += Double.parseDouble(b.eval());
                            x = (() -> a.eval() + "^" + b.eval());
                            TermOutputSetFlag = true;
                            log.info("LookAheadDiffConst:parseFactor:Rule7:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        }
                        log.info("LookAheadDiffConst:parseFactor:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {
                        log.info("LookAheadDiffConst:parseFactor:ReturnValue:x.eval()=" + x.eval() + " DiffTermOperator=" + (char) DiffTermOperator + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
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
                        if ((IsAConstant(StrLADiff.substring(startPos1, posLADiff), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            xx = Double.parseDouble(StrLADiff.substring(startPos1, posLADiff));
                        }
                    } else {
                        if ((IsAConstant(StrLADiff.substring(startPos1), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            xx = Double.parseDouble(StrLADiff.substring(startPos1));
                        }
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
                    if ((!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (!TempVar.equalsIgnoreCase("NaN")) && (!TempVar.equalsIgnoreCase("Infinity")) && (!functionsDiff.containsKey(TempVar))) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos1;
                    } else if ((ComplexAssignFlag == false)) {
                        GotVariablePos = startPos1;
                        GotVariableFlag = true;
                        ComplexAssignFlag = true;
                    }
                    // Got a Function
                    log.info("LookAheadDiffConst:parseBase:Before Function:TempVar=" + TempVar + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    if ((TempVar != null) && (!TempVar.equalsIgnoreCase("")) && (!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (!TempVar.equalsIgnoreCase("NaN")) && (!TempVar.equalsIgnoreCase("Infinity")) && (functionsDiff.containsKey(TempVar))) {
                        log.info("LookAheadDiffConst:parseBase:Function:TempVar=" + TempVar + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if (chLADiff == '(') {
                            //x = parseBase();
                            if (chLADiff == '(') {
                                eatLADiff('(');
                            } else {
                                while ((chLADiff > -1) && (chLADiff != '(')) {
                                    nextCharLADiff();
                                }
                                eatLADiff('(');
                            }
                            int openBracesCount = 1;
                            int closeBracesCount = 0;
                            int startcount = posLADiff;
                            while ((posLADiff < StrLADiff.length()) && ((chLADiff != ')') || (openBracesCount != closeBracesCount))) {
                                nextCharLADiff();
                                if (chLADiff == '(') {
                                    openBracesCount++;
                                }
                                if (chLADiff == ')') {
                                    closeBracesCount++;
                                }
                            }
                            eatLADiff(')');
                            log.info("LookAheadDiffConst:parseBase:(: Expression=" + StrLADiff.substring(startPos, posLADiff) + " :x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                            String TempExprn = StrLADiff.substring(startcount, posLADiff - 1);
                            if (IsAConstant(TempExprn, DiffWithRespTo)) {
                                final String TempExprnFullFunc = TempExprn;
                                x = () -> TempVar + "(" + TempExprnFullFunc + ")";
                            }
                            log.info("LookAheadDiffConst:parseBase:(: Function:x.eval()=" + x.eval() + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                            return x;
                        }
                    } else if ((TempVar != null) && (!TempVar.equalsIgnoreCase("")) && (!TempVar.equalsIgnoreCase("NaN")) && (!TempVar.equalsIgnoreCase("Infinity")) && (!TempVar.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = TempVar;
                        GotIndConstFlag = true;
                        LastLADiffIndConstAidPos = startPos1 - 1;
                        x = () -> MyStr;
                        log.info("LookAheadDiffConst:parseBase:Variable:TempVar=" + TempVar + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else if ((TempVar != null) && (!TempVar.equalsIgnoreCase("")) && ((TempVar.equalsIgnoreCase("NaN")) || (TempVar.equalsIgnoreCase("Infinity"))) && (!TempVar.equalsIgnoreCase(DiffWithRespTo))) {
                        final String MyStr = TempVar;
                        GotExtremityFlag = true;
                        LastLADiffExtremityAidPos = posLADiff;
                        x = () -> MyStr;
                        log.info("LookAheadDiffConst:parseBase:Variable:TempVar=" + TempVar + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                    } else {
                        if ((!TempVar.equalsIgnoreCase(DiffWithRespTo)) && (LastLADiffIndConstAidPos == -1)) {
                            LastLADiffIndConstAidPos = startPos1 - 1;
                        }
                        x = () -> "1.0";
                    }
                    log.info("LookAheadDiffConst:parseBase:Variable:x.eval()=" + x.eval() + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " GotVariablePos=" + GotVariablePos + " GotIndConstFlag=" + GotIndConstFlag);
                    return x;
                } else if (chLADiff == '(') {
                    nextCharLADiff();
                    return x;
                } else if (chLADiff == ')') {
                    nextCharLADiff();
                    return x;
                } else {
                    if ((StrLADiff != null) && (startPos < StrLADiff.length()) && (startPos < posLADiff) && (chLADiff != -1) && (chLADiff != 0)) {
                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0) && (posLADiff >= 1) && (posLADiff < StrLADiff.length()) && (IsNumber(chLADiff))) {
                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0) && (posLADiff >= 1) && (posLADiff < StrLADiff.length()) && (IsOperator(chLADiff))) {
                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    } else if ((chLADiff != -1) && (chLADiff != 0) && (posLADiff >= 1) && (posLADiff < StrLADiff.length())) {
                        throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "LookAheadConst:parseBase:Unexpected: " + (char) chLADiff + " int=" + (int) chLADiff + " Cannot Format:" + StrLADiff.substring(posLADiff, StrLADiff.length()));
                    }
                    return x;
                }
            }
        }.parseLADiff();
    }

    @FunctionalInterface
    public interface DiffExpr {

        String DiffExpr();
    }

    public static DiffExpr parseDiff(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            boolean ParanExprInProgressFlag = false;

            DiffExpr parseDiffExpr() {
                DiffExpr x = (() -> "");
                ResetVars("DiffExpr");
                StrDiff = str;
                if ((StrDiff == null) || (StrDiff.equalsIgnoreCase(""))) {
                    return x;
                }
                posDiff = 0;
                chDiff = StrDiff.charAt(0);
                int oldch = chDiff;
                if ((IsVariable(chDiff)) || (IsNumber(chDiff))) {
                    oldch = 0;
                } else if ((chDiff == '-') || (chDiff == '+')) {
                    posDiff = posDiff + 1;
                    chDiff = StrDiff.charAt(1);
                }
                if ((StrDiff != null) && (StrDiff.length() > 0)
                        && (StrDiff.length() < 2)
                        && ((chDiff == '*')
                        || (chDiff == '/')
                        || (chDiff == '^'))) {
                    return x;
                } else {
                    x = parseExpressionDiff();//parseParaDiff();
                }
                log.info("Diff:parseDiff:Final:x.DiffExpr()=" + x.DiffExpr());
                if ((oldch == '-') && (x.DiffExpr().startsWith("-"))) {
                    final String FinalOutput = x.DiffExpr().replaceFirst("-", "");
                    x = (() -> FinalOutput);
                } else if ((oldch == '-') && (!x.DiffExpr().startsWith("-"))) {
                    final String FinalOutput = x.DiffExpr();
                    final int MySignInt = oldch;
                    x = (() -> (char) MySignInt + FinalOutput);
                }
//                if (x.DiffExpr().contains("--")) {
//                    String OutputExprn = x.DiffExpr().replaceAll("\\--", "-");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
//                if (x.DiffExpr().contains("+-")) {
//                    String OutputExprn = x.DiffExpr().replaceAll("\\+-", "-");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
                return x;
            }

            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            DiffExpr parseExpressionDiff() {
                int ExprStartPos = posDiff;
                int ExprEndPos = -1;

                int startpos = posDiff;
                DiffTermOperator = -1;
                DiffExpr x = parseTermDiff();
                int endpos = posDiff;
                String xExprn = "";
                String OutputExprn = "";
                if ((startpos != -1) && (endpos != -1) && (startpos < endpos) && (startpos <= StrDiff.length()) && (startpos < StrDiff.length()) && (endpos <= StrDiff.length())) {
                    xExprn = StrDiff.substring(startpos, endpos);
                }
                OutputExprn = x.DiffExpr();
                log.info("Diff:parseExpressionDiff:Initial Call to parseTermDiff:OutputExprn=" + OutputExprn);
                log.info("Diff:parseExpressionDiff:Initial Call to parseTermDiff:xExprn=" + xExprn);
                if ((IsAConstant(OutputExprn, DiffWithRespTo)) && (IsAConstant(xExprn, DiffWithRespTo)) && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    log.info("Diff:parseExpressionDiff:Mapping 0.0:Got a Constant Value from parseTermDiff:OutputExprn=" + OutputExprn);
                    OutputExprn = "0.0";
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                } else if ((!IsAConstant(OutputExprn, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstant")))
                        && (!IsAConstant(xExprn, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstant")))
                        && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    log.info("Diff:parseExpressionDiff:Mapping 0.0:Got a Constant Value from parseTermDiff:OutputExprn=" + OutputExprn);
                    if (!OutputExprn.endsWith("*0.0")) {
                        OutputExprn = OutputExprn + "*" + "0.0";
                    }
                    final String Output = OutputExprn;
                    x = (() -> Output);
                    xExprn = Output;
                } else if ((OutputExprn.equalsIgnoreCase(DiffWithRespTo)) || (OutputExprn.equalsIgnoreCase("1.0")) && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    OutputExprn = "1.0";
                    if (xExprn.equalsIgnoreCase("-" + DiffWithRespTo)) {
                        final String Output = "-" + OutputExprn;
                        x = (() -> Output);
                        xExprn = Output;
                    } else {
                        final String Output = OutputExprn;
                        x = (() -> Output);
                        xExprn = Output;
                    }
                    log.info("Diff:parseExpressionDiff:Mapping 1.0:Got a " + DiffWithRespTo + " from parseTermDiff:OutputExprn=" + OutputExprn + " x.DiffExpr()=" + x.DiffExpr() + " xExprn=" + xExprn);
                } else if ((OutputExprn.equalsIgnoreCase("")) || (xExprn.equalsIgnoreCase("")) && ((chDiff == '+') || (chDiff == '-') || (chDiff == -1))) {
                    log.info("Diff:parseExpressionDiff:Mapping 0.0:Got a Constant Value from parseTermDiff:OutputExprn=" + OutputExprn);
                    OutputExprn = "0.0";
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
                    DiffTermOperator = -1;
                    ContainsXFlag = false;
                    if ((eatDiff('+')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) {// addition

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
                        if (((IsAConstant(b.DiffExpr(), DiffWithRespTo))
                                && (!b.DiffExpr().contains(DiffWithRespTo))
                                && (b.DiffExpr().equalsIgnoreCase(bExprn)))
                                || (b.DiffExpr().equalsIgnoreCase(""))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if (!(IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            final String Temp = b.DiffExpr();
                            if (!Temp.endsWith("*0.0")) {
                                b = (() -> Temp + "*" + "0.0");
                                bExprn = Temp + "*" + "0.0";
                            } else {
                                b = (() -> Temp);
                                bExprn = Temp;
                            }
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
                        DiffTermOperator = -1;
                        x = ApplyLambdaRules("parseExpressionDiff", xExprn, a, "", b, "", "", "+", "SumRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                        xExprn = ApplyLambdaString("parseExpressionDiff", xExprn, a, "", b, "", "", "+", "SumRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                        log.info("Diff:parseExpressionDiff::a.DiffExpr()=" + a.DiffExpr());
                        log.info("Diff:parseExpressionDiff::b.DiffExpr()=" + b.DiffExpr());
                        log.info("Diff:parseExpressionDiff::x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseExpressionDiff::xExprn=" + xExprn);
                    } else if ((eatDiff('-')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) { // subtraction

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
                        if ((IsAConstant(b.DiffExpr(), DiffWithRespTo))
                                && (!b.DiffExpr().contains(DiffWithRespTo))
                                && (b.DiffExpr().equalsIgnoreCase(bExprn))
                                || (b.DiffExpr().equalsIgnoreCase(""))) {
                            b = (() -> "0.0");
                            bExprn = "0.0";
                        } else if (!(IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            if (((b.DiffExpr().equalsIgnoreCase("Infinity")) || (b.DiffExpr().equalsIgnoreCase("NaN"))) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                                final String Temp = b.DiffExpr();
                                b = (() -> Temp);
                                bExprn = Temp;
                            } else {
                                final String Temp = bExprn;
                                if (!Temp.endsWith("*0.0")) {
                                    b = (() -> Temp + "*" + "0.0");
                                    bExprn = Temp + "*" + "0.0";
                                } else {
                                    b = (() -> Temp);
                                    bExprn = Temp;
                                }

                            }
                        } else if (!(IsAConstant(b.DiffExpr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) && (!b.DiffExpr().contains(DiffWithRespTo)) && (b.DiffExpr().equalsIgnoreCase(bExprn))) {
                            final String Temp = bExprn;
                            if (!Temp.endsWith("*0.0")) {
                                b = (() -> Temp + "*" + "0.0");
                                bExprn = Temp + "*" + "0.0";
                            } else {
                                b = (() -> Temp);
                                bExprn = Temp;
                            }

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
                        DiffTermOperator = -1;
                        x = ApplyLambdaRules("parseExpressionDiff", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                        xExprn = ApplyLambdaString("parseExpressionDiff", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
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
                EnteredLAConstFlag = false;
                DiffExpr x = parseFactorDiff();
                DiffExpr c = x;
                DiffExpr dNull = (() -> "");
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
                if ((posDiff == StrDiff.length()) && (chDiff == -1)) {
                    final String OutputExprn1 = OutputExprn;
                    x = (() -> OutputExprn1);
                    log.info("Diff:parseTermDiff:Last Term:Return Value parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                    if (TermStartPos < TermEndPos) {
                        log.info(":Last Term:Original Diff=" + StrDiff.substring(TermStartPos, TermEndPos));
                    }
                    log.info("Diff:parseTermDiff:Last Term:Return Value parseTermDiff:xExprn=" + xExprn);
                    return x;
                } else if ((xConst.equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermDiff00", OutputExprn, x, x.DiffExpr(), dNull, "", "", ("" + (char) chDiff), "NoExponentRuleDiff", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermDiff00", OutputExprn, c, c.DiffExpr(), dNull, "", "", ("" + (char) chDiff), "NoExponentRuleDiff", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                } else if ((xConst.equalsIgnoreCase(OutputExprn)) && (!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermDiff01", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermDiff01", OutputExprn, c, c.DiffExpr(), c, "", "", "*", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                } else if ((!x.DiffExpr().equalsIgnoreCase("")) && (!x.DiffExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermDiff10", OutputExprn, x, x.DiffExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermDiff10", OutputExprn, c, c.DiffExpr(), c, "", "", "*", "CoeffExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                } else if (x.DiffExpr().equalsIgnoreCase("0.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "0.0";
                } else if ((x.DiffExpr().equalsIgnoreCase("")) && (!IsAConstant(xExprn, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermDiff11", xExprn, x, x.DiffExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermDiff11", OutputExprn, c, c.DiffExpr(), c, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                }
                log.info("Diff:parseTermDiff:Initial Call to FactorDiff:aMappedProduct=" + aMappedProduct);
                log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr());
                log.info("Diff:parseTermDiff:xExprn=" + xExprn + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                for (;;) {
                    if (eatDiff('*')) { // multiplication
                        log.info("Diff:parseTermDiff:Multiply Entered" + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                        if ((DiffTermOperator == -1) || (DiffTermOperator == '/')) {
                            DiffTermOperator = '*';
                        }
                        int bstartpos = posDiff;
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr());

                        DiffExpr a = x, b = parseFactorDiff();

                        int bendpos = posDiff;
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length())) {
                            MappedProduct = StrDiff.substring(bstartpos, bendpos);
                        }

                        log.info("Diff:parseTermDiff:Mult:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:Mult:a.DiffExpr()=" + a.DiffExpr());
                        log.info("Diff:parseTermDiff:Mult:x.DiffExpr()=" + x.DiffExpr());
                        OutputExprn = b.DiffExpr();
                        log.info("Diff:parseTermDiff:Mult:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseTermDiff:Mult:aMappedProduct=" + aMappedProduct);
                        log.info("Diff:parseTermDiff:Mult:MappedProduct=" + MappedProduct);
                        log.info("Diff:parseTermDiff:Mult:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:Mult:b.DiffExpr()=" + b.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Diff:parseTermDiff:Mult:a.DiffExpr()=" + a.DiffExpr());
                        if (((b.DiffExpr() == null) || (b.DiffExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyLambdaRules("parseTermDiff00", OutputExprn, a, x.DiffExpr(), b, "", "", "*", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff00", OutputExprn, a, b.DiffExpr(), b, "", "", "+", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if (OutputExprn.equalsIgnoreCase(DiffWithRespTo)) {
                            x = ApplyLambdaRules("parseTermDiff01", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff01", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((a.DiffExpr().contains("Parenthesis")) || (b.DiffExpr().contains("Parenthesis"))) {
                            x = ApplyLambdaRules("parseTermDiff10", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff10", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            if (!MappedProduct.contains("^" + DiffWithRespTo)) {
                                x = ApplyLambdaRules("parseTermDiff11", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                xExprn = ApplyLambdaString("parseTermDiff11", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                    aMappedProduct = aMappedProduct + "*" + MappedProduct;
                                }
                            } else {
                                x = ApplyLambdaRules("parseTermDiff16", aMappedProduct, a, b.DiffExpr(), b, MappedProduct, "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                xExprn = ApplyLambdaString("parseTermDiff16", aMappedProduct, a, b.DiffExpr(), b, MappedProduct, "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                    aMappedProduct = aMappedProduct + "*" + MappedProduct;
                                }
                            }
                        } else if ((!IsAConstant(aMappedProduct, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))
                                && (!IsAConstant(b.DiffExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                            x = ApplyLambdaRules("parseTermDiff12", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff12", aMappedProduct, a, MappedProduct, b, "", "", "*", "MappedProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((!IsAConstant(aMappedProduct, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            x = ApplyLambdaRules("parseTermDiff15", aMappedProduct, a, x.DiffExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff15", aMappedProduct, a, b.DiffExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if (b.DiffExpr().contains("@Complete@")) {
                            final String aTemp = a.DiffExpr().replaceFirst(FunctionName, "");
                            final String Temp = b.DiffExpr().replaceFirst("@Complete@", "");
                            if ((aTemp.endsWith("*")) || (aTemp.endsWith("/"))) {
                                x = (() -> aTemp + Temp);
                                xExprn = aTemp + Temp;
                            } else {
                                x = (() -> aTemp + "*" + Temp);
                                xExprn = aTemp + "*" + Temp;
                            }
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:xExprn=" + xExprn);
                            //return x;
                        } else {
                            x = ApplyLambdaRules("parseTermDiff13", OutputExprn, a, OutputExprn, b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff13", OutputExprn, a, OutputExprn, b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        }
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Diff:parseTermDiff:xExprn=" + xExprn);
                        log.info("Diff:parseTermDiff:aMappedProduct=" + aMappedProduct);
                        if ((chDiff == '+') || (chDiff == '-')) {

                        } else if ((chDiff != -1) && (chDiff != 0)) {
                            SyncDiff();
                        }
                    } else if (eatDiff('/')) { // division
                        log.info("Diff:parseTermDiff:Divide Entered" + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if ((DiffTermOperator == -1) || (DiffTermOperator == '*')) {
                            DiffTermOperator = '/';
                        }

                        int bstartpos = posDiff;
                        DiffExpr a = x, b = parseFactorDiff();
                        int bendpos = posDiff;

                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrDiff.length()) && (bstartpos < StrDiff.length())) {
                            MappedProduct = StrDiff.substring(bstartpos, bendpos);
                        }
                        log.info("Diff:parseTermDiff:Div:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:Div:a.DiffExpr()=" + a.DiffExpr());
                        log.info("Diff:parseTermDiff:Div:x.DiffExpr()=" + x.DiffExpr());
                        OutputExprn = b.DiffExpr();
                        log.info("Diff:parseTermDiff:Div:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseTermDiff:Div:aMappedProduct=" + aMappedProduct);
                        log.info("Diff:parseTermDiff:Div:MappedProduct=" + MappedProduct);
                        log.info("Diff:parseTermDiff:Div:OutputExprn=" + OutputExprn);
                        log.info("Diff:parseTermDiff:Div:b.DiffExpr()=" + b.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Diff:parseTermDiff:Div:a.DiffExpr()=" + a.DiffExpr());
                        if (((b.DiffExpr() == null) || (b.DiffExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyLambdaRules("parseTermDiff00", OutputExprn, a, a.DiffExpr(), b, "", "", "*", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff00", OutputExprn, a, b.DiffExpr(), b, "", "", "+", "ExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if (OutputExprn.equalsIgnoreCase(DiffWithRespTo)) {
                            x = ApplyLambdaRules("parseTermDiff10", aMappedProduct, a, b.DiffExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff10", aMappedProduct, a, b.DiffExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((a.DiffExpr().contains("Parenthesis")) || (b.DiffExpr().contains("Parenthesis"))) {
                            x = ApplyLambdaRules("parseTermDiff11", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff11", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((aMappedProduct.contains(DiffWithRespTo)) && (b.DiffExpr().contains(DiffWithRespTo))) {
                            x = ApplyLambdaRules("parseTermDiff12", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff12", aMappedProduct, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((!IsAConstant(OutputExprn, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            x = ApplyLambdaRules("parseTermDiff13", OutputExprn, a, b.DiffExpr(), b, "", OutputExprn, "/", "QuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff13", OutputExprn, a, b.DiffExpr(), b, "", OutputExprn, "/", "QuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else {
                            x = ApplyLambdaRules("parseTermDiff14", OutputExprn, a, b.DiffExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermDiff14", OutputExprn, a, b.DiffExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        }
                        log.info("Diff:parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " posDiff=" + posDiff + " chDiff=" + (char) chDiff + " posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff);
                        if ((chDiff == '+') || (chDiff == '-')) {

                        } else if ((chDiff != -1) && (chDiff != 0)) {
                            SyncDiff();
                        }
                    } else {
                        if (posDiff < StrDiff.length()) {
                            TermEndPos = posDiff;
                        } else {
                            TermEndPos = StrDiff.length();
                        }
                        if (TermStartPos <= TermEndPos) {
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Term Expr=" + StrDiff.substring(TermStartPos, TermEndPos) + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:xExprn=" + xExprn);
                        } else {
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                            log.info("Diff:parseTermDiff:Return Value parseTermDiff:xExprn=" + xExprn);
                        }
                        return x;
                    }
                }
            }

            DiffExpr parseFactorDiff() {
                String Exprn = "";
                int mychNext = chDiff;
                int myposNext = posDiff;
                int StartPos = posDiff;
                int aStartPos = posDiff;
                int bStartPos = -1;
                int FactorStartPos = posDiff;
                int FactorEndPos = -1;
                DiffExpr x = parseBaseDiff();
                log.info("Diff:parseFactorDiff:Initial:x.DiffExpr()=" + x.DiffExpr() + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
                if ((x.DiffExpr().equalsIgnoreCase("-"))
                        || (x.DiffExpr().equalsIgnoreCase("+"))
                        || (x.DiffExpr().equalsIgnoreCase("*"))
                        || (x.DiffExpr().equalsIgnoreCase("/"))
                        || (x.DiffExpr().equalsIgnoreCase("^"))) {
                    x = (() -> "");
                    log.info("Diff:parseFactorDiff:Initial Call:Return Value:x.DiffExpr()=" + x.DiffExpr());
                    return x;
                }
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
                        if (!FunctionDiffExpr.equalsIgnoreCase("0.0")) {
                            x = (() -> FunctionDiffExpr);
                            Exprn = FunctionDiffExpr;
                            log.info("Diff:parseFactorDiff:@Function@:FunctionDiffExpr=" + FunctionDiffExpr);
                            log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);
                        } else {
                            x = (() -> FactorsExpr);
                            Exprn = FactorsExpr;
                            log.info("Diff:parseFactorDiff:@Function@:FunctionDiffExpr=" + FunctionDiffExpr);
                            log.info("Diff:parseFactorDiff:@Function@:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:@Function@:Exprn=" + Exprn);
                        }
                    } else if ((FunctionDiffExpr == null) || (FunctionDiffExpr.equalsIgnoreCase(""))) {
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
                        log.info("Diff:parseFactorDiff:Initial Call:Return Value:x.DiffExpr()=" + x.DiffExpr());
                        return x;
                    }
                } else if (x.DiffExpr().contains("@ExtremityFactor@")) {
                    x = (() -> FactorsExpr);
                    Exprn = FactorsExpr;
                    log.info("Diff:parseFactorDiff:Initial Call:x.DiffExpr()=" + x.DiffExpr());
                    log.info("Diff:parseFactorDiff:Initial Call:Exprn=" + Exprn);
                    if ((chDiff == '*') || (chDiff == '/') || (chDiff == -1) || (chDiff == 0)) {
                        log.info("Diff:parseFactorDiff:Initial Call:Return Value:x.DiffExpr()=" + x.DiffExpr());
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

                        GotExpntNumFlag = true;

                        if (bStartPos == -1) {
                            bStartPos = posDiff;
                        }
                        DiffExpr a = x, b = parseBaseDiff();
                        int bEndPos = posDiff;
                        int aEndPos = posDiff;
                        int aHighLvlMappos = 0;
                        int bHighLvlMappos = 0;
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Diff:parseFactorDiff:Loop:b.DiffExpr()=" + b.DiffExpr() + " a.DiffExpr()=" + a.DiffExpr() + " Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                        log.info("Diff:parseFactorDiff:Loop:aStartPos=" + aStartPos + " aEndPos=" + aEndPos);
                        log.info("Diff:parseFactorDiff:Loop:bStartPos=" + bStartPos + " bEndPos=" + bEndPos);
                        if (bStartPos > bEndPos) {
                            log.info("Diff:parseFactorDiff:Loop:bExprn(bStartPos)=" + StrDiff.substring(bStartPos));
                            log.info("Diff:parseFactorDiff:Loop:bExprn(bEndPos)=" + StrDiff.substring(bEndPos));
                            bHighLvlMappos = bStartPos;
                            bStartPos = bEndPos;
                        }
                        if (aStartPos > aEndPos) {
                            log.info("Diff:parseFactorDiff:Loop:aExprn(aStartPos)=" + StrDiff.substring(aStartPos));
                            log.info("Diff:parseFactorDiff:Loop:aExprn(aEndPos)=" + StrDiff.substring(aEndPos));
                            aHighLvlMappos = aStartPos;
                            aStartPos = aEndPos;
                        }
                        log.info("Diff:parseFactorDiff:Loop:b.DiffExpr()=" + b.DiffExpr() + " a.DiffExpr()=" + a.DiffExpr() + " SavedOperator=" + (char) SavedOperator + " aStartPos=" + aStartPos + " aEndPos=" + aEndPos + " bStartPos=" + bStartPos + " bEndPos=" + bEndPos);
                        log.info("Diff:parseFactorDiff:Loop:Exprn=" + Exprn);
                        //log.info("Diff:parseFactorDiff:Loop:aExprn=" + StrDiff.substring(aStartPos, aEndPos));
                        //log.info("Diff:parseFactorDiff:Loop:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                        // x^4
                        if (Exprn.equalsIgnoreCase(DiffWithRespTo)) {
                            Split(b.DiffExpr(), DiffWithRespTo);
                            Expr_Func_Split(b.DiffExpr(), DiffWithRespTo);
                            String bExpr = FactorsExpr;
                            String bDiffExpr = FactorsDiffExpr;
                            log.info("Diff:parseFactorDiff:Loop:FactorsExpr=" + FactorsExpr);
                            log.info("Diff:parseFactorDiff:Loop:FactorsDiffExpr=" + FactorsDiffExpr);
                            if (DiffTermOperator == '/') {
                                if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) >= 1)) {
                                    x = (() -> "-" + bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1));
                                    Exprn = "-" + bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 1:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 1:Exprn=" + Exprn);
                                } else if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) < 0)) {
                                    x = (() -> bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1));
                                    Exprn = bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 2:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 2:Exprn=" + Exprn);
                                } else if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) > 0)) {
                                    x = (() -> "-" + bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1));
                                    Exprn = "-" + bExpr + "/" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) + 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 1:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 1:Exprn=" + Exprn);
                                }
                            } else {
                                if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) >= 1)) {
                                    x = (() -> bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1));
                                    Exprn = bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 3:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 3:Exprn=" + Exprn);
                                } else if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) < 0)) {
                                    x = (() -> bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1));
                                    Exprn = bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 4:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 4:Exprn=" + Exprn);
                                } else if ((IsAConstant(bExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (Double.parseDouble(bExpr) > 0)) {
                                    x = (() -> bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1));
                                    Exprn = bExpr + "*" + DiffWithRespTo + "^" + RemoveEDbl(Double.parseDouble(bExpr) - 1);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 3:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator + " bExpr=" + bExpr);
                                    log.info("Diff:parseFactorDiff:x^4:Rule 3:Exprn=" + Exprn);
                                }
                            }
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
                        } else if ((IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // 2^3
                            log.info("Diff:parseFactorDiff:2^3:aExprn=" + StrDiff.substring(aStartPos, aEndPos));
                            log.info("Diff:parseFactorDiff:2^3:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                            if (b.DiffExpr().contains("@Constant@")) {
                                log.info("Diff:parseFactorDiff:Exponential Constant:Exprn=" + Exprn + " x.DiffExpr()=" + x.DiffExpr());
                                Split(b.DiffExpr(), DiffWithRespTo);
                                log.info("Diff:parseFactorDiff:Loop:FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " DiffTermOperator=" + (char) DiffTermOperator);
                                String Temp = "";

                                if (DiffTermOperator == -1) {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                } else {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) DiffTermOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                }
                                log.info("Diff:parseFactorDiff:Temp=" + Temp + " FactorsExpr=" + FactorsExpr + " DiffTermOperator=" + (char) DiffTermOperator);
                                final String MyTerm = Temp;
                                x = (() -> MyTerm);
                                Exprn = MyTerm;
                            }
                            log.info("Diff:parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Diff:parseFactorDiff:Exprn=" + Exprn);
                        } else if ((IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            // 2^a
                            final String aExprn = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn = StrDiff.substring(bStartPos, bEndPos);
                            x = (() -> aExprn + "^" + bExprn);
                            Exprn = aExprn + "^" + bExprn;
                            log.info("Diff:parseFactorDiff:2^a:aExprn=" + StrDiff.substring(aStartPos, aEndPos));
                            log.info("Diff:parseFactorDiff:2^a:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                            log.info("Diff:parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Diff:parseFactorDiff:Exprn=" + Exprn);
                        } else if (((IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var")))) {
                            // 2^x
                            final String aExprn = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn = StrDiff.substring(bStartPos, bEndPos);
                            x = (() -> "1.0/" + "ln(" + aExprn + ")" + "*" + aExprn + "^" + bExprn);
                            Exprn = "1.0/" + "ln(" + aExprn + ")" + "*" + aExprn + "^" + bExprn;
                            log.info("Diff:parseFactorDiff:2^x:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:2^x:aExprn=" + StrDiff.substring(aStartPos, aEndPos));
                            log.info("Diff:parseFactorDiff:2^x:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Var"))
                                || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                            // 5.0^xyc1
                            final String aExprn = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn = StrDiff.substring(bStartPos, bEndPos);
                            String[] Temp = new String[2];
                            Temp = StrDiff.substring(aStartPos, aEndPos).split("\\^");
                            if (Temp.length >= 1) {
                                final String Temp0 = Temp[0];
                                x = (() -> "ln(" + Temp0 + ")" + "*" + aExprn);
                                Exprn = "ln(" + Temp0 + ")" + "*" + aExprn;
                                log.info("Diff:parseFactorDiff:2^x:Temp[0]=" + Temp[0]);
                                log.info("Diff:parseFactorDiff:2^x:Temp0=" + Temp0);
                            }
                            log.info("Diff:parseFactorDiff:2^x:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:2^x:aExprn=" + StrDiff.substring(aStartPos, aEndPos));
                            log.info("Diff:parseFactorDiff:2^x:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // a^b
                            final String aExprn = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn = StrDiff.substring(bStartPos, bEndPos);
                            if ((!IsAConstant(aExprn, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) {
                                x = (() -> aExprn);
                                Exprn = aExprn;
                            } else if (!Exprn.equalsIgnoreCase(aExprn)) {
                                x = (() -> aExprn + "^" + bExprn);
                                Exprn = aExprn + "^" + bExprn;
                            } else {
                                x = (() -> aExprn);
                                Exprn = aExprn;
                            }
                            if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Diff:parseFactorDiff:a^b:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                            log.info("Diff:parseFactorDiff:a^b:aExprn=" + StrDiff.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Diff:parseFactorDiff:a^b:bExprn=" + StrDiff.substring(bStartPos, bEndPos));

                        } else if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))
                                && (!IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // x^b
                            //x = (() -> "1");
                            if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '*')) {
                                LADiffFactorOperator = '*';
                            } else if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '/')) {
                                LADiffFactorOperator = '/';
                            }
                            log.info("Diff:parseFactorDiff:x^b:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:x^b:aExprn=" + StrDiff.substring(aStartPos, aEndPos) + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                            log.info("Diff:parseFactorDiff:x^b:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                && (!IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // a^x
                            final String aExprn1 = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn1 = StrDiff.substring(bStartPos, bEndPos);
                            x = (() -> "1.0/" + "ln(" + aExprn1 + ")" + "*" + aExprn1 + "^" + bExprn1);
                            Exprn = "1.0/" + "ln(" + aExprn1 + ")" + "*" + aExprn1 + "^" + bExprn1;
                            if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Diff:parseFactorDiff:a^x:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseFactorDiff:a^x:aExprn=" + StrDiff.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Diff:parseFactorDiff:a^x:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                && (IsAConstant(StrDiff.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // a^2
                            final String aExprn1 = StrDiff.substring(aStartPos, aEndPos);
                            final String bExprn1 = StrDiff.substring(bStartPos, bEndPos);
                            if ((!IsAConstant(StrDiff.substring(aStartPos, aEndPos), DiffWithRespTo))
                                    && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) {
                                x = (() -> aExprn1);
                                Exprn = aExprn1;
                            } else {
                                x = (() -> aExprn1 + "^" + bExprn1);
                                Exprn = aExprn1 + "^" + bExprn1;
                            }
                            if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrDiff.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Diff:parseFactorDiff:a^2:aExprn=" + StrDiff.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Diff:parseFactorDiff:a^2:bExprn=" + StrDiff.substring(bStartPos, bEndPos));
                            log.info("Diff:parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Diff:parseFactorDiff:Exprn=" + Exprn);
                        }
                        if (bStartPos == bEndPos) {
                            log.info("Diff:parseFactorDiff:Mapping Loop:StrDiff=" + StrDiff.substring(posDiff));
                            posDiff = bHighLvlMappos;
                            while ((IsNumber(StrDiff.charAt(posDiff))) || (IsVariable(StrDiff.charAt(posDiff)))) {
                                nextCharDiff();
                            }
                            log.info("Diff:parseFactorDiff:Mapping Loop:StrDiff=" + StrDiff.substring(posDiff));
                        }
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Diff:parseFactorDiff:x.DiffExpr()=" + x.DiffExpr());
                        log.info("Diff:parseFactorDiff:Exprn=" + Exprn);
                    } else {
                        if ((posDiff >= 0) && (posDiff < StrDiff.length())) {
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
                            if (FactorStartPos <= FactorEndPos) {
                                log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:x.DiffExpr()=" + x.DiffExpr() + " Original Diff Factor Expr=" + StrDiff.substring(FactorStartPos, FactorEndPos));
                                log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:Exprn=" + Exprn);
                            } else {
                                log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:x.DiffExpr()=" + x.DiffExpr());
                                log.info("Diff:parseFactorDiff:Normal:Return Value parseFactorDiff:Exprn=" + Exprn);

                            }
                            return x;
                        }

                    }
                }
            }

            DiffExpr parseBaseDiff() {
                int startPos = posDiff;
                boolean ConfirmedConstFlag = false;
                boolean ConfirmedFuncFlag = false;
                boolean ConfirmedIndConstFlag = false;
                DiffExpr x = (() -> "");
                if (eatDiff('-')) { // unary minus

                    DiffExpr b = parseBaseDiff();
                    //x = () -> "-" + xx;
                    if (startPos == 0) {
                        x = (() -> "-" + b.DiffExpr());
                    } else {
                        x = (() -> "-" + b.DiffExpr());
                    }
                    log.info("Diff:parseBaseDiffExpr:Sign -: x.DiffExpr()=" + x.DiffExpr());
                    return x;
                } else if (eatDiff('+')) { // unary plus
                    log.info("Diff:parseBaseDiffExpr:Sign +: x.DiffExpr()=" + x.DiffExpr());
                }
                if (IsNumber(chDiff)) { //numbers
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
                    String xx = "";
                    if ((IsAConstant(StrDiff.substring(startPos, posDiff), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                        xx = "" + Double.parseDouble(StrDiff.substring(startPos, posDiff));
                    }
                    log.info("Diff:parseBaseDiffExpr:Numbers: xx=" + xx + " EnteredLAConstFlag=" + EnteredLAConstFlag);
                    LookAheadDiffConst MyCoeff;
                    int DiffIntLength = posDiff - startPos;
                    if ((IsAConstant(xx, DiffWithRespTo)) && ((chDiff == '*') || (chDiff == '/') || (chDiff == -1))) {
                        log.info("Diff:parseBaseDiffExpr:Entered LookAheadDiffConst Number:MyCoeff=" + xx);
                        MyCoeff = parseLADiff(StrDiff.substring(startPos), startPos, DiffIntLength, "Differentiation", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                            log.info("Diff:parseBaseDiffExpr:ConfirmedConstFlag:if:Function Expression:xConst=" + xConst + " ConfirmedConstFlag=" + ConfirmedConstFlag + " ConfirmedFuncFlag=" + ConfirmedFuncFlag + " ConfirmedIndConstFlag=" + ConfirmedIndConstFlag);
                        } else if ((!IsAConstant(xConst, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                            ConfirmedFuncFlag = true;
                            log.info("Diff:parseBaseDiffExpr:ConfirmedConstFlag:Else:Function Expression:xConst=" + xConst + " ConfirmedConstFlag=" + ConfirmedConstFlag + " ConfirmedFuncFlag=" + ConfirmedFuncFlag + " ConfirmedIndConstFlag=" + ConfirmedIndConstFlag);
                        }
                        log.info("Diff:parseBaseDiffExpr:ConfirmedConstFlag:Expression:xConst=" + xConst + " ConfirmedConstFlag=" + ConfirmedConstFlag);
                    } else {
                        MyCoeff = parseLADiff(StrDiff.substring(startPos), startPos, DiffIntLength, "Differentiation", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                        } else if ((!IsAConstant(xConst, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                            ConfirmedIndConstFlag = true;
                            log.info("Diff:parseBaseDiffExpr:IndConst Expression:ConfirmedConstFlag:Else:Function Expression:xConst=" + xConst + " ConfirmedConstFlag=" + ConfirmedConstFlag + " ConfirmedFuncFlag=" + ConfirmedFuncFlag + " ConfirmedIndConstFlag=" + ConfirmedIndConstFlag);
                        }
                        log.info("Diff:parseBaseDiffExpr:IndConst Expression:xConst=" + xConst);
                    }
                    log.info("Diff:parseBaseDiffExpr:Number:MyCoeff=" + xConst + " EnteredLAConstFlag=" + EnteredLAConstFlag);
                    if (chDiff != -1) {
                        SavedOperator = chDiff;
                        if ((SavedOperator == ')') && (posDiff + 1) < StrDiff.length()) {
                            LookAheadOperator = StrDiff.charAt(posDiff + 1);
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
                    } else if ((xConst.equalsIgnoreCase("Infinity")) || (xConst.equalsIgnoreCase("NaN"))
                            || (xConst.equalsIgnoreCase("-Infinity")) || (xConst.equalsIgnoreCase("-NaN"))
                            || (xConst.equalsIgnoreCase("+Infinity")) || (xConst.equalsIgnoreCase("+NaN"))
                            || (xConst.contains("Infinity"))
                            || (xConst.contains("NaN"))) {
                        final String ConsolidatedIndConstTerm = xConst;
                        x = () -> (ConsolidatedIndConstTerm + "@" + "ExtremityFactor" + "@" + ConsolidatedIndConstTerm);
                        log.info("Diff:parseBaseDiffExpr:IndConstExpression:x.DiffExpr()=" + x.DiffExpr());
                        return x;
                    } else if ((ConfirmedFuncFlag == true) && (ConfirmedIndConstFlag == false)) {
                        final String ConsolidatedIndConstTerm = xConst;
                        x = () -> (ConsolidatedIndConstTerm + "@" + "Constant" + "@" + "0.0");
                        log.info("Diff:parseBaseDiffExpr:Constant:x.DiffExpr()=" + x.DiffExpr());
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
                        int argEndpos = -1;
                        if (chDiff == '(') {
                            eatDiff('(');
                        } else {
                            while ((chDiff > -1) && (chDiff != '(')) {
                                nextCharDiff();
                            }
                            eatDiff('(');
                        }
                        int openBracesCount = 1;
                        int closeBracesCount = 0;
                        int startcount = posDiff;
                        while ((posDiff < StrDiff.length()) && ((chDiff != ')') || (openBracesCount != closeBracesCount))) {
                            nextCharDiff();
                            if (chDiff == '(') {
                                openBracesCount++;
                            }
                            if (chDiff == ')') {
                                closeBracesCount++;
                            }
                        }
                        String Exprn = StrDiff.substring(startcount, posDiff);
                        log.info("Diff:parseBaseDiff:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chDiff=" + (char) chDiff);
                        ParseLogDiffrIntegr("Diff:", "parseBaseDiff", "Initial", "StrDiff", StrDiff);
                        DiffExpr arg = (() -> "");
                        log.info("Diff:parseBaseDiff:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chDiff=" + (char) chDiff);
                        if (Exprn.contains(DiffWithRespTo)) {
                            Map<String, String> variablesBackupState = new HashMap();
                            BackupDiffIntegrStateAll(variablesBackupState);
                            MathsContxtLAv.ChaosPrevention();
                            InitState();
                            arg = parseDiff(Exprn, variables, DiffWithRespTo);
                            argEndpos = posDiff;
                            RestoreDiffIntegrStateAll(variablesBackupState);
                            posDiff = argEndpos - 1;
                            if ((posDiff > -1) && (posDiff < StrDiff.length())) {
                                chDiff = StrDiff.charAt(posDiff);
                            } else {
                                posDiff = StrDiff.length();
                                chDiff = -1;
                                argEndpos = posDiff;
                            }
                            log.info("Diff:parseBaseDiff:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.DiffExpr()=" + arg.DiffExpr() + " chDiff=" + (char) chDiff);
                            ParseLogDiffrIntegr("Diff:", "parseBaseDiff", "Final", "StrDiff", StrDiff);
                        } else {
                            log.info("Diff:parseBaseDiff:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.DiffExpr()=" + arg.DiffExpr() + " chDiff=" + (char) chDiff);
                        }
                        FuncArgFlag = false;
                        ParanExprFlag = false;
                        ParenthesisFlag = false;
                        eatDiff(')');
                        log.info("Diff:parseBaseDiff:Function Argument:arg.DiffExpr()=" + arg.DiffExpr());
                        ParanExprInProgressFlag = false;
                        log.info("Diff:parseBaseDiff:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " Str.length()=" + StrDiff.length());
                        String Factor = Exprn;
                        if (argstartpos <= argEndpos) {
                            log.info("Diff:parseBaseDiff:Function Argument:Factor=" + Factor + " String=" + StrDiff.substring(argstartpos, argEndpos));
                        }
                        log.info("Diff:parseBaseDiff:Aligning Parenthesis:Started");
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
                                if ((startPos >= 1) && (startPos < StrDiff.length()) && (StrDiff.charAt(startPos - 1) == '-')) {
                                    name = "-" + name;
                                }
                                final String MyName = name;
                                final String MyArgStr = arg.DiffExpr();
                                //Dummy Call
                                if (!arg.DiffExpr().equalsIgnoreCase("")) {
                                    x = (() -> MyName + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + MyArgStr);
                                    log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                                } else if (!Exprn.contains(DiffWithRespTo)) {
                                    x = (() -> MyName + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + 0.0 + "@" + "ConstantExprn" + "@" + MyName + "(" + Exprn + ")");
                                    log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                                } else {
                                    x = (() -> MyName + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + 0.0 + "@" + "ConstantExprn" + "@" + MyName + "(" + Exprn + ")");
                                    log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                                }
                                Split(x.DiffExpr(), DiffWithRespTo);
                                Expr_Func_Split(x.DiffExpr(), DiffWithRespTo);
                                log.info("Diff:parseBaseDiff:arg.DiffExpr()=" + arg.DiffExpr());
                                log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                                log.info("Diff:parseBaseDiff:FunctionDiffExpr=" + FunctionDiffExpr);
                                if (!FactorsDiffExpr.equalsIgnoreCase("0.0")) {
                                    x = (() -> FunctionDiffExpr + "@" + "Complete" + "@");
                                    log.info("Diff:parseBaseDiff:Complete:x.DiffExpr()=" + x.DiffExpr() + " FactorsDiffExpr=" + FactorsDiffExpr + " FunctionDiffExpr=" + FunctionDiffExpr);
                                } else {
                                    x = (() -> FactorsDiffExpr);
                                    log.info("Diff:parseBaseDiff:Complete:x.DiffExpr()=" + x.DiffExpr() + " FactorsDiffExpr=" + FactorsDiffExpr + " FunctionDiffExpr=" + FunctionDiffExpr);
                                }
                                return x;
                            } else {
                                final String MyName = name;
                                x = (() -> (MyName + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + "(" + "Unknown" + ")"));
                                log.info("Diff:parseBaseDiff:Complete:x.DiffExpr()=" + x.DiffExpr());
                            }
                            log.info("Diff:parseBaseDiff:x.DiffExpr()=" + x.DiffExpr());
                            log.info("Diff:parseBaseDiff:FunctionDiffExpr=" + FunctionDiffExpr);
                            log.info("Diff:parseBaseDiff:arg.DiffExpr()=" + arg.DiffExpr());
                        } else {
                            final String MyName = name;
                            x = () -> (MyName + "@" + "Unknown" + "@" + (MyName));
                        }
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo))) {
                        log.info("Diff:parseBaseDiff:Polynomial:posDiff=" + posDiff);
                        final String MyName = name;
                        x = () -> (MyName + "@" + "Polynomial" + "@" + (MyName));
                        ContainsXFlag = true;
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo)) && (!name.contains("^"))) {
                        log.info("Diff:parseBaseDiff:Factor:posDiff=" + posDiff);
                        final String MyName = name;
                        x = () -> (MyName + "@" + "Factor" + "@" + (MyName));
                        ContainsXFlag = true;
                    } else if ((!name.equalsIgnoreCase(DiffWithRespTo)) && (!name.equalsIgnoreCase("Infinity")) && (!name.equalsIgnoreCase("Nan"))) {//Independent Variable
                        log.info("Diff:parseBaseDiff:IndependentFactor:posDiff=" + posDiff);
                        final String MyName = name;
                        x = () -> (MyName + "@" + "IndependentFactor" + "@" + (MyName));
                    } else if ((!name.equalsIgnoreCase(DiffWithRespTo)) && ((name.equalsIgnoreCase("Infinity")) || (name.equalsIgnoreCase("NaN")))) {//Extremity Variable
                        log.info("Diff:parseBaseDiff:ExtremityFactor:posDiff=" + posDiff);
                        final String MyName = name;
                        x = () -> (MyName + "@" + "ExtremityFactor" + "@" + (MyName));
                    }
                    log.info("Diff:parseBaseDiffExpr:Variable/Function:name=" + name + " x.DiffExpr()=" + x.DiffExpr());
                    return x;
                } else if (chDiff == '(') {
                    int argstartpos = posDiff;
                    int argEndpos = -1;
                    if (chDiff == '(') {
                        eatDiff('(');
                    } else {
                        while ((chDiff > -1) && (chDiff != '(')) {
                            nextCharDiff();
                        }
                        eatDiff('(');
                    }
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posDiff;
                    while ((posDiff < StrDiff.length()) && ((chDiff != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharDiff();
                        if (chDiff == '(') {
                            openBracesCount++;
                        }
                        if (chDiff == ')') {
                            closeBracesCount++;
                        }
                    }
                    eatDiff(')');
                    String Exprn = StrDiff.substring(startcount, posDiff - 1);
                    log.info("Diff:parseBaseDiff:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chDiff=" + (char) chDiff);
                    log.info("Diff:parseBaseDiff:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chDiff=" + (char) chDiff);
                    ParseLogDiffrIntegr("Diff:", "parseBaseDiff", "Initial", "StrDiff", StrDiff);
                    DiffExpr arg = (() -> "");
                    log.info("Diff:parseBaseDiff:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chDiff=" + (char) chDiff);
                    if (Exprn.contains(DiffWithRespTo)) {
                        Map<String, String> variablesBackupState = new HashMap();
                        BackupDiffIntegrStateAll(variablesBackupState);
                        MathsContxtLAv.ChaosPrevention();
                        InitState();
                        arg = parseDiff(Exprn, variables, DiffWithRespTo);
                        argEndpos = posDiff;
                        RestoreDiffIntegrStateAll(variablesBackupState);
                        if ((posDiff > -1) && (posDiff < StrDiff.length())) {
                            chDiff = StrDiff.charAt(posDiff);
                        } else {
                            posDiff = StrDiff.length();
                            chDiff = -1;
                            argEndpos = posDiff;
                        }
                        log.info("Diff:parseBaseDiff:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.DiffExpr()=" + arg.DiffExpr() + " chDiff=" + (char) chDiff);
                        ParseLogDiffrIntegr("Diff:", "parseBaseDiff", "Final", "StrDiff", StrDiff);
                    } else {
                        log.info("Diff:parseBaseDiff:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.DiffExpr()=" + arg.DiffExpr() + " chDiff=" + (char) chDiff);
                    }
                    ParanExprFlag = false;
                    ParenthesisFlag = false;
                    log.info("Diff:parseBaseDiff:Function Argument:arg.DiffExpr()=" + arg.DiffExpr());
                    ParanExprInProgressFlag = false;
                    log.info("Diff:parseBaseDiff:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " Str.length()=" + StrDiff.length());
                    //(name + "@" + "Parenthesis" + "@" + ParanDiffExpr) )
                    final String ParaDiff = arg.DiffExpr();
                    if (IsAConstant(ParaDiff, DiffWithRespTo)) {
                        x = (() -> Exprn + "@" + "Parenthesis" + "@" + ParaDiff);
                        log.info("Diff:parseBaseDiff:Function Argument:x.DiffExpr()=" + x.DiffExpr() + " chDiff=" + (char) chDiff + " posDiff=" + posDiff);
                    } else {
                        x = (() -> Exprn + "@" + "Parenthesis" + "@" + "(" + ParaDiff + ")");
                        log.info("Diff:parseBaseDiff:Function Argument:x.DiffExpr()=" + x.DiffExpr() + " chDiff=" + (char) chDiff + " posDiff=" + posDiff);
                    }
                    return x;
                } else {
                    if ((IsNotAnOperator(chDiff) && (IsNotANumber(chDiff))) && (chDiff != 0) && (chDiff != -1) && (chDiff != '(')) {
                        if ((chDiff != -1) && (chDiff != 0) && (posDiff >= 1) && (posDiff < StrDiff.length()) && (IsNumber(chDiff))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Diff:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                        } else if ((chDiff != -1) && (chDiff != 0) && (posDiff >= 1) && (posDiff < StrDiff.length()) && (IsOperator(chDiff))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Diff:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                        } else if ((chDiff != -1) && (chDiff != 0) && (posDiff >= 1) && (posDiff < StrDiff.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Diff:parseBase:Unexpected: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Diff:parseBase:Unexpected char: " + (char) chDiff + " int=" + (int) chDiff + " posDiff=" + posDiff + " StrDiff=" + StrDiff.substring(posDiff, StrDiff.length()));
                        }
                    }
                }
                return x;
            }
        }.parseDiffExpr();
    }
//Created by Rajesh Pai
// Integrates F(x)
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface IntegrExpr {

        String IntegrExpr();
    }

    public static IntegrExpr parseIntegr(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            IntegrExpr parseIntegrExpr() {
                IntegrExpr x = (() -> "");
                StrIntegr = str;
                ResetVars("IntegrExpr");
                if ((StrIntegr == null) || (StrIntegr.equalsIgnoreCase(""))) {
                    return x;
                }
                posIntegr = 0;
                chIntegr = StrIntegr.charAt(0);
                SavedOperator = 0;
                int oldch = chIntegr;
                if ((IsVariable(chIntegr)) || (IsNumber(chIntegr))) {
                    oldch = 0;
                } else if ((chIntegr == '-') || (chIntegr == '+')) {
                    posIntegr = posIntegr + 1;
                    chIntegr = StrIntegr.charAt(1);
                }
                if ((StrIntegr != null) && (StrIntegr.length() > 0)
                        && (StrIntegr.length() < 2)
                        && ((chIntegr == '*')
                        || (chIntegr == '/')
                        || (chIntegr == '^'))) {
                    return x;
                } else {
                    x = parseExpressionIntegr();//parseParaIntegr();
                }
                log.info("Integr:parseIntegr:Final:x.IntegrExpr()=" + x.IntegrExpr());
                if ((oldch == '-') && (x.IntegrExpr().startsWith("-"))) {
                    final String FinalOutput = x.IntegrExpr().replaceFirst("-", "");
                    x = (() -> FinalOutput);
                } else if ((oldch == '-') && (!x.IntegrExpr().startsWith("-"))) {
                    final String FinalOutput = x.IntegrExpr();
                    final int MySignInt = oldch;
                    x = (() -> (char) MySignInt + FinalOutput);
                }
//                if (x.IntegrExpr().contains("--")) {
//                    String OutputExprn = x.IntegrExpr().replaceAll("\\--", "-");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
//                if (x.IntegrExpr().contains("+-")) {
//                    String OutputExprn = x.IntegrExpr().replaceAll("\\+-", "-");
//                    final String FinalOutput = OutputExprn;
//                    x = (() -> FinalOutput);
//                }
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Diff:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                return x;
            }

            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            IntegrExpr parseExpressionIntegr() {
                log.info("Integr:parseExpressionIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                int ExprStartPos = posIntegr;
                int ExprEndPos = -1;
                int startpos = posIntegr;
                DiffTermOperator = -1;
                SavedOperator = 0;
                IntegrExpr x = parseTermIntegr();
                int endpos = posIntegr;
                String xExprn = "";
                String OutputExprn = "";
                if ((startpos != -1) && (endpos != -1) && (startpos < endpos) && (startpos <= StrIntegr.length()) && (startpos < StrIntegr.length()) && (endpos <= StrIntegr.length())) {
                    xExprn = StrIntegr.substring(startpos, endpos);
                }
                String xConst = "";
                OutputExprn = x.IntegrExpr();
                log.info("Integr:parseExpressionIntegr:Initial Call to parseTermIntegr:OutputExprn=" + OutputExprn);
                if (((IsAConstant(OutputExprn, DiffWithRespTo)) && (IsAConstant(xExprn, DiffWithRespTo))
                        || ((!IsAConstant(OutputExprn, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstant")))
                        && ((!IsAConstant(xExprn, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstant"))))))
                        && ((chIntegr == '+') || (chIntegr == '-') || (chIntegr == -1))) {
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
                    DiffTermOperator = -1;
                    ContainsXFlag = false;
                    log.info("Integr:parseExpressionIntegr:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    if ((eatIntegr('+')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) {// addition
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
                        x = ApplyLambdaRules("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "+", "SumRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                        xExprn = ApplyLambdaString("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "+", "SumRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");

                        log.info("Integr:parseExpressionIntegr:a.IntegrExpr()=" + a.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseExpressionIntegr:xExprn=" + xExprn);
                    } else if ((eatIntegr('-')) && ((NormalExprnFlag == true) || (NormalNotPareFlag == true) || (ParenthesisFlag == true) || (FuncArgFlag == true))) { // subtraction
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
                        x = ApplyLambdaRules("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                        xExprn = ApplyLambdaString("parseExpressionIntegr0", xExprn, a, bExprn, b, "", "", "-", "SubtractionRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");

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
                        if (ExprStartPos < ExprEndPos) {
                            log.info("Integr:parseExpressionIntegr:Return Value parseExpressionIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(ExprStartPos, ExprEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        }
                        log.info("Integr:parseExpressionIntegr:Return Value parseExpressionIntegr:xExprn=" + xExprn);
                        ParseLog("Integr", "parseExpressionIntegr", "Initial", "StrIntegr", StrIntegr);
                        final String Temp = xExprn;
                        x = (() -> Temp);
                        ParseLog("Integr", "parseExpressionIntegr", "Final", "StrIntegr", StrIntegr);
                        return x;
                    }
                }
            }

            IntegrExpr parseTermIntegr() {
                log.info("Integr:parseTermIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                int TermStartPos = posIntegr;
                int TermEndPos = -1;
                int startpos = posIntegr;
                DiffTermOperator = -1;
                LADiffFactorOperator = -1;
                GotExpntNumFlag = false;
                IntegrExpr a = (() -> "");
                IntegrExpr b = (() -> "");
                IntegrExpr c = (() -> "");
                IntegrExpr x = (() -> "");
                int astartpos = posIntegr;
                x = parseFactorIntegr();
                c = x;
                int endpos = posIntegr;
                String OutputExprn = "";
                String MappedProduct = "";
                String aMappedProduct = "";
                String aOrginIntegr = "";
                String bOrginIntegr = "";
                String xExprn = "";

                String xConst = "";
                OutputExprn = x.IntegrExpr();
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:OutputExprn=" + OutputExprn);
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:xExprn=" + xExprn + " StrIntegr.length()=" + StrIntegr.length());
                log.info("Integr:parseTermIntegr:After Initial Call to FactorDiff:x.IntegrExpr()=" + x.IntegrExpr() + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr);
                int aendpos = posIntegr;
                if ((astartpos != -1) && (astartpos < aendpos) && (astartpos <= StrIntegr.length()) && (astartpos < StrIntegr.length()) && (aendpos < StrIntegr.length())) {
                    aOrginIntegr = StrIntegr.substring(astartpos, aendpos);
                }
                if ((posIntegr == StrIntegr.length()) && (chIntegr == -1)) {
                    final String OutputExprn1 = OutputExprn;
                    x = (() -> OutputExprn1);
                    log.info("Integr:parseTermIntegr:Last Term:Return Value parseTermDiff:x.IntegrExpr()=" + x.IntegrExpr() + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                    if (TermStartPos < TermEndPos) {
                        log.info(":Last Term:Original Integrand=" + StrIntegr.substring(TermStartPos, TermEndPos));
                    }
                    log.info("Integr:parseTermIntegr:Last Term:Return Value parseTermDiff:xExprn=" + xExprn);
                    return x;
                } else if ((xConst.equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0")) && (!OutputExprn.equalsIgnoreCase("1.0")) && (!x.IntegrExpr().equalsIgnoreCase("1.0"))) {
                    if ((!xExprn.equalsIgnoreCase("")) && (!aMappedProduct.equalsIgnoreCase(""))) {
                        aMappedProduct = xExprn;
                    }
                    log.info("Integr:parseTermIntegr:Original Integrand:xExprn=" + xExprn + " aMappedProduct=" + aMappedProduct + " aOrginIntegr=" + aOrginIntegr);
                    ParseLog("Integr", "parseTermIntegr", "Initial", "StrIntegr", StrIntegr);
                    x = ApplyLambdaRules("parseTermIntegr001", OutputExprn, x, x.IntegrExpr(), x, "", aOrginIntegr, "*", "NoExponentRuleDiff", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermIntegr001", OutputExprn, c, c.IntegrExpr(), c, "", aOrginIntegr, "*", "NoExponentRuleDiff", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    ParseLog("Integr", "parseTermIntegr", "Final", "StrIntegr", StrIntegr);
                } else if ((xConst.equalsIgnoreCase(OutputExprn)) && (!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0")) && (!OutputExprn.equalsIgnoreCase("1.0")) && (!x.IntegrExpr().equalsIgnoreCase("1.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermIntegr001", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "NoExponentRuleTerm", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermIntegr001", OutputExprn, c, c.IntegrExpr(), c, "", "", "*", "NoExponentRuleTerm", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                } else if ((!x.IntegrExpr().equalsIgnoreCase("")) && (!x.IntegrExpr().equalsIgnoreCase("0.0"))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermIntegr001", OutputExprn, x, x.IntegrExpr(), x, "", "", "*", "CoeffExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermIntegr001", OutputExprn, c, c.IntegrExpr(), c, "", "", "*", "CoeffExponentRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                } else if (x.IntegrExpr().equalsIgnoreCase("1.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "1.0";
                } else if (x.IntegrExpr().equalsIgnoreCase("0.0")) {
                    aMappedProduct = xExprn;
                    xExprn = "0.0";
                } else if ((x.IntegrExpr().equalsIgnoreCase("")) && (!IsAConstant(xExprn, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))) {
                    aMappedProduct = xExprn;
                    x = ApplyLambdaRules("parseTermIntegrInit", xExprn, x, x.IntegrExpr(), x, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    xExprn = ApplyLambdaString("parseTermIntegrInit", OutputExprn, c, c.IntegrExpr(), c, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                    log.info("Integr:parseTermIntegr:parseTermIntegrInit:x.IntegrExpr()=" + x.IntegrExpr());
                    log.info("Integr:parseTermIntegr:parseTermIntegrInit:xExprn=" + xExprn);

                }
                log.info("Integr:parseTermIntegr:After Co-efficient:x.IntegrExpr()=" + x.IntegrExpr());
                log.info("Integr:parseTermIntegr:After Co-efficient:xExprn=" + xExprn);
                log.info("Integr:parseTermIntegr:After Co-efficient:aMappedProduct=" + aMappedProduct);
                for (;;) {
                    if (eatIntegr('*')) { // multiplication
                        log.info("Integr:parseTermIntegr:Multiply:Entered" + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        if (DiffTermOperator == -1) {
                            DiffTermOperator = '*';
                        } else if (DiffTermOperator == '/') {
                            DiffTermOperator = '*';
                        }
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        int bstartpos = posIntegr;
                        a = x;
                        b = parseFactorIntegr();
                        int bendpos = posIntegr;
                        if ((bstartpos != -1) && (bstartpos < bendpos) && (bstartpos <= StrIntegr.length()) && (bstartpos < StrIntegr.length()) && (bendpos < StrIntegr.length())) {
                            MappedProduct = StrIntegr.substring(bstartpos, bendpos);
                            bOrginIntegr = MappedProduct;
                        }
                        log.info("Integr:parseTermIntegr:Multiply:a.IntegrExpr()=" + a.IntegrExpr() + " xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Multiply:b.IntegrExpr()=" + b.IntegrExpr());
                        if (!b.IntegrExpr().contains("Parenthesis")) {
                            OutputExprn = b.IntegrExpr();
                        }
                        log.info("Integr:parseTermIntegr:Multiply:aMappedProduct=" + aMappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:MappedProduct=" + MappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Multiply:xConst=" + xConst);
                        log.info("Integr:parseTermIntegr:Multiply:b.IntegrExpr()=" + b.IntegrExpr());
                        log.info("Integr:parseTermIntegr:Multiply:a.IntegrExpr()=" + a.IntegrExpr());
                        if ((((b.IntegrExpr() == null)) || (b.IntegrExpr().equalsIgnoreCase("")) || (b.IntegrExpr().equalsIgnoreCase("0.0"))) && ((OutputExprn == null) || (OutputExprn.equalsIgnoreCase("")))) {
                            x = ApplyLambdaRules("parseTermIntegr01", OutputExprn, a, a.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr01", OutputExprn, a, a.IntegrExpr(), b, "", "", "+", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if (xConst.equalsIgnoreCase(OutputExprn)) {
                            x = ApplyLambdaRules("parseTermIntegr10", OutputExprn, a, b.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr10", OutputExprn, a, b.IntegrExpr(), b, "", "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else if ((a.IntegrExpr().contains("Parenthesis")) || (b.IntegrExpr().contains("Parenthesis"))
                                || ((a.IntegrExpr().contains(DiffWithRespTo)) && (b.IntegrExpr().contains(DiffWithRespTo)))) {
                            if (b.IntegrExpr().contains("Parenthesis")) {
                                x = ApplyLambdaRules("parseTermIntegr11", OutputExprn, a, MappedProduct, b, "", aOrginIntegr, "*", "IntegrationByParts", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                xExprn = ApplyLambdaString("parseTermIntegr11", OutputExprn, a, MappedProduct, b, "", aOrginIntegr, "*", "IntegrationByParts", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                    aMappedProduct = aMappedProduct + "*" + MappedProduct;
                                }
                            } else if (((a.IntegrExpr().contains(DiffWithRespTo)) && (b.IntegrExpr().contains(DiffWithRespTo))) && ((!a.IntegrExpr().contains("\\(")) && (!b.IntegrExpr().contains("\\(")))) {
                                x = ApplyLambdaRules("parseTermIntegr14", OutputExprn, a, MappedProduct, b, bOrginIntegr, aOrginIntegr, "*", "IntegrationByPartsBothContainX", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                xExprn = ApplyLambdaString("parseTermIntegr14", OutputExprn, a, MappedProduct, b, bOrginIntegr, aOrginIntegr, "*", "IntegrationByPartsBothContainX", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                                if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                    aMappedProduct = aMappedProduct + "*" + MappedProduct;
                                }
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            x = ApplyLambdaRules("parseTermIntegr12", aMappedProduct, a, b.IntegrExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffIndConstOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr12", aMappedProduct, a, b.IntegrExpr(), b, "", "", "*", "ProductRule", DiffWithRespTo, LADiffIndConstOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else {
                            x = ApplyLambdaRules("parseTermIntegr13", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr13", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "*", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        }
                        log.info("Integr:parseTermIntegr:Multiply:aMappedProduct=" + aMappedProduct);
                        log.info("Integr:parseTermIntegr:Multiply:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Integr:parseTermIntegr:Multiply:xExprn=" + xExprn + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        if ((chIntegr == '+') || (chIntegr == '-')) {

                        } else if ((chIntegr != -1) && (chIntegr != 0)) {
                            SyncIntegr();
                        }
                    } else if (eatIntegr('/')) { // division
                        log.info("Integr:parseTermIntegr:Divide:Entered" + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr());
                        if (DiffTermOperator == -1) {
                            DiffTermOperator = '/';
                        } else if (DiffTermOperator == '*') {
                            DiffTermOperator = '/';
                        }
                        log.info("Integr:parseTermIntegr:Divide:OutputExprn=" + OutputExprn + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
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
                            x = ApplyLambdaRules("parseTermIntegr01", OutputExprn, a, a.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr01", OutputExprn, a, a.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if (xConst.equalsIgnoreCase(OutputExprn)) {
                            x = ApplyLambdaRules("parseTermIntegr10", OutputExprn, a, b.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr10", OutputExprn, a, b.IntegrExpr(), b, "", "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((a.IntegrExpr().contains("Parenthesis")) || (b.IntegrExpr().contains("Parenthesis"))) {
                            x = ApplyLambdaRules("parseTermIntegr11", OutputExprn, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr11", OutputExprn, a, MappedProduct, b, "", "", "/", "MappedQuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        } else if ((!aMappedProduct.contains(DiffWithRespTo)) && (MappedProduct.contains(DiffWithRespTo)) && (!MappedProduct.equalsIgnoreCase(DiffWithRespTo))) {
                            x = ApplyLambdaRules("parseTermIntegr12", aMappedProduct, a, b.IntegrExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr12", aMappedProduct, a, b.IntegrExpr(), b, "", "", "/", "QuotientRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "*" + MappedProduct;
                            }
                        } else {
                            x = ApplyLambdaRules("parseTermIntegr13", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            xExprn = ApplyLambdaString("parseTermIntegr13", OutputExprn, a, a.IntegrExpr(), b, aMappedProduct, "", "/", "ConstantRule", DiffWithRespTo, DiffTermOperator, "LowLevelRules");
                            if ((!MappedProduct.equalsIgnoreCase("")) && (!MappedProduct.equalsIgnoreCase("1.0"))) {
                                aMappedProduct = aMappedProduct + "/" + MappedProduct;
                            }
                        }

                        log.info("Integr:parseTermIntegr:Divide:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Integr:parseTermIntegr:Divide:xExprn=" + xExprn);
                        log.info("Integr:parseTermIntegr:Divide:aMappedProduct=" + aMappedProduct);
                        if ((chIntegr == '+') || (chIntegr == '-')) {

                        } else if ((chIntegr != -1) && (chIntegr != 0)) {
                            SyncIntegr();
                        }
                    } else {
                        if (posIntegr < StrIntegr.length()) {
                            TermEndPos = posIntegr;
                        } else {
                            TermEndPos = StrIntegr.length();
                        }
                        log.info("Integr:parseTermIntegr:Return Value parseTermDiff:x.IntegrExpr()=" + x.IntegrExpr() + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        if (TermStartPos < TermEndPos) {
                            log.info(" Original Integrand=" + StrIntegr.substring(TermStartPos, TermEndPos));
                        }
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
                } else if (x.IntegrExpr().contains("@ExtremityFactor@")) {
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
                    int bHighLvlMappos = 0;
                    if (eatIntegr('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        if (bStartPos == -1) {
                            bStartPos = posIntegr;
                        }
                        IntegrExpr a = x, b = parseBaseIntegr();
                        int bEndPos = posIntegr;
                        GotExpntNumFlag = false;
                        FactorsExpr = "";
                        FactorsDiffExpr = "";
                        log.info("Integr:parseFactorIntegr:Loop:aStartPos=" + aStartPos + " aEndPos=" + aEndPos);
                        log.info("Integr:parseFactorIntegr:Loop:bStartPos=" + bStartPos + " bEndPos=" + bEndPos);
                        if (bStartPos > bEndPos) {
                            log.info("Integr:parseFactorIntegr:Loop:bExprn(bStartPos)=" + StrIntegr.substring(bStartPos));
                            log.info("Integr:parseFactorIntegr:Loop:bExprn(bEndPos)=" + StrIntegr.substring(bEndPos));
                            bHighLvlMappos = bStartPos;
                            bStartPos = bEndPos;
                        }
                        log.info("Integr:parseFactorIntegr:Loop:b.IntegrExpr()=" + b.IntegrExpr() + " a.IntegrExpr()=" + a.IntegrExpr() + " SavedOperator=" + (char) SavedOperator);
                        log.info("Integr:parseFactorIntegr:Loop:Exprn=" + Exprn);
                        log.info("Integr:parseFactorIntegr:Loop:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                        log.info("Integr:parseFactorIntegr:Loop:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                        //Copied from LADiffConst
                        if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // 2^3
                            //x = (() -> "" + (Math.pow(Double.parseDouble(a.eval()), Double.parseDouble(b.eval()))));
                            log.info("Integr:parseFactorIntegr:2^3:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^3:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if (b.IntegrExpr().contains("@Constant@")) {
                                //(IsAConstant(x.IntegrExpr(),DiffWithRespTo)) 
                                log.info("Integr:parseFactorIntegr:Exponential Constant:Exprn=" + Exprn + " x.IntegrExpr()=" + x.IntegrExpr());
                                Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                log.info("Integr:parseFactorIntegr:Loop:FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " DiffTermOperator=" + (char) DiffTermOperator);
                                String Temp = "";

                                if (DiffTermOperator == -1) {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                } else {
                                    Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) DiffTermOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                }
                                log.info("Integr:parseFactorIntegr:Temp=" + Temp + " FactorsExpr=" + FactorsExpr + " DiffTermOperator=" + (char) DiffTermOperator);
                                final String MyTerm = Temp;
                                x = (() -> MyTerm);
                                Exprn = MyTerm;
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        } else if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // 2^a
                            final String aExprn = StrIntegr.substring(aStartPos, aEndPos);
                            final String bExprn = StrIntegr.substring(bStartPos, bEndPos);
                            x = (() -> aExprn + "^" + bExprn);
                            log.info("Integr:parseFactorIntegr:2^a:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^a:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            if (variables.containsKey(StrIntegr.substring(bStartPos, bEndPos))) {//Is a valid Independent Constant
                                log.info("Integr:parseFactorIntegr:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
//                                if ((SavedOperator == '+') || (SavedOperator == '-')) {
//                                    x = (() -> "0.0");
//                                    Exprn = "0.0";
//                                } else if ((SavedOperator == '*') || (SavedOperator == '/')) {
//                                    Split_Integr(b.IntegrExpr(), DiffWithRespTo);
//                                    final String MyTerm = Exprn + "^" + FactorsExpr;
//                                    x = (() -> MyTerm);
//                                    Exprn = MyTerm;
//                                }
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        } else if ((IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // 2^x
                            final String aExprn = StrIntegr.substring(aStartPos, aEndPos);
                            final String bExprn = StrIntegr.substring(bStartPos, bEndPos);
                            x = (() -> "1.0/" + "ln(" + aExprn + ")" + "*" + aExprn + "^" + bExprn);
                            log.info("Integr:parseFactorIntegr:2^x:aExprn=" + StrIntegr.substring(aStartPos, aEndPos));
                            log.info("Integr:parseFactorIntegr:2^x:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // a^b
                            final String aExprn = StrIntegr.substring(aStartPos, aEndPos);
                            final String bExprn = StrIntegr.substring(bStartPos, bEndPos);
                            x = (() -> aExprn + "^" + bExprn);
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:Independent Variables:Exprn=" + Exprn + " SavedOperator=" + (char) SavedOperator);
                            log.info("Integr:parseFactorIntegr:a^b:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Integr:parseFactorIntegr:a^b:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));

                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))
                                && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                            // x^b
                            //x = (() -> "1");
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffFactorOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffFactorOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:x^b:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                            log.info("Integr:parseFactorIntegr:x^b:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                && (!IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            // a^x
                            final String aExprn1 = StrIntegr.substring(aStartPos, aEndPos);
                            final String bExprn1 = StrIntegr.substring(bStartPos, bEndPos);
                            x = (() -> "1.0/" + "ln(" + aExprn1 + ")" + "*" + aExprn1 + "^" + bExprn1);
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:a^x:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Integr:parseFactorIntegr:a^x:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                && (IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo))) {
                            // a^2
                            final String aExprn1 = StrIntegr.substring(aStartPos, aEndPos);
                            final String bExprn1 = StrIntegr.substring(bStartPos, bEndPos);
                            if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                    && (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) {
                                x = (() -> aExprn1);
                            } else {
                                x = (() -> aExprn1 + "^" + bExprn1);
                            }
                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
                                LADiffIndConstOperator = '*';
                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
                                LADiffIndConstOperator = '/';
                            }
                            log.info("Integr:parseFactorIntegr:a^2:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffIndConstOperator=" + (char) LADiffIndConstOperator);
                            log.info("Integr:parseFactorIntegr:a^2:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        } else if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Var"))
                                || (StrIsAConstant.equalsIgnoreCase("VarExpression")))
                                && ((IsAConstant(StrIntegr.substring(bStartPos, bEndPos), DiffWithRespTo)))
                                || (StrIntegr.substring(bStartPos, bEndPos).equalsIgnoreCase(""))) {
                            // x^2
//                            if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '*')) {
//                                LADiffFactorOperator = '*';
//                            } else if ((aStartPos >= 1) && (StrIntegr.charAt(aStartPos - 1) == '/')) {
//                                LADiffFactorOperator = '/';
//                            }
                            boolean StrIsAConstantVarExprFlag = false;
                            if ((!IsAConstant(StrIntegr.substring(aStartPos, aEndPos), DiffWithRespTo))
                                    && ((StrIsAConstant.equalsIgnoreCase("VarExpression")) || (StrIsAConstant.equalsIgnoreCase("Var")))) {
                                StrIsAConstantVarExprFlag = true;
                            }
                            log.info("Integr:parseFactorIntegr:x^2:aExprn=" + StrIntegr.substring(aStartPos, aEndPos) + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                            log.info("Integr:parseFactorIntegr:x^2:bExprn=" + StrIntegr.substring(bStartPos, bEndPos));
                            log.info("Integr:parseFactorIntegr:x^2:b.IntegrExpr()=" + b.IntegrExpr());
                            String DataConst = "";
                            String[] DataTemp = StrIntegr.substring(aStartPos, aEndPos).split(DiffWithRespTo);
                            if (DataTemp.length == 1) {
                                if (DataTemp[0].charAt(DataTemp[0].length() - 1) == '*') {
                                    DataConst = DataTemp[0].substring(0, DataTemp[0].length() - 1);
                                } else if (DataTemp[0].charAt(DataTemp[0].length() - 1) == '/') {
                                    DataConst = DataTemp[0].substring(0, DataTemp[0].length() - 1);
                                }
                            }
                            log.info("Integr:parseFactorIntegr:x^2:DataConst=" + DataConst);
                            if (b.IntegrExpr().contains("@Constant@")) {
                                log.info("Integr:parseFactorIntegr:Exponential Constant:Exprn=" + Exprn + " x.IntegrExpr()=" + x.IntegrExpr() + " StrIsAConstantVarExprFlag=" + StrIsAConstantVarExprFlag);
                                Split_Integr(b.IntegrExpr(), DiffWithRespTo);
                                log.info("Integr:parseFactorIntegr:Loop:FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " DiffTermOperator=" + (char) DiffTermOperator + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " StrIsAConstantVarExprFlag=" + StrIsAConstantVarExprFlag);
                                String Temp = "";
                                if (StrIsAConstantVarExprFlag == true) {
                                    if ((IsAConstant(DataConst, DiffWithRespTo))
                                            || ((!IsAConstant(DataConst, DiffWithRespTo))
                                            && ((StrIsAConstant.equalsIgnoreCase("Var"))
                                            || (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                            || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))
                                            || (StrIsAConstant.equalsIgnoreCase("Unknown"))))) {
                                        StrIsAConstantVarExprFlag = false;
                                    }
                                    log.info("Integr:parseFactorIntegr:StrIsAConstantVarExprFlag:StrIsAConstantVarExprFlag=" + StrIsAConstantVarExprFlag + " DataConst=" + DataConst + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator + " DataTemp.length=" + DataTemp.length);
                                }
                                if (StrIsAConstantVarExprFlag == true) {
                                    if ((DiffTermOperator == -1) && (LADiffFactorOperator == '*')) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:If IndConstant:LADiffTermOperator:Rule1:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if (LADiffFactorOperator == '*') {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:If IndConstant:LADiffFactorOperator:Rule2:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) > 0)) {
                                        //Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                        log.info("Integr:parseFactorIntegr:If IndConstant:LADiffFactorOperator:Rule3:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) < 0)) {
                                        //Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (-(Double.parseDouble(FactorsExpr) - 1.0));
                                        log.info("Integr:parseFactorIntegr:If IndConstant:LADiffFactorOperator:Rule4:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((DiffTermOperator == -1) && (LADiffFactorOperator == -1)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:If IndConstant:LADiffTermOperator:Rule5:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    }
                                    log.info("Integr:parseFactorIntegr:If IndConstant:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                } else {
                                    if ((DiffTermOperator == -1) && (LADiffFactorOperator == '*')) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule1:LADiffTermOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if (LADiffFactorOperator == '*') {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                            log.info("Integr:parseFactorIntegr:Else:Rule2.1:DataConst=" + DataConst + " Temp=" + Temp + " x.IntegrExpr()=" + x.IntegrExpr() + " b.IntegrExpr()=" + b.IntegrExpr() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                        } else if (IsAConstant(x.IntegrExpr(), DiffWithRespTo)) {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                            log.info("Integr:parseFactorIntegr:Else:Rule2.2:DataConst=" + DataConst + " Temp=" + Temp + " x.IntegrExpr()=" + x.IntegrExpr() + " b.IntegrExpr()=" + b.IntegrExpr() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                        } else {
                                            Temp = "/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                            log.info("Integr:parseFactorIntegr:Else:Rule2.3:DataConst=" + DataConst + " Temp=" + Temp + " x.IntegrExpr()=" + x.IntegrExpr() + " b.IntegrExpr()=" + b.IntegrExpr() + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule2:LADiffFactorOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) > 0)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule3:LADiffFactorOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((LADiffFactorOperator == '/') && (Double.parseDouble(FactorsExpr) < 0)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (-(Double.parseDouble(FactorsExpr) - 1.0));
                                        } else {
                                            Temp = DataConst + "/" + (-Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (-(Double.parseDouble(FactorsExpr) - 1.0));
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule4:LADiffFactorOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((DiffTermOperator == -1) && (LADiffFactorOperator == -1)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule5:LADiffTermOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((DiffTermOperator == '*') && (LADiffFactorOperator == -1)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (Double.parseDouble(FactorsExpr) + 1.0) + "*" + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule6:LADiffTermOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    } else if ((DiffTermOperator == '/') && (LADiffFactorOperator == -1)) {
                                        if (DataConst.equalsIgnoreCase("")) {
                                            Temp = "1.0/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) DiffTermOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                        } else {
                                            Temp = DataConst + "/" + (-Double.parseDouble(FactorsExpr) + 1.0) + (char) DiffTermOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                        }
                                        log.info("Integr:parseFactorIntegr:Else:Rule6:LADiffTermOperator:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                    }
                                    log.info("Integr:parseFactorIntegr:Else:Temp=" + Temp + " LADiffFactorOperator=" + (char) LADiffFactorOperator + " DiffTermOperator=" + (char) DiffTermOperator);
                                }
                                //else if (LADiffTermOperator=='/') Temp="1.0/" + (Double.parseDouble(FactorsExpr) - 1.0) + (char)LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) - 1.0);
                                //else Temp="1.0/" + (Double.parseDouble(FactorsExpr) + 1.0) + (char)LADiffFactorOperator + DiffWithRespTo + "^" + (Double.parseDouble(FactorsExpr) + 1.0);
                                log.info("Integr:parseFactorIntegr:Temp=" + Temp + " FactorsExpr=" + FactorsExpr + " DiffTermOperator=" + (char) DiffTermOperator + " LADiffFactorOperator=" + (char) LADiffFactorOperator);
                                final String MyTerm = Temp;
                                x = (() -> MyTerm);
                                Exprn = "" + MyTerm;
                            }
                            if (bStartPos == bEndPos) {
                                log.info("Integr:parseFactorIntegr:Mapping Loop:StrIntegr=" + StrIntegr.substring(posIntegr));
                                posIntegr = bHighLvlMappos;
                                while ((IsNumber(StrIntegr.charAt(posIntegr))) || (IsVariable(StrIntegr.charAt(posIntegr)))) {
                                    nextCharIntegr();
                                }
                                log.info("Integr:parseFactorIntegr:Mapping Loop:StrIntegr=" + StrIntegr.substring(posIntegr));
                            }
                            log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                            log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);
                        }
                        //FactorsExpr = "";
                        //FactorsDiffExpr = "";
                        log.info("Integr:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " DiffTermOperator=" + (char) DiffTermOperator);
                        log.info("Integr:parseFactorIntegr:Exprn=" + Exprn);

                    } else {
                        if (posIntegr < StrIntegr.length()) {
                            FactorEndPos = posIntegr;
                        } else {
                            FactorEndPos = StrIntegr.length();
                        }
                        if (ParanExprInProgressFlag == true) {
                            if (FactorStartPos < FactorEndPos) {
                                log.info("Integr:parseFactorIntegr:ParanExprInProgressFlag:Return Value parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(FactorStartPos, FactorEndPos));
                            }
                            log.info("Integr:parseFactorIntegr:ParanExprInProgressFlag:Return Value parseFactorIntegr:Exprn=" + Exprn);
                            final String ParaOutput = Exprn;
                            IntegrExpr y = (() -> ParaOutput);
                            return y;
                        } else {
                            if (FactorStartPos < FactorEndPos) {
                                log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(FactorStartPos, FactorEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            }
                            log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:Exprn=" + Exprn);
                            if ((FactorStartPos < FactorEndPos) && (StrIntegr.substring(FactorStartPos, FactorEndPos).contains("*" + DiffWithRespTo))
                                    && (!StrIntegr.substring(FactorStartPos, FactorEndPos).contains(DiffWithRespTo + "^"))
                                    && (!StrIntegr.substring(FactorStartPos, FactorEndPos).equalsIgnoreCase(DiffWithRespTo))) {
                                final String ParaOutput = x.IntegrExpr();
                                log.info("Integr:parseFactorIntegr:Normal:RuleA:ParaOutput=" + ParaOutput);
                                //2.0/2.0*x^2.0
                                if ((ParaOutput.equalsIgnoreCase("1.0/2.0"))) {
                                    x = (() -> DiffWithRespTo + "^2.0");
                                    log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                } else if ((!ParaOutput.contains("@Parenthesis@")) && (!ParaOutput.equalsIgnoreCase("2.0")) && (chIntegr != -1)) {
                                    x = (() -> ParaOutput + "/2.0*" + DiffWithRespTo + "^2.0");
                                    log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 2:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                } else if ((!ParaOutput.contains("@Parenthesis@")) && (!ParaOutput.equalsIgnoreCase("2.0")) && (chIntegr == -1)) {
                                    if ((Exprn.equalsIgnoreCase(ParaOutput)) && (IsAConstant(Exprn, DiffWithRespTo))) {
                                        final String MyExprn = Exprn;
                                        x = (() -> MyExprn + "/2.0*" + DiffWithRespTo + "^2.0");
                                        log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 3.1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                    } else if ((Exprn.equalsIgnoreCase(ParaOutput)) && (!IsAConstant(Exprn, DiffWithRespTo))) {
                                        final String MyExprn = ParaOutput;
                                        x = (() -> MyExprn + "/2.0*" + DiffWithRespTo + "^2.0");
                                        log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 3.1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                    } else {
                                        x = (() -> "1.0/2.0*" + DiffWithRespTo + "^2.0");
                                        log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 3.2:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                    }
                                    log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 3:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                } else if ((!ParaOutput.contains("@Parenthesis@"))) {
                                    x = (() -> DiffWithRespTo + "^2.0");
                                    log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 4:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                } else {
                                    x = (() -> ParaOutput);
                                    log.info("Integr:parseFactorIntegr:Normal:RuleA:Rule 5:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                }
                                log.info("Integr:parseFactorIntegr:Normal:Rule A :parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            } else if ((FactorStartPos < FactorEndPos) && (StrIntegr.substring(FactorStartPos, FactorEndPos).contains("/" + DiffWithRespTo))
                                    && (!StrIntegr.substring(FactorStartPos, FactorEndPos).contains(DiffWithRespTo + "^"))
                                    && (!StrIntegr.substring(FactorStartPos, FactorEndPos).equalsIgnoreCase(DiffWithRespTo))) {
                                final String ParaOutput = x.IntegrExpr();
                                if (!ParaOutput.equalsIgnoreCase("1.0")) {
                                    x = (() -> ParaOutput + "*" + "ln(" + DiffWithRespTo + ")");
                                    log.info("Integr:parseFactorIntegr:Normal:Rule B 1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                } else {
                                    x = (() -> "ln(" + DiffWithRespTo + ")");
                                    log.info("Integr:parseFactorIntegr:Normal:Rule B 2:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                                }
                                log.info("Integr:parseFactorIntegr:Normal:Rule B :parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " ParaOutput=" + ParaOutput + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            } else if ((IsAConstant(FactorsExpr, DiffWithRespTo)) && (IsAConstant(Exprn, DiffWithRespTo))) {
                                final String MyExprn = x.IntegrExpr();
                                x = (() -> FactorsExpr);
                                Exprn = FactorsExpr;
                                log.info("Integr:parseFactorIntegr:Normal:Rule C 1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Exprn=" + Exprn + " FactorsExpr=" + FactorsExpr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            } else if ((IsAConstant(FactorsExpr, DiffWithRespTo)) && (!IsAConstant(Exprn, DiffWithRespTo)) && (!Exprn.startsWith("/")) && (!Exprn.startsWith("*"))) {
                                final String MyExprn = x.IntegrExpr();
                                x = (() -> MyExprn);
                                Exprn = MyExprn;
                                log.info("Integr:parseFactorIntegr:Normal:Rule D 1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Exprn=" + Exprn + " FactorsExpr=" + FactorsExpr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            } else if ((IsAConstant(FactorsExpr, DiffWithRespTo)) && (!IsAConstant(Exprn, DiffWithRespTo)) && ((Exprn.startsWith("/")) || (Exprn.startsWith("*")))) {
                                final String MyExprn = x.IntegrExpr();
                                x = (() -> FactorsExpr + MyExprn);
                                Exprn = FactorsExpr + MyExprn;
                                log.info("Integr:parseFactorIntegr:Normal:Rule E 1:parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Exprn=" + Exprn + " FactorsExpr=" + FactorsExpr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            }
                            if (FactorStartPos < FactorEndPos) {
                                log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:x.IntegrExpr()=" + x.IntegrExpr() + " Original Integrand=" + StrIntegr.substring(FactorStartPos, FactorEndPos) + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                            }
                            log.info("Integr:parseFactorIntegr:Normal:Return Value parseFactorIntegr:Exprn=" + Exprn);
                            return x;
                        }

                    }
                }
            }

            IntegrExpr parseBaseIntegr() {
                boolean ConfirmedConstFlag = false;
                int startPos1 = posIntegr;
                IntegrExpr x = (() -> "");
                log.info("Integr:parseBaseIntegr:StrIntegr=" + StrIntegr + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                if (eatIntegr('-')) { // unary minus
                    IntegrExpr b = parseBaseIntegr();
                    //x = () -> "-" + xx;
                    x = (() -> "-" + b.IntegrExpr());
                    return x;
                } else if (eatIntegr('+')) { // unary plus
                    //x = parseBaseIntegr();
                    //return x;
                }
                log.info("Integr:parseBaseIntegr:Before Parenthesis: chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr + " StrIntegr.substring(posIntegr, StrIntegr.length())=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                if (IsNumber(chIntegr)) { //numbers
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
                    log.info("Integr:parseBaseIntegr:Numbers: xx=" + xx + " DiffTermOperator=" + (char) DiffTermOperator + " GotExpntNumFlag=" + GotExpntNumFlag + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " StrIntegr(posIntegr)=" + StrIntegr.substring(posIntegr));
                    if (GotExpntNumFlag == false) {
                        LookAheadDiffConst MyCoeff;
                        int DiffIntLength = xx.length();
                        log.info("Integr:parseBaseIntegr:Entered LookAheadDiffConst Number:xConst=" + xConst + " xx=" + xx + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
                        MyCoeff = parseLADiff(StrIntegr.substring(startPos1), startPos, DiffIntLength, "Integration", DiffWithRespTo);
                        xConst = "" + MyCoeff.eval();
                        if (IsAConstant(xConst, DiffWithRespTo)) {
                            ConfirmedConstFlag = true;
                        }
                    } else {
                        xConst = xx;
                        ConfirmedConstFlag = true;
                        LastLADiffConstCoeffAidPos = -1;
                        ParseLog("Integr", "parseBaseIntegr", "BeforeMap", "StrIntegr", StrIntegr);
                        ParseMap("LookAheadDiffConst:parseLADiff:Integration", StrIntegr.length(), 0, 0, DiffWithRespTo);
                        ParseLog("Integr", "parseBaseIntegr", "Final", "StrIntegr", StrIntegr);
                    }
                    log.info("Integr:parseBaseIntegr:NumberORConstant:xConst=" + xConst + " DiffTermOperator=" + (char) DiffTermOperator + " GotExpntNumFlag=" + GotExpntNumFlag + " ConfirmedConstFlag=" + ConfirmedConstFlag + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
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
                    if ((startPos1 >= 0) && (posIntegr >= 0) && (startPos1 < StrIntegr.length())) {
                        name = StrIntegr.substring(startPos1, posIntegr).trim();
                    }
                    if (name.equalsIgnoreCase("")) {
                        log.fatal("Integr:parseBaseIntegr:name null:Name=" + name);
                    }
                    log.info("Integr:parseBaseIntegr:Before Function StrIntegr.substring(posIntegr,StrIntegr.length())=" + StrIntegr.substring(posIntegr, StrIntegr.length()) + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr);
                    //Code to handle Function
                    if (functionsIntegr.containsKey(name)) {
                        //String func = functionsDiff.get(name);
                        log.info("Integr:parseBaseIntegr:Function Name=" + name);
                        String func = name;
                        FuncName = name;
                        FuncArgFlag = true;
                        ParanExprFlag = false;
                        ParenthesisFlag = true;
                        int argstartpos = posIntegr;
                        //IntegrExpr arg = parseParaParaIntegr();
                        ParseLogDiffrIntegr("Integr:", "parseBaseIntegr", "Initial", "StrIntegr", StrIntegr);
                        MathsContxtLAv.LookAheadParen MyFuncArg = MathsContxtLAv.parseParen(StrIntegr.substring(posIntegr, StrIntegr.length()), 0, DiffWithRespTo, true);
                        Map<String, String> variablesBackupState = new HashMap();
                        BackupDiffIntegrStateAll(variablesBackupState);
                        IntegrExpr arg = parseIntegr(MyFuncArg.eval(), variables, DiffWithRespTo);
                        int argEndpos = -1;
                        if (argstartpos > -1) {
                            argEndpos = argstartpos + posIntegr + posParen;
                        } else {
                            argEndpos = posIntegr + posParen;
                        }
                        RestoreDiffIntegrStateAll(variablesBackupState);
                        posIntegr = argEndpos - 1;
                        if (posIntegr < StrIntegr.length()) {
                            chIntegr = StrIntegr.charAt(posIntegr);
                        } else {
                            posIntegr = StrIntegr.length();
                            chIntegr = -1;
                            argEndpos = posIntegr;
                        }
                        ParseLogDiffrIntegr("Integr:", "parseBaseIntegr", "Final", "StrIntegr", StrIntegr);
                        FuncArgFlag = false;
                        ParanExprFlag = false;
                        ParenthesisFlag = false;
                        eatIntegr(')');
                        log.info("Integr:parseBaseIntegr:Function Argument:arg.IntegrExpr()=" + arg.IntegrExpr());
                        ParanExprInProgressFlag = false;
                        log.info("Integr:parseBaseIntegr:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " StrIntegr.length()=" + StrIntegr.length());
                        String Factor = "(" + MyFuncArg.eval() + ")";
                        if (argstartpos <= argEndpos) {
                            log.info("Integr:parseBaseIntegr:Function Argument:Factor=" + Factor + " String=" + StrIntegr.substring(argstartpos, argEndpos));
                        }
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
                                if ((startPos1 >= 1) && (startPos1 < StrIntegr.length()) && (StrIntegr.charAt(startPos1 - 1) == '-')) {
                                    name = "-" + name;
                                }
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
                                } else if ((FunctionDiffExpr != null) && (!FunctionDiffExpr.equalsIgnoreCase("")) && (!FunctionDiffExpr.contains("+")) && (!FunctionDiffExpr.contains("-"))) {
                                    final String name2 = name;
                                    x = (() -> (name2 + "@" + "Function" + "@" + "Factor" + "@" + (Factor) + "@" + FunctionDiffExpr));
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
                        ContainsXFlag = true;
                        log.info("Integr:parseBaseIntegr:Polynomial:posIntegr=" + posIntegr + " ContainsXFlag=" + ContainsXFlag);
                        final String name5 = name;
                        x = () -> (name5 + "@" + "Polynomial" + "@" + (name5));
                    } else if ((name.equalsIgnoreCase(DiffWithRespTo)) && (!name.contains("^"))) {
                        ContainsXFlag = true;
                        log.info("Integr:parseBaseIntegr:Factor:posIntegr=" + posIntegr);
                        final String name6 = name;
                        x = () -> (name6 + "@" + "Factor" + "@" + (name6));
                    } else if ((!name.equalsIgnoreCase(DiffWithRespTo)) && (!name.equalsIgnoreCase("Infinity"))) {//Independent Variable
                        log.info("Integr:parseBaseIntegr:IndependentFactor:posIntegr=" + posIntegr);
                        final String name7 = name;
                        x = () -> (name7 + "@" + "IndependentFactor" + "@" + (name7));
                    } else if ((!name.equalsIgnoreCase(DiffWithRespTo)) && ((name.equalsIgnoreCase("Infinity")) || (name.equalsIgnoreCase("NaN")))) {//Extremity Variable
                        log.info("Integr:parseBaseIntegr:ExtremityFactor:posDiff=" + posDiff);
                        final String name4 = name;
                        x = (() -> (name4 + "@" + "ExtremityFactor" + "@" + (name4)));
                    }
                    log.info("Integr:parseBaseIntegr:Variable/Function:name=" + name + " x.IntegrExpr()=" + x.IntegrExpr());
                    return x;
                } else if (chIntegr == '(') {
                    int argstartpos = posIntegr;
                    int argEndpos = -1;
                    if (chIntegr == '(') {
                        eatIntegr('(');
                    } else {
                        while ((chIntegr > -1) && (chIntegr != '(')) {
                            nextCharIntegr();
                        }
                        eatIntegr('(');
                    }
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posIntegr;
                    while ((posIntegr < StrIntegr.length()) && ((chIntegr != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharIntegr();
                        if (chIntegr == '(') {
                            openBracesCount++;
                        }
                        if (chIntegr == ')') {
                            closeBracesCount++;
                        }
                    }
                    eatIntegr(')');
                    String Exprn = StrIntegr.substring(startcount, posIntegr - 1);
                    log.info("Integr:parseBaseIntegr:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chIntegr=" + (char) chIntegr);
                    log.info("Integr:parseBaseIntegr:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chIntegr=" + (char) chIntegr);
                    ParseLogDiffrIntegr("Integr:", "parseBaseIntegr", "Initial", "StrIntegr", StrIntegr);
                    IntegrExpr arg = (() -> "");
                    log.info("Integr:parseBaseIntegr:Entered Functions & Variable:Arg:Exprn=" + Exprn + " chIntegr=" + (char) chIntegr);
                    if (Exprn.contains(DiffWithRespTo)) {
                        Map<String, String> variablesBackupState = new HashMap();
                        BackupDiffIntegrStateAll(variablesBackupState);
                        MathsContxtLAv.ChaosPrevention();
                        InitState();
                        arg = parseIntegr(Exprn, variables, DiffWithRespTo);
                        argEndpos = posIntegr;
                        RestoreDiffIntegrStateAll(variablesBackupState);
                        if ((posIntegr > -1) && (posIntegr < StrIntegr.length())) {
                            chIntegr = StrIntegr.charAt(posIntegr);
                        } else {
                            posIntegr = StrIntegr.length();
                            chIntegr = -1;
                            argEndpos = posIntegr;
                        }
                        log.info("Integr:parseBaseIntegr:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.IntegrExpr()=" + arg.IntegrExpr() + " chIntegr=" + (char) chIntegr);
                        ParseLogDiffrIntegr("Integr:", "parseBaseIntegr", "Final", "StrIntegr", StrIntegr);
                    } else {
                        log.info("Integr:parseBaseIntegr:Entered Functions & Variable:Got DiffWithRespTo Dependency:arg.IntegrExpr()=" + arg.IntegrExpr() + " chIntegr=" + (char) chIntegr);
                    }
                    ParanExprFlag = false;
                    ParenthesisFlag = false;
                    log.info("Integr:parseBaseIntegr:Function Argument:arg.IntegrExpr()=" + arg.IntegrExpr());
                    ParanExprInProgressFlag = false;
                    log.info("Integr:parseBaseIntegr:Function Argument:argstartpos=" + argstartpos + " argEndpos=" + argEndpos + " Str.length()=" + StrIntegr.length());
                    //(name + "@" + "Parenthesis" + "@" + ParanDiffExpr) )
                    final String ParaDiff = arg.IntegrExpr();
                    if (IsAConstant(ParaDiff, DiffWithRespTo)) {
                        x = (() -> Exprn + "@" + "Parenthesis" + "@" + ParaDiff);
                        log.info("Integr:parseBaseIntegr:Function Argument:x.IntegrExpr()=" + x.IntegrExpr());
                    } else {
                        x = (() -> Exprn + "@" + "Parenthesis" + "@" + "(" + ParaDiff + ")");
                        log.info("Integr:parseBaseIntegr:Function Argument:x.IntegrExpr()=" + x.IntegrExpr());
                    }
                    return x;
                } else {
                    if ((IsNotAnOperator(chIntegr) && (IsNotANumber(chIntegr))) && (chIntegr != 0) && (chIntegr != -1) && (chIntegr != '(')) {
                        if ((chIntegr != -1) && (chIntegr != 0) && (posIntegr >= 1) && (posIntegr < StrIntegr.length()) && (IsNumber(chIntegr))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Integr:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                        } else if ((chIntegr != -1) && (chIntegr != 0) && (chIntegr != '^') && (posIntegr >= 1) && (posIntegr < StrIntegr.length()) && (IsOperator(chIntegr))) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Integr:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                        } else if ((chIntegr != -1) && (chIntegr != 0) && (chIntegr != '^') && (posIntegr >= 1) && (posIntegr < StrIntegr.length())) {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Integr:parseBase:Unexpected: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                        } else {
                            throw new RuntimeException(StageMapping + ":" + TestCase + ":Order=" + Order + "Integr:parseBase:Unexpected char: " + (char) chIntegr + " int=" + (int) chIntegr + " posIntegr=" + posIntegr + " StrIntegr=" + StrIntegr.substring(posIntegr, StrIntegr.length()));
                        }
                    }
                }
                return x;
            }
        }.parseIntegrExpr();
    }

    static void Split(String MyString, String DiffWithRespTo) {
        log.info("Diff:Split:Initial LookAhead Stack: Start Context:MyString=" + MyString
                + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
        int Operator = 0;
        String Temp = "";
        String[] Factors = MyString.split("@");

        if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Polynomial"))) {
            FactorsExpr = Factors[2];
            FactorsDiffExpr = Factors[0];
            log.info("Diff:Split:Polynomial: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Factor"))) {
            //FactorsExpr = Factors[0];
            FactorsExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
            //FactorsDiffExpr = Factors[2];
            //Diff of x-> "1"
            FactorsDiffExpr = "1.0/2.0*" + DiffWithRespTo + "^2.0";
            log.info("Diff:Split:Factor: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
            log.info("Diff:Split:Generic Constant: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("IndependentFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
            log.info("Diff:Split:IndependentFactor: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("ExtremityFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
            log.info("Diff:Split:ExtremityFactor: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
            log.info("Diff:Split:Unknown: Start Context: FactorsExpr=" + FactorsExpr + " FactorsDiffExpr=" + FactorsDiffExpr + " Factors[0]=" + Factors[0] + " Factors[2]=" + Factors[2]);
        }
        log.info("Diff:Split:Final:FactorsExpr=" + FactorsExpr);
        log.info("Diff:Split:Final:ParanExpr=" + ParanExpr);
        log.info("Diff:Split:Final:ParanDiffExpr=" + ParanDiffExpr);
        log.info("Diff:Split:Final:FactorsDiffExpr=" + FactorsDiffExpr);
    }

    static void Expr_Func_Split(String MyString, String DiffWithRespTo) {
        log.info("Diff:Expr_Func_Split:Initial LookAhead Stack: Start Context:MyString=" + MyString + " posDiff=" + posDiff + " chDiff=" + (char) chDiff);
        String[] Factors = MyString.split("@");
        String StrSignFactor0 = "";
        int Operator = 0;
        String Temp = "";
        log.info("Diff:Expr_Func_Split:@Input@:split:MyString=" + MyString);
        log.info("Diff:Expr_Func_Split:@Input@:split:Factors.length=" + Factors.length);
        if ((Factors.length == 6) && (Factors[1].equalsIgnoreCase("FunctionArgument"))) {
            //(name + "@" + "FunctionArgument" + "@" + "Factor" + "@" + name + "@" + "FactorDiff" + "@" +arg.evalDiffExpr()));
            String MyFactor = Factors[0];
            String MyFactorDiff = Factors[5];
            FactorsExpr = MyFactor;
            FactorsDiffExpr = MyFactorDiff;
            FunctionDiffExpr = MyFactorDiff;
        } else if ((Factors.length == 5) && (Factors[1].equalsIgnoreCase("Function"))) {
            log.info("Diff:Expr_Func_Split:Function:Factors.length=" + Factors.length);
            //name + "@" + "Function" + "@" + "Factor" + "@" + FactorsExpr + "@" + FunctionDiffExpr));
            //sin@Function@Factor@(2.0*x)@cos(2.0*x)*2.0
            FactorsExpr = Factors[3];
            StrSignFactor0 = Factors[0];
            if (Factors[0].startsWith("+")) {
                Factors[0] = Factors[0].replaceFirst("+", "");
            } else if (Factors[0].startsWith("-")) {
                Factors[0] = Factors[0].replaceFirst("-", "");
            }
            FunctionName = Factors[0];
            FunctionExpr = functionsDiff.get(Factors[0]) + "(" + Factors[3] + ")";
            FactorsDiffExpr = Factors[4];
            log.info("Diff:Expr_Func_Split:Function:FactorsExpr=" + FactorsExpr);
            log.info("Diff:Expr_Func_Split:Function:FactorsDiffExpr=" + FactorsDiffExpr);
            String[] StrMyTemp = new String[4];
            if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.contains("\\(")) && (!FactorsDiffExpr.contains("\\)"))) {
                FunctionDiffExpr = FactorsDiffExpr + "*" + FunctionExpr;
                FunctionName = Factors[0];
                log.info("Diff:Expr_Func_Split:Final:FactorsExpr=" + FactorsExpr);
                log.info("Diff:Expr_Func_Split:Final:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:Final:ParanExpr=" + ParanExpr);
                log.info("Diff:Expr_Func_Split:Final:ParanDiffExpr=" + ParanDiffExpr);
                log.info("Diff:Expr_Func_Split:Final:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:Final:FunctionDiffExpr=" + FunctionDiffExpr);
                log.info("Diff:Expr_Func_Split:Final:FunctionName=" + FunctionName);
                return;
            }
            if ((StrMyTemp.length >= 2) && (StrMyTemp[1] != null) && (!StrMyTemp[1].contains("\\(")) && (!StrMyTemp[1].contains("\\)"))) {
                FactorsDiffExpr = StrMyTemp[1];
            }
            if (IsAConstant(FactorsDiffExpr, DiffWithRespTo)) {
                if (FactorsDiffExpr.equalsIgnoreCase("1.0")) {
                    FunctionDiffExpr = FunctionExpr;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                } else if ((FactorsDiffExpr.equalsIgnoreCase("-1.0")) && (FunctionExpr.startsWith("-")) && (StrSignFactor0.startsWith("-"))) {
                    FunctionDiffExpr = FunctionExpr;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                } else if ((FactorsDiffExpr.equalsIgnoreCase("-1.0")) && (FunctionExpr.startsWith("-"))) {
                    FunctionDiffExpr = FunctionExpr.replaceFirst("-", "");
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                } else if (FactorsDiffExpr.equalsIgnoreCase("-1.0")) {
                    FunctionDiffExpr = "-" + FunctionExpr;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                } else if ((!FactorsDiffExpr.equalsIgnoreCase("-1.0")) && (!FactorsDiffExpr.equalsIgnoreCase("0.0"))) {
                    FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                } else if (FactorsDiffExpr.equalsIgnoreCase("0.0")) {
                    FunctionDiffExpr = FunctionExpr;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr + " FunctionExpr=" + FunctionExpr);
                }
                if (FunctionDiffExpr.startsWith("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replaceFirst("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:SignFlip-:FactorsDiffExpr=" + FactorsDiffExpr);
                    log.info("Diff:Expr_Func_Split:@Function@:else:SignFlip-:FunctionExpr=" + FunctionExpr);
                    log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:SignFlip-:FunctionDiffExpr=" + FunctionDiffExpr);
                }
                log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr);
            } else if ((!IsAConstant(FactorsDiffExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                if (FactorsDiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                    FunctionDiffExpr = FunctionExpr;
                } else if (FactorsDiffExpr.equalsIgnoreCase("-" + DiffWithRespTo)) {
                    FunctionExpr = functionsIntegr.get(Factors[0]) + Factors[3];;
                    FunctionDiffExpr = FunctionExpr;
                } else if (FactorsDiffExpr.equalsIgnoreCase("+" + DiffWithRespTo)) {
                    FunctionDiffExpr = FunctionExpr;
                }
                if ((FunctionDiffExpr.startsWith("-")) && (FactorsDiffExpr.startsWith("-"))) {
                    Operator = '+';
                    Temp = FunctionDiffExpr.replaceFirst("-", "");
                    FunctionDiffExpr = Temp;
                    log.info("Diff:Expr_Func_Split:@Function@:if:FunctionDiffExpr=" + FunctionDiffExpr);
                } else if ((FunctionDiffExpr.startsWith("-")) && (!FactorsDiffExpr.startsWith("-"))) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replaceFirst("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                    log.info("Diff:Expr_Func_Split:@Function@:else if:FunctionDiffExpr=" + FunctionDiffExpr);
                }
                log.info("Diff:Expr_Func_Split:@Function@:IsAVar OR VarExpression:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:IsAVar OR VarExpression:FunctionDiffExpr=" + FunctionDiffExpr);
            } else {
                log.info("Diff:Expr_Func_Split:@Function@:else:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
                FunctionDiffExpr = FactorsDiffExpr + "*" + FunctionExpr;
//                if ((!FactorsDiffExpr.contains("+")) && (!FactorsDiffExpr.contains("+"))) {
//                    FunctionDiffExpr = FactorsDiffExpr + "*" + FunctionExpr;
//                    log.info("Diff:Expr_Func_Split:@Function@:else:FactorsDiffExpr=" + FactorsDiffExpr);
//                    log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
//                    log.info("Diff:Expr_Func_Split:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
//                } else {
//                    FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
//                    log.info("Diff:Expr_Func_Split:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
//                }
                log.info("Diff:Expr_Func_Split:@Function@:else:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Diff:Expr_Func_Split:@Function@:else:FunctionDiffExpr=" + FunctionDiffExpr);
            }
        } else if ((Factors.length == 7) && (Factors[1].equalsIgnoreCase("Function"))) {
            //MyName + "@" + "Function" + "@" + "Factor" + "@" + (Factor)  + "@" + 0.0 + "@" + "ConstantExprn" + "@" +MyName+"("+Exprn + ")");
            FactorsExpr = Factors[0];
            ParanExpr = Factors[0];
            ParanDiffExpr = Factors[2];
            FunctionDiffExpr = Factors[6];
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:FactorsExpr=" + FactorsExpr);
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:ParanExpr=" + ParanExpr);
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:ParanDiffExpr=" + ParanDiffExpr);
            log.info("Diff:Expr_Func_Split:@Parenthesis@:else:FunctionDiffExpr=" + FunctionDiffExpr);
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Parenthesis"))) {
            //(name + "@" + "Parenthesis" + "@" + ParanDiffExpr) );
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
        log.info("Diff:Expr_Func_Split:Final:FactorsExpr=" + FactorsExpr);
        log.info("Diff:Expr_Func_Split:Final:ParanExpr=" + ParanExpr);
        log.info("Diff:Expr_Func_Split:Final:ParanDiffExpr=" + ParanDiffExpr);
        log.info("Diff:Expr_Func_Split:Final:FactorsDiffExpr=" + FactorsDiffExpr);
        log.info("Diff:Expr_Func_Split:Final:FunctionDiffExpr=" + FunctionDiffExpr);
        log.info("Diff:Expr_Func_Split:Final:FunctionName=" + FunctionName);
    }

    static void Split_Integr(String MyString, String DiffWithRespTo) {
        log.info("Integr:Split:Initial LookAhead Stack: Start Context:MyString=" + MyString + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
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
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Constant"))) {
            log.info("Integr:Split_Integr:Generic Constant: Start Context:posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("IndependentFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("ExtremityFactor"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        } else if ((Factors.length == 3) && (Factors[1].equalsIgnoreCase("Unknown"))) {
            FactorsExpr = Factors[0];
            FactorsDiffExpr = Factors[2];
        }
        log.info("Integr:Split_Integr:Final:FactorsExpr=" + FactorsExpr);
        log.info("Integr:Split_Integr:Final:ParanExpr=" + ParanExpr);
        log.info("Integr:Split_Integr:Final:ParanDiffExpr=" + ParanDiffExpr);
        log.info("Integr:Split_Integr:Final:FactorsDiffExpr=" + FactorsDiffExpr);
    }

    static void Expr_Func_Split_Integr(String MyString, String DiffWithRespTo) {
        log.info("Integr:Expr_Func_Split_Integr:Initial LookAhead Stack: Start Context:MyString=" + MyString + " posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr);
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
            if (Factors[0].startsWith("+")) {
                Factors[0] = Factors[0].replaceFirst("+", "");
            } else if (Factors[0].startsWith("-")) {
                Factors[0] = Factors[0].replaceFirst("-", "");
            }
            FunctionExpr = functionsIntegr.get(Factors[0]) + Factors[3];
            String[] StrMyTemp = new String[4];
            if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.contains("\\(")) && (!FactorsDiffExpr.contains("\\)"))) {
                StrMyTemp = FactorsDiffExpr.split("[\\(\\)]");
                log.info("Integr:Expr_Func_Split_Integr:@Function@:split:FactorsDiffExpr=" + FactorsDiffExpr + " StrMyTemp.length=" + StrMyTemp.length);
            }
            if ((StrMyTemp.length >= 2) && (StrMyTemp[1] != null) && (!StrMyTemp[1].contains("\\(")) && (!StrMyTemp[1].contains("\\)"))) {
                FactorsDiffExpr = StrMyTemp[1];
            }
            if (IsAConstant(FactorsDiffExpr, DiffWithRespTo)) {
                if ((FactorsDiffExpr.contains("+")) || (FactorsDiffExpr.contains("-"))) {
                    FunctionDiffExpr = "(" + FactorsDiffExpr + ")" + "*" + FunctionExpr;
                } else {
                    FunctionDiffExpr = FactorsDiffExpr + "*" + FunctionExpr;
                }
                if (FunctionDiffExpr.contains("-")) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replace("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAConstant:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAConstant:FunctionDiffExpr=" + FunctionDiffExpr);
            } else if ((!IsAConstant(FactorsDiffExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                if (FactorsDiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                    FunctionDiffExpr = FunctionExpr;
                } else if (FactorsDiffExpr.equalsIgnoreCase("-" + DiffWithRespTo)) {
                    FunctionExpr = functionsIntegr.get(Factors[0]) + Factors[3];;
                    FunctionDiffExpr = FunctionExpr;
                } else if (FactorsDiffExpr.equalsIgnoreCase("+" + DiffWithRespTo)) {
                    FunctionDiffExpr = FunctionExpr;
                }
                if ((FunctionDiffExpr.startsWith("-")) && (FactorsDiffExpr.startsWith("-"))) {
                    Operator = '+';
                    Temp = FunctionDiffExpr.replaceFirst("-", "");
                    FunctionDiffExpr = Temp;
                } else if ((FunctionDiffExpr.startsWith("-")) && (!FactorsDiffExpr.startsWith("-"))) {
                    Operator = '-';
                    Temp = FunctionDiffExpr.replaceFirst("-", "");
                    FunctionDiffExpr = (char) Operator + Temp;
                }
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAVar OR VarExpression:FactorsDiffExpr=" + FactorsDiffExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:else:FunctionExpr=" + FunctionExpr);
                log.info("Integr:Expr_Func_Split_Integr:@Function@:IsAVar OR VarExpression:FunctionDiffExpr=" + FunctionDiffExpr);
            } else {
                if ((FactorsDiffExpr != null) && (!FactorsDiffExpr.equalsIgnoreCase("")) && (!FactorsDiffExpr.equalsIgnoreCase(DiffWithRespTo))) {
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
            //(name + "@" + "Parenthesis" + "@" + ParanDiffExpr) );
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
        log.info("Integr:Expr_Func_Split_Integr:Final:FactorsExpr=" + FactorsExpr);
        log.info("Integr:Expr_Func_Split_Integr:Final:ParanExpr=" + ParanExpr);
        log.info("Integr:Expr_Func_Split_Integr:Final:ParanDiffExpr=" + ParanDiffExpr);
        log.info("Integr:Expr_Func_Split_Integr:Final:FactorsDiffExpr=" + FactorsDiffExpr);
    }

    public static DiffExpr ApplyLambdaRules(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        log.info("Diff:ApplyLambdaRules:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Diff:ApplyLambdaRules:Initial :Term1=" + Term1);
        log.info("Diff:ApplyLambdaRules:Initial :Diff1.DiffExpr()=" + Diff1.DiffExpr());
        log.info("Diff:ApplyLambdaRules:Initial :Term2=" + Term2);
        log.info("Diff:ApplyLambdaRules:Initial :Diff2.DiffExpr()=" + Diff2.DiffExpr());
        log.info("Diff:ApplyLambdaRules:Initial :Term3=" + Term3);
        log.info("Diff:ApplyLambdaRules:Initial :Term4=" + Term4);
        log.info("Diff:ApplyLambdaRules:Initial :StrOperator=" + StrOperator);
        log.info("Diff:ApplyLambdaRules:Initial :Rule=" + Rule);
        log.info("Diff:ApplyLambdaRules:Initial :LowLevelRules=" + LowLevelRules);
        log.info("Diff:ApplyLambdaRules:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Diff:ApplyLambdaRules:Initial :TermOperator=" + (char) TermOperator);
        log.info("Diff:ApplyLambdaRules:Initial :DiffTermOperator=" + (char) DiffTermOperator);
        log.info("Diff:ApplyLambdaRules:Initial :");
        log.info("Diff:ApplyLambdaRules:Initial :");
        log.info("Diff:ApplyLambdaRules:Initial :");
        DiffExpr x = (() -> "");
        DiffrLambda y = (() -> "");
        boolean loadposDecrFlag = false;
        y = parseDiffExpr(AlgoName, Term1, Diff1, Term2, Diff2, Term3, Term4, StrOperator, Rule, DiffWithRespTo, TermOperator, LowLevelRules);
        final String MyString = y.Expr();
        x = (() -> "" + MyString);
        return x;
    }

    @FunctionalInterface
    public interface DiffrLambda {

        String Expr();
    }

    public static DiffrLambda parseDiffExpr(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        return new Object() {

            DiffrLambda parseDiff() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = parseNoExponentRuleTerm();
                    log.info("Diff:parseOper:NoExponentRuleTerm:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    x = parseNoExponentRuleDiff();
                    log.info("Diff:parseOper:NoExponentRuleDiff:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    x = parseCoeffExponentRule();
                    log.info("Diff:parseOper:CoeffExponentRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("ConstantRule")) {
                    x = parseConstantRule();
                    log.info("Diff:ApplyFuncRules:ConstantRule:x.Expr()=" + x.Expr());
                } else if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    x = parseSumRule();
                    log.info("Diff:parseOper:SumRule OR SubtractionRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("ExponentRule")) {

                    log.info("Diff:parseOper:ExponentRule:x.Expr()=" + x.Expr());
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
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedProductRule:x.Expr()=" + x.Expr());
                        } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                            x = (() -> Ux1 + "*" + VDashx1);
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedProductRule:x.Expr()=" + x.Expr());
                        } else if ((Vx1.contains("+")) || (Vx1.contains("-"))) {
                            x = (() -> "(" + Vx1 + ")*" + UDashx1 + "+" + VDashx1 + "*" + Ux1);
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedProductRule:x=" + x);
                        } else {
                            x = (() -> Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1);
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedProductRule:x.Expr()=" + x.Expr());
                        }
                    }
                    log.info("Diff:ApplyFuncRules:parseDiff:MappedProductRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("ProductRule")) {
                    x = parseProductRule();
                    log.info("Diff:ApplyFuncRules:ProductRule:x.Expr()=" + x.Expr());
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
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedQuotientRule:x.Expr()=" + x.Expr());
                        } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                            x = (() -> Ux1 + "/" + Vx1 + "^2" + "*" + VDashx1);
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedQuotientRule:x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1);
                            log.info("Diff:ApplyFuncRules:parseDiff:MappedQuotientRule:x.Expr()=" + x.Expr());
                        }
                    }
                } else if (Rule.equalsIgnoreCase("QuotientRule")) {
                    x = parseQuotientRule();
                    log.info("Diff:parseOperQuotientRule:x.Expr()=" + x.Expr());
                }
                log.info("Diff:ApplyFuncRules:parseDiff:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseNoExponentRuleTerm() {
                DiffrLambda x = (() -> "");
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = (() -> Term1);
                }
                log.info("Diff:parseNoExponentRuleTerm:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseNoExponentRuleDiff() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    if (Term1.equalsIgnoreCase("")) {
                        x = (() -> Diff1DiffExpr);
                        log.info("Diff:parseNoExponentRuleDiff:Rule 1:x.Expr()=" + x.Expr());
                    } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && (IsAConstant(Term1, DiffWithRespTo)) && ((IsAConstant(Diff1DiffExpr, DiffWithRespTo)))) {
                        x = (() -> "0.0");
                        log.info("Diff:parseNoExponentRuleDiff:Rule 2:x.Expr()=" + x.Expr());
                    } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-")))
                            && (!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")))
                            && (!IsExtremity(Diff1DiffExpr))) {
                        if (!Term1.endsWith("*0.0")) {
                            x = (() -> Term1 + "*" + "0.0");
                        } else {
                            x = (() -> Term1);
                        }
                        log.info("Diff:parseNoExponentRuleDiff:Rule 3:x.Expr()=" + x.Expr());
                    } else if ((((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/")))
                            && (!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (!IsExtremity(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(Term2))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))))) {
                        x = (() -> Term1);
                        log.info("Diff:parseNoExponentRuleDiff:Rule 4:x.Expr()=" + x.Expr());
                    } else if ((((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/")))
                            && (!Diff2DiffExpr.equalsIgnoreCase(""))
                            && (!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (!IsExtremity(Diff1DiffExpr))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))))) {
                        x = (() -> Term1 + StrOperator + "0.0");
                        log.info("Diff:parseNoExponentRuleDiff:Rule 5:x.Expr()=" + x.Expr());
                    } else if ((Term1.equalsIgnoreCase(Diff1DiffExpr)) && (!IsExtremity(Diff1DiffExpr)) && (!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                        if ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) {
                            x = (() -> Diff1DiffExpr + StrOperator + "0.0");
                        } else {
                            x = (() -> Diff1DiffExpr + "*" + "0.0");
                        }
                        log.info("Diff:parseNoExponentRuleDiff:Rule 6:x.Expr()=" + x.Expr());
                    } else if (!Term1.contains("Parenthesis")) {
                        x = (() -> Term1);
                        log.info("Diff:parseNoExponentRuleDiff:Rule 7:x.Expr()=" + x.Expr());
                    } else if ((Term1.equalsIgnoreCase(Diff1DiffExpr)) && (IsExtremity(Diff1DiffExpr)) && (!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                        x = (() -> Term1);
                        log.info("Diff:parseNoExponentRuleDiff:Rule 8:x.Expr()=" + x.Expr());
                    }
                }
                log.info("Diff:parseNoExponentRuleDiff:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseCoeffExponentRule() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    if (!Term4.equalsIgnoreCase("")) {
                        x = (() -> Term4 + "*" + Term3 + "^" + Term2);
                    } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                        x = (() -> Term3 + "^" + Term2);
                    } else {
                        x = (() -> Term1);
                    }
                }
                log.info("Diff:parseCoeffExponentRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseConstantRule() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("ConstantRule")) {
                    if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                            && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                            && (Term1.equalsIgnoreCase(Term2))
                            && (Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        log.info("Diff:parseConstantRule:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = (() -> "1.0");
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (StrOperator.equalsIgnoreCase("*"))) {
                        log.info("Diff:parseConstantRule:Rule2:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = (() -> Term1);
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (StrOperator.equalsIgnoreCase("/"))) {
                        log.info("Diff:parseConstantRule:Rule3:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = (() -> "-" + Term1 + "/" + DiffWithRespTo + "^2.0");
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))) {
                        log.info("Diff:parseConstantRule:Rule4:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if (Term3.equalsIgnoreCase("")) {
                            x = (() -> Term1 + StrOperator + Term2);
                            log.info("Diff:parseConstantRule:Rule4.1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                            log.info("Diff:parseConstantRule:Rule4.1:x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Term1 + StrOperator + Term3);
                            log.info("Diff:parseConstantRule:Rule4.2:Term3=" + Term3 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                            log.info("Diff:parseConstantRule:Rule4.2:x.Expr()=" + x.Expr());
                        }
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                        log.info("Diff:parseConstantRule:Rule5:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Diff1DiffExpr.equalsIgnoreCase("Infinity")) || (Diff1DiffExpr.equalsIgnoreCase("NaN"))) {
                            x = (() -> Diff1DiffExpr + StrOperator + DiffWithRespTo + "^2.0");
                            log.info("Diff:parseConstantRule:Rule5.1:x.Expr()=" + x.Expr());
                        } else {
                            if (StrOperator.equalsIgnoreCase("/")) //Map Sign with "-"
                            {
                                x = (() -> "-" + Term1 + StrOperator + DiffWithRespTo + "^2.0");
                                log.info("Diff:parseConstantRule:Rule5.2:x.Expr()=" + x.Expr());
                            } else if (StrOperator.equalsIgnoreCase("*")) {
                                x = (() -> Term1 + StrOperator + DiffWithRespTo + "^2.0");
                                log.info("Diff:parseConstantRule:Rule5.3:x.Expr()=" + x.Expr());
                            }
                        }
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/"))) {
                        log.info("Diff:parseConstantRule:Rule6:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = (() -> Term1 + StrOperator + Diff1DiffExpr);
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                        log.info("Diff:parseConstantRule:Rule7:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Term1.equalsIgnoreCase("0.0")) || (Term1.equalsIgnoreCase("-0.0")) || (Term1.equalsIgnoreCase("+0.0"))
                                || (Term1.equalsIgnoreCase("0"))) {
                            x = (() -> "0.0");
                        } else if ((IsExtremity(Term1))
                                && (Diff1DiffExpr.equalsIgnoreCase(""))
                                && (Diff2DiffExpr.equalsIgnoreCase(""))
                                && (Term2.equalsIgnoreCase(""))) {
                            x = (() -> Term1);
                            log.info("Diff:parseConstantRule:Rule7.1:x.Expr()=" + x.Expr());
                        } else if ((!Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) && (Diff1DiffExpr.contains(Term1))) {
                            String Temp = Diff1DiffExpr.replaceFirst(Term1, "");
                            if (Temp.startsWith("*")) {
                                Temp = Temp.replaceFirst("\\*", "");
                            } else if (Temp.startsWith("/")) {
                                Temp = Temp.replaceFirst("/", "");
                            }
                            if ((functionsDiff.containsKey(Temp)) && (!Diff2DiffExpr.contains(Term1))) {
                                x = (() -> Term1 + StrOperator + Diff2DiffExpr);
                                log.info("Diff:parseConstantRule:Rule7.2.1.1:x.Expr()=" + x.Expr() + " Temp=" + Temp);
                            } else if (functionsDiff.containsKey(Temp)) {
                                x = (() -> Diff2DiffExpr);
                                log.info("Diff:parseConstantRule:Rule7.2.1.2:x.Expr()=" + x.Expr() + " Temp=" + Temp);
                            } else {
                                x = (() -> Diff1DiffExpr + StrOperator + Diff2DiffExpr);
                                log.info("Diff:parseConstantRule:Rule7.2.1.3:x.Expr()=" + x.Expr() + " Temp=" + Temp);
                            }
                            log.info("Diff:parseConstantRule:Rule7.2.1:x.Expr()=" + x.Expr() + " Temp=" + Temp);
                        } else if (!Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                            x = (() -> Diff1DiffExpr + StrOperator + Diff2DiffExpr);
                            log.info("Diff:parseConstantRule:Rule7.2.2:x.Expr()=" + x.Expr());
                        } else if ((Term2.equalsIgnoreCase(Diff2DiffExpr)) && (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Term1, DiffWithRespTo))) {
                            x = (() -> Term1);
                            log.info("Diff:parseConstantRule:Rule7.2.3:x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1DiffExpr);
                            log.info("Diff:parseConstantRule:Rule7.3:x.Expr()=" + x.Expr());
                        }
                    } else {
                        log.info("Diff:parseConstantRule:Rule8:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo))) {
                            x = (() -> "-" + Diff1DiffExpr + "/" + DiffWithRespTo + "^2.0");
                        } else {
                            x = (() -> Term2);
                        }
                        log.info("Diff:parseConstantRule:Rule8:x.Expr()=" + x.Expr());
                    }
                }
                log.info("Diff:parseConstantRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseSumRule() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                        log.info("Diff:parseSumRule:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
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
                        log.info("Diff:parseSumRule:Got Term Expr:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                        x = (() -> Term1 + StrOperator + Term2);
                    } else if (((Term2 == null) || (Term2.equalsIgnoreCase(""))) || (Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                        log.info("Diff:parseSumRule:Got a Constant:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                        x = (() -> Term1 + StrOperator + Diff2DiffExpr);
                    } else {
                        log.info("Diff:parseSumRule:Else:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1.DiffExpr=" + Diff1DiffExpr);
                        x = (() -> Diff1DiffExpr);
                    }

                }
                return x;
            }

            DiffrLambda parseProductRule() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = (() -> Term3 + "*" + Diff1DiffExpr + "+" + Term1 + "*" + Diff2DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule1:x.Expr()=" + x.Expr());
                    } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = (() -> Diff2DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule2:x.Expr()=" + x.Expr());
                    } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = (() -> Diff1DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule3:x.Expr()=" + x.Expr());
                    } else if ((Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (!Diff2DiffExpr.contains(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                        //Differentiation of IndConstant Expression term:
                        if ((!IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            x = (() -> Diff1DiffExpr + "*" + Diff2DiffExpr);
                        } else {
                            x = (() -> Diff1DiffExpr);
                        }
                        log.info("Diff:parseProductRule:ProductRule:Rule4:x.Expr()=" + x.Expr());
                    } else if ((Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (!Diff2DiffExpr.contains(DiffWithRespTo)) && (IsAConstant(Term1, DiffWithRespTo)) && (Diff1DiffExpr.contains(Term1)) && (Diff1DiffExpr.contains(DiffWithRespTo))) {
                        //Drop Trailing Constant(Duplicate) in Differentiation of IndConstant Expression term:
                        x = (() -> Diff1DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule5:x.Expr()=" + x.Expr());
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && ((!Diff1DiffExpr.equalsIgnoreCase("1.0")))) {
                        if ((!Diff2DiffExpr.equalsIgnoreCase("0.0")) && (Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (IsAConstant(Term1, DiffWithRespTo))) {
                            x = (() -> Diff1DiffExpr + "*" + Term1);
                            log.info("Diff:parseProductRule:ProductRule:Rule6.1:x.Expr()=" + x.Expr());
                        } else if (!Diff2DiffExpr.equalsIgnoreCase("0.0")) {
                            x = (() -> Diff1DiffExpr + "*" + Diff2DiffExpr);
                            log.info("Diff:parseProductRule:ProductRule:Rule6.2:x.Expr()=" + x.Expr());
                        } else if ((!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                            x = (() -> Term1 + "*" + Diff2DiffExpr);
                            log.info("Diff:parseProductRule:ProductRule:Rule6.3:x.Expr()=" + x.Expr());
                        } else {
                            MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "ProductRule", DiffWithRespTo, '*', LowLevelRules);
                            x = (() -> y.StrExpr());
                            log.info("Diff:parseProductRule:ProductRule:Rule6.4:x.Expr()=" + x.Expr());
                        }
                    } else if (((Diff2DiffExpr.equalsIgnoreCase("")) || ((Diff2DiffExpr.equalsIgnoreCase("0.0"))))
                            && (!Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (IsAConstant(Diff2DiffExpr, DiffWithRespTo))
                            && (StrOperator.equalsIgnoreCase("*"))) {
                        x = (() -> "0.0");
                        log.info("Diff:parseProductRule:ProductRule:Rule7:x.Expr()=" + x.Expr());
                    } else if (((Diff1DiffExpr.equalsIgnoreCase("")) || ((Diff1DiffExpr.equalsIgnoreCase("1.0"))))
                            && (!Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (IsAConstant(Diff2DiffExpr, DiffWithRespTo))
                            && (StrOperator.equalsIgnoreCase("*")) && (DiffTermOperator == '/')) {
                        MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "QuotientRule", DiffWithRespTo, DiffTermOperator, LowLevelRules);
                        x = (() -> y.StrExpr());
                        log.info("Diff:parseProductRule:ProductRule:Rule8:x.Expr()=" + x.Expr());
                    } else if (Diff1DiffExpr.equalsIgnoreCase("")) {
                        x = (() -> "0.0" + "*" + Diff2DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule9:x.Expr()=" + x.Expr());
                    } else if (Diff1DiffExpr.equalsIgnoreCase("1.0")) {
                        x = (() -> Diff2DiffExpr);
                        log.info("Diff:parseProductRule:ProductRule:Rule10:x.Expr()=" + x.Expr());
                    } else {
                        MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "ProductRule", DiffWithRespTo, TermOperator, LowLevelRules);
                        x = (() -> y.StrExpr());
                        log.info("Diff:parseProductRule:ProductRule:Rule11:x.Expr()=" + x.Expr());
                    }
                }
                log.info("Diff:parseProductRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            DiffrLambda parseQuotientRule() {
                DiffrLambda x = (() -> "");
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("QuotientRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = (() -> ("(" + Term2 + "*" + Diff1DiffExpr + "-" + Term1 + "*" + Diff2DiffExpr) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")");
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule1:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Term2.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff1DiffExpr.equalsIgnoreCase("1.0"))) {
                        x = (() -> 1.0 + "/" + DiffWithRespTo + "^2.0");
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule2:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
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
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule3:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = (() -> Diff2DiffExpr);
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule4:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of 1/x term:
                        x = (() -> Diff1DiffExpr + "/" + DiffWithRespTo + "^2.0");
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule5:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                        if (!Diff2DiffExpr.equalsIgnoreCase("")) {
                            if (StrOperator.equalsIgnoreCase("/")) {
                                x = (() -> Diff1DiffExpr + "/" + Diff2DiffExpr);
                            } else {
                                x = (() -> Diff1DiffExpr + "*" + Diff2DiffExpr);
                            }
                        }
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule6:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase(""))) {
                        x = (() -> Diff1DiffExpr + "/" + Diff2DiffExpr);
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule7:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((Diff1DiffExpr.equalsIgnoreCase(""))) {
                        x = (() -> Diff2DiffExpr);
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule8:x.Expr()=" + x.Expr() + " Term2=" + Term2 + " Diff2.DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else {
                        MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "QuotientRule", DiffWithRespTo, TermOperator, LowLevelRules);
                        x = (() -> y.StrExpr());
                        log.info("Diff:parseQuotientRule:QuotientRule:Rule9:x.Expr()=" + x.Expr());
                    }
                }
                return x;
            }
        }.parseDiff();
    }

    public static String ApplyLambdaString(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        log.info("Diff:ApplyLambdaString:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Diff:ApplyLambdaString:Initial :Term1=" + Term1);
        log.info("Diff:ApplyLambdaString:Initial :Diff1.DiffExpr()=" + Diff1.DiffExpr());
        log.info("Diff:ApplyLambdaString:Initial :Term2=" + Term2);
        log.info("Diff:ApplyLambdaString:Initial :Diff2.DiffExpr()=" + Diff2.DiffExpr());
        log.info("Diff:ApplyLambdaString:Initial :Term3=" + Term3);
        log.info("Diff:ApplyLambdaString:Initial :Term4=" + Term4);
        log.info("Diff:ApplyLambdaString:Initial :StrOperator=" + StrOperator);
        log.info("Diff:ApplyLambdaString:Initial :Rule=" + Rule);
        log.info("Diff:ApplyLambdaString:Initial :LowLevelRules=" + LowLevelRules);
        log.info("Diff:ApplyLambdaString:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Diff:ApplyLambdaString:Initial :TermOperator=" + (char) TermOperator);
        log.info("Diff:ApplyLambdaString:Initial :DiffTermOperator=" + (char) DiffTermOperator);
        log.info("Diff:ApplyLambdaString:Initial :");
        log.info("Diff:ApplyLambdaString:Initial :");
        log.info("Diff:ApplyLambdaString:Initial :");
        String x = "";
        DiffrLambdaStr y = (() -> "");
        y = parseDiffStr(AlgoName, Term1, Diff1, Term2, Diff2, Term3, Term4, StrOperator, Rule, DiffWithRespTo, TermOperator, LowLevelRules);
        x = y.StrExpr();
        return x;
    }

    @FunctionalInterface
    public interface DiffrLambdaStr {

        String StrExpr();
    }

    public static DiffrLambdaStr parseDiffStr(String AlgoName, String Term1, DiffExpr Diff1, String Term2, DiffExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        return new Object() {

            DiffrLambdaStr parseDiffStr() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = parseNoExponentRuleTerm();
                    log.info("Diff:String:parseOper:NoExponentRuleTerm:x=" + x);
                } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    x = parseNoExponentRuleDiff();
                    log.info("Diff:String:parseOper:NoExponentRuleDiff:x=" + x);
                } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    x = parseCoeffExponentRule();
                    log.info("Diff:String:parseOper:CoeffExponentRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("ConstantRule")) {
                    x = parseConstantRule();
                    log.info("Diff:String:ApplyFuncRules:ConstantRule:x=" + x);
                } else if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    x = parseSumRule();
                    log.info("Diff:String:parseOper:SumRule OR SubtractionRule:x)=" + x);
                } else if (Rule.equalsIgnoreCase("ExponentRule")) {

                    log.info("Diff:String:parseOper:ExponentRule:x=" + x);
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
                            x = UDashx1 + "*" + VDashx1;
                            log.info("Diff:String:parseOper:MappedProductRule:x=" + x);
                        } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                            x = Ux1 + "*" + VDashx1;
                            log.info("Diff:String:parseOper:MappedProductRule:x=" + x);
                        } else if ((Vx1.contains("+")) || (Vx1.contains("-"))) {
                            x = "(" + Vx1 + ")*" + UDashx1 + "+" + VDashx1 + "*" + Ux1;
                            log.info("Diff:String:parseOper:MappedProductRule:x=" + x);
                        } else {
                            x = Vx1 + "*" + UDashx1 + "+" + VDashx1 + "*" + Ux1;
                            log.info("Diff:String:parseOper:MappedProductRule:x=" + x);
                        }
                    }
                    log.info("Diff:String:parseOper:MappedProductRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("ProductRule")) {
                    x = parseProductRule();
                    log.info("Diff:String:parseOper:ProductRule:x=" + x);
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
                            x = UDashx1 + "/" + Vx1 + "^2" + "*" + VDashx1;
                            log.info("Diff:String:parseOper:MappedQuotientRule:x=" + x);
                        } else if ((IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && (Diff1DiffExpr.contains("Parenthesis"))) {
                            x = Ux1 + "/" + Vx1 + "^2" + "*" + VDashx1;
                            log.info("Diff:String:parseOper:MappedQuotientRule:x=" + x);
                        } else {
                            x = "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1;
                            log.info("Diff:String:parseOper:MappedQuotientRule:x=" + x);
                        }
                    }
                    log.info("Diff:String:parseOper:MappedQuotientRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("QuotientRule")) {
                    x = parseQuotientRule();
                    log.info("Diff:String:parseOperQuotientRule:x=" + x);
                }
                log.info("Diff:String:parseDiffStr:Final:x=" + x);
                final String y = x;
                return (() -> y);
            }

            String parseNoExponentRuleTerm() {
                String x = "";
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = Term1;
                }
                log.info("Diff:String:parseNoExponentRuleTerm:Final:x=" + x);
                return x;
            }

            String parseNoExponentRuleDiff() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    if (Term1.equalsIgnoreCase("")) {
                        x = Diff1DiffExpr;
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 1:x=" + x);
                    } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-"))) && (IsAConstant(Term1, DiffWithRespTo)) && ((IsAConstant(Diff1DiffExpr, DiffWithRespTo)))) {
                        x = "0.0";
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 2:x=" + x);
                    } else if (((StrOperator.equalsIgnoreCase("+")) || (StrOperator.equalsIgnoreCase("-")))
                            && (!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")))
                            && (!IsExtremity(Diff1DiffExpr))) {
                        if (!Term1.endsWith("*0.0")) {
                            x = Term1 + "*" + "0.0";
                        } else {
                            x = Term1;
                        }
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 3:x=" + x);
                    } else if ((((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/")))
                            && (!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (!IsExtremity(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(Term2))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))))) {
                        x = Term1;
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 4:x=" + x);
                    } else if ((((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/")))
                            && (!Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (!IsExtremity(Diff1DiffExpr))
                            && ((!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))))) {
                        x = Term1 + StrOperator + "0.0";
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 5:x=" + x);
                    } else if ((Term1.equalsIgnoreCase(Diff1DiffExpr)) && (!IsExtremity(Diff1DiffExpr)) && (!IsAConstant(Diff1DiffExpr, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                        if ((StrOperator.equalsIgnoreCase("*")) || (StrOperator.equalsIgnoreCase("/"))) {
                            x = Diff1DiffExpr + StrOperator + "0.0";
                        } else {
                            x = Diff1DiffExpr + "*" + "0.0";
                        }
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 6:x=" + x);
                    } else if (!Term1.contains("Parenthesis")) {
                        x = Term1;
                        log.info("Diff:String:parseNoExponentRuleDiff:Rule 7:x=" + x);
                    }
                }
                log.info("Diff:String:parseNoExponentRuleDiff:Final:x=" + x);
                return x;
            }

            String parseCoeffExponentRule() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    if (!Term4.equalsIgnoreCase("")) {
                        x = Term4 + "*" + Term3 + "^" + Term2;
                    } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                        x = Term3 + "^" + Term2;
                    } else {
                        x = Term1;
                    }
                }
                log.info("Diff:String:parseCoeffExponentRule:Final:x=" + x);
                return x;
            }

            String parseConstantRule() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("ConstantRule")) {
                    if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                            && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                            && (Term1.equalsIgnoreCase(Term2))
                            && (Diff1DiffExpr.equalsIgnoreCase(Diff2DiffExpr))
                            && (Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        log.info("Diff:String:parseConstantRule:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = "1.0";
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (StrOperator.equalsIgnoreCase("*"))) {
                        log.info("Diff:String:parseConstantRule:Rule2:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = Term1;
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (StrOperator.equalsIgnoreCase("/"))) {
                        log.info("Diff:String:parseConstantRule:Rule3:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = "-" + Term1 + "/" + DiffWithRespTo + "^2.0";
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo)) && (Term2 != null) && (!Term2.equalsIgnoreCase(""))) {
                        if (Term3.equalsIgnoreCase("")) {
                            log.info("Diff:String:parseConstantRule:Rule4.1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                            x = Term1 + StrOperator + Term2;
                            log.info("Diff:String:parseConstantRule:Rule4.1:x=" + x);
                        } else {
                            log.info("Diff:String:parseConstantRule:Rule4.2:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                            x = Term1 + StrOperator + Term3;
                            log.info("Diff:String:parseConstantRule:Rule4.2:x=" + x);
                        }
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(DiffWithRespTo))) {
                        log.info("Diff:String:parseConstantRule:Rule5:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Diff1DiffExpr.equalsIgnoreCase("Infinity")) || (Diff1DiffExpr.equalsIgnoreCase("NaN"))) {
                            x = Diff1DiffExpr + StrOperator + DiffWithRespTo + "^2.0";
                            log.info("Diff:String:parseConstantRule:Rule5.1:x=" + x);
                        } else {
                            if (StrOperator.equalsIgnoreCase("/")) //Map Sign with "-"
                            {
                                x = "-" + Term1 + StrOperator + DiffWithRespTo + "^2.0";
                                log.info("Diff:String:parseConstantRule:Rule5.2:x=" + x);
                            } else if (StrOperator.equalsIgnoreCase("*")) {
                                x = Term1 + StrOperator + DiffWithRespTo + "^2.0";
                                log.info("Diff:String:parseConstantRule:Rule5.3:x=" + x);
                            }
                        }
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr)) && (StrOperator.equalsIgnoreCase("/"))) {
                        log.info("Diff:String:parseConstantRule:Rule6:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        x = Term1 + StrOperator + Diff1DiffExpr;
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                        log.info("Diff:String:parseConstantRule:Rule7:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Term1.equalsIgnoreCase("0.0")) || (Term1.equalsIgnoreCase("-0.0")) || (Term1.equalsIgnoreCase("+0.0"))
                                || (Term1.equalsIgnoreCase("0"))) {
                            x = "0.0";
                        } else if ((IsExtremity(Term1))
                                && (Diff1DiffExpr.equalsIgnoreCase(""))
                                && (Diff2DiffExpr.equalsIgnoreCase(""))
                                && (Term2.equalsIgnoreCase(""))) {
                            x = Term1;
                            log.info("Diff:String:parseConstantRule:Rule7.1:x=" + x);
                        } else if ((!Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) && (Diff1DiffExpr.contains(Term1))) {
                            String Temp = Diff1DiffExpr.replaceFirst(Term1, "");
                            if (Temp.startsWith("*")) {
                                Temp = Temp.replaceFirst("\\*", "");
                            } else if (Temp.startsWith("/")) {
                                Temp = Temp.replaceFirst("/", "");
                            }
                            if ((functionsDiff.containsKey(Temp)) && (!Diff2DiffExpr.contains(Term1))) {
                                x = Term1 + StrOperator + Diff2DiffExpr;
                                log.info("Diff:parseConstantRule:Rule7.2.1.1:x=" + x + " Temp=" + Temp);
                            } else if (functionsDiff.containsKey(Temp)) {
                                x = Diff2DiffExpr;
                                log.info("Diff:parseConstantRule:Rule7.2.1.1:x=" + x + " Temp=" + Temp);
                            } else {
                                x = Diff1DiffExpr + StrOperator + Diff2DiffExpr;
                                log.info("Diff:parseConstantRule:Rule7.2.1.2:x=" + x + " Temp=" + Temp);
                            }
                            log.info("Diff:parseConstantRule:Rule7.2.1:x=" + x + " Temp=" + Temp);
                        } else if (!Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                            x = Diff1DiffExpr + StrOperator + Diff2DiffExpr;
                            log.info("Diff:String:parseConstantRule:Rule7.2.2:x=" + x);
                        } else if ((Term2.equalsIgnoreCase(Diff2DiffExpr)) && (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Term1, DiffWithRespTo))) {
                            x = Term1;
                            log.info("Diff:String:parseConstantRule:Rule7.2.3:x=" + x);
                        } else {
                            x = Diff1DiffExpr;
                            log.info("Diff:String:parseConstantRule:Rule7.3:x=" + x);
                        }
                    } else {
                        log.info("Diff:String:parseConstantRule:Rule8:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo))) {
                            x = "-" + Diff1DiffExpr + "/" + DiffWithRespTo + "^2.0";
                        } else {
                            x = Term2;
                        }
                        log.info("Diff:String:parseConstantRule:Rule8:x=" + x);
                    }
                }
                log.info("Diff:String:parseConstantRule:Final:x=" + x);
                return x;
            }

            String parseSumRule() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                        log.info("Diff:String:parseSumRule:Rule1:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                        if ((StrOperator.equalsIgnoreCase("+")) && ((!Diff2DiffExpr.startsWith("-")) && (!Diff2DiffExpr.startsWith("-")))) {
                            x = Diff1DiffExpr + StrOperator + Diff2DiffExpr;
                        } else if ((StrOperator.equalsIgnoreCase("+")) && (Diff2DiffExpr.startsWith("-"))) {
                            x = Diff1DiffExpr + Diff2DiffExpr;
                        } else if ((StrOperator.equalsIgnoreCase("-")) && (Diff2DiffExpr.startsWith("-"))) {
                            if (Diff2DiffExpr.length() > 1) {
                                final String TempData = Diff2DiffExpr.substring(1);
                                //Mapped Sign
                                x = Diff1DiffExpr + "+" + TempData;
                            }
                        } else {
                            x = Diff1DiffExpr + StrOperator + Diff2DiffExpr;
                        }
                    } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                        log.info("Diff:String:parseSumRule:Got Term Expr:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                        x = Term1 + StrOperator + Term2;
                    } else if (((Term2 == null) || (Term2.equalsIgnoreCase(""))) || (Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                        log.info("Diff:String:parseSumRule:Got a Constant:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr);
                        x = Term1 + StrOperator + Diff2DiffExpr;
                    } else {
                        log.info("Diff:String:parseSumRule:Else:Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1.DiffExpr=" + Diff1DiffExpr);
                        x = Diff1DiffExpr;
                    }

                }
                log.info("Diff:String:parseCoeffExponentRule:Final:x=" + x);
                return x;
            }

            String parseProductRule() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = Term3 + "*" + Diff1DiffExpr + "+" + Term1 + "*" + Diff2DiffExpr;
                        log.info("Diff:String:parseProductRule:ProductRule:Rule1:x=" + x);
                    } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = Diff2DiffExpr;
                        log.info("Diff:String:parseProductRule:ProductRule:Rule2:x=" + x);
                    } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = Diff1DiffExpr;
                        log.info("Diff:String:parseProductRule:ProductRule:Rule3:x=" + x);
                    } else if ((Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (!Diff2DiffExpr.contains(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                        //Differentiation of IndConstant Expression term:
                        if ((!IsAConstant(Diff2DiffExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            x = Diff1DiffExpr + "*" + Diff2DiffExpr;
                        } else {
                            x = Diff1DiffExpr;
                        }
                        log.info("Diff:String:parseProductRule:ProductRule:Rule4:x=" + x);
                    } else if ((Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (!Diff2DiffExpr.contains(DiffWithRespTo)) && (IsAConstant(Term1, DiffWithRespTo)) && (Diff1DiffExpr.contains(Term1)) && (Diff1DiffExpr.contains(DiffWithRespTo))) {
                        //Drop Trailing Constant(Duplicate) in Differentiation of IndConstant Expression term:
                        x = Diff1DiffExpr;
                        log.info("Diff:String:parseProductRule:ProductRule:Rule5:x=" + x);
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && ((!Diff1DiffExpr.equalsIgnoreCase("1.0")))) {
                        if ((!Diff2DiffExpr.equalsIgnoreCase("0.0")) && (Term1.equalsIgnoreCase(Term2)) && (Term1.equalsIgnoreCase(Diff2DiffExpr)) && (IsAConstant(Term1, DiffWithRespTo))) {
                            x = Diff1DiffExpr + "*" + Term1;
                            log.info("Diff:String:parseProductRule:ProductRule:Rule6.1:x=" + x);
                        } else if (!Diff2DiffExpr.equalsIgnoreCase("0.0")) {
                            x = Diff1DiffExpr + "*" + Diff2DiffExpr;
                            log.info("Diff:String:parseProductRule:ProductRule:Rule6.2:x=" + x);
                        } else if ((!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (Diff2DiffExpr.equalsIgnoreCase("0.0"))) {
                            x = Term1 + "*" + Diff2DiffExpr;
                            log.info("Diff:parseProductRule:ProductRule:Rule6.4:x=" + x);
                        } else {
                            MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "ProductRule", DiffWithRespTo, '*', LowLevelRules);
                            x = y.StrExpr();
                            log.info("Diff:String:parseProductRule:ProductRule:Rule6.4:x=" + x);
                        }
                    } else if (((Diff2DiffExpr.equalsIgnoreCase("")) || ((Diff2DiffExpr.equalsIgnoreCase("0.0"))))
                            && (!Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (IsAConstant(Diff2DiffExpr, DiffWithRespTo))
                            && (StrOperator.equalsIgnoreCase("*"))) {
                        x = "0.0";
                        log.info("Diff:String:parseProductRule:ProductRule:Rule7:x=" + x);
                    } else if (((Diff1DiffExpr.equalsIgnoreCase("")) || ((Diff1DiffExpr.equalsIgnoreCase("1.0"))))
                            && (!Term1.equalsIgnoreCase(Diff1DiffExpr))
                            && (IsAConstant(Diff2DiffExpr, DiffWithRespTo))
                            && (StrOperator.equalsIgnoreCase("*")) && (DiffTermOperator == '/')) {
                        if (!Diff2DiffExpr.equalsIgnoreCase("0.0")) {
                            x = "1.0/" + Diff2DiffExpr;
                        } else {
                            MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "QuotientRule", DiffWithRespTo, DiffTermOperator, LowLevelRules);
                            x = y.StrExpr();
                        }
                        log.info("Diff:String:parseProductRule:ProductRule:Rule8:x=" + x);
                    } else if (Diff1DiffExpr.equalsIgnoreCase("")) {
                        x = "0.0" + "*" + Diff2DiffExpr;
                        log.info("Diff:parseProductRule:ProductRule:Rule9:x=" + x);
                    } else if (Diff1DiffExpr.equalsIgnoreCase("1.0")) {
                        x = Diff2DiffExpr;
                        log.info("Diff:String:parseProductRule:ProductRule:Rule10:x=" + x);
                    } else {
                        MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "ProductRule", DiffWithRespTo, TermOperator, LowLevelRules);
                        x = y.StrExpr();
                        log.info("Diff:String:parseProductRule:ProductRule:Rule11:x=" + x);
                    }
                }
                log.info("Diff:String:parseProductRule:Final:x=" + x);
                return x;
            }

            String parseQuotientRule() {
                String x = "";
                final String Diff1DiffExpr = Diff1.DiffExpr();
                final String Diff2DiffExpr = Diff2.DiffExpr();
                if (Rule.equalsIgnoreCase("QuotientRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = ("(" + Term2 + "*" + Diff1DiffExpr + "-" + Term1 + "*" + Diff2DiffExpr) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")";
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule1:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Term2.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(DiffWithRespTo)) && (Diff1DiffExpr.equalsIgnoreCase("1.0"))) {
                        x = 1.0 + "/" + DiffWithRespTo + "^2.0";
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule2:x)=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Term1.equalsIgnoreCase("")) && (Diff1DiffExpr.equalsIgnoreCase("1.0"))) {
                        if (StrOperator.equalsIgnoreCase("*")) {
                            x = Term1;
                        } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term2.equalsIgnoreCase(Term1))) {
                            x = "1.0" + StrOperator + Term1;
                        } else if ((StrOperator.equalsIgnoreCase("/")) && (Term2.equalsIgnoreCase(Term1)) && (Diff2DiffExpr.equalsIgnoreCase(Term2)) && (!Term4.equalsIgnoreCase(""))) {
                            x = "1.0" + StrOperator + Term1;
                        } else if ((StrOperator.equalsIgnoreCase("/")) && (!Term4.equalsIgnoreCase(""))) {
                            x = "1.0" + StrOperator + Term1;
                        } else if ((StrOperator.equalsIgnoreCase("/"))) {
                            x = Term1;
                        }
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule3:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if (Diff1DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = Diff2DiffExpr;
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule4:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if (Diff2DiffExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of 1/x term:
                        x = Diff1DiffExpr + "/" + DiffWithRespTo + "^2.0";
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule5:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase("")) && (Term2.equalsIgnoreCase(Diff2DiffExpr))) {
                        if (!Diff2DiffExpr.equalsIgnoreCase("")) {
                            if (StrOperator.equalsIgnoreCase("/")) {
                                x = Diff1DiffExpr + "/" + Diff2DiffExpr;
                            } else {
                                x = Diff1DiffExpr + "*" + Diff2DiffExpr;
                            }
                        }
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule6:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((!Diff1DiffExpr.equalsIgnoreCase(""))) {
                        x = Diff1DiffExpr + "/" + Diff2DiffExpr;
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule7:x=" + x + " Term2=" + Term2 + " Diff2DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else if ((Diff1DiffExpr.equalsIgnoreCase(""))) {
                        x = Diff2DiffExpr;
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule8:x=" + x + " Term2=" + Term2 + " Diff2.DiffExpr=" + Diff2DiffExpr + " Diff1DiffExpr=" + Diff1DiffExpr);
                    } else {
                        MathsContxtLAv.ProLambdaExtr y = MathsContxtLAv.parseExpr(AlgoName, Diff1.DiffExpr(), Diff2.DiffExpr(), "QuotientRule", DiffWithRespTo, TermOperator, LowLevelRules);
                        x = y.StrExpr();
                        log.info("Diff:String:parseQuotientRule:QuotientRule:Rule9:x=" + x);
                    }
                }
                log.info("Diff:String:parseQuotientRule:Final:x=" + x);
                return x;
            }
        }.parseDiffStr();
    }

    public static IntegrExpr ApplyLambdaRules(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        log.info("Integr:ApplyLambdaRules:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Integr:ApplyLambdaRules:Initial :Term1=" + Term1);
        log.info("Integr:ApplyLambdaRules:Initial :Diff1.IntegrExpr()=" + Diff1.IntegrExpr());
        log.info("Integr:ApplyLambdaRules:Initial :Term2=" + Term2);
        log.info("Integr:ApplyLambdaRules:Initial :Diff2.IntegrExpr()=" + Diff2.IntegrExpr());
        log.info("Integr:ApplyLambdaRules:Initial :Term3=" + Term3);
        log.info("Integr:ApplyLambdaRules:Initial :Term4=" + Term4);
        log.info("Integr:ApplyLambdaRules:Initial :StrOperator=" + StrOperator);
        log.info("Integr:ApplyLambdaRules:Initial :Rule=" + Rule);
        log.info("Integr:ApplyLambdaRules:Initial :LowLevelRules=" + LowLevelRules);
        log.info("Integr:ApplyLambdaRules:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Integr:ApplyLambdaRules:Initial :TermOperator=" + (char) TermOperator);
        log.info("Integr:ApplyLambdaRules:Initial :DiffTermOperator=" + (char) DiffTermOperator);
        log.info("Integr:ApplyLambdaRules:Initial :");
        log.info("Integr:ApplyLambdaRules:Initial :");
        log.info("Integr:ApplyLambdaRules:Initial :");
        IntegrExpr x = (() -> "");
        IntegrLambda y = (() -> "");
        boolean loadposDecrFlag = false;
        String[] MyTemp = new String[2];
        if (Diff2.IntegrExpr().contains("@Parenthesis@")) {
            MyTemp = Diff2.IntegrExpr().split("@Parenthesis@");
            Term2 = MyTemp[1];
        }
        y = parseIntegrExpr(AlgoName, Term1, Diff1, Term2, Diff2, Term3, Term4, StrOperator, Rule, DiffWithRespTo, TermOperator, LowLevelRules);
        final String MyString = y.Expr();
        x = (() -> "" + MyString);
        return x;
    }

    @FunctionalInterface
    public interface IntegrLambda {

        String Expr();
    }

    public static IntegrLambda parseIntegrExpr(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        return new Object() {

            IntegrLambda parseIntegr() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                int Supported = 1;
                if ((Diff1IntegrExpr.contains(DiffWithRespTo)) && (Diff2IntegrExpr.contains(DiffWithRespTo)) && (Rule.equalsIgnoreCase("MappedQuotientRule")) && (StrOperator.equalsIgnoreCase("/"))) {
                    Supported = 0;
                }
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = parseNoExponentRuleTerm();
                    log.info("Integr:parseIntegr:NoExponentRuleTerm:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    x = parseNoExponentRuleDiff();
                    log.info("Integr:parseIntegr:NoExponentRuleDiff:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    x = parseCoeffExponentRule();
                    log.info("Integr:parseIntegr:CoeffExponentRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("ConstantRule")) {
                    x = parseConstantRule();
                    log.info("Integr:parseIntegr:ConstantRule:x.Expr()=" + x.Expr());
                } else if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    x = parseSumRule();
                    log.info("Integr:parseIntegr:SumRule OR SubtractionRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("ExponentRule")) {

                    log.info("Integr:parseIntegr:ExponentRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("IntegrationByParts")) {
                    if (Supported == 1) {
                        // Integral u*Diffv= uv - Integral vDiffu
                        // Integral Ux*DiffV= Ux*IntegralDiffVx - Integral IntegralDiffVx*DiffUx
                        if ((!Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr)) && (StrOperator.equalsIgnoreCase("*"))) {
                            String Ux = "";
                            String DiffUx = "";
                            String DiffVx = "";
                            String IntegralDiffVx = "";
                            // Determine Order ParanExpr
                            // Determine Order Term1
                            MathsContxtLAv.OrderVar expOrder = MathsContxtLAv.parseOrder(Term1, DiffWithRespTo);
                            int myOrderUx = (int) Double.parseDouble(expOrder.eval());
                            log.info("Integr:ApplyFuncRules " + DiffWithRespTo + " Term1=" + Term1 + " myOrderUx=" + myOrderUx);
                            // Determine Order Term2
                            expOrder = MathsContxtLAv.parseOrder(Term2, DiffWithRespTo);
                            int myOrderVx = (int) Double.parseDouble(expOrder.eval());
                            log.info("Integr:ApplyFuncRules " + DiffWithRespTo + " Term2=" + Term2 + " myOrderVx=" + myOrderVx);

                            //Choose Ux 
                            // Choose Vx
                            // Set Ux Parameters
                            if (Diff1IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff1IntegrExpr, DiffWithRespTo);
                                Ux = ParanExpr;
                            } else if ((!Diff1IntegrExpr.contains("Parenthesis")) && (Diff1IntegrExpr.equalsIgnoreCase(Term1))) {
                                Ux = Term1;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(Term1, variables, DiffWithRespTo);
                                DiffExpr MyUxDash = parseDiff(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                DiffUx = MyUxDash.DiffExpr();
                                log.info("Integr:ApplyFuncRules:IntegrationByParts:Ux=" + Ux + " Term1=" + Term1);
                            }
                            // Set Vx Parameters
                            IntegrExpr MyTemp2 = (() -> "");
                            if (Diff2IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff2IntegrExpr, DiffWithRespTo);
                                DiffVx = ParanExpr;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(DiffVx, variables, DiffWithRespTo);
                                MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                IntegralDiffVx = MyTemp2.IntegrExpr();
                            } else if (!Diff2IntegrExpr.contains("Parenthesis")) {
                                DiffVx = Term2;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(DiffVx, variables, DiffWithRespTo);
                                MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                IntegralDiffVx = MyTemp2.IntegrExpr();
                            }

                            final String Ux1 = Ux;
                            final String DiffUx1 = DiffUx;
                            final String DiffVx1 = DiffVx;
                            final String IntegralDiffVx1 = IntegralDiffVx;
                            IntegrExpr MyTerm2 = (() -> "");
                            if ((IntegralDiffVx1.isEmpty()) && (!DiffVx1.isEmpty())) {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:Vx" + ConsoleColors.RESET);
                            } else if ((Ux.isEmpty()) && (!DiffUx1.isEmpty())) {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:Ux" + ConsoleColors.RESET);
                            } else if (myOrderUx <= myOrderVx) {
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(DiffUx1 + "*" + IntegralDiffVx1, variables, DiffWithRespTo);
                                MyTerm2 = DiffrIntegrSep.parseIntegr(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                            } else if (DETAIL(Term1, Term2, DiffWithRespTo)) {
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(DiffUx1 + "*" + IntegralDiffVx1, variables, DiffWithRespTo);
                                MyTerm2 = DiffrIntegrSep.parseIntegr(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                            } else {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:RUNAWAY Integration:myOrderUx=" + myOrderUx + " myOrderVx=" + myOrderVx + ConsoleColors.RESET);
                            }
                            final String MyTerm2Str = MyTerm2.IntegrExpr();
                            // Integral u*Diffv= uv - Integral vDiffu
                            // Integral Ux*DiffV= Ux*IntegralDiffVx - Integral IntegralDiffVx*DiffUx

                            x = (() -> Ux1 + "*(" + IntegralDiffVx1 + ")-" + MyTerm2Str);
                            log.info("Integr:ApplyFuncRules:IntegrationByParts:x.Expr()=" + x.Expr());
                            log.info("Integr:ApplyFuncRules:IntegrationByParts:Vx1=" + IntegralDiffVx1);
                            log.info("Integr:ApplyFuncRules:IntegrationByParts:DiffUx1=" + DiffUx1);
                            log.info("Integr:ApplyFuncRules:IntegrationByParts:DiffVx1=" + DiffVx1);
                            log.info("Integr:ApplyFuncRules:IntegrationByParts:Ux1=" + Ux1);
                        }
                    } else {
                        log.info("Integr:ApplyFuncRules:IntegrationByParts:Maths OR This Software does not support :(" + Diff1.IntegrExpr() + ") * (" + Diff2.IntegrExpr() + ")");
                    }
                    log.info("Integr:parseIntegr:IntegrationByParts:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("IntegrationByPartsBothContainX")) {
                    if (Supported == 1) {
                        if (((Term4.contains(DiffWithRespTo + "^")) && (!Term4.contains("*")) && (!Term4.contains("/")))
                                && ((Term2.contains(DiffWithRespTo + "^")) && (!Term2.contains("*")) && (!Term2.contains("/")))) {
                            IntegrExpr MyTemp2 = (() -> "");
                            Map<String, String> variablesBackupState = new HashMap();
                            BackupDiffIntegrStateAll(variablesBackupState);
                            MathsContxtLAv.ChaosPrevention();
                            MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(Term4 + "*" + Term2, variables, DiffWithRespTo);
                            MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                            RestoreDiffIntegrStateAll(variablesBackupState);
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:MyTemp2.IntegrExpr()=" + MyTemp2.IntegrExpr() + " Diff2IntegrExpr=" + Diff2IntegrExpr + " Diff1IntegrExpr=" + Diff1IntegrExpr);
                            final String MyTemp2Str = MyTemp2.IntegrExpr();
                            x = (() -> MyTemp2Str);
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:x.Expr()=" + x.Expr());
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:Term4=" + Term4);
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:Term2=" + Term2);
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:MyTemp.SimpleExpr()=" + MyTemp.SimpleExpr());
                        } else {
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:Maths OR This Software does not support :" + Term4 + " * " + Term2);
                        }
                    } else {
                        log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:Maths OR This Software does not support :" + Diff1.IntegrExpr() + " * " + Diff2.IntegrExpr());
                    }
                } else if (Rule.equalsIgnoreCase("ProductRule")) {
                    x = parseProductRule();
                    log.info("Integr:parseIntegr:ProductRule:x.Expr()=" + x.Expr());
                } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
                    if (Supported == 1) {
                        if ((!Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr)) && (StrOperator.equalsIgnoreCase("/"))) {
                            String Ux = "";
                            String UDashx = "";
                            String Vx = "";
                            String VDashx = "";
                            // Set Ux Parameters
                            if (Diff1IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff1IntegrExpr, DiffWithRespTo);
                                Ux = ParanExpr;
                                UDashx = ParanDiffExpr;

                            } else if (!Diff1IntegrExpr.contains("Parenthesis")) {
                                Ux = Term1;
                                UDashx = Diff2IntegrExpr;
                            }
                            // Set Vx Parameters
                            if (Diff2IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff2IntegrExpr, DiffWithRespTo);
                                Vx = ParanExpr;
                                VDashx = ParanDiffExpr;

                            } else if (!Diff2IntegrExpr.contains("Parenthesis")) {
                                Vx = Term2;
                                VDashx = Diff2IntegrExpr;
                            }
                            final String Vx1 = Vx;
                            final String UDashx1 = UDashx;
                            final String VDashx1 = VDashx;
                            final String Ux1 = Ux;
                            x = (() -> "(" + Vx1 + "*" + UDashx1 + "-" + VDashx1 + "*" + Ux1 + ")" + "/" + Vx1 + "/" + Vx1);
                        }
                    } else {
                        log.info("Integr:ApplyFuncRules:MappedQuotientRule:Maths OR This Software does not support :(" + Diff1IntegrExpr + ") / (" + Diff2IntegrExpr + ")");
                    }
                } else if (Rule.equalsIgnoreCase("QuotientRule")) {
                    x = parseQuotientRule();
                    log.info("Integr:parseIntegr:QuotientRule:x.Expr()=" + x.Expr());
                }
                log.info("Integr:parseIntegr:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseNoExponentRuleTerm() {
                IntegrLambda x = (() -> "");
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = (() -> Term1);
                }
                log.info("Integr:parseNoExponentRuleTerm:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseNoExponentRuleDiff() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    if (Term1.equalsIgnoreCase("")) {
                        x = (() -> Diff1IntegrExpr);
                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule1 :x.IntegrExpr()=" + x.Expr());
                    } else if ((Term1.contains(DiffWithRespTo)) && (Term1.contains("ln"))) {
                        x = (() -> Term1);
                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule2 :x.IntegrExpr()=" + x.Expr());
                    } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (DiffTermOperator == '*')) {
                        x = (() -> "1.0/2.0" + StrOperator + Term1 + "^2.0");
                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule3 :x.IntegrExpr()=" + x.Expr());
                    } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (DiffTermOperator == '/')) {
                        x = (() -> "ln(" + Term1 + ")");

                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule4 :x.IntegrExpr()=" + x.Expr());
                    } else if ((StrOperator.equalsIgnoreCase("/")) && (DiffTermOperator == '/')) {
                        x = (() -> Term1);
                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule5 :x=" + x);
                    } else {
                        x = (() -> Term1);
                        log.info("Integr:ApplyFuncRules:NoExponentRuleDiff:Rule6 :x.IntegrExpr()=" + x.Expr());
                    }
                }
                log.info("Integr:parseNoExponentRuleDiff:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseCoeffExponentRule() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    if (!Term4.equalsIgnoreCase("")) {
                        x = (() -> Term4 + StrOperator + Term3 + "^" + Term2);
                    } else if ((!Term3.equalsIgnoreCase("")) && (!Term3.equalsIgnoreCase("1.0"))) {
                        x = (() -> Term3 + "^" + Term2);
                    } else {
                        x = (() -> Term1);
                    }
                }
                log.info("Integr:parseCoeffExponentRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseConstantRule() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("ConstantRule")) {
                    if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                            && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                            && (Term1.equalsIgnoreCase(Term2))
                            && (Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr))
                            && (Term1.equalsIgnoreCase(Diff1IntegrExpr))
                            && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        x = (() -> "1.0");
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule1 :x.Expr()=" + x.Expr());
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        if (Term3.equalsIgnoreCase("")) {
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1 :SavedOperator=" + (char) SavedOperator);
                            if (((SavedOperator == '*') || (SavedOperator == 0) || (SavedOperator == -1)) && (!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                                if ((SavedOperator == '*')) {
                                    x = (() -> Term1 + (char) SavedOperator + Term2);
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.1 :x.Expr()=" + x.Expr());
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
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.2 :x.Expr()=" + x.Expr());
                                } else {
                                    if (Term1.startsWith("1.0")) {
                                        final String Temp = Term1.replaceFirst("1.0", "");
                                        x = (() -> Term2 + Temp);
                                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.1 :x.Expr()=" + x.Expr());
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (!Term1.equalsIgnoreCase(Diff2IntegrExpr))) {
                                        x = (() -> Diff1IntegrExpr);
                                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.2 :x.Expr()=" + x.Expr());
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1))
                                            && (Term1.equalsIgnoreCase(Diff2IntegrExpr))
                                            && (!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                        if (Term1.startsWith("/")) {
                                            x = (() -> Term2 + Term1);
                                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.3.1 :x.Expr()=" + x.Expr());
                                        } else {
                                            x = (() -> Term2 + "*" + Term1);
                                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.3 :x.Expr()=" + x.Expr());
                                        }
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr)) && (!IsAConstant(Term1, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                        x = (() -> Term2 + StrOperator + Term1);
                                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.4 :x.Expr()=" + x.Expr());
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr))) {
                                        x = (() -> Term2);
                                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.5 :x.Expr()=" + x.Expr());
                                    } else {
                                        x = (() -> Term2 + "*" + Term1);
                                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3.6 :x.Expr()=" + x.Expr());
                                    }
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1.3 :x.Expr()=" + x.Expr());
                                }
                            } else if ((!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                                x = (() -> Term1 + StrOperator + Term2);
                            }
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.1 :x.Expr()=" + x.Expr());
                        } else if (Term2.equalsIgnoreCase(DiffWithRespTo)) {
                            x = (() -> Diff1IntegrExpr + StrOperator + Term2 + "^2.0*1.0/2.0" + Term3);
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.2 :x.Expr()=" + x.Expr());
                        } else if (Diff1IntegrExpr.equalsIgnoreCase(Term2)) {
                            if ((Term1.equalsIgnoreCase(Diff2IntegrExpr)) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                                if ((IsAConstant(Term3, DiffWithRespTo)) && (!Term2.contains(Term3))) {
                                    x = (() -> Diff1IntegrExpr + Term3 + StrOperator + Diff2IntegrExpr + "^2.0*1.0/2.0");
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.A :x.Expr()=" + x.Expr());
                                } else {
                                    x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr + "^2.0*1.0/2.0");
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.B :x.Expr()=" + x.Expr());
                                }
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1 :x.Expr()=" + x.Expr());
                            } else if (Term1.equalsIgnoreCase(Diff2IntegrExpr)) {
                                if (((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (!IsAConstant(Term1, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                    x = (() -> Term2 + StrOperator + Term1);
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.A.1 :x=" + x);
                                } else if ((Term1.equalsIgnoreCase("")) && ((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                    x = (() -> "1.0" + Term3 + StrOperator + Diff2IntegrExpr);
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.A.2 :x=" + x);
                                } else if (((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (!IsAConstant(Term1, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                                    x = (() -> Term2 + StrOperator + Term1);
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.A.3 :x=" + x);
                                } else if ((!IsAConstant(Term3, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (Term2.contains(Term3))) {
                                    x = (() -> Term2);
                                    log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1.A.3 :x=" + x);
                                }
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.1 :x.Expr()=" + x.Expr());
                            } else {
                                x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.3.2 :x.Expr()=" + x.Expr());
                            }
                        } else {
                            x = (() -> Diff1IntegrExpr + StrOperator + Term2 + Term3);
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2.4 :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule2 :x.Expr()=" + x.Expr());
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2IntegrExpr))) {
                        x = (() -> Diff1IntegrExpr);
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule3 :x.Expr()=" + x.Expr());
                    } else if ((((IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                            || ((!IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                            || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))))) && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))) {
                        if (((SavedOperator == 0) || (SavedOperator == -1)) && (!IsAConstant(Diff2IntegrExpr, DiffWithRespTo))) {
                            x = (() -> Diff1IntegrExpr);
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.1 :x.Expr()=" + x.Expr());
                        } else if (((SavedOperator == 0) || (SavedOperator == -1))
                                && (Term1.equalsIgnoreCase(Diff2IntegrExpr))
                                && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))
                                && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                && (Term2.contains(Term3)) && (Term2.contains(Term1))) {
                            if ((Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr)) && (Term3.equalsIgnoreCase(""))) {
                                x = (() -> Term2);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.2.1 :x.Expr()=" + x.Expr());
                            } else if (!Term3.equalsIgnoreCase("")) {
                                x = (() -> "1.0" + Term3 + StrOperator + Term1);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.2.2 :x.Expr()=" + x.Expr());
                            } else if ((Term3.equalsIgnoreCase("")) && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) && (Term2.contains(Diff2IntegrExpr))) {
                                x = (() -> Diff1IntegrExpr + StrOperator + DiffWithRespTo);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.2.3 :x.Expr()=" + x.Expr());
                            }
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.2 :x.Expr()=" + x.Expr());
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr)) && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            if (Term1.equalsIgnoreCase("0.0")) {
                                x = (() -> Term1);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.3 :x.Expr()=" + x.Expr());
                            } else if (!Term1.equalsIgnoreCase("0.0")) {
                                x = (() -> Term2 + StrOperator + Term1);
                                log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.4 :x.Expr()=" + x.Expr());
                            }
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr)) && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))) {
                            x = (() -> "1.0" + Term3 + StrOperator + Term1);
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.5 :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                            log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4.6 :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule4 :x.Expr()=" + x.Expr());
                    } else if ((((IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                            || ((!IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                            || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))))) && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))) {
                        x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule5 :x.Expr()=" + x.Expr());
                    } else if ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                        x = (() -> Term2);
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule6 :x.Expr()=" + x.Expr());
                    } else if ((!IsAConstant(Diff1IntegrExpr, DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                        x = (() -> Diff1IntegrExpr);
                        log.info("Integr:ApplyFunc_ConstantRules:ConstantRule :Rule7 :x.Expr()=" + x.Expr());
                    }
                }
                log.info("Integr:ApplyFuncRules:ConstantRule:x.Expr()=" + x.Expr());
                log.info("Integr:parseConstantRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseSumRule() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        if (Diff2IntegrExpr.contains(DiffWithRespTo)) {
                            if ((Diff2IntegrExpr.startsWith("-"))) {
                                x = (() -> Diff1IntegrExpr + Diff2IntegrExpr);
                            } else if ((Diff2IntegrExpr.startsWith("+"))) {
                                x = (() -> Diff1IntegrExpr + Diff2IntegrExpr);
                            } else {
                                x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                            }
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule1.1 :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo);
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule1.2 :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule1 :x.Expr()=" + x.Expr());
                    } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        x = (() -> Term1 + StrOperator + Term2);
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule2 :x.Expr()=" + x.Expr());
                    } else if ((!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        x = (() -> Diff1IntegrExpr);
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule3 :x.Expr()=" + x.Expr());
                    } else if ((!Term2.contains(DiffWithRespTo)) && (IsAConstant(Term2, DiffWithRespTo))) {
                        x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule5 :x.Expr()=" + x.Expr());
                    } else if ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                        if (Diff2IntegrExpr.equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                            x = (() -> Diff1IntegrExpr + StrOperator + Term2 + "*" + DiffWithRespTo);
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6A :x.Expr()=" + x.Expr());
                        } else if (Diff2IntegrExpr.equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                            x = (() -> Diff1IntegrExpr + StrOperator + Term2 + "*" + DiffWithRespTo + "^2.0" + "*1.0/2.0");
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6B :x.Expr()=" + x.Expr());
                        } else if (!Diff2IntegrExpr.contains(DiffWithRespTo)) {
                            x = (() -> Term1 + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo);
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6C :x.Expr()=" + x.Expr());
                        } else {
                            if ((IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) || ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))))) {
                                x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo);
                                log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6D.1 :x.Expr()=" + x.Expr());
                            } else {
                                x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6D.2 :x.Expr()=" + x.Expr());
                            }
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6D :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule6 :x.Expr()=" + x.Expr());
                    } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))) {
                        if ((IsAConstant(Term2, DiffWithRespTo)) || ((!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))))) {
                            x = (() -> Term1 + StrOperator + Term2 + "*" + DiffWithRespTo);
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule7A.1 :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Term1 + StrOperator + Term2);
                            log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule7A.2 :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_SumSubRules:SumRule or SubtractionRule :Rule7 :x.Expr()=" + x.Expr());
                    }
                    //Drops Term2 && Diff2.IntegrExpr()
                    log.info("Integr:ApplyFuncRules:SumRule OR SubtractionRule:x.Expr()=" + x.Expr());
                }
                log.info("Integr:parseSumRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseProductRule() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        ;//x = (() -> Term3 + "*" + Diff1.IntegrExpr() + "+" + Term1 + "*" + Diff2.IntegrExpr());
                        log.info("Integr:ApplyFunc_ProductRules:ProductRule:Maths OR This Software does not support :" + Term3);
                        log.info("Integr:ApplyFunc_ProductRules:Rule1 :x.Expr()=" + x.Expr());
                    } else if (Diff1IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = (() -> Diff2IntegrExpr);
                        log.info("Integr:ApplyFunc_ProductRules:Rule2 :x.Expr()=" + x.Expr());
                    } else if ((Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1IntegrExpr, DiffWithRespTo))) {
                        //Differentiation of x term:
                        x = (() -> Diff1IntegrExpr + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0");
                        log.info("Integr:ApplyFunc_ProductRules:Rule3 :x.Expr()=" + x.Expr());
                    } else if ((Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1IntegrExpr, DiffWithRespTo))) {
                        //Differentiation of x term with Independent Constant:
                        x = (() -> Diff1IntegrExpr + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0");
                        //x = (() -> Diff1.IntegrExpr());
                        log.info("Integr:ApplyFunc_ProductRules:Rule4 :x.Expr()=" + x.Expr());
                    } else if (!Diff1IntegrExpr.equalsIgnoreCase("")) {
                        if ((((IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                                || ((!IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("Unknown")))))
                                && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))
                                || ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("Unknown")))))) {
                            if (Diff1IntegrExpr.contains("@IndependentFactor@")) {
                                Split(Diff1IntegrExpr, DiffWithRespTo);
                                final String TempStrDiff1 = FactorsDiffExpr;
                                x = (() -> TempStrDiff1);
                            } else if (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                                x = (() -> Diff1IntegrExpr + "*" + Diff2IntegrExpr + "^2.0");
                            } else {
                                x = (() -> Diff1IntegrExpr + "*" + Diff2IntegrExpr + "*" + DiffWithRespTo);
                            }
                            log.info("Integr:ApplyFunc_ProductRules:Rule5A :x.Expr()=" + x.Expr());
                        } else {
                            if ((Diff2IntegrExpr.startsWith("1.0*")) || (Diff2IntegrExpr.startsWith("1.0/"))) {
                                String Temp = Diff2IntegrExpr.replaceFirst("1.0", "");
                                if ((Diff1IntegrExpr.endsWith("/")) && (Term1.startsWith("/"))) {
                                    String Temp1 = Diff1IntegrExpr.replaceFirst("/", "");
                                    x = (() -> Temp1 + Term1 + Temp);
                                    log.info("Integr:ApplyFunc_ProductRules:Rule5B.1 :x.Expr())=" + x.Expr());
                                } else if (Diff1IntegrExpr.endsWith("/")) {
                                    String Temp1 = Diff1IntegrExpr.replaceFirst("/", "");
                                    x = (() -> Temp1 + Temp);
                                    log.info("Integr:ApplyFunc_ProductRules:Rule5B.2 :x.Expr()=" + x.Expr());
                                } //Need to uncomment below code
                                else {
                                    if ((Term1.startsWith("/")) && (Diff1IntegrExpr.startsWith("1.0" + Term1))) {
                                        if ((Temp.startsWith("/")) || (Temp.startsWith("*"))) {
                                            x = (() -> "1.0" + Term1 + Temp);
                                        } else {
                                            x = (() -> "1.0" + Term1 + StrOperator + Temp);
                                        }
                                    } else if ((!Term1.equalsIgnoreCase("")) && (!Diff1IntegrExpr.contains(Term1))) {
                                        x = (() -> Diff1IntegrExpr + Term1 + Temp);
                                        log.info("Integr:ApplyFunc_ProductRules:Rule5B.3.A :x.Expr()=" + x.Expr());
                                    } else {
                                        if ((Temp.startsWith("/")) || (Temp.startsWith("*"))) {
                                            x = (() -> Diff1IntegrExpr + Temp);
                                        } else {
                                            x = (() -> Diff1IntegrExpr + StrOperator + Temp);
                                        }
                                        log.info("Integr:ApplyFunc_ProductRules:Rule5B.3.B :x.Expr()=" + x.Expr());
                                    }
                                    log.info("Integr:ApplyFunc_ProductRules:Rule5B.3 :x.Expr()=" + x.Expr());
                                }
                                log.info("Integr:ApplyFunc_ProductRules:Rule5B :x.Expr()=" + x.Expr());
                            } else if ((Term1.startsWith("/")) && (Diff2IntegrExpr.contains(DiffWithRespTo))) {
                                x = (() -> "1.0" + Term1 + "*" + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_ProductRules:Rule5C :x.Expr()=" + x.Expr());
                            } else if ((Diff1IntegrExpr.contains("*2.0")) && (Diff2IntegrExpr.contains(DiffWithRespTo + "^2.0"))) {
                                x = (() -> Diff1IntegrExpr + "/2.0" + "*" + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_ProductRules:Rule5D :x.Expr()=" + x.Expr());
                            } else {
                                x = (() -> Diff1IntegrExpr + "*" + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_ProductRules:Rule5E :x.Expr()=" + x.Expr());
                            }
                        }
                        log.info("Integr:ApplyFunc_ProductRules:Rule5 :x.Expr()=" + x.Expr());
                    } else if (Diff1IntegrExpr.equalsIgnoreCase("")) {
                        x = (() -> Diff2IntegrExpr);
                        log.info("Integr:ApplyFunc_ProductRules:Rule6 :x.Expr()=" + x.Expr());
                    }
                }
                log.info("Integr:parseProductRule:Final:x.Expr()=" + x.Expr());
                return x;
            }

            IntegrLambda parseQuotientRule() {
                IntegrLambda x = (() -> "");
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("QuotientRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = (() -> ("(" + Term2 + "*" + Diff1IntegrExpr + "-" + Term1 + "*" + Diff2IntegrExpr) + "/" + "(" + Term2 + "*" + Term2 + ")" + ")");
                        log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule1 :x.Expr()=" + x.Expr());
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo))) {
                        if (Diff1IntegrExpr.equalsIgnoreCase("1.0")) {
                            x = (() -> "ln(" + DiffWithRespTo + ")");
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule2A :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1IntegrExpr + "*" + "ln(" + DiffWithRespTo + ")");
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule2B :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule2 :x.Expr()=" + x.Expr());
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff1IntegrExpr.equalsIgnoreCase("1.0"))) {
                        if (Diff2IntegrExpr.startsWith("1.0")) {
                            String Temp = Diff2IntegrExpr.replaceFirst("1.0", "");
                            if ((Term2.equalsIgnoreCase(Diff2IntegrExpr)) && (!Term1.equalsIgnoreCase(""))) {
                                x = (() -> "1.0" + Term1 + Temp);
                            } else {
                                x = (() -> Diff1IntegrExpr + Temp);
                            }
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3A :x.Expr()=" + x.Expr());
                        } else if (!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) {
                            if (Diff2IntegrExpr.startsWith(StrOperator)) {
                                x = (() -> Diff1IntegrExpr + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3B.1 :x.Expr()=" + x.Expr());
                            } else {
                                x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                                log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3B.2 :x.Expr()=" + x.Expr());
                            }
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3B :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1IntegrExpr + StrOperator + Diff2IntegrExpr);
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3C :x.Expr()=" + x.Expr());
                        }
                        log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule3 :x.Expr()=" + x.Expr());
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.contains(Diff1IntegrExpr + "/"))) {
                        if (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                            x = (() -> "ln(" + DiffWithRespTo + ")");
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule4A :x.Expr()=" + x.Expr());
                        } else {
                            x = (() -> Diff1IntegrExpr + "/" + Diff2IntegrExpr);
                            log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule4B :x.Expr()=" + x.Expr());
                        }
//                else {
//                    x = (() -> Diff2.IntegrExpr());
//                    log.info("Integr:ApplyFuncRules:QuotientRule:Rule4C :x.Expr()=" + x.Expr());
//                }
                    } else {
                        x = (() -> Diff2IntegrExpr);
                        log.info("Integr:ApplyFunc_QuotientRules:QuotientRule:Rule4 :x.Expr()=" + x.Expr());
                    }
                }
                log.info("Integr:parseQuotientRule:Final:x.Expr()=" + x.Expr());
                return x;
            }
        }.parseIntegr();
    }

    public static String ApplyLambdaString(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        log.info("Integr:ApplyLambdaString:Initial :AlgoName(Calling Function)=" + AlgoName);
        log.info("Integr:ApplyLambdaString:Initial :Term1=" + Term1);
        log.info("Integr:ApplyLambdaString:Initial :Diff1.IntegrExpr()=" + Diff1.IntegrExpr());
        log.info("Integr:ApplyLambdaString:Initial :Term2=" + Term2);
        log.info("Integr:ApplyLambdaString:Initial :Diff2.IntegrExpr()=" + Diff2.IntegrExpr());
        log.info("Integr:ApplyLambdaString:Initial :Term3=" + Term3);
        log.info("Integr:ApplyLambdaString:Initial :Term4=" + Term4);
        log.info("Integr:ApplyLambdaString:Initial :StrOperator=" + StrOperator);
        log.info("Integr:ApplyLambdaString:Initial :Rule=" + Rule);
        log.info("Integr:ApplyLambdaString:Initial :LowLevelRules=" + LowLevelRules);
        log.info("Integr:ApplyLambdaString:Initial :SavedOperator=" + (char) SavedOperator);
        log.info("Integr:ApplyLambdaString:Initial :TermOperator=" + (char) TermOperator);
        log.info("Integr:ApplyLambdaString:Initial :DiffTermOperator=" + (char) DiffTermOperator);
        log.info("Integr:ApplyLambdaString:Initial :");
        log.info("Integr:ApplyLambdaString:Initial :");
        log.info("Integr:ApplyLambdaString:Initial :");
        String x = "";
        IntegrLambdaStr y = (() -> "");
        String[] MyTemp = new String[2];
        if (Diff2.IntegrExpr().contains("@Parenthesis@")) {
            MyTemp = Diff2.IntegrExpr().split("@Parenthesis@");
            Term2 = MyTemp[1];
        }
        y = parseIntegrStr(AlgoName, Term1, Diff1, Term2, Diff2, Term3, Term4, StrOperator, Rule, DiffWithRespTo, TermOperator, LowLevelRules);
        x = y.StrExpr();
        return x;
    }

    @FunctionalInterface
    public interface IntegrLambdaStr {

        String StrExpr();
    }

    public static IntegrLambdaStr parseIntegrStr(String AlgoName, String Term1, IntegrExpr Diff1, String Term2, IntegrExpr Diff2, String Term3, String Term4, String StrOperator, String Rule, String DiffWithRespTo, int TermOperator, String LowLevelRules) {
        return new Object() {

            IntegrLambdaStr parseIntegrStr() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                int Supported = 1;
                if ((Diff1IntegrExpr.contains(DiffWithRespTo)) && (Diff2IntegrExpr.contains(DiffWithRespTo)) && (Rule.equalsIgnoreCase("MappedQuotientRule")) && (StrOperator.equalsIgnoreCase("/"))) {
                    Supported = 0;
                }
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    x = parseNoExponentRuleTerm();
                    log.info("Integr:String:parseIntegrStr:NoExponentRuleTerm:x=" + x);
                } else if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    x = parseNoExponentRuleDiff();
                    log.info("Integr:String:parseIntegrStr:NoExponentRuleDiff:x=" + x);
                } else if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
                    x = parseCoeffExponentRule();
                    log.info("Integr:String:parseIntegrStr:CoeffExponentRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("ConstantRule")) {
                    x = parseConstantRule();
                    log.info("Integr:String:parseIntegrStr:ConstantRule:x=" + x);
                } else if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    x = parseSumRule();
                    log.info("Integr:String:parseIntegrStr:SumRule OR SubtractionRule:x)=" + x);
                } else if (Rule.equalsIgnoreCase("ExponentRule")) {

                    log.info("Integr:String:parseIntegrStr:ExponentRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("IntegrationByParts")) {
                    if (Supported == 1) {
                        // Integral u*Diffv= uv - Integral vDiffu
                        // Integral Ux*DiffV= Ux*IntegralDiffVx - Integral IntegralDiffVx*DiffUx
                        if ((!Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr)) && (StrOperator.equalsIgnoreCase("*"))) {
                            String Ux = "";
                            String DiffUx = "";
                            String DiffVx = "";
                            String IntegralDiffVx = "";
                            // Determine Order ParanExpr
                            // Determine Order Term1
                            MathsContxtLAv.OrderVar expOrder = MathsContxtLAv.parseOrder(Term1, DiffWithRespTo);
                            int myOrderUx = (int) Double.parseDouble(expOrder.eval());
                            log.info("Integr:ApplyFuncString: " + DiffWithRespTo + " Term1=" + Term1 + " myOrderUx=" + myOrderUx);
                            // Determine Order Term2
                            expOrder = MathsContxtLAv.parseOrder(Term2, DiffWithRespTo);
                            int myOrderVx = (int) Double.parseDouble(expOrder.eval());
                            log.info("Integr:ApplyFuncString: " + DiffWithRespTo + " Term2=" + Term2 + " myOrderVx=" + myOrderVx);

                            //Choose Ux 
                            // Choose Vx
                            // Set Ux Parameters
                            if (Diff1IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff1IntegrExpr, DiffWithRespTo);
                                Ux = ParanExpr;
                            } else if ((!Diff1IntegrExpr.contains("Parenthesis")) && (Diff1IntegrExpr.equalsIgnoreCase(Term1))) {
                                Ux = Term1;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(Term1, variables, DiffWithRespTo);
                                DiffExpr MyUxDash = parseDiff(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                DiffUx = MyUxDash.DiffExpr();
                                log.info("Integr:ApplyFuncString:IntegrationByParts:Ux=" + Ux + " Term1=" + Term1);
                            }
                            // Set Vx Parameters
                            IntegrExpr MyTemp2 = (() -> "");
                            if (Diff2IntegrExpr.contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff2IntegrExpr, DiffWithRespTo);
                                DiffVx = ParanExpr;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(DiffVx, variables, DiffWithRespTo);
                                MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                IntegralDiffVx = MyTemp2.IntegrExpr();
                            } else if (!Diff2IntegrExpr.contains("Parenthesis")) {
                                DiffVx = Term2;
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(DiffVx, variables, DiffWithRespTo);
                                MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                                IntegralDiffVx = MyTemp2.IntegrExpr();
                            }

                            final String Ux1 = Ux;
                            final String DiffUx1 = DiffUx;
                            final String DiffVx1 = DiffVx;
                            final String IntegralDiffVx1 = IntegralDiffVx;
                            IntegrExpr MyTerm2 = (() -> "");
                            if ((IntegralDiffVx1.isEmpty()) && (!DiffVx1.isEmpty())) {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:Vx" + ConsoleColors.RESET);
                            } else if ((Ux.isEmpty()) && (!DiffUx1.isEmpty())) {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:Ux" + ConsoleColors.RESET);
                            } else if (myOrderUx <= myOrderVx) {
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(DiffUx1 + "*" + IntegralDiffVx1, variables, DiffWithRespTo);
                                MyTerm2 = DiffrIntegrSep.parseIntegr(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                            } else if (DETAIL(Term1, Term2, DiffWithRespTo)) {
                                Map<String, String> variablesBackupState = new HashMap();
                                BackupDiffIntegrStateAll(variablesBackupState);
                                MathsContxtLAv.ChaosPrevention();
                                MathsContxtLAv.SimpleExpression MyTempTerm1 = MathsContxtLAv.parseSimple(DiffUx1 + "*" + IntegralDiffVx1, variables, DiffWithRespTo);
                                MyTerm2 = DiffrIntegrSep.parseIntegr(MyTempTerm1.SimpleExpr(), variables, DiffWithRespTo);
                                RestoreDiffIntegrStateAll(variablesBackupState);
                            } else {
                                System.out.println(ConsoleColors.RED + "Integr:ApplyFuncString:IntegrationByParts:FORMULA WRONG:RUNAWAY Integration:myOrderUx=" + myOrderUx + " myOrderVx=" + myOrderVx + ConsoleColors.RESET);
                            }
                            final String MyTerm2Str = MyTerm2.IntegrExpr();
                            // Integral u*Diffv= uv - Integral vDiffu
                            // Integral Ux*DiffV= Ux*IntegralDiffVx - Integral IntegralDiffVx*DiffUx

                            x = Ux1 + "*(" + IntegralDiffVx1 + ")-" + MyTerm2Str;
                            log.info("Integr:ApplyFuncString:IntegrationByParts:x=" + x);
                            log.info("Integr:ApplyFuncString:IntegrationByParts:Vx1=" + IntegralDiffVx1);
                            log.info("Integr:ApplyFuncString:IntegrationByParts:DiffUx1=" + DiffUx1);
                            log.info("Integr:ApplyFuncString:IntegrationByParts:DiffVx1=" + DiffVx1);
                            log.info("Integr:ApplyFuncString:IntegrationByParts:Ux1=" + Ux1);
                        }
                    } else {
                        log.info("Integr:ApplyFuncRulesString:IntegrationByParts:Maths OR This Software does not support :(" + Diff1.IntegrExpr() + ") * (" + Diff2.IntegrExpr() + ")");
                    }
                    log.info("Integr:String:parseIntegrStr:IntegrationByParts:x=" + x);
                } else if (Rule.equalsIgnoreCase("IntegrationByPartsBothContainX")) {
                    if (Supported == 1) {
                        if (((Term4.contains(DiffWithRespTo + "^")) && (!Term4.contains("*")) && (!Term4.contains("/")))
                                && ((Term2.contains(DiffWithRespTo + "^")) && (!Term2.contains("*")) && (!Term2.contains("/")))) {
                            IntegrExpr MyTemp2 = (() -> "");
                            Map<String, String> variablesBackupState = new HashMap();
                            BackupDiffIntegrStateAll(variablesBackupState);
                            MathsContxtLAv.ChaosPrevention();
                            MathsContxtLAv.SimpleExpression MyTemp = MathsContxtLAv.parseSimple(Term4 + "*" + Term2, variables, DiffWithRespTo);
                            MyTemp2 = DiffrIntegrSep.parseIntegr(MyTemp.SimpleExpr(), variables, DiffWithRespTo);
                            RestoreDiffIntegrStateAll(variablesBackupState);
                            log.info("Integr:ApplyFuncRules:IntegrationByPartsBothContainX:MyTemp2.IntegrExpr()=" + MyTemp2.IntegrExpr() + " Diff2IntegrExpr=" + Diff2IntegrExpr + " Diff1IntegrExpr=" + Diff1IntegrExpr);
                            final String MyTemp2Str = MyTemp2.IntegrExpr();
                            x = MyTemp2Str;
                            log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:x=" + x);
                            log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:Term4=" + Term4);
                            log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:Term2=" + Term2);
                            log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:MyTemp.SimpleExpr()=" + MyTemp.SimpleExpr());
                        } else {
                            log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:Maths OR This Software does not support :" + Term4 + " * " + Term2);
                        }
                    } else {
                        log.info("Integr:ApplyFuncString:IntegrationByPartsBothContainX:Maths OR This Software does not support :" + Diff1.IntegrExpr() + " * " + Diff2.IntegrExpr());
                    }
                } else if (Rule.equalsIgnoreCase("ProductRule")) {
                    x = parseProductRule();
                    log.info("Integr:String:parseIntegrStr:ProductRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("MappedQuotientRule")) {
                    if (Supported == 1) {
                        if ((!Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr())) && (StrOperator.equalsIgnoreCase("*"))) {
                            String Ux = "";
                            String UDashx = "";
                            String Vx = "";
                            String VDashx = "";
                            // Set Ux Parameters
                            if (Diff1.IntegrExpr().contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff1.IntegrExpr(), DiffWithRespTo);
                                Ux = ParanExpr;
                                UDashx = ParanDiffExpr;

                            } else if (!Diff1.IntegrExpr().contains("Parenthesis")) {
                                Ux = Term1;
                                UDashx = Diff2.IntegrExpr();
                            }
                            // Set Vx Parameters
                            if (Diff2.IntegrExpr().contains("Parenthesis")) {
                                Expr_Func_Split_Integr(Diff2.IntegrExpr(), DiffWithRespTo);
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
                    log.info("Integr:String:parseIntegrStr:MappedQuotientRule:x=" + x);
                } else if (Rule.equalsIgnoreCase("QuotientRule")) {
                    x = parseQuotientRule();
                    log.info("Integr:String:parseIntegrStr:QuotientRule:x=" + x);
                }
                log.info("Integr:String:parseIntegrStr:Final:x=" + x);
                final String y = x;
                return (() -> y);
            }

            String parseNoExponentRuleTerm() {
                String x = "";
                if (Rule.equalsIgnoreCase("NoExponentRuleTerm")) {
                    if (Term1.equalsIgnoreCase("")) {
                        x = Diff1.IntegrExpr();
                    } else {
                        x = Term1;
                    }
                }
                log.info("Integr:String:parseNoExponentRuleTerm:Final:x=" + x);
                return x;
            }

            String parseNoExponentRuleDiff() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                if (Rule.equalsIgnoreCase("NoExponentRuleDiff")) {
                    if (Term1.equalsIgnoreCase("")) {
                        x = Diff1.IntegrExpr();
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule1 :x=" + x);
                    } else if ((Term1.contains(DiffWithRespTo)) && (Term1.contains("ln"))) {
                        x = Term1;
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule2 :x=" + x);
                    } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (DiffTermOperator == '*')) {
                        x = "1.0/2.0" + StrOperator + Term1 + "^2.0";
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule3 :x=" + x);
                    } else if ((Term1.contains(DiffWithRespTo)) && (!Term1.contains("^")) && (DiffTermOperator == '/')) {
                        x = "ln(" + Term1 + ")";
                        //LADiffTermOperator="*";
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule4 :x=" + x);
                    } else if ((StrOperator.equalsIgnoreCase("/")) && (DiffTermOperator == '/')) {
                        x = Term1;
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule5 :x=" + x);
                    } else {
                        x = Term1;
                        log.info("Integr:ApplyFuncRulesString:NoExponentRuleDiff:Rule6 :x=" + x);
                    }
                }
                log.info("Integr:String:parseNoExponentRuleDiff:Final:x=" + x);
                return x;
            }

            String parseCoeffExponentRule() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                if (Rule.equalsIgnoreCase("CoeffExponentRule")) {
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
                }
                log.info("Integr:String:parseCoeffExponentRule:Final:x=" + x);
                return x;
            }

            String parseConstantRule() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("ConstantRule")) {
                    if (((Term1 != null) && (!Term1.equalsIgnoreCase("")))
                            && ((Term2 != null) && (!Term2.equalsIgnoreCase("")))
                            && (Term1.equalsIgnoreCase(Term2))
                            && (Diff1.IntegrExpr().equalsIgnoreCase(Diff2.IntegrExpr()))
                            && (Term1.equalsIgnoreCase(Diff1.IntegrExpr()))
                            && (Term1.equalsIgnoreCase(DiffWithRespTo)) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        x = "1.0";
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule1 :x=" + x);
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (!IsAConstant(Term1, DiffWithRespTo))) {
                        if (Term3.equalsIgnoreCase("")) {
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1 :SavedOperator=" + (char) SavedOperator);
                            if (((SavedOperator == '*') || (SavedOperator == 0) || (SavedOperator == -1)) && (!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                                if ((SavedOperator == '*')) {
                                    x = Term1 + (char) SavedOperator + Term2;
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.1 :x=" + x);
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
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.2 :x=" + x);
                                } else {
                                    if (Term1.startsWith("1.0")) {
                                        final String Temp = Term1.replaceFirst("1.0", "");
                                        x = Term2 + Temp;
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.1 :x=" + x);
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (!Term1.equalsIgnoreCase(Diff2.IntegrExpr()))) {
                                        x = Diff1.IntegrExpr();
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.2 :x=" + x);
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1))
                                            && (Term1.equalsIgnoreCase(Diff2.IntegrExpr()))
                                            && (!IsAConstant(Term1, DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                        if (Term1.startsWith("/")) {
                                            x = Term2 + Term1;
                                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.3.1 :x=" + x);
                                        } else {
                                            x = Term2 + "*" + Term1;
                                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.3.2 :x=" + x);
                                        }
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.3 :x=" + x);
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr)) && (!IsAConstant(Term1, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                        x = Term2 + StrOperator + Term1;
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.4 :x=" + x);
                                    } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2IntegrExpr))) {
                                        x = Term2;
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.5 :x=" + x);
                                    } else {
                                        x = Term2 + "*" + Term1;
                                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3.6 :x=" + x);
                                    }
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1.3 :x=" + x);
                                }
                            } else if ((!StrOperator.equalsIgnoreCase("\\+")) && (!StrOperator.equalsIgnoreCase("-"))) {
                                x = Term1 + StrOperator + Term2;
                            }
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.1 :x=" + x);
                        } else if (Term2.equalsIgnoreCase(DiffWithRespTo)) {
                            x = Diff1.IntegrExpr() + StrOperator + Term2 + "^2.0*1.0/2.0" + Term3;
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.2 :x=" + x);
                        } else if (Diff1.IntegrExpr().equalsIgnoreCase(Term2)) {
                            if ((Term1.equalsIgnoreCase(Diff2.IntegrExpr())) && (Term1.equalsIgnoreCase(DiffWithRespTo))) {
                                if ((IsAConstant(Term3, DiffWithRespTo)) && (!Term2.contains(Term3))) {
                                    x = Diff1.IntegrExpr() + Term3 + StrOperator + Diff2.IntegrExpr() + "^2.0*1.0/2.0";
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.A :x=" + x);
                                } else {
                                    x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr() + "^2.0*1.0/2.0";
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.B :x=" + x);
                                }
                            } else if (Term1.equalsIgnoreCase(Diff2IntegrExpr)) {
                                if (((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (!IsAConstant(Term1, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                    x = Term2 + StrOperator + Term1;
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.A.1 :x=" + x);
                                } else if ((Term1.equalsIgnoreCase("")) && ((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                                    x = "1.0" + Term3 + StrOperator + Diff2IntegrExpr;
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.A.2 :x=" + x);
                                } else if (((Term3.startsWith("*")) || (Term3.startsWith("/")))
                                        && (Term2.equalsIgnoreCase(Diff1IntegrExpr))
                                        && (!IsAConstant(Term2, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (!IsAConstant(Term1, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("Var")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                                    x = Term2 + StrOperator + Term1;
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.A.3 :x=" + x);
                                } else if ((!IsAConstant(Term3, DiffWithRespTo))
                                        && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                        && (Term2.contains(Term3))) {
                                    x = Term2;
                                    log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1.A.3 :x=" + x);
                                }
                                log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.1 :x=" + x);
                            } else {
                                x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                                log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.3.2 :x=" + x);
                            }
                        } else {
                            x = Diff1.IntegrExpr() + StrOperator + Term2 + Term3;
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2.4 :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule2 :x=" + x);
                    } else if (((Term1 != null) && (!Term1.equalsIgnoreCase(""))) && (Term2.equalsIgnoreCase(Diff2.IntegrExpr()))) {
                        x = Diff1.IntegrExpr();
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule3 :x=" + x);
                    } else if ((IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) || ((!IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))) {
                        if (((SavedOperator == 0) || (SavedOperator == -1)) && (!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))) {
                            x = Diff1.IntegrExpr();
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.1 :x=" + x);
                        } else if (((SavedOperator == 0) || (SavedOperator == -1))
                                && (Term1.equalsIgnoreCase(Diff2.IntegrExpr()))
                                && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))
                                && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))
                                && (Term2.contains(Term3)) && (Term2.contains(Term1))) {
                            if ((Diff1IntegrExpr.equalsIgnoreCase(Diff2IntegrExpr)) && (Term3.equalsIgnoreCase(""))) {
                                x = Term2;
                                log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.2.1 :x=" + x);
                            } else if (!Term3.equalsIgnoreCase("")) {
                                x = "1.0" + Term3 + StrOperator + Term1;
                                log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.2.2 :x=" + x);
                            } else if ((Term3.equalsIgnoreCase("")) && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) && (Term2.contains(Diff2IntegrExpr))) {
                                x = Diff1IntegrExpr + StrOperator + DiffWithRespTo;
                                log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.2.3 :x=" + x);
                            }
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.2 :x=" + x);
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2.IntegrExpr())) && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && (!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            x = Term2 + StrOperator + Term1;
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.3 :x=" + x);
                        } else if (((SavedOperator == 0) || (SavedOperator == -1)) && (Term1.equalsIgnoreCase(Diff2.IntegrExpr())) && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))) {
                            x = "1.0" + Term3 + StrOperator + Term1;
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.4 :x=" + x);
                        } else {
                            x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                            log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4.5 :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule4 :x=" + x);
                    } else if ((IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) || ((!IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) && (IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo))) {
                        x = Diff1.IntegrExpr() + StrOperator + Diff2.IntegrExpr();
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule5 :x=" + x);
                    } else if ((!IsAConstant(Diff2.IntegrExpr(), DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                        x = Term2;
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule6 :x=" + x);
                    } else if ((!IsAConstant(Diff1.IntegrExpr(), DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("Unknown"))) {
                        x = Diff1.IntegrExpr();
                        log.info("Integr:ApplyFuncString_ConstantRules:ConstantRule :Rule7 :x=" + x);
                    }
                }
                log.info("Integr:String:parseConstantRule:Final:x=" + x);
                return x;
            }

            String parseSumRule() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if ((Rule.equalsIgnoreCase("SumRule"))
                        || (Rule.equalsIgnoreCase("SubtractionRule"))) {
                    if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        if (Diff2.IntegrExpr().contains(DiffWithRespTo)) {
                            if ((Diff2IntegrExpr.startsWith("-"))) {
                                x = Diff1IntegrExpr + Diff2IntegrExpr;
                            } else if ((Diff2IntegrExpr.startsWith("+"))) {
                                x = Diff1IntegrExpr + Diff2IntegrExpr;
                            } else {
                                x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr;
                            }
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule1.1 :x=" + x);
                        } else {
                            x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo;
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule1.2 :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule1 :x=" + x);
                    } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase("")) && (!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        x = Term1 + StrOperator + Term2;
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule2 :x=" + x);
                    } else if ((!Diff2IntegrExpr.equalsIgnoreCase(Term2)) && (Term3.equalsIgnoreCase(""))) {
                        x = Diff1IntegrExpr;
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule3 :x=" + x);
                    } else if ((!Term2.contains(DiffWithRespTo)) && (IsAConstant(Term2, DiffWithRespTo))) {
                        x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr;
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule5 :x=" + x);
                    } else if ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstantExpression")) || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                        if (Diff2IntegrExpr.equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                            x = Diff1IntegrExpr + StrOperator + Term2 + "*" + DiffWithRespTo;
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6A :x=" + x);
                        } else if (Diff2IntegrExpr.equalsIgnoreCase(Term2 + "*" + DiffWithRespTo)) {
                            x = Diff1IntegrExpr + StrOperator + Term2 + "*" + DiffWithRespTo + "^2.0" + "*1.0/2.0";
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6B :x=" + x);
                        } else if (!Diff2IntegrExpr.contains(DiffWithRespTo)) {
                            x = Term1 + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo;
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6C :x=" + x);
                        } else {
                            if ((IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) || ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))))) {
                                x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr + "*" + DiffWithRespTo;
                                log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6D.1 :x=" + x);
                            } else {
                                x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6D.2 :x=" + x);
                            }
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6D :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule6 :x=" + x);
                    } else if ((!Term1.equalsIgnoreCase("")) && (!Term2.equalsIgnoreCase(""))) {
                        if ((IsAConstant(Term2, DiffWithRespTo)) || ((!IsAConstant(Term2, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))))) {
                            x = Term1 + StrOperator + Term2 + "*" + DiffWithRespTo;
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule7A.1 :x=" + x);
                        } else {
                            x = Term1 + StrOperator + Term2;
                            log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule7A.2 :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_SumSubRules:SumRule or SubtractionRule :Rule7 :x=" + x);
                    }
                    //Drops Term2 && Diff2.IntegrExpr()
                }
                log.info("Integr:String:parseCoeffExponentRule:Final:x=" + x);
                return x;
            }

            String parseProductRule() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("ProductRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        ;//x = Term3 + "*" + Diff1.IntegrExpr() + "+" + Term1 + "*" + Diff2.IntegrExpr();
                        log.info("Integr:ApplyFuncString_ProductRules:ProductRule:Maths OR This Software does not support :" + Term3);
                        log.info("Integr:ApplyFuncString_ProductRules:Rule1 :x=" + x);
                    } else if (Diff1IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                        //Differentiation of x term:
                        x = Diff2IntegrExpr;
                        log.info("Integr:ApplyFuncString_ProductRules:Rule2 :x=" + x);
                    } else if ((Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1IntegrExpr, DiffWithRespTo))) {
                        //Differentiation of x term:
                        x = Diff1IntegrExpr + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0";
                        log.info("Integr:ApplyFuncString_ProductRules:Rule3 :x=" + x);
                    } else if ((Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) && (IsAConstant(Diff1IntegrExpr, DiffWithRespTo))) {
                        //Differentiation of x term with Independent Constant:
                        x = Diff1IntegrExpr + "/2.0" + "*" + DiffWithRespTo + "^" + "2.0";
                        //x = Diff1.IntegrExpr();
                        log.info("Integr:ApplyFuncString_ProductRules:Rule4 :x=" + x);
                    } else if (!Diff1IntegrExpr.equalsIgnoreCase("")) {
                        if ((((IsAConstant(Diff1IntegrExpr, DiffWithRespTo))
                                || ((!IsAConstant(Diff1IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))
                                && (IsAConstant(Diff2IntegrExpr, DiffWithRespTo))
                                || ((!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) && ((StrIsAConstant.equalsIgnoreCase("IndConstant")) || (StrIsAConstant.equalsIgnoreCase("Unknown")))))) {
                            if (Diff1IntegrExpr.contains("@IndependentFactor@")) {
                                Split(Diff1IntegrExpr, DiffWithRespTo);
                                final String TempStrDiff1 = FactorsDiffExpr;
                                x = TempStrDiff1;
                            } else if (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                                x = Diff1IntegrExpr + "*" + Diff2IntegrExpr + "^2.0";
                            } else {
                                x = Diff1IntegrExpr + "*" + Diff2IntegrExpr + "*" + DiffWithRespTo;
                            }
                            log.info("Integr:ApplyFuncString_ProductRules:Rule5A :x=" + x);
                        } else {
                            if ((Diff2IntegrExpr.startsWith("1.0*")) || (Diff2IntegrExpr.startsWith("1.0/"))) {
                                String Temp = Diff2IntegrExpr.replaceFirst("1.0", "");
                                if ((Diff1.IntegrExpr().endsWith("/")) && (Term1.startsWith("/"))) {
                                    String Temp1 = Diff1IntegrExpr.replaceFirst("/", "");
                                    x = Temp1 + Term1 + Temp;
                                    log.info("Integr:ApplyFuncString_ProductRules:Rule5B.1 :x)=" + x);
                                } else if (Diff1IntegrExpr.endsWith("/")) {
                                    String Temp1 = Diff1IntegrExpr.replaceFirst("/", "");
                                    x = Temp1 + Temp;
                                    log.info("Integr:ApplyFuncString_ProductRules:Rule5B.2 :x=" + x);
                                } //Need to uncomment the below code
                                else {
                                    if ((Term1.startsWith("/")) && (Diff1IntegrExpr.startsWith("1.0" + Term1))) {
                                        x = "1.0" + Term1 + Temp;
                                    } else if ((!Term1.equalsIgnoreCase("")) && (!Diff1IntegrExpr.contains(Term1))) {
                                        x = Diff1IntegrExpr + Term1 + Temp;
                                        log.info("Integr:ApplyFuncString_ProductRules:Rule5B.3.A :x=" + x);
                                    } else {
                                        if ((Temp.startsWith("/")) || (Temp.startsWith("*"))) {
                                            x = Diff1IntegrExpr + Temp;
                                        } else {
                                            x = Diff1IntegrExpr + StrOperator + Temp;
                                        }
                                        log.info("Integr:ApplyFuncString_ProductRules:Rule5B.3.B :x=" + x);
                                    }
                                }
                            } else if ((Term1.startsWith("/")) && (Diff2IntegrExpr.contains(DiffWithRespTo))) {
                                x = "1.0" + Term1 + "*" + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_ProductRules:Rule5C :x=" + x);
                            } else if ((Diff1IntegrExpr.contains("*2.0")) && (Diff2IntegrExpr.contains(DiffWithRespTo + "^2.0"))) {
                                x = Diff1IntegrExpr + "/2.0" + "*" + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_ProductRules:Rule5D :x=" + x);
                            } else {
                                x = Diff1IntegrExpr + "*" + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_ProductRules:Rule5E :x=" + x);
                            }
                        }
                        log.info("Integr:ApplyFuncString_ProductRules:Rule5 :x=" + x);
                    }
                }
                log.info("Integr:String:parseProductRule:Final:x=" + x);
                return x;
            }

            String parseQuotientRule() {
                String x = "";
                final String Diff1IntegrExpr = Diff1.IntegrExpr();
                final String Diff2IntegrExpr = Diff2.IntegrExpr();
                if (Rule.equalsIgnoreCase("QuotientRule")) {
                    if (!Term3.equalsIgnoreCase("")) {
                        x = "(" + Term2 + "*" + Diff1IntegrExpr + "-" + Term1 + "*" + Diff2IntegrExpr + "/" + "(" + Term2 + "*" + Term2 + ")" + ")";
                        log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule1 :x=" + x);
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo))) {
                        if (Diff1IntegrExpr.equalsIgnoreCase("1.0")) {
                            x = "ln(" + DiffWithRespTo + ")";
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule2A :x)=" + x);
                        } else {
                            x = Diff1IntegrExpr + "*" + "ln(" + DiffWithRespTo + ")";
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule2B :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule2 :x=" + x);
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff1IntegrExpr.equalsIgnoreCase("1.0"))) {
                        if (Diff2IntegrExpr.startsWith("1.0")) {
                            String Temp = Diff2IntegrExpr.replaceFirst("1.0", "");
                            if ((Term2.equalsIgnoreCase(Diff2IntegrExpr)) && (!Term1.equalsIgnoreCase(""))) {
                                x = "1.0" + Term1 + Temp;
                            } else {
                                x = Diff1IntegrExpr + Temp;
                            }
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3A :x=" + x);
                        } else if (!IsAConstant(Diff2IntegrExpr, DiffWithRespTo)) {
                            if (Diff2IntegrExpr.startsWith(StrOperator)) {
                                x = Diff1IntegrExpr + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3B.1 :x=" + x);
                            } else {
                                x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr;
                                log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3B.2 :x=" + x);
                            }
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3B :x=" + x);
                        } else {
                            x = Diff1IntegrExpr + StrOperator + Diff2IntegrExpr;
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3C :x=" + x);
                        }
                        log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule3 :x=" + x);
                    } else if ((!Diff1IntegrExpr.equalsIgnoreCase("")) && (!Diff2IntegrExpr.contains(Diff1IntegrExpr + "/"))) {
                        if (Diff2IntegrExpr.equalsIgnoreCase(DiffWithRespTo)) {
                            x = "ln(" + DiffWithRespTo + ")";
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule4A :x=" + x);
                        } else {
                            x = Diff1IntegrExpr + "/" + Diff2IntegrExpr;
                            log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule4B :x=" + x);
                        }
                    } else {
                        x = Diff2IntegrExpr;
                        log.info("Integr:ApplyFuncString_QuotientRules:QuotientRule:Rule5 :x=" + x);
                    }
                }
                log.info("Integr:String:parseQuotientRule:Final:x=" + x);
                return x;
            }
        }.parseIntegrStr();
    }

    public static boolean DETAIL(String Term1, String Term2, String DiffWithRespTo) {
        //Logarithmic
        if ((Term1.contains("ln")) || (Term1.contains("log"))) {
            return true;
        } //Inverse trignometric
        else if ((Term1.contains("arctan")) || (Term1.contains("arcsec")) || (Term1.contains("arsinh"))) {
            return true;
        } //Algebraic
        else if (Term1.contains(DiffWithRespTo + "^")) {
            return true;
        } //trignometric
        else if ((Term1.contains("sin")) || (Term1.contains("cos")) || (Term1.contains("tan")) || (Term1.contains("sech"))) {
            return true;
        } //Exponential
        else if ((Term1.contains("exp")) || (Term1.contains("^" + DiffWithRespTo))) {
            return true;
        }
        return false;
    }

    public static void InitState() {
        posLADiff = -1;
        chLADiff = -1;
        //Diff Expression character Index and Character Variable
        posDiff = -1;
        chDiff = -1;
        //Diff Expression character Index and Character Variable
        posIntegr = -1;
        chIntegr = -1;
        // Global Variables && Functions
        // Maximum number of Double Fractional Digits displayed in Equation
        MaximumFractionDigits = 25;
        // Maximum number of Integer Digits displayed in Equation
        MaximumIntegerDigits = 50;

        ZeroTermSignFlag = false;
        ParanExprInProgressFlag = false;
    }

    public static void BackupDiffIntegrStateAll(Map<String, String> variablesBackupState) {
        BackupDiffIntegrParserState(variablesBackupState);
        BackupDiffIntegrControlStateVar(variablesBackupState);
    }

    public static void RestoreDiffIntegrStateAll(Map<String, String> variablesBackupState) {
        RestoreDiffIntegrParserState(variablesBackupState);
        RestoreDiffIntegrControlStateVar(variablesBackupState);
    }

    public static void BackupDiffIntegrControlStateVar(Map<String, String> variablesBackupState) {
        //Boolean Variables
        variablesBackupState.put("GotExpntNumFlag", "" + GotExpntNumFlag);
        variablesBackupState.put("EnteredLAConstFlag", "" + EnteredLAConstFlag);
        variablesBackupState.put("ParanExprFlag", "" + ParanExprFlag);
        variablesBackupState.put("FuncArgFlag", "" + FuncArgFlag);
        variablesBackupState.put("ContainsXFlag", "" + ContainsXFlag);
        variablesBackupState.put("NormalExprnFlag", "" + NormalExprnFlag);
        variablesBackupState.put("TermOutputSetFlag", "" + TermOutputSetFlag);
        variablesBackupState.put("GotIndConstFlag", "" + GotIndConstFlag);
        variablesBackupState.put("GotExtremityFlag", "" + GotExtremityFlag);
        variablesBackupState.put("ComplexAssignFlag", "" + ComplexAssignFlag);
        variablesBackupState.put("ContainsIndConstFlag", "" + ContainsIndConstFlag);
        variablesBackupState.put("GotVariableFlag", "" + GotVariableFlag);
        variablesBackupState.put("NormalNotPareFlag", "" + NormalNotPareFlag);
        variablesBackupState.put("ParenthesisFlag", "" + ParenthesisFlag);
        variablesBackupState.put("ZeroTermSignFlag", "" + ZeroTermSignFlag);

        //Integer Variables
        variablesBackupState.put("LookAheadOperator", "" + LookAheadOperator);
        variablesBackupState.put("ParenthesisHighLvlNum", "" + ParenthesisHighLvlNum);
        variablesBackupState.put("ParenthesisCurrNum", "" + ParenthesisCurrNum);
        variablesBackupState.put("LastLADiffIndConstAidPos", "" + LastLADiffIndConstAidPos);
        variablesBackupState.put("GotIndConstPos", "" + GotIndConstPos);
        variablesBackupState.put("LADiffIndConstOperator", "" + LADiffIndConstOperator);
        variablesBackupState.put("LADiffFactorOperator", "" + LADiffFactorOperator);
        variablesBackupState.put("LastLADiffExtremityAidPos", "" + LastLADiffExtremityAidPos);
        variablesBackupState.put("DiffTermOperator", "" + DiffTermOperator);
        variablesBackupState.put("GotVariablePos", "" + GotVariablePos);
        variablesBackupState.put("SavedOperator", "" + SavedOperator);
        //variablesBackupState.put("TermOutputIndConst", "" +TermOutputIndConst);
    }

    public static void RestoreDiffIntegrControlStateVar(Map<String, String> variablesBackupState) {
        // Boolean Variable Flags
        ComplexAssignFlag = Boolean.parseBoolean(variablesBackupState.get("ComplexAssignFlag"));
        GotExpntNumFlag = Boolean.parseBoolean(variablesBackupState.get("GotExpntNumFlag"));
        EnteredLAConstFlag = Boolean.parseBoolean(variablesBackupState.get("GotExpntNumFlag"));
        ParanExprFlag = Boolean.parseBoolean(variablesBackupState.get("ParanExprFlag"));
        FuncArgFlag = Boolean.parseBoolean(variablesBackupState.get("FuncArgFlag"));
        ContainsXFlag = Boolean.parseBoolean(variablesBackupState.get("ContainsXFlag"));
        NormalExprnFlag = Boolean.parseBoolean(variablesBackupState.get("NormalExprnFlag"));
        TermOutputSetFlag = Boolean.parseBoolean(variablesBackupState.get("TermOutputSetFlag"));
        GotIndConstFlag = Boolean.parseBoolean(variablesBackupState.get("GotIndConstFlag"));
        GotExtremityFlag = Boolean.parseBoolean(variablesBackupState.get("GotExtremityFlag"));
        ComplexAssignFlag = Boolean.parseBoolean(variablesBackupState.get("ComplexAssignFlag"));
        ContainsIndConstFlag = Boolean.parseBoolean(variablesBackupState.get("ContainsIndConstFlag"));
        GotVariableFlag = Boolean.parseBoolean(variablesBackupState.get("GotVariableFlag"));
        NormalNotPareFlag = Boolean.parseBoolean(variablesBackupState.get("NormalNotPareFlag"));
        ParenthesisFlag = Boolean.parseBoolean(variablesBackupState.get("ParenthesisFlag"));
        ZeroTermSignFlag = Boolean.parseBoolean(variablesBackupState.get("ZeroTermSignFlag"));

        //Integer Variables
        LookAheadOperator = Integer.parseInt(variablesBackupState.get("LookAheadOperator"));
        ParenthesisHighLvlNum = Integer.parseInt(variablesBackupState.get("ParenthesisHighLvlNum"));
        ParenthesisCurrNum = Integer.parseInt(variablesBackupState.get("ParenthesisCurrNum"));
        LastLADiffIndConstAidPos = Integer.parseInt(variablesBackupState.get("LastLADiffIndConstAidPos"));
        GotIndConstPos = Integer.parseInt(variablesBackupState.get("GotIndConstPos"));
        LADiffIndConstOperator = Integer.parseInt(variablesBackupState.get("LADiffIndConstOperator"));
        LADiffFactorOperator = Integer.parseInt(variablesBackupState.get("LADiffFactorOperator"));
        DiffTermOperator = Integer.parseInt(variablesBackupState.get("DiffTermOperator"));
        LastLADiffExtremityAidPos = Integer.parseInt(variablesBackupState.get("DiffTermOperator"));
        GotVariablePos = Integer.parseInt(variablesBackupState.get("GotVariablePos"));
        SavedOperator = Integer.parseInt(variablesBackupState.get("SavedOperator"));
        //TermOutputIndConst= Integer.parseInt(variablesBackupState.get("TermOutputIndConst")); 
    }

    public static void BackupDiffIntegrParserState(Map<String, String> variablesBackupState) {
        //Backup LoohAheadConstDiff
        variablesBackupState.put("posLADiff", "" + posLADiff);
        variablesBackupState.put("chLADiff", "" + chLADiff);
        variablesBackupState.put("StrLADiff", StrLADiff);

        //Backup Diffr
        variablesBackupState.put("posDiff", "" + posDiff);
        variablesBackupState.put("chDiff", "" + chDiff);
        variablesBackupState.put("StrDiff", StrDiff);

        //Backup Integr
        variablesBackupState.put("posIntegr", "" + posIntegr);
        variablesBackupState.put("chIntegr", "" + chIntegr);
        variablesBackupState.put("StrIntegr", StrIntegr);

        //String Variables
        variablesBackupState.put("FunctionDiffExpr", FunctionDiffExpr);
        variablesBackupState.put("FunctionExpr", FunctionExpr);
        variablesBackupState.put("FactorsDiffExpr", FactorsDiffExpr);
        variablesBackupState.put("ParanDiffExpr", ParanDiffExpr);
        variablesBackupState.put("ParanExpr", ParanExpr);
        variablesBackupState.put("StrIsAConstant", StrIsAConstant);
    }

    public static void RestoreDiffIntegrParserState(Map<String, String> variablesBackupState) {
        //Restore Simple Expression
        posLADiff = Integer.parseInt(variablesBackupState.get("posLADiff"));
        chLADiff = Integer.parseInt(variablesBackupState.get("chLADiff"));
        StrLADiff = variablesBackupState.get("StrLADiff");

        //Restore Diffr
        posDiff = Integer.parseInt(variablesBackupState.get("posDiff"));
        chDiff = Integer.parseInt(variablesBackupState.get("chDiff"));
        StrDiff = variablesBackupState.get("StrDiff");

        //Restore Integr
        posIntegr = Integer.parseInt(variablesBackupState.get("posIntegr"));
        chIntegr = Integer.parseInt(variablesBackupState.get("chIntegr"));
        StrIntegr = variablesBackupState.get("StrIntegr");

        //String Variables
        FunctionDiffExpr = variablesBackupState.get("FunctionDiffExpr");
        FunctionExpr = variablesBackupState.get("FunctionExpr");
        FactorsDiffExpr = variablesBackupState.get("FactorsDiffExpr");
        ParanDiffExpr = variablesBackupState.get("ParanDiffExpr");
        ParanExpr = variablesBackupState.get("ParanExpr");
        StrIsAConstant = variablesBackupState.get("StrIsAConstant");
    }

    public static void ParseLogDiffrIntegr(String ExprDesc, String HighLevelCallFunc, String InitialORFinal, String StrDesc, String MyStr) {
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":Parser State");

        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":" + StrDesc + "=" + MyStr);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":posLADiff=" + posLADiff + " chLADiff=" + (char) chLADiff + " chLADiff=" + chLADiff);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":posDiff=" + posDiff + " chDiff=" + (char) chDiff + " chDiff=" + chDiff);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":posIntegr=" + posIntegr + " chIntegr=" + (char) chIntegr + " chIntegr=" + chIntegr);

        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":Parser Control State");

        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ComplexAssignFlag=" + ComplexAssignFlag + " ContainsXFlag=" + ContainsXFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":GotVariableFlag=" + GotVariableFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":GotExpntNumFlag=" + GotExpntNumFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":EnteredLAConstFlag=" + EnteredLAConstFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ParanExprFlag=" + ParanExprFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":FuncArgFlag=" + FuncArgFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ContainsXFlag=" + ContainsXFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":NormalExprnFlag=" + NormalExprnFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":TermOutputSetFlag=" + TermOutputSetFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":GotIndConstFlag=" + GotIndConstFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":GotExtremityFlag=" + GotExtremityFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ComplexAssignFlag=" + ComplexAssignFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ContainsIndConstFlag=" + ContainsIndConstFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":GotVariableFlag=" + GotVariableFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":NormalNotPareFlag=" + NormalNotPareFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + ":ParenthesisFlag=" + ParenthesisFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " ParanExprInProgressFlag=" + ParanExprInProgressFlag);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " ZeroTermSignFlag=" + ZeroTermSignFlag);

        // Diffr && Integr : Integer Control State
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LookAheadOperator=" + LookAheadOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " ParenthesisHighLvlNum=" + ParenthesisHighLvlNum);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " ParenthesisCurrNum=" + ParenthesisCurrNum);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LastLADiffIndConstAidPos=" + LastLADiffIndConstAidPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " GotIndConstPos=" + GotIndConstPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LADiffIndConstOperator=" + LADiffIndConstOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LADiffFactorOperator=" + LADiffFactorOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " DiffTermOperator=" + DiffTermOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LastLADiffExtremityAidPos=" + LastLADiffExtremityAidPos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " GotVariablePos=" + GotVariablePos);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " SavedOperator=" + SavedOperator);
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " LastLADiffConstCoeffAidPos=" + LastLADiffConstCoeffAidPos);

        // Diffr && Integr : String Control State
        log.info(ExprDesc + ":" + HighLevelCallFunc + ":ParseLogDiffrIntegr:" + InitialORFinal + " FuncName=" + FuncName);
    }
}
