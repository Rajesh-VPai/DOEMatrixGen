/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import Calculus.ConsoleColors;
import Calculus.UsageCalculus;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessNames {
    public static Logger log = Logger.getLogger(TestHarnessNames.class.getName());
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
                break;
            case 10005:
                TestCaseDiff10005();
                break;
            // Need Test Cases for multichar Independant Constants
            case 10006:
                TestCaseDiff10006();
                break;
            case 10007:
                TestCaseDiff10007();
                break;
            case 10008:
                TestCaseDiff10008();
                break;
            case 10009:
                TestCaseDiff10009();
                break;
            case 10010:
                TestCaseDiff10010();
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
        UsageCalculus.TestCase = 10001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1.0*x^8 + x^5 + 3.0/3.0*x^4 - x - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:1 Letter";
        UsageCalculus.TestCaseMatrixID="Variables:1 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/1*x^8.0+x^5.0+1.0/1*x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x^2.0+";
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
        public static void TestCaseDiff10002() {
        //Test Case 10002
        UsageCalculus.TestCase = 10002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xy^8 + xy^5 + xy^4 - xy - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:2 Letter";
        UsageCalculus.TestCaseMatrixID="Variables:2 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xy^8.0+xy^5.0+xy^4.0-xy-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xy^8.0+xy^5.0+xy^4.0-xy-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xy^9.0+1.0/6.0*xy^6.0+1.0/5.0*xy^5.0-1.0/2.0*xy^2.0-25.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xy^9.0+1.0/6.0*xy^6.0+1.0/5.0*xy^5.0-1.0/2.0*xy^2.0-25.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xy^7.0+5.0*xy^4.0+4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xy^6.0+5.0*4.0*xy^3.0+4.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xy^6.0+20.0*xy^3.0+12.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xy^9.0+1.0/6.0*xy^6.0+1.0/5.0*xy^5.0-1.0/2.0*xy^2.0-25.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xy^10.0+0.16666666666666666/7.0*xy^7.0+0.2/6.0*xy^6.0-0.5/3.0*xy^3.0-25.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xy^10.0+0.023809523809523808*xy^7.0+0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0-12.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xy^6.0+20.0*xy^3.0+12.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xy^5.0+20.0*3.0*xy^2.0+12.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xy^5.0+60.0*xy^2.0+24.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xy^10.0+0.023809523809523808*xy^7.0+0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0-12.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xy^11.0+0.023809523809523808/8.0*xy^8.0+0.03333333333333333/7.0*xy^7.0-0.16666666666666666/4.0*xy^4.0-12.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xy^11.0+0.002976190476190476*xy^8.0+0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0-4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xy^5.0+60.0*xy^2.0+24.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xy^4.0+60.0*2.0*xy^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xy^4.0+120.0*xy+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xy^11.0+0.002976190476190476*xy^8.0+0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0-4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xy^12.0+0.002976190476190476/9.0*xy^9.0+0.0047619047619047615/8.0*xy^8.0-0.041666666666666664/5.0*xy^5.0-4.166666666666667/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xy^12.0+3.3068783068783067E-4*xy^9.0+5.952380952380952E-4*xy^8.0-0.008333333333333333*xy^5.0-1.0416666666666667*xy^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*xy^4.0+120.0*xy+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*xy^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*xy^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*xy^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*xy^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*xy^2.0+";
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
        public static void TestCaseDiff10003() {
        //Test Case 10003
        UsageCalculus.TestCase = 10003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "abc^8 + abc^5 + abc^4 - abc - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "abc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "abc^8.0+abc^5.0+abc^4.0-abc-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="abc^8.0+abc^5.0+abc^4.0-abc-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*abc^9.0+1.0/6.0*abc^6.0+1.0/5.0*abc^5.0-1.0/2.0*abc^2.0-25.0*abc";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*abc^9.0+1.0/6.0*abc^6.0+1.0/5.0*abc^5.0-1.0/2.0*abc^2.0-25.0*abc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*abc^7.0+5.0*abc^4.0+4.0*abc^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*abc^6.0+5.0*4.0*abc^3.0+4.0*3.0*abc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*abc^6.0+20.0*abc^3.0+12.0*abc^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*abc^9.0+1.0/6.0*abc^6.0+1.0/5.0*abc^5.0-1.0/2.0*abc^2.0-25.0*abc";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*abc^10.0+0.16666666666666666/7.0*abc^7.0+0.2/6.0*abc^6.0-0.5/3.0*abc^3.0-25.0/2.0*abc^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*abc^10.0+0.023809523809523808*abc^7.0+0.03333333333333333*abc^6.0-0.16666666666666666*abc^3.0-12.5*abc^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*abc^6.0+20.0*abc^3.0+12.0*abc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*abc^5.0+20.0*3.0*abc^2.0+12.0*2.0*abc^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*abc^5.0+60.0*abc^2.0+24.0*abc";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*abc^10.0+0.023809523809523808*abc^7.0+0.03333333333333333*abc^6.0-0.16666666666666666*abc^3.0-12.5*abc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*abc^11.0+0.023809523809523808/8.0*abc^8.0+0.03333333333333333/7.0*abc^7.0-0.16666666666666666/4.0*abc^4.0-12.5/3.0*abc^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*abc^11.0+0.002976190476190476*abc^8.0+0.0047619047619047615*abc^7.0-0.041666666666666664*abc^4.0-4.166666666666667*abc^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*abc^5.0+60.0*abc^2.0+24.0*abc";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*abc^4.0+60.0*2.0*abc^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*abc^4.0+120.0*abc+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*abc^11.0+0.002976190476190476*abc^8.0+0.0047619047619047615*abc^7.0-0.041666666666666664*abc^4.0-4.166666666666667*abc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*abc^12.0+0.002976190476190476/9.0*abc^9.0+0.0047619047619047615/8.0*abc^8.0-0.041666666666666664/5.0*abc^5.0-4.166666666666667/4.0*abc^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*abc^12.0+3.3068783068783067E-4*abc^9.0+5.952380952380952E-4*abc^8.0-0.008333333333333333*abc^5.0-1.0416666666666667*abc^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*abc^4.0+120.0*abc+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*abc^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*abc^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*abc^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*abc^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*abc^2.0+";
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
        public static void TestCaseDiff10004() {
        //Test Case 10004
        UsageCalculus.TestCase = 10004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "xyc1^8 + xyc1^5 + xyc1^4 - xyc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0-xyc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0-xyc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0-1.0/2.0*xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0-1.0/2.0*xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0-1.0/2.0*xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc1^10.0+0.16666666666666666/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0-0.5/3.0*xyc1^3.0-25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc1^11.0+0.023809523809523808/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0-0.16666666666666666/4.0*xyc1^4.0-12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc1^12.0+0.002976190476190476/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0-0.041666666666666664/5.0*xyc1^5.0-4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0-0.008333333333333333*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*xyc1^4.0+120.0*xyc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*xyc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*xyc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*xyc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*xyc1^2.0+";
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
        public static void TestCaseDiff10005() {
        //Test Case 10005
        UsageCalculus.TestCase = 10005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x2yc1^8 + x2yc1^5 + x2yc1^4 - x2yc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x2yc1^8.0+x2yc1^5.0+x2yc1^4.0-x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x2yc1^8.0+x2yc1^5.0+x2yc1^4.0-x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x2yc1^9.0+1.0/6.0*x2yc1^6.0+1.0/5.0*x2yc1^5.0-1.0/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x2yc1^9.0+1.0/6.0*x2yc1^6.0+1.0/5.0*x2yc1^5.0-1.0/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x2yc1^7.0+5.0*x2yc1^4.0+4.0*x2yc1^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x2yc1^6.0+5.0*4.0*x2yc1^3.0+4.0*3.0*x2yc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x2yc1^6.0+20.0*x2yc1^3.0+12.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x2yc1^9.0+1.0/6.0*x2yc1^6.0+1.0/5.0*x2yc1^5.0-1.0/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x2yc1^10.0+0.16666666666666666/7.0*x2yc1^7.0+0.2/6.0*x2yc1^6.0-0.5/3.0*x2yc1^3.0-25.0/2.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x2yc1^10.0+0.023809523809523808*x2yc1^7.0+0.03333333333333333*x2yc1^6.0-0.16666666666666666*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x2yc1^6.0+20.0*x2yc1^3.0+12.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x2yc1^5.0+20.0*3.0*x2yc1^2.0+12.0*2.0*x2yc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x2yc1^5.0+60.0*x2yc1^2.0+24.0*x2yc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x2yc1^10.0+0.023809523809523808*x2yc1^7.0+0.03333333333333333*x2yc1^6.0-0.16666666666666666*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x2yc1^11.0+0.023809523809523808/8.0*x2yc1^8.0+0.03333333333333333/7.0*x2yc1^7.0-0.16666666666666666/4.0*x2yc1^4.0-12.5/3.0*x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x2yc1^11.0+0.002976190476190476*x2yc1^8.0+0.0047619047619047615*x2yc1^7.0-0.041666666666666664*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x2yc1^5.0+60.0*x2yc1^2.0+24.0*x2yc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x2yc1^4.0+60.0*2.0*x2yc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x2yc1^11.0+0.002976190476190476*x2yc1^8.0+0.0047619047619047615*x2yc1^7.0-0.041666666666666664*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x2yc1^12.0+0.002976190476190476/9.0*x2yc1^9.0+0.0047619047619047615/8.0*x2yc1^8.0-0.041666666666666664/5.0*x2yc1^5.0-4.166666666666667/4.0*x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x2yc1^12.0+3.3068783068783067E-4*x2yc1^9.0+5.952380952380952E-4*x2yc1^8.0-0.008333333333333333*x2yc1^5.0-1.0416666666666667*x2yc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
        public static void TestCaseDiff10006() {
        //Test Case 10006
        UsageCalculus.TestCase = 10006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x2yc1^8*xyc1 + x2yc1^5*xyc1 + x2yc1^4*xyc1 - x2yc1*xyc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1*x2yc1^8.0+xyc1*x2yc1^5.0+xyc1*x2yc1^4.0-xyc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "xyc1*8.0*x2yc1^7.0+xyc1*5.0*x2yc1^4.0+xyc1*4.0*x2yc1^3.0-xyc1-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "xyc1*8.0*x2yc1^7.0+xyc1*5.0*x2yc1^4.0+xyc1*4.0*x2yc1^3.0-xyc1";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1*x2yc1^8.0+xyc1*x2yc1^5.0+xyc1*x2yc1^4.0-xyc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="xyc1/9.0*x2yc1^9.0+xyc1/6.0*x2yc1^6.0+xyc1/5.0*x2yc1^5.0-xyc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="xyc1/9.0*x2yc1^9.0+xyc1/6.0*x2yc1^6.0+xyc1/5.0*x2yc1^5.0-xyc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "xyc1*8.0*x2yc1^7.0+xyc1*5.0*x2yc1^4.0+xyc1*4.0*x2yc1^3.0-xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "xyc1*8.0*7.0*x2yc1^6.0+xyc1*5.0*4.0*x2yc1^3.0+xyc1*4.0*3.0*x2yc1^2.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "xyc1*56.0*x2yc1^6.0+xyc1*20.0*x2yc1^3.0+xyc1*12.0*x2yc1^2.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="xyc1/9.0*x2yc1^9.0+xyc1/6.0*x2yc1^6.0+xyc1/5.0*x2yc1^5.0-xyc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="xyc1/9.0/10.0*x2yc1^10.0+xyc1/6.0/7.0*x2yc1^7.0+xyc1/5.0/6.0*x2yc1^6.0-xyc1/2.0/3.0*x2yc1^3.0-25.0/2.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="xyc1/90.0*x2yc1^10.0+xyc1/42.0*x2yc1^7.0+xyc1/30.0*x2yc1^6.0-xyc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "xyc1*56.0*x2yc1^6.0+xyc1*20.0*x2yc1^3.0+xyc1*12.0*x2yc1^2.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "xyc1*56.0*6.0*x2yc1^5.0+xyc1*20.0*3.0*x2yc1^2.0+xyc1*12.0*2.0*x2yc1^1.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "xyc1*336.0*x2yc1^5.0+xyc1*60.0*x2yc1^2.0+xyc1*24.0*x2yc1-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="xyc1/90.0*x2yc1^10.0+xyc1/42.0*x2yc1^7.0+xyc1/30.0*x2yc1^6.0-xyc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="xyc1/90.0/11.0*x2yc1^11.0+xyc1/42.0/8.0*x2yc1^8.0+xyc1/30.0/7.0*x2yc1^7.0-xyc1/6.0/4.0*x2yc1^4.0-12.5/3.0*x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="xyc1/990.0*x2yc1^11.0+xyc1/336.0*x2yc1^8.0+xyc1/210.0*x2yc1^7.0-xyc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "xyc1*336.0*x2yc1^5.0+xyc1*60.0*x2yc1^2.0+xyc1*24.0*x2yc1-xyc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "xyc1*336.0*5.0*x2yc1^4.0+xyc1*60.0*2.0*x2yc1^1.0+xyc1*24.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "xyc1*1680.0*x2yc1^4.0+xyc1*120.0*x2yc1+xyc1*24.0-xyc1*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="xyc1/990.0*x2yc1^11.0+xyc1/336.0*x2yc1^8.0+xyc1/210.0*x2yc1^7.0-xyc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="xyc1/990.0/12.0*x2yc1^12.0+xyc1/336.0/9.0*x2yc1^9.0+xyc1/210.0/8.0*x2yc1^8.0-xyc1/24.0/5.0*x2yc1^5.0-4.166666666666667/4.0*x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="xyc1/11880.0*x2yc1^12.0+xyc1/3024.0*x2yc1^9.0+xyc1/1680.0*x2yc1^8.0-xyc1/120.0*x2yc1^5.0-1.0416666666666667*x2yc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
        public static void TestCaseDiff10007() {
        //Test Case 10007
        UsageCalculus.TestCase = 10007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x2yc1^8*x1yc1 + x2yc1^5*x1yc1 + x2yc1^4*x1yc1 - x2yc1*x1yc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x1yc1*x2yc1^8.0+x1yc1*x2yc1^5.0+x1yc1*x2yc1^4.0-x1yc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1";
        UsageCalculus.EqnSolArrayMap3[1]="x1yc1*x2yc1^8.0+x1yc1*x2yc1^5.0+x1yc1*x2yc1^4.0-x1yc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "x1yc1*8.0*7.0*x2yc1^6.0+x1yc1*5.0*4.0*x2yc1^3.0+x1yc1*4.0*3.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "x1yc1*56.0*x2yc1^6.0+x1yc1*20.0*x2yc1^3.0+x1yc1*12.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="x1yc1/9.0/10.0*x2yc1^10.0+x1yc1/6.0/7.0*x2yc1^7.0+x1yc1/5.0/6.0*x2yc1^6.0-x1yc1/2.0/3.0*x2yc1^3.0-25.0/2.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="x1yc1/90.0*x2yc1^10.0+x1yc1/42.0*x2yc1^7.0+x1yc1/30.0*x2yc1^6.0-x1yc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "x1yc1*56.0*x2yc1^6.0+x1yc1*20.0*x2yc1^3.0+x1yc1*12.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "x1yc1*56.0*6.0*x2yc1^5.0+x1yc1*20.0*3.0*x2yc1^2.0+x1yc1*12.0*2.0*x2yc1^1.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "x1yc1*336.0*x2yc1^5.0+x1yc1*60.0*x2yc1^2.0+x1yc1*24.0*x2yc1-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="x1yc1/90.0*x2yc1^10.0+x1yc1/42.0*x2yc1^7.0+x1yc1/30.0*x2yc1^6.0-x1yc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="x1yc1/90.0/11.0*x2yc1^11.0+x1yc1/42.0/8.0*x2yc1^8.0+x1yc1/30.0/7.0*x2yc1^7.0-x1yc1/6.0/4.0*x2yc1^4.0-12.5/3.0*x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="x1yc1/990.0*x2yc1^11.0+x1yc1/336.0*x2yc1^8.0+x1yc1/210.0*x2yc1^7.0-x1yc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "x1yc1*336.0*x2yc1^5.0+x1yc1*60.0*x2yc1^2.0+x1yc1*24.0*x2yc1-x1yc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "x1yc1*336.0*5.0*x2yc1^4.0+x1yc1*60.0*2.0*x2yc1^1.0+x1yc1*24.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "x1yc1*1680.0*x2yc1^4.0+x1yc1*120.0*x2yc1+x1yc1*24.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="x1yc1/990.0*x2yc1^11.0+x1yc1/336.0*x2yc1^8.0+x1yc1/210.0*x2yc1^7.0-x1yc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="x1yc1/990.0/12.0*x2yc1^12.0+x1yc1/336.0/9.0*x2yc1^9.0+x1yc1/210.0/8.0*x2yc1^8.0-x1yc1/24.0/5.0*x2yc1^5.0-4.166666666666667/4.0*x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="x1yc1/11880.0*x2yc1^12.0+x1yc1/3024.0*x2yc1^9.0+x1yc1/1680.0*x2yc1^8.0-x1yc1/120.0*x2yc1^5.0-1.0416666666666667*x2yc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
        public static void TestCaseDiff10008() {
        //Test Case 10008
        UsageCalculus.TestCase = 10008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x1yc1*x2yc1^8 + x1yc1*x2yc1^5 + x1yc1*x2yc1^4 - x1yc1*x2yc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x1yc1*x2yc1^8.0+x1yc1*x2yc1^5.0+x1yc1*x2yc1^4.0-x1yc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1";
        UsageCalculus.EqnSolArrayMap3[1]="x1yc1*x2yc1^8.0+x1yc1*x2yc1^5.0+x1yc1*x2yc1^4.0-x1yc1*x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "x1yc1*8.0*x2yc1^7.0+x1yc1*5.0*x2yc1^4.0+x1yc1*4.0*x2yc1^3.0-x1yc1";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "x1yc1*8.0*7.0*x2yc1^6.0+x1yc1*5.0*4.0*x2yc1^3.0+x1yc1*4.0*3.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "x1yc1*56.0*x2yc1^6.0+x1yc1*20.0*x2yc1^3.0+x1yc1*12.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="x1yc1/9.0*x2yc1^9.0+x1yc1/6.0*x2yc1^6.0+x1yc1/5.0*x2yc1^5.0-x1yc1/2.0*x2yc1^2.0-25.0*x2yc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="x1yc1/9.0/10.0*x2yc1^10.0+x1yc1/6.0/7.0*x2yc1^7.0+x1yc1/5.0/6.0*x2yc1^6.0-x1yc1/2.0/3.0*x2yc1^3.0-25.0/2.0*x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="x1yc1/90.0*x2yc1^10.0+x1yc1/42.0*x2yc1^7.0+x1yc1/30.0*x2yc1^6.0-x1yc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "x1yc1*56.0*x2yc1^6.0+x1yc1*20.0*x2yc1^3.0+x1yc1*12.0*x2yc1^2.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "x1yc1*56.0*6.0*x2yc1^5.0+x1yc1*20.0*3.0*x2yc1^2.0+x1yc1*12.0*2.0*x2yc1^1.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "x1yc1*336.0*x2yc1^5.0+x1yc1*60.0*x2yc1^2.0+x1yc1*24.0*x2yc1-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="x1yc1/90.0*x2yc1^10.0+x1yc1/42.0*x2yc1^7.0+x1yc1/30.0*x2yc1^6.0-x1yc1/6.0*x2yc1^3.0-12.5*x2yc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="x1yc1/90.0/11.0*x2yc1^11.0+x1yc1/42.0/8.0*x2yc1^8.0+x1yc1/30.0/7.0*x2yc1^7.0-x1yc1/6.0/4.0*x2yc1^4.0-12.5/3.0*x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="x1yc1/990.0*x2yc1^11.0+x1yc1/336.0*x2yc1^8.0+x1yc1/210.0*x2yc1^7.0-x1yc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "x1yc1*336.0*x2yc1^5.0+x1yc1*60.0*x2yc1^2.0+x1yc1*24.0*x2yc1-x1yc1*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "x1yc1*336.0*5.0*x2yc1^4.0+x1yc1*60.0*2.0*x2yc1^1.0+x1yc1*24.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "x1yc1*1680.0*x2yc1^4.0+x1yc1*120.0*x2yc1+x1yc1*24.0-x1yc1*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="x1yc1/990.0*x2yc1^11.0+x1yc1/336.0*x2yc1^8.0+x1yc1/210.0*x2yc1^7.0-x1yc1/24.0*x2yc1^4.0-4.166666666666667*x2yc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="x1yc1/990.0/12.0*x2yc1^12.0+x1yc1/336.0/9.0*x2yc1^9.0+x1yc1/210.0/8.0*x2yc1^8.0-x1yc1/24.0/5.0*x2yc1^5.0-4.166666666666667/4.0*x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="x1yc1/11880.0*x2yc1^12.0+x1yc1/3024.0*x2yc1^9.0+x1yc1/1680.0*x2yc1^8.0-x1yc1/120.0*x2yc1^5.0-1.0416666666666667*x2yc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
        public static void TestCaseDiff10009() {
        //Test Case 10009
        UsageCalculus.TestCase = 10009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x1yc1/x2yc1^8 + x1yc1/x2yc1^5 + x1yc1/x2yc1^4 - x1yc1/x2yc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x1yc1/x2yc1^8.0+x1yc1/x2yc1^5.0+x1yc1/x2yc1^4.0-x1yc1/x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "x1yc1*-8.0/x2yc1^9.0+x1yc1*-5.0/x2yc1^6.0+x1yc1*-4.0/x2yc1^5.0+x1yc1/x2yc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-x1yc1*8.0/x2yc1^9.0-x1yc1*5.0/x2yc1^6.0-x1yc1*4.0/x2yc1^5.0+x1yc1/x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="x1yc1/x2yc1^8.0+x1yc1/x2yc1^5.0+x1yc1/x2yc1^4.0-x1yc1/x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="x1yc1/-7.0/x2yc1^7.0+x1yc1/-4.0/x2yc1^4.0+x1yc1/-3.0/x2yc1^3.0-x1yc1*ln(x2yc1)-25.0*x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="-x1yc1/7.0/x2yc1^7.0-x1yc1/4.0/x2yc1^4.0-x1yc1/3.0/x2yc1^3.0-x1yc1*ln(x2yc1)-25.0*x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-x1yc1*8.0/x2yc1^9.0-x1yc1*5.0/x2yc1^6.0-x1yc1*4.0/x2yc1^5.0+x1yc1/x2yc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-x1yc1*8.0*-9.0/x2yc1^10.0-x1yc1*5.0*-6.0/x2yc1^7.0-x1yc1*4.0*-5.0/x2yc1^6.0+x1yc1*-2.0/x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "x1yc1*72.0/x2yc1^10.0+x1yc1*30.0/x2yc1^7.0+x1yc1*20.0/x2yc1^6.0-x1yc1*2.0/x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "x1yc1*72.0/x2yc1^10.0+x1yc1*30.0/x2yc1^7.0+x1yc1*20.0/x2yc1^6.0-x1yc1*2.0/x2yc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "x1yc1*72.0*-10.0/x2yc1^11.0+x1yc1*30.0*-7.0/x2yc1^8.0+x1yc1*20.0*-6.0/x2yc1^7.0-x1yc1*2.0*-3.0/x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-x1yc1*720.0/x2yc1^11.0-x1yc1*210.0/x2yc1^8.0-x1yc1*120.0/x2yc1^7.0+x1yc1*6.0/x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-x1yc1*720.0/x2yc1^11.0-x1yc1*210.0/x2yc1^8.0-x1yc1*120.0/x2yc1^7.0+x1yc1*6.0/x2yc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-x1yc1*720.0*-11.0/x2yc1^12.0-x1yc1*210.0*-8.0/x2yc1^9.0-x1yc1*120.0*-7.0/x2yc1^8.0+x1yc1*6.0*-4.0/x2yc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "x1yc1*7920.0/x2yc1^12.0+x1yc1*1680.0/x2yc1^9.0+x1yc1*840.0/x2yc1^8.0-x1yc1*24.0/x2yc1^5.0";
        UsageCalculus.EqnSolArrayMap3[4]="";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="";
        UsageCalculus.EqnSolArrayMap4[4]="";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
        public static void TestCaseDiff10010() {
        //Test Case 10010
        UsageCalculus.TestCase = 10010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x1yc1x2yc1^8 + x1yc1x2yc1^5 + x1yc1x2yc1^4 - x1yc1x2yc1 - 25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Variables:3 Letter with Numerics:No Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x1yc1x2yc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x1yc1x2yc1^8.0+x1yc1x2yc1^5.0+x1yc1x2yc1^4.0-x1yc1x2yc1-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x1yc1x2yc1^7.0+5.0*x1yc1x2yc1^4.0+4.0*x1yc1x2yc1^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x1yc1x2yc1^7.0+5.0*x1yc1x2yc1^4.0+4.0*x1yc1x2yc1^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x1yc1x2yc1^8.0+x1yc1x2yc1^5.0+x1yc1x2yc1^4.0-x1yc1x2yc1-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x1yc1x2yc1^9.0+1.0/6.0*x1yc1x2yc1^6.0+1.0/5.0*x1yc1x2yc1^5.0-1.0/2.0*x1yc1x2yc1^2.0-25.0*x1yc1x2yc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x1yc1x2yc1^9.0+1.0/6.0*x1yc1x2yc1^6.0+1.0/5.0*x1yc1x2yc1^5.0-1.0/2.0*x1yc1x2yc1^2.0-25.0*x1yc1x2yc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x1yc1x2yc1^7.0+5.0*x1yc1x2yc1^4.0+4.0*x1yc1x2yc1^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x1yc1x2yc1^6.0+5.0*4.0*x1yc1x2yc1^3.0+4.0*3.0*x1yc1x2yc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x1yc1x2yc1^6.0+20.0*x1yc1x2yc1^3.0+12.0*x1yc1x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x1yc1x2yc1^9.0+1.0/6.0*x1yc1x2yc1^6.0+1.0/5.0*x1yc1x2yc1^5.0-1.0/2.0*x1yc1x2yc1^2.0-25.0*x1yc1x2yc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x1yc1x2yc1^10.0+0.16666666666666666/7.0*x1yc1x2yc1^7.0+0.2/6.0*x1yc1x2yc1^6.0-0.5/3.0*x1yc1x2yc1^3.0-25.0/2.0*x1yc1x2yc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x1yc1x2yc1^10.0+0.023809523809523808*x1yc1x2yc1^7.0+0.03333333333333333*x1yc1x2yc1^6.0-0.16666666666666666*x1yc1x2yc1^3.0-12.5*x1yc1x2yc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x1yc1x2yc1^6.0+20.0*x1yc1x2yc1^3.0+12.0*x1yc1x2yc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x1yc1x2yc1^5.0+20.0*3.0*x1yc1x2yc1^2.0+12.0*2.0*x1yc1x2yc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x1yc1x2yc1^5.0+60.0*x1yc1x2yc1^2.0+24.0*x1yc1x2yc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x1yc1x2yc1^10.0+0.023809523809523808*x1yc1x2yc1^7.0+0.03333333333333333*x1yc1x2yc1^6.0-0.16666666666666666*x1yc1x2yc1^3.0-12.5*x1yc1x2yc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x1yc1x2yc1^11.0+0.023809523809523808/8.0*x1yc1x2yc1^8.0+0.03333333333333333/7.0*x1yc1x2yc1^7.0-0.16666666666666666/4.0*x1yc1x2yc1^4.0-12.5/3.0*x1yc1x2yc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x1yc1x2yc1^11.0+0.002976190476190476*x1yc1x2yc1^8.0+0.0047619047619047615*x1yc1x2yc1^7.0-0.041666666666666664*x1yc1x2yc1^4.0-4.166666666666667*x1yc1x2yc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x1yc1x2yc1^5.0+60.0*x1yc1x2yc1^2.0+24.0*x1yc1x2yc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x1yc1x2yc1^4.0+60.0*2.0*x1yc1x2yc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x1yc1x2yc1^4.0+120.0*x1yc1x2yc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x1yc1x2yc1^11.0+0.002976190476190476*x1yc1x2yc1^8.0+0.0047619047619047615*x1yc1x2yc1^7.0-0.041666666666666664*x1yc1x2yc1^4.0-4.166666666666667*x1yc1x2yc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x1yc1x2yc1^12.0+0.002976190476190476/9.0*x1yc1x2yc1^9.0+0.0047619047619047615/8.0*x1yc1x2yc1^8.0-0.041666666666666664/5.0*x1yc1x2yc1^5.0-4.166666666666667/4.0*x1yc1x2yc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x1yc1x2yc1^12.0+3.3068783068783067E-4*x1yc1x2yc1^9.0+5.952380952380952E-4*x1yc1x2yc1^8.0-0.008333333333333333*x1yc1x2yc1^5.0-1.0416666666666667*x1yc1x2yc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x2yc1^4.0+120.0*x2yc1+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x2yc1^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x2yc1^3.0+120.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x2yc1^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x2yc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x2yc1^2.0+";
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
