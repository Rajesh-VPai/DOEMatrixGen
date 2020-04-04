/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncDiff;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncExpress;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.MyFuncSimple;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parse;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseDiff;
import static DOEMatrixGen.MathsContxtLAv0_1_Prod.parseSimple;
import static DOEMatrixGen.Usage.FormEquation;
import static DOEMatrixGen.UsageDOE.mainRegresssionDOE;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
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
    public static String ConfigFileName="DOEinputGeneralDefault";
    public static String LevelFileName="DOEColumninputLevel";
    public static int STATSMOREDATAFACTOR = 3;
    public static long ROWSDOE =1;
    public static long ROWSDOEComputed =0;
    public static long ROWSDOERecommended =0;
    // Number of Levels of each Factor (Signal Columns) in Ortho Matrix
    public static int LEVELS = 5;
    public static long Length = 1000;// Length (Number of Signal Columns of Orthogonal Matrix)
    
    //BlkFactor data Elements
    // Matrix Controlling Tweaks
    public static long NumROWSDOE;
    public static long NumCOLMDOE;
   
   // HyperDOE Data Elements
   // High Level Variables for Signal Noise Nature
    public static boolean HasSignal=true;
    public static boolean UseLevelsFromFile=false;
    public static boolean UseLevelsFromArray=false;
    
    // Generation of DOE Boolean Control
    public static boolean ClassicTaguchiAlg = false;
    public static boolean UseFullFactorial = true;
    public static boolean PrintStackTraceFlag = false;
    
    //Signal Data Elements
   private static long OverallDupCount = 0;
   private static long OverallUniqueCount = 0;
   private static LinkedList<String> DupsList;
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
    
    public static String[] MeanInputModeTracker;//SigLength :Number of Datum
    public static long[] Mode;//SigLength :Number of Datum:The Frequency of each  Value
    public static long[] ModeValue;//Value :Individual Different Datum
    public static long[] ModeIndex;//Value :Individual Different Datum

    public static int[][] StrengthMode;    
    //LCG Variables
    private static long LCG_TempValue = 1;
    private static long LCG_Value = 1;
    private static long LCG_levelCntrl = 1;
    private static long LCG_FactValue = 1;
    private static long LCG_NPRFactValue = 1;
    private static long SimpleModuloLevelXn = 1;
    
    private static ByteBuffer LevelCntrlArray;

    //Storage Tweak
    public static int SIZEOFCELL = 4;
    
    public static double[][] LevelValueStore;
    
    public static int[] LevelValueInit;//-1=LevelValuePair Not Initialized
    
    public static String[] Name;//Name of the Column(Display)
    //InitLevels Column 0 Value Need not be continuous but has to be increasing 
    // i.e 0,1,2 ,15,30,390. 
    // Just input the columns that you need. Rest are set to LEVELS
    // OR User to implement UseLevelsFromLEVELS=true/false
    private static int[][] InitLevels = {
    {0, 1, 5/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, //Column, LevelMinIndex, LevelMaxIndex, LevelCtrlIndex, LastIssuedLevel, LastIssuedValue, FactorialRow1Lev, FactSpreadRow1Lev, FactRowRow1Lev, FactRowSpreadRow1Lev, FactorialRow3Lev, FactSpreadRow3Lev, FactRowRow3Lev, FactRowSpreadRow3Lev, FactorialNMinusLev, FactSpreadNMinusLev, FactRowNMinusLev, FactRowSpreadNMinusLev, 
    {1, 1, 5/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {2, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {3, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {4, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {5, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {6, 1, 5/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {7, 1, 2, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
    {8, 1, 3/*2*/, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
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
    
    // Column Equation Details for each Input Column
    public static ColConfigration[] EqnDetails;
    public static int[] EqnParsed;
    // Random Generator Values & Variables
    private static Map<String, Double> variables = new HashMap<>();
    private static Map<String, String> variablesDiff = new HashMap<>();
//    private static String a="1.0";
//    private static String c="1.0";
//    private static String RandVar="Xn";
//    private static String DiffWithRespTo = "Xn";
//    //private static String Exprn = RandomEquation( a, c,  RandVar); 
//    private static String Exprn = FormEquation( a, c,  RandVar,7); 
    
   public static void GenerateDOELatin(int planeNum, long rows, long col) {
        try {
            long MyStartROWSDOE = ROWSDOE;
            long MyStartCOLMDOE = Length;
            long Levels = LEVELS;
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
            log.fatal("Starting Duplicate Check:OverallDupCount=" + OverallDupCount + " DupCheckColStart=" + DupCheckColStart + " DupCheckColEnd=" + DupCheckColEnd);
            DupCount = 0;
            long OverallDupCountOrg = OverallDupCount;
            if ((HasSignal == true)&&(HasNormal==true)) {
                log.fatal("GenerateDOELatin:Started Signal DupCheckMatrixMode");
                DupCheckMatrixMode(planeNum);
            }
            System.out.print(System.lineSeparator());
            
            long OverallDupCountDiff = OverallDupCount - OverallDupCountOrg;
            System.out.println("Overall Duplicate Count=" + OverallDupCountDiff + " OverallUniqueCount=" + OverallUniqueCount);
            AlgoParamLogger("Main", StartRowOrg, StartColOrg, DupCheckColStart, DupCheckColEnd, OverallDupCountOrg, MyStartROWSDOE, MyStartCOLMDOE, OverallDupCount);

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
        log.error("AlgoName="+ AlgoName + " StartRowOrg=" + StartRowOrg);
        log.error("AlgoName="+ AlgoName + " StartRowEnd=" + StartRowEnd);
        log.error("AlgoName="+ AlgoName + " StartColOrg=" + StartColOrg);
        log.error("AlgoName="+ AlgoName + " StartColEnd=" + StartColEnd);
        log.error("AlgoName="+ AlgoName + " DupCheckColStartOrg=" + DupCheckColStartOrg);
        log.error("AlgoName="+ AlgoName + " DupCheckColEndOrg=" + DupCheckColEndOrg);
        log.error("AlgoName="+ AlgoName + " OverallDupCountOrg=" + OverallDupCountOrg);
        log.error("AlgoName="+ AlgoName + " OverallDupCountEnd=" + OverallDupCountEnd);
        log.error("AlgoName="+ AlgoName + " Dup Diff Count=" + (OverallDupCountEnd - OverallDupCountOrg));
        log.error("AlgoName="+ AlgoName + " Row Count=" + (StartRowEnd - StartRowOrg));
        if (StartColEnd > StartColOrg) {
            log.error("AlgoName="+ AlgoName + " Col Count=" + (StartColEnd - StartColOrg));
        } else {
            log.error("AlgoName="+ AlgoName + " Col Count=" + (StartColOrg - StartColEnd));
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
            long Levels = LEVELS;
            double levelCntrl = 1;
            if (HasNormal == true) {
                setArrayLevelCntrlArray(planeNum);
                setArrayDataArray(planeNum);
                if(UseLevelsFromFile==true){
                 for (int ObjectColNum = 0; ObjectColNum < Length; ObjectColNum++) {
                    DOEColumnInputLevel.ProgramDefine("Signal");
                    DOEColumnInputLevel.IO(0, "NormalInput", "Specific", ObjectColNum);
                    if (LevelValueInit[ObjectColNum] == -1) {
                        DOEColumnInputLevel.IO(0, "NormalInput", "Common", ObjectColNum);
                    }
                    DOEColumnInputLevel.Myprintf(0, "NormalInput", ObjectColNum);
                }
                }
                if (Checker(planeNum, false, HasNormal, (int) Length) == true)
                    ;// Do nothing
                else {
                    log.error("GenerateArray:Signal:NormalChecker Failed:Should get an Exception");
                }

                if (NumROWSDOE > 1) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 0, LevelMinIndex);
                    ColumnZero(planeNum, 0, 0, LevelCntrlArrayGet(planeNum, 0, LevelMaxIndex));
                }

                if (NumROWSDOE > 2) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 1, LevelMinIndex);
                    ColumnOne(planeNum, 0, 1, LevelCntrlArrayGet(planeNum, 1,LevelMaxIndex));
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
                    }else if ((NumROWSDOE >= 3)&& (NumROWSDOE <= 10 * Levels)&& (NumROWSDOE > Levels)) {
                        RowLevel3Levels(planeNum, 2*Levels, 2, 0);;
                    } else {
                        log.error("GenerateArray:Signal: Skipping RowLevel3Levels");
                    }
                    log.error("GenerateArray:Signal: RowLevel3Levels Completed ");
                    System.out.println("RowLevel3Levels Completed");
                    //printDataOutput(0);
                   
                    if (((NumROWSDOE >= 3) && (NumROWSDOE >= 10 * Levels)) ) {
                        levelCntrl = LevelCntrlArrayGet(planeNum, NumCOLMDOE - 2, LevelMinIndex);
                        ColumnNMinus2Levels(planeNum, (long) Levels, (long) (NumCOLMDOE - 2), ((int) LevelCntrlArrayGet(planeNum, NumCOLMDOE - 2,  LevelMaxIndex)), 0);
                        log.error("GenerateArray:Signal: ColumnNMinus2Levels Completed ");
                        levelCntrl = LevelCntrlArrayGet(planeNum, NumCOLMDOE - 1, LevelMinIndex);
                        ColumnN(planeNum, Levels, NumCOLMDOE - 1, Levels);
                        log.error("GenerateArray:Signal: ColumnN Completed ");
                    }else if ((NumROWSDOE >= 3)&&((NumCOLMDOE - 2) > LEVELS)) {
                        levelCntrl = LevelCntrlArrayGet(planeNum, NumCOLMDOE - 2, LevelMinIndex);
                        ColumnNMinus2Levels(planeNum, (long) Levels, (long) (NumCOLMDOE - 2), ((int) LevelCntrlArrayGet(planeNum, NumCOLMDOE - 2,  LevelMaxIndex)), 0);
                        log.error("GenerateArray:Signal: ColumnNMinus2Levels Completed ");
                        levelCntrl = LevelCntrlArrayGet(planeNum, NumCOLMDOE - 1, LevelMinIndex);
                        ColumnN(planeNum, Levels, NumCOLMDOE - 1, Levels);
                        log.error("GenerateArray:Signal: ColumnN Completed ");
                    } else {
                        log.error("GenerateArray:Signal: Skipping ColumnNMinus2Levels & ColumnN ");
                    }
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
            long Levels = LEVELS;
            double levelCntrl = 1;
            if (HasNormal == true) {
                setArrayLevelCntrlArray(planeNum);
                setArrayDataArray(planeNum);
                if (Checker(planeNum, false, HasNormal, (int) Length) == true)
                    ;// Do nothing
                else {
                    log.error("GenerateArrayFF:Signal:NormalChecker Failed:Should get an Exception");
                }

                if (NumROWSDOE > 1) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 0, LevelMinIndex);
                    ColumnZero(planeNum, 0, 0, LevelCntrlArrayGet(planeNum, 0, LevelMaxIndex));
                }

                if (NumROWSDOE > 2) {
                    levelCntrl = LevelCntrlArrayGet(planeNum, 1, LevelMinIndex);
                    ColumnOne(planeNum, 0, 1, LevelCntrlArrayGet(planeNum, 1, LevelMaxIndex));
                }
                System.out.println("ColumnOne Completed");
                //printDataOutput(0);
                levelCntrl = LevelCntrlArrayGet(planeNum, 2, LevelMinIndex);
                RowLevelupto1Levels(planeNum, 0, 2, Levels);
                System.out.println("RowLevelupto1Levels Completed");
                //printDataOutput(0);
                log.fatal("GenerateArrayFF:Signal: RowLevelupto1Levels Completed ");
                ColumnFullFactorial(planeNum, 0, NumCOLMDOE, Levels);
                System.out.println("ColumnFullFactorial Completed");
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
            if (Levels > 0 )
                LevelSpread = NumROWSDOE / Levels;

            for (;
                    ((( getArrayDataArray().limit() != 0))
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
                    ((row < NumROWSDOE)
                    && (col < NumCOLMDOE));
                    row++) {

                //log.info("ColumnOne col=" + col + " row=" + row);
                 ColumnOneMain(planeNum, row, col,  LevelCntrlArrayGet(planeNum, col,  LevelMaxIndex));
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

    public static void RowLevelupto1Levels(int planeNum, long row, long col, long Levels) {

        log.error("Processing RowLevelupto1Levels ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;

            long LastZ = NumCOLMDOE - 1;
            for (int levelCntrl =  LevelCntrlArrayGet(planeNum, col,  LevelMinIndex); row < Levels; row++) {
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
    }

    public static void RowLevel2Levels(int planeNum, long Levels, long col) {
        log.error("Processing RowLevel2Levels ");
        long NumROWSDOE = ROWSDOE;
        long NumCOLMDOE = Length;
        long row = Levels;
        long StartRowOrg = Levels;
        long StartColOrg = col;
        long OverallDupCountOrg = OverallDupCount;
        long DupCheckColStartOrg = col;//DupCheckColStart;
        long DupCheckColEndOrg = NumCOLMDOE - Levels + 1;//DupCheckColEnd;

        long LastZ = NumCOLMDOE;
        long RowNum = NumROWSDOE;
        long ColNum = NumCOLMDOE - Levels + 1;
        long TempValue = 0;
        if (NumROWSDOE > (10 * Levels)) {
            RowNum = (10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels))&&(NumROWSDOE > Levels)) {
            RowNum = 2*Levels;
        } else {
            RowNum = NumROWSDOE;
        }
        ComputeFactorial(planeNum, "RowLevel2Levels",
                col,
                LEVELS,
                (NumCOLMDOE ),
                RowNum,
                ColNum,
                FactorialRow1Lev,
                FactSpreadRow1Lev,
                FactRowRow1Lev
        );
        for (; (row < RowNum); row++) {
            for (int j = (int) col; (j < NumCOLMDOE); j++) {
                log.info("RowLevel2Levels  row=" + row + " col=" + j 
                        + " FactorialRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactorialRow1Lev) 
                        + " FactSpreadRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactSpreadRow1Lev) 
                        + " FactRowRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactRowRow1Lev) );
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
        AlgoParamLogger("RowLevel2Levels", StartRowOrg, StartColOrg, DupCheckColStartOrg, DupCheckColEndOrg, OverallDupCountOrg, row, (NumCOLMDOE - Levels + 1), OverallDupCount);
        log.error("Completed RowLevel2Levels ");
    }

    public static void RowLevel3Levels(int planeNum, long row, long col, long Value) {
        log.error("Processing RowLevel3Levels ");
        long NumROWSDOE = ROWSDOE;
        long NumCOLMDOE = Length;
        long Levels = LEVELS;

        long LastZ = NumCOLMDOE;
        long StartRowOrg = row;
        long StartColOrg = col;
        long OverallDupCountOrg = OverallDupCount;
        long DupCheckColStartOrg = col;//DupCheckColStart;
        long DupCheckColEndOrg = NumCOLMDOE - Levels + 1;//DupCheckColEnd;

        long RowNum = row;
        long ColNum = Levels;
        
        if (NumROWSDOE > (10 * Levels)) {
            RowNum = NumROWSDOE;//(10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels))&&(NumROWSDOE > Levels)) {
            RowNum = NumROWSDOE;
        } else {
            RowNum = NumROWSDOE;
        }
        
        ComputeFactorial(planeNum, "RowLevel3Levels", col, Levels, (NumCOLMDOE - Levels + 1), RowNum, ColNum,
                 FactorialRow3Lev,
                 FactSpreadRow3Lev,
                 FactRowRow3Lev);

        for (; row < RowNum; row++) {
            for (int j = (int) col; j < NumCOLMDOE ; j++) {
//                log.error("RowLevel3Levels  row=" + row + " col=" + j 
//                        + " FactorialRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactorialRow3Lev) 
//                        + " FactSpreadRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactSpreadRow3Lev) 
//                        + " FactRowRow1Lev Value=" +  LevelCntrlArrayGet(planeNum, j,  FactRowRow3Lev) );
                long TempValue =  ComplexCommonRCMain(planeNum, "RowLevel3Levels",
                        /* row*/ (long) row,
                        /* col*/ (long) j,
                        /* Levels*/ (long)  LevelCntrlArrayGet(planeNum, j,  LevelMaxIndex),
                        /*Value*/ (long)  LevelCntrlArrayGet(planeNum, j,  LastIssuedValue),
                        /* RowSub*/ 1,
                        /*ColSub*/ (long) 1,
                        /*AlgoRowFactIndex*/ (long)  FactorialRow3Lev,
                        /* AlgoRowSpreadIndex*/ (long)  FactSpreadRow3Lev,
                        /*AlgoFactRowFactIndex*/ (long)  FactRowRow3Lev
                );
                log.info("RowLevel3Levels:First For Loop:  row=" + row + " col=" + j + " TempValue=" + TempValue);
                 LevelCntrlArrayPut(planeNum, j,  LastIssuedValue, TempValue);
            if (row < RowNum) {
                //FindnFixDuplicate(planeNum, "RowLevel3Levels", row, DupCheckColStartOrg, DupCheckColEndOrg, NumCOLMDOE, LastZ);
                //DupCheckColMode( planeNum);
            }
        }
        }
        AlgoParamLogger("RowLevel3Levels", StartRowOrg, StartColOrg, DupCheckColStartOrg, DupCheckColEndOrg, OverallDupCountOrg, row, (NumCOLMDOE - Levels + 1), OverallDupCount);
        log.error("Completed RowLevel3Levels ");
    }

    public static void ColumnNMinus2Levels(int planeNum, long row, long col, long Levels1, long Value) {

        log.info("Processing ColumnNMinus2Levels ");

        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long Levels = LEVELS;
            long LevelSpread = NumCOLMDOE / Levels;
            long LastZ = col;
            long j = col;
            long StartRowOrg = row;
            long StartColOrg = col;
            long OverallDupCountOrg = OverallDupCount;
            long DupCheckColStartOrg = col - Levels;//DupCheckColStart;
            long DupCheckColEndOrg = col;//DupCheckColEnd;

            long RowNum = NumROWSDOE - (10 * Levels);
            long ColNum = Levels;
            ComputeFactorial(planeNum, "ColumnNMinus2Levels", col, Levels, (Levels), RowNum, ColNum,
                     FactorialNMinusLev,
                     FactSpreadNMinusLev,
                     FactRowNMinusLev
            );
            for (; (row < NumROWSDOE); row++) {
                for (j = col; (j > (col - Levels + 1)); j--) {
                    log.error("ColumnNMinus2Levels col=" + j + " row=" + row + " ColumnNMinus2Levels Value=" + Value);
                     LevelCntrlArrayPut(planeNum, j,
                             LastIssuedValue, ( ComplexCommonRCMain(planeNum, "ColumnNMinus2Levels", row, j, Levels,
                                     LevelCntrlArrayGet(planeNum, j,
                                             LastIssuedValue), 8, Levels,
                                     FactorialNMinusLev,
                                     FactSpreadNMinusLev,
                                     FactRowNMinusLev
                            )));
                }
                for (int x = (int) col; (x < col - Levels + 1); x++) {
                     LevelCntrlArrayPut(planeNum, x,
                             FactRowNMinusLev, (( LevelCntrlArrayGet(planeNum, x,
                                     FactRowNMinusLev) - 1)));
                }
                if (row < ROWSDOE) {
                    //FindnFixDuplicate(planeNum, "ColumnNMinus2Levels", row, DupCheckColStartOrg, DupCheckColEndOrg, col, LastZ);
                    //DupCheckColMode( planeNum);
                }
            }
            AlgoParamLogger("ColumnNMinus2Levels", StartRowOrg, StartColOrg, DupCheckColStartOrg, DupCheckColEndOrg, OverallDupCountOrg, row, (col - Levels - 1), OverallDupCount);
        } catch (Exception HyperE) {
            log.info("Exception: ColumnNMinus2Levels");
            System.out.println("Exception: ColumnNMinus2Levels");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.info("Completed ColumnNMinus2Levels ");
    }

    public static void ColumnN(int planeNum, long row, long col, long Levels) {
        log.info("Processing ColumnN ");
        try {
            long NumROWSDOE = ROWSDOE;
            int NumCOLMDOE = (int) Length;
            long LevelSpread = NumROWSDOE / Levels;
            for (;
                    ((row < NumROWSDOE)
                    && (col < NumCOLMDOE));
                    row++) {

                log.error("ColumnN col=" + col + " row=" + row);
                 ColumnOneMain(planeNum, row, col,
                         LevelCntrlArrayGet(planeNum, col,
                                 LevelMaxIndex));
            }
        } catch (Exception HyperE) {
            log.info("Exception: ColumnN");
            System.out.println("Exception: ColumnN");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.info("Completed ColumnN ");
    }

    public static void ColumnFullFactorial(int planeNum, long row, long col, long Levels) {
        log.error("Processing ColumnFullFactorial ");
        try {
            long NumROWSDOE = ROWSDOE;
            long NumCOLMDOE = Length;
            long LevelSpread = 0;
            if(Levels > 0 )
                LevelSpread = NumROWSDOE / Levels;
            long FullFactorialROWS = 1;
            for (int j = 2; (j < NumCOLMDOE); j++) {
                for (int i = (int) Levels; (i < NumROWSDOE); i++) {
                    FullFactorialROWS = (long) Math.pow((1.0 * Levels), (1.0 * j));
                     ColumnFullFactorialRecursiveMain(planeNum, i, j,
                             LevelCntrlArrayGet(planeNum, j,
                                     LevelMaxIndex), NumCOLMDOE, LevelSpread);
                    if (dupCombinationCol(planeNum, "ColumnFullFactorial", i, 0, j, Length, false)) {
                        //i--;
                        log.info("ColumnFullFactorial:Got Duplicate in row=" + i + " Col=" + j + " Value=" +  DataArrayGet(planeNum, i, j) + " FullFactorialROWS=" + FullFactorialROWS + " LevelSpread=" + LevelSpread);
                    }
                    //log.info("ColumnFullFactorial col=" + j + " row=" + i + " Value=" +  DataArrayGet(planeNum, i, j));
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
        long Levels = LEVELS;
        long row = 0;
        if (NumROWSDOE > (10 * Levels)) {
            row=10 * Levels;//(10 * Levels);
        } else if ((NumROWSDOE < (10 * Levels))&&(NumROWSDOE > Levels)) {
            row = Levels;
        } else {
            row = Levels;
        }
        
        int col = 3;
        long Value = 0;
        long LastZ = NumCOLMDOE - 1;
        long DupCheckColStartOrg = DupCheckColStart;
        long DupCheckColEndOrg = DupCheckColEnd;
        for (; row < NumROWSDOE; row++) {
            for (col = 2; col < NumCOLMDOE; col++) {
                Levels =  LevelCntrlArrayGet(planeNum, col,  LevelMaxIndex);
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
//                SimpleModuloLevelXn=Value;
//                Value = SimpleModuloLevel(planeNum, "ClassicTaguchiRowLevel3Levels", row, col,
//                    SimpleModuloLevelXn,
//                    Levels, LevelCntrlArrayGet(planeNum, col,  LevelMinIndex));
//                SimpleModuloLevelXn = Value;    
                if (Value == 0) {
                    Value = 1;
                }
                 DataArrayPut(planeNum, row, col, (byte) Value);
                if (row < Length) {
                    dupCombinationCol(planeNum, "ClassicTaguchiRowLevel3Levels", row, 0, col, 20, false);
                }
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
        long Factorial = Fact(N, 1);
        long RowFactorial = nPr(R, ColNum, 1);
        for (long i = col; i <= (col + R); i++) {
            //if (AlgoName.equalsIgnoreCase("RowLevel3Levels")) 
            //log.info("ComputeFactorial:" + AlgoName + " col=" + i + " Factorial=" + Factorial);
            LevelCntrlArrayPut(planeNum, i, FuncFactIndex, (byte) Factorial);
            long SpreadLevel = 1;
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
    public static boolean dupCombinationCol(int planeNum, String AlgoName, long row, long colDupCheckColStart, long colDupCheckColEnd, long LastZ, boolean Matrix) {
        boolean RetValue = false;
        String HistRow = "";
        String CurrRow = "";
        int Duptimes = 0;
        log.info("Entered dupCombinationCol row=" + row + " colDupCheckColStart=" + colDupCheckColStart + " colDupCheckColEnd=" + colDupCheckColEnd + " LastZ=" + LastZ);
        long i = 0;
        long j = 0;
        try {
            long upperlimit = row;
            if (Matrix == true) {
                upperlimit = ROWSDOE;
            }
            CurrRow = getRowAsStringDataArray(planeNum, row, colDupCheckColStart, colDupCheckColEnd, false);
            log.info("dupCombinationCol row=" + row + " CurrRow =" + CurrRow);
            for (j = 0; (j < upperlimit) && (Duptimes < 1); j++) {
                if ((j == row) && (Matrix == true)) {
                    continue;
                }
                RetValue = false;
                log.info("dupCombinationCol CurrRow =" + CurrRow);
                HistRow = getRowAsStringDataArray(planeNum, j, colDupCheckColStart, colDupCheckColEnd, false);
                if ((CurrRow.length() == HistRow.length()) && (CurrRow.equalsIgnoreCase(HistRow))) {
                    RetValue = true;
                    log.info("dupCombinationCol Got Duplicates in AlgoName=" + AlgoName + ": HistRow=" + j + " Curr row=" + row);
                    //log.error("dupCombinationRow HistRow =" + HistRow.toString());
                    log.info("dupCombinationCol HistRow(" + j + " =" + HistRow);
                    log.info("dupCombinationCol CurrRow (" + row + " =" + CurrRow);
                    //FixerIncremFast(planeNum, row, colDupCheckColStart, LastZ, FixDepth);
                    log.info("dupCombinationCol FixedRow=" + getRowAsStringDataArray(planeNum, row, colDupCheckColStart, colDupCheckColEnd, true));
                    // Restart combination as the fixed Row may clash 
                    // with previously unique values
                    break;
                }
            }

            if (RetValue == true) {
                log.info("Found Dups in AlgoName=" + AlgoName + " for row(Current Row)=" + row + " i(Historical Row)=" + j + " OverallDupCount=" + OverallDupCount + " DupCount=" + DupCount + " ZeroCount=" + ZeroCount);
                log.info("dupCombinationCol HistRow(" + j + ") =" + getRowAsStringDataArray(planeNum, j, colDupCheckColStart, colDupCheckColEnd, true));
                log.info("dupCombinationCol CurrRow(" + row + ") =" + CurrRow);
            } else if (RetValue == false) {
                log.info("dupCombinationCol:No duplicates for row=" + row);
                RetValue = false;
            }
        } catch (Exception HyperE) {
            log.info("Exception: dupCombinationCol");
            System.out.println("Exception: dupCombinationCol");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
        log.info("Completed dupCombinationCol ");
        return RetValue;
    }
    public static long Fact(long n, long Result) {
        long TempResult = 1;
        if (n > 1) {
            if (Result > SYSTEM_BIGNUMBER) {
                log.info("Fact:Result=" + Result);
                return (long) SYSTEM_BIGNUMBER;
            } else {
                TempResult = Result * n;
                log.info("Fact:Result=" + Result);
                if ((TempResult > SYSTEM_BIGNUMBER) && (Result < SYSTEM_BIGNUMBER)) {
                    return (long) Result;
                } else {
                    return Fact(n - 1, TempResult);
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
        TempResult = Result * nPr(n, r, Result) / Fact(r, 1);
        if (TempResult > 0) {
            Result = TempResult;
        }
        log.info("nCr:Result=" + Result + " n=" + n + " r=" + r + " i=" + i);
        return Result;
    }
    //Normal Input Code
    // Checker Function to Validate class Normal
    public static boolean Checker(int planeNum,boolean UseFlag,boolean HasFlag,Integer HasColumns){
        if((HasFlag==true)&&(HasColumns > 0))
        {
            //if (HyperCubeArray[planeNum].SignalObj.ColNormalArray!=null) {
                {
                    if (DataArray!=null) {
                        {
                            if (LevelCntrlArray != null)
                                 return true;
                            else log.error("NormalChecker:Failed Making Object Null:LevelCntrlArray");
                        }
                    }
                    else log.error("NormalChecker:Failed Making Object Null:LevelCntrlArray");
                }
            //}
            //else log.error("NormalChecker:Failed Making Object Null:ColNormalArray");
        }
        else log.error("NormalChecker:Failed Making Object Null:HasNormal:HasColumns");
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
        LevelValueStore=new double[(int)Length][LEVELS+1];
        LevelValueInit=new int[(int)Length];
        Name=new String[(int)Length];
        for(int j=0; j < Length; j++){
            LevelValueStore[j]= new double[(int)LEVELS+1];
            LevelValueInit[j]=-1;
            Name[j]="";
        }
        for (long j = 0, i = 0; (j < MyStartROWSDOE); j++) {
            if (UseLevelsFromFile == true) {
                    DOEColumnInputLevel.ProgramDefine("Signal");
                    DOEColumnInputLevel.IO(planeNum, "NormalInput", "Specific", (int)j);
                    if (LevelValueInit[(int)j] == -1) {
                        DOEColumnInputLevel.IO(planeNum, "NormalInput", "Common", (int)j);
                    }
                    DOEColumnInputLevel.Myprintf(planeNum, "NormalInput", (int)j);
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
                    LevelValueStore[(int)j]= new double[(int)LevelCntrlArrayGet(planeNum, j, LevelMaxIndex)+1];
            }else if ((UseLevelsFromArray==true)&&(i < InitLevels.length)&&(j == InitLevels[(int) i][0])) {
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
            System.out.println(" Column=" + j  + " Min Level=" +LevelCntrlArrayGet(planeNum, j, LevelMinIndex) +  " Max Level=" +LevelCntrlArrayGet(planeNum, j, LevelMaxIndex));
        }
        
        log.error("LevelCntrlArray.size=" + LevelCntrlArray.limit() + " MyStartROWSDOE=" + MyStartROWSDOE + " MyStartCOLMDOE=" + MyStartCOLMDOE + " for planeNum=" + planeNum);
        ComputeROWSDOETaguchi();
    }

    public static int LevelCntrlArrayGet(int planeNum, long row, long LevelIndex) {
        long MaxRow = Length;
        long MaxCol = InitLevels[0].length - 1;
        if ((LevelCntrlArray != null)
                && ((MaxRow * MaxCol) < LevelCntrlArray.limit())
                &&(row < MaxRow)
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
                &&(row < MaxRow)
                && ((row * LevelIndex) < LevelCntrlArray.limit())) {
            long n = ((row) * MaxCol) + LevelIndex;
            if (n <= LevelCntrlArray.limit()) {
                if ((Value <= Byte.MAX_VALUE)&&(Value >= Byte.MIN_VALUE)) LevelCntrlArray.put((int) n, (byte) Value);
                else LevelCntrlArray.put((int) n, (byte) 1);
            }
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
        
        for (int i = 0; ((i < MyStartROWSDOE) ); i++) {
               for (int j = 0; ((j < MyStartCOLMDOE)); j++) {
                    {
                        DataArrayPut(planeNum, i, j, (byte)0);
                        log.info("setArrayDataArray:Putting Got Initial Value DOE Matrix=" + 0 + " at i=" + i + " j=" + j + " Retrieved Value=" + DataArrayGet(planeNum, i, j));
                    }
                }
            }
        log.error("setArrayDataArray.size=" + DataArray.limit() + " MyStartROWSDOE=" + MyStartROWSDOE + " MyStartCOLMDOE=" + MyStartCOLMDOE + " for planeNum=" + planeNum);

    }

    public static byte DataArrayGet(int planeNum, long row, long col) {
        long MaxRow = ROWSDOE;
        long MaxCol = Length;
        if ( (DataArray != null)
                && ((MaxRow * MaxCol) < DataArray.limit())
                &&(row < MaxRow)
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
                &&(row < MaxRow)
                && (col <= MaxCol)
                && ((row * col) < DataArray.limit())) {
            long n = ((row) * MaxCol) + col;
            if (n <= DataArray.limit()) {
                DataArray.put((int) n, Value);
            }
            else log.error("DataArrayPut: Size Exceeded:row=" + row + " col=" + col);
        }
    }
    /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ************************Normal Core Cell Generating Functions Start Here* */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
 /* ********************                                  ************ */
    public static void ColZeroMain(int planeNum, long row, long col, long Levels, long LevelSpread) {
        int levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
        //HyperCubeArray[planeNum].getDataArray().get((int) row)[(int) col].setCell((int) row, (int) col, HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        DataArrayPut(planeNum, row, col, (byte)LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
        if ((LevelSpread >0)&&(((row + 1) % LevelSpread) == 0) ) {
            LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, LevelCntrlArrayGet(planeNum, col, LevelMinIndex));
            if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
                levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            } else {
                levelCntrl++;
            }
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
    }

    public static void ColumnOneMain(int planeNum, long row, long col, long Levels) {
        int levelCntrl = 1;
        levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex);
        DataArrayPut(planeNum, row, col, (byte)LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl);
        if (levelCntrl == LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        } else if((levelCntrl < LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)) ){
            levelCntrl++;
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
    }

    public static void RowLevelupto1LevelsRecursiveMain(int planeNum, long row, long col) {
        int levelCntrl = 1;
        if (LevelCntrlArrayGet(planeNum, col, LastIssuedLevel) == 0) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        } else if (((LevelCntrlArrayGet(planeNum, col, LastIssuedLevel)) >= (LevelCntrlArrayGet(planeNum, col, LevelMinIndex)))
                && ((LevelCntrlArrayGet(planeNum, col, LastIssuedLevel)) <= (LevelCntrlArrayGet(planeNum, col, LevelMaxIndex)))) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LastIssuedLevel);
        }
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);

        //HyperCubeArray[planeNum].getDataArray().get((int) row)[(int) col].setCell((int) row, (int) col, HyperCube[planeNum].Matrix.LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        DataArrayPut(planeNum, row, col, (byte)LevelCntrlArrayGet(planeNum, col, LevelCtrlIndex));
        LevelCntrlArrayPut(planeNum, col, LevelCtrlIndex, levelCntrl);
        if ((levelCntrl >= LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))
                || (levelCntrl <= LevelCntrlArrayGet(planeNum, col, LevelMinIndex))) {
            levelCntrl = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        }
        LevelCntrlArrayPut(planeNum, col, LastIssuedLevel, levelCntrl + 1);

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
        long NPRSpreadValue =1;
        if(AlgoName.equalsIgnoreCase("RowLevel1Levels")) 
//            log.info("ComplexCommonRCMain " 
//                + " AlgoRowFactIndex=" + AlgoRowFactIndex 
//                + " AlgoRowSpreadIndex="+ AlgoRowSpreadIndex
//                + " AlgoFactRowFactIndex="+ AlgoFactRowFactIndex);
        FactValue = LevelCntrlArrayGet(planeNum, col, AlgoRowFactIndex);
        SpreadValue = LevelCntrlArrayGet(planeNum, col, AlgoRowSpreadIndex);
        NPRFactValue = LevelCntrlArrayGet(planeNum, col, AlgoFactRowFactIndex);
        if (NPRFactValue > 0) NPRSpreadValue= ROWSDOE/NPRFactValue;
        else NPRSpreadValue= 1;
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
        if ((LCG_NPRFactValue == 1)&&(NPRFactValue > 1)&&(NPRSpreadValue > 1)) {
            LCG_NPRFactValue = LCG_Factorial_Random(planeNum, AlgoName, NPRFactValue, NPRSpreadValue, 4, 1, Levels);
        } else {
            LCG_NPRFactValue = LCG_Factorial_Random(planeNum, AlgoName, LCG_NPRFactValue, NPRSpreadValue, 4, 1, Levels);
        }

        if ((SpreadValue > 0) ) {
            Temp = (LCG_Value + LCG_levelCntrl + LCG_TempValue  + LCG_FactValue + LCG_NPRFactValue) % (Levels + 1);
        } else if ((Levels + 1) > 0) {
            Temp = ( Value + levelCntrl + LCG_TempValue) % (Levels + 1);
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

    public static void ColumnFullFactorialRecursiveMain(int planeNum, long row, long col, long Levels, long NumCOLMDOE, long LevelSpread) {
        long Temp = 0;
        long FullFactorialROWS = (long) Math.pow((1.0 * Levels), (1.0 * col));
        if (FullFactorialROWS <= LevelSpread) {
            //Generate Random Level Value within LevelMinIndex-LevelMaxIndex(Modulus Parameter)
            Temp = ComplexCommonRCMain(planeNum, "ColumnFullFactorialRecursiveMain", row, col,
                    LevelCntrlArrayGet(planeNum, col, LevelMaxIndex),
                    LevelCntrlArrayGet(planeNum, col, LastIssuedValue),
                    4, LevelCntrlArrayGet(planeNum, col, LevelMaxIndex),
                    FactorialRow3Lev, FactSpreadRow3Lev, FactRowRow3Lev);//Need to test last parameter
            if ((DataArrayGet(planeNum, row, (col - 1)) == Temp)) {
                Temp = (1 * (Temp) + 1) % (Levels + 1);
            }
            if (Temp <= LevelCntrlArrayGet(planeNum, col, LevelMinIndex)) {
                Temp = LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
            }
            log.info("ColumnFullFactorialRecursiveMain:FullFactorialROWS=" + FullFactorialROWS + " LevelSpread=" + LevelSpread + " row=" + row + " col=" + col + " Temp=" + Temp + " Proceeding with Random Value");

        } else {
            Temp = SimpleModuloLevel(planeNum, "ColumnFullFactorialRecursiveMain", row, col,
                    SimpleModuloLevelXn,
                    Levels, 1);
            SimpleModuloLevelXn = Temp;
            log.info("ColumnFullFactorialRecursiveMain:FullFactorialROWS=" + FullFactorialROWS + " LevelSpread=" + LevelSpread + " row=" + row + " col=" + col + " Temp=" + Temp + " Proceeding with SimpleModuloLevel");

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
        if (((Modulus + 1) > 0)&&((Math.abs(row - RowSub + 1) % (Modulus + 1)) > 0) && ((Math.abs(col - ColSub + 1) % (Modulus + 1)) > 0) && (((Xn + 1) % (Modulus + 1)) > 0)) {
            TempValue = ((row - RowSub + 1) % (Modulus + 1)) + ((col - ColSub + 1) % (Modulus + 1));
        } else if (((Modulus + 1) > 0)&&(Math.abs(col - ColSub + 1) % (Modulus + 1)) > 0) {
            TempValue = (Math.abs(row - RowSub) % (Modulus + 1));
        } else if (((Modulus + 1) > 0)&&(Math.abs(row - RowSub + 1) % (Modulus + 1)) > 0) {
            TempValue = (Math.abs(col - ColSub + 1) % (Modulus + 1));
        } else if (((Modulus + 1) > 0)&&((Xn + 1) % (Modulus + 1)) > 0) {
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
        levelCntrl = (1 * Xn + 1) % (Modulus + 1);
        if ((DataArrayGet(planeNum, row, (col - 1)) == levelCntrl) && (DataArrayGet(planeNum, row, (col - 1)) != Xn)) {
            levelCntrl = (1 * DataArrayGet(planeNum, row, (col - 1)) + 1) % (Modulus + 1);
        }
        if (levelCntrl < ModulusLow) {
            levelCntrl = ModulusLow;
        }
        //if (((levelCntrl >= HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMaxIndex))||(levelCntrl <= HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMinIndex))))
        //    levelCntrl=HyperCubeArray[planeNum].MatrixArray.LevelCntrlArrayGet(planeNum, col, LevelMinIndex);
        return levelCntrl;
    }
 public static long LCG_Value_Random(int planeNum, String AlgoName, long a, long c, long Xn, long Modulus) {
        //Value = (a * Xn + c) % (Modulus + 1);
        
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        
        String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
        String Exprn=FormEquation( Xn,a, c, (long)1.0 , DiffWithRespTo, (int)Modulus);
        MathsContxtLAv0_1_Prod.SimpleExpression expSimple ;
        MathsContxtLAv0_1_Prod.Expression expValue;
        String StrexpSimpleExpress="";
        String StrexpDiffExpress ="";
        int i = 1;
        long Xnplus1=Xn;
        long Temp=0;
        
        for ( ; (i < 10)&&(Xn == Xnplus1);  Xn++, i++) {
            variables.put(DiffWithRespTo, ((double)Xn));
        expValue = parse(Exprn, variables, DiffWithRespTo);
        Temp=(long)expValue.eval();
        log.info("Expr_LCG_Value:Temp(Xn)=" + Temp + " Xn=" + Xn + " Xnplus1=" + Xnplus1);
        if((Temp==Xn)||(i > 1)){
            expSimple=parseSimple(Exprn, variables, variablesDiff, "Xn");
            StrexpSimpleExpress="";
            StrexpSimpleExpress = expSimple.SimpleExpr();
            log.info("Expr_LCG_Value: Xn=" + Xn + " Basic Simple Exprn=" + StrexpSimpleExpress);
          
            MathsContxtLAv0_1_Prod.DiffExpr exp = parseDiff(StrexpSimpleExpress, variables, variablesDiff, DiffWithRespTo);
            StrexpDiffExpress = "";
            StrexpDiffExpress = exp.DiffExpr();
            log.info("Expr_LCG_Value:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);

            expSimple = parseSimple(StrexpDiffExpress, variables, variablesDiff, "Xn");
            StrexpSimpleExpress = expSimple.SimpleExpr();
            log.info("Expr_LCG_Value: Xn=" + Xn + " (Simplified Diff Equation) Exprn=" + StrexpSimpleExpress);

            expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
            log.info("Expr_LCG_Value:Result(Value)(Xn)=" + expValue.eval());
            Exprn = StrexpSimpleExpress;
            StrexpSimpleExpress = "";
            StrexpDiffExpress = "";
            Xnplus1 = (long) (expValue.eval() % (Modulus + 1));
        }else 
            Xnplus1=Temp;
        }
        return ((long) Xnplus1);
    }
public static long LCG_Factorial_Random(int planeNum, String AlgoName, long Xn, long FactorialSpread, long a, long c, long Modulus) {
        //Value = (a * Factorial + c) % (FactorialSpread + 1);
        Map<String, Double> variables = new HashMap<>();
        Map<String, String> variablesDiff = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        String DiffWithRespTo = "Xn";
        variablesDiff.put(DiffWithRespTo, DiffWithRespTo);
        String Exprn = FormEquation( Xn,a, c, (long)1.0 , DiffWithRespTo, (int)Modulus);
        
        MathsContxtLAv0_1_Prod.SimpleExpression expSimple ;
        MathsContxtLAv0_1_Prod.Expression expValue;
        String StrexpSimpleExpress="";
        String StrexpDiffExpress ="";
        int i = 1;
        long Xnplus1=Xn;
        long Temp=0;
        for ( ; (i < 10)&&(Xn == Xnplus1);  Xn++, i++) {
            variables.put("Xn", ((double)Xn));
        expValue = parse(Exprn, variables, DiffWithRespTo);
        Temp=(long)expValue.eval();
        log.info("Expr_LCG_Value:Temp(Xn)=" + Temp + " Xn=" + Xn + " Xnplus1=" + Xnplus1);
        if((Temp==Xn)||(i > 1)){
        expSimple=parseSimple(Exprn, variables, variablesDiff, DiffWithRespTo);
        StrexpSimpleExpress = expSimple.SimpleExpr();
        log.info("Expr_LCG_Factorial: Xn=" + Xn + " Basic Simple Exprn=" + StrexpSimpleExpress);
          
        MathsContxtLAv0_1_Prod.DiffExpr exp = parseDiff(StrexpSimpleExpress, variables, variablesDiff, DiffWithRespTo);
        StrexpDiffExpress=exp.DiffExpr();
        log.info("Expr_LCG_Factorial:Result(Diff Equation)(" + i + " th order)=> " + StrexpDiffExpress);
        
        expSimple=parseSimple(StrexpDiffExpress, variables, variablesDiff, "Xn");
        StrexpSimpleExpress = expSimple.SimpleExpr();
        log.info("Expr_LCG_Value: Xn=" + Xn + " (Simplified Diff Equation) Exprn=" + StrexpSimpleExpress);
        
        expValue = parse(StrexpSimpleExpress, variables, DiffWithRespTo);
        log.info("Expr_LCG_Factorial:Result(Value)(Xn)=" + expValue.eval());
        Exprn = StrexpSimpleExpress;
        StrexpSimpleExpress="";
        StrexpDiffExpress ="";
        Xnplus1=(long)(expValue.eval() % (Modulus + 1));
        }else 
            Xnplus1=Temp;
        }
        return ((long) Xnplus1);
    } 
public static boolean DupCheckMatrixMode(int planeNum) {
        int i = 0;
        int k = 0;
        int m=0;
        boolean flag = false;
        long a = 0;
        int j = 0;
        for (i = 0; i < ROWSDOE; i++) {
            a = getRowAsInteger(planeNum, i, Length);
            for(m=0; m <= j;m++) {
               if ((a==ModeValue[m])) 
               {
                   Mode[m]=Mode[m]+1;
                   log.fatal("DupCheckMatrixMode:Duplicate row=(i)=" + i + " m=" + m + " j=" + j + " Integer(a)=" + a + " Freq="+ Mode[m]);
                   OverallDupCount++;
                   DupsList.add(i+"@"+m);
                   break;
               }
            }
            if (m >=j) {
                   ModeValue[j] = a;
                   Mode[j]++;
                   ModeIndex[j]=i;
                   //log.fatal("DupCheckMatrixMode:Inserting row=(i)=" + i + " ModeValue["+ j +"]=" + ModeValue[j] + " Integer(a)=" + a + " Freq="+ Mode[j]);
                   j++;
               }
        }
        log.fatal("DupCheckMatrixMode:ROWSDOE=" + ROWSDOE + " Mode.length(j)=" + j);
        for (k = 0; k < j; k++) {
            if ( (Mode[k] > 1)) {
                log.fatal("DupCheckMatrix:Array Incomplete and with Duplicates duplicate Row k=" + k + " MeanOutputModeTracker=" + MeanInputModeTracker[k] + "ModeValue[k]=" + ModeValue[k] + " Mode[k]=" + Mode[k] + " i=" + i);
                flag = true;
            }
        }
        OverallUniqueCount=j;
        if (flag == false) {
            log.fatal("DupCheckMatrix:No duplicates for Whole Array ");
            System.out.println("DupCheckMatrix:No duplicates for Whole Array ");
        }
        return flag;
    }
public static long getRowAsInteger(int planeNum, long row, long Col) {
        long Temp = 0;
        long col = Col;
        //long col = 6;
        for (long i = 0; i < col; i++) {
                Temp = 10*Temp + DataArrayGet(planeNum, row, i);
        }
        return Temp;
    }
public static long getColAsInteger(int planeNum, long col /*, long colStart, long colEnd*/) {
        long Temp = 0;
        // Below Length is ROWS of DataArray and NOT String Length
        //long row = LocalLength;
        long row = 6;
        for (long i = 0; i < row; i++) {
                Temp = 10*Temp + DataArrayGet(planeNum, col, i);
        }
        return Temp;
    }
public static String getRowAsStringDataArray(int planeNum, long row,long colDupCheckColStart,long colDupCheckColEnd, boolean DisplayAsString) {
        String Temp = "";
        long col = Length;
        for (long i = colDupCheckColStart; i < colDupCheckColEnd; i++) {
            if (DisplayAsString==false)
                Temp = Temp + DataArrayGet(planeNum, row, i);
            else
                Temp = Temp + " " + DataArrayGet(planeNum, row, i);
        }
        return Temp;
    }
public static double getLevelValuePair(int col,double Level){
        return LevelValueStore[col][(int)Level];
    }
    public static void PutLevelValuePair(int col,double Level, Double Value){
         LevelValueStore[col][(int)Level]=Value;
    }
    
    public static void pmain(String... args){
        log.info("HyperDOE: main Entered");
        String Path="data\\";
        String msg="";
        try{
        FileInputStream fpinput=null;
        if ((fpinput = new FileInputStream(Path+ConfigFileName)) == null) {
                msg = "Input: can't open " + Path+ConfigFileName;
                System.out.println(msg);
                log.error(msg);
            }
            log.error("Opened File Name: Infile=" + Path+ConfigFileName);
            System.out.println("Opened File Name: Infile=" + Path+ConfigFileName);
            HDOEDefine.Myfscanf( fpinput);
            HDOEDefine.Myprintf();
        
        MeanColModeTracker = new String[(int)Length];
        ColMode= new int[(int)Length];// Needs to be improved aqs this is a wastage of space
        ColModeValue=new int[(int)Length];// Needs to be improved aqs this is a wastage of space
        //Intimate Compiler of Equation Memory
        EqnDetails= new ColConfigration[(int)Length];
        EqnParsed = new int[(int) Length];
        for (int i=0; i < Length;i++){
            MeanColModeTracker[i]=new String("");
            ColMode[i]=0;
            ColModeValue[i]=0;
            //Set Equation Memory
            EqnDetails[i]= new ColConfigration(i);
            EqnParsed[i]=0;
        }
        MeanInputModeTracker = new String[(int) ROWSDOE];
        Mode = new long[(int) ROWSDOE];// Needs to be improved as this is a wastage of space. Can be reduced to ROWSDOE/LEVELS
        ModeValue = new long[(int) ROWSDOE];// Needs to be improved as this is a wastage of space. Can be reduced to ROWSDOE/LEVELS
        ModeIndex = new long[(int) ROWSDOE];// Needs to be improved as this is a wastage of space. Can be reduced to ROWSDOE/LEVELS
        for (int i = 0; i < ROWSDOE; i++) {
            MeanInputModeTracker[i] = new String("");
            Mode[i] = 0;
            ModeValue[i] =0;
            ModeIndex[i]=0;
        }
        DupsList=new LinkedList<String>();
        
        GenerateDOELatin(0,ROWSDOE,Length);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        System.out.println("Final Completed DOE Matrix:");
        System.out.print(System.lineSeparator());
        printDataOutput(0);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        
        System.out.print(System.lineSeparator());
        System.out.println("main:ROWSDOEComputed=" + ROWSDOEComputed + " ROWSDOE=" + ROWSDOE + " ROWSDOERecommended=" + ROWSDOERecommended);
        System.out.print(System.lineSeparator());
        } catch (Exception HyperDOEse) {
            log.error("Exception: main");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            HyperDOEse.printStackTrace();
        }
    }
    public static void printDataOutput(int planeNum) {
        try {
            int NumROWSDOE = (int) ROWSDOE;//matrix.limit();
            int NumCOLMDOE = (int) Length;
            int Levels=LEVELS;
            int FullFactorialCnt=0;
            int FullFactUptoCol=0;
            int FullFactIndivCol=0;
            int c = 0,DataCount=0;
            int i=0,j=0,k=0;
            String DupString="";
            String[] DataTemp;
            boolean FullFactFlag=false;
            System.out.print("DataArray:Header:");
            for (int r = 0; r < NumCOLMDOE; r++) {
                    System.out.print(r + " ");
            }
            System.out.print(System.lineSeparator());
            if(DupsList.size() >0) {
                DupString=DupsList.getFirst();
                DataTemp=DupString.split("@");
                if(DataTemp.length ==2) {
                    i=Integer.parseInt(DataTemp[0]);
                    k=Integer.parseInt(DataTemp[1]);
                }
            }
            for (int r = 0; r < NumROWSDOE; r++) {
                System.out.print("Row=" + r + " ");
                // Rajesh Pai:Not sure why c should start from 1;
                for ( c = 0; c < NumCOLMDOE; c++) {
                    System.out.print(DataArrayGet(planeNum, r, c) + " ");
                    Levels=Math.max(Levels, LevelCntrlArrayGet(0, c, LevelMaxIndex));
                }
                if(r==i)
                {
                    System.out.print(ConsoleColors.RED +" duplicate with Row=" +ModeIndex[k]  + ConsoleColors.RESET);
                    j++;
                    if(j < DupsList.size()) {
                        DupString=DupsList.get(j);
                        DataTemp=DupString.split("@");
                        if(DataTemp.length ==2) {
                            i=Integer.parseInt(DataTemp[0]);
                            k=Integer.parseInt(DataTemp[1]);
                        }                       
                    }
                }
                else {
                  System.out.print(ConsoleColors.GREEN +" Unique Row " + ConsoleColors.RESET);
                }
                System.out.print(System.lineSeparator());
            }
            System.out.print(System.lineSeparator());
            System.out.println("Overall DupsList.size()=" + DupsList.size() + " OverallDupCount=" + OverallDupCount + " OverallUniqueCount=" + OverallUniqueCount);
            System.out.print(System.lineSeparator());
            System.out.println("Full Factorial Sum Of Levels Summary:" );
            System.out.print(System.lineSeparator());
            System.out.println("Strength of Levels Summary:" );    
            System.out.print("Column=" );
               for (int r = 0; r < NumCOLMDOE; r++) {
                    System.out.print( r + " ");
               }
               System.out.print(System.lineSeparator());
               StrengthMode=new int [(int)Length][Levels+10];
               for (c=0,DataCount=0; ( (c < NumCOLMDOE) ); c++,DataCount=0) {
                   int Li=LevelCntrlArrayGet(0, c, LevelMaxIndex);
                   StrengthMode[c]=new int [Levels+10];
                    for ( int r = 0; (r < NumROWSDOE); r++) {
                            int CellVal=DataArrayGet(planeNum, r, c);
                            if ( (CellVal >=0) && (CellVal < StrengthMode[c].length ) )
                                StrengthMode[c][CellVal]++;
                    }
               }
               //Printing StrengthMode
               int r1 = 1;
               for ( r1 = 1; r1 < (Levels+1); r1++) {
                   System.out.print("Strength of Level=" + r1);
                   for (c=0; c < NumCOLMDOE; c++) {
                     System.out.print(" " + StrengthMode[c][r1]);
                     }
                   System.out.print(System.lineSeparator());
               }
               System.out.print(System.lineSeparator());
               System.out.print("DataCount=" );
               int r = 0;
               for (c=0,DataCount=0; c < NumCOLMDOE; c++,DataCount=0) {
                    for ( r = 0; r < NumROWSDOE; r++) {
                            DataCount += DataArrayGet(planeNum, r, c);
                    }
                    System.out.print( DataCount + " ");
                    if (c==0) {
                        FullFactorialCnt=DataCount;
                        FullFactFlag=true;
                        FullFactUptoCol=c;
                    }else if ((DataCount != FullFactorialCnt)&&(FullFactFlag==true)) {
                        FullFactFlag=false;
                    } else if (FullFactFlag==true){
                        FullFactUptoCol=c;
                        FullFactFlag=true;
                    }
               }
                System.out.print(System.lineSeparator());
                System.out.println("Matrix is Full Factorial upto FullFactUptoCol=" +FullFactUptoCol + ".");
                System.out.println(ConsoleColors.RED + "Columns " + (FullFactUptoCol+1) + " upto " + (c-1) 
                        + " Should be Full Factored Manually." + ConsoleColors.RESET);
                System.out.print(System.lineSeparator());
                System.out.print("Full Factorial % =" );
               for (c=0,DataCount=0,FullFactIndivCol=0; c < NumCOLMDOE; c++,DataCount=0) {
                    for ( r = 0; r < NumROWSDOE; r++) {
                            DataCount += DataArrayGet(planeNum, r, c);
                    }
                    if(FullFactorialCnt==DataCount)
                        FullFactIndivCol++;
                    if (FullFactorialCnt > 0) 
                        System.out.printf( "%3.2f ",((double)DataCount/(double)FullFactorialCnt*100.00));
                  }
                System.out.print(System.lineSeparator());
                if (c >0)
                    System.out.printf("Matrix High Level Full Factorial=%3.2f%%. ( %d Columns out of %d Columns)" ,(double)(FullFactUptoCol+1)/(double)Length*100.00 ,(FullFactUptoCol+1),Length);
                else
                    System.out.printf("Matrix High Level Full Factorial=%3.2f%%." ,0.00 );
                System.out.print(System.lineSeparator());
                System.out.print("Matrix High Level Full Factorial :Individual Column Count=" + FullFactIndivCol + " out of " + Length);
                if (Length >0)
                    System.out.printf( "(%3.2f%%) ",((double)FullFactIndivCol/(double)Length*100.00));
                else
                    System.out.printf( "(%3.2f%%) ",0.00);
        } catch (Exception HyperE) {
            log.info("Exception: printDataOutput");
            System.out.println("Exception: printDataOutput");
            System.out.println(HyperE.getStackTrace());
            System.out.println(HyperE.getMessage());
            HyperE.printStackTrace();
        }
    }
    //RowsTaguchi=(1 + Sigma((i=1 to NV) (Li-1)) ) 
    public static void ComputeROWSDOETaguchi(){
        int i=0;
        int Li=0;
        ROWSDOEComputed=1;
        for (i=0 ; i < Length; i++){
            Li=LevelCntrlArrayGet(0, i, LevelMaxIndex);
             ROWSDOEComputed=ROWSDOEComputed+(Li-1);
        }
        ROWSDOERecommended=ROWSDOEComputed;
        for (i=1 ; i < Length; i++){
            Li=LevelCntrlArrayGet(0, i, LevelMaxIndex);
            
            if((Li >0) && (ROWSDOERecommended % Li==0))
                ;
            else if(Li >0) {
                ROWSDOERecommended++;
                i=0;
            }
        }
        System.out.print(System.lineSeparator());
        System.out.println("ComputeROWSDOETaguchi:ROWSDOEComputed=" + ROWSDOEComputed + " ROWSDOE=" + ROWSDOE + " ROWSDOERecommended=" + ROWSDOERecommended);
        System.out.print(System.lineSeparator());
    }
    public static void main(String... args) {
        //Uncomment below Line for Normal Usage of DOEMatrixGen
        pmain(args);
        //Starting Test Case :21 to 34, 39,40 , 101 to 104, 201 to 209
        String StartMyTestCase = "-1";//Test Case Number : Optiona :All or a Single Number
        String EndMyTestCase = "29";//Test Case Number : Optiona :All or a Single Number
        String TestCaseType = "LimitedRegression";//Test Suite Type:Individual OR LimitedRegression OR Regression
        TestHarnessDOE MyTestData = new TestHarnessDOE( StartMyTestCase, EndMyTestCase, TestCaseType);
        //Uncomment below Line for DOEMatrixGen Self Regression Test
        //mainRegresssionDOE( MyTestData);
    }
}