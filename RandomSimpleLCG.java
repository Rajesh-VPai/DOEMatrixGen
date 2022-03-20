/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import Calculus.DiffrIntegrSep;
import static Calculus.DiffrIntegrSep.parseDiff;
import Calculus.MathsContxtLAv;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import static RandomNumber.UserResearchStudy.CompletionTime;
import static RandomNumber.UserResearchStudy.CompletionTimeOverall;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.InitFlagRecordTime;
import static RandomNumber.UserResearchStudy.LCGPatternLastBits;
import static RandomNumber.UserResearchStudy.LowInitFlagRecordTime;
import static RandomNumber.UserResearchStudy.NoOfBits;
import static RandomNumber.UserResearchStudy.NoOfBuffBitsMax;
import static RandomNumber.UserResearchStudy.NoOfSlots;
import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.PatternFreqMaxIndex;
import static RandomNumber.UserResearchStudy.PatternLength;
import static RandomNumber.UserResearchStudy.PatternRepLength;
import static RandomNumber.UserResearchStudy.SeedSimple;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class RandomSimpleLCG {

    public static Logger log = Logger.getLogger(RandomSimpleLCG.class.getName());
    public static boolean LCGXnplus1Avail = false;

    //Theory & Source:Wiki Confusion and Diffusion
    public long ConfuseValue = 1;
    public long DiffusionValue = 2;
    //Theory & Source:Wiki Information Content
    public long SupportValue = 3;
    public long SurprisalValue = 4;

    private long LCGXValuemin = 0;
    private long LCGXValuemax = 0;
    private long LCGXValueInc = 0;

    private MathsContxtLAv.SimpleExpression LCGexpSimple = (() -> "");
    private MathsContxtLAv.Expression LCGexpValue = (() -> 0.0);
    private boolean LCGexpValueObjExpire = false;
    private MathsContxtLAv.Expression LCGexpValue2 = (() -> 0.0);
    private boolean LCGexpValueObjExpire2 = false;
    private DiffrIntegrSep.DiffExpr LCGexp = (() -> "");
    private boolean LCGexpObjExpire = false;

    // Core Variables
    public String ExprnLCG = "";
    public String ConfuseExprnLCG = "";
    public String DiffusionExprnLCG = "";
    public String SupportExprnLCG = "";
    public String SurprisalExprnLCG = "";

    // I+1th Bit (NextIth Bit)
    //Theory & Source:Wiki next-bit Test
    public long NextIthLCGPatternRep = 0;
    public long NextIthLCGPattern = 0;
    public long NextIthLCGBuffer = 0;
    public long NextIthLCGPatRecog = 0;

    public long GuessLCG = 0;
    public long GuessLCGPatRecog = 0;
    public long GuessLCGRep = 0;
    public long GuessLCGPattern = 0;
    public long GuessLCGBuff = 0;

    //    LCG Pattern: 1 5 4 5 3 5 4 4 7 1 1 5 7 7 7 7 5 1 7 1 3 3 2 4 3 1 6 6 2 2 1 2 3 5   
    public static long[] LCGPattern = {1, 53, 48, 36, 71, 31, 122, 1, 5, 4, 5, 3, 5, 4, 4, 7, 1, 1, 5, 7, 7, 7, 7, 5, 1, 7, 1, 3, 3, 2, 4, 3, 1, 6, 6, 2, 2, 1, 2, 3, 5};
    public LinkedList LCGNextBitDataBuff = new LinkedList();

    public ArrayList<PatternBits> DataStoreLCG = null;
    
    // Stats Data Structures
    public double[] FreqStore = null;
    public double[][] FreqMaxStore = null;
    public double[] GaussianPDFStore = null;
    public double[] EntropyPDFStore = null;
    public double[] MeanSigma = null;

    public double Mean = 0.0;
    public double Sigma = 0.0;

    // Read Write File Control Mechanism
    public String ReadExpireStr = "Number";//Number,FactorNumberOfBits,Linear
    public long ReadExpireFactor = 3;
    public long ReadExpireMax = 1000;
    public long ReadExpireNumber = 0;
    public boolean ReadExpireFlag = false;
    
    //InitValues for Documentation -Variables
    public static Map<String, String> InitValue = new HashMap<>();

    public RandomSimpleLCG(int Index, long NoOfSlots, long ModulusEncrypt, long Xn_LCG, long a, long c, long b, long MaxExponent) {
        MaxExponent = MaxExponent + Index;
        // Form the Equations
        ExprnLCG = FormEquationLocal(Xn_LCG, a, c, b, "Xn", (int) MaxExponent);
        ConfuseExprnLCG = FormEquationSimpleLocal(Xn_LCG, a, c, b, "Xn", (int) ModulusEncrypt);
        DiffusionExprnLCG = FormEquationSimpleLocal(Xn_LCG, a, c, b, "Xn", (int) ModulusEncrypt);
        SupportExprnLCG = FormEquationSupportLocal(Xn_LCG, a, c, b, "Xn", (int) ModulusEncrypt);
        SurprisalExprnLCG = FormEquationSurpriseLocal(Xn_LCG, a, c, b, "Xn", (int) ModulusEncrypt);
        //ConfuseValue = 4 + Index;
        //DiffusionValue = 5 + Index;
        //SupportValue = 13 + Index;
        //SurprisalValue = 31 + Index;
        DocumentInitValues(Index, NoOfSlots, ModulusEncrypt, Xn_LCG, Xn_LCGDyDx, a, c, b, MaxExponent);
        FreqStore = new double[(int) NoOfSlots];
        FreqMaxStore = new double[PatternLength][2];
        GaussianPDFStore = new double[(int) NoOfSlots];
        EntropyPDFStore = new double[(int) NoOfSlots];
        MeanSigma = new double[3];
        for (int j = 0; j < NoOfSlots; j++) {
            FreqStore[j] = 0.0;
            GaussianPDFStore[j] = 0.0;
            EntropyPDFStore[j] = 0.0;
        }
        for (int j = 0; j < 3; j++) {
            MeanSigma[j] = 0.0;
        }
        for (int j = 0; j < 2; j++) {
            for (int k = 0; k < PatternLength; k++) {
                FreqMaxStore[k][j] = 0.0;
            }
        }
        DataStoreLCG = new ArrayList<RandomSimpleLCG.PatternBits>();
        if (ReadExpireStr.equalsIgnoreCase("Number")) {
            ReadExpireMax = ReadExpireMax;
        } else if (ReadExpireStr.equalsIgnoreCase("FactorNumberOfBits")) {
            ReadExpireMax = NoOfBits / ReadExpireFactor;
        } else if (ReadExpireStr.equalsIgnoreCase("Linear")) {
            ReadExpireMax = ReadExpireMax + NoOfBits / ReadExpireFactor;
        }
    }

    void DocumentInitValues(long Index, long NoOfSlots, long ModulusEncrypt, long Xn_LCG, long Xn_LCGDyDx, long a, long c, long b, long MaxExponent) {
        //Init Values
        InitValue.put("InitVal" + Index, "" + Xn_LCG);
        InitValue.put("a" + Index, "" + a);
        InitValue.put("c" + Index, "" + c);
        InitValue.put("b" + Index, "" + b);
        InitValue.put("ModulusEncrypt" + Index, "" + ModulusEncrypt);
        InitValue.put("MaxExponent" + Index, "" + MaxExponent);

        //Equations
        InitValue.put("Exprn" + Index, ExprnLCG);
        InitValue.put("ConfuseExprn" + Index, ConfuseExprnLCG);
        InitValue.put("DiffusionExprn" + Index, DiffusionExprnLCG);
        InitValue.put("SupportExprn" + Index, SupportExprnLCG);
        InitValue.put("SurprisalExprn" + Index, SurprisalExprnLCG);

        // Seed Values
        InitValue.put("ConfuseValue" + Index, "" + ConfuseValue);
        InitValue.put("DiffusionValue" + Index, "" + DiffusionValue);
        InitValue.put("SupportValue" + Index, "" + SupportValue);
        InitValue.put("SurprisalValue" + Index, "" + SurprisalValue);

    }
    // The LCG_Value_Random Method uses F(x)==Xn && ODE Dy/Dx(F(x))== Xn
    // If Xn != Xn+1, Method Returns Xn+1
    // This can be viewed as a Lyapunov Function Also 
    //(Source :wikiLyapunov) where F'(x)=F(x)
    public long LCG_Value_Random(String AlgoName, String Exprn, long a, long c, long Xn, long Modulus, long MaxExponent, String EqnCompFlag) {
        if (LowInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[2]);
            System.out.println("LCG_Value_Random:Started:Run Started Time:" + current + " ");
        }
        Map<String, Double> variables = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        String DiffWithRespTo = "Xn";
        
        String StrexpSimpleExpress = "";
        String StrexpDiffExpress = "";
        int i = 1;
        long Xnplus1 = Xn;
        long TempXn = 0;
        Exprn = Exprn + "-" + a + "*" + DiffWithRespTo + "+" + c + "*" + Xn + "-" + Xn;
        Exprn = eatAll(Exprn, ' ');
        //System.out.println("Expr_LCG_Value:Exprn=" + Exprn + " EqnCompFlag=" + EqnCompFlag);
        GetExprnMinMaxRange(Xn, DiffWithRespTo, Modulus, 10);
        LCGXnplus1Avail = false;
        for (long x = LCGXValuemin; (i < 10) && ((i < 3) || (Xn == Xnplus1) || (TempXn == Xnplus1)); x += LCGXValueInc, i++) {
            variables.put(DiffWithRespTo, ((double) x));
            if (LCGexpValueObjExpire == false) {
                LCGexpValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                LCGexpValueObjExpire = true;
            }
            //F(x)==Xn 
            TempXn = (long) ((LCGexpValue.eval()) % (Modulus ));
            if ((TempXn == Xn) || (i >= 1)) {
                StrexpSimpleExpress = eatAll(Exprn, ' ');
                if (LCGexpObjExpire == false) {
                    StrexpSimpleExpress = StrexpSimpleExpress + "-" + Xnplus1;
                    StrexpSimpleExpress = eatAll(StrexpSimpleExpress, ' ');
                    LCGexp = parseDiff(StrexpSimpleExpress, variables, DiffWithRespTo);
                    LCGexpObjExpire = true;
                }
                StrexpDiffExpress = LCGexp.DiffExpr();
                StrexpSimpleExpress = eatAll(StrexpDiffExpress, ' ');
                if (LCGexpValueObjExpire2 == false) {
                    LCGexpValue2 = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                    LCGexpValueObjExpire2 = true;
                }
                Exprn = StrexpSimpleExpress;
                //ODE Dy/Dx(F(x))== Xn
                Xnplus1 = (long) ((LCGexpValue2.eval() + x) % (Modulus ));
                LCGXnplus1Avail = true;
                GetExprnMinMaxRange(Xnplus1, DiffWithRespTo, (LCGXValuemax - x), (10 - i));
            } else if (TempXn != Xnplus1) {
                Xnplus1 = (long) (LCGexpValue2.eval() % (Modulus ));
                break;
            }
        }
        if (LowInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[2]) - CompletionTime;
            System.out.println("LCG_Value_Random:Completed:Run Started Time:" + current + " " + " CompletionTime=" + CompletionTime * 1000 + " millisecs");
            LowInitFlagRecordTime = true;
        }
        //System.out.println("LCG_Value_Random:Completed:Xnplus1=" + Xnplus1 + " Modulus=" + Modulus);
        return ((long) Xnplus1);
    }

    public long LCG_Value_RandomSimple(long Xn_LCG, long a, long c, int Modulus, int MaxExponent, int ModulusEncrypt) {
        if (InitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]);
            System.out.println("LCG_Value_RandomSimple:Started:Run Started Time:" + current + " ");
        }
        //System.out.println("LCG_Value_RandomSimple:Initial:Xn_LCG=" + Xn_LCG + " ConfuseValue=" + ConfuseValue + " DiffusionValue=" + DiffusionValue);
        ResetSimpleObj();
        Xn_LCG = LCG_Value_Random("LCG_Value_RandomSimple", ExprnLCG, a, c, Xn_LCG, (long) Modulus, MaxExponent, "Xn_LCG");
        ResetSimpleObj();
        ConfuseValue += (LCG_Value_Random("LCG_Value_RandomSimple", ConfuseExprnLCG, a, c, ConfuseValue, (int) ModulusEncrypt, ModulusEncrypt, "ConfuseValue") % (ModulusEncrypt));
        ResetSimpleObj();
        DiffusionValue += (LCG_Value_Random("LCG_Value_RandomSimple", DiffusionExprnLCG, a, c, DiffusionValue, (int) ModulusEncrypt, ModulusEncrypt, "DiffusionValue") % (ModulusEncrypt));
        ResetSimpleObj();
        SupportValue -= (LCG_Value_Random("LCG_Value_RandomSimple", SupportExprnLCG, a, c, SupportValue, (int) ModulusEncrypt, ModulusEncrypt, "SupportValue") % (ModulusEncrypt));
        ResetSimpleObj();
        SurprisalValue += (LCG_Value_Random("LCG_Value_RandomSimple", SurprisalExprnLCG, a, c, SurprisalValue, (int) ModulusEncrypt, ModulusEncrypt, "SurpriseValue") % (ModulusEncrypt));
        //System.out.println("LCG_Value_RandomSimple:Middle:Xn_LCG=" + Xn_LCG + " ConfuseValue=" + ConfuseValue + " DiffusionValue=" + DiffusionValue);
        Xn_LCG = (Xn_LCG + ConfuseValue + DiffusionValue - SupportValue + SurprisalValue) % (Modulus);
        //System.out.println("    LCG_Value_RandomSimple:Final:Xn_LCG=" + Xn_LCG + " ConfuseValue=" + ConfuseValue + " DiffusionValue=" + DiffusionValue + " SupportValue=" + SupportValue + " SurprisalValue=" + SurprisalValue);
        if (InitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]) - CompletionTimeOverall;
            System.out.println("LCG_Value_RandomSimple:Completed:Run Started Time:" + current + " " + " InitFlagRecordTime=" + InitFlagRecordTime + " Modulus=" + Modulus + " MaxExponent=" + MaxExponent + " ModulusEncrypt=" + ModulusEncrypt + " CompletionTimeOverall=" + CompletionTimeOverall * 1000 + " millisecs");
            InitFlagRecordTime = true;
        }
        return Xn_LCG;
    }

    void GetExprnMinMaxRange(long Xn, String DiffWithRespTo, long Modulus, int NoOfTimes) {
        this.LCGXValuemax = Modulus;
        if (Xn > 0) {
            this.LCGXValuemin = Xn;
        } else {
            this.LCGXValuemin = -Modulus;
        }
        if (Modulus > 0) {
            this.LCGXValueInc = NoOfTimes * 1 / Modulus;
        } else if ((Modulus != 0) && (this.LCGXValueInc == NoOfTimes * 1 / Modulus)) {
            this.LCGXValueInc = NoOfTimes * 1 / this.LCGXValuemax;
        } else {
            this.LCGXValueInc = 1;
        }
    }

    void ResetSimpleObj() {
        LCGexpValueObjExpire = false;
        LCGexpObjExpire = false;
        LCGexpValueObjExpire2 = false;
    }

    static String FormEquationLocal(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        //if(Xn==0) MaxExponent=5;
        //else MaxExponent=(int)Xn;
        long bCoeffStore = MgtbCoeff;
        //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff );
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        for (int i = MgtMaxExponent - 1; i > 1; i--) {
            if (MgtbCoeff > 2) {
                Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
            } else {
                Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
                MgtbCoeff = bCoeffStore;
            }
            //MgtbCoeff=GetPrimeNumber(MgtbCoeff,i);
            MgtbCoeff++;
            //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff + " i=" + i);
        }
        if (MgtbCoeff > 2) {
            Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo;
        } else {
            Exprn = Exprn + " + " + TechDiffWithRespTo;
        }
        Exprn = Exprn + " + " + Xn + " + " + Mgtc;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquation:Exprn=" + Exprn);
        //System.out.println("FormEquationLocal:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationSimpleLocal(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        //if(Xn==0) MaxExponent=5;
        //else MaxExponent=(int)Xn;
        long bCoeffStore = MgtbCoeff;
        //log.fatal("FormEquation: MgtbCoeff=" + MgtbCoeff );
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        for (int i = MgtMaxExponent - 1; i >= 1; i--) {
            Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo + "^" + i;
        }
        if (MgtbCoeff > 2) {
            Exprn = Exprn + " + " + MgtbCoeff + "*" + TechDiffWithRespTo;
        }
        Exprn = Exprn + " + " + Xn + " + " + Mgtc;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquationSimple:Exprn=" + Exprn);
        //System.out.println("FormEquationSimpleLocal:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationSupportLocal(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        Exprn = Exprn + " + " + Xn + " + " + Mgtc + " * " + SeedSimple++;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquationSimple:Exprn=" + Exprn);
        //System.out.println("FormEquationSupportLocal:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationSurpriseLocal(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        String Exprn = MgtACoeff + "/" + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        Exprn = Exprn + " + " + Xn + " + " + Mgtc + " * " + SeedSimple++;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquationSimple:Exprn=" + Exprn);
        //System.out.println("FormEquationSurpriseLocal:Exprn=" + Exprn);
        return Exprn;
    }

    void UpdateStats(long Xn_LCG) {
        if ((NextIthLCGPatRecog == Xn_LCG)
                || (NextIthLCGPatternRep == Xn_LCG)
                || (NextIthLCGPattern == Xn_LCG)
                || (NextIthLCGBuffer == Xn_LCG)) {
            GuessLCG++;
            if (NextIthLCGPatRecog == Xn_LCG) {
                GuessLCGPatRecog++;
            }
            if (NextIthLCGPatternRep == Xn_LCG) {
                GuessLCGRep++;
            }
            if (NextIthLCGPattern == Xn_LCG) {
                GuessLCGPattern++;
            }
            if (NextIthLCGBuffer == Xn_LCG) {
                GuessLCGBuff++;
            }
        }
    }

    //Detect the Pattern
    void DetectLCGPattern(int EntIndex, long NoOfSlots, long Xn) {
        boolean LoopAssignFlag = false;
           // Extract Freq Distribution Info from FreqStore
            if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
                    && (PatternFreqMaxIndex[EntIndex] == 0)
                    && (this.FreqMaxStore[0][0] == 0) && (this.FreqMaxStore[0][1] == 0)) {
                this.FreqMaxStore[0][0] = Xn;
                this.FreqMaxStore[0][1] = this.FreqStore[(int) Xn];
                PatternFreqMaxIndex[EntIndex]++;
            } else if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
                    && (PatternFreqMaxIndex[EntIndex] == 0)
                    && (Xn == EntPool[0].MySimple.FreqMaxStore[0][0])
                    && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[0][1])) {
                this.FreqMaxStore[0][0] = Xn;
                this.FreqMaxStore[0][1] = this.FreqStore[(int) Xn];
            }  else if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
                    && (PatternFreqMaxIndex[EntIndex] > 0)) {
                for (int i = 0; (i < PatternLength) && (i < PatternFreqMaxIndex[EntIndex]); i++) {
                    if ((Xn == this.FreqMaxStore[i][0])) {
                        this.FreqMaxStore[i][1] = this.FreqStore[(int) Xn];
                        LoopAssignFlag = true;
                    }
                }
                if ((LoopAssignFlag == false) && (PatternFreqMaxIndex[EntIndex] < PatternLength)
                        && (PatternFreqMaxIndex[EntIndex] > 0)
                        && (this.FreqMaxStore[PatternFreqMaxIndex[EntIndex]][0] == 0)
                        && (this.FreqMaxStore[PatternFreqMaxIndex[EntIndex]][1] == 0)) {
                    this.FreqMaxStore[PatternFreqMaxIndex[EntIndex]][0] = Xn;
                    this.FreqMaxStore[PatternFreqMaxIndex[EntIndex]][1] = this.FreqStore[(int) Xn];
                    PatternFreqMaxIndex[EntIndex]++;
                }
            } else if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
                    && (PatternFreqMaxIndex[EntIndex] > 0)
                    && (Xn == this.FreqMaxStore[PatternFreqMaxIndex[0]][0])
                    && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[PatternFreqMaxIndex[0]][1])) {
                this.FreqMaxStore[PatternFreqMaxIndex[0]][0] = Xn;
                this.FreqMaxStore[PatternFreqMaxIndex[0]][1] = this.FreqStore[(int) Xn];
                PatternFreqMaxIndex[EntIndex]++;
            }

            //Sort by Frequency
            this.Mean += Xn;
            this.DataStoreLCG.removeAll(DataStoreLCG);
            for (int i = 0; i < PatternFreqMaxIndex[EntIndex]; i++) {
                this.DataStoreLCG.add(new PatternBits(((int) this.FreqMaxStore[i][0]), ((int) this.FreqMaxStore[i][1])));
            }
            Collections.sort(this.DataStoreLCG, new SortbyFreq());
    }

    void SearcNFormLCGPattern(int EntIndex, long Xn) {
        int i = 0;
        int j = 0;
        for (i = 0; (i < PatternFreqMaxIndex[EntIndex]) ; i++)
               if((EntIndex==0)&& (Xn != this.DataStoreLCG.get(i).Xn) )
                       ;
               else if ((EntIndex==2)&& (Xn != this.DataStoreLCG.get(i).Xn) )
                   ;
               else
                   break;
        //Found PatternBit
        //Constant Pattern
        if ((i < PatternFreqMaxIndex[EntIndex]) && ((i+1) <= DataStoreLCG.size()) ) {
            this.NextIthLCGPatRecog = (long) DataStoreLCG.get(i).Xn;
        }//Random Pattern
        else if ((i < PatternFreqMaxIndex[EntIndex]) && ((i + 1) < DataStoreLCG.size()) && (DataStoreLCG.get(i + 1).Freq > 1)) {
            this.NextIthLCGPatRecog = (long) DataStoreLCG.get(i + 1).Xn;
        }
    }

    void PatternRepetionTest(int i, long IthXn_LCG) {
        int j = 0;
        if (LCGPatternLastBits.isEmpty()) {
            LCGPatternLastBits.addLast(IthXn_LCG);
        } else {
            for (j = 0; j < LCGPatternLastBits.size() - 1; j++) {
                if ((long) LCGPatternLastBits.get(j) == IthXn_LCG) {
                    break;
                }
            }
            //Found PatternBit
             //Constant Pattern
            if ((j < LCGPatternLastBits.size())&&((j+1) <= LCGPatternLastBits.size()) )  {
                this.NextIthLCGPatternRep = (long) LCGPatternLastBits.get(j );
            }//Random Pattern
            else if (j < LCGPatternLastBits.size() - 1) {
                this.NextIthLCGPatternRep = (long) LCGPatternLastBits.get(j + 1);
            }
            if (LCGPatternLastBits.size() < PatternRepLength) {
                LCGPatternLastBits.push(IthXn_LCG);
            } else if (LCGPatternLastBits.size() == PatternRepLength) {
                LCGPatternLastBits.removeFirst();
                LCGPatternLastBits.push(IthXn_LCG);
            }
        }
    }

    void PatternTest(int i, long IthXn_LCG) {
        int j = 0;
        for (j = 0; j < LCGPattern.length; j++) {
            if (LCGPattern[j] == IthXn_LCG) {
                break;
            }
        }
        //Found PatternBit
         //Constant Pattern
        if ((j < LCGPattern.length)&&((j+1) <= LCGPattern.length) ) {
            this.NextIthLCGPattern = LCGPattern[j];
        }//Random Pattern
        else if (j < LCGPattern.length) {
            this.NextIthLCGPattern = LCGPattern[j + 1];
        }
    }

    void BufferTest(int i, long IthXn_LCG) {
        int j = 0;
        for (j = 0; j < LCGNextBitDataBuff.size() - 1; j++) {
            if ((long) LCGNextBitDataBuff.get(j) == IthXn_LCG) {
                break;
            }
        }
        //Found PatternBit
         //Constant Pattern
        if ((j < LCGNextBitDataBuff.size())&&((j+1) <= LCGNextBitDataBuff.size()) ) {
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j);
        }//Random Pattern
        else if (j < LCGNextBitDataBuff.size() - 1) {
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j + 1);
        }
    }
 void ReadFromFile(int EntIndex, long NoOfBuffBitsMax, long Xn_LCG,String  RunNumber) {
        try {
            String FileName = Path + "RNDXn_LCG" + RunNumber;
            FileInputStream fp = new FileInputStream(FileName);
            BufferedReader din = new BufferedReader(new InputStreamReader(fp));
            LinkedList Temp = new LinkedList();
            long data = 0;
            if (ReadExpireFlag == false) {
                while (fp.available() > 0) {
                    String line = din.readLine();
                    if (line != null) {
                        line = line.trim();
                        Temp.add(Long.parseLong(line));
                    } else {
                        break;
                    }
                }
                din = null;
                fp.close();
                this.LCGNextBitDataBuff.clear();
                int k = Temp.size() - 1;
                int i = (k - (int) NoOfBuffBitsMax);
                if (i < 0) {
                    for (i = 0; (i < Temp.size()); i++) {
                        data = (long) Temp.get(i);
                        if (NoOfBuffBitsMax > this.LCGNextBitDataBuff.size()) {
                            this.LCGNextBitDataBuff.add(data);
                        } else {
                            this.LCGNextBitDataBuff.push(data);
                        }
                    }
                } else {
                    for (; (i < Temp.size()); i++) {
                        data = (long) Temp.get(i);
                        if (NoOfBuffBitsMax > this.LCGNextBitDataBuff.size()) {
                            this.LCGNextBitDataBuff.add(data);
                        } else {
                            this.LCGNextBitDataBuff.push(data);
                        }
                    }
                }
                ReadExpireFlag = true;
            } else {
                this.LCGNextBitDataBuff.add(Xn_LCG);
                ReadExpireNumber++;
                if (ReadExpireNumber == ReadExpireMax) {
                    ReadExpireNumber = 0;
                    ReadExpireFlag = false;
                }
            }
        } catch (Exception RND) {
            log.info("Exception: RND ReadFromFile");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
    }
    void printDocValues(int Index, int NoOfBits) {
            System.out.println("LCG InitValues:" + Index);
            //PrintSeedValues(Index);
            PrintMeanValues(Index);
            System.out.printf("GuessLCG=%d %%=%5.4f\n", EntPool[Index].MySimple.GuessLCG, (100.0 * (double) EntPool[Index].MySimple.GuessLCG / (double) NoOfBits));
            System.out.printf("GuessLCGPatRecog=%d %%=%5.4f\n", EntPool[Index].MySimple.GuessLCGPatRecog, (100.0 * (double) EntPool[Index].MySimple.GuessLCGPatRecog / (double) NoOfBits));
            System.out.printf("GuessLCGRep=%d %%=%5.4f\n", EntPool[Index].MySimple.GuessLCGRep, (100.0 * (double) EntPool[Index].MySimple.GuessLCGRep / (double) NoOfBits));
            System.out.printf("GuessLCGPattern=%d %%=%5.4f\n", EntPool[Index].MySimple.GuessLCGPattern, (100.0 * (double) EntPool[Index].MySimple.GuessLCGPattern / (double) NoOfBits));
            System.out.printf("GuessLCGBuff=%d %%=%5.4f\n", EntPool[Index].MySimple.GuessLCGBuff, (100.0 * (double) EntPool[Index].MySimple.GuessLCGBuff / (double) NoOfBits));
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());

            //PrintFreqStore(Index);
            //PrintFreqMaxStore(Index);
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
    }
    void PrintSeedValues(int Index){
        System.out.println("LCG InitVal:" + this.InitValue.get("InitVal" + Index));
            System.out.println("LCG a:" + this.InitValue.get("a" + Index));
            System.out.println("LCG c:" + this.InitValue.get("c" + Index));
            System.out.println("LCG b:" + this.InitValue.get("b" + Index));
            System.out.println("LCG ModulusEncrypt:" + this.InitValue.get("ModulusEncrypt" + Index));
            System.out.println("LCG MaxExponent:" + this.InitValue.get("MaxExponent" + Index));

            System.out.println("LCG Seed Equations:" + Index);
            System.out.println("LCG Exprn:" + this.InitValue.get("Exprn" + Index));
            System.out.println("LCG ConfuseExprn:" + this.InitValue.get("ConfuseExprn" + Index));
            System.out.println("LCG DiffusionExprn:" + this.InitValue.get("DiffusionExprn" + Index));
            System.out.println("LCG SupportExprn:" + this.InitValue.get("SupportExprn" + Index));
            System.out.println("LCG SurprisalExprn:" + this.InitValue.get("SurprisalExprn" + Index));

            System.out.println("LCG Seed Values:");
            System.out.println("LCG ConfuseValue:" + this.InitValue.get("ConfuseValue" + Index));
            System.out.println("LCG DiffusionValue:" + this.InitValue.get("DiffusionValue" + Index));
            System.out.println("LCG SupportValue:" + this.InitValue.get("SupportValue" + Index));
            System.out.println("LCG SurprisalValue:" + this.InitValue.get("SurprisalValue" + Index));

    }
    public void PrintMeanValues(int Index){
            System.out.println("LCG Stats:" + Index + ":Mean=" + this.MeanSigma[0] + " Low Level Mean=" + this.Mean / NoOfBits);
            System.out.println("LCG Stats:" + Index + ":Sigma=" + this.MeanSigma[1]);
            System.out.println("LCG Stats:" + Index + ":Sigma(2 bit)=" + this.MeanSigma[2]);
            
    }
    void PrintFreqStore(int Index){
        for (int i = 0; i < NoOfSlots; i++) {
                System.out.println("LCG Stats: i=" + i + " " + this.FreqStore[i] + " Probability=" + this.GaussianPDFStore[i] + " TheoryProb=" + (100.0 * RandomNumber.Probability(i, this.MeanSigma[0], this.MeanSigma[1])) + " Actual=" + 100 * this.FreqStore[i] / NoOfBits);
            }
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
    }
    void PrintFreqMaxStore(int Index){
        int Freq = 0;
            double mean = 0.0;
            for (int i = 0; i < PatternFreqMaxIndex[Index]; i++) {
                if (this.DataStoreLCG.get(i).Freq > 0) {
                    Freq += this.DataStoreLCG.get(i).Freq;
                }
                mean += this.DataStoreLCG.get(i).Xn * this.DataStoreLCG.get(i).Freq;
                if (this.DataStoreLCG.get(i).Freq > 0) {
                    System.out.println("LCG Stats:" + Index + ":Max:i=" + i + " Number=" + this.DataStoreLCG.get(i).Xn + " " + this.DataStoreLCG.get(i).Freq);
                }
            }
            System.out.println("LCG Stats:Max:Freq=" + Freq + " mean=" + (mean / NoOfBits));
            
    }
    public void TestInfraSingle(int i,long Xn_LCG,String FileName, String RunNumber){
            WriteToFileInfra(i, Xn_LCG, FileName,RunNumber);
            StatsSingle(0, Xn_LCG);
            ReadFromFile(0, NoOfBuffBitsMax, Xn_LCG,RunNumber);
            DetectLCGPattern(0, NoOfSlots, Xn_LCG);
            GuessNextIthBit(0, i, Xn_LCG);
    }
    void GuessNextIthBit(int EntIndex, int i, long IthXn_LCG){
        this.SearcNFormLCGPattern(0, IthXn_LCG);
        this.PatternRepetionTest(i, IthXn_LCG);
        this.PatternTest(i, IthXn_LCG);
        this.BufferTest(i, IthXn_LCG);
    }
    void WriteToFileInfra(int i, long Xn_LCG,String FileName, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        FileName = Path + FileName + RunNumber;
        String DataLCG = "" + Xn_LCG + " ";
        try {
            if (i == 0) {
                fp = new FileWriter(FileName, false);
                bw = new BufferedWriter(fp);
                fpout = new PrintWriter(bw);
            } else {
                fp = new FileWriter(FileName, true);
                bw = new BufferedWriter(fp);
                fpout = new PrintWriter(bw);
            }
            fpout.println(DataLCG);
            fpout.close();
            fpout = null;
            bw = null;
            fp = null;
            
        } catch (Exception RND) {
            log.info("Exception: RND WriteToFileInfra");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
        // }
    }
    public void StatsSingle(int EntIndex, long Xn_LCG) {
    int i=EntIndex;        
//for (int i = EntIndex; i < EntPoolIndex; i++) {
            this.FreqStore[(int) Xn_LCG]++;
            this.UpdateStats(Xn_LCG);
            
        //}
    }
    class PatternBits {

        int Xn = 0;
        int Freq = 0;

        PatternBits(int myXn, int myFreq) {
            Xn = myXn;
            Freq = myFreq;
        }

    }

    class SortbyFreq implements Comparator<PatternBits> {

        public int compare(PatternBits a, PatternBits b) {

            return b.Freq - a.Freq;
        }
    }
}
