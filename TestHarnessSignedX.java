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
import Calculus.TestHarnessClass;
import Calculus.UsageCalculus;
import static Calculus.UsageCalculus.MissedNumberTestCases;
import static Calculus.UsageCalculus.MissedTestCasesException;
import static Calculus.UsageCalculus.MissedTestCasesInfinity;

/**
 *
 * @author Administrator
 */
public class TestHarnessSignedX {
   //Signed X Test Cases
    public static void SignedX(String AlgoName, int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapInfinity(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapInfinity(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED +"SignedX TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapException(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapException(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED +"SignedX TestCase:j= " + j  + ": Exception GAP" + ConsoleColors.RESET);
        }
        else {
    switch(j)    
        {
            case 40001:
                TestCaseDiff40001();
                break;
            case 40002:
                TestCaseDiff40002();
                break;
            case 40003:
                TestCaseDiff40003();
                break;
            case 40004:
                TestCaseDiff40004();
                break;
            case 40005:
                TestCaseDiff40005();
                break;
            case 40006:
                TestCaseDiff40006();
                break;
            case 40007:
                TestCaseDiff40007();
                break;
            case 40008:
                TestCaseDiff40008();
                break;    
            case 40009:
                TestCaseDiff40009();
                break;
            case 40010:
                TestCaseDiff40010();
                break;
            case 40011:
                TestCaseDiff40011();
                break;
            case 40012:
                TestCaseDiff40012();
                break;
            case 40013:
                TestCaseDiff40013();
                break;
            case 40014:
                TestCaseDiff40014();
                break;
            case 40015:
                TestCaseDiff40015();
                break;
            case 40016:
                TestCaseDiff40016();
                break;
            case 40017:
                TestCaseDiff40017();
                break;
            case 40018:
                MissedNumberTestCases++;
                break;    
            case 40019:
                TestCaseDiff40019();
                break;
            case 40020:
                TestCaseDiff40020();
                break;
            case 40021:
                TestCaseDiff40021();
                break;
            case 40022:
                MissedNumberTestCases++;
                break;
            case 40023:
                MissedNumberTestCases++;
                break;
            case 40024:
                MissedNumberTestCases++;
                break;
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
            case 40025:
                MissedNumberTestCases++;
                break;
            case 40026:
                MissedNumberTestCases++;
                break;
            case 40027:
                MissedNumberTestCases++;
                break;
            case 40028:
                MissedNumberTestCases++;
                break;    
            case 40029:
                MissedNumberTestCases++;
                break;
            case 40030:
                MissedNumberTestCases++;
                break;
            case 40031:
                MissedNumberTestCases++;
                break;
            case 40032:
                MissedNumberTestCases++;
                break;
            case 40033:
                MissedNumberTestCases++;
                break;
            case 40034:
                MissedNumberTestCases++;
                break;
       /*********************************************/
       /************ Copied Test Cases Version 2 Begins**********/
       /*********************************************/
            case 40035:
                TestCaseDiff40035();
                break;
            case 40036:
                TestCaseDiff40036();
                break;
            case 40037:
                MissedNumberTestCases++;
                break;
            case 40038:
                MissedNumberTestCases++;
                break;    
            case 40039:
                TestCaseDiff40039();
                break;
            case 40040:
                TestCaseDiff40040();
                break;
            case 40041:
                TestCaseDiff40041();
                break;
            case 40042:
                MissedNumberTestCases++;
                break;
            case 40043:
                TestCaseDiff40043();
                break;
            case 40044:
                TestCaseDiff40044();
                break;
            case 40045:
                TestCaseDiff40045();
                break;
            case 40046:
                TestCaseDiff40046();
                break;
            case 40047:
                TestCaseDiff40047();
                break;
            case 40048:
                TestCaseDiff40048();
                break;
            case 40049:
                TestCaseDiff40049();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Exponent TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
        }
    }
        /* **************************************** */
        //Structured Testing
        /* **************************************** */
    
        /* **************************************** */
        /* ***************Signed X Test Case***************** */
        /* **************************************** */
        public static void TestCaseDiff40001() {
        //Test Case 40001
        UsageCalculus.TestCase = 40001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*+xyc1^8 + 1.0*+5*+xyc1^5 + 1.0*+5*+xyc1^4 + 1.0*+5*+xyc1 + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+1.0/6.0*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+1.0/6.0*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40002() {
        //Test Case 40002
        UsageCalculus.TestCase = 40002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*-xyc1^8 + 1.0*+5*-xyc1^5 + 1.0*+5*-xyc1^4 + 1.0*+5*-xyc1 + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xyc1^7.0-5.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xyc1^9.0-5.0/6.0*xyc1^6.0-5.0/5.0*xyc1^5.0-5.0/2.0*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xyc1^6.0-25.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xyc1^10.0-0.8333333333333334/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xyc1^5.0-100.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xyc1^11.0-0.11904761904761905/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xyc1^4.0-300.0*2.0*xyc1^1.0-120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xyc1^12.0-0.014880952380952382/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*xyc1^12.0-0.0016534391534391536*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
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
        public static void TestCaseDiff40003() {
        //Test Case 40003
        UsageCalculus.TestCase = 40003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*(-xyc1)^8 + 1.0*+5*(-xyc1)^5 + 1.0*+5*(-xyc1)^4 + 1.0*+5*(-xyc1) + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40004() {
        //Test Case 40004
        UsageCalculus.TestCase = 40004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*(+xyc1)^8 + 1.0*+5*(+xyc1)^5 + 1.0*+5*(+xyc1)^4 + 1.0*+5*(+xyc1) + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40005() {
        //Test Case 40005
        UsageCalculus.TestCase = 40005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2.1/2.1*0.0*x^8 + 0.0*x^5 + 0.0*x^4 - 0.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/9.0*x^9.0+0.0/6.0*x^6.0+0.0/5.0*x^5.0-0.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0/10.0*x^10.0+0.0/7.0*x^7.0+0.0/6.0*x^6.0-0.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.0/11.0*x^11.0+0.0/8.0*x^8.0+0.0/7.0*x^7.0-0.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0/12.0*x^12.0+0.0/9.0*x^9.0+0.0/8.0*x^8.0-0.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0*x^12.0+0.0*x^9.0+0.0*x^8.0-0.0*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40006() {
        //Test Case 40006
        UsageCalculus.TestCase = 40006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2.2/2.2*0*xy^8 + 0*xy^5 + 0*xy^4 - 0*xy - 25*0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0*xy^8.0+0.0*xy^5.0+0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="0.0*xy^8.0+0.0*xy^5.0+0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/9.0*xy^9.0+0.0/6.0*xy^6.0+0.0/5.0*xy^5.0-0.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="0.0*xy^9.0+0.0*xy^6.0+0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="0.0*xy^9.0+0.0*xy^6.0+0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0/10.0*xy^10.0+0.0/7.0*xy^7.0+0.0/6.0*xy^6.0-0.0/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.0*xy^10.0+0.0*xy^7.0+0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="0.0*xy^10.0+0.0*xy^7.0+0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.0/11.0*xy^11.0+0.0/8.0*xy^8.0+0.0/7.0*xy^7.0-0.0/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0*xy^11.0+0.0*xy^8.0+0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="0.0*xy^11.0+0.0*xy^8.0+0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0/12.0*xy^12.0+0.0/9.0*xy^9.0+0.0/8.0*xy^8.0-0.0/5.0*xy^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0*xy^12.0+0.0*xy^9.0+0.0*xy^8.0-0.0*xy^5.0";
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
        public static void TestCaseDiff40007() {
        //Test Case 40007
        UsageCalculus.TestCase = 40007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc*xyc^8 + xyc*xyc^5 + xyc*xyc^4 - xyc*xyc - xyc*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^9.0+xyc^6.0+xyc^5.0-xyc^2.0-25.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc^1.0-25.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc^9.0+xyc^6.0+xyc^5.0-xyc^2.0-25.0*xyc";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*xyc^10.0+1.0/7.0*xyc^7.0+1.0/6.0*xyc^6.0-1.0/3.0*xyc^3.0-25.0/2.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*xyc^10.0+1.0/7.0*xyc^7.0+1.0/6.0*xyc^6.0-1.0/3.0*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc^7.0+6.0*5.0*xyc^4.0+5.0*4.0*xyc^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*xyc^10.0+1.0/7.0*xyc^7.0+1.0/6.0*xyc^6.0-1.0/3.0*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc^11.0+0.14285714285714285/8.0*xyc^8.0+0.16666666666666666/7.0*xyc^7.0-0.3333333333333333/4.0*xyc^4.0-12.5/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc^6.0+30.0*4.0*xyc^3.0+20.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc^12.0+0.017857142857142856/9.0*xyc^9.0+0.023809523809523808/8.0*xyc^8.0-0.08333333333333333/5.0*xyc^5.0-4.166666666666667/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc^5.0+120.0*3.0*xyc^2.0+60.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*xyc^5.0+360.0*xyc^2.0+120.0*xyc";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc^13.0+0.001984126984126984/10.0*xyc^10.0+0.002976190476190476/9.0*xyc^9.0-0.016666666666666666/6.0*xyc^6.0-1.0416666666666667/5.0*xyc^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc^13.0+1.984126984126984E-4*xyc^10.0+3.3068783068783067E-4*xyc^9.0-0.002777777777777778*xyc^6.0-0.20833333333333334*xyc^5.0";
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
        public static void TestCaseDiff40008() {
        //Test Case 40008
        UsageCalculus.TestCase = 40008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc1*xyc1^8 + xyc1*xyc1^5 + xyc1*xyc1^4 - xyc1*xyc1 - xyc1*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Term:xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0-xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1^1.0-25.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1-25.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^9.0+xyc1^6.0+xyc1^5.0-xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc1^7.0+6.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc1^11.0+0.14285714285714285/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0-0.3333333333333333/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc1^6.0+30.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc1^12.0+0.017857142857142856/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0-0.08333333333333333/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc1^5.0+120.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*xyc1^5.0+360.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc1^13.0+0.001984126984126984/10.0*xyc1^10.0+0.002976190476190476/9.0*xyc1^9.0-0.016666666666666666/6.0*xyc1^6.0-1.0416666666666667/5.0*xyc1^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc1^13.0+1.984126984126984E-4*xyc1^10.0+3.3068783068783067E-4*xyc1^9.0-0.002777777777777778*xyc1^6.0-0.20833333333333334*xyc1^5.0";
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
    public static void TestCaseDiff40009() {
        //Test Case 40009
        UsageCalculus.TestCase = 40009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-x*x^8 -x*x^5 -x*x^4 -x*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1.0";
        UsageCalculus.TestCaseMatrixID="Term:-x:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-x^9.0-x^6.0-x^5.0-x^2.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x^1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x";
        UsageCalculus.EqnSolArrayMap3[1]="-x^9.0-x^6.0-x^5.0-x^2.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/10.0*x^10.0-1.0/7.0*x^7.0-1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/10.0*x^10.0-1.0/7.0*x^7.0-1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-9.0*8.0*x^7.0-6.0*5.0*x^4.0-5.0*4.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0*x^7.0-30.0*x^4.0-20.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/10.0*x^10.0-1.0/7.0*x^7.0-1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1/11.0*x^11.0-0.14285714285714285/8.0*x^8.0-0.16666666666666666/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.009090909090909092*x^11.0-0.017857142857142856*x^8.0-0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0*x^7.0-30.0*x^4.0-20.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*7.0*x^6.0-30.0*4.0*x^3.0-20.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-504.0*x^6.0-120.0*x^3.0-60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="-0.009090909090909092*x^11.0-0.017857142857142856*x^8.0-0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*x^12.0-0.017857142857142856/9.0*x^9.0-0.023809523809523808/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-7.575757575757577E-4*x^12.0-0.001984126984126984*x^9.0-0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-504.0*x^6.0-120.0*x^3.0-60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-504.0*6.0*x^5.0-120.0*3.0*x^2.0-60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-3024.0*x^5.0-360.0*x^2.0-120.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="-7.575757575757577E-4*x^12.0-0.001984126984126984*x^9.0-0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*x^13.0-0.001984126984126984/10.0*x^10.0-0.002976190476190476/9.0*x^9.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-5.827505827505828E-5*x^13.0-1.984126984126984E-4*x^10.0-3.3068783068783067E-4*x^9.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40010() {
        //Test Case 40010
        UsageCalculus.TestCase = 40010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-xy*xy^8 -xy*xy^5 -xy*xy^4 -xy*xy - 25*-xy";
        UsageCalculus.TestCaseName = "Simple: Term:-xy";
        UsageCalculus.TestCaseMatrixID="Term:-xy:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-xy^9.0-xy^6.0-xy^5.0-xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy^1.0+25.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy+25.0";
        UsageCalculus.EqnSolArrayMap3[1]="-xy^9.0-xy^6.0-xy^5.0-xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xy^10.0-1.0/7.0*xy^7.0-1.0/6.0*xy^6.0-1.0/3.0*xy^3.0+25.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/10.0*xy^10.0-1.0/7.0*xy^7.0-1.0/6.0*xy^6.0-1.0/3.0*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xy^7.0-6.0*5.0*xy^4.0-5.0*4.0*xy^3.0-2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0*xy^7.0-30.0*xy^4.0-20.0*xy^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/10.0*xy^10.0-1.0/7.0*xy^7.0-1.0/6.0*xy^6.0-1.0/3.0*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xy^11.0-0.14285714285714285/8.0*xy^8.0-0.16666666666666666/7.0*xy^7.0-0.3333333333333333/4.0*xy^4.0+12.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.009090909090909092*xy^11.0-0.017857142857142856*xy^8.0-0.023809523809523808*xy^7.0-0.08333333333333333*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0*xy^7.0-30.0*xy^4.0-20.0*xy^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xy^6.0-30.0*4.0*xy^3.0-20.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-504.0*xy^6.0-120.0*xy^3.0-60.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="-0.009090909090909092*xy^11.0-0.017857142857142856*xy^8.0-0.023809523809523808*xy^7.0-0.08333333333333333*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xy^12.0-0.017857142857142856/9.0*xy^9.0-0.023809523809523808/8.0*xy^8.0-0.08333333333333333/5.0*xy^5.0+4.166666666666667/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="-7.575757575757577E-4*xy^12.0-0.001984126984126984*xy^9.0-0.002976190476190476*xy^8.0-0.016666666666666666*xy^5.0+1.0416666666666667*xy^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-504.0*xy^6.0-120.0*xy^3.0-60.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xy^5.0-120.0*3.0*xy^2.0-60.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-3024.0*xy^5.0-360.0*xy^2.0-120.0*xy";
        UsageCalculus.EqnSolArrayMap3[4]="-7.575757575757577E-4*xy^12.0-0.001984126984126984*xy^9.0-0.002976190476190476*xy^8.0-0.016666666666666666*xy^5.0+1.0416666666666667*xy^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xy^13.0-0.001984126984126984/10.0*xy^10.0-0.002976190476190476/9.0*xy^9.0-0.016666666666666666/6.0*xy^6.0+1.0416666666666667/5.0*xy^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="-5.827505827505828E-5*xy^13.0-1.984126984126984E-4*xy^10.0-3.3068783068783067E-4*xy^9.0-0.002777777777777778*xy^6.0+0.20833333333333334*xy^5.0";
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
        public static void TestCaseDiff40011() {
        //Test Case 40011
        UsageCalculus.TestCase = 40011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+x*+x^8 + x*+x^5 + x*+x^4 - x*+x- 25";
        UsageCalculus.TestCaseName = "Simple: Term:+x";
        UsageCalculus.TestCaseMatrixID="Term:x:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^9.0+x^6.0+x^5.0-x^2.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x^1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x";
        UsageCalculus.EqnSolArrayMap3[1]="x^9.0+x^6.0+x^5.0-x^2.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/7.0*x^7.0+1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*x^10.0+1.0/7.0*x^7.0+1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+6.0*5.0*x^4.0+5.0*4.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^7.0+30.0*x^4.0+20.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*x^10.0+1.0/7.0*x^7.0+1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.14285714285714285/8.0*x^8.0+0.16666666666666666/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.017857142857142856*x^8.0+0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^7.0+30.0*x^4.0+20.0*x^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+30.0*4.0*x^3.0+20.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*x^6.0+120.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.017857142857142856*x^8.0+0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.017857142857142856/9.0*x^9.0+0.023809523809523808/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+0.001984126984126984*x^9.0+0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*x^6.0+120.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+120.0*3.0*x^2.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*x^5.0+360.0*x^2.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*x^12.0+0.001984126984126984*x^9.0+0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*x^13.0+0.001984126984126984/10.0*x^10.0+0.002976190476190476/9.0*x^9.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*x^13.0+1.984126984126984E-4*x^10.0+3.3068783068783067E-4*x^9.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40012() {
        //Test Case 40012
        UsageCalculus.TestCase = 40012;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xy*-xy^8 - 5*-xy^5 - 5*-xy^4 - 5*-xy - 25*-xy -67";
        UsageCalculus.TestCaseName = "Simple: Term:-xy";
        UsageCalculus.TestCaseMatrixID="Term:-xy:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-xy^9.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+25.0*xy-67.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-9.0*xy^8.0+5.0*5.0*xy^4.0+5.0*4.0*xy^3.0+5.0+25.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-9.0*xy^8.0+25.0*xy^4.0+20.0*xy^3.0+5.0+25.0";
        UsageCalculus.EqnSolArrayMap3[1]="-xy^9.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+25.0*xy-67.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xy^10.0+5.0/6.0*xy^6.0+5.0/5.0*xy^5.0+5.0/2.0*xy^2.0+25.0/2.0*xy^2.0-67.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/10.0*xy^10.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+12.5*xy^2.0-67.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-9.0*xy^8.0+25.0*xy^4.0+20.0*xy^3.0+5.0+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xy^7.0+25.0*4.0*xy^3.0+20.0*3.0*xy^2.0+0.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0*xy^7.0+100.0*xy^3.0+60.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/10.0*xy^10.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+12.5*xy^2.0-67.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xy^11.0+0.8333333333333334/7.0*xy^7.0+1.0/6.0*xy^6.0+2.5/3.0*xy^3.0+12.5/3.0*xy^3.0-67.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.009090909090909092*xy^11.0+0.11904761904761905*xy^7.0+1.0/6.0*xy^6.0+0.8333333333333334*xy^3.0+4.166666666666667*xy^3.0-33.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0*xy^7.0+100.0*xy^3.0+60.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xy^6.0+100.0*3.0*xy^2.0+60.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-504.0*xy^6.0+300.0*xy^2.0+120.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="-0.009090909090909092*xy^11.0+0.11904761904761905*xy^7.0+1.0/6.0*xy^6.0+0.8333333333333334*xy^3.0+4.166666666666667*xy^3.0-33.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xy^12.0+0.11904761904761905/8.0*xy^8.0+0.16666666666666666/7.0*xy^7.0+0.8333333333333334/4.0*xy^4.0+4.166666666666667/4.0*xy^4.0-33.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-7.575757575757577E-4*xy^12.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+1.0416666666666667*xy^4.0-11.166666666666666*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-504.0*xy^6.0+300.0*xy^2.0+120.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xy^5.0+300.0*2.0*xy^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-3024.0*xy^5.0+600.0*xy+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-7.575757575757577E-4*xy^12.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+1.0416666666666667*xy^4.0-11.166666666666666*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xy^13.0+0.014880952380952382/9.0*xy^9.0+0.023809523809523808/8.0*xy^8.0+0.20833333333333334/5.0*xy^5.0+1.0416666666666667/5.0*xy^5.0-11.166666666666666/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-5.827505827505828E-5*xy^13.0+0.0016534391534391536*xy^9.0+0.002976190476190476*xy^8.0+0.04166666666666667*xy^5.0+0.20833333333333334*xy^5.0-2.7916666666666665*xy^4.0";
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
        public static void TestCaseDiff40013() {
        //Test Case 40013
        UsageCalculus.TestCase = 40013;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-xyc1*xyc1^8 - xyc1*xyc1^5 - xyc1*xyc1^4 - xyc1*xyc1 - xyc1*25";
        UsageCalculus.TestCaseName = "Simple: Term:-xyc1";
        UsageCalculus.TestCaseMatrixID="Term:-xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-xyc1^9.0-xyc1^6.0-xyc1^5.0-xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1^1.0-25.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1-25.0";
        UsageCalculus.EqnSolArrayMap3[1]=  "-xyc1^9.0-xyc1^6.0-xyc1^5.0-xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xyc1^10.0-1.0/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/10.0*xyc1^10.0-1.0/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xyc1^7.0-6.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0*xyc1^7.0-30.0*xyc1^4.0-20.0*xyc1^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/10.0*xyc1^10.0-1.0/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xyc1^11.0-0.14285714285714285/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.3333333333333333/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.009090909090909092*xyc1^11.0-0.017857142857142856*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0*xyc1^7.0-30.0*xyc1^4.0-20.0*xyc1^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xyc1^6.0-30.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-504.0*xyc1^6.0-120.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="-0.009090909090909092*xyc1^11.0-0.017857142857142856*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xyc1^12.0-0.017857142857142856/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.08333333333333333/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="-7.575757575757577E-4*xyc1^12.0-0.001984126984126984*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-504.0*xyc1^6.0-120.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xyc1^5.0-120.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-3024.0*xyc1^5.0-360.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[4]="-7.575757575757577E-4*xyc1^12.0-0.001984126984126984*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xyc1^13.0-0.001984126984126984/10.0*xyc1^10.0-0.002976190476190476/9.0*xyc1^9.0-0.016666666666666666/6.0*xyc1^6.0-1.0416666666666667/5.0*xyc1^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="-5.827505827505828E-5*xyc1^13.0-1.984126984126984E-4*xyc1^10.0-3.3068783068783067E-4*xyc1^9.0-0.002777777777777778*xyc1^6.0-0.20833333333333334*xyc1^5.0";
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
        public static void TestCaseDiff40014() {
        //Test Case 40014
        UsageCalculus.TestCase = 40014;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+xyc1*xyc1^8 + 1.0*+xyc1*xyc1^5 + 1.0*+xyc1*xyc1^4 + 1.0*+xyc1*xyc1 + 1.0*+xyc1*25 + 67";
        UsageCalculus.TestCaseName = "Simple: Term:+xyc1";
        UsageCalculus.TestCaseMatrixID="Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0+xyc1^2.0+25.0*xyc1+67.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1^1.0+25.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayMap3[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0+xyc1^2.0+25.0*xyc1+67.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]= "1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+1.0/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0+67.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]= "1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+1.0/3.0*xyc1^3.0+12.5*xyc1^2.0+67.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc1^7.0+6.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0+2.0";
        UsageCalculus.EqnSolArrayMap3[2] = "1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+1.0/3.0*xyc1^3.0+12.5*xyc1^2.0+67.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]= "0.1/11.0*xyc1^11.0+0.14285714285714285/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.3333333333333333/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0+67.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]= "0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.08333333333333333*xyc1^4.0+4.166666666666667*xyc1^3.0+33.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0+2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc1^6.0+30.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[3] = "0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.08333333333333333*xyc1^4.0+4.166666666666667*xyc1^3.0+33.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]= "0.009090909090909092/12.0*xyc1^12.0+0.017857142857142856/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.08333333333333333/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0+33.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]= "7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.016666666666666666*xyc1^5.0+1.0416666666666667*xyc1^4.0+11.166666666666666*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc1^5.0+120.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*xyc1^5.0+360.0*xyc1^2.0+120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.016666666666666666*xyc1^5.0+1.0416666666666667*xyc1^4.0+11.166666666666666*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc1^13.0+0.001984126984126984/10.0*xyc1^10.0+0.002976190476190476/9.0*xyc1^9.0+0.016666666666666666/6.0*xyc1^6.0+1.0416666666666667/5.0*xyc1^5.0+11.166666666666666/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc1^13.0+1.984126984126984E-4*xyc1^10.0+3.3068783068783067E-4*xyc1^9.0+0.002777777777777778*xyc1^6.0+0.20833333333333334*xyc1^5.0+2.7916666666666665*xyc1^4.0";
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
        public static void TestCaseDiff40015() {
        //Test Case 40015
        UsageCalculus.TestCase = 40015;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "xyc1*+xyc1*xyc1^8 + xyc1*+xyc1*xyc1^5 + xyc1*+xyc1*xyc1^4 + xyc1*+xyc1*xyc1 + xyc1*+xyc1*25";
        UsageCalculus.TestCaseName = "Simple: Term:+xyc1";
        UsageCalculus.TestCaseMatrixID="Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^10.0+xyc1^7.0+xyc1^6.0+xyc1^3.0+25.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+25.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[1] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+50.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^10.0+xyc1^7.0+xyc1^6.0+xyc1^3.0+25.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/11.0*xyc1^11.0+1.0/8.0*xyc1^8.0+1.0/7.0*xyc1^7.0+1.0/4.0*xyc1^4.0+25.0/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/11.0*xyc1^11.0+1.0/8.0*xyc1^8.0+1.0/7.0*xyc1^7.0+1.0/4.0*xyc1^4.0+8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+50.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "10.0*9.0*xyc1^8.0+7.0*6.0*xyc1^5.0+6.0*5.0*xyc1^4.0+3.0*2.0*xyc1^1.0+50.0";
        UsageCalculus.EqnSolArrayMap2[2] = "90.0*xyc1^8.0+42.0*xyc1^5.0+30.0*xyc1^4.0+6.0*xyc1+50.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/11.0*xyc1^11.0+1.0/8.0*xyc1^8.0+1.0/7.0*xyc1^7.0+1.0/4.0*xyc1^4.0+8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*xyc1^12.0+0.125/9.0*xyc1^9.0+0.14285714285714285/8.0*xyc1^8.0+0.25/5.0*xyc1^5.0+8.333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.007575757575757576*xyc1^12.0+0.013888888888888888*xyc1^9.0+0.017857142857142856*xyc1^8.0+0.05*xyc1^5.0+2.0833333333333335*xyc1^4.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "90.0*xyc1^8.0+42.0*xyc1^5.0+30.0*xyc1^4.0+6.0*xyc1+50.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "90.0*8.0*xyc1^7.0+42.0*5.0*xyc1^4.0+30.0*4.0*xyc1^3.0+6.0+0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0*xyc1^7.0+210.0*xyc1^4.0+120.0*xyc1^3.0+6.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.007575757575757576*xyc1^12.0+0.013888888888888888*xyc1^9.0+0.017857142857142856*xyc1^8.0+0.05*xyc1^5.0+2.0833333333333335*xyc1^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*xyc1^13.0+0.013888888888888888/10.0*xyc1^10.0+0.017857142857142856/9.0*xyc1^9.0+0.05/6.0*xyc1^6.0+2.0833333333333335/5.0*xyc1^5.0";
        UsageCalculus.EqnSolArrayMap4[3]="5.827505827505828E-4*xyc1^13.0+0.0013888888888888887*xyc1^10.0+0.001984126984126984*xyc1^9.0+0.008333333333333333*xyc1^6.0+0.4166666666666667*xyc1^5.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0*xyc1^7.0+210.0*xyc1^4.0+120.0*xyc1^3.0+6.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*7.0*xyc1^6.0+210.0*4.0*xyc1^3.0+120.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5040.0*xyc1^6.0+840.0*xyc1^3.0+360.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[4]="5.827505827505828E-4*xyc1^13.0+0.0013888888888888887*xyc1^10.0+0.001984126984126984*xyc1^9.0+0.008333333333333333*xyc1^6.0+0.4166666666666667*xyc1^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="5.827505827505828E-4/14.0*xyc1^14.0+0.0013888888888888887/11.0*xyc1^11.0+0.001984126984126984/10.0*xyc1^10.0+0.008333333333333333/7.0*xyc1^7.0+0.4166666666666667/6.0*xyc1^6.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.1625041625041625E-5*xyc1^14.0+1.2626262626262624E-4*xyc1^11.0+1.984126984126984E-4*xyc1^10.0+0.0011904761904761904*xyc1^7.0+0.06944444444444445*xyc1^6.0";
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
        public static void TestCaseDiff40016() {
        //Test Case 40016
        UsageCalculus.TestCase = 40016;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+1.0*+xyc1^8 + 1.0*+1.0*+xyc1^5 + 1.0*+1.0*+xyc1^4 + 1.0*+1.0*+xyc1 + 1.0*+1.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1.0:Term :+xyc1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0+xyc1+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0+xyc1+25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+1.0/2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+1.0/2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+1.0/2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc1^10.0+0.16666666666666666/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0+0.5/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.16666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.16666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc1^11.0+0.023809523809523808/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0+0.16666666666666666/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.041666666666666664*xyc1^4.0+4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.041666666666666664*xyc1^4.0+4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc1^12.0+0.002976190476190476/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0+0.041666666666666664/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0+0.008333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40017() {
        //Test Case 40017
        UsageCalculus.TestCase = 40017;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5.0*-xyc1^8 + 1.0*+5.0*-xyc1^5 + 1.0*+5.0*-xyc1^4 + 1.0*+5.0*-xyc1 + 1.0*+5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0:Term :-xyc1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xyc1^7.0-5.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xyc1^9.0-5.0/6.0*xyc1^6.0-5.0/5.0*xyc1^5.0-5.0/2.0*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xyc1^6.0-25.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xyc1^10.0-0.8333333333333334/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xyc1^5.0-100.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xyc1^11.0-0.11904761904761905/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xyc1^4.0-300.0*2.0*xyc1^1.0-120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xyc1^12.0-0.014880952380952382/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*xyc1^12.0-0.0016534391534391536*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
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
        
        public static void TestCaseDiff40019() {
        //Test Case 40019
        UsageCalculus.TestCase = 40019;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+235*+xyc1^8 + 1.0*+2355*+xyc1^5 + 1.0*+235*+xyc1^4 + 1.0*+235*+xyc1 + 1.0*+235*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+235:Term:+xyc1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+235:Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "235.0*xyc1^8.0+2355.0*xyc1^5.0+235.0*xyc1^4.0+235.0*xyc1+5875.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "235.0*8.0*xyc1^7.0+2355.0*5.0*xyc1^4.0+235.0*4.0*xyc1^3.0+235.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1880.0*xyc1^7.0+11775.0*xyc1^4.0+940.0*xyc1^3.0+235.0";
        UsageCalculus.EqnSolArrayMap3[1]="235.0*xyc1^8.0+2355.0*xyc1^5.0+235.0*xyc1^4.0+235.0*xyc1+5875.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="235.0/9.0*xyc1^9.0+2355.0/6.0*xyc1^6.0+235.0/5.0*xyc1^5.0+235.0/2.0*xyc1^2.0+5875.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="26.11111111111111*xyc1^9.0+392.5*xyc1^6.0+47.0*xyc1^5.0+117.5*xyc1^2.0+5875.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1880.0*xyc1^7.0+11775.0*xyc1^4.0+940.0*xyc1^3.0+235.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "1880.0*7.0*xyc1^6.0+11775.0*4.0*xyc1^3.0+940.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "13160.0*xyc1^6.0+47100.0*xyc1^3.0+2820.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="26.11111111111111*xyc1^9.0+392.5*xyc1^6.0+47.0*xyc1^5.0+117.5*xyc1^2.0+5875.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="26.11111111111111/10.0*xyc1^10.0+392.5/7.0*xyc1^7.0+47.0/6.0*xyc1^6.0+117.5/3.0*xyc1^3.0+5875.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="2.611111111111111*xyc1^10.0+56.07142857142857*xyc1^7.0+7.833333333333333*xyc1^6.0+39.166666666666664*xyc1^3.0+2937.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "13160.0*xyc1^6.0+47100.0*xyc1^3.0+2820.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "13160.0*6.0*xyc1^5.0+47100.0*3.0*xyc1^2.0+2820.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "78960.0*xyc1^5.0+141300.0*xyc1^2.0+5640.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="2.611111111111111*xyc1^10.0+56.07142857142857*xyc1^7.0+7.833333333333333*xyc1^6.0+39.166666666666664*xyc1^3.0+2937.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="2.611111111111111/11.0*xyc1^11.0+56.07142857142857/8.0*xyc1^8.0+7.833333333333333/7.0*xyc1^7.0+39.166666666666664/4.0*xyc1^4.0+2937.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.23737373737373738*xyc1^11.0+7.008928571428571*xyc1^8.0+1.119047619047619*xyc1^7.0+9.791666666666666*xyc1^4.0+979.1666666666666*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "78960.0*xyc1^5.0+141300.0*xyc1^2.0+5640.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "78960.0*5.0*xyc1^4.0+141300.0*2.0*xyc1^1.0+5640.0";
        UsageCalculus.EqnSolArrayMap2[4] = "394800.0*xyc1^4.0+282600.0*xyc1+5640.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.23737373737373738*xyc1^11.0+7.008928571428571*xyc1^8.0+1.119047619047619*xyc1^7.0+9.791666666666666*xyc1^4.0+979.1666666666666*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.23737373737373738/12.0*xyc1^12.0+7.008928571428571/9.0*xyc1^9.0+1.119047619047619/8.0*xyc1^8.0+9.791666666666666/5.0*xyc1^5.0+979.1666666666666/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.01978114478114478*xyc1^12.0+0.7787698412698413*xyc1^9.0+0.13988095238095238*xyc1^8.0+1.9583333333333333*xyc1^5.0+244.79166666666666*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40020() {
        //Test Case 40020
        UsageCalculus.TestCase = 40020;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+p*xyc1^8 + 1.0*+p*xyc1^5 + 1.0*+p*xyc1^4 + 1.0*+p*xyc1 + 1.0*+p*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+p";
        UsageCalculus.TestCaseMatrixID="Coefficients:+p:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p+p*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p+p*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p+p*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "p*8.0*7.0*xyc1^6.0+p*5.0*4.0*xyc1^3.0+p*4.0*3.0*xyc1^2.0+0.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "p*56.0*xyc1^6.0+p*20.0*xyc1^3.0+p*12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="p/9.0/10.0*xyc1^10.0+p/6.0/7.0*xyc1^7.0+p/5.0/6.0*xyc1^6.0+p/2.0/3.0*xyc1^3.0+p*25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="p/90.0*xyc1^10.0+p/42.0*xyc1^7.0+p/30.0*xyc1^6.0+p/6.0*xyc1^3.0+p*12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "p*56.0*xyc1^6.0+p*20.0*xyc1^3.0+p*12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "p*56.0*6.0*xyc1^5.0+p*20.0*3.0*xyc1^2.0+p*12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "p*336.0*xyc1^5.0+p*60.0*xyc1^2.0+p*24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="p/90.0*xyc1^10.0+p/42.0*xyc1^7.0+p/30.0*xyc1^6.0+p/6.0*xyc1^3.0+p*12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="p/90.0/11.0*xyc1^11.0+p/42.0/8.0*xyc1^8.0+p/30.0/7.0*xyc1^7.0+p/6.0/4.0*xyc1^4.0+p*12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="p/990.0*xyc1^11.0+p/336.0*xyc1^8.0+p/210.0*xyc1^7.0+p/24.0*xyc1^4.0+p*4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "p*336.0*xyc1^5.0+p*60.0*xyc1^2.0+p*24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "p*336.0*5.0*xyc1^4.0+p*60.0*2.0*xyc1^1.0+p*24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "p*1680.0*xyc1^4.0+p*120.0*xyc1+p*24.0";
        UsageCalculus.EqnSolArrayMap3[4]="p/990.0*xyc1^11.0+p/336.0*xyc1^8.0+p/210.0*xyc1^7.0+p/24.0*xyc1^4.0+p*4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="p/990.0/12.0*xyc1^12.0+p/336.0/9.0*xyc1^9.0+p/210.0/8.0*xyc1^8.0+p/24.0/5.0*xyc1^5.0+p*4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="p/11880.0*xyc1^12.0+p/3024.0*xyc1^9.0+p/1680.0*xyc1^8.0+p/120.0*xyc1^5.0+p*1.0416666666666667*xyc1^4.0";
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
        public static void TestCaseDiff40021() {
        //Test Case 40021
        UsageCalculus.TestCase = 40021;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+2^a*xyc1^8 + 1.0*+2^a*xyc1^5 + 1.0*+2^a*xyc1^4 + 1.0*+2^a*xyc1 + 1.0*+2^a*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+2^a";
        UsageCalculus.TestCaseMatrixID="Coefficients:+2^a:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "2.0^a*xyc1^8.0+2.0^a*xyc1^5.0+2.0^a*xyc1^4.0+2.0^a*xyc1+2.0^a*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a";
        UsageCalculus.EqnSolArrayMap3[1]="2.0^a*xyc1^8.0+2.0^a*xyc1^5.0+2.0^a*xyc1^4.0+2.0^a*xyc1+2.0^a*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="2.0^a/9.0*xyc1^9.0+2.0^a/6.0*xyc1^6.0+2.0^a/5.0*xyc1^5.0+2.0^a/2.0*xyc1^2.0+2.0^a*25.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="2.0^a/9.0*xyc1^9.0+2.0^a/6.0*xyc1^6.0+2.0^a/5.0*xyc1^5.0+2.0^a/2.0*xyc1^2.0+2.0^a*25.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*xyc1^6.0+2.0^a*5.0*4.0*xyc1^3.0+2.0^a*4.0*3.0*xyc1^2.0+";
        UsageCalculus.EqnSolArrayMap2[2] = "2.0^a*56.0*xyc1^6.0+2.0^a*20.0*xyc1^3.0+2.0^a*12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="2.0^a/9.0*xyc1^9.0+2.0^a/6.0*xyc1^6.0+2.0^a/5.0*xyc1^5.0+2.0^a/2.0*xyc1^2.0+2.0^a*25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="2.0^a/9.0/10.0*xyc1^10.0+2.0^a/6.0/7.0*xyc1^7.0+2.0^a/5.0/6.0*xyc1^6.0+2.0^a/2.0/3.0*xyc1^3.0+2.0^a*25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="2.0^a/90.0*xyc1^10.0+2.0^a/42.0*xyc1^7.0+2.0^a/30.0*xyc1^6.0+2.0^a/6.0*xyc1^3.0+2.0^a*12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.0^a*56.0*xyc1^6.0+2.0^a*20.0*xyc1^3.0+2.0^a*12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*xyc1^5.0+2.0^a*20.0*3.0*xyc1^2.0+2.0^a*12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "2.0^a*336.0*xyc1^5.0+2.0^a*60.0*xyc1^2.0+2.0^a*24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="2.0^a/90.0*xyc1^10.0+2.0^a/42.0*xyc1^7.0+2.0^a/30.0*xyc1^6.0+2.0^a/6.0*xyc1^3.0+2.0^a*12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="2.0^a/90.0/11.0*xyc1^11.0+2.0^a/42.0/8.0*xyc1^8.0+2.0^a/30.0/7.0*xyc1^7.0+2.0^a/6.0/4.0*xyc1^4.0+2.0^a*12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="2.0^a/990.0*xyc1^11.0+2.0^a/336.0*xyc1^8.0+2.0^a/210.0*xyc1^7.0+2.0^a/24.0*xyc1^4.0+2.0^a*4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "2.0^a*336.0*xyc1^5.0+2.0^a*60.0*xyc1^2.0+2.0^a*24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*xyc1^4.0+2.0^a*60.0*2.0*xyc1^1.0+2.0^a*24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "2.0^a*1680.0*xyc1^4.0+2.0^a*120.0*xyc1+2.0^a*24.0";
        UsageCalculus.EqnSolArrayMap3[4]="2.0^a/990.0*xyc1^11.0+2.0^a/336.0*xyc1^8.0+2.0^a/210.0*xyc1^7.0+2.0^a/24.0*xyc1^4.0+2.0^a*4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="2.0^a/990.0/12.0*xyc1^12.0+2.0^a/336.0/9.0*xyc1^9.0+2.0^a/210.0/8.0*xyc1^8.0+2.0^a/24.0/5.0*xyc1^5.0+2.0^a*4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.0^a/11880.0*xyc1^12.0+2.0^a/3024.0*xyc1^9.0+2.0^a/1680.0*xyc1^8.0+2.0^a/120.0*xyc1^5.0+2.0^a*1.0416666666666667*xyc1^4.0";
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
       
              
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       
       
       /*********************************************/
       /************ Copied Test Cases Version 2 Begins**********/
       /*********************************************/
        
        public static void TestCaseDiff40035() {
        //Test Case 40035
        UsageCalculus.TestCase = 40035;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^2 + 1.0*+5*+xyc1^5/xyc1^2 + 1.0*+5*+xyc1^4/xyc1^2 + 1.0*+5*+xyc1/xyc1^2 + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Complex: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*xyc1^6.0+5.0*xyc1^3.0+5.0*xyc1^2.0+5.0/xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*6.0*xyc1^5.0+5.0*3.0*xyc1^2.0+5.0*2.0*xyc1^1.0-5.0/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "30.0*xyc1^5.0+15.0*xyc1^2.0+10.0*xyc1-5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*xyc1^6.0+5.0*xyc1^3.0+5.0*xyc1^2.0+5.0/xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/7.0*xyc1^7.0+5.0/4.0*xyc1^4.0+5.0/3.0*xyc1^3.0+5.0*ln(xyc1)+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.7142857142857143*xyc1^7.0+1.25*xyc1^4.0+1.6666666666666667*xyc1^3.0+5.0*ln(xyc1)+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "30.0*xyc1^5.0+15.0*xyc1^2.0+10.0*xyc1-5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "30.0*5.0*xyc1^4.0+15.0*2.0*xyc1^1.0+10.0-5.0*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "150.0*xyc1^4.0+30.0*xyc1+10.0+10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "150.0*xyc1^4.0+30.0*xyc1+10.0+10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "150.0*4.0*xyc1^3.0+30.0+0.0+10.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "600.0*xyc1^3.0+30.0-30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "600.0*xyc1^3.0+30.0-30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "600.0*3.0*xyc1^2.0+0.0-30.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1800.0*xyc1^2.0+120.0/xyc1^5.0";
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
        public static void TestCaseDiff40036() {
        //Test Case 40036
        UsageCalculus.TestCase = 40036;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*-xyc1^8/xyc1^2 + 1.0*+5*-xyc1^5/xyc1^2 + 1.0*+5*-xyc1^4/xyc1^2 + 1.0*+5*-xyc1/xyc1^2 + 1.0*+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xyc1^6.0-5.0*xyc1^3.0-5.0*xyc1^2.0-5.0/xyc1+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*6.0*xyc1^5.0-5.0*3.0*xyc1^2.0-5.0*2.0*xyc1^1.0+5.0/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-30.0*xyc1^5.0-15.0*xyc1^2.0-10.0*xyc1+5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xyc1^6.0-5.0*xyc1^3.0-5.0*xyc1^2.0-5.0/xyc1+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/7.0*xyc1^7.0-5.0/4.0*xyc1^4.0-5.0/3.0*xyc1^3.0-5.0*ln(xyc1)+125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.7142857142857143*xyc1^7.0-1.25*xyc1^4.0-1.6666666666666667*xyc1^3.0-5.0*ln(xyc1)+125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-30.0*xyc1^5.0-15.0*xyc1^2.0-10.0*xyc1+5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-30.0*5.0*xyc1^4.0-15.0*2.0*xyc1^1.0-10.0+5.0*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-150.0*xyc1^4.0-30.0*xyc1-10.0-10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-150.0*xyc1^4.0-30.0*xyc1-10.0-10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-150.0*4.0*xyc1^3.0-30.0-0.0-10.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-600.0*xyc1^3.0-30.0+30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-600.0*xyc1^3.0-30.0+30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-600.0*3.0*xyc1^2.0-0.0+30.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-1800.0*xyc1^2.0-120.0/xyc1^5.0";
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
        
        
        public static void TestCaseDiff40039() {
        //Test Case 40039
        UsageCalculus.TestCase = 40039;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0E+0*x^8 + 0.0E+0*x^5 + 0.0E+0*x^4 - 0.0E+0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E+0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0E+0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/9.0*x^9.0+0.0/6.0*x^6.0+0.0/5.0*x^5.0-0.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0/10.0*x^10.0+0.0/7.0*x^7.0+0.0/6.0*x^6.0-0.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.0/11.0*x^11.0+0.0/8.0*x^8.0+0.0/7.0*x^7.0-0.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0/12.0*x^12.0+0.0/9.0*x^9.0+0.0/8.0*x^8.0-0.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0*x^12.0+0.0*x^9.0+0.0*x^8.0-0.0*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40040() {
        //Test Case 40040
        UsageCalculus.TestCase = 40040;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0E-0*xy^8 - 0.0E-0*xy^5 - 0.0E-0*xy^4 - 0.0E+0*xy - 25*0.0E-0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E-0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0*xy^8.0-0.0*xy^5.0-0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0*xy^8.0-0.0*xy^5.0-0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/9.0*xy^9.0-0.0/6.0*xy^6.0-0.0/5.0*xy^5.0-0.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0*xy^9.0-0.0*xy^6.0-0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="-0.0*xy^9.0-0.0*xy^6.0-0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.0/10.0*xy^10.0-0.0/7.0*xy^7.0-0.0/6.0*xy^6.0-0.0/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.0*xy^10.0-0.0*xy^7.0-0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="-0.0*xy^10.0-0.0*xy^7.0-0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.0/11.0*xy^11.0-0.0/8.0*xy^8.0-0.0/7.0*xy^7.0-0.0/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0*xy^11.0-0.0*xy^8.0-0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0*xy^11.0-0.0*xy^8.0-0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0/12.0*xy^12.0-0.0/9.0*xy^9.0-0.0/8.0*xy^8.0-0.0/5.0*xy^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="-0.0*xy^12.0-0.0*xy^9.0-0.0*xy^8.0-0.0*xy^5.0";
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
        public static void TestCaseDiff40041() {
        //Test Case 40041
        UsageCalculus.TestCase = 40041;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc^0.0E-0*xyc^8 + xyc^0.0E-0*xyc^5 + xyc^0.0E-0*xyc^4 - xyc^0.0E-0*xyc - xyc*0.0E-0*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0/2.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0*xyc^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc^6.0+5.0*4.0*xyc^3.0+4.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc^10.0+0.16666666666666666/7.0*xyc^7.0+0.2/6.0*xyc^6.0-0.5/3.0*xyc^3.0-0.0/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-0.0*xyc^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc^5.0+20.0*3.0*xyc^2.0+12.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-0.0*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc^11.0+0.023809523809523808/8.0*xyc^8.0+0.03333333333333333/7.0*xyc^7.0-0.16666666666666666/4.0*xyc^4.0-0.0/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-0.0*xyc^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc^4.0+60.0*2.0*xyc^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc^4.0+120.0*xyc+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-0.0*xyc^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc^12.0+0.002976190476190476/9.0*xyc^9.0+0.0047619047619047615/8.0*xyc^8.0-0.041666666666666664/5.0*xyc^5.0-0.0/5.0*xyc^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc^12.0+3.3068783068783067E-4*xyc^9.0+5.952380952380952E-4*xyc^8.0-0.008333333333333333*xyc^5.0-0.0*xyc^5.0";
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
        
    
     /*********************************************/
       /************ Copied Test Cases Version 2 Ends**********/
       /*********************************************/ 
        public static void TestCaseDiff40043() {
        //Test Case 40043
        UsageCalculus.TestCase = 40043;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0E+10*x^8 + 0.0E+10*x^5 + 0.0E+10*x^4 - 0.0E+10*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E+10";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0E+0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0+0.0+0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="0.0*x^8.0+0.0*x^5.0+0.0*x^4.0-0.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/9.0*x^9.0+0.0/6.0*x^6.0+0.0/5.0*x^5.0-0.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="0.0*x^9.0+0.0*x^6.0+0.0*x^5.0-0.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0/10.0*x^10.0+0.0/7.0*x^7.0+0.0/6.0*x^6.0-0.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="0.0*x^10.0+0.0*x^7.0+0.0*x^6.0-0.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.0/11.0*x^11.0+0.0/8.0*x^8.0+0.0/7.0*x^7.0-0.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="0.0*x^11.0+0.0*x^8.0+0.0*x^7.0-0.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0/12.0*x^12.0+0.0/9.0*x^9.0+0.0/8.0*x^8.0-0.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0*x^12.0+0.0*x^9.0+0.0*x^8.0-0.0*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40044() {
        //Test Case 40044
        UsageCalculus.TestCase = 40044;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0E-10*xy^8 - 0.0E-10*xy^5 - 0.0E-10*xy^4 - 0.0E+10*xy - 25*0.0E-10";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E-0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0*xy^8.0-0.0*xy^5.0-0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-0.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0*xy^8.0-0.0*xy^5.0-0.0*xy^4.0-0.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/9.0*xy^9.0-0.0/6.0*xy^6.0-0.0/5.0*xy^5.0-0.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0*xy^9.0-0.0*xy^6.0-0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="-0.0*xy^9.0-0.0*xy^6.0-0.0*xy^5.0-0.0*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.0/10.0*xy^10.0-0.0/7.0*xy^7.0-0.0/6.0*xy^6.0-0.0/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.0*xy^10.0-0.0*xy^7.0-0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="-0.0*xy^10.0-0.0*xy^7.0-0.0*xy^6.0-0.0*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.0/11.0*xy^11.0-0.0/8.0*xy^8.0-0.0/7.0*xy^7.0-0.0/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0*xy^11.0-0.0*xy^8.0-0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0*xy^11.0-0.0*xy^8.0-0.0*xy^7.0-0.0*xy^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0/12.0*xy^12.0-0.0/9.0*xy^9.0-0.0/8.0*xy^8.0-0.0/5.0*xy^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="-0.0*xy^12.0-0.0*xy^9.0-0.0*xy^8.0-0.0*xy^5.0";
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
        public static void TestCaseDiff40045() {
        //Test Case 40045
        UsageCalculus.TestCase = 40045;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc^0.0E-10*xyc^8 + xyc^0.0E-10*xyc^5 + xyc^0.0E-10*xyc^4 - xyc^0.0E-10*xyc - xyc*0.0E-10*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0/2.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0*xyc^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc^6.0+5.0*4.0*xyc^3.0+4.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-0.0*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc^10.0+0.16666666666666666/7.0*xyc^7.0+0.2/6.0*xyc^6.0-0.5/3.0*xyc^3.0-0.0/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-0.0*xyc^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc^5.0+20.0*3.0*xyc^2.0+12.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-0.0*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc^11.0+0.023809523809523808/8.0*xyc^8.0+0.03333333333333333/7.0*xyc^7.0-0.16666666666666666/4.0*xyc^4.0-0.0/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-0.0*xyc^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc^4.0+60.0*2.0*xyc^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc^4.0+120.0*xyc+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-0.0*xyc^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc^12.0+0.002976190476190476/9.0*xyc^9.0+0.0047619047619047615/8.0*xyc^8.0-0.041666666666666664/5.0*xyc^5.0-0.0/5.0*xyc^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc^12.0+3.3068783068783067E-4*xyc^9.0+5.952380952380952E-4*xyc^8.0-0.008333333333333333*xyc^5.0-0.0*xyc^5.0";
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
        public static void TestCaseDiff40046() {
        //Test Case 40046
        UsageCalculus.TestCase = 40046;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0E+0.010*x^8 + 0.0E+0.010*x^5 + 0.0E+0.010*x^4 - 0.0E+0.010*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E+0.010";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0E+0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40047() {
        //Test Case 40047
        UsageCalculus.TestCase = 40047;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0E-0.010*xy^8 - 0.0E-0.010*xy^5 - 0.0E-0.010*xy^4 - 0.0E+0.010*xy - 25*0.0E-0.010";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0E-0.010";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="";
        UsageCalculus.EqnSolArrayMap4[1]="";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
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
        public static void TestCaseDiff40048() {
        //Test Case 40048
        UsageCalculus.TestCase = 40048;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc^0.0E-0.010*xyc^8 + xyc^0.0E-0.010*xyc^5 + xyc^0.0E-0.010*xyc^4 - xyc^0.0E-0.010*xyc - xyc*0.0E-0.010*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="";
        UsageCalculus.EqnSolArrayMap4[1]="";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc^7.0+6.0*5.0*xyc^4.0+5.0*4.0*xyc^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.1*xyc^10.0+0.14285714285714285*xyc^7.0+0.16666666666666666*xyc^6.0-0.3333333333333333*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc^11.0+0.14285714285714285/8.0*xyc^8.0+0.16666666666666666/7.0*xyc^7.0-0.3333333333333333/4.0*xyc^4.0-12.5/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc^6.0+30.0*4.0*xyc^3.0+20.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc^12.0+0.017857142857142856/9.0*xyc^9.0+0.023809523809523808/8.0*xyc^8.0-0.08333333333333333/5.0*xyc^5.0-4.166666666666667/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc^5.0+120.0*3.0*xyc^2.0+60.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*xyc^5.0+360.0*xyc^2.0+120.0*xyc";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc^13.0+0.001984126984126984/10.0*xyc^10.0+0.002976190476190476/9.0*xyc^9.0-0.016666666666666666/6.0*xyc^6.0-1.0416666666666667/5.0*xyc^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc^13.0+1.984126984126984E-4*xyc^10.0+3.3068783068783067E-4*xyc^9.0-0.002777777777777778*xyc^6.0-0.20833333333333334*xyc^5.0";
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
        public static void TestCaseDiff40049() {
        //Test Case 40049
        UsageCalculus.TestCase = 40049;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc^0.0E-1.010*xyc^8 + xyc^0.0E-1.010*xyc^5 + xyc^0.0E-1.010*xyc^4 - xyc^0.0E-1.010*xyc - xyc*0.0E-1.010*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^8.0+xyc^5.0+xyc^4.0-xyc-0.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="";
        UsageCalculus.EqnSolArrayMap4[1]="";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc^7.0+6.0*5.0*xyc^4.0+5.0*4.0*xyc^3.0-2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.1*xyc^10.0+0.14285714285714285*xyc^7.0+0.16666666666666666*xyc^6.0-0.3333333333333333*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc^11.0+0.14285714285714285/8.0*xyc^8.0+0.16666666666666666/7.0*xyc^7.0-0.3333333333333333/4.0*xyc^4.0-12.5/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc^6.0+30.0*4.0*xyc^3.0+20.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc^12.0+0.017857142857142856/9.0*xyc^9.0+0.023809523809523808/8.0*xyc^8.0-0.08333333333333333/5.0*xyc^5.0-4.166666666666667/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc^5.0+120.0*3.0*xyc^2.0+60.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*xyc^5.0+360.0*xyc^2.0+120.0*xyc";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc^13.0+0.001984126984126984/10.0*xyc^10.0+0.002976190476190476/9.0*xyc^9.0-0.016666666666666666/6.0*xyc^6.0-1.0416666666666667/5.0*xyc^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc^13.0+1.984126984126984E-4*xyc^10.0+3.3068783068783067E-4*xyc^9.0-0.002777777777777778*xyc^6.0-0.20833333333333334*xyc^5.0";
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
