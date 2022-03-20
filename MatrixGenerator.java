/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import Calculus.ConsoleColors;
import Calculus.MathsContxtLAv;
import static DOEMatrixGen.DOEColumnInputLevel.ReadSection;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.DiffrIntegrSep.parseDiff;
import static Calculus.MathsContxtLAv.parseSimple;
import static DOEMatrixGen.UsageMatrixGen.FormEquation;
import static DOEMatrixGen.UsageDOE.mainRegresssionDOE;
import RandomNumber.RandomFactorialLCGDyDx;
import RandomNumber.RandomSimpleLCG;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.MaxExponent;
import static RandomNumber.UserResearchStudy.Modulus;
import static RandomNumber.UserResearchStudy.ModulusEncrypt;
import static RandomNumber.UserResearchStudy.NoOfSlots;
import static RandomNumber.UserResearchStudy.Xn_LCG;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx;
import static RandomNumber.UserResearchStudy.a;
import static RandomNumber.UserResearchStudy.b;
import static RandomNumber.UserResearchStudy.c;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class MatrixGenerator {

    public static Logger log = Logger.getLogger(MatrixGenerator.class.getName());
    // Global Variables
    public static double SYSTEM_BIGNUMBER = 1.0e50;
    public static double SYSTEM_Delta = 1.0e-28;
    public static double SYSTEM_BIGNUMBERnPr = 1.0e6;
    public static String Path;
    public static String ConfigFileName = "DOEinputConfigDefault";
    public static String LevelFileName = "DOEJavaCalculusLevels";
    public static int STATSMOREDATAFACTOR = 3;
    public static long ROWSDOE = 1;
    public static long ROWSDOEComputed = 0;
    public static long ROWSDOERecommended = 0;
    // Number of Levels of each Factor (Signal Columns) in Ortho Matrix
    public static int LEVELS = 5;
    public static long MaxLevel = 0;// Maximum Levels across all Columns
    public static long Length = 1000;// Length (Number of Signal Columns of Orthogonal Matrix)

    public static boolean Regression = true;
    public static boolean Interactive = true;
    public static boolean GenHadamardProduct = true;
    public static boolean GenDeterminant = true;
    //BlkFactor data Elements
    // HyperDOE Data Elements
    // High Level Variables for Signal Noise Nature
    public static boolean HasSignal = true;
    public static boolean UseLevelsFromFile = false;
    public static boolean UseLevelsFromArray = false;

    // Generation of DOE Boolean Control
    public static boolean ClassicTaguchiAlg = false;
    public static boolean UseFullFactorial = true;
    public static boolean PrintStackTraceFlag = true;

    //Signal Data Elements
    public static long OverallRowDupCount = 0;
    public static long OverallColDupCount = 0;
    public static long OverallUniqueRowCount = 0;
    public static long OverallUniqueColCount = 0;
    private static LinkedList<String> DupsRowList;
    private static LinkedList<String> DupColList;
    private static Map<String, Long> UniqueMap = new HashMap<String, Long>();
    private static Map<String, Long> UniqueMapCol = new HashMap<String, Long>();
    //Variable for Levels Control
    private static int levelCntrl = 1;
    //Variable for Levels Control
    private static int levelCntrlBack = 1;
    // Number of positions to interchange when we get a Duplicate
    public static int FixDepth = 20;
    // Number of times Dups have been detected
    public static int DupCount = 0;
    // Number of times Dups have been detected
    public static int ZeroCount = 0;
    public static long DupCheckColStart;
    public static long DupCheckColEnd;
    public static long DupCheckRowStart;
    // Boolean Type of Columns
    public static boolean HasNormal = true;
    //Data Array
    private static ByteBuffer DataArray;

    public static String[] MeanColModeTracker;//Length :Number of Datum
    public static int[] ColMode;//Length :Number of Datum:The Frequency of each  Value
    public static int[] ColModeValue;//Value :Individual Different Datum

    public static int[][] StrengthMode;
    public static int[] DataCountSum = null;
    public static int[][] ColFactorialMode;
    public static int[] DataCountSumActual = null;
    //LCG Variables
    private static long LCG_TempValue = 1;
    private static long LCG_Value = 1;
    private static long LCG_levelCntrl = 1;
    private static long LCG_FactValue = 1;
    private static long LCG_NPRFactValue = 1;
    private static long SimpleModuloLevelXn = 1;
    private static long Counter = 1;
    private static int BitPatternIndex=0;
    private static long BitPatColIndex=0;
    private static int BitPatRotateIndex=0;
    private static int BitPatIncrIndex=0;
    private static ByteBuffer LevelCntrlArray;

    //Storage Tweak
    public static int SIZEOFCELL = 4;

    public static double[][] LevelValueStore;

    public static int[] LevelValueInit;//-1=LevelValuePair Not Initialized

    public static String[] NameCol;//Name of the Column(Display)
    //InitLevels Column 0 Value Need not be continuous but has to be increasing 
    // i.e 0,1,2 ,15,30,390. 
    // Just input the columns that you need. Rest are set to LEVELS
    // OR User to implement UseLevelsFromLEVELS=true/false
//    private static int[][] InitLevels = {
//        {0, 1, 2/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Column, LevelMinIndex, LevelMaxIndex, LevelCtrlIndex, LastIssuedLevel, LastIssuedValue, FactorialRow1Lev, FactSpreadRow1Lev, FactRowRow1Lev, FactRowSpreadRow1Lev, FactorialRow3Lev, FactSpreadRow3Lev, FactRowRow3Lev, FactRowSpreadRow3Lev, FactorialNMinusLev, FactSpreadNMinusLev, FactRowNMinusLev, FactRowSpreadNMinusLev, 
//        {1, 1, 5/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {4, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {5, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {6, 1, 5/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {7, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
//        {8, 1, 3/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},};
    //Java Calculus Number of Equations Required for High Level Testing JavaCalculus
//    private static int[][] InitLevels = {
//        {0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Constant  Or Ind Constant Leading Sign
//        {1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- IndConstant OR Constant
//        {2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Constant PostFix Operator
//        {3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Variable Leading Sign
//        {4, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- Variable
//        {5, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent Operator ^ or No Operator
//        {6, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent Leading Sign
//        {7, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent or No Exponent
//        {8, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent PostFix Operator
//        {9, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Trailing Constant Leading Sign 
//        {10, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Trailing Constant- IndConstant OR Constant
//        {11, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Trailing Constant PostFix Operator
//        {12, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Leading Sign
//        {13, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression (Braces/Parentheses)
//        {14, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent or No Exponent
//        {15, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent Leading Sign
//        {16, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent
//        {17, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Term Operator :+ OR - or 1 Term only
//        {18, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// Function 
//        };
    //Java Calculus Number of Equations Required for Low Level Testing JavaCalculus
    private static int[][] InitLevels = {
        {0, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Constant  Or Ind Constant Leading Sign
        {1, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- IndConstant OR Constant
        {2, 1, 9, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- Constant
        {3, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- IndConstant 
        {4, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, // Constant PostFix Operator
        {5, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Variable Leading Sign
        {6, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Names- Variable
        {7, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent Operator ^ or No Operator
        {8, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent Leading Sign
        {9, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent or No Exponent
        {10, 1, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent 
        {11, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Exponent PostFix Operator
        {12, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Trailing Constant Leading Sign 
        {13, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Trailing Constant- IndConstant OR Constant
        {14, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Trailing Constant PostFix Operator
        {15, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Leading Sign
        {16, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression (Braces/Parentheses)
        {17, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent or No Exponent
        {18, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent Leading Sign
        {19, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Expression Exponent
        {20, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Term Operator :+ OR - or 1 Term only
        {21, 1, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Term Leading Sign
        {22, 1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},//Number of Terms
        {23, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},// Function 
    };
    public static int LevelMinIndex = 0;
    public static int LevelMaxIndex = 1;
    public static int LevelCtrlIndex = 2;
    public static int LastIssuedLevel = 3;
    public static int LastIssuedValue = 4;

    public static int FactorialRow1Lev = 5;
    public static int FactSpreadRow1Lev = 6;
    public static int FactRowRow1Lev = 7;

    public static int FactorialRow3Lev = 8;
    public static int FactSpreadRow3Lev = 9;
    public static int FactRowRow3Lev = 10;

    public static int FactorialNMinusLev = 11;
    public static int FactSpreadNMinusLev = 12;
    public static int FactRowNMinusLev = 13;

    //Statistical Data Variables (Super Variables)
    private static long rowdimension;
    private static long coldimension;

    public double[][] MeanTracker;
    public double[][] VarianceTracker;
    public double[][] MeanOutputTracker;
    public double[][] VarianceOutputTracker;
    public String[] MeanOutputModeTracker;//Length :Number of Datum

    public static Long DOEBestBitsStr = 0L;
    public static double DOEBestOutput = 0;
    private double SmallBetter = 0.0;
    private double NominalBest = 0.0;
    private double LargerBetter = 0.0;
    private double DataMinInp = 0.0;
    private double DataMaxInp = 0.0;
    private double ErrorDiffInp = 0.0;
    private double DataDiffInp = 0.0;
    private double DataDiff = 0.0;
    private int sizeyi = 0;
    private int Outputsizeyi = 0;
    private double DataMinOut = 0.0;
    private double DataMaxOut = 0.0;
    private double DataDiffOut = 0.0;
    private double ErrorDiffOut = 0.0;
    public double MeanWithinGroupsInput;
    public double MeanWithinGroupsOutput;

    //Value Tracker Data Structs
    private IntBuffer ValueTrkIndex;
    private IntBuffer RowValueTrkIndex;

    // Random Generator Values & Variables
    private static Map<String, Double> variables = new HashMap<>();
    //    private static String a="1.0";
//    private static String c="1.0";
//    private static String RandVar="Xn";
//    private static String DiffWithRespTo = "Xn";
//    //private static String Exprn = RandomEquation( a, c,  RandVar); 
//    private static String Exprn = FormEquation( a, c,  RandVar,7); 
    
    // Random Number Structures
    public static RandomSimpleLCG MySimple = null;
    public static RandomFactorialLCGDyDx MyFactorial = null;
    
    public static void GenerateDOELatin(int planeNum, long rows, long col) {
        try {
            long MyStartROWSDOE = ROWSDOE;
            long MyStartCOLMDOE = Length;
            long LastZ = MyStartCOLMDOE - 1;

            DupCheckColStart = 0;
            DupCheckColEnd = MyStartCOLMDOE;
            long StartRowOrg = 0;
            long StartColOrg = DupCheckColStart;

            long DupCheckColStartOrg = DupCheckColStart;//DupCheckColStart;
            long DupCheckColEndOrg = DupCheckColEnd;//DupCheckColEnd;

            if ((UseFullFactorial == false)) {
                log.error("GenerateDOELatin:Signal Dimensions Being Processed:");
                if (HasSignal == true) {
                    GenerateArray(planeNum);
                }
            } else if ((UseFullFactorial == true)) {
                if (HasSignal == true) {
                    GenerateArrayFF(planeNum);
                }
            }

            long i = 0;

            DupCheckColStart = 2;
            DupCheckColEnd = MyStartCOLMDOE;
            log.fatal("Starting Duplicate Check:OverallRowDupCount=" + OverallRowDupCount + " DupCheckColStart=" + DupCheckColStart + " DupCheckColEnd=" + DupCheckColEnd);
            DupCount = 0;
            long OverallDupCountOrg = OverallRowDupCount;
            if ((HasSignal == true) && (HasNormal == true)) {
                log.fatal("GenerateDOELatin:Started Signal DupCheckMatrixMode");
                DupRowCheckMatrixMode(planeNum, (int) Length);
                log.fatal("GenerateDOELatin:Started Signal DupAliasCheckMatrixMode");
                DupColCheckMatrixMode(planeNum, (int) Length);
            }
            System.out.print(System.lineSeparator());

            long OverallDupCountDiff = OverallRowDupCount - OverallDupCountOrg;
            System.out.println("Overall Row Duplicate & Aliases Count=" + OverallDupCountDiff + " OverallUniqueRowCount=" + OverallUniqueRowCount);
            System.out.println("Overall Col Duplicate & Aliases Count=" + OverallColDupCount + " OverallUniqueColCount=" + OverallUniqueColCount);
            AlgoParamLogger("Main", StartRowOrg, StartColOrg, DupCheckColStart, DupCheckColEnd, OverallDupCountOrg, MyStartROWSDOE, MyStartCOLMDOE, OverallRowDupCount);

        } catch (Exception HyperE) {
            log.info("Exception: GenerateDOELatin");
            System.out.println("Exception: GenerateDOELatin");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
    }

    /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ************************DOEMatrixArray Core Cell Generating Functions Start Here* */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
    private static void AlgoParamLogger(String AlgoName, long StartRowOrg, long StartColOrg, long DupCheckColStartOrg, long DupCheckColEndOrg, long OverallDupCountOrg, long StartRowEnd, long StartColEnd, long OverallDupCountEnd) {
        log.error("AlgoParamLogger:Logging Parameters for AlgoName=" + AlgoName);
        log.error("AlgoName=" + AlgoName + " StartRowOrg=" + StartRowOrg);
        log.error("AlgoName=" + AlgoName + " StartRowEnd=" + StartRowEnd);
        log.error("AlgoName=" + AlgoName + " StartColOrg=" + StartColOrg);
        log.error("AlgoName=" + AlgoName + " StartColEnd=" + StartColEnd);
        log.error("AlgoName=" + AlgoName + " DupCheckColStartOrg=" + DupCheckColStartOrg);
        log.error("AlgoName=" + AlgoName + " DupCheckColEndOrg=" + DupCheckColEndOrg);
        log.error("AlgoName=" + AlgoName + " OverallDupCountOrg=" + OverallDupCountOrg);
        log.error("AlgoName=" + AlgoName + " OverallDupCountEnd=" + OverallDupCountEnd);
        log.error("AlgoName=" + AlgoName + " Dup Diff Count=" + (OverallDupCountEnd - OverallDupCountOrg));
        log.error("AlgoName=" + AlgoName + " Row Count=" + (StartRowEnd - StartRowOrg));
        if (StartColEnd > StartColOrg) {
            log.error("AlgoName=" + AlgoName + " Col Count=" + (StartColEnd - StartColOrg));
        } else {
            log.error("AlgoName=" + AlgoName + " Col Count=" + (StartColOrg - StartColEnd));
        }

    }

    /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ************************Signal Core Cell Generating Functions Start Here* */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
    public static void GenerateArray(int planeNum) {
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;

            double levelCntrl = 1;
            if (HasNormal == true) {
                long Levels = MaxLevel;
                if (Checker(planeNum, false, HasNormal, (int) Length) == true)
                    ;// Do nothing
                else {
                    log.error("GenerateArray:Signal:NormalChecker Failed:Should get an Exception");
                }

                if (NumROWSDOE >= 1) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 0, LevelMinIndex);
                    ColumnZero(planeNum, 0, 0, LevelCntrlArrayGet(planeNum, 0, LevelMaxIndex));
                }

                if (NumROWSDOE >= 2) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 1, LevelMinIndex);
                    ColumnOne(planeNum, 0, 1, LevelCntrlArrayGet(planeNum, 1, LevelMaxIndex));
                }

                // Simple Row-wise Filler
                levelCntrl = LevelCntrlArrayGet(planeNum, 2, LevelMinIndex);
                RowLevelupto1Levels(planeNum, 0, 2, Levels);
                log.error("GenerateArray:Signal: RowLevelupto1Levels Completed ");
                System.out.println("RowLevelupto1Levels Completed");
                //printDataOutput(0);
                levelCntrl = LevelCntrlArrayGet(planeNum, 2, LevelMinIndex);
                RowLevel2Levels(planeNum, Levels, 2);
                log.error("GenerateArray:Signal: RowLevel2Levels Completed ");
                System.out.println("RowLevel2Levels Completed");
                //printDataOutput(0);

                if (ClassicTaguchiAlg == true) {
                    ClassicTaguchiRowLevel3Levels(planeNum);
                } else {
                    if (((NumROWSDOE >= 3) && (NumROWSDOE >= 10 * Levels))) {
                        RowLevel3Levels(planeNum, 2 * Levels, 2, 0);;
                    } else if ((NumROWSDOE >= 3) && (NumROWSDOE <= 10 * Levels) && (NumROWSDOE > Levels)) {
                        RowLevel3Levels(planeNum, 2 * Levels, 2, 0);;
                    } else {
                        log.error("GenerateArray:Signal: Skipping RowLevel3Levels");
                    }
                    log.error("GenerateArray:Signal: RowLevel3Levels Completed ");
                    System.out.println("RowLevel3Levels Completed");
                    //printDataOutput(0);
                }
            }
            log.info(System.lineSeparator());
        } catch (Exception HyperE) {
            log.info("Exception: GenerateArray:Signal:");
            System.out.println("Exception: GenerateArray:Signal:");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
    }

    public static void GenerateArrayFF(int planeNum) {
        try {
            log.error("GenerateArrayFF:Signal:Entered");
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long RootN=0;
            long LogN=0;
            if ((ROWSDOE > 100)&&(Length > 100) ){
                if (ROWSDOE > Length)
                        RootN=(long)Math.sqrt(Length);
                else if (ROWSDOE <=Length)
                        RootN=(long)Math.sqrt(ROWSDOE);
            }
            if ((ROWSDOE > 100)&&(Length > 100) ){
                if (ROWSDOE > Length)
                        LogN=(long)(Math.round( Math.log10(Length)/Math.log10(LEVELS)) );
                else if (ROWSDOE <=Length)
                        LogN=(long)(Math.round( Math.log10(ROWSDOE)/Math.log10(LEVELS)) );
            }
            System.out.println("GenerateArrayFF:RootN=" + RootN + " LogN=" + LogN);
            double levelCntrl = 1;
            if (HasNormal == true) {
                long Levels = MaxLevel;
                if (Checker(planeNum, false, HasNormal, (int) Length) == true)
                    ;// Do nothing
                else {
                    log.error("GenerateArrayFF:Signal:NormalChecker Failed:Should get an Exception");
                }
                 System.out.print(System.lineSeparator());
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " Generating Matrix : ");
                }
                if ((NumROWSDOE >= 1)&&(LogN==0)) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 0, LevelMinIndex);
                    ColumnZero(planeNum, 0, 0, LevelCntrlArrayGet(planeNum, 0, LevelMaxIndex));
                    System.out.println("ColumnZero Completed");
                }else if ((NumROWSDOE >= 1)&&(LogN > 0)) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 0, LevelMinIndex);
                    ColumnZeroLogN(planeNum, 0, 0, LogN,LevelCntrlArrayGet(planeNum, 0, LevelMaxIndex));
                    System.out.println("ColumnZeroLogN Completed");
                }
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " ColumnZeroLogN Completed : " );
                }
                if ((NumROWSDOE >= 2)&&(RootN==0)&&(LogN==0)) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 1, LevelMinIndex);
                    ColumnOne(planeNum, 0, 1, LevelCntrlArrayGet(planeNum, 1, LevelMaxIndex));
                    System.out.println("ColumnOne Completed");
                }else if ((NumROWSDOE >= 2)&&(RootN > 0)&&(LogN >0)) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 1, LevelMinIndex);
                    ColumnRootNPlusOne(planeNum, Levels, LogN, RootN,LogN,LevelCntrlArrayGet(planeNum, 1, LevelMaxIndex));
                    System.out.println("ColumnRootNPlusOne Completed");
                }
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " ColumnRootNPlusOne Completed : " );
                }
                //printDataOutput(0);
                levelCntrl = LevelCntrlArrayGet(planeNum, 2, LevelMinIndex);
                long scale=0;
                if ((RootN==0)&&(LogN==0)) {
                     scale=RowLevelupto1Levels(planeNum, 0, 2, LevelCntrlArrayGet(planeNum, 2, LevelMaxIndex));
                     System.out.println("RowLevelupto1Levels Completed");
                }
                else {
                    scale=RowLevelupto1Levels(planeNum, 0, 1, LevelCntrlArrayGet(planeNum, 1, LevelMaxIndex));
                    System.out.println("RowLevelupto1Levels Completed");
                }
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " RowLevelupto1Levels Completed : ");
                }
                log.fatal("GenerateArrayFF:Signal: RowLevelupto1Levels Completed ");
                ColumnFullFactorial(planeNum, 0, NumCOLMDOE, RootN,LogN,LevelCntrlArrayGet(planeNum, 2, LevelMaxIndex));
                System.out.println("ColumnFullFactorial Completed");
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " ColumnFullFactorial Completed : " );
                }
                //printDataOutput(0);
            }
            log.fatal("GenerateArrayFF:Exited");
        } catch (Exception HyperE) {
            log.info("Exception: GenerateArrayFF:Signal:");
            System.out.println("Exception: GenerateArrayFF:Signal:");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
    }

    /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ************************Signal Cell Generating Functions Start Here ***** */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
    public static void ColumnZero(int planeNum, long row, long col, long Levels) {

        log.error("Processing ColumnZero ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long LevelSpread = 0;
            if (Levels > 0) {
                LevelSpread = NumROWSDOE / Levels;
            }

            for (;
                    (((getArrayDataArray().limit() != 0))
                    && (row < NumROWSDOE));
                    row++) {
                //log.info("ColumnZero col=" + col + " row=" + row);
                ColZeroMain(planeNum, row, col, Levels, LevelSpread);
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnZero");
            System.out.println("Exception: ColumnZero");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed ColumnZero ");
    }
  public static void ColumnOne(int planeNum, long row, long col, long Levels) {
        log.error("Processing ColumnOne ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;

            for (;
                    (row < NumROWSDOE)
                    ;
                    row++) {

                //log.info("ColumnOne col=" + col + " row=" + row);
                ColumnOneMain(planeNum, row, col, LevelCntrlArrayGet(planeNum, col, LevelMaxIndex));
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnOne");
            System.out.println("Exception: ColumnOne");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed ColumnOne ");
    }
  public static void ColumnZeroLogN(int planeNum, long row, long col, long LogN,long Levels) {

        log.error("Processing ColumnZero ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long LevelSpread = 0;
            if (Levels > 0) {
                LevelSpread = NumROWSDOE / Levels;
            }
            // 2 Zero Columns get added :Have not Debugged Lift & Shift Properly
            LogN+=2;
            for (; ((col < (LogN))); col++) {
                for (row=Levels;
                        (((getArrayDataArray().limit() != 0))
                        && (row < NumROWSDOE));
                        row++) {
                    //log.info("ColumnZero col=" + col + " row=" + row);
                    row = ColZeroLogNMain(planeNum, row, col, LogN, Levels, LevelSpread);
                }
                
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnZeroLogN");
            System.out.println("Exception: ColumnZeroLogN");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed ColumnZeroLogN ");
    }
    public static void ColumnRootNPlusOne(int planeNum, long row, long col, long RootN, long LogN,long Levels) {
        log.error("Processing ColumnRootNPlusOne ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = RootN+LogN;
            
            BitPatColIndex=0;
            for (col=LogN; ((col < NumCOLMDOE));   col++) {
                    for (row=Levels;(row < NumROWSDOE);row++) {
                    //log.info("ColumnOne col=" + col + " row=" + row);
                    row=ColumnRootNPlusOneMain(planeNum, row, col, RootN,LogN, LevelCntrlArrayGet(planeNum, col, LevelMaxIndex));
                }
                    BitPatColIndex++;
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnRootNPlusOne");
            System.out.println("Exception: ColumnRootNPlusOne");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed ColumnRootNPlusOne ");
    }

    public static long RowLevelupto1Levels(int planeNum, long row, long col, long Levels) {
    long scale = 1;
        log.error("Processing RowLevelupto1Levels ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            
            if ((Levels==2)&&(ROWSDOE >100)&&(Length >100))
                scale = 4;
            long LastZ = NumCOLMDOE - 1;
            for (int levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex); 
                    row < (Levels); row++) {
                for (int j = (int) col; (j < NumCOLMDOE); j++) {
                    //log.info("RowLevelupto1Levels  row=" + row + " col=" + j + " levelCntrl=" + levelCntrl);
                    RowLevelupto1LevelsRecursiveMain(planeNum, row, j);
                }
            }
        } catch (Exception HyperE) {
            log.info("Exception: RowLevelupto1Levels");
            System.out.println("Exception: RowLevelupto1Levels");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed RowLevelupto1Levels ");
        return scale;
    }

    public static void RowLevel2Levels(int planeNum, long Levels, long col) {
        log.error("Processing RowLevel2Levels ");
        long NumROWSDOE = ROWSDOE;
        long NumCOLMDOE = Length;
        long row = Levels;
        long StartRowOrg = Levels;
        long StartColOrg = col;
        long OverallDupCountOrg = OverallRowDupCount;
        long DupCheckColStartOrg = col;//DupCheckColStart;
        long DupCheckColEndOrg = NumCOLMDOE - Levels + 1;//DupCheckColEnd;

        long LastZ = NumCOLMDOE;
        long RowNum = NumROWSDOE;
        long ColNum = NumCOLMDOE - Levels + 1;
        long TempValue = 0;
        if (NumROWSDOE > (10 * Levels)) {
            RowNum = (10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels)) && (NumROWSDOE > Levels)) {
            RowNum = 2 * Levels;
        } else {
            RowNum = NumROWSDOE;
        }
        ComputeFactorial(planeNum, "RowLevel2Levels",
                col,
                MaxLevel,
                (NumCOLMDOE),
                RowNum,
                ColNum,
                FactorialRow1Lev,
                FactSpreadRow1Lev,
                FactRowRow1Lev
        );
        for (; (row < RowNum); row++) {
            for (int j = (int) col; (j < NumCOLMDOE); j++) {
                log.info("RowLevel2Levels  row=" + row + " col=" + j
                        + " FactorialRow1Lev Value=" + LevelCntrlArrayGet(planeNum, j, FactorialRow1Lev)
                        + " FactSpreadRow1Lev Value=" + LevelCntrlArrayGet(planeNum, j, FactSpreadRow1Lev)
                        + " FactRowRow1Lev Value=" + LevelCntrlArrayGet(planeNum, j, FactRowRow1Lev));
                TempValue = ComplexCommonRCMain(planeNum, "RowLevel1Levels",
                        /* row*/ (long) row,
                        /* col*/ (long) j,
                        /* Levels*/ (long) LevelCntrlArrayGet(planeNum, j, LevelMaxIndex),
                        /*Value*/ (long) LevelCntrlArrayGet(planeNum, j, LastIssuedValue),
                        /* RowSub*/ 1,
                        /*ColSub*/ (long) 1,
                        /*AlgoRowFactIndex*/ FactorialRow1Lev,
                        /* AlgoRowSpreadIndex*/ FactSpreadRow1Lev,
                        /*AlgoFactRowFactIndex*/ FactRowRow1Lev);
                LevelCntrlArrayPut(planeNum, j, LastIssuedValue, TempValue);
            }
            for (int x = (int) col; (x < NumCOLMDOE - Levels + 1); x++) {
                long TempValue1 = LevelCntrlArrayGet(planeNum, x, FactRowRow1Lev);
                LevelCntrlArrayPut(planeNum, x,
                        FactRowRow1Lev,
                        (TempValue1 - 1));
            }
            if (row < RowNum) {
                //FindnFixDuplicate(planeNum, "RowLevel1Levels", row, DupCheckColStartOrg, DupCheckColEndOrg, NumCOLMDOE, LastZ);
                //DupCheckColMode( planeNum);
            }
        }
        AlgoParamLogger("RowLevel2Levels", StartRowOrg, StartColOrg, DupCheckColStartOrg, DupCheckColEndOrg, OverallDupCountOrg, row, (NumCOLMDOE - Levels + 1), OverallRowDupCount);
        log.error("Completed RowLevel2Levels ");
    }

    public static void RowLevel3Levels(int planeNum, long row, long col, long Value) {
        log.error("Processing RowLevel3Levels ");
        long NumROWSDOE = ROWSDOE;
        long NumCOLMDOE = Length;
        long Levels = MaxLevel;

        long LastZ = NumCOLMDOE;
        long StartRowOrg = row;
        long StartColOrg = col;
        long OverallDupCountOrg = OverallRowDupCount;
        long DupCheckColStartOrg = col;//DupCheckColStart;
        long DupCheckColEndOrg = NumCOLMDOE - Levels + 1;//DupCheckColEnd;

        long RowNum = row;
        long ColNum = Levels;

        if (NumROWSDOE > (10 * Levels)) {
            RowNum = NumROWSDOE;//(10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels)) && (NumROWSDOE > Levels)) {
            RowNum = NumROWSDOE;
        } else {
            RowNum = NumROWSDOE;
        }

        ComputeFactorial(planeNum, "RowLevel3Levels", col, Levels, (NumCOLMDOE - Levels + 1), RowNum, ColNum,
                FactorialRow3Lev,
                FactSpreadRow3Lev,
                FactRowRow3Lev);

        for (; row < RowNum; row++) {
            for (int j = (int) col; j < NumCOLMDOE; j++) {
//                log.error("RowLevel3Levels  row=" + row + " col=" + j 
//                        + " FactorialRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactorialRow3Lev) 
//                        + " FactSpreadRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactSpreadRow3Lev) 
//                        + " FactRowRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactRowRow3Lev) );
                long TempValue = ComplexCommonRCMain(planeNum, "RowLevel3Levels",
                        /* row*/ (long) row,
                        /* col*/ (long) j,
                        /* Levels*/ (long) LevelCntrlArrayGet(planeNum, j, LevelMaxIndex),
                        /*Value*/ (long) LevelCntrlArrayGet(planeNum, j, LastIssuedValue),
                        /* RowSub*/ 1,
                        /*ColSub*/ (long) 1,
                        /*AlgoRowFactIndex*/ (long) FactorialRow3Lev,
                        /* AlgoRowSpreadIndex*/ (long) FactSpreadRow3Lev,
                        /*AlgoFactRowFactIndex*/ (long) FactRowRow3Lev
                );
                log.info("RowLevel3Levels:First For Loop:  row=" + row + " col=" + j + " TempValue=" + TempValue);
                LevelCntrlArrayPut(planeNum, j, LastIssuedValue, TempValue);
                if (row < RowNum) {
                    //FindnFixDuplicate(planeNum, "RowLevel3Levels", row, DupCheckColStartOrg, DupCheckColEndOrg, NumCOLMDOE, LastZ);
                    //DupCheckColMode( planeNum);
                }
            }
        }
        AlgoParamLogger("RowLevel3Levels", StartRowOrg, StartColOrg, DupCheckColStartOrg, DupCheckColEndOrg, OverallDupCountOrg, row, (NumCOLMDOE - Levels + 1), OverallRowDupCount);
        log.error("Completed RowLevel3Levels ");
    }

    public static void ColumnFullFactorial(int planeNum, long scale, long col,long RootN, long LogN,long Levels) {
        log.error("Processing ColumnFullFactorial ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long LevelSpread = 0;
            if (Levels > 0) {
                LevelSpread = NumROWSDOE / Levels;
            }
            long FullFactorialROWS = 1;
            int j = (int)2;
            if ((RootN >0)&&(LogN >0))
                 j = (int)(RootN+LogN);
            for (; (j < NumCOLMDOE); j++) {
                for (int i = (int) (Levels); (i < NumROWSDOE); i++) {
                    FullFactorialROWS = (long) Math.pow((1.0 * Levels), (1.0 * j));
                    ColumnFullFactorialRecursiveMain(planeNum, i, j,
                            LevelCntrlArrayGet(planeNum, j,
                                    LevelMaxIndex), NumCOLMDOE, LevelSpread,RootN,LogN);
                }
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnFullFactorial");
            System.out.println("Exception: ColumnFullFactorial");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.error("Completed ColumnFullFactorial ");
    }

    public static void ClassicTaguchiRowLevel3Levels(int planeNum) {
        log.error("Processing ClassicTaguchiRowLevel3Levels ");
        long NumROWSDOE = ROWSDOE;
        long NumCOLMDOE = Length;
        long Levels = MaxLevel;//LevelCntrlArrayGet(planeNum, 3, LevelMaxIndex);
        long row = 0;
        if (NumROWSDOE > (10 * Levels)) {
            row = 10 * Levels;//(10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels)) && (NumROWSDOE > Levels)) {
            row = Levels;
        } else {
            row = Levels;
        }

        int col = 3;
        long Value = 0;
        for (; row < NumROWSDOE; row++) {
            for (col = 2; col < NumCOLMDOE; col++) {
                Levels = LevelCntrlArrayGet(planeNum, col, LevelMaxIndex);
                // Caution:Positional "If then Else" Blocks
                // Results will be different if position is interchanged
                if ((((row - Levels + 1) % (Levels + 1)) != 0) && ((col - 3 + 1) % (Levels + 1)) != 0) {
                    Value = ((row - Levels + 1) % (Levels + 1)) + ((col - 3 + 1) % (Levels + 1));
                } else if (((row - Levels + 1) % (Levels + 1)) != 0) {
                    Value = ((col - 3 + 1) % (Levels + 1));
                } else if (((col - 3 + 1) % (Levels + 1)) != 0) {
                    Value = ((row - Levels) % (Levels + 1));
                } else {
                    Value = levelCntrl;
                }
                if (Value >= Levels) {
                    Value = Value % (Levels + 1);
                }
                if (Value == 0) {
                    Value = 1;
                }
                DataArrayPut(planeNum, row, col, (byte) Value);
            }
            if (levelCntrl >= Levels) {
                levelCntrl = 1;
            } else {
                levelCntrl++;
            }
            if (levelCntrlBack >= Levels) {
                levelCntrlBack = 2;
            } else {
                levelCntrl++;
            }
            log.info("Completed ClassicTaguchiRowLevel3Levels ");
        }
    }

    public static void ComputeFactorial(int planeNum, String AlgoName,
            long col,
            /*LEVELS*/ long N,
            long R,
            long RowNum,
            long ColNum,
            int FuncFactIndex,
            int SpreadIndex,
            int FuncRowFactIndex) {
        //log.info("ComputeFactorial:" + AlgoName + " RowNum=" + RowNum + " ColNum=" + ColNum);
        double Factorial = Fact((double) N, 1.0);
        long RowFactorial = nPr(R, ColNum, 1);
        for (long i = col; i <= (col + R); i++) {
            //if (AlgoName.equalsIgnoreCase("RowLevel3Levels")) 
            //log.info("ComputeFactorial:" + AlgoName + " col=" + i + " Factorial=" + Factorial);
            LevelCntrlArrayPut(planeNum, i, FuncFactIndex, (byte) Factorial);
            double SpreadLevel = 1;
            if (ColNum > 0) {
                SpreadLevel = Factorial / ColNum;
            }
            //if (AlgoName.equalsIgnoreCase("RowLevel3Levels")) 
            //log.info("ComputeFactorial:" + AlgoName + " col=" + i + " SpreadLevel=" + SpreadLevel );
            LevelCntrlArrayPut(planeNum, i, SpreadIndex, (byte) SpreadLevel);
            //if (AlgoName.equalsIgnoreCase("RowLevel3Levels")) 
            //log.info("ComputeFactorial:" + AlgoName + " col=" + i + " RowFactorial=" + RowFactorial);
            LevelCntrlArrayPut(planeNum, i, FuncRowFactIndex, (byte) RowFactorial);
        }
    }

    public static double Fact(Double n, Double Result) {
        double TempResult = 1;
        if (n > 1) {
            if (Result > SYSTEM_BIGNUMBER) {
                log.info("Fact:Result=" + Result);
                return (Double) SYSTEM_BIGNUMBER;
            } else {
                TempResult = (double) (Result * n);
                log.info("Fact:Result=" + Result);
                if (((TempResult > SYSTEM_BIGNUMBER) && (Result < TempResult))
                        || (TempResult < 0) && (Result > TempResult)) {
                    return (Double) Result;
                } else {
                    return Fact(n - 1, (Double) TempResult);
                }
            }
        } else {
            return Result;
        }
    }

    public static long nPr(long n, long r, long Result) {
        long TempResult = 1;
        for (int i = 1; (i <= r) && (n > 0) && (TempResult < SYSTEM_BIGNUMBERnPr) && (TempResult > 0); i++, n--) {
            TempResult = Result * n;
            if (TempResult > 0) {
                Result = TempResult;
            }
            log.info("nPr:Result=" + Result + " n=" + n + " r=" + r + " i=" + i);
        }
        return Result;
    }

    public static long nCr(long n, long r, long Result) {
        long TempResult = 1;
        int i = 1;
        TempResult = Result * nPr(n, r, Result) / (long) Fact((double) r, 1.0);
        if (TempResult > 0) {
            Result = TempResult;
        }
        log.info("nCr:Result=" + Result + " n=" + n + " r=" + r + " i=" + i);
        return Result;
    }

    //Normal Input Code
    // Checker Function to Validate class Normal
    public static boolean Checker(int planeNum, boolean UseFlag, boolean HasFlag, Integer HasColumns) {
        if ((HasFlag == true) && (HasColumns > 0)) {
            //if (HyperCubeArray[planeNum].SignalObj.ColNormalArray!=null) {
            {
                if (DataArray != null) {
                    {
                        if (LevelCntrlArray != null) {
                            return true;
                        } else {
                            log.error("NormalChecker:Failed Making Object Null:LevelCntrlArray");
                        }
                    }
                } else {
                    log.error("NormalChecker:Failed Making Object Null:LevelCntrlArray");
                }
            }
            //}
            //else log.error("NormalChecker:Failed Making Object Null:ColNormalArray");
        } else {
            log.error("NormalChecker:Failed Making Object Null:HasNormal:HasColumns");
        }
        //HyperCubeArray[planeNum].SignalObj.ColNormalArray=null;
        return false;
    }

    public static void setArrayLevelCntrlArray(int planeNum) {
        long MyStartROWSDOE = Length;
        long MyStartCOLMDOE = InitLevels[0].length - 1;
        LevelCntrlArray = ByteBuffer.allocateDirect((int) (MyStartROWSDOE * MyStartCOLMDOE * SIZEOFCELL)).order(ByteOrder.nativeOrder());
        if ((LevelCntrlArray == null)) {
            log.error("setArrayLevelCntrlArray:Null LevelCntrlArray for planeNum=" + planeNum + " MyStartROWSDOE=" + MyStartROWSDOE + " MyStartCOLMDOE=" + MyStartCOLMDOE);
        }
        LevelValueStore = new double[(int) Length][LEVELS + 10];
        LevelValueInit = new int[(int) Length];
        NameCol = new String[(int) Length];
        for (int j = 0; j < Length; j++) {
            LevelValueStore[j] = new double[(int) LEVELS + 10];
            LevelValueInit[j] = -1;
            NameCol[j] = "";
        }
        for (long j = 0, i = 0; (j < MyStartROWSDOE); j++) {
            if (UseLevelsFromFile == true) {
                DOEColumnInputLevel.ProgramDefine("Signal");
                DOEColumnInputLevel.IO(planeNum, "NormalInput", "Specific", (int) j);
                if (LevelValueInit[(int) j] == -1) {
                    DOEColumnInputLevel.IO(planeNum, "NormalInput", "Common", (int) j);
                }
                DOEColumnInputLevel.Myprintf(planeNum, "NormalInput", (int) j);
                LevelCntrlArrayPut(planeNum, j, LevelCtrlIndex, 1);
                LevelCntrlArrayPut(planeNum, j, LastIssuedLevel, 1);
                LevelCntrlArrayPut(planeNum, j, LastIssuedValue, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialRow1Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow1Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowRow1Lev, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialRow3Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow3Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowRow3Lev, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialNMinusLev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadNMinusLev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowNMinusLev, 1);
                LevelValueStore[(int) j] = new double[(int) LevelCntrlArrayGet(planeNum, j, LevelMaxIndex) + 1];
            } else if ((UseLevelsFromArray == true) && (i < InitLevels.length) && (j == InitLevels[(int) i][0])) {
                //System.out.println(" Setting Level for Column=" + j + " i=" + i + " from Array.");
                LevelCntrlArrayPut(planeNum, j, LevelMinIndex, ((int) InitLevels[(int) i][1]));
                LevelCntrlArrayPut(planeNum, j, LevelMaxIndex, ((int) InitLevels[(int) i][2]));
                LevelCntrlArrayPut(planeNum, j, LevelCtrlIndex, ((int) InitLevels[(int) i][3]));
                LevelCntrlArrayPut(planeNum, j, LastIssuedLevel, ((int) InitLevels[(int) i][4]));
                LevelCntrlArrayPut(planeNum, j, LastIssuedValue, ((int) InitLevels[(int) i][5]));

                LevelCntrlArrayPut(planeNum, j, FactorialRow1Lev, ((int) InitLevels[(int) i][6]));
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow1Lev, ((int) InitLevels[(int) i][7]));
                LevelCntrlArrayPut(planeNum, j, FactRowRow1Lev, ((int) InitLevels[(int) i][8]));

                LevelCntrlArrayPut(planeNum, j, FactorialRow3Lev, ((int) InitLevels[(int) i][9]));
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow3Lev, ((int) InitLevels[(int) i][10]));
                LevelCntrlArrayPut(planeNum, j, FactRowRow3Lev, ((int) InitLevels[(int) i][11]));

                LevelCntrlArrayPut(planeNum, j, FactorialNMinusLev, ((int) InitLevels[(int) i][12]));
                LevelCntrlArrayPut(planeNum, j, FactSpreadNMinusLev, ((int) InitLevels[(int) i][13]));
                LevelCntrlArrayPut(planeNum, j, FactRowNMinusLev, ((int) InitLevels[(int) i][14]));
                NameCol[(int) j] =/*"Signal"+ "_" + "NormalInput" + "_" +*/ "_Factor_" + j + "_";
                log.info("setArrayLevelCntrlArray:Got Initial Value i=" + i + " Putting  at j=" + j
                        + " Retrieved LevelMinValue=" + LevelCntrlArrayGet(planeNum, j, LevelMinIndex)
                        + " Retrieved LevelMaxValue=" + LevelCntrlArrayGet(planeNum, j, LevelMaxIndex)
                        + " Retrieved LevelCtrlIndex=" + LevelCntrlArrayGet(planeNum, j, LevelCtrlIndex)
                        + " Retrieved LastIssuedLevel=" + LevelCntrlArrayGet(planeNum, j, LastIssuedLevel)
                        + " Retrieved LastIssuedValue=" + LevelCntrlArrayGet(planeNum, j, LastIssuedValue)
                        + " Retrieved FactorialRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactorialRow1Lev)
                        + " Retrieved FactSpreadRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadRow1Lev)
                        + " Retrieved FactRowRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactRowRow1Lev)
                        + " Retrieved FactorialRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactorialRow3Lev)
                        + " Retrieved FactSpreadRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadRow3Lev)
                        + " Retrieved FactRowRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactRowRow3Lev)
                        + " Retrieved FactorialNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactorialNMinusLev)
                        + " Retrieved FactSpreadNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadNMinusLev)
                        + " Retrieved FactRowNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactRowNMinusLev)
                );
                i++;
            } else {
                LevelCntrlArrayPut(planeNum, j, LevelMinIndex, 1);
                LevelCntrlArrayPut(planeNum, j, LevelMaxIndex, LEVELS);
                LevelCntrlArrayPut(planeNum, j, LevelCtrlIndex, 1);
                LevelCntrlArrayPut(planeNum, j, LastIssuedLevel, 1);
                LevelCntrlArrayPut(planeNum, j, LastIssuedValue, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialRow1Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow1Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowRow1Lev, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialRow3Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadRow3Lev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowRow3Lev, 1);

                LevelCntrlArrayPut(planeNum, j, FactorialNMinusLev, 1);
                LevelCntrlArrayPut(planeNum, j, FactSpreadNMinusLev, 1);
                LevelCntrlArrayPut(planeNum, j, FactRowNMinusLev, 1);
                NameCol[(int) j] =/*"Signal"+ "_" + "NormalInput" + "_" +*/ "_Factor_" + j + "_";
                log.info("setArrayLevelCntrlArray:Putting Default Values at j=" + j
                        + " Retrieved LevelMinValue=" + LevelCntrlArrayGet(planeNum, j, LevelMinIndex)
                        + " Retrieved LevelMaxValue=" + LevelCntrlArrayGet(planeNum, j, LevelMaxIndex)
                        + " Retrieved LevelCtrlIndex=" + LevelCntrlArrayGet(planeNum, j, LevelCtrlIndex)
                        + " Retrieved LastIssuedLevel=" + LevelCntrlArrayGet(planeNum, j, LastIssuedLevel)
                        + " Retrieved LastIssuedValue=" + LevelCntrlArrayGet(planeNum, j, LastIssuedValue)
                        + " Retrieved FactorialRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactorialRow1Lev)
                        + " Retrieved FactSpreadRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadRow1Lev)
                        + " Retrieved FactRowRow1Lev=" + LevelCntrlArrayGet(planeNum, j, FactRowRow1Lev)
                        + " Retrieved FactorialRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactorialRow3Lev)
                        + " Retrieved FactSpreadRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadRow3Lev)
                        + " Retrieved FactRowRow3Lev=" + LevelCntrlArrayGet(planeNum, j, FactRowRow3Lev)
                        + " Retrieved FactorialNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactorialNMinusLev)
                        + " Retrieved FactSpreadNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactSpreadNMinusLev)
                        + " Retrieved FactRowNMinusLev=" + LevelCntrlArrayGet(planeNum, j, FactRowNMinusLev)
                );
            }
            System.out.println(" Column=" + j + " Min Level=" + LevelCntrlArrayGet(planeNum, j, LevelMinIndex) + " Max Level=" + LevelCntrlArrayGet(planeNum, j, LevelMaxIndex));
        }
        MaxLevelCompute();
        log.error("LevelCntrlArray.size=" + LevelCntrlArray.limit() + " MyStartROWSDOE=" + MyStartROWSDOE + " MyStartCOLMDOE=" + MyStartCOLMDOE + " for planeNum=" + planeNum);
        ComputeROWSDOETaguchi();
    }

    public static int LevelCntrlArrayGet(int planeNum, long row, long LevelIndex) {
        long MaxRow = Length;
        long MaxCol = InitLevels[0].length - 1;
        if ((LevelCntrlArray != null)
                && ((MaxRow * MaxCol) < LevelCntrlArray.limit())
                && (row < MaxRow)
                && ((row * LevelIndex) < LevelCntrlArray.limit())) {
            long n = ((row) * MaxCol) + LevelIndex;
            if (n <= LevelCntrlArray.limit()) {
                return (int) LevelCntrlArray.get((int) n);
            }
        }
        return -1;
    }

    public static void LevelCntrlArrayPut(int planeNum, long row, long LevelIndex, long Value) {
        long MaxRow = Length;
        long MaxCol = InitLevels[0].length - 1;
        if (LevelCntrlArray == null) {
            log.error("LevelCntrlArrayPut:LevelCntrlArray=null for planeNum=" + planeNum + " MaxRow=" + MaxRow + " MaxCol=" + MaxCol);
            return;
        }
        if ((LevelCntrlArray != null)
                && ((MaxRow * MaxCol) < LevelCntrlArray.limit())
                && (row < MaxRow)
                && ((row * LevelIndex) < LevelCntrlArray.limit())) {
            long n = ((row) * MaxCol) + LevelIndex;
            if (n <= LevelCntrlArray.limit()) {
                if ((Value <= Byte.MAX_VALUE) && (Value >= Byte.MIN_VALUE)) {
                    LevelCntrlArray.put((int) n, (byte) Value);
                } else {
                    LevelCntrlArray.put((int) n, (byte) 1);
                }
            }
        }
    }

    public static void MaxLevelCompute() {
        for (int col = 0; col < Length; col++) {
            MaxLevel = Math.max(MaxLevel, LevelCntrlArrayGet(0, col, LevelMaxIndex));
        }
    }
    // DataArray Functions

    public static ByteBuffer getArrayDataArray() {
        return DataArray;
    }

    public static void setArrayDataArray(int planeNum) {
        long MyStartROWSDOE = ROWSDOE;
        long MyStartCOLMDOE = Length;
        DataArray = ByteBuffer.allocateDirect((int) (MyStartROWSDOE * MyStartCOLMDOE * SIZEOFCELL)).order(ByteOrder.nativeOrder());
        if ((DataArray == null)) {
            log.error("setArrayDataArray:Null DataArray for planeNum=" + planeNum);
        }

        for (int i = 0; ((i < MyStartROWSDOE)); i++) {
            for (int j = 0; ((j < MyStartCOLMDOE)); j++) {
                {
                    DataArrayPut(planeNum, i, j, (byte) 0);
                    log.info("setArrayDataArray:Putting Got Initial Value DOE Matrix=" + 0 + " at i=" + i + " j=" + j + " Retrieved Value=" + DataArrayGet(planeNum, i, j));
                }
            }
        }
        log.error("setArrayDataArray.size=" + DataArray.limit() + " MyStartROWSDOE=" + MyStartROWSDOE + " MyStartCOLMDOE=" + MyStartCOLMDOE + " for planeNum=" + planeNum);

    }

    public static byte DataArrayGet(int planeNum, long row, long col) {
        long MaxRow = ROWSDOE;
        long MaxCol = Length;
        if ((DataArray != null)
                && ((MaxRow * MaxCol) < DataArray.limit())
                && (row < MaxRow)
                && (col <= MaxCol)
                && ((row * col) < DataArray.limit())) {
            long n = ((row) * MaxCol) + col;
            if (n <= DataArray.limit()) {
                return DataArray.get((int) n);
            }
        }
        log.fatal("DataArrayGet: Size Exceeded:row=" + row + " MaxRow=" + MaxRow + " col=" + col + " MaxCol=" + MaxCol + " this.DataArray.limit()=" + DataArray.limit());
        return 0;
    }

    public static void DataArrayPut(int planeNum, long row, long col, Byte Value) {
        long MaxRow = ROWSDOE;
        long MaxCol = Length;
        if (DataArray == null) {
            log.info("DataArrayPut:DataArray=null for planeNum=" + planeNum);
            return;
        }
        if ((DataArray != null)
                && ((MaxRow * MaxCol) < DataArray.limit())
                && (row < MaxRow)
                && (col <= MaxCol)
                && ((row * col) < DataArray.limit())) {
            long n = ((row) * MaxCol) + col;
            if (n <= DataArray.limit()) {
                DataArray.put((int) n, Value);
            } else {
                log.error("DataArrayPut: Size Exceeded:row=" + row + " col=" + col);
            }
        }
    }

    /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ************************Normal Core Cell Generating Functions Start Here* */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
    public static void ColZeroMain(int planeNum, long row, long col, long Levels, long LevelSpread) {
        int i = (int) (row + 1) / (int) Levels;
        boolean IncrementFlag = false;
        int levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
        DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
        if (((i > 0) && (((LevelSpread <= Levels) && (Levels > 0) && (((row + 1) % Levels) == 0)))
                || ((i > 0) && (LevelSpread > 0) && (LevelSpread >= Levels) && (((row + 1) % LevelSpread) == 0)))
                || ((i == 0) && (LevelSpread > 0) && (((row + 1) % LevelSpread) == 0))
                || ((i == 1) && (Levels > 0) && (row > 0) && (((row + 1) % LevelSpread) == 0))) {
            IncrementFlag = true;
            //}
            if (IncrementFlag == true) {
                LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
                if ((levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
                } else {
                    levelCntrl++;
                }
            }
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
    }
    public static long ColZeroLogNMain(int planeNum, long row, long col, long LogN, long Levels, long LevelSpread) {
        int i = (int) (row + 1) / (int) Levels;
        boolean IncrementFlag = false;
        int levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
        if (LogN == 0) {
            DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            if (((i > 0) && (((LevelSpread <= Levels) && (Levels > 0) && (((row + 1) % Levels) == 0)))
                    || ((i > 0) && (LevelSpread > 0) && (LevelSpread >= Levels) && (((row + 1) % LevelSpread) == 0)))
                    || ((i == 0) && (LevelSpread > 0) && (((row + 1) % LevelSpread) == 0))
                    || ((i == 1) && (Levels > 0) && (row > 0) && (((row + 1) % LevelSpread) == 0))) {
                IncrementFlag = true;
                if (IncrementFlag == true) {
                    LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
                    if ((levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                        levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
                    } else {
                        levelCntrl++;
                    }
                }
            }
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        }else if ((LogN > 0)&&(col==0)&&(row == Levels)) {
            for (int x = 0; x <= Levels; x++) {
                DataArrayPut(planeNum, x, col, (byte) levelCntrl);
                LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
                LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            }
            return (row);
        }else if ((LogN > 0)&&(col==0)&&(row > Levels)) {
            DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            if (((i > 0) && (((LevelSpread <= Levels) && (Levels > 0) && (((row + 1) % Levels) == 0)))
                    || ((i > 0) && (LevelSpread > 0) && (LevelSpread >= Levels) && (((row + 1) % LevelSpread) == 0)))
                    || ((i == 0) && (LevelSpread > 0) && (((row + 1) % LevelSpread) == 0))
                    || ((i == 1) && (Levels > 0) && (row > 0) && (((row + 1) % LevelSpread) == 0))) {
                IncrementFlag = true;
                if (IncrementFlag == true) {
                    LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
                    if ((levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                        levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
                    } else {
                        levelCntrl++;
                    }
                }
            }
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        }else if ((LogN > 0)&&(col > 0)&&(col <(LogN - 1)) ) {
            //Mapping Beware Begin
            int j = (int) (row + 1) / (int) Math.pow(Levels,col);
            DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            if (((i > 0) && (((LevelSpread <= Levels) && (Levels > 0) && (((row + 1) % Levels) == 0)))
                    || ((i > 0) && (LevelSpread > 0) && (LevelSpread >= Levels) && (((row + 1) % LevelSpread) == 0)))
                    || ((i == 0) && (LevelSpread > 0) && (((row + 1) % LevelSpread) == 0))
                    || ((i == 1) && (Levels > 0) && (row > 0) && (((row + 1) % LevelSpread) == 0))
                    || ((col < LogN)&&(row > Levels) && (j > 0) && (LevelSpread > 0) && (LevelSpread >= Levels) && (((row + 1) % j) == 0))) {
                IncrementFlag = true;
                if (IncrementFlag == true) {
                    LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
                    if ((levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                        levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
                    } else {
                        levelCntrl++;
                    }
                }
            }
            //Mapping Beware End
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        }
        return (row);
    }
 public static void ColumnOneMain(int planeNum, long row, long col, long Levels) {
        int levelCntrl = 1;
        levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
        DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
        if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        } else if ((levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
            levelCntrl++;
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
    }
    public static long ColumnRootNPlusOneMain(int planeNum, long row, long col, long RootN, long LogN,long Levels) {
        int levelCntrl = 1;
        if ((RootN == 0)&&(LogN==0)) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
            DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            } else if ((levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                levelCntrl++;
            }
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        } else if ((RootN > 0)&&(LogN >=0) && (row == Levels)) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
            DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
            if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            } else if ((levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                levelCntrl++;
            }
            LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        } else {
            if ((RootN > 0)&&(col <(LogN+RootN-1))) {
                //Mapping Beware Begin
                int j=(int)(LogN+RootN-col-1);
                
                int[] sampleBitPattern=null;
                // Bitpattern= 1 1 2 for Level=2 for col=RootN-2
                // Bitpattern= 1 1 2 3 for Level=3 for col=RootN-2
                // Bitpattern= 1 1 1 2 for Level=2 for col=RootN-3
                // Bitpattern= 1 1 1 2 3 for Level=3 for col=RootN-3
                int Level1=LevelCntrlArrayGet(planeNum, j, LevelMaxIndex);
                sampleBitPattern=new int[Level1+(int)BitPatColIndex+1];
                BitPatternIndex=Level1+(int)BitPatColIndex+1;
                sampleBitPattern=GenerateBitPattern(sampleBitPattern, Level1);
                sampleBitPattern=RotateBitPattern( sampleBitPattern, BitPatRotateIndex);               
                sampleBitPattern=IncreaseBitPattern(sampleBitPattern, Level1);
                for(int k=0; (k < BitPatternIndex)&&(row < ROWSDOE); k++ , row++){
                    DataArrayPut(planeNum, row, LogN+j, (byte)sampleBitPattern[k]);
                }
                BitPatRotateIndex++;
                if (BitPatRotateIndex==BitPatternIndex) {
                    BitPatRotateIndex=0;
                    BitPatIncrIndex++;
                }
                if (BitPatIncrIndex==BitPatternIndex) {
                    BitPatIncrIndex=0;
                }
                //Mapping Beware End
                return (row-1);
            }
            else if (col == (RootN+LogN-1)) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
                DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
                LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
                LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
                if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
                } else if ((levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
                    levelCntrl++;
                }
                LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
            }
        }
        return row;
    }

    public static void RowLevelupto1LevelsRecursiveMain(int planeNum, long row, long col) {
        int levelCntrl = 1;
        int Levels=(LevelCntrlArrayGet(planeNum, col, LevelMaxIndex));
        if (LevelCntrlArrayGet(planeNum, col, LastIssuedLevel) == 0) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            } else if (((LevelCntrlArrayGet(planeNum, col, LastIssuedLevel)) >= (LevelCntrlArrayGet(planeNum, col, LevelMinIndex)))
                    && ((LevelCntrlArrayGet(planeNum, col, LastIssuedLevel)) <= (LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)))) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LastIssuedLevel);
            }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);

        //HyperCubeArray[planeNum].getDataArray().get((int) row)[(int) col].setCell((int) row, (int) col, HyperCube[planeNum].Matrix.LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        DataArrayPut(planeNum, row, col, (byte) LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        if ((levelCntrl >= LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))
                || (levelCntrl <= LevelCntrlArrayGet(planeNum, col, LevelMinIndex))) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        }
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl + 1);

    }
    public static int[] GenerateBitPattern(int[] sampleBitPattern, int Level1) {
        int i = 0;
        for (; ((i < 2)&&(Level1 >= 2)) ; i++) {
            sampleBitPattern[i] = 1;
        }
        int k = 0;
        for (; (i < BitPatternIndex) && (k < BitPatternIndex); i++, k++) {
            if ( ( (Level1 - k) >= 2) && (k > 0) && ( (k+1) <= Level1) )
                sampleBitPattern[i] = k+1;
            else
                sampleBitPattern[i] = Level1;
        }
//        System.out.println("GenerateBitPattern: Generated 1 time:sampleBitPattern=");
//                for (int x = 0; (x < (sampleBitPattern.length)); x++) {
//                    System.out.print(" " + sampleBitPattern[x] + " ");
//                }
//                System.out.println(System.lineSeparator());
        return sampleBitPattern;
    }
    public static int[] RotateBitPattern(int[] sampleBitPattern, int BitPatRotateIndex) {
        if (BitPatRotateIndex > 0) {
            for (int j = 1; j <= BitPatRotateIndex; j++) {
                int oldBegin = sampleBitPattern[0];
                int i = 0;
                for (i = 0; (i < (BitPatternIndex - 1)); i++) {
                    sampleBitPattern[i] = sampleBitPattern[i + 1];
                }
                sampleBitPattern[i] = oldBegin;
//                System.out.println("RotateBitPattern: Rotated "+ j + " time:sampleBitPattern=");
//                for (int k = 0; (k < (sampleBitPattern.length)); k++) {
//                    System.out.print(" " + sampleBitPattern[k] + " ");
//                }
//                System.out.println(System.lineSeparator());
            }
        }
        return sampleBitPattern;
    }
    public static int[] IncreaseBitPattern(int[] sampleBitPattern, int Level1) {
        int i = 0;
        if(BitPatIncrIndex >0){
            int[] Newsample=new int[Level1+(int)BitPatColIndex+1+BitPatIncrIndex];
            BitPatternIndex=Level1+(int)BitPatColIndex+1+BitPatIncrIndex;
        for (; (i < (sampleBitPattern.length - 2)); i++) {
            Newsample[i] = 1;
        }
        int k = 0;
        for (; (i < BitPatternIndex) && (k < BitPatternIndex) ; i++, k++) {
            Newsample[i] = Level1 ;
        }
//        System.out.println("IncreaseBitPattern: Increased " + BitPatIncrIndex + " time:Newsample=");
//                for (int x = 0; (x < (Newsample.length)); x++) {
//                    System.out.print(" " + Newsample[x] + " ");
//                }
//                System.out.println(System.lineSeparator());
                return Newsample;
        }
        return sampleBitPattern;
    }
    public static long ComplexCommonRCMain(int planeNum, String AlgoName,
            long row,
            long col,
            long Levels,
            long Value,
            long RowSub,
            long ColSub,
            long AlgoRowFactIndex,
            long AlgoRowSpreadIndex,
            long AlgoFactRowFactIndex) {
        long Temp = 1;
        int levelCntrl = 1;
        long NPRFactValue = 1;
        long SpreadValue = 1;
        long FactValue = 1;
        long NPRSpreadValue = 1;
        if (AlgoName.equalsIgnoreCase("RowLevel1Levels")) //            log.info("ComplexCommonRCMain " 
        //                + " AlgoRowFactIndex=" + AlgoRowFactIndex 
        //                + " AlgoRowSpreadIndex="+ AlgoRowSpreadIndex
        //                + " AlgoFactRowFactIndex="+ AlgoFactRowFactIndex);
        {
            FactValue = LevelCntrlArrayGet(planeNum, col, AlgoRowFactIndex);
        }
        SpreadValue = LevelCntrlArrayGet(planeNum, col, AlgoRowSpreadIndex);
        NPRFactValue = LevelCntrlArrayGet(planeNum, col, AlgoFactRowFactIndex);
        if (NPRFactValue > 0) {
            NPRSpreadValue = ROWSDOE / NPRFactValue;
        } else {
            NPRSpreadValue = 1;
        }
        LCG_Value = LCG_Value_Random(planeNum, AlgoName, 4, 1, 1, Levels);
        LCG_levelCntrl = (int) LCG_Value_Random(planeNum, AlgoName, 4, 1, LCG_levelCntrl, Levels);
        LCG_TempValue = LCG_TempValue(planeNum, AlgoName, row, col, RowSub, ColSub, Value, Levels);
        if (LCG_Value == 1) {
            LCG_Value = LCG_Value_Random(planeNum, AlgoName, 4, 1, LCG_Value, Levels);
        } else {
            LCG_Value = LCG_Value_Random(planeNum, AlgoName, 4, 1, 1, Levels);
        }
        if (LCG_FactValue == 1) {
            LCG_FactValue = LCG_Factorial_Random(planeNum, AlgoName, FactValue, SpreadValue, 4, 1, Levels);
        } else {
            LCG_FactValue = LCG_Factorial_Random(planeNum, AlgoName, LCG_FactValue, SpreadValue, 4, 1, Levels);
        }
        if ((LCG_NPRFactValue == 1) && (NPRFactValue > 1) && (NPRSpreadValue > 1)) {
            LCG_NPRFactValue = LCG_Factorial_Random(planeNum, AlgoName, NPRFactValue, NPRSpreadValue, 4, 1, Levels);
        } else {
            LCG_NPRFactValue = LCG_Factorial_Random(planeNum, AlgoName, LCG_NPRFactValue, NPRSpreadValue, 4, 1, Levels);
        }

        if ((SpreadValue > 0)) {
            Temp = (LCG_Value + LCG_levelCntrl + LCG_TempValue + LCG_FactValue + LCG_NPRFactValue) % (Levels + 1);
        } else if ((Levels + 1) > 0) {
            Temp = (Value + levelCntrl + LCG_TempValue) % (Levels + 1);
        }
        //log.info("Func:" + AlgoName + ": row=" + row + " col=" + col + " levelCntrl=" + levelCntrl + " Value=" + Value + " LCG_TempValue=" + LCG_TempValue + " LCG_NPRFactValue=" + LCG_NPRFactValue + " Temp=" + Temp);
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);

        if ((Temp >= LevelCntrlArrayGet(planeNum, col, LevelMinIndex)) && (Temp <= LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))) {
            DataArrayPut(planeNum, row, col, (byte) Temp);
        } else {
            Temp = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            DataArrayPut(planeNum, row, col, (byte) Temp);
        }
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, (int) Temp);
        if ((Value >= LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))
                || (Value < LevelCntrlArrayGet(planeNum, col, LevelMinIndex))) {
            Value = LevelCntrlArrayGet(planeNum, col, LastIssuedValue);
        }
        if ((levelCntrl >= LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))
                || (levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMinIndex))) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LastIssuedLevel);
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, (int) Temp);
        LevelCntrlArrayPut(planeNum, col, AlgoRowFactIndex, (FactValue - 1));
        return Temp;
    }

    public static void ColumnFullFactorialRecursiveMain(int planeNum, long row, long col, long Levels, long NumCOLMDOE, long LevelSpread, long RootN, long LogN) {
        long Temp = 0;
        long FullFactorialROWS = (long) Math.pow((1.0 * Levels), (1.0 * col));
        long FullFactorialCOLS = (long) Math.pow((1.0 * Levels), (1.0 * row));
        if ((FullFactorialROWS <= LevelSpread)
                || (FullFactorialCOLS <= LevelSpread)
                || (FullFactorialROWS >= 2) && (FullFactorialROWS <= 4)
                || (FullFactorialCOLS >= 2) && (FullFactorialCOLS <= 32)
                || ((RootN==0)&&(LogN==0)&&((Levels==2)||(Levels==3)||(Levels==4)||(Levels==5))&&(ROWSDOE < 100)&&(Length < 100)) ) {
            //Generate Random Level Value within LevelMinIndex-LevelMaxIndex(Modulus Parameter)
            Temp = ComplexCommonRCMain(planeNum, "ColumnFullFactorialRecursiveMain", row, col,
                    LevelCntrlArrayGet(planeNum, col, LevelMaxIndex),
                    LevelCntrlArrayGet(planeNum, col, LastIssuedValue),
                    4, (long) Math.pow(LevelCntrlArrayGet(planeNum, col, LevelMaxIndex), LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)),
                    FactorialRow3Lev, FactSpreadRow3Lev, FactRowRow3Lev);//Need to test last parameter
            if ((DataArrayGet(planeNum, row, (col - 1)) == Temp)) {
                Temp = (1 * (Temp) + 1) % (Levels + 1);
            }

            //log.info("ColumnFullFactorialRecursiveMain:Random:FullFactorialROWS=" + FullFactorialROWS + " LevelSpread=" + LevelSpread + " FullFactorialCOLS=" + FullFactorialCOLS + " row=" + row + " col=" + col + " Temp=" + Temp + " Proceeding with Random Value");

        } else {
            Temp = SimpleModuloLevel(planeNum, "ColumnFullFactorialRecursiveMain", row, col,
                    SimpleModuloLevelXn,
                    Levels, 1);
            //log.fatal("ColumnFullFactorialRecursiveMain:SimpleModuloLevel:FullFactorialROWS=" + FullFactorialROWS + " LevelSpread=" + LevelSpread + " row=" + row + " col=" + col + " Temp=" + Temp + " Proceeding with SimpleModuloLevel");

        }
        if (Temp < LevelCntrlArrayGet(planeNum, col, LevelMinIndex)) {
            Temp = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        }
        if (Temp > LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
            Temp = LevelCntrlArrayGet(planeNum, col, LevelMaxIndex);
        }
        LevelCntrlArrayPut(planeNum, col, LastIssuedValue, Temp);
        DataArrayPut(planeNum, row, col, (byte) Temp);
        log.info("ColumnFullFactorialRecursiveMain col=" + col + " row=" + row + " Value=" + DataArrayGet(planeNum, row, col));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, Temp);
    }

    // LCG Functions    
    public static long LCG_TempValue(int planeNum, String AlgoName, long row, long col, long RowSub, long ColSub, long Xn, long Modulus) {
        long TempValue = 1;
        // Caution:Positional "If then Else" Blocks
        // Results will be different if position is interchanged
        if (((Modulus + 1) > 0) && ((Math.abs(row - RowSub + 1) % (Modulus + 1)) > 0) && ((Math.abs(col - ColSub + 1) % (Modulus + 1)) > 0) && (((Xn + 1) % (Modulus + 1)) > 0)) {
            TempValue = ((row - RowSub + 1) % (Modulus + 1)) + ((col - ColSub + 1) % (Modulus + 1));
        } else if (((Modulus + 1) > 0) && (Math.abs(col - ColSub + 1) % (Modulus + 1)) > 0) {
            TempValue = (Math.abs(row - RowSub) % (Modulus + 1));
        } else if (((Modulus + 1) > 0) && (Math.abs(row - RowSub + 1) % (Modulus + 1)) > 0) {
            TempValue = (Math.abs(col - ColSub + 1) % (Modulus + 1));
        } else if (((Modulus + 1) > 0) && ((Xn + 1) % (Modulus + 1)) > 0) {
            TempValue = (Math.abs(row - RowSub) % (Modulus + 1));
        }
        return TempValue;
    }

    public long LCG_Value(int planeNum, String AlgoName,
            long a,
            long c,
            long Xn,
            long Modulus) {
        long Value = 1;
        Value = (a * Xn + c) % (Modulus + 1);
        return Value;
    }

    public long LCG_Factorial(int planeNum, String AlgoName, long Factorial, long FactorialSpread, long a, long c, long Modulus) {
        long Value = 1;
        //log.info("LCG_Factorial:" + AlgoName + ": Factorial=" + Factorial + " FactorialSpread=" + FactorialSpread);
        Value = (a * Factorial + c) % (FactorialSpread + 1);
        //Value = (a * Value + c) % (Modulus + 1);
        //log.info("LCG_Factorial:" + AlgoName + ": Value=" + Value);
        return Value;
    }

    public static long SimpleModuloLevel(int planeNum, String AlgoName, long row, long col, long Xn, long Modulus, long ModulusLow) {
        long levelCntrl = 1;
        long Temp=0;
        long Temp1=0;
        
        levelCntrl = (1 * Xn + 1) % (Modulus + 1);
        if ((DataArrayGet(planeNum, row, (col - 1)) == levelCntrl) && (DataArrayGet(planeNum, row, (col - 1)) != Xn)) {
            levelCntrl = (1 * DataArrayGet(planeNum, row, (col - 1)) + 1) % (Modulus + 1);
        }
        if (levelCntrl < ModulusLow) {
            levelCntrl = ModulusLow;
        }
        Counter++;
        //if (((levelCntrl >= HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))||(levelCntrl <= HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMinIndex))))
        //    levelCntrl=HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        return levelCntrl;
    }

    public static long LCG_Value_Random(int planeNum, String AlgoName, long a, long c, long Xn, long Modulus) {
//        //Value = (a * Xn + c) % (Modulus + 1);
//
//        Map<String, Double> variables = new HashMap<>();
//        MyFuncExpress();
//        MyFuncDiff();
//        MyFuncSimple();
//
//        String DiffWithRespTo = "Xn";
//        String Exprn = FormEquation(Xn, a, c, (long) 1.0, DiffWithRespTo, (int) Modulus);
//        Calculus.MathsContxtLAv.SimpleExpression expSimple;
//        Calculus.MathsContxtLAv.Expression expValue;
//        String StrexpSimpleExpress = "";
//        String StrexpDiffExpress = "";
//        int i = 1;
//        long Xnplus1 = Xn;
//        long Temp = 0;
//
//        for (; (i < 10) && (Xn == Xnplus1); Xn++, i++) {
//            variables.put(DiffWithRespTo, ((double) Xn));
//            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
//            Temp = (long) expValue.eval().longValue();
//            log.info("Expr_LCG_Value:Temp(Xn)=" + Temp + " Xn=" + Xn + " Xnplus1=" + Xnplus1);
//            if ((Temp == Xn) || (i > 1)) {
//                expSimple = parseSimple(Exprn, variables,  "Xn");
//                StrexpSimpleExpress = "";
//                StrexpSimpleExpress = expSimple.SimpleExpr();
//                log.info("Expr_LCG_Value: Xn=" + Xn + " Basic Simple Exprn=" + StrexpSimpleExpress);
//
//                Calculus.DiffrIntegrSep.DiffExpr exp = parseDiff(StrexpSimpleExpress, variables, DiffWithRespTo);
//                StrexpDiffExpress = "";
//                StrexpDiffExpress = exp.DiffExpr();
//                log.info("Expr_LCG_Value:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
//
//                expSimple = parseSimple(StrexpDiffExpress, variables, "Xn");
//                StrexpSimpleExpress = expSimple.SimpleExpr();
//                log.info("Expr_LCG_Value: Xn=" + Xn + " (Simplified Diff Equation) Exprn=" + StrexpSimpleExpress);
//
//                expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
//                log.info("Expr_LCG_Value:Result(Value)(Xn)=" + expValue.eval());
//                Exprn = StrexpSimpleExpress;
//                StrexpSimpleExpress = "";
//                StrexpDiffExpress = "";
//                Xnplus1 = (long) (expValue.eval() % (Modulus + 1));
//            } else {
//                Xnplus1 = Temp;
//            }
//        }
//        return ((long) Xnplus1);
          Xn_LCG = MySimple.LCG_Value_RandomSimple(Xn_LCG, (long) a, (long) c, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
          return Xn_LCG;
    }

    public static long LCG_Factorial_Random(int planeNum, String AlgoName, long Xn, long FactorialSpread, long a, long c, long Modulus) {
//        //Value = (a * Factorial + c) % (FactorialSpread + 1);
//        Map<String, Double> variables = new HashMap<>();
//        Map<String, String> variablesDiff = new HashMap<>();
//        MyFuncExpress();
//        MyFuncDiff();
//        MyFuncSimple();
//        String DiffWithRespTo = "Xn";
//        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
//        String Exprn = FormEquation(Xn, a, c, (long) 1.0, DiffWithRespTo, (int) Modulus);
//
//        Calculus.MathsContxtLAv.SimpleExpression expSimple;
//        Calculus.MathsContxtLAv.Expression expValue;
//        String StrexpSimpleExpress = "";
//        String StrexpDiffExpress = "";
//        int i = 1;
//        long Xnplus1 = Xn;
//        long Temp = 0;
//        for (; (i < 10) && (Xn == Xnplus1); Xn++, i++) {
//            variables.put("Xn", ((double) Xn));
//            expValue = parse(Exprn, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
//            Temp = (long) expValue.eval().longValue();
//            log.info("Expr_LCG_Value:Temp(Xn)=" + Temp + " Xn=" + Xn + " Xnplus1=" + Xnplus1);
//            if ((Temp == Xn) || (i > 1)) {
//                expSimple = parseSimple(Exprn, variables,  DiffWithRespTo);
//                StrexpSimpleExpress = expSimple.SimpleExpr();
//                log.info("Expr_LCG_Factorial: Xn=" + Xn + " Basic Simple Exprn=" + StrexpSimpleExpress);
//
//                Calculus.DiffrIntegrSep.DiffExpr exp = parseDiff(StrexpSimpleExpress, variables, DiffWithRespTo);
//                StrexpDiffExpress = exp.DiffExpr();
//                log.info("Expr_LCG_Factorial:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
//
//                expSimple = parseSimple(StrexpDiffExpress, variables, "Xn");
//                StrexpSimpleExpress = expSimple.SimpleExpr();
//                log.info("Expr_LCG_Value: Xn=" + Xn + " (Simplified Diff Equation) Exprn=" + StrexpSimpleExpress);
//
//                expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent",MathsContxtLAv.LogLevel.Off);
//                log.info("Expr_LCG_Factorial:Result(Value)(Xn)=" + expValue.eval());
//                Exprn = StrexpSimpleExpress;
//                StrexpSimpleExpress = "";
//                StrexpDiffExpress = "";
//                Xnplus1 = (long) (expValue.eval() % (Modulus + 1));
//            } else {
//                Xnplus1 = Temp;
//            }
//        }
//        return ((long) Xnplus1);
        Xn_LCGDyDx = MyFactorial.LCG_Factorial_RandomSimple(Xn_LCGDyDx, (long) a, (long) c, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
     return ((long) Xn_LCGDyDx);
    }

    public static boolean DupRowCheckMatrixMode(int planeNum, int length) {
        int i = 0;
        int k = 0;
        int c = 0;
        int t = 0;
        int p = 0;
        int scalar_m = 1;
        int tLin = 0;
        int ValueRowC = -1;
        int ValueRowI = -1;
        int ValueRowLinP = -1;
        int ValueRowLinI = -1;
        int ValueRowLinK = -1;
        boolean flag = false;
        boolean IsMultipleflag = false;
        boolean IsLinAddflag = false;
        String str = "";
        String strDup = "";
        String strDupK = "";
        long j = 0;
        Set<String> set = new HashSet<String>();
        UniqueMap = new HashMap<String, Long>();
        for (i = 0; i < ROWSDOE; i++) {
            str = getRowAsString(planeNum, i, length);
            if ((set.add(str) == false) && (!UniqueMap.isEmpty()) && (UniqueMap.containsKey(str))) {
                j = UniqueMap.get(str);
                OverallRowDupCount++;
                DupsRowList.add(i + "@" + j);
                flag = true;
                //System.out.println("DupRowCheckMatrixMode:Row:i=" + i + " is duplicate (Copy) of j=" + j);
            } else {
                //Aliases of Rows (Row Multiples)
                for (c = 0; (c < i); c++) {
                    strDup = getRowAsString(planeNum, i, length);
                    for (IsMultipleflag = true, t = 0; t < ROWSDOE; t++) {
                        ValueRowC = DataArrayGet(planeNum, c, t);
                        ValueRowI = DataArrayGet(planeNum, i, t);
                        // Is an Integer Multiple whichever is greater
                        if ((((ValueRowC > ValueRowI) && (ValueRowI > 0) && (ValueRowC % ValueRowI) == 0)
                                || ((ValueRowI > ValueRowC) && (ValueRowC > 0) && ((ValueRowI % ValueRowC) == 0))) && (IsMultipleflag == true)) {
                            IsMultipleflag = true;
                        } else {
                            IsMultipleflag = false;
                            break;
                        }
                    }
                }
                if ((IsMultipleflag == true) && (!strDup.equalsIgnoreCase("")) && (!UniqueMap.isEmpty()) && (UniqueMap.containsKey(strDup))) {
                    j = UniqueMap.get(strDup);
                    OverallRowDupCount++;
                    DupsRowList.add(i + "@" + j);
                    flag = true;
                    //System.out.println("DupRowCheckMatrixMode:Row:i=" + i + " is duplicate (Multiple) of j=" + j);
                } else if ((IsMultipleflag == false)) {
                    // Source:Wiki Elementary Matrices
                    //Linear Addition of Rows
                    // (Row Ri + scalar_m*Row Rk == Row Rp)
                    IsLinAddflag = false;
                    for (k = 0; (k < i); k++) {
                        strDupK = getRowAsString(planeNum, k, length);
                        for (p = 0; (p < k); p++) {
                            for (scalar_m = LevelCntrlArrayGet(0, i, LevelMinIndex), IsLinAddflag = false; scalar_m <= 2/*LevelCntrlArrayGet(0, i, LevelMaxIndex)*/; scalar_m++, IsLinAddflag = false) {
                                for (tLin = 0; tLin < Length; tLin++) {
                                    ValueRowLinP = DataArrayGet(planeNum, p, tLin);
                                    ValueRowLinI = DataArrayGet(planeNum, i, tLin);
                                    ValueRowLinK = DataArrayGet(planeNum, k, tLin);
                                    //System.out.println("DupCheckMatrix:Row:i=" + i + " tLin=" + tLin + " k=" + k + " and p=" + p + " scalar_m=" + scalar_m + " ValueRowLinP=" + ValueRowLinP + " ValueRowLinI=" + ValueRowLinI + " ValueRowLinK=" + ValueRowLinK);
                                    // Is an Integer Multiple whichever is greater
                                    if (((ValueRowLinK + ValueRowLinI) > ValueRowLinP)
                                            && (((ValueRowLinK + scalar_m * ValueRowLinI) == ValueRowLinP)
                                            || ((ValueRowLinK - scalar_m * ValueRowLinI) == ValueRowLinP)
                                            || ((ValueRowLinI + scalar_m * ValueRowLinK) == ValueRowLinP)
                                            || ((ValueRowLinI - scalar_m * ValueRowLinK) == ValueRowLinP))) {
                                        IsLinAddflag = true;
                                    } else {
                                        IsLinAddflag = false;
                                        //System.out.println("DupRowCheckMatrixMode:Row:i=" + i + " tLin=" + tLin + " is NOT duplicate (Linear Addition) with k=" + k + " and p=" + p + " scalar_m=" + scalar_m + " ValueRowLinI=" + ValueRowLinI + " ValueRowLinK=" + ValueRowLinK + " =ValueRowLinP=" + ValueRowLinP );
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if ((IsLinAddflag == true) && (!strDupK.equalsIgnoreCase("")) && (!UniqueMap.isEmpty()) && (UniqueMap.containsKey(strDupK))) {
                        j = UniqueMap.get(strDupK);
                        OverallRowDupCount++;
                        DupsRowList.add(i + "@" + j);
                        flag = true;
                        //System.out.println("DupRowCheckMatrixMode:Row:i=" + i + " is duplicate (Linear Addition) with j=" + j + " scalar_m=" + scalar_m);
                    } else {
                        //System.out.println("DupRowCheckMatrixMode:Adding i=" + i + " c=" + c + " scalar_m=" + scalar_m +  " IsMultipleflag=" + IsMultipleflag + " IsLinAddflag=" + IsLinAddflag);
                        UniqueMap.put(str, (long) i);
                    }
                }
            }
        }
        OverallUniqueRowCount = ROWSDOE - OverallRowDupCount;
        if (flag == false) {
            log.fatal("DupRowCheckMatrixMode:No Row duplicates & Aliases for Whole Array ");
            //System.out.println("DupRowCheckMatrixMode:No duplicates for Whole Array ");
        }
        return flag;
    }

    public static boolean DupColCheckMatrixMode(int planeNum, int length) {
        int i = 0;
        int c = 0;
        int k = 0;
        int t = 0;
        int p = 0;
        int ValueColC = -1;
        int ValueColI = -1;
        int scalar_m = 1;
        int tLin = 0;
        int ValueColLinP = -1;
        int ValueColLinI = -1;
        int ValueColLinK = -1;
        boolean IsLinAddflag = false;
        boolean flag = false;
        boolean IsMultipleflag = false;
        boolean IsOutputMultipleflag = true;
        String str = "";
        String strDup = "";
        String strDupK = "";
        long j = 0;
        Set<String> set = new HashSet<String>();
        UniqueMapCol = new HashMap<String, Long>();
        for (i = 0; i < length; i++) {
            str = getColAsString(planeNum, ROWSDOE, i);
            if ((set.add(str) == false) && (!UniqueMapCol.isEmpty()) && (UniqueMapCol.containsKey(str))) {
                j = UniqueMapCol.get(str);
                OverallColDupCount++;
                DupColList.add(i + "@" + j);
                //System.out.println("DupColCheckMatrixMode:Col:i=" + i + " is duplicate(copy) of j=" + j);
                flag = true;
            } else {
                for (c = 0; (c < i); c++) {
                    strDup = getColAsString(planeNum, ROWSDOE, c);
                    for (IsMultipleflag = true, t = 0; t < ROWSDOE; t++) {
                        ValueColC = DataArrayGet(planeNum, t, c);
                        ValueColI = DataArrayGet(planeNum, t, i);
                        // Is an Integer Multiple whichever is greater
                        if ((((ValueColC > ValueColI) && (ValueColI > 0) && (ValueColC % ValueColI) == 0)
                                || ((ValueColI > ValueColC) && (ValueColC > 0) && ((ValueColI % ValueColC) == 0))) && (IsMultipleflag == true)) {
                            IsMultipleflag = true;
                        } else {
                            IsMultipleflag = false;
                            break;
                        }
                    }
                }
                if ((IsMultipleflag == true) && (!strDup.equalsIgnoreCase("")) && (!UniqueMapCol.isEmpty()) && (UniqueMapCol.containsKey(strDup))) {
                    j = UniqueMapCol.get(strDup);
                    OverallColDupCount++;
                    DupColList.add(i + "@" + j);
                    flag = true;
                    //System.out.println("DupColCheckMatrixMode:Col:i=" + i + " is duplicate (Multiple) of j=" + j);
                } else if ((IsMultipleflag == false)) {
                    // Source:Wiki Elementary Matrices
                    //Linear Addition of Cols
                    // (Col Ci + scalar_m*Col Ck == Col Cp)
                    for (k = 0; (k < length); k++) {
                        strDupK = getColAsString(planeNum, ROWSDOE, k);
                        for (p = 0; (p < length); p++) {
                            for (scalar_m = LevelCntrlArrayGet(0, i, LevelMinIndex), IsLinAddflag = false; scalar_m <= 2/*LevelCntrlArrayGet(0, i, LevelMaxIndex)*/; scalar_m++, IsLinAddflag = false) {
                                for (tLin = 0; tLin < ROWSDOE; tLin++) {
                                    ValueColLinP = DataArrayGet(planeNum, tLin, c);
                                    ValueColLinI = DataArrayGet(planeNum, tLin, i);
                                    ValueColLinK = DataArrayGet(planeNum, tLin, k);
                                    //System.out.println("DupColCheckMatrixMode:Col:i=" + i + " tLin=" + tLin + " k=" + k + " and p=" + p + " scalar_m=" + scalar_m + " ValueColLinP=" + ValueColLinP + " ValueColLinI=" + ValueColLinI + " ValueColLinK=" + ValueColLinK);

                                    // Is an Integer Multiple whichever is greater
                                    if (((ValueColLinK + ValueColLinI) > ValueColLinP)
                                            && (((ValueColLinK + scalar_m * ValueColLinI) == ValueColLinP)
                                            || ((ValueColLinK - scalar_m * ValueColLinI) == ValueColLinP)
                                            || ((ValueColLinI + scalar_m * ValueColLinK) == ValueColLinP)
                                            || ((ValueColLinI - scalar_m * ValueColLinK) == ValueColLinP))) {
                                        IsLinAddflag = true;
                                    } else {
                                        IsLinAddflag = false;
                                        //System.out.println("DupColCheckMatrixMode:Row:i=" + i + " tLin=" + tLin + " is NOT duplicate (Linear Addition) with k=" + k + " and p=" + p + " scalar_m=" + scalar_m + " ValueColLinI=" + ValueColLinI + " ValueColLinK=" + ValueColLinK + " =ValueColLinP=" + ValueColLinP );
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    if ((IsLinAddflag == true) && (!strDupK.equalsIgnoreCase("")) && (!UniqueMapCol.isEmpty()) && (UniqueMapCol.containsKey(strDupK))) {
                        j = UniqueMapCol.get(strDupK);
                        OverallColDupCount++;
                        DupColList.add(i + "@" + j);
                        flag = true;
                        //System.out.println("DupColCheckMatrixMode:Col:i=" + i + " is duplicate (Linear Addition) with j=" + j + " scalar_m=" + scalar_m);
                    } else {
                        //System.out.println("DupColCheckMatrixMode:Adding i=" + i + " c=" + c + " scalar_m=" + scalar_m +  " IsMultipleflag=" + IsMultipleflag + " IsLinAddflag=" + IsLinAddflag);
                        UniqueMapCol.put(str, (long) i);
                    }
                }
            }
        }
        OverallUniqueColCount = Length - OverallColDupCount;
        if (flag == false) {
            log.fatal("DupColCheckMatrixMode:No Column duplicates & Aliases for Whole Array ");
            //System.out.println("DupAliasCheckMatrixMode:No Aliases for Whole Array ");
        }
        return flag;
    }

    public static String getColAsString(int planeNum, long row, long Col) {
        String Temp = "";
        long col = Col;
        for (long i = 0; i < row; i++) {
            Temp = Temp + DataArrayGet(planeNum, i, col);
        }
        return Temp;
    }

    public static String getRowAsString(int planeNum, long row, long Col) {
        String Temp = "";
        long col = Col;
        //long col = 6;
        for (long i = 0; i < col; i++) {
            Temp = Temp + DataArrayGet(planeNum, row, i);
        }
        return Temp;
    }

    public static long getRowAsInteger(int planeNum, long row, long Col) {
        long Temp = 0;
        long col = Col;
        //long col = 6;
        for (long i = 0; i < col; i++) {
            Temp = 10 * Temp + DataArrayGet(planeNum, row, i);
        }
        return Temp;
    }

    public static long getColAsInteger(int planeNum, long col /*, long colStart, long colEnd*/) {
        long Temp = 0;
        // Below Length is ROWS of DataArray and NOT String Length
        //long row = LocalLength;
        long row = 6;
        for (long i = 0; i < row; i++) {
            Temp = 10 * Temp + DataArrayGet(planeNum, col, i);
        }
        return Temp;
    }

    public static String getRowAsStringDataArray(int planeNum, long row, long colDupCheckColStart, long colDupCheckColEnd, boolean DisplayAsString) {
        String Temp = "";
        long col = Length;
        for (long i = colDupCheckColStart; i < colDupCheckColEnd; i++) {
            if (DisplayAsString == false) {
                Temp = Temp + DataArrayGet(planeNum, row, i);
            } else {
                Temp = Temp + " " + DataArrayGet(planeNum, row, i);
            }
        }
        return Temp;
    }

    public static double getLevelValuePair(int col, double Level) {
        return LevelValueStore[col][(int) Level];
    }

    public static void PutLevelValuePair(int col, double Level, Double Value) {
        LevelValueStore[col][(int) Level] = Value;
    }

    public static void printDataOutput(int planeNum) {
        try {
            int NumROWSDOE = (int) ROWSDOE;//matrix.limit();
            int NumCOLMDOE = (int) Length;
            int Levels = (int) MaxLevel;
            int FullFactorialCnt = 0;
            int FullFactUptoCol = 0;
            int FullFactIndivCol = 0;
            int c = 0, DataCount = 0;
            int i = -1, j = 0, k = 0;
            String DupString = "";
            String[] DataTemp;
            boolean FullFactFlag = false;
            System.out.print("DataArray:Header:");
            for (int r = 0; r < NumCOLMDOE; r++) {
                System.out.print(r + " ");
            }
            System.out.print(System.lineSeparator());
            System.out.print("DataArray:Header(Name):");
            for (int r = 0; r < NumCOLMDOE; r++) {
                System.out.print(NameCol[r] + " ");
            }
            System.out.print(System.lineSeparator());
            if (DupsRowList.size() > 0) {
                DupString = DupsRowList.getFirst();
                DataTemp = DupString.split("@");
                if (DataTemp.length == 2) {
                    i = Integer.parseInt(DataTemp[0]);
                    k = Integer.parseInt(DataTemp[1]);
                }
            }
            for (int r = 0; r < NumROWSDOE; r++) {
                System.out.print("Row=" + r + " ");
                // Rajesh Pai:Not sure why c should start from 1;
                for (c = 0; c < NumCOLMDOE; c++) {
                    System.out.print(DataArrayGet(planeNum, r, c) + " ");
                    Levels = Math.max(Levels, LevelCntrlArrayGet(0, c, LevelMaxIndex));
                }
                if (r == i) {
                    System.out.print(ConsoleColors.RED + " Row i=" + i + " duplicate with Row=" + k + ConsoleColors.RESET);
                    j++;
                    if (j < DupsRowList.size()) {
                        DupString = DupsRowList.get(j);
                        DataTemp = DupString.split("@");
                        if (DataTemp.length == 2) {
                            i = Integer.parseInt(DataTemp[0]);
                            k = Integer.parseInt(DataTemp[1]);
                        }
                    }
                } else {
                    System.out.print(ConsoleColors.GREEN + " Unique Row " + ConsoleColors.RESET);
                }
                System.out.print(System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
            System.out.println("Overall Row Duplicate & Aliases DupsList.size()=" + DupsRowList.size() + " OverallRowDupCount=" + OverallRowDupCount + " OverallUniqueRowCount=" + OverallUniqueRowCount);
            System.out.println("Overall Column Duplicate & Aliases DupColList.size()=" + DupColList.size() + " OverallColDupCount=" + OverallColDupCount + " OverallUniqueColCount=" + OverallUniqueColCount);

        } catch (Exception HyperE) {
            log.info("Exception: printDataOutput");
            System.out.println("Exception: printDataOutput");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
    }

    //RowsTaguchi=(1 + Sigma((i=1 to NV) (Li-1)) ) 
    public static void ComputeROWSDOETaguchi() {
        int i = 0;
        int Li = 0;
        ROWSDOEComputed = 1;
        for (i = 0; i < Length; i++) {
            Li = LevelCntrlArrayGet(0, i, LevelMaxIndex);
            ROWSDOEComputed = ROWSDOEComputed + (Li - 1);
        }
        ROWSDOERecommended = ROWSDOEComputed;
        for (i = 1; i < Length; i++) {
            Li = LevelCntrlArrayGet(0, i, LevelMaxIndex);

            if ((Li > 0) && (ROWSDOERecommended % Li == 0))
                ; else if (Li > 0) {
                ROWSDOERecommended++;
                i = 0;
            }
        }
    }

    public static void StrengthLevel(int planeNum, int Levels) {
        int c = 0, DataCount = 0;
        int NumCOLMDOE = (int) Length;
        int NumROWSDOE = (int) ROWSDOE;
        System.out.println("Strength(Number/Count) of Levels Summary:");
        System.out.print("Column=");
        for (int r = 0; r < NumCOLMDOE; r++) {
            System.out.print(r + " ");
        }
        System.out.print(System.lineSeparator());
        StrengthMode = new int[(int) Length][Levels + 1];
        for (c = 0, DataCount = 0; ((c < NumCOLMDOE)); c++, DataCount = 0) {
            int Li = LevelCntrlArrayGet(0, c, LevelMaxIndex);
            StrengthMode[c] = new int[Levels + 1];
            for (int r = 0; (r < NumROWSDOE); r++) {
                int CellVal = DataArrayGet(planeNum, r, c);
                if ((CellVal >= 0) && (CellVal < StrengthMode[c].length)) {
                    StrengthMode[c][CellVal]++;
                }
            }
        }
        //Printing StrengthMode
        int r1 = 1;
        for (r1 = 1; r1 < (Levels + 1); r1++) {
            System.out.print("Strength(Number/Count) of Level=" + r1);
            for (c = 0; c < NumCOLMDOE; c++) {
                System.out.print(" " + StrengthMode[c][r1]);
                DataCountSum[c] += StrengthMode[c][r1];
            }
            System.out.print(System.lineSeparator());
        }
    }

    public static void DataCountAnalysis(int planeNum) {
        int c = 0, DataCount = 0;
        int NumCOLMDOE = (int) Length;
        int NumROWSDOE = (int) ROWSDOE;
        int FullFactorialCnt = 0;
        int FullFactUptoCol = 0;
        int FullFactIndivCol = 0;
        boolean FullFactFlag = false;
        System.out.print("DataCountSum=");
        for (c = 0; c < NumCOLMDOE; c++) {
            System.out.print(" " + DataCountSum[c]);
        }
        System.out.print(System.lineSeparator());
        System.out.print("DataCount(Sum of Cells of DOE Matrix)=");
        int r = 0;
        for (c = 0, DataCount = 0; c < NumCOLMDOE; c++) {
            DataCount = 0;
            for (r = 0; r < NumROWSDOE; r++) {
                DataCount += DataArrayGet(planeNum, r, c);
            }
            DataCountSumActual[c] = DataCount;
            System.out.print(DataCount + " ");
        }
        for (c = 0, DataCount = 0; c < NumCOLMDOE; c++) {
            DataCount = 0;
            for (r = 0; r < NumROWSDOE; r++) {
                DataCount += DataArrayGet(planeNum, r, c);
            }
            if (c == 0) {
                FullFactorialCnt = DataCount;
                FullFactFlag = true;
                FullFactUptoCol++;
            } else if ((DataCount != FullFactorialCnt) && (FullFactFlag == true)) {
                FullFactFlag = false;
            } else if (FullFactFlag == true) {
                FullFactUptoCol++;
                FullFactFlag = true;
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println("Matrix is Full Factorial upto FullFactUptoCol=" + (FullFactUptoCol) + ".");
        System.out.println(ConsoleColors.RED + "Columns " + (FullFactUptoCol) + " upto " + (Length - 1)
                + " Should be Full Factored Manually." + ConsoleColors.RESET);
        System.out.print("ColFullFactorial(Theoretical DataCount)=");
        for (c = 0, DataCount = 0, FullFactIndivCol = 0; c < NumCOLMDOE; c++, DataCount = 0) {
            int ColFullFactorial = 0;
            for (int i = 1; i <= LevelCntrlArrayGet(0, c, LevelMaxIndex); i++) {
                ColFullFactorial += i * ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex);
                ColFactorialMode[c][i] = (int) ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex);
            }
            if (ColFullFactorial > 0) {
                System.out.printf("%d ", ColFullFactorial);
            }
        }
        System.out.print(System.lineSeparator());
        System.out.print("Full Factorial % =");
        for (c = 0, DataCount = 0, FullFactIndivCol = 0; c < NumCOLMDOE; c++, DataCount = 0) {
            int ColFullFactorial = 0;
            for (int i = 1; i <= LevelCntrlArrayGet(0, c, LevelMaxIndex); i++) {
                ColFullFactorial += i * ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex);
            }
            for (r = 0; r < NumROWSDOE; r++) {
                DataCount += DataArrayGet(planeNum, r, c);
            }
            if (ColFullFactorial == DataCount) {
                FullFactIndivCol++;
            }
            if (ColFullFactorial > 0) {
                System.out.printf("%3.2f ", ((double) DataCount / (double) ColFullFactorial * 100.00));
            }
        }
        System.out.print(System.lineSeparator());
        if (c > 0) {
            System.out.printf("Matrix High Level Full Factorial(Continuous)=%3.2f%%. ( %d Columns out of %d Columns)", (double) (FullFactUptoCol + 1) / (double) Length * 100.00, (FullFactUptoCol + 1), (Length));
        } else {
            System.out.printf("Matrix High Level Full Factorial=%3.2f%%.", 0.00);
        }
        System.out.print(System.lineSeparator());
        System.out.print("Matrix High Level Full Factorial :Individual Column Count=" + FullFactIndivCol + " out of " + Length);
        if (Length > 0) {
            System.out.printf("(%3.2f%%) ", ((double) FullFactIndivCol / (double) Length * 100.00));
        } else {
            System.out.printf("(%3.2f%%) ", 0.00);
        }
    }

    public static void DuplicateRemover() {
        boolean NoColDupFlag = false;
        int i = 0;
        int iLow = -1;
        int iHigh = -1;
        int ColFullFactorial = 0;
        for (int c = 0; c < Length; c++, NoColDupFlag = false, iLow = -1, iHigh = -1) {
            ColFullFactorial = 0;
            for (i = 1; i <= LevelCntrlArrayGet(0, c, LevelMaxIndex); i++) {
                ColFullFactorial += i * ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex);
                if ((StrengthMode[c][i] == ColFactorialMode[c][i])
                        && (ColFactorialMode[c][i] == (ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex)))) {
                    NoColDupFlag = true;
                } else {
                    NoColDupFlag = false;
                    if (iLow == -1) {
                        iLow = i;
                    } else if (iHigh == -1) {
                        iHigh = i;
                    }
                }
            }
            if ((ColFullFactorial != DataCountSumActual[c])) {
                NoColDupFlag = false;
            }
            if ((NoColDupFlag == false) && (c > 0)
                    && (((iLow >= 1) && ((ColFactorialMode[c][iLow] - StrengthMode[c][iLow]) != 0))
                    || (ColFullFactorial != DataCountSumActual[c]))) {
                //System.out.println("DuplicateRemover:Entered Corrector Code: c=" + c + " iLow=" + iLow + " iHigh=" + iHigh);
                CorrectDups(c, iLow, iHigh, (ColFactorialMode[c][iLow] - (int) ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex)));
                DupsRowList.clear();
                OverallRowDupCount = 0;
                DupRowCheckMatrixMode(0, (int) Length);
                for (int x = 0; x < Length; x++) {
                    for (int y = 1; y <= LevelCntrlArrayGet(0, x, LevelMaxIndex); y++) {
                        ColFactorialMode[x][y] = (int) ROWSDOE / LevelCntrlArrayGet(0, x, LevelMaxIndex);
                    }
                    int DataCount = 0;
                    for (int r = 0; r < ROWSDOE; r++) {
                        DataCount += DataArrayGet(0, r, c);
                    }
                    DataCountSumActual[c] = DataCount;
                }
                if (DupsRowList.size() == 0) {
                    OverallRowDupCount = 0;
                    //break;
                }
            } else {
                //System.out.println("DuplicateRemover:No Change Code");
            }
        }
    }

    public static void CorrectDups(int c, int iLow, int iHigh, int NoOfDups) {
        int i = -1, j = 0;
        String DupString = "";
        String[] DataTemp;
        int ValueLow = -1;
        if (DupsRowList.size() > 0) {
            DupString = DupsRowList.getFirst();
            DataTemp = DupString.split("@");
            if (DataTemp.length == 2) {
                i = Integer.parseInt(DataTemp[0]);
            }
        }
        //System.out.println("CorrectDups:Unpacked:First Row="+i);
        if ((i != -1) && (DupsRowList.size() > 0)) {
            for (int r = i; r < ROWSDOE; r++, ValueLow = -1) {
                if (r == i) {
                    //System.out.println("CorrectDups:Correcting:Row=" + r);
                    if (ValueLow == -1) {
                        ValueLow = DataArrayGet(0, r, c);
                    }
                    if (ValueLow == iLow) {
                        DataArrayPut(0, r, c, (byte) iHigh);
                        //System.out.println("CorrectDups:Swapped iLow:iLow=" + iLow + " ValueLow=" + ValueLow + " iHigh=" + iHigh);
                        ValueLow = -1;
                    } else if (ValueLow == iHigh) {
                        DataArrayPut(0, r, c, (byte) iLow);
                        //System.out.println("CorrectDups:Swapped iHigh:iLow=" + iLow + " ValueLow=" + ValueLow + " iHigh=" + iHigh);
                        ValueLow = -1;
                    }
                    
                    j++;
                    if (j < DupsRowList.size()) {
                        DupString = DupsRowList.get(j);
                        DataTemp = DupString.split("@");
                        if (DataTemp.length == 2) {
                            i = Integer.parseInt(DataTemp[0]);
                        }
                    }
                }
            }
        }
    }

    public static void DupsRemoverLocalMinima() {
        boolean NoColDupFlag = false;
        boolean Col0Col1IsFullFact = false;
        int i = 0;
        int ColFullFactorial = 0;
        int c = 0;
        for (c = 0; c < 2; c++, NoColDupFlag = false) {
            for (i = 1; i <= LevelCntrlArrayGet(0, c, LevelMaxIndex); i++) {
                if ((StrengthMode[c][i] == ColFactorialMode[c][i])
                        && (ColFactorialMode[c][i] == (ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex)))) {
                    Col0Col1IsFullFact = true;
                } else {
                    Col0Col1IsFullFact = false;
                }
            }
        }
        if (c == 2) {
            Col0Col1IsFullFact = true;
        } else {
            Col0Col1IsFullFact = false;
        }
        NoColDupFlag = false;
        for (c = 2; c < Length; c++, NoColDupFlag = false) {
            ColFullFactorial = 0;
            for (i = 1; i <= LevelCntrlArrayGet(0, c, LevelMaxIndex); i++) {
                ColFullFactorial += i * ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex);
                if ((StrengthMode[c][i] == ColFactorialMode[c][i])
                        && (ColFactorialMode[c][i] == (ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex)))) {
                    NoColDupFlag = true;
                } else {
                    NoColDupFlag = false;

                }
            }
            if ((ColFullFactorial != DataCountSumActual[c])) {
                NoColDupFlag = false;
            }
            if (((NoColDupFlag == false) || (DupsRowList.size() > 0)) && (c > 0)) {
                //System.out.println("DupsRemoverLocalMinima:Entered Corrector Code: c=" + c );
                CorrectDupsLocalMinima(c, Col0Col1IsFullFact);
                DupsRowList.clear();
                OverallRowDupCount = 0;
                DupRowCheckMatrixMode(0, (int) Length);
                for (int x = 0; x < Length; x++) {
                    for (int y = 1; y <= LevelCntrlArrayGet(0, x, LevelMaxIndex); y++) {
                        ColFactorialMode[x][y] = (int) ROWSDOE / LevelCntrlArrayGet(0, x, LevelMaxIndex);
                    }
                    int DataCount = 0;
                    for (int r = 0; r < ROWSDOE; r++) {
                        DataCount += DataArrayGet(0, r, c);
                    }
                    DataCountSumActual[c] = DataCount;
                }
                if (DupsRowList.size() == 0) {
                    OverallRowDupCount = 0;
                }
            } else {
                //System.out.println("DuplicateRemover:No Change Code");
            }
        }
    }

    public static void CorrectDupsLocalMinima(int c, boolean Col0Col1IsFullFact) {
        int i = -1, j = 0;
        String DupString = "";
        String[] DataTemp;
        int iLow = -1;
        int iHigh = -1;
        int iHighCol1 = -1;
        //if (Col0Col1IsFullFact==false) return;
        if (DupsRowList.size() > 0) {
            DupString = DupsRowList.getFirst();
            DataTemp = DupString.split("@");
            if (DataTemp.length == 2) {
                i = Integer.parseInt(DataTemp[0]);
            }
        }
        //System.out.println("CorrectDups:Unpacked:First Row="+i);
        if ((i != -1) && (DupsRowList.size() > 0)) {
            for (int r = i; r < ROWSDOE; r++, iLow = -1, iHigh = -1) {
                if (r == i) {

                    if (iLow == -1) {
                        iLow = DataArrayGet(0, r, c);
                    }
                    if (iHighCol1 == -1) {
                        iHighCol1 = DataArrayGet(0, r, 1);
                    }
                    if (iHigh == -1) {
                        iHigh = DataArrayGet(0, r, c + 1);
                    }
                    //System.out.println("CorrectDupsLocalMinima:Correcting:Row=" + r + " iLow=" + iLow + " iHigh=" + iHigh);
                    if ((iLow != iHigh) && (iLow != iHighCol1)) {
                        //Copy Col c+1 to Col c
                        DataArrayPut(0, r, c, (byte) iHigh);
                        //System.out.println("CorrectDupsLocalMinima:" + c + ":Swapped iLow:Copy C+1:iLow=" + iLow + " iHigh=" + iHigh);
                    } else if ((iLow != iHigh) && (iLow == iHighCol1)) {
                        //Copy Col 1 to Col c
                        DataArrayPut(0, r, c, (byte) iHighCol1);
                        //System.out.println("CorrectDupsLocalMinima:" + c + ":Swapped iLow:Copy 2:iLow=" + iLow + " iHigh=" + iHigh);
                    } else if ((iLow == iHigh) && ((iHigh + 1) < LevelCntrlArrayGet(0, c, LevelMaxIndex))) {
                        //Swap Col 2 Value with MaxLevel
                        DataArrayPut(0, r, c, (byte) (iHigh + 1));
                        //System.out.println("CorrectDups:Swapped iHigh:" + c + ":MaxLevel:iLow=" + iLow + " ValueLow=" + (iLow+1) + " iHigh=" + iHigh);
                    } else if ((iLow == iHigh) && ((iHigh + 1) > LevelCntrlArrayGet(0, c, LevelMaxIndex))) {
                        //Swap Col 2 Value with MinLevel
                        DataArrayPut(0, r, c, (byte) SimpleModuloLevel(0, "CorrectDupsLocalMinima", r, c,
                                iHigh,
                                LevelCntrlArrayGet(0, c, LevelMaxIndex), 1));
                        //System.out.println("CorrectDups:Swapped iHigh:" + c + ":MinLevel:iLow=" + iLow + " ValueLow=" + LevelCntrlArrayGet(0, c, LevelMinIndex) + " iHigh=" + iHigh);
                    }
                    j++;
                    if (j < DupsRowList.size()) {
                        DupString = DupsRowList.get(j);
                        DataTemp = DupString.split("@");
                        if (DataTemp.length == 2) {
                            i = Integer.parseInt(DataTemp[0]);
                        }
                    }
                }
            }
        }
    }
public static void AliasRemover() {
        boolean NoColAliasFlag = false;
        int i = 0;
        int k = 0;
        int c = 0;
        int times = 0;
        int iLow = -1;
        int iHigh = -1;
        int ColFullFactorial = 0;
        int Index=0;
        String DupString = "";
        String[] DataTemp;
        if (DupColList.size() > 0) {
            Index=DupColList.size()-1;
            DupString = DupColList.get(Index);
            DataTemp = DupString.split("@");
            if (DataTemp.length == 2) {
                i = Integer.parseInt(DataTemp[0]);
                k = Integer.parseInt(DataTemp[1]);
            }
            Index--;
        }
        for(;(DupColList.size() > 0)&&(times < 5);  ){
            CorrectAlias(i);
            if (Index >= 0) {
                DupString = DupColList.get(Index);
                DataTemp = DupString.split("@");
                if (DataTemp.length == 2) {
                    i = Integer.parseInt(DataTemp[0]);
                    k = Integer.parseInt(DataTemp[1]);
                }
                Index--;
            }else {
                DupColList.clear();
                OverallColDupCount = 0;
                DupColCheckMatrixMode(0, (int) Length);
                if (DupColList.size() == 0) {
                    OverallColDupCount = 0;
                    break;
                }else {
                    Index=DupColList.size()-1;
                    times++;
                }
            }
        }
    }
    public static void AliasRemover1() {
        boolean NoColAliasFlag = false;
        int i = 0;
        int k = 0;
        int c = 0;
        int times = 0;
        int iLow = -1;
        int iHigh = -1;
        int ColFullFactorial = 0;
        String DupString = "";
        String[] DataTemp;
        if (DupColList.size() > 0) {
            DupString = DupColList.getFirst();
            DataTemp = DupString.split("@");
            if (DataTemp.length == 2) {
                i = Integer.parseInt(DataTemp[0]);
                k = Integer.parseInt(DataTemp[1]);
            }
        }
        int oldI = i;
        int oldK = k;
        int Maxtry = 0;
        boolean Flag = false;
        while ((DupColList.size() > 0) && (Maxtry < 5) && (times < 5)) {

            while ((((oldI == i) || (oldK == k)) && (times < 5)) || ((oldI != i) && (oldK != k))) {
                if (((oldI != i) && (oldK != k))) {
                    oldI = i;
                    oldK = k;
                    System.out.println("AliasColRemover:Inner While:1:Same Alignment: times=" + times + " Maxtry=" + Maxtry + " i=" + i + " k=" + k);
                    CorrectAlias(i);
                    Flag = true;
                } else if ((oldI == i) && (oldK == k) && (Flag == false)) {
                    System.out.println("AliasColRemover:Inner While:2:Same Alignment: times=" + times + " Maxtry=" + Maxtry + " i=" + i + " k=" + k);
                    CorrectAlias(i);
                    Flag = true;
                } else {
                    System.out.println("AliasColRemover:Inner While:Opposite Alignment: times=" + times + " Maxtry=" + Maxtry + " i=" + i + " k=" + k);
                    CorrectAlias(k);
                }
                DupColList.clear();
                OverallColDupCount = 0;
                DupColCheckMatrixMode(0, (int) Length);
                if (DupColList.size() == 0) {
                    OverallColDupCount = 0;
                } else if (DupColList.size() > 0) {
                    DupString = DupColList.getFirst();
                    DataTemp = DupString.split("@");
                    if (DataTemp.length == 2) {
                        i = Integer.parseInt(DataTemp[0]);
                        k = Integer.parseInt(DataTemp[1]);

                    }
                }
                times++;
                System.out.println("AliasColRemover:Inner While:Infinite Loop: times=" + times + " Maxtry=" + Maxtry + " i=" + i + " oldI=" + oldI + " k=" + k + " oldK=" + oldK + " Flag=" + Flag);
                if (((oldI == i) || (oldK == k)) && (times > 5)) {
                    times = 0;
                    break;
                }
            }
            DupColList.clear();
            OverallColDupCount = 0;
            times = 1;
            DupColCheckMatrixMode(0, (int) Length);
            if (DupColList.size() > 0) {
                DupString = DupColList.getFirst();
                DataTemp = DupString.split("@");
                if (DataTemp.length == 2) {
                    i = Integer.parseInt(DataTemp[0]);
                    k = Integer.parseInt(DataTemp[1]);
                    if ((oldI == i) || (oldK == k)) {
                        oldI = -1;
                        oldK = -1;
                        Flag = false;
                    }
                }
            }
            if ((Maxtry == 4)) {
                break;
            } else if (times > 0) {
                {
                    Maxtry++;
                }
            }
            System.out.println("AliasColRemover:Outer While:Infinite Loop: times=" + times + " Maxtry=" + Maxtry + " i=" + i + " oldI=" + oldI + " k=" + k + " oldK=" + oldK + " Flag=" + Flag);
        }
    }

    public static void CorrectAlias(int c) {
        int i = -1, j = 0, k = 0;
        String DupString = "";
        String[] DataTemp;
        int ValueLow = -1;
        int iLow = -1;
        int iHigh = -1;
        if (DupColList.size() > 0) {
            DupString = DupColList.getFirst();
            DataTemp = DupString.split("@");
            if (DataTemp.length == 2) {
                i = Integer.parseInt(DataTemp[0]);
                k = Integer.parseInt(DataTemp[1]);
            }
        }
        //System.out.println("CorrectAlias:Unpacked:First Col:c=" + c + " i=" + i + " k=" + k);
        if ((i != -1) && (DupColList.size() > 0)) {
            for (int r = ((int) ROWSDOE - ((int) ROWSDOE / LevelCntrlArrayGet(0, c, LevelMaxIndex))); r < ROWSDOE; r++, ValueLow = -1, iLow = -1, iHigh = -1) {
                if (ValueLow == -1) {
                    ValueLow = DataArrayGet(0, r, c);
                }
                if (iLow == -1) {
                    iLow = DataArrayGet(0, r, i);
                }
                if (iHigh == -1) {
                    iHigh = DataArrayGet(0, r, k);
                }
                if ((ValueLow == iLow) && (ValueLow == iHigh)) {
                    DataArrayPut(0, r, c, (byte) SimpleModuloLevel(0, "CorrectAlias", r, c,
                            ValueLow,
                            LevelCntrlArrayGet(0, c, LevelMaxIndex), 1));
                    //System.out.println("CorrectAlias:ExactCopy:Col=" + c + " i=" + i + " k=" + k + " Swapped Row=" + r + " iLow:iLow=" + iLow + " ValueLow=" + ValueLow + " New ValueLow=" + DataArrayGet(0, r, c));
                    ValueLow = -1;
                } else if ((ValueLow == iLow)) {
                    DataArrayPut(0, r, i, (byte) SimpleModuloLevel(0, "CorrectAlias", r, i,
                            ValueLow,
                            LevelCntrlArrayGet(0, i, LevelMaxIndex), 2));
                    //System.out.println("CorrectAlias:Multiple:Col=" + c + " i=" + i + " k=" + k + " Swapped Row=" + r + " iLow:iLow=" + iLow + " iHigh=" + iHigh + " ValueLow=" + ValueLow + " New ValueLow=" + DataArrayGet(0, r, c));
                    ValueLow = -1;
                }
                j++;
                if (j < DupColList.size()) {
                    DupString = DupColList.get(j);
                    DataTemp = DupString.split("@");
                    if (DataTemp.length == 2) {
                        i = Integer.parseInt(DataTemp[0]);
                        k = Integer.parseInt(DataTemp[1]);
                    }
                }
            }
            for (int r = 0; r < LevelCntrlArrayGet(0, c, LevelMaxIndex); r++, ValueLow = -1, iLow = -1, iHigh = -1) {
                if (ValueLow == -1) {
                    ValueLow = DataArrayGet(0, r, c);
                }
                if (iLow == -1) {
                    iLow = DataArrayGet(0, r, i);
                }
                if (iHigh == -1) {
                    iHigh = DataArrayGet(0, r, k);
                }
                if ((ValueLow == iLow) && (ValueLow == iHigh)) {
                    DataArrayPut(0, r, c, (byte) SimpleModuloLevel(0, "CorrectAlias", r, c,
                            ValueLow,
                            LevelCntrlArrayGet(0, c, LevelMaxIndex), 1));
                    //System.out.println("CorrectAlias:ExactCopy:Col=" + c + " i=" + i + " k=" + k + " Swapped Row=" + r + " iLow:iLow=" + iLow + " ValueLow=" + ValueLow + " New ValueLow=" + DataArrayGet(0, r, c));
                    ValueLow = -1;
                } else if ((ValueLow == iLow)) {
                    DataArrayPut(0, r, i, (byte) SimpleModuloLevel(0, "CorrectAlias", r, i,
                            ValueLow,
                            LevelCntrlArrayGet(0, i, LevelMaxIndex), 2));
                    //System.out.println("CorrectAlias:Multiple:Col=" + c + " i=" + i + " k=" + k + " Swapped Row=" + r + " iLow:iLow=" + iLow + " iHigh=" + iHigh + " ValueLow=" + ValueLow + " New ValueLow=" + DataArrayGet(0, r, c));
                    ValueLow = -1;
                }
                j++;
                if (j < DupColList.size()) {
                    DupString = DupColList.get(j);
                    DataTemp = DupString.split("@");
                    if (DataTemp.length == 2) {
                        i = Integer.parseInt(DataTemp[0]);
                        k = Integer.parseInt(DataTemp[1]);
                    }
                }
            }
        }
    }

    public static void MultiplicationMatrixX2(int row, int column) {
        int[][] Product = null;
        String[] Names = new String[column];
        Product = new int[row][column];
        int[] X = new int[row];
        int[] Y = new int[row];
        if ((row > 0) && (column > 0)) {
            Names = new String[column];
            Product = new int[row][column];
            X = new int[row];
            Y = new int[row];
            for (int i = 0; i < Length - 1; i++) {
                //Form Name of 2 way interaction
                Names[i] = " X" + i + ".Y" + (i + 1);
                for (int p = 0; p < ROWSDOE; p++) {
                    Product[p][i] = DataArrayGet(0, p, i) * DataArrayGet(0, p, (i + 1));
                }
            }
            //Last Column Computation of 2 way interaction
            int n = (int) Length - 1;
            //Form Name of 2 way interaction
            Names[n] = " X" + n + ".Y" + (0);
            for (int p = 0; p < ROWSDOE; p++) {
                Product[p][n] = DataArrayGet(0, p, n) * DataArrayGet(0, p, 0);
            }
            System.out.println("Hadamard Product(2-way Interaction-X^2(Arithmetic XY i.e X[i].Y[i]))=");
            System.out.print("Column Interaction Names=");
            for (int i = 0; i < Length; i++) {
                System.out.print(Names[i]);
            }
            System.out.print(System.lineSeparator());
            for (int r = 0; r < Product.length; r++) {
                System.out.print("Row=" + r + " ");
                for (int c = 0; c < Product[0].length; c++) {
                    System.out.print(Product[r][c] + " ");
                }
                System.out.print(System.lineSeparator());
            }
        }
    }

    //Code taken from:http://professorjava.weebly.com/matrix-determinant
    //Matrix Determinant Calculator Begins
    public static Double determinant(double[][] matrix) {
        Double sum = 0.0;
        int a = 0;
        int b = 0;
        int s;
        if (matrix.length == 1) {
            return (matrix[0][0]);
        }
        for (int i = 0; i < matrix.length; i++) {
            double[][] smaller = new double[matrix.length - 1][matrix.length - 1];
            for (a = 1; a < matrix.length; a++) {
                for (b = 0; b < matrix.length; b++) {
                    if (b < i) {
                        smaller[a - 1][b] = matrix[a][b];
                    } else if (b > i) {
                        smaller[a - 1][b - 1] = matrix[a][b];
                    }
                }
            }
            if ((i % 2) == 0) {
                s = 1;
            } else {
                s = -1;
            }
            sum += s * matrix[0][i] * (determinant(smaller));
        }
        return (sum);
    }
    //Code taken from:http://professorjava.weebly.com/matrix-determinant
    //Matrix Determinant Calculator Ends

    //pseudoCode taken from:Wiki Gaussian elimination
    //Matrix Determinant Calculator Begins
    //argmax code taken from introcs.cs.princeton
    public static double[][] determinantWikiU(double[][] matrix) {
        double sum = 0;
        int h = 0;/* Initialization of pivot row */
        int k = 0;/* Initialization of pivot column */
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        int i = 0;
        int i_max = 0;
        double f = 0.0;

        while ((h <= m) && (k <= n)) {
            /* find the k-th pivot */
            i_max = arg_max(h, m, k, matrix);

            if (matrix[i_max][k] == 0) {
                /* No pivot in this column. pass to next column */
                k = k + 1;
            } else {
                matrix = swap_rows(h, i_max, matrix);
                /* Do for all rows below pivot */
                for (i = h + 1; i <= m; i++) {
                    if (matrix[h][k] != 0.0) {
                        f = matrix[i][k] / matrix[h][k];
                    } else {
                        f = 0.0;
                    }
                    /* Fill with zeros the lower part of the pivot column*/
                    matrix[i][k] = 0;
                    /* Do for all the remaining elements in the current row */
                    for (int j = k + 1; j <= n; j++) {
                        //System.out.println("determinantWiki:i=" + i + " j=" + j + " h=" + h);
                        matrix[i][j] = matrix[i][j] - matrix[h][j] * f;
                    }
                }
                /* Increase the pivot row and column */
                h++;
            }
        }
        return (matrix);
    }

    public static double[][] swap_rows(int h, int i_max, double[][] matrix) {
        double[] hrow = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            hrow[i] = matrix[h][i];
        }
        for (int j = 0; j < matrix.length; j++) {
            matrix[h][j] = matrix[i_max][j];
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[i_max][i] = hrow[i];
        }

        return matrix;
    }

    public static int arg_max(int h, int m, int k, double[][] matrix) {
        int i_maxTemp = h;
        for (int c = h; c <= m; c++) {
            if (Math.abs(matrix[c][k]) > Math.abs(matrix[i_maxTemp][k])) {
                i_maxTemp = c;
            }
        }
        return i_maxTemp;
    }

    public static double[][] determinantWikiL(double[][] matrix) {
        double sum = 0;
        int h = matrix.length - 1;/* Initialization of pivot row */
        int k = matrix[0].length - 1;/* Initialization of pivot column */
        int m = 0;
        int n = 0;
        int i = 0;
        int i_max = matrix.length - 1;
        double f = 0.0;

        while ((h >= m) && (k >= n)) {
            /* find the k-th pivot */
            i_max = arg_maxL(h, m, k, matrix);

            if (matrix[i_max][k] == 0) {
                /* No pivot in this column. pass to next column */
                k = k - 1;
            } else {
                matrix = swap_rows(h, i_max, matrix);
                /* Do for all rows below pivot */
                for (i = h - 1; i >= m; i--) {
                    if (matrix[h][k] != 0.0) {
                        f = matrix[i][k] / matrix[h][k];
                    } else {
                        f = 0.0;
                    }
                    /* Fill with zeros the lower part of the pivot column*/
                    matrix[i][k] = 0;
                    /* Do for all the remaining elements in the current row */
                    for (int j = k - 1; j >= n; j--) {
                        //System.out.println("determinantWiki:i=" + i + " j=" + j + " h=" + h);
                        matrix[i][j] = matrix[i][j] - matrix[h][j] * f;
                    }
                }
                /* Increase the pivot row and column */
                h--;
            }
        }
        return (matrix);
    }

    public static int arg_maxL(int h, int m, int k, double[][] matrix) {
        int i_maxTemp = h;
        for (int c = h; c >= m; c--) {
            if (Math.abs(matrix[c][k]) > Math.abs(matrix[i_maxTemp][k])) {
                i_maxTemp = c;
            }
        }
        return i_maxTemp;
    }
    //argmax code taken from introcs.cs.princeton
    //pseudoCode taken from:Wiki Gaussian elimination
    //Matrix Determinant Calculator Ends

    public static void pmain(String... args) {
        log.info("HyperDOE: main Entered");
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Started Time:" + current + " ");
        }
        String Path = "data\\";
        String msg = "";
        try {
            FileInputStream fpinput = null;
            if ((fpinput = new FileInputStream(Path + ConfigFileName)) == null) {
                msg = "Input: can't open " + Path + ConfigFileName;
                System.out.println(msg);
                log.error(msg);
            }
            log.error("Opened File Name: Infile=" + Path + ConfigFileName);
            System.out.println("Opened File Name: Infile=" + Path + ConfigFileName);
            DOEMatrixGen.HDOEDefine.Myfscanf(fpinput);
            DOEMatrixGen.HDOEDefine.Myprintf();
            setArrayLevelCntrlArray(0);
            setArrayDataArray(0);
            MeanColModeTracker = new String[(int) Length];
            ColMode = new int[(int) Length];// Needs to be improved aqs this is a wastage of space
            ColModeValue = new int[(int) Length];// Needs to be improved aqs this is a wastage of space
            for (int i = 0; i < Length; i++) {
                MeanColModeTracker[i] = new String("");
                ColMode[i] = 0;
                ColModeValue[i] = 0;
             }
            DupsRowList = new LinkedList<String>();
            DupColList = new LinkedList<String>();
            DataCountSum = new int[(int) Length];
            DataCountSumActual = new int[(int) Length];
            ColFactorialMode = new int[(int) Length][(int) MaxLevel + 1];
            MySimple = new RandomSimpleLCG(0, NoOfSlots, ModulusEncrypt, Xn_LCG, a, c, b, MaxExponent);
            MyFactorial = new RandomFactorialLCGDyDx(0, NoOfSlots, ModulusEncrypt, Xn_LCG, a, c, b, MaxExponent);
            GenerateDOELatin(0, ROWSDOE, Length);
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            printDataOutput(0);
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            if ((DupsRowList.size() == 0) && (DupColList.size() == 0)) {
                System.out.println("DupAliasCheckMatrixMode:No Row Duplicates & Aliases for Whole Array ");
                System.out.println("DupAliasCheckMatrixMode:No Col Duplicates & Aliases for Whole Array ");
                System.out.println("Final Completed DOE Matrix:");
                System.out.print(System.lineSeparator());
                printDataOutput(0);
                System.out.print(System.lineSeparator());
                StrengthLevel(0, (int) MaxLevel);
                DataCountAnalysis(0);
                System.out.print(System.lineSeparator());
                ComputeROWSDOETaguchi();
                System.out.println("main:ROWSDOEComputed=" + ROWSDOEComputed + " ROWSDOE=" + ROWSDOE + " ROWSDOERecommended=" + ROWSDOERecommended);
            } else if ((DupsRowList.size() > 0) || (DupColList.size() > 0)) {
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " ");
                }
                StrengthLevel(0, (int) MaxLevel);
                DataCountAnalysis(0);
                System.out.print(System.lineSeparator());
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " DuplicateRemover Invoked : Dups=" + DupsRowList.size());
                }
                if (DupsRowList.size() > 0) 
                    DuplicateRemover();
                System.out.print(System.lineSeparator());
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " DuplicateRemover Completed : Dups=" + DupsRowList.size());
                }
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " AliasRemover Invoked : Aliases=" + DupColList.size());
                }
                if (DupColList.size() > 0) {
                    AliasRemover();
                }
                System.out.print(System.lineSeparator());
                {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " AliasRemover Completed : Aliases=" + DupColList.size());
                }
                
                if (DupsRowList.size() > 0) {
                    LocalDateTime current = LocalDateTime.now();
                    System.out.println("Time:" + current + " DupsRemoverLocalMinima Invoked: Dups=" + DupsRowList.size());
                    DupsRemoverLocalMinima();
                }
                System.out.println("Final Completed DOE Matrix After Dups Removed:");
                System.out.print(System.lineSeparator());
                printDataOutput(0);
                System.out.print(System.lineSeparator());
                StrengthLevel(0, (int) MaxLevel);
                DataCountAnalysis(0);
                System.out.print(System.lineSeparator());
                ComputeROWSDOETaguchi();
                System.out.println("main:ROWSDOEComputed=" + ROWSDOEComputed + " ROWSDOE=" + ROWSDOE + " ROWSDOERecommended=" + ROWSDOERecommended);
            }
            Scanner scanner = new Scanner(System.in);
            String Temp = "";
            if (Regression == false) {
                System.out.print("Do you want to calculate the Hadamard Product? [y]:");
                Temp = scanner.nextLine();
            }
            if ((Regression == false)
                    && ((Temp.equalsIgnoreCase("y"))
                    || (Temp.equalsIgnoreCase(""))
                    || (Temp.equalsIgnoreCase("Yes")))) {
                MultiplicationMatrixX2((int) ROWSDOE, (int) Length);
            }
            if (Regression == false) {
                System.out.print("Do you want to calculate the Determinant? [y]:");
                Temp = scanner.nextLine();
            }
            if ((Regression == false)
                    && ((Temp.equalsIgnoreCase("y"))
                    || (Temp.equalsIgnoreCase(""))
                    || (Temp.equalsIgnoreCase("Yes")))) {
                int MatrixDetlength = 0;
                if (ROWSDOE > Length) {
                    MatrixDetlength = (int) Length;
                } else {
                    MatrixDetlength = (int) ROWSDOE;
                }
                if (ROWSDOE != Length) {
                    System.out.println("main:Determinant:Truncating Matrix to MatrixDetlength=" + MatrixDetlength);
                }
                double[][] Matrix = new double[(int) MatrixDetlength][(int) MatrixDetlength];
                double[][] Matrix1U = null;
                for (int i = 0; i < MatrixDetlength; i++) {
                    for (int j = 0; j < MatrixDetlength; j++) {
                        Matrix[i][j] = (double) DataArrayGet(0, i, j);
                        //Matrix1L[i][j] = DataArrayGet(0, i, j);
                        //Matrix1U[i][j] = DataArrayGet(0, i, j);
                    }
                }
                double HadamardInequality = Math.pow(MatrixDetlength, (MatrixDetlength / 2.0));
                if (MatrixDetlength < 11) {
                    System.out.println("main:Determinant:CoFactor Expansion Method:Length < 11:Estimated Time=" + 8 + " seconds.");
                    System.out.println("main:Determinant:CoFactor Expansion Method:Length < 11: Computed=" + determinant(Matrix) + " HadamardInequality (Det(A) < +-n^n/2)=" + HadamardInequality);
                } else {
                    double TempFact = Fact((double) MatrixDetlength, 1.0);
                    System.out.println("main:Determinant:CoFactor Expansion Method:Length > 11:Estimated Time=" + TempFact + " seconds.");
                }
                Matrix1U = determinantWikiU(Matrix);
                Double DetMatrix1U = 1.0;
                //double DetMatrix1L = 1.0;
                System.out.println("Transposed Matrix1U:");
                String ZerosDiag = "";
                int ZeroCount = 0;
                int ZeroDiagElememt = 0;
                boolean AllZeroFlag = false;
                String AllZeros = "";
                int AllZeroCount = 0;
                for (int i = 0; i < Matrix1U.length; i++) {
                    AllZeroFlag = false;
                    for (int j = 0; j < Matrix1U.length; j++) {
                        System.out.print(" " + Matrix1U[i][j] + " ");
                        if ((Matrix1U[i][j] == 0) && (i == j) && (AllZeroFlag == false)) {
                            ZerosDiag = ZerosDiag + i + " ";
                            ZeroDiagElememt++;
                        }
                        if (i == j) {
                            DetMatrix1U *= Matrix1U[i][j];
                        }
                        if (((Matrix1U[i][j] == 0) && (AllZeroFlag == true))
                                || ((j == 0) && (AllZeroFlag == false) && (Matrix1U[i][j] == 0) && (Matrix1U[i][Matrix1U.length - 1] == 0))) {
                            AllZeroFlag = true;
                            
                        } else {
                            AllZeroFlag = false;
                        }

                    }
                    if (AllZeroFlag == true) {
                        AllZeros = AllZeros + i + " ";
                        AllZeroCount++;
                    }
                    System.out.print(System.lineSeparator());
                }
                System.out.println("Zeros in Rows(ZerosDiag):" + ZerosDiag + " ZeroDiagElememt=" + ZeroDiagElememt + " ZeroCount=" + ZeroCount);
                System.out.println("All Zeros in Rows(AllZeros):" + AllZeros);
                System.out.println("All Zeros in Rows(Count):" + AllZeroCount + " Rows.");
//                System.out.println("Transposed Matrix1U:");
//                for (int i = 0; i < Matrix1L.length; i++) {
//                    for (int j = 0; j < Matrix1L.length; j++) {
//                        //System.out.print(" " + Matrix1L[i][j] + " ");
//                        if(i==j)
//                            DetMatrix1U *=Matrix1L[i][j];
//                    }
//                    //System.out.print(System.lineSeparator());
//                }
                System.out.println("main:Determinant:LU Decomposition Method:Length > 11:Determinant=" + (DetMatrix1U) + ".");
            }
        } catch (Exception HyperDOEse) {
            log.error("Exception: main");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            HyperDOEse.printStackTrace();
        }
    }

    public static void main(String... args) {
        //Uncomment below Line for Normal UsageMatrixGen of DOEMatrixGen
        System.out.println(ConsoleColors.BLUE + " DOE Matrix Generation Start" + ConsoleColors.RESET);
        pmain(args);
        System.out.println(ConsoleColors.BLUE + " DOE Matrix Generation End" + ConsoleColors.RESET);

        //Starting Test Case :21 to 34, 39,40 , 101 to 104, 201 to 209
        String StartMyTestCase = "-1";//Test Case Number : Optiona :All or a Single Number
        String EndMyTestCase = "29";//Test Case Number : Optiona :All or a Single Number
        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        //TestHarnessDOE MyTestData = new TestHarnessDOE(StartMyTestCase, EndMyTestCase, TestCaseType);
        //Uncomment below Line for DOEMatrixGen Self Regression Test
        //mainRegresssionDOE( MyTestData);
        //System.out.println(ConsoleColors.BLUE + " DOE Matrix Generation End" + ConsoleColors.RESET);

    }
}
