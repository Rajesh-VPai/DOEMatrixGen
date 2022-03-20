/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import Calculus.ConsoleColors;
import Calculus.MathsContxtLAv;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static Calculus.UsageCalculus.DiffWithRespTo;
import static RandomNumber.RandomNumber.DetectPattern;
import static RandomNumber.RandomNumber.DetectSeriesPattern;
import static RandomNumber.RandomNumber.GuessNextIthBit;
import static RandomNumber.RandomNumber.PrintOutStats;
import static RandomNumber.RandomNumber.PrintOutStatsSeriesVariables;
import static RandomNumber.RandomNumber.ReadFromFile;
import static RandomNumber.RandomNumber.ReadFromFileSeries;
import static RandomNumber.RandomNumber.SeriesStats;
import static RandomNumber.RandomNumber.Stats;
import static RandomNumber.RandomNumber.WriteToFile;
import static RandomNumber.RandomNumber.WriteToFileSeries;
import static RandomNumber.RandomSeries.GuessNextIthBitSeries;
import static RandomNumber.XORRandom.WriteToFileXOR;
import static RandomNumber.XORRandom.WriteToFileXORCircular;
import static RandomNumber.XORRandom.XOR_SandCastle;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class UserResearchStudy {

    public static Logger log = Logger.getLogger(UserResearchStudy.class.getName());
    public static boolean DebugFlag=true;
    public static Map<String, String> functionsDiff = new HashMap<>();
    private static MathsContxtLAv.Expression RandStudyValue = (() -> 0.0);
    //Entropy Pool Parameters
    public static Rand_Pool[] EntPool = null;
    public static int EntPoolIndex = 5;
    public static int PoolIndexSeries = 6;

    public static String Path = "data\\";

    public static long SeedSimple = 41;
    public static long SeedDyDx = 29;
    public static long SeedXOR = 31;

    //Entropy Pool Parameters
    public static int PatternLength = 100;
    public static int[] PatternFreqMaxIndex = new int[(int) (EntPoolIndex)];
    public static int[] PatternFreqMaxIndexSeries = new int[(int) (PoolIndexSeries)];

    public static LinkedList LCGPatternLastBits = new LinkedList();
    public static LinkedList LCGDyDxPatternLastBits = new LinkedList();
    public static long PatternRepLength = 500;

    // Performance Parameters
    public static boolean InitFlagRecordTime = false;
    public static boolean InitFlagXORRecordTime = false;
    public static boolean FactorialInitFlagRecordTime = false;
    public static boolean LowInitFlagRecordTime = false;
    public static boolean LowInitFlagXORRecordTime = false;
    public static boolean LowFactorialInitFlagRecordTime = false;
    public static String ReadExpireStr = "Number";//Number,FactorNumberOfBits,Linear
    public static long ReadExpireFactor = 3;
    public static long ReadExpireMax = 100;
    public static long ReadExpireNumber = 0;
    public static boolean ReadExpireFlag = false;

    public static double CompletionTime = 0.0;
    public static double CompletionTimeOverall = 0.0;

    //User Tweaks Begin
    public static long Xn_XOR = 3;
    public static double Xn_Circular = 3.0;
    public static double Radius = 5.0;
    public static int NumberOfStates=17;
    public static long Xn_LCG = 3;
    public static long Xn_LCGDyDx = 5;
    public static long Xn_LCG3 = 7;
    public static long Xn_LCGDyDx4 = 11;
    public static long Xn_Series0 = 0;
    public static long Xn_Series1 = 0;
    public static long Xn_Series2 = 0;
    public static long Xn_Series3 = 0;
    public static long Xn_Series4 = 0;
    public static long Xn_Series5 = 0;

    public static long Modulus = 128;
    public static long NoOfBits = 500;
    public static long NoOfSlots = Modulus + 2;
    public static long NoOfBuffBitsMax = Modulus;
    //Equation Common Parameters Below
    public static long MaxExponent = 23;
    public static long ModulusEncrypt = 7;
    public static long a = 43;
    public static long c = 47;
    public static long b = 53;
    public static long d = 59;
    public static String StudyType = "";
    public static String RunNumber = "_0005";

    public static long Success = 0;
    public static long FailedMesg = 0;
    
    public static void mymain(String[] args) {
        int xLocal = 0, mLocal = 0, cLocal = 0, opLocal = '+';
        Map<String, Double> variables = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();

        UserTweaks();
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Started Time:" + current + " RunNumber=" + RunNumber);
        }
        if (ReadExpireStr.equalsIgnoreCase("Number")) {
            ReadExpireMax = ReadExpireMax;
        } else if (ReadExpireStr.equalsIgnoreCase("FactorNumberOfBits")) {
            ReadExpireMax = NoOfBits / ReadExpireFactor;
        } else if (ReadExpireStr.equalsIgnoreCase("Linear")) {
            ReadExpireMax = ReadExpireMax + NoOfBits / ReadExpireFactor;
        }
        //Init Parameters
        PatternLength = (int) NoOfSlots;
        //MySimple && Factorial Objects
        EntPool = new Rand_Pool[EntPoolIndex + PoolIndexSeries];
        EntPool[0] = new Rand_Pool("RandomSimpleLCG", 0, NoOfSlots, ModulusEncrypt, Xn_LCG, Xn_LCGDyDx, a, c, b, MaxExponent);
        EntPool[1] = new Rand_Pool("RandomFactorialLCGDyDx", 1, NoOfSlots, ModulusEncrypt, Xn_LCG, Xn_LCGDyDx, a, c, b, MaxExponent);
        EntPool[2] = new Rand_Pool("RandomSimpleLCG", 2, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 2, c + 4, b + 6, MaxExponent);
        EntPool[3] = new Rand_Pool("RandomFactorialLCGDyDx", 3, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        //XOR Object
        EntPool[4] = new Rand_Pool("XORRandom", 4, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        //Series Objects
        EntPool[5] = new Rand_Pool("Series", 5, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        EntPool[6] = new Rand_Pool("Series", 6, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        EntPool[7] = new Rand_Pool("Series", 7, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        EntPool[8] = new Rand_Pool("Series", 8, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        EntPool[9] = new Rand_Pool("Series", 9, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        EntPool[10] = new Rand_Pool("Series", 10, NoOfSlots, ModulusEncrypt, Xn_LCG3, Xn_LCGDyDx4, a + 1, c + 3, b + 7, MaxExponent);
        System.out.println("main:RunNumber=" + RunNumber + " Xn_XOR=" + Xn_XOR + " Xn_LCG=" + Xn_LCG + " Xn_LCGDyDx=" + Xn_LCGDyDx + " Xn_LCG3=" + Xn_LCG3 + " Xn_LCGDyDx4=" + Xn_LCGDyDx4);
        System.out.println("main:NoOfBits=" + NoOfBits + " NoOfSlots=" + NoOfSlots + " NoOfBuffBitsMax=" + NoOfBuffBitsMax + " Modulus=" + Modulus + " PatternRepLength=" + PatternRepLength);
        System.out.println("main:FileRead/Write Parameters:ReadExpireNumber=" + ReadExpireNumber + " ReadExpireMax=" + ReadExpireMax + " ReadExpireFactor=" + ReadExpireFactor + " ReadExpireStr=" + ReadExpireStr);
        System.out.println("main:Low Level Performance Parameters:MaxExponent=" + MaxExponent + " ModulusEncrypt=" + ModulusEncrypt);
        System.out.println("main:Low Level Performance Parameters:a=" + a + " c=" + c + " b=" + b + " d=" + d + " SeedSimple=" + SeedSimple + " SeedDyDx=" + SeedDyDx);
        System.out.println("main:Low Level Uniqueness Parameters:LCG:ConfuseValue=" + EntPool[0].MySimple.ConfuseValue + " DiffusionValue=" + EntPool[0].MySimple.DiffusionValue + " SupportValue=" + EntPool[0].MySimple.SupportValue + " SurprisalValue=" + EntPool[0].MySimple.SurprisalValue);
        System.out.println("main:Low Level Uniqueness Parameters:LCGDyDx:ConfuseFactorial=" + EntPool[1].MyFactorial.ConfuseFactorial + " DiffusionFactorial=" + EntPool[1].MyFactorial.DiffusionFactorial + " SupportFactorial=" + EntPool[1].MyFactorial.SupportFactorial + " SurprisalFactorial=" + EntPool[1].MyFactorial.SurprisalFactorial);
        String Exprn = "";
        for (int i = 0; i < NoOfBits; i++) {

            Xn_LCG = EntPool[0].MySimple.LCG_Value_RandomSimple(Xn_LCG, (long) a, (long) c, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
            Xn_LCGDyDx = EntPool[1].MyFactorial.LCG_Factorial_RandomSimple(Xn_LCGDyDx, (long) a, (long) c, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
            Xn_LCG3 = EntPool[2].MySimple.LCG_Value_RandomSimple(Xn_LCG3, (long) a + 2, (long) c + 4, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
            Xn_LCGDyDx4 = EntPool[3].MyFactorial.LCG_Factorial_RandomSimple(Xn_LCGDyDx4, (long) a + 1, (long) c + 3, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
            Xn_XOR = EntPool[4].MyXOR.LCG_XOR_Random(Xn_XOR, (int) a, (int) c, (int) b, (int) d, (int) Modulus, (int) MaxExponent, (int) ModulusEncrypt);
            StudyComputerEntropy(i);
            //StudyRandomValues(i);
            //StudyRandomVariables(i);
            //StudyLogicValues(i);
            //StudyXORLogicValues(i);
            //StudyRandomLinearEqns(i);
            //StudyRandomSecurity(i);
            if ((Xn_XOR == 0) || (Xn_XOR < 1)) {
                Xn_XOR = 1;
            }
            if (Xn_LCG == 0) {
                Xn_LCG = 1;
            } else if (Xn_LCG < 1) {
                System.out.println("main:Negative Number:Before:Xn_LCG=" + Xn_LCG);
                Xn_LCG = -Xn_LCG;
                System.out.println("main:Negative Number:After:Xn_LCG=" + Xn_LCG);
            }
            if (Xn_LCG3 == 0) {
                Xn_LCG3 = 1;
            } else if (Xn_LCG3 < 1) {
                System.out.println("main:Negative Number:Before:Xn_LCG3=" + Xn_LCG3);
                Xn_LCG3 = -Xn_LCG;
                System.out.println("main:Negative Number:After:Xn_LCG3=" + Xn_LCG3);
            }
            if ((Xn_LCGDyDx == 0) || (Xn_LCGDyDx < 1)) {
                Xn_LCGDyDx = 1;
            }
            if ((Xn_LCGDyDx4 == 0) || (Xn_LCGDyDx4 < 1)) {
                Xn_LCGDyDx4 = 1;
            }
            if (Xn_Series0 == 0) {
                Xn_Series0 = 1;
            } else if (Xn_Series0 < 1) {
                //System.out.println("main:Negative Number:Before:Xn_Series0=" + Xn_Series0);
                Xn_Series0 = -Xn_Series0;
                //System.out.println("main:Negative Number:After:Xn_Series0=" + Xn_Series0);
            }
            if (Xn_Series1 == 0) {
                Xn_Series1 = 1;
            } else if (Xn_Series1 < 1) {
                //System.out.println("main:Negative Number:Before:Xn_Series1=" + Xn_Series1);
                Xn_Series1 = -Xn_Series1;
                //System.out.println("main:Negative Number:After:Xn_Series1=" + Xn_Series1);
            }
            if (Xn_Series2 == 0) {
                Xn_Series2 = 1;
            } else if (Xn_Series2 < 1) {
                //System.out.println("main:Negative Number:Before:Xn_Series2=" + Xn_Series2);
                Xn_Series2 = -Xn_Series2;
                //System.out.println("main:Negative Number:After:Xn_Series2=" + Xn_Series2);
            }
            if (Xn_Series3 == 0) {
                Xn_Series3 = 1;
            } else if (Xn_Series3 < 1) {
                //System.out.println("main:Negative Number:Before:Xn_Series3=" + Xn_Series3);
                Xn_Series3 = -Xn_Series3;
                //System.out.println("main:Negative Number:After:Xn_Series3=" + Xn_Series3);
            }
            //Not Sure why Guess% goes to 100 %
            //when below lines are commented
            xLocal = (int) Xn_LCG;
            mLocal = (int) Xn_LCGDyDx;
            cLocal = (int) Xn_LCG3;
            opLocal = (int) Xn_Series0 % 2;
            if (opLocal == 0) {
                opLocal = '+';
            } else {
                opLocal = '-';
            }
            Exprn = "mLocal*xLocal" + (char) opLocal + "cLocal";
            Exprn = eatAll(Exprn, ' ');
            variables.put("xLocal", ((double) xLocal));
            variables.put("mLocal", ((double) mLocal));
            variables.put("cLocal", ((double) cLocal));
            if (i == 0) {
                RandStudyValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
            }
            Xn_Series4 = (long) ((RandStudyValue.eval()) % (Modulus));
            //LowLevelPrintOut(i,StudyType);
            if (Xn_Series4 == 0) {
                Xn_Series4 = 1;
            } else if (Xn_Series4 < 1) {
                //System.out.println("main:Negative Number:Before:Xn_Series4=" + Xn_Series4);
                Xn_Series4 = -Xn_Series4;
                //System.out.println("main:Negative Number:After:Xn_Series4=" + Xn_Series4);
            }
            Xn_Series5=(long)Xn_Circular;
            WriteToFileXOR(i, Xn_XOR, NoOfBuffBitsMax, RunNumber);
            WriteToFileXORCircular(i, (long)Xn_Circular, NoOfBuffBitsMax, RunNumber);
            WriteToFile(i, Xn_LCG, Xn_LCGDyDx, Xn_LCG3, Xn_LCGDyDx4, NoOfBuffBitsMax, RunNumber);
            WriteToFileSeries(i, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4, Xn_Series5, NoOfBuffBitsMax, RunNumber);
            Stats(0, Xn_LCG, Xn_LCGDyDx, Xn_LCG3, Xn_LCGDyDx4, Xn_XOR);
            SeriesStats(0, NoOfSlots, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4, Xn_Series5);
            ReadFromFile(0, NoOfBuffBitsMax, Xn_LCG, Xn_LCGDyDx, Xn_LCG3, Xn_LCGDyDx4, Xn_XOR, RunNumber);
            ReadFromFileSeries(0, NoOfBuffBitsMax, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4,Xn_Series5, RunNumber);
            DetectPattern(0, NoOfSlots, Xn_LCG, Xn_LCGDyDx, Xn_LCG3, Xn_LCGDyDx4, Xn_XOR);
            DetectSeriesPattern(0, NoOfSlots, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4,Xn_Series5);
            GuessNextIthBit(0, i, Xn_LCG, Xn_LCGDyDx, Xn_LCG3, Xn_LCGDyDx4, Xn_XOR);
            GuessNextIthBitSeries(0, i, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4 , Xn_Series5);
            //System.out.println("main:Xn_XOR=" + Xn_XOR + " Xn_LCG=" + Xn_LCG + " Xn_LCGDyDx=" + Xn_LCGDyDx + " Xn_LCG3=" + Xn_LCG3 + " Xn_LCGDyDx4=" + Xn_LCGDyDx4);
            LowLevelPrintOut(i, "StudyComputerEntropy");
        }
        PrintOutStats(0, NoOfSlots, NoOfBits);
        //PrintOutStatsSeriesValues( NoOfSlots, NoOfBits);
        PrintOutStatsSeriesVariables( NoOfSlots, NoOfBits);
        //PrintOutStatsSeriesLogic(NoOfSlots, NoOfBits);
        //PrintOutStatsSeriesImplies(NoOfSlots, NoOfBits);
        //PrintOutStatsSeriesLinEqn(NoOfSlots, NoOfBits);
        System.out.println("main:Success=" + Success + " FailedMesg=" + FailedMesg + " NoOfBits=" + NoOfBits);
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Completed Time:" + current + " " + " RunNumber=" + RunNumber);
        }
    }

    public static void main(String... args) {
        mymain(args);
        //xmain(args);
    }

    static void xmain(String... args) {
        String All = "-1"; // Test Case Number : Options :All or a Single Number
        String StartMyTestCase = "-1";
        String MyEndTestCase = "-1";//Test Case Number : Options :All or a Single Number
        String RegrORIndivORLimit = "LimitedRegression";

        DOETest.TestHarnessDOE TestData = new DOETest.TestHarnessDOE(All, MyEndTestCase, RegrORIndivORLimit);
        DOETest.UsageDOE.mainRegresssionDOE(TestData);
    }

    static void UserTweaks() {
        DebugFlag=false;
        Xn_XOR = 1;
        Radius= 3.0;
        NumberOfStates=17;
        Xn_LCG = 3;
        Xn_LCGDyDx = 5;
        Xn_LCG3 = 7;
        Xn_LCGDyDx4 = 11;
        Modulus = 5000;
        NoOfBits = 100;
        NoOfSlots = Modulus + 2;
        NoOfBuffBitsMax = Modulus;
        //Equation Common Parameters Below
        MaxExponent = 23;
        ModulusEncrypt = 7;
        a = 3;
        c = 7;
        b = 3;
        d = 9;
        StudyType = "";
        // Seed Initialization
        SeedSimple = 41;
        SeedDyDx = 29;
        SeedXOR = 31;
    }
static void StudyComputerEntropy(int i) {
        if (i == 0) {
            StudyType = "StudyComputerEntropy";
            System.out.println("***************StudyComputerEntropy************");
        }
        Xn_Circular=XOR_SandCastle("main", (long) Xn_Circular, (long)Radius,  a,  c, b,  d,  Modulus, (i%NumberOfStates) );
        //Xn_Circular=XOR_ComputerEntropyValues("main", (long) Xn_Circular, (long)Radius,  a,  c, b,  d,  Modulus);
        //Xn_Circular=XOR_CircularRandom("main", (long) Xn_Circular, (long)Radius,  a,  c, b,  d,  Modulus);
        //Xn_Circular=XOR_CounterFlipFlop("main", (long) Xn_Circular, (long)Radius,  a,  c, b,  d,  Modulus);
        //Xn_Circular=XOR_ComputerEntropyJavaCalculus("main", (long) Xn_Circular, (long)Radius,  a,  c, b,  d,  Modulus);
    }
    static void StudyRandomValues(int i) {
        if (i == 0) {
            StudyType = "StudyRandomValues";
            System.out.println("***************StudyRandomValues************");
        }
        Xn_Series0 = (Xn_LCG + Xn_LCGDyDx) % (NoOfSlots);
        Xn_Series1 = (Xn_LCG - Xn_LCG3) % (NoOfSlots);
        Xn_Series2 = (Xn_LCGDyDx - Xn_LCGDyDx4) % (NoOfSlots);
        if (Xn_LCG > 64) {
            Xn_Series3 = (Xn_LCG3) % (NoOfSlots);
        }else
            Xn_Series3 = 2;
    }
   static void StudyRandomVariables(int i) {
         if (i == 0) {
            StudyType = "StudyRandomVariables";
            System.out.println("***************StudyRandomVariables************");
        }
        if (Xn_LCG > 64) {
            Xn_Series0 = (Xn_LCG3) % (NoOfSlots);
        }else
            Xn_Series0 = 2;
        if (Xn_LCG > 50) {
            Xn_Series1 = (5000) % (Xn_LCG);
        }else
            Xn_Series1 = 2;
        if (Xn_LCG > 50) {
            Xn_Series2 = (i) % (Xn_LCG);
        }else
            Xn_Series2 = 2;
    }
    static void StudyLogicValues(int i) {
        if (i == 0) {
            StudyType = "StudyLogicValues";
            System.out.println("***************StudyLogicValues************");
        }
        if (Xn_LCG > Xn_LCGDyDx) {
            Xn_Series0 = (Xn_LCG) % (NoOfSlots);
            //To Keep Dataflow Concepts intact
            Xn_Series1 = 0;
            Xn_Series2 = 0;
            Xn_Series3 = 0;
        } else if (Xn_LCG < Xn_LCGDyDx) {
            //To Keep Dataflow Concepts intact
            Xn_Series0 = 0;
            Xn_Series1 = (Xn_LCG) % (NoOfSlots);
            //To Keep Dataflow intact
            Xn_Series2 = 0;
            Xn_Series3 = 0;
        } else if (Xn_LCG == Xn_LCGDyDx) {
            //To Keep Dataflow Concepts intact
            Xn_Series0 = 0;
            Xn_Series1 = 0;
            Xn_Series2 = (Xn_LCG) % (NoOfSlots);
            Xn_Series3 = 0;
        }// Below CODE will never execute as Operator can be > or < or ==

    }

    static void StudyXORLogicValues(int i) {
        if (i == 0) {
            StudyType = "StudyXORLogicValues";
            System.out.println("***************StudyXORLogicValues************");
        }
        //P=(Xn_LCG > Xn_LCGDyDx)
        //Q=(Xn_LCG3 > Xn_LCGDyDx4)
        //p=> q= ((p^q)&&(p' v (OR) q')
        if (((Xn_LCG > Xn_LCGDyDx) && (Xn_LCG3 > Xn_LCGDyDx4))
                && ((!(Xn_LCG > Xn_LCGDyDx)) && (!(Xn_LCG3 > Xn_LCGDyDx4)))) {
            Xn_Series0 = (Xn_LCG) % (NoOfSlots);
            //To Keep Dataflow Concepts intact
            Xn_Series1 = 0;
            Xn_Series2 = 0;
        } // If NOT ((p=> q))
        else if (!(((Xn_LCG > Xn_LCGDyDx) && (Xn_LCG3 > Xn_LCGDyDx4))
                && ((!(Xn_LCG > Xn_LCGDyDx)) && (!(Xn_LCG3 > Xn_LCGDyDx4))))) {
            //To Keep Dataflow Concepts intact
            Xn_Series0 = 0;
            Xn_Series1 = (Xn_LCG) % (NoOfSlots);
            //To Keep Dataflow intact
            Xn_Series2 = 0;
        } else {
            //To Keep Dataflow Concepts intact
            Xn_Series0 = 0;
            Xn_Series1 = 0;
            Xn_Series2 = 0;
        }
    }

    static void StudyRandomLinearEqns(int i) {
        MathsContxtLAv.Expression RandStudyValue0 = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValue1 = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValue2 = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValue3 = (() -> 0.0);
        Map<String, Double> variables = new HashMap<>();
        String Exprn = "";
        if (i == 0) {
            StudyType = "StudyRandomLinearEquations";
            System.out.println("***************StudyRandomLinearEquations************");
        }
        Exprn = "i*Xn";
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Xn_LCG));
        variables.put("i", ((double) i));
        RandStudyValue0 = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Xn_Series0 = (long) ((RandStudyValue0.eval()) % (Modulus));

        Exprn = "i*Xn" + "+" + "c";
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Xn_LCG));
        variables.put("i", ((double) i));
        variables.put("c", ((double) c));
        RandStudyValue1 = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Xn_Series1 = (long) ((RandStudyValue1.eval()) % (Modulus));

        Exprn = "m*Xn";
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Xn_LCG));
        variables.put("m", ((double) Xn_LCGDyDx));
        RandStudyValue2 = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Xn_Series2 = (long) ((RandStudyValue2.eval()) % (Modulus));

        Exprn = "m*Xn" + "+" + "c";
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Xn_LCG));
        variables.put("m", ((double) 2.0));
        variables.put("c", ((double) c));
        RandStudyValue3 = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Xn_Series3 = (long) ((RandStudyValue3.eval()) % (Modulus));
    }

    static void StudyRandomSecurity(int i) {
        System.out.println("StudyRandomSecurity:");
        //SymmetricKey(i);
        //PKILin(i);
        PKINonLin(i);
    }

    static void SymmetricKey(int i) {
        MathsContxtLAv.Expression RandStudyValueEncryption = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValueDecryption = (() -> 0.0);
        Map<String, Double> variables = new HashMap<>();
        String EqnUnderTest = "m*Xn+c";
        String InverseEqnUnderTest = "(y-c)/m";
        int Mychar = 'a';
        String Exprn = EqnUnderTest;
        if (i == 0) {
            StudyType = "StudyRandomSecurity";
            System.out.println("***************StudyRandomLinearEquations************");
        }
        System.out.println("SymmetricKey:Symmetric");
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Mychar));
        variables.put("m", ((double) Xn_LCG));
        variables.put("c", ((double) c));
        RandStudyValueEncryption = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Double Value = (RandStudyValueEncryption.eval());
        System.out.println("SymmetricKey:Symmetric Key:Encryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCG);
        Xn_Series0 = (long) (Value % Modulus);

        Exprn = InverseEqnUnderTest;
        Exprn = eatAll(Exprn, ' ');
        variables.put("y", ((double) Value));
        variables.put("m", ((double) Xn_LCG));
        variables.put("c", ((double) c));
        RandStudyValueDecryption = parse(Exprn, variables, "y", "Silent", MathsContxtLAv.LogLevel.Off);
        Value = (RandStudyValueDecryption.eval());
        System.out.println("SymmetricKey:Symmetric Key:Decryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCG);
        Xn_Series1 = (long) (Value % Modulus);
        if(Mychar==Value.intValue()){
            System.out.println(ConsoleColors.GREEN + "SymmetricKey:Information Sucess:" + " Mychar=" + Mychar + " Value=" + Value + ConsoleColors.RESET);
            Success++;
        }
        else {
          System.out.println(ConsoleColors.RED + "SymmetricKey:Information Loss:" + " Mychar=" + Mychar + " Value=" + Value + ConsoleColors.RESET);
          FailedMesg++;
        }
    }

    static void PKILin(int i) {
        MathsContxtLAv.Expression RandStudyValueEncryption = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValueDecryption = (() -> 0.0);
        Map<String, Double> variables = new HashMap<>();
        System.out.println("PKI:");
        String EqnUnderTest = "m*Xn+c";
        String InverseEqnUnderTest = "(y-c)/m";
        int Mychar = 'a';
        String Exprn = EqnUnderTest;
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Mychar));
        variables.put("m", ((double) Xn_LCG));
        variables.put("c", ((double) c));
        RandStudyValueEncryption = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Double Value = (RandStudyValueEncryption.eval());
        System.out.println("PKI:PKI Key:Encryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCG + " Xn="+ variables.get("Xn"));
        Xn_Series2 = (long) (Value % Modulus);

        Exprn = InverseEqnUnderTest;
        Exprn = eatAll(Exprn, ' ');
        variables.put("y", ((double) Value));
        variables.put("m", ((double) Xn_LCGDyDx));
        variables.put("c", ((double) c));
        RandStudyValueDecryption = parse(Exprn, variables, "y", "Silent", MathsContxtLAv.LogLevel.Off);
        Value = (RandStudyValueDecryption.eval());
        System.out.println("PKI:PKI Key:Decryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCGDyDx + " y=" + variables.get("y"));
        Xn_Series3 = (long) (Value % Modulus);
        if(Mychar==Math.rint(Value)){
            System.out.println(ConsoleColors.GREEN + "PKI:PKI Key:Information Sucess:" + " Mychar=" + Mychar + " Value=" + Math.rint(Value) + ConsoleColors.RESET);
            Success++;
        }
        else {
          System.out.println(ConsoleColors.RED + "PKI:PKI Key:Information Loss:" + " Mychar=" + Mychar + " Value=" + Math.rint(Value) + ConsoleColors.RESET);
          FailedMesg++;
        }
    }
 static void PKINonLin(int i){
     MathsContxtLAv.Expression RandStudyValueEncryption = (() -> 0.0);
        MathsContxtLAv.Expression RandStudyValueDecryption = (() -> 0.0);
        Map<String, Double> variables = new HashMap<>();
        System.out.println("PKI:");
        String EqnUnderTest = "m*Xn^5+c";
        String InverseEqnUnderTest = "((y-c)/m)^(1/5)";
        int Mychar = 'a';
        String Exprn = EqnUnderTest;
        Exprn = eatAll(Exprn, ' ');
        variables.put("Xn", ((double) Mychar));
        variables.put("m", ((double) Xn_LCG));
        variables.put("c", ((double) c));
        RandStudyValueEncryption = parse(Exprn, variables, "Xn", "Silent", MathsContxtLAv.LogLevel.Off);
        Double Value = (RandStudyValueEncryption.eval());
        System.out.println("PKI:PKI Key:Encryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCG + " Xn="+ variables.get("Xn"));
        Xn_Series2 = (long) (Value % Modulus);

        Exprn = InverseEqnUnderTest;
        Exprn = eatAll(Exprn, ' ');
        variables.put("y", ((double) Value));
        variables.put("m", ((double) Xn_LCGDyDx));
        variables.put("c", ((double) c));
        RandStudyValueDecryption = parse(Exprn, variables, "y", "Silent", MathsContxtLAv.LogLevel.Off);
        Value = (RandStudyValueDecryption.eval());
        System.out.println("PKI:PKI Key:Decryption:Value=" + Value + " Mychar=" + Mychar + " m=" + variables.get("m") + " Random Value=" + Xn_LCGDyDx + " y=" + variables.get("y"));
        Xn_Series3 = (long) (Value % Modulus);
        if(Mychar==Math.rint(Value)){
            System.out.println(ConsoleColors.GREEN + "PKI:PKI Key:Information Sucess:" + " Mychar=" + Mychar + " Value=" + Math.rint(Value) + ConsoleColors.RESET);
            Success++;
        }
        else {
          System.out.println(ConsoleColors.RED + "PKI:PKI Key:Information Loss:" + " Mychar=" + Mychar + " Value=" + Math.rint(Value) + ConsoleColors.RESET);
          FailedMesg++;
        }
 }
    static void LowLevelPrintOut(int i, String StudyType) {
        System.out.println("*******************************:" + StudyType + ": i=" + i + " Begin*******************************");
        System.out.println("main:Xn_XOR=" + Xn_XOR);
        System.out.println("main:Xn_Circular=" + Xn_Circular);
        System.out.println("main:Xn_LCG=" + Xn_LCG);
        System.out.println("main:Xn_LCGDyDx=" + Xn_LCGDyDx);
        System.out.println("main:Xn_LCG3=" + Xn_LCG3);
        System.out.println("main:Xn_LCGDyDx4=" + Xn_LCGDyDx4);
        System.out.println("main:Xn_Series0=" + Xn_Series0);
        System.out.println("main:Xn_Series1=" + Xn_Series1);
        System.out.println("main:Xn_Series2=" + Xn_Series2);
        System.out.println("main:Xn_Series3=" + Xn_Series3);
        System.out.println("main:Xn_Series4=" + Xn_Series4);
        System.out.println("main:Xn_Series5=" + Xn_Series5);
        System.out.println("*******************************:" + StudyType + ": i=" + i + " End*******************************");

    }
}
