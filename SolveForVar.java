/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.DiffrIntegrSep.MyFuncDiff;
import Calculus.MathsContxtLAv;
import static Calculus.MathsContxtLAv.BackupStateAll;
import static Calculus.MathsContxtLAv.ComplexAssignFlag;
import static Calculus.MathsContxtLAv.ContainsIndConstFlag;
import static Calculus.MathsContxtLAv.ContainsXFlag;
import static Calculus.MathsContxtLAv.GotExpntNumFlag;
import static Calculus.MathsContxtLAv.GotIndConstFlag;
import static Calculus.MathsContxtLAv.GotIndConstPos;
import static Calculus.MathsContxtLAv.GotParenPos;
import static Calculus.MathsContxtLAv.GotVariableFlag;
import static Calculus.MathsContxtLAv.GotVariablePos;
import static Calculus.MathsContxtLAv.IsNotANumber;
import static Calculus.MathsContxtLAv.IsNotAnOperator;
import static Calculus.MathsContxtLAv.IsNumber;
import static Calculus.MathsContxtLAv.IsOperator;
import static Calculus.MathsContxtLAv.IsVariable;
import static Calculus.MathsContxtLAv.LookAheadOperator;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.ParenthesisFlag;
import static Calculus.MathsContxtLAv.ParenthesisOperator;
import static Calculus.MathsContxtLAv.ParseLog;
import static Calculus.MathsContxtLAv.ParseMap;
import static Calculus.MathsContxtLAv.RestoreStateAll;
import static Calculus.MathsContxtLAv.SavedOperator;
import static Calculus.MathsContxtLAv.Str;
import static Calculus.MathsContxtLAv.StrIsAConstant;
import static Calculus.MathsContxtLAv.TermOutputIndConst;
import static Calculus.MathsContxtLAv.TermOutputSetFlag;
import static Calculus.MathsContxtLAv.chTerm;
import static Calculus.MathsContxtLAv.functions;
import static Calculus.MathsContxtLAv.functionsSimple;
import static Calculus.MathsContxtLAv.log;
import static Calculus.MathsContxtLAv.parseExpr;
import static Calculus.MathsContxtLAv.parseIndConst;
import static Calculus.MathsContxtLAv.parseLA;
import static Calculus.MathsContxtLAv.parseSimple;
import static Calculus.MathsContxtLAv.parseVar;
import static Calculus.MathsContxtLAv.pos;
import static Calculus.MathsContxtLAv.posTerm;
import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleUnaryOperator;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class SolveForVar {

    public static Logger log = Logger.getLogger(SolveForVar.class.getName());
    //Important Mapping Variables
    static Map<String, Double> variables = new HashMap<>();
    private static Map<String, String> functionsSimple = new HashMap<>();
    private static Map<String, DoubleUnaryOperator> functions = new HashMap<>();
    // Simple Expression & Simple Term character Index and Character Variable    
    public static int posSolve = -1, chSolve;
    public static int chNextSolve, chTermSolve, posTermSolve = -1;
    public static String StrSolve;
    // Algebra & AlgebraTerm character Index and Character Variable    
    public static int posFunFSolve = -1, chFunFSolve;
    public static int posFunGSolve = -1, chFunGSolve;
    public static String StrFunFSolve;
    public static String StrFunGSolve;
    public static String[] StrTermFunFSolve;
    public static String[] StrTermFunGSolve;
    public static String[] StrUnknownSolve;
    public static String[] StrCoeffSolve;
    public static int IndexUnknownSolve = 0;
    public static String[] FuncFStrUnknownSolve;
    public static Double[] FuncFStrCoeffSolve;
    public static String[] FuncGStrUnknownSolve;
    public static Double[] FuncGStrCoeffSolve;
    public static String[] ResultStrUnknownSolve;
    public static Double[] ResultStrCoeffSolve;
    //IsAConstant Return Type 
    public static String StrIsAConstant = "";

    public static String NumberREGex = "[\\-\\+\\*/]*[0-9\\.]+";
    public static String StrVar = "";
    public static int posPVar = -1, chPVar;
    public static int chNextPVar;

    // Unknown Lambda Variables Parser State Variables
    private static int posVarUnk = -1, chVarUnk;
    private static int posIndConstUnk = -1, chIndConstUnk;
    private static String StrVarUnk;
    private static String StrIndConstUnk;

    // Unknown Lambda Variables Parser Control State Variables
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
    
    static Double Slope = 0.0;
    static Double Constant = 0.0;
    static int CoeffVar = -1;
    static int EliminatedVar = -1;
    static int AnalyticIgnoreNumEqn = 0;
    
    static int NoiseyNumEqn = 0;
    static int NoNoiseNumEqn = 0;
    static int OverallNoNoiseNumEqn=0;
    static int OverallNoiseyNumEqn=0;
    
    static MathsContxtLAv.Expression expValue;
    
    public static enum LogLevel {
        All(7), Debug(6), Info(5), Warn(4), Error(3), Fatal(2), Off(1);
        private int level = 0;

        LogLevel(int level1) {
            this.level = level1;
        }
    }
    public static void MyFuncExpressUnk() {
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
        //TODO:More Unary Functions to be added
    }
    public static void MyFuncSimpleUnk() {
        functionsSimple.put("sin", "sin");
        functionsSimple.put("cos", "cos");
        functionsSimple.put("tan", "tan");
        functionsSimple.put("log", "log");
        functionsSimple.put("ln", "ln");
        functionsSimple.put("exp", "exp");
        //TODO:More Simple Unary Functions to be added
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

    public static boolean IsOperator(int Mych) {
        return (((Mych == '+') || (Mych == '-') || (Mych == '*') || (Mych == '/') || (Mych == '^')));
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
//Created by Rajesh Pai
// Solves for unknown variables in 2 equations using Elimination Method
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SolveSimulElim {

        Double Eliminate();
    }

    public static SolveSimulElim parseSolveElim(String FuncF, Double ResultF, String FuncG, Double ResultG) {

        return new Object() {
            void ExtractUnknownVar(String TermF, String DiffWithRespTo, String[] StrUnknownSolve) {
                if ((IsAConstant(TermF, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("Number"))
                        || (StrIsAConstant.equalsIgnoreCase("NumberExpression"))))
                        ; else if ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) {
                    String[] Temp = TermF.split("[\\*/]");
                    for (int i = 0; i < Temp.length; i++) {
                        log.fatal("SolveElim:parseTermSimple:InsideLoop: are:" + Temp[i]);
                        if ((IsAConstant(Temp[i], DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Number")))
                                ; else {
                            log.fatal("SolveElim:parseTermSimple:Assigned: are:" + Temp[i]);
                            StrUnknownSolve[IndexUnknownSolve++] = Temp[i];
                        }
                    }
                }
            }

            Double EliminateByAdd(String TermF, int indexF, String TermG, int indexG, String TermToElim, int IndexUnknownSolve) {
                System.out.println("SolveElim:EliminateByAdd:IndexUnknownSolve=" + IndexUnknownSolve + " TermF=" + TermF + " indexF=" + indexF);
                System.out.println("SolveElim:EliminateByAdd:FuncF=" + FuncF);
                System.out.println("SolveElim:EliminateByAdd:FuncG=" + FuncG);

                for (int i = 0; i < IndexUnknownSolve; i++) {
                    ResultStrCoeffSolve[i] = FuncFStrCoeffSolve[i] + FuncGStrCoeffSolve[i];
                }

                return (ResultF + ResultG);
            }

            Double EliminateByMinus(String TermF, int indexF, String TermG, int indexG, String TermToElim, int IndexUnknownSolve) {
                System.out.println("SolveElim:EliminateByMinus:IndexUnknownSolve=" + IndexUnknownSolve + " TermF=" + TermF + " indexF=" + indexF);
                System.out.println("SolveElim:EliminateByMinus:FuncF=" + FuncF);
                System.out.println("SolveElim:EliminateByMinus:FuncG=" + FuncG);
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    if (FuncGStrCoeffSolve[i] < FuncFStrCoeffSolve[i]) {
                        ResultStrCoeffSolve[i] = FuncFStrCoeffSolve[i] - FuncGStrCoeffSolve[i];
                    } else {
                        ResultStrCoeffSolve[i] = -1.0 * (FuncGStrCoeffSolve[i] - FuncFStrCoeffSolve[i]);
                    }
                    System.out.println("SolveElim:EliminateByMinus:ResultStrCoeffSolve[i]=" + ResultStrCoeffSolve[i] + " FuncFStrCoeffSolve[i]=" + FuncFStrCoeffSolve[i] + " FuncGStrCoeffSolve[i]=" + FuncGStrCoeffSolve[i]);
                }

                return (ResultF - ResultG);
            }

            Double EliminateBySubstitution(String TermF, int indexF, String TermG, int indexG, String TermToElim, int IndexUnknownSolve) {
                Double[] TempFResultStrCoeffSolve = new Double[IndexUnknownSolve];
                Double[] TempGResultStrCoeffSolve = new Double[IndexUnknownSolve];
                System.out.println("SolveElim:EliminateBySubstitution:FuncF=" + FuncF);
                System.out.println("SolveElim:EliminateBySubstitution:FuncG=" + FuncG);
                //Multiply Func Eqn Coefficients by First Coeff of FuncG
                Double Multiplicand1 = FuncGStrCoeffSolve[1];
                Double Multiplicand2 = FuncFStrCoeffSolve[1];
                System.out.println("SolveElim:EliminateBySubstitution:IndexUnknownSolve=" + IndexUnknownSolve + " TermF=" + TermF + " indexF=" + indexF + " Multiplicand1=" + Multiplicand1);
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    TempFResultStrCoeffSolve[i] = FuncFStrCoeffSolve[i] * Multiplicand1;
                }
                final Double MyTemp = ResultF;
                final Double MyTemp1 = Multiplicand1;
                Double ResultF1 = MyTemp * MyTemp1;
                //Multiply Func Eqn Coefficients by First Coeff of FuncG
                System.out.println("SolveElim:EliminateBySubstitution:IndexUnknownSolve=" + IndexUnknownSolve + " TermF=" + TermF + " indexF=" + indexF + " Multiplicand2=" + Multiplicand2);
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    TempGResultStrCoeffSolve[i] = FuncGStrCoeffSolve[i] * Multiplicand2;
                }
                final Double MyTemp2 = ResultG;
                final Double MyTemp3 = Multiplicand2;
                Double ResultG1 = MyTemp2 * MyTemp3;
                //Subtract the 2 Eqn to eliminate 1st Unknown
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    if (TempGResultStrCoeffSolve[i] < TempFResultStrCoeffSolve[i]) {
                        ResultStrCoeffSolve[i] = TempFResultStrCoeffSolve[i] - TempGResultStrCoeffSolve[i];
                    } else {
                        ResultStrCoeffSolve[i] = -1.0 * (TempGResultStrCoeffSolve[i] - TempFResultStrCoeffSolve[i]);
                    }
                    System.out.println("SolveElim:EliminateBySubstitution:ResultStrCoeffSolve[" + i + "]=" + ResultStrCoeffSolve[i] + " FuncFStrCoeffSolve[" + i + "]=" + FuncFStrCoeffSolve[i] + " FuncGStrCoeffSolve[" + i + "]=" + FuncGStrCoeffSolve[i]);
                }
                //Multiply LHS Result also by Multiplcand
                return ((ResultF1 - ResultG1));
            }

            SolveSimulElim parseSolveElim() {

                SolveSimulElim x = (() -> Double.NaN);

                double ExprnexpValue = 0;
                ResultStrCoeffSolve = new Double[IndexUnknownSolve];
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    ResultStrCoeffSolve[i] = Double.NaN;
                }
                String OutputExprn = "";
                x = parseTermSimple();

                log.fatal("SolveElim:parseSimple:x.SimpleExpr()=" + x.Eliminate());
                return x;
            }

            //Grammar:
            //Parenthesis = '(' expression ')' | function '(' expression ')'
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //
            SolveSimulElim parseTermSimple() {
                log.fatal("SolveElim:parseTermSimple:Topmost:posSolve=" + posSolve + " chSolve=" + (char) chSolve + " chSolve=" + chSolve);
                SolveSimulElim x = (() -> Double.NaN);
                String TermF = "";
                String TermG = "";
                Double Result = Double.NaN;
                //Eliminate Variables
                for (int i = 0; i < (IndexUnknownSolve); i++) {
                    if (FuncFStrUnknownSolve[i] == null) {
                        FuncFStrUnknownSolve[i] = "";
                    }
                    if (FuncGStrUnknownSolve[i] == null) {
                        FuncGStrUnknownSolve[i] = "";
                    }
                    TermF = FuncFStrUnknownSolve[i];
                    TermG = FuncGStrUnknownSolve[i];
                    log.fatal("SolveElim:parseTermSimple:TermF=" + TermF + " TermG=" + TermG);
                    log.fatal("SolveElim:parseTermSimple:FuncFStrCoeffSolve[" + i + "]=" + FuncFStrCoeffSolve[i] + " FuncGStrCoeffSolve[" + i + "]=" + FuncGStrCoeffSolve[i]);
                    if ((TermF.equalsIgnoreCase(TermG)) && (IndexUnknownSolve == 2)
                            && (FuncFStrCoeffSolve[i] != null)
                            && (FuncGStrCoeffSolve[i] != null)
                            && (FuncFStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncGStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncFStrCoeffSolve[i].doubleValue() == FuncGStrCoeffSolve[i].doubleValue())) {
                        Result = EliminateByMinus(TermF, i, TermG, i, TermF, IndexUnknownSolve);
                    } else if ((TermF.equalsIgnoreCase(TermG)) && (IndexUnknownSolve == 2)
                            && (FuncFStrCoeffSolve[i] != null)
                            && (FuncGStrCoeffSolve[i] != null)
                            && (FuncFStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncGStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncFStrCoeffSolve[i].doubleValue() == -FuncGStrCoeffSolve[i].doubleValue())) {
                        variables.put(FuncFStrUnknownSolve[i], FuncFStrCoeffSolve[i]);
                        variables.put(StrUnknownSolve[i + 1], FuncFStrCoeffSolve[i + 1]);
                        Result = EliminateByAdd(TermF, i, TermG, i, TermF, IndexUnknownSolve);
                    } else if ((TermF.equalsIgnoreCase(TermG)) && (IndexUnknownSolve == 2)
                            && (FuncFStrCoeffSolve[i] != null)
                            && (FuncGStrCoeffSolve[i] != null)
                            && (FuncFStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncGStrCoeffSolve[i].doubleValue() != 0)
                            && (FuncFStrCoeffSolve[i].doubleValue() != FuncGStrCoeffSolve[i].doubleValue())) {
                        Result = EliminateBySubstitution(TermF, i, TermG, i, TermF, IndexUnknownSolve);
                        i++;
                    }
                }
                final Double Temp = Result;
                x = (() -> Temp);
                return x;
            }
        }.parseSolveElim();
    }
// Created by Rajesh Pai
// Extract unknown variables in 2 equations 
// Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SimulExtractUnk {

        String Extract();
    }

    public static SimulExtractUnk parseExtract(String TermEqnName, String FunctionF, String DiffWithRespTo, boolean NoConstructFlag) {

        return new Object() {
            SimulExtractUnk ExtractUnknownVar(String TermF, String DiffWithRespTo, String[] StrUnknownSolve) {
                int SimulCoeffIndex = -1;
                String Result = "";
                System.out.println("SimulExtractUnk:ExtractUnknownVar:TermF=" + TermF + " FunctionF=" + FunctionF);
                String Mname = TermF;
                if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '+')) {
                    Mname = Mname.replace('+', ' ').trim();
                } else if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '-')) {
                    Mname = Mname.replace('-', ' ').trim();
                }
                TermF = Mname;
                if ((IsAConstant(TermF, DiffWithRespTo))
                        && ((StrIsAConstant.equalsIgnoreCase("Number"))
                        || (StrIsAConstant.equalsIgnoreCase("NumberExpression"))))
                        ; else if ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                        || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))) {
                    String[] Temp = TermF.split("[\\+\\-\\*/]");
                    if ((Temp.length == 1) && (Temp[0].equalsIgnoreCase(TermF)) && ((!IsAConstant(TermF, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                            || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression"))))) {
                        StrUnknownSolve[IndexUnknownSolve++] = TermF;
                        System.out.println("SimulExtractUnk:ExtractUnknownVar:IndConstant:Final:Result=" + StrUnknownSolve[IndexUnknownSolve - 1]);
                        final String TempResult = Result;
                        return (() -> TempResult);
                    }
                    for (int i = 0; (i < Temp.length) && (Temp.length > 1); i++) {
                        log.fatal("SimulExtractUnk:ExtractUnknownVar:Loop:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                        log.fatal("SimulExtractUnk:ExtractUnknownVar:InsideLoop: are:" + Temp[i]);
                        if ((IsAConstant(Temp[i], DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            StrCoeffSolve[IndexUnknownSolve] = Temp[i];
                        }
                        if ((!IsAConstant(Temp[i], DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:IndConstant:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            System.out.println("SimulExtractUnk:ExtractUnknownVar:Assigned: are:" + Temp[i]);
                            StrUnknownSolve[IndexUnknownSolve] = Temp[i];
                            if (StrCoeffSolve[IndexUnknownSolve] == null) {
                                IndexUnknownSolve++;
                            }
                        }
                        if ((!IsAConstant(Temp[i], DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Var"))
                                || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Var:Assigned:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Assigned: are:" + Temp[i]);
                            //CODE for writing eqn as col2*m2+c instead of m2*col2+c:Begins 
                            //Supports :col1*m1+col2*m2+m7*col7 OR m1*col1+*m2*col2+m7*col7
                            if ((SimulCoeffIndex > -1) && ((StrCoeffSolve[SimulCoeffIndex] == null) || (StrCoeffSolve[SimulCoeffIndex].equalsIgnoreCase("")))) {
                                StrCoeffSolve[SimulCoeffIndex] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:IndConstant:If:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            } else if (IndexUnknownSolve >= 1) {
                                StrCoeffSolve[IndexUnknownSolve] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:IndConstant:Else If:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            } else {
                                StrCoeffSolve[i] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:IndConstant:Else:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            }
                            //CODE for writing eqn as col2*m2+c instead of m2*col2+c :Ends
                            Result = Result + "@" + Temp[i];
                        }
                    }
                } else if ((StrIsAConstant.equalsIgnoreCase("Var"))
                        || (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                    String[] Temp = TermF.split("[\\+\\-\\*/]");
                    if ((Temp.length == 1) && (Temp[0].equalsIgnoreCase(TermF)) && ((!IsAConstant(TermF, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("Var"))
                            || (StrIsAConstant.equalsIgnoreCase("VarExpression"))))) {
                        StrCoeffSolve[IndexUnknownSolve++] = TermF;
                        Result = Result + "@" + Temp[0];
                        final String TempResult = Result;
                        System.out.println("SimulExtractUnk:ExtractUnknownVar:Var:Final:Result=" + Result);
                        return (() -> TempResult);
                    }
                    for (int i = 0; (i < Temp.length) && (Temp.length > 1); i++) {
                        log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                        log.fatal("SimulExtractUnk:ExtractUnknownVar:InsideLoop: are:" + Temp[i]);
                        System.out.println("SimulExtractUnk:ExtractUnknownVar:Var:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                        if ((IsAConstant(Temp[i], DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Number"))) {
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Number:Assigned:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                            StrCoeffSolve[IndexUnknownSolve] = Temp[i];
                            StrUnknownSolve[IndexUnknownSolve++] = "";
                        }
                        if ((!IsAConstant(Temp[i], DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("IndConstant"))
                                || (StrIsAConstant.equalsIgnoreCase("IndConstantExpression")))) {
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:IndConstant:Assigned:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                            //Store m1 Index
                            SimulCoeffIndex = IndexUnknownSolve;
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Assigned: are:" + Temp[i]);
                            StrUnknownSolve[IndexUnknownSolve] = Temp[i];
                            //if((StrCoeffSolve[IndexUnknownSolve] != null)&&(IndexUnknownSolve==0)) {
                            IndexUnknownSolve++;
                            //}
                        }
                        if ((!IsAConstant(Temp[i], DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("Var"))
                                || (StrIsAConstant.equalsIgnoreCase("VarExpression")))) {
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Var:Assigned:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo);
                            log.fatal("SimulExtractUnk:ExtractUnknownVar:Assigned: are:" + Temp[i]);
                            //CODE for writing eqn as col2*m2+c instead of m2*col2+c:Begins 
                            //Supports :col1*m1+col2*m2+m7*col7 OR m1*col1+*m2*col2+m7*col7
                            if ((SimulCoeffIndex > -1) && ((StrCoeffSolve[SimulCoeffIndex] == null) || (StrCoeffSolve[SimulCoeffIndex].equalsIgnoreCase("")))) {
                                StrCoeffSolve[SimulCoeffIndex] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:If:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            } else if (IndexUnknownSolve >= 1) {
                                StrCoeffSolve[IndexUnknownSolve] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Else If:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            } else {
                                StrCoeffSolve[i] = Temp[i];
                                log.fatal("SimulExtractUnk:ExtractUnknownVar:Var:Else:Temp[" + i + "]=" + Temp[i] + " DiffWithRespTo=" + DiffWithRespTo + " SimulCoeffIndex=" + SimulCoeffIndex + " IndexUnknownSolve=" + IndexUnknownSolve);
                            }
                            //CODE for writing eqn as col2*m2+c instead of m2*col2+c :Ends
                            Result = Result + "@" + Temp[i];
                        }
                    }
                }
                System.out.println("SimulExtractUnk:ExtractUnknownVar:Final:Result=" + Result);
                final String TempResult = Result;
                return (() -> TempResult);
            }

            SimulExtractUnk ExtractUnknownVarN(String TermF, String DiffWithRespTo, String[] StrUnknownSolve) {
                int SimulCoeffIndex = -1;
                String Result = "";
                System.out.println("SimulExtractUnk:ExtractUnknownVarN:TermF=" + TermF + " FunctionF=" + FunctionF);
                String Mname = TermF;
                if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '+')) {
                    Mname = Mname.replace('+', ' ').trim();
                } else if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '-')) {
                    Mname = Mname.replace('-', ' ').trim();
                }
                TermF = Mname;
                if ((TermF.contains("*")) || (TermF.contains("/"))) {
                    MathsContxtLAv.ChaosPrevention();
                    MathsContxtLAv.LookAheadConst MyCoeffConst;
                    MyCoeffConst = parseLA(TermF, 0, variables, DiffWithRespTo,false);
                    String StrConst = "" + MyCoeffConst.eval();
                    log.info("SimulExtractUnk:ExtractUnknownVarN:Number:MyCoeff=" + StrConst);
                    if ((!StrConst.equalsIgnoreCase("") && (!StrConst.equalsIgnoreCase("1.0")))) {
                        StrUnknownSolve[IndexUnknownSolve] = StrConst;
                    }
                    System.out.println("SimulExtractUnk:ExtractUnknownVarN:Constant:Final:StrConst=" + StrConst);
                    if ((TermF.contains(DiffWithRespTo))) {
                        LookAheadVarUnk MyCoeffVar;
                        MyCoeffVar = parseVarUnk(TermF, variables, DiffWithRespTo);
                        String nameVar = MyCoeffVar.eval();
                        log.info("SimulExtractUnk:ExtractUnknownVarN:Variable:nameVar=" + nameVar);
                        if (!nameVar.equalsIgnoreCase("")) {
                            StrCoeffSolve[IndexUnknownSolve] = nameVar;
                        }
                        System.out.println("SimulExtractUnk:ExtractUnknownVarN:Var:Final:nameVar=" + nameVar);
                    }

                    if (StrConst.equalsIgnoreCase("") || (StrConst.equalsIgnoreCase("1.0"))) {
                        LookAheadIndConstUnk MyCoeffIndConst;
                        MyCoeffIndConst = parseIndConstUnk(TermF, DiffWithRespTo);
                        String nameIndConst = MyCoeffIndConst.eval();
                        log.fatal("SimulExtractUnk:ExtractUnknownVar:Assigned: are:nameIndConst=" + nameIndConst);
                        StrUnknownSolve[IndexUnknownSolve] = nameIndConst;
                        IndexUnknownSolve++;
                        System.out.println("SimulExtractUnk:ExtractUnknownVarN:IndConst:Final:nameIndConst=" + nameIndConst);
                    }
                } else {
                    SimulExtractUnk b = ExtractUnknownVar(TermF, DiffWithRespTo, StrUnknownSolve);
                    return b;
                }
                System.out.println("SimulExtractUnk:ExtractUnknownVar:Final:Result=" + Result);
                final String TempResult = Result;
                return (() -> TempResult);
            }

            SimulExtractUnk parseExtract() {
                StrSolve = FunctionF;
                StrFunFSolve = StrSolve;
                Str = StrSolve;
                posFunFSolve = 0;
                posSolve = 0;
                pos = 0;
                IndexUnknownSolve = 0;
                SimulExtractUnk x = (() -> "");
                if ((Str == null) || (Str.equalsIgnoreCase("")) || (StrSolve == null) || (StrSolve.equalsIgnoreCase(""))) {
                    return x;
                }

                String OutputExprn = "";
                int startpos = posSolve;
                x = parseTermSimple();
                log.fatal("SimulExtractUnk:parseExtract:x.SimpleExpr()=" + x.Extract());

                log.fatal("SimulExtractUnk:parseExtract:IndexUnknownSolve=" + IndexUnknownSolve);
                //CopyVarNCoeff(TermEqnName);
                log.info("SimulExtractUnk:parseExtract:x.Extract()=" + x.Extract());
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
            SimulExtractUnk parseTermSimple() {
                log.fatal("SimulExtractUnk:parseTermSimple:Topmost:posSolve=" + posSolve + " chSolve=" + (char) chSolve + " chSolve=" + chSolve);
                SimulExtractUnk x = (() -> "");
                SimulExtractUnk a = (() -> "");
                SimulExtractUnk b = (() -> "");
                int c = 0;
                int d = 0;
                Calculus.MathsContxtLAv.SimpleTerm u = (() -> "");
                Calculus.MathsContxtLAv.SimpleTerm v = (() -> "");

                boolean Flag = false;
                String TermF = "";
                String TermG = "";
                // Split StrFunFSolve into its Terms
                StrTermFunFSolve = new String[StrFunFSolve.length()];
                StrUnknownSolve = new String[StrFunFSolve.length()];
                StrCoeffSolve = new String[StrFunFSolve.length()];
                for (c = 0;;) {
                    a = x;
                    System.out.println("SimulExtractUnk:parseTermSimple:StrFunFSolve.substring=" + StrFunFSolve.substring(posFunFSolve));
                    v = Calculus.MathsContxtLAv.parseSimpleTerm(StrFunFSolve.substring(posFunFSolve), variables, DiffWithRespTo);
                    TermF = v.SimpleTerm();
                    System.out.println("SimulExtractUnk:parseTermSimple:TermF=" + TermF);
                    if ((TermF.equalsIgnoreCase("")) && (c == 0)) {
                        String Mname = StrSolve;
                        if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '+')) {
                            Mname = Mname.replace('+', ' ').trim();
                        } else if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '-')) {
                            Mname = Mname.replace('-', ' ').trim();
                        }
                        StrTermFunFSolve[c] = Mname;
                        TermF = Mname;
                    } else {
                        String Mname = TermF;
                        if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '+')) {
                            Mname = Mname.replace('+', ' ').trim();
                        } else if ((NoConstructFlag == true) && (!Mname.equalsIgnoreCase("")) && (Mname.charAt(0) == '-')) {
                            Mname = Mname.replace('-', ' ').trim();
                        }
                        StrTermFunFSolve[c++] = Mname;
                        TermF = Mname;
                    }
                    System.out.println("SimulExtractUnk:parseTermSimple:TermF are:" + TermF);
                    final String aTemp = a.Extract();
                    b = ExtractUnknownVarN(TermF, DiffWithRespTo, StrUnknownSolve);
                    final String bTemp = b.Extract();
                    x = (() -> bTemp);
                    log.fatal("SimulExtractUnk:parseTermSimple:Primary Loop parseTermSimple:posSolve=" + posSolve + " chSolve=" + (char) chSolve);
                    log.fatal("SimulExtractUnk:parseTermSimple:Primary Loop parseTermSimple:TermF=" + TermF + " c=" + c + " posFunFSolve=" + posFunFSolve + " chFunFSolve=" + (char) chFunFSolve);
                    posSolve = pos;
                    posFunFSolve = posSolve;
                    if (posFunFSolve == -1) {
                        break;
                    }
                }
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    if (StrUnknownSolve[i] == null) {
                        IndexUnknownSolve--;
                    }
                    //log.fatal("ExtractUnk:parseTermSimple:Unknown Vars are:i=" + i + ":" + StrUnknownSolve[i] + " IndexUnknownSolve=" + IndexUnknownSolve);
                    System.out.println("SimulExtractUnk:parseTermSimple:Unknown Vars are:i=" + i + ":" + StrUnknownSolve[i] + " StrCoeffSolve[i]=" + StrCoeffSolve[i] + " IndexUnknownSolve=" + IndexUnknownSolve);
                }
                log.fatal("SimulExtractUnk:parseTermSimple:Result: x.Extract()=" + x.Extract());
                return x;
            }
        }.parseExtract();

    }
//Created by Rajesh Pai
// Solves for unknown variables in 2 equations using Elimination Method
//Copyright (c) 2018 by Rajesh V. Pai

    @FunctionalInterface
    public interface SolveSimulGauss {

        String Eliminate();
    }

    public static SolveSimulGauss parseSolveSimul(int row, int row1,
            int NumOfEqn, int NumOfUnknown, int eqn, int kspace,
            String ColumnVariables, String BaseDiffWithRespTo,
            boolean SolveFlag, boolean SolveNoConstantFlag,
            String FuncF, Double ResultF, String FuncG, Double ResultG) {

        return new Object() {

            SolveSimulGauss parseSolveSimulGauss() {

                SolveSimulGauss x = (() -> "");

                double ExprnexpValue = 0;
                ResultStrCoeffSolve = new Double[IndexUnknownSolve];
                for (int i = 0; i < IndexUnknownSolve; i++) {
                    ResultStrCoeffSolve[i] = Double.NaN;
                }
                String OutputExprn = "";
                x = parseTermSimple();

                log.fatal("SolveElim:parseSimple:x.SimpleExpr()=" + x.Eliminate());
                return x;
            }

            SolveSimulGauss parseTermSimple() {
                log.fatal("SolveElim:parseTermSimple:Topmost:posSolve=" + posSolve + " chSolve=" + (char) chSolve + " chSolve=" + chSolve);
                SolveSimulGauss x = (() -> "");
                String TermF = "";
                String TermG = "";
                Double Result = Double.NaN;
                Double Slope = 0.0;
                Double Constant = 0.0;
                int CoeffVar = -1;
                int EliminatedVar = -1;

                SolveSimulElim F = (() -> 0.0);
                log.fatal("SolveSimulGauss:SolveSimulEqn:Solving Equations:Random Row Selected 1:row=" + row);
                log.fatal("SolveSimulGauss:SolveSimulEqn:Solving Equations:Random Row Selected 2:row1=" + row1);
                System.out.println("DOEOFATAnalyzer:SolveSimulEqn:Solving Equations:ColumnVariables=" + ColumnVariables);
                if (SolveFlag == true) {
                    //log.fatal("DOEOFATAnalyzer:SolveSimulEqn:Solving Equations: Level row=" + Level1 + " Value=" + LvlValue1);
                    //log.fatal("DOEOFATAnalyzer:SolveSimulEqn:Solving Equations: Leve2 row1=" + Level2 + " Value=" + LvlValue2);
                    log.fatal("SolveSimulGauss:SolveSimulEqn:Solving Equations:FuncF=" + FuncF);
                    log.fatal("SolveSimulGauss:SolveSimulEqn:Solving Equations:FuncG=" + FuncG);
                    log.fatal("SolveSimulGauss:SolveSimulEqn:ResultsArrayGet(0, row, kspace)=" + ResultF);
                    log.fatal("SolveSimulGauss:SolveSimulEqn:ResultsArrayGet(0, row1, kspace)=" + ResultG);
                    if (FuncF.equalsIgnoreCase(FuncG)) {
                        System.out.println("DOEOFATAnalyzer:SolveSimulEqn:Solving Equations:Both Equations are the same. Coefficients must be different.");
                        System.out.println("DOEOFATAnalyzer:SolveSimulEqn:Solving Equations:Choose Different Rows where Levels of column are different");
                        AnalyticIgnoreNumEqn++;
                    } else {
                        //for (row = 0; row < NumOfEqn; row += 2) {
                        F = parseSolveElim(FuncF, ResultF, FuncG, ResultG);
                        System.out.println("DOEOFATAnalyzer:SolveEquations:F.Eliminate()=" + F.Eliminate());
                        int OneAssignOnlyFlag = 0;

                        for (int i = 0; i < NumOfUnknown; i++) {
                            if (ResultStrCoeffSolve[i] == 0) {
                                OneAssignOnlyFlag++;
                                EliminatedVar = i;
                                log.fatal("SolveSimulGauss:SolveSimulEqn:0.0:Equation=" + ResultStrCoeffSolve[i] + " Var=" + StrUnknownSolve[i] + " EliminatedVar=" + EliminatedVar);
                                if (OneAssignOnlyFlag == 2) {
                                    System.out.println("SolveSimulGauss:SolveSimulEqn:CANNOT SOLVE:Both Variables are eliminated");
                                    EliminatedVar = -1;
                                    CoeffVar = -1;
                                    AnalyticIgnoreNumEqn++;
                                    break;
                                }
                            } else {
                                CoeffVar = i;
                                log.fatal("SolveSimulGauss:SolveSimulEqn:NOT 0.0:Equation=" + ResultStrCoeffSolve[i] + " Var=" + StrUnknownSolve[i] + " CoeffVar=" + CoeffVar + " i=" + i);
                            }
                        }
                        log.fatal("SolveSimulGauss:SolveSimulEqn:FuncF=" + FuncF + " ResultsArrayGet(0, row, kspace)=" + ResultF);
                        log.fatal("SolveSimulGauss:SolveSimulEqn:FuncG=" + FuncG + " ResultsArrayGet(0, row1, kspace)=" + ResultG);
                        // Determine the Value of the CoeffVar Variable (as other one has been eliminated)
                        if (CoeffVar != -1) {
                            log.fatal("SolveSimulGauss:SolveSimulEqn:OneAssignOnlyFlag:Result=" + F.Eliminate() + " SlopeName=" + FuncFStrUnknownSolve[CoeffVar] + " ResultStrCoeffSolve[CoeffVar]=" + ResultStrCoeffSolve[CoeffVar]);
                            Slope = F.Eliminate() / ResultStrCoeffSolve[CoeffVar];
                            log.fatal("SolveSimulGauss:SolveSimulEqn:OneAssignOnlyFlag:Var=" + FuncFStrUnknownSolve[CoeffVar] + "=" + F.Eliminate() / ResultStrCoeffSolve[CoeffVar]);

                        }
                        // Determine the Value of the EliminatedVar(which had been eliminated)
                        if ((SolveNoConstantFlag == false) && (EliminatedVar != -1) && (CoeffVar != -1)) {
                            log.fatal("SolveSimulGauss:SolveSimulEqn:!SolveNoConstantFlag:EliminatedVar:Result=" + ResultF + " cName=" + StrUnknownSolve[EliminatedVar] + "  Coeff=" + FuncFStrCoeffSolve[EliminatedVar]);
                            Constant = (ResultF - (Slope * FuncFStrCoeffSolve[CoeffVar]));
                            log.fatal("SolveSimulGauss:SolveSimulEqn:!SolveNoConstantFlag:EliminatedVar:" + StrUnknownSolve[EliminatedVar] + "=" + Constant);
                        } else if ((SolveNoConstantFlag == true) && (EliminatedVar != -1) && (CoeffVar != -1)) {
                            log.fatal("SolveSimulGauss:SolveSimulEqn:SolveNoConstantFlag:EliminatedVar:Result=" + ResultF + " Slope=" + Slope + " Slope Coeff=" + FuncFStrCoeffSolve[CoeffVar] + " CName=" + StrUnknownSolve[EliminatedVar] + " C Coeff=" + FuncFStrCoeffSolve[EliminatedVar]);
                            Constant = ((ResultF - Slope * FuncFStrCoeffSolve[CoeffVar]) / FuncFStrCoeffSolve[EliminatedVar]);
                            log.fatal("SolveSimulGauss:SolveSimulEqn:SolveNoConstantFlag:EliminatedVar:" + StrUnknownSolve[EliminatedVar] + "=" + Constant);
                        }
                        //}
                    }
                }
                if ((CoeffVar >= 0) && (EliminatedVar >= 0)) {
                    final String Temp = "ValidResult"
                            + "@" + FuncFStrUnknownSolve[CoeffVar] + ":" + Slope
                            + "@" + StrUnknownSolve[EliminatedVar] + ":" + Constant
                            + "@" + "CoeffVar" + ":" + CoeffVar
                            + "@" + "EliminatedVar" + ":" + EliminatedVar;
                    x = (() -> Temp);
                    return x;
                } else {
                    final String Temp = "InvalidResult"
                            + "@" + StrUnknownSolve[0] + ":" + Slope
                            + "@" + StrUnknownSolve[1] + ":" + Constant
                            + "@" + "CoeffVar" + ":" + CoeffVar
                            + "@" + "EliminatedVar" + ":" + EliminatedVar;
                    x = (() -> Temp);
                    return x;
                }
            }
        }.parseSolveSimulGauss();
    }

//Created by Rajesh Pai
// Solves for unknown variables in 2 equations using Elimination Method
//Copyright (c) 2018 by Rajesh V. Pai
    @FunctionalInterface
    public interface VariableCol {

        int Index();
    }

    public static VariableCol parseVariableCol(String Exprn, String BaseDiffWithRespTo, String Silent, LogLevel MyLogLevel) {
        return new Object() {
            void nextCharVar() {
                ++posPVar;
                chPVar = (posPVar < StrVar.length()) ? StrVar.charAt(posPVar) : -1;
                if ((chPVar == -1) && (posPVar >= StrVar.length())) {
                    posPVar = StrVar.length();
                } else if ((chPVar == -1) || ((chPVar == 0))) {
                    throw new RuntimeException("nextChar:nextChar:Unexpected: " + (char) chPVar + " int=" + (int) chPVar + " Cannot Format:" + StrVar.substring(posPVar, StrVar.length()));
                }
            }

            boolean eat(int CharToEat) {
                while (chPVar == ' ') {
                    nextCharVar();
                }
                if (chPVar == CharToEat) {
                    nextCharVar();
                    return true;
                }
                return false;
            }

            VariableCol parseVariableCol() {

                VariableCol x = (() -> -1);
                chPVar = -1;
                posPVar = 0;
                StrVar = Exprn;
                if ((StrVar == null) || (StrVar.equalsIgnoreCase(""))) {
                    return x;
                }
                chPVar = StrVar.charAt(posPVar);

                x = parseExpression();
                System.out.println("VariableCol:parseSimple:Exprn=" + Exprn + " x.Index()=" + x.Index());
                log.fatal("VariableCol:parseSimple:x.SimpleExpr()=" + x.Index());
                return x;
            }

            VariableCol parseExpression() {
                log.fatal("VariableCol:parseTermSimple:Topmost:posPVar=" + posPVar + " chPVar=" + (char) chPVar + " chPVar=" + chPVar);
                VariableCol x = (() -> -1);
                VariableCol a = (() -> -1);
                for (;;) {
                    if ((chPVar != 0) && (chPVar != -1)) {
                        a = parseBase();
                        if (a.Index() >= 0) {
                            final int Result = a.Index();
                            x = () -> Result;
                        }
                    } else {
                        log.info("VariableCol:parseTerm:Return Value:y.Index()=" + x.Index());
                        return x;
                    }
                }
            }

            VariableCol parseBase() {
                int startPos = posPVar;
                VariableCol x = (() -> -1);
                if (posPVar >= StrVar.length()) {
                    posPVar = -1;
                    chPVar = -1;
                    return x;
                }
                if ((eat('-')) && (startPos != 0)) { // unary minus
                    VariableCol b = parseBase();
                    x = (() -> b.Index());
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:-:Return Value:x.Index=" + x.Index() + " startPos=" + startPos);
                    }
                    return x;
                } else if (eat('+')) { // unary plus
                    x = parseBase();
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:+:Return Value:x.Index=" + x.Index());
                    }
                    return x;
                }

                if (eat('(')) { //parentheses
                    //Code to handle Expressions:
                    int POpos = posPVar;
                    do {
                        posPVar--;
                    } while ((posPVar >= 0) && (posPVar < StrVar.length()) && (!IsOperator(StrVar.charAt(posPVar))));

                    posPVar = POpos;
                    x = parseExpression();
                    eat(')');
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:(:Return Value:x.Index=" + x.Index());
                    }
                    return x;
                } else if (IsNumber(chPVar)) { //numbers
                    while (IsNumber(chPVar)) {
                        nextCharVar();
                        if ((chPVar == 'E') || (chPVar == 'e')) {
                            nextCharVar();
                            if ((chPVar == '-') || (chPVar == '+')) {
                                nextCharVar();
                            }
                        }
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:Number:Return Value:x.Index=" + x.Index());
                    }
                    return x;
                } else if (IsVariable(chPVar)) {//functions & variables
                    int TempIndex = -1;
                    while ((IsVariable(chPVar)) || (IsNumber(chPVar))) {
                        if ((TempIndex == -1) && (IsNumber(chPVar))) {
                            TempIndex = posPVar;
                        }
                        nextCharVar();
                    }
                    if ((startPos == 0) && (StrVar.charAt(startPos) == '-')) {
                        startPos++;
                    }
                    String name = StrVar.substring(startPos, posPVar).trim();
                    //System.out.println("VariableCol:parseBase:name=" + name + " x.Index()=" + x.Index() + " TempIndex=" + TempIndex + " startPos=" + startPos + " posPVar=" + posPVar + " chPVar=" + (char) chPVar);
                    if (functions.containsKey(name)) {
                        nextCharVar();//get open braces
                        VariableCol arg = parseExpression();
                        x = () -> arg.Index();
                        if (MyLogLevel.level >= LogLevel.Info.level) {
                            log.info("VariableCol:parseBase: Function: name=" + name + " x.Index()=" + x.Index() + " arg.eval()=" + arg.Index() + " startPos=" + startPos);
                        }
                    } else {
                        if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (name.contains(BaseDiffWithRespTo))) {
                            final int MyIndex = Integer.parseInt(StrVar.substring(TempIndex, posPVar));
                            x = () -> MyIndex;
                            //System.out.println("VariableCol:parseBase:name=" + name + " x.Index()=" + x.Index() + " posPVar=" + posPVar + " chPVar=" + (char) chPVar);
                        } else if (name.equalsIgnoreCase("Infinity")) {
                            x = () -> -1;
                        } else if (name.equalsIgnoreCase("Nan")) {
                            x = () -> -1;
                        } else {
                            x = () -> -1;
                        }
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase: Variable: name=" + name + " x.Index()=" + x.Index() + " startPos=" + startPos);
                    }
                    return x;
                } else if (chPVar == ')') {
                    nextCharVar();
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:):Return Value:x.Index=" + x.Index());
                    }
                    return x;
                } else {
                    if ((IsNotAnOperator(chPVar) && (IsNotANumber(chPVar))) && (chPVar != 0) && (chPVar != -1) && (chPVar != '(')) {
                        if ((StrVar != null) && (startPos < StrVar.length()) && (startPos < posPVar) && (chPVar != -1) && (chPVar != 0)) {
                            throw new RuntimeException("VariableCol:parseBase:Unexpected: " + (char) chPVar + " int=" + (int) chPVar + " Cannot Format:" + StrVar.substring(posPVar, StrVar.length()));
                        } else if ((chPVar != -1) && (chPVar != 0) && (posPVar >= 1) && (posPVar < StrVar.length()) && (IsNumber(chPVar))) {
                            throw new RuntimeException("VariableCol:parseBase:Please Check Code:Should not reach Here:Unexpected: " + (char) chPVar + " int=" + (int) chPVar + " Cannot Format:" + StrVar.substring(posPVar, StrVar.length()));
                        } else if ((chPVar != -1) && (chPVar != 0) && (posPVar >= 1) && (posPVar < StrVar.length()) && (IsOperator(chPVar))) {
                            throw new RuntimeException("VariableCol:parseBase:Please Check Equation Operators / Code:Should not reach Here:Unexpected: " + (char) chPVar + " int=" + (int) chPVar + " Cannot Format:" + StrVar.substring(posPVar, StrVar.length()));
                        } else if ((chPVar != -1) && (chPVar != 0) && (posPVar >= 1) && (posPVar < StrVar.length())) {
                            throw new RuntimeException("VariableCol:parseBase:Unexpected: " + (char) chPVar + " int=" + (int) chPVar + " Cannot Format:" + StrVar.substring(posPVar, StrVar.length()));
                        }
                    }
                    if (MyLogLevel.level >= LogLevel.Info.level) {
                        log.info("VariableCol:parseBase:Exception:Return Value:x.Index=" + x.Index());
                    }
                    return x;
                }
            }
        }.parseVariableCol();
    }

    //Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value
    @FunctionalInterface
    public interface LookAheadVarUnk {

        String eval();
    }

    public static LookAheadVarUnk parseVarUnk(String str, Map<String, Double> variables, String DiffWithRespTo) {

        return new Object() {
            void nextCharVar() {
                ++posVarUnk;
                chVarUnk = (posVarUnk < StrVarUnk.length()) ? StrVarUnk.charAt(posVarUnk) : -1;
                if ((chVarUnk == -1) && (posVarUnk >= StrVarUnk.length())) {
                    posVarUnk = StrVarUnk.length();
                } else if ((chVarUnk == -1) || ((chVarUnk == 0))) {
                    throw new RuntimeException("LookAheadVar:nextCharVar:Unexpected: " + (char) chVarUnk + " int=" + (int) chVarUnk + " Cannot Format:" + StrVarUnk.substring(posVarUnk, StrVarUnk.length()));
                }
            }

            boolean eatVar(int CharToEat) {
                while (chVarUnk == ' ') {
                    nextCharVar();
                }
                if (chVarUnk == CharToEat) {
                    nextCharVar();
                    return true;
                }
                return false;
            }

            void SyncVar() {
                if ((posVarUnk < StrVarUnk.length())
                        && (StrVarUnk.charAt(posVarUnk) != chVarUnk)) {
                    if (StrVarUnk.charAt(posVarUnk + 1) == chVarUnk) {
                        posVarUnk++;
                    } else if (StrVarUnk.charAt(posVarUnk - 1) == chVarUnk) {
                        posVarUnk--;
                    }
                }
            }

            LookAheadVarUnk parseVarUnk() {
                LookAheadVarUnk x = (() -> "");
                LookAheadVarUnk y = (() -> "");
                StrVarUnk = str;
                FunctionStr = "";
                LAVarTermOperator = -1;
                LATermOperator = -1;
                LAVarSimpleMapFlag = false;
                LAVarComplexMapFlag = false;
                ContainsXFlag = false;

                ParseLog("LookAheadVar", "parseVar", "Initial0", "StrVar", StrVar);
                posVarUnk = 0;
                chVarUnk = StrVarUnk.charAt(0);
                TermOperatorPos = -1;
                TermOutput = 0.0;
                int StartPos = posVarUnk;
                log.info("LookAheadVar:parseVar:StartPos=" + StartPos + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (TermOperatorPos != -1) {
                    log.info("LookAheadVar:parseVar:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrVarUnk.charAt(TermOperatorPos) + " chVarUnk=" + (char) chVarUnk);
                }
                ParseLog("LookAheadVar", "parseVar", "Initial", "StrVarUnk", StrVarUnk);
                if ((StrVarUnk != null) && (StrVarUnk.length() > 0)
                        && (StrVarUnk.length() < 2)
                        && ((chVarUnk == '*')
                        || (chVarUnk == '/')
                        || (chVarUnk == '^'))) {
                    return x;
                } else {
                    x = parseTerm();
                }
                y = x;
                if ((TrailConstantFlag == true) && (GotVariableFlag == true) || (ComplexAssignFlag == true)) {
                    ParseLog("LookAheadVar", "parseVar", "BeforeMap", "StrVarUnk", StrVarUnk);
                    ParseMap("LookAheadVar:parseVar", StrVarUnk.length(), 0, 0, DiffWithRespTo);
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

                if ((x.eval().contains("(")) || (x.eval().contains(DiffWithRespTo + "^"))) {
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
                    TermOutput = -1.0 * TermOutput;
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
                log.info("LookAheadVar:parseVar:Final :LAVarSimpleMapFlag=" + LAVarSimpleMapFlag);
                log.info("LookAheadVar:parseVar:Final :LAVarComplexMapFlag=" + LAVarComplexMapFlag);
                if (((y.eval().contains("(")) || (y.eval().contains("^" + DiffWithRespTo))) && (!y.eval().equalsIgnoreCase(x.eval()))) {
                    if ((LAVarTermOperator == -1) && (x.eval().equalsIgnoreCase("0.0"))) {
                        FunctionStr = y.eval();//Do Nothing as eval already is finished with function & Argument
                    } else if (!x.eval().equalsIgnoreCase("")) {
                        FunctionStr = x.eval() + (char) LAVarTermOperator + y.eval();//Do Nothing as eval already is finished with function & Argument
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
                ParseLog("LookAheadVar", "parseVar", "Final", "StrVarUnk", StrVarUnk);
                return x;
            }

            LookAheadVarUnk parseTerm() {
                LookAheadVarUnk x = (() -> "");
                log.info("LookAheadVar:parseTerm:TermOutput=" + TermOutput);
                for (;;) {
                    if ((eatVar('*') || (TermOperatorPos == -1) || (StrVarUnk.charAt(TermOperatorPos) == '*')) || (StrVarUnk.charAt(TermOperatorPos) == '^')) { // multiplication
                        log.info("LookAheadVar:parseTerm:Multiply:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVarUnk.length=" + StrVarUnk.length());
                        TermOutputOperator = '*';
                        LookAheadVarUnk a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if (TermOutput == -1.0) {
                                LAVarSimpleMapFlag = true;
                            }
                            if ((TermOutputSetFlag == false) || ((posVarUnk < StrVarUnk.length()) && ((StrVarUnk.charAt(posVarUnk + 1) == '*') || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                if ((!b.eval().contains("("))
                                        && (((posVarUnk + 1) < StrVarUnk.length())
                                        && ((StrVarUnk.charAt(posVarUnk + 1) == '*')
                                        || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                    TermOutput += 1.0;
                                    TermOutputSetFlag = true;
                                }
                            }
                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule1:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        } // Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo)) && ((b.eval().equalsIgnoreCase("-1.0")) || (b.eval().equalsIgnoreCase("1.0")))) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule3:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if ((TermOutputSetFlag == false) || ((posVarUnk < StrVarUnk.length()) && ((StrVarUnk.charAt(posVarUnk + 1) == '*') || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                TermOutput += 1.0;
                                if (GotBracesVarFlag == true) {
                                    GotBracesVarFlag = false;
                                    nextCharVar();
                                }
                            }
                            x = (() -> DiffWithRespTo);
                            log.info("LookAheadVar:parseTerm:Multiply:Rule4:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            log.info("LookAheadVar:parseTerm:Multiply:Rule5:a.eval()=" + a.eval() + " b.eval()=" + b.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            if ((!a.eval().equalsIgnoreCase("0.0")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (!StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> (a.eval() + "*" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))
                                    && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                //ln(2.0)*2^x
                                x = (() -> (a.eval() + "*" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            } else {
                                x = (() -> b.eval());
                            }
                            log.info("LookAheadVar:parseTerm:Multiply:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Multiply:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        if ((x.eval().contains("(")) && (FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = x.eval();
                            log.info("LookAheadVar:parseTerm:Multiply:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        } else if ((x.eval().contains("(")) && (!FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = FunctionStr + "*" + x.eval();
                            log.info("LookAheadVar:parseTerm:Multiply:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        }
                        log.info("LookAheadVar:parseTerm:Multiply:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        if ((chVarUnk == '+') || (chVarUnk == '-')) {
                            if ((LAMAPOperator == 0) && (TermOutput != -1.0)) {
                                LAMAPOperator = -1;
                            }
                            log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVarUnk=" + (char) chVarUnk);
                            return x;
                        } else if ((chVarUnk != -1) && (chVarUnk != 0)) {
                            log.info("LookAheadVar:parseTerm:Multiply:Sync:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVarUnk.length=" + StrVarUnk.length());
                            SyncVar();
                        }
                    } else if ((eatVar('/') || (StrVar.charAt(TermOperatorPos) == '/'))) { // division
                        log.info("LookAheadVar:parseTerm:Divide:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVarUnk.length=" + StrVarUnk.length());
                        TermOutputOperator = '/';
                        LookAheadVarUnk a = x, b = parseFactor();
                        if (TermOperatorPos == -1) {
                            if ((TermOutputSetFlag == false) || ((posVarUnk < StrVarUnk.length()) && ((StrVarUnk.charAt(posVarUnk + 1) == '*') || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                if ((!b.eval().contains("("))
                                        && ((posVarUnk < StrVarUnk.length())
                                        && ((StrVarUnk.charAt(posVarUnk + 1) == '*')
                                        || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                    TermOutput -= 1.0;
                                    TermOutputSetFlag = true;
                                }
                            }

                            x = (() -> b.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule1:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        }// Mapping x^-1.0 OR x^1.0
                        else if ((IsAConstant(b.eval(), DiffWithRespTo)) && ((b.eval().equalsIgnoreCase("-1.0")) || (b.eval().equalsIgnoreCase("1.0")))) {
                            x = (() -> a.eval());
                            LAVarComplexMapFlag = true;
                            log.info("LookAheadVar:parseTerm:Divide:Rule2:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } // Mapping x^2.0 OR x^-2.0 Onwards
                        else if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> a.eval());
                            log.info("LookAheadVar:parseTerm:Divide:Rule3:IsAConstant:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutput == 1.0) {
                                TermOutput = -1.0;
                                LAVarSimpleMapFlag = true;
                            } else if ((TermOutputSetFlag == false) || ((posVarUnk < StrVarUnk.length()) && ((StrVarUnk.charAt(posVarUnk + 1) == '*') || (StrVarUnk.charAt(posVarUnk + 1) == '/')))) {
                                TermOutput -= 1.0;
                                if (GotBracesVarFlag == true) {
                                    GotBracesVarFlag = false;
                                    nextCharVar();
                                }
                            }
                            x = (() -> DiffWithRespTo);
                            log.info("LookAheadVar:parseTerm:Divide:Rule4:DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
                            if ((!a.eval().equalsIgnoreCase("0.0")) && ((!IsAConstant(b.eval(), DiffWithRespTo))) && (!StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                x = (() -> (a.eval() + "/" + b.eval()));
                            } else if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Unknown"))
                                    && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("VarExpression"))) {
                                //ln(2.0)/2^x
                                x = (() -> (a.eval() + "/" + b.eval()));
                                log.info("LookAheadVar:parseTerm:Divide:Rule5:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            } else {
                                x = (() -> b.eval());
                            }
                            log.info("LookAheadVar:parseTerm:Divide:Rule5:!DiffWithRespTo:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } else if ((!b.eval().equalsIgnoreCase(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {

                            x = (() -> b.eval());
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        log.info("LookAheadVar:parseTerm:Divide:Rule6:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        if ((x.eval().contains("(")) && (FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = x.eval();
                            log.info("LookAheadVar:parseTerm:Divide:Rule7:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        } else if ((x.eval().contains("(")) && (!FunctionStr.equalsIgnoreCase(""))) {
                            FunctionStr = FunctionStr + "/" + x.eval();
                            log.info("LookAheadVar:parseTerm:Divide:Rule8:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        }
                        if ((chVarUnk == '+') || (chVarUnk == '-')) {
                            if ((TermOutput != -1.0) && (LAMAPOperator == 0)) {
                                LAMAPOperator = -1;
                            }
                            log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVarUnk=" + (char) chVarUnk);
                            return x;
                        } else if ((chVarUnk != -1) && (chVarUnk != 0)) {
                            log.info("LookAheadVar:parseTerm:Divide:Sync:Entered:TermOperatorPos:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVarUnk.length=" + StrVarUnk.length());
                            SyncVar();
                        }
                    } else {
                        // Unset MapOperator if Overall TerOutput for the Term is not -1
                        if ((LAMAPOperator == 0) && (TermOutput != -1.0)) {
                            LAMAPOperator = -1;
                        }
                        log.info("LookAheadVar:parseTerm:Return Value:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " chVarUnk=" + (char) chVarUnk);
                        return x;
                    }
                }
            }

            LookAheadVarUnk parseFactor() {
                int StartPos = posVarUnk;
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                LookAheadVarUnk x = parseBase();
                log.info("LookAheadVar:parseFactor:x.eval()=" + x.eval() + " TermOutput=" + TermOutput);
                for (;;) {
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadVarUnk a = x, b = parseBase();
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
                                TempIndConstStrSet = false;
                                log.info("LookAheadVar:parseFactor:2^a:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((IsAConstant(a.eval(), DiffWithRespTo)) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // 2^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:2^x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // a^b
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:a^b:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
                                // x^b
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:x^b:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                                // a^x
                                x = (() -> a.eval() + "^" + b.eval());
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:a^x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (IsAConstant(b.eval(), DiffWithRespTo))) {
                                // a^2
                                x = (() -> "1.0");
                                TermOutputSetFlag = true;
                                log.info("LookAheadVar:parseFactor:a^2:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("Var")) && (IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("0.0"))) {
                                //x^2  
                                if ((Double.parseDouble(b.eval()) == -1) || (Double.parseDouble(b.eval()) == 1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                } else if ((Double.parseDouble(b.eval()) < -1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                } else if ((Double.parseDouble(b.eval()) > 1) && (TermOutputOperator == '/')) {
                                    TermOutput -= Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                } else if ((Double.parseDouble(b.eval()) > 1)) {
                                    TermOutput += Double.parseDouble(b.eval());
                                    TermOutputSetFlag = true;
                                }
                                x = b;
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
                        log.info("LookAheadVar:parseFactor:x=x:x.eval()=" + x.eval() + " chVarUnk=" + (char) chVarUnk + " chVarUnk=" + chVarUnk + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        if ((GotExpntNumFlag == false)
                                && (x.eval().equalsIgnoreCase(DiffWithRespTo))
                                && (TermOutput == 0.0)
                                && ((posVarUnk < StrVarUnk.length())
                                && ((StrVarUnk.charAt(posVarUnk) == '*')
                                || (StrVarUnk.charAt(posVarUnk) == '/'))
                                || (chVarUnk == -1) || (chVarUnk == '-') || (chVarUnk == '+'))) {
                            TermOutput = 1.0;
                            TermOutputSetFlag = true;
                            x = (() -> "1.0");
                            log.info("LookAheadVar:parseFactor:x=x:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        }
                        // Needs to be mapped set
                        if ((TermOutput == -1.0) || ((TermOutput == 0.0) && (StartPos >= 1) && (StrVarUnk.charAt(StartPos - 1) == '/'))) {
                            LAMAPOperator = 0;
                        }
                        log.info("LookAheadVar:parseFactor:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAMAPOperator=" + LAMAPOperator + " GotExpntNumFlag=" + GotExpntNumFlag);
                        GotExpntNumFlag = false;
                        return x;
                    }
                }
            }

            LookAheadVarUnk parseBase() {
                int startPos = posVarUnk;
                if ((posVarUnk >= 0) && (posVarUnk < StrVarUnk.length())) {
                    log.info("LookAheadVar:parseBase:Entered:posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVar=" + StrVarUnk.substring(startPos));
                }
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                LookAheadVarUnk x = (() -> "");
                if (eatVar('-')) { // unary minus
                    LookAheadVarUnk b = parseBase();
                    if (GotExpntNumFlag == true) {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "-" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> "-" + b.eval());
                        }
                        log.info("LookAheadVar:parseBase:Number:x.eval()=" + x.eval() + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                    } else {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> b.eval());
                        }
                        log.info("LookAheadVar:parseBase:Number:x.eval()=" + x.eval() + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                    }
                    return x;
                } else if (eatVar('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chVarUnk))) { //numbers
                    log.info("LookAheadVar:parseBase:Exponent:Number:posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " StrVarUnk=" + StrVarUnk.substring(startPos));
                    while ((IsNumber(chVarUnk)) && (chVarUnk != -1)) {
                        nextCharVar();
                        if ((chVarUnk == 'E') || (chVarUnk == 'e')) {
                            nextCharVar();
                            if ((chVarUnk == '-') || (chVarUnk == '+')) {
                                nextCharVar();
                            }
                        }

                    }
                    double xx = 0.0;
                    if ((GotExpntNumFlag == true) && (posVarUnk != -1) && (StrVarUnk != null) && (!StrVarUnk.equalsIgnoreCase("")) && (!StrVar.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadVar:parseBase:Expnt Num:Number:xx=" + xx + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrVarUnk.substring(startPos, posVarUnk));
                    } else if ((GotExpntNumFlag == true) && (StrVarUnk != null) && (!StrVarUnk.equalsIgnoreCase("")) && (!StrVarUnk.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadVar:parseBase:Expnt Num:Number:xx=" + xx + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrVarUnk.substring(startPos));
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (timesLoop == 0) && (posVarUnk != -1) && (StrVarUnk != null) && (!StrVarUnk.equalsIgnoreCase("")) && (!StrVarUnk.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrVarUnk.substring(startPos, posVarUnk));
                        log.info("LookAheadVar:parseBase:Exponent:Number:xx=" + xx + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        TrailConstantFlag = true;
                        GotVariablePos = startPos;
                        GotVariableFlag = true;
                        if ((startPos >= 1) && ((StrVarUnk.charAt(startPos - 1) == '*') || (StrVarUnk.charAt(startPos - 1) == '/'))) {
                            TrailConstantOperator = StrVarUnk.charAt(startPos - 1);
                        }
                        if (ComplexAssignFlag == false) {
                            ComplexAssignFlag = true;
                        }
                    } else {
                        xx = Double.parseDouble(StrVarUnk.substring(startPos, posVarUnk));
                    }
                    log.info("LookAheadVar:parseBase:Number:xx=" + xx + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " GotExpntNumFlag=" + GotExpntNumFlag + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " TrailConstantOperator=" + (char) TrailConstantOperator);
                    final Double XX = xx;
                    if (XX >= 0.0) {
                        x = () -> "" + XX;
                    } else {
                        x = () -> "";
                    }
                    log.info("LookAheadVar:parseBase:Number:xx=" + xx + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag + " TrailConstantOperator=" + (char) TrailConstantOperator);
                    return x;
                } else if (IsVariable(chVarUnk)) {//functions & variables
                    while (((IsVariable(chVarUnk)) || (IsNumber(chVarUnk))) && (chVarUnk != -1)) {
                        nextCharVar();
                    }
                    String name = "";
                    if (posVarUnk != -1) {
                        name = StrVarUnk.substring(startPos, posVarUnk).trim();
                        if ((startPos >= 1) && ((StrVarUnk.charAt(startPos - 1) == '*') || (StrVarUnk.charAt(startPos - 1) == '/'))) {
                            LAVarTermOperator = StrVarUnk.charAt(startPos - 1);
                        } else if ((startPos >= 2) && ((StrVarUnk.charAt(startPos - 1) == '+') || (StrVarUnk.charAt(startPos - 1) == '-'))) {
                            LAVarTermOperator = StrVarUnk.charAt(startPos - 2);
                        }
                    } else {
                        name = StrVarUnk.substring(startPos).trim();
                    }

                    log.info("LookAheadVar:parseBase:Variable:name=" + name + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);

                    if ((functionsSimple.containsKey(name)) && (!name.contains(DiffWithRespTo))) {
                        LookAheadVarUnk arg = (() -> "");
                        log.info("LookAheadVar:parseBase:Function:name=" + name + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        //nextCharVar();
                        //arg = parseBase();
                        //nextCharVar();
                        eatVar('(');
                        int openBracesCount = 1;
                        int closeBracesCount = 0;
                        int startcount = posVarUnk;
                        while ((posVarUnk < StrVarUnk.length()) && ((chVarUnk != ')') || (openBracesCount != closeBracesCount))) {
                            nextCharVar();
                            if (chVarUnk == '(') {
                                openBracesCount++;
                            }
                            if (chVarUnk == ')') {
                                closeBracesCount++;
                            }
                        }
                        if ((chVarUnk == ')') && (StrVarUnk.substring(startcount, posVarUnk).contains(")"))) {
                            posVarUnk--;
                        }
                        String Exprn = StrVarUnk.substring(startcount, posVarUnk);
                        arg = (() -> Exprn);
                        eatVar(')');
                        final String MyFnStr = name;
                        final String MyStr = arg.eval();
                        log.info("LookAheadVar:parseBase:Function:arg.eval()=" + arg.eval() + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        if ((IsAConstant(Exprn, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("NumberExpression"))
                                || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                            x = (() -> MyFnStr + "(" + MyStr + ")");
                            log.info("LookAheadVar:parseBase:Function:Constant:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                            x = (() -> MyFnStr + "(" + MyStr + ")");
                            log.info("LookAheadVar:parseBase:Function:Var:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else {
                            log.info("LookAheadVar:parseBase:Function:Calling SimpleExpression:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            MathsContxtLAv.SimpleExpression MyMappedArg = parseSimple(Exprn, variables, DiffWithRespTo);
                            RestoreStateAll(variablesBackupState);
                            final String MySimpleExprn = MyMappedArg.SimpleExpr();
                            log.info("LookAheadVar:parseBase:Function:Else:arg.eval()=" + MyStr + " MySimpleExprn=" + MySimpleExprn + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            x = (() -> MyFnStr + "(" + MySimpleExprn + ")");
                            log.info("LookAheadVar:parseBase:Finished Calling SimpleExpression:Function String:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        }
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (name.contains(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                        ContainsXFlag = true;
                        log.info("LookAheadVar:parseBase:Variable:DiffWithRespTo:name=" + name + " x.eval()=" + x.eval());
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity"))) {
                        //ProLambdaExtr y=parseExpr("LookAheadVar",  name, "1.0",  "ProductRule",  DiffWithRespTo,  '*', "LowLevelRules");
                        //final String MyStr = y.StrExpr();
                        x = () -> "1.0";
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos;
                        log.info("LookAheadVar:parseBase:Variable:Extremity:name=" + name + " x.eval()=" + x.eval());
                    } else if (!name.contains(DiffWithRespTo)) {
                        ContainsIndConstFlag = true;
                        GotIndConstFlag = true;
                        GotIndConstPos = startPos;
                        final String MyStr = name;
                        x = (() -> MyStr);
                        log.info("LookAheadVar:parseBase:Variable:Independent Constant:name=" + name + " x.eval()=" + x.eval() + " ContainsIndConstFlag=" + ContainsIndConstFlag);
                    }
                    return x;
                } else if (chVarUnk == '(') {
                    GotParenPos = posVarUnk;
                    eatVar('(');
                    int openBracesCount = 1;
                    int closeBracesCount = 0;
                    int startcount = posVarUnk;
                    while ((posVarUnk < StrVarUnk.length()) && ((chVarUnk != ')') || (openBracesCount != closeBracesCount))) {
                        nextCharVar();
                        if (chVarUnk == '(') {
                            openBracesCount++;
                        }
                        if (chVarUnk == ')') {
                            closeBracesCount++;
                        }
                    }
                    if ((chVarUnk == ')') && (StrVarUnk.substring(startcount, posVarUnk).contains(")"))) {
                        posVarUnk--;
                    }
                    String Exprn = StrVarUnk.substring(startcount, posVarUnk);
                    eatVar(')');
                    log.info("LookAheadVar:parseBase:Parenthesis:Function:Exprn=" + Exprn + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                    if ((IsAConstant(Exprn, DiffWithRespTo))
                            && ((StrIsAConstant.equalsIgnoreCase("NumberExpression"))
                            || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                        x = (() -> Exprn);
                        log.info("LookAheadVar:parseBase:Parenthesis:Function:Constant:Exprn=" + Exprn + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        GotBracesVarFlag = true;
                        ContainsFuncFlag = true;
                    } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                            && (StrIsAConstant.equalsIgnoreCase("Var"))) {
                        x = (() -> Exprn);
                        log.info("LookAheadVar:parseBase:Parenthesis:Function:Var:Exprn=" + Exprn + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        GotBracesVarFlag = true;
                        ContainsFuncFlag = true;
                    } else {
                        log.info("LookAheadVar:parseBase:Parenthesis:Else:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        GotBracesVarFlag = true;
                        ContainsFuncFlag = true;
                    }
                    log.info("LookAheadVar:parseBase:BraceExpression:Exprn=" + Exprn + " x.eval()=" + x.eval() + " ContainsIndConstFlag=" + ContainsIndConstFlag + " GotBracesVarFlag=" + GotBracesVarFlag + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk + " posVarUnk=" + posVarUnk);
                    return x;
                } else {
                    if ((IsNotAnOperator(chVarUnk) && (IsNotANumber(chVarUnk))) && (chVarUnk != 0) && (chVarUnk != -1) && (chVarUnk != '(')) {
                        if ((StrVarUnk != null) && (startPos < StrVarUnk.length()) && (startPos < posVarUnk) && (chVarUnk != -1) && (chVarUnk != 0)) {
                            throw new RuntimeException("LookAheadVar:parseBase:Unexpected: " + (char) chVarUnk + " int=" + (int) chVarUnk + " Cannot Format:" + StrVar.substring(posVarUnk, StrVarUnk.length()));
                        } else if ((chVarUnk != -1) && (chVarUnk != 0) && (posVarUnk >= 1) && (posVarUnk < StrVarUnk.length()) && (IsNumber(chVarUnk))) {
                            throw new RuntimeException("LookAheadVar:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chVarUnk + " int=" + (int) chVarUnk + " Cannot Format:" + StrVarUnk.substring(posVarUnk, StrVarUnk.length()));
                        } else if ((chVarUnk != -1) && (chVarUnk != 0) && (posVarUnk >= 1) && (posVarUnk < StrVarUnk.length()) && (IsOperator(chVarUnk))) {
                            throw new RuntimeException("LookAheadVar:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chVarUnk + " int=" + (int) chVarUnk + " Cannot Format:" + StrVar.substring(posVarUnk, StrVarUnk.length()));
                        } else if ((chVarUnk != -1) && (chVarUnk != 0) && (posVarUnk >= 1) && (posVarUnk < StrVarUnk.length())) {
                            throw new RuntimeException("LookAheadVar:parseBase:Unexpected: " + (char) chVarUnk + " int=" + (int) chVarUnk + " Cannot Format:" + StrVarUnk.substring(posVarUnk, StrVarUnk.length()));
                        }
                    }
                    return x;
                }
            }
        }.parseVarUnk();
    }
//Created by Rajesh Pai
// Converts Coefficients of a Term to a Single Value

    @FunctionalInterface
    public interface LookAheadIndConstUnk {

        String eval();
    }

    public static LookAheadIndConstUnk parseIndConstUnk(String str, String DiffWithRespTo) {

        return new Object() {
            void nextCharIndConst() {
                ++posIndConstUnk;
                chIndConstUnk = (posIndConstUnk < StrIndConstUnk.length()) ? StrIndConstUnk.charAt(posIndConstUnk) : -1;
                if ((chIndConstUnk == -1) && (posIndConstUnk >= StrIndConstUnk.length())) {
                    posIndConstUnk = StrIndConstUnk.length();
                } else if ((chIndConstUnk == -1) || ((chIndConstUnk == 0))) {
                    throw new RuntimeException("LookAheadIndConst:nextCharIndConst:Unexpected: " + (char) chIndConstUnk + " int=" + (int) chIndConstUnk + " Cannot Format:" + StrIndConstUnk.substring(posIndConstUnk, StrIndConstUnk.length()));
                }
            }

            boolean eatVar(int CharToEat) {
                while (chIndConstUnk == ' ') {
                    nextCharIndConst();
                }
                if (chIndConstUnk == CharToEat) {
                    nextCharIndConst();
                    return true;
                }
                return false;
            }

            void SyncIndConst() {
                if ((posIndConstUnk < StrIndConstUnk.length())
                        && (StrIndConstUnk.charAt(posIndConstUnk) != chIndConstUnk)) {
                    if (StrIndConstUnk.charAt(posIndConstUnk + 1) == chIndConstUnk) {
                        posIndConstUnk++;
                    } else if (StrIndConstUnk.charAt(posIndConstUnk - 1) == chIndConstUnk) {
                        posIndConstUnk--;
                    }
                }
            }

            LookAheadIndConstUnk parseIndConstUnk() {
                GotBracesVarFlag = false;
                StrIndConstUnk = str;
                LookAheadIndConstUnk x = (() -> "");
                String CompleteStr = "";
                LAIndConstOperator = '*';
                posIndConstUnk = 0;
                chIndConstUnk = StrIndConstUnk.charAt(0);
                TermOperatorPos = -1;
                TermOutputIndConst = 0.0;
                TempIndConstBase = "";
                int StartPos = posIndConstUnk;
                System.out.println("LookAheadIndConst:parseIndConst:StartPos=" + StartPos + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " ComplexAssignFlag=" + ComplexAssignFlag);
                if (TermOperatorPos != -1) {
                    log.info("LookAheadIndConst:parseIndConst:TermOperatorPos=" + TermOperatorPos + " TermOperator=" + (char) StrIndConstUnk.charAt(TermOperatorPos) + " chIndConstUnk=" + (char) chIndConstUnk);
                }
                ParseLog("LookAheadIndConst", "parseIndConst", "Initial", "StrIndConstUnk", StrIndConstUnk);
                if ((StrIndConstUnk != null) && (StrIndConstUnk.length() > 0)
                        && (StrIndConstUnk.length() < 2)
                        && ((chIndConstUnk == '*')
                        || (chIndConstUnk == '/')
                        || (chIndConstUnk == '^'))) {
                    return x;
                } else {
                    x = parseTerm();
                }
                System.out.println("LookAheadIndConst:parseIndConst:Variable:x.eval()=" + x.eval() + " TermOutput=" + TermOutput + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreTermOperator=" + (char) LAPreTermOperator);
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
                if ((!CompleteStr.equalsIgnoreCase("")) && (!x.eval().equalsIgnoreCase("1")) && (!CompleteStr.equalsIgnoreCase(x.eval()))) {
                    CompleteStr = CompleteStr + "*" + x.eval();
                } else if ((!CompleteStr.equalsIgnoreCase("")) && (x.eval().equalsIgnoreCase("1"))) {
                    CompleteStr = CompleteStr;
                } else {
                    CompleteStr = x.eval();
                }
                log.info("LookAheadIndConst:parseIndConst:Final:CompleteStr=" + CompleteStr + " x.eval()=" + x.eval());
                final String MyString = CompleteStr;
                if ((posIndConstUnk < (StrIndConstUnk.length() - 1)) && (chIndConstUnk != -1) && (chIndConstUnk != 0)) {
                    x = (() -> MyString);
                } else if (posIndConstUnk < (StrIndConstUnk.length() - 1)) {
                    x = (() -> MyString);
                } else {
                    x = (() -> MyString);
                }
                log.info("LookAheadIndConst:parseIndConst:Final:x.eval()=" + x.eval());
                ParseLog("LookAheadIndConst", "parseIndConst", "Final", "StrIndConstUnk", StrIndConstUnk);
//                if ((posVar != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(posVar, str.length()).equalsIgnoreCase(""))&&(posVar <= str.length())) {
//                    throw new RuntimeException("LookAheadVar:parse:Unexpected: " + (char) chVar + " int=" + (int) chVar + " posVar=" + posVar + " str=" + str.substring(posVar, str.length()));
//                }
                ComplexAssignFlag = false;
                return x;
            }

            LookAheadIndConstUnk parseTerm() {
                LookAheadIndConstUnk x = (() -> "");
                TempIndConstStrSet = false;
                //x = parseFactor();
                //log.info("LookAheadVar:parseTerm:x.eval()=" + x.eval());
                for (;;) {
                    if ((eatVar('*') || (TermOperatorPos == -1) || (StrIndConstUnk.charAt(TermOperatorPos) == '*'))) { // multiplication
                        LookAheadIndConstUnk a = x, b = parseFactor();
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
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().contains(DiffWithRespTo))) {
                                if ((chIndConstUnk == '*') || (chIndConstUnk == '/')) {
                                    LAIndConstOperator = chIndConstUnk;
                                }
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                            x = (() -> "1.0");
                            log.info("LookAheadIndConst:parseTerm:Multiply:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if (b.eval().contains(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst += 1.0;
                            }
                            x = (() -> a.eval());

                            log.info("LookAheadIndConst:parseTerm:Multiply:DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((!b.eval().contains(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
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
                                if ((chIndConstUnk == '*') || (chIndConstUnk == '/')) {
                                    LAIndConstOperator = chIndConstUnk;
                                }
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((!b.eval().contains(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            if ((!a.eval().equalsIgnoreCase("")) && (!IsAConstant(b.eval(), DiffWithRespTo))) {
                                x = (() -> (a.eval() + "*" + b.eval()));
                            }
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().equalsIgnoreCase(DiffWithRespTo))) {
                                if ((chIndConstUnk == '*') || (chIndConstUnk == '/')) {
                                    LAIndConstOperator = chIndConstUnk;
                                }
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((!b.eval().contains(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());

                            log.info("LookAheadIndConst:parseTerm:Multiply:!DiffWithRespTo:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        }
                        TermOperatorPos = 0;//Should never Enter again
                        GotVariablePos = -1;
                        log.info("LookAheadIndConst:parseTerm:Multiply:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutput=" + TermOutput + " chVarUnk=" + (char) chVarUnk);
                        if ((chIndConstUnk == '+') || (chIndConstUnk == '-')) {
                            return x;
                        } else if ((chIndConstUnk != -1) && (chIndConstUnk != 0)) {
                            SyncIndConst();
                        }
                    } else if ((eatVar('/') || (StrIndConstUnk.charAt(TermOperatorPos) == '/'))) { // division

                        LookAheadIndConstUnk a = x, b = parseFactor();
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
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().contains(DiffWithRespTo))) {
                                if ((chIndConstUnk == '*') || (chIndConstUnk == '/')) {
                                    LAIndConstOperator = chIndConstUnk;
                                }
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Divide:TermOperatorPos:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((IsAConstant(b.eval(), DiffWithRespTo)) && (a.eval().equalsIgnoreCase("1.0"))) {
                            x = (() -> "1.0");
                            log.info("LookAheadIndConst:parseTerm:Divide:IsAConstant:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if (b.eval().equalsIgnoreCase(DiffWithRespTo)) {
                            if (TermOutputSetFlag == false) {
                                TermOutputIndConst -= 1.0;
                            }
                            x = (() -> a.eval());

                            log.info("LookAheadIndConst:parseTerm:Divide:DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((!b.eval().contains(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (!b.eval().contains("(")))) {
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
                            if ((!IsAConstant(b.eval(), DiffWithRespTo)) && (!b.eval().contains(DiffWithRespTo))) {
                                if ((chIndConstUnk == '*') || (chIndConstUnk == '/')) {
                                    LAIndConstOperator = chIndConstUnk;
                                }
                                LAPreIndConstOperator = '*';
                            }
                            log.info("LookAheadIndConst:parseTerm:Divide:!DiffWithRespTo:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        } else if ((!b.eval().contains(DiffWithRespTo)) && (!b.eval().equalsIgnoreCase("") && (b.eval().contains("(")))) {
                            x = (() -> b.eval());

                        }
                        TermOperatorPos = 0;//Should never Enter again
                        GotVariablePos = -1;
                        log.info("LookAheadIndConst:parseTerm:Divide:x.eval()=" + x.eval() + " LAIndConstOperator=" + (char) LAIndConstOperator + " LAPreIndConstOperator=" + (char) LAPreIndConstOperator + " TermOutputIndConst=" + TermOutputIndConst + " chIndConstUnk=" + (char) chIndConstUnk);
                        if ((chIndConstUnk == '+') || (chIndConstUnk == '-')) {
                            return x;
                        } else if ((chIndConstUnk != -1) && (chIndConstUnk != 0)) {
                            SyncIndConst();
                        }
                    } else {
                        return x;
                    }
                }
            }

            LookAheadIndConstUnk parseFactor() {
                TermOutputSetFlag = false;
                GotExpntNumFlag = false;
                TempIndConstBase = "";
                LookAheadIndConstUnk x = parseBase();
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval());
                if ((!x.eval().equalsIgnoreCase(""))
                        && (!x.eval().equalsIgnoreCase(DiffWithRespTo))) {
                    TempIndConstBase = x.eval();
                }
                log.info("LookAheadIndConst:parseFactor:x.eval()=" + x.eval() + " TempIndConstBase=" + TempIndConstBase);
                for (;;) {
                    if (eatVar('^')) { // exponentiation
                        GotExpntNumFlag = true;
                        LookAheadIndConstUnk a = x, b = parseBase();
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
                            } else if ((!IsAConstant(a.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant")) && (!IsAConstant(b.eval(), DiffWithRespTo)) && (StrIsAConstant.equalsIgnoreCase("IndConstant"))) {
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

            LookAheadIndConstUnk parseBase() {
                int startPos = posIndConstUnk;
                if ((posIndConstUnk >= 0) && (posIndConstUnk < StrIndConstUnk.length())) {
                    log.info("LookAheadIndConst:parseBase:Entered:posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " StrIndConstUnk=" + StrIndConstUnk.substring(startPos));
                }
                if (startPos >= 0) ; else {
                    startPos = 0;
                }
                LookAheadIndConstUnk x = (() -> "");
                if (eatVar('-')) { // unary minus
                    LookAheadIndConstUnk b = parseBase();
                    if (GotExpntNumFlag == true) {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "-" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> "-" + b.eval());
                        }
                        log.info("LookAheadIndConst:parseBase:Number:x.eval()=" + x.eval() + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                    } else {
                        if (IsAConstant(b.eval(), DiffWithRespTo)) {
                            x = (() -> "" + (Double.parseDouble(b.eval())));
                        } else {
                            x = (() -> b.eval());
                        }
                        log.info("LookAheadIndConst:parseBase:Number:x.eval()=" + x.eval() + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                    }
                    return x;
                } else if (eatVar('+')) { // unary plus
                    x = parseBase();
                    return x;
                }
                if ((IsNumber(chIndConstUnk))) { //numbers
                    log.info("LookAheadIndConst:parseBase:Exponent:Number:posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " StrIndConstUnk=" + StrIndConstUnk.substring(startPos));
                    while ((IsNumber(chIndConstUnk)) && (chIndConstUnk != -1)) {
                        nextCharIndConst();
                        if ((chIndConstUnk == 'E') || (chIndConstUnk == 'e')) {
                            nextCharIndConst();
                            if ((chIndConstUnk == '-') || (chIndConstUnk == '+')) {
                                nextCharIndConst();
                            }
                        }

                    }
                    double xx = 1.0;
                    if ((GotExpntNumFlag == true) && (!TempIndConstBase.equalsIgnoreCase("")) && (posIndConstUnk != -1) && (StrIndConstUnk != null) && (!StrIndConstUnk.equalsIgnoreCase("")) && (!StrIndConstUnk.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadIndConst:parseBase:Expnt Num:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrIndConstUnk.substring(startPos, posIndConstUnk));
                    } else if ((GotExpntNumFlag == true) && (!TempIndConstBase.equalsIgnoreCase("")) && (StrIndConstUnk != null) && (!StrIndConstUnk.equalsIgnoreCase("")) && (!StrIndConstUnk.equalsIgnoreCase("NaN"))) {
                        log.info("LookAheadIndConst:parseBase:Expnt Num:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                        xx = Double.parseDouble(StrIndConstUnk.substring(startPos));
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (timesLoop == 0) && (startPos != posIndConstUnk) && (posIndConstUnk != -1) && (StrIndConstUnk != null) && (!StrIndConstUnk.equalsIgnoreCase("")) && (!StrIndConstUnk.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrIndConstUnk.substring(startPos, posIndConstUnk));
                        log.info("LookAheadIndConst:parseBase:Trailing Constant:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    } else if ((GotExpntNumFlag == false) && (TrailConstantFlag == false) && (TempIndConstStrSet == false) && (startPos != posIndConstUnk) && (posIndConstUnk != -1) && (StrIndConstUnk != null) && (!StrIndConstUnk.equalsIgnoreCase("")) && (!StrIndConstUnk.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrIndConstUnk.substring(startPos, posIndConstUnk));
                        log.info("LookAheadIndConst:parseBase:Base^:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    } else {
                        xx = 1.0;
                    }
                    log.info("LookAheadIndConst:parseBase:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " GotExpntNumFlag=" + GotExpntNumFlag + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    final Double XX = xx;
                    if (XX >= 1.0) {
                        x = () -> "" + XX;
                    } else {
                        x = () -> "";
                    }
                    log.info("LookAheadIndConst:parseBase:Number:xx=" + xx + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk + " TrailConstantFlag=" + TrailConstantFlag + " ComplexAssignFlag=" + ComplexAssignFlag + " GotVariableFlag=" + GotVariableFlag);
                    return x;
                } else if (IsVariable(chIndConstUnk)) {//functions & variables
                    int startPos1 = posIndConstUnk;
                    if (startPos1 >= 0) ; else {
                        startPos1 = 0;
                    }
                    while (((IsVariable(chIndConstUnk)) || (IsNumber(chIndConstUnk))) && (chIndConstUnk != -1)) {
                        nextCharIndConst();
                    }
                    String name = "";
                    if (posIndConstUnk != -1) {
                        name = StrIndConstUnk.substring(startPos, posIndConstUnk).trim();
                    } else {
                        name = StrIndConstUnk.substring(startPos).trim();
                    }

                    log.info("LookAheadIndConst:parseBase:Variable:name=" + name + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                    if ((functionsSimple.containsKey(name)) && (!name.contains(DiffWithRespTo))) {
                        LookAheadIndConstUnk arg = (() -> "");
                        log.info("LookAheadIndConst:parseBase:Function:name=" + name + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                        eatVar('(');
                        int openBracesCount = 1;
                        int closeBracesCount = 0;
                        int startcount = posIndConstUnk;
                        while ((posIndConstUnk < StrIndConstUnk.length()) && ((chIndConstUnk != ')') || (openBracesCount != closeBracesCount))) {
                            nextCharIndConst();
                            if (chIndConstUnk == '(') {
                                openBracesCount++;
                            }
                            if (chIndConstUnk == ')') {
                                closeBracesCount++;
                            }
                        }
                        if ((chIndConstUnk == ')') && (StrIndConstUnk.substring(startcount, posIndConstUnk).contains(")"))) {
                            posIndConstUnk--;
                        }
                        String Exprn = StrIndConstUnk.substring(startcount, posIndConstUnk);
                        arg = (() -> Exprn);
                        eatVar(')');
                        final String MyFnStr = name;
                        final String MyStr = arg.eval();
                        log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + arg.eval() + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                        if ((IsAConstant(Exprn, DiffWithRespTo))
                                && ((StrIsAConstant.equalsIgnoreCase("NumberExpression"))
                                || (StrIsAConstant.equalsIgnoreCase("NumberExpression")))) {
                            x = (() -> MyFnStr + "(" + MyStr + ")");
                            log.info("LookAheadIndConst:parseBase:Function:Constant:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVarUnk=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else if ((!IsAConstant(Exprn, DiffWithRespTo))
                                && (StrIsAConstant.equalsIgnoreCase("Var"))) {

                            log.info("LookAheadIndConst:parseBase:Function:Var:arg.eval()=" + MyStr + " TermOutput=" + TermOutput + " posVar=" + posVarUnk + " chVarUnk=" + (char) chVarUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        } else {
                            log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + arg.eval() + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                            Map<String, String> variablesBackupState = new HashMap<>();
                            BackupStateAll(variablesBackupState);
                            MathsContxtLAv.SimpleExpression MyMappedArg = parseSimple(StrIndConstUnk.substring(startcount, posIndConstUnk), variables, DiffWithRespTo);
                            RestoreStateAll(variablesBackupState);
                            final String MySimpleExprn = MyMappedArg.SimpleExpr();
                            log.info("LookAheadIndConst:parseBase:Function:arg.eval()=" + MyStr + " MySimpleExprn=" + MySimpleExprn + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                            if (!MyStr.equalsIgnoreCase("")) {
                                x = (() -> MyFnStr + "(" + MySimpleExprn + ")");
                            }
                            log.info("LookAheadIndConst:parseBase:Complete Function String:x.eval()=" + x.eval() + " posIndConstUnk=" + posIndConstUnk + " chIndConstUnk=" + (char) chIndConstUnk);
                            GotBracesVarFlag = true;
                            ContainsFuncFlag = true;
                        }
                    } else if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (!name.equalsIgnoreCase("Infinity")) && (!name.contains(DiffWithRespTo))) {
                        final String MyStr = name;
                        x = () -> MyStr;
                        System.out.println("LookAheadIndConst:parseBase:name=" + name);
                    } else if ((name.equalsIgnoreCase("NaN")) || (name.equalsIgnoreCase("Infinity"))) {
                        MathsContxtLAv.ProLambdaExtr y = parseExpr("LookAheadIndConst", name, "1.0", "ProductRule", DiffWithRespTo, '*', "LowLevelRules");
                        final String MyStr = y.StrExpr();
                        x = () -> MyStr;
                    }
                    if ((!name.equalsIgnoreCase(DiffWithRespTo))) {
                        //ContainsIndConstFlag = true;
                        //GotIndConstFlag = true;
                        //GotIndConstPos = startPos1;
                    } else if (ComplexAssignFlag == false) {
                        GotVariablePos = startPos1;
                        if ((startPos1 >= 2) && ((StrIndConstUnk.charAt(startPos1 - 1) == '+') || (StrIndConstUnk.charAt(startPos1 - 1) == '-'))) {
                            TermOperatorPos = startPos1 - 2;
                        } else if (startPos1 >= 1) {
                            TermOperatorPos = startPos1 - 1;
                        }
                        GotVariableFlag = true;
                        ComplexAssignFlag = true;
                    }
                    return x;
                } else if (chIndConstUnk == '(') {
                    ParenthesisFlag = true;
                    GotParenPos = posIndConstUnk;
                    nextCharIndConst();
                    ParenthesisOperator = chIndConstUnk;
                    return x;
                } else if (chIndConstUnk == ')') {
                    ParenthesisOperator = 0;
                    ParenthesisFlag = false;
                    nextCharIndConst();
                    return x;
                } else {
                    if ((StrIndConstUnk != null) && (startPos < StrIndConstUnk.length()) && (startPos < posIndConstUnk) && (chIndConstUnk != -1) && (chIndConstUnk != 0)) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConstUnk + " int=" + (int) chIndConstUnk + " Cannot Format:" + StrIndConstUnk.substring(posIndConstUnk, StrIndConstUnk.length()));
                    } else if ((chIndConstUnk != -1) && (chIndConstUnk != 0) && (posIndConstUnk >= 1) && (posIndConstUnk < StrIndConstUnk.length()) && (IsNumber(chIndConstUnk))) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Please Check Code:Should NOT reach Here:Unexpected: " + (char) chIndConstUnk + " int=" + (int) chIndConstUnk + " Cannot Format:" + StrIndConstUnk.substring(posIndConstUnk, StrIndConstUnk.length()));
                    } else if ((chIndConstUnk != -1) && (chIndConstUnk != 0) && (posIndConstUnk >= 1) && (posIndConstUnk < StrIndConstUnk.length()) && (IsOperator(chIndConstUnk))) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Please Check Equation Operators:Should NOT reach Here:Unexpected: " + (char) chIndConstUnk + " int=" + (int) chIndConstUnk + " Cannot Format:" + StrIndConstUnk.substring(posIndConstUnk, StrIndConstUnk.length()));
                    } else if ((chIndConstUnk != -1) && (chIndConstUnk != 0) && (posIndConstUnk >= 1) && (posIndConstUnk < StrIndConstUnk.length())) {
                        throw new RuntimeException("LookAheadIndConst:parseBase:Unexpected: " + (char) chIndConstUnk + " int=" + (int) chIndConstUnk + " Cannot Format:" + StrIndConstUnk.substring(posIndConstUnk, StrIndConstUnk.length()));
                    }
                    return x;
                }
            }
        }.parseIndConstUnk();
    }
    static void CallGaussSolver(int row, int row1, int NumOfEqn, int NumOfUnknown, int eqn, int kspace, String ColumnVariables, String BaseDiffWithRespTo, boolean SolveFlag, boolean SolveNoConstantFlag) {
        String FuncF="x+y";
        String FuncG="x-y";
                
        SolveForVar.SolveSimulGauss F = parseSolveSimul(row, row1, NumOfEqn, NumOfUnknown, eqn, kspace, ColumnVariables, BaseDiffWithRespTo, SolveFlag, SolveNoConstantFlag, FuncF, 0.0, FuncG, 0.0);
        String StrResult = F.Eliminate();
        String[] DataTemp;
        String[] VarValueName;
        String[] VarValue;
        if (StrResult.contains("ValidResult")) {
            VarValueName = new String[5];
            VarValue = new String[5];
            DataTemp = StrResult.split("@");
            if (DataTemp.length == 5) {
                //Slope
                String[] Temp = DataTemp[1].split(":");
                VarValueName[0] = Temp[0];
                VarValue[0] = Temp[1];
                Slope = Double.parseDouble(VarValue[0]);
                //Constant
                Temp = DataTemp[2].split(":");
                VarValueName[1] = Temp[0];
                VarValue[1] = Temp[1];
                Constant = Double.parseDouble(VarValue[1]);
                //CoeffVar
                Temp = DataTemp[3].split(":");
                VarValueName[0] = Temp[0];
                VarValue[2] = Temp[1];
                CoeffVar = Integer.parseInt(VarValue[2]);
                //EliminatedVar
                Temp = DataTemp[4].split(":");
                VarValueName[1] = Temp[0];
                VarValue[3] = Temp[1];
                EliminatedVar = Integer.parseInt(VarValue[3]);
            }
        } else if (StrResult.contains("InvalidResult")) {
            VarValueName = new String[5];
            VarValue = new String[5];
            DataTemp = StrResult.split("@");
            if (DataTemp.length == 5) {
                //Slope
                String[] Temp = DataTemp[1].split(":");
                VarValueName[0] = Temp[0];
                VarValue[0] = Temp[1];
                Slope = Double.parseDouble(VarValue[0]);
                //Constant
                Temp = DataTemp[2].split(":");
                VarValueName[1] = Temp[0];
                VarValue[1] = Temp[1];
                Constant = Double.parseDouble(VarValue[1]);
                //CoeffVar
                Temp = DataTemp[3].split(":");
                VarValueName[0] = Temp[0];
                VarValue[2] = Temp[1];
                CoeffVar = Integer.parseInt(VarValue[2]);
                //EliminatedVar
                Temp = DataTemp[4].split(":");
                VarValueName[1] = Temp[0];
                VarValue[3] = Temp[1];
                EliminatedVar = Integer.parseInt(VarValue[3]);
            }
        }
    }

    static void ValidateSolutionToEqn(String Exprn, String Mname, Double m, String Cname, Double c, Double LHS) {
        double ExprnexpValue = 0;
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        if ((CoeffVar > -1)) {
            variables.put(Mname, m);
        }
        if ((EliminatedVar > -1)) {
            variables.put(Cname, c);
        }
        expValue = Calculus.MathsContxtLAv.parse(Exprn, variables, "x", "Silent", Calculus.MathsContxtLAv.LogLevel.Off);
        ExprnexpValue = expValue.eval();
        System.out.println(" ValidateSolutionToEqn:m=" + Mname + "=" + m);
        if ((EliminatedVar > -1)) {
            System.out.println(" ValidateSolutionToEqn:c=" + Cname + "=" + c);
        }
        System.out.println(" ValidateSolutionToEqn:Exprn=" + Exprn);
        if (ExprnexpValue == LHS.doubleValue()) {
            System.out.println(" ValidateSolutionToEqn:Result(Expn Value)(RHS)=" + ExprnexpValue + " LHS=" + LHS);
            NoNoiseNumEqn++;
        } else if (ExprnexpValue != LHS.doubleValue()) {
            System.out.println(ConsoleColors.RED + " ValidateSolutionToEqn:Result(Expn Value)(RHS)=" + ExprnexpValue + " LHS=" + LHS + ConsoleColors.RESET);
            NoiseyNumEqn++;
        }
    }
}
