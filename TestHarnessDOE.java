/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOETest;

import Calculus.ConsoleColors;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessDOE {

    public static Logger log = Logger.getLogger(TestHarnessDOE.class.getName());
    // Maximum number of Double Fractional Digits displayed in Equation
    private static int MaximumCompFractionDigits = 2;
    public static String TestCaseRow="";
    public static String ConfigFileName="";
    public static String LevelFileName="";
    
    public TestHarnessDOE( String All, String MyEndTestCase,String RegrORIndivORLimit) {
        
        UsageDOE.LastTestCaseHarness=30;
        if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            UsageDOE.TestCase = -1;
            UsageDOE.TestCaseLoopTimes = UsageDOE.LastTestCaseHarness;
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression"))) {
            UsageDOE.TestCase = -1;
            UsageDOE.EndTestCase=Integer.parseInt(MyEndTestCase);
            UsageDOE.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if (RegrORIndivORLimit.equalsIgnoreCase("Individual")) {
            UsageDOE.TestCase = Integer.parseInt(All);
            UsageDOE.TestCaseLoopTimes = 1;
            UsageDOE.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if ((All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Individual"))) {
            System.out.println(" Wrong Input :All & Individual");
            UsageDOE.TestCase = -1;
            UsageDOE.TestCaseLoopTimes = 1;
            UsageDOE.EndTestCase=Integer.parseInt(MyEndTestCase);
        } else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("Regression"))) {
            UsageDOE.TestCase = Integer.parseInt(All);
            UsageDOE.TestCaseLoopTimes = UsageDOE.LastTestCaseHarness;
        }else if ((!All.equalsIgnoreCase("All")) && (RegrORIndivORLimit.equalsIgnoreCase("LimitedRegression"))) {
            UsageDOE.TestCase = Integer.parseInt(All);
            UsageDOE.TestCaseLoopTimes = Integer.parseInt(MyEndTestCase)-UsageDOE.TestCase+1;
            UsageDOE.EndTestCase=Integer.parseInt(MyEndTestCase);
        }
        
    }
    
    //Sanity Test Cases
    public static void SanityTestCases(String AlgoName,int j){
        if(((AlgoName.equalsIgnoreCase("mainDiffTest"))&&(DifferentialGap(j))) )
        {
            System.out.println(ConsoleColors.RED +"Coefficients TestCase:j= " + j  + ": Infinite Loop GAP" + ConsoleColors.RESET);
        }
        else {
            switch(j){
            case -1:
                TestCaseDiffM001();
                break;
            case 0:
                TestCaseDiff000();
                break;
            case 1:
                TestCaseDiff001();
                break;
            case 2:
                TestCaseDiff002();
                break;
            case 3:
                TestCaseDiff003();
                break;
            case 4:
                TestCaseDiff004();
                break;
            case 5:
                TestCaseDiff005();
                break;
            case 6:
                TestCaseDiff006();
                break;
            case 7:
                TestCaseDiff007();
                break;    
            case 8:
                TestCaseDiff008();
                break;
            case 9:
                TestCaseDiff009();
                break;
            case 10:
                TestCaseDiff010();
                break;
            case 11:
                TestCaseDiff011();
                break;
            case 12:
                TestCaseDiff012();
                break;
            case 13:
                TestCaseDiff013();
                break;
            case 14:
                TestCaseDiff014();
                break;
            case 15:
                TestCaseDiff015();
                break;
            case 16:
                TestCaseDiff016();
                break;
            case 17:
                TestCaseDiff017();
                break;    
            case 18:
                TestCaseDiff018();
                break;
            case 19:
                TestCaseDiff019();
                break;
            case 20:
                TestCaseDiff020();
                break;
            case 21:
                TestCaseDiff021();
                break;
            case 22:
                TestCaseDiff022();
                break;
            case 23:
                TestCaseDiff023();
                break;
            case 24:
                TestCaseDiff024();
                break;
            case 25:
                TestCaseDiff025();
                break;
            case 26:
                TestCaseDiff026();
                break;
            case 27:
                TestCaseDiff027();
                break;    
            case 28:
                TestCaseDiff028();
                break;
            case 29:
                TestCaseDiff029();
                break;
            default:
                System.out.println(ConsoleColors.RED +"Names TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
        }
    }
}
     
    public static boolean DifferentialGap(int j){
        //Infinite Loops
        //if((j==34)||(j==35))
        //    return true;
        //else 
        //if((j==20016)||(j==20017)||(j==20046)||(j==20047)||(j==20061)||(j==20062)||(j==20076)||(j==20077))
        //      return true;
        //else if ((j==80001)||(j==80006))
        //    return true;
        return false;
    }
    public static void TestCaseDiffM001() {
        //Test Case 000
        UsageDOE.TestCase = -1;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:Basic Sanity:Default Values";
        UsageDOE.TestCaseMatrixID="Sanity TestCase";
        ConfigFileName="DOESelfTestConfig\\DOEConfigDefault";
        LevelFileName="DOELevelFile";
        TestCaseRow="";
    }
    public static void TestCaseDiff000() {
        //Test Case 000
        UsageDOE.TestCase = 0;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-000";
        ConfigFileName="DOESelfTestConfig\\DOEConfig000";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=0 1 1 1 1 1 1 1 1 1";
    }
   
    public static void TestCaseDiff001() {
        //Test Case 001
        UsageDOE.TestCase = 1;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-001";
        ConfigFileName="DOESelfTestConfig\\DOEConfig001";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=1 1 2 2 2 2 2 2 2 2";
    }
    public static void TestCaseDiff002() {
        //Test Case 002
        UsageDOE.TestCase = 2;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-002";
        ConfigFileName="DOESelfTestConfig\\DOEConfig002";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=2 1 3 2 2 2 2 3 2 3";
    }
    public static void TestCaseDiff003() {
        //Test Case 003
        UsageDOE.TestCase = 3;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-003";
        ConfigFileName="DOESelfTestConfig\\DOEConfig003";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=3 1 4 2 2 2 2 4 2 2";
    }
    public static void TestCaseDiff004() {
        //Test Case 004
        UsageDOE.TestCase = 4;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-004";
        ConfigFileName="DOESelfTestConfig\\DOEConfig004";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=4 1 5 2 2 2 2 5 2 3";
    }
    public static void TestCaseDiff005() {
        //Test Case 005
        UsageDOE.TestCase = 5;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-005";
        ConfigFileName="DOESelfTestConfig\\DOEConfig005";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=5 1 1 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff006() {
        //Test Case 006
        UsageDOE.TestCase = 6;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-006";
        ConfigFileName="DOESelfTestConfig\\DOEConfig006";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=6 2 2 1 2 1 2 3 2 3";
    }
    public static void TestCaseDiff007() {
        //Test Case 007
        UsageDOE.TestCase = 7;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-007";
        ConfigFileName="DOESelfTestConfig\\DOEConfig007";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=7 2 3 2 1 2 1 4 1 1";
    }
    public static void TestCaseDiff008() {
        //Test Case 008
        UsageDOE.TestCase = 8;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-008";
        ConfigFileName="DOESelfTestConfig\\DOEConfig008";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=8 2 4 2 1 1 2 5 2 3";
    }
    public static void TestCaseDiff009() {
        //Test Case 009
        UsageDOE.TestCase = 9;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-009";
        ConfigFileName="DOESelfTestConfig\\DOEConfig009";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=9 2 5 1 2 1 1 1 1 1";
    }
    public static void TestCaseDiff010() {
        //Test Case 010
        UsageDOE.TestCase = 10;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-010";
        ConfigFileName="DOESelfTestConfig\\DOEConfig010";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=10 2 1 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff011() {
        //Test Case 011
        UsageDOE.TestCase = 11;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-011";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0011";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=11 2 2 1 2 1 2 3 2 3";
    }
    public static void TestCaseDiff012() {
        //Test Case 012
        UsageDOE.TestCase = 12;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-012";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0012";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=12 3 3 1 1 2 1 4 1 1";
    }
    public static void TestCaseDiff013() {
        //Test Case 013
        UsageDOE.TestCase = 13;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-013";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0013";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=13 3 4 1 2 1 2 5 2 3";
    }
    public static void TestCaseDiff014() {
        //Test Case 014
        UsageDOE.TestCase = 14;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-014";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0014";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=14 3 5 1 1 2 1 1 1 1";
    }
    public static void TestCaseDiff015() {
        //Test Case 015
        UsageDOE.TestCase = 15;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-015";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0015";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=15 3 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff016() {
        //Test Case 016
        UsageDOE.TestCase = 16;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-016";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0016";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=16 3 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff017() {
        //Test Case 017
        UsageDOE.TestCase = 17;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-017";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0017";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=17 3 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff018() {
        //Test Case 018
        UsageDOE.TestCase = 18;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-018";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0018";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=18 4 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff019() {
        //Test Case 019
        UsageDOE.TestCase = 19;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-019";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0019";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=19 4 5 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff020() {
        //Test Case 020
        UsageDOE.TestCase = 20;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-020";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0020";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=20 4 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff021() {
        //Test Case 021
        UsageDOE.TestCase = 21;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-021";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0021";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=21 4 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff022() {
        //Test Case 022
        UsageDOE.TestCase = 22;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-022";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0022";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=22 4 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff023() {
        //Test Case 023
        UsageDOE.TestCase = 23;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-023";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0023";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=23 4 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff024() {
        //Test Case 024
        UsageDOE.TestCase = 24;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-024";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0024";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=24 5 5 1 1 2 1 2 1 2";
    }
    public static void TestCaseDiff025() {
        //Test Case 025
        UsageDOE.TestCase = 25;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-025";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0025";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=25 5 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff026() {
        //Test Case 026
        UsageDOE.TestCase = 26;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-026";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0026";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=26 5 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff027() {
        //Test Case 027
        UsageDOE.TestCase = 27;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-027";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0027";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=27 5 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff028() {
        //Test Case 028
        UsageDOE.TestCase = 28;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-028";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0028";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=28 5 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff029() {
        //Test Case 029
        UsageDOE.TestCase = 29;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-029";
        ConfigFileName="DOESelfTestConfig\\DOEConfig0029";
        LevelFileName="DOELevelFile";
        TestCaseRow="Row=29 5 5 1 1 2 1 2 1 2";
    }
}
