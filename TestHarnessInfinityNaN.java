/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import Calculus.ConsoleColors;
import static Calculus.ExclusionTestCases.DifferentialGapException;
import static Calculus.ExclusionTestCases.DifferentialGapInfinity;
import static Calculus.ExclusionTestCases.IntegralGapException;
import static Calculus.ExclusionTestCases.IntegralGapInfinity;
import Calculus.UsageCalculus;
import static Calculus.UsageCalculus.MissedNumberTestCases;
import static Calculus.UsageCalculus.MissedTestCasesException;
import static Calculus.UsageCalculus.MissedTestCasesInfinity;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessInfinityNaN {
   public static Logger log = Logger.getLogger(TestHarnessInfinityNaN.class.getName());
   
   //InfinityNaN Test Cases
    public static void InfinityNaNTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapInfinity(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapInfinity(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED +"InfinityNaNTestCases TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapException(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapException(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED +"InfinityNaNTestCases TestCase:j= " + j  + ": Exception GAP" + ConsoleColors.RESET);
        }
        else {
            switch(j){
            case 2000:
                TestCaseDiff2000();
                break;
            case 2001:
                TestCaseDiff2001();
                break;
            case 2002:
                TestCaseDiff2002();
                break;
            case 2003:
                TestCaseDiff2003();
                break;
            case 2004:
                TestCaseDiff2004();
                break;
            case 2005:
                TestCaseDiff2005();
                break;
            case 2006:
                TestCaseDiff2006();
                break;
            case 2007:
                TestCaseDiff2007();
                break;
            case 2008:
                TestCaseDiff2008();
                break;
            case 2009:
                TestCaseDiff2009();
                break;
            case 2010:
                TestCaseDiff2010();
                break;
            case 2011:
                TestCaseDiff2011();
                break;
            case 2012:
                TestCaseDiff2012();
                break;
            case 2013:
                TestCaseDiff2013();
                break;
            case 2014:
                TestCaseDiff2014();
                break;
            case 2015:
                TestCaseDiff2015();
                break;
            case 2016:
                TestCaseDiff2016();
                break;
            case 2017:
                TestCaseDiff2017();
                break;
            case 2018:
                TestCaseDiff2018();
                break;
            case 2019:
                TestCaseDiff2019();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Names TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
    }
}
    public static void TestCaseDiff2000() {
        //Test Case 2000
        UsageCalculus.TestCase = 2000;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/-0/x^8 + 1.0/-0/x^5 + 1.0/-0/x^4 - 1.0/-0/x - 25/-0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-Infinity*-8.0/x^9.0-Infinity*-5.0/x^6.0-Infinity*-4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-Infinity/-7.0/x^7.0-Infinity/-4.0/x^4.0-Infinity/-3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="Infinity/7.0/x^7.0+Infinity/4.0/x^4.0+Infinity/3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "Infinity*8.0*-9.0/x^10.0+Infinity*5.0*-6.0/x^7.0+Infinity*4.0*-5.0/x^6.0-Infinity*-2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-Infinity*72.0*-10.0/x^11.0-Infinity*30.0*-7.0/x^8.0-Infinity*20.0*-6.0/x^7.0+Infinity*2.0*-3.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "Infinity*720.0*-11.0/x^12.0+Infinity*210.0*-8.0/x^9.0+Infinity*120.0*-7.0/x^8.0-Infinity*6.0*-4.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "-Infinity*7920.0/x^12.0-Infinity*1680.0/x^9.0-Infinity*840.0/x^8.0+Infinity*24.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2001() {
        //Test Case 2001
        UsageCalculus.TestCase = 2001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*Infinity/x^8 + 1.0*Infinity/x^5 + 1.0*Infinity/x^4 - 1.0*Infinity/x - 25*Infinity";
        UsageCalculus.TestCaseName = "Simple: Coefficients:Infinity";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*-8.0/x^9.0+Infinity*-5.0/x^6.0+Infinity*-4.0/x^5.0+Infinity/x^2.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/-7.0/x^7.0+Infinity/-4.0/x^4.0+Infinity/-3.0/x^3.0-Infinity*ln(x)-Infinity*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-Infinity/7.0/x^7.0-Infinity/4.0/x^4.0-Infinity/3.0/x^3.0-Infinity*ln(x)-Infinity*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-Infinity*8.0*-9.0/x^10.0-Infinity*5.0*-6.0/x^7.0-Infinity*4.0*-5.0/x^6.0+Infinity*-2.0/x^3.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*72.0*-10.0/x^11.0+Infinity*30.0*-7.0/x^8.0+Infinity*20.0*-6.0/x^7.0-Infinity*2.0*-3.0/x^4.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-Infinity*720.0*-11.0/x^12.0-Infinity*210.0*-8.0/x^9.0-Infinity*120.0*-7.0/x^8.0+Infinity*6.0*-4.0/x^5.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*7920.0/x^12.0+Infinity*1680.0/x^9.0+Infinity*840.0/x^8.0-Infinity*24.0/x^5.0-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2002() {
        //Test Case 2002
        UsageCalculus.TestCase = 2002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/Infinity/x^8 + 1.0/Infinity/x^5 + 1.0/Infinity/x^4 - 1.0/Infinity/x - 25/Infinity";
        UsageCalculus.TestCaseName = "Simple: Coefficients:Infinity";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/Infinity/x^8.0+1.0/Infinity/x^5.0+1.0/Infinity/x^4.0-1.0/Infinity/x-25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/Infinity*-8.0/x^9.0+1.0/Infinity*-5.0/x^6.0+1.0/Infinity*-4.0/x^5.0+1.0/Infinity/x^2.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/Infinity/x^9.0-5.0/Infinity/x^6.0-4.0/Infinity/x^5.0+1.0/Infinity/x^2.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/Infinity/x^8.0+1.0/Infinity/x^5.0+1.0/Infinity/x^4.0-1.0/Infinity/x-25.0/Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/Infinity/-7.0/x^7.0+1.0/Infinity/-4.0/x^4.0+1.0/Infinity/-3.0/x^3.0-1.0/Infinity*ln(x)-25.0/Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="-7.0/Infinity/x^7.0-4.0/Infinity/x^4.0-3.0/Infinity/x^3.0-1.0/Infinity*ln(x)-25.0/Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/Infinity/x^9.0-5.0/Infinity/x^6.0-4.0/Infinity/x^5.0+1.0/Infinity/x^2.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0/Infinity*-9.0/x^10.0-5.0/Infinity*-6.0/x^7.0-4.0/Infinity*-5.0/x^6.0+1.0/Infinity*-2.0/x^3.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/Infinity/x^10.0+30.0/Infinity/x^7.0+20.0/Infinity/x^6.0-2.0/Infinity/x^3.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/Infinity/x^10.0+30.0/Infinity/x^7.0+20.0/Infinity/x^6.0-2.0/Infinity/x^3.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0/Infinity*-10.0/x^11.0+30.0/Infinity*-7.0/x^8.0+20.0/Infinity*-6.0/x^7.0-2.0/Infinity*-3.0/x^4.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/Infinity/x^11.0-210.0/Infinity/x^8.0-120.0/Infinity/x^7.0+6.0/Infinity/x^4.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/Infinity/x^11.0-210.0/Infinity/x^8.0-120.0/Infinity/x^7.0+6.0/Infinity/x^4.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0/Infinity*-11.0/x^12.0-210.0/Infinity*-8.0/x^9.0-120.0/Infinity*-7.0/x^8.0+6.0/Infinity*-4.0/x^5.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/Infinity/x^12.0+1680.0/Infinity/x^9.0+840.0/Infinity/x^8.0-24.0/Infinity/x^5.0-25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2003() {
        //Test Case 2003
        UsageCalculus.TestCase = 2003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-Infinity/x^8 + 1.0*-Infinity/x^5 + 1.0*-Infinity/x^4 - 1.0*-Infinity/x - 25*-Infinity";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-Infinity";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-Infinity*-8.0/x^9.0-Infinity*-5.0/x^6.0-Infinity*-4.0/x^5.0-Infinity/x^2.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-Infinity/-7.0/x^7.0-Infinity/-4.0/x^4.0-Infinity/-3.0/x^3.0+Infinity*ln(x)+Infinity*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="Infinity/7.0/x^7.0+Infinity/4.0/x^4.0+Infinity/3.0/x^3.0+Infinity*ln(x)+Infinity*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "Infinity*8.0*-9.0/x^10.0+Infinity*5.0*-6.0/x^7.0+Infinity*4.0*-5.0/x^6.0-Infinity*-2.0/x^3.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-Infinity*72.0*-10.0/x^11.0-Infinity*30.0*-7.0/x^8.0-Infinity*20.0*-6.0/x^7.0+Infinity*2.0*-3.0/x^4.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "Infinity*720.0*-11.0/x^12.0+Infinity*210.0*-8.0/x^9.0+Infinity*120.0*-7.0/x^8.0-Infinity*6.0*-4.0/x^5.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-Infinity*7920.0/x^12.0-Infinity*1680.0/x^9.0-Infinity*840.0/x^8.0+Infinity*24.0/x^5.0+Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2004() {
        //Test Case 2004
        UsageCalculus.TestCase = 2004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/-Infinity/x^8 + 1.0/-Infinity/x^5 + 1.0/-Infinity/x^4 - 1.0/-Infinity/x - 25/-Infinity";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-Infinity";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/Infinity/x^8.0-1.0/Infinity/x^5.0-1.0/Infinity/x^4.0+1.0/Infinity/x+25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0/Infinity*-8.0/x^9.0-1.0/Infinity*-5.0/x^6.0-1.0/Infinity*-4.0/x^5.0-1.0/Infinity/x^2.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0/Infinity/x^9.0+5.0/Infinity/x^6.0+4.0/Infinity/x^5.0-1.0/Infinity/x^2.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/Infinity/x^8.0-1.0/Infinity/x^5.0-1.0/Infinity/x^4.0+1.0/Infinity/x+25.0/Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/Infinity/-7.0/x^7.0-1.0/Infinity/-4.0/x^4.0-1.0/Infinity/-3.0/x^3.0+1.0/Infinity*ln(x)+25.0/Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="7.0/Infinity/x^7.0+4.0/Infinity/x^4.0+3.0/Infinity/x^3.0+1.0/Infinity*ln(x)+25.0/Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0/Infinity/x^9.0+5.0/Infinity/x^6.0+4.0/Infinity/x^5.0-1.0/Infinity/x^2.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0/Infinity*-9.0/x^10.0+5.0/Infinity*-6.0/x^7.0+4.0/Infinity*-5.0/x^6.0-1.0/Infinity*-2.0/x^3.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0/Infinity/x^10.0-30.0/Infinity/x^7.0-20.0/Infinity/x^6.0+2.0/Infinity/x^3.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0/Infinity/x^10.0-30.0/Infinity/x^7.0-20.0/Infinity/x^6.0+2.0/Infinity/x^3.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0/Infinity*-10.0/x^11.0-30.0/Infinity*-7.0/x^8.0-20.0/Infinity*-6.0/x^7.0+2.0/Infinity*-3.0/x^4.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0/Infinity/x^11.0+210.0/Infinity/x^8.0+120.0/Infinity/x^7.0-6.0/Infinity/x^4.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0/Infinity/x^11.0+210.0/Infinity/x^8.0+120.0/Infinity/x^7.0-6.0/Infinity/x^4.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0/Infinity*-11.0/x^12.0+210.0/Infinity*-8.0/x^9.0+120.0/Infinity*-7.0/x^8.0-6.0/Infinity*-4.0/x^5.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "-7920.0/Infinity/x^12.0-1680.0/Infinity/x^9.0-840.0/Infinity/x^8.0+24.0/Infinity/x^5.0+25.0/Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2005() {
        //Test Case 2005
        UsageCalculus.TestCase = 2005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*NaN/x^8 + 1.0*NaN/x^5 + 1.0*NaN/x^4 - 1.0*NaN/x - 25*NaN";
        UsageCalculus.TestCaseName = "Simple: Coefficients:NaN";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-NaN*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*-8.0/x^9.0+NaN*-5.0/x^6.0+NaN*-4.0/x^5.0+NaN/x^2.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN/x^2.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-NaN*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN/-7.0/x^7.0+NaN/-4.0/x^4.0+NaN/-3.0/x^3.0-NaN*ln(x)-NaN*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-NaN/7.0/x^7.0-NaN/4.0/x^4.0-NaN/3.0/x^3.0-NaN*ln(x)-NaN*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN/x^2.0-NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-NaN*8.0*-9.0/x^10.0-NaN*5.0*-6.0/x^7.0-NaN*4.0*-5.0/x^6.0+NaN*-2.0/x^3.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0-NaN*2.0/x^3.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0-NaN*2.0/x^3.0-NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*72.0*-10.0/x^11.0+NaN*30.0*-7.0/x^8.0+NaN*20.0*-6.0/x^7.0-NaN*2.0*-3.0/x^4.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*6.0/x^4.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*6.0/x^4.0-NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-NaN*720.0*-11.0/x^12.0-NaN*210.0*-8.0/x^9.0-NaN*120.0*-7.0/x^8.0+NaN*6.0*-4.0/x^5.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*7920.0/x^12.0+NaN*1680.0/x^9.0+NaN*840.0/x^8.0-NaN*24.0/x^5.0-NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2006() {
        //Test Case 2006
        UsageCalculus.TestCase = 2006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/NaN/x^8 + 1.0/NaN/x^5 + 1.0/NaN/x^4 - 1.0/NaN/x - 25/NaN";
        UsageCalculus.TestCaseName = "Simple: Coefficients:NaN";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/NaN/x^8.0+1.0/NaN/x^5.0+1.0/NaN/x^4.0-1.0/NaN/x-25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/NaN*-8.0/x^9.0+1.0/NaN*-5.0/x^6.0+1.0/NaN*-4.0/x^5.0+1.0/NaN/x^2.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/NaN/x^9.0-5.0/NaN/x^6.0-4.0/NaN/x^5.0+1.0/NaN/x^2.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/NaN/x^8.0+1.0/NaN/x^5.0+1.0/NaN/x^4.0-1.0/NaN/x-25.0/NaN";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/NaN/-7.0/x^7.0+1.0/NaN/-4.0/x^4.0+1.0/NaN/-3.0/x^3.0-1.0/NaN*ln(x)-25.0/NaN*x";
        UsageCalculus.EqnSolArrayMap4[1]="-7.0/NaN/x^7.0-4.0/NaN/x^4.0-3.0/NaN/x^3.0-1.0/NaN*ln(x)-25.0/NaN*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/NaN/x^9.0-5.0/NaN/x^6.0-4.0/NaN/x^5.0+1.0/NaN/x^2.0-25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0/NaN*-9.0/x^10.0-5.0/NaN*-6.0/x^7.0-4.0/NaN*-5.0/x^6.0+1.0/NaN*-2.0/x^3.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/NaN/x^10.0+30.0/NaN/x^7.0+20.0/NaN/x^6.0-2.0/NaN/x^3.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/NaN/x^8.0+1.0/NaN/x^5.0+1.0/NaN/x^4.0-1.0/NaN/x-1.0/NaN*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="1.0/NaN/-7.0/x^7.0+1.0/NaN/-4.0/x^4.0+1.0/NaN/-3.0/x^3.0-1.0/NaN*ln(x)-1.0/NaN*25.0";
        UsageCalculus.EqnSolArrayMap4[2]="-1.0/NaN/7.0/x^7.0-1.0/NaN/4.0/x^4.0-1.0/NaN/3.0/x^3.0-1.0/NaN*ln(x)-1.0/NaN*25.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/NaN/x^10.0+30.0/NaN/x^7.0+20.0/NaN/x^6.0-2.0/NaN/x^3.0-25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0/NaN*-10.0/x^11.0+30.0/NaN*-7.0/x^8.0+20.0/NaN*-6.0/x^7.0-2.0/NaN*-3.0/x^4.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/NaN/x^11.0-210.0/NaN/x^8.0-120.0/NaN/x^7.0+6.0/NaN/x^4.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/NaN/x^11.0-210.0/NaN/x^8.0-120.0/NaN/x^7.0+6.0/NaN/x^4.0-25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0/NaN*-11.0/x^12.0-210.0/NaN*-8.0/x^9.0-120.0/NaN*-7.0/x^8.0+6.0/NaN*-4.0/x^5.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/NaN/x^12.0+1680.0/NaN/x^9.0+840.0/NaN/x^8.0-24.0/NaN/x^5.0-25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2007() {
        //Test Case 2007
        UsageCalculus.TestCase = 2007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-NaN/x^8 + 1.0*-NaN/x^5 + 1.0*-NaN/x^4 - 1.0*-NaN/x - 25*-NaN";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-NaN";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-NaN/x^8.0-NaN/x^5.0-NaN/x^4.0+NaN/x+NaN*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-NaN*-8.0/x^9.0-NaN*-5.0/x^6.0-NaN*-4.0/x^5.0-NaN/x^2.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "NaN*8.0/x^9.0+NaN*5.0/x^6.0+NaN*4.0/x^5.0-NaN/x^2.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="-NaN/x^8.0-NaN/x^5.0-NaN/x^4.0+NaN/x+NaN*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-NaN/-7.0/x^7.0-NaN/-4.0/x^4.0-NaN/-3.0/x^3.0+NaN*ln(x)+NaN*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="NaN/7.0/x^7.0+NaN/4.0/x^4.0+NaN/3.0/x^3.0+NaN*ln(x)+NaN*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "NaN*8.0/x^9.0+NaN*5.0/x^6.0+NaN*4.0/x^5.0-NaN/x^2.0+NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "NaN*8.0*-9.0/x^10.0+NaN*5.0*-6.0/x^7.0+NaN*4.0*-5.0/x^6.0-NaN*-2.0/x^3.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-NaN*72.0/x^10.0-NaN*30.0/x^7.0-NaN*20.0/x^6.0+NaN*2.0/x^3.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-NaN*72.0/x^10.0-NaN*30.0/x^7.0-NaN*20.0/x^6.0+NaN*2.0/x^3.0+NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-NaN*72.0*-10.0/x^11.0-NaN*30.0*-7.0/x^8.0-NaN*20.0*-6.0/x^7.0+NaN*2.0*-3.0/x^4.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "NaN*720.0/x^11.0+NaN*210.0/x^8.0+NaN*120.0/x^7.0-NaN*6.0/x^4.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "NaN*720.0/x^11.0+NaN*210.0/x^8.0+NaN*120.0/x^7.0-NaN*6.0/x^4.0+NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "NaN*720.0*-11.0/x^12.0+NaN*210.0*-8.0/x^9.0+NaN*120.0*-7.0/x^8.0-NaN*6.0*-4.0/x^5.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-NaN*7920.0/x^12.0-NaN*1680.0/x^9.0-NaN*840.0/x^8.0+NaN*24.0/x^5.0+NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2008() {
        //Test Case 2008
        UsageCalculus.TestCase = 2008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/-NaN/x^8 + 1.0/-NaN/x^5 + 1.0/-NaN/x^4 - 1.0/-NaN/x - 25/-NaN";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-NaN";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/NaN/x^8.0-1.0/NaN/x^5.0-1.0/NaN/x^4.0+1.0/NaN/x+25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0/NaN*-8.0/x^9.0-1.0/NaN*-5.0/x^6.0-1.0/NaN*-4.0/x^5.0-1.0/NaN/x^2.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0/NaN/x^9.0+5.0/NaN/x^6.0+4.0/NaN/x^5.0-1.0/NaN/x^2.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/NaN/x^8.0-1.0/NaN/x^5.0-1.0/NaN/x^4.0+1.0/NaN/x+25.0/NaN";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/NaN/-7.0/x^7.0-1.0/NaN/-4.0/x^4.0-1.0/NaN/-3.0/x^3.0+1.0/NaN*ln(x)+25.0/NaN*x";
        UsageCalculus.EqnSolArrayMap4[1]="7.0/NaN/x^7.0+4.0/NaN/x^4.0+3.0/NaN/x^3.0+1.0/NaN*ln(x)+25.0/NaN*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0/NaN/x^9.0+5.0/NaN/x^6.0+4.0/NaN/x^5.0-1.0/NaN/x^2.0+25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0/NaN*-9.0/x^10.0+5.0/NaN*-6.0/x^7.0+4.0/NaN*-5.0/x^6.0-1.0/NaN*-2.0/x^3.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0/NaN/x^10.0-30.0/NaN/x^7.0-20.0/NaN/x^6.0+2.0/NaN/x^3.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0/NaN/x^10.0-30.0/NaN/x^7.0-20.0/NaN/x^6.0+2.0/NaN/x^3.0+25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0/NaN*-10.0/x^11.0-30.0/NaN*-7.0/x^8.0-20.0/NaN*-6.0/x^7.0+2.0/NaN*-3.0/x^4.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0/NaN/x^11.0+210.0/NaN/x^8.0+120.0/NaN/x^7.0-6.0/NaN/x^4.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0/NaN/x^11.0+210.0/NaN/x^8.0+120.0/NaN/x^7.0-6.0/NaN/x^4.0+25.0/NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0/NaN*-11.0/x^12.0+210.0/NaN*-8.0/x^9.0+120.0/NaN*-7.0/x^8.0-6.0/NaN*-4.0/x^5.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "-7920.0/NaN/x^12.0-1680.0/NaN/x^9.0-840.0/NaN/x^8.0+24.0/NaN/x^5.0+25.0/NaN";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    //1/25 Test Cases
    public static void TestCaseDiff2009() {
        //Test Case 2009
        UsageCalculus.TestCase = 2009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*1/25/x^8 + 1.0*1/25/x^5 + 1.0*1/25/x^4 - 1.0*1/25/x - 25*1/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/25";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/25.0/x^8.0+1.0/25.0/x^5.0+1.0/25.0/x^4.0-1.0/25.0/x-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/25.0*-8.0/x^9.0+1.0/25.0*-5.0/x^6.0+1.0/25.0*-4.0/x^5.0+1.0/25.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.32/x^9.0-0.2/x^6.0-0.16/x^5.0+1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/25.0/x^8.0+1.0/25.0/x^5.0+1.0/25.0/x^4.0-1.0/25.0/x-1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.04/-7.0/x^7.0+0.04/-4.0/x^4.0+0.04/-3.0/x^3.0-0.04*ln(x)-1.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-0.005714285714285714/x^7.0-0.01/x^4.0-0.013333333333333334/x^3.0-0.04*ln(x)-x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.32/x^9.0-0.2/x^6.0-0.16/x^5.0+1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.32*-9.0/x^10.0-0.2*-6.0/x^7.0-0.16*-5.0/x^6.0+1.0/25.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.88/x^10.0+1.2000000000000002/x^7.0+0.8/x^6.0-0.08/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.88/x^10.0+1.2000000000000002/x^7.0+0.8/x^6.0-0.08/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.88*-10.0/x^11.0+1.2000000000000002*-7.0/x^8.0+0.8*-6.0/x^7.0-0.08*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-28.799999999999997/x^11.0-8.400000000000002/x^8.0-4.800000000000001/x^7.0+0.24/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-28.799999999999997/x^11.0-8.400000000000002/x^8.0-4.800000000000001/x^7.0+0.24/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-28.799999999999997*-11.0/x^12.0-8.400000000000002*-8.0/x^9.0-4.800000000000001*-7.0/x^8.0+0.24*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "316.79999999999995/x^12.0+67.20000000000002/x^9.0+33.60000000000001/x^8.0-0.96/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2010() {
        //Test Case 2010
        UsageCalculus.TestCase = 2010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1/25/x^8 + 1.0/1/25/x^5 + 1.0/1/25/x^4 - 1.0/1/25/x - 25/1/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/25";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/25.0/x^8.0+1.0/25.0/x^5.0+1.0/25.0/x^4.0-1.0/25.0/x-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/25.0*-8.0/x^9.0+1.0/25.0*-5.0/x^6.0+1.0/25.0*-4.0/x^5.0+1.0/25.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.32/x^9.0-0.2/x^6.0-0.16/x^5.0+1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/25.0/x^8.0+1.0/25.0/x^5.0+1.0/25.0/x^4.0-1.0/25.0/x-1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.04/-7.0/x^7.0+0.04/-4.0/x^4.0+0.04/-3.0/x^3.0-0.04*ln(x)-1.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-0.005714285714285714/x^7.0-0.01/x^4.0-0.013333333333333334/x^3.0-0.04*ln(x)-x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.32/x^9.0-0.2/x^6.0-0.16/x^5.0+1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.32*-9.0/x^10.0-0.2*-6.0/x^7.0-0.16*-5.0/x^6.0+1.0/25.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.88/x^10.0+1.2000000000000002/x^7.0+0.8/x^6.0-0.08/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.88/x^10.0+1.2000000000000002/x^7.0+0.8/x^6.0-0.08/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.88*-10.0/x^11.0+1.2000000000000002*-7.0/x^8.0+0.8*-6.0/x^7.0-0.08*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-28.799999999999997/x^11.0-8.400000000000002/x^8.0-4.800000000000001/x^7.0+0.24/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-28.799999999999997/x^11.0-8.400000000000002/x^8.0-4.800000000000001/x^7.0+0.24/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-28.799999999999997*-11.0/x^12.0-8.400000000000002*-8.0/x^9.0-4.800000000000001*-7.0/x^8.0+0.24*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "316.79999999999995/x^12.0+67.20000000000002/x^9.0+33.60000000000001/x^8.0-0.96/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2011() {
        //Test Case 2011
        UsageCalculus.TestCase = 2011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-1/25/x^8 + 1.0*-1/25/x^5 + 1.0*-1/25/x^4 - 1.0*-1/25/x - 25*-1/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1/25";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/25.0/x^8.0-1.0/25.0/x^5.0-1.0/25.0/x^4.0+1.0/25.0/x+1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0/25.0*-8.0/x^9.0-1.0/25.0*-5.0/x^6.0-1.0/25.0*-4.0/x^5.0-1.0/25.0/x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.32/x^9.0+0.2/x^6.0+0.16/x^5.0-1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/25.0/x^8.0-1.0/25.0/x^5.0-1.0/25.0/x^4.0+1.0/25.0/x+1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.04/-7.0/x^7.0-0.04/-4.0/x^4.0-0.04/-3.0/x^3.0+0.04*ln(x)+1.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.005714285714285714/x^7.0+0.01/x^4.0+0.013333333333333334/x^3.0+0.04*ln(x)+x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.32/x^9.0+0.2/x^6.0+0.16/x^5.0-1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.32*-9.0/x^10.0+0.2*-6.0/x^7.0+0.16*-5.0/x^6.0-1.0/25.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-2.88/x^10.0-1.2000000000000002/x^7.0-0.8/x^6.0+0.08/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-2.88/x^10.0-1.2000000000000002/x^7.0-0.8/x^6.0+0.08/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-2.88*-10.0/x^11.0-1.2000000000000002*-7.0/x^8.0-0.8*-6.0/x^7.0+0.08*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "28.799999999999997/x^11.0+8.400000000000002/x^8.0+4.800000000000001/x^7.0-0.24/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "28.799999999999997/x^11.0+8.400000000000002/x^8.0+4.800000000000001/x^7.0-0.24/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "28.799999999999997*-11.0/x^12.0+8.400000000000002*-8.0/x^9.0+4.800000000000001*-7.0/x^8.0-0.24*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-316.79999999999995/x^12.0-67.20000000000002/x^9.0-33.60000000000001/x^8.0+0.96/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2012() {
        //Test Case 2012
        UsageCalculus.TestCase = 2012;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/-1/25/x^8 + 1.0/-1/25/x^5 + 1.0/-1/25/x^4 - 1.0/-1/25/x - 25/-1/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1/25";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/25.0/x^8.0-1.0/25.0/x^5.0-1.0/25.0/x^4.0+1.0/25.0/x+1.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-1.0/25.0*-8.0/x^9.0-1.0/25.0*-5.0/x^6.0-1.0/25.0*-4.0/x^5.0-1.0/25.0/x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.32/x^9.0+0.2/x^6.0+0.16/x^5.0-1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/25.0/x^8.0-1.0/25.0/x^5.0-1.0/25.0/x^4.0+1.0/25.0/x+1.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.04/-7.0/x^7.0-0.04/-4.0/x^4.0-0.04/-3.0/x^3.0+0.04*ln(x)+1.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.005714285714285714/x^7.0+0.01/x^4.0+0.013333333333333334/x^3.0+0.04*ln(x)+x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.32/x^9.0+0.2/x^6.0+0.16/x^5.0-1.0/25.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.32*-9.0/x^10.0+0.2*-6.0/x^7.0+0.16*-5.0/x^6.0-1.0/25.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-2.88/x^10.0-1.2000000000000002/x^7.0-0.8/x^6.0+0.08/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-2.88/x^10.0-1.2000000000000002/x^7.0-0.8/x^6.0+0.08/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-2.88*-10.0/x^11.0-1.2000000000000002*-7.0/x^8.0-0.8*-6.0/x^7.0+0.08*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "28.799999999999997/x^11.0+8.400000000000002/x^8.0+4.800000000000001/x^7.0-0.24/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "28.799999999999997/x^11.0+8.400000000000002/x^8.0+4.800000000000001/x^7.0-0.24/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "28.799999999999997*-11.0/x^12.0+8.400000000000002*-8.0/x^9.0+4.800000000000001*-7.0/x^8.0-0.24*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-316.79999999999995/x^12.0-67.20000000000002/x^9.0-33.60000000000001/x^8.0+0.96/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    //1/0.0
    public static void TestCaseDiff2013() {
        //Test Case 2013
        UsageCalculus.TestCase = 2013;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*1/0.0/x^8 + 1.0*1/0.0/x^5 + 1.0*1/0.0/x^4 - 1.0*1/0.0/x - 25*1/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*-8.0/x^9.0+Infinity*-5.0/x^6.0+Infinity*-4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/-7.0/x^7.0+Infinity/-4.0/x^4.0+Infinity/-3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="-Infinity/7.0/x^7.0-Infinity/4.0/x^4.0-Infinity/3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-Infinity*8.0*-9.0/x^10.0-Infinity*5.0*-6.0/x^7.0-Infinity*4.0*-5.0/x^6.0+Infinity*-2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*72.0*-10.0/x^11.0+Infinity*30.0*-7.0/x^8.0+Infinity*20.0*-6.0/x^7.0-Infinity*2.0*-3.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-Infinity*720.0*-11.0/x^12.0-Infinity*210.0*-8.0/x^9.0-Infinity*120.0*-7.0/x^8.0+Infinity*6.0*-4.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*7920.0/x^12.0+Infinity*1680.0/x^9.0+Infinity*840.0/x^8.0-Infinity*24.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2014() {
        //Test Case 2014
        UsageCalculus.TestCase = 2014;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1/0.0/x^8 + 1.0/1/0.0/x^5 + 1.0/1/0.0/x^4 - 1.0/1/0.0/x - 25/1/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*-8.0/x^9.0+Infinity*-5.0/x^6.0+Infinity*-4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/-7.0/x^7.0+Infinity/-4.0/x^4.0+Infinity/-3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="-Infinity/7.0/x^7.0-Infinity/4.0/x^4.0-Infinity/3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-Infinity*8.0*-9.0/x^10.0-Infinity*5.0*-6.0/x^7.0-Infinity*4.0*-5.0/x^6.0+Infinity*-2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*72.0*-10.0/x^11.0+Infinity*30.0*-7.0/x^8.0+Infinity*20.0*-6.0/x^7.0-Infinity*2.0*-3.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-Infinity*720.0*-11.0/x^12.0-Infinity*210.0*-8.0/x^9.0-Infinity*120.0*-7.0/x^8.0+Infinity*6.0*-4.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*7920.0/x^12.0+Infinity*1680.0/x^9.0+Infinity*840.0/x^8.0-Infinity*24.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2015() {
        //Test Case 2015
        UsageCalculus.TestCase = 2015;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-1/0.0/x^8 + 1.0*-1/0.0/x^5 + 1.0*-1/0.0/x^4 - 1.0*-1/0.0/x - 25*-1/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-Infinity*-8.0/x^9.0-Infinity*-5.0/x^6.0-Infinity*-4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-Infinity/-7.0/x^7.0-Infinity/-4.0/x^4.0-Infinity/-3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="Infinity/7.0/x^7.0+Infinity/4.0/x^4.0+Infinity/3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "Infinity*8.0*-9.0/x^10.0+Infinity*5.0*-6.0/x^7.0+Infinity*4.0*-5.0/x^6.0-Infinity*-2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-Infinity*72.0*-10.0/x^11.0-Infinity*30.0*-7.0/x^8.0-Infinity*20.0*-6.0/x^7.0+Infinity*2.0*-3.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "Infinity*720.0*-11.0/x^12.0+Infinity*210.0*-8.0/x^9.0+Infinity*120.0*-7.0/x^8.0-Infinity*6.0*-4.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "-Infinity*7920.0/x^12.0-Infinity*1680.0/x^9.0-Infinity*840.0/x^8.0+Infinity*24.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2016() {
        //Test Case 2016
        UsageCalculus.TestCase = 2016;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/-1/0.0/x^8 + 1.0/-1/0.0/x^5 + 1.0/-1/0.0/x^4 - 1.0/-1/0.0/x - 25/-1/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-Infinity*-8.0/x^9.0-Infinity*-5.0/x^6.0-Infinity*-4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-Infinity/-7.0/x^7.0-Infinity/-4.0/x^4.0-Infinity/-3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="Infinity/7.0/x^7.0+Infinity/4.0/x^4.0+Infinity/3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "Infinity*8.0*-9.0/x^10.0+Infinity*5.0*-6.0/x^7.0+Infinity*4.0*-5.0/x^6.0-Infinity*-2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-Infinity*72.0*-10.0/x^11.0-Infinity*30.0*-7.0/x^8.0-Infinity*20.0*-6.0/x^7.0+Infinity*2.0*-3.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "Infinity*720.0*-11.0/x^12.0+Infinity*210.0*-8.0/x^9.0+Infinity*120.0*-7.0/x^8.0-Infinity*6.0*-4.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "-Infinity*7920.0/x^12.0-Infinity*1680.0/x^9.0-Infinity*840.0/x^8.0+Infinity*24.0/x^5.0+Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    //0/0.0
    public static void TestCaseDiff2017() {
        //Test Case 2017
        UsageCalculus.TestCase = 2017;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*0/0.0/x^8 + 1.0*0/0.0/x^5 + 1.0*0/0.0/x^4 - 1.0*0/0.0/x - 25*0/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*-8.0/x^9.0+NaN*-5.0/x^6.0+NaN*-4.0/x^5.0+NaN/x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN*x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap3[1]="NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-NaN";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN/-7.0/x^7.0+NaN/-4.0/x^4.0+NaN/-3.0/x^3.0-NaN*ln(x)-NaN*x";
        UsageCalculus.EqnSolArrayMap4[1]="-NaN/7.0/x^7.0-NaN/4.0/x^4.0-NaN/3.0/x^3.0-NaN*ln(x)-NaN*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN*x^2.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-NaN*8.0*-9.0/x^10.0-NaN*5.0*-6.0/x^7.0-NaN*4.0*-5.0/x^6.0+NaN*2.0*x^1.0-NaN";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0+NaN*2.0*x-NaN";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0+NaN*2.0*x-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*72.0*-10.0/x^11.0+NaN*30.0*-7.0/x^8.0+NaN*20.0*-6.0/x^7.0+NaN*2.0-NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*2.0-NaN";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*2.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-NaN*720.0*-11.0/x^12.0-NaN*210.0*-8.0/x^9.0-NaN*120.0*-7.0/x^8.0+NaN*2.0*0.0-NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*7920.0/x^12.0+NaN*1680.0/x^9.0+NaN*840.0/x^8.0+NaN*0.0-NaN";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2018() {
        //Test Case 2018
        UsageCalculus.TestCase = 2018;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/0/0.0/x^8 + 1.0/0/0.0/x^5 + 1.0/0/0.0/x^4 - 1.0/0/0.0/x - 25/0/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*-8.0/x^9.0+Infinity*-5.0/x^6.0+Infinity*-4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/-7.0/x^7.0+Infinity/-4.0/x^4.0+Infinity/-3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolArrayMap4[1]="-Infinity/7.0/x^7.0-Infinity/4.0/x^4.0-Infinity/3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-Infinity*8.0*-9.0/x^10.0-Infinity*5.0*-6.0/x^7.0-Infinity*4.0*-5.0/x^6.0+Infinity*-2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*72.0*-10.0/x^11.0+Infinity*30.0*-7.0/x^8.0+Infinity*20.0*-6.0/x^7.0-Infinity*2.0*-3.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-Infinity*720.0*-11.0/x^12.0-Infinity*210.0*-8.0/x^9.0-Infinity*120.0*-7.0/x^8.0+Infinity*6.0*-4.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*7920.0/x^12.0+Infinity*1680.0/x^9.0+Infinity*840.0/x^8.0-Infinity*24.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
    public static void TestCaseDiff2019() {
        //Test Case 2019
        UsageCalculus.TestCase = 2019;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*-0/0.0/x^8 + 1.0*-0/0.0/x^5 + 1.0*-0/0.0/x^4 - 1.0*-0/0.0/x - 25*-0/0.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-0/0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/x^10.0+0.0*-6.0/x^7.0+0.0*-5.0/x^6.0-0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/x^11.0-0.0*-7.0/x^8.0-0.0*-6.0/x^7.0+0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/x^12.0+0.0*-8.0/x^9.0+0.0*-7.0/x^8.0-0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/x^12.0-0.0/x^9.0-0.0/x^8.0+0.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "";
        UsageCalculus.EqnSolArrayMap2[5] = "";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "";
        UsageCalculus.EqnSolArrayMap2[6] = "";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
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
}
