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
public class SubsStrSep {
    public static Logger log = Logger.getLogger(SubsStrSep.class.getName());
    //Important Mapping Variables
    static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    
    // Global Variables && Functions
    // Simple Expression & Simple Term character Index and Character Variable    
    //private static int pos = -1, ch;
    // static int chNext, chTerm, posTerm = -1;
    //private static String Str;
    //private static String StrIsAConstant = "";
    
    //private static boolean NormalNotPareFlag = true;
    private static boolean ZeroTermSignFlag = false;
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
// Created by Rajesh Pai
// Substritutes ReplacementStr of a Term 
    @FunctionalInterface
    public interface Substitute {

        String SubsStr();
    }
        public static Substitute parseSubsStr(String str, String ReplacementStr, Map<String, Double> variables, String DiffWithRespTo) {
        
        return new Object() {
            Substitute parse() {
                Str = str;
                Substitute x = (() -> "");
                if (Str.length() >= 1) {
                    pos = 0;
                    ch = Str.charAt(0);
                } else if (Str.length() <= 0) {
                    return x;
                }
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ZeroTermSignFlag = false;
                log.info("Substitute:parseExpression:Initial:Str=" + Str);
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

            Substitute parseExpression() {
                Substitute x = (() -> "");
                int ExprStartPos = pos;
                int ExprEndPos = -1;
                if (pos < 0) {
                    ExprStartPos = 0;
                }

                x = parseTerm();
                log.info("Substitute:parseExpression:Initial:x.SubsStr=" + x.SubsStr());
                for (;;) {
                    log.info("Substitute:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch + " pos=" + pos);
                    if (eat(')')) {
                        return x;
                    } else if ((eat('+'))) {// addition
                        Substitute a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                    && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                                x = (() -> "-" + a.SubsStr() + "+" + b.SubsStr());
                            } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> "-" + a.SubsStr() + "+" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> "-" + a.SubsStr() + "+" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                x = (() -> "-" + a.SubsStr() + "+" + b.SubsStr());
                            } else {
                                x = (() -> "Infinity");
                            }
                            ZeroTermSignFlag = false;
                        } else {
                            if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                    && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                                x = (() -> a.SubsStr() + "+" + b.SubsStr());
                            } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> a.SubsStr() + "+" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> a.SubsStr() + "+" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                x = (() -> a.SubsStr() + "+" + b.SubsStr());
                            } else {
                                x = (() -> "Infinity");
                            }
                        }
                        log.info("Substitute:parseExpression:x.SubsStr=" + x.SubsStr() + " a.SubsStr()=" + a.SubsStr() + "+ b.SubsStr()=" + b.SubsStr());
                    } else if ((eat('-'))) { // subtraction
                        Substitute a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                    && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                                x = (() -> "-" + a.SubsStr() + "-" + b.SubsStr());
                            } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> "-" + a.SubsStr() + "-" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> "-" + a.SubsStr() + "-" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                x = (() -> "-" + a.SubsStr() + "-" + b.SubsStr());
                            } else {
                                x = (() -> "Infinity");
                            }
                            ZeroTermSignFlag = false;
                        } else {
                            if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                    && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                                x = (() -> a.SubsStr() + "-" + b.SubsStr());
                            } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> a.SubsStr() + "-" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> a.SubsStr() + "-" + b.SubsStr());
                            } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                                x = (() -> a.SubsStr() + "-" + b.SubsStr());
                            } else {
                                x = (() -> "Infinity");
                            }
                        }
                        log.info("Substitute:parseExpression:x.SubsStr=" + x.SubsStr() + " a.SubsStr()=" + a.SubsStr() + "- b.SubsStr()=" + b.SubsStr());
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            ExprEndPos = pos;
                        } else {
                            ExprEndPos = Str.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = Str.length();
                        }
                        log.info("Substitute:parseExpression:Return Value:x.SubsStr=" + x.SubsStr() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        log.warn("Substitute:parseExpression:Return Value:x.SubsStr=" + x.SubsStr() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        return x;
                    }
                }
            }

            Substitute parseTerm() {
                int TermStartPos = pos;
                int TermEndPos = -1;
                if (pos < 0) {
                    TermStartPos = 0;
                }
                Substitute x = parseFactor();
                log.info("Substitute:parseTerm:Initial:x.SubsStr=" + x.SubsStr());
                for (;;) {
                    Substitute a = x;
                    if (eat('*')) { // multiplication
                        Substitute b = parseFactor();
                        if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                            x = (() -> a.SubsStr() + "*" + b.SubsStr());
                        } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "*" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "*" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            x = (() -> a.SubsStr() + "*" + b.SubsStr());
                        } else {
                            x = (() -> "Infinity");
                        }
                        log.info("Substitute:parseTerm:*:a.SubsStr=" + a.SubsStr());
                        log.info("Substitute:parseTerm:*:b.SubsStr=" + b.SubsStr());
                        log.info("Substitute:parseTerm:*:x.SubsStr=" + x.SubsStr() + " a.SubsStr()=" + a.SubsStr() + "* b.SubsStr()=" + b.SubsStr());
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
                        Substitute b = parseFactor();
                        if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                            x = (() -> a.SubsStr() + "/" + b.SubsStr());
                        } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "/" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "/" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            x = (() -> a.SubsStr() + "/" + b.SubsStr());
                        } else {
                            x = (() -> "Infinity");
                        }
                        log.info("Substitute:parseTerm:/:a.SubsStr=" + a.SubsStr());
                        log.info("Substitute:parseTerm:/:b.SubsStr=" + b.SubsStr());
                        log.info("Substitute:parseTerm:/:x.SubsStr=" + x.SubsStr() + " a.SubsStr()=" + a.SubsStr() + "/ b.SubsStr()=" + b.SubsStr());
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
                        log.info("Substitute:parseTerm:Return Value:x.SubsStr=" + x.SubsStr() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        log.warn("Substitute:parseTerm:Return Value:x.SubsStr=" + x.SubsStr() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        return x;
                    }
                }
            }

            Substitute parseFactor() {
                int FactorStartPos = pos;
                int FactorEndPos = -1;
                if (pos < 0) {
                    FactorStartPos = 0;
                }
                Substitute x = parseBase();
                for (;;) {
                    if (eat('^')) { // exponentiation
                        //nextChar();
                        Substitute a = x, b = parseBase();
                        if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(a.SubsStr())))
                                && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (Double.isFinite(Double.parseDouble(b.SubsStr())))) {
                            x = (() -> a.SubsStr() + "^" + b.SubsStr());
                        } else if ((IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "^" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (!IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                            x = (() -> a.SubsStr() + "^" + b.SubsStr());
                        } else if ((!IsAConstant(a.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression")) && (IsAConstant(b.SubsStr(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            x = (() -> a.SubsStr() + "^" + b.SubsStr());
                        } else {
                            x = (() -> "Infinity");
                        }
                        log.info("Substitute:parseFactor:x.SubsStr=" + x.SubsStr() + " Math.pow(a.SubsStr()=" + a.SubsStr() + "^ b.SubsStr()=" + b.SubsStr() + ")");
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            FactorEndPos = pos;
                        } else {
                            FactorEndPos = Str.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = Str.length();
                        }
                        log.info("Substitute:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                        log.info("Substitute:parseFactor:Return Value:x.SubsStr=" + x.SubsStr() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        log.warn("Substitute:parseFactor:Return Value:x.SubsStr=" + x.SubsStr() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        return x;
                    }
                }
            }

            Substitute parseBase() {
                int startPos = pos;
                Substitute x = (() -> "");
                if (pos >= str.length()) {
                    pos = -1;
                    ch = -1;
                    return x;
                }
                if ((eat('-')) && (startPos != 0)) { // unary minus
                    Substitute b = parseBase();
                    x = (() -> ("-" + b.SubsStr()));
                    log.info("Substitute:parseBase:-:Return Value:x.SubsStr=" + x.SubsStr() + " startPos=" + startPos);
                    return x;
                } else if (eat('+')) { // unary plus
                    x = parseBase();
                    log.info("Substitute:parseBase:+:Return Value:x.SubsStr=" + x.SubsStr());
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
                    log.info("Substitute:parseBase:(:Return Value:x.SubsStr=" + x.SubsStr());
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
                        log.info("Substitute:parseBase: Number=" + Str.substring(startPos, pos));
                        xx = Double.parseDouble(Str.substring(startPos, pos));
                    } else {
                        log.info("Substitute:parseBase:NaN:Exp Form: Number=" + Str.substring(startPos, pos));
                        //xx=Double.parseDouble(String.format("%.12f"+StrRemoveEDbl(Str.substring(startPos, pos)) ) );
                        xx = Double.NaN;
                        pos = -1;
                        ch = -1;
                    }
                    final Double XX = xx;
                    x = () -> "" + XX;
                    log.info("Substitute:parseBase:Number:Return Value:x.SubsStr=" + x.SubsStr());
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
                        Substitute arg = parseExpression();
                        x = () -> name + "(" + arg.SubsStr() + ")";
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null) && (name.equalsIgnoreCase(DiffWithRespTo))) {
                        x = () -> ReplacementStr;
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                            x = () -> name;
                        }
                    }
                    log.info("Substitute:parseBase: Variable: name=" + name + " x.SubsStr()=" + x.SubsStr() + " startPos=" + startPos);
                    return x;
                } else if (ch == ')') {
                    nextChar();
                    log.info("Substitute:parseBase:):Return Value:x.SubsStr=" + x.SubsStr());
                    return x;
                } else {
                    if ((str != null) && (startPos < str.length()) && (startPos < pos) && (ch != -1) && (ch != 0)) {
                        throw new RuntimeException("Substitute:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length()) && (IsNumber(ch))) {
                        throw new RuntimeException("Substitute:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length()) && (IsOperator(ch))) {
                        throw new RuntimeException("Substitute:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < Str.length())) {
                        throw new RuntimeException("Substitute:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    }
                    log.info("Substitute:parseBase:Exception:Return Value:x.SubsStr=" + x.SubsStr());
                    return x;
                }
            }
        }.parse();
  }
 // Created by Rajesh Pai
// Substritutes Expression in a Term 
    @FunctionalInterface
    public interface ExpressionSubs {

        String eval();
    }

    public static ExpressionSubs parseSubs(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            ExpressionSubs parse() {
                Str = str;
                ExpressionSubs x = (() -> "0.0");
                if (Str.length() >= 1) {
                    pos = 0;
                    ch = Str.charAt(0);
                } else if (Str.length() <= 0) {
                    return x;
                }
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                ZeroTermSignFlag = false;
                log.info("ExpressionSubs:parseExpression:Initial:Str=" + Str);
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

            ExpressionSubs parseExpression() {
                ExpressionSubs x = (() -> "0.0");
                int ExprStartPos = pos;
                int ExprEndPos = -1;
                if (pos < 0) {
                    ExprStartPos = 0;
                }

                x = parseTerm();
                log.info("ExpressionSubs:parseExpression:Initial:x.eval=" + x.eval());
                for (;;) {
                    log.info("ExpressionSubs:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch + " pos=" + pos);
                    if (eat(')')) {
                        return x;
                    } else if ((eat('+'))) {// addition
                        ExpressionSubs a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = (() -> a.eval() + "+" + b.eval());
                            ZeroTermSignFlag = false;
                        } else {
                            x = (() -> a.eval() + b.eval());
                        }
                        log.info("ExpressionSubs:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "+ b.eval()=" + b.eval());
                    } else if ((eat('-'))) { // subtraction
                        ExpressionSubs a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = (() -> "-" + a.eval() + "-" + b.eval());
                            ZeroTermSignFlag = false;
                        } else {
                            x = (() -> a.eval() + "-" + b.eval());
                        }
                        log.info("ExpressionSubs:parseExpression:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "- b.eval()=" + b.eval());
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            ExprEndPos = pos;
                        } else {
                            ExprEndPos = Str.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = Str.length();
                        }
                        log.info("ExpressionSubs:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        log.warn("ExpressionSubs:parseExpression:Return Value:x.eval=" + x.eval() + " Expr=" + Str.substring(ExprStartPos, ExprEndPos));
                        return x;
                    }
                }
            }

            ExpressionSubs parseTerm() {
                int TermStartPos = pos;
                int TermEndPos = -1;
                if (pos < 0) {
                    TermStartPos = 0;
                }
                ExpressionSubs x = parseFactor();
                log.info("ExpressionSubs:parseTerm:Initial:x.eval=" + x.eval());
                for (;;) {
                    ExpressionSubs a = x;
                    if (eat('*')) { // multiplication
                        ExpressionSubs b = parseFactor();

                        x = (() -> a.eval() + "*" + b.eval());
                        log.info("ExpressionSubs:parseTerm:*:a.eval=" + a.eval());
                        log.info("ExpressionSubs:parseTerm:*:b.eval=" + b.eval());
                        log.info("ExpressionSubs:parseTerm:*:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "* b.eval()=" + b.eval());
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
                        ExpressionSubs b = parseFactor();
                        x = (() -> a.eval() + "/" + b.eval());
                        log.info("ExpressionSubs:parseTerm:/:a.eval=" + a.eval());
                        log.info("ExpressionSubs:parseTerm:/:b.eval=" + b.eval());
                        log.info("ExpressionSubs:parseTerm:/:x.eval=" + x.eval() + " a.eval()=" + a.eval() + "/ b.eval()=" + b.eval());
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
                        log.info("ExpressionSubs:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        log.warn("ExpressionSubs:parseTerm:Return Value:x.eval=" + x.eval() + " Term=" + Str.substring(TermStartPos, TermEndPos));
                        return x;
                    }
                }
            }

            ExpressionSubs parseFactor() {
                int FactorStartPos = pos;
                int FactorEndPos = -1;
                if (pos < 0) {
                    FactorStartPos = 0;
                }
                ExpressionSubs x = parseBase();
                for (;;) {
                    if (eat('^')) { // exponentiation
                        //nextChar();
                        ExpressionSubs a = x, b = parseBase();
                        //x = (() -> "" + Math.pow(a.eval(), b.eval()) );
                        log.info("ExpressionSubs:parseFactor:x.eval=" + x.eval() + " Math.pow(a.eval()=" + a.eval() + "^ b.eval()=" + b.eval() + ")");
                    } else {
                        if ((pos >= 0) && (pos < Str.length())) {
                            FactorEndPos = pos;
                        } else {
                            FactorEndPos = Str.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = Str.length();
                        }
                        log.info("ExpressionSubs:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                        log.info("ExpressionSubs:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        log.warn("ExpressionSubs:parseFactor:Return Value:x.eval=" + x.eval() + " Factor=" + Str.substring(FactorStartPos, FactorEndPos));
                        return x;
                    }
                }
            }

            ExpressionSubs parseBase() {
                int startPos = pos;
                ExpressionSubs x = (() -> "0.0");
                if (pos >= str.length()) {
                    pos = -1;
                    ch = -1;
                    return x;
                }
                if ((eat('-')) && (startPos != 0)) { // unary minus
                    ExpressionSubs b = parseBase();
                    x = (() -> "-" + b.eval());
                    log.info("ExpressionSubs:parseBase:-:Return Value:x.eval=" + x.eval() + " startPos=" + startPos);
                    return x;
                } else if (eat('+')) { // unary plus
                    x = parseBase();
                    log.info("ExpressionSubs:parseBase:+:Return Value:x.eval=" + x.eval());
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
                    log.info("ExpressionSubs:parseBase:(:Return Value:x.eval=" + x.eval());
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
                        log.info("ExpressionSubs:parseBase: Number=" + Str.substring(startPos, pos));
                        xx = Double.parseDouble(Str.substring(startPos, pos));
                    } else {
                        log.info("ExpressionSubs:parseBase:NaN:Exp Form: Number=" + Str.substring(startPos, pos));
                        //xx=Double.parseDouble(String.format("%.12f"+StrRemoveEDbl(Str.substring(startPos, pos)) ) );
                        xx = Double.NaN;
                        pos = -1;
                        ch = -1;
                    }
                    final Double XX = xx;
                    x = () -> "" + XX;
                    log.info("ExpressionSubs:parseBase:Number:Return Value:x.eval=" + x.eval());
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
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                        //x = () -> variables.get(name);
                    }
                    log.info("ExpressionSubs:parseBase: Variable: name=" + name + " x.eval()=" + x.eval() + " startPos=" + startPos);
                    return x;
                } else if (ch == ')') {
                    nextChar();
                    log.info("ExpressionSubs:parseBase:):Return Value:x.eval=" + x.eval());
                    return x;
                } else {
                    if ((str != null) && (startPos < str.length()) && (startPos < pos) && (ch != -1) && (ch != 0)) {
                        throw new RuntimeException("ExpressionSubs:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < str.length()) && (IsNumber(ch))) {
                        throw new RuntimeException("ExpressionSubs:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < str.length()) && (IsOperator(ch))) {
                        throw new RuntimeException("ExpressionSubs:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    } else if ((ch != -1) && (ch != 0) && (pos >= 1) && (pos < str.length())) {
                        throw new RuntimeException("ExpressionSubs:parseBase:Unexpected: " + (char) ch + " int=" + (int) ch + " Cannot Format:" + str.substring(pos, str.length()));
                    }
                    log.info("ExpressionSubs:parseBase:Exception:Return Value:x.eval=" + x.eval());
                    return x;
                }
            }
        }.parse();
    }
    public static void main(String... args){
      Map<String, Double> variables = new HashMap<>();
      String FuncF = "x^2+25";
      FuncF = eatAll(FuncF, ' ');
        
      String FuncG = "2*x+98";FuncF = eatAll(FuncF, ' ');
      FuncG = eatAll(FuncG, ' ');  
      
      SubsStrSep.Substitute F;
      variables.put(DiffWithRespTo, 1.0);
      SubsStrSep  MyObject=new SubsStrSep();
      System.out.println("Hello World");
      System.out.println("FuncF=" + FuncF + " FuncG(Substitue)=" + FuncG);
      F = MyObject.parseSubsStr(FuncF, FuncG, variables, DiffWithRespTo);
      System.out.println("Substituted String=" + F.SubsStr() );
    }
}

