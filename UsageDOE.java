/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import static DOEMatrixGen.MathsContxtLAv0_1_Prod.eatAll;
import static DOEMatrixGen.MatrixGenerator.ClassicTaguchiAlg;
import static DOEMatrixGen.MatrixGenerator.HasSignal;
import static DOEMatrixGen.MatrixGenerator.LEVELS;
import static DOEMatrixGen.MatrixGenerator.Length;
import static DOEMatrixGen.MatrixGenerator.Path;
import static DOEMatrixGen.MatrixGenerator.ROWSDOE;
import static DOEMatrixGen.MatrixGenerator.UseFullFactorial;
import static DOEMatrixGen.MatrixGenerator.UseLevelsFromArray;
import static DOEMatrixGen.MatrixGenerator.UseLevelsFromFile;
import static DOEMatrixGen.TestHarnessDOE.PassFailTestCase;
import java.util.HashMap;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UsageDOE {

    public static Logger log = Logger.getLogger(UsageDOE.class.getName());
     
     static int TestCase = -2;
    static int EndTestCase = -1;
    static int LastTestCaseHarness=0;
    static int TestCaseLoopTimes = 0;
    static String EqnRegressionDiffStatus = "All RED";
    static String EqnRegressionIntegreStatus = "All RED";
    static String TestCaseName = "Standard";
    static String TestCaseMatrixID = "Standard";
    
public static int FailingTestHarnessDiff(String AlgoName,int j) {
//Equation Component Testing
//1)	Variables :L=3:Single Letter, Multi Letter, with Numerics
//2)	Coefficient:L=9:0.0,0,1.0,1,-1.0,-1,5,-5,-5.0
//3)	Exponent:L=6: +ve,-ve,0,1,Independent Constants, Long Form (x * x*x)
//4)	Operator:L=2: *,/
//5)	Constants:L=3: Start of Expr, Middle (2-5) Terms, End Term
//6)	Independent Constants:L=3:Single Letter, Multi Letter, with Numerics
//7)	Expression
//8)	Term:L=3: Start Term, Middle (2-5) Terms, End Term
//9)	Term Sign:L=3: +ve, -ve, No Sign
//10)	Exponent Sign:L=3: +ve, -ve, No Sign
//11)	Coefficient Sign:L=3: +ve, -ve, No Sign
//12)	Variable Sign:L=3: +ve, -ve, No Sign
//13)	Parenthesis:L=3 : 1st Term, 2nd Term , 3-5 Terms
//14)	Functions:L5: Sin, Cos, Tan, Log, Ln
//15)	Argument of Functions:L=4: Expr Alone(3 types), Expr with Inner Function
        if((j >= -1)&&(j < 30))
            TestHarnessDOE.SanityTestCases(AlgoName,j);
        
        
        return j;
    }
    
    public static void mainRegresssionDOE(TestHarnessDOE TestData) {
        
        int j = TestCase;
        boolean TestCasePassStrFlag = false;
        boolean TestCaseHghLvlStatFlag = true;
        boolean TestCasePassHighLevelFlag = true;
        HashMap<Integer, String> HghLvlTestCases = new HashMap<Integer, String>();
        HashMap<Integer, String> FailedTestCases = new HashMap<Integer, String>();
        HashMap<Integer, String> ExceptionTestCases = new HashMap<Integer, String>();
        int MaxIndexHighLevel = 0;
        int MaxIndexFail = 0;
        int MaxIndexExcept = 0;
        
        int TotalTestCases = 0;
        
        String OverallRegressionStatus = "Diff:Pass All (5019 to 100001) Except Test Cases: "
                + "\n" + "TestCases:Unit Test Cases" + " All Passing"
                + "\n" + "TestCases:Names " + " All Passing"
                + "\n" + "TestCases:Coefficients " + " 20016 , 20017"
                + "\n" + "TestCases:Exponent " + " 30006 to 30008";
        System.out.print(ConsoleColors.BLUE + "Diff Overall Regression Status" + OverallRegressionStatus + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        if(EndTestCase > LastTestCaseHarness)
            EndTestCase=LastTestCaseHarness;
        while (j <= EndTestCase) {
            String TempExprn = "";

            TempExprn = ""+FailingTestHarnessDiff("mainDiffTest",j);
            if ((!TempExprn.equalsIgnoreCase("")) && (TempExprn != null) && (!TempExprn.equalsIgnoreCase(""))) {
                ;
            } else {
                j = RegressionControl(j);
                System.out.println(ConsoleColors.RED + "mainDiffTest: j=" + j + " TestCase=" + TestCase);
                continue;
            }
            System.out.println(ConsoleColors.YELLOW + "Test Case " + TestCase + " EndTestCase=" + EndTestCase + " j=" + j +  ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Test Case " + TestCase  + ConsoleColors.RESET);
            System.out.println(ConsoleColors.BLUE + "Regression Status : Test Case should be " + EqnRegressionDiffStatus + ConsoleColors.RESET);
            TotalTestCases++;
            TestCasePassHighLevelFlag=true;
            try {
                    TestCasePassStrFlag = false;
                    System.out.println(ConsoleColors.GREEN +"DOETestCase Row=:"+TestHarnessDOE.TestCaseRow + ConsoleColors.RESET);
                    
                    String[] args=null;
                    MatrixGenerator.pmain(args);
                    System.out.print(System.lineSeparator());
                    if (PassFailTestCase(TestCase) ) {
                        System.out.println(ConsoleColors.GREEN + " main:Test Case Status=" + "Pass STRING TEST Complete" + ConsoleColors.RESET);
                        TestCasePassStrFlag = true;
                    } else {
                        System.out.println(ConsoleColors.RED + " main:Test Case Status=" + "FAIL STRING TEST Complete" + ConsoleColors.RESET);
                        if(TestCasePassHighLevelFlag==true)
                            TestCasePassHighLevelFlag=false;
                    }

                    TestCaseHghLvlStatFlag=Analysis();
                    System.out.print(System.lineSeparator());

                    if ((TestCasePassStrFlag == true) )
                ;//Do Nothing : Test Case Passed
                    else {
                        String Temp = FailedTestCases.get(MaxIndexFail);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," +  ":LowLvL=" + TestCasePassStrFlag + ":HghLvl=" + (!TestCaseHghLvlStatFlag) ;
                        } else {
                            HashVal = "TestCase=" + TestCase + ":LowLvL=" + TestCasePassStrFlag + ":HghLvl=" + (!TestCaseHghLvlStatFlag) ;
                        }
                        FailedTestCases.put(MaxIndexFail, HashVal);
                        //i=MaxOrder1;
                    }
                    System.out.print(System.lineSeparator());
                    System.out.println(ConsoleColors.GREEN +"DOETestCase Row=:"+TestHarnessDOE.TestCaseRow + ConsoleColors.RESET);
                    System.out.println(ConsoleColors.GREEN +"System Set InputFile:"+MatrixGenerator.ConfigFileName + ConsoleColors.RESET);
                    HDOEDefine.Myprintf();
                    
                    if ((TestCaseHghLvlStatFlag == false) ){
                        String Temp = HghLvlTestCases.get(MaxIndexHighLevel);
                        String HashVal = "";
                        if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + "," +  ":LowLvL=" + TestCasePassStrFlag + ":HghLvL=" + (!TestCaseHghLvlStatFlag) ;
                        } else {
                            HashVal = "TestCase=" + TestCase +  ":LowLvL=" + TestCasePassStrFlag + ":HghLvL=" + (!TestCaseHghLvlStatFlag) ;
                        }
                        HghLvlTestCases.put(MaxIndexHighLevel, HashVal);
                        MaxIndexHighLevel++;
                        TestCaseHghLvlStatFlag=true;
                    }
                    System.out.print(System.lineSeparator());
            } catch (Exception MyException) {
                System.out.print(System.lineSeparator());
                System.out.print(System.lineSeparator());
                MyException.printStackTrace();
                System.out.println(ConsoleColors.RED + "TestCase " + TestCase + " due to Exception." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                String Temp = FailedTestCases.get(MaxIndexFail);
                String HashVal = "";
                if ((Temp != null) && (!Temp.equalsIgnoreCase(""))) {
                            HashVal = Temp + ","  + ":LowLvL=" + TestCasePassStrFlag ;
                } else {
                            HashVal = "TestCase=" + TestCase + ":LowLvL=" + TestCasePassStrFlag ;
                       }
                FailedTestCases.put(MaxIndexFail, HashVal);
                String Temp1 = "";
                if (MaxIndexExcept >=1)
                    Temp1 =ExceptionTestCases.get(MaxIndexExcept);
                String HashVal1 = "";
                if ((Temp1 != null) && (!Temp1.equalsIgnoreCase(""))) {
                            HashVal1 = Temp1 + ","  ;
                } else {
                            HashVal1 = "TestCase=" + TestCase  ;
                       }
                ExceptionTestCases.put(MaxIndexExcept, HashVal);
                MaxIndexExcept++;
                TestCasePassHighLevelFlag=false;
            }
            System.out.print(System.lineSeparator());

            if (TestCaseLoopTimes == 1) {
                break;
            } else {
                j = RegressionControl(j);
                //System.out.println(ConsoleColors.RED +"mainDiffTest: j="+ j + " TestCase=" + TestCase);
            }
            if (TestCasePassHighLevelFlag==false) MaxIndexFail++;
        }
        System.out.println(ConsoleColors.RED + "mainDiffTest: TestCase Summary (Low Level Business Rules): Total Failed=" + MaxIndexFail + " Out of Run TestCases=" + TotalTestCases  + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED +  " Failed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexFail; y++) {
            System.out.print(ConsoleColors.RED + " " + FailedTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y > 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.RED + "mainDiffTest: Exception TestCase Summary (Low Level Business Rules): Total Exceptions=" + MaxIndexExcept + " Exception Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexExcept; y++) {
            System.out.print(ConsoleColors.RED + " " + ExceptionTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y > 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println(ConsoleColors.GREEN + "mainDiffTest: TestCase Summary (High Level): Total Pass=" + MaxIndexHighLevel + " Out of Run TestCases=" + TotalTestCases  + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN + " Passed Test Cases are:" + ConsoleColors.RESET);
        for (int y = 0; y < MaxIndexHighLevel; y++) {
            System.out.print(ConsoleColors.GREEN + " " + HghLvlTestCases.get(y) + ", " + ConsoleColors.RESET);
            if ((y > 0) && (y % 5 == 0)) {
                System.out.print(System.lineSeparator());
            }
        }
        System.out.print(System.lineSeparator());
    }

    public static int RegressionControl(int j) {
        if ((j >= -1) && (j < 30)&&(j <=EndTestCase)) {
            j++;
        } 
        else {
            System.out.println(ConsoleColors.RED + "RegressionControl:j= " + j + ": Infinite Loop" + ConsoleColors.RESET);
        }
        return j;

    }
    public static boolean Analysis(){
        System.out.println(ConsoleColors.GREEN +"Analysis" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN +"Test Case Passes if ROWSDOE >= LEVELS && Length >= LEVELS" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN +"Test Case Passes if UseLevelsFromFile OR(XOR) UseLevelsFromArray is TRUE OR(XOR) LEVELS > 0:In Other words one has to be true and the other false" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN +"Test Case Passes if UseFullFactorial OR(XOR) ClassicTaguchiAlg is TRUE:In Other words one has to be true and the other false" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN +"Test Case Passes if HasSignal is TRUE" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.GREEN +"Test Case Passes if Path = data" + ConsoleColors.RESET);
        System.out.println(ConsoleColors.RED   +"All OTHER CONDITIONS OF Test Cases FAIL" + ConsoleColors.RESET);
        System.out.print(System.lineSeparator());
        boolean Fail=true;//Fail=true mean TestCase Fails; 
                          //Fail=false means Test Case Passes            
        if ((ROWSDOE >= LEVELS)
            &&(Length >= LEVELS)) {
            Fail=false;
        }
        if ((Fail==false)
                &&(((UseLevelsFromFile == true)&&(UseLevelsFromArray == false))
                    || ((UseLevelsFromFile == false)&&(UseLevelsFromArray == true))
                )
                ){
            Fail=false;
        }else if (((UseLevelsFromFile == false)&&(UseLevelsFromArray == false))
                    || ((UseLevelsFromFile == true)&&(UseLevelsFromArray == true))
                ){
            Fail=true;
        }
        if ((Fail==false)
                &&(((UseFullFactorial == true)&&(ClassicTaguchiAlg == false))
                || ((UseFullFactorial == false)&&(ClassicTaguchiAlg == true))
                )
                ){
            Fail=false;
        }else if (((UseFullFactorial == false)&&(ClassicTaguchiAlg == false))
                || ((UseFullFactorial == true)&&(ClassicTaguchiAlg == true))
                ){
            Fail=true;
        }
        if ((Fail==false)
                &&(HasSignal == true)
                ){
            Fail=false;
        }
        if ((Fail==false)
                &&(Path.equalsIgnoreCase("data\\"))
                ){
            Fail=false;
        }
        if (Fail==false){
            System.out.println(ConsoleColors.GREEN +"This Test Case ("+ TestCase +") Passes" + ConsoleColors.RESET);
        }
        else {
            System.out.println(ConsoleColors.RED +"This Test Case ("+ TestCase +") FAILS" + ConsoleColors.RESET);
        }
        return Fail;
    }
}
