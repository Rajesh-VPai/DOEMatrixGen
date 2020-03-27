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
public class TestHarnessIndConstant {
    //Independent Constant Test Cases
    public static void IndConstant(String AlgoName, int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(TestHarness.IntegralGap(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(TestHarness.DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else {
    switch(j)    
        {
            case 50001:
                TestCaseDiff50001();
                break;
            case 50002:
                TestCaseDiff50002();
                break;
            case 50003:
                TestCaseDiff50003();
                break;
            case 50004:
                TestCaseDiff50004();
                break;
            case 50005:
                TestCaseDiff50005();
                break;
            case 50006:
                TestCaseDiff50006();
                break;
            case 50007:
                TestCaseDiff50007();
                break;
            case 50008:
                TestCaseDiff50008();
                break;
            case 50009:
                TestCaseDiff50009();
                break;
            case 50010:
                TestCaseDiff50010();
                break;
            case 50011:
                TestCaseDiff50011();
                break;
            case 50012:
                TestCaseDiff50012();
                break;
            case 50013:
                TestCaseDiff50013();
                break;
            case 50014:
                TestCaseDiff50014();
                break;
            case 50015:
                TestCaseDiff50015();
                break;
            case 50016:
                TestCaseDiff50016();
                break;
            case 50017:
                TestCaseDiff50017();
                break;
            case 50018:
                TestCaseDiff50018();
                break;
            case 50019:
                TestCaseDiff50019();
                break;
            case 50020:
                TestCaseDiff50020();
                break;
            case 50021:
                TestCaseDiff50021();
                break;
            case 50022:
                TestCaseDiff50022();
                break;
            case 50023:
                TestCaseDiff50023();
                break;
            case 50024:
                TestCaseDiff50024();
                break;
            case 50025:
                TestCaseDiff50025();
                break;
            case 50026:
                TestCaseDiff50026();
                break;
            case 50027:
                TestCaseDiff50027();
                break;
            case 50028:
                TestCaseDiff50028();
                break;
            case 50029:
                TestCaseDiff50029();
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
        /* ***************IndConstant Test Case***************** */
        /* **************************************** */
          public static void TestCaseDiff50001() {
        //Test Case 50001
        Usage.TestCase = 50001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        Usage.EqnUnderTest = "p/9.0*xyc1^9.0 + p*6.0*xyc1^6.0 + p/5.0*xyc1^5.0 + p*2.0*xyc1^2.0 + p*25.0*xyc1 + p*50.0";
        Usage.TestCaseName = "Standard:Independent Constant:Basic Equation:Divide Operator";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "p/9.0*xyc1^9.0+p*6.0*xyc1^6.0+p/5.0*xyc1^5.0+p*2.0*xyc1^2.0+p*25.0*xyc1+p*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "p/9.0*9.0*xyc1^8.0+p*6.0*6.0*xyc1^5.0+p/5.0*5.0*xyc1^4.0+p*2.0*2.0*xyc1^1.0+p*25.0+0.0";
        Usage.EqnSolArrayMap2[1] = "p/81.0*xyc1^8.0+p*36.0*xyc1^5.0+p/25.0*xyc1^4.0+p*4.0*xyc1+p*25.0";
        Usage.EqnSolArrayMap3[1]="p/9.0*xyc1^9.0+p*6.0*xyc1^6.0+p/5.0*xyc1^5.0+p*2.0*xyc1^2.0+p*25.0*xyc1+p*50.0";
        Usage.EqnSolArrayIntegrAlone[1]="p/9.0/10.0*xyc1^10.0+6.0*p/7.0*xyc1^7.0+p/5.0/6.0*xyc1^6.0+2.0*p/3.0*xyc1^3.0+25.0*p*xyc1^2.0*1.0/2.0+50.0*p*xyc1";
        Usage.EqnSolArrayMap4[1]="p/90.00000000000001*xyc1^10.0+p*0.8571428571428571*xyc1^7.0+p/30.0*xyc1^6.0+p*0.6666666666666666*xyc1^3.0+p*12.5*xyc1^2.0+p*50.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "p/81.0*xyc1^8.0+p*36.0*xyc1^5.0+p/25.0*xyc1^4.0+p*4.0*xyc1+p*25.0";
        Usage.EqnSolArrayDiffAlone[2] = "p/81.0*8.0*xyc1^7.0+p*36.0*5.0*xyc1^4.0+p/25.0*4.0*xyc1^3.0+p*4.0+0.0";
        Usage.EqnSolArrayMap2[2] = "p/648.0*xyc1^7.0+p*180.0*xyc1^4.0+p/100.0*xyc1^3.0+p*4.0";
        Usage.EqnSolArrayMap3[2]="p/90.00000000000001*xyc1^10.0+p*0.8571428571428571*xyc1^7.0+p/30.0*xyc1^6.0+p*0.6666666666666666*xyc1^3.0+p*12.5*xyc1^2.0+p*50.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="p/90.00000000000001/11.0*xyc1^11.0+0.8571428571428571*p/8.0*xyc1^8.0+p/30.0/7.0*xyc1^7.0+0.6666666666666666*p/4.0*xyc1^4.0+12.5*p/3.0*xyc1^3.0+50.0*p*xyc1^2.0*1.0/2.0";
        Usage.EqnSolArrayMap4[2]="p/990.0000000000002*xyc1^11.0+p*0.10714285714285714*xyc1^8.0+p/210.00000000000003*xyc1^7.0+p*0.16666666666666666*xyc1^4.0+p*4.166666666666667*xyc1^3.0+p*25.0*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "p/648.0*xyc1^7.0+p*180.0*xyc1^4.0+p/100.0*xyc1^3.0+p*4.0";
        Usage.EqnSolArrayDiffAlone[3] = "p/648.0*7.0*xyc1^6.0+p*180.0*4.0*xyc1^3.0+p/100.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[3] = "p/4536.0*xyc1^6.0+p*720.0*xyc1^3.0+p/300.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3]="p/990.0000000000002*xyc1^11.0+p*0.10714285714285714*xyc1^8.0+p/210.00000000000003*xyc1^7.0+p*0.16666666666666666*xyc1^4.0+p*4.166666666666667*xyc1^3.0+p*25.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="p/990.0000000000002/12.0*xyc1^12.0+0.10714285714285714*p/9.0*xyc1^9.0+p/210.00000000000003/8.0*xyc1^8.0+0.16666666666666666*p/5.0*xyc1^5.0+4.166666666666667*p/4.0*xyc1^4.0+25.0*p*1.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="p/11880.000000000004*xyc1^12.0+p*0.011904761904761904*xyc1^9.0+p/1680.0000000000002*xyc1^8.0+p*0.03333333333333333*xyc1^5.0+p*1.0416666666666667*xyc1^4.0+p*8.333333333333334*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "p/4536.0*xyc1^6.0+p*720.0*xyc1^3.0+p/300.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "p/4536.0*6.0*xyc1^5.0+p*720.0*3.0*xyc1^2.0+p/300.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "p/27216.0*xyc1^5.0+p*2160.0*xyc1^2.0+p/600.0*xyc1";
        Usage.EqnSolArrayMap3[4]="p/11880.000000000004*xyc1^12.0+p*0.011904761904761904*xyc1^9.0+p/1680.0000000000002*xyc1^8.0+p*0.03333333333333333*xyc1^5.0+p*1.0416666666666667*xyc1^4.0+p*8.333333333333334*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="p/11880.000000000004/13.0*xyc1^13.0+0.011904761904761904*p/10.0*xyc1^10.0+p/1680.0000000000002/9.0*xyc1^9.0+0.03333333333333333*p/6.0*xyc1^6.0+1.0416666666666667*p/5.0*xyc1^5.0+8.333333333333334*p*1.0/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="p/154440.00000000003*xyc1^13.0+p*0.0011904761904761904*xyc1^10.0+p/15120.000000000002*xyc1^9.0+p*0.005555555555555556*xyc1^6.0+p*0.20833333333333334*xyc1^5.0+p*2.0833333333333335*xyc1^4.0";
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
          public static void TestCaseDiff50002() {
        //Test Case 50002
        Usage.TestCase = 50002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a*x^8 + a*x^5 + a*x^4 - 2*a*x- 25*(2)";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "a*x^8.0+a*x^5.0+a*x^4.0-a*2.0*x-50.0";
        Usage.EqnSolArrayDiffAlone[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0";
        Usage.EqnSolArrayMap3[1]="a*x^8.0+a*x^5.0+a*x^4.0-a*2.0*x-50.0";
        Usage.EqnSolArrayIntegrAlone[1]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-2.0*a*x^2.0*1.0/2.0-50.0*x";
        Usage.EqnSolArrayMap4[1]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-a*x^2.0-50.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-a*2.0";
        Usage.EqnSolArrayDiffAlone[2] = "a*8.0*7.0*x^6.0+a*5.0*4.0*x^3.0+a*4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-a*x^2.0-50.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="a/9.0/10.0*x^10.0+a/6.0/7.0*x^7.0+a/5.0/6.0*x^6.0-a/3.0*x^3.0-50.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="a/90.00000000000001*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-a/3.0*x^3.0-25.0*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "a*56.0*6.0*x^5.0+a*20.0*3.0*x^2.0+a*12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x";
        Usage.EqnSolArrayMap3[3]="a/90.00000000000001*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-a/3.0*x^3.0-25.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="a/90.00000000000001/11.0*x^11.0+a/42.0/8.0*x^8.0+a/30.0/7.0*x^7.0-a/3.0/4.0*x^4.0-25.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="a/990.0000000000002*x^11.0+a/336.0*x^8.0+a/210.00000000000003*x^7.0-a/12.0*x^4.0-8.333333333333334*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "a*336.0*5.0*x^4.0+a*60.0*2.0*x^1.0+a*24.0";
        Usage.EqnSolArrayMap2[4] = "a*1680.0*x^4.0+a*120.0*x+a*24.0";
        Usage.EqnSolArrayMap3[4]="a/990.0000000000002*x^11.0+a/336.0*x^8.0+a/210.00000000000003*x^7.0-a/12.0*x^4.0-8.333333333333334*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="a/990.0000000000002/12.0*x^12.0+a/336.0/9.0*x^9.0+a/210.00000000000003/8.0*x^8.0-a/12.0/5.0*x^5.0-8.333333333333334/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="a/11880.000000000004*x^12.0+a/3024.0*x^9.0+a/1680.0000000000002*x^8.0-a/60.0*x^5.0-2.0833333333333335*x^4.0";
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
       
       public static void TestCaseDiff50003() {
        //Test Case 50003
        Usage.TestCase = 50003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^a*x^8 + 2^a*x^5 + 2^a*x^4 - 2*x*2^a- 25*2*3^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-3.0^a*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayMap3[1]="2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-3.0^a*50.0";
        Usage.EqnSolArrayIntegrAlone[1]="";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*x^6.0+2.0^a*5.0*4.0*x^3.0+2.0^a*4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*x^5.0+2.0^a*20.0*3.0*x^2.0+2.0^a*12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*x^4.0+2.0^a*60.0*2.0*x^1.0+2.0^a*24.0";
        Usage.EqnSolArrayMap2[4] = "2.0^a*1680.0*x^4.0+2.0^a*120.0*x+2.0^a*24.0";
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
       public static void TestCaseDiff50004() {
        //Test Case 50004
        Usage.TestCase = 50004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^2*x^8 + a^2*x^5 + a^2*x^4 - 2*x*a^2- 25*2*a^3";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50005() {
        //Test Case 50005
        Usage.TestCase = 50005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^x*x^8 + 2^x*x^5 + 2^x*x^4 - 2*x*2^x- 25*2*3^x";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50006() {
        //Test Case 50006
        Usage.TestCase = 50006;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^x*x^8 + a^x*x^5 + a^x*x^4 - 2*x*a^x- 25*2*a^x";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50007() {
        //Test Case 50007
        Usage.TestCase = 50007;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "x^a*x^8 + x^a*x^5 + x^a*x^4 - 2*x*x^a- 25*2*x^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50008() {
        //Test Case 50008
        Usage.TestCase = 50008;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^b*x^8 + a^b*x^5 + a^b*x^4 - 2*x*a^b- 25*2*a^b";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50009() {
        //Test Case 50009
        Usage.TestCase = 50009;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a*b*c*x^8 + a*b*c*x^5 + a*b*c*x^4 - 2*x*a*b*c- 25*2*a*b*c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "a*b*c*x^8.0+a*b*c*x^5.0+a*b*c*x^4.0-a*b*c*2.0*x-a*b*c*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "a*b*c*8.0*x^7.0+a*b*c*5.0*x^4.0+a*b*c*4.0*x^3.0-a*b*c*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "a*b*c*8.0*x^7.0+a*b*c*5.0*x^4.0+a*b*c*4.0*x^3.0-a*b*c*2.0";
        Usage.EqnSolArrayMap3[1]="";
        Usage.EqnSolArrayIntegrAlone[1]="";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "a*b*c*8.0*x^7.0+a*b*c*5.0*x^4.0+a*b*c*4.0*x^3.0-a*b*c*2.0";
        Usage.EqnSolArrayDiffAlone[2] = "a*b*c*8.0*7.0*x^6.0+a*b*c*5.0*4.0*x^3.0+a*b*c*4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "a*b*c*56.0*x^6.0+a*b*c*20.0*x^3.0+a*b*c*12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "a*b*c*56.0*x^6.0+a*b*c*20.0*x^3.0+a*b*c*12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "a*b*c*56.0*6.0*x^5.0+a*b*c*20.0*3.0*x^2.0+a*b*c*12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "a*b*c*336.0*x^5.0+a*b*c*60.0*x^2.0+a*b*c*24.0*x";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "a*b*c*336.0*x^5.0+a*b*c*60.0*x^2.0+a*b*c*24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "a*b*c*336.0*5.0*x^4.0+a*b*c*60.0*2.0*x^1.0+a*b*c*24.0";
        Usage.EqnSolArrayMap2[4] = "a*b*c*1680.0*x^4.0+a*b*c*120.0*x+a*b*c*24.0";
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
       public static void TestCaseDiff50010() {
        //Test Case 50010
        Usage.TestCase = 50010;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a/b/c*x^8 + a/b/c*x^5 + a/b/c*x^4 - 2*x*a/b/c- 25*2*a/b/c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50011() {
        //Test Case 50011
        Usage.TestCase = 50011;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a*b/c*x^8 + a*b/c*x^5 + a*b/c*x^4 - 2*x*a*b/c- 25*2*a*b/c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50012() {
        //Test Case 50012
        Usage.TestCase = 50012;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0*+p*xyc1^8 + 1.0*+p*xyc1^5 + 1.0*+p*xyc1^4 + 1.0*+p*xyc1 + 1.0*+p*25";
        Usage.TestCaseName = "Simple: Coefficients:+a";
        Usage.TestCaseMatrixID="Coefficients:+a:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p+0.0";
        Usage.EqnSolArrayMap2[1] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p*1.0";
        Usage.EqnSolArrayMap3[1]="p*xyc1^8.0+p*xyc1^5.0+p*xyc1^4.0+p*xyc1+p*25.0";
        Usage.EqnSolArrayIntegrAlone[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+25.0*p*xyc1";
        Usage.EqnSolArrayMap4[1]="p/9.0*xyc1^9.0+p/6.0*xyc1^6.0+p/5.0*xyc1^5.0+p/2.0*xyc1^2.0+p*25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "p*8.0*xyc1^7.0+p*5.0*xyc1^4.0+p*4.0*xyc1^3.0+p*1";
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
        public static void TestCaseDiff50013() {
        //Test Case 50013
        Usage.TestCase = 50013;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0*+2^a*xyc1^8 + 1.0*+2^a*xyc1^5 + 1.0*+2^a*xyc1^4 + 1.0*+2^a*xyc1 + 1.0*+2^a*25";
        Usage.TestCaseName = "Simple: Coefficients:+2^a";
        Usage.TestCaseMatrixID="Coefficients:+2^a:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
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
        public static void TestCaseDiff50014() {
        //Test Case 50014
        Usage.TestCase = 50014;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0/+a*xyc1^8 + 1.0/+a*xyc1^5 + 1.0/+a*xyc1^4 + 1.0/+a*xyc1 + 1.0/+a*25";
        Usage.TestCaseName = "Simple: Coefficients:+a";
        Usage.TestCaseMatrixID="Coefficients:+a:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0/a*xyc1^8.0+1.0/a*xyc1^5.0+1.0/a*xyc1^4.0+1.0/a*xyc1+1.0/a*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0/a*8.0*xyc1^7.0+1.0/a*5.0*xyc1^4.0+1.0/a*4.0*xyc1^3.0+1.0/a+0.0";
        Usage.EqnSolArrayMap2[1] = "1.0/a*8.0*xyc1^7.0+1.0/a*5.0*xyc1^4.0+1.0/a*4.0*xyc1^3.0+1.0/a";
        Usage.EqnSolArrayMap3[1]="1.0/a*xyc1^8.0+1.0/a*xyc1^5.0+1.0/a*xyc1^4.0+1.0/a*xyc1+1.0/a*25.0";
        Usage.EqnSolArrayIntegrAlone[1]="0.1111111111111111*1.0/a*xyc1^9.0+0.16666666666666666*1.0/a*xyc1^6.0+0.2*1.0/a*xyc1^5.0+1.0/a*xyc1^2.0*1/2.0+1.0/a*25.0*xyc1";
        Usage.EqnSolArrayMap4[1]="1.0/a*0.1111111111111111*xyc1^9.0+1.0/a*0.16666666666666666*xyc1^6.0+1.0/a*0.2*xyc1^5.0+1.0/a*0.5*xyc1^2.0+1.0/a*25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0/a*8.0*xyc1^7.0+1.0/a*5.0*xyc1^4.0+1.0/a*4.0*xyc1^3.0+1.0/a";
        Usage.EqnSolArrayDiffAlone[2] = "1.0/a*8.0*7.0*xyc1^6.0+1.0/a*5.0*4.0*xyc1^3.0+1.0/a*4.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "1.0/a*56.0*xyc1^6.0+1.0/a*20.0*xyc1^3.0+1.0/a*12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="1.0/a*0.1111111111111111*xyc1^9.0+1.0/a*0.16666666666666666*xyc1^6.0+1.0/a*0.2*xyc1^5.0+1.0/a*0.5*xyc1^2.0+1.0/a*25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="a*0.1111111111111111/10.0*xyc1^10.0+a*0.16666666666666666/7.0*xyc1^7.0+a*0.2/6.0*xyc1^6.0+a*0.5/3.0*xyc1^3.0+a*25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="a*0.01111111111111111*xyc1^10.0+a*0.023809523809523808*xyc1^7.0+a*0.03333333333333333*xyc1^6.0+a*0.16666666666666666*xyc1^3.0+a*12.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "1.0/a*56.0*xyc1^6.0+1.0/a*20.0*xyc1^3.0+1.0/a*12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "1.0/a*56.0*6.0*xyc1^5.0+1.0/a*20.0*3.0*xyc1^2.0+1.0/a*12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1.0/a*336.0*xyc1^5.0+1.0/a*60.0*xyc1^2.0+1.0/a*24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="a*0.01111111111111111*xyc1^10.0+a*0.023809523809523808*xyc1^7.0+a*0.03333333333333333*xyc1^6.0+a*0.16666666666666666*xyc1^3.0+a*12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="a*0.01111111111111111/11.0*xyc1^11.0+a*0.023809523809523808/8.0*xyc1^8.0+a*0.03333333333333333/7.0*xyc1^7.0+a*0.16666666666666666/4.0*xyc1^4.0+a*12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="a*0.0010101010101010099*xyc1^11.0+a*0.002976190476190476*xyc1^8.0+a*0.0047619047619047615*xyc1^7.0+a*0.041666666666666664*xyc1^4.0+a*4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1.0/a*336.0*xyc1^5.0+1.0/a*60.0*xyc1^2.0+1.0/a*24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1.0/a*336.0*5.0*xyc1^4.0+1.0/a*60.0*2.0*xyc1^1.0+1.0/a*24.0";
        Usage.EqnSolArrayMap2[4] = "1.0/a*1680.0*xyc1^4.0+1.0/a*120.0*xyc1+1.0/a*24.0";
        Usage.EqnSolArrayMap3[4]="a*0.0010101010101010099*xyc1^11.0+a*0.002976190476190476*xyc1^8.0+a*0.0047619047619047615*xyc1^7.0+a*0.041666666666666664*xyc1^4.0+a*4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="a*0.0010101010101010099/12.0*xyc1^12.0+a*0.002976190476190476/9.0*xyc1^9.0+a*0.0047619047619047615/8.0*xyc1^8.0+a*0.041666666666666664/5.0*xyc1^5.0+a*4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="a*8.417508417508415E-5*xyc1^12.0+a*3.3068783068783067E-4*xyc1^9.0+a*5.952380952380952E-4*xyc1^8.0+a*0.008333333333333333*xyc1^5.0+a*1.0416666666666667*xyc1^4.0";
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
        public static void TestCaseDiff50015() {
        //Test Case 50015
        Usage.TestCase = 50015;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0/+2^a*xyc1^8 + 1.0/+2^a*xyc1^5 + 1.0/+2^a*xyc1^4 + 1.0/+2^a*xyc1 + 1.0/+2^a*25";
        Usage.TestCaseName = "Simple: Coefficients:+2^a";
        Usage.TestCaseMatrixID="Coefficients:+2^a:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/2.0^a*xyc1^8.0+1.0/2.0^a*xyc1^5.0+1.0/2.0^a*xyc1^4.0+1.0/2.0^a*xyc1+1.0/2.0^a*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0/2.0^a*8.0*xyc1^7.0+1.0/2.0^a*5.0*xyc1^4.0+1.0/2.0^a*4.0*xyc1^3.0+1.0/2.0^a+0.0";
        Usage.EqnSolArrayMap2[1] = "1.0/2.0^a*8.0*xyc1^7.0+1.0/2.0^a*5.0*xyc1^4.0+1.0/2.0^a*4.0*xyc1^3.0+1.0/2.0^a";
        Usage.EqnSolArrayMap3[1]="1.0/2.0^a*xyc1^8.0+1.0/2.0^a*xyc1^5.0+1.0/2.0^a*xyc1^4.0+1.0/2.0^a*xyc1+1.0/2.0^a*25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/2.0^a/9.0*xyc1^9.0+1.0/2.0^a/6.0*xyc1^6.0+1.0/2.0^a/5.0*xyc1^5.0+1.0/2.0^a/2.0*xyc1^2.0+1.0/2.0^a*25.0*xyc1";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0/2.0^a*8.0*xyc1^7.0+1.0/2.0^a*5.0*xyc1^4.0+1.0/2.0^a*4.0*xyc1^3.0+1.0/2.0^a";
        Usage.EqnSolArrayDiffAlone[2] = "1.0/2.0^a*8.0*7.0*xyc1^6.0+1.0/2.0^a*5.0*4.0*xyc1^3.0+1.0/2.0^a*4.0*3.0*xyc1^2.0+";
        Usage.EqnSolArrayMap2[2] = "1.0/2.0^a*56.0*xyc1^6.0+1.0/2.0^a*20.0*xyc1^3.0+1.0/2.0^a*12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "1.0/2.0^a*56.0*xyc1^6.0+1.0/2.0^a*20.0*xyc1^3.0+1.0/2.0^a*12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "1.0/2.0^a*56.0*6.0*xyc1^5.0+1.0/2.0^a*20.0*3.0*xyc1^2.0+1.0/2.0^a*12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "1.0/2.0^a*336.0*xyc1^5.0+1.0/2.0^a*60.0*xyc1^2.0+1.0/2.0^a*24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1.0/2.0^a*336.0*xyc1^5.0+1.0/2.0^a*60.0*xyc1^2.0+1.0/2.0^a*24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1.0/2.0^a*336.0*5.0*xyc1^4.0+1.0/2.0^a*60.0*2.0*xyc1^1.0+1.0/2.0^a*24.0";
        Usage.EqnSolArrayMap2[4] = "1.0/2.0^a*1680.0*xyc1^4.0+1.0/2.0^a*120.0*xyc1+1.0/2.0^a*24.0";
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
        public static void TestCaseDiff50016() {
        //Test Case 50016
        Usage.TestCase = 50016;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0/+a*+a/+a/+5.0/+a/+a*xyc1^8 + 1.0/+a*+a/+a/+5.0/+a/+a*xyc1^5 + 1.0/+a*+a/+a*+5.0/+a/+a*xyc1^4 + 1.0/+a*b/c*xyc1 + 1.0/+a*+a/a*25";
        Usage.TestCaseName = "Simple: Coefficients:+a,b,c";
        Usage.TestCaseMatrixID="Coefficients:+a:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 2;
        
        Usage.EqnSolArrayMap1[1] = "1.0/a*a/a/a/a*0.2*xyc1^8.0+1.0/a*a/a/a/a*0.2*xyc1^5.0+1.0/a*a/a/a/a*5.0*xyc1^4.0+1.0/a*b/c*xyc1+1.0/a*a/a*25.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0/a*a/a/a/a*0.2*8.0*xyc1^7.0+1.0/a*a/a/a/a*0.2*5.0*xyc1^4.0+1.0/a*a/a/a/a*5.0*4.0*xyc1^3.0+1.0/a*b/c+0.0";
        Usage.EqnSolArrayMap2[1] = "1.0/a*a/a/a/a*1.6*xyc1^7.0+1.0/a*a/a/a/a*xyc1^4.0+1.0/a*a/a/a/a*20.0*xyc1^3.0+1.0/a*b/c";
        Usage.EqnSolArrayMap3[1]="";
        Usage.EqnSolArrayIntegrAlone[1]="";
        Usage.EqnSolArrayMap4[1]="";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0/a*a/a/a/a*1.6*xyc1^7.0+1.0/a*a/a/a/a*xyc1^4.0+1.0/a*a/a/a/a*20.0*xyc1^3.0+1.0/a*b/c";
        Usage.EqnSolArrayDiffAlone[2] = "1.0/a*a/a/a/a*1.6*7.0*xyc1^6.0+1.0/a*a/a/a/a*4.0*xyc1^3.0+1.0/a*a/a/a/a*20.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[2] = "1.0/a*a/a/a/a*11.200000000000001*xyc1^6.0+1.0/a*a/a/a/a*4.0*xyc1^3.0+1.0/a*a/a/a/a*60.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "1.0/a*a/a/a/a*11.200000000000001*xyc1^6.0+1.0/a*a/a/a/a*4.0*xyc1^3.0+1.0/a*a/a/a/a*60.0*xyc1^2.0+1.0/a*c*c";
        Usage.EqnSolArrayDiffAlone[3] = "1.0/a*a/a/a/a*11.200000000000001*6.0*xyc1^5.0+1.0/a*a/a/a/a*4.0*3.0*xyc1^2.0+1.0/a*a/a/a/a*60.0*2.0*xyc1^1.0+0.0";
        Usage.EqnSolArrayMap2[3] = "1.0/a*a/a/a/a*67.2*xyc1^5.0+1.0/a*a/a/a/a*12.0*xyc1^2.0+1.0/a*a/a/a/a*120.0*xyc1";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1.0/a*a/a/a/a*67.2*xyc1^5.0+1.0/a*a/a/a/a*12.0*xyc1^2.0+1.0/a*a/a/a/a*120.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "1.0/a*a/a/a/a*67.2*5.0*xyc1^4.0+1.0/a*a/a/a/a*12.0*2.0*xyc1^1.0+1.0/a*a/a/a/a*120.0";
        Usage.EqnSolArrayMap2[4] = "1.0/a*a/a/a/a*336.0*xyc1^4.0+1.0/a*a/a/a/a*24.0*xyc1+1.0/a*a/a/a/a*120.0";
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
        public static void TestCaseDiff50017() {
        //Test Case 50017
        Usage.TestCase = 50017;
        Usage.EqnRegressionDiffStatus = "All RED Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";        
        Usage.EqnUnderTest = "1.0/+2^a*xyc1^8 + 1.0/+2^a*xyc1^5 + 1.0/+2^a*xyc1^4 + 1.0/+2^a*xyc1 + 1.0/+2^a*25";
        Usage.TestCaseName = "Simple: Coefficients:+2^a";
        Usage.TestCaseMatrixID="Coefficients:+2^a:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
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
        public static void TestCaseDiff50018() {
        //Test Case 50018
        Usage.TestCase = 50018;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^a*x^8 + 2^a*x^5 + 2^a*x^4 - 2*x*2^a- 25*2*2^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-2.0^a*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayMap3[1]="2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-2.0^a*50.0";
        Usage.EqnSolArrayIntegrAlone[1]="2.0^a/9.0*x^9.0+2.0^a/6.0*x^6.0+2.0^a/5.0*x^5.0-2.0^a*50.0*x*50.0-2.0^a*50.0*x";
        Usage.EqnSolArrayMap4[1]="2.0^a*0.1111111111111111*x^9.0+2.0^a*0.16666666666666666*x^6.0+2.0^a*0.2*x^5.0-2.0^a*2500.0*x-2.0^a*50.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*x^6.0+2.0^a*5.0*4.0*x^3.0+2.0^a*4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*x^5.0+2.0^a*20.0*3.0*x^2.0+2.0^a*12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*x^4.0+2.0^a*60.0*2.0*x^1.0+2.0^a*24.0";
        Usage.EqnSolArrayMap2[4] = "2.0^a*1680.0*x^4.0+2.0^a*120.0*x+2.0^a*24.0";
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
        public static void TestCaseDiff50019() {
        //Test Case 50019
        Usage.TestCase = 50019;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^a*x^8 + 2^a*x^5 + 2^a*x^4 - 2*x*2^a- 25*2*3^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-2.0^a*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayMap3[1]="2.0^a*x^8.0+2.0^a*x^5.0+2.0^a*x^4.0-2.0^a*2.0*x-2.0^a*50.0";
        Usage.EqnSolArrayIntegrAlone[1]="2.0^a/9.0*x^9.0+2.0^a/6.0*x^6.0+2.0^a/5.0*x^5.0-2.0^a*50.0*x*50.0-2.0^a*50.0*x";
        Usage.EqnSolArrayMap4[1]="2.0^a*0.1111111111111111*x^9.0+2.0^a*0.16666666666666666*x^6.0+2.0^a*0.2*x^5.0-2.0^a*2500.0*x-2.0^a*50.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "2.0^a*8.0*x^7.0+2.0^a*5.0*x^4.0+2.0^a*4.0*x^3.0-2.0^a*2.0";
        Usage.EqnSolArrayDiffAlone[2] = "2.0^a*8.0*7.0*x^6.0+2.0^a*5.0*4.0*x^3.0+2.0^a*4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="";
        Usage.EqnSolArrayIntegrAlone[2]="";
        Usage.EqnSolArrayMap4[2]="";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "2.0^a*56.0*x^6.0+2.0^a*20.0*x^3.0+2.0^a*12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "2.0^a*56.0*6.0*x^5.0+2.0^a*20.0*3.0*x^2.0+2.0^a*12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayMap3[3]="";
        Usage.EqnSolArrayIntegrAlone[3]="";
        Usage.EqnSolArrayMap4[3]="";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "2.0^a*336.0*x^5.0+2.0^a*60.0*x^2.0+2.0^a*24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "2.0^a*336.0*5.0*x^4.0+2.0^a*60.0*2.0*x^1.0+2.0^a*24.0";
        Usage.EqnSolArrayMap2[4] = "2.0^a*1680.0*x^4.0+2.0^a*120.0*x+2.0^a*24.0";
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
        public static void TestCaseDiff50020() {
        //Test Case 50020
        Usage.TestCase = 50020;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^a*x^8 + 2^a*x^5 + 2^a*x^4 - 2*x*2^a- 25*(2)*3^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50021() {
        //Test Case 50021
        Usage.TestCase = 50021;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^2*x^8 + a^2*x^5 + a^2*x^4 - 2*x*a^2- 25*(2)*a^3";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50022() {
        //Test Case 50022
        Usage.TestCase = 50022;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "2^x*x^8 + 2^x*x^5 + 2^x*x^4 - 2*x*2^x- 25*(2)*3^x";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50023() {
        //Test Case 50023
        Usage.TestCase = 50023;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^x*x^8 + a^x*x^5 + a^x*x^4 - 2*x*a^x- 25*(2)*a^x";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50024() {
        //Test Case 50024
        Usage.TestCase = 50024;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "x^a*x^8 + x^a*x^5 + x^a*x^4 - 2*x*x^a- 25*(2)*x^a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50025() {
        //Test Case 50025
        Usage.TestCase = 50025;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a^b*x^8 + a^b*x^5 + a^b*x^4 - 2*x*a^b- 25*(2)*a^b";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50026() {
        //Test Case 50026
        Usage.TestCase = 50026;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a*b*c*x^8 + a*b*c*x^5 + a*b*c*x^4 - 2*x*a*b*c- 25*(2)*a*b*c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50027() {
        //Test Case 50027
        Usage.TestCase = 50027;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a/b/c*x^8 + a/b/c*x^5 + a/b/c*x^4 - 2*x*a/b/c- 25*(2)*a/b/c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
       public static void TestCaseDiff50028() {
        //Test Case 50028
        Usage.TestCase = 50028;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "x^8 + x^5 + x^4 - (2)*x- 25*(2)";
        Usage.EqnUnderTest = "a*b/c*x^8 + a*b/c*x^5 + a*b/c*x^4 - 2*x*a*b/c- 25*(2)*a*b/c";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
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
              public static void TestCaseDiff50029() {
        //Test Case 50029
        Usage.TestCase = 50029;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0/a*x^8 + 1.0/a*x^5 + 1.0/a*x^4 - 2*x/a- 25*2/a";
        Usage.TestCaseName = "Simple: IndConstant";
        Usage.TestCaseMatrixID="IndConstant:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-:Parenthesis:COnstants:Coeffiecient:Exponent";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "1.0/a*x^8.0+1.0/a*x^5.0+1.0/a*x^4.0-1.0/a*2.0*x-1.0/a*50.0";
        Usage.EqnSolArrayDiffAlone[1] = "1.0/a*8.0*x^7.0+1.0/a*5.0*x^4.0+1.0/a*4.0*x^3.0-1.0/a*2.0-0.0";
        Usage.EqnSolArrayMap2[1] = "";
        Usage.EqnSolArrayMap3[1]="1.0/a*x^8.0+1.0/a*x^5.0+1.0/a*x^4.0-1.0/a*2.0/x-1.0/a*50.0";
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
}
