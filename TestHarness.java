/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parse;
import static DOEMatrixGen.Usage.DiffWithRespTo;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarness {

    public static Logger log = Logger.getLogger(TestHarness.class.getName());

    

    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumCompFractionDigits = 2;
    
    
    TestHarness(int MaxOrder, String All, String MyEndTestCase,String RegrORIndivORLimit) {
        Usage.EqnSolArrayMap1 = new String[MaxOrder + 1];
        Usage.EqnSolArrayDiffAlone = new String[MaxOrder + 1];
        Usage.EqnSolArrayMap2 = new String[MaxOrder + 1];
        Usage.EqnSolArrayMap3= new String[MaxOrder + 1];
        Usage.EqnSolArrayIntegrAlone= new String[MaxOrder + 1];
        Usage.EqnSolArrayMap4= new String[MaxOrder + 1];
        Usage.EqnSolArrayIntegr_BeforeDiff= new String[MaxOrder + 1];
        Usage.EqnSolArrayDiff_AfterIntegr= new String[MaxOrder + 1];
        Usage.EqnSolArrayDiff_BeforeIntegr= new String[MaxOrder + 1];
        Usage.EqnSolArrayIntegr_AfterDiff= new String[MaxOrder + 1];
        
        Usage.EqnSolOrder = new int[MaxOrder + 1];
        for (int i = 0; i <= MaxOrder; i++) {
            Usage.EqnSolArrayMap1[i] = "";
            Usage.EqnSolArrayDiffAlone[i] = "";
            Usage.EqnSolArrayMap2[i] = "";
            Usage.EqnSolArrayMap3[i]="";
            Usage.EqnSolArrayIntegrAlone[i]="";
            Usage.EqnSolArrayMap4[i]="";
            Usage.EqnSolArrayIntegr_BeforeDiff[i]="";
            Usage.EqnSolArrayDiff_AfterIntegr[i]="";
            Usage.EqnSolArrayDiff_BeforeIntegr[i]="";
            Usage.EqnSolArrayIntegr_AfterDiff[i]="";
                    
            Usage.EqnSolOrder[i] = 0;
        }
        Usage.LastTestCaseHarness=110007;
        if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            Usage.TestCase = 100;
            Usage.TestCaseLoopTimes = Usage.LastTestCaseHarness;
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression"))) {
            Usage.TestCase = 100;
            //TestCaseLoopTimes = 80001;
            Usage.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if (RegrORIndivORLimit.equalsIgnoreCase("Individual")) {
            Usage.TestCase = Integer.parseInt(All);
            Usage.TestCaseLoopTimes = 1;
            Usage.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Individual"))) {
            System.out.println(" Wrong Input :All & Individual");
            Usage.TestCase = 100;
            Usage.TestCaseLoopTimes = 1;
            Usage.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            Usage.TestCase = Integer.parseInt(All);
            Usage.TestCaseLoopTimes = Usage.LastTestCaseHarness;
        }else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression"))&& (!MyEndTestCase.equalsIgnoreCase("-1"))) {
            Usage.TestCase = Integer.parseInt(All);
            Usage.TestCaseLoopTimes = Integer.parseInt(MyEndTestCase)-Usage.TestCase+1;
            Usage.EndTestCase=Integer.parseInt(MyEndTestCase);
        }

    }

    public static String TestPredicate() {
        //String Exprn = "0 < = x < 5 "; 
        //String Exprn = "(0 < = x)&&(x < 5 )"; //A&&B
        String Exprn = "(!(0 > x))&&((5 > x )))";//(A'||B')&&(A||B)
        return Exprn;
    }

    

    public static boolean PassFailTestCaseString(int myTestCase, int Order, String DiffORIntegre,String Exprn, String StrMap1, String StrDiff, String StrMap2, String Answer1, String Answer2, String Answer3, String Answer4) {
        boolean ReturnFlagMap1 = false;
        boolean ReturnFlagDiff = false;
        boolean ReturnFlagMap2 = false;
        int TestCase=Usage.TestCase;
        if (TestCase == myTestCase) {
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap1.equalsIgnoreCase(Answer2)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Exprn=" + Exprn  + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " StrMap1=" + StrMap1  + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map1" + ConsoleColors.RESET);
                ReturnFlagMap1 = true;
            } 
//            else if ((Answer2 != null) && (!Answer2.equalsIgnoreCase("")) && ((StrMap1.contains(Answer2)))) {
//                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST (Reduced) Map1" + ConsoleColors.RESET);
//                ReturnFlag = true;
//            } 
            else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Exprn=" + Exprn  + ConsoleColors.RESET);
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
            } 
//            else if ((Answer3 != null) && (!Answer3.equalsIgnoreCase("")) && ((StrDiff.contains(Answer3)))) {
//                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST (Reduced) Diff" + ConsoleColors.RESET);
//                ReturnFlag = true;
//            } 
            else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass STRING TEST " + DiffORIntegre + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff"))
                    System.out.println(ConsoleColors.YELLOW + " main:StrDiff  =" + StrDiff + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.YELLOW + " main:StrIntegr=" + StrDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW     + " main:Answer3  =" + Answer3 + ConsoleColors.RESET);
                ReturnFlagDiff = false;
            }
            // Assumption :New String is bigger than the Actual Result ONLY DIFF being last '-' OR last '+'
            if (StrMap2.equalsIgnoreCase(Answer4)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:StrMap2=" + StrMap2 + ConsoleColors.RESET);
                ReturnFlagMap2 = true;
            } 
            else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL STRING TEST Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:StrMap2=" + StrMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.YELLOW + " main:Answer4=" + Answer4 + ConsoleColors.RESET);
                ReturnFlagMap2 = false;
            }

            return ((ReturnFlagMap1)&&(ReturnFlagDiff)&& (ReturnFlagMap2) );
        }
        return false;
    }
    
    public static boolean PassFailTestCaseValue(int myTestCase, int Order, String DiffORIntegre, String Exprn, Double ExprnexpValue, Double DblexpValueMap1, Double DblexpDiffValue, Double DblexpValueMap2,Map<String, Double> variables) {
        boolean ReturnFlag = false;
        Double Number=ExprnexpValue.doubleValue();
        Double NumberAnsMap2=0.0;
        Double NumberEqnSol=0.0;
        Double NumberDiff=0.0;
        
        NumberFormat formatter = new DecimalFormat();
        MathsContxtLAv0_1_Prod.Expression expValueExprn;
        String DblexpValueAnswerExprn = "";
        String DblexpValueAnswerExprnIntegrity ="";
        String TestAnsVal0="";
        String TestAnsVal5="";
        String TestAnsVal6="";
        String TestAnsVal7="";
        
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(Usage.EqnSolArrayMap1[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprn = "" + expValueExprn.eval();
            Number=expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0=formatter.format(Number);
        } else {
            expValueExprn = parse(Usage.EqnSolArrayMap3[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprn = "" + expValueExprn.eval();
            Number=expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal0=formatter.format(Number);
        }
        MathsContxtLAv0_1_Prod.Expression expValueMap2;
        MathsContxtLAv0_1_Prod.Expression expValueDiffExprn;
        String DblexpValueAnswer3 = "";
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueMap2 = parse(Usage.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo);
            DblexpValueAnswer3 = "" + expValueMap2.eval();
            Number=expValueMap2.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5=formatter.format(Number);
        } else {
            expValueMap2 = parse(Usage.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo);
            DblexpValueAnswer3 = "" + expValueMap2.eval();
            Number=expValueMap2.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal5=formatter.format(Number);
        }
        
        //Answer System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueExprn = parse(Usage.EqnSolArrayMap2[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprnIntegrity = "" + expValueExprn.eval();
            NumberAnsMap2=expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6=formatter.format(NumberAnsMap2);
        } else {
            expValueExprn = parse(Usage.EqnSolArrayMap4[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprnIntegrity = "" + expValueExprn.eval();
            NumberAnsMap2=expValueExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal6=formatter.format(NumberAnsMap2);
        }
        //Diff System
        if (DiffORIntegre.equalsIgnoreCase("Diff")) {
            expValueDiffExprn = parse(Usage.EqnSolArrayDiffAlone[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprnIntegrity = "" + expValueDiffExprn.eval();
            NumberEqnSol=expValueDiffExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7=formatter.format(NumberEqnSol);
        } else {
            expValueDiffExprn = parse(Usage.EqnSolArrayIntegrAlone[Order], variables, DiffWithRespTo);
            DblexpValueAnswerExprnIntegrity = "" + expValueDiffExprn.eval();
            NumberEqnSol=expValueDiffExprn.eval();
            formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
            TestAnsVal7=formatter.format(NumberEqnSol);
        }
        Number=ExprnexpValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal1=formatter.format(Number);
        Number=DblexpValueMap1.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String TestVal2=formatter.format(Number);
        
        NumberDiff=DblexpDiffValue.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String DiffVal3=formatter.format(NumberDiff);
        Number=DblexpValueMap2.doubleValue();
        formatter.setMaximumFractionDigits(MaximumCompFractionDigits);
        String SimpleVal4=formatter.format(Number);
        String Temp="";
        int TestCase=Usage.TestCase;
        if (TestCase == myTestCase) {
            //Value Check with Exprn & Map1
            if (TestVal1.equalsIgnoreCase(TestVal2)) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 +  " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 +  " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Exprn Map1" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal1=" + TestVal1 +  " ExprnexpValue=" + ExprnexpValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " TestVal2=" + TestVal2 +  " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Exprn & Answer(Map1)
            if ((TestVal1.equalsIgnoreCase(TestAnsVal0))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    ||( ( (TestVal1.equalsIgnoreCase("0.0"))||(TestVal1.equalsIgnoreCase("-0.0"))||(TestVal1.equalsIgnoreCase("+0.0"))
                    ||(TestVal1.equalsIgnoreCase("-0"))||(TestVal1.equalsIgnoreCase("+0")) 
                    )
                        && ((TestAnsVal0.equalsIgnoreCase("0.0"))||(TestAnsVal0.equalsIgnoreCase("0"))||(TestAnsVal0.equalsIgnoreCase("-0.0"))||(TestAnsVal0.equalsIgnoreCase("+0.0")) 
                           ||(TestAnsVal0.equalsIgnoreCase("-0"))||(TestAnsVal0.equalsIgnoreCase("+0"))
                    ) )
                    ){
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "Pass VALUE TEST Answer Exprn " + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase  +  " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Order=" + Order + " Status=" + "FAIL VALUE TEST Answer Exprn" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswerExprn=" + DblexpValueAnswerExprn + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase  +  " DblexpValueMap1=" + DblexpValueMap1.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Map2
            if ((DiffVal3.equalsIgnoreCase(SimpleVal4))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    ||( ( (DiffVal3.equalsIgnoreCase("0.0"))||(DiffVal3.equalsIgnoreCase("-0.0"))||(DiffVal3.equalsIgnoreCase("+0.0"))
                    ||(DiffVal3.equalsIgnoreCase("-0"))||(DiffVal3.equalsIgnoreCase("+0")) 
                    )
                        && ((SimpleVal4.equalsIgnoreCase("0.0"))||(SimpleVal4.equalsIgnoreCase("0"))||(SimpleVal4.equalsIgnoreCase("-0.0"))||(SimpleVal4.equalsIgnoreCase("+0.0")) 
                           ||(SimpleVal4.equalsIgnoreCase("-0"))||(SimpleVal4.equalsIgnoreCase("+0"))
                    ) )
                    ) {
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST "+ DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 +  " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST "+ DiffORIntegre + " Map2" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 +  " DblexpDiffValue=" + DblexpDiffValue.doubleValue() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " SimpleVal4=" + SimpleVal4 +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value Check with Diff Exprn & Diff Answer(Map2)
            if ((DiffVal3.equalsIgnoreCase(TestAnsVal5))
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    ||( ( (DiffVal3.equalsIgnoreCase("0.0"))||(DiffVal3.equalsIgnoreCase("-0.0"))||(DiffVal3.equalsIgnoreCase("+0.0"))
                    ||(DiffVal3.equalsIgnoreCase("-0"))||(DiffVal3.equalsIgnoreCase("+0")) 
                    )
                        && ((TestAnsVal5.equalsIgnoreCase("0.0"))||(TestAnsVal5.equalsIgnoreCase("0"))||(TestAnsVal5.equalsIgnoreCase("-0.0"))||(TestAnsVal5.equalsIgnoreCase("+0.0")) 
                           ||(TestAnsVal5.equalsIgnoreCase("-0"))||(TestAnsVal5.equalsIgnoreCase("+0"))
                    ) ) ) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer "+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer "+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST "+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL VALUE TEST "+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DblexpValueAnswer3=" + DblexpValueAnswer3 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }//Value (Answer) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal6.equalsIgnoreCase(DiffVal3))
                    && ((TestAnsVal6.equalsIgnoreCase(SimpleVal4))
                    && ((DblexpValueMap2.doubleValue() == expValueExprn.eval())
                    && ((DblexpValueMap2.doubleValue() == NumberAnsMap2)
                    && (DblexpDiffValue.doubleValue() == NumberDiff))))) 
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                        || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                        || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0"))) 
                    && ((TestAnsVal6.equalsIgnoreCase("0.0")) || (TestAnsVal6.equalsIgnoreCase("0")) || (TestAnsVal6.equalsIgnoreCase("-0.0")) || (TestAnsVal6.equalsIgnoreCase("+0.0"))
                        || (TestAnsVal6.equalsIgnoreCase("-0")) || (TestAnsVal6.equalsIgnoreCase("+0")))
                    ) )  {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Answer Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6+ ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + Usage.EqnSolArrayDiffAlone[Order]);
                }
                else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + Usage.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayDiffAlone[Order]=" + Usage.EqnSolArrayDiffAlone[Order]);
                }
                else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + Usage.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " NumberAnsMap2=" + NumberAnsMap2 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberDiff=" + NumberDiff + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            //Value (Answer-Diff System) Integrity Check with Ans Diff Exprn & Answer(Map2)
            if (((TestAnsVal7.equalsIgnoreCase(DiffVal3))
                        &&((TestAnsVal7.equalsIgnoreCase(SimpleVal4))
                        &&((DblexpValueMap2.doubleValue()==expValueExprn.eval())
                        &&((DblexpValueMap2.doubleValue()==NumberEqnSol)
                        &&(DblexpDiffValue.doubleValue()==NumberEqnSol)
                        ) ) ) )
                    //Zero Integrity Pass Through Check:0.0, -0.0 ,+0.0,-0,+0
                    || (((DiffVal3.equalsIgnoreCase("0.0")) || (DiffVal3.equalsIgnoreCase("-0.0")) || (DiffVal3.equalsIgnoreCase("+0.0"))
                        || (DiffVal3.equalsIgnoreCase("-0")) || (DiffVal3.equalsIgnoreCase("+0")))
                    && ((SimpleVal4.equalsIgnoreCase("0.0")) || (SimpleVal4.equalsIgnoreCase("0")) || (SimpleVal4.equalsIgnoreCase("-0.0")) || (SimpleVal4.equalsIgnoreCase("+0.0"))
                        || (SimpleVal4.equalsIgnoreCase("-0")) || (SimpleVal4.equalsIgnoreCase("+0"))) 
                    && ((TestAnsVal7.equalsIgnoreCase("0.0")) || (TestAnsVal7.equalsIgnoreCase("0")) || (TestAnsVal7.equalsIgnoreCase("-0.0")) || (TestAnsVal7.equalsIgnoreCase("+0.0"))
                        || (TestAnsVal7.equalsIgnoreCase("-0")) || (TestAnsVal7.equalsIgnoreCase("+0")))
                    )) {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone " + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Status=" + "Pass VALUE TEST Diff System Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone " + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + " TestAnsVal7=" + TestAnsVal7 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6=" + TestAnsVal6 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + Usage.EqnSolArrayDiffAlone[Order]);
                }
                else {
                    System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + Usage.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.GREEN + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + ConsoleColors.RESET);
                
                ReturnFlag = true;
            } else {
                if (DiffORIntegre.equalsIgnoreCase("Diff")) 
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayDiffAlone" + ConsoleColors.RESET);
                else
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Status=" + "FAIL Integrity Check TEST Integrity Check"+ DiffORIntegre + " Usage.EqnSolArrayIntegrAlone" + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " DiffVal3=" + DiffVal3 + " TestAnsVal6=" + TestAnsVal6 + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity TestAnsVal6=" + TestAnsVal6 + " TestAnsVal7=" + TestAnsVal7 + " SimpleVal4=" + SimpleVal4 + " DblexpValueAnswerExprnIntegrity=" + DblexpValueAnswerExprnIntegrity + ConsoleColors.RESET);
                if (DiffORIntegre.equalsIgnoreCase("Diff")) {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayDiffAlone[Order]=" + Usage.EqnSolArrayDiffAlone[Order]);
                }
                else {
                    System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity Usage.EqnSolArrayIntegrAlone[Order]=" + Usage.EqnSolArrayIntegrAlone[Order]);
                }
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueAnswer3=" + DblexpValueAnswer3 + " Number=" + Number + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpDiffValue=" + DblexpDiffValue + " NumberUsage.EqnSol=" + NumberEqnSol + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase + " Diff System Integrity DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " expValueExprn.eval()=" + expValueExprn.eval() + ConsoleColors.RESET);
                System.out.println(ConsoleColors.RED + " main:Test Case " + TestCase  +  " DblexpValueMap2=" + DblexpValueMap2.doubleValue() + " :Check if there is E or e or Rounding Of in Double Coefficient" + ConsoleColors.RESET);
                ReturnFlag = false;
            }
            return ReturnFlag;
        }
        return false;
    }
    //Sanity Test Cases
    public static void SanityTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGap(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else {
            switch(j){
            case 100:
                TestCaseDiff100();
                break;
            case 101:
                TestCaseDiff101();
                break;
            case 102:
                TestCaseDiff102();
                break;
            case 103:
                TestCaseDiff103();
                break;
            case 104:
                TestCaseDiff104();
                break;
            case 105:
                TestCaseDiff105();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Names TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
    }
}
    //Exponent Test Cases
    public static void Exponent(String AlgoName, int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGap(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else {
    switch(j)    
        {
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
                System.out.println(ConsoleColors.RED +"Exponent TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
        }
    }
    
    
    //Parenthesis Test Cases
    public static void Parenthesis(String AlgoName, int j) {
        if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGap(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGap(j)))) {
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Infinite Loop GAP" + ConsoleColors.RESET);
        } else {
            switch (j) {

                case 80001:
                    TestCaseDiff80001();
                    break;
                case 80002:
                    TestCaseDiff80002();
                    break;
                case 80003:
                    TestCaseDiff80003();
                    break;
                case 80004:
                    TestCaseDiff80004();
                    break;
                case 80005:
                    TestCaseDiff80005();
                    break;
                case 80006:
                    TestCaseDiff80006();
                    break;
                case 80007:
                    TestCaseDiff80007();
                    break;
                case 80008:
                    TestCaseDiff80008();
                    break;
                case 80009:
                    TestCaseDiff80009();
                    break;
                case 80010:
                    TestCaseDiff80010();
                    break;
                case 80011:
                    TestCaseDiff80011();
                    break;
                case 80012:
                    TestCaseDiff80012();
                    break;
                default:
                    System.out.println(ConsoleColors.RED + "Parenthesis TestCase:j= " + j + ": Gap" + ConsoleColors.RESET);
                    break;
            }
        }
    }
    public static void SoftwareEngg(String AlgoName, int j) {
        if (((AlgoName.equalsIgnoreCase("mainIntegrTest")) && (IntegralGap(j))) || ((AlgoName.equalsIgnoreCase("mainDiffTest")) && (DifferentialGap(j)))) {
            System.out.println(ConsoleColors.RED + "Coefficients TestCase:j= " + j + ": Infinite Loop GAP" + ConsoleColors.RESET);
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
                default:
                    System.out.println(ConsoleColors.RED + "Parenthesis TestCase:j= " + j + ": Gap" + ConsoleColors.RESET);
                    break;
            }
        }
    }
    public static boolean IntegralGap(int j){
        //Infinite Loops
        //if((j==34)||(j==35))
        //    return true;
        //else 
        //    if((j==20016)||(j==20017)||(j==20046)||(j==20047)||(j==20061)||(j==20062)||(j==20076)||(j==20077))
        //      return true;
        //else if ((j==80001)||(j==80003)||(j==80006))
        //    return true;
        //else if ((j==20033)||(j==20034)||(j==20035)||(j==20036)||(j==20037)||(j==20038)||(j==20039))
        //    return true;
        //else if ((j==20062)||(j==20063)||(j==20064))
        //    return true;
        //if ((j==50009)||(j==50026))
        //    return true;
        return false;
    }
    public static boolean DifferentialGap(int j){
        //Infinite Loops
        //if((j==34)||(j==35))
        //    return true;
        //else 
        //if((j==20016)||(j==20017)||(j==20046)||(j==20047)||(j==20061)||(j==20062)||(j==20076)||(j==20077))
        //      return true;
        //else if ((j==80001)||(j==80006))
        //    return true;
        return false;
    }
    public static void TestCaseDiff100() {
        //Test Case 100
        Usage.TestCase = 100;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + x^5 + x^4 - x - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation";
        Usage.TestCaseMatrixID="Sanity TestCase";
       
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        //Usage.EqnUnderTest="8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*x^2.0";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolArrayIntegrAlone[8]="";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolArrayIntegrAlone[9]="";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolArrayIntegrAlone[10]="";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolArrayIntegrAlone[11]="";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolArrayIntegrAlone[12]="";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolArrayIntegrAlone[13]="";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolArrayIntegrAlone[14]="";
        Usage.EqnSolOrder[14] = 14;
    }
    //Palindromic String-Equation Test
    public static void TestCaseDiff101() {
        //Palindromic String-Equation Test
        //Test Case 101
        Usage.TestCase = 101;
        Usage.EqnRegressionDiffStatus = "All Green Including Values Except 6th Order Onwards";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        Usage.EqnUnderTest = " -25.0+x+x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        //Usage.EqnUnderTest="-120.0-480.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.TestCaseName = "Standard:Palindromic String Equation:";
        Usage.TestCaseMatrixID="Sanity TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-0.0+1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x+0.5*x^2.0+0.2*x^5.0-0.16666666666666666*x^6.0-0.1111111111111111*x^9.0+0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0+4.0*3.0*x^2.0-5.0*4.0*x^3.0-8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="-25.0*x+0.5*x^2.0+0.2*x^5.0-0.16666666666666666*x^6.0-0.1111111111111111*x^9.0+0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0+0.5/3.0*x^3.0+0.2/6.0*x^6.0-0.16666666666666666/7.0*x^7.0-0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0-20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0+0.03333333333333333/7.0*x^7.0-0.023809523809523808/8.0*x^8.0-0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "24.0-60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0+0.0047619047619047615/8.0*x^8.0-0.002976190476190476/9.0*x^9.0-0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0+0.008333333333333333*x^5.0+5.952380952380952E-4*x^8.0-3.3068783068783067E-4*x^9.0-8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "0.0-120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "0.0-6720.0*3.0*x^2.0+6720.0*3.0^x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "-20160.0*x^2.0+20160.0*x^2.0+";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff102() {
        //Test Case 102
        Usage.TestCase = 102;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "9*x^8 + 7*x^5 + 6*x^4 - 4*x - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation";
        Usage.TestCaseMatrixID="Sanity TestCase";
       
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*8.0*x^7.0+7.0*5.0*x^4.0+6.0*4.0*x^3.0-4.0-0.0";
        Usage.EqnSolArrayMap2[1] = "72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        Usage.EqnSolArrayMap3[1]="9.0*x^8.0+7.0*x^5.0+6.0*x^4.0-4.0*x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="9.0/9.0*x^9.0+7.0/6.0*x^6.0+6.0/5.0*x^5.0-4.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "72.0*x^7.0+35.0*x^4.0+24.0*x^3.0-4.0";
        Usage.EqnSolArrayDiffAlone[2] = "72.0*7.0*x^6.0+35.0*4.0*x^3.0+24.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="x^9.0+1.1666666666666667*x^6.0+1.2*x^5.0-2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/10.0*x^10.0+1.1666666666666667/7.0*x^7.0+1.2/6.0*x^6.0-2.0/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.1*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "504.0*x^6.0+140.0*x^3.0+72.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "504.0*6.0*x^5.0+140.0*3.0*x^2.0+72.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "3024.0*x^5.0+420.0*x^2.0+144.0*x";
        Usage.EqnSolArrayMap3[3]="0.1*x^10.0+0.16666666666666669*x^7.0+0.19999999999999998*x^6.0-0.6666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.1/11.0*x^11.0+0.16666666666666669/8.0*x^8.0+0.19999999999999998/7.0*x^7.0-0.6666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "3024.0*x^5.0+420.0*x^2.0+144.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "3024.0*5.0*x^4.0+420.0*2.0*x^1.0+144.0";
        Usage.EqnSolArrayMap2[4] = "15120.0*x^4.0+840.0*x+144.0";
        Usage.EqnSolArrayMap3[4]="0.009090909090909092*x^11.0+0.020833333333333336*x^8.0+0.02857142857142857*x^7.0-0.16666666666666666*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.009090909090909092/12.0*x^12.0+0.020833333333333336/9.0*x^9.0+0.02857142857142857/8.0*x^8.0-0.16666666666666666/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="7.575757575757577E-4*x^12.0+0.002314814814814815*x^9.0+0.0035714285714285713*x^8.0-0.03333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolArrayIntegrAlone[8]="";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolArrayIntegrAlone[9]="";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolArrayIntegrAlone[10]="";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolArrayIntegrAlone[11]="";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolArrayIntegrAlone[12]="";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolArrayIntegrAlone[13]="";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolArrayIntegrAlone[14]="";
        Usage.EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff103() {
        //Test Case 103
        Usage.TestCase = 103;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        Usage.EqnUnderTest = "1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        Usage.TestCaseMatrixID="Sanity TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        Usage.EqnSolArrayMap3[1]="1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285/x^7.0-0.25/x^4.0-0.3333333333333333/x^3.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0";
        Usage.EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        Usage.EqnSolArrayMap3[2]="-0.14285714285714285/x^7.0-0.25/x^4.0-0.3333333333333333/x^3.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-0.14285714285714285/-6.0/x^6.0-0.25/-3.0/x^3.0-0.3333333333333333/-2.0/x^2.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        Usage.EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        Usage.EqnSolArrayMap3[3]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.023809523809523808/-5.0/x^5.0+0.08333333333333333/-2.0/x^2.0+0.16666666666666666/-1.0/x^1.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        Usage.EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
        Usage.EqnSolArrayMap3[4]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-0.0047619047619047615/-4.0/x^4.0-0.041666666666666664/-1.0/x^1.0-0.16666666666666666*ln(x)-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.0011904761904761904/x^4.0+0.041666666666666664/x-0.16666666666666666*ln(x)-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff104() {
        //Only Trailing Constant
        //Test Case 104
        Usage.TestCase = 104;
        Usage.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnUnderTest = "x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0";
        Usage.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        Usage.TestCaseMatrixID="Sanity TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0/8.0*7.0*x^6.0-1.0/8.0*7.0*x^6.0+1.0/5.0*4.0*x^3.0-1.0/5.0*4.0*x^3.0+3.0*x^2.0-1.0/4.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[1] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[1]="0.125/8.0*x^8.0-0.125/8.0*x^8.0+0.2/5.0*x^5.0-0.2/5.0*x^5.0+1.0/4.0*x^4.0-0.25/4.0*1.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[1]="0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+0.25*x^4.0-0.015625*x^4.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.875*6.0*x^5.0-0.875*6.0*x^5.0+0.8*3.0*x^2.0-0.8*3.0*x^2.0+3.0*2.0*x^1.0-0.75*2.0*x^1.0";
        Usage.EqnSolArrayMap2[2] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        Usage.EqnSolArrayMap3[2]="0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+0.25*x^4.0-0.015625*x^4.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.015625/9.0*x^9.0-0.015625/9.0*x^9.0+0.04/6.0*x^6.0-0.04/6.0*x^6.0+0.25/5.0*x^5.0-0.015625/5.0*x^5.0";
        Usage.EqnSolArrayMap4[2]="0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.003125*x^5.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        Usage.EqnSolArrayDiffAlone[3] = "5.25*5.0*x^4.0-5.25*5.0*x^4.0+2.4000000000000004*2.0*x^1.0-2.4000000000000004*2.0*x^1.0+6.0-1.5";
        Usage.EqnSolArrayMap2[3] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        Usage.EqnSolArrayMap3[3]="0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.003125*x^5.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.001736111111111111/10.0*x^10.0-0.001736111111111111/10.0*x^10.0+0.006666666666666667/7.0*x^7.0-0.006666666666666667/7.0*x^7.0+0.05/6.0*x^6.0-0.003125/6.0*x^6.0";
        Usage.EqnSolArrayMap4[3]="1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-5.208333333333333E-4*x^6.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        Usage.EqnSolArrayDiffAlone[4] = "26.25*4.0*x^3.0-26.25*4.0*x^3.0+4.800000000000001-4.800000000000001+0.0-0.0";
        Usage.EqnSolArrayMap2[4] = "105.0*x^3.0-105.0*x^3.0+4.800000000000001-4.800000000000001";
        Usage.EqnSolArrayMap3[4]="1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-5.208333333333333E-4*x^6.0";
        Usage.EqnSolArrayIntegrAlone[4]="1.736111111111111E-4/11.0*x^11.0-1.736111111111111E-4/11.0*x^11.0+9.523809523809525E-4/8.0*x^8.0-9.523809523809525E-4/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-5.208333333333333E-4/7.0*x^7.0";
        Usage.EqnSolArrayMap4[4]="1.578282828282828E-5*x^11.0-1.578282828282828E-5*x^11.0+1.1904761904761906E-4*x^8.0-1.1904761904761906E-4*x^8.0+0.0011904761904761904*x^7.0-7.44047619047619E-5*x^7.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff105() {
        //Test Case 105
        Usage.TestCase = 105;
        Usage.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1/x^-8 + 1/x^-5 + 1/x^-4 - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        Usage.TestCaseMatrixID="Complex:Sanity TestCase:Mapping of -ve Exponent & Division";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "1.0/x^-8.0+1.0/x^-5.0+1.0/x^-4.0-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0";
        Usage.EqnSolArrayMap3[1]="1.0/x^-8.0+1.0/x^-5.0+1.0/x^-4.0-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-7.0/x^-6.0-5.0*-4.0/x^-3.0-4.0*-3.0/x^-2.0";
        Usage.EqnSolArrayMap2[2] = "56.0/x^-6.0+20.0/x^-3.0+12.0/x^-2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0/x^-6.0+20.0/x^-3.0+12.0/x^-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*-6.0/x^-5.0+20.0*-3.0/x^-2.0+12.0*-2.0/x^-1.0";
        Usage.EqnSolArrayMap2[3] = "-336.0/x^-5.0-60.0/x^-2.0-24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-336.0/x^-5.0-60.0/x^-2.0-24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "-336.0*-5.0/x^-4.0-60.0*-2.0/x^-1.0-24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0/x^-4.0+120.0*x-24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
    
        /* **************************************** */
        /* ***************Exponent Test Case***************** */
        /* **************************************** */       
        public static void TestCaseDiff30001() {
        //Test Case 30001
        Usage.TestCase = 30001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + x^5 + x^4 - x- 25";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:+ve Numbers:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        
        public static void TestCaseDiff30002() {
        //Test Case 30002
        Usage.TestCase = 30002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^-8 + x^-5 + x^-4 - x^-1- 25";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:-ve Numbers:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "x^-8.0+x^-5.0+x^-4.0-1.0/x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0+1.0/x^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0+1.0/x^2.0";
        Usage.EqnSolArrayMap3[1]="x^-8.0+x^-5.0+x^-4.0-1.0/x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0*x^-7.0+1.0/-4.0*x^-4.0+1.0/-3.0*x^-3.0-ln(x)-25.0*x";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285*x^-7.0-0.25*x^-4.0-0.3333333333333333*x^-3.0-ln(x)-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0+1.0/x^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0*x^-10.0-5.0*-6.0*x^-7.0-4.0*-5.0*x^-6.0-2.0/x^3.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^-10.0+30.0*x^-7.0+20.0*x^-6.0-2.0/x^3.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^-10.0+30.0*x^-7.0+20.0*x^-6.0-2.0/x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0*x^-11.0+30.0*-7.0*x^-8.0+20.0*-6.0*x^-7.0-2.0*-3.0/x^4.0";
        Usage.EqnSolArrayMap2[3] = "-720.0*x^-11.0-210.0*x^-8.0-120.0*x^-7.0+6.0/x^4.0";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0*x^-11.0-210.0*x^-8.0-120.0*x^-7.0+6.0/x^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0*x^-12.0-210.0*-8.0*x^-9.0-120.0*-7.0*x^-8.0+6.0*-4.0/x^5.0";
        Usage.EqnSolArrayMap2[4] = "7920.0*x^-12.0+1680.0*x^-9.0+840.0*x^-8.0-24.0/x^5.0";
        Usage.EqnSolArrayMap3[4]="";
        Usage.EqnSolArrayIntegrAlone[4]="";
        Usage.EqnSolArrayMap4[4]="";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30003() {
        //Test Case 30003
        Usage.TestCase = 30003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^0 + x^0 + x^0 - x^0- 25^0";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:0:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0+1.0+1.0-1.0-1.0";
        Usage.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="1.0+1.0+1.0-1.0-1.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0*x+1.0*x+1.0*x-1.0*x-1.0*x";
        Usage.EqnSolArrayMap4[1]="x+x+x-x-x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="x+x+x-x-x";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-1.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-0.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-0.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-0.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-0.16666666666666666*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-0.041666666666666664*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30004() {
        //Test Case 30004
        Usage.TestCase = 30004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^1 + x^1 + x^1 - x^1- 25^1";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:1:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayMap3[1]="x+x+x-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30005() {
        //Test Case 30005
        Usage.TestCase = 30005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^-1 + x^-1 + x^-1 - x^-1- 25^-1";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:-1:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/x+1.0/x+1.0/x-1.0/x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/x+1.0/x+1.0/x-1.0/x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="ln(x)+ln(x)+ln(x)-ln(x)-25.0*x";
        Usage.EqnSolArrayMap4[1]="ln(x)+ln(x)+ln(x)-ln(x)-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-1.0/x^2.0-1.0/x^2.0-1.0/x^2.0+1.0/x^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-1.0*-2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        Usage.EqnSolArrayMap2[2] = "2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0/x^3.0+2.0/x^3.0+2.0/x^3.0-2.0/x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "2.0*-3.0/x^4.0+2.0*-3.0/x^4.0+2.0*-3.0/x^4.0-2.0*-3.0/x^4.0";
        Usage.EqnSolArrayMap2[3] = "-6.0/x^4.0-6.0/x^4.0-6.0/x^4.0+6.0/x^4.0";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-6.0/x^4.0-6.0/x^4.0-6.0/x^4.0+6.0/x^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-6.0*-4.0/x^5.0-6.0*-4.0/x^5.0-6.0*-4.0/x^5.0+6.0*-4.0/x^5.0";
        Usage.EqnSolArrayMap2[4] = "24.0/x^5.0+24.0/x^5.0+24.0/x^5.0-24.0/x^5.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30006() {
        //Test Case 30006
        Usage.TestCase = 30006;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^a + x^a + x^a - x^a- 25^a";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30007() {
        //Test Case 30007
        Usage.TestCase = 30007;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^-a + x^-a + x^-a - x^-a- 25^-a";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30008() {
        //Test Case 30008
        Usage.TestCase = 30008;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^a^2 + x^-a + x^-a^-2 - x^a^0- 25^a^0";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:a:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30009() {
        //Test Case 30009
        Usage.TestCase = 30009;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^+1 + x^+1 + x^+1 - x^+1- 25^+1";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:+1:* Operator:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayMap3[1]="x+x+x-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30010() {
        //Test Case 30009
        Usage.TestCase = 30010;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^+1.0 + x^+1.0 + x^+1.0 - x^+1.0- 25^+1.0";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:+1.0:* Operator:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x+x+x-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0+1.0+1.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayMap3[1]="x+x+x-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/2.0*x^2.0+1.0/2.0*x^2.0+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0+1.0+1.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0+0.0+0.0-0.0";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="0.5*x^2.0+0.5*x^2.0+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.5/3.0*x^3.0+0.5/3.0*x^3.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.16666666666666666*x^3.0+0.16666666666666666*x^3.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.041666666666666664*x^4.0+0.041666666666666664*x^4.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.008333333333333333*x^5.0+0.008333333333333333*x^5.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff30011() {
        //Test Case 30011
        Usage.TestCase = 30011;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^+5 + x^+5 + x^+5 - x^+5- 25^+5";
        Usage.TestCaseName = "Simple: Exponent";
        Usage.TestCaseMatrixID="Exponent:+5:* Operator:Constant End:Term Sign +&-:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^5.0+x^5.0+x^5.0-x^5.0-9765625.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0-0.0";
        Usage.EqnSolArrayMap2[1] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0";
        Usage.EqnSolArrayMap3[1]="x^5.0+x^5.0+x^5.0-x^5.0-9765625.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/6.0*x^6.0+1.0/6.0*x^6.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0-9765625.0*x";
        Usage.EqnSolArrayMap4[1]="0.16666666666666666*x^6.0+0.16666666666666666*x^6.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0-9765625.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "5.0*x^4.0+5.0*x^4.0+5.0*x^4.0-5.0*x^4.0";
        Usage.EqnSolArrayDiffAlone[2] = "5.0*4.0*x^3.0+5.0*4.0*x^3.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0";
        Usage.EqnSolArrayMap2[2] = "20.0*x^3.0+20.0*x^3.0+20.0*x^3.0-20.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.16666666666666666*x^6.0+0.16666666666666666*x^6.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0-9765625.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.16666666666666666/7.0*x^7.0+0.16666666666666666/7.0*x^7.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0-9765625.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.023809523809523808*x^7.0+0.023809523809523808*x^7.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0-4882812.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "20.0*x^3.0+20.0*x^3.0+20.0*x^3.0-20.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "20.0*3.0*x^2.0+20.0*3.0*x^2.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "60.0*x^2.0+60.0*x^2.0+60.0*x^2.0-60.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.023809523809523808*x^7.0+0.023809523809523808*x^7.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0-4882812.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.023809523809523808/8.0*x^8.0+0.023809523809523808/8.0*x^8.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0-4882812.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.002976190476190476*x^8.0+0.002976190476190476*x^8.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0-1627604.1666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "60.0*x^2.0+60.0*x^2.0+60.0*x^2.0-60.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "60.0*2.0*x^1.0+60.0*2.0*x^1.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "120.0*x+120.0*x+120.0*x-120.0*x";
        Usage.EqnSolArrayMap3[4]="0.002976190476190476*x^8.0+0.002976190476190476*x^8.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0-1627604.1666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.002976190476190476/9.0*x^9.0+0.002976190476190476/9.0*x^9.0+0.002976190476190476/9.0*x^9.0-0.002976190476190476/9.0*x^9.0-1627604.1666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="3.3068783068783067E-4*x^9.0+3.3068783068783067E-4*x^9.0+3.3068783068783067E-4*x^9.0-3.3068783068783067E-4*x^9.0-406901.0416666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        
        
        /* **************************************** */
        /* ***************Parenthesis Test Case***************** */
        /* **************************************** */
        public static void TestCaseDiff80001() {
        //Test Case 80001
        Usage.TestCase = 80001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "x^8 + x^5 + x^4 - 2*x- 25*(2)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0";
        Usage.EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-2.0*x-50.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-2.0/2.0*x^2.0-50.0*x";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-x^2.0-50.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-x^2.0-50.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-1.0/3.0*x^3.0-50.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.3333333333333333*x^3.0-25.0*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.3333333333333333*x^3.0-25.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-25.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.08333333333333333*x^4.0-8.333333333333334*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.08333333333333333*x^4.0-8.333333333333334*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-8.333333333333334/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.016666666666666666*x^5.0-2.0833333333333335*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80002() {
        //Test Case 80002
        Usage.TestCase = 80002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*(6) + x^5 + x^4 - (2)*x*(7)- (3)*25*(9)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "6.0*x^8.0+x^5.0+x^4.0-14.0*x-675.0";
        Usage.EqnSolArrayDiffAlone[1] = "6.0*8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-14.0-0.0";
        Usage.EqnSolArrayMap2[1] = "48.0*x^7.0+5.0*x^4.0+4.0*x^3.0-14.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80003() {
        //Test Case 80003
        Usage.TestCase = 80003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*(5*x) + x^5 + x^4 - (2)*x*(5)- (2)*25*(2)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:Constants:Coefficient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "5.0*x^9.0+x^5.0+x^4.0-10.0*x-100.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*9.0*x^8.0+5.0*x^4.0+4.0*x^3.0-10.0-0.0";
        Usage.EqnSolArrayMap2[1] = "45.0*x^8.0+5.0*x^4.0+4.0*x^3.0-10.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80004() {
        //Test Case 80004
        Usage.TestCase = 80004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*(5*x)/x + x^5/x + x^4/x - (2)*x*5/5- (2)*25*(2)/2";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:Constants:Coefficient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "5.0*x^8.0+x^4.0+x^3.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+4.0*x^3.0+3.0*x^2.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*x^7.0+4.0*x^3.0+3.0*x^2.0-2.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80005() {
        //Test Case 80005
        Usage.TestCase = 80005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + sin(x) + x^5 + x^4 - x- 25";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        // Parenthesis Test Case
        public static void TestCaseDiff80006() {
        //Test Case 80006
        Usage.TestCase = 80006;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^(8) + x^(5) + x^(4) - (2)*x- 25*(1)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-2.0*x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0-";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80007() {
        //Test Case 80007
        Usage.TestCase = 80007;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*(5*x+2) + x^5 + x^4 - 2*x*5- 2*25*2";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:Constants:Coefficient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 2;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0*(5.0*x+2.0)+x^5.0+x^4.0-10.0*x-100.0";
        Usage.EqnSolArrayDiffAlone[1] = "(5.0*x+2.0)*8.0*x^7.0+(5.0+0.0)*x^8.0+5.0*x^4.0+4.0*x^3.0-10.0-0.0";
        Usage.EqnSolArrayMap2[1] = "(5.0*x+2.0)*8.0*x^7.0+5.0*x^8.0+5.0*x^4.0+4.0*x^3.0-10.0";
        Usage.EqnSolArrayMap3[1]="x^8.0*(5.0*x+2.0)+x^5.0+x^4.0-10.0*x-100.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80008() {
        //Test Case 80008
        Usage.TestCase = 80008;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*(5*x)/x + x^5/x + x^4/x - (2)*x*(5)/5- (2)*25*(2)/2";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:Constants:Coefficient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "5.0*x^8.0+x^4.0+x^3.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+4.0*x^3.0+3.0*x^2.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*x^7.0+4.0*x^3.0+3.0*x^2.0-2.0";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff80009() {
        //Test Case 80009
        Usage.TestCase = 80009;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1*+1/xyc - 1*+1/25";
        Usage.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1";
        Usage.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc";
        Usage.MaxDiffOrder = 2;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="1.0/xyc-0.04";
        Usage.EqnSolArrayIntegrAlone[1]="1.0*ln(xyc)-0.04*xyc";
        Usage.EqnSolArrayMap4[1]="1.0*ln(xyc)-0.04*xyc";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="";
        Usage.EqnSolArrayIntegrAlone[4]="";
        Usage.EqnSolArrayMap4[4]="";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }   
    
        public static void TestCaseDiff80010() {
        //Test Case 80010
        Usage.TestCase = 80010;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + (2*x+1)*x^5 + x^4 - 2*x- 25*2";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff80011() {
        //Test Case 80011
        Usage.TestCase = 80011;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*sin(x) + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        
        public static void TestCaseDiff80012() {
        //Test Case 80012
        Usage.TestCase = 80012;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + (2*x+1)*x^5 + x^4 - (2)*x- 25*(2)";
        Usage.TestCaseName = "Simple: Parenthesis";
        Usage.TestCaseMatrixID="Parenthesis:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-2.0-";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        /* **************************************** */
        /* ***************Functions Test Case***************** */
        /* **************************************** */       
        public static void TestCaseDiff90001() {
        //Test Case 90001
        Usage.TestCase = 90001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "sin(x) + x^5 + x^4 - x- 25";
        Usage.TestCaseName = "Simple: Functions";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Functions";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "sin(x)+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "cos(x)+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "cos(x)+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "cos(x)+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "-sin(x)+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-sin(x)+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-sin(x)+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-cos(x)+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "-cos(x)+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-cos(x)+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "sin(x)+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "sin(x)+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        /* **************************************** */
        /* ***************Integration by Parts (Product Integration) Test Case***************** */
        /* **************************************** */ 
        public static void TestCaseDiff100001() {
        //Test Case 100001
        Usage.TestCase = 100001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8*sin(x) + x^5 + x^4 - x- 25";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "sin(x)*x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "x^8.0*cos(x)+8.0*x^7.0*sin(x)+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "cos(x)*x^8.0+8.0*sin(x)*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="sin(x)*x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "cos(x)*x^8.0+8.0*sin(x)*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "x^8.0*-sin(x)+8.0*x^7.0*cos(x)+x^7.0*8.0*cos(x)+7.0*x^6.0*8.0*sin(x)+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-sin(x)*x^8.0+8.0*cos(x)*x^7.0+8.0*cos(x)*x^7.0+56.0*sin(x)*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-sin(x)*x^8.0+8.0*cos(x)*x^7.0+8.0*cos(x)*x^7.0+56.0*sin(x)*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "x^8.0*cos(x)+8.0*x^7.0*-sin(x)+x^7.0*8.0*-sin(x)+7.0*x^6.0*8.0*cos(x)+x^7.0*8.0*-sin(x)+7.0*x^6.0*8.0*cos(x)+x^6.0*56.0*cos(x)+6.0*x^5.0*56.0*sin(x)+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "cos(x)*x^8.0-8.0*sin(x)*x^7.0-8.0*sin(x)*x^7.0+56.0*cos(x)*x^6.0-8.0*sin(x)*x^7.0+56.0*cos(x)*x^6.0+56.0*cos(x)*x^6.0+336.0*sin(x)*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "cos(x)*x^8.0-8.0*sin(x)*x^7.0-8.0*sin(x)*x^7.0+56.0*cos(x)*x^6.0-8.0*sin(x)*x^7.0+56.0*cos(x)*x^6.0+56.0*cos(x)*x^6.0+336.0*sin(x)*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "x^8.0*-sin(x)+8.0*x^7.0*cos(x)-x^7.0*8.0*cos(x)+7.0*x^6.0*8.0*sin(x)-x^7.0*8.0*cos(x)+7.0*x^6.0*8.0*sin(x)+x^6.0*56.0*-sin(x)+6.0*x^5.0*56.0*cos(x)-x^7.0*8.0*cos(x)+7.0*x^6.0*8.0*sin(x)+x^6.0*56.0*-sin(x)+6.0*x^5.0*56.0*cos(x)+x^6.0*56.0*-sin(x)+6.0*x^5.0*56.0*cos(x)+x^5.0*336.0*cos(x)+5.0*x^4.0*336.0*sin(x)+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "-sin(x)*x^8.0+8.0*cos(x)*x^7.0-8.0*cos(x)*x^7.0+56.0*sin(x)*x^6.0-8.0*cos(x)*x^7.0+56.0*sin(x)*x^6.0-56.0*sin(x)*x^6.0+336.0*cos(x)*x^5.0-8.0*cos(x)*x^7.0+56.0*sin(x)*x^6.0-56.0*sin(x)*x^6.0+336.0*cos(x)*x^5.0-56.0*sin(x)*x^6.0+336.0*cos(x)*x^5.0+336.0*cos(x)*x^5.0+1680.0*sin(x)*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        /* **************************************** */
        /* ***************Software Engg Test Case***************** */
        /* **************************************** */ 
        public static void TestCaseDiff110001() {
        //Test Case 110001
        Usage.TestCase = 110001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^2";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^2.0";
        Usage.EqnSolArrayDiffAlone[1] = "2.0*x^1.0";
        Usage.EqnSolArrayMap2[1] = "2.0*x";
        Usage.EqnSolArrayMap3[1]="x^2.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[1]="0.3333333333333333*x^3.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "2.0*x";
        Usage.EqnSolArrayDiffAlone[2] = "2.0";
        Usage.EqnSolArrayMap2[2] = "2.0";
        Usage.EqnSolArrayMap3[2]="0.3333333333333333*x^3.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.3333333333333333/4.0*x^4.0";
        Usage.EqnSolArrayMap4[2]="0.08333333333333333*x^4.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0";
        Usage.EqnSolArrayDiffAlone[3] = "0.0";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.08333333333333333*x^4.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.08333333333333333/5.0*x^5.0";
        Usage.EqnSolArrayMap4[3]="0.016666666666666666*x^5.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.016666666666666666*x^5.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.016666666666666666/6.0*x^6.0";
        Usage.EqnSolArrayMap4[4]="0.002777777777777778*x^6.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110002() {
        //Test Case 110002
        Usage.TestCase = 110002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x";
        Usage.EqnSolArrayDiffAlone[1] = "1.0";
        Usage.EqnSolArrayMap2[1] = "1.0";
        Usage.EqnSolArrayMap3[1]="x";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[1]="0.5*x^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="0.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[2]="0.16666666666666666*x^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.16666666666666666*x^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.16666666666666666/4.0*x^4.0";
        Usage.EqnSolArrayMap4[3]="0.041666666666666664*x^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.041666666666666664*x^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.041666666666666664/5.0*x^5.0";
        Usage.EqnSolArrayMap4[4]="0.008333333333333333*x^5.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110003() {
        //Test Case 110003
        Usage.TestCase = 110003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "2.0";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "2.0";
        Usage.EqnSolArrayDiffAlone[1] = "0.0";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="2.0";
        Usage.EqnSolArrayIntegrAlone[1]="2.0*x";
        Usage.EqnSolArrayMap4[1]="2.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="2.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="2.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="1.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.3333333333333333*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.3333333333333333*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.3333333333333333/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.08333333333333333*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110004() {
        //Test Case 110004
        Usage.TestCase = 110004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0";
        Usage.EqnSolArrayDiffAlone[1] = "0.0";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="1.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0*x";
        Usage.EqnSolArrayMap4[1]="x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="x";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.16666666666666666*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.16666666666666666*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.16666666666666666/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.041666666666666664*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110005() {
        //Test Case 110005
        Usage.TestCase = 110005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "0.0";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="";
        Usage.EqnSolArrayIntegrAlone[1]="";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="";
        Usage.EqnSolArrayIntegrAlone[4]="";
        Usage.EqnSolArrayMap4[4]="";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110006() {
        //Test Case 110006
        Usage.TestCase = 110006;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0";
        Usage.EqnSolArrayDiffAlone[1] = "0.0";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="1.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0*x";
        Usage.EqnSolArrayMap4[1]="x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="x";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="0.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.16666666666666666*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="0.16666666666666666*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.16666666666666666/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="0.041666666666666664*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff110007() {
        //Test Case 110007
        Usage.TestCase = 110007;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "0";
        Usage.TestCaseName = "Simple: Product Integration";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Function";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "";
        Usage.EqnSolArrayDiffAlone[1] = "";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="";
        Usage.EqnSolArrayIntegrAlone[1]="";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "";
        Usage.EqnSolArrayDiffAlone[2] = "";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="";
        Usage.EqnSolArrayIntegrAlone[4]="";
        Usage.EqnSolArrayMap4[4]="";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "";
        Usage.EqnSolArrayDiffAlone[5] = "";
        Usage.EqnSolArrayMap2[5] = "";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "";
        Usage.EqnSolArrayDiffAlone[6] = "";
        Usage.EqnSolArrayMap2[6] = "";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolOrder[14] = 14;
    }
        /* **************************************** */
        /* ***************SoftwareEngg Test Case***************** */
        /* **************************************** */   
}
