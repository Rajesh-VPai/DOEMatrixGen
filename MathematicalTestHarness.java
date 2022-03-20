/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import Calculus.MathsContxtLAv.*;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.UsageCalculus.EndTestCase;
import static Calculus.UsageCalculus.UserTestPredicate;
import static Calculus.UsageCalculus.mainRegressionIntegrTest;
import static Calculus.UsageCalculus.mainRegresssionDiffTest;
import static Calculus.UsageCalculus.mainRegresssionHghLvlExprODETest;
import static Calculus.UsageCalculus.mainRegresssionPredicate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class MathematicalTestHarness {

    public static Logger log = Logger.getLogger(MathematicalTestHarness.class.getName());

    static Map<String, Double> variables = new HashMap<>();

    public static String TestDiffWithRespTo = "x";
    public static boolean LowInitFlagRecordTime = false;
    public static double CompletionTime = 0.0;

    public static void main(String... args) {
        if (LowInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[1]);
            System.out.println("MathematicalTestHarness:Started:Run Started Time:" + current + " ");
        }
        String StartMyTestCase = "1000";//4022//4019//1003//80003//50001//1000;  //Test Case Number : Options :All or a Single Number
        String EndMyTestCase = "110023";//110023; //Test Case Number : Options :All or a Single Number
        int MaxDiffOrder = 5; // Maximum Differentiation Order (with Answers): 5 :Integration Order (with Answers): 5;
        double NumberOfTestCases = Double.parseDouble(EndMyTestCase) - Double.parseDouble(StartMyTestCase);

        variables.put(TestDiffWithRespTo, 0.0);
        variables.put("Index", 1.0);
        variables.put("StartMyTestCase", Double.parseDouble(StartMyTestCase));
        String Exprn = "Index*x+StartMyTestCase";
        System.out.println(" MathematicalTestHarness:TestHarness Equation=" + Exprn);

        Expression expValue;
        expValue = parse(Exprn, variables, TestDiffWithRespTo, "Silent", LogLevel.Off);
        StartMyTestCase = "" + Math.round(expValue.eval());
        System.out.println(" MathematicalTestHarness:Result(Expn Value:StartMyTestCase)=" + expValue.eval());
        variables.put(TestDiffWithRespTo, NumberOfTestCases);
        expValue = parse(Exprn, variables, TestDiffWithRespTo, "Silent", LogLevel.Off);
        EndMyTestCase = "" + Math.round(expValue.eval());
        System.out.println(" MathematicalTestHarness:Result(Expn Value:EndMyTestCase)=" + expValue.eval());

        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        //Options( MaxDiffOrder, StartMyTestCase, EndMyTestCase , TestCaseType);
        String xExprn = "Regression";
        System.out.print("Do you want to run Regression OR 1 Equation Demo(UserInteractive) OR HghLvlExprODE: [" + xExprn + "]:");
        Scanner scanner = new Scanner(System.in);
        String TempxExprn = xExprn.trim();
        TempxExprn = scanner.nextLine();
        if (TempxExprn.equalsIgnoreCase("")) {
            ;
        } else if (TempxExprn.equalsIgnoreCase("Regression")) {
            ;
        } else if ((TempxExprn.equalsIgnoreCase("Demo")) || (TempxExprn.equalsIgnoreCase("UserInteractive"))) {
            xExprn = "UserInteractive";
        } else if (TempxExprn.equalsIgnoreCase("HghLvlExprODE")) {
            xExprn = "HghLvlExprODE";
        }
        xExprn = xExprn.trim();
        xExprn = eatAll(xExprn, ' ');
        System.out.println("You Entered:" + xExprn);
        if (xExprn.equalsIgnoreCase("Regression")) {
            System.out.println(" MathematicalTestHarness:MaxDiffOrder=" + MaxDiffOrder);
            TestHarnessClass MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
            TestDiff(MaxDiffOrder, MyTestData);
            MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
            TestIntegr(MaxDiffOrder, MyTestData);
            //TestPredicateMisc();
        } else if (xExprn.equalsIgnoreCase("UserInteractive")) {
            MathsContxtLAv.xmain();
        }else if (xExprn.equalsIgnoreCase("HghLvlExprODE")) {
            TestHarnessClass MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
            mainRegresssionHghLvlExprODETest(MaxDiffOrder,MyTestData);
        }
        if (LowInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[1]) - CompletionTime;
            System.out.println("MathematicalTestHarness:Completed:Run Started Time:" + current + " " + " CompletionTime=" + CompletionTime + " minutes");
            LowInitFlagRecordTime = true;
        }
    }

    public static void mainMathPredicate(String... args) {
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
            System.out.println("mainMathPredicate: x=" + x + " y=" + y + " Exprn=" + Exprn);
            expValue = PredicateSep.parsePredicate(Exprn, variables, "Silent", MathsContxtLAv.LogLevel.Off);
            System.out.println(" mainMathPredicate:Result(Logical Value)=" + expValue.eval());
            System.out.print(System.lineSeparator());
        }
    }

    public static void TestDiff(int MaxDiffOrder, TestHarnessClass MyTestData) {
        mainRegresssionDiffTest(MaxDiffOrder, MyTestData);
        //PalinController( MaxDiffOrder, StartMyTestCase, EndMyTestCase, "Diff");
        //PalinControllerSame( MaxDiffOrder, StartMyTestCase, EndMyTestCase, "Diff");
        //Exprn = "x^80+x^45+x^251-x^3+25";
        //DiffLimitTestCase( 80, Exprn);    
    }

    public static void TestIntegr(int MaxDiffOrder, TestHarnessClass MyTestData) {
        mainRegressionIntegrTest(MaxDiffOrder, MyTestData);
        //PalinController( MaxDiffOrder, StartMyTestCase, EndMyTestCase, "Integral");
        //PalinControllerSame( MaxDiffOrder, StartMyTestCase, EndMyTestCase, "Integral");
        //Exprn = "x^80+x^45+x^251-x^3+25";
        //IntegralLimitTest( 80, Exprn);

    }

    public static void TestPredicateMisc() {
        String StartMyTestCase = "100";//100;  // Test Case Number : Options :All or a Single Number
        String EndMyTestCase = "227";//227; // Test Case Number : Options :All or a Single Number
        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        TestHarnessClass MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);

        StartMyTestCase = "100";
        EndTestCase = 227;
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        EndTestCase = 227;
        //mainMathPredicate(args);
        mainRegresssionPredicate(MyTestData);

        StartMyTestCase = "100";
        EndTestCase = 225;
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        EndTestCase = 225;
        //mainRegresssionExpr(MyTestData);
        StartMyTestCase = "100";
        EndTestCase = 225;
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        EndTestCase = 225;
        //mainRegresssionAlgebra(MyTestData);
        StartMyTestCase = "100";
        EndTestCase = 225;
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        EndTestCase = 225;
        //mainRegresssionSubs(MyTestData);
        StartMyTestCase = "102";
        EndTestCase = 102;
        MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
        EndTestCase = 102;
        //mainRegresssionFInverse(MyTestData);
    }

    public static void Options(int MaxDiffOrder, String StartMyTestCase, String EndMyTestCase, String TestCaseType) {
        String xExprn = "Regression";
        System.out.print("Do you want to run Regression OR 1 Equation Demo(UserInteractive): [" + xExprn + "]:");
        Scanner scanner = new Scanner(System.in);
        String TempxExprn = xExprn.trim();
        TempxExprn = scanner.nextLine();
        if (TempxExprn.equalsIgnoreCase("")) {
            ;
        } else if (TempxExprn.equalsIgnoreCase("Regression")) {
            ;
        } else if ((TempxExprn.equalsIgnoreCase("Demo")) || (TempxExprn.equalsIgnoreCase("UserInteractive"))) {
            xExprn = "UserInteractive";
        }
        xExprn = xExprn.trim();
        xExprn = eatAll(xExprn, ' ');
        System.out.println("You Entered:" + xExprn);
        if (xExprn.equalsIgnoreCase("Regression")) {
            TestHarnessClass MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
            TestDiff(MaxDiffOrder, MyTestData);
            MyTestData = new TestHarnessClass(14, StartMyTestCase, EndMyTestCase, TestCaseType);
            TestIntegr(MaxDiffOrder, MyTestData);
        } else if (xExprn.equalsIgnoreCase("UserInteractive")) {
            MathsContxtLAv.xmain();
        }
    }
}




