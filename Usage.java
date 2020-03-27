/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import DOEMatrixGen.MathsContxtLAv0_1_Prod.DiffExpr;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.Expression;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.FoG;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.IntegrExpr;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncDiff;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncExpress;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncIntegr;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncSimple;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.OrderVar;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.Predicate;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.SimpleAlgebra;
import DOEMatrixGen.MathsContxtLAv0_1_Prod.SimpleExpression;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.eatAll;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parse;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseAlgebra;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseDiff;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseFoG;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseIntegr;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseOrder;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parsePredicate;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseSimple;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.variablesDiff;
import static DOEMatrixGen.TestHarness.PassFailTestCaseString;
import static DOEMatrixGen.TestHarness.PassFailTestCaseValue;
import static DOEMatrixGen.TestHarness.TestPredicate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class Usage {

    public static Logger log = Logger.getLogger(Usage.class.getName());
    public static String DiffWithRespTo = "x";
    public static int MaxDiffOrder = 6;
    public static int MaxIntegreOrder = 6;
    
    static String EqnUnderTest;
    // Differentiation Mapping
    static String[] EqnSolArrayMap1;
    static String[] EqnSolArrayDiffAlone;
    static String[] EqnSolArrayMap2;
    
    // Integration Mapping
    static String[] EqnSolArrayMap3;
    static String[] EqnSolArrayIntegrAlone;
    static String[] EqnSolArrayMap4;
    
    static String[] EqnSolArrayIntegr_BeforeDiff;
    static String[] EqnSolArrayDiff_AfterIntegr;
    
    static String[] EqnSolArrayDiff_BeforeIntegr;
    static String[] EqnSolArrayIntegr_AfterDiff;
    
    
    static int[] EqnSolOrder;
    static int TestCase = 0;
    static int EndTestCase = 0;
    static int LastTestCaseHarness=0;
    static int TestCaseLoopTimes = 0;
    static String EqnRegressionDiffStatus = "All RED";
    static String EqnRegressionIntegreStatus = "All RED";
    static String TestCaseName = "Standard";
    static String TestCaseMatrixID = "Standard";
    
public static String FailingTestHarnessDiff(String AlgoName,int j) {
//Equation Component Testing
//1)	Variables :L=3:Single Letter, Multi Letter, with Numerics
//2)	Coefficient:L=9:0.0,0,1.0,1,-1.0,-1,5,-5,-5.0
//3)	Exponent:L=6: +ve,-ve,0,1,Independent Constants, Long Form (x * x*x)
//4)	Operator:L=2: *,/
//5)	Constants:L=3: Start of Expr, Middle (2-5) Terms, End Term
//6)	Independent Constants:L=3:Single Letter, Multi Letter, with Numerics
//7)	Expression
//8)	Term:L=3: Start Term, Middle (2-5) Terms, End Term
//9)	Term Sign:L=3: +ve, -ve, No Sign
//10)	Exponent Sign:L=3: +ve, -ve, No Sign
//11)	Coefficient Sign:L=3: +ve, -ve, No Sign
//12)	Variable Sign:L=3: +ve, -ve, No Sign
//13)	Parenthesis:L=3 : 1st Term, 2nd Term , 3-5 Terms
//14)	Functions:L5: Sin, Cos, Tan, Log, Ln
//15)	Argument of Functions:L=4: Expr Alone(3 types), Expr with Inner Function
        if((j >= 100)&&(j <= 105))
            TestHarness.SanityTestCases(AlgoName,j);
        else if((j >= 5019)&&(j <= 5042))
            TestHarnessUnitTest.UnitTestCases(AlgoName,j);
            //
            // Variants
            //
        else if ((j >= 10001)&&(j <= 10005))
            TestHarnessNames.Names(j);
        else if((j >= 20001)&&(j <= 20092))
            TestHarnessCoefficients.Coefficients(AlgoName,j);
        else if((j >= 30001)&&(j <= 30011))
            TestHarness.Exponent(AlgoName,j);
        else if((j >= 40001)&&(j <= 40034))
            TestHarnessSignedX.SignedX(AlgoName,j);
        else if((j >= 50001)&&(j <= 50029))
           TestHarnessIndConstant.IndConstant(AlgoName,j);
        else if((j >= 80001)&&(j <= 80012))
            TestHarness.Parenthesis(AlgoName,j);
        else if((j >= 110001)&&(j <= 110007))
            TestHarness.SoftwareEngg(AlgoName,j);
        else {
            switch (j) {
            //Function Test Cases
            case 90001:
                TestHarness.TestCaseDiff90001();
                break;
            //Function Argument Test Cases
            case 100001:
                TestHarness.TestCaseDiff100001();
                break;
            default:
                System.out.println(ConsoleColors.RED +"FailingTestHarnessDiff:j= " + j  + ": Infinite Loop"+ ConsoleColors.RESET);
                break;
         }
        }
        
        return EqnUnderTest;
    }
    public static void mainPredicate(String[] args) {
        Map<String, Double> variables = new HashMap<>();
        String Exprn = TestPredicate();
        String DiffWithRespTo = "x";
        Predicate expValue;

        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');

        int i = 1;
        for (double x = -2, y = 7; i < 10; y++, x++, i++) {
            variables.put("x", x);
            variables.put("y", y);
            System.out.println("main: x=" + x + " Exprn=" + Exprn);
            expValue = parsePredicate(Exprn, variables);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
        }
    }

    public static void mainDiff(String... args) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        String Exprn = "";//eatAll(FailingLowLevelUnitTestCases(), ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String DiffWithRespTo = "x";
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        String StrexpSimpleExpress = "";

        int i = 1;
        for (double x = 1, y = 7; i < 5; y++, x++, i++) {
            variables.put(DiffWithRespTo, x);
            variables.put("y", y);
            variables.put(Constant, 5.0);

            System.out.println("main: x=" + x + " Exprn=" + Exprn);
            expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
            expValue = parse(Exprn, variables, DiffWithRespTo);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());

            StrexpSimpleExpress = expSimple.SimpleExpr();

            System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
            System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

            exp = parseDiff(Exprn, variables, variablesDiff, DiffWithRespTo);
            StrexpSimpleExpress = exp.DiffExpr();
            System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
            System.out.println(" main:Result(Diff Value)=" + expValue.eval());

            expSimple = parseSimple(StrexpSimpleExpress, variables, variablesDiff, DiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
            expValue = parse(Exprn, variables, DiffWithRespTo);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
        }
    }

    public static void mainIntegral(String... args) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesIntegr = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        String Exprn = "";//eatAll(FailingLowLevelUnitTestCases(), ' ');
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr expIntgr;

        MyFuncExpress();
        MyFuncSimple();
        MyFuncIntegr();

        String DiffWithRespTo = "x";
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        String StrexpIntegrExpress = "";
        String StrexpSimpleExpress = "";
        variablesIntegr.put(DiffWithRespTo, DiffWithRespTo);
        variablesIntegr.put(Constant, Constant);
        StrexpIntegrExpress = Exprn;

        int i = 1;
        for (double x = -20, y = 7; i < 2; y++, x++, i++) {
            variables.put(DiffWithRespTo, x);
            variables.put("y", y);
            variables.put(Constant, 5.0);
            System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpIntegrExpress);
            expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
            System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

            expIntgr = parseIntegr(StrexpIntegrExpress, variables, variablesDiff, DiffWithRespTo);
            StrexpIntegrExpress = expIntgr.IntegrExpr();
            System.out.println(" main:Result(Integral Equation)(" + i + " th order)=> " + StrexpIntegrExpress);
            expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
            System.out.println(" main:Result(Integral Value)=" + expValue.eval());

            expSimple = parseSimple(StrexpIntegrExpress, variables, variablesDiff, DiffWithRespTo);
            StrexpIntegrExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Integral Equation)(" + i + " th order)=> " + StrexpIntegrExpress);
            expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
            System.out.println(" main:Result(Simplified Integral Value)=" + expValue.eval());
            expValue = parse(Exprn, variables, DiffWithRespTo);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());

            System.out.print(System.lineSeparator());
        }
    }

    public static void mainDiffIntegral(String[] args) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        Map<String, String> variablesIntegr = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        String Exprn = "";//eatAll(FailingLowLevelUnitTestCases(), ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
        IntegrExpr expIntgr;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        MyFuncIntegr();

        String DiffWithRespTo = "x";
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        String StrexpIntegrExpress = "";
        String StrexpSimpleExpress = "";
        variablesIntegr.put(DiffWithRespTo, DiffWithRespTo);
        variablesIntegr.put(Constant, Constant);

        int i = 1;
        for (double x = -20, y = 7; i < 14; y++, x++, i++) {
            variables.put(DiffWithRespTo, x);
            variables.put("y", y);
            variables.put(Constant, 5.0);
            for (int j = 1; j <= i; j++) {
                System.out.println("main: x=" + x + " Exprn=" + Exprn);
                expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
                expValue = parse(Exprn, variables, DiffWithRespTo);
                System.out.println(" main:Result(Expn Value)=" + expValue.eval());

                StrexpSimpleExpress = expSimple.SimpleExpr();

                System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpSimpleExpress);
                expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
                System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

                exp = parseDiff(StrexpSimpleExpress, variables, variablesDiff, DiffWithRespTo);
                StrexpSimpleExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + j + " th order)=> " + StrexpSimpleExpress);
                expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
                System.out.println(" main:Result(Diff Value)=" + expValue.eval());

                expSimple = parseSimple(StrexpSimpleExpress, variables, variablesDiff, DiffWithRespTo);
                StrexpSimpleExpress = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
                expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
                System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
                System.out.print(System.lineSeparator());
                expValue = parse(Exprn, variables, DiffWithRespTo);
                System.out.println(" main:Result(Expn Value)=" + expValue.eval());
                Exprn = StrexpSimpleExpress;
            }
            StrexpIntegrExpress = StrexpSimpleExpress;
            for (int j = 1; j <= i; j++) {
                System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpIntegrExpress);
                expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
                System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

                expIntgr = parseIntegr(StrexpIntegrExpress, variables, variablesDiff, DiffWithRespTo);
                StrexpIntegrExpress = expIntgr.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + j + " th order)=> " + StrexpIntegrExpress);
                expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
                System.out.println(" main:Result(Integral Value)=" + expValue.eval());

                expSimple = parseSimple(StrexpIntegrExpress, variables, variablesDiff, DiffWithRespTo);
                StrexpIntegrExpress = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + i + " th order)=> " + StrexpIntegrExpress);
                expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo);
                System.out.println(" main:Result(Simplified Integral Value)=" + expValue.eval());
                expValue = parse(Exprn, variables, DiffWithRespTo);
                System.out.println(" main:Result(Expn Value)=" + expValue.eval());

                System.out.print(System.lineSeparator());
            }
            Exprn = StrexpIntegrExpress;
        }
    }

    public static String RandomEquation(String a, String c, String RandVar) {
        String Exprn = a + " * " + RandVar + "^7" + " + " + RandVar + "^5" + " + " + RandVar + "^2" + " - " + RandVar + " + " + c;
        Exprn = eatAll(Exprn, ' ');
        return Exprn;
    }

    public static String BrownianMotionEquation(String N, String ETA, String r, String R, String T, String x, String t, String RandVar) {
        String Exprn = N + "*" + "sqrt((3*" + N + "*" + ETA + "*" + r + ")/(2*" + R + "*" + T + "))" + "*" + "exp((-3*" + Math.PI + "*" + ETA + "*" + N + "*" + r + "*" + x + "^2)/(2*" + R + "*" + T + "*" + t + "))";
        Exprn = eatAll(Exprn, ' ');
        return Exprn;
    }

    public static String FormEquation(long Xn,long MgtACoeff, long Mgtc, long MgtbCoeff,String TechDiffWithRespTo, int MgtMaxExponent) {
        //if(Xn==0) MaxExponent=5;
        //else MaxExponent=(int)Xn;
        long bCoeffStore=MgtbCoeff;
        //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff );
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent );
        for (int i = MgtMaxExponent - 1; i > 1; i--) {
            if(MgtbCoeff >2)
                    Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
            else {
                Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
                MgtbCoeff=bCoeffStore;
            }
            //MgtbCoeff=GetPrimeNumber(MgtbCoeff,i);
            MgtbCoeff++;
            //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff + " i=" + i);
        }
        if(MgtbCoeff > 2)
                    Exprn =Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo;
        else 
            Exprn =Exprn + " + " + TechDiffWithRespTo;
        Exprn = Exprn + " + " + Mgtc;
        Exprn = eatAll(Exprn, ' ');
        log.warn("FormEquation:Exprn=" + Exprn);
        return Exprn;
    }

    public static void mainRegresssionDiffTest(int MaxOrder, TestHarness TestData) {
        String Exprn = "";
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";
        //String DiffWithRespTo = "Xn";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int j = TestCase;
        boolean TestCasePassStrFlag = false;
        boolean TestCasePassValFlag = false;
        boolean TestCasePassHighLevelFlag = true;
        HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
        HashMap<Integer, String> ExceptionTestCases = new HashMap<Integer, String>();
        int MaxIndexFail = 0;
        int MaxIndexExcept = 0;
        int MaxOrder1 = MaxOrder;
        int i = 1;
        int TotalTestCases = 0;
        
        String OverallRegressionStatus = "Diff:Pass All (5019 to 100001) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if(EndTestCase > LastTestCaseHarness)
            EndTestCase=LastTestCaseHarness;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = FailingTestHarnessDiff("mainDiffTest",j);
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder >= MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }

            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            } else {
                j = RegressionControl(j);
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                continue;
            }
            System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + " j=" + j +  ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
            i = 1;
            TotalTestCases++;
            TestCasePassHighLevelFlag=true;
            String StageMapping = "";
            variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
            try {
                for (double x = 1, y = 7; i < MaxOrder1; y++, x++, i++) {
                    TestCasePassStrFlag = false;
                    TestCasePassValFlag = false;
                    variables.put(DiffWithRespTo, x);
                    variables.put("y", y);
                    variables.put("a", 1.0);
                    variables.put("b", 2.0);//variables.put("b", 0.0);
                    variables.put("c", -1.0);
                    variables.put("d", 2.0);
                    variables.put("p", 3.0);
                    variables.put("q", 0.0);
                    variables.put("r", -1.0);
                    variables.put("s", 2.0);
                    variables.put(Constant, 5.0);
                    StageMapping = "Basic Mapping";
                    expOrder = parseOrder(Exprn, DiffWithRespTo);
                    myOrder = expOrder.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn + " Order=" + myOrder);

                    expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);
                    
                    
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    ExprnexpValue = expValue.eval();
                    System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);
                    
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    DblexpValueMap1 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                    StageMapping = "Differentiation";
                    exp = parseDiff(StrexpSimpleExpressMap1, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = exp.DiffExpr();
                    System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    StageMapping = "Differentiation Mapping";
                    expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                    System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpressMap2);
                    expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo);
                    DblexpValueMap2 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                    System.out.print(System.lineSeparator());
                    if (PassFailTestCaseString(TestCase, i, "Diff" , Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap1[i], EqnSolArrayDiffAlone[i], EqnSolArrayMap2[i])) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                        TestCasePassStrFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }

                    if (PassFailTestCaseValue(TestCase, i, "Diff",Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2,variables)) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                        TestCasePassValFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                         if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }
                    Exprn = StrexpSimpleExpressMap2;
                    System.out.print(System.lineSeparator());

                    if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true))
                ;//Do Nothing : Test Case Passed
                    else {
                        String Temp = FailedTestCases.get(MaxIndexFail);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        }
                        FailedTestCases.put(MaxIndexFail, HashVal);
                        //i=MaxOrder1;
                    }
                }
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + i + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                       }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                            HashVal1 = Temp1 + "," + ":Order=" + (i ) ;
                } else {
                            HashVal1 = "TestCase=" + TestCase + ":Order=" + (i ) ;
                       }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                //i=MaxOrder1;
                TestCasePassHighLevelFlag=false;
            }
            System.out.print(System.lineSeparator());

            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControl(j);
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag==false) MaxIndexFail++;
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : Total Failed=" + MaxIndexFail + " Out of Run TestCases=" + TotalTestCases +  " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainDiffTest: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static int RegressionControl(int j) {
        if ((j >= 100) && (j < 105)) {
            j++;
        } else if ((j >= 5019) && (j < 5042)) {
            j++;
        } else if ((j >= 10001) && (j < 10005)) {
            j++;
        } else if ((j >= 20001) && (j < 20092)) {
            j++;
        } else if ((j >= 30001) && (j < 30011)) {
            j++;
        }  else if ((j >= 40001) && (j < 40034)) {
            j++;
        }else if ((j >= 50001) && (j < 50029)) {
            j++;
        }else if( (j >= 80001) && (j < 80012)){
            j++;
        } else if( (j >= 110001) && (j < 110007)){
            j++;
        } else if (j == 90001) {
            j = 100001;
        }else if (j == 100001) {
            j = 110001;
        }else if (j == 110007) {
            j = 120001;
        } 
//        else if ((j >= 90001) && (j < 90002)) {
//            j++;
//        } else if ((j >= 100001) && (j < 100002)) {
//            j++;
//        }    
        //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        }else if ((j == 105)) {
            j = 5019;
        }else if (j == 5042) {
            j = 10001;
        } else if (j == 10005) {
            j = 20001;
        } else if (j == 20092) {
            j = 30001;
        } else if (j == 30011) {
            j = 40001;
        }else if (j == 40034) {
            j = 50001;
        }else if (j == 50029) {
            j = 80001;
        } else if (j == 80012) {
            j = 90001;
        }
        else {
            System.out.println(ConsoleColors.RED + "RegressionControl:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void mainRegressionIntegrTest(int MaxOrder, TestHarness TestData) {

        String Exprn = "";
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        MyFuncIntegr();
        
        String Constant = "C";
        //String DiffWithRespTo = "Xn";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int j = TestCase;
        boolean TestCasePassStrFlag = false;
        boolean TestCasePassValFlag = false;
        boolean TestCasePassHighLevelFlag = true;
        HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
        HashMap<Integer, String> ExceptionTestCases = new HashMap<Integer, String>();
        int MaxIndexFail = 0;
        int MaxIndexExcept = 0;
        int MaxOrder1 = MaxOrder;
        int i = 1;
        int TotalTestCases=0;
        String OverallRegressionStatus = "Integral:"
                + "\n" + "TestCases:Unit Test Cases" + " 24"
                + "\n" + "TestCases:Names "
                + "\n" + "TestCases:Coefficients " + " 20016, 20017 "
                + "\n" + "TestCases:Exponent " + " 30002 to 30008";
        System.out.print(ConsoleColors.BLUE + "Integral Overall Regression Status =" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if(EndTestCase > LastTestCaseHarness)
            EndTestCase=LastTestCaseHarness;
            
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = FailingTestHarnessDiff("mainIntegrTest",j);
            if (MaxOrder == -1) {
                MaxOrder1 = MaxIntegreOrder; //From Test Case
            } else if (MaxOrder > MaxIntegreOrder) {
                MaxOrder1 = MaxIntegreOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }

            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            } else {
                j = RegressionControl(j);
                System.out.println(ConsoleColors.RED + "mainIntegrTest: j=" + j + " TestCase=" + TestCase);
                continue;
            }
            System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
            i = 1;
            TotalTestCases++;
            TestCasePassHighLevelFlag=true;
            String StageMapping = "";
            variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
            try {
                for (double x = 1, y = 7; i < MaxOrder1; y++, /*x++,*/ i++) {
                    TestCasePassStrFlag = false;
                    TestCasePassValFlag = false;
                    variables.put(DiffWithRespTo, x);
                    variables.put("y", y);
                    variables.put("a", 1.0);
                    variables.put("b", 2.0);//variables.put("b", 0.0);
                    variables.put("c", -1.0);
                    variables.put("d", 2.0);
                    variables.put("p", 1.0);
                    variables.put("q", 0.0);
                    variables.put("r", -1.0);
                    variables.put("s", 2.0);
                    variables.put(Constant, 5.0);
                    StageMapping = "Basic Mapping";
                    expOrder = parseOrder(Exprn, DiffWithRespTo);
                    myOrder = expOrder.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn + " Order=" + myOrder);
                    expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);
                    
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    ExprnexpValue = expValue.eval();
                    System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);
                    
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    DblexpValueMap1 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                    StageMapping = "Integration";
                    exp = parseIntegr(StrexpSimpleExpressMap1, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = exp.IntegrExpr();
                    System.out.println(" main:Result(Integral Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    
                    StageMapping = "Integration Mapping";
                    expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                    System.out.println(" main:Result(Simplified Integral Equation)(" + i + " th order)=> " + StrexpSimpleExpressMap2);
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                    expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo);
                    DblexpValueMap2 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                    System.out.print(System.lineSeparator());

                    if (PassFailTestCaseString(TestCase, i, "Integre" , Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap3[i], EqnSolArrayIntegrAlone[i], EqnSolArrayMap4[i])) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                        TestCasePassStrFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }

                    if (PassFailTestCaseValue(TestCase, i, "Integre" , Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2,variables)) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                        TestCasePassValFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }
                    Exprn = StrexpSimpleExpressMap2;
                    System.out.print(System.lineSeparator());

                    if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true))
                ;//Do Nothing : Test Case Passed
                    else {
                        String Temp = FailedTestCases.get(MaxIndexFail);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        }
                        FailedTestCases.put(MaxIndexFail, HashVal);
                        //i=MaxOrder1;
                    }
                }
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + i + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i ) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                       }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                            HashVal1 = Temp1 + "," + ":Order=" + (i ) ;
                } else {
                            HashVal1 = "TestCase=" + TestCase + ":Order=" + (i ) ;
                       }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                i=MaxOrder1;
                TestCasePassHighLevelFlag=false;
            }
            System.out.print(System.lineSeparator());

            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControl(j);
            }
            if (TestCasePassHighLevelFlag==false) MaxIndexFail++;
        }
        for(int a=0; a < 10; a++)
                System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : Total Failed=" + MaxIndexFail + " Out of Run TestCases=" + TotalTestCases + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainIntegrTest: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void mainIntegreDiffTest(String Exprn, int MaxOrder, TestHarness TestData) {

        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
        IntegrExpr expIntegr;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int j = TestCase;
        boolean TestCasePassStrFlag = false;
        boolean TestCasePassValFlag = false;
        boolean TestCasePassHighLevelFlag = true;
        HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
        int MaxIndexFail = 0;
        int MaxOrder1 = MaxOrder;
        int i = 1;
        while (j <= TestCaseLoopTimes) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = FailingTestHarnessDiff("mainIntegreDiffTest",j);
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder > MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }

            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            } else {
                j = RegressionControl(j);
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                continue;
            }

            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            } else {
                continue;
            }
            System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Diff Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
            i = 1;
            TestCasePassHighLevelFlag=true;
            String StageMapping = "";

            try {
                for (double x = 1, y = 7; i < MaxOrder; y++, x++, i++) {
                    TestCasePassStrFlag = false;
                    TestCasePassValFlag = false;
                    variables.put(DiffWithRespTo, x);
                    variables.put("y", y);
                    variables.put(Constant, 5.0);
                    StageMapping = "Basic Mapping";
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn);
                    expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
                    System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);
                    expValue = parse(Exprn, variables, DiffWithRespTo);
                    ExprnexpValue = expValue.eval();
                    StrexpSimpleExpressMap1 = expSimple.SimpleExpr();

                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    DblexpValueMap1 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                    StageMapping = "Integration";
                    expIntegr = parseIntegr(StrexpSimpleExpressMap1, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = expIntegr.IntegrExpr();
                    System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);

                    StageMapping = "Differentiation";
                    exp = parseDiff(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = exp.DiffExpr();
                    System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);

                    StageMapping = "Differentiation Mapping";
                    expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                    System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpressMap2);
                    expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo);
                    DblexpValueMap2 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                    System.out.print(System.lineSeparator());
                    if (PassFailTestCaseString(TestCase, i, "Diff", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap3[i], EqnSolArrayIntegrAlone[i], EqnSolArrayMap4[i])) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                        TestCasePassStrFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }

                    if (PassFailTestCaseValue(TestCase, i, "Diff",Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2,variables)) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                        TestCasePassValFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }
                    Exprn = StrexpSimpleExpressMap2;
                    System.out.print(System.lineSeparator());

                    if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true))
                ;//Do Nothing : Test Case Passed
                    else {
                        String Temp = FailedTestCases.get(MaxIndexFail);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "TestCase=" + TestCase + ":Order=" + (i - 1) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i - 1) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        }
                        FailedTestCases.put(MaxIndexFail, HashVal);
                        
                    }
                }
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + i + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
            if (TestCasePassHighLevelFlag==false) MaxIndexFail++;
        }
        System.out.println(ConsoleColors.RED + "mainIntegreDiffTest: TestCase Summary : Total Failed=" + MaxIndexFail + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
}
    public static void mainDiffIntegreTest(String Exprn, int MaxOrder, TestHarness TestData) {

        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
        IntegrExpr expIntegr;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";
        //String DiffWithRespTo = "Xn";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int j = TestCase;
        boolean TestCasePassStrFlag = false;
        boolean TestCasePassValFlag = false;
        boolean TestCasePassHighLevelFlag = true;
        HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
        int MaxIndexFail = 0;
        int MaxOrder1 = MaxOrder;
        int i = 1;
        while (j <= TestCaseLoopTimes) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = FailingTestHarnessDiff("mainDiffIntegreTest",j);
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder > MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }

            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            } else {
                j = RegressionControl(j);
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                continue;
            }
            System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Diff Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
            i = 1;
            TestCasePassHighLevelFlag=true;
            String StageMapping = "";
            variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

            try {
                for (double x = 1, y = 7; i < MaxOrder; y++, x++, i++) {
                    TestCasePassStrFlag = false;
                    TestCasePassValFlag = false;
                    variables.put(DiffWithRespTo, x);
                    variables.put("y", y);
                    variables.put(Constant, 5.0);
                    StageMapping = "Basic Mapping";
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn);
                    expSimple = parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
                    System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);
                    expValue = parse(Exprn, variables, DiffWithRespTo);
                    ExprnexpValue = expValue.eval();
                    StrexpSimpleExpressMap1 = expSimple.SimpleExpr();

                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);
                    expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                    DblexpValueMap1 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                    StageMapping = "Differentiation";
                    exp = parseDiff(StrexpSimpleExpressMap1, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = exp.DiffExpr();
                    System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);

                    StageMapping = "Integration";
                    expIntegr = parseIntegr(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpDiffExpress = expIntegr.IntegrExpr();
                    System.out.println(" main:Result(Integral Equation)(" + i + " th order)=> " + StrexpDiffExpress);
                    expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo);
                    DblexpDiffValue = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);

                    StageMapping = "Integration Mapping";
                    expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, DiffWithRespTo);
                    StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                    System.out.println(" main:Result(Simplified Integral Equation)(" + i + " th order)=> " + StrexpSimpleExpressMap2);
                    expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo);
                    DblexpValueMap2 = expValue.eval();
                    System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                    System.out.print(System.lineSeparator());
                    if (PassFailTestCaseString(TestCase, i, "Integre", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap3[i], EqnSolArrayIntegrAlone[i], EqnSolArrayMap4[i])) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                        TestCasePassStrFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }

                    if (PassFailTestCaseValue(TestCase, i, "Integre", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2,variables)) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                        TestCasePassValFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }
                    Exprn = StrexpSimpleExpressMap2;
                    System.out.print(System.lineSeparator());

                    if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true))
                ;//Do Nothing : Test Case Passed
                    else {
                        String Temp = FailedTestCases.get(MaxIndexFail);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "TestCase=" + TestCase + ":Order=" + (i - 1) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        } else {
                            HashVal = "TestCase=" + TestCase + ":Order=" + (i - 1) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                        }
                        FailedTestCases.put(MaxIndexFail, HashVal);
                        
                    }
                }
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + i + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
            if (TestCasePassHighLevelFlag==false) MaxIndexFail++;
        }
        System.out.println(ConsoleColors.RED + "mainDiffIntegreTest: TestCase Summary : Total Failed=" + MaxIndexFail + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y >= 10) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }
    public static void mainUser(String... args) {
        String TechDiffWithRespTo=DiffWithRespTo;
        String xExprn = "Enter*Your*Equation/Here:x^2+25:";
        Scanner scanner = new Scanner(System.in);
        
        System.out.print(xExprn);
        xExprn=scanner.nextLine();
        xExprn = eatAll(xExprn, ' ');
        System.out.print("You Entered Equation:Example:x^2+25:"+xExprn);
        System.out.print(System.lineSeparator());
        
        
        System.out.print("DiffWithRespTo:[" + TechDiffWithRespTo+"]:");
        TechDiffWithRespTo=scanner.nextLine();
        
        if(TechDiffWithRespTo.equalsIgnoreCase(""))
            TechDiffWithRespTo=DiffWithRespTo;
        //TechDiffWithRespTo = eatAll(TechDiffWithRespTo, ' ');
        
        if (xExprn.contains(TechDiffWithRespTo)) {
                System.out.print("You Entered Primary Variable:"+TechDiffWithRespTo);
                System.out.print(System.lineSeparator());
        }
        else {
             System.out.println("You Entered Equation:"+xExprn);
             System.out.println("You Entered Primary Variable:"+TechDiffWithRespTo + " but Equation entered does not contain " + TechDiffWithRespTo);
             System.out.println("Proceeding with Entered Values");
        }
        System.out.print("Diff And Integration Order:");
        String Temp=scanner.nextLine();
        int TechDiffOrder=2;
        if (!Temp.equalsIgnoreCase(""))
            TechDiffOrder=Integer.parseInt(scanner.nextLine());
        System.out.print("You Entered Diff And Integration Order:"+(TechDiffOrder-1));
        System.out.print(System.lineSeparator());
        
        System.out.println("Differentiation Started:");
        
        UserDiff( xExprn, "mainUser", TechDiffOrder, TechDiffWithRespTo) ;
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
        System.out.println("Integration Started:");
        UserIntegr( xExprn, "mainUser", TechDiffOrder, TechDiffWithRespTo) ;
        
    }
    public static String UserDiff(String Exprn,  String AlgoName,   int TechDiffOrder, String TechDiffWithRespTo) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        SimpleExpression expSimple;
        Expression expValue;
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int TechDiffOrderIndex = 1;
        expValue = parse(Exprn, variables, TechDiffWithRespTo);
        for (; (TechDiffOrderIndex <  TechDiffOrder) ; TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);
            expSimple = parseSimple(Exprn, variables, variablesDiff, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            DiffExpr exp = parseDiff(StrexpSimpleExpress, variables, variablesDiff, TechDiffWithRespTo);
            StrexpDiffExpress = exp.DiffExpr();
            System.out.println("UserDiff:Result(Diff Equation)(" + (TechDiffOrder-1) + " th order)=> " + StrexpDiffExpress);

            expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo);
            System.out.println("UserDiff:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
            
        }
       return StrexpSimpleExpress;
    }
    public static String UserIntegr(String Exprn,  String AlgoName,   int TechDiffOrder, String TechDiffWithRespTo) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        variablesDiff.put(TechDiffWithRespTo, TechDiffWithRespTo);
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr exp;
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int TechDiffOrderIndex = 1;
        expValue = parse(Exprn, variables, TechDiffWithRespTo);
        for (; (TechDiffOrderIndex <  TechDiffOrder) ; TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);
            expSimple = parseSimple(Exprn, variables, variablesDiff, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            exp = parseIntegr(StrexpSimpleExpress, variables, variablesDiff, TechDiffWithRespTo);
            StrexpDiffExpress = exp.IntegrExpr();
            System.out.println("UserIntegr:Result(Integr Equation)(" + (TechDiffOrder-1) + " th order)=> " + StrexpDiffExpress);

            expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            log.info("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo);
            System.out.println("UserIntegr:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
            
        }
       return StrexpSimpleExpress;
    }
    
    public static void mainAlgebraUser(String... args){
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        String StrexpSimpleExpress = "";
        SimpleExpression expSimple;
        String FuncF="x^2+25";
        
        String FuncG="2*x+98";
        SimpleAlgebra F;
        FoG FuncComp;
        
        String TechDiffWithRespTo=DiffWithRespTo;
        System.out.print("Enter*Your* First Equation/Here:x^2+25:");
        
        Scanner scanner = new Scanner(System.in);

        FuncF=scanner.nextLine();
        FuncF = eatAll(FuncF, ' ');
        System.out.println("You Entered First Equation:Example:x^2+25:"+FuncF);
        System.out.print(System.lineSeparator());
        System.out.print("Enter*Your* Second Equation/Here:x^2+25:");
        FuncG=scanner.nextLine();
        FuncG = eatAll(FuncG, ' ');
        
        System.out.println("You Entered Second Equation:Example:x^2+25:"+FuncG);
        System.out.print(System.lineSeparator());
             
        System.out.print("DiffWithRespTo:[" + TechDiffWithRespTo+"]:");
        TechDiffWithRespTo=scanner.nextLine();
        
        if(TechDiffWithRespTo.equalsIgnoreCase(""))
            TechDiffWithRespTo=DiffWithRespTo;
        TechDiffWithRespTo = eatAll(TechDiffWithRespTo, ' ');
        
        if ((FuncF.contains(TechDiffWithRespTo))&&(FuncF.contains(TechDiffWithRespTo))) {
                System.out.print("You Entered Primary Variable:"+TechDiffWithRespTo);
                System.out.print(System.lineSeparator());
        }
        else {
             System.out.println("You Entered First Equation:"+FuncF);
             System.out.println("You Entered Second Equation:"+FuncG);
             System.out.println("You Entered Primary Variable:"+TechDiffWithRespTo + " but Equation entered does not contain " + TechDiffWithRespTo);
             System.out.println("Proceeding with Entered Values");
        }
        
        F=parseAlgebra(FuncF, FuncG, "*", variables, TechDiffWithRespTo);
        expSimple = parseSimple(F.Algebra(), variables, variablesDiff, TechDiffWithRespTo);
        StrexpSimpleExpress = expSimple.SimpleExpr();
        System.out.println("mainAlgebra: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Algebraic Exprn=" + StrexpSimpleExpress);

        FuncComp=parseFoG(FuncF, FuncG, variables, TechDiffWithRespTo);
        expSimple = parseSimple(FuncComp.Substitute(), variables, variablesDiff, TechDiffWithRespTo);
        StrexpSimpleExpress = expSimple.SimpleExpr();
        System.out.println("mainAlgebra: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Function Composition FoG=" + StrexpSimpleExpress);

    }
}
