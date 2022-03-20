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
public class TestHarnessUnitTest {
    //UnitTestCases Test Cases
    public static void UnitTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapInfinity(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapInfinity(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED +"UnitTest TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapException(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapException(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED +"UnitTest TestCase:j= " + j  + ": Exception GAP" + ConsoleColors.RESET);
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
                //System.out.println(ConsoleColors.BLUE + "Bypassing Test Case 35 :Infinite Loop UsageCalculus.EqnUnderTest=" + UsageCalculus.EqnUnderTest + ConsoleColors.RESET);
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
            case 5043:
                TestCaseDiff5043();
                break;
            case 5044:
                TestCaseDiff5044();
                break;
            case 5045:
                TestCaseDiff5045();
                //System.out.println(ConsoleColors.BLUE + "Bypassing Test Case 35 :Infinite Loop UsageCalculus.EqnUnderTest=" + UsageCalculus.EqnUnderTest + ConsoleColors.RESET);
                break;
            case 5046:
                TestCaseDiff5046();
                break;
            case 5047:
                TestCaseDiff5047();
                break;
            case 5048:
                TestCaseDiff5048();
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
        UsageCalculus.TestCase = 5019;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnUnderTest = "x^7.0/8.0-x^7.0/8.0+x^4.0/5.0-x^4.0/5.0+x^3.0-x^3.0/4.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/8.0*7.0*x^6.0-1.0/8.0*7.0*x^6.0+1.0/5.0*4.0*x^3.0-1.0/5.0*4.0*x^3.0+3.0*x^2.0-1.0/4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/8.0*x^7.0-1.0/8.0*x^7.0+1.0/5.0*x^4.0-1.0/5.0*x^4.0+x^3.0-1.0/4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.125/8.0*x^8.0-0.125/8.0*x^8.0+0.2/5.0*x^5.0-0.2/5.0*x^5.0+1.0/4.0*x^4.0-0.25/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[1]="0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.875*x^6.0-0.875*x^6.0+0.8*x^3.0-0.8*x^3.0+3.0*x^2.0-0.75*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.875*6.0*x^5.0-0.875*6.0*x^5.0+0.8*3.0*x^2.0-0.8*3.0*x^2.0+3.0*2.0*x^1.0-0.75*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[2] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayMap3[2]="0.015625*x^8.0-0.015625*x^8.0+0.04*x^5.0-0.04*x^5.0+1.0/4.0*x^4.0-0.0625*x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.015625/9.0*x^9.0-0.015625/9.0*x^9.0+0.04/6.0*x^6.0-0.04/6.0*x^6.0+0.25/5.0*x^5.0-0.0625/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "5.25*x^5.0-5.25*x^5.0+2.4000000000000004*x^2.0-2.4000000000000004*x^2.0+6.0*x-1.5*x";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "5.25*5.0*x^4.0-5.25*5.0*x^4.0+2.4000000000000004*2.0*x^1.0-2.4000000000000004*2.0*x^1.0+6.0-1.5";
        UsageCalculus.EqnSolArrayMap2[3] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayMap3[3]="0.001736111111111111*x^9.0-0.001736111111111111*x^9.0+0.006666666666666667*x^6.0-0.006666666666666667*x^6.0+0.05*x^5.0-0.0125*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.001736111111111111/10.0*x^10.0-0.001736111111111111/10.0*x^10.0+0.006666666666666667/7.0*x^7.0-0.006666666666666667/7.0*x^7.0+0.05/6.0*x^6.0-0.0125/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[3]="1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "26.25*x^4.0-26.25*x^4.0+4.800000000000001*x-4.800000000000001*x+6.0-1.5";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "26.25*4.0*x^3.0-26.25*4.0*x^3.0+4.800000000000001-4.800000000000001+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "105.0*x^3.0-105.0*x^3.0+4.800000000000001-4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4]="1.736111111111111E-4*x^10.0-1.736111111111111E-4*x^10.0+9.523809523809525E-4*x^7.0-9.523809523809525E-4*x^7.0+0.008333333333333333*x^6.0-0.0020833333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="1.736111111111111E-4/11.0*x^11.0-1.736111111111111E-4/11.0*x^11.0+9.523809523809525E-4/8.0*x^8.0-9.523809523809525E-4/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.0020833333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[4]="1.578282828282828E-5*x^11.0-1.578282828282828E-5*x^11.0+1.1904761904761906E-4*x^8.0-1.1904761904761906E-4*x^8.0+0.0011904761904761904*x^7.0-2.976190476190476E-4*x^7.0";
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
    public static void TestCaseDiff5020() {
        //Test Case 5020
        UsageCalculus.TestCase = 5020;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "9.0*xyc1^9.0 + 6.0*xyc1^6.0 + 5.0*xyc1^5.0 + 2.0*xyc1^2.0 + 25.0*xyc1 + 50.0";
        UsageCalculus.TestCaseName = "Standard:Independent Constant:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "9.0*xyc1^9.0+6.0*xyc1^6.0+5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1+50.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*9.0*xyc1^8.0+6.0*6.0*xyc1^5.0+5.0*5.0*xyc1^4.0+2.0*2.0*xyc1^1.0+25.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "81.0*xyc1^8.0+36.0*xyc1^5.0+25.0*xyc1^4.0+4.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayMap3[1]=  "9.0*xyc1^9.0+6.0*xyc1^6.0+5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1+50.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="9.0/10.0*xyc1^10.0+6.0/7.0*xyc1^7.0+5.0/6.0*xyc1^6.0+2.0/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0+50.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.9*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.8333333333333334*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0+50.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "81.0*xyc1^8.0+36.0*xyc1^5.0+25.0*xyc1^4.0+4.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "81.0*8.0*xyc1^7.0+36.0*5.0*xyc1^4.0+25.0*4.0*xyc1^3.0+4.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "648.0*xyc1^7.0+180.0*xyc1^4.0+100.0*xyc1^3.0+4.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.9*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.8333333333333334*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0+50.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.9/11.0*xyc1^11.0+0.8571428571428571/8.0*xyc1^8.0+0.8333333333333334/7.0*xyc1^7.0+0.6666666666666666/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0+50.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.08181818181818182*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0+25.0*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "648.0*xyc1^7.0+180.0*xyc1^4.0+100.0*xyc1^3.0+4.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "648.0*7.0*xyc1^6.0+180.0*4.0*xyc1^3.0+100.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "4536.0*xyc1^6.0+720.0*xyc1^3.0+300.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.08181818181818182*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.11904761904761905*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0+25.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.08181818181818182/12.0*xyc1^12.0+0.10714285714285714/9.0*xyc1^9.0+0.11904761904761905/8.0*xyc1^8.0+0.16666666666666666/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0+25.0/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.006818181818181818*xyc1^12.0+0.011904761904761904*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.03333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0+8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "4536.0*xyc1^6.0+720.0*xyc1^3.0+300.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "4536.0*6.0*xyc1^5.0+720.0*3.0*xyc1^2.0+300.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "27216.0*xyc1^5.0+2160.0*xyc1^2.0+600.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[4]="0.006818181818181818*xyc1^12.0+0.011904761904761904*xyc1^9.0+0.014880952380952382*xyc1^8.0+0.03333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0+8.333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.006818181818181818/13.0*xyc1^13.0+0.011904761904761904/10.0*xyc1^10.0+0.014880952380952382/9.0*xyc1^9.0+0.03333333333333333/6.0*xyc1^6.0+1.0416666666666667/5.0*xyc1^5.0+8.333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.244755244755245E-4*xyc1^13.0+0.0011904761904761904*xyc1^10.0+0.0016534391534391536*xyc1^9.0+0.005555555555555556*xyc1^6.0+0.20833333333333334*xyc1^5.0+2.0833333333333335*xyc1^4.0";
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
    
    public static void TestCaseDiff5021() {
        //Test Case 5021
        UsageCalculus.TestCase = 5021;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^8 + x^5 + x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
       
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        //Usage.EqnUnderTest="8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[1]=UsageCalculus.EqnSolArrayIntegrAlone[1];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[1]=UsageCalculus.EqnSolArrayMap1[1];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[1]=UsageCalculus.EqnSolArrayDiffAlone[1];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[1]=UsageCalculus.EqnSolArrayMap1[1];
        
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        
        
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[2]=UsageCalculus.EqnSolArrayIntegrAlone[2];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[2]=UsageCalculus.EqnSolArrayMap1[2];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[2]=UsageCalculus.EqnSolArrayDiffAlone[2];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[2]=UsageCalculus.EqnSolArrayMap1[2];

        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[3]=UsageCalculus.EqnSolArrayIntegrAlone[3];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[3]=UsageCalculus.EqnSolArrayMap1[3];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[3]=UsageCalculus.EqnSolArrayDiffAlone[3];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[3]=UsageCalculus.EqnSolArrayMap1[3];

        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[4]=UsageCalculus.EqnSolArrayIntegrAlone[4];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[4]=UsageCalculus.EqnSolArrayMap1[4];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[4]=UsageCalculus.EqnSolArrayDiffAlone[4];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[4]=UsageCalculus.EqnSolArrayMap1[4];

        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[5]=UsageCalculus.EqnSolArrayIntegrAlone[5];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[5]=UsageCalculus.EqnSolArrayMap1[5];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[5]=UsageCalculus.EqnSolArrayDiffAlone[5];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[5]=UsageCalculus.EqnSolArrayMap1[5];

        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[6]=UsageCalculus.EqnSolArrayIntegrAlone[6];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[6]=UsageCalculus.EqnSolArrayMap1[6];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[6]=UsageCalculus.EqnSolArrayDiffAlone[6];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[6]=UsageCalculus.EqnSolArrayMap1[6];

        UsageCalculus.EqnSolOrder[6] = 6;

        UsageCalculus.EqnSolArrayMap1[7] = "";
        UsageCalculus.EqnSolArrayDiffAlone[7] = "";
        UsageCalculus.EqnSolArrayMap2[7] = "";
        UsageCalculus.EqnSolArrayMap3[7]="";
        UsageCalculus.EqnSolArrayIntegrAlone[7]="";
        UsageCalculus.EqnSolArrayMap4[7]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[7]=UsageCalculus.EqnSolArrayIntegrAlone[7];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[7]=UsageCalculus.EqnSolArrayMap1[7];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[7]=UsageCalculus.EqnSolArrayDiffAlone[7];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[7]=UsageCalculus.EqnSolArrayMap1[7];
        UsageCalculus.EqnSolOrder[7] = 7;

        UsageCalculus.EqnSolArrayMap1[8] = "";
        UsageCalculus.EqnSolArrayDiffAlone[8] = "";
        UsageCalculus.EqnSolArrayMap2[8] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[8]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[8]=UsageCalculus.EqnSolArrayIntegrAlone[8];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[8]=UsageCalculus.EqnSolArrayMap1[8];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[8]=UsageCalculus.EqnSolArrayDiffAlone[8];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[8]=UsageCalculus.EqnSolArrayMap1[8];
        UsageCalculus.EqnSolOrder[8] = 8;

        UsageCalculus.EqnSolArrayMap1[9] = "";
        UsageCalculus.EqnSolArrayDiffAlone[9] = "";
        UsageCalculus.EqnSolArrayMap2[9] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[9]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[9]=UsageCalculus.EqnSolArrayIntegrAlone[9];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[9]=UsageCalculus.EqnSolArrayMap1[9];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[9]=UsageCalculus.EqnSolArrayDiffAlone[9];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[9]=UsageCalculus.EqnSolArrayMap1[9];
        UsageCalculus.EqnSolOrder[9] = 9;

        UsageCalculus.EqnSolArrayMap1[10] = "";
        UsageCalculus.EqnSolArrayDiffAlone[10] = "";
        UsageCalculus.EqnSolArrayMap2[10] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[10]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[10]=UsageCalculus.EqnSolArrayIntegrAlone[10];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[10]=UsageCalculus.EqnSolArrayMap1[10];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[10]=UsageCalculus.EqnSolArrayDiffAlone[10];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[10]=UsageCalculus.EqnSolArrayMap1[10];
        UsageCalculus.EqnSolOrder[10] = 10;

        UsageCalculus.EqnSolArrayMap1[11] = "";
        UsageCalculus.EqnSolArrayDiffAlone[11] = "";
        UsageCalculus.EqnSolArrayMap2[11] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[11]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[11]=UsageCalculus.EqnSolArrayIntegrAlone[11];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[11]=UsageCalculus.EqnSolArrayMap1[11];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[11]=UsageCalculus.EqnSolArrayDiffAlone[11];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[11]=UsageCalculus.EqnSolArrayMap1[11];
        UsageCalculus.EqnSolOrder[11] = 11;

        UsageCalculus.EqnSolArrayMap1[12] = "";
        UsageCalculus.EqnSolArrayDiffAlone[12] = "";
        UsageCalculus.EqnSolArrayMap2[12] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[12]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[12]=UsageCalculus.EqnSolArrayIntegrAlone[12];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[12]=UsageCalculus.EqnSolArrayMap1[12];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[12]=UsageCalculus.EqnSolArrayDiffAlone[12];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[12]=UsageCalculus.EqnSolArrayMap1[12];
        UsageCalculus.EqnSolOrder[12] = 12;

        UsageCalculus.EqnSolArrayMap1[13] = "";
        UsageCalculus.EqnSolArrayDiffAlone[13] = "";
        UsageCalculus.EqnSolArrayMap2[13] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[13]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[13]=UsageCalculus.EqnSolArrayIntegrAlone[13];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[13]=UsageCalculus.EqnSolArrayMap1[13];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[13]=UsageCalculus.EqnSolArrayDiffAlone[13];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[13]=UsageCalculus.EqnSolArrayMap1[13];
        UsageCalculus.EqnSolOrder[13] = 13;

        UsageCalculus.EqnSolArrayMap1[14] = "";
        UsageCalculus.EqnSolArrayDiffAlone[14] = "";
        UsageCalculus.EqnSolArrayMap2[14] = "";
        UsageCalculus.EqnSolArrayIntegrAlone[14]="";
        UsageCalculus.EqnSolArrayIntegr_BeforeDiff[14]=UsageCalculus.EqnSolArrayIntegrAlone[14];
        UsageCalculus.EqnSolArrayDiff_AfterIntegr[14]=UsageCalculus.EqnSolArrayMap1[14];
        UsageCalculus.EqnSolArrayDiff_BeforeIntegr[14]=UsageCalculus.EqnSolArrayDiffAlone[14];
        UsageCalculus.EqnSolArrayIntegr_AfterDiff[14]=UsageCalculus.EqnSolArrayMap1[14];
        UsageCalculus.EqnSolOrder[14] = 14;
    }

    public static void TestCaseDiff5022() {
        //Test Case 5022
        UsageCalculus.TestCase = 5022;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values Except 4th Order Integration";
        UsageCalculus.EqnUnderTest = "1/x^8 + 1/x^5 + 1/x^4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Divide Operator";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/x^8.0+1.0/x^5.0+1.0/x^4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/x^7.0+1.0/-4.0/x^4.0+1.0/-3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.14285714285714285/-6.0/x^6.0-0.25/-3.0/x^3.0-0.3333333333333333/-2.0/x^2.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.023809523809523808/-5.0/x^5.0+0.08333333333333333/-2.0/x^2.0+0.16666666666666666/-1.0/x^1.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0047619047619047615/-4.0/x^4.0-0.041666666666666664/-1.0/x^1.0-0.16666666666666666*ln(x)-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0011904761904761904/x^4.0+0.041666666666666664/x-0.16666666666666666*ln(x)-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff5023() {
        //Test Case 5023
        UsageCalculus.TestCase = 5023;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values(Except 6th Order Values) :Negative Exponent Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "x^-8 + x^-5 + x^-4 - x - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^-8.0+x^-5.0+x^-4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0*x^-9.0-5.0*x^-6.0-4.0*x^-5.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^-8.0+x^-5.0+x^-4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0*x^-7.0+1.0/-4.0*x^-4.0+1.0/-3.0*x^-3.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/x^9.0-5.0/x^6.0-4.0/x^5.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/x^10.0-5.0*-6.0/x^7.0-4.0*-5.0/x^6.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/7.0/x^7.0-1.0/4.0/x^4.0-1.0/3.0/x^3.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.14285714285714285/-6.0/x^6.0-0.25/-3.0/x^3.0-0.3333333333333333/-2.0/x^2.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/x^10.0+30.0/x^7.0+20.0/x^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/x^11.0+30.0*-7.0/x^8.0+20.0*-6.0/x^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.023809523809523808/x^6.0+0.08333333333333333/x^3.0+0.16666666666666666/x^2.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.023809523809523808/-5.0/x^5.0+0.08333333333333333/-2.0/x^2.0+0.16666666666666666/-1.0/x^1.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/x^11.0-210.0/x^8.0-120.0/x^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/x^12.0-210.0*-8.0/x^9.0-120.0*-7.0/x^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/x^12.0+1680.0/x^9.0+840.0/x^8.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0047619047619047615/x^5.0-0.041666666666666664/x^2.0-0.16666666666666666/x-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0047619047619047615/-4.0/x^4.0-0.041666666666666664/-1.0/x^1.0-0.16666666666666666*ln(x)-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0011904761904761904/x^4.0+0.041666666666666664/x-0.16666666666666666*ln(x)-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "7920.0*x^-12.0+1680.0*x^-9.0+840.0*x^-8.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "7920.0*-12.0*x^-13.0+1680.0*-9.0*x^-10.0+840.0*-8.0*x^-9.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-95040.0*x^-13.0-15120.0*x^-10.0-6720.0*x^-9.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-95040.0*x^-13.0-15120.0*x^-10.0-6720.0*x^-9.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-95040.0*-13.0*x^-14.0-15120.0*-10.0*x^-11.0-6720.0*-9.0*x^-10.0";
        UsageCalculus.EqnSolArrayMap2[6] = "1235520.0*x^-14.0+151200.0*x^-11.0+60480.0*x^-10.0";
        UsageCalculus.EqnSolArrayMap3[6]="";
        UsageCalculus.EqnSolArrayIntegrAlone[6]="";
        UsageCalculus.EqnSolArrayMap4[6]="";
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

    public static void TestCaseDiff5024() {
        //Test Case 5024
        UsageCalculus.TestCase= 5024;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values :Negative Exponent Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1/x^-8 + 1/x^-5 + 1/x^-4 - 25.0";
        UsageCalculus.TestCaseName = "Standard:No Coefficients:Basic Equation:Negative Exponent & Divide Operator";
        UsageCalculus.TestCaseMatrixID="Complex:Unit TestCase:Mapping of -ve Exponent & Division";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/x^-8.0+1.0/x^-5.0+1.0/x^-4.0-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/x^-8.0+1.0/x^-5.0+1.0/x^-4.0-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/x^-7.0-5.0/x^-4.0-4.0/x^-3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-7.0/x^-6.0-5.0*-4.0/x^-3.0-4.0*-3.0/x^-2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0/x^-6.0+20.0/x^-3.0+12.0/x^-2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0/x^-6.0+20.0/x^-3.0+12.0/x^-2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*-6.0/x^-5.0+20.0*-3.0/x^-2.0+12.0*-2.0/x^-1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-336.0/x^-5.0-60.0/x^-2.0-1.0/24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-336.0/x^-5.0-60.0/x^-2.0-1.0/24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-336.0*-5.0/x^-4.0-60.0*-2.0/x^-1.0-1.0/24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0/x^-4.0+1.0/120.0*x-1.0/24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-1.0416666666666667*x^4.0";
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

    public static void TestCaseDiff5025() {
        //Test Case 5025
        UsageCalculus.TestCase = 5025;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*x^8 + 1.0*x^5 + 1.0*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Standard:Coefficients=1.0 Explicit:Basic Equation:Coefficients=1.0 Explicit";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
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
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
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

    public static void TestCaseDiff5026() {
        //Test Case 5026
        UsageCalculus.TestCase = 5026;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*x^8 + 1.0*x^5 + 1.0*x^4 - 1.0*x - 25.0";
        UsageCalculus.TestCaseName = "Complex:Coefficients=1.0 Explicit:Basic Equation:Coefficients=1.0 Explicit";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0+x^5.0+x^4.0-x-25.0";
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
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0+120.0";
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

    //Palindromic String-Equation Test
    public static void TestCaseDiff5027() {
        //Palindromic String-Equation Test
        //Test Case 5027
        UsageCalculus.TestCase = 5027;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values Except 6th Order Onwards";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = " 25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        UsageCalculus.EqnUnderTest = " -25.0+x+x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - x - 25.0";
        //Usage.EqnUnderTest="-120.0-480.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.TestCaseName = "Standard:Palindromic String Equation:";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0+1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-25.0+x+x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0+4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+4.0*3.0*x^2.0-5.0*4.0*x^3.0-8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x+1.0/2.0*x^2.0+1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0+0.5/3.0*x^3.0+0.2/6.0*x^6.0-0.16666666666666666/7.0*x^7.0-0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "12.0*2.0*x^1.0-20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0+0.16666666666666666*x^3.0+0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0+0.03333333333333333/7.0*x^7.0-0.023809523809523808/8.0*x^8.0-0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "24.0-60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0+0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0+0.0047619047619047615/8.0*x^8.0-0.002976190476190476/9.0*x^9.0-0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0+0.008333333333333333*x^5.0+5.952380952380952E-4*x^8.0-3.3068783068783067E-4*x^9.0-8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "0.0-120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "0.0-6720.0*3.0*x^2.0+6720.0*3.0^x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-20160.0*x^2.0+20160.0*x^2.0+";
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

    public static void TestCaseDiff5028() {
        //Test Case 5028
        UsageCalculus.TestCase = 5028;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = " -25.0 + x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - 1.0*x - 25.0";
        //Usage.EqnUnderTest = " 25.0 + 1.0*x - x^4 -x^5 -x^8 + x^8 + x^5 + x^4 - 1.0*x - 25.0";
        UsageCalculus.TestCaseName = "Standard:Palindromic String Equation:Coefficient=1.0 Explicit";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-25.0+x-x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0+1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-25.0+x-x^4.0-x^5.0-x^8.0+x^8.0+x^5.0+x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-25.0*x+1.0/2.0*x^2.0-1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-25.0*x+1.0/2.0*x^2.0-1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.0-4.0*x^3.0-5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0-4.0*3.0*x^2.0-5.0*4.0*x^3.0-8.0*7.0*x^6.0+8.0*7.0*x^6.0+5.0*4.0*x^3.0+4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x+1.0/2.0*x^2.0-1.0/5.0*x^5.0-1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0+0.5/3.0*x^3.0-0.2/6.0*x^6.0-0.16666666666666666/7.0*x^7.0-0.1111111111111111/10.0*x^10.0+0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0+0.16666666666666666*x^3.0-0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-12.0*x^2.0-20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-12.0*2.0*x^1.0-20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0+0.16666666666666666*x^3.0-0.03333333333333333*x^6.0-0.023809523809523808*x^7.0-0.01111111111111111*x^10.0+0.01111111111111111*x^10.0+0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0-0.03333333333333333/7.0*x^7.0-0.023809523809523808/8.0*x^8.0-0.01111111111111111/11.0*x^11.0+0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-24.0*x-60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-24.0-60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.0047619047619047615*x^7.0-0.002976190476190476*x^8.0-0.0010101010101010099*x^11.0+0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0-0.0047619047619047615/8.0*x^8.0-0.002976190476190476/9.0*x^9.0-0.0010101010101010099/12.0*x^12.0+0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-1.0416666666666667*x^4.0+0.008333333333333333*x^5.0-5.952380952380952E-4*x^8.0-3.3068783068783067E-4*x^9.0-8.417508417508415E-5*x^12.0+8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "-24.0-120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "0.0-120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-120.0-6720.0*x^3.0+6720.0*x^3.0+120.0+";
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

    public static void TestCaseDiff5029() {
        //Test Case 5029
        UsageCalculus.TestCase = 5029;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values Except 5th Order Onwards";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "20.0*3.0*x^2.0-56.0*6.0*x^5.0+56.0*6.0*x^5.0+20.0*3.0*x^2.0+12.0*2.0*x";
        UsageCalculus.TestCaseName = "Standard:Basic Equation: With Coefficient";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "60.0*2.0*x^1.0-336.0*5.0*x^4.0+336.0*5.0*x^4.0+60.0*2.0*x^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[1] = "120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayMap3[1]="60.0*x^2.0-336.0*x^5.0+336.0*x^5.0+60.0*x^2.0+24.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="60.0/3.0*x^3.0-336.0/6.0*x^6.0+336.0/6.0*x^6.0+60.0/3.0*x^3.0+24.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[1]="20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "120.0*x-1680.0*x^4.0+1680.0*x^4.0+120.0*x+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "120.0-1680.0*4.0*x^3.0+1680.0*4.0*x^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[2]="20.0*x^3.0-56.0*x^6.0+56.0*x^6.0+20.0*x^3.0+12.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="20.0/4.0*x^4.0-56.0/7.0*x^7.0+56.0/7.0*x^7.0+20.0/4.0*x^4.0+12.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[2]="5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "120.0-6720.0*x^3.0+6720.0*x^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0-6720.0*3.0*x^2.0+6720.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-20160.0*x^2.0+20160.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="5.0*x^4.0-8.0*x^7.0+8.0*x^7.0+5.0*x^4.0+4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="5.0/5.0*x^5.0-8.0/8.0*x^8.0+8.0/8.0*x^8.0+5.0/5.0*x^5.0+4.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[3]="x^5.0-x^8.0+x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-20160.0*x^2.0+20160.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-20160.0*2.0*x^1.0+20160.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-40320.0*x+40320.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="x^5.0-x^8.0+x^8.0+x^5.0+x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[4]="1.0/6.0*x^6.0-1.0/9.0*x^9.0+1.0/9.0*x^9.0+1.0/6.0*x^6.0+1.0/5.0*x^5.0";
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

    public static void TestCaseDiff5030() {
        //Test Case 5030
        UsageCalculus.TestCase = 5030;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "40320.0*x +40320.0*x -25";
        UsageCalculus.TestCaseName = "Simple:Basic Equation: With Coefficient";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "40320.0*x+40320.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "40320.0+40320.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40320.0+40320.0";
        UsageCalculus.EqnSolArrayMap3[1]="40320.0*x+40320.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="40320.0/2.0*x^2.0+40320.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="20160.0*x^2.0+20160.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40320.0+40320.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "";
        UsageCalculus.EqnSolArrayMap3[2]="20160.0*x^2.0+20160.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="20160.0/3.0*x^3.0+20160.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="6720.0*x^3.0+6720.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "";
        UsageCalculus.EqnSolArrayMap2[3] = "";
        UsageCalculus.EqnSolArrayMap3[3]="6720.0*x^3.0+6720.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="6720.0/4.0*x^4.0+6720.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="1680.0*x^4.0+1680.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "";
        UsageCalculus.EqnSolArrayMap2[4] = "";
        UsageCalculus.EqnSolArrayMap3[4]="1680.0*x^4.0+1680.0*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="1680.0/5.0*x^5.0+1680.0/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="336.0*x^5.0+336.0*x^5.0-1.0416666666666667*x^4.0";
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

    //Palindromic String-Equation Test-Rearranged
    public static void TestCaseDiff5031() {
        //Palindromic String-Equation Test-Rearranged
        //Test Case 5031
        UsageCalculus.TestCase = 5031;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values Constant Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = " x^8 -x^8 + x^5 -x^5 + x^4 - x^4 + 25.0 + x - x - 25.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation: With Constants in the Middle";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0+25.0+x-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+0.0+1.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+1.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0+25.0+x-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0+25.0*x+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0+25.0*x+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0+1.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+4.0*3.0*x^2.0-4.0*3.0*x^2.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0+25.0*x+1.0/2.0*x^2.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.2/6.0*x^6.0+25.0/2.0*x^2.0+0.5/3.0*x^3.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0+12.5*x^2.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+12.0*2.0*x^1.0-12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0+12.5*x^2.0+0.16666666666666666*x^3.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0+12.5/3.0*x^3.0+0.16666666666666666/4.0*x^4.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0+4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+24.0-24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0+4.166666666666667*x^3.0+0.041666666666666664*x^4.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*x^12.0-0.0010101010101010099/12.0*x^12.0+0.002976190476190476/9.0*x^9.0-0.002976190476190476/9.0*x^9.0+0.0047619047619047615/8.0*x^8.0-0.0047619047619047615/8.0*x^8.0+4.166666666666667/4.0*x^4.0+0.041666666666666664/5.0*x^5.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*x^12.0-8.417508417508415E-5*x^12.0+3.3068783068783067E-4*x^9.0-3.3068783068783067E-4*x^9.0+5.952380952380952E-4*x^8.0-5.952380952380952E-4*x^8.0+1.0416666666666667*x^4.0+0.008333333333333333*x^5.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0+-";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0+";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*x^2.0-6720.0*3.0*x^2.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*x^2.0-20160.0*x^2.0-";
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

    public static void TestCaseDiff5032() {
        //Test Case 5032
        UsageCalculus.TestCase = 5032;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+4.0*3.0*x^2.0-4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+4.0*x^3.0-4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="8.0/8.0*x^8.0-8.0/8.0*x^8.0+5.0/5.0*x^5.0-5.0/5.0*x^5.0+4.0/4.0*x^4.0-4.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[1]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+12.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+12.0*2.0*x^1.0-12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[2] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        UsageCalculus.EqnSolArrayMap3[2]="x^8.0-x^8.0+x^5.0-x^5.0+x^4.0-x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+24.0*x-24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+24.0-24.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        UsageCalculus.EqnSolArrayMap3[3]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+1.0/5.0*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.2/6.0*x^6.0-0.2/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+24.0-24.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.03333333333333333*x^6.0-0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.03333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0047619047619047615*x^7.0-0.0047619047619047615*x^7.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "6720.0*3.0*x^2.0-6720.0*3.0*x^2.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "20160.0*x^2.0-20160.0*x^2.0-";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "20160.0*x^2.0-20160.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "20160.0*2.0*x^1.0-20160.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[6] = "40320.0*x-40320.0*x";
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

    public static void TestCaseDiff5033() {
        //Leading & Trailing Constant
        //Test Case 5033
        UsageCalculus.TestCase = 5033;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = " 2*x^8*5 -3*x^8*6 + 4*x^5 -5*x^5*7 + 6*x^4*6 - 7*x^4*9 + 25.0 + 8*x*9 - 9*x*10 - 25.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:With Leading and Trailing Coefficients";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "10.0*x^8.0-18.0*x^8.0+4.0*x^5.0-35.0*x^5.0+36.0*x^4.0-63.0*x^4.0+25.0+72.0*x-90.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "10.0*8.0*x^7.0-18.0*8.0*x^7.0+4.0*5.0*x^4.0-35.0*5.0*x^4.0+36.0*4.0*x^3.0-63.0*4.0*x^3.0+0.0+72.0-90.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "80.0*x^7.0-144.0*x^7.0+20.0*x^4.0-175.0*x^4.0+144.0*x^3.0-252.0*x^3.0+72.0-90.0";
        UsageCalculus.EqnSolArrayMap3[1]="10.0*x^8.0-18.0*x^8.0+4.0*x^5.0-35.0*x^5.0+36.0*x^4.0-63.0*x^4.0+25.0+72.0*x-90.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="10.0/9.0*x^9.0-18.0/9.0*x^9.0+4.0/6.0*x^6.0-35.0/6.0*x^6.0+36.0/5.0*x^5.0-63.0/5.0*x^5.0+25.0*x+72.0/2.0*x^2.0-90.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.1111111111111112*x^9.0-2.0*x^9.0+0.6666666666666666*x^6.0-5.833333333333333*x^6.0+7.2*x^5.0-12.6*x^5.0+25.0*x+36.0*x^2.0-45.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "80.0*x^7.0-144.0*x^7.0+20.0*x^4.0-175.0*x^4.0+144.0*x^3.0-252.0*x^3.0+72.0-90.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "80.0*7.0*x^6.0-144.0*7.0*x^6.0+20.0*4.0*x^3.0-175.0*4.0*x^3.0+144.0*3.0*x^2.0-252.0*3.0*x^2.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "560.0*x^6.0-1008.0*x^6.0+80.0*x^3.0-700.0*x^3.0+432.0*x^2.0-756.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.1111111111111112*x^9.0-2.0*x^9.0+0.6666666666666666*x^6.0-5.833333333333333*x^6.0+7.2*x^5.0-12.6*x^5.0+25.0*x+36.0*x^2.0-45.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="1.1111111111111112/10.0*x^10.0-2.0/10.0*x^10.0+0.6666666666666666/7.0*x^7.0-5.833333333333333/7.0*x^7.0+7.2/6.0*x^6.0-12.6/6.0*x^6.0+25.0/2.0*x^2.0+36.0/3.0*x^3.0-45.0/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.11111111111111112*x^10.0-0.2*x^10.0+0.09523809523809523*x^7.0-0.8333333333333333*x^7.0+1.2*x^6.0-2.1*x^6.0+12.5*x^2.0+12.0*x^3.0-15.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "560.0*x^6.0-1008.0*x^6.0+80.0*x^3.0-700.0*x^3.0+432.0*x^2.0-756.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "560.0*6.0*x^5.0-1008.0*6.0*x^5.0+80.0*3.0*x^2.0-700.0*3.0*x^2.0+432.0*2.0*x^1.0-756.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3360.0*x^5.0-6048.0*x^5.0+240.0*x^2.0-2100.0*x^2.0+864.0*x-1512.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.11111111111111112*x^10.0-0.2*x^10.0+0.09523809523809523*x^7.0-0.8333333333333333*x^7.0+1.2*x^6.0-2.1*x^6.0+12.5*x^2.0+12.0*x^3.0-15.0*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.11111111111111112/11.0*x^11.0-0.2/11.0*x^11.0+0.09523809523809523/8.0*x^8.0-0.8333333333333333/8.0*x^8.0+1.2/7.0*x^7.0-2.1/7.0*x^7.0+12.5/3.0*x^3.0+12.0/4.0*x^4.0-15.0/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.010101010101010102*x^11.0-0.018181818181818184*x^11.0+0.011904761904761904*x^8.0-0.10416666666666666*x^8.0+0.17142857142857143*x^7.0-0.3*x^7.0+4.166666666666667*x^3.0+3.0*x^4.0-3.75*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3360.0*x^5.0-6048.0*x^5.0+240.0*x^2.0-2100.0*x^2.0+864.0*x-1512.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3360.0*5.0*x^4.0-6048.0*5.0*x^4.0+240.0*2.0*x^1.0-2100.0*2.0*x^1.0+864.0-1512.0";
        UsageCalculus.EqnSolArrayMap2[4] = "16800.0*x^4.0-30240.0*x^4.0+480.0*x-4200.0*x+864.0-1512.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.010101010101010102*x^11.0-0.018181818181818184*x^11.0+0.011904761904761904*x^8.0-0.10416666666666666*x^8.0+0.17142857142857143*x^7.0-0.3*x^7.0+4.166666666666667*x^3.0+3.0*x^4.0-3.75*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.010101010101010102/12.0*x^12.0-0.018181818181818184/12.0*x^12.0+0.011904761904761904/9.0*x^9.0-0.10416666666666666/9.0*x^9.0+0.17142857142857143/8.0*x^8.0-0.3/8.0*x^8.0+4.166666666666667/4.0*x^4.0+3.0/5.0*x^5.0-3.75/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508418E-4*x^12.0-0.0015151515151515154*x^12.0+0.0013227513227513227*x^9.0-0.011574074074074073*x^9.0+0.02142857142857143*x^8.0-0.0375*x^8.0+1.0416666666666667*x^4.0+0.6*x^5.0-0.75*x^5.0-1.0416666666666667*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;

        UsageCalculus.EqnSolArrayMap1[5] = "16800.0*x^4.0-30240.0*x^4.0+480.0*x-4200.0*x+864.0-1512.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "16800.0*4.0*x^3.0-30240.0*4.0*x^3.0+480.0-4200.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "67200.0*x^3.0-120960.0*x^3.0+480.0-4200.0-";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "67200.0*x^3.0-120960.0*x^3.0+480.0-4200.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "67200.0*3.0*x^2.0-120960.0*3.0*x^2.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "201600.0*x^2.0-362880.0*x^2.0-";
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

    public static void TestCaseDiff5034() {
        //Only Trailing Constant
        //Test Case 5034
        UsageCalculus.TestCase = 5034;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values Trailing Constant Dropped";
        UsageCalculus.EqnUnderTest = "x^7.0*8.0-x^7.0*8.0+x^4.0*5.0-x^4.0*5.0+x^3.0-x^3.0*4.0";
        UsageCalculus.TestCaseName = "Standard:Basic Equation:With  Trailing Coefficients alone";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+x^3.0-4.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*7.0*x^6.0-8.0*7.0*x^6.0+5.0*4.0*x^3.0-5.0*4.0*x^3.0+3.0*x^2.0-4.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+3.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="8.0*x^7.0-8.0*x^7.0+5.0*x^4.0-5.0*x^4.0+x^3.0-4.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="8.0/8.0*x^8.0-8.0/8.0*x^8.0+5.0/5.0*x^5.0-5.0/5.0*x^5.0+1.0/4.0*x^4.0-4.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[1]="x^8.0-x^8.0+x^5.0-x^5.0+1.0/4.0*x^4.0-x^4.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "56.0*x^6.0-56.0*x^6.0+20.0*x^3.0-20.0*x^3.0+3.0*x^2.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "56.0*6.0*x^5.0-56.0*6.0*x^5.0+20.0*3.0*x^2.0-20.0*3.0*x^2.0+3.0*2.0*x^1.0-12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[2] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+6.0*x-24.0*x";
        UsageCalculus.EqnSolArrayMap3[2]="x^8.0-x^8.0+x^5.0-x^5.0+1.0/4.0*x^4.0-x^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+0.25/5.0*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayMap4[2]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+0.05*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "336.0*x^5.0-336.0*x^5.0+60.0*x^2.0-60.0*x^2.0+6.0*x-24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "336.0*5.0*x^4.0-336.0*5.0*x^4.0+60.0*2.0*x^1.0-60.0*2.0*x^1.0+6.0-24.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+6.0-24.0";
        UsageCalculus.EqnSolArrayMap3[3]="1.0/9.0*x^9.0-1.0/9.0*x^9.0+1.0/6.0*x^6.0-1.0/6.0*x^6.0+0.05*x^5.0-1.0/5.0*x^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.1111111111111111/10.0*x^10.0-0.1111111111111111/10.0*x^10.0+0.16666666666666666/7.0*x^7.0-0.16666666666666666/7.0*x^7.0+0.05/6.0*x^6.0-0.2/6.0*x^6.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.008333333333333333*x^6.0-0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^4.0-1680.0*x^4.0+120.0*x-120.0*x+6.0-24.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*4.0*x^3.0-1680.0*4.0*x^3.0+120.0-120.0+0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "6720.0*x^3.0-6720.0*x^3.0+120.0-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.01111111111111111*x^10.0-0.01111111111111111*x^10.0+0.023809523809523808*x^7.0-0.023809523809523808*x^7.0+0.008333333333333333*x^6.0-0.03333333333333333*x^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.01111111111111111/11.0*x^11.0-0.01111111111111111/11.0*x^11.0+0.023809523809523808/8.0*x^8.0-0.023809523809523808/8.0*x^8.0+0.008333333333333333/7.0*x^7.0-0.03333333333333333/7.0*x^7.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0010101010101010099*x^11.0-0.0010101010101010099*x^11.0+0.002976190476190476*x^8.0-0.002976190476190476*x^8.0+0.0011904761904761904*x^7.0-0.0047619047619047615*x^7.0";
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

       
    //
    //
    //
    public static void TestCaseDiff5035() {
        //Test Case 5035
        UsageCalculus.TestCase = 5035;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        //Usage.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        UsageCalculus.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1] = "x^9.0+x^10.0+x^7.0+x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+1.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+1.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+5.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+5.0*4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+1.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.16666666666666666/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.023809523809523808*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+20.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+20.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.023809523809523808*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.023809523809523808/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.002976190476190476*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.002976190476190476*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="7.575757575757577E-4/13.0*x^13.0+5.827505827505828E-4/14.0*x^14.0+0.0013888888888888887/11.0*x^11.0+0.002976190476190476/9.0*x^9.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.827505827505828E-5*x^13.0+4.1625041625041625E-5*x^14.0+1.2626262626262624E-4*x^11.0+3.3068783068783067E-4*x^9.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5036() {
        //Test Case 5036
        UsageCalculus.TestCase = 5036;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "x^9*x+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Complex: Multiple x variables in 1 term with Starting Term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "10.0*9.0*x^8.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*x^12.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "90.0*8.0*x^7.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*x^13.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="5.827505827505828E-4*x^13.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*7.0*x^6.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5040.0*x^6.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="5.827505827505828E-4*x^13.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="5.827505827505828E-4/14.0*x^14.0+5.827505827505828E-4/14.0*x^14.0+0.0013888888888888887/11.0*x^11.0+0.005952380952380952/9.0*x^9.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.1625041625041625E-5*x^14.0+4.1625041625041625E-5*x^14.0+1.2626262626262624E-4*x^11.0+6.613756613756613E-4*x^9.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5037() {
        //Test Case 5037
        UsageCalculus.TestCase = 5037;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "x^9+ x^8*x*x + x*x*x^5 + x*2*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 4;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5038() {
        //Test Case 5038
        UsageCalculus.TestCase = 5038;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1.0*x^9*x+ x^8*x*x + 2.0/2.0*x*x*x^5 + x*2*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Complex: Multiple x variables in 1 term with Starting Term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 4;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^10.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "10.0*x^9.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "10.0*9.0*x^8.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/11.0*x^11.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.09090909090909091/12.0*x^12.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "90.0*x^8.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "90.0*8.0*x^7.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.007575757575757576*x^12.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.007575757575757576/13.0*x^13.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="5.827505827505828E-4*x^13.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0*x^7.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*7.0*x^6.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "5040.0*x^6.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5039() {
        //Test Case 5039
        UsageCalculus.TestCase = 5039;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*x^8*5.0 + 2*x^5 + 3*x^4*5 - 2*x*3 - 25.0*24*23";
        UsageCalculus.TestCaseName = "Simple: Multiple Coeffiecients in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*x^8.0+2.0*x^5.0+15.0*x^4.0-6.0*x-13800.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+2.0*5.0*x^4.0+15.0*4.0*x^3.0-6.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*x^7.0+10.0*x^4.0+60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*x^8.0+2.0*x^5.0+15.0*x^4.0-6.0*x-13800.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+2.0/6.0*x^6.0+15.0/5.0*x^5.0-6.0/2.0*x^2.0-13800.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0+3.0*x^5.0-3.0*x^2.0-13800.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*x^7.0+10.0*x^4.0+60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+10.0*4.0*x^3.0+60.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*x^6.0+40.0*x^3.0+180.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0+3.0*x^5.0-3.0*x^2.0-13800.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0+3.0/6.0*x^6.0-3.0/3.0*x^3.0-13800.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0+0.5*x^6.0-x^3.0-6900.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*x^6.0+40.0*x^3.0+180.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+40.0*3.0*x^2.0+180.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^5.0+120.0*x^2.0+360.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0+0.5*x^6.0-x^3.0-6900.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0+0.5/7.0*x^7.0-1.0/4.0*x^4.0-6900.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0+0.07142857142857142*x^7.0-1.0/4.0*x^4.0-2300.0*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^5.0+120.0*x^2.0+360.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+120.0*2.0*x^1.0+360.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*x^4.0+240.0*x+360.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0+0.07142857142857142*x^7.0-1.0/4.0*x^4.0-2300.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0+0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0-2300.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0+0.008928571428571428*x^8.0-0.05*x^5.0-575.0*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "8400.0*x^4.0+240.0*x+360.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "8400.0*4.0*x^3.0+240.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "33600.0*x^3.0+240.0+";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "33600.0*x^3.0+240.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "33600.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "100800.0*x^2.0+";
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
    public static void TestCaseDiff5040() {
        //Test Case 5040
        UsageCalculus.TestCase = 5040;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-1.0*x^8*5.0 + 2*x^5 -3*x^4*5 - 2*x*3 - 25.0*-24*23";
        UsageCalculus.TestCaseName = "Complex: Multiple Coeffiecients in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*x^7.0+2.0*5.0*x^4.0-15.0*4.0*x^3.0-6.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*x^9.0+2.0/6.0*x^6.0-15.0/5.0*x^5.0-6.0/2.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*x^6.0+10.0*4.0*x^3.0-60.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0-3.0/6.0*x^6.0-3.0/3.0*x^3.0+13800.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*x^5.0+40.0*3.0*x^2.0-180.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*x^5.0+120.0*x^2.0-360.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0-0.5/7.0*x^7.0-1.0/4.0*x^4.0+6900.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-1.0/4.0*x^4.0+2300.0*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*x^5.0+120.0*x^2.0-360.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*x^4.0+120.0*2.0*x^1.0-360.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*x^4.0+240.0*x-360.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-1.0/4.0*x^4.0+2300.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0-0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0+2300.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0-0.008928571428571428*x^8.0-0.05*x^5.0+575.0*x^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*x^4.0+240.0*x-360.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*x^3.0+240.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*x^3.0+240.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*x^3.0+240.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*x^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*x^2.0";
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
    public static void TestCaseDiff5041() {
        //Test Case 5041
        UsageCalculus.TestCase = 5041;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        //Usage.EqnUnderTest = "-1.0*x^8*(-5.0) + 2*x^5 + -3*x^4*(-5) - +2*x*+3 - 25.0*(-24)*+23";
        UsageCalculus.EqnUnderTest = "-1.0*x^8*-5.0 + 2*x^5 + 3*x^4*-5 - 2*x*3 - 25.0*-24*+23";
        UsageCalculus.TestCaseName = "Complex: Multiple Coeffiecients in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+2.0*5.0*x^4.0-15.0*4.0*x^3.0-6.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*x^8.0+2.0*x^5.0-15.0*x^4.0-6.0*x+13800.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+2.0/6.0*x^6.0-15.0/5.0*x^5.0-6.0/2.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*x^7.0+10.0*x^4.0-60.0*x^3.0-6.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+10.0*4.0*x^3.0-60.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.3333333333333333*x^6.0-3.0*x^5.0-3.0*x^2.0+13800.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.3333333333333333/7.0*x^7.0-3.0/6.0*x^6.0-3.0/3.0*x^3.0+13800.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*x^6.0+40.0*x^3.0-180.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+40.0*3.0*x^2.0-180.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^5.0+120.0*x^2.0-360.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.047619047619047616*x^7.0-0.5*x^6.0-x^3.0+6900.0*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.047619047619047616/8.0*x^8.0-0.5/7.0*x^7.0-1.0/4.0*x^4.0+6900.0/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-1.0/4.0*x^4.0+2300.0*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^5.0+120.0*x^2.0-360.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+120.0*2.0*x^1.0-360.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*x^4.0+240.0*x-360.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.005952380952380952*x^8.0-0.07142857142857142*x^7.0-1.0/4.0*x^4.0+2300.0*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.005952380952380952/9.0*x^9.0-0.07142857142857142/8.0*x^8.0-0.25/5.0*x^5.0+2300.0/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+6.613756613756613E-4*x^9.0-0.008928571428571428*x^8.0-0.05*x^5.0+575.0*x^4.0";
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
    public static void TestCaseDiff5042() {
        //Test Case 5042
        UsageCalculus.TestCase = 5042;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "1.0/1.0*x^9+ x^8*x*x + 2.0/2.0*x*x*x^5 + x*2*x^4 - x - 25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 4;
        
        UsageCalculus.EqnSolArrayMap1[1] = "x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+2.0*5.0*x^4.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="x^9.0+x^10.0+x^7.0+2.0*x^5.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+2.0/6.0*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "9.0*x^8.0+10.0*x^9.0+7.0*x^6.0+10.0*x^4.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "9.0*8.0*x^7.0+10.0*9.0*x^8.0+7.0*6.0*x^5.0+10.0*4.0*x^3.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/10.0*x^10.0+1.0/11.0*x^11.0+1.0/8.0*x^8.0+0.3333333333333333*x^6.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1/11.0*x^11.0+0.09090909090909091/12.0*x^12.0+0.125/9.0*x^9.0+0.3333333333333333/7.0*x^7.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0*x^7.0+90.0*x^8.0+42.0*x^5.0+40.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*7.0*x^6.0+90.0*8.0*x^7.0+42.0*5.0*x^4.0+40.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.009090909090909092*x^11.0+0.007575757575757576*x^12.0+0.013888888888888888*x^9.0+0.047619047619047616*x^7.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.009090909090909092/12.0*x^12.0+0.007575757575757576/13.0*x^13.0+0.013888888888888888/10.0*x^10.0+0.047619047619047616/8.0*x^8.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="7.575757575757577E-4*x^12.0+5.827505827505828E-4*x^13.0+0.0013888888888888887*x^10.0+0.005952380952380952*x^8.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "504.0*x^6.0+720.0*x^7.0+210.0*x^4.0+120.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "504.0*6.0*x^5.0+720.0*7.0*x^6.0+210.0*4.0*x^3.0+120.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "3024.0*x^5.0+5040.0*x^6.0+840.0*x^3.0+240.0*x";
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
    public static void TestCaseDiff5043() {
        //Test Case 5043
        UsageCalculus.TestCase = 5043;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "Infinity*x^9+ Infinity*x^8*x*x + Infinity*x*x*x^5 + Infinity*x*2*x^4 - Infinity*x - Infinity*25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity*x^9.0+Infinity*x^10.0+Infinity*x^7.0+Infinity*2.0*x^5.0-Infinity*x-Infinity*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*9.0*x^8.0+Infinity*10.0*x^9.0+Infinity*7.0*x^6.0+Infinity*2.0*5.0*x^4.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "Infinity*9.0*x^8.0+Infinity*10.0*x^9.0+Infinity*7.0*x^6.0+Infinity*10.0*x^4.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity*x^9.0+Infinity*x^10.0+Infinity*x^7.0+Infinity*2.0*x^5.0-Infinity*x-Infinity*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/10.0*x^10.0+Infinity/11.0*x^11.0+Infinity/8.0*x^8.0+Infinity*2.0/6.0*x^6.0-Infinity/2.0*x^2.0-Infinity*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="Infinity/10.0*x^10.0+Infinity/11.0*x^11.0+Infinity/8.0*x^8.0+Infinity*0.3333333333333333*x^6.0-Infinity/2.0*x^2.0-Infinity*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "Infinity*9.0*x^8.0+Infinity*10.0*x^9.0+Infinity*7.0*x^6.0+Infinity*10.0*x^4.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "Infinity*9.0*8.0*x^7.0+Infinity*10.0*9.0*x^8.0+Infinity*7.0*6.0*x^5.0+Infinity*10.0*4.0*x^3.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*72.0*x^7.0+Infinity*90.0*x^8.0+Infinity*42.0*x^5.0+Infinity*40.0*x^3.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[2]="Infinity/10.0*x^10.0+Infinity/11.0*x^11.0+Infinity/8.0*x^8.0+Infinity*0.3333333333333333*x^6.0-Infinity/2.0*x^2.0-Infinity*25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="Infinity/10.0/11.0*x^11.0+Infinity/11.0/12.0*x^12.0+Infinity/8.0/9.0*x^9.0+Infinity*0.3333333333333333/7.0*x^7.0-Infinity/2.0/3.0*x^3.0-Infinity*25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="Infinity/110.0*x^11.0+Infinity/132.0*x^12.0+Infinity/72.0*x^9.0+Infinity*0.047619047619047616*x^7.0-Infinity/6.0*x^3.0-Infinity*12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*72.0*x^7.0+Infinity*90.0*x^8.0+Infinity*42.0*x^5.0+Infinity*40.0*x^3.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*72.0*7.0*x^6.0+Infinity*90.0*8.0*x^7.0+Infinity*42.0*5.0*x^4.0+Infinity*40.0*3.0*x^2.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "Infinity*504.0*x^6.0+Infinity*720.0*x^7.0+Infinity*210.0*x^4.0+Infinity*120.0*x^2.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[3]="Infinity/110.0*x^11.0+Infinity/132.0*x^12.0+Infinity/72.0*x^9.0+Infinity*0.047619047619047616*x^7.0-Infinity/6.0*x^3.0-Infinity*12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="Infinity/110.0/12.0*x^12.0+Infinity/132.0/13.0*x^13.0+Infinity/72.0/10.0*x^10.0+Infinity*0.047619047619047616/8.0*x^8.0-Infinity/6.0/4.0*x^4.0-Infinity*12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="Infinity/1320.0*x^12.0+Infinity/1716.0*x^13.0+Infinity/720.0*x^10.0+Infinity*0.005952380952380952*x^8.0-Infinity/24.0*x^4.0-Infinity*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "Infinity*504.0*x^6.0+Infinity*720.0*x^7.0+Infinity*210.0*x^4.0+Infinity*120.0*x^2.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "Infinity*504.0*6.0*x^5.0+Infinity*720.0*7.0*x^6.0+Infinity*210.0*4.0*x^3.0+Infinity*120.0*2.0*x^1.0-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*3024.0*x^5.0+Infinity*5040.0*x^6.0+Infinity*840.0*x^3.0+Infinity*240.0*x-Infinity-Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[4]="Infinity/1320.0*x^12.0+Infinity/1716.0*x^13.0+Infinity/720.0*x^10.0+Infinity*0.005952380952380952*x^8.0-Infinity/24.0*x^4.0-Infinity*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="Infinity/1320.0/13.0*x^13.0+Infinity/1716.0/14.0*x^14.0+Infinity/720.0/11.0*x^11.0+Infinity*0.005952380952380952/9.0*x^9.0-Infinity/24.0/5.0*x^5.0-Infinity*4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="Infinity/17160.0*x^13.0+Infinity/24024.0*x^14.0+Infinity/7920.0*x^11.0+Infinity*6.613756613756613E-4*x^9.0-Infinity/120.0*x^5.0-Infinity*1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5044() {
        //Test Case 5044
        UsageCalculus.TestCase = 5044;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "Infinity/x^9+ Infinity/x^8*x*x + Infinity/x*x*x^5 + Infinity/x*2*x^4 - Infinity/x - Infinity/25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "Infinity/x^9.0+Infinity/x^10.0+Infinity/x^7.0+Infinity/2.0*x^5.0-Infinity/x-Infinity/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "Infinity*-9.0/x^10.0+Infinity*-10.0/x^11.0+Infinity*-7.0/x^8.0+Infinity/2.0*5.0*x^4.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-Infinity*9.0/x^10.0-Infinity*10.0/x^11.0-Infinity*7.0/x^8.0+Infinity/2.5*x^4.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="Infinity/x^9.0+Infinity/x^10.0+Infinity/x^7.0+Infinity/2.0*x^5.0-Infinity/x-Infinity/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="Infinity/-8.0/x^8.0+Infinity/-9.0/x^9.0+Infinity/-6.0/x^6.0+Infinity/2.0/6.0*x^6.0-Infinity*ln(x)-Infinity/25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-Infinity/8.0/x^8.0-Infinity/9.0/x^9.0-Infinity/6.0/x^6.0+Infinity/12.0*x^6.0-Infinity*ln(x)-Infinity/25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-Infinity*9.0/x^10.0-Infinity*10.0/x^11.0-Infinity*7.0/x^8.0+Infinity/2.5*x^4.0+Infinity/x^2.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-Infinity*9.0*-10.0/x^11.0-Infinity*10.0*-11.0/x^12.0-Infinity*7.0*-8.0/x^9.0+Infinity/2.5*4.0*x^3.0+Infinity*-2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "Infinity*90.0/x^11.0+Infinity*110.0/x^12.0+Infinity*56.0/x^9.0+Infinity/1.6*x^3.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "Infinity*90.0/x^11.0+Infinity*110.0/x^12.0+Infinity*56.0/x^9.0+Infinity/1.6*x^3.0-Infinity*2.0/x^3.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "Infinity*90.0*-11.0/x^12.0+Infinity*110.0*-12.0/x^13.0+Infinity*56.0*-9.0/x^10.0+Infinity/1.6*3.0*x^2.0-Infinity*2.0*-3.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-Infinity*990.0/x^12.0-Infinity*1320.0/x^13.0-Infinity*504.0/x^10.0+Infinity/1.875*x^2.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-Infinity*990.0/x^12.0-Infinity*1320.0/x^13.0-Infinity*504.0/x^10.0+Infinity/1.875*x^2.0+Infinity*6.0/x^4.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-Infinity*990.0*-12.0/x^13.0-Infinity*1320.0*-13.0/x^14.0-Infinity*504.0*-10.0/x^11.0+Infinity/1.875*2.0*x^1.0+Infinity*6.0*-4.0/x^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "Infinity*11880.0/x^13.0+Infinity*17160.0/x^14.0+Infinity*5040.0/x^11.0+Infinity/1.0666666666666667*x-Infinity*24.0/x^5.0-Infinity";
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
    public static void TestCaseDiff5045() {
        //Test Case 5045
        UsageCalculus.TestCase = 5045;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "NaN/x^9+ NaN/x^8*x*x + NaN/x*x*x^5 + NaN/x*2*x^4 - NaN/x - NaN/25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN/x^9.0+NaN/x^10.0+NaN/x^7.0+NaN/2.0*x^5.0-NaN/x-NaN/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*-9.0/x^10.0+NaN*-10.0/x^11.0+NaN*-7.0/x^8.0+NaN/2.0*5.0*x^4.0+NaN/x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "-NaN*9.0/x^10.0-NaN*10.0/x^11.0-NaN*7.0/x^8.0+NaN/2.5*x^4.0+NaN/x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap3[1]="NaN/x^9.0+NaN/x^10.0+NaN/x^7.0+NaN/2.0*x^5.0-NaN/x-NaN/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN/-8.0/x^8.0+NaN/-9.0/x^9.0+NaN/-6.0/x^6.0+NaN/2.0/6.0*x^6.0-NaN*ln(x)-NaN/25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-NaN/8.0/x^8.0-NaN/9.0/x^9.0-NaN/6.0/x^6.0+NaN/12.0*x^6.0-NaN*ln(x)-NaN/25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-NaN*9.0/x^10.0-NaN*10.0/x^11.0-NaN*7.0/x^8.0+NaN/2.5*x^4.0+NaN/x^2.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-NaN*9.0*-10.0/x^11.0-NaN*10.0*-11.0/x^12.0-NaN*7.0*-8.0/x^9.0+NaN/2.5*4.0*x^3.0+NaN*-2.0/x^3.0-NaN";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*90.0/x^11.0+NaN*110.0/x^12.0+NaN*56.0/x^9.0+NaN/1.6*x^3.0-NaN*2.0/x^3.0-NaN";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*90.0/x^11.0+NaN*110.0/x^12.0+NaN*56.0/x^9.0+NaN/1.6*x^3.0-NaN*2.0/x^3.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*90.0*-11.0/x^12.0+NaN*110.0*-12.0/x^13.0+NaN*56.0*-9.0/x^10.0+NaN/1.6*3.0*x^2.0-NaN*2.0*-3.0/x^4.0-NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "-NaN*990.0/x^12.0-NaN*1320.0/x^13.0-NaN*504.0/x^10.0+NaN/1.875*x^2.0+NaN*6.0/x^4.0-NaN";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-NaN*990.0/x^12.0-NaN*1320.0/x^13.0-NaN*504.0/x^10.0+NaN/1.875*x^2.0+NaN*6.0/x^4.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-NaN*990.0*-12.0/x^13.0-NaN*1320.0*-13.0/x^14.0-NaN*504.0*-10.0/x^11.0+NaN/1.875*2.0*x^1.0+NaN*6.0*-4.0/x^5.0-NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*11880.0/x^13.0+NaN*17160.0/x^14.0+NaN*5040.0/x^11.0+NaN/1.0666666666666667*x-NaN*24.0/x^5.0-NaN";
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
    public static void TestCaseDiff5046() {
        //Test Case 5046
        UsageCalculus.TestCase = 5046;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "NaN*x^9+ NaN*x^8*x*x + NaN*x*x*x^5 + NaN*x*2*x^4 - NaN*x - NaN*25.0";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN*x^9.0+NaN*x^10.0+NaN*x^7.0+NaN*2.0*x^5.0-NaN*x-NaN*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*9.0*x^8.0+NaN*10.0*x^9.0+NaN*7.0*x^6.0+NaN*2.0*5.0*x^4.0-NaN-NaN*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "NaN*9.0*x^8.0+NaN*10.0*x^9.0+NaN*7.0*x^6.0+NaN*10.0*x^4.0-NaN-NaN*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="NaN*x^9.0+NaN*x^10.0+NaN*x^7.0+NaN*2.0*x^5.0-NaN*x-NaN*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN/10.0*x^10.0+NaN/11.0*x^11.0+NaN/8.0*x^8.0+NaN*2.0/6.0*x^6.0-NaN/2.0*x^2.0-NaN*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="NaN/10.0*x^10.0+NaN/11.0*x^11.0+NaN/8.0*x^8.0+NaN*0.3333333333333333*x^6.0-NaN/2.0*x^2.0-NaN*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "NaN*9.0*x^8.0+NaN*10.0*x^9.0+NaN*7.0*x^6.0+NaN*10.0*x^4.0-NaN-NaN*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "NaN*9.0*8.0*x^7.0+NaN*10.0*9.0*x^8.0+NaN*7.0*6.0*x^5.0+NaN*10.0*4.0*x^3.0-NaN-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*72.0*x^7.0+NaN*90.0*x^8.0+NaN*42.0*x^5.0+NaN*40.0*x^3.0-NaN";
        UsageCalculus.EqnSolArrayMap3[2]="NaN/10.0*x^10.0+NaN/11.0*x^11.0+NaN/8.0*x^8.0+NaN*0.3333333333333333*x^6.0-NaN/2.0*x^2.0-NaN*25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="NaN/10.0/11.0*x^11.0+NaN/11.0/12.0*x^12.0+NaN/8.0/9.0*x^9.0+NaN*0.3333333333333333/7.0*x^7.0-NaN/2.0/3.0*x^3.0-NaN*25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="NaN/110.0*x^11.0+NaN/132.0*x^12.0+NaN/72.0*x^9.0+NaN*0.047619047619047616*x^7.0-NaN/6.0*x^3.0-NaN*12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*72.0*x^7.0+NaN*90.0*x^8.0+NaN*42.0*x^5.0+NaN*40.0*x^3.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*72.0*7.0*x^6.0+NaN*90.0*8.0*x^7.0+NaN*42.0*5.0*x^4.0+NaN*40.0*3.0*x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "NaN*504.0*x^6.0+NaN*720.0*x^7.0+NaN*210.0*x^4.0+NaN*120.0*x^2.0-NaN";
        UsageCalculus.EqnSolArrayMap3[3]="NaN/110.0*x^11.0+NaN/132.0*x^12.0+NaN/72.0*x^9.0+NaN*0.047619047619047616*x^7.0-NaN/6.0*x^3.0-NaN*12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="NaN/110.0/12.0*x^12.0+NaN/132.0/13.0*x^13.0+NaN/72.0/10.0*x^10.0+NaN*0.047619047619047616/8.0*x^8.0-NaN/6.0/4.0*x^4.0-NaN*12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="NaN/1320.0*x^12.0+NaN/1716.0*x^13.0+NaN/720.0*x^10.0+NaN*0.005952380952380952*x^8.0-NaN/24.0*x^4.0-NaN*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "NaN*504.0*x^6.0+NaN*720.0*x^7.0+NaN*210.0*x^4.0+NaN*120.0*x^2.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "NaN*504.0*6.0*x^5.0+NaN*720.0*7.0*x^6.0+NaN*210.0*4.0*x^3.0+NaN*120.0*2.0*x^1.0-NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*3024.0*x^5.0+NaN*5040.0*x^6.0+NaN*840.0*x^3.0+NaN*240.0*x-NaN";
        UsageCalculus.EqnSolArrayMap3[4]="NaN/1320.0*x^12.0+NaN/1716.0*x^13.0+NaN/720.0*x^10.0+NaN*0.005952380952380952*x^8.0-NaN/24.0*x^4.0-NaN*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="NaN/1320.0/13.0*x^13.0+NaN/1716.0/14.0*x^14.0+NaN/720.0/11.0*x^11.0+NaN*0.005952380952380952/9.0*x^9.0-NaN/24.0/5.0*x^5.0-NaN*4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="NaN/17160.0*x^13.0+NaN/24024.0*x^14.0+NaN/7920.0*x^11.0+NaN*6.613756613756613E-4*x^9.0-NaN/120.0*x^5.0-NaN*1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5047() {
        //Test Case 5047
        UsageCalculus.TestCase = 5047;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values";
        UsageCalculus.EqnUnderTest = "NaN*x^9*Infinity+ NaN*x^8*x*x*Infinity + NaN*x*x*x^5*Infinity + NaN*x*2*x^4*Infinity - NaN*x*Infinity - NaN*25.0*Infinity";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN*Infinity*x^9.0+NaN*Infinity*x^10.0+NaN*Infinity*x^7.0+NaN*Infinity*2.0*x^5.0-NaN*Infinity*x-NaN*Infinity*25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*Infinity*9.0*x^8.0+NaN*Infinity*10.0*x^9.0+NaN*Infinity*7.0*x^6.0+NaN*Infinity*2.0*5.0*x^4.0-NaN*Infinity-NaN*Infinity*0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "NaN*Infinity*9.0*x^8.0+NaN*Infinity*10.0*x^9.0+NaN*Infinity*7.0*x^6.0+NaN*Infinity*10.0*x^4.0-NaN*Infinity-NaN*Infinity*0.0";
        UsageCalculus.EqnSolArrayMap3[1]="NaN*Infinity*x^9.0+NaN*Infinity*x^10.0+NaN*Infinity*x^7.0+NaN*Infinity*2.0*x^5.0-NaN*Infinity*x-NaN*Infinity*25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN*Infinity/10.0*x^10.0+NaN*Infinity/11.0*x^11.0+NaN*Infinity/8.0*x^8.0+NaN*2.0/6.0*x^6.0-NaN*x^2.0*1.0/2.0-NaN*25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="NaN*Infinity/10.0*x^10.0+NaN*Infinity/11.0*x^11.0+NaN*Infinity/8.0*x^8.0+NaN*0.3333333333333333*x^6.0-NaN*2.0*x^2.0-NaN*25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "NaN*Infinity*9.0*x^8.0+NaN*Infinity*10.0*x^9.0+NaN*Infinity*7.0*x^6.0+NaN*Infinity*10.0*x^4.0-NaN*Infinity-NaN*Infinity*0.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "NaN*Infinity*9.0*8.0*x^7.0+NaN*Infinity*10.0*9.0*x^8.0+NaN*Infinity*7.0*6.0*x^5.0+NaN*Infinity*10.0*4.0*x^3.0-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*Infinity*72.0*x^7.0+NaN*Infinity*90.0*x^8.0+NaN*Infinity*42.0*x^5.0+NaN*Infinity*40.0*x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="NaN*Infinity/10.0*x^10.0+NaN*Infinity/11.0*x^11.0+NaN*Infinity/8.0*x^8.0+NaN*0.3333333333333333*x^6.0-NaN*2.0*x^2.0-NaN*25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="NaN/10.0/11.0*x^11.0+NaN/11.0/12.0*x^12.0+NaN/8.0/9.0*x^9.0+NaN*0.3333333333333333/7.0*x^7.0-NaN*2.0/3.0*x^3.0-NaN*25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="NaN/110.0*x^11.0+NaN/132.0*x^12.0+NaN/72.0*x^9.0+NaN*0.047619047619047616*x^7.0-NaN*0.6666666666666666*x^3.0-NaN*12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*Infinity*72.0*x^7.0+NaN*Infinity*90.0*x^8.0+NaN*Infinity*42.0*x^5.0+NaN*Infinity*40.0*x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*Infinity*72.0*7.0*x^6.0+NaN*Infinity*90.0*8.0*x^7.0+NaN*Infinity*42.0*5.0*x^4.0+NaN*Infinity*40.0*3.0*x^2.0";
        UsageCalculus.EqnSolArrayMap2[3] = "NaN*Infinity*504.0*x^6.0+NaN*Infinity*720.0*x^7.0+NaN*Infinity*210.0*x^4.0+NaN*Infinity*120.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="NaN/110.0*x^11.0+NaN/132.0*x^12.0+NaN/72.0*x^9.0+NaN*0.047619047619047616*x^7.0-NaN*0.6666666666666666*x^3.0-NaN*12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="NaN/110.0/12.0*x^12.0+NaN/132.0/13.0*x^13.0+NaN/72.0/10.0*x^10.0+NaN*0.047619047619047616/8.0*x^8.0-NaN*0.6666666666666666/4.0*x^4.0-NaN*12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="NaN/1320.0*x^12.0+NaN/1716.0*x^13.0+NaN/720.0*x^10.0+NaN*0.005952380952380952*x^8.0-NaN*0.16666666666666666*x^4.0-NaN*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "NaN*Infinity*504.0*x^6.0+NaN*Infinity*720.0*x^7.0+NaN*Infinity*210.0*x^4.0+NaN*Infinity*120.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "NaN*Infinity*504.0*6.0*x^5.0+NaN*Infinity*720.0*7.0*x^6.0+NaN*Infinity*210.0*4.0*x^3.0+NaN*Infinity*120.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*Infinity*3024.0*x^5.0+NaN*Infinity*5040.0*x^6.0+NaN*Infinity*840.0*x^3.0+NaN*Infinity*240.0*x";
        UsageCalculus.EqnSolArrayMap3[4]="NaN/1320.0*x^12.0+NaN/1716.0*x^13.0+NaN/720.0*x^10.0+NaN*0.005952380952380952*x^8.0-NaN*0.16666666666666666*x^4.0-NaN*4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="NaN/1320.0/13.0*x^13.0+NaN/1716.0/14.0*x^14.0+NaN/720.0/11.0*x^11.0+NaN*0.005952380952380952/9.0*x^9.0-NaN*0.16666666666666666/5.0*x^5.0-NaN*4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="NaN/17160.0*x^13.0+NaN/24024.0*x^14.0+NaN/7920.0*x^11.0+NaN*6.613756613756613E-4*x^9.0-NaN*0.03333333333333333*x^5.0-NaN*1.0416666666666667*x^4.0";
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
    public static void TestCaseDiff5048() {
        //Test Case 5048
        UsageCalculus.TestCase = 5048;
        UsageCalculus.EqnRegressionDiffStatus = "All RED Including Values Fails for Unknown Reasons";
        UsageCalculus.EqnRegressionIntegreStatus = "All RED Including Values Fails for Unknown Reasons";
        UsageCalculus.EqnUnderTest = "2.0*NaN*x^9*Infinity+ 3.0/NaN*x^8*x*x*Infinity + 4.0*NaN*x*x*x^5*Infinity + NaN*x*2*x^4*Infinity*3.0 - NaN*x*Infinity/25.0 - NaN*25.0*Infinity/25";
        UsageCalculus.TestCaseName = "Simple: Multiple x variables in 1 term";
        UsageCalculus.TestCaseMatrixID="Unit TestCase";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 2;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN*Infinity*2.0*x^9.0+1.0/NaN*Infinity*3.0*x^10.0+NaN*Infinity*4.0*x^7.0+NaN*Infinity*6.0*x^5.0-NaN*Infinity/25.0*x-NaN*Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*Infinity*2.0*9.0*x^8.0+1.0/NaN*Infinity*3.0*x^10.0+NaN*Infinity*4.0*x^7.0+NaN*Infinity*6.0*x^5.0-NaN*Infinity/25.0-NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "NaN*Infinity*18.0*x^8.0+1.0/NaN*Infinity*3.0*x^10.0+NaN*Infinity*4.0*x^7.0+NaN*Infinity*6.0*x^5.0-NaN*Infinity/25.0-NaN";
        UsageCalculus.EqnSolArrayMap3[1]="NaN*Infinity*2.0*x^9.0+1.0/NaN*Infinity*3.0*x^10.0+NaN*Infinity*4.0*x^7.0+NaN*Infinity*6.0*x^5.0-NaN*Infinity/25.0*x-NaN*Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN*2.0/10.0*x^10.0+3.0/11.0*x^11.0+NaN*4.0/8.0*x^8.0+NaN*6.0/6.0*x^6.0-NaN/25.0/2.0*x^2.0-NaN*x";
        UsageCalculus.EqnSolArrayMap4[1]="NaN*0.2*x^10.0+0.2727272727272727*x^11.0+NaN*0.5*x^8.0+NaN*x^6.0-NaN/50.0*x^2.0-NaN*x";
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
}
