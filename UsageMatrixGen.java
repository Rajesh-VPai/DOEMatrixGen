/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import Calculus.ConsoleColors;
import Calculus.MathematicalTestHarness;
import Calculus.MathsContxtLAv;
import Calculus.DiffrIntegrSep.DiffExpr;
import Calculus.MathsContxtLAv.Expression;
import static Calculus.CalculusApplication.FInverseLagrangeBurmannLowLevel;
import Calculus.SimpleAlgebraSep.FoG;
import Calculus.DiffrIntegrSep.IntegrExpr;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.DiffrIntegrSep.MyFuncIntegr;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import Calculus.MathsContxtLAv.OrderVar;
import Calculus.SimpleAlgebraSep.SimpleAlgebra;
import Calculus.SimpleAlgebraSep.SimpleAlgebraHghLvl;
import Calculus.MathsContxtLAv.SimpleExpression;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.SimpleAlgebraSep.parseAlgebra;
import static Calculus.SimpleAlgebraSep.parseAlgebraHghLvl;
import static Calculus.DiffrIntegrSep.parseDiff;
import static Calculus.SimpleAlgebraSep.parseFoG;
import static Calculus.DiffrIntegrSep.parseIntegr;
import static Calculus.MathsContxtLAv.parseOrder;
import static Calculus.MathsContxtLAv.parseSimple;
import Calculus.PredicateSep;
import Calculus.PredicateSep.PredicateBool;
import static Calculus.SubsStrSep.parseSubsStr;
import Calculus.TestHarnessClass;
import static Calculus.TestHarnessClass.PassFailTestCaseString;
import static Calculus.TestHarnessClass.PassFailTestCaseValue;
import TestHarness.TestHarnessCoefficients;
import TestHarness.TestHarnessIndConstant;
import TestHarness.TestHarnessNames;
import TestHarness.TestHarnessSignedX;
import TestHarness.TestHarnessUnitTest;
import TestHarness.TestHarnessPredicate;
import TestHarness.TestHarnessAlgebra;
import TestHarness.TestHarnessFunctions;
import static TestHarness.TestHarnessFunctions.TestCaseDiff90001;
import TestHarness.TestHarnessParenthesis;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UsageMatrixGen {

    public static Logger log = Logger.getLogger(UsageMatrixGen.class.getName());
    public static String DiffWithRespTo = "x";
    public static int MaxDiffOrder = 6;
    public static int MaxIntegreOrder = 6;
    public static String UserEquation = "";
    public static String UserTestPredicate = "";
    public static String UserTestAnswer = "";

    static String EqnUnderTest;
    static String FInverseEqn;
    static String EqnUnderTest2;

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
    static int LastTestCaseHarness = 110007;
    static int TestCaseLoopTimes = 110007;
    static String EqnRegressionDiffStatus = "All RED";
    static String EqnRegressionIntegreStatus = "All RED";
    static String TestCaseName = "Standard";
    static String TestCaseMatrixID = "Standard";

    static boolean TestCasePassStrFlag = false;
    static boolean TestCasePassValFlag = false;
    static boolean TestCasePassHighLevelFlag = true;
    static HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
    static HashMap<Integer, String> ExceptionTestCases = new HashMap<Integer, String>();
    static int MaxIndexExcept = 0;
    static int TotalTestCases = 0;
    static int MaxIndexFail = 0;
    static int SectionIndexFail = 1;
    static String Section = "";
    static int FailedTestCount = 0;
    static int SectionStart = 0;
    static int SectionTestCaseStart = 0;
    static int SectionTestCaseEnd = 0;

    static Map<Integer, String> EqnDB = new HashMap<>();
    static int EqnDBIndex = 0;
    static int EqnDBDupCount = 0;

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
        if ((j >= 100) && (j <= 125)) {
            Section = "SanityTestCases";
            SectionTestCaseStart = 100;
            SectionTestCaseEnd = 125;
            TestHarnessClass.SanityTestCases(AlgoName, j);
        } else if ((j >= 5019) && (j <= 5042)) {
            Section = "UnitTestCases";
            SectionTestCaseStart = 5019;
            SectionTestCaseEnd = 5042;
            TestHarnessUnitTest.UnitTestCases(AlgoName, j);
        } //
        // Variants
        //
        else if ((j >= 10001) && (j <= 10005)) {
            Section = "Names";
            SectionTestCaseStart = 10001;
            SectionTestCaseEnd = 10005;
            TestHarnessNames.Names(j);
        } else if ((j >= 20001) && (j <= 20092)) {
            Section = "Coefficients";
            SectionTestCaseStart = 20001;
            SectionTestCaseEnd = 20092;
            TestHarnessCoefficients.Coefficients(AlgoName, j);
        } else if ((j >= 30001) && (j <= 30011)) {
            Section = "Exponent";
            SectionTestCaseStart = 30001;
            SectionTestCaseEnd = 30011;
            TestHarnessClass.Exponent(AlgoName, j);
        } else if ((j >= 40001) && (j <= 40075)) {
            Section = "SignedX";
            SectionTestCaseStart = 40001;
            SectionTestCaseEnd = 40075;
            TestHarnessSignedX.SignedX(AlgoName, j);
        } else if ((j >= 50001) && (j <= 50044)) {
            Section = "IndConstant";
            SectionTestCaseStart = 50001;
            SectionTestCaseEnd = 50044;
            TestHarnessIndConstant.IndConstant(AlgoName, j);
        } else if ((j >= 80001) && (j < 80042)) {
            Section = "Parentheses";
            SectionTestCaseStart = 80001;
            SectionTestCaseEnd = 80042;
            TestHarnessParenthesis.Parenthesis(AlgoName, j);
        } else if ((j >= 110001) && (j <= 110007)) {
            Section = "SoftwareEngg";
            SectionTestCaseStart = 110001;
            SectionTestCaseEnd = 110007;
            TestHarnessClass.SoftwareEngg(AlgoName, j);
        } else {
            switch (j) {
                //Function Test Cases
                case 90001:
                    Section = "Function Test Cases";
                    SectionTestCaseStart = 90001;
                    SectionTestCaseEnd = 90001;
                    TestHarnessFunctions.TestCaseDiff90001();
                    break;
                //Function Argument Test Cases
                case 100001:
                    Section = "Function Argument Test Cases";
                    SectionTestCaseStart = 100001;
                    SectionTestCaseEnd = 100001;
                    TestHarnessFunctions.TestCaseDiff100001();
                    break;
                default:
                    SectionTestCaseStart = -1;
                    SectionTestCaseEnd = -1;
                    System.out.println(ConsoleColors.RED + "FailingTestHarnessDiff:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
                    ResetTestCase();
                    break;
            }
        }

        return EqnUnderTest;
    }

    public static void mainPredicate(String... args) {
        Map<String, Double> variables = new HashMap<>();
        //String Exprn = TestPredicate();
        String Exprn = UserTestPredicate;
        String DiffWithRespTo = "x";
        PredicateBool expValue;

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
        String Exprn = eatAll(UsageMatrixGen.UserEquation, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;

        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        String DiffWithRespTo = "x";
        String Constant = "C";
        //String DiffWithRespTo = "Xn";
        
        String StrexpSimpleExpress = "";

        int i = 1;
        for (double x = 1, y = 7; i < UsageMatrixGen.MaxDiffOrder; y++, x++, i++) {
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
            expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());

            StrexpSimpleExpress = expSimple.SimpleExpr();

            System.out.println("main: x=" + x + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println("main: x=" + x + " Basic Simple (Exprn Value)=" + expValue.eval());

            exp = parseDiff(StrexpSimpleExpress, variables,  DiffWithRespTo);
            StrexpSimpleExpress = exp.DiffExpr();
            System.out.println(" main:Result(Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Diff Value)=" + expValue.eval());

            expSimple = parseSimple(StrexpSimpleExpress, variables,  DiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
        }
    }

    public static void mainIntegral(String... args) {
        Map<String, Double> variables = new HashMap<>();
        String Exprn = eatAll(UsageMatrixGen.UserEquation, ' ');
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
        int i = 1;
        for (double x = -20, y = 7; i < UsageMatrixGen.MaxIntegreOrder; y++, x++, i++) {
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
            expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());

            StrexpSimpleExpress = expSimple.SimpleExpr();

            expIntgr = parseIntegr(StrexpSimpleExpress, variables,  DiffWithRespTo);
            StrexpIntegrExpress = expIntgr.IntegrExpr();
            System.out.println(" main:Result(Integral Equation)(" + i + " th order)=> " + StrexpIntegrExpress);
            expValue = parse(StrexpIntegrExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Integral Value)=" + expValue.eval());

            expSimple = parseSimple(StrexpIntegrExpress, variables,  DiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println(" main:Result(Simplified Diff Equation)(" + i + " th order)=> " + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Simplified Diff Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println(" main:Result(Expn Value)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
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
        String OverallRegressionStatus = "Diff:Pass All (100 to 110007) Except Test Cases: "
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
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        int OldFailedTestCount = FailedTestCount;
        int SectionDisplayCntrl = 0;
        SectionStart = TestCase;
        EqnDB = new HashMap<>();
        EqnDBIndex = 0;
        EqnDBDupCount = 0;
        int OldDups=EqnDBDupCount;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = TestHarnessDiff("mainDiffTest", j);
            Exprn = eatAll(TempExprn, ' ');
            DupChecknInsert(j);
            TestCasePassHighLevelFlag = true;
            if (MaxOrder == -1) {
                MaxOrder1 = MaxDiffOrder; //From Test Case
            } else if (MaxOrder >= MaxDiffOrder) {
                MaxOrder1 = MaxDiffOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }
            int OldMaxIndexFail = MaxIndexFail;
            if (!EqnUnderTest.equalsIgnoreCase("")) {
                    DiffSingleHighTestCase(MaxOrder1, Exprn);
                    System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                    TotalTestCases++;
            }

            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                int OldTestCase = j;
                j = RegressionControl(j);
                if ((j - OldTestCase) > 1) {
                    SectionDisplayCntrl = 1;
                    GapProcess(j, OldTestCase, OldMaxIndexFail, SectionDisplayCntrl, OldFailedTestCount, FailedTestCount,OldDups,EqnDBDupCount);
                    OldFailedTestCount = FailedTestCount;
                    OldDups=EqnDBDupCount;
                    SectionStart = j;
                }
                TestCase = j;
            }
            if (TestCasePassHighLevelFlag == false) {
                if (SectionDisplayCntrl == 0) {
                    MaxIndexFail++;
                    FailedTestCount++;
                }
                if (SectionDisplayCntrl == 1) {
                    SectionDisplayCntrl = 0;
                }
            }
        }
        int k=0;
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))
                    && (!Temp.contains(":Dup with :20016:"))
                    && (!Temp.contains(":Dup with :20013:"))) {
                k++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases-Dups=" + (TotalTestCases - k ) + " Passed Count=" + (TotalTestCases - FailedTestCount) + " DupCount=" + k + ConsoleColors.RESET);
        for (int y = 0; y < FailedTestCases.size(); y++) {
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
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Duplicate Summary : " + ConsoleColors.RESET);
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))
                    && (!Temp.contains(":Dup with :20016:"))
                    && (!Temp.contains(":Dup with :20013:"))) {
                System.out.print(ConsoleColors.GREEN + EqnDB.get(y) + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Duplicate Summary : Total Dups=" + k + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
    }

    public static int RegressionControl(int j) {
        if ((j >= 100) && (j < 125)) {
            j++;
        } else if ((j >= 5019) && (j < 5042)) {
            j++;
        } else if ((j >= 10001) && (j < 10005)) {
            j++;
        } else if ((j >= 20001) && (j < 20092)) {
            j++;
        } else if ((j >= 30001) && (j < 30011)) {
            j++;
        } else if ((j >= 40001) && (j < 40076)) {
            j++;
        } else if ((j >= 50001) && (j < 50044)) {
            j++;
        } else if ((j >= 80001) && (j < 80042)) {
            j++;
        } else if ((j >= 110001) && (j < 110007)) {
            j++;
        } else if (j == 90001) {
            j = 100001;
        } else if (j == 100001) {
            j = 110001;
        } else if (j == 110007) {
            j = 120001;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 125)) {
            j = 5019;
        } else if (j == 5042) {
            j = 10001;
        } else if (j == 10005) {
            j = 20001;
        } else if (j == 20092) {
            j = 30001;
        } else if (j == 30011) {
            j = 40001;
        } else if (j == 40076) {
            j = 50001;
        } else if (j == 50044) {
            j = 80001;
        } else if (j == 80042) {
            j = 90001;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControl:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void mainRegressionIntegrTest(int MaxOrder, TestHarnessClass TestData) {
        int MaxOrder1 = MaxOrder;
        String Exprn = "";
        String OverallRegressionStatus = "Integral:Pass All (100 to 110007) Except Test Cases: "
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
        int j = TestCase;
        FailedTestCases = new HashMap<Integer, String>();
        ExceptionTestCases = new HashMap<Integer, String>();
        TotalTestCases = 0;
        MaxIndexExcept = 0;
        MaxIndexFail = 0;
        SectionIndexFail = 1;
        FailedTestCount = 0;
        int SectionDisplayCntrl = 0;
        int OldFailedTestCount = FailedTestCount;
        SectionStart = TestCase;
        EqnDB = new HashMap<>();
        EqnDB.clear();
        EqnDBIndex = 0;
        EqnDBDupCount = 0;
        int OldDups=EqnDBDupCount;
        while (j <= EndTestCase) {
            EqnUnderTest = "";
            String TempExprn = "";

            TempExprn = TestHarnessDiff("mainIntegrTest", j);
            Exprn = eatAll(TempExprn, ' ');
            DupChecknInsert(j);
            TestCasePassHighLevelFlag = true;
            if (MaxOrder == -1) {
                MaxOrder1 = MaxIntegreOrder; //From Test Case
            } else if (MaxOrder > MaxIntegreOrder) {
                MaxOrder1 = MaxIntegreOrder;
            } else {
                MaxOrder1 = MaxOrder;
            }
            int OldMaxIndexFail = MaxIndexFail;
            if (!EqnUnderTest.equalsIgnoreCase("")) {
                IntegralHighSingleTest(MaxOrder1, Exprn);
                System.out.println(ConsoleColors.RED + "mainIntegrTest: j=" + j + " TestCase=" + TestCase);
                TotalTestCases++;
            }
                if (TestCaseLoopTimes == 1) {
                    break;
                } else {
                    int OldTestCase = j;
                    j = RegressionControl(j);
                    if ((j - OldTestCase) > 1) {
                        SectionDisplayCntrl = 1;
                        GapProcess(j, OldTestCase, OldMaxIndexFail, SectionDisplayCntrl, OldFailedTestCount, FailedTestCount,OldDups,EqnDBDupCount);
                        OldFailedTestCount = FailedTestCount;
                        OldDups=EqnDBDupCount;
                        SectionStart = j;
                    }
                    TestCase = j;
                    //System.out.println(ConsoleColors.RED +"mainIntegrTest: j="+ j + " TestCase=" + TestCase);
                }
                if (TestCasePassHighLevelFlag == false) {
                    if (SectionDisplayCntrl == 0) {
                        MaxIndexFail++;
                        FailedTestCount++;
                    }
                }
                if (SectionDisplayCntrl == 1) {
                    SectionDisplayCntrl = 0;
                }
            }
        int k=0;
        for (int y = 0; (y < EqnDB.size()); y++) {
            String Temp = EqnDB.get(y);
            if ((Temp != null) && (Temp.contains(":Dup with"))
                    && (!Temp.contains(":Dup with :20016:"))
                    && (!Temp.contains(":Dup with :20013:"))) {
                k++;
            }
        }
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Summary : Total Failed=" + FailedTestCount + " Out of Run TestCases-Dups=" + (TotalTestCases - k ) + " Passed Count=" + (TotalTestCases - FailedTestCount) + " DupCount=" + k + ConsoleColors.RESET);
        for (int y = 0; y < FailedTestCases.size(); y++) {
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
            if ((Temp != null) && (Temp.contains(":Dup with"))
                    && (!Temp.contains(":Dup with :20016:"))
                    && (!Temp.contains(":Dup with :20013:"))) {
                System.out.print(ConsoleColors.GREEN + EqnDB.get(y) + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
            }
        }
        System.out.println(ConsoleColors.RED + "mainIntegrTest: TestCase Duplicate Summary : Total Dups=" + k + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
    }

    public static void mainUser(String... args) {
        System.out.println(ConsoleColors.BLUE + "Differentiation / Integration:" + ConsoleColors.RESET);
        String TechDiffWithRespTo = DiffWithRespTo;
        String xExprn = "Enter*Your*Equation/Here:x^2+25:";
        Scanner scanner = new Scanner(System.in);

        System.out.print(xExprn);
        xExprn = scanner.nextLine();
        xExprn = eatAll(xExprn, ' ');
        System.out.print("You Entered Equation:Example:x^2+25:" + xExprn);
        System.out.print(System.lineSeparator());

        System.out.print("DiffWithRespTo:[" + TechDiffWithRespTo + "]:");
        TechDiffWithRespTo = scanner.nextLine();

        if (TechDiffWithRespTo.equalsIgnoreCase("")) {
            TechDiffWithRespTo = DiffWithRespTo;
        }
        //TechDiffWithRespTo = eatAll(TechDiffWithRespTo, ' ');

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
        expValue = parse(Exprn, variables, TechDiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
        for (; (TechDiffOrderIndex <= TechDiffOrder); TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);
            expSimple = parseSimple(Exprn, variables,  TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            DiffExpr exp = parseDiff(StrexpSimpleExpress, variables,  TechDiffWithRespTo);
            StrexpDiffExpress = exp.DiffExpr();
            System.out.println("UserDiff:Result(Diff Equation)(" + TechDiffOrderIndex + " th order)=> " + StrexpDiffExpress);

            expSimple = parseSimple(StrexpDiffExpress, variables,  TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserDiff: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println("UserDiff:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;

        }
        return StrexpSimpleExpress;
    }

    public static String UserIntegr(String Exprn, String AlgoName, int TechDiffOrder, String TechDiffWithRespTo) {
        Map<String, Double> variables = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncIntegr();
        MyFuncSimple();
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr exp;
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int TechDiffOrderIndex = 1;
        expValue = parse(Exprn, variables, TechDiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
        for (; (TechDiffOrderIndex <= TechDiffOrder); TechDiffOrderIndex++) {
            variables.put(TechDiffWithRespTo, 1.0);
            expSimple = parseSimple(Exprn, variables,  TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);

            exp = parseIntegr(StrexpSimpleExpress, variables,  TechDiffWithRespTo);
            StrexpDiffExpress = exp.IntegrExpr();
            System.out.println("UserIntegr:Result(Integr Equation)(" + TechDiffOrderIndex + " th order)=> " + StrexpDiffExpress);

            expSimple = parseSimple(StrexpDiffExpress, variables,  TechDiffWithRespTo);
            StrexpSimpleExpress = expSimple.SimpleExpr();
            System.out.println("UserIntegr: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Exprn=" + StrexpSimpleExpress);
            expValue = parse(StrexpSimpleExpress, variables, TechDiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
            System.out.println("UserIntegr:Result(Value)(" + TechDiffWithRespTo + "=" + variables.get(TechDiffWithRespTo) + ")=" + expValue.eval());
            Exprn = StrexpSimpleExpress;

        }
        return StrexpSimpleExpress;
    }

    public static void mainAlgebraUser(String... args) {
        Map<String, Double> variables = new HashMap<>();
        String StrexpSimpleExpress = "";
        SimpleExpression expSimple;
        String FuncF = "x^2+25";

        String FuncG = "2*x+98";
        SimpleAlgebra F;
        //SimpleAlgebraHghLvl F;
        FoG FuncComp;
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

        F = parseAlgebra(FuncF, FuncG, "*", variables, TechDiffWithRespTo);
        //F = parseAlgebraHghLvl(FuncF, FuncG, "*", variables, TechDiffWithRespTo);

        expSimple = parseSimple(F.Algebra(), variables,  TechDiffWithRespTo);
        StrexpSimpleExpress = expSimple.SimpleExpr();
        System.out.println("mainAlgebra: TechDiffWithRespTo=" + TechDiffWithRespTo + " Basic Simple Algebraic Exprn=" + StrexpSimpleExpress);

        FuncComp = parseFoG(FuncF, FuncG, variables, TechDiffWithRespTo);

        //FuncComp=parseFoGLowLvL(FuncF, FuncG, variables, TechDiffWithRespTo);
        if (!FuncComp.Substitute().contains("(")) {
            expSimple = parseSimple(FuncComp.Substitute(), variables,  TechDiffWithRespTo);
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
            IntegrExpr exp;
            exp = parseIntegr(UsageMatrixGen.EqnSolArrayMap2[Order], variables,  DiffWithRespTo);
            Exprn = exp.IntegrExpr();
            expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
            Exprn = expSimple.SimpleExpr();
            expSimple = parseSimple(EqnSolArrayMap1[Order], variables, DiffWithRespTo);
            Exprn1 = expSimple.SimpleExpr();
            if ((Exprn.equalsIgnoreCase(Exprn1) || (Exprn1.contains(Exprn)))) {
                return true;
            }
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Integral)=" + Exprn + ConsoleColors.RESET);
            System.out.println(ConsoleColors.RED + "ValidateGivenAnswer:Exprn(Answer)  =" + Exprn1 + ConsoleColors.RESET);
        } else if (OrigOperation.equalsIgnoreCase("Integre")) {
            DiffExpr exp;
            exp = parseDiff(UsageMatrixGen.EqnSolArrayMap4[Order], variables,  DiffWithRespTo);
            Exprn = exp.DiffExpr();
            expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
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
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
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
                expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
                StrexpSimpleExpressMap1 = expSimple.SimpleExpr();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple Exprn=" + StrexpSimpleExpressMap1);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = parseDiff(StrexpSimpleExpressMap1, variables,  DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables,  DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageMatrixGen.FInverseEqn);
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

                if (PassFailTestCaseValue(TestCase, Order, "Diff", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress,variables,x)) {
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

    public static void IntegralHighSingleTest(int MaxOrder1, String Exprn) {
        Map<String, Double> variables = new HashMap<>();
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
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
        int Order = 1;
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
        Order = 1;
        String StageMapping = "";
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

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                StageMapping = "Integration";
                exp = parseIntegr(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + Order + " th order)=> " + StrexpDiffExpress);

                StageMapping = "Integration Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);
                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, (x +(10 - x) / 10), "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageMatrixGen.FInverseEqn);

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

                if (PassFailTestCaseValue(TestCase, Order, "Integre", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress,variables,x)) {
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
        PredicateSep.Predicate3VL expValue;

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
                expValue = PredicateSep.parsePredicate3VL(Exprn, variables, "Silent", MathsContxtLAv.LogLevel.Off);
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
        } else if ((j >= 200) && (j <= 226)) {
            TestHarnessPredicate.UnitTestCases(AlgoName, j);
        }

        return UserTestPredicate;
    }

    public static int RegressionControlPredicate(int j) {
        if ((j >= 100) && (j < 107)) {
            j++;
        } else if ((j >= 200) && (j < 227)) {
            j++;
        } //Bypass Gaps Section
        else if ((j < 100)) {
            j = 100;
        } else if ((j == 107)) {
            j = 200;
        } else if ((j == 227)) {
            j = 5019;
        } else {
            System.out.println(ConsoleColors.RED + "RegressionControlPredicate:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }

    public static void GapProcess(int CurrentTestCase, int OldTestCase, int OldMaxIndexFail, int SectionDisplayCntrl, int OldFailedTestCount, int FailedTestCount, int OldDups, int DupsCur) {
        if ((SectionDisplayCntrl == 1)) {
            String Temp = FailedTestCases.get(MaxIndexFail);
            FailedTestCases.put(MaxIndexFail, Temp + "\n");
            FailedTestCases.put(MaxIndexFail + 1, "Section " + Section + " Complete" + " # of Failures=" + (FailedTestCount - OldFailedTestCount) + " Out of=" + (OldTestCase - SectionStart + 1) + " Test Cases. Dups=" + (DupsCur-OldDups)+ " in Section \n");
            MaxIndexFail++;
            MaxIndexFail++;
        }
    }

    public static void mainRegresssionAlgebra(TestHarnessClass TestData) {
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
        SimpleAlgebra expValue;
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
            System.out.println("mainMathAlgebra: Exprn=" + Exprn + " EqnUnderTest2=" + UsageMatrixGen.EqnUnderTest2);
            expValue = parseAlgebra(Exprn, UsageMatrixGen.EqnUnderTest2, "*", variables, DiffWithRespTo);
            expSimple = parseSimple(expValue.Algebra(), variables, DiffWithRespTo);
            if (expSimple.SimpleExpr().equalsIgnoreCase(UsageMatrixGen.UserTestAnswer)) {
                TestCasePassStrFlag = true;
                TestCasePassHighLevelFlag = true;
                System.out.println(ConsoleColors.GREEN + " mainMathAlgebra:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " mainMathAlgebra:Answer=" + UsageMatrixGen.UserTestAnswer + ConsoleColors.RESET);
            } else {
                System.out.println(ConsoleColors.RED + " mainMathAlgebra:Result=" + expSimple.SimpleExpr() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " mainMathAlgebra:Answer=" + UsageMatrixGen.UserTestAnswer + ConsoleColors.RESET);
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

        UsageMatrixGen.EqnRegressionDiffStatus = "All Green Including Values";
        UsageMatrixGen.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageMatrixGen.EqnUnderTest = "";
        UsageMatrixGen.TestCaseName = "";
        UsageMatrixGen.TestCaseMatrixID = "";

        UsageMatrixGen.DiffWithRespTo = "";
        UsageMatrixGen.MaxDiffOrder = 0;
        UsageMatrixGen.MaxIntegreOrder = 0;

        UsageMatrixGen.EqnSolArrayMap1[1] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[1] = "";
        UsageMatrixGen.EqnSolArrayMap2[1] = "";
        UsageMatrixGen.EqnSolArrayMap3[1] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[1] = "";
        UsageMatrixGen.EqnSolArrayMap4[1] = "";
        UsageMatrixGen.EqnSolOrder[1] = 1;

        UsageMatrixGen.EqnSolArrayMap1[2] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[2] = "";
        UsageMatrixGen.EqnSolArrayMap2[2] = "";
        UsageMatrixGen.EqnSolArrayMap3[2] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[2] = "";
        UsageMatrixGen.EqnSolArrayMap4[2] = "";
        UsageMatrixGen.EqnSolOrder[2] = 2;

        UsageMatrixGen.EqnSolArrayMap1[3] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[3] = "";
        UsageMatrixGen.EqnSolArrayMap2[3] = "";
        UsageMatrixGen.EqnSolArrayMap3[3] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[3] = "";
        UsageMatrixGen.EqnSolArrayMap4[3] = "";
        UsageMatrixGen.EqnSolOrder[3] = 3;

        UsageMatrixGen.EqnSolArrayMap1[4] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[4] = "";
        UsageMatrixGen.EqnSolArrayMap2[4] = "";
        UsageMatrixGen.EqnSolArrayMap3[4] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[4] = "";
        UsageMatrixGen.EqnSolArrayMap4[4] = "";
        UsageMatrixGen.EqnSolOrder[4] = 4;

        UsageMatrixGen.EqnSolArrayMap1[5] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[5] = "";
        UsageMatrixGen.EqnSolArrayMap2[5] = "";
        UsageMatrixGen.EqnSolArrayMap3[5] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[5] = "";
        UsageMatrixGen.EqnSolArrayMap4[5] = "";
        UsageMatrixGen.EqnSolOrder[5] = 5;

        UsageMatrixGen.EqnSolArrayMap1[6] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[6] = "";
        UsageMatrixGen.EqnSolArrayMap2[6] = "";
        UsageMatrixGen.EqnSolArrayMap3[6] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[6] = "";
        UsageMatrixGen.EqnSolArrayMap4[6] = "";
        UsageMatrixGen.EqnSolOrder[6] = 6;

        UsageMatrixGen.EqnSolArrayMap1[7] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[7] = "";
        UsageMatrixGen.EqnSolArrayMap2[7] = "";
        UsageMatrixGen.EqnSolArrayMap3[7] = "";
        UsageMatrixGen.EqnSolArrayIntegrAlone[7] = "";
        UsageMatrixGen.EqnSolArrayMap4[7] = "";
        UsageMatrixGen.EqnSolOrder[7] = 7;

        UsageMatrixGen.EqnSolArrayMap1[8] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[8] = "";
        UsageMatrixGen.EqnSolArrayMap2[8] = "";
        UsageMatrixGen.EqnSolOrder[8] = 8;

        UsageMatrixGen.EqnSolArrayMap1[9] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[9] = "";
        UsageMatrixGen.EqnSolArrayMap2[9] = "";
        UsageMatrixGen.EqnSolOrder[9] = 9;

        UsageMatrixGen.EqnSolArrayMap1[10] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[10] = "";
        UsageMatrixGen.EqnSolArrayMap2[10] = "";
        UsageMatrixGen.EqnSolOrder[10] = 10;

        UsageMatrixGen.EqnSolArrayMap1[11] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[11] = "";
        UsageMatrixGen.EqnSolArrayMap2[11] = "";
        UsageMatrixGen.EqnSolOrder[11] = 11;

        UsageMatrixGen.EqnSolArrayMap1[12] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[12] = "";
        UsageMatrixGen.EqnSolArrayMap2[12] = "";
        UsageMatrixGen.EqnSolOrder[12] = 12;

        UsageMatrixGen.EqnSolArrayMap1[13] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[13] = "";
        UsageMatrixGen.EqnSolArrayMap2[13] = "";
        UsageMatrixGen.EqnSolOrder[13] = 13;

        UsageMatrixGen.EqnSolArrayMap1[14] = "";
        UsageMatrixGen.EqnSolArrayDiffAlone[14] = "";
        UsageMatrixGen.EqnSolArrayMap2[14] = "";
        UsageMatrixGen.EqnSolOrder[14] = 14;
    }

    public static void PalinDiffSingleHighTestCase(int MaxOrder1, String Exprn, int TestCase1, int TestCase2, int Operator) {
        Map<String, Double> variables = new HashMap<>();
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        DiffExpr exp;
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

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);
                StageMapping = "Differentiation";
                exp = parseDiff(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.DiffExpr();
                System.out.println(" main:Result(Diff Equation)(" + Order + " th order)=> " + StrexpDiffExpress);
                StageMapping = "Differentiation Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Diff Value)=" + DblexpDiffValue);
                System.out.println(" main:Result(Simplified Diff Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);

                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, 10.0, "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageMatrixGen.FInverseEqn);
                System.out.print(System.lineSeparator());
                if (PassFailTestCaseString(TestCase1, Order, "Diff", Exprn, StrexpSimpleExpressMap1, StrexpDiffExpress, StrexpSimpleExpressMap2, EqnUnderTest, EqnSolArrayMap1[Order], EqnSolArrayDiffAlone[Order], EqnSolArrayMap2[Order])) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                    TestCasePassStrFlag = true;
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                    if (TestCasePassHighLevelFlag == true) {
                        TestCasePassHighLevelFlag = false;
                    }
                }

                if (PassFailTestCaseValue(TestCase1, Order, "Diff", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress,variables,x)) {
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
        Exprn = eatAll(Exprn, ' ');
        SimpleExpression expSimple;
        Expression expValue;
        IntegrExpr exp;
        OrderVar expOrder;

        MyFuncExpress();
        MyFuncDiff();
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
        int Order = 1;
        System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseName + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Test Case Name=" + TestCaseMatrixID + ConsoleColors.RESET);
        System.out.println(ConsoleColors.BLUE + "Integral Regression Status : Test Case should be " + EqnRegressionIntegreStatus + ConsoleColors.RESET);
        Order = 1;
        String StageMapping = "";
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

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                ExprnexpValue = expValue.eval();
                System.out.println(" main:Result(Expn Value)=" + ExprnexpValue);

                expValue = parse(StrexpSimpleExpressMap1, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap1 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Basic Simple (Exprn Value)=" + DblexpValueMap1);

                StageMapping = "Integration";
                exp = parseIntegr(StrexpSimpleExpressMap1, variables, DiffWithRespTo);
                StrexpDiffExpress = exp.IntegrExpr();
                System.out.println(" main:Result(Integral Equation)(" + Order + " th order)=> " + StrexpDiffExpress);

                StageMapping = "Integration Mapping";
                expSimple = parseSimple(StrexpDiffExpress, variables, DiffWithRespTo);
                StrexpSimpleExpressMap2 = expSimple.SimpleExpr();
                System.out.println(" main:Result(Simplified Integral Equation)(" + Order + " th order)=> " + StrexpSimpleExpressMap2);
                expValue = parse(StrexpDiffExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpDiffValue = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Integral Value)=" + DblexpDiffValue);
                expValue = parse(StrexpSimpleExpressMap2, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
                DblexpValueMap2 = expValue.eval();
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Integral Value)=" + expValue.eval());
                System.out.println("main: " + DiffWithRespTo + "=" + x + " Result(Simplified Diff Value)=" + expValue.eval());
                //Usage.FInverseEqn=FInverseLagrangeBurmann( Exprn, DiffWithRespTo, MaxOrder1,x, "",  "", '/', 12);
                //Usage.FInverseEqn = FInverseLagrangeBurmannLowLevel(Exprn, DiffWithRespTo, MaxOrder1, x, (x +(10 - x) / 10), "", "", '/', 4);
                //System.out.println("main: FInverse:" + "x" + "=" + UsageMatrixGen.FInverseEqn);

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

                if (PassFailTestCaseValue(TestCase, Order, "Integre", Exprn, ExprnexpValue, DblexpValueMap1, DblexpDiffValue, DblexpValueMap2, StrexpDiffExpress,variables,x)) {
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
                    Exprn2 = TestHarnessDiff("mainDiffTest", TestCase2);
                    Exprn = Exprn1 + (char) Operator + Exprn2;
                    PalinDiffSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase2, Operator);
                } else if (IntegreOrDiff.equalsIgnoreCase("Integral")) {
                    Exprn1 = TestHarnessDiff("mainIntegrTest", TestCase1);
                    Exprn2 = TestHarnessDiff("mainIntegrTest", TestCase2);
                    Exprn = Exprn1 + (char) Operator + Exprn2;
                    PalinIntegralSingleHighTestCase(MaxOrder, Exprn, TestCase1, TestCase2, Operator);
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
            } else if ((TestCaseCur >= 50001) && (TestCaseCur <= 50044) || (TestCaseCur < 80000)) {
                SectionTestCaseStart = 50001;
                SectionTestCaseEnd = 50044;
            } else if ((TestCaseCur >= 80001) && (TestCaseCur < 80042) || (TestCaseCur < 90000)) {
                SectionTestCaseStart = 80001;
                SectionTestCaseEnd = 80042;
            } else if ((TestCaseCur >= 110001) && (TestCaseCur <= 110007) || (TestCaseCur < 120000)) {
                SectionTestCaseStart = 110001;
                SectionTestCaseEnd = 110007;
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
        Calculus.SubsStrSep.Substitute expValue;

        MyFuncExpress();
        Exprn = eatAll(Exprn, ' ');

        int i = 1;
        for (double x = 0, y = 0; i < 10; y++, x++, i++) {
            variables.put("x", x);
            variables.put("y", y);
            System.out.println("mainRegresssionSubs: x=" + x + " y=" + y + " Exprn=" + Exprn);
            expValue = parseSubsStr(Exprn, "x-2", variables, DiffWithRespTo);
            System.out.println(" mainRegresssionSubs:Result(Logical Value)=" + expValue.SubsStr());
            System.out.print(System.lineSeparator());
        }
    }

    public static void DupChecknInsert(int j) {
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
            if ((!EqnDB.isEmpty()) && (EqnUnderTest != null) && (Exprn != null) && (Temp != null) && (!Temp.equalsIgnoreCase(""))
                    && (!Temp.contains(":Dup with"))
                    && (Exprn.equalsIgnoreCase(EqnUnderTest))) {
                DupFlag = true;
                String[] DataTemp = Temp.split("@");
                if ((Integer.parseInt(DataTemp[1])) > 100) {
                    DupList = DupList + ":" + Integer.parseInt(DataTemp[1]);
                }
            }
        }
        if (((DupFlag == false) && (i == EqnDB.size())) || (EqnDB.isEmpty()) || (DupList.equalsIgnoreCase(""))) {
            System.out.println("DupChecknInsert:Inserting TestCase=" + j + " DupList=" + DupList + " EqnUnderTest=" + EqnUnderTest);
            EqnDB.put(i, "TestCase@" + j + "@" + EqnUnderTest);
            EqnDBIndex++;
        } else {
            EqnDB.put(EqnDBIndex, "TestCase@" + j + "@" + ":Dup with " + DupList + ":");
            if ( (!EqnDB.get(EqnDBIndex).contains(":Dup with :20016:"))
                    && (!EqnDB.get(EqnDBIndex).contains(":Dup with :20013:")) )
                    EqnDBDupCount++;
            EqnDBIndex++;
        }
    }
}
