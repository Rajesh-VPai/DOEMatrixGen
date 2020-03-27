/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;


/**
 *
 * @author Administrator
 */
public class TestHarnessSignedX {
   //Signed X Test Cases
    public static void SignedX(String AlgoName, int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(TestHarness.IntegralGap(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(TestHarness.DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
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
                TestCaseDiff40018();
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
                TestCaseDiff40022();
                break;
            case 40023:
                TestCaseDiff40023();
                break;
            case 40024:
                TestCaseDiff40024();
                break;
            case 40025:
                TestCaseDiff40025();
                break;
            case 40026:
                TestCaseDiff40026();
                break;
            case 40027:
                TestCaseDiff40027();
                break;
            case 40028:
                TestCaseDiff40028();
                break;    
            case 40029:
                TestCaseDiff40029();
                break;
            case 40030:
                TestCaseDiff40030();
                break;
            case 40031:
                TestCaseDiff40031();
                break;
            case 40032:
                TestCaseDiff40032();
                break;
            case 40033:
                TestCaseDiff40033();
                break;
            case 40034:
                TestCaseDiff40034();
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
        Usage.TestCase = 40001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5*+xyc1^8 + 1.0*+5*+xyc1^5 + 1.0*+5*+xyc1^4 + 1.0*+5*+xyc1 + 1.0*+5*25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40002() {
        //Test Case 40002
        Usage.TestCase = 40002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5*-xyc1^8 + 1.0*+5*-xyc1^5 + 1.0*+5*-xyc1^4 + 1.0*+5*-xyc1 + 1.0*+5*25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xyc1^7.0-5.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        Usage.EqnSolArrayMap3[1]="-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xyc1^9.0-5.0/6.0*xyc1^6.0-5.0/5.0*xyc1^5.0-5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        Usage.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xyc1^6.0-25.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xyc1^10.0-0.8333333333333334/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xyc1^5.0-100.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xyc1^11.0-0.11904761904761905/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xyc1^4.0-300.0*2.0*xyc1^1.0-120.0";
        Usage.EqnSolArrayMap2[4] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayMap3[4]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xyc1^12.0-0.014880952380952382/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="-4.208754208754209E-4*xyc1^12.0-0.0016534391534391536*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
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
        public static void TestCaseDiff40003() {
        //Test Case 40003
        Usage.TestCase = 40003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5*(-xyc1)^8 + 1.0*+5*(-xyc1)^5 + 1.0*+5*(-xyc1)^4 + 1.0*+5*(-xyc1) + 1.0*+5*25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40004() {
        //Test Case 40004
        Usage.TestCase = 40004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5*(+xyc1)^8 + 1.0*+5*(+xyc1)^5 + 1.0*+5*(+xyc1)^4 + 1.0*+5*(+xyc1) + 1.0*+5*25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40005() {
        //Test Case 40005
        Usage.TestCase = 40005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "0.0*x^8 + 0.0*x^5 + 0.0*x^4 - 0.0*x- 25";
        Usage.TestCaseName = "Simple: Coefficients:0.0";
        Usage.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-0.0";
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
        public static void TestCaseDiff40006() {
        //Test Case 40006
        Usage.TestCase = 40006;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "0*xy^8 + 0*xy^5 + 0*xy^4 - 0*xy - 25*0";
        Usage.TestCaseName = "Simple: Coefficients:0";
        Usage.TestCaseMatrixID="Coefficients:0:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
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
        public static void TestCaseDiff40007() {
        //Test Case 40007
        Usage.TestCase = 40007;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "xyc*xyc^8 + xyc*xyc^5 + xyc*xyc^4 - xyc*xyc - xyc*25";
        Usage.TestCaseName = "Simple: Variables:3 Letter";
        Usage.TestCaseMatrixID="Term:xyc:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "xyc^9.0+xyc^6.0+xyc^5.0-xyc^2.0-25.0*xyc";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc^1.0-25.0";
        Usage.EqnSolArrayMap2[1] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        Usage.EqnSolArrayMap3[1]="xyc^9.0+xyc^6.0+xyc^5.0-xyc^2.0-25.0*xyc";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*xyc^10.0+1.0/7.0*xyc^7.0+1.0/6.0*xyc^6.0-1.0/3.0*xyc^3.0-25.0/2.0*xyc^2.0";
        Usage.EqnSolArrayMap4[1]="0.1*xyc^10.0+0.14285714285714285*xyc^7.0+0.16666666666666666*xyc^6.0-0.3333333333333333*xyc^3.0-12.5*xyc^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*xyc^8.0+6.0*xyc^5.0+5.0*xyc^4.0-2.0*xyc-25.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc^7.0+6.0*5.0*xyc^4.0+5.0*4.0*xyc^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="0.1*xyc^10.0+0.14285714285714285*xyc^7.0+0.16666666666666666*xyc^6.0-0.3333333333333333*xyc^3.0-12.5*xyc^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc^11.0+0.14285714285714285/8.0*xyc^8.0+0.16666666666666666/7.0*xyc^7.0-0.3333333333333333/4.0*xyc^4.0-12.5/3.0*xyc^3.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*xyc^7.0+30.0*xyc^4.0+20.0*xyc^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc^6.0+30.0*4.0*xyc^3.0+20.0*3.0*xyc^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*xyc^11.0+0.017857142857142856*xyc^8.0+0.023809523809523808*xyc^7.0-0.08333333333333333*xyc^4.0-4.166666666666667*xyc^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc^12.0+0.017857142857142856/9.0*xyc^9.0+0.023809523809523808/8.0*xyc^8.0-0.08333333333333333/5.0*xyc^5.0-4.166666666666667/4.0*xyc^4.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*xyc^6.0+120.0*xyc^3.0+60.0*xyc^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc^5.0+120.0*3.0*xyc^2.0+60.0*2.0*xyc^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*xyc^5.0+360.0*xyc^2.0+120.0*xyc";
        Usage.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc^12.0+0.001984126984126984*xyc^9.0+0.002976190476190476*xyc^8.0-0.016666666666666666*xyc^5.0-1.0416666666666667*xyc^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc^13.0+0.001984126984126984/10.0*xyc^10.0+0.002976190476190476/9.0*xyc^9.0-0.016666666666666666/6.0*xyc^6.0-1.0416666666666667/5.0*xyc^5.0";
        Usage.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc^13.0+1.984126984126984E-4*xyc^10.0+3.3068783068783067E-4*xyc^9.0-0.002777777777777778*xyc^6.0-0.20833333333333334*xyc^5.0";
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
        public static void TestCaseDiff40008() {
        //Test Case 40008
        Usage.TestCase = 40008;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "xyc1*xyc1^8 + xyc1*xyc1^5 + xyc1*xyc1^4 - xyc1*xyc1 - xyc1*25";
        Usage.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        Usage.TestCaseMatrixID="Term:xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0-xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1^1.0-25.0";
        Usage.EqnSolArrayMap2[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1-25.0";
        Usage.EqnSolArrayMap3[1]="xyc1^9.0+xyc1^6.0+xyc1^5.0-xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[1]="0.1*xyc1^10.0+0.14285714285714285*xyc1^7.0+0.16666666666666666*xyc1^6.0-0.3333333333333333*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0-2.0*xyc1-25.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc1^7.0+6.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="0.1*xyc1^10.0+0.14285714285714285*xyc1^7.0+0.16666666666666666*xyc1^6.0-0.3333333333333333*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*xyc1^11.0+0.14285714285714285/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0-0.3333333333333333/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc1^6.0+30.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*xyc1^12.0+0.017857142857142856/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0-0.08333333333333333/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc1^5.0+120.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*xyc1^5.0+360.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc1^13.0+0.001984126984126984/10.0*xyc1^10.0+0.002976190476190476/9.0*xyc1^9.0-0.016666666666666666/6.0*xyc1^6.0-1.0416666666666667/5.0*xyc1^5.0";
        Usage.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc1^13.0+1.984126984126984E-4*xyc1^10.0+3.3068783068783067E-4*xyc1^9.0-0.002777777777777778*xyc1^6.0-0.20833333333333334*xyc1^5.0";
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
    public static void TestCaseDiff40009() {
        //Test Case 40009
        Usage.TestCase = 40009;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "-x*x^8 -x*x^5 -x*x^4 -x*x- 25";
        Usage.TestCaseName = "Simple: Coefficients:-1.0";
        Usage.TestCaseMatrixID="Term:-x:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-x^9.0-x^6.0-x^5.0-x^2.0-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x^1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x";
        Usage.EqnSolArrayMap3[1]="-x^9.0-x^6.0-x^5.0-x^2.0-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-1.0/10.0*x^10.0-1.0/7.0*x^7.0-1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="-0.1*x^10.0-0.14285714285714285*x^7.0-0.16666666666666666*x^6.0-0.3333333333333333*x^3.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-9.0*x^8.0-6.0*x^5.0-5.0*x^4.0-2.0*x";
        Usage.EqnSolArrayDiffAlone[2] = "-9.0*8.0*x^7.0-6.0*5.0*x^4.0-5.0*4.0*x^3.0-2.0";
        Usage.EqnSolArrayMap2[2] = "-72.0*x^7.0-30.0*x^4.0-20.0*x^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="-0.1*x^10.0-0.14285714285714285*x^7.0-0.16666666666666666*x^6.0-0.3333333333333333*x^3.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-0.1/11.0*x^11.0-0.14285714285714285/8.0*x^8.0-0.16666666666666666/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-0.009090909090909092*x^11.0-0.017857142857142856*x^8.0-0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-72.0*x^7.0-30.0*x^4.0-20.0*x^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-72.0*7.0*x^6.0-30.0*4.0*x^3.0-20.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "-504.0*x^6.0-120.0*x^3.0-60.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="-0.009090909090909092*x^11.0-0.017857142857142856*x^8.0-0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*x^12.0-0.017857142857142856/9.0*x^9.0-0.023809523809523808/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-7.575757575757577E-4*x^12.0-0.001984126984126984*x^9.0-0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-504.0*x^6.0-120.0*x^3.0-60.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "-504.0*6.0*x^5.0-120.0*3.0*x^2.0-60.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "-3024.0*x^5.0-360.0*x^2.0-120.0*x";
        Usage.EqnSolArrayMap3[4]="-7.575757575757577E-4*x^12.0-0.001984126984126984*x^9.0-0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*x^13.0-0.001984126984126984/10.0*x^10.0-0.002976190476190476/9.0*x^9.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-5.827505827505828E-5*x^13.0-1.984126984126984E-4*x^10.0-3.3068783068783067E-4*x^9.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40010() {
        //Test Case 40010
        Usage.TestCase = 40010;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "-xy*xy^8 -xy*xy^5 -xy*xy^4 -xy*xy - 25*-xy";
        Usage.TestCaseName = "Simple: Term:-xy";
        Usage.TestCaseMatrixID="Term:-xy:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-xy^9.0-xy^6.0-xy^5.0-xy^2.0+25.0*xy";
        Usage.EqnSolArrayDiffAlone[1] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy^1.0+25.0";
        Usage.EqnSolArrayMap2[1] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy+25.0";
        Usage.EqnSolArrayMap3[1]="-xy^9.0-xy^6.0-xy^5.0-xy^2.0+25.0*xy";
        Usage.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xy^10.0-1.0/7.0*xy^7.0-1.0/6.0*xy^6.0-1.0/3.0*xy^3.0+25.0/2.0*xy^2.0";
        Usage.EqnSolArrayMap4[1]="-0.1*xy^10.0-0.14285714285714285*xy^7.0-0.16666666666666666*xy^6.0-0.3333333333333333*xy^3.0+12.5*xy^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-9.0*xy^8.0-6.0*xy^5.0-5.0*xy^4.0-2.0*xy+25.0";
        Usage.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xy^7.0-6.0*5.0*xy^4.0-5.0*4.0*xy^3.0-2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "-72.0*xy^7.0-30.0*xy^4.0-20.0*xy^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="-0.1*xy^10.0-0.14285714285714285*xy^7.0-0.16666666666666666*xy^6.0-0.3333333333333333*xy^3.0+12.5*xy^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xy^11.0-0.14285714285714285/8.0*xy^8.0-0.16666666666666666/7.0*xy^7.0-0.3333333333333333/4.0*xy^4.0+12.5/3.0*xy^3.0";
        Usage.EqnSolArrayMap4[2]="-0.009090909090909092*xy^11.0-0.017857142857142856*xy^8.0-0.023809523809523808*xy^7.0-0.08333333333333333*xy^4.0+4.166666666666667*xy^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-72.0*xy^7.0-30.0*xy^4.0-20.0*xy^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xy^6.0-30.0*4.0*xy^3.0-20.0*3.0*xy^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "-504.0*xy^6.0-120.0*xy^3.0-60.0*xy^2.0";
        Usage.EqnSolArrayMap3[3]="-0.009090909090909092*xy^11.0-0.017857142857142856*xy^8.0-0.023809523809523808*xy^7.0-0.08333333333333333*xy^4.0+4.166666666666667*xy^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xy^12.0-0.017857142857142856/9.0*xy^9.0-0.023809523809523808/8.0*xy^8.0-0.08333333333333333/5.0*xy^5.0+4.166666666666667/4.0*xy^4.0";
        Usage.EqnSolArrayMap4[3]="-7.575757575757577E-4*xy^12.0-0.001984126984126984*xy^9.0-0.002976190476190476*xy^8.0-0.016666666666666666*xy^5.0+1.0416666666666667*xy^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-504.0*xy^6.0-120.0*xy^3.0-60.0*xy^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xy^5.0-120.0*3.0*xy^2.0-60.0*2.0*xy^1.0";
        Usage.EqnSolArrayMap2[4] = "-3024.0*xy^5.0-360.0*xy^2.0-120.0*xy";
        Usage.EqnSolArrayMap3[4]="-7.575757575757577E-4*xy^12.0-0.001984126984126984*xy^9.0-0.002976190476190476*xy^8.0-0.016666666666666666*xy^5.0+1.0416666666666667*xy^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xy^13.0-0.001984126984126984/10.0*xy^10.0-0.002976190476190476/9.0*xy^9.0-0.016666666666666666/6.0*xy^6.0+1.0416666666666667/5.0*xy^5.0";
        Usage.EqnSolArrayMap4[4]="-5.827505827505828E-5*xy^13.0-1.984126984126984E-4*xy^10.0-3.3068783068783067E-4*xy^9.0-0.002777777777777778*xy^6.0+0.20833333333333334*xy^5.0";
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
        public static void TestCaseDiff40011() {
        //Test Case 40011
        Usage.TestCase = 40011;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "+x*+x^8 + x*+x^5 + x*+x^4 - x*+x- 25";
        Usage.TestCaseName = "Simple: Term:+x";
        Usage.TestCaseMatrixID="Term:x:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^9.0+x^6.0+x^5.0-x^2.0-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x^1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x";
        Usage.EqnSolArrayMap3[1]="x^9.0+x^6.0+x^5.0-x^2.0-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/7.0*x^7.0+1.0/6.0*x^6.0-1.0/3.0*x^3.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1*x^10.0+0.14285714285714285*x^7.0+0.16666666666666666*x^6.0-0.3333333333333333*x^3.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*x^8.0+6.0*x^5.0+5.0*x^4.0-2.0*x";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+6.0*5.0*x^4.0+5.0*4.0*x^3.0-2.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^7.0+30.0*x^4.0+20.0*x^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="0.1*x^10.0+0.14285714285714285*x^7.0+0.16666666666666666*x^6.0-0.3333333333333333*x^3.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.14285714285714285/8.0*x^8.0+0.16666666666666666/7.0*x^7.0-0.3333333333333333/4.0*x^4.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.017857142857142856*x^8.0+0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^7.0+30.0*x^4.0+20.0*x^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+30.0*4.0*x^3.0+20.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "504.0*x^6.0+120.0*x^3.0+60.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.017857142857142856*x^8.0+0.023809523809523808*x^7.0-0.08333333333333333*x^4.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.017857142857142856/9.0*x^9.0+0.023809523809523808/8.0*x^8.0-0.08333333333333333/5.0*x^5.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+0.001984126984126984*x^9.0+0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*x^6.0+120.0*x^3.0+60.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+120.0*3.0*x^2.0+60.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*x^5.0+360.0*x^2.0+120.0*x";
        Usage.EqnSolArrayMap3[4]="7.575757575757577E-4*x^12.0+0.001984126984126984*x^9.0+0.002976190476190476*x^8.0-0.016666666666666666*x^5.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*x^13.0+0.001984126984126984/10.0*x^10.0+0.002976190476190476/9.0*x^9.0-0.016666666666666666/6.0*x^6.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="5.827505827505828E-5*x^13.0+1.984126984126984E-4*x^10.0+3.3068783068783067E-4*x^9.0-0.002777777777777778*x^6.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff40012() {
        //Test Case 40012
        Usage.TestCase = 40012;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "xy*-xy^8 - 5*-xy^5 - 5*-xy^4 - 5*-xy - 25*-xy -67";
        Usage.TestCaseName = "Simple: Term:-xy";
        Usage.TestCaseMatrixID="Term:-xy:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-xy^9.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+25.0*xy-67.0";
        Usage.EqnSolArrayDiffAlone[1] = "-9.0*xy^8.0+5.0*5.0*xy^4.0+5.0*4.0*xy^3.0+5.0+25.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-9.0*xy^8.0+25.0*xy^4.0+20.0*xy^3.0+5.0+25.0";
        Usage.EqnSolArrayMap3[1]="-xy^9.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+25.0*xy-67.0";
        Usage.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xy^10.0+5.0/6.0*xy^6.0+5.0/5.0*xy^5.0+5.0/2.0*xy^2.0+25.0/2.0*xy^2.0-67.0*xy";
        Usage.EqnSolArrayMap4[1]="-0.1*xy^10.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+12.5*xy^2.0-67.0*xy";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-9.0*xy^8.0+25.0*xy^4.0+20.0*xy^3.0+5.0+25.0";
        Usage.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xy^7.0+25.0*4.0*xy^3.0+20.0*3.0*xy^2.0+0.0+0.0";
        Usage.EqnSolArrayMap2[2] = "-72.0*xy^7.0+100.0*xy^3.0+60.0*xy^2.0";
        Usage.EqnSolArrayMap3[2]="-0.1*xy^10.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+12.5*xy^2.0-67.0*xy";
        Usage.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xy^11.0+0.8333333333333334/7.0*xy^7.0+1.0/6.0*xy^6.0+2.5/3.0*xy^3.0+12.5/3.0*xy^3.0-67.0/2.0*xy^2.0";
        Usage.EqnSolArrayMap4[2]="-0.009090909090909092*xy^11.0+0.11904761904761905*xy^7.0+0.16666666666666666*xy^6.0+0.8333333333333334*xy^3.0+4.166666666666667*xy^3.0-33.5*xy^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-72.0*xy^7.0+100.0*xy^3.0+60.0*xy^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xy^6.0+100.0*3.0*xy^2.0+60.0*2.0*xy^1.0";
        Usage.EqnSolArrayMap2[3] = "-504.0*xy^6.0+300.0*xy^2.0+120.0*xy";
        Usage.EqnSolArrayMap3[3]="-0.009090909090909092*xy^11.0+0.11904761904761905*xy^7.0+0.16666666666666666*xy^6.0+0.8333333333333334*xy^3.0+4.166666666666667*xy^3.0-33.5*xy^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xy^12.0+0.11904761904761905/8.0*xy^8.0+0.16666666666666666/7.0*xy^7.0+0.8333333333333334/4.0*xy^4.0+4.166666666666667/4.0*xy^4.0-33.5/3.0*xy^3.0";
        Usage.EqnSolArrayMap4[3]="-7.575757575757577E-4*xy^12.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+1.0416666666666667*xy^4.0-11.166666666666666*xy^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-504.0*xy^6.0+300.0*xy^2.0+120.0*xy";
        Usage.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xy^5.0+300.0*2.0*xy^1.0+120.0";
        Usage.EqnSolArrayMap2[4] = "-3024.0*xy^5.0+600.0*xy+120.0";
        Usage.EqnSolArrayMap3[4]="-7.575757575757577E-4*xy^12.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+1.0416666666666667*xy^4.0-11.166666666666666*xy^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xy^13.0+0.014880952380952382/9.0*xy^9.0+0.023809523809523808/8.0*xy^8.0+0.20833333333333334/5.0*xy^5.0+1.0416666666666667/5.0*xy^5.0-11.166666666666666/4.0*xy^4.0";
        Usage.EqnSolArrayMap4[4]="-5.827505827505828E-5*xy^13.0+0.0016534391534391536*xy^9.0+0.002976190476190476*xy^8.0+0.04166666666666667*xy^5.0+0.20833333333333334*xy^5.0-2.7916666666666665*xy^4.0";
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
        public static void TestCaseDiff40013() {
        //Test Case 40013
        Usage.TestCase = 40013;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "-xyc1*xyc1^8 - xyc1*xyc1^5 - xyc1*xyc1^4 - xyc1*xyc1 - xyc1*25";
        Usage.TestCaseName = "Simple: Term:-xyc1";
        Usage.TestCaseMatrixID="Term:-xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-xyc1^9.0-xyc1^6.0-xyc1^5.0-xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayDiffAlone[1] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1^1.0-25.0";
        Usage.EqnSolArrayMap2[1] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1-25.0";
        Usage.EqnSolArrayMap3[1]=  "-xyc1^9.0-xyc1^6.0-xyc1^5.0-xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[1]="-1.0/10.0*xyc1^10.0-1.0/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-1.0/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[1]="-0.1*xyc1^10.0-0.14285714285714285*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.3333333333333333*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-9.0*xyc1^8.0-6.0*xyc1^5.0-5.0*xyc1^4.0-2.0*xyc1-25.0";
        Usage.EqnSolArrayDiffAlone[2] = "-9.0*8.0*xyc1^7.0-6.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-72.0*xyc1^7.0-30.0*xyc1^4.0-20.0*xyc1^3.0-2.0";
        Usage.EqnSolArrayMap3[2]="-0.1*xyc1^10.0-0.14285714285714285*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.3333333333333333*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="-0.1/11.0*xyc1^11.0-0.14285714285714285/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.3333333333333333/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[2]="-0.009090909090909092*xyc1^11.0-0.017857142857142856*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-72.0*xyc1^7.0-30.0*xyc1^4.0-20.0*xyc1^3.0-2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-72.0*7.0*xyc1^6.0-30.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[3] = "-504.0*xyc1^6.0-120.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3]="-0.009090909090909092*xyc1^11.0-0.017857142857142856*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.08333333333333333*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.009090909090909092/12.0*xyc1^12.0-0.017857142857142856/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.08333333333333333/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[3]="-7.575757575757577E-4*xyc1^12.0-0.001984126984126984*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-504.0*xyc1^6.0-120.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "-504.0*6.0*xyc1^5.0-120.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "-3024.0*xyc1^5.0-360.0*xyc1^2.0-120.0*xyc1";
        Usage.EqnSolArrayMap3[4]="-7.575757575757577E-4*xyc1^12.0-0.001984126984126984*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.016666666666666666*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="-7.575757575757577E-4/13.0*xyc1^13.0-0.001984126984126984/10.0*xyc1^10.0-0.002976190476190476/9.0*xyc1^9.0-0.016666666666666666/6.0*xyc1^6.0-1.0416666666666667/5.0*xyc1^5.0";
        Usage.EqnSolArrayMap4[4]="-5.827505827505828E-5*xyc1^13.0-1.984126984126984E-4*xyc1^10.0-3.3068783068783067E-4*xyc1^9.0-0.002777777777777778*xyc1^6.0-0.20833333333333334*xyc1^5.0";
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
        public static void TestCaseDiff40014() {
        //Test Case 40014
        Usage.TestCase = 40014;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+xyc1*xyc1^8 + 1.0*+xyc1*xyc1^5 + 1.0*+xyc1*xyc1^4 + 1.0*+xyc1*xyc1 + 1.0*+xyc1*25 + 67";
        Usage.TestCaseName = "Simple: Term:+xyc1";
        Usage.TestCaseMatrixID="Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0+xyc1^2.0+25.0*xyc1+67.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1^1.0+25.0+0.0";
        Usage.EqnSolArrayMap2[1] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1+25.0";
        Usage.EqnSolArrayMap3[1] = "xyc1^9.0+xyc1^6.0+xyc1^5.0+xyc1^2.0+25.0*xyc1+67.0";
        Usage.EqnSolArrayIntegrAlone[1]= "1.0/10.0*xyc1^10.0+1.0/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+1.0/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0+67.0*xyc1";
        Usage.EqnSolArrayMap4[1]= "0.1*xyc1^10.0+0.14285714285714285*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.3333333333333333*xyc1^3.0+12.5*xyc1^2.0+67.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*xyc1^8.0+6.0*xyc1^5.0+5.0*xyc1^4.0+2.0*xyc1+25.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*xyc1^7.0+6.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0+2.0";
        Usage.EqnSolArrayMap3[2] = "0.1*xyc1^10.0+0.14285714285714285*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.3333333333333333*xyc1^3.0+12.5*xyc1^2.0+67.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]= "0.1/11.0*xyc1^11.0+0.14285714285714285/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.3333333333333333/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0+67.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]= "0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.08333333333333333*xyc1^4.0+4.166666666666667*xyc1^3.0+33.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*xyc1^7.0+30.0*xyc1^4.0+20.0*xyc1^3.0+2.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*xyc1^6.0+30.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[3] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3] = "0.009090909090909092*xyc1^11.0+0.017857142857142856*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.08333333333333333*xyc1^4.0+4.166666666666667*xyc1^3.0+33.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]= "0.009090909090909092/12.0*xyc1^12.0+0.017857142857142856/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.08333333333333333/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0+33.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]= "7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.016666666666666666*xyc1^5.0+1.0416666666666667*xyc1^4.0+11.166666666666666*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*xyc1^6.0+120.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*xyc1^5.0+120.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*xyc1^5.0+360.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[4]="7.575757575757577E-4*xyc1^12.0+0.001984126984126984*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.016666666666666666*xyc1^5.0+1.0416666666666667*xyc1^4.0+11.166666666666666*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*xyc1^13.0+0.001984126984126984/10.0*xyc1^10.0+0.002976190476190476/9.0*xyc1^9.0+0.016666666666666666/6.0*xyc1^6.0+1.0416666666666667/5.0*xyc1^5.0+11.166666666666666/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="5.827505827505828E-5*xyc1^13.0+1.984126984126984E-4*xyc1^10.0+3.3068783068783067E-4*xyc1^9.0+0.002777777777777778*xyc1^6.0+0.20833333333333334*xyc1^5.0+2.7916666666666665*xyc1^4.0";
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
        public static void TestCaseDiff40015() {
        //Test Case 40015
        Usage.TestCase = 40015;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "xyc1*+xyc1*xyc1^8 + xyc1*+xyc1*xyc1^5 + xyc1*+xyc1*xyc1^4 + xyc1*+xyc1*xyc1 + xyc1*+xyc1*25";
        Usage.TestCaseName = "Simple: Term:+xyc1";
        Usage.TestCaseMatrixID="Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "xyc1^10.0+xyc1^7.0+xyc1^6.0+xyc1^3.0+25.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+25.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[1] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+50.0*xyc1";
        Usage.EqnSolArrayMap3[1]="xyc1^10.0+xyc1^7.0+xyc1^6.0+xyc1^3.0+25.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/11.0*xyc1^11.0+1.0/8.0*xyc1^8.0+1.0/7.0*xyc1^7.0+1.0/4.0*xyc1^4.0+25.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[1]="0.09090909090909091*xyc1^11.0+0.125*xyc1^8.0+0.14285714285714285*xyc1^7.0+0.25*xyc1^4.0+8.333333333333334*xyc1^3.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "10.0*xyc1^9.0+7.0*xyc1^6.0+6.0*xyc1^5.0+3.0*xyc1^2.0+50.0*xyc1";
        Usage.EqnSolArrayDiffAlone[2] = "10.0*9.0*xyc1^8.0+7.0*6.0*xyc1^5.0+6.0*5.0*xyc1^4.0+3.0*2.0*xyc1^1.0+50.0";
        Usage.EqnSolArrayMap2[2] = "90.0*xyc1^8.0+42.0*xyc1^5.0+30.0*xyc1^4.0+6.0*xyc1+50.0";
        Usage.EqnSolArrayMap3[2]="0.09090909090909091*xyc1^11.0+0.125*xyc1^8.0+0.14285714285714285*xyc1^7.0+0.25*xyc1^4.0+8.333333333333334*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*xyc1^12.0+0.125/9.0*xyc1^9.0+0.14285714285714285/8.0*xyc1^8.0+0.25/5.0*xyc1^5.0+8.333333333333334/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[2]="0.007575757575757576*xyc1^12.0+0.013888888888888888*xyc1^9.0+0.017857142857142856*xyc1^8.0+0.05*xyc1^5.0+2.0833333333333335*xyc1^4.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "90.0*xyc1^8.0+42.0*xyc1^5.0+30.0*xyc1^4.0+6.0*xyc1+50.0";
        Usage.EqnSolArrayDiffAlone[3] = "90.0*8.0*xyc1^7.0+42.0*5.0*xyc1^4.0+30.0*4.0*xyc1^3.0+6.0+0.0";
        Usage.EqnSolArrayMap2[3] = "720.0*xyc1^7.0+210.0*xyc1^4.0+120.0*xyc1^3.0+6.0";
        Usage.EqnSolArrayMap3[3]="0.007575757575757576*xyc1^12.0+0.013888888888888888*xyc1^9.0+0.017857142857142856*xyc1^8.0+0.05*xyc1^5.0+2.0833333333333335*xyc1^4.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*xyc1^13.0+0.013888888888888888/10.0*xyc1^10.0+0.017857142857142856/9.0*xyc1^9.0+0.05/6.0*xyc1^6.0+2.0833333333333335/5.0*xyc1^5.0";
        Usage.EqnSolArrayMap4[3]="5.827505827505828E-4*xyc1^13.0+0.0013888888888888887*xyc1^10.0+0.001984126984126984*xyc1^9.0+0.008333333333333333*xyc1^6.0+0.4166666666666667*xyc1^5.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "720.0*xyc1^7.0+210.0*xyc1^4.0+120.0*xyc1^3.0+6.0";
        Usage.EqnSolArrayDiffAlone[4] = "720.0*7.0*xyc1^6.0+210.0*4.0*xyc1^3.0+120.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[4] = "5040.0*xyc1^6.0+840.0*xyc1^3.0+360.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[4]="5.827505827505828E-4*xyc1^13.0+0.0013888888888888887*xyc1^10.0+0.001984126984126984*xyc1^9.0+0.008333333333333333*xyc1^6.0+0.4166666666666667*xyc1^5.0";
        Usage.EqnSolArrayIntegrAlone[4]="5.827505827505828E-4/14.0*xyc1^14.0+0.0013888888888888887/11.0*xyc1^11.0+0.001984126984126984/10.0*xyc1^10.0+0.008333333333333333/7.0*xyc1^7.0+0.4166666666666667/6.0*xyc1^6.0";
        Usage.EqnSolArrayMap4[4]="4.1625041625041625E-5*xyc1^14.0+1.2626262626262624E-4*xyc1^11.0+1.984126984126984E-4*xyc1^10.0+0.0011904761904761904*xyc1^7.0+0.06944444444444445*xyc1^6.0";
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
        public static void TestCaseDiff40016() {
        //Test Case 40016
        Usage.TestCase = 40016;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+1.0*+xyc1^8 + 1.0*+1.0*+xyc1^5 + 1.0*+1.0*+xyc1^4 + 1.0*+1.0*+xyc1 + 1.0*+1.0*25";
        Usage.TestCaseName = "Simple: Coefficients:+1.0:Term :+xyc1";
        Usage.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0+xyc1+25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0+0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0";
        Usage.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0+xyc1+25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+1.0/2.0*xyc1^2.0+25.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0+0.5*xyc1^2.0+25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0+1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0+0.5*xyc1^2.0+25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc1^10.0+0.16666666666666666/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0+0.5/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.16666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.16666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc1^11.0+0.023809523809523808/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0+0.16666666666666666/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.041666666666666664*xyc1^4.0+4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.041666666666666664*xyc1^4.0+4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc1^12.0+0.002976190476190476/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0+0.041666666666666664/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0+0.008333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40017() {
        //Test Case 40017
        Usage.TestCase = 40017;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5.0*-xyc1^8 + 1.0*+5.0*-xyc1^5 + 1.0*+5.0*-xyc1^4 + 1.0*+5.0*-xyc1 + 1.0*+5.0*25";
        Usage.TestCaseName = "Simple: Coefficients:+5.0:Term :-xyc1";
        Usage.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xyc1^7.0-5.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        Usage.EqnSolArrayMap3[1]="-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xyc1^9.0-5.0/6.0*xyc1^6.0-5.0/5.0*xyc1^5.0-5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        Usage.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xyc1^6.0-25.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xyc1^10.0-0.8333333333333334/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xyc1^5.0-100.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-0.16666666666666666*xyc1^6.0-0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xyc1^11.0-0.11904761904761905/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xyc1^4.0-300.0*2.0*xyc1^1.0-120.0";
        Usage.EqnSolArrayMap2[4] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayMap3[4]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xyc1^12.0-0.014880952380952382/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="-4.208754208754209E-4*xyc1^12.0-0.0016534391534391536*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
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
        public static void TestCaseDiff40018() {
        //Test Case 40018
        Usage.TestCase = 40018;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+5*+xyc1^8 + 1.0*+5*+xyc1^5 + 1.0*+5*+xyc1^4 + 1.0*+5*+xyc1 + 1.0*+5*25";
        Usage.TestCaseName = "Simple: Coefficients:+5:Term:+xyc1";
        Usage.TestCaseMatrixID="Coefficients:+5:Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0+5.0+0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0*xyc1+125.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0/2.0*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0+5.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+2.5*xyc1^2.0+125.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*xyc1^10.0+0.8333333333333334/7.0*xyc1^7.0+1.0/6.0*xyc1^6.0+2.5/3.0*xyc1^3.0+125.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*xyc1^10.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^6.0+0.8333333333333334*xyc1^3.0+62.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*xyc1^11.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/7.0*xyc1^7.0+0.8333333333333334/4.0*xyc1^4.0+62.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*xyc1^11.0+0.014880952380952382*xyc1^8.0+0.023809523809523808*xyc1^7.0+0.20833333333333334*xyc1^4.0+20.833333333333332*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*xyc1^12.0+0.014880952380952382/9.0*xyc1^9.0+0.023809523809523808/8.0*xyc1^8.0+0.20833333333333334/5.0*xyc1^5.0+20.833333333333332/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*xyc1^12.0+0.0016534391534391536*xyc1^9.0+0.002976190476190476*xyc1^8.0+0.04166666666666667*xyc1^5.0+5.208333333333333*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40019() {
        //Test Case 40019
        Usage.TestCase = 40019;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+235*+xyc1^8 + 1.0*+2355*+xyc1^5 + 1.0*+235*+xyc1^4 + 1.0*+235*+xyc1 + 1.0*+235*25";
        Usage.TestCaseName = "Simple: Coefficients:+235:Term:+xyc1";
        Usage.TestCaseMatrixID="Coefficients:+235:Term:+xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "235.0*xyc1^8.0+2355.0*xyc1^5.0+235.0*xyc1^4.0+235.0*xyc1+5875.0";
        Usage.EqnSolArrayDiffAlone[1] = "235.0*8.0*xyc1^7.0+2355.0*5.0*xyc1^4.0+235.0*4.0*xyc1^3.0+235.0+0.0";
        Usage.EqnSolArrayMap2[1] = "1880.0*xyc1^7.0+11775.0*xyc1^4.0+940.0*xyc1^3.0+235.0";
        Usage.EqnSolArrayMap3[1]="235.0*xyc1^8.0+2355.0*xyc1^5.0+235.0*xyc1^4.0+235.0*xyc1+5875.0";
        Usage.EqnSolArrayIntegrAlone[1]="235.0/9.0*xyc1^9.0+2355.0/6.0*xyc1^6.0+235.0/5.0*xyc1^5.0+235.0/2.0*xyc1^2.0+5875.0*xyc1";
        Usage.EqnSolArrayMap4[1]="26.11111111111111*xyc1^9.0+392.5*xyc1^6.0+47.0*xyc1^5.0+117.5*xyc1^2.0+5875.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1880.0*xyc1^7.0+11775.0*xyc1^4.0+940.0*xyc1^3.0+235.0";
        Usage.EqnSolArrayDiffAlone[2] = "1880.0*7.0*xyc1^6.0+11775.0*4.0*xyc1^3.0+940.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "13160.0*xyc1^6.0+47100.0*xyc1^3.0+2820.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="26.11111111111111*xyc1^9.0+392.5*xyc1^6.0+47.0*xyc1^5.0+117.5*xyc1^2.0+5875.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="26.11111111111111/10.0*xyc1^10.0+392.5/7.0*xyc1^7.0+47.0/6.0*xyc1^6.0+117.5/3.0*xyc1^3.0+5875.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="2.611111111111111*xyc1^10.0+56.07142857142857*xyc1^7.0+7.833333333333333*xyc1^6.0+39.166666666666664*xyc1^3.0+2937.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "13160.0*xyc1^6.0+47100.0*xyc1^3.0+2820.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "13160.0*6.0*xyc1^5.0+47100.0*3.0*xyc1^2.0+2820.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "78960.0*xyc1^5.0+141300.0*xyc1^2.0+5640.0*xyc1";
        Usage.EqnSolArrayMap3[3]="2.611111111111111*xyc1^10.0+56.07142857142857*xyc1^7.0+7.833333333333333*xyc1^6.0+39.166666666666664*xyc1^3.0+2937.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="2.611111111111111/11.0*xyc1^11.0+56.07142857142857/8.0*xyc1^8.0+7.833333333333333/7.0*xyc1^7.0+39.166666666666664/4.0*xyc1^4.0+2937.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.23737373737373738*xyc1^11.0+7.008928571428571*xyc1^8.0+1.119047619047619*xyc1^7.0+9.791666666666666*xyc1^4.0+979.1666666666666*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "78960.0*xyc1^5.0+141300.0*xyc1^2.0+5640.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "78960.0*5.0*xyc1^4.0+141300.0*2.0*xyc1^1.0+5640.0";
        Usage.EqnSolArrayMap2[4] = "394800.0*xyc1^4.0+282600.0*xyc1+5640.0";
        Usage.EqnSolArrayMap3[4]="0.23737373737373738*xyc1^11.0+7.008928571428571*xyc1^8.0+1.119047619047619*xyc1^7.0+9.791666666666666*xyc1^4.0+979.1666666666666*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.23737373737373738/12.0*xyc1^12.0+7.008928571428571/9.0*xyc1^9.0+1.119047619047619/8.0*xyc1^8.0+9.791666666666666/5.0*xyc1^5.0+979.1666666666666/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="0.01978114478114478*xyc1^12.0+0.7787698412698413*xyc1^9.0+0.13988095238095238*xyc1^8.0+1.9583333333333333*xyc1^5.0+244.79166666666666*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0";
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
        public static void TestCaseDiff40020() {
        //Test Case 40020
        Usage.TestCase = 40020;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0*+p*xyc1^8 + 1.0*+p*xyc1^5 + 1.0*+p*xyc1^4 + 1.0*+p*xyc1 + 1.0*+p*25";
        Usage.TestCaseName = "Simple: Coefficients:+p";
        Usage.TestCaseMatrixID="Coefficients:+p:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p+0.0";
        Usage.EqnSolArrayMap2[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p*0.0";
        Usage.EqnSolArrayMap3[1]="p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        Usage.EqnSolArrayIntegrAlone[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+25.0*p*xyc1";
        Usage.EqnSolArrayMap4[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p*0.0";
        Usage.EqnSolArrayDiffAlone[2] = "p*8.0*7.0*xyc1^6.0+p*5.0*4.0*xyc1^3.0+p*4.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "p*56.0*xyc1^6.0+p*20.0*xyc1^3.0+p*12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="p/9.0/10.0*xyc1^10.0+p/6.0/7.0*xyc1^7.0+p/5.0/6.0*xyc1^6.0+p/2.0/3.0*xyc1^3.0+25.0*p*xyc1^2.0*1.0/2.0";
        Usage.EqnSolArrayMap4[2]="p/90.00000000000001*xyc1^10.0+p/42.0*xyc1^7.0+p/30.0*xyc1^6.0+p/6.0*xyc1^3.0+p*12.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "p*56.0*xyc1^6.0+p*20.0*xyc1^3.0+p*12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "p*56.0*6.0*xyc1^5.0+p*20.0*3.0*xyc1^2.0+p*12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "p*336.0*xyc1^5.0+p*60.0*xyc1^2.0+p*24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="p/90.00000000000001*xyc1^10.0+p/42.0*xyc1^7.0+p/30.0*xyc1^6.0+p/6.0*xyc1^3.0+p*12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="p/90.00000000000001/11.0*xyc1^11.0+p/42.0/8.0*xyc1^8.0+p/30.0/7.0*xyc1^7.0+p/6.0/4.0*xyc1^4.0+12.5*p*1.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="p/990.0000000000002*xyc1^11.0+p/336.0*xyc1^8.0+p/210.00000000000003*xyc1^7.0+p/24.0*xyc1^4.0+p*4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "p*336.0*xyc1^5.0+p*60.0*xyc1^2.0+p*24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "p*336.0*5.0*xyc1^4.0+p*60.0*2.0*xyc1^1.0+p*24.0";
        Usage.EqnSolArrayMap2[4] = "p*1680.0*xyc1^4.0+p*120.0*xyc1+p*24.0";
        Usage.EqnSolArrayMap3[4]="p/990.0000000000002*xyc1^11.0+p/336.0*xyc1^8.0+p/210.00000000000003*xyc1^7.0+p/24.0*xyc1^4.0+p*4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="p/990.0000000000002/12.0*xyc1^12.0+p/336.0/9.0*xyc1^9.0+p/210.00000000000003/8.0*xyc1^8.0+p/24.0/5.0*xyc1^5.0+4.166666666666667*p*1.0/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="p/11880.000000000004*xyc1^12.0+p/3024.0*xyc1^9.0+p/1680.0000000000002*xyc1^8.0+p/120.0*xyc1^5.0+p*1.0416666666666667*xyc1^4.0";
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
        public static void TestCaseDiff40021() {
        //Test Case 40021
        Usage.TestCase = 40021;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0*+2^a*xyc1^8 + 1.0*+2^a*xyc1^5 + 1.0*+2^a*xyc1^4 + 1.0*+2^a*xyc1 + 1.0*+2^a*25";
        Usage.TestCaseName = "Simple: Coefficients:+2^a";
        Usage.TestCaseMatrixID="Coefficients:+2^a:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "2.0^a*xyc1^8.0+2.0^a*xyc1^5.0+2.0^a*xyc1^4.0+2.0^a*xyc1+2.0^a*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a+0.0";
        Usage.EqnSolArrayMap2[1] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a";
        Usage.EqnSolArrayMap3[1]="2.0^a*xyc1^8.0+2.0^a*xyc1^5.0+2.0^a*xyc1^4.0+2.0^a*xyc1+2.0^a*25.0";
        Usage.EqnSolArrayIntegrAlone[1]="2.0^a/9.0*xyc1^9.0+2.0^a/6.0*xyc1^6.0+2.0^a/5.0*xyc1^5.0+2.0^a*xyc1^2.0*1/2.0+2.0^a*25.0*xyc1";
        Usage.EqnSolArrayMap4[1]="2.0^a*0.1111111111111111*xyc1^9.0+2.0^a*0.16666666666666666*xyc1^6.0+2.0^a*0.2*xyc1^5.0+2.0^a*0.5*xyc1^2.0+2.0^a*25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "2.0^a*8.0*xyc1^7.0+2.0^a*5.0*xyc1^4.0+2.0^a*4.0*xyc1^3.0+2.0^a";
        Usage.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*xyc1^6.0+2.0^a*5.0*4.0*xyc1^3.0+2.0^a*4.0*3.0*xyc1^2.0+";
        Usage.EqnSolArrayMap2[2] = "2.0^a*56.0*xyc1^6.0+2.0^a*20.0*xyc1^3.0+2.0^a*12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="2.0^a*0.1111111111111111*xyc1^9.0+2.0^a*0.16666666666666666*xyc1^6.0+2.0^a*0.2*xyc1^5.0+2.0^a*0.5*xyc1^2.0+2.0^a*25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="2.0^a*0.1111111111111111/10.0*xyc1^10.0+2.0^a*0.16666666666666666/7.0*xyc1^7.0+2.0^a*0.2/6.0*xyc1^6.0+2.0^a*0.5/3.0*xyc1^3.0+2.0^a*25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="2.0^a*0.01111111111111111*xyc1^10.0+2.0^a*0.023809523809523808*xyc1^7.0+2.0^a*0.03333333333333333*xyc1^6.0+2.0^a*0.16666666666666666*xyc1^3.0+2.0^a*12.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0^a*56.0*xyc1^6.0+2.0^a*20.0*xyc1^3.0+2.0^a*12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*xyc1^5.0+2.0^a*20.0*3.0*xyc1^2.0+2.0^a*12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "2.0^a*336.0*xyc1^5.0+2.0^a*60.0*xyc1^2.0+2.0^a*24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="2.0^a*0.01111111111111111*xyc1^10.0+2.0^a*0.023809523809523808*xyc1^7.0+2.0^a*0.03333333333333333*xyc1^6.0+2.0^a*0.16666666666666666*xyc1^3.0+2.0^a*12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="2.0^a*0.01111111111111111/11.0*xyc1^11.0+2.0^a*0.023809523809523808/8.0*xyc1^8.0+2.0^a*0.03333333333333333/7.0*xyc1^7.0+2.0^a*0.16666666666666666/4.0*xyc1^4.0+2.0^a*12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="2.0^a*0.0010101010101010099*xyc1^11.0+2.0^a*0.002976190476190476*xyc1^8.0+2.0^a*0.0047619047619047615*xyc1^7.0+2.0^a*0.041666666666666664*xyc1^4.0+2.0^a*4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "2.0^a*336.0*xyc1^5.0+2.0^a*60.0*xyc1^2.0+2.0^a*24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*xyc1^4.0+2.0^a*60.0*2.0*xyc1^1.0+2.0^a*24.0";
        Usage.EqnSolArrayMap2[4] = "2.0^a*1680.0*xyc1^4.0+2.0^a*120.0*xyc1+2.0^a*24.0";
        Usage.EqnSolArrayMap3[4]="2.0^a*0.0010101010101010099*xyc1^11.0+2.0^a*0.002976190476190476*xyc1^8.0+2.0^a*0.0047619047619047615*xyc1^7.0+2.0^a*0.041666666666666664*xyc1^4.0+2.0^a*4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="2.0^a*0.0010101010101010099/12.0*xyc1^12.0+2.0^a*0.002976190476190476/9.0*xyc1^9.0+2.0^a*0.0047619047619047615/8.0*xyc1^8.0+2.0^a*0.041666666666666664/5.0*xyc1^5.0+2.0^a*4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="2.0^a*8.417508417508415E-5*xyc1^12.0+2.0^a*3.3068783068783067E-4*xyc1^9.0+2.0^a*5.952380952380952E-4*xyc1^8.0+2.0^a*0.008333333333333333*xyc1^5.0+2.0^a416666666666667*xyc1^4.0";
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
       public static void TestCaseDiff40022() {
        //Test Case 40022
        Usage.TestCase = 40022;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+2*xyc1^8*5 + 1.0*+2*xyc1^5*5 + 1.0*+2*xyc1^4*5 + 1.0*+2*xyc1*5 + 1.0*+2*25*5";
        Usage.TestCaseName = "Simple: Coefficients:5:Trailing Constant:End Term";
        Usage.TestCaseMatrixID="Coefficients:5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*8.0*xyc1^7.0+10.0*5.0*xyc1^4.0+10.0*4.0*xyc1^3.0+10.0+0.0";
        Usage.EqnSolArrayMap2[1] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        Usage.EqnSolArrayMap3[1]="10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        Usage.EqnSolArrayIntegrAlone[1]="10.0/9.0*xyc1^9.0+10.0/6.0*xyc1^6.0+10.0/5.0*xyc1^5.0+10.0/2.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolArrayMap4[1]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        Usage.EqnSolArrayDiffAlone[2] = "80.0*7.0*xyc1^6.0+50.0*4.0*xyc1^3.0+40.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="1.1111111111111112/10.0*xyc1^10.0+1.6666666666666667/7.0*xyc1^7.0+2.0/6.0*xyc1^6.0+5.0/3.0*xyc1^3.0+250.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "560.0*6.0*xyc1^5.0+200.0*3.0*xyc1^2.0+120.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.11111111111111112/11.0*xyc1^11.0+0.2380952380952381/8.0*xyc1^8.0+0.3333333333333333/7.0*xyc1^7.0+1.6666666666666667/4.0*xyc1^4.0+125.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "3360.0*5.0*xyc1^4.0+600.0*2.0*xyc1^1.0+240.0";
        Usage.EqnSolArrayMap2[4] = "16800.0*xyc1^4.0+1200.0*xyc1+240.0";
        Usage.EqnSolArrayMap3[4]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.010101010101010102/12.0*xyc1^12.0+0.029761904761904764/9.0*xyc1^9.0+0.047619047619047616/8.0*xyc1^8.0+0.4166666666666667/5.0*xyc1^5.0+41.666666666666664/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508418E-4*xyc1^12.0+0.003306878306878307*xyc1^9.0+0.005952380952380952*xyc1^8.0+0.08333333333333334*xyc1^5.0+10.416666666666666*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0-";
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
       public static void TestCaseDiff40023() {
        //Test Case 40023
        Usage.TestCase = 40023;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+2*xyc1^8*5*xyc1*7 + 1.0*+2*xyc1^5*5*xyc1*7 + 1.0*+2*xyc1^4*5*xyc1*7 + 1.0*+2*xyc1*5*xyc1*7 + 1.0*+2*25*5";
        Usage.TestCaseName = "Simple: Coefficients:5*xyc1*7:Trailing Constant:Middle Term";
        Usage.TestCaseMatrixID="Coefficients:5*xyc1*7:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "70.0*xyc1^9.0+70.0*xyc1^6.0+70.0*xyc1^5.0+70.0*xyc1^2.0+250.0";
        Usage.EqnSolArrayDiffAlone[1] = "70.0*9.0*xyc1^8.0+70.0*6.0*xyc1^5.0+70.0*5.0*xyc1^4.0+70.0*2.0*xyc1^1.0+0.0";
        Usage.EqnSolArrayMap2[1] = "630.0*xyc1^8.0+420.0*xyc1^5.0+350.0*xyc1^4.0+140.0*xyc1";
        Usage.EqnSolArrayMap3[1]="70.0*xyc1^9.0+70.0*xyc1^6.0+70.0*xyc1^5.0+70.0*xyc1^2.0+250.0";
        Usage.EqnSolArrayIntegrAlone[1]="70.0/10.0*xyc1^10.0+70.0/7.0*xyc1^7.0+70.0/6.0*xyc1^6.0+70.0/3.0*xyc1^3.0+250.0*xyc1";
        Usage.EqnSolArrayMap4[1]="7.0*xyc1^10.0+10.0*xyc1^7.0+11.666666666666666*xyc1^6.0+23.333333333333332*xyc1^3.0+250.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "630.0*xyc1^8.0+420.0*xyc1^5.0+350.0*xyc1^4.0+140.0*xyc1";
        Usage.EqnSolArrayDiffAlone[2] = "630.0*8.0*xyc1^7.0+420.0*5.0*xyc1^4.0+350.0*4.0*xyc1^3.0+140.0";
        Usage.EqnSolArrayMap2[2] = "5040.0*xyc1^7.0+2100.0*xyc1^4.0+1400.0*xyc1^3.0+140.0";
        Usage.EqnSolArrayMap3[2]="7.0*xyc1^10.0+10.0*xyc1^7.0+11.666666666666666*xyc1^6.0+23.333333333333332*xyc1^3.0+250.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="7.0/11.0*xyc1^11.0+10.0/8.0*xyc1^8.0+11.666666666666666/7.0*xyc1^7.0+23.333333333333332/4.0*xyc1^4.0+250.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.6363636363636364*xyc1^11.0+1.25*xyc1^8.0+1.6666666666666665*xyc1^7.0+5.833333333333333*xyc1^4.0+125.0*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "5040.0*xyc1^7.0+2100.0*xyc1^4.0+1400.0*xyc1^3.0+140.0";
        Usage.EqnSolArrayDiffAlone[3] = "5040.0*7.0*xyc1^6.0+2100.0*4.0*xyc1^3.0+1400.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[3] = "35280.0*xyc1^6.0+8400.0*xyc1^3.0+4200.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3]="0.6363636363636364*xyc1^11.0+1.25*xyc1^8.0+1.6666666666666665*xyc1^7.0+5.833333333333333*xyc1^4.0+125.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.6363636363636364/12.0*xyc1^12.0+1.25/9.0*xyc1^9.0+1.6666666666666665/8.0*xyc1^8.0+5.833333333333333/5.0*xyc1^5.0+125.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.05303030303030303*xyc1^12.0+0.1388888888888889*xyc1^9.0+0.20833333333333331*xyc1^8.0+1.1666666666666665*xyc1^5.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "35280.0*xyc1^6.0+8400.0*xyc1^3.0+4200.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "35280.0*6.0*xyc1^5.0+8400.0*3.0*xyc1^2.0+4200.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "211680.0*xyc1^5.0+25200.0*xyc1^2.0+8400.0*xyc1";
        Usage.EqnSolArrayMap3[4]="0.05303030303030303*xyc1^12.0+0.1388888888888889*xyc1^9.0+0.20833333333333331*xyc1^8.0+1.1666666666666665*xyc1^5.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.05303030303030303/13.0*xyc1^13.0+0.1388888888888889/10.0*xyc1^10.0+0.20833333333333331/9.0*xyc1^9.0+1.1666666666666665/6.0*xyc1^6.0+41.666666666666664/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="0.004079254079254079*xyc1^13.0+0.01388888888888889*xyc1^10.0+0.023148148148148147*xyc1^9.0+0.19444444444444442*xyc1^6.0+10.416666666666666*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0-";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0-";
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
       public static void TestCaseDiff40024() {
        //Test Case 40024
        Usage.TestCase = 40024;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0*+2*xyc1^8*5^xyc1 + 1.0*+2*xyc1^5*5^xyc1 + 1.0*+2*xyc1^4*5^xyc1 + 1.0*+2*xyc1*5^xyc1 + 1.0*+2*25*5";
        Usage.TestCaseName = "Simple: Coefficients:5^xyc1:";
        Usage.TestCaseMatrixID="Coefficients:5^xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 5;
        
        Usage.EqnSolArrayMap1[1] = "10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*8.0*xyc1^7.0+10.0*5.0*xyc1^4.0+10.0*4.0*xyc1^3.0+10.0+0.0";
        Usage.EqnSolArrayMap2[1] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        Usage.EqnSolArrayMap3[1]="10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        Usage.EqnSolArrayIntegrAlone[1]="10.0/9.0*xyc1^9.0+10.0/6.0*xyc1^6.0+10.0/5.0*xyc1^5.0+10.0/2.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolArrayMap4[1]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        Usage.EqnSolArrayDiffAlone[2] = "80.0*7.0*xyc1^6.0+50.0*4.0*xyc1^3.0+40.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="1.1111111111111112/10.0*xyc1^10.0+1.6666666666666667/7.0*xyc1^7.0+2.0/6.0*xyc1^6.0+5.0/3.0*xyc1^3.0+250.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "560.0*6.0*xyc1^5.0+200.0*3.0*xyc1^2.0+120.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.11111111111111112/11.0*xyc1^11.0+0.2380952380952381/8.0*xyc1^8.0+0.3333333333333333/7.0*xyc1^7.0+1.6666666666666667/4.0*xyc1^4.0+125.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "3360.0*5.0*xyc1^4.0+600.0*2.0*xyc1^1.0+240.0";
        Usage.EqnSolArrayMap2[4] = "16800.0*xyc1^4.0+1200.0*xyc1+240.0";
        Usage.EqnSolArrayMap3[4]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.010101010101010102/12.0*xyc1^12.0+0.029761904761904764/9.0*xyc1^9.0+0.047619047619047616/8.0*xyc1^8.0+0.4166666666666667/5.0*xyc1^5.0+41.666666666666664/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508418E-4*xyc1^12.0+0.003306878306878307*xyc1^9.0+0.005952380952380952*xyc1^8.0+0.08333333333333334*xyc1^5.0+10.416666666666666*xyc1^4.0";
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
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff40025() {
        //Test Case 40025
        Usage.TestCase = 40025;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "-0.0/x^8 + 1.0*-0.0/x^5 + 1.0*-0.0/x^4 - 1.0*-0.0/x- 25";
        Usage.TestCaseName = "Simple: Coefficients:-0.0";
        Usage.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-0.0";
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
        public static void TestCaseDiff40026() {
        //Test Case 40026
        Usage.TestCase = 40026;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0*-0/xy^8 + 1.0*-0/xy^5 + 1.0*-0/xy^4 - 1.0*-0/xy - 25*-0";
        Usage.TestCaseName = "Simple: Coefficients:0";
        Usage.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
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
        public static void TestCaseDiff40027() {
        //Test Case 40027
        Usage.TestCase = 40027;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0*+1/xyc^8 + 1.0*+1/xyc^5 + 1*+1/xyc^4 - 1*+1/xyc - 1*+1/25";
        Usage.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1";
        Usage.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc";
        Usage.MaxDiffOrder = 5;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-0.04";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-0.04";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285/xyc^7.0-0.25/xyc^4.0-0.3333333333333333/xyc^3.0-ln(xyc)-0.04*xyc";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        Usage.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        Usage.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff40028() {
        //Test Case 40028
        Usage.TestCase = 40028;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1*+1.0/xyc1^8 + 1*+1.0/xyc1^5 + 1*+1.0/xyc1^4 - 1*+1.0/xyc1 - 1*+1.0/25";
        Usage.TestCaseName = "Simple: Variables:3 Letter with Numerics :Co-efficients:+1.0";
        Usage.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-0.04";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-0.04";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285/xyc1^7.0-0.25/xyc1^4.0-0.3333333333333333/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        Usage.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        Usage.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
        public static void TestCaseDiff40029() {
        //Test Case 40029
        Usage.TestCase = 40029;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0/+5/xyc1^8 + 1.0/+5*xyc1^5 + 1/+5*xyc1^4 + 1.0/+5*xyc1 + 1.0/+5/25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 4;
        
        Usage.EqnSolArrayMap1[1] = "0.2/xyc1^8.0+0.2*xyc1^5.0+0.2*xyc1^4.0+0.2*xyc1+0.008";
        Usage.EqnSolArrayDiffAlone[1] = "0.2*-8.0/xyc1^9.0+0.2*5.0*xyc1^4.0+0.2*4.0*xyc1^3.0+0.2+0.0";
        Usage.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+0.2";
        Usage.EqnSolArrayMap3[1]="0.2/xyc1^8.0+0.2*xyc1^5.0+0.2*xyc1^4.0+0.2*xyc1+0.008";
        Usage.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+0.2";
        Usage.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="-0.028571428571428574/-6.0/xyc1^6.0+0.03333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+0.008/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+4.0*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        Usage.EqnSolArrayMap3[3]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.004761904761904762/-5.0/xyc1^5.0+0.0047619047619047615/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+0.004/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0+12.0*2.0*xyc1^1.0+4.800000000000001";
        Usage.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+24.0*xyc1+4.800000000000001";
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
        public static void TestCaseDiff40030() {
        //Test Case 40030
        Usage.TestCase = 40030;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "-0.0/x^8 + (-0.0)/x^5 + (-0.0)/x^4 - (-0.0)/x- 25";
        Usage.TestCaseName = "Simple: Coefficients:-0.0";
        Usage.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-0.0";
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
        public static void TestCaseDiff40031() {
        //Test Case 40031
        Usage.TestCase = 40031;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "(-0)/xy^8 + (-0)/xy^5 + (-0)/xy^4 - (-0)/xy - 25*(-0)";
        Usage.TestCaseName = "Simple: Coefficients:0";
        Usage.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
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
        public static void TestCaseDiff40032() {
        //Test Case 40032
        Usage.TestCase = 40032;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "(+1)/xyc^8 + (+1)/xyc^5 + (+1)/xyc^4 - (+1)/xyc - (+1)/25";
        //Usage.EqnUnderTest = "1/xyc^8 + 1/xyc^5 + 1/xyc^4 - 1/xyc - 1/25";
        Usage.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1";
        Usage.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-0.04";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-0.04";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285/xyc^7.0-0.25/xyc^4.0-0.3333333333333333/xyc^3.0-ln(xyc)-0.04*xyc";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        Usage.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        Usage.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff40033() {
        //Test Case 40033
        Usage.TestCase = 40033;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "(+1.0)/xyc1^8 + (+1.0)/xyc1^5 + (+1.0)/xyc1^4 - (+1.0)/xyc1 - (+1.0)/25";
        Usage.TestCaseName = "Simple: Variables:3 Letter with Numerics :Co-efficients:+1.0";
        Usage.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-0.04";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        Usage.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-0.04";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285/xyc1^7.0-0.25/xyc1^4.0-0.3333333333333333/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        Usage.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        Usage.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
        public static void TestCaseDiff40034() {
        //Test Case 40034
        Usage.TestCase = 40034;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";        
        Usage.EqnUnderTest = "1.0/+5/xyc1^8 + 1.0/+5*xyc1^5 + 1/+5*xyc1^4 + 1.0/+5*xyc1 + 1.0/+5/25";
        Usage.TestCaseName = "Simple: Coefficients:+5";
        Usage.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "0.2/xyc1^8.0+0.2*xyc1^5.0+0.2*xyc1^4.0+0.2*xyc1+0.008";
        Usage.EqnSolArrayDiffAlone[1] = "0.2*-8.0/xyc1^9.0+0.2*5.0*xyc1^4.0+0.2*4.0*xyc1^3.0+0.2+0.0";
        Usage.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+0.2";
        Usage.EqnSolArrayMap3[1]="0.2/xyc1^8.0+0.2*xyc1^5.0+0.2*xyc1^4.0+0.2*xyc1+0.008";
        Usage.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+0.2";
        Usage.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="-0.028571428571428574/-6.0/xyc1^6.0+0.03333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+0.008/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+4.0*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        Usage.EqnSolArrayMap3[3]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.004761904761904762/-5.0/xyc1^5.0+0.0047619047619047615/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+0.004/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0+12.0*2.0*xyc1^1.0+4.800000000000001";
        Usage.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+24.0*xyc1+4.800000000000001";
        Usage.EqnSolArrayMap3[4]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-9.523809523809525E-4/-4.0/xyc1^4.0+5.952380952380952E-4/9.0*xyc1^9.0+9.523809523809525E-4/8.0*xyc1^8.0+0.008333333333333333/5.0*xyc1^5.0+0.0013333333333333333/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="2.3809523809523812E-4/xyc1^4.0+6.613756613756613E-5*xyc1^9.0+1.1904761904761906E-4*xyc1^8.0+0.0016666666666666666*xyc1^5.0+3.333333333333333E-4*xyc1^4.0";
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
}
