/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.MathsContxtLAv.ResetVars;
import static Calculus.MathsContxtLAv.Str;
import static Calculus.MathsContxtLAv.StrAlgeTermFunF;
import static Calculus.MathsContxtLAv.StrAlgeTermFunG;
import static Calculus.MathsContxtLAv.StrFunF;
import static Calculus.MathsContxtLAv.StrFunG;
import static Calculus.MathsContxtLAv.StrIsAConstant;
import static Calculus.MathsContxtLAv.ch;
import static Calculus.MathsContxtLAv.chFunF;
import static Calculus.MathsContxtLAv.chFunG;
import static Calculus.MathsContxtLAv.pos;
import static Calculus.MathsContxtLAv.posFunF;
import static Calculus.MathsContxtLAv.posFunFLocal;
import static Calculus.MathsContxtLAv.posFunG;
import static Calculus.MathsContxtLAv.posFunGLocal;
import static Calculus.UsageCalculus.DiffWithRespTo;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class SimpleAlgebraSep {
   public static Logger log = Logger.getLogger(SimpleAlgebraSep.class.getName());
    //Important Mapping Variables
    static Map<String, Double> variables = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    
    // Global Variables && Functions
    // Simple Expression & Simple Term character Index and Character Variable    
    //private static int pos = -1, ch;
    //private static int chNext, chTerm, posTerm = -1;
    //private static String Str;
    //private static String StrIsAConstant = "";
    
    // Algebra & AlgebraTerm character Index and Character Variable    
    //private static int posFunF = -1, chFunF, posFunFLocal = -1;
    //private static int posFunG = -1, chFunG, posFunGLocal = -1;
    //private static String StrFunF;
    //private static String StrFunG;
    //private static String[] StrAlgeTermFunF;
    //private static String[] StrAlgeTermFunG;
    //private static int SavedOperator = 0;
    
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
    public interface SimpleAlgebra {

        String Algebra();
    } 
    public static SimpleAlgebra parseAlgebra(String FunctionF, String FunctionG, String Operator, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            SimpleAlgebra parseAlgebra() {
                ResetVars("Algebra");
                Str = FunctionF;
                
                StrFunF = Str;
                posFunF = 0;
                if (!FunctionF.equalsIgnoreCase("")) {
                    chFunF = FunctionF.charAt(posFunF);
                }
                StrFunG = FunctionG;
                posFunG = 0;
                if (!FunctionG.equalsIgnoreCase("")) {
                    chFunG = FunctionG.charAt(posFunG);
                }

                pos = 0;
                ch = chFunF;

                
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
                log.fatal("Algebra:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                SimpleAlgebra x = (() -> "");
                SimpleAlgebra a = (() -> "");
                SimpleAlgebra b = (() -> "");
                MathsContxtLAv.SimpleTerm u = (() -> "");
                MathsContxtLAv.SimpleTerm v = (() -> "");

                int i = 0;
                // Avoid Function F Infinite Loop
                int j = 0;
                // Avoid Function G Infinite Loop
                int k = -1;
                int l = 0;
                boolean Flag = false;
                for (;;) {
                    if (l == 0) {
                        MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunF", Str.length(), 0, 0, DiffWithRespTo);
                    } else {
                        posFunF = posFunFLocal;
                        MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunF-SteadyState", Str.length(), 0, 0, DiffWithRespTo);
                        if ((pos == 0) && (posFunFLocal == 0)) {
                            break;
                        }
                    }
                    log.fatal("Algebra:parseTermSimple:Str:=" + Str.substring(pos) + " pos=" + pos + " ch=" + (char) ch);
                    
                    v = MathsContxtLAv.parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                    String TermF = v.SimpleTerm();
                    MathsContxtLAv.ParseStore("StrFunF");
                    posFunFLocal = posFunF;
                    log.fatal("Algebra:parseTermSimple:Outer Loop parseTermSimple:x.SimpleExpr()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch);
                    log.fatal("Algebra:parseTermSimple:Outer Loop parseTermSimple:TermF=" + TermF + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                    for (;;) {
                        a = x;
                        //Initial First Time
                        if ((i == 0) && (j == 0)) {
                            MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            MathsContxtLAv.ParseStore("StrFunG");
                        } else if (j == 0) {
                            MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            MathsContxtLAv.ParseStore("StrFunG-Inf");
                            j = 1;
                        } else if (k == -1) {
                            MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunF-SteadyState", Str.length(), 0, 0, DiffWithRespTo);
                            v = MathsContxtLAv.parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                            TermF = v.SimpleTerm();
                            MathsContxtLAv.ParseStore("StrFunF");
                            posFunFLocal = posFunF;
                            posFunG = posFunGLocal;
                            log.fatal("Algebra:parseTermSimple:StrFunG-Inf-SteadyState:Entered");
                            MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            MathsContxtLAv.ParseStore("StrFunG-Inf-SteadyState");
                            k = 0;
                        } else {
                            posFunG = posFunGLocal;
                            log.fatal("Algebra:parseTermSimple:StrFunG-Inf-SteadyState:Entered");
                            MathsContxtLAv.ParseMap("SimpleAlgebra:parseSimple:StrFunG", Str.length(), 0, 0, DiffWithRespTo);
                            MathsContxtLAv.ParseStore("StrFunG-Inf-SteadyState");
                            k = 1;
                        }
                        log.fatal("Algebra:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                        u = MathsContxtLAv.parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                        final String Term2 = u.SimpleTerm();
                        MathsContxtLAv.ParseStore("StrFunG");
                        posFunGLocal = posFunG;
                        if ((TermF.equalsIgnoreCase(""))
                                || (TermF.equalsIgnoreCase("0.0"))
                                || (TermF.equalsIgnoreCase("0"))
                                || (TermF.equalsIgnoreCase("-0.0"))
                                || (TermF.equalsIgnoreCase("-0"))
                                || (TermF.equalsIgnoreCase("+0.0"))
                                || (TermF.equalsIgnoreCase("+0"))) {
                            if ((!Term2.equalsIgnoreCase("0.0"))
                                    && (!Term2.equalsIgnoreCase("-0.0"))
                                    && (!Term2.equalsIgnoreCase("+0.0"))
                                    && (!Term2.equalsIgnoreCase("0"))
                                    && (!Term2.equalsIgnoreCase("-0"))
                                    && (!Term2.equalsIgnoreCase("+0"))) {
                                x = (() -> Term2);
                            } else {
                                x = (() -> "");
                            }
                        } else if (((!TermF.equalsIgnoreCase(""))
                                && (!TermF.equalsIgnoreCase("0.0"))
                                && (!TermF.equalsIgnoreCase("0"))
                                && (!TermF.equalsIgnoreCase("-0.0"))
                                && (!TermF.equalsIgnoreCase("-0"))
                                && (!TermF.equalsIgnoreCase("+0.0"))
                                && (!TermF.equalsIgnoreCase("+0")))
                                && ((!Term2.equalsIgnoreCase("0.0"))
                                && (!Term2.equalsIgnoreCase("-0.0"))
                                && (!Term2.equalsIgnoreCase("+0.0"))
                                && (!Term2.equalsIgnoreCase("0"))
                                && (!Term2.equalsIgnoreCase("-0"))
                                && (!Term2.equalsIgnoreCase("+0")))) {
                            final String Temp = x.Algebra();
                            final String TermF1 = TermF;
                            if ((TermF.startsWith("+")) || (TermF.startsWith("-"))) {
                                x = (() -> Temp + TermF1 + Operator + Term2);
                                log.fatal("Algebra:parseTermSimple:If:x.Algebra()=" + x.Algebra());
                                log.fatal("Algebra:parseTermSimple:If:TermF1=" + TermF1 + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                                log.fatal("Algebra:parseTermSimple:If:Term2=" + Term2 + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posFunGLocal=" + posFunGLocal);
                                log.fatal("Algebra:parseTermSimple:If:pos=" + pos);
                                log.fatal("Algebra:parseTermSimple:If:Str=" + Str);
                            } else {
                                x = (() -> Temp + "+" + TermF1 + Operator + Term2);
                                log.fatal("Algebra:parseTermSimple:Else:x.Algebra()=" + x.Algebra());
                                log.fatal("Algebra:parseTermSimple:Else:TermF1=" + TermF1 + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                                log.fatal("Algebra:parseTermSimple:Else:Term2=" + Term2 + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posFunGLocal=" + posFunGLocal);
                                log.fatal("Algebra:parseTermSimple:Else:pos=" + pos);
                                log.fatal("Algebra:parseTermSimple:Else:Str=" + Str);
                                if ((pos == Str.length() - 1) && (posFunF == -1) && (posFunG == 0)) {
                                    i = 2;
                                    break;
                                } else if (TermF1.length() >= Str.length()) {
                                    i = 2;
                                    break;
                                }
                            }
                        }
                        //Avoid Infinite Loop
                        i = 1;
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:u.SimpleTerm()=" + u.SimpleTerm() + " pos=" + pos + " ch=" + (char) ch);
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:x.Algebra()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch);
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                        log.fatal("SimpleAlgebra:parseTermSimple:Inside Loop parseTermSimple:Term2=" + Term2 + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posFunGLocal=" + posFunGLocal);
                        if (k == 1) {
                            break;
                        }

                    }
                    l = 1;
                    if (i == 2) {
                        break;
                    }
                }
                return x;
            }
        }.parseAlgebra();
    }
    //Created by Rajesh Pai
// Expands (Multiplies) Or Divides Terms with multiplication by Zero: x*0.0=0 OR/And One:x*1.0 = x And/OR addition of Zero: x+0.0=x
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SimpleAlgebraHghLvl {

        String Algebra();
    }

    public static SimpleAlgebraHghLvl parseAlgebraHghLvl(String FunctionF, String FunctionG, String Operator, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            SimpleAlgebraHghLvl parseAlgebra() {
                ResetVars("Algebra");
                Str = FunctionF;
                StrFunF = Str;
                posFunF = 0;
                chFunF = FunctionF.charAt(posFunF);
                StrFunG = FunctionG;
                posFunG = 0;
                chFunG = FunctionG.charAt(posFunG);

                pos = 0;
                ch = chFunF;

                SimpleAlgebraHghLvl x = (() -> "");
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
                log.fatal("SimpleAlgebraHghLvl:parseSimple:x.SimpleExpr()=" + x.Algebra());
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
                log.info("SimpleAlgebraHghLvl:parseSimple:x.Algebra()=" + x.Algebra());
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
            SimpleAlgebraHghLvl parseTermSimple() {
                log.fatal("AlgebraHghLvl:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                SimpleAlgebraHghLvl x = (() -> "");
                SimpleAlgebraHghLvl a = (() -> "");
                SimpleAlgebraHghLvl b = (() -> "");
                int c = 0;
                int d = 0;
                MathsContxtLAv.SimpleTerm u = (() -> "");
                MathsContxtLAv.SimpleTerm v = (() -> "");

                boolean Flag = false;
                String TermF = "";
                String TermG = "";
                StrAlgeTermFunF = new String[StrFunF.length()];
                pos = -1;
                for (c = 0;;) {
                    v = MathsContxtLAv.parseSimpleTerm(StrFunF.substring(posFunF), variables, DiffWithRespTo);
                    TermF = v.SimpleTerm();
                    StrAlgeTermFunF[c++] = TermF;
                    log.fatal("AlgebraHghLvl:parseTermSimple:Primary Loop parseTermSimple:x.SimpleExpr()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch );
                    log.fatal("AlgebraHghLvl:parseTermSimple:Primary Loop parseTermSimple:TermF=" + TermF + " c=" + c + " posFunF=" + posFunF + " chFunF=" + (char) chFunF + " posFunFLocal=" + posFunFLocal);
                    posFunF = pos;
                    if (posFunF == -1) {
                        break;
                    }
                }
                StrAlgeTermFunG = new String[StrFunG.length()];
                pos = -1;
                for (d = 0;;) {
                    u = MathsContxtLAv.parseSimpleTerm(StrFunG.substring(posFunG), variables, DiffWithRespTo);
                    TermG = u.SimpleTerm();
                    StrAlgeTermFunG[d++] = TermG;
                    log.fatal("AlgebraHghLvl:parseTermSimple:Primary Loop parseTermSimple:x.SimpleExpr()=" + x.Algebra() + " pos=" + pos + " ch=" + (char) ch );
                    log.fatal("AlgebraHghLvl:parseTermSimple:Primary Loop parseTermSimple:TermG=" + TermG + " d=" + d + " posFunG=" + posFunG + " chFunG=" + (char) chFunG + " posFunGLocal=" + posFunGLocal);
                    posFunG = pos;
                    if (posFunG == -1) {
                        break;
                    }
                }
                for (int i = 0; i < c; i++) {
                    TermF = StrAlgeTermFunF[i];
                    for (int j = 0; j < d; j++) {
                        TermG = StrAlgeTermFunG[j];
                        a = x;
                        log.fatal("AlgebraHghLvl:parseTermSimple:Inside Inner Loop:TermF=" + TermF + " i=" + i + " TermG=" + TermG + " j=" + j);
                        if ((TermF.equalsIgnoreCase(""))
                                || (TermF.equalsIgnoreCase("0.0"))
                                || (TermF.equalsIgnoreCase("0"))
                                || (TermF.equalsIgnoreCase("-0.0"))
                                || (TermF.equalsIgnoreCase("-0"))
                                || (TermF.equalsIgnoreCase("+0.0"))
                                || (TermF.equalsIgnoreCase("+0"))) {
                            if ((!TermG.equalsIgnoreCase("0.0"))
                                    && (!TermG.equalsIgnoreCase("-0.0"))
                                    && (!TermG.equalsIgnoreCase("+0.0"))
                                    && (!TermG.equalsIgnoreCase("0"))
                                    && (!TermG.equalsIgnoreCase("-0"))
                                    && (!TermG.equalsIgnoreCase("+0"))) {
                                final String Term2 = TermG;
                                x = (() -> Term2);
                            } else {
                                x = (() -> "");
                            }
                        } else if (((!TermF.equalsIgnoreCase(""))
                                && (!TermF.equalsIgnoreCase("0.0"))
                                && (!TermF.equalsIgnoreCase("0"))
                                && (!TermF.equalsIgnoreCase("-0.0"))
                                && (!TermF.equalsIgnoreCase("-0"))
                                && (!TermF.equalsIgnoreCase("+0.0"))
                                && (!TermF.equalsIgnoreCase("+0")))
                                && ((!TermG.equalsIgnoreCase("0.0"))
                                && (!TermG.equalsIgnoreCase("-0.0"))
                                && (!TermG.equalsIgnoreCase("+0.0"))
                                && (!TermG.equalsIgnoreCase("0"))
                                && (!TermG.equalsIgnoreCase("-0"))
                                && (!TermG.equalsIgnoreCase("+0")))) {
                            final String Temp = x.Algebra();
                            final String TermF1 = TermF;
                            final String Term2 = TermG;
                            if ((TermF.startsWith("+")) || (TermF.startsWith("-"))) {
                                x = (() -> Temp + TermF1 + Operator + Term2);
                                log.fatal("AlgebraHghLvl:parseTermSimple:If:x.Algebra()=" + x.Algebra());
                                log.fatal("AlgebraHghLvl:parseTermSimple:If:TermF1=" + TermF1);
                                log.fatal("AlgebraHghLvl:parseTermSimple:If:TermG=" + TermG);
                            } else {
                                x = (() -> Temp + "+" + TermF1 + Operator + Term2);
                                log.fatal("AlgebraHghLvl:parseTermSimple:Else:x.Algebra()=" + x.Algebra());
                                log.fatal("AlgebraHghLvl:parseTermSimple:Else:TermF1=" + TermF1);
                                log.fatal("AlgebraHghLvl:parseTermSimple:Else:TermG=" + TermG + " posFunG=");
                            }
                        }
                        log.fatal("AlgebraHghLvl:parseTermSimple:Inside Inner Loop parseTermSimple:u.SimpleTerm()=" + u.SimpleTerm() + " pos=" + pos);
                        log.fatal("AlgebraHghLvl:parseTermSimple:Inside Inner Loop parseTermSimple:x.Algebra()=" + x.Algebra() + " pos=" + pos + " i=" + i + " j=" + j);
                        log.fatal("AlgebraHghLvl:parseTermSimple:Inside Inner Loop parseTermSimple:TermF=" + TermF);
                        log.fatal("AlgebraHghLvl:parseTermSimple:Inside Inner Loop parseTermSimple:TermG=" + TermG);
                    }
                    log.fatal("AlgebraHghLvl:parseTermSimple:Inside Outer Loop parseTermSimple:x.Algebra()=" + x.Algebra() + " pos=" + pos + " i=" + i);
                    log.fatal("AlgebraHghLvl:parseTermSimple:Inside Outer Loop parseTermSimple:TermF=" + TermF);
                    log.fatal("AlgebraHghLvl:parseTermSimple:Inside Outer Loop parseTermSimple:TermG=" + TermG);

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
                StrFunF = Str;
                posFunF = 0;
                chFunF = FunctionF.charAt(posFunF);
                StrFunG = FunctionG;
                posFunG = 0;
                chFunG = FunctionG.charAt(posFunG);

                pos = -1;
                ch = -1;
                if ((pos >= 0) && (pos < Str.length())) {
                    ch = Str.charAt(pos);
                }
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
                MathsContxtLAv.SimpleTerm u = (() -> "");
                MathsContxtLAv.SimpleTerm v = (() -> "");

                log.fatal("FoG:parseTermSimple:parseTermSimple:StrFunF=" + StrFunF);
                for (;;) {
                    log.fatal("FoG:parseTermSimple:FunF Loop:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                    if ((ch != -1) && (ch != 0)) {
                        log.fatal("FoG:parseTermSimple:Begining:str=" + Str.substring(pos));
                        v = MathsContxtLAv.parseSimpleTerm(Str.substring(pos), variables, DiffWithRespTo);
                        final String TermF = v.SimpleTerm();
                        log.fatal("FoG:parseTermSimple:u.TermF=" + TermF + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                        a = x;

                        if (((!TermF.equalsIgnoreCase(""))
                                && (!TermF.equalsIgnoreCase("0.0"))
                                && (!TermF.equalsIgnoreCase("0"))
                                && (!TermF.equalsIgnoreCase("-0.0"))
                                && (!TermF.equalsIgnoreCase("-0"))
                                && (!TermF.equalsIgnoreCase("+0.0"))
                                && (!TermF.equalsIgnoreCase("+0")))) {
                            String Temp = TermF.replaceAll(DiffWithRespTo, "(" + StrFunG + ")");
                            final String Output = a.Substitute();
                            x = (() -> Output + Temp);
                        }
                        log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:x.Substitute()=" + x.Substitute() + " pos=" + pos + " ch=" + (char) ch);
                        log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:TermF=" + TermF);
                        log.fatal("FoG:parseTermSimple:Inside Loop parseTermSimple:StrFunG=" + StrFunG);
                    } else {
                        log.fatal("FoG:parseTermSimple:Return Value:x.Substitute()=" + x.Substitute() + " pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                        return x;
                    }
                }
            }
        }.parseFoG();
    }

    @FunctionalInterface
    public interface FoGLowLvL {

        String Substitute();
    }

    public static FoGLowLvL parseFoGLowLvL(String FunctionF, String FunctionG, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {

            FoGLowLvL parseFoG() {
                ResetVars("FoG");
                Str = FunctionF;
                StrFunF = Str;
                posFunF = 0;
                chFunF = FunctionF.charAt(posFunF);
                StrFunG = FunctionG;
                posFunG = 0;
                chFunG = FunctionG.charAt(posFunG);

                pos = -1;
                ch = -1;
                if ((pos >= 0) && (pos < Str.length())) {
                    ch = Str.charAt(pos);
                }
                FoGLowLvL x = (() -> "");
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
                log.fatal("FoGLowLvL:parseSimple:x.Substitute()=" + x.Substitute());
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
                log.info("FoGLowLvL:parseSimple:x.Substitute()=" + x.Substitute());
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
            FoGLowLvL parseTermSimple() {
                log.fatal("FoGLowLvL:parseTermSimple:Topmost:pos=" + pos + " ch=" + (char) ch + " ch=" + ch);
                FoGLowLvL x = (() -> "");
                FoGLowLvL a = (() -> "");
                MathsContxtLAv.SimpleTerm v = (() -> "");
                String TermF1 = "";
                int c = 0, d = 0;
                StrAlgeTermFunF = new String[StrFunF.length()];
                pos = -1;
                for (d = 0;;) {
                    v = MathsContxtLAv.parseSimpleTerm(StrFunF.substring(posFunF), variables, DiffWithRespTo);
                    TermF1 = v.SimpleTerm();
                    StrAlgeTermFunF[d++] = TermF1;
                    log.fatal("FoGLowLvL:parseTermSimple:Primary Loop parseTermSimple:TermF=" + TermF1 + " d=" + d + " posFunF=" + posFunF);
                    posFunF = pos;
                    if (posFunF == -1) {
                        break;
                    }
                }
                StrAlgeTermFunG = new String[StrFunG.length()];
                pos = -1;
                for (c = 0;;) {
                    v = MathsContxtLAv.parseSimpleTerm(StrFunG.substring(posFunG), variables, DiffWithRespTo);
                    TermF1 = v.SimpleTerm();
                    StrAlgeTermFunG[c++] = TermF1;
                    log.fatal("FoGLowLvL:parseTermSimple:Primary Loop parseTermSimple:TermF=" + TermF1 + " c=" + c + " posFunG=" + posFunG);
                    posFunG = pos;
                    if (posFunG == -1) {
                        break;
                    }
                }
                log.fatal("FoGLowLvL:parseTermSimple:StrFunF=" + StrFunF);
                log.fatal("FoGLowLvL:parseTermSimple:StrFunG=" + StrFunG);
                int i = 0, j = 0;
                String TermF = "";
                String TermG = "";
                for (i = 0; i < d; i++) {
                    TermF = StrAlgeTermFunF[i];
                    for (j = 0; j < c; j++) {
                        TermG = StrAlgeTermFunG[j];
                        log.fatal("FoGLowLvL:parseTermSimple:TermF=" + TermF);
                        log.fatal("FoGLowLvL:parseTermSimple:TermG=" + TermG);
                        a = x;
                        if ((TermG.contains(DiffWithRespTo)) && ((!TermF.equalsIgnoreCase(""))
                                && (!TermF.equalsIgnoreCase("0.0"))
                                && (!TermF.equalsIgnoreCase("0"))
                                && (!TermF.equalsIgnoreCase("-0.0"))
                                && (!TermF.equalsIgnoreCase("-0"))
                                && (!TermF.equalsIgnoreCase("+0.0"))
                                && (!TermF.equalsIgnoreCase("+0")))) {
                            String Temp = TermF.replaceAll(DiffWithRespTo, TermG);
                            final String Output = a.Substitute();
                            x = (() -> Output + Temp);
                        }
                        log.fatal("FoGLowLvL:parseTermSimple:Inner Loop parseTermSimple:x.Substitute()=" + x.Substitute());
                        log.fatal("FoGLowLvL:parseTermSimple:Inner Loop parseTermSimple:TermG=" + TermG);
                    }
                    log.fatal("FoGLowLvL:parseTermSimple:Outer Loop parseTermSimple:x.Substitute()=" + x.Substitute());
                    log.fatal("FoGLowLvL:parseTermSimple:Outer Loop parseTermSimple:TermF=" + TermF);
                }
                log.fatal("FoGLowLvL:parseTermSimple:Return Value:x.Substitute()=" + x.Substitute());
                return x;
            }
        }.parseFoG();
    }
    
    public static void main(String... args){
      Map<String, Double> variables = new HashMap<>();
      String FuncF = "x^2+25";
      FuncF = eatAll(FuncF, ' ');
        
      String FuncG = "2*x+98";FuncF = eatAll(FuncF, ' ');
      FuncG = eatAll(FuncG, ' ');  
      
      SimpleAlgebraSep.SimpleAlgebra F;
      variables.put(DiffWithRespTo, 1.0);
      SimpleAlgebraSep  MyObject=new SimpleAlgebraSep();
      System.out.println("Hello World");
      System.out.println("FuncF=" + FuncF + " FuncG(Substitue)=" + FuncG);
      F = MyObject.parseAlgebra(FuncF, FuncG, "*", variables, DiffWithRespTo);
      System.out.println("Substituted String=" + F.Algebra());
    }
}
