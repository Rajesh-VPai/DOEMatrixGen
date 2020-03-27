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
public class TestHarnessUnitTest {
    //UnitTestCases Test Cases
    public static void UnitTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(TestHarness.IntegralGap(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(TestHarness.DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else {switch (j) {
            //Unstructured Testing
             case 5019:
                TestCaseDiff5019();
                break;
             case 5020:
                TestCaseDiff5020();
                break;
             case 5021:
                TestCaseDiff5021();
                break;
            case 5022:
                TestCaseDiff5022();
                break;
            case 5023:
                TestCaseDiff5023();
                break;
            case 5024:
                TestCaseDiff5024();
                break;
            case 5025:
                TestCaseDiff5025();
                break;
            case 5026:
                TestCaseDiff5026();
                break;
            //Palindromic String-Equation Test
            case 5027:
                TestCaseDiff5027();
                break;
            case 5028:
                TestCaseDiff5028();
                break;
            case 5029:
                TestCaseDiff5029();
                break;
            case 5030:
                TestCaseDiff5030();
                break;
            //Palindromic String-Equation Test-Rearranged
            case 5031:
                TestCaseDiff5031();
                break;
            case 5032:
                TestCaseDiff5032();
                break;
            //Test for LookAheadFuncMultDiv
            //Straight Numbers Leading + Trailing Co-eff :(LookAheadFuncMultDiv & ConstExprn)
            case 5033:
                TestCaseDiff5033();
                break;
            case 5034:
                TestCaseDiff5034();
                break;
            case 5035:
                TestCaseDiff5035();
                //System.out.println(ConsoleColors.BLUE + "Bypassing Test Case 35 :Infinite Loop Usage.EqnUnderTest=" + Usage.EqnUnderTest + ConsoleColors.RESET);
                break;
            case 5036:
                TestCaseDiff5036();
                break;
            case 5037:
                TestCaseDiff5037();
                break;
            case 5038:
                TestCaseDiff5038();
                break;
            case 5039:
                TestCaseDiff5039();
                break;
            case 5040:
                TestCaseDiff5040();
                break;
            case 5041:
                TestCaseDiff5041();
                break;
            case 5042:
                TestCaseDiff5042();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Unit TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
        }
    }
    /* **************************************** */
    //Structured Testing
    /* **************************************** */
    public static void TestCaseDiff5019() {
        //Only Trailing Constant
        //Test Case 5019
        Usage.TestCase = 5019;
        Usage.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnUnderTest = "x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0";
        Usage.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        Usage.TestCaseMatrixID="Unit TestCase";
        
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
    public static void TestCaseDiff5020() {
        //Test Case 5020
        Usage.TestCase = 5020;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        Usage.EqnUnderTest = "9.0*xyc1^9.0 + 6.0*xyc1^6.0 + 5.0*xyc1^5.0 + 2.0*xyc1^2.0 + 25.0*xyc1 + 50.0";
        Usage.TestCaseName = "Standard:Independent Constant:Basic Equation:Divide Operator";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "xyc1";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "9.0*xyc1^9.0+6.0*xyc1^6.0+5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1+50.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*9.0*xyc1^8.0+6.0*6.0*xyc1^5.0+5.0*5.0*xyc1^4.0+2.0*2.0*xyc1^1.0+25.0+0.0";
        Usage.EqnSolArrayMap2[1] = "81.0*xyc1^8.0+36.0*xyc1^5.0+25.0*xyc1^4.0+4.0*xyc1+25.0";
        Usage.EqnSolArrayMap3[1]=  "9.0*xyc1^9.0+6.0*xyc1^6.0+5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1+50.0";
        Usage.EqnSolArrayIntegrAlone[1]="9.0/10.0*xyc1^10.0+6.0/7.0*xyc1^7.0+5.0/6.0*xyc1^6.0+2.0/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0+50.0*xyc1";
        Usage.EqnSolArrayMap4[1]="0.9*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.8333333333333334*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0+50.0*xyc1";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "81.0*xyc1^8.0+36.0*xyc1^5.0+25.0*xyc1^4.0+4.0*xyc1+25.0";
        Usage.EqnSolArrayDiffAlone[2] = "81.0*8.0*xyc1^7.0+36.0*5.0*xyc1^4.0+25.0*4.0*xyc1^3.0+4.0+0.0";
        Usage.EqnSolArrayMap2[2] = "648.0*xyc1^7.0+180.0*xyc1^4.0+100.0*xyc1^3.0+4.0";
        Usage.EqnSolArrayMap3[2]="0.9*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.8333333333333334*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0+50.0*xyc1";
        Usage.EqnSolArrayIntegrAlone[2]="0.9/11.0*xyc1^11.0+0.8571428571428571/8.0*xyc1^8.0+0.8333333333333334/7.0*xyc1^7.0+0.6666666666666666/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0+50.0/2.0*xyc1^2.0";
        Usage.EqnSolArrayMap4[2]="0.08181818181818182*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0+25.0*xyc1^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "648.0*xyc1^7.0+180.0*xyc1^4.0+100.0*xyc1^3.0+4.0";
        Usage.EqnSolArrayDiffAlone[3] = "648.0*7.0*xyc1^6.0+180.0*4.0*xyc1^3.0+100.0*3.0*xyc1^2.0+0.0";
        Usage.EqnSolArrayMap2[3] = "4536.0*xyc1^6.0+720.0*xyc1^3.0+300.0*xyc1^2.0";
        Usage.EqnSolArrayMap3[3]="0.08181818181818182*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0+25.0*xyc1^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.08181818181818182/12.0*xyc1^12.0+0.10714285714285714/9.0*xyc1^9.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0+25.0/3.0*xyc1^3.0";
        Usage.EqnSolArrayMap4[3]="0.006818181818181818*xyc1^12.0+0.011904761904761904*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.03333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0+8.333333333333334*xyc1^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "4536.0*xyc1^6.0+720.0*xyc1^3.0+300.0*xyc1^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "4536.0*6.0*xyc1^5.0+720.0*3.0*xyc1^2.0+300.0*2.0*xyc1^1.0";
        Usage.EqnSolArrayMap2[4] = "27216.0*xyc1^5.0+2160.0*xyc1^2.0+600.0*xyc1";
        Usage.EqnSolArrayMap3[4]="0.006818181818181818*xyc1^12.0+0.011904761904761904*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.03333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0+8.333333333333334*xyc1^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.006818181818181818/13.0*xyc1^13.0+0.011904761904761904/10.0*xyc1^10.0+0.014880952380952382/9.0*xyc1^9.0+0.03333333333333333/6.0*xyc1^6.0+1.0416666666666667/5.0*xyc1^5.0+8.333333333333334/4.0*xyc1^4.0";
        Usage.EqnSolArrayMap4[4]="5.244755244755245E-4*xyc1^13.0+0.0011904761904761904*xyc1^10.0+0.0016534391534391536*xyc1^9.0+0.005555555555555556*xyc1^6.0+0.20833333333333334*xyc1^5.0+2.0833333333333335*xyc1^4.0";
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
    
    public static void TestCaseDiff5021() {
        //Test Case 5021
        Usage.TestCase = 5021;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^8 + x^5 + x^4 - x - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation";
        Usage.TestCaseMatrixID="Unit TestCase";
       
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
        
        Usage.EqnSolArrayIntegr_BeforeDiff[1]=Usage.EqnSolArrayIntegrAlone[1];
        Usage.EqnSolArrayDiff_AfterIntegr[1]=Usage.EqnSolArrayMap1[1];
        Usage.EqnSolArrayDiff_BeforeIntegr[1]=Usage.EqnSolArrayDiffAlone[1];
        Usage.EqnSolArrayIntegr_AfterDiff[1]=Usage.EqnSolArrayMap1[1];
        
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        
        
        Usage.EqnSolArrayIntegr_BeforeDiff[2]=Usage.EqnSolArrayIntegrAlone[2];
        Usage.EqnSolArrayDiff_AfterIntegr[2]=Usage.EqnSolArrayMap1[2];
        Usage.EqnSolArrayDiff_BeforeIntegr[2]=Usage.EqnSolArrayDiffAlone[2];
        Usage.EqnSolArrayIntegr_AfterDiff[2]=Usage.EqnSolArrayMap1[2];

        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        
        Usage.EqnSolArrayIntegr_BeforeDiff[3]=Usage.EqnSolArrayIntegrAlone[3];
        Usage.EqnSolArrayDiff_AfterIntegr[3]=Usage.EqnSolArrayMap1[3];
        Usage.EqnSolArrayDiff_BeforeIntegr[3]=Usage.EqnSolArrayDiffAlone[3];
        Usage.EqnSolArrayIntegr_AfterDiff[3]=Usage.EqnSolArrayMap1[3];

        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolArrayIntegr_BeforeDiff[4]=Usage.EqnSolArrayIntegrAlone[4];
        Usage.EqnSolArrayDiff_AfterIntegr[4]=Usage.EqnSolArrayMap1[4];
        Usage.EqnSolArrayDiff_BeforeIntegr[4]=Usage.EqnSolArrayDiffAlone[4];
        Usage.EqnSolArrayIntegr_AfterDiff[4]=Usage.EqnSolArrayMap1[4];

        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[5]=Usage.EqnSolArrayIntegrAlone[5];
        Usage.EqnSolArrayDiff_AfterIntegr[5]=Usage.EqnSolArrayMap1[5];
        Usage.EqnSolArrayDiff_BeforeIntegr[5]=Usage.EqnSolArrayDiffAlone[5];
        Usage.EqnSolArrayIntegr_AfterDiff[5]=Usage.EqnSolArrayMap1[5];

        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*x^2.0";
        Usage.EqnSolArrayMap3[6]="";
        Usage.EqnSolArrayIntegrAlone[6]="";
        Usage.EqnSolArrayMap4[6]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[6]=Usage.EqnSolArrayIntegrAlone[6];
        Usage.EqnSolArrayDiff_AfterIntegr[6]=Usage.EqnSolArrayMap1[6];
        Usage.EqnSolArrayDiff_BeforeIntegr[6]=Usage.EqnSolArrayDiffAlone[6];
        Usage.EqnSolArrayIntegr_AfterDiff[6]=Usage.EqnSolArrayMap1[6];

        Usage.EqnSolOrder[6] = 6;

        Usage.EqnSolArrayMap1[7] = "";
        Usage.EqnSolArrayDiffAlone[7] = "";
        Usage.EqnSolArrayMap2[7] = "";
        Usage.EqnSolArrayMap3[7]="";
        Usage.EqnSolArrayIntegrAlone[7]="";
        Usage.EqnSolArrayMap4[7]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[7]=Usage.EqnSolArrayIntegrAlone[7];
        Usage.EqnSolArrayDiff_AfterIntegr[7]=Usage.EqnSolArrayMap1[7];
        Usage.EqnSolArrayDiff_BeforeIntegr[7]=Usage.EqnSolArrayDiffAlone[7];
        Usage.EqnSolArrayIntegr_AfterDiff[7]=Usage.EqnSolArrayMap1[7];
        Usage.EqnSolOrder[7] = 7;

        Usage.EqnSolArrayMap1[8] = "";
        Usage.EqnSolArrayDiffAlone[8] = "";
        Usage.EqnSolArrayMap2[8] = "";
        Usage.EqnSolArrayIntegrAlone[8]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[8]=Usage.EqnSolArrayIntegrAlone[8];
        Usage.EqnSolArrayDiff_AfterIntegr[8]=Usage.EqnSolArrayMap1[8];
        Usage.EqnSolArrayDiff_BeforeIntegr[8]=Usage.EqnSolArrayDiffAlone[8];
        Usage.EqnSolArrayIntegr_AfterDiff[8]=Usage.EqnSolArrayMap1[8];
        Usage.EqnSolOrder[8] = 8;

        Usage.EqnSolArrayMap1[9] = "";
        Usage.EqnSolArrayDiffAlone[9] = "";
        Usage.EqnSolArrayMap2[9] = "";
        Usage.EqnSolArrayIntegrAlone[9]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[9]=Usage.EqnSolArrayIntegrAlone[9];
        Usage.EqnSolArrayDiff_AfterIntegr[9]=Usage.EqnSolArrayMap1[9];
        Usage.EqnSolArrayDiff_BeforeIntegr[9]=Usage.EqnSolArrayDiffAlone[9];
        Usage.EqnSolArrayIntegr_AfterDiff[9]=Usage.EqnSolArrayMap1[9];
        Usage.EqnSolOrder[9] = 9;

        Usage.EqnSolArrayMap1[10] = "";
        Usage.EqnSolArrayDiffAlone[10] = "";
        Usage.EqnSolArrayMap2[10] = "";
        Usage.EqnSolArrayIntegrAlone[10]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[10]=Usage.EqnSolArrayIntegrAlone[10];
        Usage.EqnSolArrayDiff_AfterIntegr[10]=Usage.EqnSolArrayMap1[10];
        Usage.EqnSolArrayDiff_BeforeIntegr[10]=Usage.EqnSolArrayDiffAlone[10];
        Usage.EqnSolArrayIntegr_AfterDiff[10]=Usage.EqnSolArrayMap1[10];
        Usage.EqnSolOrder[10] = 10;

        Usage.EqnSolArrayMap1[11] = "";
        Usage.EqnSolArrayDiffAlone[11] = "";
        Usage.EqnSolArrayMap2[11] = "";
        Usage.EqnSolArrayIntegrAlone[11]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[11]=Usage.EqnSolArrayIntegrAlone[11];
        Usage.EqnSolArrayDiff_AfterIntegr[11]=Usage.EqnSolArrayMap1[11];
        Usage.EqnSolArrayDiff_BeforeIntegr[11]=Usage.EqnSolArrayDiffAlone[11];
        Usage.EqnSolArrayIntegr_AfterDiff[11]=Usage.EqnSolArrayMap1[11];
        Usage.EqnSolOrder[11] = 11;

        Usage.EqnSolArrayMap1[12] = "";
        Usage.EqnSolArrayDiffAlone[12] = "";
        Usage.EqnSolArrayMap2[12] = "";
        Usage.EqnSolArrayIntegrAlone[12]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[12]=Usage.EqnSolArrayIntegrAlone[12];
        Usage.EqnSolArrayDiff_AfterIntegr[12]=Usage.EqnSolArrayMap1[12];
        Usage.EqnSolArrayDiff_BeforeIntegr[12]=Usage.EqnSolArrayDiffAlone[12];
        Usage.EqnSolArrayIntegr_AfterDiff[12]=Usage.EqnSolArrayMap1[12];
        Usage.EqnSolOrder[12] = 12;

        Usage.EqnSolArrayMap1[13] = "";
        Usage.EqnSolArrayDiffAlone[13] = "";
        Usage.EqnSolArrayMap2[13] = "";
        Usage.EqnSolArrayIntegrAlone[13]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[13]=Usage.EqnSolArrayIntegrAlone[13];
        Usage.EqnSolArrayDiff_AfterIntegr[13]=Usage.EqnSolArrayMap1[13];
        Usage.EqnSolArrayDiff_BeforeIntegr[13]=Usage.EqnSolArrayDiffAlone[13];
        Usage.EqnSolArrayIntegr_AfterDiff[13]=Usage.EqnSolArrayMap1[13];
        Usage.EqnSolOrder[13] = 13;

        Usage.EqnSolArrayMap1[14] = "";
        Usage.EqnSolArrayDiffAlone[14] = "";
        Usage.EqnSolArrayMap2[14] = "";
        Usage.EqnSolArrayIntegrAlone[14]="";
        Usage.EqnSolArrayIntegr_BeforeDiff[14]=Usage.EqnSolArrayIntegrAlone[14];
        Usage.EqnSolArrayDiff_AfterIntegr[14]=Usage.EqnSolArrayMap1[14];
        Usage.EqnSolArrayDiff_BeforeIntegr[14]=Usage.EqnSolArrayDiffAlone[14];
        Usage.EqnSolArrayIntegr_AfterDiff[14]=Usage.EqnSolArrayMap1[14];
        Usage.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff5022() {
        //Test Case 5022
        Usage.TestCase = 5022;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        Usage.EqnUnderTest = "1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
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

    public static void TestCaseDiff5023() {
        //Test Case 5023
        Usage.TestCase = 5023;
        Usage.EqnRegressionDiffStatus = "All Green Including Values(Except 6th Order Values) :Negative Exponent Dropped";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "x^-8 + x^-5 + x^-4 - x - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 3;
        
        Usage.EqnSolArrayMap1[1] = "x^-8.0+x^-5.0+x^-4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^-8.0+x^-5.0+x^-4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/-7.0*x^-7.0+1.0/-4.0*x^-4.0+1.0/-3.0*x^-3.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="-0.14285714285714285*x^-7.0-0.25*x^-4.0-0.3333333333333333*x^-3.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "-8.0*-9.0*x^-10.0-5.0*-6.0*x^-7.0-4.0*-5.0*x^-6.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^-10.0+30.0*x^-7.0+20.0*x^-6.0";
        Usage.EqnSolArrayMap3[2]="-0.14285714285714285*x^-7.0-0.25*x^-4.0-0.3333333333333333*x^-3.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-0.14285714285714285/-6.0*x^-6.0-0.25/-3.0*x^-3.0-0.3333333333333333/-2.0*x^-2.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.023809523809523808*x^-6.0+0.08333333333333333*x^-3.0+0.16666666666666666*x^-2.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^-10.0+30.0*x^-7.0+20.0*x^-6.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*-10.0*x^-11.0+30.0*-7.0*x^-8.0+20.0*-6.0*x^-7.0";
        Usage.EqnSolArrayMap2[3] = "-720.0*x^-11.0-210.0*x^-8.0-120.0*x^-7.0";
        Usage.EqnSolArrayMap3[3]="0.023809523809523808*x^-6.0+0.08333333333333333*x^-3.0+0.16666666666666666*x^-2.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.023809523809523808/-5.0*x^-5.0+0.08333333333333333/-2.0*x^-2.0+0.16666666666666666/-1.0*x^-1.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0047619047619047615*x^-5.0-0.041666666666666664*x^-2.0-0.16666666666666666*x^-1.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-720.0*x^-11.0-210.0*x^-8.0-120.0*x^-7.0";
        Usage.EqnSolArrayDiffAlone[4] = "-720.0*-11.0*x^-12.0-210.0*-8.0*x^-9.0-120.0*-7.0*x^-8.0";
        Usage.EqnSolArrayMap2[4] = "7920.0*x^-12.0+1680.0*x^-9.0+840.0*x^-8.0";
        Usage.EqnSolArrayMap3[4]="-0.0047619047619047615*x^-5.0-0.041666666666666664*x^-2.0-0.16666666666666666*x^-1.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-0.0047619047619047615/-4.0*x^-4.0-0.041666666666666664/-1.0*x^-1.0-0.16666666666666666/0.0*x^0.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "7920.0*x^-12.0+1680.0*x^-9.0+840.0*x^-8.0";
        Usage.EqnSolArrayDiffAlone[5] = "7920.0*-12.0*x^-13.0+1680.0*-9.0*x^-10.0+840.0*-8.0*x^-9.0";
        Usage.EqnSolArrayMap2[5] = "-95040.0*x^-13.0-15120.0*x^-10.0-6720.0*x^-9.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-95040.0*x^-13.0-15120.0*x^-10.0-6720.0*x^-9.0";
        Usage.EqnSolArrayDiffAlone[6] = "-95040.0*-13.0*x^-14.0-15120.0*-10.0*x^-11.0-6720.0*-9.0*x^-10.0";
        Usage.EqnSolArrayMap2[6] = "1235520.0*x^-14.0+151200.0*x^-11.0+60480.0*x^-10.0";
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

    public static void TestCaseDiff5024() {
        //Test Case 5024
        Usage.TestCase= 5024;
        Usage.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1/x^-8 + 1/x^-5 + 1/x^-4 - 25.0";
        Usage.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        Usage.TestCaseMatrixID="Complex:Unit TestCase:Mapping of -ve Exponent & Division";
        
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

    public static void TestCaseDiff5025() {
        //Test Case 5025
        Usage.TestCase = 5025;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0*x^8 + 1.0*x^5 + 1.0*x^4 - x - 25.0";
        Usage.TestCaseName = "Standard:Coefficients=1.0 Explicit:Basic Equation:Coefficients=1.0 Explicit";
        Usage.TestCaseMatrixID="Unit TestCase";
        
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
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
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

    public static void TestCaseDiff5026() {
        //Test Case 5026
        Usage.TestCase = 5026;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0*x^8 + 1.0*x^5 + 1.0*x^4 - 1.0*x - 25.0";
        Usage.TestCaseName = "Complex:Coefficients=1.0 Explicit:Basic Equation:Coefficients=1.0 Explicit";
        Usage.TestCaseMatrixID="Unit TestCase";
        
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
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
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

    //Palindromic String-Equation Test
    public static void TestCaseDiff5027() {
        //Palindromic String-Equation Test
        //Test Case 5027
        Usage.TestCase = 5027;
        Usage.EqnRegressionDiffStatus = "All Green Including Values Except 6th Order Onwards";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        Usage.EqnUnderTest = " -25.0+x+x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        //Usage.EqnUnderTest="-120.0-480.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.TestCaseName = "Standard:Palindromic String Equation:";
        Usage.TestCaseMatrixID="Unit TestCase";
        
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

    public static void TestCaseDiff5028() {
        //Test Case 5028
        Usage.TestCase = 5028;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        Usage.EqnUnderTest = " -25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - 1.0*x - 25.0";
        //Usage.EqnUnderTest = " 25.0 + 1.0*x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - 1.0*x - 25.0";
        Usage.TestCaseName = "Standard:Palindromic String Equation:Coefficient=1.0 Explicit";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-25.0+x-x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "-0.0+1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayMap3[1]="-25.0+x-x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="-25.0*x+1.0/2.0*x^2.0-1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="-25.0*x+0.5*x^2.0-0.2*x^5.0-0.16666666666666666*x^6.0-0.1111111111111111*x^9.0+0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0-4.0*3.0*x^2.0-5.0*4.0*x^3.0-8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="-25.0*x+0.5*x^2.0-0.2*x^5.0-0.16666666666666666*x^6.0-0.1111111111111111*x^9.0+0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0+0.5/3.0*x^3.0-0.2/6.0*x^6.0-0.16666666666666666/7.0*x^7.0-0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-12.5*x^2.0+0.16666666666666666*x^3.0-0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-12.0*2.0*x^1.0-20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "-24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayMap3[3]="-12.5*x^2.0+0.16666666666666666*x^3.0-0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0-0.03333333333333333/7.0*x^7.0-0.023809523809523808/8.0*x^8.0-0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "-24.0-60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[4] = "-24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0-0.0047619047619047615/8.0*x^8.0-0.002976190476190476/9.0*x^9.0-0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0+0.008333333333333333*x^5.0-5.952380952380952E-4*x^8.0-3.3068783068783067E-4*x^9.0-8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "-24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[5] = "0.0-120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[5] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0+";
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

    public static void TestCaseDiff5029() {
        //Test Case 5029
        Usage.TestCase = 5029;
        Usage.EqnRegressionDiffStatus = "All Green Including Values Except 5th Order Onwards";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x";
        Usage.TestCaseName = "Standard:Basic Equation: With Coefficient";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayDiffAlone[1] = "60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        Usage.EqnSolArrayMap2[1] = "120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayMap3[1]="60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        Usage.EqnSolArrayIntegrAlone[1]="60.0/3.0*x^3.0-336.0/6.0*x^6.0+336.0/6.0*x^6.0+60.0/3.0*x^3.0+24.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[1]="20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        Usage.EqnSolArrayDiffAlone[2] = "120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        Usage.EqnSolArrayMap2[2] = "120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.EqnSolArrayMap3[2]="20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[2]="20.0/4.0*x^4.0-56.0/7.0*x^7.0+56.0/7.0*x^7.0+20.0/4.0*x^4.0+12.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[2]="5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        Usage.EqnSolArrayDiffAlone[3] = "0.0-6720.0*3.0*x^2.0+6720.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[3] = "-20160.0*x^2.0+20160.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[3]="5.0/5.0*x^5.0-8.0/8.0*x^8.0+8.0/8.0*x^8.0+5.0/5.0*x^5.0+4.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[3]="x^5.0-x^8.0+x^8.0+x^5.0+x^4.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-20160.0*x^2.0+20160.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "-20160.0*2.0*x^1.0+20160.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "-40320.0*x+40320.0*x";
        Usage.EqnSolArrayMap3[4]="x^5.0-x^8.0+x^8.0+x^5.0+x^4.0";
        Usage.EqnSolArrayIntegrAlone[4]="1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        Usage.EqnSolArrayMap4[4]="0.16666666666666666*x^6.0-0.1111111111111111*x^9.0+0.1111111111111111*x^9.0+0.16666666666666666*x^6.0+0.2*x^5.0";
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

    public static void TestCaseDiff5030() {
        //Test Case 5030
        Usage.TestCase = 5030;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "40320.0*x +40320.0*x -25";
        Usage.TestCaseName = "Simple:Basic Equation: With Coefficient";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "40320.0*x+40320.0*x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "40320.0+40320.0-0.0";
        Usage.EqnSolArrayMap2[1] = "40320.0+40320.0";
        Usage.EqnSolArrayMap3[1]="40320.0*x+40320.0*x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="40320.0/2.0*x^2.0+40320.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="20160.0*x^2.0+20160.0*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40320.0+40320.0";
        Usage.EqnSolArrayDiffAlone[2] = "0.0+0.0";
        Usage.EqnSolArrayMap2[2] = "";
        Usage.EqnSolArrayMap3[2]="20160.0*x^2.0+20160.0*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="20160.0/3.0*x^3.0+20160.0/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="6720.0*x^3.0+6720.0*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "";
        Usage.EqnSolArrayDiffAlone[3] = "";
        Usage.EqnSolArrayMap2[3] = "";
        Usage.EqnSolArrayMap3[3]="6720.0*x^3.0+6720.0*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="6720.0/4.0*x^4.0+6720.0/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="1680.0*x^4.0+1680.0*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "";
        Usage.EqnSolArrayDiffAlone[4] = "";
        Usage.EqnSolArrayMap2[4] = "";
        Usage.EqnSolArrayMap3[4]="1680.0*x^4.0+1680.0*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="1680.0/5.0*x^5.0+1680.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="336.0*x^5.0+336.0*x^5.0-1.0416666666666667*x^4.0";
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

    //Palindromic String-Equation Test-Rearranged
    public static void TestCaseDiff5031() {
        //Palindromic String-Equation Test-Rearranged
        //Test Case 5031
        Usage.TestCase = 5031;
        Usage.EqnRegressionDiffStatus = "All Green Including Values Constant Dropped";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = " x^8 -x^8 + x^5 -x^5 + x^4 - x^4 + 25.0 + x - x - 25.0";
        Usage.TestCaseName = "Standard:Basic Equation: With Constants in the Middle";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0+25.0+x-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+0.0+1.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+1.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0+25.0+x-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0+25.0*x+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.2*x^5.0-0.2*x^5.0+25.0*x+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+1.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+4.0*3.0*x^2.0-4.0*3.0*x^2.0+0.0-0.0";
        Usage.EqnSolArrayMap2[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.2*x^5.0-0.2*x^5.0+25.0*x+0.5*x^2.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.2/6.0*x^6.0+25.0/2.0*x^2.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0+12.5*x^2.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+12.0*2.0*x^1.0-12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        Usage.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0+12.5*x^2.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0+12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0+4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+24.0-24.0";
        Usage.EqnSolArrayMap2[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        Usage.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0+4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0-0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0-0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.0047619047619047615/8.0*x^8.0+4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0-8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0-3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-5.952380952380952E-4*x^8.0+1.0416666666666667*x^4.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        Usage.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        Usage.EqnSolArrayMap2[5] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0+-";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0+";
        Usage.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0-6720.0*3.0*x^2.0+0.0-0.0";
        Usage.EqnSolArrayMap2[6] = "20160.0*x^2.0-20160.0*x^2.0-";
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

    public static void TestCaseDiff5032() {
        //Test Case 5032
        Usage.TestCase = 5032;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        Usage.TestCaseName = "Standard:Basic Equation:";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+4.0*3.0*x^2.0-4.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[1] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayMap3[1]="8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[1]="8.0/8.0*x^8.0-8.0/8.0*x^8.0+5.0/5.0*x^5.0-5.0/5.0*x^5.0+4.0/4.0*x^4.0-4.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[1]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+12.0*2.0*x^1.0-12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[2] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        Usage.EqnSolArrayMap3[2]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0";
        Usage.EqnSolArrayMap4[2]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.2*x^5.0-0.2*x^5.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        Usage.EqnSolArrayDiffAlone[3] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+24.0-24.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        Usage.EqnSolArrayMap3[3]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.2*x^5.0-0.2*x^5.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.2/6.0*x^6.0";
        Usage.EqnSolArrayMap4[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        Usage.EqnSolArrayMap2[4] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        Usage.EqnSolArrayMap3[4]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0";
        Usage.EqnSolArrayMap4[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        Usage.EqnSolArrayDiffAlone[5] = "6720.0*3.0*x^2.0-6720.0*3.0*x^2.0+0.0-0.0";
        Usage.EqnSolArrayMap2[5] = "20160.0*x^2.0-20160.0*x^2.0-";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "20160.0*x^2.0-20160.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[6] = "20160.0*2.0*x^1.0-20160.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[6] = "40320.0*x-40320.0*x";
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

    public static void TestCaseDiff5033() {
        //Leading & Trailing Constant
        //Test Case 5033
        Usage.TestCase = 5033;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = " 2*x^8*5 -3*x^8*6 + 4*x^5 -5*x^5*7 + 6*x^4*6 - 7*x^4*9 + 25.0 + 8*x*9 - 9*x*10 - 25.0";
        Usage.TestCaseName = "Standard:Basic Equation:With Leading and Trailing Coefficients";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "10.0*x^8.0-18.0*x^8.0+4.0*x^5.0-35.0*x^5.0+36.0*x^4.0-63.0*x^4.0+25.0+72.0*x-90.0*x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*8.0*x^7.0-18.0*8.0*x^7.0+4.0*5.0*x^4.0-35.0*5.0*x^4.0+36.0*4.0*x^3.0-63.0*4.0*x^3.0+0.0+72.0-90.0-0.0";
        Usage.EqnSolArrayMap2[1] = "80.0*x^7.0-144.0*x^7.0+20.0*x^4.0-175.0*x^4.0+144.0*x^3.0-252.0*x^3.0+72.0-90.0";
        Usage.EqnSolArrayMap3[1]="10.0*x^8.0-18.0*x^8.0+4.0*x^5.0-35.0*x^5.0+36.0*x^4.0-63.0*x^4.0+25.0+72.0*x-90.0*x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="10.0/9.0*x^9.0-18.0/9.0*x^9.0+4.0/6.0*x^6.0-35.0/6.0*x^6.0+36.0/5.0*x^5.0-63.0/5.0*x^5.0+25.0*x+72.0/2.0*x^2.0-90.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="1.1111111111111112*x^9.0-2.0*x^9.0+0.6666666666666666*x^6.0-5.833333333333333*x^6.0+7.2*x^5.0-12.6*x^5.0+25.0*x+36.0*x^2.0-45.0*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "80.0*x^7.0-144.0*x^7.0+20.0*x^4.0-175.0*x^4.0+144.0*x^3.0-252.0*x^3.0+72.0-90.0";
        Usage.EqnSolArrayDiffAlone[2] = "80.0*7.0*x^6.0-144.0*7.0*x^6.0+20.0*4.0*x^3.0-175.0*4.0*x^3.0+144.0*3.0*x^2.0-252.0*3.0*x^2.0+0.0-0.0";
        Usage.EqnSolArrayMap2[2] = "560.0*x^6.0-1008.0*x^6.0+80.0*x^3.0-700.0*x^3.0+432.0*x^2.0-756.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="1.1111111111111112*x^9.0-2.0*x^9.0+0.6666666666666666*x^6.0-5.833333333333333*x^6.0+7.2*x^5.0-12.6*x^5.0+25.0*x+36.0*x^2.0-45.0*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="1.1111111111111112/10.0*x^10.0-2.0/10.0*x^10.0+0.6666666666666666/7.0*x^7.0-5.833333333333333/7.0*x^7.0+7.2/6.0*x^6.0-12.6/6.0*x^6.0+25.0/2.0*x^2.0+36.0/3.0*x^3.0-45.0/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.11111111111111112*x^10.0-0.2*x^10.0+0.09523809523809523*x^7.0-0.8333333333333333*x^7.0+1.2*x^6.0-2.1*x^6.0+12.5*x^2.0+12.0*x^3.0-15.0*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "560.0*x^6.0-1008.0*x^6.0+80.0*x^3.0-700.0*x^3.0+432.0*x^2.0-756.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "560.0*6.0*x^5.0-1008.0*6.0*x^5.0+80.0*3.0*x^2.0-700.0*3.0*x^2.0+432.0*2.0*x^1.0-756.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "3360.0*x^5.0-6048.0*x^5.0+240.0*x^2.0-2100.0*x^2.0+864.0*x-1512.0*x";
        Usage.EqnSolArrayMap3[3]="0.11111111111111112*x^10.0-0.2*x^10.0+0.09523809523809523*x^7.0-0.8333333333333333*x^7.0+1.2*x^6.0-2.1*x^6.0+12.5*x^2.0+12.0*x^3.0-15.0*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.11111111111111112/11.0*x^11.0-0.2/11.0*x^11.0+0.09523809523809523/8.0*x^8.0-0.8333333333333333/8.0*x^8.0+1.2/7.0*x^7.0-2.1/7.0*x^7.0+12.5/3.0*x^3.0+12.0/4.0*x^4.0-15.0/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.010101010101010102*x^11.0-0.018181818181818184*x^11.0+0.011904761904761904*x^8.0-0.10416666666666666*x^8.0+0.17142857142857143*x^7.0-0.3*x^7.0+4.166666666666667*x^3.0+3.0*x^4.0-3.75*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "3360.0*x^5.0-6048.0*x^5.0+240.0*x^2.0-2100.0*x^2.0+864.0*x-1512.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "3360.0*5.0*x^4.0-6048.0*5.0*x^4.0+240.0*2.0*x^1.0-2100.0*2.0*x^1.0+864.0-1512.0";
        Usage.EqnSolArrayMap2[4] = "16800.0*x^4.0-30240.0*x^4.0+480.0*x-4200.0*x+864.0-1512.0";
        Usage.EqnSolArrayMap3[4]="0.010101010101010102*x^11.0-0.018181818181818184*x^11.0+0.011904761904761904*x^8.0-0.10416666666666666*x^8.0+0.17142857142857143*x^7.0-0.3*x^7.0+4.166666666666667*x^3.0+3.0*x^4.0-3.75*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.010101010101010102/12.0*x^12.0-0.018181818181818184/12.0*x^12.0+0.011904761904761904/9.0*x^9.0-0.10416666666666666/9.0*x^9.0+0.17142857142857143/8.0*x^8.0-0.3/8.0*x^8.0+4.166666666666667/4.0*x^4.0+3.0/5.0*x^5.0-3.75/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="8.417508417508418E-4*x^12.0-0.0015151515151515154*x^12.0+0.0013227513227513227*x^9.0-0.011574074074074073*x^9.0+0.02142857142857143*x^8.0-0.0375*x^8.0+1.0416666666666667*x^4.0+0.6*x^5.0-0.75*x^5.0-1.0416666666666667*x^4.0";
        Usage.EqnSolOrder[4] = 4;

        Usage.EqnSolArrayMap1[5] = "16800.0*x^4.0-30240.0*x^4.0+480.0*x-4200.0*x+864.0-1512.0";
        Usage.EqnSolArrayDiffAlone[5] = "16800.0*4.0*x^3.0-30240.0*4.0*x^3.0+480.0-4200.0+0.0-0.0";
        Usage.EqnSolArrayMap2[5] = "67200.0*x^3.0-120960.0*x^3.0+480.0-4200.0-";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "67200.0*x^3.0-120960.0*x^3.0+480.0-4200.0";
        Usage.EqnSolArrayDiffAlone[6] = "67200.0*3.0*x^2.0-120960.0*3.0*x^2.0+0.0-0.0";
        Usage.EqnSolArrayMap2[6] = "201600.0*x^2.0-362880.0*x^2.0-";
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

    public static void TestCaseDiff5034() {
        //Only Trailing Constant
        //Test Case 5034
        Usage.TestCase = 5034;
        Usage.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        Usage.EqnUnderTest = "x^7.0*8.0-x^7.0*8.0+x^4.0*5.0-x^4.0*5.0+x^3.0-x^3.0*4.0";
        Usage.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+x^3.0-4.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[1] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+3.0*x^2.0-4.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[1] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+3.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayMap3[1]="8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+x^3.0-4.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[1]="8.0/8.0*x^8.0-8.0/8.0*x^8.0+5.0/5.0*x^5.0-5.0/5.0*x^5.0+1.0/4.0*x^4.0-4.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[1]="x^8.0-x^8.0+x^5.0-x^5.0+0.25*x^4.0-x^4.0";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+3.0*x^2.0-12.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[2] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+3.0*2.0*x^1.0-12.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[2] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+6.0*x-24.0*x";
        Usage.EqnSolArrayMap3[2]="x^8.0-x^8.0+x^5.0-x^5.0+0.25*x^4.0-x^4.0";
        Usage.EqnSolArrayIntegrAlone[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+0.25/5.0*x^5.0-1.0/5.0*x^5.0";
        Usage.EqnSolArrayMap4[2]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.05*x^5.0-0.2*x^5.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+6.0*x-24.0*x";
        Usage.EqnSolArrayDiffAlone[3] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+6.0-24.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+6.0-24.0";
        Usage.EqnSolArrayMap3[3]="0.1111111111111111*x^9.0-0.1111111111111111*x^9.0+0.16666666666666666*x^6.0-0.16666666666666666*x^6.0+0.05*x^5.0-0.2*x^5.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.05/6.0*x^6.0-0.2/6.0*x^6.0";
        Usage.EqnSolArrayMap4[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.008333333333333333*x^6.0-0.03333333333333333*x^6.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+6.0-24.0";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        Usage.EqnSolArrayMap2[4] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        Usage.EqnSolArrayMap3[4]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.008333333333333333*x^6.0-0.03333333333333333*x^6.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0";
        Usage.EqnSolArrayMap4[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0011904761904761904*x^7.0-0.0047619047619047615*x^7.0";
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

       
    //
    //
    //
    public static void TestCaseDiff5035() {
        //Test Case 5035
        Usage.TestCase = 5035;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        //Usage.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        Usage.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*x^4 - x - 25.0";
        Usage.TestCaseName = "Simple: Multiple x variables in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+x^5.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0";
        Usage.EqnSolArrayMap3[1] = "x^9.0+x^10.0+x^7.0+x^5.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+1.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.16666666666666666*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+5.0*4.0*x^3.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+20.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.16666666666666666*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.16666666666666666/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.023809523809523808*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+20.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+20.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+60.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.023809523809523808*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.023809523809523808/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.002976190476190476*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+60.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+60.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+120.0*x";
        Usage.EqnSolArrayMap3[4]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.002976190476190476*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*x^13.0+5.827505827505828E-4/14.0*x^14.0+0.0013888888888888887/11.0*x^11.0+0.002976190476190476/9.0*x^9.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="5.827505827505828E-5*x^13.0+4.1625041625041625E-5*x^14.0+1.2626262626262624E-4*x^11.0+3.3068783068783067E-4*x^9.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5036() {
        //Test Case 5036
        Usage.TestCase = 5036;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        Usage.EqnUnderTest = "x^9*x+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        Usage.TestCaseName = "Complex: Multiple x variables in 1 term with Starting Term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 4;
        
        Usage.EqnSolArrayMap1[1] = "x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.09090909090909091*x^11.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "10.0*9.0*x^8.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        Usage.EqnSolArrayMap2[2] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.09090909090909091*x^11.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*x^12.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "90.0*8.0*x^7.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*x^13.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="5.827505827505828E-4*x^13.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "720.0*7.0*x^6.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "5040.0*x^6.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5037() {
        //Test Case 5037
        Usage.TestCase = 5037;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        Usage.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        Usage.TestCaseName = "Simple: Multiple x variables in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 4;
        
        Usage.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5038() {
        //Test Case 5038
        Usage.TestCase = 5038;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        Usage.EqnUnderTest = "x^9*x+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        Usage.TestCaseName = "Complex: Multiple x variables in 1 term with Starting Term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 4;
        
        Usage.EqnSolArrayMap1[1] = "x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.09090909090909091*x^11.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "10.0*9.0*x^8.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        Usage.EqnSolArrayMap2[2] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.09090909090909091*x^11.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*x^12.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "90.0*8.0*x^7.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*x^13.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="5.827505827505828E-4*x^13.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "720.0*7.0*x^6.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "5040.0*x^6.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5039() {
        //Test Case 5039
        Usage.TestCase = 5039;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "1.0*x^8*5.0 + 2*x^5 + 3*x^4*5 - 2*x*3 - 25.0*24*23";
        Usage.TestCaseName = "Simple: Multiple Coeffiecients in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "5.0*x^8.0+2.0*x^5.0+15.0*x^4.0-6.0*x-13800.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+2.0*5.0*x^4.0+15.0*4.0*x^3.0-6.0-0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*x^7.0+10.0*x^4.0+60.0*x^3.0-6.0";
        Usage.EqnSolArrayMap3[1]="5.0*x^8.0+2.0*x^5.0+15.0*x^4.0-6.0*x-13800.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+2.0/6.0*x^6.0+15.0/5.0*x^5.0-6.0/2.0*x^2.0-13800.0*x";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0+3.0*x^5.0-3.0*x^2.0-13800.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*x^7.0+10.0*x^4.0+60.0*x^3.0-6.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+10.0*4.0*x^3.0+60.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*x^6.0+40.0*x^3.0+180.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0+3.0*x^5.0-3.0*x^2.0-13800.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0+3.0/6.0*x^6.0-3.0/3.0*x^3.0-13800.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0+0.5*x^6.0-x^3.0-6900.0*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*x^6.0+40.0*x^3.0+180.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+40.0*3.0*x^2.0+180.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*x^5.0+120.0*x^2.0+360.0*x";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0+0.5*x^6.0-x^3.0-6900.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0+0.5/7.0*x^7.0-1.0/4.0*x^4.0-6900.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0+0.07142857142857142*x^7.0-0.25*x^4.0-2300.0*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*x^5.0+120.0*x^2.0+360.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+120.0*2.0*x^1.0+360.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*x^4.0+240.0*x+360.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0+0.07142857142857142*x^7.0-0.25*x^4.0-2300.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0+0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0-2300.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0+0.008928571428571428*x^8.0-0.05*x^5.0-575.0*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "8400.0*x^4.0+240.0*x+360.0";
        Usage.EqnSolArrayDiffAlone[5] = "8400.0*4.0*x^3.0+240.0+0.0";
        Usage.EqnSolArrayMap2[5] = "33600.0*x^3.0+240.0+";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "33600.0*x^3.0+240.0";
        Usage.EqnSolArrayDiffAlone[6] = "33600.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "100800.0*x^2.0+";
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
    public static void TestCaseDiff5040() {
        //Test Case 5040
        Usage.TestCase = 5040;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        Usage.EqnUnderTest = "-1.0*x^8*5.0 + 2*x^5 -3*x^4*5 - 2*x*3 - 25.0*-24*23";
        Usage.TestCaseName = "Complex: Multiple Coeffiecients in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "-5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        Usage.EqnSolArrayDiffAlone[1] = "-5.0*8.0*x^7.0+2.0*5.0*x^4.0-15.0*4.0*x^3.0-6.0+0.0";
        Usage.EqnSolArrayMap2[1] = "-40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        Usage.EqnSolArrayMap3[1]="-5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        Usage.EqnSolArrayIntegrAlone[1]="-5.0/9.0*x^9.0+2.0/6.0*x^6.0-15.0/5.0*x^5.0-6.0/2.0*x^2.0+13800.0*x";
        Usage.EqnSolArrayMap4[1]="-0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "-40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        Usage.EqnSolArrayDiffAlone[2] = "-40.0*7.0*x^6.0+10.0*4.0*x^3.0-60.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "-280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="-0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0-3.0/6.0*x^6.0-3.0/3.0*x^3.0+13800.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="-0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "-280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "-280.0*6.0*x^5.0+40.0*3.0*x^2.0-180.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "-1680.0*x^5.0+120.0*x^2.0-360.0*x";
        Usage.EqnSolArrayMap3[3]="-0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0-0.5/7.0*x^7.0-1.0/4.0*x^4.0+6900.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="-0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-0.25*x^4.0+2300.0*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "-1680.0*x^5.0+120.0*x^2.0-360.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*x^4.0+120.0*2.0*x^1.0-360.0";
        Usage.EqnSolArrayMap2[4] = "-8400.0*x^4.0+240.0*x-360.0";
        Usage.EqnSolArrayMap3[4]="-0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-0.25*x^4.0+2300.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0-0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0+2300.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="-4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0-0.008928571428571428*x^8.0-0.05*x^5.0+575.0*x^4.0";
        Usage.EqnSolOrder[4] = 4;
        
        Usage.EqnSolArrayMap1[5] = "-8400.0*x^4.0+240.0*x-360.0";
        Usage.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*x^3.0+240.0-0.0";
        Usage.EqnSolArrayMap2[5] = "-33600.0*x^3.0+240.0";
        Usage.EqnSolArrayMap3[5]="";
        Usage.EqnSolArrayIntegrAlone[5]="";
        Usage.EqnSolArrayMap4[5]="";
        Usage.EqnSolOrder[5] = 5;

        Usage.EqnSolArrayMap1[6] = "-33600.0*x^3.0+240.0";
        Usage.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*x^2.0+0.0";
        Usage.EqnSolArrayMap2[6] = "-100800.0*x^2.0";
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
    public static void TestCaseDiff5041() {
        //Test Case 5041
        Usage.TestCase = 5041;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "-1.0*x^8*(-5.0) + 2*x^5 + -3*x^4*(-5) - +2*x*+3 - 25.0*(-24)*+23";
        Usage.EqnUnderTest = "-1.0*x^8*-5.0 + 2*x^5 + 3*x^4*-5 - 2*x*3 - 25.0*-24*+23";
        Usage.TestCaseName = "Complex: Multiple Coeffiecients in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 14;
        
        Usage.EqnSolArrayMap1[1] = "5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        Usage.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+2.0*5.0*x^4.0-15.0*4.0*x^3.0-6.0+0.0";
        Usage.EqnSolArrayMap2[1] = "40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        Usage.EqnSolArrayMap3[1]="5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        Usage.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+2.0/6.0*x^6.0-15.0/5.0*x^5.0-6.0/2.0*x^2.0+13800.0*x";
        Usage.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        Usage.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+10.0*4.0*x^3.0-60.0*3.0*x^2.0-0.0";
        Usage.EqnSolArrayMap2[2] = "280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        Usage.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0-3.0/6.0*x^6.0-3.0/3.0*x^3.0+13800.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+40.0*3.0*x^2.0-180.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[3] = "1680.0*x^5.0+120.0*x^2.0-360.0*x";
        Usage.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0-0.5/7.0*x^7.0-1.0/4.0*x^4.0+6900.0/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-0.25*x^4.0+2300.0*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "1680.0*x^5.0+120.0*x^2.0-360.0*x";
        Usage.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+120.0*2.0*x^1.0-360.0";
        Usage.EqnSolArrayMap2[4] = "8400.0*x^4.0+240.0*x-360.0";
        Usage.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-0.25*x^4.0+2300.0*x^3.0";
        Usage.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0-0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0+2300.0/4.0*x^4.0";
        Usage.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0-0.008928571428571428*x^8.0-0.05*x^5.0+575.0*x^4.0";
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
    public static void TestCaseDiff5042() {
        //Test Case 5042
        Usage.TestCase = 5042;
        Usage.EqnRegressionDiffStatus = "All Green Including Values";
        Usage.EqnRegressionIntegreStatus = "All RED Including Values";
        Usage.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        Usage.TestCaseName = "Simple: Multiple x variables in 1 term";
        Usage.TestCaseMatrixID="Unit TestCase";
        
        Usage.DiffWithRespTo = "x";
        Usage.MaxDiffOrder = 14;
        Usage.MaxIntegreOrder = 4;
        
        Usage.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        Usage.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayMap3[1]="x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        Usage.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        Usage.EqnSolArrayMap4[1]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolOrder[1] = 1;

        Usage.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        Usage.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        Usage.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayMap3[2]="0.1*x^10.0+0.09090909090909091*x^11.0+0.125*x^8.0+0.3333333333333333*x^6.0-0.5*x^2.0-25.0*x";
        Usage.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        Usage.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolOrder[2] = 2;

        Usage.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        Usage.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        Usage.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        Usage.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        Usage.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        Usage.EqnSolOrder[3] = 3;

        Usage.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        Usage.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        Usage.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
