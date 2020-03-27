/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import static DOEMatrixGen.MatrixGenerator.DataArrayGet;
import static DOEMatrixGen.MatrixGenerator.LevelCntrlArrayGet;
import static DOEMatrixGen.MatrixGenerator.LevelMaxIndex;
import static DOEMatrixGen.MatrixGenerator.StrengthMode;
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
    
    TestHarnessDOE( String All, String MyEndTestCase,String RegrORIndivORLimit) {
        
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

    public static boolean PassFailTestCase(int myTestCase) {
        boolean ReturnFlag1 = false;
        boolean ReturnFlag2 = false;
        boolean ReturnFlag3 = false;
        boolean ReturnFlag4 = false;
        boolean ReturnFlag5 = false;
        boolean ReturnFlag6 = false;
        boolean ReturnFlag7 = false;
        boolean ReturnFlag8 = false;
        
        System.out.println("Business Domain :Sanity Test Rules");
        if(PassFailRule1(myTestCase)==true)
                    ReturnFlag1=true;
        if(PassFailRule2(myTestCase)==true)
                    ReturnFlag2=true;
        //if(PassFailRule3(myTestCase)==true)
                    ReturnFlag3=true;
        if(PassFailRule4(myTestCase)==true)
                    ReturnFlag4=true;
        if(PassFailRule5(myTestCase)==true)
                    ReturnFlag5=true;
        if(PassFailRule6(myTestCase)==true)
                    ReturnFlag6=true;
        System.out.println("Business Domain :Process Test Rules");
        //if(PassFailRule7(myTestCase)==true)
                    ReturnFlag7=true;
        //if(PassFailRule8(myTestCase)==true)
                    ReturnFlag8=true;
        
        
        return ((ReturnFlag1)&&(ReturnFlag2)&&(ReturnFlag3)
                &&(ReturnFlag4)&&(ReturnFlag5)&&(ReturnFlag6)
                &&(ReturnFlag7)&&(ReturnFlag8) );
        
    }
    //Test Pass Fail Rules Functions
    //Sanity - PassFailRule1
    //LevelMinIndex <= CellValue <=LevelMaxIndex
    public static boolean PassFailRule1(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        boolean PrintOnceFlag = false;
        for (int i = 0; i < MatrixGenerator.ROWSDOE; i++) {
            for (int j = 0; j < MatrixGenerator.Length; j++) {
                int LiMin = MatrixGenerator.LevelCntrlArrayGet(0, j, MatrixGenerator.LevelMinIndex);
                int LiMax = MatrixGenerator.LevelCntrlArrayGet(0, j, MatrixGenerator.LevelMaxIndex);
                int CellValue = MatrixGenerator.DataArrayGet(0, i, j);
                if ((LiMin <= CellValue) && (CellValue <= LiMax)) {
                    ReturnFlagMap1 = true;
                } else if (PrintOnceFlag==false) {
                    System.out.println(ConsoleColors.RED + "PassFailRule1:Test Case=" + myTestCase + " Row=" + i + " Column=" + j + " LiMin=" + LiMin +  "Cell Value=" +CellValue + " LiMax=" + LiMax + ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                    PrintOnceFlag=true;
                }
            }
        }
        if (ReturnFlagMap1 == true) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule1" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule1" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
    }
    //Sanity - PassFailRule2
    //No Duplicates
    public static boolean PassFailRule2(int myTestCase) {
        boolean ReturnFlagMap1 = true;
        ReturnFlagMap1=MatrixGenerator.DupCheckMatrixMode(0);
        if (ReturnFlagMap1 == false) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule2" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + "PassFailRule2:Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule2" + ConsoleColors.RESET);
        }
        return ((!ReturnFlagMap1) );
    }
    //Sanity - PassFailRule1
    //Each Column Sum of Levels=100%
    public static boolean PassFailRule3(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        int FullFactorialCnt=0;
        for (int c=0,DataCount=0,FullFactIndivCol=0; c < MatrixGenerator.Length; c++,DataCount=0) {
                for ( int r = 0; r < MatrixGenerator.ROWSDOE; r++) {
                            DataCount += MatrixGenerator.DataArrayGet(0, r, c);
                    }
                if (c==0) FullFactorialCnt=DataCount;
                if (FullFactorialCnt==DataCount) 
                    ReturnFlagMap1=true;
                else 
                    ReturnFlagMap1=false;
            }
        if (ReturnFlagMap1 == true) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule3" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule3" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1) );
    } 
    //Sanity - PassFailRule4
    //Number of Cells in a Row = Length
    public static boolean PassFailRule4(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        int j = 0;
        for (int i = 0; i < MatrixGenerator.ROWSDOE; i++) {
            for (j = 0; j < MatrixGenerator.Length; j++) {
                int CellValue = MatrixGenerator.DataArrayGet(0, i, j);
                if (j < MatrixGenerator.Length) {
                    ReturnFlagMap1 = true;
                } else {
                    System.out.println(ConsoleColors.RED + "PassFailRule4:Test Case=" + myTestCase + " Row=" + i + " Column=" + j +  "Cell Value=" +CellValue  + ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                }
            }
        }
        if ((ReturnFlagMap1 == true) && (j == MatrixGenerator.Length)) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule4" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule4" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
    } 
    //Sanity - PassFailRule5
    //Number of Rows = ROWSDOE
    public static boolean PassFailRule5(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        int i = 0;
        for ( i = 0; i < MatrixGenerator.ROWSDOE; i++) {
            for (int j = 0; j < MatrixGenerator.Length; j++) {
                int CellValue = MatrixGenerator.DataArrayGet(0, i, j);
                if (i < MatrixGenerator.ROWSDOE) {
                    ReturnFlagMap1 = true;
                } else {
                    System.out.println(ConsoleColors.RED + "PassFailRule5: Test Case=" + myTestCase + " Row=" + i + " Column=" + j +  "Cell Value=" +CellValue + ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                }
            }
        }
        if ((ReturnFlagMap1 == true) && (i == MatrixGenerator.ROWSDOE)) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule5" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule5" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
    } 
    //Sanity - PassFailRule6
    //No Value < LevelMinIndex && CellValue !=0
    public static boolean PassFailRule6(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        boolean PrintOnceFlag = false;
        int i = 0;
        for ( i = 0; i < MatrixGenerator.ROWSDOE; i++) {
            for (int j = 0; j < MatrixGenerator.Length; j++) {
                int LiMin = MatrixGenerator.LevelCntrlArrayGet(0, j, MatrixGenerator.LevelMinIndex);
                int LiMax = MatrixGenerator.LevelCntrlArrayGet(0, j, MatrixGenerator.LevelMaxIndex);
                int CellValue = MatrixGenerator.DataArrayGet(0, i, j);
                if ((CellValue >= LiMin)&&(CellValue != 0) ) {
                    ReturnFlagMap1 = true;
                } else if (PrintOnceFlag==false) {
                    System.out.println(ConsoleColors.RED + "PassFailRule6:Test Case=" + myTestCase + " Row=" + i + " Column=" + j +  "Cell Value=" +CellValue + ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                    PrintOnceFlag=true;
                }
            }
        }
        if ((ReturnFlagMap1 == true) && (i == MatrixGenerator.ROWSDOE)) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule6" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule6" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
    } 
    //Business Domain Process - PassFailRule7
    // CellValue Follows SimpleModulo:(CellValue == (CellValueM1+1)) :(CellValue == (CellValueP1-1)
    public static boolean PassFailRule7(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        boolean PrintOnceFlag = false;
        for (int i = 1; i < MatrixGenerator.ROWSDOE; i++) {
            for (int j = 0; j < MatrixGenerator.Length; j++) {
                int CellValue = MatrixGenerator.DataArrayGet(0, i, j);
                int CellValueM1 = MatrixGenerator.DataArrayGet(0, i-1, j);
                int CellValueP1 = MatrixGenerator.DataArrayGet(0, i+1, j);
                if ( (CellValue == (CellValueM1+1)) && (CellValue == (CellValueP1-1)) ) {
                    ReturnFlagMap1 = true;
                } else if(PrintOnceFlag==false) {
                    System.out.println(ConsoleColors.RED + "PassFailRule7:Test Case=" + myTestCase + " Row=" + i + " Column=" + j  +  " Cell Value=" +CellValue + ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                    PrintOnceFlag=true;
                }
            }
        }
        if (ReturnFlagMap1 == true) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule7" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule7" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
    }
    //Business Domain Process- PassFailRule8
    // StrengthMode    
    public static boolean PassFailRule8(int myTestCase) {
        boolean ReturnFlagMap1 = false;
        boolean PrintOnceFlag = false;
        int r1 = 1;
        for (r1 = 1; r1 < (MatrixGenerator.LEVELS + 1); r1++) {
            for (int c = 0; c < MatrixGenerator.Length; c++) {
                int LiMax = MatrixGenerator.LevelCntrlArrayGet(0, c, MatrixGenerator.LevelMaxIndex);
                if(StrengthMode[c][r1] == 
                        (MatrixGenerator.ROWSDOE/LiMax) )
                    ReturnFlagMap1=true;
                 else if(PrintOnceFlag==false) {
                    System.out.println(ConsoleColors.RED + "PassFailRule8:Test Case=" + myTestCase + " Level=" + (r1) + " Column=" + c  +  " StrengthMode[c][r1]=" +StrengthMode[c][r1] + " Should be " + (MatrixGenerator.ROWSDOE/LiMax)+ ConsoleColors.RESET);
                    ReturnFlagMap1 = false;
                    PrintOnceFlag=true;
                }
            }
        }
        if (ReturnFlagMap1 == true) {
            System.out.println(ConsoleColors.GREEN + " Test Case=" + myTestCase + " Status=" + "Pass PassFailRule8" + ConsoleColors.RESET);
        } else {
            System.out.println(ConsoleColors.RED + " Test Case=" + myTestCase + " Status=" + "FAIL PassFailRule8" + ConsoleColors.RESET);
        }
        return ((ReturnFlagMap1));
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
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneralDefault";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="";
    }
    public static void TestCaseDiff000() {
        //Test Case 000
        UsageDOE.TestCase = 0;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-000";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral000";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=0 1 1 1 1 1 1 1 1 1";
    }
   
    public static void TestCaseDiff001() {
        //Test Case 001
        UsageDOE.TestCase = 1;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-001";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral001";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=1 1 2 2 2 2 2 2 2 2";
    }
    public static void TestCaseDiff002() {
        //Test Case 002
        UsageDOE.TestCase = 2;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-002";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral002";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=2 1 3 2 2 2 2 3 2 3";
    }
    public static void TestCaseDiff003() {
        //Test Case 003
        UsageDOE.TestCase = 3;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-003";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral003";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=3 1 4 2 2 2 2 4 2 2";
    }
    public static void TestCaseDiff004() {
        //Test Case 004
        UsageDOE.TestCase = 4;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-004";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral004";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=4 1 5 2 2 2 2 5 2 3";
    }
    public static void TestCaseDiff005() {
        //Test Case 005
        UsageDOE.TestCase = 5;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-005";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral005";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=5 1 1 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff006() {
        //Test Case 006
        UsageDOE.TestCase = 6;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-006";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral006";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=6 2 2 1 2 1 2 3 2 3";
    }
    public static void TestCaseDiff007() {
        //Test Case 007
        UsageDOE.TestCase = 7;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-007";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral007";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=7 2 3 2 1 2 1 4 1 1";
    }
    public static void TestCaseDiff008() {
        //Test Case 008
        UsageDOE.TestCase = 8;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-008";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral008";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=8 2 4 2 1 1 2 5 2 3";
    }
    public static void TestCaseDiff009() {
        //Test Case 009
        UsageDOE.TestCase = 9;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-009";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral009";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=9 2 5 1 2 1 1 1 1 1";
    }
    public static void TestCaseDiff010() {
        //Test Case 010
        UsageDOE.TestCase = 10;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-010";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral010";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=10 2 1 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff011() {
        //Test Case 011
        UsageDOE.TestCase = 11;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-011";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral011";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=11 2 2 1 2 1 2 3 2 3";
    }
    public static void TestCaseDiff012() {
        //Test Case 012
        UsageDOE.TestCase = 12;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-012";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral012";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=12 3 3 1 1 2 1 4 1 1";
    }
    public static void TestCaseDiff013() {
        //Test Case 013
        UsageDOE.TestCase = 13;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-013";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral013";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=13 3 4 1 2 1 2 5 2 3";
    }
    public static void TestCaseDiff014() {
        //Test Case 014
        UsageDOE.TestCase = 14;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-014";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral014";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=14 3 5 1 1 2 1 1 1 1";
    }
    public static void TestCaseDiff015() {
        //Test Case 015
        UsageDOE.TestCase = 15;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-015";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral015";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=15 3 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff016() {
        //Test Case 016
        UsageDOE.TestCase = 16;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-016";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral016";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=16 3 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff017() {
        //Test Case 017
        UsageDOE.TestCase = 17;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-017";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral017";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=17 3 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff018() {
        //Test Case 018
        UsageDOE.TestCase = 18;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-018";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral018";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=18 4 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff019() {
        //Test Case 019
        UsageDOE.TestCase = 19;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-019";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral019";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=19 4 5 2 1 2 1 2 1 2";
    }
    public static void TestCaseDiff020() {
        //Test Case 020
        UsageDOE.TestCase = 20;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-020";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral020";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=20 4 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff021() {
        //Test Case 021
        UsageDOE.TestCase = 21;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-021";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral021";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=21 4 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff022() {
        //Test Case 022
        UsageDOE.TestCase = 22;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-022";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral022";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=22 4 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff023() {
        //Test Case 023
        UsageDOE.TestCase = 23;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-023";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral023";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=23 4 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff024() {
        //Test Case 024
        UsageDOE.TestCase = 24;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-024";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral024";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=24 5 5 1 1 2 1 2 1 2";
    }
    public static void TestCaseDiff025() {
        //Test Case 025
        UsageDOE.TestCase = 25;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-025";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral025";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=25 5 1 2 1 1 2 3 2 3";
    }
    public static void TestCaseDiff026() {
        //Test Case 026
        UsageDOE.TestCase = 26;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-026";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral026";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=26 5 2 1 2 1 1 4 1 1";
    }
    public static void TestCaseDiff027() {
        //Test Case 027
        UsageDOE.TestCase = 27;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-027";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral027";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=27 5 3 1 1 2 1 5 2 3";
    }
    public static void TestCaseDiff028() {
        //Test Case 028
        UsageDOE.TestCase = 28;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-028";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral028";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=28 5 4 1 2 1 2 1 1 1";
    }
    public static void TestCaseDiff029() {
        //Test Case 029
        UsageDOE.TestCase = 29;
        UsageDOE.EqnRegressionDiffStatus = "All Green Including Values";
        UsageDOE.TestCaseName = "Standard:No Coefficients:Basic Equation";
        UsageDOE.TestCaseMatrixID="TestCase:Row-029";
        MatrixGenerator.ConfigFileName="DOESelfTestConfig\\DOEinputGeneral029";
        MatrixGenerator.LevelFileName="DOELevelFile";
        TestCaseRow="Row=29 5 5 1 1 2 1 2 1 2";
    }
}
