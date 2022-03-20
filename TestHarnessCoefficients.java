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
public class TestHarnessCoefficients {
    //Coefficients Test Cases
    public static void Coefficients(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapInfinity(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapInfinity(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesInfinity++;
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else if(((AlgoName.equalsIgnoreCase("mainIntegrTest"))&&(IntegralGapException(j)))||((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGapException(j))) )
        {
            MissedNumberTestCases++;
            MissedTestCasesException++;
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Exception GAP" + ConsoleColors.RESET);
        }
        else {
        switch(j){
            case 20001:
                TestCaseDiff20001();
                break;
            case 20002:
                TestCaseDiff20002();
                break;
            case 20003:
                TestCaseDiff20003();
                break;
            case 20004:
                TestCaseDiff20004();
                break;
            case 20005:
                TestCaseDiff20005();
                break;
            case 20006:
                TestCaseDiff20006();
                break;
            case 20007:
                TestCaseDiff20007();
                break;
            case 20008:
                TestCaseDiff20008();
                break;
            case 20009:
                TestCaseDiff20009();
                break;
            case 20010:
                TestCaseDiff20010();
                break;
            case 20011:
                TestCaseDiff20011();
                break;
            case 20012:
                TestCaseDiff20012();
                break;
            case 20013:
                TestCaseDiff20013();
                break;
            case 20014:
                TestCaseDiff20014();
                break;
            case 20015:
                TestCaseDiff20015();
                break;
            case 20016:
                TestCaseDiff20016();
                break;
            case 20017:
                TestCaseDiff20017();
                break;
            case 20018:
                TestCaseDiff20018();
                break;
            case 20019:
                TestCaseDiff20019();
                break;
            case 20020:
                TestCaseDiff20020();
                break;
            case 20021:
                TestCaseDiff20021();
                break;
            case 20022:
                TestCaseDiff20022();
                break;
            case 20023:
                TestCaseDiff20023();
                break;
            case 20024:
                TestCaseDiff20024();
                break;
            case 20025:
                TestCaseDiff20025();
                break;
            case 20026:
                TestCaseDiff20026();
                break;
            case 20027:
                TestCaseDiff20027();
                break;
            case 20028:
                TestCaseDiff20028();
                break;
            case 20029:
                TestCaseDiff20029();
                break;
            case 20030:
                TestCaseDiff20030();
                break;
            case 20031:
                TestCaseDiff20031();
                break;
            case 20032:
                TestCaseDiff20032();
                break;
            case 20033:
                TestCaseDiff20033();
                break;
            case 20034:
                TestCaseDiff20034();
                break;
            case 20035:
                TestCaseDiff20035();
                break;
            case 20036:
                TestCaseDiff20036();
                break;
            case 20037:
                TestCaseDiff20037();
                break;
            case 20038:
                TestCaseDiff20038();
                break;
            case 20039:
                TestCaseDiff20039();
                break;
            case 20040:
                TestCaseDiff20040();
                break;
            case 20041:
                TestCaseDiff20041();
                break;
            case 20042:
                TestCaseDiff20042();
                break;
            case 20043:
                TestCaseDiff20043();
                break;
            case 20044:
                TestCaseDiff20044();
                break;
            case 20045:
                TestCaseDiff20045();
                break;
            case 20046:
                TestCaseDiff20046();
                break;
            case 20047:
                TestCaseDiff20047();
                break;
            case 20048:
                TestCaseDiff20048();
                break;
            case 20049:
                TestCaseDiff20049();
                break;
            case 20050:
                TestCaseDiff20050();
                break;
            case 20051:
                TestCaseDiff20051();
                break;
            case 20052:
                TestCaseDiff20052();
                break;
            case 20053:
                TestCaseDiff20053();
                break;
            case 20054:
                TestCaseDiff20054();
                break;
            case 20055:
                TestCaseDiff20055();
                break;
            case 20056:
                TestCaseDiff20056();
                break;
            case 20057:
                TestCaseDiff20057();
                break;
            case 20058:
                TestCaseDiff20058();
                break;
            case 20059:
               TestCaseDiff20059();
               break;
            case 20060:
                TestCaseDiff20060();
                break;    
            case 20061:
                TestCaseDiff20061();
                break;
            case 20062:
                TestCaseDiff20062();
                break;
            case 20063:
                TestCaseDiff20063();
                break;
            case 20064:
                TestCaseDiff20064();
                break;
            case 20065:
                TestCaseDiff20065();
                break;
            case 20066:
                TestCaseDiff20066();
                break;
            case 20067:
                TestCaseDiff20067();
                break;
            case 20068:
                TestCaseDiff20068();
                break;
            case 20069:
                TestCaseDiff20069();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
    }
        }
    }
    /* **************************************** */
    //Structured Testing
    /* **************************************** */
    /* **************************************** */
    /* ***************Coefficients***************** */
    /* **************************************** */
        
        public static void TestCaseDiff20001() {
        //Test Case 20001
        UsageCalculus.TestCase = 20001;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*0.0*x^8 + 0.0*x^5 + 0.0*x^4 - 0.0*x- 25";
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
        public static void TestCaseDiff20002() {
        //Test Case 20002
        UsageCalculus.TestCase = 20002;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0*xy^8 + 0*xy^5 + 0*xy^4 - 0*xy - 25*0";
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
        public static void TestCaseDiff20003() {
        //Test Case 20003
        UsageCalculus.TestCase = 20003;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1*xyc^8 + 1*xyc^5 + 1*xyc^4 - 1*xyc - 1*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter";
        UsageCalculus.TestCaseMatrixID="Coefficients:1:3 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc^8.0+xyc^5.0+xyc^4.0-xyc-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc^8.0+xyc^5.0+xyc^4.0-xyc-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-25.0*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-25.0*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc^7.0+5.0*xyc^4.0+4.0*xyc^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc^6.0+5.0*4.0*xyc^3.0+4.0*3.0*xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc^9.0+1.0/6.0*xyc^6.0+1.0/5.0*xyc^5.0-1.0/2.0*xyc^2.0-25.0*xyc";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc^10.0+0.16666666666666666/7.0*xyc^7.0+0.2/6.0*xyc^6.0-0.5/3.0*xyc^3.0-25.0/2.0*xyc^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc^6.0+20.0*xyc^3.0+12.0*xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc^5.0+20.0*3.0*xyc^2.0+12.0*2.0*xyc^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc^10.0+0.023809523809523808*xyc^7.0+0.03333333333333333*xyc^6.0-0.16666666666666666*xyc^3.0-12.5*xyc^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc^11.0+0.023809523809523808/8.0*xyc^8.0+0.03333333333333333/7.0*xyc^7.0-0.16666666666666666/4.0*xyc^4.0-12.5/3.0*xyc^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc^5.0+60.0*xyc^2.0+24.0*xyc";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc^4.0+60.0*2.0*xyc^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc^4.0+120.0*xyc+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc^11.0+0.002976190476190476*xyc^8.0+0.0047619047619047615*xyc^7.0-0.041666666666666664*xyc^4.0-4.166666666666667*xyc^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc^12.0+0.002976190476190476/9.0*xyc^9.0+0.0047619047619047615/8.0*xyc^8.0-0.041666666666666664/5.0*xyc^5.0-4.166666666666667/4.0*xyc^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc^12.0+3.3068783068783067E-4*xyc^9.0+5.952380952380952E-4*xyc^8.0-0.008333333333333333*xyc^5.0-1.0416666666666667*xyc^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "1680.0*xyc^4.0+120.0*xyc+24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "1680.0*4.0*xyc^3.0+120.0+0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "6720.0*xyc^3.0+120.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "6720.0*xyc^3.0+120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "6720.0*3.0*xyc^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "20160.0*xyc^2.0+";
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
        public static void TestCaseDiff20004() {
        //Test Case 20004
        UsageCalculus.TestCase = 20004;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0*xyc1^8 + 1.0*xyc1^5 + 1.0*xyc1^4 - 1.0*xyc1 - 1.0*25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics";
        UsageCalculus.TestCaseMatrixID="Coefficients:1.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
    public static void TestCaseDiff20005() {
        //Test Case 20005
        UsageCalculus.TestCase = 20005;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-1.0*x^8 -1.0*x^5 -1.0*x^4 -1.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-x^8.0-x^5.0-x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0*x^7.0-5.0*x^4.0-4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0*x^7.0-5.0*x^4.0-4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-x^8.0-x^5.0-x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/9.0*x^9.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/9.0*x^9.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0*x^7.0-5.0*x^4.0-4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*7.0*x^6.0-5.0*4.0*x^3.0-4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-56.0*x^6.0-20.0*x^3.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/9.0*x^9.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1111111111111111/10.0*x^10.0-0.16666666666666666/7.0*x^7.0-0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.01111111111111111*x^10.0-0.023809523809523808*x^7.0-0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-56.0*x^6.0-20.0*x^3.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-56.0*6.0*x^5.0-20.0*3.0*x^2.0-12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-336.0*x^5.0-60.0*x^2.0-24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="-0.01111111111111111*x^10.0-0.023809523809523808*x^7.0-0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.01111111111111111/11.0*x^11.0-0.023809523809523808/8.0*x^8.0-0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0010101010101010099*x^11.0-0.002976190476190476*x^8.0-0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-336.0*x^5.0-60.0*x^2.0-24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-336.0*5.0*x^4.0-60.0*2.0*x^1.0-24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-1680.0*x^4.0-120.0*x-24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0010101010101010099*x^11.0-0.002976190476190476*x^8.0-0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0010101010101010099/12.0*x^12.0-0.002976190476190476/9.0*x^9.0-0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-8.417508417508415E-5*x^12.0-3.3068783068783067E-4*x^9.0-5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff20006() {
        //Test Case 20006
        UsageCalculus.TestCase = 20006;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-1*xy^8 -1*xy^5 -1*xy^4 -1*xy - 25*-1";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-xy^8.0-xy^5.0-xy^4.0-xy+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-xy^8.0-xy^5.0-xy^4.0-xy+25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*7.0*xy^6.0-5.0*4.0*xy^3.0-4.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-56.0*xy^6.0-20.0*xy^3.0-12.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1111111111111111/10.0*xy^10.0-0.16666666666666666/7.0*xy^7.0-0.2/6.0*xy^6.0-0.5/3.0*xy^3.0+25.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.01111111111111111*xy^10.0-0.023809523809523808*xy^7.0-0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-56.0*xy^6.0-20.0*xy^3.0-12.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-56.0*6.0*xy^5.0-20.0*3.0*xy^2.0-12.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-336.0*xy^5.0-60.0*xy^2.0-24.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="-0.01111111111111111*xy^10.0-0.023809523809523808*xy^7.0-0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.01111111111111111/11.0*xy^11.0-0.023809523809523808/8.0*xy^8.0-0.03333333333333333/7.0*xy^7.0-0.16666666666666666/4.0*xy^4.0+12.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0010101010101010099*xy^11.0-0.002976190476190476*xy^8.0-0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-336.0*xy^5.0-60.0*xy^2.0-24.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-336.0*5.0*xy^4.0-60.0*2.0*xy^1.0-24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-1680.0*xy^4.0-120.0*xy-24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0010101010101010099*xy^11.0-0.002976190476190476*xy^8.0-0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0010101010101010099/12.0*xy^12.0-0.002976190476190476/9.0*xy^9.0-0.0047619047619047615/8.0*xy^8.0-0.041666666666666664/5.0*xy^5.0+4.166666666666667/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-8.417508417508415E-5*xy^12.0-3.3068783068783067E-4*xy^9.0-5.952380952380952E-4*xy^8.0-0.008333333333333333*xy^5.0+1.0416666666666667*xy^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-1680.0*xy^4.0-120.0*xy-24.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-1680.0*4.0*xy^3.0-120.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-1680.0*4.0*xy^3.0-120.0-0.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-6720.0*xy^3.0-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-6720.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-6720.0*3.0*xy^2.0-0.0";
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
        public static void TestCaseDiff20007() {
        //Test Case 20007
        UsageCalculus.TestCase = 20007;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "5*x^8 + 5*x^5 + 5*x^4 - 5*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*x^8.0+5.0*x^5.0+5.0*x^4.0-5.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+5.0*5.0*x^4.0+5.0*4.0*x^3.0-5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*x^7.0+25.0*x^4.0+20.0*x^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*x^8.0+5.0*x^5.0+5.0*x^4.0-5.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+5.0/6.0*x^6.0+5.0/5.0*x^5.0-5.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.8333333333333334*x^6.0+x^5.0-2.5*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*x^7.0+25.0*x^4.0+20.0*x^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+25.0*4.0*x^3.0+20.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*x^6.0+100.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.8333333333333334*x^6.0+x^5.0-2.5*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.8333333333333334/7.0*x^7.0+1.0/6.0*x^6.0-2.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.11904761904761905*x^7.0+1.0/6.0*x^6.0-0.8333333333333334*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*x^6.0+100.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+100.0*3.0*x^2.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^5.0+300.0*x^2.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.11904761904761905*x^7.0+1.0/6.0*x^6.0-0.8333333333333334*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.11904761904761905/8.0*x^8.0+0.16666666666666666/7.0*x^7.0-0.8333333333333334/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.014880952380952382*x^8.0+0.023809523809523808*x^7.0-0.20833333333333334*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^5.0+300.0*x^2.0+120.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+300.0*2.0*x^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*x^4.0+600.0*x+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.014880952380952382*x^8.0+0.023809523809523808*x^7.0-0.20833333333333334*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.014880952380952382/9.0*x^9.0+0.023809523809523808/8.0*x^8.0-0.20833333333333334/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+0.0016534391534391536*x^9.0+0.002976190476190476*x^8.0-0.04166666666666667*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff20008() {
        //Test Case 20008
        UsageCalculus.TestCase = 20008;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-5*xy^8 - 5*xy^5 - 5*xy^4 - 5*xy - 25*-5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5:2 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xy^8.0-5.0*xy^5.0-5.0*xy^4.0-5.0*xy+125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xy^7.0-5.0*5.0*xy^4.0-5.0*4.0*xy^3.0-5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*xy^7.0-25.0*xy^4.0-20.0*xy^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xy^8.0-5.0*xy^5.0-5.0*xy^4.0-5.0*xy+125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xy^9.0-5.0/6.0*xy^6.0-5.0/5.0*xy^5.0-5.0/2.0*xy^2.0+125.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*xy^9.0-0.8333333333333334*xy^6.0-xy^5.0-2.5*xy^2.0+125.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*xy^7.0-25.0*xy^4.0-20.0*xy^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xy^6.0-25.0*4.0*xy^3.0-20.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*xy^6.0-100.0*xy^3.0-60.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*xy^9.0-0.8333333333333334*xy^6.0-xy^5.0-2.5*xy^2.0+125.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xy^10.0-0.8333333333333334/7.0*xy^7.0-1.0/6.0*xy^6.0-2.5/3.0*xy^3.0+125.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*xy^10.0-0.11904761904761905*xy^7.0-1.0/6.0*xy^6.0-0.8333333333333334*xy^3.0+62.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*xy^6.0-100.0*xy^3.0-60.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xy^5.0-100.0*3.0*xy^2.0-60.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*xy^5.0-300.0*xy^2.0-120.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*xy^10.0-0.11904761904761905*xy^7.0-1.0/6.0*xy^6.0-0.8333333333333334*xy^3.0+62.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xy^11.0-0.11904761904761905/8.0*xy^8.0-0.16666666666666666/7.0*xy^7.0-0.8333333333333334/4.0*xy^4.0+62.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*xy^11.0-0.014880952380952382*xy^8.0-0.023809523809523808*xy^7.0-0.20833333333333334*xy^4.0+20.833333333333332*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*xy^5.0-300.0*xy^2.0-120.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xy^4.0-300.0*2.0*xy^1.0-120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*xy^4.0-600.0*xy-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*xy^11.0-0.014880952380952382*xy^8.0-0.023809523809523808*xy^7.0-0.20833333333333334*xy^4.0+20.833333333333332*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xy^12.0-0.014880952380952382/9.0*xy^9.0-0.023809523809523808/8.0*xy^8.0-0.20833333333333334/5.0*xy^5.0+20.833333333333332/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*xy^12.0-0.0016534391534391536*xy^9.0-0.002976190476190476*xy^8.0-0.04166666666666667*xy^5.0+5.208333333333333*xy^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xy^4.0-600.0*xy-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xy^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xy^3.0-600.0";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xy^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xy^2.0-";
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
        public static void TestCaseDiff20009() {
        //Test Case 20009
        UsageCalculus.TestCase = 20009;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-5.0*xyc1^8 - 5.0*xyc1^5 - 5.0*xyc1^4 - 5.0*xyc1 - 5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1-125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xyc1^7.0-5.0*5.0*xyc1^4.0-5.0*4.0*xyc1^3.0-5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xyc1^8.0-5.0*xyc1^5.0-5.0*xyc1^4.0-5.0*xyc1-125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xyc1^9.0-5.0/6.0*xyc1^6.0-5.0/5.0*xyc1^5.0-5.0/2.0*xyc1^2.0-125.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0-125.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*xyc1^7.0-25.0*xyc1^4.0-20.0*xyc1^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xyc1^6.0-25.0*4.0*xyc1^3.0-20.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*xyc1^9.0-0.8333333333333334*xyc1^6.0-xyc1^5.0-2.5*xyc1^2.0-125.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xyc1^10.0-0.8333333333333334/7.0*xyc1^7.0-1.0/6.0*xyc1^6.0-2.5/3.0*xyc1^3.0-125.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0-62.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*xyc1^6.0-100.0*xyc1^3.0-60.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xyc1^5.0-100.0*3.0*xyc1^2.0-60.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*xyc1^10.0-0.11904761904761905*xyc1^7.0-1.0/6.0*xyc1^6.0-0.8333333333333334*xyc1^3.0-62.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xyc1^11.0-0.11904761904761905/8.0*xyc1^8.0-0.16666666666666666/7.0*xyc1^7.0-0.8333333333333334/4.0*xyc1^4.0-62.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0-20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*xyc1^5.0-300.0*xyc1^2.0-120.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xyc1^4.0-300.0*2.0*xyc1^1.0-120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*xyc1^11.0-0.014880952380952382*xyc1^8.0-0.023809523809523808*xyc1^7.0-0.20833333333333334*xyc1^4.0-20.833333333333332*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xyc1^12.0-0.014880952380952382/9.0*xyc1^9.0-0.023809523809523808/8.0*xyc1^8.0-0.20833333333333334/5.0*xyc1^5.0-20.833333333333332/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*xyc1^12.0-0.0016534391534391536*xyc1^9.0-0.002976190476190476*xyc1^8.0-0.04166666666666667*xyc1^5.0-5.208333333333333*xyc1^4.0";
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
        public static void TestCaseDiff20010() {
        //Test Case 20010
        UsageCalculus.TestCase = 20010;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5.0*xyc1^8 + 1.0*+5.0*xyc1^5 + 1.0*+5.0*xyc1^4 + 1.0*+5.0*xyc1 + 1.0*+5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
        public static void TestCaseDiff20011() {
        //Test Case 20011
        UsageCalculus.TestCase = 20011;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+1*xyc1^8 + 1.0*+1*xyc1^5 + 1.0*+1*xyc1^4 + 1.0*+1*xyc1 + 1.0*+1*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
        public static void TestCaseDiff20012() {
        //Test Case 20012
        UsageCalculus.TestCase = 20012;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+1.0*xyc1^8 + 1.0*+1.0*xyc1^5 + 1.0*+1.0*xyc1^4 + 1.0*+1.0*xyc1 + 1.0*+1.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1.0";
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
                
        public static void TestCaseDiff20013() {
        //Test Case 20013
        UsageCalculus.TestCase = 20013;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+5*xyc1^8 + 1.0*+5*xyc1^5 + 1.0*+5*xyc1^4 + 1.0*+5*xyc1 + 1.0*+5*25";
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
        public static void TestCaseDiff20014() {
        //Test Case 20014
        UsageCalculus.TestCase = 20014;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+235*xyc1^8 + 1.0*+2355*xyc1^5 + 1.0*+235*xyc1^4 + 1.0*+235*xyc1 + 1.0*+235*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+235";
        UsageCalculus.TestCaseMatrixID="Coefficients:+235:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
       public static void TestCaseDiff20015() {
        //Test Case 20015
        UsageCalculus.TestCase = 20015;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+2*xyc1^8*5 + 1.0*+2*xyc1^5*5 + 1.0*+2*xyc1^4*5 + 1.0*+2*xyc1*5 + 1.0*+2*25*5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5:Trailing Constant:End Term";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "10.0*8.0*xyc1^7.0+10.0*5.0*xyc1^4.0+10.0*4.0*xyc1^3.0+10.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        UsageCalculus.EqnSolArrayMap3[1]="10.0*xyc1^8.0+10.0*xyc1^5.0+10.0*xyc1^4.0+10.0*xyc1+250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="10.0/9.0*xyc1^9.0+10.0/6.0*xyc1^6.0+10.0/5.0*xyc1^5.0+10.0/2.0*xyc1^2.0+250.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "80.0*xyc1^7.0+50.0*xyc1^4.0+40.0*xyc1^3.0+10.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "80.0*7.0*xyc1^6.0+50.0*4.0*xyc1^3.0+40.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.1111111111111112*xyc1^9.0+1.6666666666666667*xyc1^6.0+2.0*xyc1^5.0+5.0*xyc1^2.0+250.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="1.1111111111111112/10.0*xyc1^10.0+1.6666666666666667/7.0*xyc1^7.0+2.0/6.0*xyc1^6.0+5.0/3.0*xyc1^3.0+250.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "560.0*xyc1^6.0+200.0*xyc1^3.0+120.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "560.0*6.0*xyc1^5.0+200.0*3.0*xyc1^2.0+120.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.11111111111111112*xyc1^10.0+0.2380952380952381*xyc1^7.0+0.3333333333333333*xyc1^6.0+1.6666666666666667*xyc1^3.0+125.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.11111111111111112/11.0*xyc1^11.0+0.2380952380952381/8.0*xyc1^8.0+0.3333333333333333/7.0*xyc1^7.0+1.6666666666666667/4.0*xyc1^4.0+125.0/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3360.0*xyc1^5.0+600.0*xyc1^2.0+240.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3360.0*5.0*xyc1^4.0+600.0*2.0*xyc1^1.0+240.0";
        UsageCalculus.EqnSolArrayMap2[4] = "16800.0*xyc1^4.0+1200.0*xyc1+240.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.010101010101010102*xyc1^11.0+0.029761904761904764*xyc1^8.0+0.047619047619047616*xyc1^7.0+0.4166666666666667*xyc1^4.0+41.666666666666664*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.010101010101010102/12.0*xyc1^12.0+0.029761904761904764/9.0*xyc1^9.0+0.047619047619047616/8.0*xyc1^8.0+0.4166666666666667/5.0*xyc1^5.0+41.666666666666664/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508418E-4*xyc1^12.0+0.003306878306878307*xyc1^9.0+0.005952380952380952*xyc1^8.0+0.08333333333333334*xyc1^5.0+10.416666666666666*xyc1^4.0";
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
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0-";
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
       public static void TestCaseDiff20016() {
        //Test Case 20016
        UsageCalculus.TestCase = 20016;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0*+2*xyc1^8*5*xyc1*7 + 1.0*+2*xyc1^5*5*xyc1*7 + 1.0*+2*xyc1^4*5*xyc1*7 + 1.0*+2*xyc1*5*xyc1*7 + 1.0*+2*25*5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5*xyc1*7:Trailing Constant:Middle Term";
        UsageCalculus.TestCaseMatrixID="Coefficients:5*xyc1*7:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "70.0*xyc1^9.0+70.0*xyc1^6.0+70.0*xyc1^5.0+70.0*xyc1^2.0+250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "70.0*9.0*xyc1^8.0+70.0*6.0*xyc1^5.0+70.0*5.0*xyc1^4.0+70.0*2.0*xyc1^1.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "630.0*xyc1^8.0+420.0*xyc1^5.0+350.0*xyc1^4.0+140.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[1]="70.0*xyc1^9.0+70.0*xyc1^6.0+70.0*xyc1^5.0+70.0*xyc1^2.0+250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="70.0/10.0*xyc1^10.0+70.0/7.0*xyc1^7.0+70.0/6.0*xyc1^6.0+70.0/3.0*xyc1^3.0+250.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="7.0*xyc1^10.0+10.0*xyc1^7.0+11.666666666666666*xyc1^6.0+23.333333333333332*xyc1^3.0+250.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "630.0*xyc1^8.0+420.0*xyc1^5.0+350.0*xyc1^4.0+140.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "630.0*8.0*xyc1^7.0+420.0*5.0*xyc1^4.0+350.0*4.0*xyc1^3.0+140.0";
        UsageCalculus.EqnSolArrayMap2[2] = "5040.0*xyc1^7.0+2100.0*xyc1^4.0+1400.0*xyc1^3.0+140.0";
        UsageCalculus.EqnSolArrayMap3[2]="7.0*xyc1^10.0+10.0*xyc1^7.0+11.666666666666666*xyc1^6.0+23.333333333333332*xyc1^3.0+250.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="7.0/11.0*xyc1^11.0+10.0/8.0*xyc1^8.0+11.666666666666666/7.0*xyc1^7.0+23.333333333333332/4.0*xyc1^4.0+250.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.6363636363636364*xyc1^11.0+1.25*xyc1^8.0+1.6666666666666665*xyc1^7.0+5.833333333333333*xyc1^4.0+125.0*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "5040.0*xyc1^7.0+2100.0*xyc1^4.0+1400.0*xyc1^3.0+140.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "5040.0*7.0*xyc1^6.0+2100.0*4.0*xyc1^3.0+1400.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[3] = "35280.0*xyc1^6.0+8400.0*xyc1^3.0+4200.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.6363636363636364*xyc1^11.0+1.25*xyc1^8.0+1.6666666666666665*xyc1^7.0+5.833333333333333*xyc1^4.0+125.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.6363636363636364/12.0*xyc1^12.0+1.25/9.0*xyc1^9.0+1.6666666666666665/8.0*xyc1^8.0+5.833333333333333/5.0*xyc1^5.0+125.0/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.05303030303030303*xyc1^12.0+0.1388888888888889*xyc1^9.0+0.20833333333333331*xyc1^8.0+1.1666666666666665*xyc1^5.0+41.666666666666664*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "35280.0*xyc1^6.0+8400.0*xyc1^3.0+4200.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "35280.0*6.0*xyc1^5.0+8400.0*3.0*xyc1^2.0+4200.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[4] = "211680.0*xyc1^5.0+25200.0*xyc1^2.0+8400.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[4]="0.05303030303030303*xyc1^12.0+0.1388888888888889*xyc1^9.0+0.20833333333333331*xyc1^8.0+1.1666666666666665*xyc1^5.0+41.666666666666664*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.05303030303030303/13.0*xyc1^13.0+0.1388888888888889/10.0*xyc1^10.0+0.20833333333333331/9.0*xyc1^9.0+1.1666666666666665/6.0*xyc1^6.0+41.666666666666664/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.004079254079254079*xyc1^13.0+0.01388888888888889*xyc1^10.0+0.023148148148148147*xyc1^9.0+0.19444444444444442*xyc1^6.0+10.416666666666666*xyc1^4.0";
        UsageCalculus.EqnSolOrder[4] = 4;
        
        UsageCalculus.EqnSolArrayMap1[5] = "-8400.0*xyc1^4.0-600.0*xyc1-120.0";
        UsageCalculus.EqnSolArrayDiffAlone[5] = "-8400.0*4.0*xyc1^3.0-600.0-0.0";
        UsageCalculus.EqnSolArrayMap2[5] = "-33600.0*xyc1^3.0-600.0-";
        UsageCalculus.EqnSolArrayMap3[5]="";
        UsageCalculus.EqnSolArrayIntegrAlone[5]="";
        UsageCalculus.EqnSolArrayMap4[5]="";
        UsageCalculus.EqnSolOrder[5] = 5;

        UsageCalculus.EqnSolArrayMap1[6] = "-33600.0*xyc1^3.0-600.0";
        UsageCalculus.EqnSolArrayDiffAlone[6] = "-33600.0*3.0*xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[6] = "-100800.0*xyc1^2.0-";
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
       public static void TestCaseDiff20017() {
        //Test Case 20017
        UsageCalculus.TestCase = 20017;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "2.0/2.0*1.0*+2*xyc1^8*5^xyc1 + 1.0*+2*xyc1^5*5^xyc1 + 1.0*+2*xyc1^4*5^xyc1 + 1.0*+2*xyc1*5^xyc1 + 1.0*+2*25*5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5^xyc1:";
        UsageCalculus.TestCaseMatrixID="Coefficients:5^xyc1:3 Letter with Numerics:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 2;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "2.0*xyc1^8.0*5.0^xyc1+2.0*xyc1^5.0*5.0^xyc1+2.0*xyc1^4.0*5.0^xyc1+2.0*xyc1*5.0^xyc1+250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0^xyc1*2.0*8.0*xyc1^7.0+ln(5.0)*5.0^xyc1*2.0*xyc1^8.0+5.0^xyc1*2.0*5.0*xyc1^4.0+ln(5.0)*5.0^xyc1*2.0*xyc1^5.0+5.0^xyc1*2.0*4.0*xyc1^3.0+ln(5.0)*5.0^xyc1*2.0*xyc1^4.0+5.0^xyc1*2.0+ln(5.0)*5.0^xyc1*2.0*xyc1+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "16.0*xyc1^7.0*5.0^xyc1+2.0*ln(5.0)*5.0^xyc1+10.0*xyc1^4.0*5.0^xyc1+2.0*ln(5.0)*5.0^xyc1+8.0*xyc1^3.0*5.0^xyc1+2.0*ln(5.0)*5.0^xyc1+2.0*5.0^xyc1+2.0*ln(5.0)*5.0^xyc1";
        UsageCalculus.EqnSolArrayMap3[1]="2.0*xyc1^8.0*5.0^xyc1+2.0*xyc1^5.0*5.0^xyc1+2.0*xyc1^4.0*5.0^xyc1+2.0*xyc1*5.0^xyc1+250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="2.0/9.0*xyc1^9.0/ln(5.0)*5.0^xyc1+2.0/6.0*xyc1^6.0/ln(5.0)*5.0^xyc1+2.0/5.0*xyc1^5.0/ln(5.0)*5.0^xyc1+10.0/2.0*xyc1^2.0/ln(5.0)*5.0^xyc1+250.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.2222222222222222/ln(5.0)*5.0^xyc1*xyc1^9.0+0.3333333333333333/ln(5.0)*5.0^xyc1*xyc1^6.0+0.4/ln(5.0)*5.0^xyc1*xyc1^5.0+5.0/ln(5.0)*5.0^xyc1*xyc1^2.0+250.0*xyc1";
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
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff20018() {
        //Test Case 20018
        UsageCalculus.TestCase = 20018;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0/x^8 + 1.0*-0.0/x^5 + 1.0*-0.0/x^4 - 1.0*-0.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)-25.0*x";
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
        public static void TestCaseDiff20019() {
        //Test Case 20019
        UsageCalculus.TestCase = 20019;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "2.0/2.0*1.0*-0/xy^8 + 1.0*-0/xy^5 + 1.0*-0/xy^4 - 1.0*-0/xy - 25*-0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/xy^9.0-0.0*-5.0/xy^6.0-0.0*-4.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/xy^7.0-0.0/-4.0/xy^4.0-0.0/-3.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/xy^7.0+0.0/xy^4.0+0.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/xy^10.0+0.0*-6.0/xy^7.0+0.0*-5.0/xy^6.0-0.0*-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/xy^11.0-0.0*-7.0/xy^8.0-0.0*-6.0/xy^7.0+0.0*-3.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/xy^12.0+0.0*-8.0/xy^9.0+0.0*-7.0/xy^8.0-0.0*-4.0/xy^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/xy^12.0-0.0/xy^9.0-0.0/xy^8.0+0.0/xy^5.0";
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
        public static void TestCaseDiff20020() {
        //Test Case 20020
        UsageCalculus.TestCase = 20020;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "3.0/3.0*1.0*+1/xyc^8 + 1.0*+1/xyc^5 + 1*+1/xyc^4 - 1*+1/xyc - 1*+1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 5;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff20021() {
        //Test Case 20021
        UsageCalculus.TestCase = 20021;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1*+1.0/xyc1^8 + 1*+1.0/xyc1^5 + 1*+1.0/xyc1^4 - 1*+1.0/xyc1 - 1*+1.0/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics :Co-efficients:+1.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc1^7.0-1.0/4.0/xyc1^4.0-1.0/3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
        public static void TestCaseDiff20022() {
        //Test Case 20022
        UsageCalculus.TestCase = 20022;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5/xyc1^8 + 1.0/+5*xyc1^5 + 1/+5*xyc1^4 + 1.0/+5*xyc1 + 1.0/+5/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/5.0/xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+1.0/125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/5.0*-8.0/xyc1^9.0+1.0/5.0*5.0*xyc1^4.0+1.0/5.0*4.0*xyc1^3.0+1.0/5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/5.0/xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+1.0/125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.028571428571428574/-6.0/xyc1^6.0+0.03333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+0.008/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+4.0*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.004761904761904762/-5.0/xyc1^5.0+0.0047619047619047615/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+0.004/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0+12.0*2.0*xyc1^1.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+24.0*xyc1+4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-9.523809523809525E-4/-4.0/xyc1^4.0+5.952380952380952E-4/9.0*xyc1^9.0+9.523809523809525E-4/8.0*xyc1^8.0+0.008333333333333333/5.0*xyc1^5.0+0.0013333333333333333/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.3809523809523812E-4/xyc1^4.0+6.613756613756613E-5*xyc1^9.0+1.1904761904761906E-4*xyc1^8.0+0.0016666666666666666*xyc1^5.0+3.333333333333333E-4*xyc1^4.0";
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
        public static void TestCaseDiff20023() {
        //Test Case 20023
        UsageCalculus.TestCase = 20023;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0/x^8 + (-0.0)/x^5 + (-0.0)/x^4 - (-0.0)/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/x^10.0+0.0*-6.0/x^7.0+0.0*-5.0/x^6.0-0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/x^11.0-0.0*-7.0/x^8.0-0.0*-6.0/x^7.0+0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/x^12.0+0.0*-8.0/x^9.0+0.0*-7.0/x^8.0-0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/x^12.0-0.0/x^9.0-0.0/x^8.0+0.0/x^5.0";
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
        public static void TestCaseDiff20024() {
        //Test Case 20024
        UsageCalculus.TestCase = 20024;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "(-0)/xy^8 + (-0)/xy^5 + (-0)/xy^4 - (-0)/xy - 25*(-0)";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0-0.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0*xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy-0.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/9.0*xy^9.0-0.0/-4.0/xy^4.0-0.0/-3.0/xy^3.0+0.0*ln(xy)-0.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0*xy^9.0+0.0/xy^4.0+0.0/xy^3.0+0.0*ln(xy)-0.0*xy";
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
        public static void TestCaseDiff20025() {
        //Test Case 20025
        UsageCalculus.TestCase = 20025;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+1/xyc^8 + 1/xyc^5 + 1/xyc^4 - 1/xyc - 1/25";
        //Usage.EqnUnderTest = "1/xyc^8 + 1/xyc^5 + 1/xyc^4 - 1/xyc - 1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff20026() {
        //Test Case 20026
        UsageCalculus.TestCase = 20026;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "+1.0/xyc1^8 + 1.0/xyc1^5 + 1.0/xyc1^4 - 1.0/xyc1 - 1.0/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics :Co-efficients:+1.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc1^7.0-1.0/4.0/xyc1^4.0-1.0/3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff20027() {
        //Test Case 20027
        UsageCalculus.TestCase = 20027;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "4.0/4.0*0.0/x^8 + 0.0/x^5 + 0.0/x^4 - 0.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0*-8.0/x^9.0+0.0*-5.0/x^6.0+0.0*-4.0/x^5.0+0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="0.0/x^8.0+0.0/x^5.0+0.0/x^4.0-0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/-7.0/x^7.0+0.0/-4.0/x^4.0+0.0/-3.0/x^3.0-0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0/x^7.0-0.0/x^4.0-0.0/x^3.0-0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.0/x^9.0-0.0/x^6.0-0.0/x^5.0+0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0*-9.0/x^10.0-0.0*-6.0/x^7.0-0.0*-5.0/x^6.0+0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.0/x^10.0+0.0/x^7.0+0.0/x^6.0-0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0*-10.0/x^11.0+0.0*-7.0/x^8.0+0.0*-6.0/x^7.0-0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-0.0/x^11.0-0.0/x^8.0-0.0/x^7.0+0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-0.0*-11.0/x^12.0-0.0*-8.0/x^9.0-0.0*-7.0/x^8.0+0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "0.0/x^12.0+0.0/x^9.0+0.0/x^8.0-0.0/x^5.0";
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
        public static void TestCaseDiff20028() {
        //Test Case 20028
        UsageCalculus.TestCase = 20028;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0/xy^8 + 0/xy^5 + 0/xy^4 - 0/xy - 25/0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0/xy^8.0+0.0/xy^5.0+0.0/xy^4.0-0.0/xy-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0*-8.0/xy^9.0+0.0*-5.0/xy^6.0+0.0*-4.0/xy^5.0+0.0/xy^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.0/xy^9.0-0.0/xy^6.0-0.0/xy^5.0+0.0/xy^2.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[1]="0.0/xy^8.0+0.0/xy^5.0+0.0/xy^4.0-0.0/xy-Infinity";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/-7.0/xy^7.0+0.0/-4.0/xy^4.0+0.0/-3.0/xy^3.0-0.0*ln(xy)-Infinity*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0/xy^7.0-0.0/xy^4.0-0.0/xy^3.0-0.0*ln(xy)-Infinity*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.0/xy^9.0-0.0/xy^6.0-0.0/xy^5.0+0.0/xy^2.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0*-9.0/xy^10.0-0.0*-6.0/xy^7.0-0.0*-5.0/xy^6.0+0.0*-2.0/xy^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[2] = "0.0/xy^10.0+0.0/xy^7.0+0.0/xy^6.0-0.0/xy^3.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.0/xy^10.0+0.0/xy^7.0+0.0/xy^6.0-0.0/xy^3.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0*-10.0/xy^11.0+0.0*-7.0/xy^8.0+0.0*-6.0/xy^7.0-0.0*-3.0/xy^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[3] = "-0.0/xy^11.0-0.0/xy^8.0-0.0/xy^7.0+0.0/xy^4.0-Infinity";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-0.0/xy^11.0-0.0/xy^8.0-0.0/xy^7.0+0.0/xy^4.0-Infinity";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-0.0*-11.0/xy^12.0-0.0*-8.0/xy^9.0-0.0*-7.0/xy^8.0+0.0*-4.0/xy^5.0-Infinity";
        UsageCalculus.EqnSolArrayMap2[4] = "0.0/xy^12.0+0.0/xy^9.0+0.0/xy^8.0-0.0/xy^5.0-Infinity";
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
        public static void TestCaseDiff20029() {
        //Test Case 20029
        UsageCalculus.TestCase = 20029;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1/xyc^8 + 1/xyc^5 + 1/xyc^4 - 1/xyc - 1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff20030() {
        //Test Case 20030
        UsageCalculus.TestCase = 20030;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/xyc1^8 + 1.0/xyc1^5 + 1.0/xyc1^4 - 1.0/xyc1 - 1.0/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc1^7.0-1.0/4.0/xyc1^4.0-1.0/3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
    public static void TestCaseDiff20031() {
        //Test Case 20031
        UsageCalculus.TestCase = 20031;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-1.0/x^8 -1.0/x^5 -1.0/x^4 -1.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/x^8.0-1.0/x^5.0-1.0/x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0+1.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/x^8.0-1.0/x^5.0-1.0/x^4.0-1.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/-7.0/x^7.0-1.0/-4.0/x^4.0-1.0/-3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/7.0/x^7.0+1.0/4.0/x^4.0+1.0/3.0/x^3.0-ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0/x^9.0+5.0/x^6.0+4.0/x^5.0+1.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*-9.0/x^10.0+5.0*-6.0/x^7.0+4.0*-5.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0/x^10.0-30.0/x^7.0-20.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0/x^10.0-30.0/x^7.0-20.0/x^6.0-2.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*-10.0/x^11.0-30.0*-7.0/x^8.0-20.0*-6.0/x^7.0-2.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0/x^11.0+210.0/x^8.0+120.0/x^7.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0/x^11.0+210.0/x^8.0+120.0/x^7.0+6.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*-11.0/x^12.0+210.0*-8.0/x^9.0+120.0*-7.0/x^8.0+6.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-7920.0/x^12.0-1680.0/x^9.0-840.0/x^8.0-24.0/x^5.0";
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
        public static void TestCaseDiff20032() {
        //Test Case 20032
        UsageCalculus.TestCase = 20032;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-1/xy^8 -1/xy^5 -1/xy^4 -1/xy - 25/-1";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/xy^8.0-1.0/xy^5.0-1.0/xy^4.0-1.0/xy+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0/xy^9.0+5.0/xy^6.0+4.0/xy^5.0+1.0/xy^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0/xy^9.0+5.0/xy^6.0+4.0/xy^5.0+1.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/xy^8.0-1.0/xy^5.0-1.0/xy^4.0-1.0/xy+25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/-7.0/xy^7.0-1.0/-4.0/xy^4.0-1.0/-3.0/xy^3.0-ln(xy)+25.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/7.0/xy^7.0+1.0/4.0/xy^4.0+1.0/3.0/xy^3.0-ln(xy)+25.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0/xy^9.0+5.0/xy^6.0+4.0/xy^5.0+1.0/xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*-9.0/xy^10.0+5.0*-6.0/xy^7.0+4.0*-5.0/xy^6.0-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0/xy^10.0-30.0/xy^7.0-20.0/xy^6.0-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0/xy^10.0-30.0/xy^7.0-20.0/xy^6.0-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*-10.0/xy^11.0-30.0*-7.0/xy^8.0-20.0*-6.0/xy^7.0-2.0*-3.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0/xy^11.0+210.0/xy^8.0+120.0/xy^7.0+6.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0/xy^11.0+210.0/xy^8.0+120.0/xy^7.0+6.0/xy^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*-11.0/xy^12.0+210.0*-8.0/xy^9.0+120.0*-7.0/xy^8.0+6.0*-4.0/xy^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-7920.0/xy^12.0-1680.0/xy^9.0-840.0/xy^8.0-24.0/xy^5.0";
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
        public static void TestCaseDiff20033() {
        //Test Case 20033
        UsageCalculus.TestCase = 20033;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "5/x^8 + 5/x^5 + 5/x^4 - 5/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0/x^8.0+5.0/x^5.0+5.0/x^4.0-5.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*-8.0/x^9.0+5.0*-5.0/x^6.0+5.0*-4.0/x^5.0+5.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0/x^9.0-25.0/x^6.0-20.0/x^5.0+5.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0/x^8.0+5.0/x^5.0+5.0/x^4.0-5.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/-7.0/x^7.0+5.0/-4.0/x^4.0+5.0/-3.0/x^3.0-5.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-0.7142857142857143/x^7.0-1.25/x^4.0-1.6666666666666667/x^3.0-5.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0/x^9.0-25.0/x^6.0-20.0/x^5.0+5.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*-9.0/x^10.0-25.0*-6.0/x^7.0-20.0*-5.0/x^6.0+5.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "360.0/x^10.0+150.0/x^7.0+100.0/x^6.0-10.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "360.0/x^10.0+150.0/x^7.0+100.0/x^6.0-10.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "360.0*-10.0/x^11.0+150.0*-7.0/x^8.0+100.0*-6.0/x^7.0-10.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-3600.0/x^11.0-1050.0/x^8.0-600.0/x^7.0+30.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-3600.0/x^11.0-1050.0/x^8.0-600.0/x^7.0+30.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-3600.0*-11.0/x^12.0-1050.0*-8.0/x^9.0-600.0*-7.0/x^8.0+30.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "39600.0/x^12.0+8400.0/x^9.0+4200.0/x^8.0-120.0/x^5.0";
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
        public static void TestCaseDiff20034() {
        //Test Case 20034
        UsageCalculus.TestCase = 20034;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-5/xy^8 - 5/xy^5 - 5/xy^4 - 5/xy - 25/-5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0/xy^8.0-5.0/xy^5.0-5.0/xy^4.0-5.0/xy+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*-8.0/xy^9.0-5.0*-5.0/xy^6.0-5.0*-4.0/xy^5.0+5.0/xy^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0/xy^9.0+25.0/xy^6.0+20.0/xy^5.0+5.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0/xy^8.0-5.0/xy^5.0-5.0/xy^4.0-5.0/xy+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/-7.0/xy^7.0-5.0/-4.0/xy^4.0-5.0/-3.0/xy^3.0-5.0*ln(xy)+5.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="0.7142857142857143/xy^7.0+1.25/xy^4.0+1.6666666666666667/xy^3.0-5.0*ln(xy)+5.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0/xy^9.0+25.0/xy^6.0+20.0/xy^5.0+5.0/xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*-9.0/xy^10.0+25.0*-6.0/xy^7.0+20.0*-5.0/xy^6.0+5.0*-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-360.0/xy^10.0-150.0/xy^7.0-100.0/xy^6.0-10.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-360.0/xy^10.0-150.0/xy^7.0-100.0/xy^6.0-10.0/xy^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-360.0*-10.0/xy^11.0-150.0*-7.0/xy^8.0-100.0*-6.0/xy^7.0-10.0*-3.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3600.0/xy^11.0+1050.0/xy^8.0+600.0/xy^7.0+30.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3600.0/xy^11.0+1050.0/xy^8.0+600.0/xy^7.0+30.0/xy^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3600.0*-11.0/xy^12.0+1050.0*-8.0/xy^9.0+600.0*-7.0/xy^8.0+30.0*-4.0/xy^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-39600.0/xy^12.0-8400.0/xy^9.0-4200.0/xy^8.0-120.0/xy^5.0";
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
        public static void TestCaseDiff20035() {
        //Test Case 20035
        UsageCalculus.TestCase = 20035;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-5.0/xyc1^8 - 5.0/xyc1^5 - 5.0/xyc1^4 - 5.0/xyc1 - 5.0/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0/xyc1^8.0-5.0/xyc1^5.0-5.0/xyc1^4.0-5.0/xyc1-0.2";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*-8.0/xyc1^9.0-5.0*-5.0/xyc1^6.0-5.0*-4.0/xyc1^5.0+5.0/xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0/xyc1^9.0+25.0/xyc1^6.0+20.0/xyc1^5.0+5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0/xyc1^8.0-5.0/xyc1^5.0-5.0/xyc1^4.0-5.0/xyc1-0.2";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/-7.0/xyc1^7.0-5.0/-4.0/xyc1^4.0-5.0/-3.0/xyc1^3.0-5.0*ln(xyc1)-0.2*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.7142857142857143/xyc1^7.0+1.25/xyc1^4.0+1.6666666666666667/xyc1^3.0-5.0*ln(xyc1)-0.2*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0/xyc1^9.0+25.0/xyc1^6.0+20.0/xyc1^5.0+5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*-9.0/xyc1^10.0+25.0*-6.0/xyc1^7.0+20.0*-5.0/xyc1^6.0+5.0*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-360.0/xyc1^10.0-150.0/xyc1^7.0-100.0/xyc1^6.0-10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-360.0/xyc1^10.0-150.0/xyc1^7.0-100.0/xyc1^6.0-10.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-360.0*-10.0/xyc1^11.0-150.0*-7.0/xyc1^8.0-100.0*-6.0/xyc1^7.0-10.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "3600.0/xyc1^11.0+1050.0/xyc1^8.0+600.0/xyc1^7.0+30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "3600.0/xyc1^11.0+1050.0/xyc1^8.0+600.0/xyc1^7.0+30.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "3600.0*-11.0/xyc1^12.0+1050.0*-8.0/xyc1^9.0+600.0*-7.0/xyc1^8.0+30.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-39600.0/xyc1^12.0-8400.0/xyc1^9.0-4200.0/xyc1^8.0-120.0/xyc1^5.0";
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
        public static void TestCaseDiff20036() {
        //Test Case 20036
        UsageCalculus.TestCase = 20036;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5.0/xyc1^8 + 1.0/+5.0*xyc1^5 + 1.0/+5.0*xyc1^4 + 1.0/+5.0*xyc1 + 1.0/+5.0/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/5.0/xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+1.0/125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/5.0*-8.0/xyc1^9.0+1.0/5.0*5.0*xyc1^4.0+1.0/5.0*4.0*xyc1^3.0+1.0/5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/5.0/xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+1.0/125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.028571428571428574/xyc1^7.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+0.008*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.028571428571428574/-6.0/xyc1^6.0+0.03333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+0.008/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+4.0*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.004761904761904762/xyc1^6.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+0.004*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.004761904761904762/-5.0/xyc1^5.0+0.0047619047619047615/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+0.004/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0+12.0*2.0*xyc1^1.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+24.0*xyc1+4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4]="-9.523809523809525E-4/xyc1^5.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.0013333333333333333*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-9.523809523809525E-4/-4.0/xyc1^4.0+5.952380952380952E-4/9.0*xyc1^9.0+9.523809523809525E-4/8.0*xyc1^8.0+0.008333333333333333/5.0*xyc1^5.0+0.0013333333333333333/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.3809523809523812E-4/xyc1^4.0+6.613756613756613E-5*xyc1^9.0+1.1904761904761906E-4*xyc1^8.0+0.0016666666666666666*xyc1^5.0+3.333333333333333E-4*xyc1^4.0";
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
        public static void TestCaseDiff20037() {
        //Test Case 20037
        UsageCalculus.TestCase = 20037;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+1*xyc1^8 + 1.0/+1*xyc1^5 + 1.0/+1*xyc1^4 + 1.0/+1*xyc1 + 1.0/+1*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
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
        public static void TestCaseDiff20038() {
        //Test Case 20038
        UsageCalculus.TestCase = 20038;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+1.0*xyc1^8 + 1.0/+1.0*xyc1^5 + 1.0/+1.0*xyc1^4 + 1.0/+1.0/xyc1 + 1.0/+1.0/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0+1.0/xyc1+1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0+1.0/xyc1+1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+ln(xyc1)+0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+ln(xyc1)+0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0+2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1-6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1-6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0-6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0+24.0/xyc1^5.0";
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
        public static void TestCaseDiff20039() {
        //Test Case 20039
        UsageCalculus.TestCase = 20039;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5.0/xyc1^8 + 1.0/+5.0/xyc1^5 + 1.0/+5.0*xyc1^4 + 1.0/+5.0*xyc1 + 1.0/+5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/5.0/xyc1^8.0+1.0/5.0/xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/5.0*-8.0/xyc1^9.0+1.0/5.0*-5.0/xyc1^6.0+1.0/5.0*4.0*xyc1^3.0+1.0/5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0-1.0/xyc1^6.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/5.0/xyc1^8.0+1.0/5.0/xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/-4.0/xyc1^4.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0-0.05/xyc1^4.0+0.04*xyc1^5.0+0.1*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0-1.0/xyc1^6.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+6.0/xyc1^7.0+0.8*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+6.0/xyc1^7.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.028571428571428574/xyc1^7.0-0.05/xyc1^4.0+0.04*xyc1^5.0+0.1*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.028571428571428574/-6.0/xyc1^6.0-0.05/-3.0/xyc1^3.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+5.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.004761904761904762/xyc1^6.0+0.016666666666666666/xyc1^3.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+6.0/xyc1^7.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+6.0*-7.0/xyc1^8.0+2.4000000000000004*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0-42.0/xyc1^8.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.004761904761904762/xyc1^6.0+0.016666666666666666/xyc1^3.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.004761904761904762/-5.0/xyc1^5.0+0.016666666666666666/-2.0/xyc1^2.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+2.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-9.523809523809525E-4/xyc1^5.0-0.008333333333333333/xyc1^2.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0-42.0/xyc1^8.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0-42.0*-8.0/xyc1^9.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+336.0/xyc1^9.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4]="-9.523809523809525E-4/xyc1^5.0-0.008333333333333333/xyc1^2.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-9.523809523809525E-4/-4.0/xyc1^4.0-0.008333333333333333/-1.0/xyc1^1.0+9.523809523809525E-4/8.0*xyc1^8.0+0.008333333333333333/5.0*xyc1^5.0+0.8333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.3809523809523812E-4/xyc1^4.0+0.008333333333333333/xyc1+1.1904761904761906E-4*xyc1^8.0+0.0016666666666666666*xyc1^5.0+0.20833333333333334*xyc1^4.0";
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
        public static void TestCaseDiff20040() {
        //Test Case 20040
        UsageCalculus.TestCase = 20040;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5*xyc1^8 + 1.0/+5*xyc1^5 + 1.0/+5*xyc1^4 + 1.0/+5*xyc1 + 1.0/+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 4;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/5.0*xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/5.0*8.0*xyc1^7.0+1.0/5.0*5.0*xyc1^4.0+1.0/5.0*4.0*xyc1^3.0+1.0/5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.6*xyc1^7.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/5.0*xyc1^8.0+1.0/5.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/5.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.2/9.0*xyc1^9.0+0.2/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.2/2.0*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.022222222222222223*xyc1^9.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.6*xyc1^7.0+xyc1^4.0+0.8*xyc1^3.0+1.0/5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "1.6*7.0*xyc1^6.0+4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "11.200000000000001*xyc1^6.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.022222222222222223*xyc1^9.0+0.03333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.1*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.022222222222222223/10.0*xyc1^10.0+0.03333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.1/3.0*xyc1^3.0+5.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.0022222222222222222*xyc1^10.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "11.200000000000001*xyc1^6.0+4.0*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "11.200000000000001*6.0*xyc1^5.0+4.0*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "67.2*xyc1^5.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.0022222222222222222*xyc1^10.0+0.0047619047619047615*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.03333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.0022222222222222222/11.0*xyc1^11.0+0.0047619047619047615/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.03333333333333333/4.0*xyc1^4.0+2.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="2.0202020202020202E-4*xyc1^11.0+5.952380952380952E-4*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+0.008333333333333333*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "67.2*xyc1^5.0+12.0*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "67.2*5.0*xyc1^4.0+12.0*2.0*xyc1^1.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap2[4] = "336.0*xyc1^4.0+24.0*xyc1+4.800000000000001";
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
        public static void TestCaseDiff20041() {
        //Test Case 20041
        UsageCalculus.TestCase = 20041;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+235/xyc1^8 + 1.0/+2355/xyc1^5 + 1.0/+235*xyc1^4 + 1.0/+235*xyc1 + 1.0/+235*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+235";
        UsageCalculus.TestCaseMatrixID="Coefficients:+235:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/235.0/xyc1^8.0+1.0/2355.0/xyc1^5.0+1.0/235.0*xyc1^4.0+1.0/235.0*xyc1+0.10638297872340426";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/235.0*-8.0/xyc1^9.0+1.0/2355.0*-5.0/xyc1^6.0+1.0/235.0*4.0*xyc1^3.0+1.0/235.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.03404255319148936/xyc1^9.0-0.0021231422505307855/xyc1^6.0+0.01702127659574468*xyc1^3.0+1.0/235.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/235.0/xyc1^8.0+1.0/2355.0/xyc1^5.0+1.0/235.0*xyc1^4.0+1.0/235.0*xyc1+0.10638297872340426";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.00425531914893617/-7.0/xyc1^7.0+4.246284501061571E-4/-4.0/xyc1^4.0+0.00425531914893617/5.0*xyc1^5.0+0.00425531914893617/2.0*xyc1^2.0+0.10638297872340426*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-6.079027355623101E-4/xyc1^7.0-1.0615711252653928E-4/xyc1^4.0+8.51063829787234E-4*xyc1^5.0+0.002127659574468085*xyc1^2.0+0.10638297872340426*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.03404255319148936/xyc1^9.0-0.0021231422505307855/xyc1^6.0+0.01702127659574468*xyc1^3.0+1.0/235.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.03404255319148936*-9.0/xyc1^10.0-0.0021231422505307855*-6.0/xyc1^7.0+0.01702127659574468*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "0.30638297872340425/xyc1^10.0+0.012738853503184714/xyc1^7.0+0.05106382978723405*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-6.079027355623101E-4/xyc1^7.0-1.0615711252653928E-4/xyc1^4.0+8.51063829787234E-4*xyc1^5.0+0.002127659574468085*xyc1^2.0+0.10638297872340426*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-6.079027355623101E-4/-6.0/xyc1^6.0-1.0615711252653928E-4/-3.0/xyc1^3.0+8.51063829787234E-4/6.0*xyc1^6.0+0.002127659574468085/3.0*xyc1^3.0+0.10638297872340426/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="1.0131712259371835E-4/xyc1^6.0+3.5385704175513094E-5/xyc1^3.0+1.4184397163120567E-4*xyc1^6.0+7.092198581560284E-4*xyc1^3.0+0.05319148936170213*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.30638297872340425/xyc1^10.0+0.012738853503184714/xyc1^7.0+0.05106382978723405*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.30638297872340425*-10.0/xyc1^11.0+0.012738853503184714*-7.0/xyc1^8.0+0.05106382978723405*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-3.0638297872340425/xyc1^11.0-0.089171974522293/xyc1^8.0+0.1021276595744681*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="1.0131712259371835E-4/xyc1^6.0+3.5385704175513094E-5/xyc1^3.0+1.4184397163120567E-4*xyc1^6.0+7.092198581560284E-4*xyc1^3.0+0.05319148936170213*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="1.0131712259371835E-4/-5.0/xyc1^5.0+3.5385704175513094E-5/-2.0/xyc1^2.0+1.4184397163120567E-4/7.0*xyc1^7.0+7.092198581560284E-4/4.0*xyc1^4.0+0.05319148936170213/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-2.026342451874367E-5/xyc1^5.0-1.7692852087756547E-5/xyc1^2.0+2.0263424518743667E-5*xyc1^7.0+1.773049645390071E-4*xyc1^4.0+0.01773049645390071*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-3.0638297872340425/xyc1^11.0-0.089171974522293/xyc1^8.0+0.1021276595744681*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-3.0638297872340425*-11.0/xyc1^12.0-0.089171974522293*-8.0/xyc1^9.0+0.1021276595744681";
        UsageCalculus.EqnSolArrayMap2[4] = "33.702127659574465/xyc1^12.0+0.713375796178344/xyc1^9.0+0.1021276595744681";
        UsageCalculus.EqnSolArrayMap3[4]="-2.026342451874367E-5/xyc1^5.0-1.7692852087756547E-5/xyc1^2.0+2.0263424518743667E-5*xyc1^7.0+1.773049645390071E-4*xyc1^4.0+0.01773049645390071*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-2.026342451874367E-5/-4.0/xyc1^4.0-1.7692852087756547E-5/-1.0/xyc1^1.0+2.0263424518743667E-5/8.0*xyc1^8.0+1.773049645390071E-4/5.0*xyc1^5.0+0.01773049645390071/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="5.065856129685918E-6/xyc1^4.0+1.7692852087756547E-5/xyc1+2.5329280648429584E-6*xyc1^8.0+3.546099290780142E-5*xyc1^5.0+0.004432624113475178*xyc1^4.0";
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
        
       public static void TestCaseDiff20042() {
        //Test Case 20042
        UsageCalculus.TestCase = 20042;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+2*xyc1^8/5 + 1.0/+2*xyc1^5/5 + 1.0/+2*xyc1^4/5 + 1.0*+2/xyc1/5 + 1.0/+2/25/5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5:Trailing Constant:End Term:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/10.0*xyc1^8.0+1.0/10.0*xyc1^5.0+1.0/10.0*xyc1^4.0+0.4/xyc1+1.0/250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/10.0*8.0*xyc1^7.0+1.0/10.0*5.0*xyc1^4.0+1.0/10.0*4.0*xyc1^3.0-0.4/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.8*xyc1^7.0+0.5*xyc1^4.0+0.4*xyc1^3.0-0.4/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/10.0*xyc1^8.0+1.0/10.0*xyc1^5.0+1.0/10.0*xyc1^4.0+0.4/xyc1+1.0/250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.1/9.0*xyc1^9.0+0.1/6.0*xyc1^6.0+0.1/5.0*xyc1^5.0+0.4*ln(xyc1)+0.004*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.011111111111111112*xyc1^9.0+0.016666666666666666*xyc1^6.0+0.02*xyc1^5.0+0.4*ln(xyc1)+0.004*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.8*xyc1^7.0+0.5*xyc1^4.0+0.4*xyc1^3.0-0.4/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.8*7.0*xyc1^6.0+0.5*4.0*xyc1^3.0+0.4*3.0*xyc1^2.0-0.4*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "5.6000000000000005*xyc1^6.0+2.0*xyc1^3.0+1.2000000000000002*xyc1^2.0+0.8/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "5.6000000000000005*xyc1^6.0+2.0*xyc1^3.0+1.2000000000000002*xyc1^2.0+0.8/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "5.6000000000000005*6.0*xyc1^5.0+2.0*3.0*xyc1^2.0+1.2000000000000002*2.0*xyc1^1.0+0.8*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "33.6*xyc1^5.0+6.0*xyc1^2.0+2.4000000000000004*xyc1-2.4000000000000004/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "33.6*xyc1^5.0+6.0*xyc1^2.0+2.4000000000000004*xyc1-2.4000000000000004/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "33.6*5.0*xyc1^4.0+6.0*2.0*xyc1^1.0+2.4000000000000004-2.4000000000000004*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "168.0*xyc1^4.0+12.0*xyc1+2.4000000000000004+9.600000000000001/xyc1^5.0";
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
       public static void TestCaseDiff20043() {
        //Test Case 20043
        UsageCalculus.TestCase = 20043;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+2*xyc1^8/5*xyc1/7 + 1.0/+2*xyc1^5/5/xyc1/7 + 1.0/+2*xyc1^4/5/xyc1/7 + 1.0/+2/xyc1/5/xyc1/7 + 1.0/+2/25/5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5*xyc1*7:Trailing Constant:Middle Term";
        UsageCalculus.TestCaseMatrixID="Coefficients:5*xyc1*7:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/70.0*xyc1^9.0+1.0/70.0*xyc1^4.0+1.0/70.0*xyc1^3.0+1.0/70.0*xyc1+1.0/250.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/70.0*9.0*xyc1^8.0+1.0/70.0*4.0*xyc1^3.0+1.0/70.0*3.0*xyc1^2.0+1.0/70.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.12857142857142856*xyc1^8.0+0.05714285714285714*xyc1^3.0+0.04285714285714286*xyc1^2.0+1.0/70.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/70.0*xyc1^9.0+1.0/70.0*xyc1^4.0+1.0/70.0*xyc1^3.0+1.0/70.0*xyc1+1.0/250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.014285714285714285/10.0*xyc1^10.0+0.014285714285714285/5.0*xyc1^5.0+0.014285714285714285/4.0*xyc1^4.0+0.014285714285714285/2.0*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.0014285714285714286*xyc1^10.0+0.002857142857142857*xyc1^5.0+0.0035714285714285713*xyc1^4.0+0.007142857142857143*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.12857142857142856*xyc1^8.0+0.05714285714285714*xyc1^3.0+0.04285714285714286*xyc1^2.0+1.0/70.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.12857142857142856*8.0*xyc1^7.0+0.05714285714285714*3.0*xyc1^2.0+0.04285714285714286*2.0*xyc1^1.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "1.0285714285714285*xyc1^7.0+0.17142857142857143*xyc1^2.0+0.08571428571428572*xyc1";
        UsageCalculus.EqnSolArrayMap3[2]="0.0014285714285714286*xyc1^10.0+0.002857142857142857*xyc1^5.0+0.0035714285714285713*xyc1^4.0+0.007142857142857143*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0014285714285714286/11.0*xyc1^11.0+0.002857142857142857/6.0*xyc1^6.0+0.0035714285714285713/5.0*xyc1^5.0+0.007142857142857143/3.0*xyc1^3.0+0.004/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="1.2987012987012987E-4*xyc1^11.0+4.761904761904762E-4*xyc1^6.0+7.142857142857143E-4*xyc1^5.0+0.0023809523809523807*xyc1^3.0+0.002*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "1.0285714285714285*xyc1^7.0+0.17142857142857143*xyc1^2.0+0.08571428571428572*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "1.0285714285714285*7.0*xyc1^6.0+0.17142857142857143*2.0*xyc1^1.0+0.08571428571428572";
        UsageCalculus.EqnSolArrayMap2[3] = "7.199999999999999*xyc1^6.0+0.34285714285714286*xyc1+0.08571428571428572";
        UsageCalculus.EqnSolArrayMap3[3]="1.2987012987012987E-4*xyc1^11.0+4.761904761904762E-4*xyc1^6.0+7.142857142857143E-4*xyc1^5.0+0.0023809523809523807*xyc1^3.0+0.002*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="1.2987012987012987E-4/12.0*xyc1^12.0+4.761904761904762E-4/7.0*xyc1^7.0+7.142857142857143E-4/6.0*xyc1^6.0+0.0023809523809523807/4.0*xyc1^4.0+0.002/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="1.0822510822510823E-5*xyc1^12.0+6.802721088435374E-5*xyc1^7.0+1.1904761904761905E-4*xyc1^6.0+5.952380952380952E-4*xyc1^4.0+6.666666666666666E-4*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "7.199999999999999*xyc1^6.0+0.34285714285714286*xyc1+0.08571428571428572";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "7.199999999999999*6.0*xyc1^5.0+0.34285714285714286+0.0";
        UsageCalculus.EqnSolArrayMap2[4] = "43.199999999999996*xyc1^5.0+0.34285714285714286";
        UsageCalculus.EqnSolArrayMap3[4]="1.0822510822510823E-5*xyc1^12.0+6.802721088435374E-5*xyc1^7.0+1.1904761904761905E-4*xyc1^6.0+5.952380952380952E-4*xyc1^4.0+6.666666666666666E-4*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="1.0822510822510823E-5/13.0*xyc1^13.0+6.802721088435374E-5/8.0*xyc1^8.0+1.1904761904761905E-4/7.0*xyc1^7.0+5.952380952380952E-4/5.0*xyc1^5.0+6.666666666666666E-4/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.325008325008325E-7*xyc1^13.0+8.503401360544217E-6*xyc1^8.0+1.7006802721088435E-5*xyc1^7.0+1.1904761904761903E-4*xyc1^5.0+1.6666666666666666E-4*xyc1^4.0";
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
       public static void TestCaseDiff20044() {
        //Test Case 20044
        UsageCalculus.TestCase = 20044;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+2/xyc1^8/5^xyc1 + 1.0/+2/xyc1^5/5^xyc1 + 1.0/+2/xyc1^4/5^xyc1 + 1.0/+2*xyc1*5^xyc1 + 1.0/+2/25/5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5^xyc1:";
        UsageCalculus.TestCaseMatrixID="Coefficients:5^xyc1:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.1/xyc1^8.0+0.1/xyc1^5.0+0.1/xyc1^4.0+2.5*xyc1+0.004";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.1*-8.0/xyc1^9.0+0.1*-5.0/xyc1^6.0+0.1*-4.0/xyc1^5.0+2.5+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.8/xyc1^9.0-0.5/xyc1^6.0-0.4/xyc1^5.0+2.5";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/2.0/xyc1^8.0/5.0^xyc1+1.0/2.0/xyc1^5.0/5.0^xyc1+1.0/2.0/xyc1^4.0/5.0^xyc1+1.0/2.0*xyc1*5.0^xyc1+1.0/250.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.1/-7.0/xyc1^7.0+0.1/-4.0/xyc1^4.0+0.1/-3.0/xyc1^3.0+2.5/2.0*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.014285714285714287/xyc1^7.0-0.025/xyc1^4.0-0.03333333333333333/xyc1^3.0+1.25*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.8/xyc1^9.0-0.5/xyc1^6.0-0.4/xyc1^5.0+2.5";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.8*-9.0/xyc1^10.0-0.5*-6.0/xyc1^7.0-0.4*-5.0/xyc1^6.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "7.2/xyc1^10.0+3.0/xyc1^7.0+2.0/xyc1^6.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.014285714285714287/xyc1^7.0-0.025/xyc1^4.0-0.03333333333333333/xyc1^3.0+1.25*xyc1^2.0+0.004*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.014285714285714287/-6.0/xyc1^6.0-0.025/-3.0/xyc1^3.0-0.03333333333333333/-2.0/xyc1^2.0+1.25/3.0*xyc1^3.0+0.004/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.002380952380952381/xyc1^6.0+0.008333333333333333/xyc1^3.0+0.016666666666666666/xyc1^2.0+0.4166666666666667*xyc1^3.0+0.002*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "7.2/xyc1^10.0+3.0/xyc1^7.0+2.0/xyc1^6.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "7.2*-10.0/xyc1^11.0+3.0*-7.0/xyc1^8.0+2.0*-6.0/xyc1^7.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-72.0/xyc1^11.0-21.0/xyc1^8.0-12.0/xyc1^7.0";
        UsageCalculus.EqnSolArrayMap3[3]="0.002380952380952381/xyc1^6.0+0.008333333333333333/xyc1^3.0+0.016666666666666666/xyc1^2.0+0.4166666666666667*xyc1^3.0+0.002*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.002380952380952381/-5.0/xyc1^5.0+0.008333333333333333/-2.0/xyc1^2.0+0.016666666666666666/-1.0/xyc1^1.0+0.4166666666666667/4.0*xyc1^4.0+0.002/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.7619047619047624E-4/xyc1^5.0-0.004166666666666667/xyc1^2.0-0.016666666666666666/xyc1+0.10416666666666667*xyc1^4.0+6.666666666666666E-4*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-72.0/xyc1^11.0-21.0/xyc1^8.0-12.0/xyc1^7.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-72.0*-11.0/xyc1^12.0-21.0*-8.0/xyc1^9.0-12.0*-7.0/xyc1^8.0";
        UsageCalculus.EqnSolArrayMap2[4] = "792.0/xyc1^12.0+168.0/xyc1^9.0+84.0/xyc1^8.0";
        UsageCalculus.EqnSolArrayMap3[4]="-4.7619047619047624E-4/xyc1^5.0-0.004166666666666667/xyc1^2.0-0.016666666666666666/xyc1+0.10416666666666667*xyc1^4.0+6.666666666666666E-4*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-4.7619047619047624E-4/-4.0/xyc1^4.0-0.004166666666666667/-1.0/xyc1^1.0-0.016666666666666666*ln(xyc1)+0.10416666666666667/5.0*xyc1^5.0+6.666666666666666E-4/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="1.1904761904761906E-4/xyc1^4.0+0.004166666666666667/xyc1-0.016666666666666666*ln(xyc1)+0.020833333333333336*xyc1^5.0+1.6666666666666666E-4*xyc1^4.0";
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
       public static void TestCaseDiff20045() {
        //Test Case 20045
        UsageCalculus.TestCase = 20045;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-0.0/x^8 + 1*-0.0/x^5 + 1*-0.0/x^4 - 1*-0.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/x^10.0+0.0*-6.0/x^7.0+0.0*-5.0/x^6.0-0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/x^11.0-0.0*-7.0/x^8.0-0.0*-6.0/x^7.0+0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/x^12.0+0.0*-8.0/x^9.0+0.0*-7.0/x^8.0-0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/x^12.0-0.0/x^9.0-0.0/x^8.0+0.0/x^5.0";
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
        public static void TestCaseDiff20046() {
        //Test Case 20046
        UsageCalculus.TestCase = 20046;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1*-0/xy^8 + 1*-0/xy^5 + 1*-0/xy^4 - 1*-0/xy - 25*-0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/xy^9.0-0.0*-5.0/xy^6.0-0.0*-4.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/xy^8.0-0.0/xy^5.0-0.0/xy^4.0+0.0/xy";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/xy^7.0-0.0/-4.0/xy^4.0-0.0/-3.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/xy^7.0+0.0/xy^4.0+0.0/xy^3.0+0.0*ln(xy)";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/xy^9.0+0.0/xy^6.0+0.0/xy^5.0-0.0/xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/xy^10.0+0.0*-6.0/xy^7.0+0.0*-5.0/xy^6.0-0.0*-2.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/xy^10.0-0.0/xy^7.0-0.0/xy^6.0+0.0/xy^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/xy^11.0-0.0*-7.0/xy^8.0-0.0*-6.0/xy^7.0+0.0*-3.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/xy^11.0+0.0/xy^8.0+0.0/xy^7.0-0.0/xy^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/xy^12.0+0.0*-8.0/xy^9.0+0.0*-7.0/xy^8.0-0.0*-4.0/xy^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/xy^12.0-0.0/xy^9.0-0.0/xy^8.0+0.0/xy^5.0";
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
        public static void TestCaseDiff20047() {
        //Test Case 20047
        UsageCalculus.TestCase = 20047;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1*+1/xyc^8 + 1*+1/xyc^5 + 1*+1/xyc^4 - 1*+1/xyc - 1*+1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter :Coefficients:+1:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
       
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff20048() {
        //Test Case 20048
        UsageCalculus.TestCase = 20048;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0.0/x^8*0/0 + 0.0/x^5*0/0 + 0.0/x^4*0/0 - 0.0/x*0/0- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "NaN*-8.0/x^9.0+NaN*-5.0/x^6.0+NaN*-4.0/x^5.0+NaN/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="NaN/x^8.0+NaN/x^5.0+NaN/x^4.0-NaN/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="NaN/-7.0/x^7.0+NaN/-4.0/x^4.0+NaN/-3.0/x^3.0-NaN*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="-NaN/7.0/x^7.0-NaN/4.0/x^4.0-NaN/3.0/x^3.0-NaN*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-NaN*8.0/x^9.0-NaN*5.0/x^6.0-NaN*4.0/x^5.0+NaN/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-NaN*8.0*-9.0/x^10.0-NaN*5.0*-6.0/x^7.0-NaN*4.0*-5.0/x^6.0+NaN*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0-NaN*2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "NaN*72.0/x^10.0+NaN*30.0/x^7.0+NaN*20.0/x^6.0-NaN*2.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "NaN*72.0*-10.0/x^11.0+NaN*30.0*-7.0/x^8.0+NaN*20.0*-6.0/x^7.0-NaN*2.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*6.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-NaN*720.0/x^11.0-NaN*210.0/x^8.0-NaN*120.0/x^7.0+NaN*6.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-NaN*720.0*-11.0/x^12.0-NaN*210.0*-8.0/x^9.0-NaN*120.0*-7.0/x^8.0+NaN*6.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "NaN*7920.0/x^12.0+NaN*1680.0/x^9.0+NaN*840.0/x^8.0-NaN*24.0/x^5.0";
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
        public static void TestCaseDiff20049() {
        //Test Case 20049
        UsageCalculus.TestCase = 20049;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "0/xy^8*0/1 + 0/xy^5*0/1 + 0/xy^4*0/1 - 0/xy*0/0 - 25/0*0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0:2 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.0/xy^8.0+0.0/xy^5.0+0.0/xy^4.0-NaN/xy-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.0*-8.0/xy^9.0+0.0*-5.0/xy^6.0+0.0*-4.0/xy^5.0+NaN/xy^2.0-NaN";
        UsageCalculus.EqnSolArrayMap2[1] = "-0.0/xy^9.0-0.0/xy^6.0-0.0/xy^5.0+NaN/xy^2.0-NaN";
        UsageCalculus.EqnSolArrayMap3[1]="0.0/xy^8.0+0.0/xy^5.0+0.0/xy^4.0-NaN/xy-NaN";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.0/-7.0/xy^7.0+0.0/-4.0/xy^4.0+0.0/-3.0/xy^3.0-NaN*ln(xy)-NaN*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-0.0/xy^7.0-0.0/xy^4.0-0.0/xy^3.0-NaN*ln(xy)-NaN*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-0.0/xy^9.0-0.0/xy^6.0-0.0/xy^5.0+NaN/xy^2.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-0.0*-9.0/xy^10.0-0.0*-6.0/xy^7.0-0.0*-5.0/xy^6.0+NaN*-2.0/xy^3.0-NaN";
        UsageCalculus.EqnSolArrayMap2[2] = "0.0/xy^10.0+0.0/xy^7.0+0.0/xy^6.0-NaN*2.0/xy^3.0-NaN";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.0/xy^10.0+0.0/xy^7.0+0.0/xy^6.0-NaN*2.0/xy^3.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.0*-10.0/xy^11.0+0.0*-7.0/xy^8.0+0.0*-6.0/xy^7.0-NaN*2.0*-3.0/xy^4.0-NaN";
        UsageCalculus.EqnSolArrayMap2[3] = "-0.0/xy^11.0-0.0/xy^8.0-0.0/xy^7.0+NaN*6.0/xy^4.0-NaN";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-0.0/xy^11.0-0.0/xy^8.0-0.0/xy^7.0+NaN*6.0/xy^4.0-NaN";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-0.0*-11.0/xy^12.0-0.0*-8.0/xy^9.0-0.0*-7.0/xy^8.0+NaN*6.0*-4.0/xy^5.0-NaN";
        UsageCalculus.EqnSolArrayMap2[4] = "0.0/xy^12.0+0.0/xy^9.0+0.0/xy^8.0-NaN*24.0/xy^5.0-NaN";
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
        public static void TestCaseDiff20050() {
        //Test Case 20050
        UsageCalculus.TestCase = 20050;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1/1/1/xyc^8 + 1/1*1/xyc^5 + 1/1/1/xyc^4 - 1/1*1/xyc - 1/1*1/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:1:3 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc^8.0+1.0/xyc^5.0+1.0/xyc^4.0-1.0/xyc-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc^7.0+1.0/-4.0/xyc^4.0+1.0/-3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc^7.0-1.0/4.0/xyc^4.0-1.0/3.0/xyc^3.0-ln(xyc)-0.04*xyc";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc^9.0-5.0/xyc^6.0-4.0/xyc^5.0+1.0/xyc^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc^10.0-5.0*-6.0/xyc^7.0-4.0*-5.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc^10.0+30.0/xyc^7.0+20.0/xyc^6.0-2.0/xyc^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc^11.0+30.0*-7.0/xyc^8.0+20.0*-6.0/xyc^7.0-2.0*-3.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc^11.0-210.0/xyc^8.0-120.0/xyc^7.0+6.0/xyc^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc^12.0-210.0*-8.0/xyc^9.0-120.0*-7.0/xyc^8.0+6.0*-4.0/xyc^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc^12.0+1680.0/xyc^9.0+840.0/xyc^8.0-24.0/xyc^5.0";
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
        public static void TestCaseDiff20051() {
        //Test Case 20051
        UsageCalculus.TestCase = 20051;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1.0/xyc1^8 + 1.0/1.0/xyc1^5 + 1.0/xyc1^4 - 1.0/1.0/xyc1 - 1.0/25";
        UsageCalculus.TestCaseName = "Simple: Variables:3 Letter with Numerics:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:1.0:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/xyc1^5.0+1.0/xyc1^4.0-1.0/xyc1-1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+1.0/-4.0/xyc1^4.0+1.0/-3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc1^7.0-1.0/4.0/xyc1^4.0-1.0/3.0/xyc1^3.0-ln(xyc1)-0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0-5.0/xyc1^6.0-4.0/xyc1^5.0+1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0-5.0*-6.0/xyc1^7.0-4.0*-5.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+30.0/xyc1^7.0+20.0/xyc1^6.0-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+30.0*-7.0/xyc1^8.0+20.0*-6.0/xyc1^7.0-2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0-210.0/xyc1^8.0-120.0/xyc1^7.0+6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0-210.0*-8.0/xyc1^9.0-120.0*-7.0/xyc1^8.0+6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+1680.0/xyc1^9.0+840.0/xyc1^8.0-24.0/xyc1^5.0";
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
    public static void TestCaseDiff20052() {
        //Test Case 20052
        UsageCalculus.TestCase = 20052;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-1.0/-1.0*-1.0/x^8 -1.0/-1.0/-1.0*x^5 -1.0/-1.0*-1.0*x^4 -1.0/-1.0/-1.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1.0:1 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-1.0/x^8.0-x^5.0-x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0/x^9.0-5.0*x^4.0-4.0*x^3.0-1.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0/x^9.0-5.0*x^4.0-4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-1.0/x^8.0-x^5.0-x^4.0-x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/-7.0/x^7.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/7.0/x^7.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0/x^9.0-5.0*x^4.0-4.0*x^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*-9.0/x^10.0-5.0*4.0*x^3.0-4.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-72.0/x^10.0-20.0*x^3.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/7.0/x^7.0-1.0/6.0*x^6.0-1.0/5.0*x^5.0-1.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.14285714285714285/-6.0/x^6.0-0.16666666666666666/7.0*x^7.0-0.2/6.0*x^6.0-0.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.023809523809523808/x^6.0-0.023809523809523808*x^7.0-0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-72.0/x^10.0-20.0*x^3.0-12.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-72.0*-10.0/x^11.0-20.0*3.0*x^2.0-12.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "720.0/x^11.0-60.0*x^2.0-24.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="-0.023809523809523808/x^6.0-0.023809523809523808*x^7.0-0.03333333333333333*x^6.0-0.16666666666666666*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.023809523809523808/-5.0/x^5.0-0.023809523809523808/8.0*x^8.0-0.03333333333333333/7.0*x^7.0-0.16666666666666666/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0047619047619047615/x^5.0-0.002976190476190476*x^8.0-0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "720.0/x^11.0-60.0*x^2.0-24.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "720.0*-11.0/x^12.0-60.0*2.0*x^1.0-24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-7920.0/x^12.0-120.0*x-24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0047619047619047615/x^5.0-0.002976190476190476*x^8.0-0.0047619047619047615*x^7.0-0.041666666666666664*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0047619047619047615/-4.0/x^4.0-0.002976190476190476/9.0*x^9.0-0.0047619047619047615/8.0*x^8.0-0.041666666666666664/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-0.0011904761904761904/x^4.0-3.3068783068783067E-4*x^9.0-5.952380952380952E-4*x^8.0-0.008333333333333333*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff20053() {
        //Test Case 20053
        UsageCalculus.TestCase = 20053;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-1/-1*-1*xy^8 -1/-1*-1*xy^5 -1/-1*-1*xy^4 -1/-1*-1*xy - 25/-1/-1*-1*";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-1";
        UsageCalculus.TestCaseMatrixID="Coefficients:-1:2 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-xy^8.0-xy^5.0-xy^4.0-xy+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayMap3[1]="-xy^8.0-xy^5.0-xy^4.0-xy+25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0*xy^7.0-5.0*xy^4.0-4.0*xy^3.0-1.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*7.0*xy^6.0-5.0*4.0*xy^3.0-4.0*3.0*xy^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-56.0*xy^6.0-20.0*xy^3.0-12.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/9.0*xy^9.0-1.0/6.0*xy^6.0-1.0/5.0*xy^5.0-1.0/2.0*xy^2.0+25.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.1111111111111111/10.0*xy^10.0-0.16666666666666666/7.0*xy^7.0-0.2/6.0*xy^6.0-0.5/3.0*xy^3.0+25.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.01111111111111111*xy^10.0-0.023809523809523808*xy^7.0-0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-56.0*xy^6.0-20.0*xy^3.0-12.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-56.0*6.0*xy^5.0-20.0*3.0*xy^2.0-12.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-336.0*xy^5.0-60.0*xy^2.0-24.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="-0.01111111111111111*xy^10.0-0.023809523809523808*xy^7.0-0.03333333333333333*xy^6.0-0.16666666666666666*xy^3.0+12.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.01111111111111111/11.0*xy^11.0-0.023809523809523808/8.0*xy^8.0-0.03333333333333333/7.0*xy^7.0-0.16666666666666666/4.0*xy^4.0+12.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0010101010101010099*xy^11.0-0.002976190476190476*xy^8.0-0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-336.0*xy^5.0-60.0*xy^2.0-24.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-336.0*5.0*xy^4.0-60.0*2.0*xy^1.0-24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-1680.0*xy^4.0-120.0*xy-24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0010101010101010099*xy^11.0-0.002976190476190476*xy^8.0-0.0047619047619047615*xy^7.0-0.041666666666666664*xy^4.0+4.166666666666667*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0010101010101010099/12.0*xy^12.0-0.002976190476190476/9.0*xy^9.0-0.0047619047619047615/8.0*xy^8.0-0.041666666666666664/5.0*xy^5.0+4.166666666666667/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-8.417508417508415E-5*xy^12.0-3.3068783068783067E-4*xy^9.0-5.952380952380952E-4*xy^8.0-0.008333333333333333*xy^5.0+1.0416666666666667*xy^4.0";
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
        public static void TestCaseDiff20054() {
        //Test Case 20054
        UsageCalculus.TestCase = 20054;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "5/5*5*x^8 + 5/5*5*x^5 + 5/5*5*x^4 - 5/5*5*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:1 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "5.0*x^8.0+5.0*x^5.0+5.0*x^4.0-5.0*x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "5.0*8.0*x^7.0+5.0*5.0*x^4.0+5.0*4.0*x^3.0-5.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "40.0*x^7.0+25.0*x^4.0+20.0*x^3.0-5.0";
        UsageCalculus.EqnSolArrayMap3[1]="5.0*x^8.0+5.0*x^5.0+5.0*x^4.0-5.0*x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="5.0/9.0*x^9.0+5.0/6.0*x^6.0+5.0/5.0*x^5.0-5.0/2.0*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.5555555555555556*x^9.0+0.8333333333333334*x^6.0+x^5.0-2.5*x^2.0-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "40.0*x^7.0+25.0*x^4.0+20.0*x^3.0-5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "40.0*7.0*x^6.0+25.0*4.0*x^3.0+20.0*3.0*x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "280.0*x^6.0+100.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.5555555555555556*x^9.0+0.8333333333333334*x^6.0+x^5.0-2.5*x^2.0-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.5555555555555556/10.0*x^10.0+0.8333333333333334/7.0*x^7.0+1.0/6.0*x^6.0-2.5/3.0*x^3.0-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.05555555555555556*x^10.0+0.11904761904761905*x^7.0+1.0/6.0*x^6.0-0.8333333333333334*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "280.0*x^6.0+100.0*x^3.0+60.0*x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "280.0*6.0*x^5.0+100.0*3.0*x^2.0+60.0*2.0*x^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "1680.0*x^5.0+300.0*x^2.0+120.0*x";
        UsageCalculus.EqnSolArrayMap3[3]="0.05555555555555556*x^10.0+0.11904761904761905*x^7.0+1.0/6.0*x^6.0-0.8333333333333334*x^3.0-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.05555555555555556/11.0*x^11.0+0.11904761904761905/8.0*x^8.0+0.16666666666666666/7.0*x^7.0-0.8333333333333334/4.0*x^4.0-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.005050505050505051*x^11.0+0.014880952380952382*x^8.0+0.023809523809523808*x^7.0-0.20833333333333334*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "1680.0*x^5.0+300.0*x^2.0+120.0*x";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "1680.0*5.0*x^4.0+300.0*2.0*x^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "8400.0*x^4.0+600.0*x+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.005050505050505051*x^11.0+0.014880952380952382*x^8.0+0.023809523809523808*x^7.0-0.20833333333333334*x^4.0-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.005050505050505051/12.0*x^12.0+0.014880952380952382/9.0*x^9.0+0.023809523809523808/8.0*x^8.0-0.20833333333333334/5.0*x^5.0-4.166666666666667/4.0*x^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="4.208754208754209E-4*x^12.0+0.0016534391534391536*x^9.0+0.002976190476190476*x^8.0-0.04166666666666667*x^5.0-1.0416666666666667*x^4.0";
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
        public static void TestCaseDiff20055() {
        //Test Case 20055
        UsageCalculus.TestCase = 20055;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "-5/-5*-5*xy^8 - (-5)/-5*-5*xy^5 - (-5)/-5*-5*xy^4 - (-5)/-5*-5*xy - 25/-5/-5*-5*";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5:2 Letter:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xy";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-5.0*xy^8.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-5.0*8.0*xy^7.0+5.0*5.0*xy^4.0+5.0*4.0*xy^3.0+5.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-40.0*xy^7.0+25.0*xy^4.0+20.0*xy^3.0+5.0";
        UsageCalculus.EqnSolArrayMap3[1]="-5.0*xy^8.0+5.0*xy^5.0+5.0*xy^4.0+5.0*xy+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-5.0/9.0*xy^9.0+5.0/6.0*xy^6.0+5.0/5.0*xy^5.0+5.0/2.0*xy^2.0+5.0*xy";
        UsageCalculus.EqnSolArrayMap4[1]="-0.5555555555555556*xy^9.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+5.0*xy";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-40.0*xy^7.0+25.0*xy^4.0+20.0*xy^3.0+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-40.0*7.0*xy^6.0+25.0*4.0*xy^3.0+20.0*3.0*xy^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-280.0*xy^6.0+100.0*xy^3.0+60.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-0.5555555555555556*xy^9.0+0.8333333333333334*xy^6.0+xy^5.0+2.5*xy^2.0+5.0*xy";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.5555555555555556/10.0*xy^10.0+0.8333333333333334/7.0*xy^7.0+1.0/6.0*xy^6.0+2.5/3.0*xy^3.0+5.0/2.0*xy^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-0.05555555555555556*xy^10.0+0.11904761904761905*xy^7.0+1.0/6.0*xy^6.0+0.8333333333333334*xy^3.0+2.5*xy^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-280.0*xy^6.0+100.0*xy^3.0+60.0*xy^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-280.0*6.0*xy^5.0+100.0*3.0*xy^2.0+60.0*2.0*xy^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-1680.0*xy^5.0+300.0*xy^2.0+120.0*xy";
        UsageCalculus.EqnSolArrayMap3[3]="-0.05555555555555556*xy^10.0+0.11904761904761905*xy^7.0+1.0/6.0*xy^6.0+0.8333333333333334*xy^3.0+2.5*xy^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-0.05555555555555556/11.0*xy^11.0+0.11904761904761905/8.0*xy^8.0+0.16666666666666666/7.0*xy^7.0+0.8333333333333334/4.0*xy^4.0+2.5/3.0*xy^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.005050505050505051*xy^11.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+0.8333333333333334*xy^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-1680.0*xy^5.0+300.0*xy^2.0+120.0*xy";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-1680.0*5.0*xy^4.0+300.0*2.0*xy^1.0+120.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-8400.0*xy^4.0+600.0*xy+120.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.005050505050505051*xy^11.0+0.014880952380952382*xy^8.0+0.023809523809523808*xy^7.0+0.20833333333333334*xy^4.0+0.8333333333333334*xy^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.005050505050505051/12.0*xy^12.0+0.014880952380952382/9.0*xy^9.0+0.023809523809523808/8.0*xy^8.0+0.20833333333333334/5.0*xy^5.0+0.8333333333333334/4.0*xy^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="-4.208754208754209E-4*xy^12.0+0.0016534391534391536*xy^9.0+0.002976190476190476*xy^8.0+0.04166666666666667*xy^5.0+0.20833333333333334*xy^4.0";
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
        public static void TestCaseDiff20056() {
        //Test Case 20056
        UsageCalculus.TestCase = 20056;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "-5.0/-5.0/-5.0*-5.0*xyc1^8 - 5.0/-5.0/-5.0*-5.0*xyc1^5 - 5.0/-5.0/-5.0*-5.0*xyc1^4 - 5.0/-5.0/-5.0*-5.0*xyc1 - 5.0/-5.0/-5.0*-5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-5.0:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
        public static void TestCaseDiff20057() {
        //Test Case 20057
        UsageCalculus.TestCase = 20057;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5.0*+5.0/+5.0/xyc1^8 + 1.0/+5.0*+5.0/+5.0/xyc1^5 + 1.0/+5.0*+5.0/+5.0/xyc1^4 + 1.0/+5.0*+5.0/+5.0/xyc1 + 1.0/+5.0*+5.0/+5.0/+5.0/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/5.0/xyc1^8.0+1.0/5.0/xyc1^5.0+1.0/5.0/xyc1^4.0+1.0/5.0/xyc1+1.0/625.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/5.0*-8.0/xyc1^9.0+1.0/5.0*-5.0/xyc1^6.0+1.0/5.0*-4.0/xyc1^5.0-1.0/5.0/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-1.6/xyc1^9.0-1.0/xyc1^6.0-0.8/xyc1^5.0-1.0/5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/5.0/xyc1^8.0+1.0/5.0/xyc1^5.0+1.0/5.0/xyc1^4.0+1.0/5.0/xyc1+1.0/625.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.2/-7.0/xyc1^7.0+0.2/-4.0/xyc1^4.0+0.2/-3.0/xyc1^3.0+0.2*ln(xyc1)+0.0016*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-0.028571428571428574/xyc1^7.0-0.05/xyc1^4.0-0.06666666666666667/xyc1^3.0+0.2*ln(xyc1)+0.0016*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-1.6/xyc1^9.0-1.0/xyc1^6.0-0.8/xyc1^5.0-1.0/5.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-1.6*-9.0/xyc1^10.0+6.0/xyc1^7.0-0.8*-5.0/xyc1^6.0-1.0/5.0*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "14.4/xyc1^10.0+6.0/xyc1^7.0+4.0/xyc1^6.0+0.4/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "14.4/xyc1^10.0+6.0/xyc1^7.0+4.0/xyc1^6.0+0.4/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "14.4*-10.0/xyc1^11.0+6.0*-7.0/xyc1^8.0+4.0*-6.0/xyc1^7.0+0.4*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-144.0/xyc1^11.0-42.0/xyc1^8.0-24.0/xyc1^7.0-1.2000000000000002/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-144.0/xyc1^11.0-42.0/xyc1^8.0-24.0/xyc1^7.0-1.2000000000000002/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-144.0*-11.0/xyc1^12.0-42.0*-8.0/xyc1^9.0-24.0*-7.0/xyc1^8.0-1.2000000000000002*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1584.0/xyc1^12.0+336.0/xyc1^9.0+168.0/xyc1^8.0+4.800000000000001/xyc1^5.0";
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
        public static void TestCaseDiff20058() {
        //Test Case 20058
        UsageCalculus.TestCase = 20058;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+1*+1/+1*xyc1^8 + 1.0/+1*+1/+1*xyc1^5 + 1.0/+1*+1/+1*xyc1^4 + 1.0/+1*+1/+1*1*xyc1 + 1.0/+1*+1/+1*+1*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
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
        public static void TestCaseDiff20059() {
        //Test Case 20059
        UsageCalculus.TestCase = 20059;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+1.0*+1.0/+1.0*xyc1^8 +1.0/+1.0*+1.0/+1.0*+1.0*xyc1^5 + 1.0/+1.0*+1.0/+1.0/+1.0*xyc1^4 + 1.0/+1.0*+1.0/+1.0/+1.0/xyc1 + 1.0/+1.0*+1.0/+1.0/+1.0/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+1.0:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+1.0:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^8.0+xyc1^5.0+xyc1^4.0+1.0/xyc1+1.0/25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^8.0+xyc1^5.0+xyc1^4.0+1.0/xyc1+1.0/25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+ln(xyc1)+0.04*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+ln(xyc1)+0.04*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+5.0*xyc1^4.0+4.0*xyc1^3.0-1.0/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+20.0*xyc1^3.0+12.0*xyc1^2.0+2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0+2.0*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1-6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+60.0*xyc1^2.0+24.0*xyc1-6.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+24.0-6.0*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+120.0*xyc1+24.0+24.0/xyc1^5.0";
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
        public static void TestCaseDiff20060() {
        //Test Case 20060
        UsageCalculus.TestCase = 20060;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5.0*+5.0/+5.0*+5.0/xyc1^8 + 1.0/+5.0*+5.0/+5.0/+5.0*xyc1^5 + 1.0/+5.0*+5.0/+5.0*+5.0*xyc1^4 + 1.0/+5.0*+5.0/+5.0/+5.0/+5.0*xyc1 + 1.0/+5.0*+5.0/+5.0*+5.0/+5.0*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5.0:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 14;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/xyc1^8.0+1.0/25.0*xyc1^5.0+xyc1^4.0+1.0/125.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-8.0/xyc1^9.0+1.0/25.0*5.0*xyc1^4.0+4.0*xyc1^3.0+1.0/125.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-8.0/xyc1^9.0+0.2*xyc1^4.0+4.0*xyc1^3.0+1.0/125.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/xyc1^8.0+1.0/25.0*xyc1^5.0+xyc1^4.0+1.0/125.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/-7.0/xyc1^7.0+0.04/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+0.008/2.0*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-1.0/7.0/xyc1^7.0+0.006666666666666667*xyc1^6.0+1.0/5.0*xyc1^5.0+0.004*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-8.0/xyc1^9.0+0.2*xyc1^4.0+4.0*xyc1^3.0+1.0/125.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-8.0*-9.0/xyc1^10.0+0.2*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "72.0/xyc1^10.0+0.8*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="-1.0/7.0/xyc1^7.0+0.006666666666666667*xyc1^6.0+1.0/5.0*xyc1^5.0+0.004*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-0.14285714285714285/-6.0/xyc1^6.0+0.006666666666666667/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0+0.004/3.0*xyc1^3.0+5.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.023809523809523808/xyc1^6.0+9.523809523809525E-4*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.0013333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "72.0/xyc1^10.0+0.8*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "72.0*-10.0/xyc1^11.0+0.8*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-720.0/xyc1^11.0+2.4000000000000004*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.023809523809523808/xyc1^6.0+9.523809523809525E-4*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.0013333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.023809523809523808/-5.0/xyc1^5.0+9.523809523809525E-4/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0+0.0013333333333333333/4.0*xyc1^4.0+2.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-0.0047619047619047615/xyc1^5.0+1.1904761904761906E-4*xyc1^8.0+0.0047619047619047615*xyc1^7.0+3.333333333333333E-4*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-720.0/xyc1^11.0+2.4000000000000004*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-720.0*-11.0/xyc1^12.0+2.4000000000000004*2.0*xyc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "7920.0/xyc1^12.0+4.800000000000001*xyc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="-0.0047619047619047615/xyc1^5.0+1.1904761904761906E-4*xyc1^8.0+0.0047619047619047615*xyc1^7.0+3.333333333333333E-4*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="-0.0047619047619047615/-4.0/xyc1^4.0+1.1904761904761906E-4/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0+3.333333333333333E-4/5.0*xyc1^5.0+0.8333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="0.0011904761904761904/xyc1^4.0+1.322751322751323E-5*xyc1^9.0+5.952380952380952E-4*xyc1^8.0+6.666666666666667E-5*xyc1^5.0+0.20833333333333334*xyc1^4.0";
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
        public static void TestCaseDiff20061() {
        //Test Case 20061
        UsageCalculus.TestCase = 20061;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+5.0*+5.0/+5.0/+5.0/+5*xyc1^8 + 1.0/+5.0*+5.0/+5.0/+5.0/+5*xyc1^5 + 1.0/+5.0*+5.0/+5.0*+5.0/+5*xyc1^4 + 1.0/+5.0*+5.0/+5.0/+5.0/+5*xyc1 + 1.0/+5.0*+5.0/+5.0*+5.0/+5*25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+5";
        UsageCalculus.TestCaseMatrixID="Coefficients:+5:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/125.0*xyc1^8.0+1.0/125.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/125.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/125.0*8.0*xyc1^7.0+1.0/125.0*5.0*xyc1^4.0+1.0/5.0*4.0*xyc1^3.0+1.0/125.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.064*xyc1^7.0+0.04*xyc1^4.0+0.8*xyc1^3.0+1.0/125.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/125.0*xyc1^8.0+1.0/125.0*xyc1^5.0+1.0/5.0*xyc1^4.0+1.0/125.0*xyc1+5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.008/9.0*xyc1^9.0+0.008/6.0*xyc1^6.0+0.2/5.0*xyc1^5.0+0.008/2.0*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="8.888888888888889E-4*xyc1^9.0+0.0013333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.004*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.064*xyc1^7.0+0.04*xyc1^4.0+0.8*xyc1^3.0+1.0/125.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.064*7.0*xyc1^6.0+0.04*4.0*xyc1^3.0+0.8*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "0.448*xyc1^6.0+0.16*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="8.888888888888889E-4*xyc1^9.0+0.0013333333333333333*xyc1^6.0+0.04*xyc1^5.0+0.004*xyc1^2.0+5.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="8.888888888888889E-4/10.0*xyc1^10.0+0.0013333333333333333/7.0*xyc1^7.0+0.04/6.0*xyc1^6.0+0.004/3.0*xyc1^3.0+5.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="8.888888888888889E-5*xyc1^10.0+1.9047619047619048E-4*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.0013333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.448*xyc1^6.0+0.16*xyc1^3.0+2.4000000000000004*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.448*6.0*xyc1^5.0+0.16*3.0*xyc1^2.0+2.4000000000000004*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "2.688*xyc1^5.0+0.48*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="8.888888888888889E-5*xyc1^10.0+1.9047619047619048E-4*xyc1^7.0+0.006666666666666667*xyc1^6.0+0.0013333333333333333*xyc1^3.0+2.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="8.888888888888889E-5/11.0*xyc1^11.0+1.9047619047619048E-4/8.0*xyc1^8.0+0.006666666666666667/7.0*xyc1^7.0+0.0013333333333333333/4.0*xyc1^4.0+2.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="8.08080808080808E-6*xyc1^11.0+2.380952380952381E-5*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+3.333333333333333E-4*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "2.688*xyc1^5.0+0.48*xyc1^2.0+4.800000000000001*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "2.688*5.0*xyc1^4.0+0.48*2.0*xyc1^1.0+4.800000000000001";
        UsageCalculus.EqnSolArrayMap2[4] = "13.440000000000001*xyc1^4.0+0.96*xyc1+4.800000000000001";
        UsageCalculus.EqnSolArrayMap3[4]="8.08080808080808E-6*xyc1^11.0+2.380952380952381E-5*xyc1^8.0+9.523809523809525E-4*xyc1^7.0+3.333333333333333E-4*xyc1^4.0+0.8333333333333334*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="8.08080808080808E-6/12.0*xyc1^12.0+2.380952380952381E-5/9.0*xyc1^9.0+9.523809523809525E-4/8.0*xyc1^8.0+3.333333333333333E-4/5.0*xyc1^5.0+0.8333333333333334/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="6.734006734006734E-7*xyc1^12.0+2.6455026455026455E-6*xyc1^9.0+1.1904761904761906E-4*xyc1^8.0+6.666666666666667E-5*xyc1^5.0+0.20833333333333334*xyc1^4.0";
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
        public static void TestCaseDiff20062() {
        //Test Case 20062
        UsageCalculus.TestCase = 20062;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+235.0*+235.0/+235.0/+5.0/+235/xyc1^8 + 1.0/+235.0*+235.0/+235.0*+5.0/+235/xyc1^5 + 1.0/+235.0*+235.0/+235.0/+5.0/+235*xyc1^4 + 1.0/+235.0*+235.0/+235.0*+5.0/+235/xyc1 + 1.0/+235.0*+235.0/+235.0/+5.0/+235/25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:+235:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:+235:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/276125.0/xyc1^8.0+9.053870529651426E-5/xyc1^5.0+1.0/276125.0*xyc1^4.0+9.053870529651426E-5/xyc1+1.0/6903125.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/276125.0*-8.0/xyc1^9.0+9.053870529651426E-5*-5.0/xyc1^6.0+1.0/276125.0*4.0*xyc1^3.0-9.053870529651426E-5/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "-2.8972385694884562E-5/xyc1^9.0-4.526935264825713E-4/xyc1^6.0+1.4486192847442281E-5*xyc1^3.0-9.053870529651426E-5/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/276125.0/xyc1^8.0+1.0/9.053870529651426E-5/xyc1^5.0+1.0/276125.0*xyc1^4.0+1.0/9.053870529651426E-5/xyc1+1.0/6903125.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="3.6215482118605703E-6/-7.0/xyc1^7.0+11045.0*xyc1-5/-4.0/xyc1^4.0+3.6215482118605703E-6/5.0*xyc1^5.0+11045.0*xyc1-5.0*ln(xyc1)+1.448619284744228E-7*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="-5.173640302657957E-7/xyc1^7.0+11045.0*xyc1+1.25/xyc1^4.0+7.243096423721141E-7*xyc1^5.0+11045.0*xyc1-5.0*ln(xyc1)+1.448619284744228E-7*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "-2.8972385694884562E-5/xyc1^9.0-4.526935264825713E-4/xyc1^6.0+1.4486192847442281E-5*xyc1^3.0-9.053870529651426E-5/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "-2.8972385694884562E-5*-9.0/xyc1^10.0-4.526935264825713E-4*-6.0/xyc1^7.0+1.4486192847442281E-5*3.0*xyc1^2.0-9.053870529651426E-5*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "2.607514712539611E-4/xyc1^10.0+0.002716161158895428/xyc1^7.0+4.345857854232684E-5*xyc1^2.0+1.8107741059302852E-4/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "2.607514712539611E-4/xyc1^10.0+0.002716161158895428/xyc1^7.0+4.345857854232684E-5*xyc1^2.0+1.8107741059302852E-4/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "2.607514712539611E-4*-10.0/xyc1^11.0+0.002716161158895428*-7.0/xyc1^8.0+4.345857854232684E-5*2.0*xyc1^1.0+1.8107741059302852E-4*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "-0.0026075147125396107/xyc1^11.0-0.019013128112267998/xyc1^8.0+8.691715708465368E-5*xyc1-5.432322317790856E-4/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "-0.0026075147125396107/xyc1^11.0-0.019013128112267998/xyc1^8.0+8.691715708465368E-5*xyc1-5.432322317790856E-4/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "-0.0026075147125396107*-11.0/xyc1^12.0-0.019013128112267998*-8.0/xyc1^9.0+8.691715708465368E-5-5.432322317790856E-4*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "0.02868266183793572/xyc1^12.0+0.15210502489814398/xyc1^9.0+8.691715708465368E-5+0.002172928927116342/xyc1^5.0";
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
        
       public static void TestCaseDiff20063() {
        //Test Case 20063
        UsageCalculus.TestCase = 20063;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1.0/+2*xyc1^8/5*7/4 + 1.0/+2*xyc1^5/5*7/4 + 1.0/+2*xyc1^4/5*7/4 + 1.0*+2/xyc1/5*7/4 + 1.0/+2/25/5*7/4";
        UsageCalculus.TestCaseName = "Simple: Coefficients:5:Trailing Constant:End Term:MAP";
        UsageCalculus.TestCaseMatrixID="Coefficients:5:3 Letter with Numerics:Mixed(*,/) Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.175*xyc1^8.0+0.175*xyc1^5.0+0.175*xyc1^4.0+0.7000000000000001/xyc1+0.007";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.175*8.0*xyc1^7.0+0.175*5.0*xyc1^4.0+0.175*4.0*xyc1^3.0-0.7000000000000001/xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "1.4*xyc1^7.0+0.875*xyc1^4.0+0.7*xyc1^3.0-0.7000000000000001/xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="0.175*xyc1^8.0+0.175*xyc1^5.0+0.175*xyc1^4.0+0.7000000000000001/xyc1+0.007";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.175/9.0*xyc1^9.0+0.175/6.0*xyc1^6.0+0.175/5.0*xyc1^5.0+0.7000000000000001*ln(xyc1)+0.007*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.019444444444444445*xyc1^9.0+0.029166666666666664*xyc1^6.0+0.034999999999999996*xyc1^5.0+0.7000000000000001*ln(xyc1)+0.007*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "1.4*xyc1^7.0+0.875*xyc1^4.0+0.7*xyc1^3.0-0.7000000000000001/xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "1.4*7.0*xyc1^6.0+0.875*4.0*xyc1^3.0+0.7*3.0*xyc1^2.0-0.7000000000000001*-2.0/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "9.799999999999999*xyc1^6.0+3.5*xyc1^3.0+2.0999999999999996*xyc1^2.0+1.4000000000000001/xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="";
        UsageCalculus.EqnSolArrayMap4[2]="";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "9.799999999999999*xyc1^6.0+3.5*xyc1^3.0+2.0999999999999996*xyc1^2.0+1.4000000000000001/xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "9.799999999999999*6.0*xyc1^5.0+3.5*3.0*xyc1^2.0+2.0999999999999996*2.0*xyc1^1.0+1.4000000000000001*-3.0/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "58.8*xyc1^5.0+10.5*xyc1^2.0+4.199999999999999*xyc1-4.2/xyc1^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="";
        UsageCalculus.EqnSolArrayMap4[3]="";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "58.8*xyc1^5.0+10.5*xyc1^2.0+4.199999999999999*xyc1-4.2/xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "58.8*5.0*xyc1^4.0+10.5*2.0*xyc1^1.0+4.199999999999999-4.2*-4.0/xyc1^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "294.0*xyc1^4.0+21.0*xyc1+4.199999999999999+16.8/xyc1^5.0";
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
      
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff20064() {
        //Test Case 20064
        UsageCalculus.TestCase = 20064;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "1*-0.0/x^8 + 1*-0.0/x^5 + 1*-0.0/x^4 - 11*-0.0/x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:-0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:-0.0:1 Letter:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "x";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 2;
        
        UsageCalculus.EqnSolArrayMap1[1] = "-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "-0.0*-8.0/x^9.0-0.0*-5.0/x^6.0-0.0*-4.0/x^5.0-0.0/x^2.0-0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayMap3[1]="-0.0/x^8.0-0.0/x^5.0-0.0/x^4.0+0.0/x-25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="-0.0/-7.0/x^7.0-0.0/-4.0/x^4.0-0.0/-3.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolArrayMap4[1]="0.0/x^7.0+0.0/x^4.0+0.0/x^3.0+0.0*ln(x)-25.0*x";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0/x^9.0+0.0/x^6.0+0.0/x^5.0-0.0/x^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0*-9.0/x^10.0+0.0*-6.0/x^7.0+0.0*-5.0/x^6.0-0.0*-2.0/x^3.0";
        UsageCalculus.EqnSolArrayMap2[2] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayMap3[2]="-25.0*x";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="-25.0/2.0*x^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="-12.5*x^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "-0.0/x^10.0-0.0/x^7.0-0.0/x^6.0+0.0/x^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "-0.0*-10.0/x^11.0-0.0*-7.0/x^8.0-0.0*-6.0/x^7.0+0.0*-3.0/x^4.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayMap3[3]="-12.5*x^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="-12.5/3.0*x^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="-4.166666666666667*x^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.0/x^11.0+0.0/x^8.0+0.0/x^7.0-0.0/x^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.0*-11.0/x^12.0+0.0*-8.0/x^9.0+0.0*-7.0/x^8.0-0.0*-4.0/x^5.0";
        UsageCalculus.EqnSolArrayMap2[4] = "-0.0/x^12.0-0.0/x^9.0-0.0/x^8.0+0.0/x^5.0";
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
      
       /*********************************************/
       /************ Missed Out Test Cases **********/
       /*********************************************/
       public static void TestCaseDiff20065() {
        //Test Case 20065
        UsageCalculus.TestCase = 20065;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0-0.008333333333333333*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:Exp Form";
        UsageCalculus.TestCaseMatrixID="Coefficients:Exp Form:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0-0.008333333333333333*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.417508417508415E-5*12.0*xyc1^11.0+3.3068783068783067E-4*9.0*xyc1^8.0+5.952380952380952E-4*8.0*xyc1^7.0-0.008333333333333333*5.0*xyc1^4.0-1.0416666666666667*4.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap3[1]="8.417508417508415E-5*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+5.952380952380952E-4*xyc1^8.0-0.008333333333333333*xyc1^5.0-1.0416666666666667*xyc1^4.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="8.417508417508415E-5/13.0*xyc1^13.0+3.3068783068783067E-4/10.0*xyc1^10.0+5.952380952380952E-4/9.0*xyc1^9.0-0.008333333333333333/6.0*xyc1^6.0-1.0416666666666667/5.0*xyc1^5.0";
        UsageCalculus.EqnSolArrayMap4[1]="6.475006475006474E-6*xyc1^13.0+3.3068783068783064E-5*xyc1^10.0+6.613756613756613E-5*xyc1^9.0-0.001388888888888889*xyc1^6.0-0.20833333333333334*xyc1^5.0";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.0010101010101010099*xyc1^11.0+0.002976190476190476*xyc1^8.0+0.0047619047619047615*xyc1^7.0-0.041666666666666664*xyc1^4.0-4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.0010101010101010099*11.0*xyc1^10.0+0.002976190476190476*8.0*xyc1^7.0+0.0047619047619047615*7.0*xyc1^6.0-0.041666666666666664*4.0*xyc1^3.0-4.166666666666667*3.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap2[2] = "0.011111111111111108*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="6.475006475006474E-6*xyc1^13.0+3.3068783068783064E-5*xyc1^10.0+6.613756613756613E-5*xyc1^9.0-0.001388888888888889*xyc1^6.0-0.20833333333333334*xyc1^5.0";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="6.475006475006474E-6/14.0*xyc1^14.0+3.3068783068783064E-5/11.0*xyc1^11.0+6.613756613756613E-5/10.0*xyc1^10.0-0.001388888888888889/7.0*xyc1^7.0-0.20833333333333334/6.0*xyc1^6.0";
        UsageCalculus.EqnSolArrayMap4[2]="4.625004625004624E-7*xyc1^14.0+3.0062530062530057E-6*xyc1^11.0+6.613756613756613E-6*xyc1^10.0-1.984126984126984E-4*xyc1^7.0-0.034722222222222224*xyc1^6.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "0.011111111111111108*xyc1^10.0+0.023809523809523808*xyc1^7.0+0.03333333333333333*xyc1^6.0-0.16666666666666666*xyc1^3.0-12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "0.011111111111111108*10.0*xyc1^9.0+0.023809523809523808*7.0*xyc1^6.0+0.03333333333333333*6.0*xyc1^5.0-0.16666666666666666*3.0*xyc1^2.0-12.5*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "0.11111111111111108*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0-0.5*xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="4.625004625004624E-7*xyc1^14.0+3.0062530062530057E-6*xyc1^11.0+6.613756613756613E-6*xyc1^10.0-1.984126984126984E-4*xyc1^7.0-0.034722222222222224*xyc1^6.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="4.625004625004624E-7/15.0*xyc1^15.0+3.0062530062530057E-6/12.0*xyc1^12.0+6.613756613756613E-6/11.0*xyc1^11.0-1.984126984126984E-4/8.0*xyc1^8.0-0.034722222222222224/7.0*xyc1^7.0";
        UsageCalculus.EqnSolArrayMap4[3]="3.083336416669749E-8*xyc1^15.0+2.505210838544171E-7*xyc1^12.0+6.012506012506012E-7*xyc1^11.0-2.48015873015873E-5*xyc1^8.0-0.004960317460317461*xyc1^7.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "0.11111111111111108*xyc1^9.0+0.16666666666666666*xyc1^6.0+0.2*xyc1^5.0-0.5*xyc1^2.0-25.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "0.11111111111111108*9.0*xyc1^8.0+0.16666666666666666*6.0*xyc1^5.0+0.2*5.0*xyc1^4.0-0.5*2.0*xyc1^1.0-25.0";
        UsageCalculus.EqnSolArrayMap2[4] = "0.9999999999999997*xyc1^8.0+xyc1^5.0+xyc1^4.0-xyc1-25.0";
        UsageCalculus.EqnSolArrayMap3[4]="3.083336416669749E-8*xyc1^15.0+2.505210838544171E-7*xyc1^12.0+6.012506012506012E-7*xyc1^11.0-2.48015873015873E-5*xyc1^8.0-0.004960317460317461*xyc1^7.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="3.083336416669749E-8/16.0*xyc1^16.0+2.505210838544171E-7/13.0*xyc1^13.0+6.012506012506012E-7/12.0*xyc1^12.0-2.48015873015873E-5/9.0*xyc1^9.0-0.004960317460317461/8.0*xyc1^8.0";
        UsageCalculus.EqnSolArrayMap4[4]="1.9270852604185933E-9*xyc1^16.0+1.927085260418593E-8*xyc1^13.0+5.0104216770883434E-8*xyc1^12.0-2.7557319223985893E-6*xyc1^9.0-6.200396825396826E-4*xyc1^8.0";
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
       public static void TestCaseDiff20066() {
        //Test Case 20066
        UsageCalculus.TestCase = 20066;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1/9.0*9.0*xyc1^8.0+1*6.0*6.0*xyc1^5.0+1/5.0*5.0*xyc1^4.0+1*2.0*2.0*xyc1^1.0+1*25.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/9.0*9.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:1/9.0*9.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "xyc1^8.0+36.0*xyc1^5.0+xyc1^4.0+4.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "8.0*xyc1^7.0+36.0*5.0*xyc1^4.0+4.0*xyc1^3.0+4.0+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "8.0*xyc1^7.0+180.0*xyc1^4.0+4.0*xyc1^3.0+4.0";
        UsageCalculus.EqnSolArrayMap3[1]="xyc1^8.0+36.0*xyc1^5.0+xyc1^4.0+4.0*xyc1+25.0";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="1.0/9.0*xyc1^9.0+36.0/6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+4.0/2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="1.0/9.0*xyc1^9.0+6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "8.0*xyc1^7.0+180.0*xyc1^4.0+4.0*xyc1^3.0+4.0";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "8.0*7.0*xyc1^6.0+180.0*4.0*xyc1^3.0+4.0*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "56.0*xyc1^6.0+720.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="1.0/9.0*xyc1^9.0+6.0*xyc1^6.0+1.0/5.0*xyc1^5.0+2.0*xyc1^2.0+25.0*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.1111111111111111/10.0*xyc1^10.0+6.0/7.0*xyc1^7.0+0.2/6.0*xyc1^6.0+2.0/3.0*xyc1^3.0+25.0/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="0.01111111111111111*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "56.0*xyc1^6.0+720.0*xyc1^3.0+12.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "56.0*6.0*xyc1^5.0+720.0*3.0*xyc1^2.0+12.0*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "336.0*xyc1^5.0+2160.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="0.01111111111111111*xyc1^10.0+0.8571428571428571*xyc1^7.0+0.03333333333333333*xyc1^6.0+0.6666666666666666*xyc1^3.0+12.5*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="0.01111111111111111/11.0*xyc1^11.0+0.8571428571428571/8.0*xyc1^8.0+0.03333333333333333/7.0*xyc1^7.0+0.6666666666666666/4.0*xyc1^4.0+12.5/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="0.0010101010101010099*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "336.0*xyc1^5.0+2160.0*xyc1^2.0+24.0*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "336.0*5.0*xyc1^4.0+2160.0*2.0*xyc1^1.0+24.0";
        UsageCalculus.EqnSolArrayMap2[4] = "1680.0*xyc1^4.0+4320.0*xyc1+24.0";
        UsageCalculus.EqnSolArrayMap3[4]="0.0010101010101010099*xyc1^11.0+0.10714285714285714*xyc1^8.0+0.0047619047619047615*xyc1^7.0+0.16666666666666666*xyc1^4.0+4.166666666666667*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="0.0010101010101010099/12.0*xyc1^12.0+0.10714285714285714/9.0*xyc1^9.0+0.0047619047619047615/8.0*xyc1^8.0+0.16666666666666666/5.0*xyc1^5.0+4.166666666666667/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="8.417508417508415E-5*xyc1^12.0+0.011904761904761904*xyc1^9.0+5.952380952380952E-4*xyc1^8.0+0.03333333333333333*xyc1^5.0+1.0416666666666667*xyc1^4.0";
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
       public static void TestCaseDiff20067() {
        //Test Case 20067
        UsageCalculus.TestCase = 20067;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";
        UsageCalculus.EqnUnderTest = "5.0/5.0*0.0*x^8 + 0.0*x^5 + 0.0*x^4 - 0.0*x- 25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0:Negative TestCase";
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
       public static void TestCaseDiff20068() {
        //Test Case 20068
        UsageCalculus.TestCase = 20068;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1/9.0*9.0/6.0/6.0*xyc1^8.0+1*6.0*6.0/6.0/6.0*xyc1^5.0+1/5.0*5.0/6.0/6.0*xyc1^4.0+1*2.0*2.0/6.0/6.0*xyc1^1.0+1*25.0/6.0/6.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/9.0*9.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:1/9.0*9.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "1.0/36.0*xyc1^8.0+xyc1^5.0+1.0/36.0*xyc1^4.0+0.1111111111111111*xyc1+0.6944444444444445";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "1.0/36.0*8.0*xyc1^7.0+5.0*xyc1^4.0+1.0/36.0*4.0*xyc1^3.0+0.1111111111111111+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.2222222222222222*xyc1^7.0+5.0*xyc1^4.0+0.1111111111111111*xyc1^3.0+0.1111111111111111";
        UsageCalculus.EqnSolArrayMap3[1]="1.0/36.0*xyc1^8.0+xyc1^5.0+1.0/36.0*xyc1^4.0+0.1111111111111111*xyc1+0.6944444444444445";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.027777777777777776/9.0*xyc1^9.0+1.0/6.0*xyc1^6.0+0.027777777777777776/5.0*xyc1^5.0+0.1111111111111111/2.0*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.0030864197530864196*xyc1^9.0+1.0/6.0*xyc1^6.0+0.005555555555555555*xyc1^5.0+0.05555555555555555*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.2222222222222222*xyc1^7.0+5.0*xyc1^4.0+0.1111111111111111*xyc1^3.0+0.1111111111111111";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.2222222222222222*7.0*xyc1^6.0+5.0*4.0*xyc1^3.0+0.1111111111111111*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "1.5555555555555554*xyc1^6.0+20.0*xyc1^3.0+0.3333333333333333*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.0030864197530864196*xyc1^9.0+1.0/6.0*xyc1^6.0+0.005555555555555555*xyc1^5.0+0.05555555555555555*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0030864197530864196/10.0*xyc1^10.0+0.16666666666666666/7.0*xyc1^7.0+0.005555555555555555/6.0*xyc1^6.0+0.05555555555555555/3.0*xyc1^3.0+0.6944444444444445/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="3.0864197530864197E-4*xyc1^10.0+0.023809523809523808*xyc1^7.0+9.259259259259259E-4*xyc1^6.0+0.018518518518518517*xyc1^3.0+0.34722222222222227*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "1.5555555555555554*xyc1^6.0+20.0*xyc1^3.0+0.3333333333333333*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "1.5555555555555554*6.0*xyc1^5.0+20.0*3.0*xyc1^2.0+0.3333333333333333*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "9.333333333333332*xyc1^5.0+60.0*xyc1^2.0+0.6666666666666666*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="3.0864197530864197E-4*xyc1^10.0+0.023809523809523808*xyc1^7.0+9.259259259259259E-4*xyc1^6.0+0.018518518518518517*xyc1^3.0+0.34722222222222227*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="3.0864197530864197E-4/11.0*xyc1^11.0+0.023809523809523808/8.0*xyc1^8.0+9.259259259259259E-4/7.0*xyc1^7.0+0.018518518518518517/4.0*xyc1^4.0+0.34722222222222227/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="2.8058361391694725E-5*xyc1^11.0+0.002976190476190476*xyc1^8.0+1.3227513227513226E-4*xyc1^7.0+0.004629629629629629*xyc1^4.0+0.11574074074074076*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "9.333333333333332*xyc1^5.0+60.0*xyc1^2.0+0.6666666666666666*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "9.333333333333332*5.0*xyc1^4.0+60.0*2.0*xyc1^1.0+0.6666666666666666";
        UsageCalculus.EqnSolArrayMap2[4] = "46.66666666666666*xyc1^4.0+120.0*xyc1+0.6666666666666666";
        UsageCalculus.EqnSolArrayMap3[4]="2.8058361391694725E-5*xyc1^11.0+0.002976190476190476*xyc1^8.0+1.3227513227513226E-4*xyc1^7.0+0.004629629629629629*xyc1^4.0+0.11574074074074076*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="2.8058361391694725E-5/12.0*xyc1^12.0+0.002976190476190476/9.0*xyc1^9.0+1.3227513227513226E-4/8.0*xyc1^8.0+0.004629629629629629/5.0*xyc1^5.0+0.11574074074074076/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.338196782641227E-6*xyc1^12.0+3.3068783068783067E-4*xyc1^9.0+1.6534391534391532E-5*xyc1^8.0+9.259259259259259E-4*xyc1^5.0+0.02893518518518519*xyc1^4.0";
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
       public static void TestCaseDiff20069() {
        //Test Case 20069
        UsageCalculus.TestCase = 20069;
        UsageCalculus.EqnRegressionDiffStatus = "All Green Including Values";
        UsageCalculus.EqnRegressionIntegreStatus = "All Green Including Values";        
        UsageCalculus.EqnUnderTest = "1/9.0*9.0/6.0/6.0*6.0/6.0*xyc1^8.0+1*6.0*6.0*6.0/6.0*xyc1^5.0+1/5.0*5.0/6.0/6.0*6.0/6.0*xyc1^4.0+1*2.0*2.0/6.0/6.0*6.0/6.0*xyc1^1.0+1*25.0/6.0/6.0*6.0/6.0";
        UsageCalculus.TestCaseName = "Simple: Coefficients:1/9.0*9.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:1/9.0*9.0:3 Letter with Numerics:/ Operator:No Exponent Sign:Constant End:Term Sign +&-";
        
        UsageCalculus.DiffWithRespTo = "xyc1";
        UsageCalculus.MaxDiffOrder = 14;
        UsageCalculus.MaxIntegreOrder = 5;
        
        UsageCalculus.EqnSolArrayMap1[1] = "0.027777777777777776*xyc1^8.0+36.0*xyc1^5.0+0.027777777777777776*xyc1^4.0+0.1111111111111111*xyc1+0.6944444444444445";
        UsageCalculus.EqnSolArrayDiffAlone[1] = "0.027777777777777776*8.0*xyc1^7.0+36.0*5.0*xyc1^4.0+0.027777777777777776*4.0*xyc1^3.0+0.1111111111111111+0.0";
        UsageCalculus.EqnSolArrayMap2[1] = "0.2222222222222222*xyc1^7.0+180.0*xyc1^4.0+0.1111111111111111*xyc1^3.0+0.1111111111111111";
        UsageCalculus.EqnSolArrayMap3[1]="0.027777777777777776*xyc1^8.0+36.0*xyc1^5.0+0.027777777777777776*xyc1^4.0+0.1111111111111111*xyc1+0.6944444444444445";
        UsageCalculus.EqnSolArrayIntegrAlone[1]="0.027777777777777776/9.0*xyc1^9.0+36.0/6.0*xyc1^6.0+0.027777777777777776/5.0*xyc1^5.0+0.1111111111111111/2.0*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolArrayMap4[1]="0.0030864197530864196*xyc1^9.0+6.0*xyc1^6.0+0.005555555555555555*xyc1^5.0+0.05555555555555555*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolOrder[1] = 1;

        UsageCalculus.EqnSolArrayMap1[2] = "0.2222222222222222*xyc1^7.0+180.0*xyc1^4.0+0.1111111111111111*xyc1^3.0+0.1111111111111111";
        UsageCalculus.EqnSolArrayDiffAlone[2] = "0.2222222222222222*7.0*xyc1^6.0+180.0*4.0*xyc1^3.0+0.1111111111111111*3.0*xyc1^2.0+0.0";
        UsageCalculus.EqnSolArrayMap2[2] = "1.5555555555555554*xyc1^6.0+720.0*xyc1^3.0+0.3333333333333333*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap3[2]="0.0030864197530864196*xyc1^9.0+6.0*xyc1^6.0+0.005555555555555555*xyc1^5.0+0.05555555555555555*xyc1^2.0+0.6944444444444445*xyc1";
        UsageCalculus.EqnSolArrayIntegrAlone[2]="0.0030864197530864196/10.0*xyc1^10.0+6.0/7.0*xyc1^7.0+0.005555555555555555/6.0*xyc1^6.0+0.05555555555555555/3.0*xyc1^3.0+0.6944444444444445/2.0*xyc1^2.0";
        UsageCalculus.EqnSolArrayMap4[2]="3.0864197530864197E-4*xyc1^10.0+0.8571428571428571*xyc1^7.0+9.259259259259259E-4*xyc1^6.0+0.018518518518518517*xyc1^3.0+0.34722222222222227*xyc1^2.0";
        UsageCalculus.EqnSolOrder[2] = 2;

        UsageCalculus.EqnSolArrayMap1[3] = "1.5555555555555554*xyc1^6.0+720.0*xyc1^3.0+0.3333333333333333*xyc1^2.0";
        UsageCalculus.EqnSolArrayDiffAlone[3] = "1.5555555555555554*6.0*xyc1^5.0+720.0*3.0*xyc1^2.0+0.3333333333333333*2.0*xyc1^1.0";
        UsageCalculus.EqnSolArrayMap2[3] = "9.333333333333332*xyc1^5.0+2160.0*xyc1^2.0+0.6666666666666666*xyc1";
        UsageCalculus.EqnSolArrayMap3[3]="3.0864197530864197E-4*xyc1^10.0+0.8571428571428571*xyc1^7.0+9.259259259259259E-4*xyc1^6.0+0.018518518518518517*xyc1^3.0+0.34722222222222227*xyc1^2.0";
        UsageCalculus.EqnSolArrayIntegrAlone[3]="3.0864197530864197E-4/11.0*xyc1^11.0+0.8571428571428571/8.0*xyc1^8.0+9.259259259259259E-4/7.0*xyc1^7.0+0.018518518518518517/4.0*xyc1^4.0+0.34722222222222227/3.0*xyc1^3.0";
        UsageCalculus.EqnSolArrayMap4[3]="2.8058361391694725E-5*xyc1^11.0+0.10714285714285714*xyc1^8.0+1.3227513227513226E-4*xyc1^7.0+0.004629629629629629*xyc1^4.0+0.11574074074074076*xyc1^3.0";
        UsageCalculus.EqnSolOrder[3] = 3;

        UsageCalculus.EqnSolArrayMap1[4] = "9.333333333333332*xyc1^5.0+2160.0*xyc1^2.0+0.6666666666666666*xyc1";
        UsageCalculus.EqnSolArrayDiffAlone[4] = "9.333333333333332*5.0*xyc1^4.0+2160.0*2.0*xyc1^1.0+0.6666666666666666";
        UsageCalculus.EqnSolArrayMap2[4] = "46.66666666666666*xyc1^4.0+4320.0*xyc1+0.6666666666666666";
        UsageCalculus.EqnSolArrayMap3[4]="2.8058361391694725E-5*xyc1^11.0+0.10714285714285714*xyc1^8.0+1.3227513227513226E-4*xyc1^7.0+0.004629629629629629*xyc1^4.0+0.11574074074074076*xyc1^3.0";
        UsageCalculus.EqnSolArrayIntegrAlone[4]="2.8058361391694725E-5/12.0*xyc1^12.0+0.10714285714285714/9.0*xyc1^9.0+1.3227513227513226E-4/8.0*xyc1^8.0+0.004629629629629629/5.0*xyc1^5.0+0.11574074074074076/4.0*xyc1^4.0";
        UsageCalculus.EqnSolArrayMap4[4]="2.338196782641227E-6*xyc1^12.0+0.011904761904761904*xyc1^9.0+1.6534391534391532E-5*xyc1^8.0+9.259259259259259E-4*xyc1^5.0+0.02893518518518519*xyc1^4.0";
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
