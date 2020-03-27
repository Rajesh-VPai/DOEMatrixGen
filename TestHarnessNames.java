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
public class TestHarnessNames {
    
    //Names Test Cases
    public static void Names(int j){
        switch(j){
            case 10001:
                TestCaseDiff10001();
                break;
            case 10002:
                TestCaseDiff10002();
                break;
            case 10003:
                TestCaseDiff10003();
                break;
            case 10004:
                TestCaseDiff10004();
            case 10005:
                TestCaseDiff10005();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Names TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
    }
    /* **************************************** */
    //Structured Testing
    /* **************************************** */
    
    /* **************************************** */
    /* ***************Variables***************** */
    /* **************************************** */
        public static void TestCaseDiff10001() {
        //Test Case 10001
        Usage.TestCase = 10001;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + x^5 + x^4 - x - 25";
        Usage.TestCaseName = "Simple: Variables:1 Letter";
        Usage.TestCaseMatrixID="Variables:1 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
        
        Usage.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*x^2.0+";
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
        public static void TestCaseDiff10002() {
        //Test Case 10002
        Usage.TestCase = 10002;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "xy^8 + xy^5 + xy^4 - xy - 25";
        Usage.TestCaseName = "Simple: Variables:2 Letter";
        Usage.TestCaseMatrixID="Variables:2 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xy";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "xy^8.0+xy^5.0+xy^4.0-xy-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="xy^8.0+xy^5.0+xy^4.0-xy-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*xy^9.0+1.0/6.0*xy^6.0+1.0/5.0*xy^5.0-1.0/2.0*xy^2.0-25.0*xy";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*xy^9.0+0.16666666666666666*xy^6.0+0.2*xy^5.0-0.5*xy^2.0-25.0*xy";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*xy^6.0+5.0*4.0*xy^3.0+4.0*3.0*xy^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*xy^6.0+20.0*xy^3.0+12.0*xy^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*xy^9.0+0.16666666666666666*xy^6.0+0.2*xy^5.0-0.5*xy^2.0-25.0*xy";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xy^10.0+0.16666666666666666/7.0*xy^7.0+0.2/6.0*xy^6.0-0.5/3.0*xy^3.0-25.0/2.0*xy^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*xy^10.0+0.023809523809523808*xy^7.0+0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0-12.5*xy^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*xy^6.0+20.0*xy^3.0+12.0*xy^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*xy^5.0+20.0*3.0*xy^2.0+12.0*2.0*xy^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*xy^5.0+60.0*xy^2.0+24.0*xy";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*xy^10.0+0.023809523809523808*xy^7.0+0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0-12.5*xy^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xy^11.0+0.023809523809523808/8.0*xy^8.0+0.03333333333333333/7.0*xy^7.0-0.16666666666666666/4.0*xy^4.0-12.5/3.0*xy^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*xy^11.0+0.002976190476190476*xy^8.0+0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0-4.166666666666667*xy^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*xy^5.0+60.0*xy^2.0+24.0*xy";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*xy^4.0+60.0*2.0*xy^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*xy^4.0+120.0*xy+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*xy^11.0+0.002976190476190476*xy^8.0+0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0-4.166666666666667*xy^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xy^12.0+0.002976190476190476/9.0*xy^9.0+0.0047619047619047615/8.0*xy^8.0-0.041666666666666664/5.0*xy^5.0-4.166666666666667/4.0*xy^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*xy^12.0+3.3068783068783067E-4*xy^9.0+5.952380952380952E-4*xy^8.0-0.008333333333333333*xy^5.0-1.0416666666666667*xy^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "1680.0*xy^4.0+120.0*xy+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*xy^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*xy^3.0+120.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*xy^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*xy^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*xy^2.0+";
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
        public static void TestCaseDiff10003() {
        //Test Case 10003
        Usage.TestCase = 10003;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "abc^8 + abc^5 + abc^4 - abc - 25";
        Usage.TestCaseName = "Simple: Variables:3 Letter";
        Usage.TestCaseMatrixID="Variables:3 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "abc";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "abc^8.0+abc^5.0+abc^4.0-abc-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="abc^8.0+abc^5.0+abc^4.0-abc-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*abc^9.0+1.0/6.0*abc^6.0+1.0/5.0*abc^5.0-1.0/2.0*abc^2.0-25.0*abc";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*abc^9.0+0.16666666666666666*abc^6.0+0.2*abc^5.0-0.5*abc^2.0-25.0*abc";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*abc^6.0+5.0*4.0*abc^3.0+4.0*3.0*abc^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*abc^6.0+20.0*abc^3.0+12.0*abc^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*abc^9.0+0.16666666666666666*abc^6.0+0.2*abc^5.0-0.5*abc^2.0-25.0*abc";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*abc^10.0+0.16666666666666666/7.0*abc^7.0+0.2/6.0*abc^6.0-0.5/3.0*abc^3.0-25.0/2.0*abc^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*abc^10.0+0.023809523809523808*abc^7.0+0.03333333333333333*abc^6.0-0.16666666666666666*abc^3.0-12.5*abc^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*abc^6.0+20.0*abc^3.0+12.0*abc^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*abc^5.0+20.0*3.0*abc^2.0+12.0*2.0*abc^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*abc^5.0+60.0*abc^2.0+24.0*abc";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*abc^10.0+0.023809523809523808*abc^7.0+0.03333333333333333*abc^6.0-0.16666666666666666*abc^3.0-12.5*abc^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*abc^11.0+0.023809523809523808/8.0*abc^8.0+0.03333333333333333/7.0*abc^7.0-0.16666666666666666/4.0*abc^4.0-12.5/3.0*abc^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*abc^11.0+0.002976190476190476*abc^8.0+0.0047619047619047615*abc^7.0-0.041666666666666664*abc^4.0-4.166666666666667*abc^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*abc^5.0+60.0*abc^2.0+24.0*abc";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*abc^4.0+60.0*2.0*abc^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*abc^4.0+120.0*abc+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*abc^11.0+0.002976190476190476*abc^8.0+0.0047619047619047615*abc^7.0-0.041666666666666664*abc^4.0-4.166666666666667*abc^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*abc^12.0+0.002976190476190476/9.0*abc^9.0+0.0047619047619047615/8.0*abc^8.0-0.041666666666666664/5.0*abc^5.0-4.166666666666667/4.0*abc^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*abc^12.0+3.3068783068783067E-4*abc^9.0+5.952380952380952E-4*abc^8.0-0.008333333333333333*abc^5.0-1.0416666666666667*abc^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "1680.0*abc^4.0+120.0*abc+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*abc^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*abc^3.0+120.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*abc^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*abc^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*abc^2.0+";
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
        public static void TestCaseDiff10004() {
        //Test Case 10004
        Usage.TestCase = 10004;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "xyc1^8 + xyc1^5 + xyc1^4 - xyc1 - 25";
        Usage.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        Usage.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0-xyc1-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0-xyc1-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0-1.0/2.0*xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0-0.5*xyc1^2.0-25.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0-0.5*xyc1^2.0-25.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc1^10.0+0.16666666666666666/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0-0.5/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc1^11.0+0.023809523809523808/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0-0.16666666666666666/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc1^12.0+0.002976190476190476/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0-0.041666666666666664/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0-0.008333333333333333*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*xyc1^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*xyc1^3.0+120.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*xyc1^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*xyc1^2.0+";
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
        public static void TestCaseDiff10005() {
        //Test Case 10005
        Usage.TestCase = 10005;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x2yc1^8 + x2yc1^5 + x2yc1^4 - x2yc1 - 25";
        Usage.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        Usage.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        Usage.DiffWithRespTo = "x2yc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x2yc1^8.0+x2yc1^5.0+x2yc1^4.0-x2yc1-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="x2yc1^8.0+x2yc1^5.0+x2yc1^4.0-x2yc1-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x2yc1^9.0+1.0/6.0*x2yc1^6.0+1.0/5.0*x2yc1^5.0-1.0/2.0*x2yc1^2.0-25.0*x2yc1";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x2yc1^9.0+0.16666666666666666*x2yc1^6.0+0.2*x2yc1^5.0-0.5*x2yc1^2.0-25.0*x2yc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x2yc1^6.0+5.0*4.0*x2yc1^3.0+4.0*3.0*x2yc1^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x2yc1^6.0+20.0*x2yc1^3.0+12.0*x2yc1^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x2yc1^9.0+0.16666666666666666*x2yc1^6.0+0.2*x2yc1^5.0-0.5*x2yc1^2.0-25.0*x2yc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x2yc1^10.0+0.16666666666666666/7.0*x2yc1^7.0+0.2/6.0*x2yc1^6.0-0.5/3.0*x2yc1^3.0-25.0/2.0*x2yc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x2yc1^10.0+0.023809523809523808*x2yc1^7.0+0.03333333333333333*x2yc1^6.0-0.16666666666666666*x2yc1^3.0-12.5*x2yc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x2yc1^6.0+20.0*x2yc1^3.0+12.0*x2yc1^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x2yc1^5.0+20.0*3.0*x2yc1^2.0+12.0*2.0*x2yc1^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x2yc1^5.0+60.0*x2yc1^2.0+24.0*x2yc1";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x2yc1^10.0+0.023809523809523808*x2yc1^7.0+0.03333333333333333*x2yc1^6.0-0.16666666666666666*x2yc1^3.0-12.5*x2yc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x2yc1^11.0+0.023809523809523808/8.0*x2yc1^8.0+0.03333333333333333/7.0*x2yc1^7.0-0.16666666666666666/4.0*x2yc1^4.0-12.5/3.0*x2yc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x2yc1^11.0+0.002976190476190476*x2yc1^8.0+0.0047619047619047615*x2yc1^7.0-0.041666666666666664*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x2yc1^5.0+60.0*x2yc1^2.0+24.0*x2yc1";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x2yc1^4.0+60.0*2.0*x2yc1^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x2yc1^11.0+0.002976190476190476*x2yc1^8.0+0.0047619047619047615*x2yc1^7.0-0.041666666666666664*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x2yc1^12.0+0.002976190476190476/9.0*x2yc1^9.0+0.0047619047619047615/8.0*x2yc1^8.0-0.041666666666666664/5.0*x2yc1^5.0-4.166666666666667/4.0*x2yc1^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x2yc1^12.0+3.3068783068783067E-4*x2yc1^9.0+5.952380952380952E-4*x2yc1^8.0-0.008333333333333333*x2yc1^5.0-1.0416666666666667*x2yc1^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
