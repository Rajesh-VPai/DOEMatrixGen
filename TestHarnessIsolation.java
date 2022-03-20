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
import static Calculus.UsageCalculus.TestCase;
import static Calculus.UsageCalculus.EqnRegressionDiffStatus;
import static Calculus.UsageCalculus.EqnRegressionIntegreStatus;
import static Calculus.UsageCalculus.EqnUnderTest;
import static Calculus.UsageCalculus.TestCaseName;
import static Calculus.UsageCalculus.TestCaseMatrixID;
import static Calculus.UsageCalculus.DiffWithRespTo;
import static Calculus.UsageCalculus.MaxDiffOrder;
import static Calculus.UsageCalculus.MaxIntegreOrder;
import static Calculus.UsageCalculus.EqnSolArrayMap1;
import static Calculus.UsageCalculus.EqnSolArrayDiffAlone;
import static Calculus.UsageCalculus.EqnSolArrayMap2;
import static Calculus.UsageCalculus.EqnSolArrayMap3;
import static Calculus.UsageCalculus.EqnSolArrayIntegrAlone;
import static Calculus.UsageCalculus.EqnSolArrayMap4;
import static Calculus.UsageCalculus.EqnSolOrder;
import static Calculus.UsageCalculus.MissedNumberTestCases;
import static Calculus.UsageCalculus.MissedTestCasesException;
import static Calculus.UsageCalculus.MissedTestCasesInfinity;


import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessIsolation {
    public static Logger log = Logger.getLogger(TestHarnessIsolation.class.getName());
    //Isolation Test Cases
    public static void IsolationTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapInfinity(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapInfinity(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED +"Isolation TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapException(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapException(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED +"Isolation TestCase:j= " + j  + ": Exception GAP" + ConsoleColors.RESET);
        }
        else{
            switch(j){
            case 1000:
                TestCaseDiff1000();
                break;
            case 1001:
                TestCaseDiff1001();
                break;
            case 1002:
                TestCaseDiff1002();
                break;
            case 1003:
                TestCaseDiff1003();
                break;
            case 1004:
                TestCaseDiff1004();
                break;
            case 1005:
                TestCaseDiff1005();
                break;
            case 1006:
                TestCaseDiff1006();
                break;
            case 1007:
                TestCaseDiff1007();
                break;
            case 1008:
                TestCaseDiff1008();
                break;
            case 1009:
                TestCaseDiff1009();
                break;
            case 1010:
                TestCaseDiff1010();
                break;
            case 1011:
                TestCaseDiff1011();
                break;
            case 1012:
                TestCaseDiff1012();
                break;
            case 1013:
                TestCaseDiff1013();
                break;
            case 1014:
                TestCaseDiff1014();
                break;
            case 1015:
                TestCaseDiff1015();
                break;
            case 1016:
                TestCaseDiff1016();
                break;
            case 1017:
                TestCaseDiff1017();
                break;
            case 1018:
                TestCaseDiff1018();
                break;
            case 1019:
                TestCaseDiff1019();
                break;
            case 1020:
                TestCaseDiff1020();
                break;
            case 1021:
                TestCaseDiff1021();
                break;
            case 1022:
                TestCaseDiff1022();
                break;
            case 1023:
                TestCaseDiff1023();
                break;
            case 1024:
                TestCaseDiff1024();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Isolation TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
    }
}
    //Equation Types Test Cases
    
    public static void TestCaseDiff1000() {
        //Test Case 1000
        TestCase = 1000;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "x^8 + x^5 + x^4 - x - 25";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 5;
        
        EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-x-25.0";
        EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayMap4[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        EqnSolArrayMap3[2]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1001() {
        //Test Case 1001
        TestCase = 1001;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "8*x^8 + 5*x^5 + 4*x^4 - 1.0*x - 25";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "8.0*x^8.0+5.0*x^5.0+4.0*x^4.0-x-25.0";
        EqnSolArrayDiffAlone[1] = "8.0*8.0*x^7.0+5.0*5.0*x^4.0+4.0*4.0*x^3.0-1.0-0.0";
        EqnSolArrayMap2[1] = "64.0*x^7.0+25.0*x^4.0+16.0*x^3.0-1.0";
        EqnSolArrayMap3[1]="8.0*x^8.0+5.0*x^5.0+4.0*x^4.0-x-25.0";
        EqnSolArrayIntegrAlone[1]="8.0/9.0*x^9.0+5.0/6.0*x^6.0+4.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayMap4[1]="0.8888888888888888*x^9.0+0.8333333333333334*x^6.0+0.8*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "64.0*x^7.0+25.0*x^4.0+16.0*x^3.0-1.0";
        EqnSolArrayDiffAlone[2] = "64.0*7.0*x^6.0+25.0*4.0*x^3.0+16.0*3.0*x^2.0-0.0";
        EqnSolArrayMap2[2] = "448.0*x^6.0+100.0*x^3.0+48.0*x^2.0";
        EqnSolArrayMap3[2]="0.8888888888888888*x^9.0+0.8333333333333334*x^6.0+0.8*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayIntegrAlone[2]="0.8888888888888888/10.0*x^10.0+0.8333333333333334/7.0*x^7.0+0.8/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        EqnSolArrayMap4[2]="0.08888888888888888*x^10.0+0.11904761904761905*x^7.0+0.13333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "448.0*x^6.0+100.0*x^3.0+48.0*x^2.0";
        EqnSolArrayDiffAlone[3] = "448.0*6.0*x^5.0+100.0*3.0*x^2.0+48.0*2.0*x^1.0";
        EqnSolArrayMap2[3] = "2688.0*x^5.0+300.0*x^2.0+96.0*x";
        EqnSolArrayMap3[3]="0.08888888888888888*x^10.0+0.11904761904761905*x^7.0+0.13333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolArrayIntegrAlone[3]="0.08888888888888888/11.0*x^11.0+0.11904761904761905/8.0*x^8.0+0.13333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        EqnSolArrayMap4[3]="0.008080808080808079*x^11.0+0.014880952380952382*x^8.0+0.019047619047619046*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "2688.0*x^5.0+300.0*x^2.0+96.0*x";
        EqnSolArrayDiffAlone[4] = "2688.0*5.0*x^4.0+300.0*2.0*x^1.0+96.0";
        EqnSolArrayMap2[4] = "13440.0*x^4.0+600.0*x+96.0";
        EqnSolArrayMap3[4]="0.008080808080808079*x^11.0+0.014880952380952382*x^8.0+0.019047619047619046*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolArrayIntegrAlone[4]="0.008080808080808079/12.0*x^12.0+0.014880952380952382/9.0*x^9.0+0.019047619047619046/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        EqnSolArrayMap4[4]="6.734006734006732E-4*x^12.0+0.0016534391534391536*x^9.0+0.0023809523809523807*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1002() {
        //Test Case 1002
        TestCase = 1002;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "a*8*x^8 + a*5.0*x^5 + a*4*x^4 - a*x - 25*a";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 4;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "a*8.0*x^8.0+a*5.0*x^5.0+a*4.0*x^4.0-a*x-a*25.0";
        EqnSolArrayDiffAlone[1] = "a*8.0*8.0*x^7.0+a*5.0*5.0*x^4.0+a*4.0*4.0*x^3.0-a-a*0.0";
        EqnSolArrayMap2[1] = "a*64.0*x^7.0+a*25.0*x^4.0+a*16.0*x^3.0-a-a*0.0";
        EqnSolArrayMap3[1]="a*8.0*x^8.0+a*5.0*x^5.0+a*4.0*x^4.0-a*x-a*25.0";
        EqnSolArrayIntegrAlone[1]="a*8.0/9.0*x^9.0+a*5.0/6.0*x^6.0+a*4.0/5.0*x^5.0-a/2.0*x^2.0-a*25.0*x";
        EqnSolArrayMap4[1]="a*0.8888888888888888*x^9.0+a*0.8333333333333334*x^6.0+a*0.8*x^5.0-a/2.0*x^2.0-a*25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "a*64.0*x^7.0+a*25.0*x^4.0+a*16.0*x^3.0-a-a*0.0";
        EqnSolArrayDiffAlone[2] = "a*64.0*7.0*x^6.0+a*25.0*4.0*x^3.0+a*16.0*3.0*x^2.0-a*0.0-a*0.0";
        EqnSolArrayMap2[2] = "a*448.0*x^6.0+a*100.0*x^3.0+a*48.0*x^2.0-a*0.0-a*0.0";
        EqnSolArrayMap3[2]="a*0.8888888888888888*x^9.0+a*0.8333333333333334*x^6.0+a*0.8*x^5.0-a/2.0*x^2.0-a*25.0*x";
        EqnSolArrayIntegrAlone[2]="a*0.8888888888888888/10.0*x^10.0+a*0.8333333333333334/7.0*x^7.0+a*0.8/6.0*x^6.0-a/2.0/3.0*x^3.0-a*25.0/2.0*x^2.0";
        EqnSolArrayMap4[2]="a*0.08888888888888888*x^10.0+a*0.11904761904761905*x^7.0+a*0.13333333333333333*x^6.0-a/6.0*x^3.0-a*12.5*x^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "a*448.0*x^6.0+a*100.0*x^3.0+a*48.0*x^2.0-a*0.0-a*0.0";
        EqnSolArrayDiffAlone[3] = "a*448.0*6.0*x^5.0+a*100.0*3.0*x^2.0+a*48.0*2.0*x^1.0-a*0.0-a*0.0";
        EqnSolArrayMap2[3] = "a*2688.0*x^5.0+a*300.0*x^2.0+a*96.0*x-a*0.0-a*0.0";
        EqnSolArrayMap3[3]="a*0.08888888888888888*x^10.0+a*0.11904761904761905*x^7.0+a*0.13333333333333333*x^6.0-a/6.0*x^3.0-a*12.5*x^2.0";
        EqnSolArrayIntegrAlone[3]="a*0.08888888888888888/11.0*x^11.0+a*0.11904761904761905/8.0*x^8.0+a*0.13333333333333333/7.0*x^7.0-a/6.0/4.0*x^4.0-a*12.5/3.0*x^3.0";
        EqnSolArrayMap4[3]="a*0.008080808080808079*x^11.0+a*0.014880952380952382*x^8.0+a*0.019047619047619046*x^7.0-a/24.0*x^4.0-a*4.166666666666667*x^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "a*2688.0*x^5.0+a*300.0*x^2.0+a*96.0*x-a*0.0-a*0.0";
        EqnSolArrayDiffAlone[4] = "a*2688.0*5.0*x^4.0+a*300.0*2.0*x^1.0+a*96.0-a*0.0-a*0.0";
        EqnSolArrayMap2[4] = "a*13440.0*x^4.0+a*600.0*x+a*96.0-a*0.0-a*0.0";
        EqnSolArrayMap3[4]="a*0.008080808080808079*x^11.0+a*0.014880952380952382*x^8.0+a*0.019047619047619046*x^7.0-a/24.0*x^4.0-a*4.166666666666667*x^3.0";
        EqnSolArrayIntegrAlone[4]="a*0.008080808080808079/12.0*x^12.0+a*0.014880952380952382/9.0*x^9.0+a*0.019047619047619046/8.0*x^8.0-a/24.0/5.0*x^5.0-a*4.166666666666667/4.0*x^4.0";
        EqnSolArrayMap4[4]="a*6.734006734006732E-4*x^12.0+a*0.0016534391534391536*x^9.0+a*0.0023809523809523807*x^8.0-a/120.0*x^5.0-a*1.0416666666666667*x^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1003() {
        //Test Case 1003
        TestCase = 1003;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "a*x^8 + a*x^5 + a*x^4 - x - 25";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "a*x^8.0+a*x^5.0+a*x^4.0-x-25.0";
        EqnSolArrayDiffAlone[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-1.0-0.0";
        EqnSolArrayMap2[1] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-1.0";
        EqnSolArrayMap3[1]="a*x^8.0+a*x^5.0+a*x^4.0-x-25.0";
        EqnSolArrayIntegrAlone[1]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayMap4[1]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "a*8.0*x^7.0+a*5.0*x^4.0+a*4.0*x^3.0-1.0";
        EqnSolArrayDiffAlone[2] = "a*8.0*7.0*x^6.0+a*5.0*4.0*x^3.0+a*4.0*3.0*x^2.0-0.0";
        EqnSolArrayMap2[2] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0";
        EqnSolArrayMap3[2]="a/9.0*x^9.0+a/6.0*x^6.0+a/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        EqnSolArrayIntegrAlone[2]="a/9.0/10.0*x^10.0+a/6.0/7.0*x^7.0+a/5.0/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        EqnSolArrayMap4[2]="a/90.0*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "a*56.0*x^6.0+a*20.0*x^3.0+a*12.0*x^2.0";
        EqnSolArrayDiffAlone[3] = "a*56.0*6.0*x^5.0+a*20.0*3.0*x^2.0+a*12.0*2.0*x^1.0";
        EqnSolArrayMap2[3] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x";
        EqnSolArrayMap3[3]="a/90.0*x^10.0+a/42.0*x^7.0+a/30.0*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        EqnSolArrayIntegrAlone[3]="a/90.0/11.0*x^11.0+a/42.0/8.0*x^8.0+a/30.0/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        EqnSolArrayMap4[3]="a/990.0*x^11.0+a/336.0*x^8.0+a/210.00000000000003*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "a*336.0*x^5.0+a*60.0*x^2.0+a*24.0*x";
        EqnSolArrayDiffAlone[4] = "a*336.0*5.0*x^4.0+a*60.0*2.0*x^1.0+a*24.0";
        EqnSolArrayMap2[4] = "a*1680.0*x^4.0+a*120.0*x+a*24.0";
        EqnSolArrayMap3[4]="a/990.0*x^11.0+a/336.0*x^8.0+a/210.00000000000003*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        EqnSolArrayIntegrAlone[4]="a/990.0/12.0*x^12.0+a/336.0/9.0*x^9.0+a/210.00000000000003/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        EqnSolArrayMap4[4]="a/11880.0*x^12.0+a/3024.0*x^9.0+a/1680.0000000000002*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1004() {
        //Test Case 1004
        TestCase = 1004;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "a+b+a^2+x^8 + a+b+a^2*x^5 + a+b+a^2/x^4 - a+b+a^2*x - 25";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "a+b+a^2.0+x^8.0+a+b+a^2.0*x^5.0+a+b+a^2.0/x^4.0-a+b+a^2.0*x-25.0";
        EqnSolArrayDiffAlone[1] = "a*0.0+b*0.0+0.0+8.0*x^7.0+a*0.0+b*0.0+a^2.0*5.0*x^4.0+a*0.0+b*0.0+a^2.0*-4.0/x^5.0-a*0.0+b*0.0+a^2.0-0.0";
        EqnSolArrayMap2[1] = "a*0.0+b*0.0+8.0*x^7.0+a*0.0+b*0.0+a^2.0*5.0*x^4.0+a*0.0+b*0.0-a^2.0*4.0/x^5.0-a*0.0+b*0.0+a^2.0";
        EqnSolArrayMap3[1]="a+b+a^2.0+x^8.0+a+b+a^2.0*x^5.0+a+b+a^2.0/x^4.0-a+b+a^2.0*x-25.0";
        EqnSolArrayIntegrAlone[1]="a*x+b*x+a^2.0*x+1.0/9.0*x^9.0+a*x+b*x+a^2.0/6.0*x^6.0+a*x+b*x+a^2.0/-3.0/x^3.0-a*x+b*x+a^2.0/2.0*x^2.0-25.0*x";
        EqnSolArrayMap4[1]="a*x+b*x+a^2.0*x+1.0/9.0*x^9.0+a*x+b*x+a^2.0/6.0*x^6.0+a*x+b*x-a^2.0/3.0/x^3.0-a*x+b*x+a^2.0/2.0*x^2.0-25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "a*0.0+b*0.0+8.0*x^7.0+a*0.0+b*0.0+a^2.0*5.0*x^4.0+a*0.0+b*0.0-a^2.0*4.0/x^5.0-a*0.0+b*0.0+a^2.0";
        EqnSolArrayDiffAlone[2] = "a*0.0+b*0.0+8.0*7.0*x^6.0+a*0.0+b*0.0+a^2.0*5.0*4.0*x^3.0+a*0.0+b*0.0-a^2.0*4.0*-5.0/x^6.0-a*0.0+b*0.0+";
        EqnSolArrayMap2[2] = "a*0.0+b*0.0+56.0*x^6.0+a*0.0+b*0.0+a^2.0*20.0*x^3.0+a*0.0+b*0.0+a^2.0*20.0/x^6.0-a*0.0+b*0.0";
        EqnSolArrayMap3[2]="a*x+b*x+a^2.0*x+1.0/9.0*x^9.0+a*x+b*x+a^2.0/6.0*x^6.0+a*x+b*x-a^2.0/3.0/x^3.0-a*x+b*x+a^2.0/2.0*x^2.0-25.0*x";
        EqnSolArrayIntegrAlone[2]="a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/2.0*x^2.0+0.1111111111111111/10.0*x^10.0+a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/6.0/7.0*x^7.0+a/2.0*x^2.0+b/2.0*x^2.0-a^2.0/3.0/-2.0/x^2.0-a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/2.0/3.0*x^3.0-25.0/2.0*x^2.0";
        EqnSolArrayMap4[2]="a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/2.0*x^2.0+0.01111111111111111*x^10.0+a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/42.0*x^7.0+a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/6.0/x^2.0-a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/6.0*x^3.0-12.5*x^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "a*0.0+b*0.0+56.0*x^6.0+a*0.0+b*0.0+a^2.0*20.0*x^3.0+a*0.0+b*0.0+a^2.0*20.0/x^6.0-a*0.0+b*0.0";
        EqnSolArrayDiffAlone[3] = "a*0.0+b*0.0+56.0*6.0*x^5.0+a*0.0+b*0.0+a^2.0*20.0*3.0*x^2.0+a*0.0+b*0.0+a^2.0*20.0*-6.0/x^7.0-a*0.0+b*0.0";
        EqnSolArrayMap2[3] = "a*0.0+b*0.0+336.0*x^5.0+a*0.0+b*0.0+a^2.0*60.0*x^2.0+a*0.0+b*0.0-a^2.0*120.0/x^7.0-a*0.0+b*0.0";
        EqnSolArrayMap3[3]="a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/2.0*x^2.0+0.01111111111111111*x^10.0+a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/42.0*x^7.0+a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/6.0/x^2.0-a/2.0*x^2.0+b/2.0*x^2.0+a^2.0/6.0*x^3.0-12.5*x^2.0";
        EqnSolArrayIntegrAlone[3]="a/2.0/3.0*x^3.0+b/2.0/3.0*x^3.0+a^2.0/2.0/3.0*x^3.0+0.01111111111111111/11.0*x^11.0+a/2.0/3.0*x^3.0+b/2.0/3.0*x^3.0+a^2.0/42.0/8.0*x^8.0+a/2.0/3.0*x^3.0+b/2.0/3.0*x^3.0+a^2.0/6.0/-1.0/x^1.0-a/2.0/3.0*x^3.0+b/2.0/3.0*x^3.0+a^2.0/6.0/4.0*x^4.0-12.5/3.0*x^3.0";
        EqnSolArrayMap4[3]="a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/6.0*x^3.0+0.0010101010101010099*x^11.0+a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/336.0*x^8.0+a/6.0*x^3.0+b/6.0*x^3.0-a^2.0/6.0/x-a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/24.0*x^4.0-4.166666666666667*x^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "a*0.0+b*0.0+336.0*x^5.0+a*0.0+b*0.0+a^2.0*60.0*x^2.0+a*0.0+b*0.0-a^2.0*120.0/x^7.0-a*0.0+b*0.0";
        EqnSolArrayDiffAlone[4] = "a*0.0+b*0.0+336.0*5.0*x^4.0+a*0.0+b*0.0+a^2.0*60.0*2.0*x^1.0+a*0.0+b*0.0-a^2.0*120.0*-7.0/x^8.0-a*0.0+b*0.0";
        EqnSolArrayMap2[4] = "a*0.0+b*0.0+1680.0*x^4.0+a*0.0+b*0.0+a^2.0*120.0*x+a*0.0+b*0.0+a^2.0*840.0/x^8.0-a*0.0+b*0.0";
        EqnSolArrayMap3[4]="a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/6.0*x^3.0+0.0010101010101010099*x^11.0+a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/336.0*x^8.0+a/6.0*x^3.0+b/6.0*x^3.0-a^2.0/6.0/x-a/6.0*x^3.0+b/6.0*x^3.0+a^2.0/24.0*x^4.0-4.166666666666667*x^3.0";
        EqnSolArrayIntegrAlone[4]="a/6.0/4.0*x^4.0+b/6.0/4.0*x^4.0+a^2.0/6.0/4.0*x^4.0+0.0010101010101010099/12.0*x^12.0+a/6.0/4.0*x^4.0+b/6.0/4.0*x^4.0+a^2.0/336.0/9.0*x^9.0+a/6.0/4.0*x^4.0+b/6.0/4.0*x^4.0-a^2.0/6.0*ln(x)-a/6.0/4.0*x^4.0+b/6.0/4.0*x^4.0+a^2.0/24.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        EqnSolArrayMap4[4]="a/24.0*x^4.0+b/24.0*x^4.0+a^2.0/24.0*x^4.0+8.417508417508415E-5*x^12.0+a/24.0*x^4.0+b/24.0*x^4.0+a^2.0/3024.0*x^9.0+a/24.0*x^4.0+b/24.0*x^4.0-a^2.0/6.0*ln(x)-a/24.0*x^4.0+b/24.0*x^4.0+a^2.0/120.0*x^5.0-1.0416666666666667*x^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
        public static void TestCaseDiff1005() {
        //Test Case 1005
        TestCase = 1005;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "(a+b+a^2)*x^8 + (a+b+a^2)*x^5 + (a+b+a^2)/x^4 - (a+b+a^2)*x - 25";
        TestCaseName = "Simple: Coefficients:";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "x^8.0*(a+b+a^2.0)+x^5.0*(a+b+a^2.0)+x^-4.0*(a+b+a^2.0)-x*(a+b+a^2.0)-25.0";
        EqnSolArrayDiffAlone[1] = "";
        EqnSolArrayMap2[1] = "";
        EqnSolArrayMap3[1]="";
        EqnSolArrayIntegrAlone[1]="";
        EqnSolArrayMap4[1]="";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "";
        EqnSolArrayDiffAlone[2] = "";
        EqnSolArrayMap2[2] = "";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "";
        EqnSolArrayDiffAlone[3] = "";
        EqnSolArrayMap2[3] = "";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "";
        EqnSolArrayDiffAlone[4] = "";
        EqnSolArrayMap2[4] = "";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
        //Equation Types Test Cases End
        //Zero -Zero, Infinity, -Infinity , NaN, -NaN Test Cases Start
        //Multiply Zero, Divide Zero, Multiply -Zero, Divide -Zero
        //Multiply Infinity, Divide Infinity, Multiply -Infinity, Divide -Infinity
        //Multiply NaN, Divide NaN, Multiply -NaN, Divide -NaN
    public static void TestCaseDiff1006() {
        //Test Case 1006
        TestCase = 1006;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0*0/x^8 + 1.0*0/x^5 + 1.0*0/x^4 - 1.0*0/x - 25*0";
        TestCaseName = "Simple: Coefficients:0";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x";
        EqnSolArrayDiffAlone[1] = "0.0*-8.0/x^9.0+0.0*-5.0/x^6.0+0.0*-4.0/x^5.0+0.0/x^2.0";
        EqnSolArrayMap2[1] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        EqnSolArrayMap3[1]="0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x";
        EqnSolArrayIntegrAlone[1]="0.0/-7.0/x^7.0+0.0/-4.0/x^4.0+0.0/-3.0/x^3.0-0.0*ln(x)";
        EqnSolArrayMap4[1]="-0.0/x^7.0-0.0/x^4.0-0.0/x^3.0-0.0*ln(x)";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "-0.0*-9.0/x^10.0-0.0*-6.0/x^7.0-0.0*-5.0/x^6.0+0.0*-2.0/x^3.0";
        EqnSolArrayMap2[2] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        EqnSolArrayDiffAlone[3] = "0.0*-10.0/x^11.0+0.0*-7.0/x^8.0+0.0*-6.0/x^7.0-0.0*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        EqnSolArrayDiffAlone[4] = "-0.0*-11.0/x^12.0-0.0*-8.0/x^9.0-0.0*-7.0/x^8.0+0.0*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "0.0/x^12.0+0.0/x^9.0+0.0/x^8.0-0.0/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1007() {
        //Test Case 1007
        TestCase = 1007;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0/0/x^8 + 1.0/0/x^5 + 1.0/0/x^4 - 1.0/0/x - 25/0";
        TestCaseName = "Simple: Coefficients:0";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        EqnSolArrayDiffAlone[1] = "Infinity*-8.0/x^9.0+Infinity*-5.0/x^6.0+Infinity*-4.0/x^5.0+Infinity/x^2.0-Infinity";
        EqnSolArrayMap2[1] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        EqnSolArrayMap3[1]="Infinity/x^8.0+Infinity/x^5.0+Infinity/x^4.0-Infinity/x-Infinity";
        EqnSolArrayIntegrAlone[1]="Infinity/-7.0/x^7.0+Infinity/-4.0/x^4.0+Infinity/-3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        EqnSolArrayMap4[1]="-Infinity/7.0/x^7.0-Infinity/4.0/x^4.0-Infinity/3.0/x^3.0-Infinity*ln(x)-Infinity*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "-Infinity*8.0/x^9.0-Infinity*5.0/x^6.0-Infinity*4.0/x^5.0+Infinity/x^2.0-Infinity";
        EqnSolArrayDiffAlone[2] = "-Infinity*8.0*-9.0/x^10.0-Infinity*5.0*-6.0/x^7.0-Infinity*4.0*-5.0/x^6.0+Infinity*-2.0/x^3.0-Infinity";
        EqnSolArrayMap2[2] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "Infinity*72.0/x^10.0+Infinity*30.0/x^7.0+Infinity*20.0/x^6.0-Infinity*2.0/x^3.0-Infinity";
        EqnSolArrayDiffAlone[3] = "Infinity*72.0*-10.0/x^11.0+Infinity*30.0*-7.0/x^8.0+Infinity*20.0*-6.0/x^7.0-Infinity*2.0*-3.0/x^4.0-Infinity";
        EqnSolArrayMap2[3] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "-Infinity*720.0/x^11.0-Infinity*210.0/x^8.0-Infinity*120.0/x^7.0+Infinity*6.0/x^4.0-Infinity";
        EqnSolArrayDiffAlone[4] = "-Infinity*720.0*-11.0/x^12.0-Infinity*210.0*-8.0/x^9.0-Infinity*120.0*-7.0/x^8.0+Infinity*6.0*-4.0/x^5.0-Infinity";
        EqnSolArrayMap2[4] = "Infinity*7920.0/x^12.0+Infinity*1680.0/x^9.0+Infinity*840.0/x^8.0-Infinity*24.0/x^5.0-Infinity";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1008() {
        //Test Case 1008
        TestCase = 1008;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0*-0/x^8 + 1.0*-0/x^5 + 1.0*-0/x^4 - 1.0*-0/x - 25*-0";
        TestCaseName = "Simple: Coefficients:0";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x";
        EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0";
        EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x";
        EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)";
        EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "0.0*-9.0/x^10.0+0.0*-6.0/x^7.0+0.0*-5.0/x^6.0-0.0*-2.0/x^3.0";
        EqnSolArrayMap2[2] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        EqnSolArrayDiffAlone[3] = "-0.0*-10.0/x^11.0-0.0*-7.0/x^8.0-0.0*-6.0/x^7.0+0.0*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        EqnSolArrayDiffAlone[4] = "0.0*-11.0/x^12.0+0.0*-8.0/x^9.0+0.0*-7.0/x^8.0-0.0*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "-0.0/x^12.0-0.0/x^9.0-0.0/x^8.0+0.0/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    
    public static void TestCaseDiff1009() {
        //Test Case 1009
        TestCase = 1009;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0/-0/0.0/x^8 + 1.0/-0/0.0/x^5 + 1.0/-0/0.0/x^4 - 1.0/-0/0.0/x - 25/-0/0.0";
        TestCaseName = "Simple: Coefficients:-0/0.0";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        EqnSolArrayDiffAlone[1] = "-Infinity*-8.0/x^9.0-Infinity*-5.0/x^6.0-Infinity*-4.0/x^5.0-Infinity/x^2.0+Infinity";
        EqnSolArrayMap2[1] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        EqnSolArrayMap3[1]="-Infinity/x^8.0-Infinity/x^5.0-Infinity/x^4.0+Infinity/x+Infinity";
        EqnSolArrayIntegrAlone[1]="-Infinity/-7.0/x^7.0-Infinity/-4.0/x^4.0-Infinity/-3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        EqnSolArrayMap4[1]="Infinity/7.0/x^7.0+Infinity/4.0/x^4.0+Infinity/3.0/x^3.0+Infinity*ln(x)+Infinity*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "Infinity*8.0/x^9.0+Infinity*5.0/x^6.0+Infinity*4.0/x^5.0-Infinity/x^2.0+Infinity";
        EqnSolArrayDiffAlone[2] = "Infinity*8.0*-9.0/x^10.0+Infinity*5.0*-6.0/x^7.0+Infinity*4.0*-5.0/x^6.0-Infinity*-2.0/x^3.0+Infinity";
        EqnSolArrayMap2[2] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "-Infinity*72.0/x^10.0-Infinity*30.0/x^7.0-Infinity*20.0/x^6.0+Infinity*2.0/x^3.0+Infinity";
        EqnSolArrayDiffAlone[3] = "-Infinity*72.0*-10.0/x^11.0-Infinity*30.0*-7.0/x^8.0-Infinity*20.0*-6.0/x^7.0+Infinity*2.0*-3.0/x^4.0+Infinity";
        EqnSolArrayMap2[3] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "Infinity*720.0/x^11.0+Infinity*210.0/x^8.0+Infinity*120.0/x^7.0-Infinity*6.0/x^4.0+Infinity";
        EqnSolArrayDiffAlone[4] = "Infinity*720.0*-11.0/x^12.0+Infinity*210.0*-8.0/x^9.0+Infinity*120.0*-7.0/x^8.0-Infinity*6.0*-4.0/x^5.0+Infinity";
        EqnSolArrayMap2[4] = "-Infinity*7920.0/x^12.0-Infinity*1680.0/x^9.0-Infinity*840.0/x^8.0+Infinity*24.0/x^5.0+Infinity";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    //25/25 Test Cases
    public static void TestCaseDiff1010() {
        //Test Case 1010
        TestCase = 1010;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0*25/25/x^8 + 1.0*25/25/x^5 + 1.0*25/25/x^4 - 1.0*25/25/x - 25*25/25";
        TestCaseName = "Simple: Coefficients:25/25";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-1.0/x-25.0";
        EqnSolArrayDiffAlone[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0-0.0";
        EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0";
        EqnSolArrayMap3[1]="1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-1.0/x-25.0";
        EqnSolArrayIntegrAlone[1]="1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-ln(x)-25.0*x";
        EqnSolArrayMap4[1]="-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-ln(x)-25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0+1.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0-2.0/x^3.0";
        EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0-2.0/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0-2.0/x^3.0";
        EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0-2.0*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0+6.0/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0+6.0/x^4.0";
        EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0+6.0*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0-24.0/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1011() {
        //Test Case 1011
        TestCase = 1011;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0/25/25/x^8 + 1.0/25/25/x^5 + 1.0/25/25/x^4 - 1.0/25/25/x - 25/25/25";
        TestCaseName = "Simple: Coefficients:25/25";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "1.0/625.0/x^8.0+1.0/625.0/x^5.0+1.0/625.0/x^4.0-1.0/625.0/x-1.0/25.0";
        EqnSolArrayDiffAlone[1] = "1.0/625.0*-8.0/x^9.0+1.0/625.0*-5.0/x^6.0+1.0/625.0*-4.0/x^5.0+1.0/625.0/x^2.0-0.0";
        EqnSolArrayMap2[1] = "-0.0128/x^9.0-0.008/x^6.0-0.0064/x^5.0+1.0/625.0/x^2.0";
        EqnSolArrayMap3[1]="1.0/625.0/x^8.0+1.0/625.0/x^5.0+1.0/625.0/x^4.0-1.0/625.0/x-1.0/25.0";
        EqnSolArrayIntegrAlone[1]="0.0016/-7.0/x^7.0+0.0016/-4.0/x^4.0+0.0016/-3.0/x^3.0-0.0016*ln(x)-0.04*x";
        EqnSolArrayMap4[1]="-2.285714285714286E-4/x^7.0-4.0E-4/x^4.0-5.333333333333334E-4/x^3.0-0.0016*ln(x)-0.04*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "-0.0128/x^9.0-0.008/x^6.0-0.0064/x^5.0+1.0/625.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "-0.0128*-9.0/x^10.0-0.008*-6.0/x^7.0-0.0064*-5.0/x^6.0+1.0/625.0*-2.0/x^3.0";
        EqnSolArrayMap2[2] = "0.11520000000000001/x^10.0+0.048/x^7.0+0.032/x^6.0-0.0032/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "0.11520000000000001/x^10.0+0.048/x^7.0+0.032/x^6.0-0.0032/x^3.0";
        EqnSolArrayDiffAlone[3] = "0.11520000000000001*-10.0/x^11.0+0.048*-7.0/x^8.0+0.032*-6.0/x^7.0-0.0032*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "-1.1520000000000001/x^11.0-0.336/x^8.0-0.192/x^7.0+0.009600000000000001/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "-1.1520000000000001/x^11.0-0.336/x^8.0-0.192/x^7.0+0.009600000000000001/x^4.0";
        EqnSolArrayDiffAlone[4] = "-1.1520000000000001*-11.0/x^12.0-0.336*-8.0/x^9.0-0.192*-7.0/x^8.0+0.009600000000000001*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "12.672/x^12.0+2.688/x^9.0+1.344/x^8.0-0.038400000000000004/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1012() {
        //Test Case 1012
        TestCase = 1012;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0*-25/25/x^8 + 1.0*-25/25/x^5 + 1.0*-25/25/x^4 - 1.0*-25/25/x - 25*-25/25";
        TestCaseName = "Simple: Coefficients:-25/25";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "-1.0/x^8.0-1.0/x^5.0-1.0/x^4.0+1.0/x+25.0";
        EqnSolArrayDiffAlone[1] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0-1.0/x^2.0+0.0";
        EqnSolArrayMap2[1] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0-1.0/x^2.0";
        EqnSolArrayMap3[1]="-1.0/x^8.0-1.0/x^5.0-1.0/x^4.0+1.0/x+25.0";
        EqnSolArrayIntegrAlone[1]="-1.0/-7.0/x^7.0-1.0/-4.0/x^4.0-1.0/-3.0/x^3.0+ln(x)+25.0*x";
        EqnSolArrayMap4[1]="1.0/7.0/x^7.0+1.0/4.0/x^4.0+1.0/3.0/x^3.0+ln(x)+25.0*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0-1.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "8.0*-9.0/x^10.0+5.0*-6.0/x^7.0+4.0*-5.0/x^6.0+2.0/x^3.0";
        EqnSolArrayMap2[2] = "-72.0/x^10.0-30.0/x^7.0-20.0/x^6.0+2.0/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "-72.0/x^10.0-30.0/x^7.0-20.0/x^6.0+2.0/x^3.0";
        EqnSolArrayDiffAlone[3] = "-72.0*-10.0/x^11.0-30.0*-7.0/x^8.0-20.0*-6.0/x^7.0+2.0*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "720.0/x^11.0+210.0/x^8.0+120.0/x^7.0-6.0/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "720.0/x^11.0+210.0/x^8.0+120.0/x^7.0-6.0/x^4.0";
        EqnSolArrayDiffAlone[4] = "720.0*-11.0/x^12.0+210.0*-8.0/x^9.0+120.0*-7.0/x^8.0-6.0*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "-7920.0/x^12.0-1680.0/x^9.0-840.0/x^8.0+24.0/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1013() {
        //Test Case 1013
        TestCase = 1013;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";
        EqnUnderTest = "1.0/-25/25/x^8 + 1.0/-25/25/x^5 + 1.0/-25/25/x^4 - 1.0/-25/25/x - 25/-2525";
        TestCaseName = "Simple: Coefficients:-25/25";
        TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "x";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "-1.0/625.0/x^8.0-1.0/625.0/x^5.0-1.0/625.0/x^4.0+1.0/625.0/x+0.009900990099009901";
        EqnSolArrayDiffAlone[1] = "-1.0/625.0*-8.0/x^9.0-1.0/625.0*-5.0/x^6.0-1.0/625.0*-4.0/x^5.0-1.0/625.0/x^2.0+0.0";
        EqnSolArrayMap2[1] = "0.0128/x^9.0+0.008/x^6.0+0.0064/x^5.0-1.0/625.0/x^2.0";
        EqnSolArrayMap3[1]="-1.0/625.0/x^8.0-1.0/625.0/x^5.0-1.0/625.0/x^4.0+1.0/625.0/x+0.009900990099009901";
        EqnSolArrayIntegrAlone[1]="-0.0016/-7.0/x^7.0-0.0016/-4.0/x^4.0-0.0016/-3.0/x^3.0+0.0016*ln(x)+0.009900990099009901*x";
        EqnSolArrayMap4[1]="2.285714285714286E-4/x^7.0+4.0E-4/x^4.0+5.333333333333334E-4/x^3.0+0.0016*ln(x)+0.009900990099009901*x";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "0.0128/x^9.0+0.008/x^6.0+0.0064/x^5.0-1.0/625.0/x^2.0";
        EqnSolArrayDiffAlone[2] = "0.0128*-9.0/x^10.0+0.008*-6.0/x^7.0+0.0064*-5.0/x^6.0-1.0/625.0*-2.0/x^3.0";
        EqnSolArrayMap2[2] = "-0.11520000000000001/x^10.0-0.048/x^7.0-0.032/x^6.0+0.0032/x^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "-0.11520000000000001/x^10.0-0.048/x^7.0-0.032/x^6.0+0.0032/x^3.0";
        EqnSolArrayDiffAlone[3] = "-0.11520000000000001*-10.0/x^11.0-0.048*-7.0/x^8.0-0.032*-6.0/x^7.0+0.0032*-3.0/x^4.0";
        EqnSolArrayMap2[3] = "1.1520000000000001/x^11.0+0.336/x^8.0+0.192/x^7.0-0.009600000000000001/x^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "1.1520000000000001/x^11.0+0.336/x^8.0+0.192/x^7.0-0.009600000000000001/x^4.0";
        EqnSolArrayDiffAlone[4] = "1.1520000000000001*-11.0/x^12.0+0.336*-8.0/x^9.0+0.192*-7.0/x^8.0-0.009600000000000001*-4.0/x^5.0";
        EqnSolArrayMap2[4] = "-12.672/x^12.0-2.688/x^9.0-1.344/x^8.0+0.038400000000000004/x^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1014() {
        //Test Case 1014
        TestCase = 1014;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "10.0*+10*+xyc1^8/xyc1^2 + 10.0*+10*+xyc1^5/xyc1^2 + 1.0*+10*+xyc1^4/xyc1^2 + 1.0*+10*+xyc1/xyc1^2 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+10";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "100.0*xyc1^6.0+100.0*xyc1^3.0+10.0*xyc1^2.0+10.0/xyc1+125.0";
        EqnSolArrayDiffAlone[1] = "100.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+10.0*2.0*xyc1^1.0-10.0/xyc1^2.0+0.0";
        EqnSolArrayMap2[1] = "600.0*xyc1^5.0+300.0*xyc1^2.0+20.0*xyc1-10.0/xyc1^2.0";
        EqnSolArrayMap3[1]="100.0*xyc1^6.0+100.0*xyc1^3.0+10.0*xyc1^2.0+10.0/xyc1+125.0";
        EqnSolArrayIntegrAlone[1]="100.0/7.0*xyc1^7.0+100.0/4.0*xyc1^4.0+10.0/3.0*xyc1^3.0+10.0*ln(xyc1)+125.0*xyc1";
        EqnSolArrayMap4[1]="14.285714285714286*xyc1^7.0+25.0*xyc1^4.0+3.3333333333333335*xyc1^3.0+10.0*ln(xyc1)+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "600.0*xyc1^5.0+300.0*xyc1^2.0+20.0*xyc1-10.0/xyc1^2.0";
        EqnSolArrayDiffAlone[2] = "600.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+20.0-10.0*-2.0/xyc1^3.0";
        EqnSolArrayMap2[2] = "3000.0*xyc1^4.0+600.0*xyc1+20.0+20.0/xyc1^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "3000.0*xyc1^4.0+600.0*xyc1+20.0+20.0/xyc1^3.0";
        EqnSolArrayDiffAlone[3] = "3000.0*4.0*xyc1^3.0+600.0+0.0+20.0*-3.0/xyc1^4.0";
        EqnSolArrayMap2[3] = "12000.0*xyc1^3.0+600.0-60.0/xyc1^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "12000.0*xyc1^3.0+600.0-60.0/xyc1^4.0";
        EqnSolArrayDiffAlone[4] = "12000.0*3.0*xyc1^2.0+0.0-60.0*-4.0/xyc1^5.0";
        EqnSolArrayMap2[4] = "36000.0*xyc1^2.0+240.0/xyc1^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1015() {
        //Test Case 1015
        TestCase = 1015;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^-2 + 1.0*+5*+xyc1^5/xyc1^-2 + 1.0*+5*+xyc1^4/xyc1^-2 + 1.0*+5*+xyc1/xyc1^-3 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^10.0+5.0*xyc1^7.0+5.0*xyc1^6.0+5.0*xyc1^4.0+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*10.0*xyc1^9.0+5.0*7.0*xyc1^6.0+5.0*6.0*xyc1^5.0+5.0*4.0*xyc1^3.0+0.0";
        EqnSolArrayMap2[1] = "50.0*xyc1^9.0+35.0*xyc1^6.0+30.0*xyc1^5.0+20.0*xyc1^3.0";
        EqnSolArrayMap3[1]="5.0*xyc1^10.0+5.0*xyc1^7.0+5.0*xyc1^6.0+5.0*xyc1^4.0+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/11.0*xyc1^11.0+5.0/8.0*xyc1^8.0+5.0/7.0*xyc1^7.0+5.0/5.0*xyc1^5.0+125.0*xyc1";
        EqnSolArrayMap4[1]="0.45454545454545453*xyc1^11.0+0.625*xyc1^8.0+0.7142857142857143*xyc1^7.0+xyc1^5.0+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "50.0*xyc1^9.0+35.0*xyc1^6.0+30.0*xyc1^5.0+20.0*xyc1^3.0";
        EqnSolArrayDiffAlone[2] = "50.0*9.0*xyc1^8.0+35.0*6.0*xyc1^5.0+30.0*5.0*xyc1^4.0+20.0*3.0*xyc1^2.0";
        EqnSolArrayMap2[2] = "450.0*xyc1^8.0+210.0*xyc1^5.0+150.0*xyc1^4.0+60.0*xyc1^2.0";
        EqnSolArrayMap3[2]="0.45454545454545453*xyc1^11.0+0.625*xyc1^8.0+0.7142857142857143*xyc1^7.0+xyc1^5.0+125.0*xyc1";
        EqnSolArrayIntegrAlone[2]="0.45454545454545453/12.0*xyc1^12.0+0.625/9.0*xyc1^9.0+0.7142857142857143/8.0*xyc1^8.0+1.0/6.0*xyc1^6.0+125.0/2.0*xyc1^2.0";
        EqnSolArrayMap4[2]="0.03787878787878788*xyc1^12.0+0.06944444444444445*xyc1^9.0+0.08928571428571429*xyc1^8.0+1.0/6.0*xyc1^6.0+62.5*xyc1^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "450.0*xyc1^8.0+210.0*xyc1^5.0+150.0*xyc1^4.0+60.0*xyc1^2.0";
        EqnSolArrayDiffAlone[3] = "450.0*8.0*xyc1^7.0+210.0*5.0*xyc1^4.0+150.0*4.0*xyc1^3.0+60.0*2.0*xyc1^1.0";
        EqnSolArrayMap2[3] = "3600.0*xyc1^7.0+1050.0*xyc1^4.0+600.0*xyc1^3.0+120.0*xyc1";
        EqnSolArrayMap3[3]="0.03787878787878788*xyc1^12.0+0.06944444444444445*xyc1^9.0+0.08928571428571429*xyc1^8.0+1.0/6.0*xyc1^6.0+62.5*xyc1^2.0";
        EqnSolArrayIntegrAlone[3]="0.03787878787878788/13.0*xyc1^13.0+0.06944444444444445/10.0*xyc1^10.0+0.08928571428571429/9.0*xyc1^9.0+0.16666666666666666/7.0*xyc1^7.0+62.5/3.0*xyc1^3.0";
        EqnSolArrayMap4[3]="0.002913752913752914*xyc1^13.0+0.006944444444444445*xyc1^10.0+0.00992063492063492*xyc1^9.0+0.023809523809523808*xyc1^7.0+20.833333333333332*xyc1^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "3600.0*xyc1^7.0+1050.0*xyc1^4.0+600.0*xyc1^3.0+120.0*xyc1";
        EqnSolArrayDiffAlone[4] = "3600.0*7.0*xyc1^6.0+1050.0*4.0*xyc1^3.0+600.0*3.0*xyc1^2.0+120.0";
        EqnSolArrayMap2[4] = "25200.0*xyc1^6.0+4200.0*xyc1^3.0+1800.0*xyc1^2.0+120.0";
        EqnSolArrayMap3[4]="0.002913752913752914*xyc1^13.0+0.006944444444444445*xyc1^10.0+0.00992063492063492*xyc1^9.0+0.023809523809523808*xyc1^7.0+20.833333333333332*xyc1^3.0";
        EqnSolArrayIntegrAlone[4]="0.002913752913752914/14.0*xyc1^14.0+0.006944444444444445/11.0*xyc1^11.0+0.00992063492063492/10.0*xyc1^10.0+0.023809523809523808/8.0*xyc1^8.0+20.833333333333332/4.0*xyc1^4.0";
        EqnSolArrayMap4[4]="2.0812520812520815E-4*xyc1^14.0+6.313131313131314E-4*xyc1^11.0+9.92063492063492E-4*xyc1^10.0+0.002976190476190476*xyc1^8.0+5.208333333333333*xyc1^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1016() {
        //Test Case 1016
        TestCase = 1016;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^-2 + 1.0*+5*+xyc1^5/xyc1^-2 + 1.0*+5*+xyc1^4/xyc1^-2 + 1.0*+5*+xyc1/xyc1^-2 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^10.0+5.0*xyc1^7.0+5.0*xyc1^6.0+5.0*xyc1^3.0+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*10.0*xyc1^9.0+5.0*7.0*xyc1^6.0+5.0*6.0*xyc1^5.0+5.0*3.0*xyc1^2.0+0.0";
        EqnSolArrayMap2[1] = "50.0*xyc1^9.0+35.0*xyc1^6.0+30.0*xyc1^5.0+15.0*xyc1^2.0";
        EqnSolArrayMap3[1]="5.0*xyc1^10.0+5.0*xyc1^7.0+5.0*xyc1^6.0+5.0*xyc1^3.0+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/11.0*xyc1^11.0+5.0/8.0*xyc1^8.0+5.0/7.0*xyc1^7.0+5.0/4.0*xyc1^4.0+125.0*xyc1";
        EqnSolArrayMap4[1]="0.45454545454545453*xyc1^11.0+0.625*xyc1^8.0+0.7142857142857143*xyc1^7.0+1.25*xyc1^4.0+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "50.0*xyc1^9.0+35.0*xyc1^6.0+30.0*xyc1^5.0+15.0*xyc1^2.0";
        EqnSolArrayDiffAlone[2] = "50.0*9.0*xyc1^8.0+35.0*6.0*xyc1^5.0+30.0*5.0*xyc1^4.0+15.0*2.0*xyc1^1.0";
        EqnSolArrayMap2[2] = "450.0*xyc1^8.0+210.0*xyc1^5.0+150.0*xyc1^4.0+30.0*xyc1";
        EqnSolArrayMap3[2]="0.45454545454545453*xyc1^11.0+0.625*xyc1^8.0+0.7142857142857143*xyc1^7.0+1.25*xyc1";
        EqnSolArrayIntegrAlone[2]="0.45454545454545453/12.0*xyc1^12.0+0.625/9.0*xyc1^9.0+0.7142857142857143/8.0*xyc1^8.0+1.25/5.0*xyc1^5.0+125.0/2.0*xyc1^2.0";
        EqnSolArrayMap4[2]="0.03787878787878788*xyc1^12.0+0.06944444444444445*xyc1^9.0+0.08928571428571429*xyc1^8.0+0.25*xyc1^5.0+62.5*xyc1^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "450.0*xyc1^8.0+210.0*xyc1^5.0+150.0*xyc1^4.0+30.0*xyc1";
        EqnSolArrayDiffAlone[3] = "450.0*8.0*xyc1^7.0+210.0*5.0*xyc1^4.0+150.0*4.0*xyc1^3.0+30.0";
        EqnSolArrayMap2[3] = "3600.0*xyc1^7.0+1050.0*xyc1^4.0+600.0*xyc1^3.0+30.0";
        EqnSolArrayMap3[3]="0.03787878787878788*xyc1^12.0+0.06944444444444445*xyc1^9.0+0.08928571428571429*xyc1^8.0+0.25*xyc1^5.0+62.5*xyc1^2.0";
        EqnSolArrayIntegrAlone[3]="0.03787878787878788/13.0*xyc1^13.0+0.06944444444444445/10.0*xyc1^10.0+0.08928571428571429/9.0*xyc1^9.0+0.25/6.0*xyc1^6.0+62.5/3.0*xyc1^3.0";
        EqnSolArrayMap4[3]="0.002913752913752914*xyc1^13.0+0.006944444444444445*xyc1^10.0+0.00992063492063492*xyc1^9.0+0.041666666666666664*xyc1^6.0+20.833333333333332*xyc1^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "3600.0*xyc1^7.0+1050.0*xyc1^4.0+600.0*xyc1^3.0+30.0";
        EqnSolArrayDiffAlone[4] = "3600.0*7.0*xyc1^6.0+1050.0*4.0*xyc1^3.0+600.0*3.0*xyc1^2.0+0.0";
        EqnSolArrayMap2[4] = "25200.0*xyc1^6.0+4200.0*xyc1^3.0+1800.0*xyc1^2.0";
        EqnSolArrayMap3[4]="0.002913752913752914*xyc1^13.0+0.006944444444444445*xyc1^10.0+0.00992063492063492*xyc1^9.0+0.041666666666666664*xyc1^6.0+20.833333333333332*xyc1^3.0";
        EqnSolArrayIntegrAlone[4]="0.002913752913752914/14.0*xyc1^14.0+0.006944444444444445/11.0*xyc1^11.0+0.00992063492063492/10.0*xyc1^10.0+0.041666666666666664/7.0*xyc1^7.0+20.833333333333332/4.0*xyc1^4.0";
        EqnSolArrayMap4[4]="2.0812520812520815E-4*xyc1^14.0+6.313131313131314E-4*xyc1^11.0+9.92063492063492E-4*xyc1^10.0+0.005952380952380952*xyc1^7.0+5.208333333333333*xyc1^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1017() {
        //Test Case 1017
        TestCase = 1017;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*2^3*+5*+xyc1^8/xyc1^2*2^3 + 1.0*2^3*+5*+xyc1^5/xyc1^2*2^3 + 1.0*2^3*+5*+xyc1^4/xyc1^2*2^3 + 1.0*+5*2^3*+xyc1/xyc1^2*2^3 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "320.0*xyc1^6.0+320.0*xyc1^3.0+320.0*xyc1^2.0+320.0/xyc1+125.0";
        EqnSolArrayDiffAlone[1] = "320.0*6.0*xyc1^5.0+320.0*3.0*xyc1^2.0+320.0*2.0*xyc1^1.0-320.0/xyc1^2.0+0.0";
        EqnSolArrayMap2[1] = "1920.0*xyc1^5.0+960.0*xyc1^2.0+640.0*xyc1-320.0/xyc1^2.0";
        EqnSolArrayMap3[1]="320.0*xyc1^6.0+320.0*xyc1^3.0+320.0*xyc1^2.0+320.0/xyc1+125.0";
        EqnSolArrayIntegrAlone[1]="320.0/7.0*xyc1^7.0+320.0/4.0*xyc1^4.0+320.0/3.0*xyc1^3.0+320.0*ln(xyc1)+125.0*xyc1";
        EqnSolArrayMap4[1]="45.714285714285715*xyc1^7.0+80.0*xyc1^4.0+106.66666666666667*xyc1^3.0+320.0*ln(xyc1)+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "1920.0*xyc1^5.0+960.0*xyc1^2.0+640.0*xyc1-320.0/xyc1^2.0";
        EqnSolArrayDiffAlone[2] = "1920.0*5.0*xyc1^4.0+960.0*2.0*xyc1^1.0+640.0-320.0*-2.0/xyc1^3.0";
        EqnSolArrayMap2[2] = "9600.0*xyc1^4.0+1920.0*xyc1+640.0+640.0/xyc1^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "9600.0*xyc1^4.0+1920.0*xyc1+640.0+640.0/xyc1^3.0";
        EqnSolArrayDiffAlone[3] = "9600.0*4.0*xyc1^3.0+1920.0+0.0+640.0*-3.0/xyc1^4.0";
        EqnSolArrayMap2[3] = "38400.0*xyc1^3.0+1920.0-1920.0/xyc1^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "38400.0*xyc1^3.0+1920.0-1920.0/xyc1^4.0";
        EqnSolArrayDiffAlone[4] = "38400.0*3.0*xyc1^2.0+0.0-1920.0*-4.0/xyc1^5.0";
        EqnSolArrayMap2[4] = "115200.0*xyc1^2.0+7680.0/xyc1^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1018() {
        //Test Case 1018
        TestCase = 1018;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^0 + 1.0*+5*+xyc1^5/xyc1^0 + 1.0*+5*+xyc1^4/xyc1^0 + 1.0*+5*+xyc1/xyc1^0 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0/xyc1+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*8.0*xyc1^7.0+5.0*5.0*xyc1^4.0+5.0*4.0*xyc1^3.0-5.0/xyc1^2.0+0.0";
        EqnSolArrayMap2[1] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0-5.0/xyc1^2.0";
        EqnSolArrayMap3[1]="5.0*xyc1^8.0+5.0*xyc1^5.0+5.0*xyc1^4.0+5.0/xyc1+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/9.0*xyc1^9.0+5.0/6.0*xyc1^6.0+5.0/5.0*xyc1^5.0+5.0*ln(xyc1)+125.0*xyc1";
        EqnSolArrayMap4[1]="0.5555555555555556*xyc1^9.0+0.8333333333333334*xyc1^6.0+xyc1^5.0+5.0*ln(xyc1)+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "40.0*xyc1^7.0+25.0*xyc1^4.0+20.0*xyc1^3.0-5.0/xyc1^2.0";
        EqnSolArrayDiffAlone[2] = "40.0*7.0*xyc1^6.0+25.0*4.0*xyc1^3.0+20.0*3.0*xyc1^2.0-5.0*-2.0/xyc1^3.0";
        EqnSolArrayMap2[2] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0+10.0/xyc1^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "280.0*xyc1^6.0+100.0*xyc1^3.0+60.0*xyc1^2.0+10.0/xyc1^3.0";
        EqnSolArrayDiffAlone[3] = "280.0*6.0*xyc1^5.0+100.0*3.0*xyc1^2.0+60.0*2.0*xyc1^1.0+10.0*-3.0/xyc1^4.0";
        EqnSolArrayMap2[3] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1-30.0/xyc1^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "1680.0*xyc1^5.0+300.0*xyc1^2.0+120.0*xyc1-30.0/xyc1^4.0";
        EqnSolArrayDiffAlone[4] = "1680.0*5.0*xyc1^4.0+300.0*2.0*xyc1^1.0+120.0-30.0*-4.0/xyc1^5.0";
        EqnSolArrayMap2[4] = "8400.0*xyc1^4.0+600.0*xyc1+120.0+120.0/xyc1^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1019() {
        //Test Case 1019
        TestCase = 1019;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^-1 + 1.0*+5*+xyc1^5/xyc1^-1 + 1.0*+5*+xyc1^4/xyc1^-1 + 1.0*+5*+xyc1/xyc1^-1 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^9.0+5.0*xyc1^6.0+5.0*xyc1^5.0+5.0*xyc1^2.0+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*9.0*xyc1^8.0+5.0*6.0*xyc1^5.0+5.0*5.0*xyc1^4.0+5.0*2.0*xyc1^1.0+0.0";
        EqnSolArrayMap2[1] = "45.0*xyc1^8.0+30.0*xyc1^5.0+25.0*xyc1^4.0+10.0*xyc1";
        EqnSolArrayMap3[1]="5.0*xyc1^9.0+5.0*xyc1^6.0+5.0*xyc1^5.0+5.0*xyc1^2.0+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/10.0*xyc1^10.0+5.0/7.0*xyc1^7.0+5.0/6.0*xyc1^6.0+5.0/3.0*xyc1^3.0+125.0*xyc1";
        EqnSolArrayMap4[1]="0.5*xyc1^10.0+0.7142857142857143*xyc1^7.0+0.8333333333333334*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "45.0*xyc1^8.0+30.0*xyc1^5.0+25.0*xyc1^4.0+10.0*xyc1";
        EqnSolArrayDiffAlone[2] = "45.0*8.0*xyc1^7.0+30.0*5.0*xyc1^4.0+25.0*4.0*xyc1^3.0+10.0";
        EqnSolArrayMap2[2] = "360.0*xyc1^7.0+150.0*xyc1^4.0+100.0*xyc1^3.0+10.0";
        EqnSolArrayMap3[2]="0.5*xyc1^10.0+0.7142857142857143*xyc1^7.0+0.8333333333333334*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1";
        EqnSolArrayIntegrAlone[2]="0.5/11.0*xyc1^11.0+0.7142857142857143/8.0*xyc1^8.0+0.8333333333333334/7.0*xyc1^7.0+1.6666666666666667/4.0*xyc1^4.0+125.0/2.0*xyc1^2.0";
        EqnSolArrayMap4[2]="0.045454545454545456*xyc1^11.0+0.08928571428571429*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.4166666666666667*xyc1^4.0+62.5*xyc1^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "360.0*xyc1^7.0+150.0*xyc1^4.0+100.0*xyc1^3.0+10.0";
        EqnSolArrayDiffAlone[3] = "360.0*7.0*xyc1^6.0+150.0*4.0*xyc1^3.0+100.0*3.0*xyc1^2.0+0.0";
        EqnSolArrayMap2[3] = "2520.0*xyc1^6.0+600.0*xyc1^3.0+300.0*xyc1^2.0";
        EqnSolArrayMap3[3]="0.045454545454545456*xyc1^11.0+0.08928571428571429*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.4166666666666667*xyc1^4.0+62.5*xyc1^2.0";
        EqnSolArrayIntegrAlone[3]="0.045454545454545456/12.0*xyc1^12.0+0.08928571428571429/9.0*xyc1^9.0+0.11904761904761905/8.0*xyc1^8.0+0.4166666666666667/5.0*xyc1^5.0+62.5/3.0*xyc1^3.0";
        EqnSolArrayMap4[3]="0.003787878787878788*xyc1^12.0+0.00992063492063492*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.08333333333333334*xyc1^5.0+20.833333333333332*xyc1^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "2520.0*xyc1^6.0+600.0*xyc1^3.0+300.0*xyc1^2.0";
        EqnSolArrayDiffAlone[4] = "2520.0*6.0*xyc1^5.0+600.0*3.0*xyc1^2.0+300.0*2.0*xyc1^1.0";
        EqnSolArrayMap2[4] = "15120.0*xyc1^5.0+1800.0*xyc1^2.0+600.0*xyc1";
        EqnSolArrayMap3[4]="0.003787878787878788*xyc1^12.0+0.00992063492063492*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.08333333333333334*xyc1^5.0+20.833333333333332*xyc1^3.0";
        EqnSolArrayIntegrAlone[4]="0.003787878787878788/13.0*xyc1^13.0+0.00992063492063492/10.0*xyc1^10.0+0.014880952380952382/9.0*xyc1^9.0+0.08333333333333334/6.0*xyc1^6.0+20.833333333333332/4.0*xyc1^4.0";
        EqnSolArrayMap4[4]="2.913752913752914E-4*xyc1^13.0+9.92063492063492E-4*xyc1^10.0+0.0016534391534391536*xyc1^9.0+0.01388888888888889*xyc1^6.0+5.208333333333333*xyc1^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1020() {
        //Test Case 1020
        TestCase = 1020;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^1 + 1.0*+5*+xyc1^5/xyc1^1 + 1.0*+5*+xyc1^4/xyc1^1 + 1.0*+5*+xyc1/xyc1^1 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 2;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^7.0+5.0*xyc1^4.0+5.0*xyc1^3.0+5.0/xyc1+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+5.0*3.0*xyc1^2.0-5.0/xyc1^2.0+0.0";
        EqnSolArrayMap2[1] = "35.0*xyc1^6.0+20.0*xyc1^3.0+15.0*xyc1^2.0-5.0/xyc1^2.0";
        EqnSolArrayMap3[1]="5.0*xyc1^7.0+5.0*xyc1^4.0+5.0*xyc1^3.0+5.0/xyc1+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/8.0*xyc1^8.0+5.0/5.0*xyc1^5.0+5.0/4.0*xyc1^4.0+5.0*ln(xyc1)+125.0*xyc1";
        EqnSolArrayMap4[1]="0.625*xyc1^8.0+xyc1^5.0+1.25*xyc1^4.0+5.0*ln(xyc1)+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "35.0*xyc1^6.0+20.0*xyc1^3.0+15.0*xyc1^2.0-5.0/xyc1^2.0";
        EqnSolArrayDiffAlone[2] = "35.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+15.0*2.0*xyc1^1.0-5.0*-2.0/xyc1^3.0";
        EqnSolArrayMap2[2] = "210.0*xyc1^5.0+60.0*xyc1^2.0+30.0*xyc1+10.0/xyc1^3.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "210.0*xyc1^5.0+60.0*xyc1^2.0+30.0*xyc1+10.0/xyc1^3.0";
        EqnSolArrayDiffAlone[3] = "210.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+30.0+10.0*-3.0/xyc1^4.0";
        EqnSolArrayMap2[3] = "1050.0*xyc1^4.0+120.0*xyc1+30.0-30.0/xyc1^4.0";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "1050.0*xyc1^4.0+120.0*xyc1+30.0-30.0/xyc1^4.0";
        EqnSolArrayDiffAlone[4] = "1050.0*4.0*xyc1^3.0+120.0+0.0-30.0*-4.0/xyc1^5.0";
        EqnSolArrayMap2[4] = "4200.0*xyc1^3.0+120.0+120.0/xyc1^5.0";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1021() {
        //Test Case 1021
        TestCase = 1021;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "1.0*+5*+xyc1^8/xyc1^-4 + 1.0*+5*+xyc1^5/xyc1^-3 + 1.0*+5*+xyc1^4/xyc1^-7 + 1.0*+5*+xyc1/xyc1^-9 + 1.0*+5*25";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "xyc1";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "5.0*xyc1^12.0+5.0*xyc1^8.0+5.0*xyc1^11.0+5.0*xyc1^10.0+125.0";
        EqnSolArrayDiffAlone[1] = "5.0*12.0*xyc1^11.0+5.0*8.0*xyc1^7.0+5.0*11.0*xyc1^10.0+5.0*10.0*xyc1^9.0+0.0";
        EqnSolArrayMap2[1] = "60.0*xyc1^11.0+40.0*xyc1^7.0+55.0*xyc1^10.0+50.0*xyc1^9.0";
        EqnSolArrayMap3[1]="5.0*xyc1^12.0+5.0*xyc1^8.0+5.0*xyc1^11.0+5.0*xyc1^10.0+125.0";
        EqnSolArrayIntegrAlone[1]="5.0/13.0*xyc1^13.0+5.0/9.0*xyc1^9.0+5.0/12.0*xyc1^12.0+5.0/11.0*xyc1^11.0+125.0*xyc1";
        EqnSolArrayMap4[1]="0.38461538461538464*xyc1^13.0+0.5555555555555556*xyc1^9.0+0.4166666666666667*xyc1^12.0+0.45454545454545453*xyc1^11.0+125.0*xyc1";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "60.0*xyc1^11.0+40.0*xyc1^7.0+55.0*xyc1^10.0+50.0*xyc1^9.0";
        EqnSolArrayDiffAlone[2] = "60.0*11.0*xyc1^10.0+40.0*7.0*xyc1^6.0+55.0*10.0*xyc1^9.0+50.0*9.0*xyc1^8.0";
        EqnSolArrayMap2[2] = "660.0*xyc1^10.0+280.0*xyc1^6.0+550.0*xyc1^9.0+450.0*xyc1^8.0";
        EqnSolArrayMap3[2]="0.38461538461538464*xyc1^13.0+0.5555555555555556*xyc1^9.0+0.4166666666666667*xyc1^12.0+0.45454545454545453*xyc1^11.0+125.0*xyc1";
        EqnSolArrayIntegrAlone[2]="0.38461538461538464/14.0*xyc1^14.0+0.5555555555555556/10.0*xyc1^10.0+0.4166666666666667/13.0*xyc1^13.0+0.45454545454545453/12.0*xyc1^12.0+125.0/2.0*xyc1^2.0";
        EqnSolArrayMap4[2]="0.027472527472527476*xyc1^14.0+0.05555555555555556*xyc1^10.0+0.032051282051282055*xyc1^13.0+0.03787878787878788*xyc1^12.0+62.5*xyc1^2.0";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "660.0*xyc1^10.0+280.0*xyc1^6.0+550.0*xyc1^9.0+450.0*xyc1^8.0";
        EqnSolArrayDiffAlone[3] = "660.0*10.0*xyc1^9.0+280.0*6.0*xyc1^5.0+550.0*9.0*xyc1^8.0+450.0*8.0*xyc1^7.0";
        EqnSolArrayMap2[3] = "6600.0*xyc1^9.0+1680.0*xyc1^5.0+4950.0*xyc1^8.0+3600.0*xyc1^7.0";
        EqnSolArrayMap3[3]="0.027472527472527476*xyc1^14.0+0.05555555555555556*xyc1^10.0+0.032051282051282055*xyc1^13.0+0.03787878787878788*xyc1^12.0+62.5*xyc1^2.0";
        EqnSolArrayIntegrAlone[3]="0.027472527472527476/15.0*xyc1^15.0+0.05555555555555556/11.0*xyc1^11.0+0.032051282051282055/14.0*xyc1^14.0+0.03787878787878788/13.0*xyc1^13.0+62.5/3.0*xyc1^3.0";
        EqnSolArrayMap4[3]="0.0018315018315018317*xyc1^15.0+0.005050505050505051*xyc1^11.0+0.0022893772893772895*xyc1^14.0+0.002913752913752914*xyc1^13.0+20.833333333333332*xyc1^3.0";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "6600.0*xyc1^9.0+1680.0*xyc1^5.0+4950.0*xyc1^8.0+3600.0*xyc1^7.0";
        EqnSolArrayDiffAlone[4] = "6600.0*9.0*xyc1^8.0+1680.0*5.0*xyc1^4.0+4950.0*8.0*xyc1^7.0+3600.0*7.0*xyc1^6.0";
        EqnSolArrayMap2[4] = "59400.0*xyc1^8.0+8400.0*xyc1^4.0+39600.0*xyc1^7.0+25200.0*xyc1^6.0";
        EqnSolArrayMap3[4]="0.0018315018315018317*xyc1^15.0+0.005050505050505051*xyc1^11.0+0.0022893772893772895*xyc1^14.0+0.002913752913752914*xyc1^13.0+20.833333333333332*xyc1^3.0";
        EqnSolArrayIntegrAlone[4]="0.0018315018315018317/16.0*xyc1^16.0+0.005050505050505051/12.0*xyc1^12.0+0.0022893772893772895/15.0*xyc1^15.0+0.002913752913752914/14.0*xyc1^14.0+20.833333333333332/4.0*xyc1^4.0";
        EqnSolArrayMap4[4]="1.1446886446886448E-4*xyc1^16.0+4.208754208754209E-4*xyc1^12.0+1.5262515262515263E-4*xyc1^15.0+2.0812520812520815E-4*xyc1^14.0+5.208333333333333*xyc1^4.0";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1022() {
        //Test Case 1022
        TestCase = 1022;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "Xn^2+2*Xn+4";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "Xn";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "Xn^2.0+2.0*Xn";
        EqnSolArrayDiffAlone[1] = "2.0*Xn^1.0+2.0";
        EqnSolArrayMap2[1] = "2.0*Xn+2.0";
        EqnSolArrayMap3[1]="";
        EqnSolArrayIntegrAlone[1]="";
        EqnSolArrayMap4[1]="";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "2.0*Xn+2.0";
        EqnSolArrayDiffAlone[2] = "2.0+0.0";
        EqnSolArrayMap2[2] = "2.0";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "2.0";
        EqnSolArrayDiffAlone[3] = "0.0";
        EqnSolArrayMap2[3] = "";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "";
        EqnSolArrayDiffAlone[4] = "";
        EqnSolArrayMap2[4] = "";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1023() {
        //Test Case 1023
        TestCase = 1023;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "y^0.5-2.0";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "y";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "y^0.5-2.0";
        EqnSolArrayDiffAlone[1] = "0.5*y^-0.5-0.0";
        EqnSolArrayMap2[1] = "0.5*y^-0.5";
        EqnSolArrayMap3[1]="";
        EqnSolArrayIntegrAlone[1]="";
        EqnSolArrayMap4[1]="";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "0.5*y^-0.5";
        EqnSolArrayDiffAlone[2] = "0.5*-0.5*y^-1.5";
        EqnSolArrayMap2[2] = "-0.25*y^-1.5";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "";
        EqnSolArrayDiffAlone[3] = "";
        EqnSolArrayMap2[3] = "";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "";
        EqnSolArrayDiffAlone[4] = "";
        EqnSolArrayMap2[4] = "";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
      
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
    public static void TestCaseDiff1024() {
        //Test Case 1024
        TestCase = 1024;
        EqnRegressionDiffStatus = "All Green Including Values";
        EqnRegressionIntegreStatus = "All Green Including Values";        
        EqnUnderTest = "y^(1/2)-2.0";
        TestCaseName = "Complex: Coefficients:+5";
        TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        DiffWithRespTo = "y";
        MaxDiffOrder = 14;
        MaxIntegreOrder = 14;
        
        EqnSolArrayMap1[1] = "";
        EqnSolArrayDiffAlone[1] = "";
        EqnSolArrayMap2[1] = "";
        EqnSolArrayMap3[1]="";
        EqnSolArrayIntegrAlone[1]="";
        EqnSolArrayMap4[1]="";
        EqnSolOrder[1] = 1;

        EqnSolArrayMap1[2] = "";
        EqnSolArrayDiffAlone[2] = "";
        EqnSolArrayMap2[2] = "";
        EqnSolArrayMap3[2]="";
        EqnSolArrayIntegrAlone[2]="";
        EqnSolArrayMap4[2]="";
        EqnSolOrder[2] = 2;

        EqnSolArrayMap1[3] = "";
        EqnSolArrayDiffAlone[3] = "";
        EqnSolArrayMap2[3] = "";
        EqnSolArrayMap3[3]="";
        EqnSolArrayIntegrAlone[3]="";
        EqnSolArrayMap4[3]="";
        EqnSolOrder[3] = 3;

        EqnSolArrayMap1[4] = "";
        EqnSolArrayDiffAlone[4] = "";
        EqnSolArrayMap2[4] = "";
        EqnSolArrayMap3[4]="";
        EqnSolArrayIntegrAlone[4]="";
        EqnSolArrayMap4[4]="";
        EqnSolOrder[4] = 4;
      
        
        EqnSolArrayMap1[5] = "";
        EqnSolArrayDiffAlone[5] = "";
        EqnSolArrayMap2[5] = "";
        EqnSolArrayMap3[5]="";
        EqnSolArrayIntegrAlone[5]="";
        EqnSolArrayMap4[5]="";
        EqnSolOrder[5] = 5;

        EqnSolArrayMap1[6] = "";
        EqnSolArrayDiffAlone[6] = "";
        EqnSolArrayMap2[6] = "";
        EqnSolArrayMap3[6]="";
        EqnSolArrayIntegrAlone[6]="";
        EqnSolArrayMap4[6]="";
        EqnSolOrder[6] = 6;

        EqnSolArrayMap1[7] = "";
        EqnSolArrayDiffAlone[7] = "";
        EqnSolArrayMap2[7] = "";
        EqnSolArrayMap3[7]="";
        EqnSolArrayIntegrAlone[7]="";
        EqnSolArrayMap4[7]="";
        EqnSolOrder[7] = 7;

        EqnSolArrayMap1[8] = "";
        EqnSolArrayDiffAlone[8] = "";
        EqnSolArrayMap2[8] = "";
        EqnSolOrder[8] = 8;

        EqnSolArrayMap1[9] = "";
        EqnSolArrayDiffAlone[9] = "";
        EqnSolArrayMap2[9] = "";
        EqnSolOrder[9] = 9;

        EqnSolArrayMap1[10] = "";
        EqnSolArrayDiffAlone[10] = "";
        EqnSolArrayMap2[10] = "";
        EqnSolOrder[10] = 10;

        EqnSolArrayMap1[11] = "";
        EqnSolArrayDiffAlone[11] = "";
        EqnSolArrayMap2[11] = "";
        EqnSolOrder[11] = 11;

        EqnSolArrayMap1[12] = "";
        EqnSolArrayDiffAlone[12] = "";
        EqnSolArrayMap2[12] = "";
        EqnSolOrder[12] = 12;

        EqnSolArrayMap1[13] = "";
        EqnSolArrayDiffAlone[13] = "";
        EqnSolArrayMap2[13] = "";
        EqnSolOrder[13] = 13;

        EqnSolArrayMap1[14] = "";
        EqnSolArrayDiffAlone[14] = "";
        EqnSolArrayMap2[14] = "";
        EqnSolOrder[14] = 14;
    }
}
