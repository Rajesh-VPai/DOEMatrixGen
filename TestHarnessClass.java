/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.ExclusionTestCases.DifferentialGapException;
import static Calculus.ExclusionTestCases.DifferentialGapInfinity;
import static Calculus.ExclusionTestCases.IntegralGapException;
import static Calculus.ExclusionTestCases.IntegralGapInfinity;
import Calculus.MathsContxtLAv.Expression;
import Calculus.MathsContxtLAv.LogLevel;
import static Calculus.MathsContxtLAv.parse;
import Calculus.UsageCalculus;
import static Calculus.UsageCalculus.DiffWithRespTo;
import static Calculus.UsageCalculus.MissedNumberTestCases;
import static Calculus.UsageCalculus.MissedTestCasesException;
import static Calculus.UsageCalculus.MissedTestCasesInfinity;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessClass {

    public static Logger log = Logger.getLogger(TestHarnessClass.class.getName());
    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumCompFractionDigits = 5;

    TestHarnessClass(int MaxOrder, String All, String MyEndTestCase, String RegrORIndivORLimit) {
        UsageCalculus.EqnSolArrayMap1 = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayDiffAlone = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayMap2 = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayMap3 = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayIntegrAlone = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayMap4 = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr = new String[MaxOrder + 1];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff = new String[MaxOrder + 1];

        UsageCalculus.EqnSolOrder = new int[MaxOrder + 1];
        for (int i = 0; i <= MaxOrder; i++) {
            UsageCalculus.EqnSolArrayMap1[i] = "";
            UsageCalculus.EqnSolArrayDiffAlone[i] = "";
            UsageCalculus.EqnSolArrayMap2[i] = "";
            UsageCalculus.EqnSolArrayMap3[i] = "";
            UsageCalculus.EqnSolArrayIntegrAlone[i] = "";
            UsageCalculus.EqnSolArrayMap4[i] = "";
            UsageCalculus.EqnSolArrayIntegr_BeforeDiff[i] = "";
            UsageCalculus.EqnSolArrayDiff_AfterIntegr[i] = "";
            UsageCalculus.EqnSolArrayDiff_BeforeIntegr[i] = "";
            UsageCalculus.EqnSolArrayIntegr_AfterDiff[i] = "";

            UsageCalculus.EqnSolOrder[i] = 0;
        }
        if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            UsageCalculus.TestCase = 100;
            UsageCalculus.TestCaseLoopTimes = UsageCalculus.LastTestCaseHarness;
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression"))) {
            UsageCalculus.TestCase = 100;
            //TestCaseLoopTimes = 80001;
            UsageCalculus.EndTestCase = Integer.parseInt(MyEndTestCase);
        } else if (RegrORIndivORLimit.equalsIgnoreCase("Individual")) {
            UsageCalculus.TestCase = Integer.parseInt(All);
            UsageCalculus.TestCaseLoopTimes = 1;
            UsageCalculus.EndTestCase = Integer.parseInt(MyEndTestCase);
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Individual"))) {
            System.out.println(" Wrong Input :All & Individual");
            UsageCalculus.TestCase = 100;
            UsageCalculus.TestCaseLoopTimes = 1;
            UsageCalculus.EndTestCase = Integer.parseInt(MyEndTestCase);
        } else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            UsageCalculus.TestCase = Integer.parseInt(All);
            UsageCalculus.TestCaseLoopTimes = UsageCalculus.LastTestCaseHarness;
        } else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression")) && (!MyEndTestCase.equalsIgnoreCase("-1"))) {
            UsageCalculus.TestCase = Integer.parseInt(All);
            //Usage.TestCaseLoopTimes = Integer.parseInt(MyEndTestCase)-UsageCalculus.TestCase+1;
            UsageCalculus.EndTestCase = Integer.parseInt(MyEndTestCase);
        }

    }

    public static String TestPredicate() {
        //String Exprn = "0 < = x < 5 "; 
        //String Exprn = "(0 < = x)&&(x < 5 )"; //A&&B
        //String Exprn = "(!(0 > x))&&((5 > x )))";//(A'||B')&&(A||B)
        String Exprn = "(x^2+y^2 >= 4)";//(A'||B')&&(A||B)
        return Exprn;
    }

    public static boolean PassFailTestCaseString(int myTestCase, int Order, String DiffORIntegre, String Exprn, String StrMap1, String StrDiff, String StrMap2, String Answer1, String Answer2, String Answer3, String Answer4) {
        boolean ReturnFlagMap1 = false;
        boolean ReturnFlagDiff = false;
        boolean ReturnFlagMap2 = false;
        int TestCase = UsageCalculus.TestCase;
        if (TestCase == myTestCase) {
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap1.equalsIgnoreCase(Answer2)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " StrMap1=" + StrMap1 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map1" + ConsoleColors.RESET);
                ReturnFlagMap1 = true;
            } //            else if ((Answer2 != null) && (!Answer2.equalsIgnoreCase("")) && ((StrMap1.contains(Answer2)))) {
            //                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST (Reduced) Map1" + ConsoleColors.RESET);
            //                ReturnFlag = true;
            //            } 
            else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL STRING TEST Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:StrMap1=" + StrMap1 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Answer2=" + Answer2 + ConsoleColors.RESET);
                ReturnFlagMap1 = false;
            }
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrDiff.equalsIgnoreCase(Answer3)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " StrDiff=" + StrDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass STRING TEST " + DiffORIntegre + ConsoleColors.RESET);
                ReturnFlagDiff = true;
            } //            else if ((Answer3 != null) && (!Answer3.equalsIgnoreCase("")) && ((StrDiff.contains(Answer3)))) {
            //                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST (Reduced) Diff" + ConsoleColors.RESET);
            //                ReturnFlag = true;
            //            } 
            else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass STRING TEST " + DiffORIntegre + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrDiff  =" + StrDiff + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrIntegr=" + StrDiff + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " Answer3  =" + Answer3 + ConsoleColors.RESET);
                ReturnFlagDiff = false;
            }
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap2.equalsIgnoreCase(Answer4)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " StrMap2=" + StrMap2 + ConsoleColors.RESET);
                ReturnFlagMap2 = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrMap2=" + StrMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " Answer4=" + Answer4 + ConsoleColors.RESET);
                ReturnFlagMap2 = false;
            }

            return ((ReturnFlagMap1) && (ReturnFlagDiff) && (ReturnFlagMap2));
        }
        return false;
    }

    public static boolean PassFailTestCaseValue(int myTestCase, int Order, String DiffORIntegre, String Exprn, Double ExprnexpValue, Double DblexpValueMap1, Double DblexpDiffValue, Double DblexpValueMap2, String StrexpDiffExpress, Map<String, Double> variables, Double x) {
        boolean ReturnFlag = false;
        Double Number = ExprnexpValue.doubleValue();
        Double NumberAnsMap2 = 0.0;
        Double NumberEqnSol = 0.0;
        Double NumberDiff = 0.0;

        NumberFormat formatter = new DecimalFormat();
        Expression expValueExprn;
        String DblexpValueAnswerExprn = "";
        String DblexpValueAnswerExprnIntegrity = "";
        String TestAnsVal0Exprn = "";
        String TestAnsVal5Diff = "";
        String TestAnsVal6Map2 = "";
        String TestAnsVal7 = "";
        variables.put(DiffWithRespTo, x);
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap1[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprn = "" + expValueExprn.eval();
            Number = expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0Exprn = formatter.format(Number);
        } else {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap3[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprn = "" + expValueExprn.eval();
            Number = expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0Exprn = formatter.format(Number);
        }
        Expression expValueMap2;
        Expression expValueDiffExprn;
        String DblexpValueAnswer3 = "";
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueMap2 = parse(UsageCalculus.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswer3 = "" + expValueMap2.eval();
            Number = expValueMap2.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5Diff = formatter.format(Number);
        } else {
            expValueMap2 = parse(UsageCalculus.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswer3 = "" + expValueMap2.eval();
            Number = expValueMap2.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5Diff = formatter.format(Number);
        }

        //Answer System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap2[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprnIntegrity = "" + expValueExprn.eval();
            NumberAnsMap2 = expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6Map2 = formatter.format(NumberAnsMap2);
        } else {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap4[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprnIntegrity = "" + expValueExprn.eval();
            NumberAnsMap2 = expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6Map2 = formatter.format(NumberAnsMap2);
            System.out.println("PassFailTestCaseValue:NumberAnsMap2=" + NumberAnsMap2 + " TestAnsVal6Map2=" + TestAnsVal6Map2);
        }
        //Diff System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueDiffExprn = parse(UsageCalculus.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprnIntegrity = "" + expValueDiffExprn.eval();
            NumberEqnSol = expValueDiffExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7 = formatter.format(NumberEqnSol);
        } else {
            expValueDiffExprn = parse(UsageCalculus.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            DblexpValueAnswerExprnIntegrity = "" + expValueDiffExprn.eval();
            NumberEqnSol = expValueDiffExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7 = formatter.format(NumberEqnSol);
            System.out.println("PassFailTestCaseValue:NumberEqnSol=" + NumberEqnSol + " TestAnsVal7=" + TestAnsVal7);
        }
        Number = ExprnexpValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal1 = formatter.format(Number);
        Number = DblexpValueMap1.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal2 = formatter.format(Number);

        NumberDiff = DblexpDiffValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String DiffVal3 = formatter.format(NumberDiff);
        Number = DblexpValueMap2.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String SimpleVal4 = formatter.format(Number);
        String Temp = "";
        int TestCase = UsageCalculus.TestCase;
        if (TestCase == myTestCase) {
            //Value Check with Exprn & Map1
            if (TestVal1.equalsIgnoreCase(TestVal2)
                    || ((NumberAnsMap2.doubleValue()==NumberEqnSol.doubleValue()) 
                    && TestAnsVal6Map2.equalsIgnoreCase(TestAnsVal7))) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " " + DiffWithRespTo + "=" + variables.get(DiffWithRespTo) + " Status=" + "Pass VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 + " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " " + DiffWithRespTo + "=" + variables.get(DiffWithRespTo) + " Status=" + "FAIL VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 + " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Exprn & Answer(Map1)
            if ((TestVal1.equalsIgnoreCase(TestAnsVal0Exprn))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((TestVal1.equalsIgnoreCase("0.0")) || (TestVal1.equalsIgnoreCase("-0.0")) || (TestVal1.equalsIgnoreCase("+0.0"))
                            || (TestVal1.equalsIgnoreCase("-0")) || (TestVal1.equalsIgnoreCase("+0")))
                            && ((TestAnsVal0Exprn.equalsIgnoreCase("0.0")) || (TestAnsVal0Exprn.equalsIgnoreCase("0")) || (TestAnsVal0Exprn.equalsIgnoreCase("-0.0")) || (TestAnsVal0Exprn.equalsIgnoreCase("+0.0"))
                            || (TestAnsVal0Exprn.equalsIgnoreCase("-0")) || (TestAnsVal0Exprn.equalsIgnoreCase("+0"))))
                    || ( (NumberAnsMap2.doubleValue()==NumberEqnSol.doubleValue())
                    ) ) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Map2
            if ((DiffVal3.equalsIgnoreCase(SimpleVal4))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0"))))) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST " + DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Diff Answer(Map2)
            if ((DiffVal3.equalsIgnoreCase(TestAnsVal5Diff))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((TestAnsVal5Diff.equalsIgnoreCase("0.0")) || (TestAnsVal5Diff.equalsIgnoreCase("0")) || (TestAnsVal5Diff.equalsIgnoreCase("-0.0")) || (TestAnsVal5Diff.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal5Diff.equalsIgnoreCase("-0")) || (TestAnsVal5Diff.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }//Value (Answer) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal6Map2.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal6Map2.equalsIgnoreCase(SimpleVal4))
                    && ((DblexpValueMap2.doubleValue() == expValueExprn.eval())
                    && ((DblexpValueMap2.doubleValue() == NumberAnsMap2)
                    && (DblexpDiffValue.doubleValue() == NumberDiff)))))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal6Map2.equalsIgnoreCase("0.0")) || (TestAnsVal6Map2.equalsIgnoreCase("0")) || (TestAnsVal6Map2.equalsIgnoreCase("-0.0")) || (TestAnsVal6Map2.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal6Map2.equalsIgnoreCase("-0")) || (TestAnsVal6Map2.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else if ((DiffVal3.equalsIgnoreCase(SimpleVal4))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal6Map2.equalsIgnoreCase("0.0")) || (TestAnsVal6Map2.equalsIgnoreCase("0")) || (TestAnsVal6Map2.equalsIgnoreCase("-0.0")) || (TestAnsVal6Map2.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal6Map2.equalsIgnoreCase("-0")) || (TestAnsVal6Map2.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass REDUCED VALUE TEST Answer Integrity Check " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass REDUCED VALUE TEST Answer Integrity Check " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                System.out.println("PassFailTestCaseValue:NumberAnsMap2=" + NumberAnsMap2 + " TestAnsVal6Map2=" + TestAnsVal6Map2);
                System.out.println("PassFailTestCaseValue:NumberEqnSol=" + NumberEqnSol + " TestAnsVal7=" + TestAnsVal7);
                ReturnFlag = false;
            }
            //Value (Answer-Diff System) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal7.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal7.equalsIgnoreCase(SimpleVal4))
                    && ((DblexpValueMap2.doubleValue() == expValueExprn.eval())
                    && ((DblexpValueMap2.doubleValue() == NumberEqnSol)
                    && (DblexpDiffValue.doubleValue() == NumberEqnSol)))))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal7.equalsIgnoreCase("0.0")) || (TestAnsVal7.equalsIgnoreCase("0")) || (TestAnsVal7.equalsIgnoreCase("-0.0")) || (TestAnsVal7.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal7.equalsIgnoreCase("-0")) || (TestAnsVal7.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + " TestAnsVal7=" + TestAnsVal7 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else if (((TestAnsVal7.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal7.equalsIgnoreCase(SimpleVal4))))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal7.equalsIgnoreCase("0.0")) || (TestAnsVal7.equalsIgnoreCase("0")) || (TestAnsVal7.equalsIgnoreCase("-0.0")) || (TestAnsVal7.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal7.equalsIgnoreCase("-0")) || (TestAnsVal7.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass REDUCED VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass REDUCED VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + " TestAnsVal7=" + TestAnsVal7 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal5Diff=" + TestAnsVal5Diff + " TestAnsVal6Map2=" + TestAnsVal6Map2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6Map2=" + TestAnsVal6Map2 + " TestAnsVal7=" + TestAnsVal7 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                System.out.println("PassFailTestCaseValue:NumberAnsMap2=" + NumberAnsMap2 + " TestAnsVal6Map2=" + TestAnsVal6Map2);
                System.out.println("PassFailTestCaseValue:NumberEqnSol=" + NumberEqnSol + " TestAnsVal7=" + TestAnsVal7);
                ReturnFlag = false;
            }
            return ReturnFlag;
        }
        return false;
    }

    public static boolean PassFailTestCaseStringPalinSame(int myTestCase, int Order, String DiffORIntegre, String Exprn, int Operator, String StrMap1, String StrDiff, String StrMap2, String Answer1, String Answer2, String Answer3, String Answer4) {
        boolean ReturnFlagMap1 = false;
        boolean ReturnFlagDiff = false;
        boolean ReturnFlagMap2 = false;
        int TestCase = UsageCalculus.TestCase;
        if (TestCase == myTestCase) {
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap1.equalsIgnoreCase(Answer2 + (char) Operator + Answer2)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " StrMap1=" + StrMap1 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map1" + ConsoleColors.RESET);
                ReturnFlagMap1 = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Exprn=" + Exprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL STRING TEST Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:StrMap1=" + StrMap1 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Answer2=" + (Answer2 + (char) Operator + Answer2) + ConsoleColors.RESET);
                ReturnFlagMap1 = false;
            }
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrDiff.equalsIgnoreCase(Answer3 + (char) Operator + Answer3)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " StrDiff=" + StrDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass STRING TEST " + DiffORIntegre + ConsoleColors.RESET);
                ReturnFlagDiff = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass STRING TEST " + DiffORIntegre + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrDiff  =" + StrDiff + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrIntegr=" + StrDiff + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " Answer3  =" + (Answer3 + (char) Operator + Answer3) + ConsoleColors.RESET);
                ReturnFlagDiff = false;
            }
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap2.equalsIgnoreCase(Answer4 + (char) Operator + Answer4)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " StrMap2=" + StrMap2 + ConsoleColors.RESET);
                ReturnFlagMap2 = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " StrMap2=" + StrMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Test Case " + TestCase + " Answer4=" + (Answer4 + (char) Operator + Answer4) + ConsoleColors.RESET);
                ReturnFlagMap2 = false;
            }

            return ((ReturnFlagMap1) && (ReturnFlagDiff) && (ReturnFlagMap2));
        }
        return false;
    }

    public static boolean PassFailTestCaseValuePalinSame(int myTestCase, int Order, String DiffORIntegre, String Exprn, int Operator, Double ExprnexpValue, Double DblexpValueMap1, Double DblexpDiffValue, Double DblexpValueMap2, Map<String, Double> variables) {
        boolean ReturnFlag = false;
        Double Number = ExprnexpValue.doubleValue();
        Double NumberAnsMap2 = 0.0;
        Double NumberEqnSol = 0.0;
        Double NumberDiff = 0.0;

        NumberFormat formatter = new DecimalFormat();
        Expression expValueExprn;
        String DblexpValueAnswerExprn = "";
        String DblexpValueAnswerExprnIntegrity = "";
        String TestAnsVal0 = "";
        String TestAnsVal5 = "";
        String TestAnsVal6 = "";
        String TestAnsVal7 = "";

        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap1[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprn = "" + (expValueExprn.eval() + expValueExprn.eval());
            } else {
                DblexpValueAnswerExprn = "" + (expValueExprn.eval() - expValueExprn.eval());
            }
            Number = (expValueExprn.eval() + expValueExprn.eval());
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0 = formatter.format(Number);
        } else {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap3[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprn = "" + (expValueExprn.eval() + expValueExprn.eval());
            } else {
                DblexpValueAnswerExprn = "" + (expValueExprn.eval() - expValueExprn.eval());
            }
            Number = Double.parseDouble(DblexpValueAnswerExprn);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0 = formatter.format(Number);
        }
        Expression expValueMap2;
        Expression expValueDiffExprn;
        String DblexpValueAnswer3 = "";
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueMap2 = parse(UsageCalculus.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswer3 = "" + (expValueMap2.eval() + expValueMap2.eval());
            } else {
                DblexpValueAnswer3 = "" + (expValueMap2.eval() - expValueMap2.eval());
            }
            Number = Double.parseDouble(DblexpValueAnswer3);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5 = formatter.format(Number);
        } else {
            expValueMap2 = parse(UsageCalculus.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswer3 = "" + (expValueMap2.eval() + expValueMap2.eval());
            } else {
                DblexpValueAnswer3 = "" + (expValueMap2.eval() - expValueMap2.eval());
            }
            Number = Double.parseDouble(DblexpValueAnswer3);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5 = formatter.format(Number);
        }

        //Answer System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap2[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprnIntegrity = "" + (expValueExprn.eval() + expValueExprn.eval());
            } else {
                DblexpValueAnswerExprnIntegrity = "" + (expValueExprn.eval() - expValueExprn.eval());
            }
            NumberAnsMap2 = Double.parseDouble(DblexpValueAnswerExprnIntegrity);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6 = formatter.format(NumberAnsMap2);
        } else {
            expValueExprn = parse(UsageCalculus.EqnSolArrayMap4[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprnIntegrity = "" + (expValueExprn.eval() + expValueExprn.eval());
            } else {
                DblexpValueAnswerExprnIntegrity = "" + (expValueExprn.eval() - expValueExprn.eval());
            }
            NumberAnsMap2 = Double.parseDouble(DblexpValueAnswerExprnIntegrity);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6 = formatter.format(NumberAnsMap2);
        }
        //Diff System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueDiffExprn = parse(UsageCalculus.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprnIntegrity = "" + (expValueDiffExprn.eval() + expValueDiffExprn.eval());
            } else {
                DblexpValueAnswerExprnIntegrity = "" + (expValueDiffExprn.eval() - expValueDiffExprn.eval());
            }
            NumberEqnSol = Double.parseDouble(DblexpValueAnswerExprnIntegrity);;
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7 = formatter.format(NumberEqnSol);
        } else {
            expValueDiffExprn = parse(UsageCalculus.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo, "Silent", LogLevel.Off);
            if (Operator == '+') {
                DblexpValueAnswerExprnIntegrity = "" + (expValueDiffExprn.eval() + expValueDiffExprn.eval());
            } else {
                DblexpValueAnswerExprnIntegrity = "" + (expValueDiffExprn.eval() - expValueDiffExprn.eval());
            }
            NumberEqnSol = Double.parseDouble(DblexpValueAnswerExprnIntegrity);
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7 = formatter.format(NumberEqnSol);
        }
        Number = ExprnexpValue.doubleValue() + ExprnexpValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal1 = formatter.format(Number);
        Number = DblexpValueMap1.doubleValue() + DblexpValueMap1.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal2 = formatter.format(Number);

        NumberDiff = DblexpDiffValue.doubleValue() + DblexpDiffValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String DiffVal3 = formatter.format(NumberDiff);
        Number = DblexpValueMap2.doubleValue() + DblexpValueMap2.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String SimpleVal4 = formatter.format(Number);
        String Temp = "";
        int TestCase = UsageCalculus.TestCase;
        if (TestCase == myTestCase) {
            //Value Check with Exprn & Map1
            if (TestVal1.equalsIgnoreCase(TestVal2)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " " + DiffWithRespTo + "=" + variables.get(DiffWithRespTo) + " Status=" + "Pass VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 + " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " " + DiffWithRespTo + "=" + variables.get(DiffWithRespTo) + " Status=" + "FAIL VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 + " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Exprn & Answer(Map1)
            if ((TestVal1.equalsIgnoreCase(TestAnsVal0))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((TestVal1.equalsIgnoreCase("0.0")) || (TestVal1.equalsIgnoreCase("-0.0")) || (TestVal1.equalsIgnoreCase("+0.0"))
                    || (TestVal1.equalsIgnoreCase("-0")) || (TestVal1.equalsIgnoreCase("+0")))
                    && ((TestAnsVal0.equalsIgnoreCase("0.0")) || (TestAnsVal0.equalsIgnoreCase("0")) || (TestAnsVal0.equalsIgnoreCase("-0.0")) || (TestAnsVal0.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal0.equalsIgnoreCase("-0")) || (TestAnsVal0.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Map2
            if ((DiffVal3.equalsIgnoreCase(SimpleVal4))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0"))))) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST " + DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Diff Answer(Map2)
            if ((DiffVal3.equalsIgnoreCase(TestAnsVal5))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((TestAnsVal5.equalsIgnoreCase("0.0")) || (TestAnsVal5.equalsIgnoreCase("0")) || (TestAnsVal5.equalsIgnoreCase("-0.0")) || (TestAnsVal5.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal5.equalsIgnoreCase("-0")) || (TestAnsVal5.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST " + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }//Value (Answer) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal6.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal6.equalsIgnoreCase(SimpleVal4))
                    && ((DblexpValueMap2.doubleValue() == expValueExprn.eval())
                    && ((DblexpValueMap2.doubleValue() == NumberAnsMap2)
                    && (DblexpDiffValue.doubleValue() == NumberDiff)))))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal6.equalsIgnoreCase("0.0")) || (TestAnsVal6.equalsIgnoreCase("0")) || (TestAnsVal6.equalsIgnoreCase("-0.0")) || (TestAnsVal6.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal6.equalsIgnoreCase("-0")) || (TestAnsVal6.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value (Answer-Diff System) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal7.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal7.equalsIgnoreCase(SimpleVal4))
                    && ((DblexpValueMap2.doubleValue() == expValueExprn.eval())
                    && ((DblexpValueMap2.doubleValue() == NumberEqnSol)
                    && (DblexpDiffValue.doubleValue() == NumberEqnSol)))))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                    || (DiffVal3.equalsIgnoreCase("0")) || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                    || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0")))
                    && ((TestAnsVal7.equalsIgnoreCase("0.0")) || (TestAnsVal7.equalsIgnoreCase("0")) || (TestAnsVal7.equalsIgnoreCase("-0.0")) || (TestAnsVal7.equalsIgnoreCase("+0.0"))
                    || (TestAnsVal7.equalsIgnoreCase("-0")) || (TestAnsVal7.equalsIgnoreCase("+0"))))) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + " TestAnsVal7=" + TestAnsVal7 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);

                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check" + DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6=" + TestAnsVal6 + " TestAnsVal7=" + TestAnsVal7 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + UsageCalculus.EqnSolArrayDiffAlone[Order]);
                } else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + UsageCalculus.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            return ReturnFlag;
        }
        return false;
    }

    public static boolean ConformalMapTestCaseDiff(int myTestCase, int Order, String Exprn, MathsContxtLAv.SimpleExpression expSimpleMap1, Calculus.DiffrIntegrSep.DiffExpr exp, MathsContxtLAv.SimpleExpression expSimpleMap2, String AnsMap1, String DiffAlone, String AnsMap2, Map<String, Double> variables, String DiffWithRespTo) {
        boolean ReturnFlag = false;
        boolean failFlag = false;
        boolean PassFlag = false;
        int ControlInt=0;
        Expression expValue;
        Double[] DblexpValueOrg = new Double[8];
        Double[] DblexpValueMap1 = new Double[8];
        //String F(x) -> String F(y)
        String StrExprn = Exprn;
        String StrConMap1 = expSimpleMap1.SimpleExpr();
        String StrDiff = exp.DiffExpr();
        String StrConMap2 = expSimpleMap2.SimpleExpr();
        //F(x) -> F(y)
        String MappedExprn = Exprn.replaceAll(DiffWithRespTo, "y");
        String MappedConMap1 = StrConMap1.replaceAll(DiffWithRespTo, "y");
        String MappedDiff = StrDiff.replaceAll(DiffWithRespTo, "y");
        String MappedConMap2 = StrConMap2.replaceAll(DiffWithRespTo, "y");

        // Compare Value of Exprn with Mapped Exprn
        Double DELTA = 0.000001;
        Double valBase = -3.0;
        Double val = valBase;
        ControlInt=1;
        for (int i = 0; i <= 7; i++) {
            switch (i) {
                case 0:
                    val = -3.0;
                    break;
                case 1:
                    valBase++;
                    break;
                case 2:
                    valBase++;
                    break;
                case 3:
                    valBase++;
                    break;
                case 4:
                    valBase++;
                    break;
                case 5:
                    valBase++;
                    break;
                case 6:
                    valBase++;
                    break;
                case 7:
                    valBase++;
                    break;
            }
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        val = valBase;
                        break;
                    case 1:
                        val = valBase - DELTA;
                        break;
                    case 2:
                        val = valBase + DELTA;
                        break;
                }
                variables.put(DiffWithRespTo, val * 1.0);
                expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                DblexpValueOrg[i] = expValue.eval();

                variables.put("y", val * 1.0);
                expValue = parse(MappedExprn, variables, "y", "Silent", LogLevel.Off);
                DblexpValueMap1[i] = expValue.eval();
                if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                        && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " Exprn=" + Exprn + " val=" + val + " x=" + variables.get("x") + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " MappedExprn=" + MappedExprn + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                    failFlag = true;
                    break;
                } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                        && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                    PassFlag = true;
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of Exprn with Mapped expSimpleMap1.SimpleExpr():Infer expSimpleMap1 Answer is RIGHT
            valBase = -3.0;
            val = valBase;
            ControlInt=2;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(StrConMap1.replaceAll(DiffWithRespTo, "y"), variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    
                    Double Number = 0.0;
                    Double NumberDiff = 0.0;
                    NumberFormat formatter = new DecimalFormat();
                    Number = expValue.eval().doubleValue();
                    formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
                    String DiffVal = formatter.format(Number);
                    Number = expValue.eval().doubleValue();
                    formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
                    String Simple = formatter.format(Number);
                    
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())
                            && (!DiffVal.equalsIgnoreCase(Simple))) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " Exprn=" + Exprn + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " expSimpleMap1=" + StrConMap1.replaceAll(DiffWithRespTo, "y") + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " DiffVal=" + DiffVal + " Simple=" + Simple + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of expSimpleMap1 with Mapped MappedConMap1
            valBase = -3.0;
            val = valBase;
            ControlInt=3;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(expSimpleMap1.SimpleExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);;
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap1, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "Simple Exprn Map1" + " expSimpleMap1.SimpleExpr()=" + expSimpleMap1.SimpleExpr() + " val=" + val + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "Simple Exprn Map1" + " MappedConMap1=" + MappedConMap1 + " val=" + val + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "Simple Exprn Map1" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of Diff exp.DiffExpr() with Mapped MappedDiff
            valBase = -3.0;
            val = valBase;
            ControlInt=4;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(exp.DiffExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedDiff, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Diffr MappedDiff" + " exp.DiffExpr()=" + exp.DiffExpr() + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Diffr MappedDiff" + " MappedDiff=" + MappedDiff + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Diffr MappedDiff" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of expSimpleMap2 with Mapped MappedConMap2
            valBase = -3.0;
            val = valBase;
            ControlInt=5;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(expSimpleMap2.SimpleExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap2, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Map2 MappedConMap2" + " expSimpleMap2.SimpleExpr()=" + expSimpleMap2.SimpleExpr() + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Map2 MappedConMap2" + " MappedConMap2=" + MappedConMap2 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Map2 MappedConMap2" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of AnsMap1 with Mapped expSimpleMap1
            valBase = -3.0;
            val = valBase;
            ControlInt=6;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(AnsMap1, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap1, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans1 Map1  " + " AnsMap1=" + AnsMap1 + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans1 Map1  " + " MappedConMap1=" + MappedConMap1 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans1 Map1  " + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of DiffAlone with Mapped exp.DiffExpr():Infer Diff Answer is RIGHT
            valBase = -3.0;
            val = valBase;
            ControlInt=7;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(DiffAlone, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(StrDiff.replaceAll(DiffWithRespTo, "y"), variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    
                    Double Number = 0.0;
                    Double NumberDiff = 0.0;
                    NumberFormat formatter = new DecimalFormat();
                    Number = expValue.eval().doubleValue();
                    formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
                    String DiffVal = formatter.format(Number);
                    Number = expValue.eval().doubleValue();
                    formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
                    String Simple = formatter.format(Number);
                    
                    if ( (!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())
                            && (!DiffVal.equalsIgnoreCase(Simple))
                            ) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone Diff" + " DiffAlone=" + DiffAlone + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone Diff" + " StrDiff.replaceAll()=" + StrDiff.replaceAll(DiffWithRespTo, "y") + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone Diff" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " DiffVal=" + DiffVal + " Simple=" + Simple + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of DiffAlone with Mapped exp
            valBase = -3.0;
            val = valBase;
            ControlInt=8;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(DiffAlone, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedDiff, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone MappedDiff" + " DiffAlone=" + DiffAlone + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone MappedDiff" + " MappedDiff=" + MappedDiff + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal DiffAlone MappedDiff" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of AnsMap2 with Mapped expSimpleMap2
            valBase = -3.0;
            val = valBase;
            ControlInt=9;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(AnsMap2, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();
                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap2, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans2 MappedConMap2" + " AnsMap2=" + AnsMap2 + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans2 MappedConMap2" + " MappedConMap2=" + MappedConMap2 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans2 MappedConMap2" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        ReturnFlag = (!failFlag);
        return ReturnFlag;
    }

    public static boolean ConformalMapTestCaseIntegr(int myTestCase, int Order, String Exprn, MathsContxtLAv.SimpleExpression expSimpleMap1, Calculus.DiffrIntegrSep.IntegrExpr exp, MathsContxtLAv.SimpleExpression expSimpleMap2, String AnsMap3, String IntegrAlone, String AnsMap4, Map<String, Double> variables, String DiffWithRespTo) {
        boolean ReturnFlag = false;
        boolean failFlag = false;
        boolean PassFlag = false;
        int ControlInt=0;
        Expression expValue;
        Double[] DblexpValueOrg = new Double[8];
        Double[] DblexpValueMap1 = new Double[8];
        //String F(x) -> String F(y)
        String StrExprn = Exprn;
        String StrConMap1 = expSimpleMap1.SimpleExpr();
        String StrIntegr = exp.IntegrExpr();
        String StrConMap2 = expSimpleMap2.SimpleExpr();
        //F(x) -> F(y)
        String MappedExprn = Exprn.replaceAll(DiffWithRespTo, "y");
        String MappedConMap1 = StrConMap1.replaceAll(DiffWithRespTo, "y");
        String MappedIntegr = StrIntegr.replaceAll(DiffWithRespTo, "y");
        String MappedConMap2 = StrConMap2.replaceAll(DiffWithRespTo, "y");
        // Compare Value of Exprn with Mapped Exprn
        //Double DELTA = 0.0000000001;
        Double DELTA = 0.000001;
        Double valBase = -3.0;
        Double val = valBase;
        ControlInt=1;
        for (int i = 0; i <= 7; i++) {
            switch (i) {
                case 0:
                    val = valBase;
                    break;
                case 1:
                    valBase++;
                    break;
                case 2:
                    valBase++;
                    break;
                case 3:
                    valBase++;
                    break;
                case 4:
                    valBase++;
                    break;
                case 5:
                    valBase++;
                    break;
                case 6:
                    valBase++;
                    break;
                case 7:
                    valBase++;
                    break;
            }
            for (int j = 0; j < 3; j++) {
                switch (j) {
                    case 0:
                        val = valBase;
                        break;
                    case 1:
                        val = valBase - DELTA;
                        break;
                    case 2:
                        val = valBase + DELTA;
                        break;
                }
                variables.put(DiffWithRespTo, val * 1.0);
                expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                DblexpValueOrg[i] = expValue.eval();
                variables.put("y", val * 1.0);
                expValue = parse(MappedExprn, variables, "y", "Silent", LogLevel.Off);
                DblexpValueMap1[i] = expValue.eval();
                if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                        && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " Exprn=" + Exprn + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " MappedExprn=" + MappedExprn + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn MappedExprn" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                    failFlag = true;
                    break;
                } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                        && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                    PassFlag = true;
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of Exprn with Mapped expSimpleMap1.SimpleExpr():Infer expSimpleMap1 Answer is RIGHT
            valBase = -3.0;
            val = valBase;
            ControlInt=2;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(Exprn, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(StrConMap1.replaceAll(DiffWithRespTo, "y"), variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " Exprn=" + Exprn + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " StrConMap1.replaceAll()=" + StrConMap1.replaceAll(DiffWithRespTo, "y") + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Exprn expSimpleMap1" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of expSimpleMap1 with Mapped MappedConMap1
            valBase = -3.0;
            val = valBase;
            ControlInt=3;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(expSimpleMap1.SimpleExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap1, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn Map1 MappedConMap1" + " expSimpleMap1.SimpleExpr()=" + expSimpleMap1.SimpleExpr() + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn Map1 MappedConMap1" + " MappedConMap1=" + MappedConMap1 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn Map1 MappedConMap1" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of Integr exp.IntegrExpr() with Mapped MappedIntegr
            valBase = -3.0;
            val = valBase;
            ControlInt=4;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(exp.IntegrExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedIntegr, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Integration MappedIntegr" + " exp.IntegrExpr()=" + exp.IntegrExpr() + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Integration MappedIntegr" + " MappedIntegr=" + MappedIntegr + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Integration MappedIntegr" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of expSimpleMap2 with Mapped MappedConMap2
            valBase = -3.0;
            val = valBase;
            ControlInt=5;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(expSimpleMap2.SimpleExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap2, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn expSimpleMap2 MappedConMap2" + " expSimpleMap2.SimpleExpr()=" + expSimpleMap2.SimpleExpr() + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn expSimpleMap2 MappedConMap2" + " MappedConMap2=" + MappedConMap2 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Simple Exprn expSimpleMap2 MappedConMap2" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of AnsMap3 with Mapped expSimpleMap1
            valBase = -3.0;
            val = valBase;
            ControlInt=6;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(AnsMap3, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap1, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal AnsMap3 MappedConMap1 " + " AnsMap3=" + AnsMap3 + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal AnsMap3 MappedConMap1 " + " MappedConMap1=" + MappedConMap1 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal AnsMap3 MappedConMap1 " + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of IntegrAlone with Mapped exp
            valBase = -3.0;
            val = valBase;
            ControlInt=7;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(IntegrAlone, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedIntegr, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone MappedIntegr" + " IntegrAlone=" + IntegrAlone + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone MappedIntegr" + " MappedIntegr=" + MappedIntegr + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone MappedIntegr" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of IntegrAlone with Mapped exp.IntegrExpr():Infer Integr Answer is RIGHT
            valBase = -3.0;
            val = valBase;
            ControlInt=8;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(IntegrAlone, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    expValue = parse(exp.IntegrExpr(), variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();
                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone exp.IntegrExpr()" + " IntegrAlone=" + IntegrAlone + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone exp.IntegrExpr()" + " exp.IntegrExpr()=" + exp.IntegrExpr() + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal IntegrAlone exp.IntegrExpr()" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        if ((PassFlag == true) && (failFlag == false)) {
            // Compare Value of AnsMap2 with Mapped expSimpleMap2
            valBase = -3.0;
            val = valBase;
            ControlInt=9;
            for (int i = 0; i <= 7; i++) {
                switch (i) {
                    case 0:
                        val = -3.0;
                        break;
                    case 1:
                        valBase++;
                        break;
                    case 2:
                        valBase++;
                        break;
                    case 3:
                        valBase++;
                        break;
                    case 4:
                        valBase++;
                        break;
                    case 5:
                        valBase++;
                        break;
                    case 6:
                        valBase++;
                        break;
                    case 7:
                        valBase++;
                        break;
                }
                for (int j = 0; j < 3; j++) {
                    switch (j) {
                        case 0:
                            val = valBase;
                            break;
                        case 1:
                            val = valBase - DELTA;
                            break;
                        case 2:
                            val = valBase + DELTA;
                            break;
                    }
                    variables.put(DiffWithRespTo, val * 1.0);
                    expValue = parse(AnsMap4, variables, DiffWithRespTo, "Silent", LogLevel.Off);
                    DblexpValueOrg[i] = expValue.eval();

                    variables.put("y", val * 1.0);
                    expValue = parse(MappedConMap2, variables, "y", "Silent", LogLevel.Off);
                    DblexpValueMap1[i] = expValue.eval();

                    if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() != DblexpValueMap1[i].doubleValue())) {
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans4 MappedConMap2" + " AnsMap4=" + AnsMap4 + " val=" + val + " x=" + variables.get(DiffWithRespTo) + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans4 MappedConMap2" + " MappedConMap2=" + MappedConMap2 + " val=" + val + " y=" + variables.get("y") + ConsoleColors.RESET);
                        System.out.println(ConsoleColors.RED + "ControlInt=" + ControlInt + " Conformal Ans4 MappedConMap2" + " DblexpValueOrg[i]=" + DblexpValueOrg[i].doubleValue() + " DblexpValueMap1[i]=" + DblexpValueMap1[i].doubleValue() + " val=" + val + ConsoleColors.RESET);
                        failFlag = true;
                        break;
                    } else if ((!DblexpValueOrg[i].isInfinite()) && (!DblexpValueOrg[i].isNaN())
                            && (DblexpValueOrg[i].doubleValue() == DblexpValueMap1[i].doubleValue())) {
                        PassFlag = true;
                    }
                }
            }
        }
        ReturnFlag = (!failFlag);
        return ReturnFlag;
    }

    //Sanity Test Cases
    public static void SanityTestCases(String AlgoName, int j) {
        if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapInfinity(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapInfinity(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED + "Infinity SanityTestCases TestCase:j= " + j + ": Infinite Loop GAP" + ConsoleColors.RESET);
        } else if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapException(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapException(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED + "Exception SanityTestCases TestCase:j= " + j + ": Exception GAP" + ConsoleColors.RESET);
        } else {
            switch (j) {
                case 4000:
                    TestCaseDiff4000();
                    break;
                case 4001:
                    TestCaseDiff4001();
                    break;
                case 4002:
                    TestCaseDiff4002();
                    break;
                case 4003:
                    TestCaseDiff4003();
                    break;
                case 4004:
                    TestCaseDiff4004();
                    break;
                case 4005:
                    TestCaseDiff4005();
                    break;
                case 4006:
                    TestCaseDiff4006();
                    break;
                case 4007:
                    TestCaseDiff4007();
                    break;
                case 4008:
                    TestCaseDiff4008();
                    break;
                case 4009:
                    TestCaseDiff4009();
                    break;
                case 4010:
                    TestCaseDiff4010();
                    break;
                case 4011:
                    TestCaseDiff4011();
                    break;
                case 4012:
                    TestCaseDiff4012();
                    break;
                case 4013:
                    TestCaseDiff4013();
                    break;
                case 4014:
                    TestCaseDiff4014();
                    break;
                case 4015:
                    TestCaseDiff4015();
                    break;
                case 4016:
                    TestCaseDiff4016();
                    break;
                case 4017:
                    TestCaseDiff4017();
                    break;
                case 4018:
                    TestCaseDiff4018();
                    break;
                case 4019:
                    TestCaseDiff4019();
                    break;
                case 4020:
                    TestCaseDiff4020();
                    break;
                case 4021:
                    TestCaseDiff4021();
                    break;
                case 4022:
                    TestCaseDiff4022();
                    break;
                case 4023:
                    TestCaseDiff4023();
                    break;
                case 4024:
                    TestCaseDiff4024();
                    break;
                case 4025:
                    TestCaseDiff4025();
                    break;
                case 4026:
                    TestCaseDiff4026();
                    break;
                case 4027:
                    TestCaseDiff4027();
                    break;
                case 4028:
                    TestCaseDiff4028();
                    break;
                case 4029:
                    TestCaseDiff4029();
                    break;
                case 4030:
                    TestCaseDiff4030();
                    break;
                case 4031:
                    TestCaseDiff4031();
                    break;
                case 4032:
                    TestCaseDiff4032();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Names TestCase:j= " + j + ": Gap" + ConsoleColors.RESET);
                    break;
            }
        }
    }

    //Exponent Test Cases
    public static void Exponent(String AlgoName, int j) {
        if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapInfinity(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapInfinity(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Infinite Loop GAP" + ConsoleColors.RESET);
        } else if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapException(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapException(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Exception GAP" + ConsoleColors.RESET);
        } else {
            switch (j) {
                case 30001:
                    TestCaseDiff30001();
                    break;
                case 30002:
                    TestCaseDiff30002();
                    break;
                case 30003:
                    TestCaseDiff30003();
                    break;
                case 30004:
                    TestCaseDiff30004();
                    break;
                case 30005:
                    TestCaseDiff30005();
                    break;
                case 30006:
                    TestCaseDiff30006();
                    break;
                case 30007:
                    TestCaseDiff30007();
                    break;
                case 30008:
                    TestCaseDiff30008();
                    break;
                case 30009:
                    TestCaseDiff30009();
                    break;
                case 30010:
                    TestCaseDiff30010();
                    break;
                case 30011:
                    TestCaseDiff30011();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Exponent TestCase:j= " + j + ": Gap" + ConsoleColors.RESET);
                    break;
            }
        }
    }

    public static void SoftwareEngg(String AlgoName, int j) {
        if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapInfinity(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapInfinity(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Infinite Loop GAP" + ConsoleColors.RESET);
        } else if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGapException(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGapException(j)))) {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Exception GAP" + ConsoleColors.RESET);
        } else {
            switch (j) {

                case 110001:
                    TestCaseDiff110001();
                    break;
                case 110002:
                    TestCaseDiff110002();
                    break;
                case 110003:
                    TestCaseDiff110003();
                    break;
                case 110004:
                    TestCaseDiff110004();
                    break;
                case 110005:
                    TestCaseDiff110005();
                    break;
                case 110006:
                    TestCaseDiff110006();
                    break;
                case 110007:
                    TestCaseDiff110007();
                    break;
                case 110008:
                    TestCaseDiff110008();
                    break;
                case 110009:
                    TestCaseDiff110009();
                    break;
                case 110010:
                    TestCaseDiff110010();
                    break;
                case 110011:
                    TestCaseDiff110011();
                    break;
                case 110012:
                    TestCaseDiff110012();
                    break;
                case 110013:
                    TestCaseDiff110013();
                    break;
                case 110014:
                    TestCaseDiff110014();
                    break;
                case 110015:
                    TestCaseDiff110015();
                    break;
                case 110016:
                    TestCaseDiff110016();
                    break;
                case 110017:
                    TestCaseDiff110017();
                    break;
                case 110018:
                    TestCaseDiff110018();
                    break;
                case 110019:
                    TestCaseDiff110019();
                    break;
                case 110020:
                    TestCaseDiff110020();
                    break;
                case 110021:
                    TestCaseDiff110021();
                    break;
                case 110022:
                    TestCaseDiff110022();
                    break;
                case 110023:
                    TestCaseDiff110023();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Parenthesis TestCase:j= " + j + ": Gap" + ConsoleColors.RESET);
                    break;
            }
        }
    }

    public static void TestCaseDiff4000() {
        //Test Case 4000
        UsageCalculus.TestCase = 4000;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1.0*x^8 + x^5 + x^4 - x - 25.0";
        UsageCalculus.FInverseEqn = "Not Computed";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        //Usage.EqnUnderTest="8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[5] = "";
        UsageCalculus.EqnSolArrayMap4[5] = "";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x^2.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    //Palindromic String-Equation Test
    public static void TestCaseDiff4001() {
        //Palindromic String-Equation Test
        //Test Case 4001
        UsageCalculus.TestCase = 4001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values Except 6th Order Onwards";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        UsageCalculus.EqnUnderTest = " -25.0+1.0/1.0*x+x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        //Usage.EqnUnderTest="-120.0-480.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.TestCaseName = "Standard:Palindromic String Equation:";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0+1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+4.0*3.0*x^2.0-5.0*4.0*x^3.0-8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0+0.5/3.0*x^3.0+0.2/6.0*x^6.0-0.16666666666666666/7.0*x^7.0-0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0-20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0+0.03333333333333333/7.0*x^7.0-0.023809523809523808/8.0*x^8.0-0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0-60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0+0.0047619047619047615/8.0*x^8.0-0.002976190476190476/9.0*x^9.0-0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0+0.008333333333333333*x^5.0+5.952380952380952E-4*x^8.0-3.3068783068783067E-4*x^9.0-8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "0.0-120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[5] = "";
        UsageCalculus.EqnSolArrayMap4[5] = "";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "0.0-6720.0*3.0*x^2.0+6720.0*3.0^x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-20160.0*x^2.0+20160.0*x^2.0+";
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

    public static void TestCaseDiff4002() {
        //Test Case 4002
        UsageCalculus.TestCase = 4002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "9*x^8 + 7*x^5 + 6*x^4 - 4*x - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*8.0*x^7.0+7.0*5.0*x^4.0+6.0*4.0*x^3.0-4.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "9.0/9.0*x^9.0+7.0/6.0*x^6.0+6.0/5.0*x^5.0-4.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "72.0*7.0*x^6.0+35.0*4.0*x^3.0+24.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "1.0/10.0*x^10.0+1.1666666666666667/7.0*x^7.0+1.2/6.0*x^6.0-2.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "504.0*6.0*x^5.0+140.0*3.0*x^2.0+72.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.1/11.0*x^11.0+0.16666666666666669/8.0*x^8.0+0.19999999999999998/7.0*x^7.0-0.6666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3024.0*5.0*x^4.0+420.0*2.0*x^1.0+144.0";
        UsageCalculus.EqnSolArrayMap2[4] = "15120.0*x^4.0+840.0*x+144.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.009090909090909092/12.0*x^12.0+0.020833333333333336/9.0*x^9.0+0.02857142857142857/8.0*x^8.0-0.16666666666666666/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "7.575757575757577E-4*x^12.0+0.002314814814814815*x^9.0+0.0035714285714285713*x^8.0-0.03333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff4003() {
        //Test Case 4003
        UsageCalculus.TestCase = 4003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "1.0/1.0*1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-0.14285714285714285/-6.0/x^6.0-0.25/-3.0/x^3.0-0.3333333333333333/-2.0/x^2.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3] = "0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.023809523809523808/-5.0/x^5.0+0.08333333333333333/-2.0/x^2.0+0.16666666666666666/-1.0/x^1.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-0.0047619047619047615/-4.0/x^4.0-0.041666666666666664/-1.0/x^1.0-0.16666666666666666*ln(x)-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.0011904761904761904/x^4.0+0.041666666666666664/x-0.16666666666666666*ln(x)-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4004() {
        //Only Trailing Constant
        //Test Case 4004
        UsageCalculus.TestCase = 4004;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnUnderTest = "1.0/1.0*x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/8.0*7.0*x^6.0-1.0/8.0*7.0*x^6.0+1.0/5.0*4.0*x^3.0-1.0/5.0*4.0*x^3.0+3.0*x^2.0-1.0/4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "0.125/8.0*x^8.0-0.125/8.0*x^8.0+0.2/5.0*x^5.0-0.2/5.0*x^5.0+1.0/4.0*x^4.0-0.25/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[1] = "0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.875*6.0*x^5.0-0.875*6.0*x^5.0+0.8*3.0*x^2.0-0.8*3.0*x^2.0+3.0*2.0*x^1.0-0.75*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[2] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayMap3[2] = "0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.015625/9.0*x^9.0-0.015625/9.0*x^9.0+0.04/6.0*x^6.0-0.04/6.0*x^6.0+0.25/5.0*x^5.0-0.0625/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "5.25*5.0*x^4.0-5.25*5.0*x^4.0+2.4000000000000004*2.0*x^1.0-2.4000000000000004*2.0*x^1.0+6.0-1.5";
        UsageCalculus.EqnSolArrayMap2[3] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayMap3[3] = "0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.001736111111111111/10.0*x^10.0-0.001736111111111111/10.0*x^10.0+0.006666666666666667/7.0*x^7.0-0.006666666666666667/7.0*x^7.0+0.05/6.0*x^6.0-0.0125/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "26.25*4.0*x^3.0-26.25*4.0*x^3.0+4.800000000000001-4.800000000000001+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "105.0*x^3.0-105.0*x^3.0+4.800000000000001-4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4] = "1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.736111111111111E-4/11.0*x^11.0-1.736111111111111E-4/11.0*x^11.0+9.523809523809525E-4/8.0*x^8.0-9.523809523809525E-4/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.0020833333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.578282828282828E-5*x^11.0-1.578282828282828E-5*x^11.0+1.1904761904761906E-4*x^8.0-1.1904761904761906E-4*x^8.0+0.0011904761904761904*x^7.0-2.976190476190476E-4*x^7.0";
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

    public static void TestCaseDiff4005() {
        //Test Case 4005
        UsageCalculus.TestCase = 4005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1/x^-8 + 1.0/1.0*1/x^-5 + 1/x^-4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Complex:Sanity TestCase:Mapping of -ve Exponent & Division";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;

        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^-8.0+1.0/x^-5.0+1.0/x^-4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0";
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

    // x-25 Test Cases: Last 2 Terms put at the Begining
    public static void TestCaseDiff4006() {
        //Test Case 4006
        UsageCalculus.TestCase = 4006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+ 1/x^-4 - 25.0-1/x^-8 + 1/x^-5 ";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Complex:Sanity TestCase:Mapping of -ve Exponent & Division";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;

        UsageCalculus.EqnSolArrayMap1[1] = "x^4.0-25.0-x^8.0+x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "4.0*x^3.0-0.0-8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayMap2[1] = "4.0*x^3.0-8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^-4.0-25.0-1.0/x^-8.0+1.0/x^-5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/5.0*x^5.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/5.0*x^5.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "4.0*x^3.0-8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "4.0*3.0*x^2.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0-56.0*x^6.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/5.0*x^5.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.2/6.0*x^6.0-25.0/2.0*x^2.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.03333333333333333*x^6.0-12.5*x^2.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0-56.0*x^6.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x-336.0*x^5.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3] = "0.03333333333333333*x^6.0-12.5*x^2.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0047619047619047615*x^7.0-4.166666666666667*x^3.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x-336.0*x^5.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0-1680.0*x^4.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0047619047619047615*x^7.0-4.166666666666667*x^3.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0-0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0";
        UsageCalculus.EqnSolArrayMap4[4] = "5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0-8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0";
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

    public static void TestCaseDiff4007() {
        //Test Case 4007
        UsageCalculus.TestCase = 4007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+ 1/x^-4 - 25.0 +1/x^-8 + 1/x^-5 ";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Complex:Sanity TestCase:Mapping of -ve Exponent & Division";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;

        UsageCalculus.EqnSolArrayMap1[1] = "x^4.0-25.0+x^8.0+x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "4.0*x^3.0-0.0+8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayMap2[1] = "4.0*x^3.0+8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^-4.0-25.0+1.0/x^-8.0+1.0/x^-5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/5.0*x^5.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/5.0*x^5.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "4.0*x^3.0+8.0*x^7.0+5.0*x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "4.0*3.0*x^2.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0+56.0*x^6.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/5.0*x^5.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.2/6.0*x^6.0-25.0/2.0*x^2.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.03333333333333333*x^6.0-12.5*x^2.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0+56.0*x^6.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x+336.0*x^5.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3] = "0.03333333333333333*x^6.0-12.5*x^2.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0047619047619047615*x^7.0-4.166666666666667*x^3.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x+336.0*x^5.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0+1680.0*x^4.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0047619047619047615*x^7.0-4.166666666666667*x^3.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0";
        UsageCalculus.EqnSolArrayMap4[4] = "5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0";
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

    public static void TestCaseDiff4008() {
        //Test Case 4008
        UsageCalculus.TestCase = 4008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "- x - 25.0 +x^8 + x^5 + x^4 ";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-x-25.0+x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0-0.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-x-25.0+x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-1.0/2.0*x^2.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/2.0*x^2.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-1.0/2.0*x^2.0-25.0*x+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-0.5/3.0*x^3.0-25.0/2.0*x^2.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-0.16666666666666666*x^3.0-12.5*x^2.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-0.16666666666666666*x^3.0-12.5*x^2.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-0.041666666666666664*x^4.0-4.166666666666667*x^3.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-0.041666666666666664*x^4.0-4.166666666666667*x^3.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff4009() {
        //Test Case 4009
        UsageCalculus.TestCase = 4009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "- x - 25.0 -x^8 + x^5 + x^4 ";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-x-25.0-x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0-0.0-8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.0-8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-x-25.0-x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-1.0/2.0*x^2.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/2.0*x^2.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.0-8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-1.0/2.0*x^2.0-25.0*x-1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-0.5/3.0*x^3.0-25.0/2.0*x^2.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-0.16666666666666666*x^3.0-12.5*x^2.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-0.16666666666666666*x^3.0-12.5*x^2.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-0.041666666666666664*x^4.0-4.166666666666667*x^3.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-0.041666666666666664*x^4.0-4.166666666666667*x^3.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0-0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0-8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "-1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[5] = "";
        UsageCalculus.EqnSolArrayMap4[5] = "";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-6720.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-20160.0*x^2.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    //Palindromic String-Equation Test Start
    public static void TestCaseDiff4010() {
        //Test Case 4010
        UsageCalculus.TestCase = 4010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "- 25.0- 4*x+ 6*x^4+ 7*x^5+9*x^8+9*x^8 + 7*x^5 + 6*x^4 - 4*x - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0-4.0*x+6.0*x^4.0+7.0*x^5.0+9.0*x^8.0+9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-4.0+6.0*4.0*x^3.0+7.0*5.0*x^4.0+9.0*8.0*x^7.0+9.0*8.0*x^7.0+7.0*5.0*x^4.0+6.0*4.0*x^3.0-4.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-4.0+24.0*x^3.0+35.0*x^4.0+72.0*x^7.0+72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0-4.0*x+6.0*x^4.0+7.0*x^5.0+9.0*x^8.0+9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x-4.0/2.0*x^2.0+6.0/5.0*x^5.0+7.0/6.0*x^6.0+9.0/9.0*x^9.0+9.0/9.0*x^9.0+7.0/6.0*x^6.0+6.0/5.0*x^5.0-4.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x-2.0*x^2.0+1.2*x^5.0+1.1666666666666667*x^6.0+x^9.0+x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-4.0+24.0*x^3.0+35.0*x^4.0+72.0*x^7.0+72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0+24.0*3.0*x^2.0+35.0*4.0*x^3.0+72.0*7.0*x^6.0+72.0*7.0*x^6.0+35.0*4.0*x^3.0+24.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^2.0+140.0*x^3.0+504.0*x^6.0+504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x-2.0*x^2.0+1.2*x^5.0+1.1666666666666667*x^6.0+x^9.0+x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0-2.0/3.0*x^3.0+1.2/6.0*x^6.0+1.1666666666666667/7.0*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+1.1666666666666667/7.0*x^7.0+1.2/6.0*x^6.0-2.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0-0.6666666666666666*x^3.0+0.19999999999999998*x^6.0+0.16666666666666669*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^2.0+140.0*x^3.0+504.0*x^6.0+504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*2.0*x^1.0+140.0*3.0*x^2.0+504.0*6.0*x^5.0+504.0*6.0*x^5.0+140.0*3.0*x^2.0+72.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "144.0*x+420.0*x^2.0+3024.0*x^5.0+3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0-0.6666666666666666*x^3.0+0.19999999999999998*x^6.0+0.16666666666666669*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0-0.6666666666666666/4.0*x^4.0+0.19999999999999998/7.0*x^7.0+0.16666666666666669/8.0*x^8.0+0.1/11.0*x^11.0+0.1/11.0*x^11.0+0.16666666666666669/8.0*x^8.0+0.19999999999999998/7.0*x^7.0-0.6666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0-0.16666666666666666*x^4.0+0.02857142857142857*x^7.0+0.020833333333333336*x^8.0+0.009090909090909092*x^11.0+0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "144.0*x+420.0*x^2.0+3024.0*x^5.0+3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "144.0+420.0*2.0*x^1.0+3024.0*5.0*x^4.0+3024.0*5.0*x^4.0+420.0*2.0*x^1.0+144.0";
        UsageCalculus.EqnSolArrayMap2[4] = "144.0+840.0*x+15120.0*x^4.0+15120.0*x^4.0+840.0*x+144.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0-0.16666666666666666*x^4.0+0.02857142857142857*x^7.0+0.020833333333333336*x^8.0+0.009090909090909092*x^11.0+0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0-0.16666666666666666/5.0*x^5.0+0.02857142857142857/8.0*x^8.0+0.020833333333333336/9.0*x^9.0+0.009090909090909092/12.0*x^12.0+0.009090909090909092/12.0*x^12.0+0.020833333333333336/9.0*x^9.0+0.02857142857142857/8.0*x^8.0-0.16666666666666666/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0-0.03333333333333333*x^5.0+0.0035714285714285713*x^8.0+0.002314814814814815*x^9.0+7.575757575757577E-4*x^12.0+7.575757575757577E-4*x^12.0+0.002314814814814815*x^9.0+0.0035714285714285713*x^8.0-0.03333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff4011() {
        //Test Case 4011
        UsageCalculus.TestCase = 4011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "- 4*x- 25.0- 4*x+ 6*x^4+ 7*x^5+9*x^8+9*x^8 + 7*x^5 + 6*x^4 - 4*x - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-4.0*x-25.0-4.0*x+6.0*x^4.0+7.0*x^5.0+9.0*x^8.0+9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-4.0-0.0-4.0+6.0*4.0*x^3.0+7.0*5.0*x^4.0+9.0*8.0*x^7.0+9.0*8.0*x^7.0+7.0*5.0*x^4.0+6.0*4.0*x^3.0-4.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-4.0-4.0+24.0*x^3.0+35.0*x^4.0+72.0*x^7.0+72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-4.0*x-25.0-4.0*x+6.0*x^4.0+7.0*x^5.0+9.0*x^8.0+9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-4.0/2.0*x^2.0-25.0*x-4.0/2.0*x^2.0+6.0/5.0*x^5.0+7.0/6.0*x^6.0+9.0/9.0*x^9.0+9.0/9.0*x^9.0+7.0/6.0*x^6.0+6.0/5.0*x^5.0-4.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-2.0*x^2.0-25.0*x-2.0*x^2.0+1.2*x^5.0+1.1666666666666667*x^6.0+x^9.0+x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-4.0-4.0+24.0*x^3.0+35.0*x^4.0+72.0*x^7.0+72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0-0.0+24.0*3.0*x^2.0+35.0*4.0*x^3.0+72.0*7.0*x^6.0+72.0*7.0*x^6.0+35.0*4.0*x^3.0+24.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^2.0+140.0*x^3.0+504.0*x^6.0+504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-2.0*x^2.0-25.0*x-2.0*x^2.0+1.2*x^5.0+1.1666666666666667*x^6.0+x^9.0+x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-2.0/3.0*x^3.0-25.0/2.0*x^2.0-2.0/3.0*x^3.0+1.2/6.0*x^6.0+1.1666666666666667/7.0*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+1.1666666666666667/7.0*x^7.0+1.2/6.0*x^6.0-2.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-0.6666666666666666*x^3.0-12.5*x^2.0-0.6666666666666666*x^3.0+0.19999999999999998*x^6.0+0.16666666666666669*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^2.0+140.0*x^3.0+504.0*x^6.0+504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*2.0*x^1.0+140.0*3.0*x^2.0+504.0*6.0*x^5.0+504.0*6.0*x^5.0+140.0*3.0*x^2.0+72.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "144.0*x+420.0*x^2.0+3024.0*x^5.0+3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-0.6666666666666666*x^3.0-12.5*x^2.0-0.6666666666666666*x^3.0+0.19999999999999998*x^6.0+0.16666666666666669*x^7.0+1.0/10.0*x^10.0+1.0/10.0*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-0.6666666666666666/4.0*x^4.0-12.5/3.0*x^3.0-0.6666666666666666/4.0*x^4.0+0.19999999999999998/7.0*x^7.0+0.16666666666666669/8.0*x^8.0+0.1/11.0*x^11.0+0.1/11.0*x^11.0+0.16666666666666669/8.0*x^8.0+0.19999999999999998/7.0*x^7.0-0.6666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-0.16666666666666666*x^4.0-4.166666666666667*x^3.0-0.16666666666666666*x^4.0+0.02857142857142857*x^7.0+0.020833333333333336*x^8.0+0.009090909090909092*x^11.0+0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "144.0*x+420.0*x^2.0+3024.0*x^5.0+3024.0*x^5.0+420.0*x^2.0+144.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "144.0+420.0*2.0*x^1.0+3024.0*5.0*x^4.0+3024.0*5.0*x^4.0+420.0*2.0*x^1.0+144.0";
        UsageCalculus.EqnSolArrayMap2[4] = "144.0+840.0*x+15120.0*x^4.0+15120.0*x^4.0+840.0*x+144.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-0.16666666666666666*x^4.0-4.166666666666667*x^3.0-0.16666666666666666*x^4.0+0.02857142857142857*x^7.0+0.020833333333333336*x^8.0+0.009090909090909092*x^11.0+0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-0.16666666666666666/5.0*x^5.0-4.166666666666667/4.0*x^4.0-0.16666666666666666/5.0*x^5.0+0.02857142857142857/8.0*x^8.0+0.020833333333333336/9.0*x^9.0+0.009090909090909092/12.0*x^12.0+0.009090909090909092/12.0*x^12.0+0.020833333333333336/9.0*x^9.0+0.02857142857142857/8.0*x^8.0-0.16666666666666666/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-0.03333333333333333*x^5.0-1.0416666666666667*x^4.0-0.03333333333333333*x^5.0+0.0035714285714285713*x^8.0+0.002314814814814815*x^9.0+7.575757575757577E-4*x^12.0+7.575757575757577E-4*x^12.0+0.002314814814814815*x^9.0+0.0035714285714285713*x^8.0-0.03333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolArrayIntegrAlone[8] = "";
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9] = "";
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10] = "";
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11] = "";
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12] = "";
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13] = "";
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14] = "";
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff4012() {
        //Test Case 4012
        UsageCalculus.TestCase = 4012;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "- 25.0-1/x + 1/x^4 + 1/x^5 + 1/x^8 + 1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0-1.0/x+1.0/x^4.0+1.0/x^5.0+1.0/x^8.0+1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0+1.0/x^2.0-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0/x^2.0-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0-1.0/x+1.0/x^4.0+1.0/x^5.0+1.0/x^8.0+1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x-ln(x)+1.0/-3.0/x^3.0+1.0/-4.0/x^4.0+1.0/-7.0/x^7.0+1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x-ln(x)-1.0/3.0/x^3.0-1.0/4.0/x^4.0-1.0/7.0/x^7.0-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0/x^2.0-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-2.0/x^3.0-4.0*-5.0/x^6.0-5.0*-6.0/x^7.0-8.0*-9.0/x^10.0-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-2.0/x^3.0+20.0/x^6.0+30.0/x^7.0+72.0/x^10.0+72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-2.0/x^3.0+20.0/x^6.0+30.0/x^7.0+72.0/x^10.0+72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-2.0*-3.0/x^4.0+20.0*-6.0/x^7.0+30.0*-7.0/x^8.0+72.0*-10.0/x^11.0+72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "6.0/x^4.0-120.0/x^7.0-210.0/x^8.0-720.0/x^11.0-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "6.0/x^4.0-120.0/x^7.0-210.0/x^8.0-720.0/x^11.0-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "6.0*-4.0/x^5.0-120.0*-7.0/x^8.0-210.0*-8.0/x^9.0-720.0*-11.0/x^12.0-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-24.0/x^5.0+840.0/x^8.0+1680.0/x^9.0+7920.0/x^12.0+7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4013() {
        //Test Case 4013
        UsageCalculus.TestCase = 4013;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "- 25.0-1/x^-1 + 1/x^-4 + 1/x^-5 + 1/x^-8 + 1/x^-8 + 1/x^-5 + 1/x^-4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0-x+x^4.0+x^5.0+x^8.0+x^8.0+x^5.0+x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-1.0+4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.0+4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0-x+x^4.0+x^5.0+x^8.0+x^8.0+x^5.0+x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x-1.0/2.0*x^2.0+1.0/5.0*x^5.0+1.0/6.0*x^6.0+1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x-1.0/2.0*x^2.0+1.0/5.0*x^5.0+1.0/6.0*x^6.0+1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.0+4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0+4.0*3.0*x^2.0+5.0*4.0*x^3.0+8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0+20.0*x^3.0+56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0+20.0*x^3.0+56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0+20.0*3.0*x^2.0+56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x+60.0*x^2.0+336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x+60.0*x^2.0+336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0+60.0*2.0*x^1.0+336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0+120.0*x+1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4014() {
        //Only Trailing Constant
        //Test Case 4014
        UsageCalculus.TestCase = 4014;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnUnderTest = "x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0 + x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0+1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/8.0*7.0*x^6.0-1.0/8.0*7.0*x^6.0+1.0/5.0*4.0*x^3.0-1.0/5.0*4.0*x^3.0+3.0*x^2.0-1.0/4.0*3.0*x^2.0+1.0/8.0*7.0*x^6.0-1.0/8.0*7.0*x^6.0+1.0/5.0*4.0*x^3.0-1.0/5.0*4.0*x^3.0+3.0*x^2.0-1.0/4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0+0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0+1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "0.125/8.0*x^8.0-0.125/8.0*x^8.0+0.2/5.0*x^5.0-0.2/5.0*x^5.0+1.0/4.0*x^4.0-0.25/4.0*x^4.0+0.125/8.0*x^8.0-0.125/8.0*x^8.0+0.2/5.0*x^5.0-0.2/5.0*x^5.0+1.0/4.0*x^4.0-0.25/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[1] = "0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0+0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0+0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.875*6.0*x^5.0-0.875*6.0*x^5.0+0.8*3.0*x^2.0-0.8*3.0*x^2.0+3.0*2.0*x^1.0-0.75*2.0*x^1.0+0.875*6.0*x^5.0-0.875*6.0*x^5.0+0.8*3.0*x^2.0-0.8*3.0*x^2.0+3.0*2.0*x^1.0-0.75*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[2] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x+5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayMap3[2] = "0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0+0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.015625/9.0*x^9.0-0.015625/9.0*x^9.0+0.04/6.0*x^6.0-0.04/6.0*x^6.0+0.25/5.0*x^5.0-0.0625/5.0*x^5.0+0.015625/9.0*x^9.0-0.015625/9.0*x^9.0+0.04/6.0*x^6.0-0.04/6.0*x^6.0+0.25/5.0*x^5.0-0.0625/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0+0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x+5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "5.25*5.0*x^4.0-5.25*5.0*x^4.0+2.4000000000000004*2.0*x^1.0-2.4000000000000004*2.0*x^1.0+6.0-1.5+5.25*5.0*x^4.0-5.25*5.0*x^4.0+2.4000000000000004*2.0*x^1.0-2.4000000000000004*2.0*x^1.0+6.0-1.5";
        UsageCalculus.EqnSolArrayMap2[3] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5+26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayMap3[3] = "0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0+0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.001736111111111111/10.0*x^10.0-0.001736111111111111/10.0*x^10.0+0.006666666666666667/7.0*x^7.0-0.006666666666666667/7.0*x^7.0+0.05/6.0*x^6.0-0.0125/6.0*x^6.0+0.001736111111111111/10.0*x^10.0-0.001736111111111111/10.0*x^10.0+0.006666666666666667/7.0*x^7.0-0.006666666666666667/7.0*x^7.0+0.05/6.0*x^6.0-0.0125/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0+1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5+26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "26.25*4.0*x^3.0-26.25*4.0*x^3.0+4.800000000000001-4.800000000000001+0.0-0.0+26.25*4.0*x^3.0-26.25*4.0*x^3.0+4.800000000000001-4.800000000000001+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "105.0*x^3.0-105.0*x^3.0+4.800000000000001-4.800000000000001+105.0*x^3.0-105.0*x^3.0+4.800000000000001-4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4] = "1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0+1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.736111111111111E-4/11.0*x^11.0-1.736111111111111E-4/11.0*x^11.0+9.523809523809525E-4/8.0*x^8.0-9.523809523809525E-4/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.0020833333333333333/7.0*x^7.0+1.736111111111111E-4/11.0*x^11.0-1.736111111111111E-4/11.0*x^11.0+9.523809523809525E-4/8.0*x^8.0-9.523809523809525E-4/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.0020833333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.578282828282828E-5*x^11.0-1.578282828282828E-5*x^11.0+1.1904761904761906E-4*x^8.0-1.1904761904761906E-4*x^8.0+0.0011904761904761904*x^7.0-2.976190476190476E-4*x^7.0+1.578282828282828E-5*x^11.0-1.578282828282828E-5*x^11.0+1.1904761904761906E-4*x^8.0-1.1904761904761906E-4*x^8.0+0.0011904761904761904*x^7.0-2.976190476190476E-4*x^7.0";
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

    public static void TestCaseDiff4015() {
        //Test Case 4015
        UsageCalculus.TestCase = 4015;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "- 25.0 + 1/x^4 + 1/x^5 + 1/x^8 + 1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0+1.0/x^4.0+1.0/x^5.0+1.0/x^8.0+1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0+1.0/x^4.0+1.0/x^5.0+1.0/x^8.0+1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x+1.0/-3.0/x^3.0+1.0/-4.0/x^4.0+1.0/-7.0/x^7.0+1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x-1.0/3.0/x^3.0-1.0/4.0/x^4.0-1.0/7.0/x^7.0-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-4.0/x^5.0-5.0/x^6.0-8.0/x^9.0-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-4.0*-5.0/x^6.0-5.0*-6.0/x^7.0-8.0*-9.0/x^10.0-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0";
        UsageCalculus.EqnSolArrayMap2[2] = "20.0/x^6.0+30.0/x^7.0+72.0/x^10.0+72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x-1.0/3.0/x^3.0-1.0/4.0/x^4.0-1.0/7.0/x^7.0-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0-0.3333333333333333/-2.0/x^2.0-0.25/-3.0/x^3.0-0.14285714285714285/-6.0/x^6.0-0.14285714285714285/-6.0/x^6.0-0.25/-3.0/x^3.0-0.3333333333333333/-2.0/x^2.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0+0.16666666666666666/x^2.0+0.08333333333333333/x^3.0+0.023809523809523808/x^6.0+0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "20.0/x^6.0+30.0/x^7.0+72.0/x^10.0+72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "20.0*-6.0/x^7.0+30.0*-7.0/x^8.0+72.0*-10.0/x^11.0+72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-120.0/x^7.0-210.0/x^8.0-720.0/x^11.0-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0+0.16666666666666666/x^2.0+0.08333333333333333/x^3.0+0.023809523809523808/x^6.0+0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0+0.16666666666666666/-1.0/x^1.0+0.08333333333333333/-2.0/x^2.0+0.023809523809523808/-5.0/x^5.0+0.023809523809523808/-5.0/x^5.0+0.08333333333333333/-2.0/x^2.0+0.16666666666666666/-1.0/x^1.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0-0.16666666666666666/x-0.041666666666666664/x^2.0-0.0047619047619047615/x^5.0-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-120.0/x^7.0-210.0/x^8.0-720.0/x^11.0-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-120.0*-7.0/x^8.0-210.0*-8.0/x^9.0-720.0*-11.0/x^12.0-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "840.0/x^8.0+1680.0/x^9.0+7920.0/x^12.0+7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0-0.16666666666666666/x-0.041666666666666664/x^2.0-0.0047619047619047615/x^5.0-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0-0.16666666666666666*ln(x)-0.041666666666666664/-1.0/x^1.0-0.0047619047619047615/-4.0/x^4.0-0.0047619047619047615/-4.0/x^4.0-0.041666666666666664/-1.0/x^1.0-0.16666666666666666*ln(x)-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0-0.16666666666666666*ln(x)+0.041666666666666664/x+0.0011904761904761904/x^4.0+0.0011904761904761904/x^4.0+0.041666666666666664/x-0.16666666666666666*ln(x)-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4016() {
        //Test Case 4016
        UsageCalculus.TestCase = 4016;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "- 25.0 + 1/x^-4 + 1/x^-5 + 1/x^-8 + 1/x^-8 + 1/x^-5 + 1/x^-4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Sanity TestCase";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0+x^4.0+x^5.0+x^8.0+x^8.0+x^5.0+x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0+4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0+x^4.0+x^5.0+x^8.0+x^8.0+x^5.0+x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x+1.0/5.0*x^5.0+1.0/6.0*x^6.0+1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x+1.0/5.0*x^5.0+1.0/6.0*x^6.0+1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "4.0*x^3.0+5.0*x^4.0+8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "4.0*3.0*x^2.0+5.0*4.0*x^3.0+8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0+20.0*x^3.0+56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x+1.0/5.0*x^5.0+1.0/6.0*x^6.0+1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0+0.2/6.0*x^6.0+0.16666666666666666/7.0*x^7.0+0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0+0.03333333333333333*x^6.0+0.023809523809523808*x^7.0+0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0+20.0*x^3.0+56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0+20.0*3.0*x^2.0+56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x+60.0*x^2.0+336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0+0.03333333333333333*x^6.0+0.023809523809523808*x^7.0+0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0+0.03333333333333333/7.0*x^7.0+0.023809523809523808/8.0*x^8.0+0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0+0.0047619047619047615*x^7.0+0.002976190476190476*x^8.0+0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x+60.0*x^2.0+336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0+60.0*2.0*x^1.0+336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0+120.0*x+1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0+0.0047619047619047615*x^7.0+0.002976190476190476*x^8.0+0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0+0.0047619047619047615/8.0*x^8.0+0.002976190476190476/9.0*x^9.0+0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0+5.952380952380952E-4*x^8.0+3.3068783068783067E-4*x^9.0+8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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
    //Palindromic String-Equation Test End

    /* **************************************** */
 /* ***************IndConstant Test Case***************** */
 /* **************************************** */
    public static void TestCaseDiff4017() {
        //Test Case 4017
        UsageCalculus.TestCase = 4017;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "1.0/1.0*p/9.0*xyc1^9.0 + 2.0/2.0*p*6.0*xyc1^6.0 + p/5.0*xyc1^5.0 + p*2.0*xyc1^2.0 + p*25.0*xyc1 + p*50.0";
        UsageCalculus.TestCaseName = "Standard:Independent Constant:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Unit TestCase";

        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "9.0/p*xyc1^9.0+p*6.0*xyc1^6.0+p/5.0*xyc1^5.0+p*2.0*xyc1^2.0+p*25.0*xyc1+p*50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0/p*9.0*xyc1^8.0+p*6.0*6.0*xyc1^5.0+p/5.0*5.0*xyc1^4.0+p*2.0*2.0*xyc1^1.0+p*25.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "p*81.0*xyc1^8.0+p*36.0*xyc1^5.0+p/5.0*xyc1^4.0+p*4.0*xyc1+p*25.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "p/9.0*xyc1^9.0+p*6.0*xyc1^6.0+p/5.0*xyc1^5.0+p*2.0*xyc1^2.0+p*25.0*xyc1+p*50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "p/9.0/10.0*xyc1^10.0+p*6.0/7.0*xyc1^7.0+p/5.0/6.0*xyc1^6.0+p*2.0/3.0*xyc1^3.0+p*25.0/2.0*xyc1^2.0+p*50.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1] = "p/90.0*xyc1^10.0+p*0.8571428571428571*xyc1^7.0+p/30.0*xyc1^6.0+p*0.6666666666666666*xyc1^3.0+p*12.5*xyc1^2.0+p*50.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "p*81.0*xyc1^8.0+p*36.0*xyc1^5.0+p/5.0*xyc1^4.0+p*4.0*xyc1+p*25.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "p*81.0*8.0*xyc1^7.0+p*36.0*5.0*xyc1^4.0+p/5.0*4.0*xyc1^3.0+p*4.0+p*25.0*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "p*648.0*xyc1^7.0+p*180.0*xyc1^4.0+p/5.0*xyc1^3.0+p*4.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "p/90.0*xyc1^10.0+p*0.8571428571428571*xyc1^7.0+p/30.0*xyc1^6.0+p*0.6666666666666666*xyc1^3.0+p*12.5*xyc1^2.0+p*50.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "p/90.0/11.0*xyc1^11.0+p*0.8571428571428571/8.0*xyc1^8.0+p/30.0/7.0*xyc1^7.0+p*0.6666666666666666/4.0*xyc1^4.0+p*12.5/3.0*xyc1^3.0+p*50.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "p/990.0*xyc1^11.0+p*0.10714285714285714*xyc1^8.0+p/210.0*xyc1^7.0+p*0.16666666666666666*xyc1^4.0+p*4.166666666666667*xyc1^3.0+p*25.0*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "p*648.0*xyc1^7.0+p*180.0*xyc1^4.0+p/5.0*xyc1^3.0+p*4.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "p*648.0*7.0*xyc1^6.0+p*180.0*4.0*xyc1^3.0+p/5.0*3.0*xyc1^2.0+p*4.0*0.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "p*4536.0*xyc1^6.0+p*720.0*xyc1^3.0+p/5.0*xyc1^2.0+p*0.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "p/990.0*xyc1^11.0+p*0.10714285714285714*xyc1^8.0+p/210.0*xyc1^7.0+p*0.16666666666666666*xyc1^4.0+p*4.166666666666667*xyc1^3.0+p*25.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "p/990.0/12.0*xyc1^12.0+p*0.10714285714285714/9.0*xyc1^9.0+p/210.0/8.0*xyc1^8.0+p*0.16666666666666666/5.0*xyc1^5.0+p*4.166666666666667/4.0*xyc1^4.0+p*25.0/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "p/11880.0*xyc1^12.0+p*0.011904761904761904*xyc1^9.0+p/1680.0*xyc1^8.0+p*0.03333333333333333*xyc1^5.0+p*1.0416666666666667*xyc1^4.0+p*8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "p*4536.0*xyc1^6.0+p*720.0*xyc1^3.0+p/5.0*xyc1^2.0+p*0.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "p*4536.0*6.0*xyc1^5.0+p*720.0*3.0*xyc1^2.0+p/5.0*2.0*xyc1^1.0+p*0.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "p*27216.0*xyc1^5.0+p*2160.0*xyc1^2.0+p/5.0*xyc1+p*0.0+p*0.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "p/11880.0*xyc1^12.0+p*0.011904761904761904*xyc1^9.0+p/1680.0*xyc1^8.0+p*0.03333333333333333*xyc1^5.0+p*1.0416666666666667*xyc1^4.0+p*8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "p/11880.0/13.0*xyc1^13.0+p*0.011904761904761904/10.0*xyc1^10.0+p/1680.0/9.0*xyc1^9.0+p*0.03333333333333333/6.0*xyc1^6.0+p*1.0416666666666667/5.0*xyc1^5.0+p*8.333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "p/154440.0*xyc1^13.0+p*0.0011904761904761904*xyc1^10.0+p/15120.0*xyc1^9.0+p*0.005555555555555556*xyc1^6.0+p*0.20833333333333334*xyc1^5.0+p*2.0833333333333335*xyc1^4.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4018() {
        //Test Case 4018
        UsageCalculus.TestCase = 4018;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1.0*a*x^8 + a*x^5 + 2.0/2.0*a*x^4 - 2*a*x- 25*(2)";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "a*x^8.0+a*x^5.0+a*x^4.0-a*2.0*x-50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "a*x^8.0+a*x^5.0+a*x^4.0-a*2.0*x-50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-a*x^2.0-50.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-a*x^2.0-50.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "a*8.0*7.0*x^6.0+a*5.0*4.0*x^3.0+a*4.0*3.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-a*x^2.0-50.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "a/9.0/10.0*x^10.0+a/6.0/7.0*x^7.0+a/5.0/6.0*x^6.0-a/3.0*x^3.0-50.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "a/90.0*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-a/3.0*x^3.0-25.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a*56.0*6.0*x^5.0+a*20.0*3.0*x^2.0+a*12.0*2.0*x^1.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x-a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "a/90.0*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-a/3.0*x^3.0-25.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "a/90.0/11.0*x^11.0+a/42.0/8.0*x^8.0+a/30.0/7.0*x^7.0-a/3.0/4.0*x^4.0-25.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "a/990.0*x^11.0+a/336.0*x^8.0+a/210.0*x^7.0-a/12.0*x^4.0-8.333333333333334*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "a*336.0*5.0*x^4.0+a*60.0*2.0*x^1.0+a*24.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a*1680.0*x^4.0+a*120.0*x+a*24.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "a/990.0*x^11.0+a/336.0*x^8.0+a/210.0*x^7.0-a/12.0*x^4.0-8.333333333333334*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "a/990.0/12.0*x^12.0+a/336.0/9.0*x^9.0+a/210.0/8.0*x^8.0-a/12.0/5.0*x^5.0-8.333333333333334/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "a/11880.0*x^12.0+a/3024.0*x^9.0+a/1680.0*x^8.0-a/60.0*x^5.0-2.0833333333333335*x^4.0";
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

    public static void TestCaseDiff4019() {
        //Test Case 4019
        UsageCalculus.TestCase = 4019;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2^a*x^8 + 2^a*x^5 + 2^a*x^4 - 2*x*2^a- 25*2*3^a";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-3.0^a*50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-3.0^a*50.0*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-3.0^a*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-3.0^a*50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "2.0^a/9.0*x^9.0+2.0^a/6.0*x^6.0+2.0^a/5.0*x^5.0-2.0^a*x^2.0-3.0^a*50.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "2.0^a/9.0*x^9.0+2.0^a/6.0*x^6.0+2.0^a/5.0*x^5.0-2.0^a*x^2.0-3.0^a*50.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-3.0^a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*x^6.0+2.0^a*5.0*4.0*x^3.0+2.0^a*4.0*3.0*x^2.0-2.0^a*2.0*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0-2.0^a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "2.0^a/9.0*x^9.0+2.0^a/6.0*x^6.0+2.0^a/5.0*x^5.0-2.0^a*x^2.0-3.0^a*50.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "2.0^a/9.0/10.0*x^10.0+2.0^a/6.0/7.0*x^7.0+2.0^a/5.0/6.0*x^6.0-2.0^a/3.0*x^3.0-3.0^a*50.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "2.0^a/90.0*x^10.0+2.0^a/42.0*x^7.0+2.0^a/30.0*x^6.0-2.0^a/3.0*x^3.0-3.0^a*25.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.0^a*56.0*6.0*x^5.0+2.0^a*20.0*3.0*x^2.0+2.0^a*12.0*2.0*x^1.0-2.0^a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*x^5.0+2.0^a*20.0*3.0*x^2.0+2.0^a*12.0*2.0*x^1.0-2.0^a*0.0*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x-2.0^a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "2.0^a/90.0*x^10.0+2.0^a/42.0*x^7.0+2.0^a/30.0*x^6.0-2.0^a/3.0*x^3.0-3.0^a*25.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "2.0^a/90.0/11.0*x^11.0+2.0^a/42.0/8.0*x^8.0+2.0^a/30.0/7.0*x^7.0-2.0^a/3.0/4.0*x^4.0-3.0^a*25.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "2.0^a/990.0*x^11.0+2.0^a/336.0*x^8.0+2.0^a/210.0*x^7.0-2.0^a/12.0*x^4.0-3.0^a*8.333333333333334*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x-2.0^a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*x^4.0+2.0^a*60.0*2.0*x^1.0+2.0^a*24.0-2.0^a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "2.0^a*1680.0*x^4.0+2.0^a*120.0*x+2.0^a*24.0-2.0^a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "2.0^a/990.0*x^11.0+2.0^a/336.0*x^8.0+2.0^a/210.0*x^7.0-2.0^a/12.0*x^4.0-3.0^a*8.333333333333334*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "2.0^a/990.0/12.0*x^12.0+2.0^a/336.0/9.0*x^9.0+2.0^a/210.0/8.0*x^8.0-2.0^a/12.0/5.0*x^5.0-3.0^a*8.333333333333334/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "2.0^a/11880.0*x^12.0+2.0^a/3024.0*x^9.0+2.0^a/1680.0*x^8.0-2.0^a/60.0*x^5.0-3.0^a*2.0833333333333335*x^4.0";
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

    public static void TestCaseDiff4020() {
        //Test Case 4020
        UsageCalculus.TestCase = 4020;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "a^2*x^8 + a^2*x^5 + a^2*x^4 - 2*x*a^2- 25*2*a^3";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "a^2.0*x^8.0+a^2.0*x^5.0+a^2.0*x^4.0-a^2.0*2.0*x-a^3.0*50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a^2.0*8.0*x^7.0+a^2.0*5.0*x^4.0+a^2.0*4.0*x^3.0-a^2.0*2.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "a^2.0*8.0*x^7.0+a^2.0*5.0*x^4.0+a^2.0*4.0*x^3.0-a^2.0*2.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "a^2.0*x^8.0+a^2.0*x^5.0+a^2.0*x^4.0-a^2.0*2.0*x-a^3.0*50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a^2.0/9.0*x^9.0+a^2.0/6.0*x^6.0+a^2.0/5.0*x^5.0-a^2.0*x^2.0-a^3.0*50.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "a^2.0/9.0*x^9.0+a^2.0/6.0*x^6.0+a^2.0/5.0*x^5.0-a^2.0*x^2.0-a^3.0*50.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "a^2.0*8.0*x^7.0+a^2.0*5.0*x^4.0+a^2.0*4.0*x^3.0-a^2.0*2.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "a^2.0*8.0*7.0*x^6.0+a^2.0*5.0*4.0*x^3.0+a^2.0*4.0*3.0*x^2.0-a^2.0*2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a^2.0*56.0*x^6.0+a^2.0*20.0*x^3.0+a^2.0*12.0*x^2.0-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "a^2.0/9.0*x^9.0+a^2.0/6.0*x^6.0+a^2.0/5.0*x^5.0-a^2.0*x^2.0-a^3.0*50.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "a^2.0/9.0/10.0*x^10.0+a^2.0/6.0/7.0*x^7.0+a^2.0/5.0/6.0*x^6.0-a^2.0/3.0*x^3.0-a^3.0*50.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "a^2.0/90.0*x^10.0+a^2.0/42.0*x^7.0+a^2.0/30.0*x^6.0-a^2.0/3.0*x^3.0-a^3.0*25.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a^2.0*56.0*x^6.0+a^2.0*20.0*x^3.0+a^2.0*12.0*x^2.0-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a^2.0*56.0*6.0*x^5.0+a^2.0*20.0*3.0*x^2.0+a^2.0*12.0*2.0*x^1.0-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "a^2.0*336.0*x^5.0+a^2.0*60.0*x^2.0+a^2.0*24.0*x-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "a^2.0/90.0*x^10.0+a^2.0/42.0*x^7.0+a^2.0/30.0*x^6.0-a^2.0/3.0*x^3.0-a^3.0*25.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "a^2.0/90.0/11.0*x^11.0+a^2.0/42.0/8.0*x^8.0+a^2.0/30.0/7.0*x^7.0-a^2.0/3.0/4.0*x^4.0-a^3.0*25.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "a^2.0/990.0*x^11.0+a^2.0/336.0*x^8.0+a^2.0/210.0*x^7.0-a^2.0/12.0*x^4.0-a^3.0*8.333333333333334*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "a^2.0*336.0*x^5.0+a^2.0*60.0*x^2.0+a^2.0*24.0*x-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "a^2.0*336.0*5.0*x^4.0+a^2.0*60.0*2.0*x^1.0+a^2.0*24.0-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a^2.0*1680.0*x^4.0+a^2.0*120.0*x+a^2.0*24.0-a^2.0*0.0-a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "a^2.0/990.0*x^11.0+a^2.0/336.0*x^8.0+a^2.0/210.0*x^7.0-a^2.0/12.0*x^4.0-a^3.0*8.333333333333334*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "a^2.0/990.0/12.0*x^12.0+a^2.0/336.0/9.0*x^9.0+a^2.0/210.0/8.0*x^8.0-a^2.0/12.0/5.0*x^5.0-a^3.0*8.333333333333334/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "a^2.0/11880.0*x^12.0+a^2.0/3024.0*x^9.0+a^2.0/1680.0*x^8.0-a^2.0/60.0*x^5.0-a^3.0*2.0833333333333335*x^4.0";
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

    // Repeat Cycle of 117 to 120 :Operators: * & /
    public static void TestCaseDiff4021() {
        //Test Case 4021
        UsageCalculus.TestCase = 4021;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "p*9.0/xyc1^9.0 + p/6.0/xyc1^6.0 + p*5.0*xyc1^5.0 + p/2.0/xyc1^2.0 + p/25.0/xyc1 + p*50.0";
        UsageCalculus.TestCaseName = "Standard:Independent Constant:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID = "Unit TestCase";

        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "p*9.0/xyc1^9.0+p/6.0/xyc1^6.0+p*5.0*xyc1^5.0+p/2.0/xyc1^2.0+p/25.0/xyc1+p*50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "p*9.0*-9.0/xyc1^10.0+p*6.0*-6.0/xyc1^7.0+p*5.0*5.0*xyc1^4.0+p*2.0*-2.0/xyc1^3.0-p/25.0/xyc1^2.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-p*81.0/xyc1^10.0-p*36.0/xyc1^7.0+p*25.0*xyc1^4.0-p*4.0/xyc1^3.0-p/25.0/xyc1^2.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "p*9.0/xyc1^9.0+p/6.0/xyc1^6.0+p*5.0*xyc1^5.0+p/2.0/xyc1^2.0+p/25.0/xyc1+p*50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "p*9.0/-8.0/xyc1^8.0+p/6.0/-5.0/xyc1^5.0+p*5.0/6.0*xyc1^6.0+p/2.0/-1.0/xyc1^1.0+p/25.0*ln(xyc1)+p*50.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1] = "-p*1.125/xyc1^8.0-p/30.0/xyc1^5.0+p*0.8333333333333334*xyc1^6.0-p/2.0/xyc1+p/25.0*ln(xyc1)+p*50.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-p*81.0/xyc1^10.0-p*36.0/xyc1^7.0+p*25.0*xyc1^4.0-p*4.0/xyc1^3.0-p/25.0/xyc1^2.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-p*81.0*-10.0/xyc1^11.0-p*36.0*-7.0/xyc1^8.0+p*25.0*4.0*xyc1^3.0-p*4.0*-3.0/xyc1^4.0-p*25.0*-2.0/xyc1^3.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "p*810.0/xyc1^11.0+p*252.0/xyc1^8.0+p*100.0*xyc1^3.0+p*12.0/xyc1^4.0+p*50.0/xyc1^3.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "p*810.0/xyc1^11.0+p*252.0/xyc1^8.0+p*100.0*xyc1^3.0+p*12.0/xyc1^4.0+p*50.0/xyc1^3.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "p*810.0*-11.0/xyc1^12.0+p*252.0*-8.0/xyc1^9.0+p*100.0*3.0*xyc1^2.0+p*12.0*-4.0/xyc1^5.0+p*50.0*-3.0/xyc1^4.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-p*8910.0/xyc1^12.0-p*2016.0/xyc1^9.0+p*300.0*xyc1^2.0-p*48.0/xyc1^5.0-p*150.0/xyc1^4.0+p*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-p*8910.0/xyc1^12.0-p*2016.0/xyc1^9.0+p*300.0*xyc1^2.0-p*48.0/xyc1^5.0-p*150.0/xyc1^4.0+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-p*8910.0*-12.0/xyc1^13.0-p*2016.0*-9.0/xyc1^10.0+p*300.0*2.0*xyc1^1.0-p*48.0*-5.0/xyc1^6.0-p*150.0*-4.0/xyc1^5.0+p*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "p*106920.0/xyc1^13.0+p*18144.0/xyc1^10.0+p*600.0*xyc1+p*240.0/xyc1^6.0+p*600.0/xyc1^5.0+p*0.0";
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
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
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

    public static void TestCaseDiff4022() {
        //Test Case 4022
        UsageCalculus.TestCase = 4022;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "a/x^8 + a/x^5 + a/x^4 - 2/a/x- 25*(2)";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "a/x^8.0+a/x^5.0+a/x^4.0-2.0/a/x-50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a*-8.0/x^9.0+a*-5.0/x^6.0+a*-4.0/x^5.0+2.0/a/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-a*8.0/x^9.0-a*5.0/x^6.0-a*4.0/x^5.0+2.0/a/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "a/x^8.0+a/x^5.0+a/x^4.0-2.0/a/x-50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a/-7.0/x^7.0+a/-4.0/x^4.0+a/-3.0/x^3.0-2.0/a*ln(x)-50.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-a/7.0/x^7.0-a/4.0/x^4.0-a/3.0/x^3.0-2.0/a*ln(x)-50.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-a*8.0/x^9.0-a*5.0/x^6.0-a*4.0/x^5.0+2.0/a/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-a*8.0*-9.0/x^10.0-a*5.0*-6.0/x^7.0-a*4.0*-5.0/x^6.0+2.0/a*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a*72.0/x^10.0+a*30.0/x^7.0+a*20.0/x^6.0-4.0/a/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a*72.0/x^10.0+a*30.0/x^7.0+a*20.0/x^6.0-4.0/a/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a*72.0*-10.0/x^11.0+a*30.0*-7.0/x^8.0+a*20.0*-6.0/x^7.0-4.0/a*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-a*720.0/x^11.0-a*210.0/x^8.0-a*120.0/x^7.0+12.0/a/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-a*720.0/x^11.0-a*210.0/x^8.0-a*120.0/x^7.0+12.0/a/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-a*720.0*-11.0/x^12.0-a*210.0*-8.0/x^9.0-a*120.0*-7.0/x^8.0+12.0/a*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a*7920.0/x^12.0+a*1680.0/x^9.0+a*840.0/x^8.0-48.0/a/x^5.0";
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

    public static void TestCaseDiff4023() {
        //Test Case 4023
        UsageCalculus.TestCase = 4023;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2^a/x^8 + 2^a/x^5 + 2^a/x^4 - 2/2^a/x- 25/2/3^a";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "2.0^a/x^8.0+2.0^a/x^5.0+2.0^a/x^4.0-1.0/2.0^a*2.0/x-1.0/3.0^a*12.5";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "2.0^a*-8.0/x^9.0+2.0^a*-5.0/x^6.0+2.0^a*-4.0/x^5.0+1.0/2.0^a*2.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-2.0^a*8.0/x^9.0-2.0^a*5.0/x^6.0-2.0^a*4.0/x^5.0+1.0/2.0^a*2.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "2.0^a*1.0/x^8.0+2.0^a*1.0/x^5.0+2.0^a*1.0/x^4.0-1.0/2.0^a*2.0/x-1.0/3.0^a*12.5";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "2.0^a/-7.0/x^7.0+2.0^a/-4.0/x^4.0+2.0^a/-3.0/x^3.0-1.0/2.0^a*2.0*ln(x)-1.0/3.0^a*12.5*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-2.0^a/7.0/x^7.0-2.0^a/4.0/x^4.0-2.0^a/3.0/x^3.0-1.0/2.0^a*2.0*ln(x)-1.0/3.0^a*12.5*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-2.0^a*8.0/x^9.0-2.0^a*5.0/x^6.0-2.0^a*4.0/x^5.0+1.0/2.0^a*2.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-2.0^a*8.0*-9.0/x^10.0-2.0^a*5.0*-6.0/x^7.0-2.0^a*4.0*-5.0/x^6.0+1.0/2.0^a*2.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.0^a*72.0/x^10.0+2.0^a*30.0/x^7.0+2.0^a*20.0/x^6.0-1.0/2.0^a*4.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.0^a*72.0/x^10.0+2.0^a*30.0/x^7.0+2.0^a*20.0/x^6.0-1.0/2.0^a*4.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.0^a*72.0*-10.0/x^11.0+2.0^a*30.0*-7.0/x^8.0+2.0^a*20.0*-6.0/x^7.0-1.0/2.0^a*4.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-2.0^a*720.0/x^11.0-2.0^a*210.0/x^8.0-2.0^a*120.0/x^7.0+1.0/2.0^a*12.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-2.0^a*720.0/x^11.0-2.0^a*210.0/x^8.0-2.0^a*120.0/x^7.0+1.0/2.0^a*12.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-2.0^a*720.0*-11.0/x^12.0-2.0^a*210.0*-8.0/x^9.0-2.0^a*120.0*-7.0/x^8.0+1.0/2.0^a*12.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "2.0^a*7920.0/x^12.0+2.0^a*1680.0/x^9.0+2.0^a*840.0/x^8.0-1.0/2.0^a*48.0/x^5.0";
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

    public static void TestCaseDiff4024() {
        //Test Case 4024
        UsageCalculus.TestCase = 4024;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "a^2/x^8 + a^2/x^5 + a^2/x^4 - 2/a^2*x- 25*2/a^3";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "a^2.0/x^8.0+a^2.0/x^5.0+a^2.0/x^4.0-2.0/a^2.0*x-50.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a^2.0*-8.0/x^9.0+a^2.0*-5.0/x^6.0+a^2.0*-4.0/x^5.0-2.0/a^2.0-50.0/a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-a^2.0*8.0/x^9.0-a^2.0*5.0/x^6.0-a^2.0*4.0/x^5.0-2.0/a^2.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "a^2.0/x^8.0+a^2.0/x^5.0+a^2.0/x^4.0-2.0/a^2.0*x-50.0/a^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a^2.0/-7.0/x^7.0+a^2.0/-4.0/x^4.0+a^2.0/-3.0/x^3.0-2.0/a^2.0/2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-a^2.0/7.0/x^7.0-a^2.0/4.0/x^4.0-a^2.0/3.0/x^3.0-1.0/a^2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-a^2.0*8.0/x^9.0-a^2.0*5.0/x^6.0-a^2.0*4.0/x^5.0-2.0/a^2.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-a^2.0*8.0*-9.0/x^10.0-a^2.0*5.0*-6.0/x^7.0-a^2.0*4.0*-5.0/x^6.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a^2.0*72.0/x^10.0+a^2.0*30.0/x^7.0+a^2.0*20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-a^2.0/7.0/x^7.0-a^2.0/4.0/x^4.0-a^2.0/3.0/x^3.0-1.0/a^2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-a^2.0/7.0/-6.0/x^6.0-a^2.0/4.0/-3.0/x^3.0-a^2.0/3.0/-2.0/x^2.0-1.0/a^2.0/3.0*x^3.0-50.0/a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "a^2.0/42.0/x^6.0+a^2.0/12.0/x^3.0+a^2.0/6.0/x^2.0-3.0/a^2.0*x^3.0-25.0/a*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a^2.0*72.0/x^10.0+a^2.0*30.0/x^7.0+a^2.0*20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a^2.0*72.0*-10.0/x^11.0+a^2.0*30.0*-7.0/x^8.0+a^2.0*20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-a^2.0*720.0/x^11.0-a^2.0*210.0/x^8.0-a^2.0*120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3] = "a^2.0/42.0/x^6.0+a^2.0/12.0/x^3.0+a^2.0/6.0/x^2.0-3.0/a^2.0*x^3.0-25.0/a*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "a^2.0/42.0/-5.0/x^5.0+a^2.0/12.0/-2.0/x^2.0+a^2.0/6.0/-1.0/x^1.0-3.0/a^2.0/4.0*x^4.0-25.0/a/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-a^2.0/210.00000000000003/x^5.0-a^2.0/24.0/x^2.0-a^2.0/6.0*x-0.75/a^2.0*x^4.0-8.333333333333334/a*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-a^2.0*720.0/x^11.0-a^2.0*210.0/x^8.0-a^2.0*120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-a^2.0*720.0*-11.0/x^12.0-a^2.0*210.0*-8.0/x^9.0-a^2.0*120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a^2.0*7920.0/x^12.0+a^2.0*1680.0/x^9.0+a^2.0*840.0/x^8.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-a^2.0/210.00000000000003/x^5.0-a^2.0/24.0/x^2.0-a^2.0/6.0*x-0.75/a^2.0*x^4.0-8.333333333333334/a*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-a^2.0/210.00000000000003/-4.0/x^4.0-a^2.0/24.0/-1.0/x^1.0-a^2.0/6.0/2.0*x^2.0-0.75/a^2.0/5.0*x^5.0-8.333333333333334/a*x";
        UsageCalculus.EqnSolArrayMap4[4] = "a^2.0/840.0000000000001/x^4.0+a^2.0/24.0*x-a^2.0/12.0*x^2.0-0.15/a^2.0*x^5.0-8.333333333333334/a*x";
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

    public static void TestCaseDiff4025() {
        //Test Case 4025
        UsageCalculus.TestCase = 4025;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "a^2/x^8 + a^2/x^5 + a^2/x^4 - 2*a^2*x- 25*2/a^3";
        UsageCalculus.TestCaseName = "Simple: IndConstant";
        UsageCalculus.TestCaseMatrixID = "IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "a^2.0/x^8.0+a^2.0/x^5.0+a^2.0/x^4.0-a^2.0*2.0*x-50.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a^2.0*-8.0/x^9.0+a^2.0*-5.0/x^6.0+a^2.0*-4.0/x^5.0-a^2.0*2.0-50.0/a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-a^2.0*8.0/x^9.0-a^2.0*5.0/x^6.0-a^2.0*4.0/x^5.0-a^2.0*2.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayMap3[1] = "a^2.0/x^8.0+a^2.0/x^5.0+a^2.0/x^4.0-a^2.0*2.0*x-50.0/a^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a^2.0/-7.0/x^7.0+a^2.0/-4.0/x^4.0+a^2.0/-3.0/x^3.0-a^2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-a^2.0/7.0/x^7.0-a^2.0/4.0/x^4.0-a^2.0/3.0/x^3.0-a^2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-a^2.0*8.0/x^9.0-a^2.0*5.0/x^6.0-a^2.0*4.0/x^5.0-a^2.0*2.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "=-a^2.0*8.0*-9.0/x^10.0-a^2.0*5.0*-6.0/x^7.0-a^2.0*4.0*-5.0/x^6.0-a^2.0*2.0*0.0-0.0/a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a^2.0*72.0/x^10.0+a^2.0*30.0/x^7.0+a^2.0*20.0/x^6.0-a^2.0*0.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-a^2.0/7.0/x^7.0-a^2.0/4.0/x^4.0-a^2.0/3.0/x^3.0-a^2.0*x^2.0-50.0/a*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-a^2.0/7.0/-6.0/x^6.0-a^2.0/4.0/-3.0/x^3.0-a^2.0/3.0/-2.0/x^2.0-a^2.0/3.0*x^3.0-50.0/a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "a^2.0/42.0/x^6.0+a^2.0/12.0/x^3.0+a^2.0/6.0/x^2.0-a^2.0/3.0*x^3.0-25.0/a*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a^2.0*72.0/x^10.0+a^2.0*30.0/x^7.0+a^2.0*20.0/x^6.0-a^2.0*0.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a^2.0*72.0*-10.0/x^11.0+a^2.0*30.0*-7.0/x^8.0+a^2.0*20.0*-6.0/x^7.0-a^2.0*0.0-0.0/a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-a^2.0*720.0/x^11.0-a^2.0*210.0/x^8.0-a^2.0*120.0/x^7.0-a^2.0*0.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayMap3[3] = "a^2.0/42.0/x^6.0+a^2.0/12.0/x^3.0+a^2.0/6.0/x^2.0-a^2.0/3.0*x^3.0-25.0/a*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "a^2.0/42.0/-5.0/x^5.0+a^2.0/12.0/-2.0/x^2.0+a^2.0/6.0/-1.0/x^1.0-a^2.0/3.0/4.0*x^4.0-25.0/a/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-a^2.0/210.00000000000003/x^5.0-a^2.0/24.0/x^2.0-a^2.0/6.0*x-a^2.0/12.0*x^4.0-8.333333333333334/a*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-a^2.0*720.0/x^11.0-a^2.0*210.0/x^8.0-a^2.0*120.0/x^7.0-a^2.0*0.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-a^2.0*720.0*-11.0/x^12.0-a^2.0*210.0*-8.0/x^9.0-a^2.0*120.0*-7.0/x^8.0-a^2.0*0.0-0.0/a^3.0*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a^2.0*7920.0/x^12.0+a^2.0*1680.0/x^9.0+a^2.0*840.0/x^8.0-a^2.0*0.0-0.0/a^3.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-a^2.0/210.00000000000003/x^5.0-a^2.0/24.0/x^2.0-a^2.0/6.0*x-a^2.0/12.0*x^4.0-8.333333333333334/a*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-a^2.0/210.00000000000003/-4.0/x^4.0-a^2.0/24.0/-1.0/x^1.0-a^2.0/6.0/2.0*x^2.0-a^2.0/12.0/5.0*x^5.0-8.333333333333334/a*x";
        UsageCalculus.EqnSolArrayMap4[4] = "a^2.0/840.0000000000001/x^4.0+a^2.0/24.0*x-a^2.0/12.0*x^2.0-a^2.0/60.0*x^5.0-8.333333333333334/a*x";
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

    public static void TestCaseDiff4026() {
        //Test Case 4026
        UsageCalculus.TestCase = 4026;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0*x^8 + 0.0*x^5 + 0.0*x^4 - 0.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "0.0/9.0*x^9.0+0.0/6.0*x^6.0+0.0/5.0*x^5.0-0.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.0/10.0*x^10.0+0.0/7.0*x^7.0+0.0/6.0*x^6.0-0.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.0/11.0*x^11.0+0.0/8.0*x^8.0+0.0/7.0*x^7.0-0.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0/12.0*x^12.0+0.0/9.0*x^9.0+0.0/8.0*x^8.0-0.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.0*x^12.0+0.0*x^9.0+0.0*x^8.0-0.0*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff4027() {
        //Test Case 4027
        UsageCalculus.TestCase = 4027;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0/x^8 + 0.0/x^5 + 0.0/x^4 - 0.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0*-8.0/x^9.0+0.0*-5.0/x^6.0+0.0*-4.0/x^5.0+0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "0.0/-7.0/x^7.0+0.0/-4.0/x^4.0+0.0/-3.0/x^3.0-0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-0.0/x^7.0-0.0/x^4.0-0.0/x^3.0-0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0*-9.0/x^10.0-0.0*-6.0/x^7.0-0.0*-5.0/x^6.0+0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0*-10.0/x^11.0+0.0*-7.0/x^8.0+0.0*-6.0/x^7.0-0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-0.0*-11.0/x^12.0-0.0*-8.0/x^9.0-0.0*-7.0/x^8.0+0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "0.0/x^12.0+0.0/x^9.0+0.0/x^8.0-0.0/x^5.0";
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

    public static void TestCaseDiff4028() {
        //Test Case 4028
        UsageCalculus.TestCase = 4028;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*x^8 + 1.0*x^5 + 1.0*x^4 - 1.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff4029() {
        //Test Case 4029
        UsageCalculus.TestCase = 4029;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/x^8 + 1.0/x^5 + 1.0/x^4 - 1.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0-2.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0+6.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0-24.0/x^5.0";
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

    public static void TestCaseDiff4030() {
        //Test Case 4030
        UsageCalculus.TestCase = 4030;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*+2*xyc1^8*5^xyc1 + 1.0*+2*xyc1^5*5^xyc1 + 1.0*+2*xyc1^4*5^xyc1 + 1.0*+2*xyc1*5^xyc1 + 1.0*+2*25*5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5^xyc1:";
        UsageCalculus.TestCaseMatrixID = "Coefficients:5^xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 2;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "2.0*xyc1^8.0*5.0^xyc1+2.0*xyc1^5.0*5.0^xyc1+2.0*xyc1^4.0*5.0^xyc1+2.0*xyc1*5.0^xyc1+250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0^xyc1*2.0*8.0*xyc1^7.0+ln(5.0)*5.0^xyc1*2.0*xyc1^8.0+5.0^xyc1*2.0*5.0*xyc1^4.0+ln(5.0)*5.0^xyc1*2.0*xyc1^5.0+5.0^xyc1*2.0*4.0*xyc1^3.0+ln(5.0)*5.0^xyc1*2.0*xyc1^4.0+5.0^xyc1*2.0+ln(5.0)*5.0^xyc1*2.0*xyc1+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        UsageCalculus.EqnSolArrayMap3[1] = "2.0*xyc1^8.0*5.0^xyc1+2.0*xyc1^5.0*5.0^xyc1+2.0*xyc1^4.0*5.0^xyc1+2.0*xyc1*5.0^xyc1+250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "2.0/9.0*xyc1^9.0/ln(5.0)*5.0^xyc1+2.0/6.0*xyc1^6.0/ln(5.0)*5.0^xyc1+2.0/5.0*xyc1^5.0/ln(5.0)*5.0^xyc1+10.0/2.0*xyc1^2.0/ln(5.0)*5.0^xyc1+250.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1] = "0.2222222222222222/ln(5.0)*5.0^xyc1*xyc1^9.0+0.3333333333333333/ln(5.0)*5.0^xyc1*xyc1^6.0+0.4/ln(5.0)*5.0^xyc1*xyc1^5.0+5.0/ln(5.0)*5.0^xyc1*xyc1^2.0+250.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "80.0*7.0*xyc1^6.0+50.0*4.0*xyc1^3.0+40.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "560.0*6.0*xyc1^5.0+200.0*3.0*xyc1^2.0+120.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3360.0*5.0*xyc1^4.0+600.0*2.0*xyc1^1.0+240.0";
        UsageCalculus.EqnSolArrayMap2[4] = "16800.0*xyc1^4.0+1200.0*xyc1+240.0";
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

    public static void TestCaseDiff4031() {
        //Test Case 4031
        UsageCalculus.TestCase = 4031;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-0/xy^8 + 1.0*-0/xy^5 + 1.0*-0/xy^4 - 1.0*-0/xy - 25*-0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/xy^9.0-0.0*-5.0/xy^6.0-0.0*-4.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-0.0/-7.0/xy^7.0-0.0/-4.0/xy^4.0-0.0/-3.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolArrayMap4[1] = "0.0/xy^7.0+0.0/xy^4.0+0.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/xy^10.0+0.0*-6.0/xy^7.0+0.0*-5.0/xy^6.0-0.0*-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/xy^11.0-0.0*-7.0/xy^8.0-0.0*-6.0/xy^7.0+0.0*-3.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/xy^12.0+0.0*-8.0/xy^9.0+0.0*-7.0/xy^8.0-0.0*-4.0/xy^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/xy^12.0-0.0/xy^9.0-0.0/xy^8.0+0.0/xy^5.0";
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

    public static void TestCaseDiff4032() {
        //Test Case 4032
        UsageCalculus.TestCase = 4032;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*+1/xyc^8 + 1.0*+1/xyc^5 + 1*+1/xyc^4 - 1*+1/xyc - 1*+1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1:MAP";
        UsageCalculus.TestCaseMatrixID = "Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";

        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 5;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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

    //IndConstant Test End
    /* **************************************** */
 /* ***************Exponent Test Case***************** */
 /* **************************************** */
    public static void TestCaseDiff30001() {
        //Test Case 30001
        UsageCalculus.TestCase = 30001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^8 + x^5 + x^4 - x- 25";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:+ve Numbers:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30002() {
        //Test Case 30002
        UsageCalculus.TestCase = 30002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^-8 + x^-5 + x^-4 - x^-1- 25";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:-ve Numbers:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0+1.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0-2.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x+6.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x+6.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0+6.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0-24.0/x^5.0";
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

    public static void TestCaseDiff30003() {
        //Test Case 30003
        UsageCalculus.TestCase = 30003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^0 + x^0 + x^0 - x^0- 25^0";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:0:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^0.0+x^0.0+x^0.0-x^0.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "x^0.0+x^0.0+x^0.0-x^0.0-1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0*x+1.0*x+1.0*x-1.0*x-1.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "x+x+x-x-x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "x+x+x-x-x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-0.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-0.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-0.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-0.041666666666666664*x^4.0";
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

    public static void TestCaseDiff30004() {
        //Test Case 30004
        UsageCalculus.TestCase = 30004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^1 + x^1 + x^1 - x^1- 25^1";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:1:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30005() {
        //Test Case 30005
        UsageCalculus.TestCase = 30005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^-1 + x^-1 + x^-1 - x^-1- 25^-1";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:-1:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0/x+1.0/x+1.0/x-1.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0/x+1.0/x+1.0/x-1.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "ln(x)+ln(x)+ln(x)-ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "ln(x)+ln(x)+ln(x)-ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.0*-3.0/x^4.0+2.0*-3.0/x^4.0+2.0*-3.0/x^4.0-2.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-6.0/x^4.0-6.0/x^4.0-6.0/x^4.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-6.0/x^4.0-6.0/x^4.0-6.0/x^4.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-6.0*-4.0/x^5.0-6.0*-4.0/x^5.0-6.0*-4.0/x^5.0+6.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0/x^5.0+24.0/x^5.0+24.0/x^5.0-24.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30006() {
        //Test Case 30006
        UsageCalculus.TestCase = 30006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^a + x^a + x^a - x^a- 25^a";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^a+x^a+x^a-x^a-25.0^a";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a*x^(a-1.0)+a*x^(a-1.0)+a*x^(a-1.0)-a*x^(a-1.0)-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30007() {
        //Test Case 30007
        UsageCalculus.TestCase = 30007;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^-a + x^-a + x^-a - x^-a- 25^-a";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^-a+x^-a+x^-a-x^-a-a";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30008() {
        //Test Case 30008
        UsageCalculus.TestCase = 30008;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^a^2 + x^-a + x^-a^-2 - x^a^0- 25^a^0";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30009() {
        //Test Case 30009
        UsageCalculus.TestCase = 30009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^+1 + x^+1 + x^+1 - x^+1- 25^+1";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:+1:* Operator:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30010() {
        //Test Case 30009
        UsageCalculus.TestCase = 30010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^+1.0 + x^+1.0 + x^+1.0 - x^+1.0- 25^+1.0";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:+1.0:* Operator:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x+x+x-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff30011() {
        //Test Case 30011
        UsageCalculus.TestCase = 30011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^+5 + x^+5 + x^+5 - x^+5- 25^+5";
        UsageCalculus.TestCaseName = "Simple: Exponent";
        UsageCalculus.TestCaseMatrixID = "Exponent:+5:* Operator:Constant End:Term Sign +&-:Exponent";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^5.0+x^5.0+x^5.0-x^5.0-9765625.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^5.0+x^5.0+x^5.0-x^5.0-9765625.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/6.0*x^6.0+1.0/6.0*x^6.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0-9765625.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "0.16666666666666666*x^6.0+0.16666666666666666*x^6.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0-9765625.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "5.0*4.0*x^3.0+5.0*4.0*x^3.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "20.0*x^3.0+20.0*x^3.0+20.0*x^3.0-20.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "0.16666666666666666*x^6.0+0.16666666666666666*x^6.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0-9765625.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.16666666666666666/7.0*x^7.0+0.16666666666666666/7.0*x^7.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0-9765625.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.023809523809523808*x^7.0+0.023809523809523808*x^7.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0-4882812.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "20.0*x^3.0+20.0*x^3.0+20.0*x^3.0-20.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "20.0*3.0*x^2.0+20.0*3.0*x^2.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "60.0*x^2.0+60.0*x^2.0+60.0*x^2.0-60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3] = "0.023809523809523808*x^7.0+0.023809523809523808*x^7.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0-4882812.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.023809523809523808/8.0*x^8.0+0.023809523809523808/8.0*x^8.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0-4882812.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.002976190476190476*x^8.0+0.002976190476190476*x^8.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0-1627604.1666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "60.0*x^2.0+60.0*x^2.0+60.0*x^2.0-60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "60.0*2.0*x^1.0+60.0*2.0*x^1.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "120.0*x+120.0*x+120.0*x-120.0*x";
        UsageCalculus.EqnSolArrayMap3[4] = "0.002976190476190476*x^8.0+0.002976190476190476*x^8.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0-1627604.1666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.002976190476190476/9.0*x^9.0+0.002976190476190476/9.0*x^9.0+0.002976190476190476/9.0*x^9.0-0.002976190476190476/9.0*x^9.0-1627604.1666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "3.3068783068783067E-4*x^9.0+3.3068783068783067E-4*x^9.0+3.3068783068783067E-4*x^9.0-3.3068783068783067E-4*x^9.0-406901.0416666667*x^4.0";
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

    /* **************************************** */
 /* ***************Software Engg Test Case***************** */
 /* **************************************** */
    public static void TestCaseDiff110001() {
        //Test Case 110001
        UsageCalculus.TestCase = 110001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^2";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[1] = "2.0*x";
        UsageCalculus.EqnSolArrayMap3[1] = "x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "2.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.3333333333333333/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.08333333333333333*x^4.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.08333333333333333*x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.08333333333333333/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.016666666666666666*x^5.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.016666666666666666*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.016666666666666666/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.002777777777777778*x^6.0";
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

    public static void TestCaseDiff110002() {
        //Test Case 110002
        UsageCalculus.TestCase = 110002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.16666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.041666666666666664*x^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.041666666666666664*x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.041666666666666664/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.008333333333333333*x^5.0";
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

    public static void TestCaseDiff110003() {
        //Test Case 110003
        UsageCalculus.TestCase = 110003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "2.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "2.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "2.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "2.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "1.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.3333333333333333/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.08333333333333333*x^4.0";
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

    public static void TestCaseDiff110004() {
        //Test Case 110004
        UsageCalculus.TestCase = 110004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.16666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.041666666666666664*x^4.0";
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

    public static void TestCaseDiff110005() {
        //Test Case 110005
        UsageCalculus.TestCase = 110005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110006() {
        //Test Case 110006
        UsageCalculus.TestCase = 110006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1] = "1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2] = "x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3] = "1.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4] = "0.16666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.16666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "0.041666666666666664*x^4.0";
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

    public static void TestCaseDiff110007() {
        //Test Case 110007
        UsageCalculus.TestCase = 110007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110008() {
        //Test Case 110008
        UsageCalculus.TestCase = 110008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110009() {
        //Test Case 110009
        UsageCalculus.TestCase = 110009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110010() {
        //Test Case 110010
        UsageCalculus.TestCase = 110010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "*";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110011() {
        //Test Case 110011
        UsageCalculus.TestCase = 110011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "/";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110012() {
        //Test Case 110012
        UsageCalculus.TestCase = 110012;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "^";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

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

    public static void TestCaseDiff110013() {
        //Test Case 110013
        UsageCalculus.TestCase = 110013;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "5*x^8 + 5/x^5 + 6*x^4 - 2*x - 26.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "5.0*x^8.0+5.0/x^5.0+6.0*x^4.0-2.0*x-26.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+5.0*-5.0/x^6.0+6.0*4.0*x^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*x^7.0-25.0/x^6.0+24.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "5.0*x^8.0+5.0/x^5.0+6.0*x^4.0-2.0*x-26.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "5.0/9.0*x^9.0+5.0/-4.0/x^4.0+6.0/5.0*x^5.0-x^2.0-26.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "0.5555555555555556*x^9.0-1.25/x^4.0+1.2*x^5.0-x^2.0-26.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*x^7.0-25.0/x^6.0+24.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0-25.0*-6.0/x^7.0+24.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*x^6.0+150.0/x^7.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "0.5555555555555556*x^9.0-1.25/x^4.0+1.2*x^5.0-x^2.0-26.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.5555555555555556/10.0*x^10.0-1.25/-3.0/x^3.0+1.2/6.0*x^6.0-1.0/3.0*x^3.0-26.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "0.05555555555555556*x^10.0+0.4166666666666667/x^3.0+0.19999999999999998*x^6.0-1.0/3.0*x^3.0-13.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*x^6.0+150.0/x^7.0+72.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+150.0*-7.0/x^8.0+72.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^5.0-1050.0/x^8.0+144.0*x";
        UsageCalculus.EqnSolArrayMap3[3] = "0.05555555555555556*x^10.0+0.4166666666666667/x^3.0+0.19999999999999998*x^6.0-1.0/3.0*x^3.0-13.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "0.05555555555555556/11.0*x^11.0+0.4166666666666667/-2.0/x^2.0+0.19999999999999998/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-13.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "0.005050505050505051*x^11.0-0.20833333333333334/x^2.0+0.02857142857142857*x^7.0-0.08333333333333333*x^4.0-4.333333333333333*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^5.0-1050.0/x^8.0+144.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0-1050.0*-8.0/x^9.0+144.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*x^4.0+8400.0/x^9.0+144.0";
        UsageCalculus.EqnSolArrayMap3[4] = "0.005050505050505051*x^11.0-0.20833333333333334/x^2.0+0.02857142857142857*x^7.0-0.08333333333333333*x^4.0-4.333333333333333*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "0.005050505050505051/12.0*x^12.0-0.20833333333333334/-1.0/x^1.0+0.02857142857142857/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-4.333333333333333/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "4.208754208754209E-4*x^12.0+0.20833333333333334/x+0.0035714285714285713*x^8.0-0.016666666666666666*x^5.0-1.0833333333333333*x^4.0";
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

    public static void TestCaseDiff110014() {
        //Test Case 110014
        UsageCalculus.TestCase = 110014;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "a*x^8 + a/x^5 + a*x^4 - a*x - 26.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "a*x^8.0+a/x^5.0+a*x^4.0-a*x-26.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "a*8.0*x^7.0+a*-5.0/x^6.0+a*4.0*x^3.0-a-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "a*8.0*x^7.0-a*5.0/x^6.0+a*4.0*x^3.0-a";
        UsageCalculus.EqnSolArrayMap3[1] = "a*x^8.0+a/x^5.0+a*x^4.0-a*x-26.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "a/9.0*x^9.0+a/-4.0/x^4.0+a/5.0*x^5.0-a/2.0*x^2.0-26.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "a/9.0*x^9.0-a/4.0/x^4.0+a/5.0*x^5.0-a/2.0*x^2.0-26.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "a*8.0*x^7.0-a*5.0/x^6.0+a*4.0*x^3.0-a";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "a*8.0*7.0*x^6.0-a*5.0*-6.0/x^7.0+a*4.0*3.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "a*56.0*x^6.0+a*30.0/x^7.0+a*12.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "a/9.0*x^9.0-a/4.0/x^4.0+a/5.0*x^5.0-a/2.0*x^2.0-26.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "a/9.0/10.0*x^10.0-a/4.0/-3.0/x^3.0+a/5.0/6.0*x^6.0-a/2.0/3.0*x^3.0-26.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "a/90.0*x^10.0+a/12.0/x^3.0+a/30.0*x^6.0-a/6.0*x^3.0-13.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "a*56.0*x^6.0+a*30.0/x^7.0+a*12.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "a*56.0*6.0*x^5.0+a*30.0*-7.0/x^8.0+a*12.0*2.0*x^1.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "a*336.0*x^5.0-a*210.0/x^8.0+a*24.0*x-a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "a/90.0*x^10.0+a/12.0/x^3.0+a/30.0*x^6.0-a/6.0*x^3.0-13.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "a/90.0/11.0*x^11.0+a/12.0/-2.0/x^2.0+a/30.0/7.0*x^7.0-a/6.0/4.0*x^4.0-13.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "a/990.0*x^11.0-a/24.0/x^2.0+a/210.0*x^7.0-a/24.0*x^4.0-4.333333333333333*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "a*336.0*x^5.0-a*210.0/x^8.0+a*24.0*x-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "a*336.0*5.0*x^4.0-a*210.0*-8.0/x^9.0+a*24.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "a*1680.0*x^4.0+a*1680.0/x^9.0+a*24.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "a/990.0*x^11.0-a/24.0/x^2.0+a/210.0*x^7.0-a/24.0*x^4.0-4.333333333333333*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "a/990.0/12.0*x^12.0-a/24.0/-1.0/x^1.0+a/210.0/8.0*x^8.0-a/24.0/5.0*x^5.0-4.333333333333333/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "a/11880.0*x^12.0+a/24.0/x+a/1680.0*x^8.0-a/120.0*x^5.0-1.0833333333333333*x^4.0";
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

    public static void TestCaseDiff110015() {
        //Test Case 110015
        UsageCalculus.TestCase = 110015;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^80.0-x^5.0+x-a";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^80.0-x^5.0+x-a";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "80.0*x^79.0-5.0*x^4.0+1.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "80.0*x^79.0-5.0*x^4.0+1.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^80.0-x^5.0+x-a";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/81.0*x^81.0-1.0/6.0*x^6.0+1.0/2.0*x^2.0-a*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/81.0*x^81.0-1.0/6.0*x^6.0+1.0/2.0*x^2.0-a*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "80.0*x^79.0-5.0*x^4.0+1.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "80.0*79.0*x^78.0-5.0*4.0*x^3.0+0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "6320.0*x^78.0-20.0*x^3.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/81.0*x^81.0-1.0/6.0*x^6.0+1.0/2.0*x^2.0-a*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.012345679012345678/82.0*x^82.0-0.16666666666666666/7.0*x^7.0+0.5/3.0*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.5055706112616682E-4*x^82.0-0.023809523809523808*x^7.0+0.16666666666666666*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "6320.0*x^78.0-20.0*x^3.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "6320.0*78.0*x^77.0-20.0*3.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "492960.0*x^77.0-60.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.5055706112616682E-4*x^82.0-0.023809523809523808*x^7.0+0.16666666666666666*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.5055706112616682E-4/83.0*x^83.0-0.023809523809523808/8.0*x^8.0+0.16666666666666666/4.0*x^4.0-a*2.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.8139404954959858E-6*x^83.0-0.002976190476190476*x^8.0+0.041666666666666664*x^4.0-a*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "492960.0*x^77.0-60.0*x^2.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "492960.0*77.0*x^76.0-60.0*2.0*x^1.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3.795792E7*x^76.0-120.0*x-a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.8139404954959858E-6*x^83.0-0.002976190476190476*x^8.0+0.041666666666666664*x^4.0-a*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.8139404954959858E-6/84.0*x^84.0-0.002976190476190476/9.0*x^9.0+0.041666666666666664/5.0*x^5.0-a*0.6666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "2.1594529708285544E-8*x^84.0-3.3068783068783067E-4*x^9.0+0.008333333333333333*x^5.0-a*0.16666666666666666*x^4.0";
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

    public static void TestCaseDiff110016() {
        //Test Case 110016
        UsageCalculus.TestCase = 110016;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^870.0-x^15.0-2.0*x-a";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^870.0-x^15.0-2.0*x-a";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "870.0*x^869.0-15.0*x^14.0-2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "870.0*x^869.0-15.0*x^14.0-2.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^870.0-x^15.0-2.0*x-a";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/871.0*x^871.0-1.0/16.0*x^16.0-x^2.0-a*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/871.0*x^871.0-1.0/16.0*x^16.0-x^2.0-a*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "870.0*x^869.0-15.0*x^14.0-2.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "870.0*869.0*x^868.0-15.0*14.0*x^13.0-0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "756030.0*x^868.0-210.0*x^13.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/871.0*x^871.0-1.0/16.0*x^16.0-x^2.0-a*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.001148105625717566/872.0*x^872.0-0.0625/17.0*x^17.0-1.0/3.0*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.3166348918779427E-6*x^872.0-0.003676470588235294*x^17.0-1.0/3.0*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "756030.0*x^868.0-210.0*x^13.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "756030.0*868.0*x^867.0-210.0*13.0*x^12.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "6.5623404E8*x^867.0-2730.0*x^12.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.3166348918779427E-6*x^872.0-0.003676470588235294*x^17.0-1.0/3.0*x^3.0-a/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.3166348918779427E-6/873.0*x^873.0-0.003676470588235294/18.0*x^18.0-0.3333333333333333/4.0*x^4.0-a*2.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.5081728429300605E-9*x^873.0-2.042483660130719E-4*x^18.0-0.08333333333333333*x^4.0-a*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "6.5623404E8*x^867.0-2730.0*x^12.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "6.5623404E8*867.0*x^866.0-2730.0*12.0*x^11.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.6895491268E11*x^866.0-32760.0*x^11.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.5081728429300605E-9*x^873.0-2.042483660130719E-4*x^18.0-0.08333333333333333*x^4.0-a*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.5081728429300605E-9/874.0*x^874.0-2.042483660130719E-4/19.0*x^19.0-0.08333333333333333/5.0*x^5.0-a*0.6666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.7255982184554468E-12*x^874.0-1.0749914000687994E-5*x^19.0-0.016666666666666666*x^5.0-a*0.16666666666666666*x^4.0";
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

    public static void TestCaseDiff110017() {
        //Test Case 110017
        UsageCalculus.TestCase = 110017;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0-2.0/x-25";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x-2.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0+2.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0+2.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x-2.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-2.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-2.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0+2.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0+2.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0-4.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "";
        UsageCalculus.EqnSolArrayMap4[2] = "";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0-4.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0-4.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0+12.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "";
        UsageCalculus.EqnSolArrayMap4[3] = "";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0+12.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0+12.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0-48.0/x^5.0";
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

    public static void TestCaseDiff110018() {
        //Test Case 110018
        UsageCalculus.TestCase = 110018;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0+a*x-b";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x+a*x-b";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0+a-b*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0+a-b*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x+a*x-b";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0+a/2.0*x^2.0-b*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0+a/2.0*x^2.0-b*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0+a-b*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0+a/2.0*x^2.0-b*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.011363636363636364/89.0*x^89.0-0.5/3.0*x^3.0+a/2.0/3.0*x^3.0-b/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0+a/6.0*x^3.0-b/2.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0+a/6.0*x^3.0-b/2.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.2768130745658836E-4/90.0*x^90.0-0.16666666666666666/4.0*x^4.0+a/6.0/4.0*x^4.0-b*2.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0+a/24.0*x^4.0-b*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0+a*0.0-b*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0+a/24.0*x^4.0-b*0.6666666666666666*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.418681193962093E-6/91.0*x^91.0-0.041666666666666664/5.0*x^5.0+a/24.0/5.0*x^5.0-b*0.6666666666666666/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.558990323035267E-8*x^91.0-0.008333333333333333*x^5.0+a/120.0*x^5.0-b*0.16666666666666666*x^4.0";
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

    public static void TestCaseDiff110019() {
        //Test Case 110019
        UsageCalculus.TestCase = 110019;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0-x^8*x-19";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x-x^9.0-19.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0-9.0*x^8.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0-9.0*x^8.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x-x^9.0-19.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/10.0*x^10.0-19.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/10.0*x^10.0-19.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0-9.0*x^8.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0-9.0*8.0*x^7.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0-72.0*x^7.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/10.0*x^10.0-19.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.011363636363636364/89.0*x^89.0-0.5/3.0*x^3.0-0.1/11.0*x^11.0-19.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.009090909090909092*x^11.0-9.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0-72.0*x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0-72.0*7.0*x^6.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0-504.0*x^6.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.009090909090909092*x^11.0-9.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.2768130745658836E-4/90.0*x^90.0-0.16666666666666666/4.0*x^4.0-0.009090909090909092/12.0*x^12.0-9.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-7.575757575757577E-4*x^12.0-3.1666666666666665*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0-504.0*x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0-504.0*6.0*x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0-3024.0*x^5.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-7.575757575757577E-4*x^12.0-3.1666666666666665*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.418681193962093E-6/91.0*x^91.0-0.041666666666666664/5.0*x^5.0-7.575757575757577E-4/13.0*x^13.0-3.1666666666666665/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.558990323035267E-8*x^91.0-0.008333333333333333*x^5.0-5.827505827505828E-5*x^13.0-0.7916666666666666*x^4.0";
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

    public static void TestCaseDiff110020() {
        //Test Case 110020
        UsageCalculus.TestCase = 110020;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0-x^0*x-25";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.011363636363636364/89.0*x^89.0-0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.2768130745658836E-4/90.0*x^90.0-0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.418681193962093E-6/91.0*x^91.0-0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.558990323035267E-8*x^91.0-0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff110021() {
        //Test Case 110021
        UsageCalculus.TestCase = 110021;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0-x^1.0*x-25";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x-x^2.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0-2.0*x^1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0-2.0*x";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x-x^2.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0-2.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0-2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.011363636363636364/89.0*x^89.0-0.5/3.0*x^3.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.2768130745658836E-4/90.0*x^90.0-0.16666666666666666/4.0*x^4.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.418681193962093E-6/91.0*x^91.0-0.041666666666666664/5.0*x^5.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.558990323035267E-8*x^91.0-0.008333333333333333*x^5.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff110022() {
        //Test Case 110022
        UsageCalculus.TestCase = 110022;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^87.0-x^1.0-x^1.0*x-25+0.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "x^87.0-x-x^2.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "87.0*x^86.0-1.0-2.0*x^1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "87.0*x^86.0-1.0-2.0*x";
        UsageCalculus.EqnSolArrayMap3[1] = "x^87.0-x-x^2.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "87.0*x^86.0-1.0-2.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "87.0*86.0*x^85.0-0.0-2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7482.0*x^85.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/88.0*x^88.0-1.0/2.0*x^2.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "0.011363636363636364/89.0*x^89.0-0.5/3.0*x^3.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7482.0*x^85.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7482.0*85.0*x^84.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayMap3[3] = "1.2768130745658836E-4*x^89.0-0.16666666666666666*x^3.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "1.2768130745658836E-4/90.0*x^90.0-0.16666666666666666/4.0*x^4.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "635970.0*x^84.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "635970.0*84.0*x^83.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5.342148E7*x^83.0";
        UsageCalculus.EqnSolArrayMap3[4] = "1.418681193962093E-6*x^90.0-0.041666666666666664*x^4.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "1.418681193962093E-6/91.0*x^91.0-0.041666666666666664/5.0*x^5.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "1.558990323035267E-8*x^91.0-0.008333333333333333*x^5.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff110023() {
        //Test Case 110023
        UsageCalculus.TestCase = 110023;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-a-a*0.0";
        UsageCalculus.TestCaseName = "Simple: Product Integration";
        UsageCalculus.TestCaseMatrixID = "Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";

        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;

        UsageCalculus.EqnSolArrayMap1[1] = "-a-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[1] = "-a-a*0.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1] = "-a*x-0.0*x";
        UsageCalculus.EqnSolArrayMap4[1] = "-a*x-0.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[2] = "-a*x-0.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2] = "-a/2.0*x^2.0-0.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2] = "-a/2.0*x^2.0-0.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[3] = "-a/2.0*x^2.0-0.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3] = "-a/2.0/3.0*x^3.0-0.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3] = "-a/6.0*x^3.0-0.0*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-a*0.0-a*0.0";
        UsageCalculus.EqnSolArrayMap3[4] = "-a/6.0*x^3.0-0.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4] = "-a/6.0/4.0*x^4.0-0.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4] = "-a/24.0*x^4.0-0.0*x^4.0";
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
    /* **************************************** */
 /* ***************SoftwareEngg Test Case***************** */
 /* **************************************** */
}
