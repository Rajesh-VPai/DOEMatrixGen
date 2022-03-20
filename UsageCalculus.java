/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.DiffrIntegrSep.MyFuncIntegr;
import static Calculus.ExclusionTestCases.DifferentialGapInfinity;
import static Calculus.ExclusionTestCases.DifferentialGapWrongAnswer;
import static Calculus.ExclusionTestCases.IntegralGapInfinity;
import static Calculus.ExclusionTestCases.IntegralGapWrongAnswer;
import static Calculus.MathsContxtLAv.ChaosPrevention;
import Calculus.MathsContxtLAv.Expression;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.DiffrIntegrSep.MyFuncIntegr;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import Calculus.MathsContxtLAv.OrderVar;
import static Calculus.MathsContxtLAv.ResetVars;
import Calculus.MathsContxtLAv.SimpleExpression;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.MathsContxtLAv.parseOrder;
import static Calculus.MathsContxtLAv.parseSimple;
import static Calculus.TestHarnessClass.ConformalMapTestCaseDiff;
import static Calculus.TestHarnessClass.ConformalMapTestCaseIntegr;
import static Calculus.TestHarnessClass.PassFailTestCaseString;
import static Calculus.TestHarnessClass.PassFailTestCaseStringPalinSame;
import static Calculus.TestHarnessClass.PassFailTestCaseValue;
import static Calculus.TestHarnessClass.PassFailTestCaseValuePalinSame;
import TestHarness.HghLvlExprODE;
import TestHarness.TestHarnessCoefficients;
import TestHarness.TestHarnessIndConstant;
import TestHarness.TestHarnessInfinityNaN;
import TestHarness.TestHarnessNames;
import TestHarness.TestHarnessParenthesis;
import TestHarness.TestHarnessSignedX;
import TestHarness.TestHarnessUnitTest;
import TestHarness.TestHarnessPredicate;
import TestHarness.TestHarnessAlgebra;
import TestHarness.TestHarnessFInverse;
import TestHarness.TestHarnessExpression;
import TestHarness.TestHarnessFunctions;
import TestHarness.TestHarnessInfiniteLoops;
import TestHarness.TestHarnessIsolation;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UsageCalculus {

    public static Logger log = Logger.getLogger(UsageCalculus.class.getName());
    public static String DiffWithRespTo = "x";
    public static int MaxDiffOrder = 6;
    public static int MaxIntegreOrder = 6;
    public static String UserEquation = "";
    public static String UserTestPredicate = "";
    public static String UserTestAnswer = "";

    public static String EqnUnderTest;
    public static String FInverseEqn;
    public static String EqnUnderTest2;

    // Differentiation Mapping
    public static String[] EqnSolArrayMap1;
    public static String[] EqnSolArrayDiffAlone;
    public static String[] EqnSolArrayMap2;

    // Integration Mapping
    public static String[] EqnSolArrayMap3;
    public static String[] EqnSolArrayIntegrAlone;
    public static String[] EqnSolArrayMap4;

    public static String[] EqnSolArrayIntegr_BeforeDiff;
    public static String[] EqnSolArrayDiff_AfterIntegr;

    public static String[] EqnSolArrayDiff_BeforeIntegr;
    public static String[] EqnSolArrayIntegr_AfterDiff;

    public static int[] EqnSolOrder;
    public static String StageMapping = "";
    public static int Order = 1;
    public static int TestCase = 0;
    public static int EndTestCase = 0;
    public static int LastTestCaseHarness = 110023;
    public static int TestCaseLoopTimes = 110023;
    public static String EqnRegressionDiffStatus = "All RED";
    public static String EqnRegressionIntegreStatus = "All RED";
    public static String TestCaseName = "Standard";
    public static String TestCaseMatrixID = "Standard";

    public static boolean TestCasePassStrFlag = false;
    public static boolean TestCasePassValFlag = false;
    public static boolean TestCasePassConformalValFlag = false;
    public static boolean TestCasePassHighLevelFlag = true;
    public static HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
    public static HashMap<Integer, String> ExceptionTestCases = new HashMap<Integer, String>();
    public static HashMap<Integer, String> ExceptionStringDB = new HashMap<Integer, String>();
    public static Throwable cause = new Throwable();
    public static int MaxIndexExcept = 0;
    public static int TotalTestCases = 0;
    public static int ByPassedTestCases = 0;
    public static int MissedNumberTestCases = 0;
    public static int MissedTestCasesInfinity = 0;
    public static int MissedTestCasesException = 0;

    public static int MaxIndexFail = 0;
    public static int SectionIndexFail = 1;
    public static String TestCaseFile = "";
    public static String Section = "";
    public static int FailedTestCount = 0;
    public static int SectionStart = 0;
    public static int SectionTestCaseStart = 0;
    public static int SectionTestCaseEnd = 0;

    public static Map<Integer, String> EqnDB = new HashMap<>();
    public static int EqnDBIndex = 0;
    public static int EqnDBDupCount = 0;

    public static String FailingLowLevelUnitTestCases() {
        //UnStructured Testing Test Cases
        //String Exprn="x^2 - x +2 + y^3 + y + 4";

        //HighLevel Unit Test for Whole Program 
        //14th Order Diff
        /* ************************************************************************ */
        //Failing Test Cases: Low Level Analysed/Debugged Failure
        //Simple Basic Equation
        //String Exprn = ;
        //Simple Basic Equation
        String Exprn = "x^8 + x^5 + x^4 - x - 25.0";
        //Palindromic String-Equation Test
        //String Exprn = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        //Palindromic String-Equation Test-Rearranged
        //String Exprn = " x^8 -x^8 + x^5 -x^5 + x^4 - x^4 + 25.0 + x - x - 25.0";
        //Test for LookAheadFuncMultDiv
        //Straight Numbers Leading + Trailing Co-eff :(LookAheadFuncMultDiv & ConstExprn)
        //(Polynomials)
        //String Exprn = "1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        //Functions 
        //String Exprn = "(2*x+1)*x^9 + x^7 + x^5 + x^2 - sin(x^2)- sin(2*x+5) ";
        //String Exprn = "sin(2.0*7.0*x)+25.0";
        //Parenthesis
        //String Exprn = "(x^8+2*x+1) + x^7  - sin(x^4+ 2*x+ 5) ";
        //Parenthesis with Constants
        //String Exprn="(114.70000000000073*(x-11)+28843.09)*(x-1)*(x-2)*(x-3)*(x-4)*(x-5)*(x-6)*(x-7)*(x-8)*(x-9)*(x-10)*(1.0)*(x-12)*(x-13)*(x-14)*(x-15)*(x-16)*(x-17)*(x-18)*(x-19)*(x-20)*(x-21)";
        //Addition Subtraction
        //String Exprn = "(x^8+2*x+1) + x^7  - (x^4+ 2*x+ 5) ";
        //Multiplication Division
        //String Exprn = "(x^8+2*x+1) + x^7  - (x^4+ 2*x+ 5) ";
        //Parenthesis within Parenthesis
        //Add Operation
        //Subtraction
        //Divide Operation
        //Multiplication
        //Constants
        //String Exprn="11.0*12.0*x*13.0*14.0";
        return Exprn;
    }

    public static String TestHarnessDiff(String AlgoName, int j) {
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
//13)	Parentheses:L=3 : 1st Term, 2nd Term , 3-5 Terms
//14)	Functions:L5: Sin, Cos, Tan, Log, Ln
//15)	Argument of Functions:L=4: Expr Alone(3 types), Expr with Inner Function
        if ((j >= 1000) && (j <= 1024)) {
            Section = "IsolationTestCases";
            SectionTestCaseStart = 1000;
            SectionTestCaseEnd = 1024;
            TestCaseFile = "TestHarnessIsolation";
            TestHarnessIsolation.IsolationTestCases(AlgoName, j);
        } else if ((j >= 2000) && (j <= 2019)) {
            Section = "InfinityNaNTestCases";
            SectionTestCaseStart = 2000;
            SectionTestCaseEnd = 2019;
            TestCaseFile = "TestHarnessInfinityNaN";
            TestHarnessInfinityNaN.InfinityNaNTestCases(AlgoName, j);
        } else if ((j >= 3000) && (j <= 3003)) {
            Section = "InfiniteLoops";
            SectionTestCaseStart = 3000;
            SectionTestCaseEnd = 3003;
            TestCaseFile = "TestHarnessInfiniteLoops";
            TestHarnessInfiniteLoops.InfiniteLoops(AlgoName, j);
        } else if ((j >= 4000) && (j <= 4032)) {
            Section = "SanityTestCases";
            SectionTestCaseStart = 4000;
            SectionTestCaseEnd = 4032;
            TestCaseFile = "TestHarnessClass";
            TestHarnessClass.SanityTestCases(AlgoName, j);
        } else if ((j >= 5019) && (j <= 5048)) {
            Section = "UnitTestCases";
            SectionTestCaseStart = 5019;
            SectionTestCaseEnd = 5048;
            TestCaseFile = "TestHarnessUnitTest";
            TestHarnessUnitTest.UnitTestCases(AlgoName, j);
        } //
        // Variants
        //
        else if ((j >= 10001) && (j <= 10010)) {
            Section = "Names";
            SectionTestCaseStart = 10001;
            SectionTestCaseEnd = 10010;
            TestCaseFile = "TestHarnessNames";
            TestHarnessNames.Names(j);
        } else if ((j >= 20001) && (j <= 20069)) {
            Section = "Coefficients";
            SectionTestCaseStart = 20001;
            SectionTestCaseEnd = 20069;
            TestCaseFile = "TestHarnessCoefficients";
            TestHarnessCoefficients.Coefficients(AlgoName, j);
        } else if ((j >= 30001) && (j <= 30011)) {
            Section = "Exponent";
            SectionTestCaseStart = 30001;
            SectionTestCaseEnd = 30011;
            TestCaseFile = "TestHarnessClass";
            TestHarnessClass.Exponent(AlgoName, j);
        } else if ((j >= 40001) && (j <= 40049)) {
            Section = "SignedX";
            SectionTestCaseStart = 40001;
            SectionTestCaseEnd = 40049;
            TestCaseFile = "TestHarnessSignedX";
            TestHarnessSignedX.SignedX(AlgoName, j);
        } else if ((j >= 50001) && (j <= 50093)) {
            Section = "IndConstant";
            SectionTestCaseStart = 50001;
            SectionTestCaseEnd = 50093;
            TestCaseFile = "TestHarnessIndConstant";
            TestHarnessIndConstant.IndConstant(AlgoName, j);
        } else if ((j >= 80000) && (j <= 80097)) {
            Section = "Parentheses";
            SectionTestCaseStart = 80000;
            SectionTestCaseEnd = 80097;
            TestCaseFile = "TestHarnessParenthesis";
            TestHarnessParenthesis.Parenthesis(AlgoName, j);
        } else if ((j >= 90000) && (j <= 90014)) {
            //Function Test Cases
            Section = "Function Test Cases";
            SectionTestCaseStart = 90000;
            SectionTestCaseEnd = 90014;
            TestCaseFile = "TestHarnessFunctions";
            //TestHarnessFunctions.Functions(AlgoName, j);
        } else if ((j >= 100000) && (j <= 100004)) {
            //Function Argument Test Cases
            Section = "Function Argument Test Cases";
            SectionTestCaseStart = 100000;
            SectionTestCaseEnd = 100004;
            TestCaseFile = "TestHarnessFunctions";
            TestHarnessFunctions.FunctionsArgs(AlgoName, j);
        } else if ((j >= 110001) && (j <= 110023)) {
            Section = "SoftwareEngg";
            SectionTestCaseStart = 110001;
            SectionTestCaseEnd = 110023;
            TestCaseFile = "TestHarnessClass";
            TestHarnessClass.SoftwareEngg(AlgoName, j);
        } else {
            SectionTestCaseStart = -1;
            SectionTestCaseEnd = -1;
            System.out.println(ConsoleColors.RED + "TestHarnessDiff:j= " + j + ": NOT Executed Loop" + ConsoleColors.RESET);
            ResetTestCase();
        }
        return EqnUnderTest;
    }

    public static void mainPredicate(String... args) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = TestPredicate();
        String Exprn = UserTestPredicate;
        String DiffWithRespTo = "x";
        PredicateSep.PredicateBool expValue;

        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');

        int i = 1;
        for (double x = 0, y = 0; i < 10; y++, x++, i++) {
            variables.put("x", x);
            variables.put("y", y);
            System.out.println("main: x=" + x + " Exprn=" + Exprn);
            expValue = PredicateSep.parsePredicate(Exprn, variables, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Logical Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
        }
    }

    public static void mainDiff(String... args) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        String Exprn = UsageCalculus.UserEquation;
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.DiffExpr exp;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String DiffWithRespTo = UsageCalculus.DiffWithRespTo;
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        String StrexpSimpleExpress = "";

        int i = 1;
        for (double x = 1, y = 7; i < UsageCalculus.MaxDiffOrder; y++, x++, i++) {
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
            ChaosPrevention();
            System.out.println("main: x=" + x + " Exprn=" + Exprn);
            expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval() + " DiffWithRespTo=" + DiffWithRespTo);

            StrexpSimpleExpress = expSimple.SimpleExpr();

            System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

            exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpress, variables, DiffWithRespTo);
            StrexpSimpleExpress = exp.DiffExpr();
            System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Diff Value)=" + expValue.eval());

            ChaosPrevention();
            expSimple = parseSimple(StrexpSimpleExpress, variables, DiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
        }
    }

    public static void mainIntegral(String... args) {
        Map<String, Double> variables = new HashMap<>();
        String Exprn = UsageCalculus.UserEquation;
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.IntegrExpr expIntgr;

        MyFuncExpress();
        MyFuncSimple();
        MyFuncIntegr();

        String DiffWithRespTo = UsageCalculus.DiffWithRespTo;
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        String StrexpIntegrExpress = "";
        String StrexpSimpleExpress = "";
        int i = 1;
        for (double x = -20, y = 7; i < UsageCalculus.MaxIntegreOrder; y++, x++, i++) {
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
            System.out.println("main: x=" + x + " Exprn=" + Exprn);
            ChaosPrevention();
            expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval() + " DiffWithRespTo=" + DiffWithRespTo);

            StrexpSimpleExpress = expSimple.SimpleExpr();

            expIntgr = DiffrIntegrSep.parseIntegr(StrexpSimpleExpress, variables, DiffWithRespTo);
            StrexpIntegrExpress = expIntgr.IntegrExpr();
            System.out.println(" main:Result(Integral Equation)(" + i + " th order)=> " + StrexpIntegrExpress);
            expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Integral Value)=" + expValue.eval());

            ChaosPrevention();
            expSimple = parseSimple(StrexpIntegrExpress, variables, DiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
        }
    }

    public static String RandomEquation(String a, String c, String RandVar) {
        String Exprn = a + " * " + RandVar + "^7" + " + " + RandVar + "^5" + " + " + RandVar + "^2" + " - " + RandVar + " + " + c;
        Exprn = eatAll(Exprn, ' ');
        return Exprn;
    }

    public static String FormEquation(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        //if(Xn==0) MaxExponent=5;
        //else MaxExponent=(int)Xn;
        long bCoeffStore = MgtbCoeff;
        //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff );
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        for (int i = MgtMaxExponent - 1; i > 1; i--) {
            if (MgtbCoeff > 2) {
                Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
            } else {
                Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
                MgtbCoeff = bCoeffStore;
            }
            //MgtbCoeff=GetPrimeNumber(MgtbCoeff,i);
            MgtbCoeff++;
            //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff + " i=" + i);
        }
        if (MgtbCoeff > 2) {
            Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo;
        } else {
            Exprn = Exprn + " + " + TechDiffWithRespTo;
        }
        Exprn = Exprn + " + " + Mgtc;
        Exprn = eatAll(Exprn, ' ');
        log.warn("FormEquation:Exprn=" + Exprn);
        return Exprn;
    }

    public static void mainRegresssionDiffTest(int MaxOrder, TestHarnessClass TestData) {
        int MaxOrder1 = MaxOrder;
        String Exprn = "";
        String OverallRegressionStatus = "Diff:Pass All (4000 to 110023) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008"
                + "\n" + "TestCases:Parentheses " + " 80001 to 80024";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if (EndTestCase > LastTestCaseHarness) {
            EndTestCase = LastTestCaseHarness;
        }
        System.out.print(ConsoleColors.BLUE + "Diff EndTestCase=" + EndTestCase + " LastTestCaseHarness=" + LastTestCaseHarness + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if (EndTestCase < TestCase) {
            EndTestCase = TestCase;
        }
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        cause = new Throwable();
        TotalTestCases = 0;
        ByPassedTestCases = 0;
        MissedNumberTestCases = 0;
        MissedTestCasesInfinity = 0;
        MissedTestCasesException = 0;

        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        int OldFailedTestCount = FailedTestCount;
        int SectionDisplayCntrl = 0;
        SectionStart = TestCase;
        EqnDB = new HashMap<>();
        ExceptionStringDB = new HashMap<Integer, String>();
        EqnDBIndex = 0;
        EqnDBDupCount = 0;
        int OldDups = EqnDBDupCount;
        int OldTestCase = j;
        int OldMaxIndexFail = 0;
        int OldTotalTestCases = 0;
        boolean DupFlag = false;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            DupFlag = false;
            String TempExprn = "";

            TempExprn = TestHarnessDiff("mainDiffTest", j);
            Exprn = eatAll(TempExprn, ' ');
            DupFlag = DupChecknInsert(j);

            TestCasePassHighLevelFlag = true;
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder >= MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }
            OldMaxIndexFail = MaxIndexFail;
            if ((!EqnUnderTest.equalsIgnoreCase("")) && ((DupFlag == false))) {
                DiffSingleHighTestCase(MaxOrder1, Exprn);
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                if (DupFlag == false) {
                    TotalTestCases++;
                }
            } else if (DupFlag == true) {
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase + " Dup. Hence NOT Executing.");

            } else if (EqnUnderTest.equalsIgnoreCase("")) {
                ByPassedTestCases++;
            }

            if (TestCasePassHighLevelFlag == false) {
                if (SectionDisplayCntrl == 0) {
                    MaxIndexFail++;
                    if (DupFlag == false) {
                        FailedTestCount++;
                    }
                } else if (DupFlag == false) {
                    FailedTestCount++;
                }
            }
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                OldTestCase = j;
                j = RegressionControl(j);
                if (((j - OldTestCase) > 1)) {
                    SectionDisplayCntrl = 1;
                    GapProcess("mainDiffTest", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, SectionDisplayCntrl, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);
                    OldFailedTestCount = FailedTestCount;
                    OldDups = EqnDBDupCount;
                    SectionStart = j;
                    OldTotalTestCases = TotalTestCases;
                }
                TestCase = j;
            }
            if (SectionDisplayCntrl == 1) {
                SectionDisplayCntrl = 0;
            }
        }
        GapProcess("mainDiffTest", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, 1, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);

        int k = 0;
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                k++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases-Dups=" + (TotalTestCases) + " Passed Count=" + (TotalTestCases - FailedTestCount) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : DupCount=" + k + " Total Exceptions=" + MaxIndexExcept + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : ByPassedTestCases=" + ByPassedTestCases + " MissedNumberTestCases=" + MissedNumberTestCases + " MissedTestCasesInfinity=" + MissedTestCasesInfinity + " MissedTestCasesException=" + MissedTestCasesException + ConsoleColors.RESET);
        for (int y = 0; (MaxIndexFail >= 0) && (FailedTestCases != null) && (y < FailedTestCases.size()); y++) {
            if ((FailedTestCases.get(y).contains("Section")) && (FailedTestCases.get(y).contains("Complete # of Failures="))) {
                System.out.print(ConsoleColors.GREEN + FailedTestCases.get(y) + ConsoleColors.RESET);
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : " + " Failed Test Cases are:" + ConsoleColors.RESET);

        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainDiffTest: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Duplicate Summary : " + " EqnDBIndex=" + EqnDBIndex + ConsoleColors.RESET);
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                System.out.print(ConsoleColors.GREEN + EqnDB.get(y) + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Duplicate Summary : Total Dups=" + k + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.println(ConsoleColors.RED + " " + ExceptionStringDB.get(y) + ", " + ConsoleColors.RESET);
        }
        System.out.print(System.lineSeparator());
    }

    public static int RegressionControl(int j) {
        if ((j >= 1000) && (j <= 1024)) {
            j++;
        } else if ((j >= 2000) && (j <= 2019)) {
            j++;
        } else if ((j >= 3000) && (j <= 3003)) {
            j++;
        } else if ((j >= 4000) && (j <= 4032)) {
            j++;
        } else if ((j >= 5019) && (j <= 5048)) {
            j++;
        } else if ((j >= 10001) && (j <= 10010)) {
            j++;
        } else if ((j >= 20001) && (j <= 20069)) {
            j++;
        } else if ((j >= 30001) && (j < 30011)) {
            j++;
        } else if ((j >= 40001) && (j <= 40049)) {
            j++;
        } else if ((j >= 50001) && (j <= 50093)) {
            j++;
        } else if ((j >= 80000) && (j <= 80097)) {
            j++;
        } else if ((j >= 90000) && (j <= 90014)) {
            j++;
        } else if ((j >= 100000) && (j <= 100004)) {
            j++;
        } else if ((j >= 110001) && (j <= 110023)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 1000)) {
            j = 1000;
        } else if ((j == 1025)) {
            j = 2000;
        } else if ((j == 2020)) {
            j = 3000;
        } else if ((j == 3004)) {
            j = 4000;
        } else if ((j == 4033)) {
            j = 5019;
        } else if (j == 5049) {
            j = 10001;
        } else if (j == 10011) {
            j = 20001;
        } else if (j == 20070) {
            j = 30001;
        } else if (j == 30011) {
            j = 40001;
        } else if (j == 40050) {
            j = 50001;
        } else if (j == 50094) {
            j = 80000;
        } else if (j == 80098) {
            j = 90000;
        } else if (j == 90015) {
            j = 100000;
        } else if (j == 100005) {
            j = 110001;
        } else if (j == 110024) {
            j = 120001;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControl:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void mainRegressionIntegrTest(int MaxOrder, TestHarnessClass TestData) {
        int MaxOrder1 = MaxOrder;
        String Exprn = "";
        String OverallRegressionStatus = "Integral:Pass All (100 to 110023) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008"
                + "\n" + "TestCases:Parentheses " + " 80001 to 80024";
        System.out.print(ConsoleColors.BLUE + "Integral Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if (EndTestCase > LastTestCaseHarness) {
            EndTestCase = LastTestCaseHarness;
        }
        System.out.println(ConsoleColors.BLUE + "Integral EndTestCase=" + EndTestCase + " LastTestCaseHarness=" + LastTestCaseHarness + ConsoleColors.RESET);
        if (EndTestCase < TestCase) {
            EndTestCase = TestCase;
        }
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        ByPassedTestCases = 0;
        MissedNumberTestCases = 0;
        MissedTestCasesInfinity = 0;
        MissedTestCasesException = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        int SectionDisplayCntrl = 0;
        int OldFailedTestCount = FailedTestCount;
        SectionStart = TestCase;
        EqnDB = new HashMap<>();
        ExceptionStringDB = new HashMap<Integer, String>();
        cause = new Throwable();
        EqnDB.clear();
        EqnDBIndex = 0;
        EqnDBDupCount = 0;
        int OldDups = EqnDBDupCount;
        int OldTestCase = j;
        int OldMaxIndexFail = 0;
        int OldTotalTestCases = 0;
        boolean DupFlag = false;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            DupFlag = false;
            String TempExprn = "";

            TempExprn = TestHarnessDiff("mainIntegrTest", j);
            Exprn = eatAll(TempExprn, ' ');
            DupFlag = DupChecknInsert(j);
            TestCasePassHighLevelFlag = true;
            if (MaxOrder == -1) {
                MaxOrder1 = MaxIntegreOrder; //From Test Case
            } else if (MaxOrder >= MaxIntegreOrder) {
                MaxOrder1 = MaxIntegreOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }
            OldMaxIndexFail = MaxIndexFail;
            if ((!EqnUnderTest.equalsIgnoreCase("")) && ((DupFlag == false))) {
                IntegralHighSingleTest(MaxOrder1, Exprn);
                System.out.println(ConsoleColors.RED + "mainIntegrTest: j=" + j + " TestCase=" + TestCase);
                if (DupFlag == false) {
                    TotalTestCases++;
                }
            } else if (DupFlag == true) {
                System.out.println(ConsoleColors.RED + "mainIntegrTest: j=" + j + " TestCase=" + TestCase + " Dup. Hence NOT Executing.");

            } else if (EqnUnderTest.equalsIgnoreCase("")) {
                ByPassedTestCases++;
            }
            if (TestCasePassHighLevelFlag == false) {
                if (SectionDisplayCntrl == 0) {
                    MaxIndexFail++;
                    if (DupFlag == false) {
                        FailedTestCount++;
                    }
                } else if (DupFlag == false) {
                    FailedTestCount++;
                }
            }

            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                OldTestCase = j;
                j = RegressionControl(j);
                if (((j - OldTestCase) > 1)) {
                    SectionDisplayCntrl = 1;
                    GapProcess("mainIntegrTest", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, SectionDisplayCntrl, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);
                    OldFailedTestCount = FailedTestCount;
                    OldDups = EqnDBDupCount;
                    SectionStart = j;
                    OldTotalTestCases = TotalTestCases;
                }
                TestCase = j;
                //System.out.println(ConsoleColors.RED +"mainIntegrTest: j="+ j + " TestCase=" + TestCase);
            }
            if (SectionDisplayCntrl == 1) {
                SectionDisplayCntrl = 0;
            }
        }
        GapProcess("mainIntegrTest", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, 1, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);
        int k = 0;
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                k++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases-Dups=" + (TotalTestCases) + " Passed Count=" + (TotalTestCases - FailedTestCount) + " DupCount=" + k + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : DupCount=" + k + " Total Exceptions=" + MaxIndexExcept + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : ByPassedTestCases=" + ByPassedTestCases + " MissedNumberTestCases=" + MissedNumberTestCases + " MissedTestCasesInfinity=" + MissedTestCasesInfinity + " MissedTestCasesException=" + MissedTestCasesException + ConsoleColors.RESET);
        for (int y = 0; (y < FailedTestCases.size()); y++) {
            if ((FailedTestCases.get(y).contains("Section")) && (FailedTestCases.get(y).contains("Complete # of Failures="))) {
                System.out.print(ConsoleColors.GREEN + FailedTestCases.get(y) + ConsoleColors.RESET);
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : " + " Failed Test Cases are:" + ConsoleColors.RESET);

        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainIntegrTest: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Duplicate Summary : " + " EqnDBIndex=" + EqnDBIndex + ConsoleColors.RESET);
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                System.out.print(ConsoleColors.GREEN + EqnDB.get(y) + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Duplicate Summary : Total Dups=" + k + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.println(ConsoleColors.RED + " " + ExceptionStringDB.get(y) + ", " + ConsoleColors.RESET);
        }
        System.out.print(System.lineSeparator());

    }

    public static void mainUser(String... args) {
        System.out.println(ConsoleColors.BLUE + "Differentiation / Integration:" + ConsoleColors.RESET);
        String TechDiffWithRespTo = DiffWithRespTo;
        String xExprn = "Enter*Your*Equation/Here:x^8 + x^5 + x^4 - x - 25:";
        Scanner scanner = new Scanner(System.in);
        xExprn = xExprn.trim();
        System.out.print(xExprn);
        xExprn = scanner.nextLine();
        xExprn = xExprn.trim();
        xExprn = eatAll(xExprn, ' ');

        System.out.print("You Entered Equation:Example:x^8 + x^5 + x^4 - x - 25:" + xExprn);
        System.out.print(System.lineSeparator());

        System.out.print("DiffWithRespTo:[" + TechDiffWithRespTo + "]:");
        TechDiffWithRespTo = scanner.nextLine();

        if (TechDiffWithRespTo.equalsIgnoreCase("")) {
            TechDiffWithRespTo = DiffWithRespTo;
        }
        TechDiffWithRespTo = TechDiffWithRespTo.trim();
        TechDiffWithRespTo = eatAll(TechDiffWithRespTo, ' ');

        if (xExprn.contains(TechDiffWithRespTo)) {
            System.out.print("You Entered Primary Variable:" + TechDiffWithRespTo);
            System.out.print(System.lineSeparator());
        } else {
            System.out.println("You Entered Equation:" + xExprn);
            System.out.println("You Entered Primary Variable:" + TechDiffWithRespTo + " but Equation entered does not contain " + TechDiffWithRespTo);
            System.out.println("Proceeding with Entered Values");
        }
        System.out.print("Diff And Integration Order:");
        String Temp = scanner.nextLine();
        int TechDiffOrder = 2;
        if (!Temp.equalsIgnoreCase("")) {
            TechDiffOrder = Integer.parseInt(Temp);
        }
        System.out.print("You Entered Diff And Integration Order:" + (TechDiffOrder));
        System.out.print(System.lineSeparator());

        System.out.println(ConsoleColors.BLUE + "Differentiation Started:" + ConsoleColors.RESET);

        UserDiff(xExprn, "mainUser", TechDiffOrder, TechDiffWithRespTo);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());

        System.out.println(ConsoleColors.BLUE + "Integration Started:" + ConsoleColors.RESET);
        UserIntegr(xExprn, "mainUser", TechDiffOrder, TechDiffWithRespTo);

    }

    public static String UserDiff(String Exprn, String AlgoName, int TechDiffOrder, String TechDiffWithRespTo) {
        Map<String, Double> variables = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        SimpleExpression expSimple;
        Expression expValue;
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int TechDiffOrderIndex = 1;
        System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + Exprn);

        expValue = parse(Exprn, variables, TechDiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
        for (; (TechDiffOrderIndex <= TechDiffOrder); TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);

            ChaosPrevention();
            expSimple = parseSimple(Exprn, variables, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            DiffrIntegrSep.DiffExpr exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpress, variables, TechDiffWithRespTo);
            StrexpDiffExpress = exp.DiffExpr();
            System.out.println("UserDiff:Result(Diff Equation)(" + TechDiffOrderIndex + " th order)=> " + StrexpDiffExpress);

            ChaosPrevention();
            expSimple = parseSimple(StrexpDiffExpress, variables, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println("UserDiff:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;

        }
        return StrexpSimpleExpress;
    }

    public static String UserIntegr(String Exprn, String AlgoName, int TechDiffOrder, String TechDiffWithRespTo) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        variablesDiff.put(TechDiffWithRespTo, TechDiffWithRespTo);
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.IntegrExpr exp;
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int TechDiffOrderIndex = 1;
        System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + Exprn);

        expValue = parse(Exprn, variables, TechDiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
        for (; (TechDiffOrderIndex <= TechDiffOrder); TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);

            ChaosPrevention();
            expSimple = parseSimple(Exprn, variables, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            exp = DiffrIntegrSep.parseIntegr(StrexpSimpleExpress, variables, TechDiffWithRespTo);
            StrexpDiffExpress = exp.IntegrExpr();
            System.out.println("UserIntegr:Result(Integr Equation)(" + TechDiffOrderIndex + " th order)=> " + StrexpDiffExpress);

            ChaosPrevention();
            expSimple = parseSimple(StrexpDiffExpress, variables, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println("UserIntegr:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;

        }
        return StrexpSimpleExpress;
    }

    public static void mainAlgebraUser(String... args) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        String StrexpSimpleExpress = "";
        SimpleExpression expSimple;
        String FuncF = "x^2+25";

        String FuncG = "2*x+98";
        SimpleAlgebraSep.SimpleAlgebra F;
        //SimpleAlgebraHghLvl F;
        SimpleAlgebraSep.FoG FuncComp;
        //FoGLowLvL FuncComp;

        String TechDiffWithRespTo = DiffWithRespTo;
        System.out.println(ConsoleColors.BLUE + "Algebra / Function Composition:" + ConsoleColors.RESET);
        System.out.print("Enter*Your* First Equation/Here:x^2+25:");

        Scanner scanner = new Scanner(System.in);

        FuncF = scanner.nextLine();
        FuncF = eatAll(FuncF, ' ');
        System.out.println("You Entered First Equation:Example:x^2+25:" + FuncF);
        System.out.print(System.lineSeparator());
        System.out.print("Enter*Your* Second Equation/Here:x^2+25:");
        FuncG = scanner.nextLine();
        FuncG = eatAll(FuncG, ' ');

        System.out.println("You Entered Second Equation:Example:x^2+25:" + FuncG);
        System.out.print(System.lineSeparator());

        System.out.print("DiffWithRespTo:[" + TechDiffWithRespTo + "]:");
        TechDiffWithRespTo = scanner.nextLine();

        if (TechDiffWithRespTo.equalsIgnoreCase("")) {
            TechDiffWithRespTo = DiffWithRespTo;
        }
        TechDiffWithRespTo = eatAll(TechDiffWithRespTo, ' ');

        if ((FuncF.contains(TechDiffWithRespTo)) && (FuncF.contains(TechDiffWithRespTo))) {
            System.out.print("You Entered Primary Variable:" + TechDiffWithRespTo);
            System.out.print(System.lineSeparator());
        } else {
            System.out.println("You Entered First Equation:" + FuncF);
            System.out.println("You Entered Second Equation:" + FuncG);
            System.out.println("You Entered Primary Variable:" + TechDiffWithRespTo + " but Equation entered does not contain " + TechDiffWithRespTo);
            System.out.println("Proceeding with Entered Values");
        }
        variables.put(TechDiffWithRespTo, 1.0);
        F = SimpleAlgebraSep.parseAlgebra(FuncF, FuncG, "*", variables, TechDiffWithRespTo);
        //F = parseAlgebraHghLvl(FuncF, FuncG, "*", variables, TechDiffWithRespTo);

        expSimple = parseSimple(F.Algebra(), variables, TechDiffWithRespTo);
        StrexpSimpleExpress = expSimple.SimpleExpr();
        System.out.println("mainAlgebra: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Algebraic Exprn=" + StrexpSimpleExpress);

        FuncComp = SimpleAlgebraSep.parseFoG(FuncF, FuncG, variables, TechDiffWithRespTo);

        //FuncComp=parseFoGLowLvL(FuncF, FuncG, variables, TechDiffWithRespTo);
        if (!FuncComp.Substitute().contains("(")) {
            expSimple = parseSimple(FuncComp.Substitute(), variables, TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
        } else {
            StrexpSimpleExpress = FuncComp.Substitute();
        }
        System.out.println("mainAlgebra: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Function Composition FoG=" + StrexpSimpleExpress);

    }

    public static boolean ValidateGivenAnswer(int TestCase, int Order, String OrigExprn, double x, double y, String OrigOperation) {
        String Exprn = "";
        String Exprn1 = "";
        SimpleExpression expSimple;
        Map<String, Double> variables = new HashMap<>();
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
        if (OrigOperation.equalsIgnoreCase("Diff")) {
            DiffrIntegrSep.IntegrExpr exp;
            exp = DiffrIntegrSep.parseIntegr(UsageCalculus.EqnSolArrayMap2[Order], variables, DiffWithRespTo);
            Exprn = exp.IntegrExpr();
            expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
            Exprn = expSimple.SimpleExpr();
            expSimple = parseSimple(EqnSolArrayMap1[Order], variables, DiffWithRespTo);
            Exprn1 = expSimple.SimpleExpr();
            if ((Exprn.equalsIgnoreCase(Exprn1) || (Exprn1.contains(Exprn)))) {
                return true;
            }
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Integral)=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Answer)  =" + Exprn1 + ConsoleColors.RESET);
        } else if (OrigOperation.equalsIgnoreCase("Integre")) {
            DiffrIntegrSep.DiffExpr exp;
            exp = DiffrIntegrSep.parseDiff(UsageCalculus.EqnSolArrayMap4[Order], variables, DiffWithRespTo);
            Exprn = exp.DiffExpr();
            expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
            Exprn = expSimple.SimpleExpr();
            expSimple = parseSimple(EqnSolArrayMap3[Order], variables, DiffWithRespTo);
            Exprn1 = expSimple.SimpleExpr();
            if ((Exprn.equalsIgnoreCase(Exprn1))) {
                return true;
            }
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Diff)  =" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Answer)=" + Exprn1 + ConsoleColors.RESET);
        }
        return false;
    }

    public static void DiffSingleHighTestCase(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        MathsContxtLAv.SimpleExpression expSimpleMap1 = (() -> "");
        MathsContxtLAv.Expression expValue = (() -> Double.NaN);
        MathsContxtLAv.SimpleExpression expSimpleMap2 = (() -> "");
        DiffrIntegrSep.DiffExpr exp = (() -> "");
        MathsContxtLAv.OrderVar expOrder = (() -> "");

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;

        StageMapping = "";
        Order = 1;

        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " TestCaseFile=" + TestCaseFile + " Section=" + Section + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);

        for (double x = 1.0, y = 7.0; Order < MaxOrder1; y++, /*x++,*/ Order++) {
            try {
                TestCasePassStrFlag = false;
                TestCasePassValFlag = false;
                TestCasePassConformalValFlag = false;
                variables.put(DiffWithRespTo, x);
                variables.put("y", y);
                variables.put("a", 1.0);
                variables.put("b", 2.0);//variables.put("b", 0.0);
                variables.put("c", -1.0);
                variables.put("d", 2.0);
                variables.put("m", -1.0);
                variables.put("p", 3.0);
                variables.put("q", 0.0);
                variables.put("r", -1.0);
                variables.put("s", 2.0);
                variables.put(Constant, 5.0);
                StageMapping = "Basic Mapping";
                expOrder = parseOrder(Exprn, DiffWithRespTo);
                myOrder = expOrder.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn + " Order=" + myOrder);
                ChaosPrevention();
                expSimpleMap1 = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimpleMap1.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue + "  x=" + x + DiffWithRespTo + "=" + x);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                ChaosPrevention();
                expSimpleMap2 = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimpleMap2.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);
                System.out.print(System.lineSeparator());
                if (PassFailTestCaseString(TestCase, Order, "Diff", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValue(TestCase, Order, "Diff", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress, variables, x)) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                    TestCasePassValFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }
                if ((TestCasePassStrFlag == true) || (TestCasePassValFlag == true)) {
                    TestCasePassConformalValFlag = ConformalMapTestCaseDiff(TestCase, Order, Exprn, expSimpleMap1, exp, expSimpleMap2, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order], variables, DiffWithRespTo);
                }
                if (TestCasePassConformalValFlag == true) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass Conformal Map TEST" + ConsoleColors.RESET);
                } else if ((TestCasePassStrFlag != true) || (TestCasePassValFlag != true)) {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "Conformal Map TEST Not Executed" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL Conformal Map TEST " + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    System.out.println(ConsoleColors.BLUE + "Super Test Answer Checks" + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) /* && (TestCasePassValFlag == true) && (TestCasePassConformalValFlag == true)*/) {
                    TestCasePassHighLevelFlag = true;//DO Nothing as Test Cases Passes
                } else {
                    String Temp = FailedTestCases.get(MaxIndexFail);
                    String HashVal = "";
                    if ((Temp != null) && (Order >= 2) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                        HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    } else {
                        HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    }
                    FailedTestCases.put(MaxIndexFail, HashVal);
                    //i=MaxOrder1;
                    TestCasePassHighLevelFlag = false;
                }
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.initCause(cause);
                if (MyException.getCause() != null) {
                    ExceptionStringDB.put(MaxIndexExcept, TestCase + ":" + MyException.getMessage());
                }
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());

    }

    public static void IntegralHighSingleTest(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        SimpleExpression expSimpleMap1 = (() -> "");
        Expression expValue = (() -> Double.NaN);;
        SimpleExpression expSimpleMap2 = (() -> "");
        DiffrIntegrSep.IntegrExpr exp = (() -> "");
        OrderVar expOrder = (() -> "");

        MyFuncExpress();
        MyFuncIntegr();
        MyFuncSimple();

        String Constant = "C";
        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";
        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        Order = 1;
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " TestCaseFile=" + TestCaseFile + " Section=" + Section + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
        Order = 1;
        StageMapping = "";
        for (double x = 1.0, y = 7; Order < MaxOrder1; y++, /*x++,*/ Order++) {
            try {
                TestCasePassStrFlag = false;
                TestCasePassValFlag = false;
                TestCasePassConformalValFlag = false;
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
                ChaosPrevention();
                expSimpleMap1 = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimpleMap1.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                StageMapping = "Integration";
                exp = DiffrIntegrSep.parseIntegr(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + Order + " th order)=> " + StrexpDiffExpress);

                StageMapping = "Integration Mapping";
                ChaosPrevention();
                expSimpleMap2 = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimpleMap2.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Info);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Info);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);
                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, (x +(10 - x) / 10), "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);

                System.out.print(System.lineSeparator());

                if (PassFailTestCaseString(TestCase, Order, "Integre", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap3[Order], EqnSolArrayIntegrAlone[Order], EqnSolArrayMap4[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValue(TestCase, Order, "Integre", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress, variables, x)) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                    TestCasePassValFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }
                if ((TestCasePassStrFlag == true) || (TestCasePassValFlag == true)) {
                    TestCasePassConformalValFlag = ConformalMapTestCaseIntegr(TestCase, Order, Exprn, expSimpleMap1, exp, expSimpleMap2, EqnSolArrayMap3[Order], EqnSolArrayIntegrAlone[Order], EqnSolArrayMap4[Order], variables, DiffWithRespTo);
                }
                if (TestCasePassConformalValFlag == true) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass Conformal Map TEST" + ConsoleColors.RESET);
                } else if ((TestCasePassStrFlag != true) || (TestCasePassValFlag != true)) {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "Conformal Map TEST Not Executed" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL Conformal Map TEST " + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    System.out.println(ConsoleColors.BLUE + "Super Test Answer Checks" + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) /* && (TestCasePassValFlag == true) && (TestCasePassConformalValFlag == true)*/) {
                    TestCasePassHighLevelFlag = true;//DO Nothing as Test Cases Passes
                } else {
                    String Temp = FailedTestCases.get(MaxIndexFail);
                    String HashVal = "";
                    if ((Temp != null) && (Order >= 2) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                        HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    } else {
                        HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    }
                    FailedTestCases.put(MaxIndexFail, HashVal);
                    //i=MaxOrder1;
                    TestCasePassHighLevelFlag = false;
                }
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.initCause(cause);
                if (MyException.getCause() != null) {
                    ExceptionStringDB.put(MaxIndexExcept, TestCase + ":" + MyException.getMessage().toString());
                }
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void mainRegresssionPredicate(TestHarnessClass TestData) {
        String Exprn = "";
        String OverallRegressionStatus = "Diff:Pass All (100 to 110007) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008"
                + "\n" + "TestCases:Parentheses " + " 80001 to 80024";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";
            TempExprn = TestHarnessPredicate("Predicate", j);
            if ((!UserTestPredicate.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            }
            TotalTestCases++;
            Exprn = eatAll(Exprn, ' ');
            TestCasePassHighLevelFlag = true;
            PredicateSingleHighTestCase(Exprn);
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControlPredicate(j);
                TestCase = j;
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag == false) {
                MaxIndexFail++;
                FailedTestCount++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainRegresssionPredicate: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases=" + TotalTestCases + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainRegresssionPredicate: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void PredicateSingleHighTestCase(String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = TestPredicate();
        String DiffWithRespTo = "x";
        PredicateSep.PredicateBool expValue;

        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');

        String StageMapping = "";
        int Order = 1;
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);

        for (double x = 0, y = 0; Order < 10; y++, x++, Order++) {
            try {
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
                StageMapping = "Basic Mapping";
                System.out.println("mainMathPredicate: x=" + x + " y=" + y + " Exprn=" + Exprn);
                expValue = PredicateSep.parsePredicate(Exprn, variables, "Silent", MathsContxtLAv.LogLevel.Off);
                System.out.println(" mainMathPredicate:Result(Logical Value)=" + expValue.eval());
                System.out.print(System.lineSeparator());

            } catch (Exception MyException) {

            }
        }
        System.out.print(System.lineSeparator());

    }

    public static String TestHarnessPredicate(String AlgoName, int j) {
        if ((j >= 100) && (j <= 107)) {
            TestHarnessPredicate.SanityTestCases(AlgoName, j);
        } else if ((j >= 200) && (j <= 227)) {
            TestHarnessPredicate.UnitTestCases(AlgoName, j);
        }

        return UserTestPredicate;
    }

    public static int RegressionControlPredicate(int j) {
        if ((j >= 100) && (j < 107)) {
            j++;
        } else if ((j >= 200) && (j < 228)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 107)) {
            j = 200;
        } else if ((j == 228)) {
            j = 5019;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControlPredicate:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void GapProcess(String AlgoName, int CurrentTestCase, int OldTotalTestCases, int OldTestCase, int OldMaxIndexFail, int SectionDisplayCntrl, int OldFailedTestCount, int FailedTestCount, int OldDups, int DupsCur) {
        String SpacePad = "";
        int i = 0;
        for (; (i < Section.length()); i++) {
            if ((Section != null) && (Section.charAt(i) != ' ')) {
                SpacePad = SpacePad + (char) Section.charAt(i);
            } else {
                SpacePad = SpacePad + (char) ' ';
            }
        }
        for (; i < 28; i++) {
            SpacePad += (char) ' ';
        }
        if ((SectionDisplayCntrl == 1)) {
            String Temp = FailedTestCases.get(MaxIndexFail);
            FailedTestCases.put(MaxIndexFail, Temp + "\n");
            FailedTestCases.put(MaxIndexFail + 1, "Section " + SpacePad + " Complete" + " # of Failures=" + (FailedTestCount - OldFailedTestCount) + " Out of=" + (TotalTestCases - OldTotalTestCases) + " Test Cases. Dups=" + (DupsCur - OldDups) + " in Section \n");
            MaxIndexFail++;
            MaxIndexFail++;
        }
    }

    public static void mainRegresssionAlgebra(TestHarnessClass TestData) {
        String Exprn = "";
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";
            Exprn = "";
            TempExprn = TestHarnessAlgebra("Algebra", j);
            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            }
            TotalTestCases++;
            Exprn = eatAll(Exprn, ' ');
            TestCasePassHighLevelFlag = true;
            AlgebraSingleHighTestCase(Exprn);
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControlAlgebra(j);
                TestCase = j;
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag == false) {
                MaxIndexFail++;
                FailedTestCount++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainRegresssionAlgebra: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases=" + TotalTestCases + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainRegresssionAlgebra: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void AlgebraSingleHighTestCase(String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        String DiffWithRespTo = "x";
        SimpleAlgebraSep.SimpleAlgebra expValue;
        SimpleExpression expSimple;
        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');
        EqnUnderTest2 = eatAll(EqnUnderTest2, ' ');
        String StageMapping = "";
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + " Exprn2=" + EqnUnderTest2 + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
        Double x = 0.0;
        Double y = 0.0;
        try {
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
            StageMapping = "Basic Mapping";
            System.out.println("mainMathAlgebra: Exprn=" + Exprn + " EqnUnderTest2=" + UsageCalculus.EqnUnderTest2);
            expValue = SimpleAlgebraSep.parseAlgebra(Exprn, UsageCalculus.EqnUnderTest2, "*", variables, DiffWithRespTo);
            expSimple = parseSimple(expValue.Algebra(), variables, DiffWithRespTo);
            if (expSimple.SimpleExpr().equalsIgnoreCase(UsageCalculus.UserTestAnswer)) {
                TestCasePassStrFlag = true;
                TestCasePassHighLevelFlag = true;
                System.out.println(ConsoleColors.GREEN + " mainMathAlgebra:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " mainMathAlgebra:Answer=" + UsageCalculus.UserTestAnswer + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + " mainMathAlgebra:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " mainMathAlgebra:Answer=" + UsageCalculus.UserTestAnswer + ConsoleColors.RESET);
                TestCasePassStrFlag = false;
                TestCasePassHighLevelFlag = false;
            }
            System.out.print(System.lineSeparator());

        } catch (Exception MyException) {
        }
        System.out.print(System.lineSeparator());

    }

    public static String TestHarnessAlgebra(String AlgoName, int j) {
        if ((j >= 100) && (j <= 107)) {
            TestHarnessAlgebra.SanityTestCases(AlgoName, j);
        } else if ((j >= 200) && (j <= 225)) {
            TestHarnessAlgebra.UnitTestCases(AlgoName, j);
        }

        return EqnUnderTest;
    }

    public static int RegressionControlAlgebra(int j) {
        if ((j >= 100) && (j < 107)) {
            j++;
        } else if ((j >= 200) && (j < 225)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 107)) {
            j = 200;
        } else if ((j == 225)) {
            j = 5019;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControlPredicate:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void ResetTestCase() {

        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "";
        UsageCalculus.TestCaseMatrixID = "";

        UsageCalculus.DiffWithRespTo = "";
        UsageCalculus.MaxDiffOrder = 0;
        UsageCalculus.MaxIntegreOrder = 0;

        UsageCalculus.EqnSolArrayMap1[1] = "";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "";
        UsageCalculus.EqnSolArrayMap4[1] = "";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "";
        UsageCalculus.EqnSolArrayMap4[4] = "";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[5] = "";
        UsageCalculus.EqnSolArrayMap4[5] = "";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[6] = "";
        UsageCalculus.EqnSolArrayMap4[6] = "";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[7] = "";
        UsageCalculus.EqnSolArrayMap4[7] = "";
        UsageCalculus.EqnSolOrder[7] = 7;

        UsageCalculus.EqnSolArrayMap1[8] = "";
        UsageCalculus.EqnSolArrayDiffAlone[8] = "";
        UsageCalculus.EqnSolArrayMap2[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void PalinDiffSingleHighTestCase(int MaxOrder1, String Exprn, int TestCase1, int TestCase2, int Operator) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.DiffExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;

        String StageMapping = "";
        int Order = 1;
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase1 + " TestCase2=" + TestCase2 + " Operator=" + (char) Operator + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + " Exprn=" + Exprn + ConsoleColors.RESET);

        for (double x = 0, y = 7; Order < MaxOrder1; y++, x++, Order++) {
            try {
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
                expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);
                System.out.print(System.lineSeparator());
                if (PassFailTestCaseStringPalinSame(TestCase1, Order, "Diff", Exprn, Operator, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValuePalinSame(TestCase1, Order, "Diff", Exprn, Operator, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, variables)) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                    TestCasePassValFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    System.out.println(ConsoleColors.BLUE + "Super Test Answer Checks" + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    ;//DO Nothing as Test Cases Passes
                } else {
                    String Temp = FailedTestCases.get(MaxIndexFail);
                    String HashVal = "";
                    if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                        HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                    } else {
                        HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                    }
                    FailedTestCases.put(MaxIndexFail, HashVal);
                    //i=MaxOrder1;
                    TestCasePassHighLevelFlag = false;
                }
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());

    }

    public static void PalinIntegralSingleHighTestCase(int MaxOrder1, String Exprn, int TestCase1, int TestCase2, int Operator) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.IntegrExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";
        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";
        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int Order = 1;
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
        Order = 1;
        String StageMapping = "";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        for (double x = 0, y = 7; Order < MaxOrder1; y++, /*x++,*/ Order++) {
            try {
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
                expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                StageMapping = "Integration";
                exp = DiffrIntegrSep.parseIntegr(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + Order + " th order)=> " + StrexpDiffExpress);

                StageMapping = "Integration Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);
                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, (x +(10 - x) / 10), "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);

                System.out.print(System.lineSeparator());

                if (PassFailTestCaseString(TestCase, Order, "Integre", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap3[Order], EqnSolArrayIntegrAlone[Order], EqnSolArrayMap4[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValue(TestCase, Order, "Integre", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress, variables, x)) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                    TestCasePassValFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    System.out.println(ConsoleColors.BLUE + "Super Test Answer Checks" + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    ;//DO Nothing as Test Cases Passes
                } else {
                    String Temp = FailedTestCases.get(MaxIndexFail);
                    String HashVal = "";
                    if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                        HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                    } else {
                        HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                    }
                    FailedTestCases.put(MaxIndexFail, HashVal);
                    //i=MaxOrder1;
                    TestCasePassHighLevelFlag = false;
                }
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void PalinController(int MaxOrder, String StartMyTestCase, String EndMyTestCase, String IntegreOrDiff) {
        System.out.println("PalinController:Start:" + IntegreOrDiff);
        double NumTestExecu = Math.round(Math.sqrt(287));
        double Start = Double.parseDouble(StartMyTestCase);
        double End = Double.parseDouble(EndMyTestCase);
        String Exprn1 = "";
        String Exprn2 = "";
        String DiffWithRespToExprn1 = "";
        String DiffWithRespToExprn2 = "";
        String Exprn = "";
        for (int i = 0; i <= 1; i++) {
            int TestCase1 = (int) (Math.random() * (End));
            int TestCase2 = (int) (Math.random() * (End));
            int NoOfAttemps = 0;
            boolean NOTinGAPTestCase1 = false;
            boolean NOTinGAPTestCase2 = false;
            while (NoOfAttemps < 2) {
                if ((TestCaseInGap(TestCase1))) {
                    TestCase1 = (int) (Math.random() * (SectionTestCaseEnd - SectionTestCaseStart)) + SectionTestCaseStart;
                    NOTinGAPTestCase1 = !TestCaseInGap(TestCase1);
                } else {
                    NOTinGAPTestCase1 = true;
                }
                SectionTestCaseStart = 0;
                SectionTestCaseEnd = 0;
                if (TestCaseInGap(TestCase2)) {
                    TestCase2 = (int) (Math.random() * (SectionTestCaseEnd - SectionTestCaseStart)) + SectionTestCaseStart;
                    NOTinGAPTestCase2 = !TestCaseInGap(TestCase2);
                } else {
                    NOTinGAPTestCase2 = true;
                }
                if ((NOTinGAPTestCase1) && (NOTinGAPTestCase2)) {
                    break;
                }
                NoOfAttemps++;
            }
            if ((NOTinGAPTestCase1) && (NOTinGAPTestCase2)) {
                double SignRand = Math.random();
                int Operator = -1;
                if (SignRand >= 0.5) {
                    Operator = '+';
                } else {
                    Operator = '-';
                }
                System.out.print(System.lineSeparator());
                System.out.println("PalinController:" + IntegreOrDiff + ":TestCase1=" + TestCase1);
                System.out.println("PalinController:" + IntegreOrDiff + ":TestCase2=" + TestCase2);
                if (IntegreOrDiff.equalsIgnoreCase("Diff")) {
                    Exprn1 = TestHarnessDiff("mainDiffTest", TestCase1);
                    DiffWithRespToExprn1 = DiffWithRespTo;
                    Exprn2 = TestHarnessDiff("mainDiffTest", TestCase2);
                    DiffWithRespToExprn2 = DiffWithRespTo;
                    if (DiffWithRespToExprn1.equalsIgnoreCase(DiffWithRespToExprn2)) {
                        Exprn = Exprn1 + (char) Operator + Exprn2;
                        PalinDiffSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase2, Operator);
                    }
                } else if (IntegreOrDiff.equalsIgnoreCase("Integral")) {
                    Exprn1 = TestHarnessDiff("mainIntegrTest", TestCase1);
                    DiffWithRespToExprn1 = DiffWithRespTo;
                    Exprn2 = TestHarnessDiff("mainIntegrTest", TestCase2);
                    DiffWithRespToExprn2 = DiffWithRespTo;
                    if (DiffWithRespToExprn1.equalsIgnoreCase(DiffWithRespToExprn2)) {
                        Exprn = Exprn1 + (char) Operator + Exprn2;
                        PalinIntegralSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase2, Operator);
                    }
                }
            }
        }
        System.out.println("PalinController:End:" + IntegreOrDiff);
    }

    public static boolean TestCaseInGap(int TestCaseCur) {
        if ((SectionTestCaseStart <= TestCaseCur) && (TestCaseCur <= SectionTestCaseEnd)) {
            return false;
        } else if ((SectionTestCaseStart == 0) && (SectionTestCaseEnd == 0)) {
            if (((TestCaseCur >= 100) && (TestCaseCur <= 125)) || (TestCaseCur < 1000)) {
                SectionTestCaseStart = 100;
                SectionTestCaseEnd = 125;
            } else if ((TestCaseCur >= 5019) && (TestCaseCur <= 5042) || (TestCaseCur < 10000)) {
                SectionTestCaseStart = 5019;
                SectionTestCaseEnd = 5042;
            } //
            // Variants
            //
            else if ((TestCaseCur >= 10001) && (TestCaseCur <= 10005) || (TestCaseCur < 20000)) {
                SectionTestCaseStart = 10001;
                SectionTestCaseEnd = 10005;
            } else if ((TestCaseCur >= 20001) && (TestCaseCur <= 20092) || (TestCaseCur < 30000)) {
                SectionTestCaseStart = 20001;
                SectionTestCaseEnd = 20092;
            } else if ((TestCaseCur >= 30001) && (TestCaseCur <= 30011) || (TestCaseCur < 40000)) {
                SectionTestCaseStart = 30001;
                SectionTestCaseEnd = 30011;
            } else if ((TestCaseCur >= 40001) && (TestCaseCur <= 40034) || (TestCaseCur < 50000)) {
                SectionTestCaseStart = 40001;
                SectionTestCaseEnd = 40034;
            } else if ((TestCaseCur >= 50001) && (TestCaseCur <= 50046) || (TestCaseCur < 80000)) {
                SectionTestCaseStart = 50001;
                SectionTestCaseEnd = 50046;
            } else if ((TestCaseCur >= 80000) && (TestCaseCur <= 80049) || (TestCaseCur < 90000)) {
                SectionTestCaseStart = 80000;
                SectionTestCaseEnd = 80049;
            } else if ((TestCaseCur >= 110001) && (TestCaseCur <= 110015) || (TestCaseCur < 120000)) {
                SectionTestCaseStart = 110001;
                SectionTestCaseEnd = 110015;
            } else {
                switch (TestCaseCur) {
                    //Function Test Cases
                    case 90001:
                        SectionTestCaseStart = 90001;
                        SectionTestCaseEnd = 90001;
                        break;
                    //Function Argument Test Cases
                    case 100001:
                        SectionTestCaseStart = 100001;
                        SectionTestCaseEnd = 100001;
                        break;
                    default:

                        break;
                }
            }
        }
        return true;
    }

    public static void mainRegresssionSubs(TestHarnessClass MyTestData) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = TestPredicate();
        String Exprn = "x+3";
        String DiffWithRespTo = "x";
        SubsStrSep.Substitute expValue;

        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');

        int i = 1;
        for (double x = 0, y = 0; i < 10; y++, x++, i++) {
            variables.put("x", x);
            variables.put("y", y);
            System.out.println("mainRegresssionSubs: x=" + x + " y=" + y + " Exprn=" + Exprn);
            expValue = SubsStrSep.parseSubsStr(Exprn, "x-2", variables, DiffWithRespTo);
            System.out.println(" mainRegresssionSubs:Result(Logical Value)=" + expValue.SubsStr());
            System.out.print(System.lineSeparator());
        }
    }

    public static boolean DupChecknInsert(int j) {
        boolean DupFlag = false;
        int i = 0;
        String DupList = "";
        String[] TempDataStr = new String[3];
        for (i = 0; (i < EqnDB.size()) && (!EqnDB.isEmpty()); i++) {
            String Temp = EqnDB.get(i);
            if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                TempDataStr = Temp.split("@");
            }
            String Exprn = "";
            if (TempDataStr.length >= 3) {
                Exprn = TempDataStr[2];
            }
            if ((EqnUnderTest != null) && (!EqnUnderTest.equalsIgnoreCase("")) && (Exprn != null)
                    && (Exprn.equalsIgnoreCase(EqnUnderTest))) {
                DupFlag = true;
                String[] DataTemp = Temp.split("@");
                if ((Integer.parseInt(DataTemp[1])) > 100) {
                    DupList = DupList + ":" + Integer.parseInt(DataTemp[1]);
                }
            }
        }
        if ((!EqnUnderTest.equalsIgnoreCase("")) && (((DupFlag == false) && (i == EqnDB.size())) || (EqnDB.isEmpty()) || (DupList.equalsIgnoreCase("")))) {
            System.out.println("DupChecknInsert:Inserting TestCase=" + j + " DupList=" + DupList + " EqnUnderTest=" + EqnUnderTest);
            EqnDB.put(i, "TestCase@" + j + "@" + EqnUnderTest);
            EqnDBIndex++;
        } else if ((!EqnUnderTest.equalsIgnoreCase(""))) {
            EqnDB.put(EqnDBIndex, "TestCase@" + j + "@" + ":Dup with " + DupList + ":");
            EqnDBDupCount++;
            EqnDBIndex++;
        }
        return DupFlag;
    }

    public static void mainRegresssionFInverse(TestHarnessClass TestData) {
        String Exprn = "";
        String OverallRegressionStatus = "Diff:Pass All (100 to 110007) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008"
                + "\n" + "TestCases:Parentheses " + " 80001 to 80024";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";
            Exprn = "";
            TempExprn = TestHarnessFInverse("FunctionInverse", j);
            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            }
            TotalTestCases++;
            Exprn = eatAll(Exprn, ' ');
            TestCasePassHighLevelFlag = true;
            FInvSingleHighTestCase(Exprn);
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControlFInv(j);
                TestCase = j;
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag == false) {
                MaxIndexFail++;
                FailedTestCount++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainRegresssionFInverse: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases=" + TotalTestCases + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainRegresssionFInverse: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void FInvSingleHighTestCase(String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        String DiffWithRespTo = "x";
        FuncInvSep.FuncInv expValue;
        SimpleExpression expSimple;
        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');
        String StageMapping = "";
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + " Exprn2=" + EqnUnderTest2 + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
        Double x = 0.0;
        Double y = 0.0;
        try {
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
            StageMapping = "Basic Mapping";
            System.out.println("FInvSingleHighTestCase: Exprn=" + Exprn + " EqnUnderTest2=" + UsageCalculus.EqnUnderTest2);
            expValue = FuncInvSep.parseFInverse(Exprn, variables, DiffWithRespTo, "y", "Neither");
            expSimple = parseSimple(expValue.evalStr(), variables, DiffWithRespTo);
            if (expSimple.SimpleExpr().equalsIgnoreCase(UsageCalculus.UserTestAnswer)) {
                TestCasePassStrFlag = true;
                TestCasePassHighLevelFlag = true;
                System.out.println(ConsoleColors.GREEN + " FInvSingleHighTestCase:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " FInvSingleHighTestCase:Answer=" + UsageCalculus.UserTestAnswer + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + " FInvSingleHighTestCase:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " FInvSingleHighTestCase:Answer=" + UsageCalculus.UserTestAnswer + ConsoleColors.RESET);
                TestCasePassStrFlag = false;
                TestCasePassHighLevelFlag = false;
            }
            System.out.print(System.lineSeparator());

        } catch (Exception MyException) {
        }
        System.out.print(System.lineSeparator());

    }

    public static String TestHarnessFInverse(String AlgoName, int j) {
        if ((j >= 100) && (j <= 115)) {
            TestHarnessFInverse.SanityTestCases(AlgoName, j);
        } else if ((j >= 200) && (j <= 225)) {
            TestHarnessFInverse.UnitTestCases(AlgoName, j);
        }

        return EqnUnderTest;
    }

    public static void DiffLimitTestCase(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.DiffExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;

        String StageMapping = "";
        int Order = 1;
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);

        for (double x = 0, y = 7; Order < MaxOrder1; y++, x++, Order++) {
            try {
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
                expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);
                System.out.print(System.lineSeparator());

                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());

    }

    public static void IntegralLimitTest(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        SimpleExpression expSimple;
        Expression expValue;
        DiffrIntegrSep.IntegrExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";
        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";
        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;
        int Order = 1;
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
        Order = 1;
        String StageMapping = "";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);

        for (double x = 0, y = 7; Order < MaxOrder1; y++, x++, Order++) {
            try {
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
                expSimple = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                StageMapping = "Integration";
                exp = DiffrIntegrSep.parseIntegr(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + Order + " th order)=> " + StrexpDiffExpress);

                StageMapping = "Integration Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);
                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, (x +(10 - x) / 10), "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);

                System.out.print(System.lineSeparator());
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static int RegressionControlFInv(int j) {
        if ((j >= 100) && (j <= 115)) {
            j++;
        } else if ((j >= 200) && (j < 225)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 115)) {
            j = 200;
        } else if ((j == 225)) {
            j = 5019;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControlPredicate:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void mainRegresssionExpr(TestHarnessClass TestData) {
        String Exprn = "";
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";
            Exprn = "";
            TempExprn = TestHarnessExpr("Expr", j);
            if ((!EqnUnderTest.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                Exprn = eatAll(TempExprn, ' ');
            }
            TotalTestCases++;
            Exprn = eatAll(Exprn, ' ');
            TestCasePassHighLevelFlag = true;
            ExprSingleHighTestCase(Exprn);
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControlExpr(j);
                TestCase = j;
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag == false) {
                MaxIndexFail++;
                FailedTestCount++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainRegresssionAlgebra: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases=" + TotalTestCases + " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainRegresssionAlgebra: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static void ExprSingleHighTestCase(String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        String DiffWithRespTo = "x";
        Expression expValue;
        SimpleExpression expSimple;
        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');
        String StageMapping = "";
        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
        Double x = 2.0;
        try {
            TestCasePassStrFlag = false;
            TestCasePassValFlag = false;
            variables.put(DiffWithRespTo, x);
            variables.put("a", 1.0);
            variables.put("b", 2.0);//variables.put("b", 0.0);
            variables.put("c", -1.0);
            variables.put("d", 2.0);
            variables.put("p", 3.0);
            variables.put("q", 0.0);
            variables.put("r", -1.0);
            variables.put("s", 2.0);
            StageMapping = "Basic Mapping";
            System.out.println("mainMathAlgebra: Exprn=" + Exprn);
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println("mainMathAlgebra: x=" + x + " expValue.eval()(y)=" + expValue.eval());
            System.out.print(System.lineSeparator());

        } catch (Exception MyException) {
        }
        System.out.print(System.lineSeparator());

    }

    public static String TestHarnessExpr(String AlgoName, int j) {
        if ((j >= 100) && (j <= 107)) {
            TestHarnessExpression.SanityTestCases(AlgoName, j);
        } else if ((j >= 200) && (j <= 225)) {
            TestHarnessExpression.UnitTestCases(AlgoName, j);
        }

        return EqnUnderTest;
    }

    public static int RegressionControlExpr(int j) {
        if ((j >= 100) && (j < 107)) {
            j++;
        } else if ((j >= 200) && (j < 225)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 107)) {
            j = 200;
        } else if ((j == 225)) {
            j = 5019;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControlPredicate:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void PalinControllerSame(int MaxOrder, String StartMyTestCase, String EndMyTestCase, String IntegreOrDiff) {
        System.out.println("PalinController:Start:" + IntegreOrDiff);
        double NumTestExecu = Math.round(Math.sqrt(287));
        double Start = Double.parseDouble(StartMyTestCase);
        double End = Double.parseDouble(EndMyTestCase);
        String Exprn1 = "";
        String Exprn2 = "";
        String Exprn = "";
        for (int i = 0; i <= 1; i++) {
            int TestCase1 = (int) (Math.random() * (End));
            int NoOfAttemps = 0;
            boolean NOTinGAPTestCase1 = false;
            if ((TestCaseInGap(TestCase1))) {
                TestCase1 = (int) (Math.random() * (SectionTestCaseEnd - SectionTestCaseStart)) + SectionTestCaseStart;
                NOTinGAPTestCase1 = !TestCaseInGap(TestCase1);
            } else {
                NOTinGAPTestCase1 = true;
            }
            SectionTestCaseStart = 0;
            SectionTestCaseEnd = 0;
            NoOfAttemps++;
            if (NOTinGAPTestCase1) {
                double SignRand = Math.random();
                int Operator = '+';
//                if (SignRand >= 0.5) {
//                    Operator = '+';
//                } else {
//                    Operator = '-';
//                }
                System.out.print(System.lineSeparator());
                System.out.println("PalinController:" + IntegreOrDiff + ":TestCase1=" + TestCase1);
                System.out.println("PalinController:" + IntegreOrDiff + ":TestCase2=" + TestCase1);
                if (IntegreOrDiff.equalsIgnoreCase("Diff")) {
                    Exprn1 = TestHarnessDiff("mainDiffTest", TestCase1);
                    Exprn2 = TestHarnessDiff("mainDiffTest", TestCase1);
                    Exprn = Exprn1 + (char) Operator + Exprn2;
                    PalinDiffSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase1, Operator);
                } else if (IntegreOrDiff.equalsIgnoreCase("Integral")) {
                    Exprn1 = TestHarnessDiff("mainIntegrTest", TestCase1);
                    Exprn2 = TestHarnessDiff("mainIntegrTest", TestCase1);
                    Exprn = Exprn1 + (char) Operator + Exprn2;
                    PalinIntegralSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase1, Operator);
                }
            }
        }
        System.out.println("PalinController:End:" + IntegreOrDiff);
    }

    public static void mainRegresssionHghLvlExprODETest(int MaxOrder, TestHarnessClass TestData) {
        int MaxOrder1 = MaxOrder;
        String Exprn = "";
        String OverallRegressionStatus = "Diff:Pass All (4000 to 110023) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008"
                + "\n" + "TestCases:Parentheses " + " 80001 to 80024";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if (EndTestCase > LastTestCaseHarness) {
            EndTestCase = LastTestCaseHarness;
        }
        System.out.print(ConsoleColors.BLUE + "Diff EndTestCase=" + EndTestCase + " LastTestCaseHarness=" + LastTestCaseHarness + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if (EndTestCase < TestCase) {
            EndTestCase = TestCase;
        }
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        cause = new Throwable();
        TotalTestCases = 0;
        ByPassedTestCases = 0;
        MissedNumberTestCases = 0;
        MissedTestCasesInfinity = 0;
        MissedTestCasesException = 0;

        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        int OldFailedTestCount = FailedTestCount;
        int SectionDisplayCntrl = 0;
        SectionStart = TestCase;
        EqnDB = new HashMap<>();
        ExceptionStringDB = new HashMap<Integer, String>();
        EqnDBIndex = 0;
        EqnDBDupCount = 0;
        int OldDups = EqnDBDupCount;
        int OldTestCase = j;
        int OldMaxIndexFail = 0;
        int OldTotalTestCases = 0;
        boolean DupFlag = false;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            DupFlag = false;
            String TempExprn = "";

            TempExprn = TestHarnessHghLvlExprODE("HghLvlExpr", j);
            Exprn = eatAll(TempExprn, ' ');
            DupFlag = DupChecknInsert(j);

            TestCasePassHighLevelFlag = true;
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder >= MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }
            OldMaxIndexFail = MaxIndexFail;
            if ((!EqnUnderTest.equalsIgnoreCase("")) && ((DupFlag == false))) {
                HghLvlExprODESingleHighTestCase(MaxOrder1, Exprn);
                System.out.println(ConsoleColors.RED + "HghLvlExpr: j=" + j + " TestCase=" + TestCase);
                if (DupFlag == false) {
                    TotalTestCases++;
                }
            } else if (DupFlag == true) {
                System.out.println(ConsoleColors.RED + "HghLvlExpr: j=" + j + " TestCase=" + TestCase + " Dup. Hence NOT Executing.");

            } else if (EqnUnderTest.equalsIgnoreCase("")) {
                ByPassedTestCases++;
            }

            if (TestCasePassHighLevelFlag == false) {
                if (SectionDisplayCntrl == 0) {
                    MaxIndexFail++;
                    if (DupFlag == false) {
                        FailedTestCount++;
                    }
                } else if (DupFlag == false) {
                    FailedTestCount++;
                }
            }
            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                OldTestCase = j;
                TestHarnessHghLvlExprODE(j);
                if (((j - OldTestCase) > 1)) {
                    SectionDisplayCntrl = 1;
                    GapProcess("HghLvlExpr", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, SectionDisplayCntrl, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);
                    OldFailedTestCount = FailedTestCount;
                    OldDups = EqnDBDupCount;
                    SectionStart = j;
                    OldTotalTestCases = TotalTestCases;
                }
                TestCase = j;
            }
            if (SectionDisplayCntrl == 1) {
                SectionDisplayCntrl = 0;
            }
        }
        GapProcess("HghLvlExpr", j, OldTotalTestCases, OldTestCase, OldMaxIndexFail, 1, OldFailedTestCount, FailedTestCount, OldDups, EqnDBDupCount);

        int k = 0;
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                k++;
            }
        }
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases-Dups=" + (TotalTestCases) + " Passed Count=" + (TotalTestCases - FailedTestCount) + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Summary : DupCount=" + k + " Total Exceptions=" + MaxIndexExcept + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Summary : ByPassedTestCases=" + ByPassedTestCases + " MissedNumberTestCases=" + MissedNumberTestCases + " MissedTestCasesInfinity=" + MissedTestCasesInfinity + " MissedTestCasesException=" + MissedTestCasesException + ConsoleColors.RESET);
        for (int y = 0; (MaxIndexFail >= 0) && (FailedTestCases != null) && (y < FailedTestCases.size()); y++) {
            if ((FailedTestCases.get(y).contains("Section")) && (FailedTestCases.get(y).contains("Complete # of Failures="))) {
                System.out.print(ConsoleColors.GREEN + FailedTestCases.get(y) + ConsoleColors.RESET);
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Summary : " + " Failed Test Cases are:" + ConsoleColors.RESET);

        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "HghLvlExpr: Exception TestCase Summary : Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y != 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Duplicate Summary : " + " EqnDBIndex=" + EqnDBIndex + ConsoleColors.RESET);
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))) {
                System.out.print(ConsoleColors.GREEN + EqnDB.get(y) + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(ConsoleColors.RED + "HghLvlExpr: TestCase Duplicate Summary : Total Dups=" + k + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.println(ConsoleColors.RED + " " + ExceptionStringDB.get(y) + ", " + ConsoleColors.RESET);
        }
        System.out.print(System.lineSeparator());
    }

    public static void HghLvlExprODESingleHighTestCase(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = eatAll(TestCases(), ' ');
        //String Exprn = eatAll(HighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingHighLevelUnitTestCases(), ' ');
        //String Exprn = eatAll(FailingLowLevelUnitTestCases(), ' ');
        //Exprn = eatAll(Exprn, ' ');
        MathsContxtLAv.SimpleExpression expSimpleMap1 = (() -> "");
        MathsContxtLAv.Expression expValue = (() -> Double.NaN);
        MathsContxtLAv.SimpleExpression expSimpleMap2 = (() -> "");
        DiffrIntegrSep.DiffExpr exp = (() -> "");
        MathsContxtLAv.OrderVar expOrder = (() -> "");

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String Constant = "C";

        String StrexpSimpleExpressMap1 = "";
        String StrexpDiffExpress = "";
        String StrexpSimpleExpressMap2 = "";
        String myOrder = "";

        double ExprnexpValue = 0;
        double DblexpValueMap1 = 0;
        double DblexpDiffValue = 0;
        double DblexpValueMap2 = 0;

        StageMapping = "";
        Order = 1;

        System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " TestCaseFile=" + TestCaseFile + " Section=" + Section + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);

        for (double x = 1.0, y = 7.0; Order < MaxOrder1; y++, /*x++,*/ Order++) {
            try {
                TestCasePassStrFlag = false;
                TestCasePassValFlag = false;
                TestCasePassConformalValFlag = false;
                variables.put(DiffWithRespTo, x);
                variables.put("y", y);
                variables.put("a", 1.0);
                variables.put("b", 2.0);//variables.put("b", 0.0);
                variables.put("c", -1.0);
                variables.put("d", 2.0);
                variables.put("m", -1.0);
                variables.put("p", 3.0);
                variables.put("q", 0.0);
                variables.put("r", -1.0);
                variables.put("s", 2.0);
                variables.put(Constant, 5.0);
                StageMapping = "Basic Mapping";
                expOrder = parseOrder(Exprn, DiffWithRespTo);
                myOrder = expOrder.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Exprn=" + Exprn + " Order=" + myOrder);
                ChaosPrevention();
                expSimpleMap1 = parseSimple(Exprn, variables, DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimpleMap1.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue + "  x=" + x + DiffWithRespTo + "=" + x);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = DiffrIntegrSep.parseDiff(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                ChaosPrevention();
                expSimpleMap2 = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimpleMap2.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageCalculus.FInverseEqn);
                System.out.print(System.lineSeparator());
                if (PassFailTestCaseString(TestCase, Order, "Diff", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValue(TestCase, Order, "Diff", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress, variables, x)) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass VALUE TEST Complete" + ConsoleColors.RESET);
                    TestCasePassValFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL VALUE TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }
                if ((TestCasePassStrFlag == true) || (TestCasePassValFlag == true)) {
                    TestCasePassConformalValFlag = ConformalMapTestCaseDiff(TestCase, Order, Exprn, expSimpleMap1, exp, expSimpleMap2, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order], variables, DiffWithRespTo);
                }
                if (TestCasePassConformalValFlag == true) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass Conformal Map TEST" + ConsoleColors.RESET);
                } else if ((TestCasePassStrFlag != true) || (TestCasePassValFlag != true)) {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "Conformal Map TEST Not Executed" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL Conformal Map TEST " + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) && (TestCasePassValFlag == true)) {
                    System.out.println(ConsoleColors.BLUE + "Super Test Answer Checks" + ConsoleColors.RESET);
                }
                if ((TestCasePassStrFlag == true) /* && (TestCasePassValFlag == true) && (TestCasePassConformalValFlag == true)*/) {
                    TestCasePassHighLevelFlag = true;//DO Nothing as Test Cases Passes
                } else {
                    String Temp = FailedTestCases.get(MaxIndexFail);
                    String HashVal = "";
                    if ((Temp != null) && (Order >= 2) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                        HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    } else {
                        HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag + ":ConfValFlag=" + TestCasePassConformalValFlag;
                    }
                    FailedTestCases.put(MaxIndexFail, HashVal);
                    //i=MaxOrder1;
                    TestCasePassHighLevelFlag = false;
                }
                Exprn = StrexpSimpleExpressMap2;
                System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.initCause(cause);
                if (MyException.getCause() != null) {
                    ExceptionStringDB.put(MaxIndexExcept, TestCase + ":" + MyException.getMessage());
                }
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " Failed at Order=" + Order + " due to Exception at Stage=" + StageMapping + "." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase("")) && (!Temp.contains("Section"))) {
                    HashVal = Temp + "," + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                } else {
                    HashVal = "TestCase=" + TestCase + ":Order=" + (Order) + ":Str=" + TestCasePassStrFlag + ":Val=" + TestCasePassValFlag;
                }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                    HashVal1 = Temp1 + "," + ":Order=" + (Order);
                } else {
                    HashVal1 = "TestCase=" + TestCase + ":Order=" + (Order);
                }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                Order = MaxOrder1;
                TestCasePassHighLevelFlag = false;
            }
        }
        System.out.print(System.lineSeparator());

    }

    public static int TestHarnessHghLvlExprODE(int j) {
        if ((j >= 100) && (j <= 107)) {
            j++;
        } else if ((j >= 200) && (j <= 225)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 107)) {
            j = 200;
        } else if ((j == 226)) {
            j = 300;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControl:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;
    }

    public static String TestHarnessHghLvlExprODE(String AlgoName, int j) {
        if ((j >= 100) && (j <= 107)) {
            Section = "SanityTest";
            SectionTestCaseStart = 100;
            SectionTestCaseEnd = 107;
            TestCaseFile = "HghLvlExprODE";
            HghLvlExprODE.SanityTestCases(AlgoName, j);
        } else if ((j >= 200) && (j <= 225)) {
            Section = "UnitTest";
            SectionTestCaseStart = 200;
            SectionTestCaseEnd = 225;
            TestCaseFile = "HghLvlExprODE";
            HghLvlExprODE.UnitTestCases(AlgoName, j);
        } else {
            SectionTestCaseStart = -1;
            SectionTestCaseEnd = -1;
            System.out.println(ConsoleColors.RED + "TestHarnessHghLvlExprODE:j= " + j + ": NOT Executed Loop" + ConsoleColors.RESET);
            ResetTestCase();
        }
        return EqnUnderTest;
    }

}
