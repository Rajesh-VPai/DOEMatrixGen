/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.MathsContxtLAv.NormalNotPareFlag;
import static Calculus.MathsContxtLAv.ParenthesisFlag;
import static Calculus.MathsContxtLAv.Str;
import static Calculus.MathsContxtLAv.StrIsAConstant;
import static Calculus.MathsContxtLAv.ch;
import static Calculus.MathsContxtLAv.pos;
import static Calculus.UsageCalculus.DiffWithRespTo;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class FuncInvSep {
   public static Logger log = Logger.getLogger(FuncInvSep.class.getName());
    //Important Mapping Variables
    static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    
    // Global Variables && Functions
    // Simple Expression & Simple Term character Index and Character Variable    
    //private static int pos = -1, ch;
    //private static int chNext, chTerm, posTerm = -1;
    //private static String Str;
    //private static String StrIsAConstant = "";
    
    //private static boolean NormalNotPareFlag = true;
     static boolean ZeroTermSignFlag = false;
    //private static int ParenthesisOperator = 0;
    //private static boolean ParenthesisFlag = false;
    
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
                    && (StrX.contains(DiffWithRespTo))) {
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
    static void nextChar() {
        ++pos;
        ch = (pos < Str.length()) ? Str.charAt(pos) : -1;
        if ((ch == -1) && (pos >= Str.length())) {
            pos = Str.length();
        } else if ((ch == -1) || ((ch == 0))) {
            throw new RuntimeException("nextChar:nextChar:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + Str.substring(pos, Str.length()));
        }
    }

    static void prevChar() {
        --pos;
        ch = (pos >= 0) ? Str.charAt(pos) : -1;
        if ((ch == -1) && (pos < 0)) 
            ; else if ((ch == -1) || ((ch == 0))) {
            throw new RuntimeException("prevChar:prevChar:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + Str.substring(pos, Str.length()));
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
//Created by Rajesh Pai
// Converts Function Inverse to a Single Value

    @FunctionalInterface
    public interface FuncInv {

        String evalStr();
    }
    public static FuncInv parseFInverse(String str, Map<String, Double> variables, String DiffWithRespTo, String OutputVar, String BernsteinChoices) {

        return new Object() {

            FuncInv parse() {
                Str = str;
                FuncInv x = (() -> "" + 0.0);
                if (Str.length() >= 1) {
                    pos = 0;
                    ch = Str.charAt(0);
                } else if (Str.length() <= 0) {
                    return x;
                }
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ZeroTermSignFlag = false;
                log.info("FuncInv:parseExpression:Initial:Str=" + Str);
                if ((Str.length() > 0)
                        && (Str.length() < 2)
                        && ((ch == '*')
                        || (ch == '/')
                        || (ch == '^'))) {
                    return x;
                } else {
                    x = parseExpression();
                }
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

            FuncInv parseExpression() {
                FuncInv x = (() -> "" + 0.0);
                int ExprStartPos = pos;
                int ExprEndPos = -1;
                if (pos < 0) {
                    ExprStartPos = 0;
                }

                x = parseTerm();
                if ((IsAConstant(x.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                    String Temp = x.evalStr();
                    x = (() -> "-" + Temp);
                    log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr());
                }
                log.info("FuncInv:parseExpression:Initial:x.evalStr=" + x.evalStr());
                for (;;) {
                    log.info("FuncInv:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch + " pos=" + pos);
                    if (eat(')')) {
                        return x;
                    } else if ((eat('+'))) {// addition
                        FuncInv a = x, b = parseTerm();
                        if (BernsteinChoices.equalsIgnoreCase("Bernstein")) {
                            // Benstein Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Benstein Laws Go Below End
                        } else if (BernsteinChoices.equalsIgnoreCase("Choices")) {
                            // Choices Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Choices Laws Go Below End
                        } else {
                            if (ZeroTermSignFlag == true) {
                                if ((IsAConstant(b.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                    String Temp = x.evalStr().replace(OutputVar, OutputVar + "-" + b.evalStr());
                                    x = (() -> Temp);
                                    log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                } else if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                        && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                    x = (() -> "" + (-1.0 * Double.parseDouble(a.evalStr()) - Double.parseDouble(b.evalStr())));
                                    log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "" + Double.POSITIVE_INFINITY);
                                }
                                log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                ZeroTermSignFlag = false;
                            } else {
                                if ((IsAConstant(b.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                    String Temp = x.evalStr().replace(OutputVar, OutputVar + "-" + b.evalStr());
                                    x = (() -> Temp);
                                    log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                } else if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                        && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                    if (((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))) {
                                        x = (() -> "" + a.evalStr() + "+" + OutputVar);
                                        log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                    } else {
                                        x = (() -> "" + a.evalStr() + "-" + b.evalStr());
                                    }
                                    log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "" + Double.POSITIVE_INFINITY);
                                }
                                log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                            }
                        }
                        log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                    } else if ((eat('-'))) { // subtraction
                        FuncInv a = x, b = parseTerm();
                        if (BernsteinChoices.equalsIgnoreCase("Bernstein")) {
                            // Benstein Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Benstein Laws Go Below End
                        } else if (BernsteinChoices.equalsIgnoreCase("Choices")) {
                            // Choices Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Choices Laws Go Below End
                        } else {
                            if (ZeroTermSignFlag == true) {
                                if ((IsAConstant(b.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                    String Temp = x.evalStr().replace(OutputVar, OutputVar + "+" + b.evalStr());
                                    x = (() -> Temp);
                                    log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                                } else if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                        && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                    x = (() -> "" + (-1.0 * Double.parseDouble(a.evalStr()) + Double.parseDouble(b.evalStr())));
                                    log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "" + Double.POSITIVE_INFINITY);
                                }
                                ZeroTermSignFlag = false;
                                log.info("FuncInv:parseExpression:Number:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                            } else {
                                if ((IsAConstant(b.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                    String Temp = x.evalStr().replace(OutputVar, OutputVar + "+" + b.evalStr());
                                    x = (() -> Temp);
                                    log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                                } else if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                        && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                        || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                    if (((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))) {
                                        x = (() -> "" + a.evalStr() + "-" + OutputVar);
                                        log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                                    } else {
                                        x = (() -> "" + a.evalStr() + "+" + b.evalStr());
                                    }
                                    log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "" + Double.POSITIVE_INFINITY);
                                }
                                log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "+ b.evalStr()=" + b.evalStr());
                            }
                        }
                        log.info("FuncInv:parseExpression:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "- b.evalStr()=" + b.evalStr());
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            ExprEndPos = pos;
                        } else {
                            ExprEndPos = Str.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = Str.length();
                        }
                        log.info("FuncInv:parseExpression:Return Value:x.evalStr=" + x.evalStr() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        log.warn("FuncInv:parseExpression:Return Value:x.evalStr=" + x.evalStr() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        return x;
                    }
                }
            }

            FuncInv parseTerm() {
                int TermStartPos = pos;
                int TermEndPos = -1;
                if (pos < 0) {
                    TermStartPos = 0;
                }
                FuncInv x = parseFactor();
                log.info("FuncInv:parseTerm:Initial:x.evalStr=" + x.evalStr());
                for (;;) {
                    FuncInv a = x;
                    if (eat('*')) { // multiplication
                        FuncInv b = parseFactor();
                        if (BernsteinChoices.equalsIgnoreCase("Bernstein")) {
                            // Benstein Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Benstein Laws Go Below End
                        } else if (BernsteinChoices.equalsIgnoreCase("Choices")) {
                            // Choices Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Choices Laws Go Below End
                        } else {
                            if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                    && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                if ((IsAConstant(a.evalStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))
                                        && (!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                    x = (() -> "(" + b.evalStr() + ")" + "/" + a.evalStr());
                                    log.info("FuncInv:parseTerm:*:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "* b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "(" + a.evalStr() + ")" + "/" + b.evalStr());
                                    log.info("FuncInv:parseTerm:*:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "* b.evalStr()=" + b.evalStr());
                                }
                            } else {
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                            }
                        }
                        log.info("FuncInv:parseTerm:*:a.evalStr=" + a.evalStr());
                        log.info("FuncInv:parseTerm:*:b.evalStr=" + b.evalStr());
                        log.info("FuncInv:parseTerm:*:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "* b.evalStr()=" + b.evalStr());
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
                        FuncInv b = parseFactor();
                        if (BernsteinChoices.equalsIgnoreCase("Bernstein")) {
                            // Benstein Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Benstein Laws Go Below End
                        } else if (BernsteinChoices.equalsIgnoreCase("Choices")) {
                            // Choices Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Choices Laws Go Below End
                        } else {
                            if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                    && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                if ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                    x = (() -> a.evalStr() + "/" + b.evalStr());
                                    log.info("FuncInv:parseTerm:/:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "/ b.evalStr()=" + b.evalStr());
                                } else {
                                    x = (() -> "(" + a.evalStr() + ")" + "*" + b.evalStr());
                                    log.info("FuncInv:parseTerm:/:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "/ b.evalStr()=" + b.evalStr());
                                }
                            } else {
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                            }
                        }
                        log.info("FuncInv:parseTerm:/:a.evalStr=" + a.evalStr());
                        log.info("FuncInv:parseTerm:/:b.evalStr=" + b.evalStr());
                        log.info("FuncInv:parseTerm:/:x.evalStr=" + x.evalStr() + " a.evalStr()=" + a.evalStr() + "/ b.evalStr()=" + b.evalStr());
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
                        log.info("FuncInv:parseTerm:Return Value:x.evalStr=" + x.evalStr() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        log.warn("FuncInv:parseTerm:Return Value:x.evalStr=" + x.evalStr() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        return x;
                    }
                }
            }

            FuncInv parseFactor() {
                int FactorStartPos = pos;
                int FactorEndPos = -1;
                if (pos < 0) {
                    FactorStartPos = 0;
                }
                FuncInv x = parseBase();
                for (;;) {
                    if (eat('^')) { // exponentiation
                        //nextChar();
                        FuncInv a = x, b = parseBase();
                        if (BernsteinChoices.equalsIgnoreCase("Bernstein")) {
                            // Benstein Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Benstein Laws Go Below End
                        } else if (BernsteinChoices.equalsIgnoreCase("Choices")) {
                            // Choices Laws Go Below Begin
                            //Rajesh Pai

                            //Rajesh Pai
                            // Choices Laws Go Below End
                        } else {
                            if ((IsAConstant(a.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(a.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var")))
                                    && ((IsAConstant(b.evalStr(), DiffWithRespTo))
                                    || ((!IsAConstant(b.evalStr(), OutputVar)) && (StrIsAConstant.equalsIgnoreCase("Var"))))) {
                                x = (() -> "" + Math.pow(Double.parseDouble(b.evalStr()), Double.parseDouble(a.evalStr())));
                            } else {
                                x = (() -> "" + Double.POSITIVE_INFINITY);
                            }
                        }
                        log.info("FuncInv:parseFactor:x.evalStr=" + x.evalStr() + " Math.pow(a.evalStr()=" + a.evalStr() + "^ b.evalStr()=" + b.evalStr() + ")");
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            FactorEndPos = pos;
                        } else {
                            FactorEndPos = Str.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = Str.length();
                        }
                        log.info("FuncInv:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                        log.info("FuncInv:parseFactor:Return Value:x.evalStr=" + x.evalStr() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        log.warn("FuncInv:parseFactor:Return Value:x.evalStr=" + x.evalStr() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        return x;
                    }
                }
            }

            FuncInv parseBase() {
                int startPos = pos;
                FuncInv x = (() -> "" + 0.0);
                if (pos >= str.length()) {
                    pos = -1;
                    ch = -1;
                    return x;
                }
                if ((eat('-')) && (startPos != 0)) { // unary minus
                    FuncInv b = parseBase();
                    x = (() -> "-" + b.evalStr());
                    log.info("FuncInv:parseBase:-:Return Value:x.evalStr=" + x.evalStr() + " startPos=" + startPos);
                    return x;
                } else if (eat('+')) { // unary plus
                    x = parseBase();
                    log.info("FuncInv:parseBase:+:Return Value:x.evalStr=" + x.evalStr());
                    return x;
                }

                if (eat('(')) { //parentheses
                    //Code to handle Expressions:
                    int POpos = pos;
                    do {
                        pos--;
                    } while ((pos >= 0) && (pos < str.length()) && (!IsOperator(str.charAt(pos))));
                    
                    pos = POpos;
                    ParenthesisFlag = true;
                    NormalNotPareFlag = false;
                    x = parseExpression();
                    eat(')');
                    ParenthesisFlag = false;
                    NormalNotPareFlag = true;
                    log.info("FuncInv:parseBase:(:Return Value:x.evalStr=" + x.evalStr());
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
                        log.info("FuncInv:parseBase: Number=" + Str.substring(startPos, pos));
                        xx = Double.parseDouble(Str.substring(startPos, pos));
                    } else {
                        log.info("FuncInv:parseBase:NaN:Exp Form: Number=" + Str.substring(startPos, pos));
                        //xx=Double.parseDouble(String.format("%.12f"+StrRemoveEDbl(Str.substring(startPos, pos)) ) );
                        xx = Double.NaN;
                        pos = -1;
                        ch = -1;
                    }
                    final Double XX = xx;
                    x = () -> "" + XX;
                    log.info("FuncInv:parseBase:Number:Return Value:x.evalStr=" + x.evalStr());
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
                        FuncInv arg = parseExpression();
                        //x = () -> func.applyAsDouble(arg.eval());
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                            if (name.equalsIgnoreCase(DiffWithRespTo)) {
                                x = () -> OutputVar;
                            } else //Handling Independent Constants in FInverse
                            {
                                x = () -> name;
                            }
                        }
                    }
                    log.info("FuncInv:parseBase: Variable: name=" + name + " x.evalStr()=" + x.evalStr() + " startPos=" + startPos);
                    return x;
                } else if (ch == ')') {
                    nextChar();
                    log.info("FuncInv:parseBase:):Return Value:x.evalStr=" + x.evalStr());
                    return x;
                } else {
                    if ((str != null) && (startPos < str.length()) && (startPos < pos) && (ch != -1) && (ch != 0)) {
                        throw new RuntimeException("FuncInv:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length()) && (IsNumber(ch))) {
                        throw new RuntimeException("FuncInv:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length()) && (IsOperator(ch))) {
                        throw new RuntimeException("FuncInv:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length())) {
                        throw new RuntimeException("FuncInv:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    }
                    log.info("FuncInv:parseBase:Exception:Return Value:x.evalStr=" + x.evalStr());
                    return x;
                }
            }
        }.parse();
    }
    public static void main(String... args){
      Map<String, Double> variables = new HashMap<>();
      String FuncF = "x^2+25";
      FuncF = eatAll(FuncF, ' ');
        
      FuncInvSep.FuncInv F;
      variables.put(DiffWithRespTo, 1.0);
      FuncInvSep  MyObject=new FuncInvSep();
      System.out.println("Hello World");
      System.out.println("FuncF=" + FuncF );
      F = MyObject.parseFInverse( FuncF, variables, DiffWithRespTo, "y","Bernstein");
      System.out.println("Inverse String=" + F.evalStr() );
    }
}
