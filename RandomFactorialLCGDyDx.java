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
import static RandomNumber.RandomNumber.Stats;
import static RandomNumber.RandomNumber.WriteToFile;
import static RandomNumber.RandomNumber.log;
import static RandomNumber.UserResearchStudy.CompletionTime;
import static RandomNumber.UserResearchStudy.CompletionTimeOverall;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.FactorialInitFlagRecordTime;
import static RandomNumber.UserResearchStudy.LCGDyDxPatternLastBits;
import static RandomNumber.UserResearchStudy.LowFactorialInitFlagRecordTime;
import static RandomNumber.UserResearchStudy.NoOfBits;
import static RandomNumber.UserResearchStudy.NoOfBuffBitsMax;
import static RandomNumber.UserResearchStudy.NoOfSlots;
import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.PatternFreqMaxIndex;
import static RandomNumber.UserResearchStudy.PatternLength;
import static RandomNumber.UserResearchStudy.PatternRepLength;
import static RandomNumber.UserResearchStudy.ReadExpireFlag;
import static RandomNumber.UserResearchStudy.ReadExpireMax;
import static RandomNumber.UserResearchStudy.ReadExpireNumber;
import static RandomNumber.UserResearchStudy.RunNumber;
import static RandomNumber.UserResearchStudy.SeedDyDx;
import static RandomNumber.UserResearchStudy.Xn_LCG;
import static RandomNumber.UserResearchStudy.Xn_LCG3;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx4;
import static RandomNumber.UserResearchStudy.Xn_XOR;
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
public class RandomFactorialLCGDyDx {
    public static Logger log = Logger.getLogger(RandomFactorialLCGDyDx.class.getName());
    
    // Below Set must be different from Above Set for Multivariate Random Number
    //Theory & Source:Wiki Confusion and Diffusion
    public long ConfuseFactorial = 1;
    public long DiffusionFactorial = 2;
    //Theory & Source:Wiki Information Content
    public long SupportFactorial = 3;
    public long SurprisalFactorial = 4;
    
    

    private MathsContxtLAv.SimpleExpression FactorialexpSimple;
    private MathsContxtLAv.Expression FactorialexpValue;
    private boolean FactorialexpValueObjExpire = false;
    private MathsContxtLAv.Expression FactorialexpValue2 = (() -> 0.0);
    private boolean FactorialexpValueObjExpire2 = false;
    private DiffrIntegrSep.DiffExpr Factorialexp = (() -> "");
    private boolean FactorialexpObjExpire = false;
    private boolean FactorialXnplus1Avail = false;
    
    // Core Variables
    public String ExprnLCGDyDx = "";
    public String ConfuseExprnLCGDyDx = "";
    public String DiffusionExprnLCGDyDx = "";
    public String SupportExprnLCGDyDx = "";
    public String SurprisalExprnLCGDyDx = "";

    private long LCGDyDxXValuemin = 0;
    private long LCGDyDxXValuemax = 0;
    private long LCGDyDxXValueInc = 0;
    
    // I+1th Bit (NextIth Bit)
    //Theory & Source:Wiki next-bit Test
    public long NextIthLCGDyDxPatternRep = 0;
    public long NextIthLCGDyDxPattern = 0;
    public long NextIthLCGDyDxBuffer = 0;
    public long NextIthLCGDyDxPatRecog = 0;

    public long GuessLCGDyDx = 0;
    public long GuessLCGDyDxPatRecog = 0;
    public long GuessLCGDyDxRep = 0;
    public long GuessLCGDyDxPattern = 0;
    public long GuessLCGDyDxBuff = 0;

    //LCGDyDx Pattern: 6 7 2 6 3 1 7 5 4 4 4 6 1 5 3 3 6 7 2 1 7 1 1 4 6 1 1 2 2 1 1 3 6 7 4 
    public static long[] LCGDyDxPattern = {12, 45, 86, 65, 22, 52, 6, 7, 2, 6, 3, 1, 7, 5, 4, 4, 4, 6, 1, 5, 3, 3, 6, 7, 2, 1, 7, 1, 1, 4, 6, 1, 1, 2, 2, 1, 1, 3, 6, 7, 4};
    public LinkedList LCGDyDxNextBitDataBuff = new LinkedList();
    public ArrayList<PatternBits> DataStoreLCGDyDx = null;
    
    // Stats Data Structures
    public double[] FreqStore = null;
    public double[][] FreqMaxStore = null;
    public double[] GaussianPDFStore = null;
    public double[] EntropyPDFStore = null;
    public double[] MeanSigma = null;
    
    public double Mean=0.0;
    public double Sigma=0.0;
    
    // Read Write File Control Mechanism
    public String ReadExpireStr = "Number";//Number,FactorNumberOfBits,Linear
    public long ReadExpireFactor = 3;
    public long ReadExpireMax = 1000;
    public long ReadExpireNumber = 0;
    public boolean ReadExpireFlag = false;
    
    //InitValues for Documentation -Variables
    public static Map<String, String> InitValue=new HashMap<>();
    
    public RandomFactorialLCGDyDx(int Index, long NoOfSlots, long ModulusEncrypt, long Xn_LCGDyDx, long a, long c, long b, long MaxExponent){
        // Form the Equations
        ExprnLCGDyDx = FormEquationFactorial(Xn_LCGDyDx, a, c, b, "Xn", (int) MaxExponent);
        ConfuseExprnLCGDyDx = FormEquationSimple(Xn_LCGDyDx, a, c, b, "Xn", (int) ModulusEncrypt);;
        DiffusionExprnLCGDyDx = FormEquationSimple(Xn_LCGDyDx, a, c, b, "Xn", (int) ModulusEncrypt);
        SupportExprnLCGDyDx = FormEquationSupport(Xn_LCGDyDx, a, c, b, "Xn", (int) ModulusEncrypt);
        SurprisalExprnLCGDyDx = FormEquationSimple(Xn_LCGDyDx, a, c, b, "Xn", (int) ModulusEncrypt);
        //ConfuseFactoriale = 7 + Index;
        //DiffusionFactorial = 11 + Index;
        //SupportFactorial = 17 + Index;
        //SurprisalFactorial = 19 + Index;
        DocumentInitValues(Index, NoOfSlots,  ModulusEncrypt,  Xn_LCG,  Xn_LCGDyDx,  a,  c,  b,  MaxExponent);
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
        DataStoreLCGDyDx = new ArrayList<RandomFactorialLCGDyDx.PatternBits>();
        if (ReadExpireStr.equalsIgnoreCase("Number")) {
            ReadExpireMax = ReadExpireMax;
        } else if (ReadExpireStr.equalsIgnoreCase("FactorNumberOfBits")) {
            ReadExpireMax = NoOfBits / ReadExpireFactor;
        } else if (ReadExpireStr.equalsIgnoreCase("Linear")) {
            ReadExpireMax = ReadExpireMax + NoOfBits / ReadExpireFactor;
        }
    }
    void DocumentInitValues(long Index, long NoOfSlots, long ModulusEncrypt, long Xn_LCG, long Xn_LCGDyDx, long a, long c, long b, long MaxExponent){
        //Init Values
        InitValue.put("InitVal"+Index, ""+Xn_LCGDyDx);
        InitValue.put("a"+Index, ""+a);
        InitValue.put("c"+Index, ""+c);
        InitValue.put("b"+Index, ""+b);
        InitValue.put("ModulusEncrypt"+Index, ""+ModulusEncrypt);
        InitValue.put("MaxExponent"+Index, ""+MaxExponent);
            
        //Equations
        InitValue.put("Exprn"+Index, ExprnLCGDyDx);
        InitValue.put("ConfuseExprn"+Index, ConfuseExprnLCGDyDx);
        InitValue.put("DiffusionExprn"+Index, DiffusionExprnLCGDyDx);
        InitValue.put("SupportExprn"+Index, SupportExprnLCGDyDx);
        InitValue.put("SurprisalExprn"+Index, SurprisalExprnLCGDyDx);
        
        // Seed Values
        InitValue.put("ConfuseFactorial"+Index, ""+ConfuseFactorial);
        InitValue.put("DiffusionFactorial"+Index, ""+DiffusionFactorial);
        InitValue.put("SupportFactorial"+Index, ""+SupportFactorial);
        InitValue.put("SurprisalFactorial"+Index, ""+SurprisalFactorial);
        
}
    // The LCG_Factorial_Random Method uses F(x)==Xn && ODE Dy/Dx(F(x))== Xn
    // If Xn != Xn+1, Method Returns Xn+1
    // This can be viewed as a Lyapunov Function Also 
    //(Source :wikiLyapunov) where F'(x)=F(x)
    public long LCG_Factorial_Random(String AlgoName, String Exprn, long a, long c, long Xn, long Modulus, long MaxExponent, String EqnCompFlag) {
        if (LowFactorialInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[2]);
            System.out.println("LCG_Factorial_Random:Started:Run Started Time:" + current + " ");
        }
        //Value = (a * Factorial + c) % (FactorialSpread + 1);
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
        Exprn=Exprn + "-" + a+"*" + DiffWithRespTo + "+" + c+"*" +Xn + "-" + Xn;
        Exprn = eatAll(Exprn, ' ');
        GetExprnMinMaxRangeLCGDyDx(Xn, DiffWithRespTo, Modulus, 10);
        FactorialXnplus1Avail = false;
        for (long x = LCGDyDxXValuemin; (i < 10) && ((i < 3) || (Xn == Xnplus1) || (TempXn == Xnplus1)); x += LCGDyDxXValueInc, i++) {
            variables.put(DiffWithRespTo, ((double) x));
            if (FactorialexpValueObjExpire == false) {
                FactorialexpValue = parse(Exprn, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                FactorialexpValueObjExpire = true;
            }
            //F(x)==Xn 
            TempXn = (long) ((FactorialexpValue.eval()) % (Modulus));
            if ((TempXn == Xn) || (i >= 1)) {
                StrexpSimpleExpress = eatAll(Exprn, ' ');
                if (FactorialexpObjExpire == false) {
                    StrexpSimpleExpress = StrexpSimpleExpress + "-" + Xnplus1;
                    StrexpSimpleExpress = eatAll(StrexpSimpleExpress, ' ');
                    Factorialexp = parseDiff(StrexpSimpleExpress, variables, DiffWithRespTo);
                    FactorialexpObjExpire = true;
                }
                StrexpDiffExpress = Factorialexp.DiffExpr();
                StrexpSimpleExpress = eatAll(StrexpDiffExpress, ' ');
                if (FactorialexpValueObjExpire2 == false) {
                    FactorialexpValue2 = parse(StrexpSimpleExpress, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
                    FactorialexpValueObjExpire2 = true;
                }
                Exprn = StrexpSimpleExpress;
                //ODE Dy/Dx(F(x))== Xn
                Xnplus1 = (long) ((FactorialexpValue2.eval() + x) % (Modulus));
                FactorialXnplus1Avail = true;
                GetExprnMinMaxRangeLCGDyDx(Xnplus1, DiffWithRespTo, (LCGDyDxXValuemax - x), (10 - i));
            } else if (TempXn != Xnplus1) {
                Xnplus1 = (long) (FactorialexpValue2.eval() % (Modulus));
                break;
            }
        }
        if (LowFactorialInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTime = Double.parseDouble(TimeTaken[2]) - CompletionTime;
            System.out.println("LCG_Factorial_Random:Completed:Run Started Time:" + current + " " + " CompletionTime=" + CompletionTime * 1000 + " millisecs");
            LowFactorialInitFlagRecordTime = true;
        }
        //System.out.println("LCG_Factorial_Random:Completed:Xnplus1=" + Xnplus1 + " Modulus=" + Modulus);
        return ((long) Xnplus1);
    }
    static String FormEquation(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
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
        //System.out.println("FormEquation:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationFactorial(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
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
        //System.out.println("FormEquationFactorial:Exprn=" + Exprn);
        return Exprn;
    }

    public long LCG_Factorial_RandomSimple(long Xn_LCGDyDx, long a, long c, int Modulus, int MaxExponent, int ModulusEncrypt) {
        if (FactorialInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]);
            System.out.println("LCG_Factorial_RandomSimple:Started:Run Started Time:" + current + " ");
        }
//System.out.println("LCG_Factorial_RandomSimple:Initial:Xn_LCGDyDx=" + Xn_LCGDyDx + " ConfuseFactorial=" + ConfuseFactorial + " DiffusionFactorial=" + DiffusionFactorial);
        ResetFactorialObj();
        Xn_LCGDyDx = (LCG_Factorial_Random("LCG_Factorial_RandomSimple", ExprnLCGDyDx, a, c, Xn_LCGDyDx, Modulus, MaxExponent, "Xn_LCGDyDx"));
        ResetFactorialObj();
        ConfuseFactorial += (LCG_Factorial_Random("LCG_Factorial_RandomSimple", ConfuseExprnLCGDyDx, a, c, ConfuseFactorial, (int) ModulusEncrypt, ModulusEncrypt, "ConfuseValue") % (ModulusEncrypt));
        ResetFactorialObj();
        DiffusionFactorial += (LCG_Factorial_Random("LCG_Factorial_RandomSimple", DiffusionExprnLCGDyDx, a, c, DiffusionFactorial, (int) ModulusEncrypt, ModulusEncrypt, "DiffusionValue") % (ModulusEncrypt));
        ResetFactorialObj();
        SupportExprnLCGDyDx=SupportExprnLCGDyDx + "-" + SupportFactorial;
        SupportFactorial -= (LCG_Factorial_Random("LCG_Factorial_RandomSimple", SupportExprnLCGDyDx, a, c, SupportFactorial, (int) ModulusEncrypt, ModulusEncrypt, "SupportValue") % (ModulusEncrypt));
        ResetFactorialObj();
        SurprisalExprnLCGDyDx=SurprisalExprnLCGDyDx + "-" + SurprisalFactorial;
        SurprisalFactorial += (LCG_Factorial_Random("LCG_Factorial_RandomSimple", SurprisalExprnLCGDyDx, a, c, SurprisalFactorial, (int) ModulusEncrypt, ModulusEncrypt, "SurpriseValue") % (ModulusEncrypt));
        //System.out.println("LCG_Factorial_RandomSimple:Middle:Xn_LCGDyDx=" + Xn_LCGDyDx + " ConfuseFactorial=" + ConfuseFactorial + " DiffusionFactorial=" + DiffusionFactorial);
        Xn_LCGDyDx = (Xn_LCGDyDx + ConfuseFactorial + DiffusionFactorial - SupportFactorial + SurprisalFactorial) % (Modulus);
        //System.out.println("    LCG_Factorial_RandomSimple:Final:Xn_LCGDyDx=" + Xn_LCGDyDx + " ConfuseFactorial=" + ConfuseFactorial + " DiffusionFactorial=" + DiffusionFactorial + " SupportFactorial=" + SupportFactorial + " SurprisalFactorial=" + SurprisalFactorial);
        if (FactorialInitFlagRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]) - CompletionTimeOverall;
            System.out.println("LCG_Factorial_RandomSimple:Completed:Run Started Time:" + current + " " + " FactorialInitFlagRecordTime=" + FactorialInitFlagRecordTime + " Modulus=" + Modulus + " MaxExponent=" + MaxExponent + " ModulusEncrypt=" + ModulusEncrypt + " CompletionTimeOverall=" + CompletionTimeOverall * 1000 + " millisecs");
            FactorialInitFlagRecordTime = true;
        }
        return Xn_LCGDyDx;
    }

    void ResetFactorialObj() {
        FactorialexpValueObjExpire = false;
        FactorialexpObjExpire = false;
        FactorialexpValueObjExpire2 = false;
    }

    static String FormEquationSimple(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
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
        //System.out.println("FormEquationSimple:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationSupport(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        String Exprn = MgtACoeff + " * " + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        Exprn = Exprn + " + " + Xn + " + " + Mgtc + " * " + SeedDyDx++;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquationSimple:Exprn=" + Exprn);
        //System.out.println("FormEquationSupport:Exprn=" + Exprn);
        return Exprn;
    }

    static String FormEquationSurprise(long Xn, long MgtACoeff, long Mgtc, long MgtbCoeff, String TechDiffWithRespTo, int MgtMaxExponent) {
        String Exprn = MgtACoeff + "/" + TechDiffWithRespTo + "^" + (MgtMaxExponent);
        Exprn = Exprn + " + " + Xn + " + " + Mgtc + " * " + SeedDyDx++;
        Exprn = eatAll(Exprn, ' ');
        log.info("FormEquationSimple:Exprn=" + Exprn);
        //System.out.println("FormEquationSurprise:Exprn=" + Exprn);
        return Exprn;
    }

    void GetExprnMinMaxRangeLCGDyDx(long Xn, String DiffWithRespTo, long Modulus, int NoOfTimes) {
        this.LCGDyDxXValuemax = Modulus;
        if (Xn > 0) {
            this.LCGDyDxXValuemin = Xn;
        } else {
            this.LCGDyDxXValuemin = -Modulus;
        }
        if (Modulus > 0) {
            this.LCGDyDxXValueInc = NoOfTimes * 1 / Modulus;
        } else if ((Modulus != 0) && (this.LCGDyDxXValueInc == NoOfTimes * 1 / Modulus)) {
            this.LCGDyDxXValueInc = NoOfTimes * 1 / this.LCGDyDxXValuemax;
        } else {
            this.LCGDyDxXValueInc = 1;
        }
    }
    void UpdateStats( long Xn_LCGDyDx){
        if ((NextIthLCGDyDxPatRecog == Xn_LCGDyDx)
                    || (NextIthLCGDyDxPatternRep == Xn_LCGDyDx)
                    || (NextIthLCGDyDxPattern == Xn_LCGDyDx)
                    || (NextIthLCGDyDxBuffer == Xn_LCGDyDx)) {
                GuessLCGDyDx++;
                if (NextIthLCGDyDxPatRecog == Xn_LCGDyDx) {
                    GuessLCGDyDxPatRecog++;
                }
                if (NextIthLCGDyDxPatternRep == Xn_LCGDyDx) {
                    GuessLCGDyDxRep++;
                }
                if (NextIthLCGDyDxPattern == Xn_LCGDyDx) {
                    GuessLCGDyDxPattern++;
                }
                if (NextIthLCGDyDxBuffer == Xn_LCGDyDx) {
                    GuessLCGDyDxBuff++;
                }
            }
    }
    void DetectLCGDyDxPattern(int EntIndex,long NoOfSlots, long Xn) {
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
                && (Xn == this.FreqMaxStore[0][0])
                && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[0][1])) {
            this.FreqMaxStore[0][0] = Xn;
            this.FreqMaxStore[0][1] = this.FreqStore[(int) Xn];
        } else if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
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
        }else if ((PatternFreqMaxIndex[EntIndex] < PatternLength)
                && (PatternFreqMaxIndex[EntIndex] > 0)
                && (Xn == this.FreqMaxStore[PatternFreqMaxIndex[0]][0])
                && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[PatternFreqMaxIndex[0]][1])) {
            this.FreqMaxStore[PatternFreqMaxIndex[0]][0] = Xn;
            this.FreqMaxStore[PatternFreqMaxIndex[0]][1] = this.FreqStore[(int) Xn];
            PatternFreqMaxIndex[EntIndex]++;
        } 
        this.Mean +=Xn;
        //Sort by Frequency
        this.DataStoreLCGDyDx.clear();
        for (int i = 0; i < PatternFreqMaxIndex[EntIndex]; i++) {
            this.DataStoreLCGDyDx.add(new PatternBits(((int) this.FreqMaxStore[i][0]), ((int) this.FreqMaxStore[i][1])));
        }
        Collections.sort(this.DataStoreLCGDyDx, new SortbyFreq());
    }
    void SearcNFormLCGDyDxPattern(int EntIndex, long Xn) {
        int i = 0;
        int j = 0;
        for (i = 0; (i < PatternFreqMaxIndex[EntIndex]) ; i++)
               if((Xn != this.DataStoreLCGDyDx.get(i).Xn) )
                   ;
               else
                   break;
        //Found PatternBit
        //Constant Pattern
        if ((i < PatternFreqMaxIndex[EntIndex]) && (i < this.DataStoreLCGDyDx.size()) && ((i+1) <= this.DataStoreLCGDyDx.size())  ){
            this.NextIthLCGDyDxPatRecog = (long) this.DataStoreLCGDyDx.get(i ).Xn;
        }//Random Pattern
        else if ((i < PatternFreqMaxIndex[EntIndex]) && ((i) < this.DataStoreLCGDyDx.size()) && (this.DataStoreLCGDyDx.get(i ).Freq > 1)) {
            this.NextIthLCGDyDxPatRecog = (long) this.DataStoreLCGDyDx.get(i+1 ).Xn;
        }
    }
    void PatternRepetionTest(int i, long IthXn_LCGDyDx) {
        int j = 0;
        
        if (LCGDyDxPatternLastBits.isEmpty()) {
            LCGDyDxPatternLastBits.addLast(IthXn_LCGDyDx);
        } else {
            for (j = 0; j < LCGDyDxPatternLastBits.size() - 1; j++) {
                if ((long) LCGDyDxPatternLastBits.get(j) == IthXn_LCGDyDx) {
                    break;
                }
            }
            //Found PatternBit
            //Constant Pattern
            if ((j < LCGDyDxPatternLastBits.size())&&((j+1) <= LCGDyDxPatternLastBits.size()) ) {
                this.NextIthLCGDyDxPatternRep = (long) LCGDyDxPatternLastBits.get(j);
            }//Random Pattern
            else if (j < LCGDyDxPatternLastBits.size() - 1) {
                this.NextIthLCGDyDxPatternRep = (long) LCGDyDxPatternLastBits.get(j + 1);
            }
            if (LCGDyDxPatternLastBits.size() < PatternRepLength) {
                LCGDyDxPatternLastBits.push(IthXn_LCGDyDx);
            } else if (LCGDyDxPatternLastBits.size() == PatternRepLength) {
                LCGDyDxPatternLastBits.removeFirst();
                LCGDyDxPatternLastBits.push(IthXn_LCGDyDx);
            }
        }
    }
void PatternTest(int i, long IthXn_LCGDyDx) {
        int j = 0;
        for (j = 0; j < LCGDyDxPattern.length; j++) {
            if (LCGDyDxPattern[j] == IthXn_LCGDyDx) {
                break;
            }
        }
        //Found PatternBit
        //Constant Pattern
        if ((j < LCGDyDxPattern.length)&&((j+1) <= LCGDyDxPattern.length) ) {
            this.NextIthLCGDyDxPattern = LCGDyDxPattern[j];
        }//Random Pattern
        else if (j < LCGDyDxPattern.length) {
            this.NextIthLCGDyDxPattern = LCGDyDxPattern[j + 1];
        }
    }
void BufferTest(int i, long IthXn_LCGDyDx) {
        int j = 0;
        for (j = 0; j < LCGDyDxNextBitDataBuff.size() - 1; j++) {
            if ((long) LCGDyDxNextBitDataBuff.get(j) == IthXn_LCGDyDx) {
                break;
            }
        }
        //Found PatternBit
        //Constant Pattern
        if ((j < LCGDyDxNextBitDataBuff.size())&&((j+1) < LCGDyDxNextBitDataBuff.size()) ) {
            this.NextIthLCGDyDxBuffer = (long) LCGDyDxNextBitDataBuff.get(j);
        }//Random Pattern
        else if (j < LCGDyDxNextBitDataBuff.size() - 1) {
            this.NextIthLCGDyDxBuffer = (long) LCGDyDxNextBitDataBuff.get(j + 1);
        }
    }
void ReadFromFile(int EntIndex, long NoOfBuffBitsMax, long Xn_LCGDyDx , String RunNumber) {
        try {
                String FileName = Path + "RNDXn_LCGDyDx" + RunNumber;
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
                this.LCGDyDxNextBitDataBuff.clear();
                int k = Temp.size() - 1;
                int i = (k - (int) NoOfBuffBitsMax);
                if (i < 0) {
                    for (i = 0; (i < Temp.size()); i++) {
                        data = (long) Temp.get(i);
                        if (NoOfBuffBitsMax > this.LCGDyDxNextBitDataBuff.size()) {
                            this.LCGDyDxNextBitDataBuff.add(data);
                        } else {
                            this.LCGDyDxNextBitDataBuff.push(data);
                        }
                    }
                } else {
                    for (; (i < Temp.size()); i++) {
                        data = (long) Temp.get(i);
                        if (NoOfBuffBitsMax > this.LCGDyDxNextBitDataBuff.size()) {
                            this.LCGDyDxNextBitDataBuff.add(data);
                        } else {
                            this.LCGDyDxNextBitDataBuff.push(data);
                        }
                    }
                }
                ReadExpireFlag = true;
            } else {
                this.LCGDyDxNextBitDataBuff.add(Xn_LCGDyDx);
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

 void printDocValues(int Index, int NoOfBits){
            System.out.println("LCGDyDx InitValues:" + Index);
            //PrintSeedValues(Index);
            PrintMeanValues(Index);
            System.out.printf("GuessLCGDyDx=%d %%=%5.4f\n", EntPool[Index].MyFactorial.GuessLCGDyDx, (100.0 * (double) EntPool[Index].MyFactorial.GuessLCGDyDx / (double) NoOfBits));
            System.out.printf("GuessLCGDyDxPatRecog=%d %%=%5.4f\n", EntPool[Index].MyFactorial.GuessLCGDyDxPatRecog, (100.0 * (double) EntPool[Index].MyFactorial.GuessLCGDyDxPatRecog / (double) NoOfBits));
            System.out.printf("GuessLCGDyDxRep=%d %%=%5.4f\n", EntPool[Index].MyFactorial.GuessLCGDyDxRep, (100.0 * (double) EntPool[Index].MyFactorial.GuessLCGDyDxRep / (double) NoOfBits));
            System.out.printf("GuessLCGDyDxPattern=%d %%=%5.4f\n", EntPool[Index].MyFactorial.GuessLCGDyDxPattern, (100.0 * (double) EntPool[Index].MyFactorial.GuessLCGDyDxPattern / (double) NoOfBits));
            System.out.printf("GuessLCGDyDxBuff=%d %%=%5.4f\n", EntPool[Index].MyFactorial.GuessLCGDyDxBuff, (100.0 * (double) EntPool[Index].MyFactorial.GuessLCGDyDxBuff / (double) NoOfBits));
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            //PrintFreqStore(Index);
            //PrintFreqMaxStore(Index);
            System.out.print(System.lineSeparator());
    }
 void PrintSeedValues(int Index){
            System.out.println("LCGDyDx InitVal:" + this.InitValue.get("InitVal"+Index));
            System.out.println("LCGDyDx a:" + this.InitValue.get("a"+Index));
            System.out.println("LCGDyDx c:" + this.InitValue.get("c"+Index));
            System.out.println("LCGDyDx b:" + this.InitValue.get("b"+Index));
            System.out.println("LCGDyDx ModulusEncrypt:" + this.InitValue.get("ModulusEncrypt"+Index));
            System.out.println("LCGDyDx MaxExponent:" + this.InitValue.get("MaxExponent"+Index));
            
            System.out.println("LCGDyDx Seed Equations:" + Index);
            System.out.println("LCGDyDx Exprn:" + this.InitValue.get("Exprn"+Index));
            System.out.println("LCGDyDx ConfuseExprn:" + this.InitValue.get("ConfuseExprn"+Index));
            System.out.println("LCGDyDx DiffusionExprn:" + this.InitValue.get("DiffusionExprn"+Index));
            System.out.println("LCGDyDx SupportExprn:" + this.InitValue.get("SupportExprn"+Index));
            System.out.println("LCGDyDx SurprisalExprn:" + this.InitValue.get("SurprisalExprn"+Index));
            
            System.out.println("LCGDyDx Seed Values:" + Index);
            System.out.println("LCGDyDx ConfuseFactorial:" + this.InitValue.get("ConfuseFactorial"+Index));
            System.out.println("LCGDyDx DiffusionFactorial:" + this.InitValue.get("DiffusionFactorial"+Index));
            System.out.println("LCGDyDx SupportFactorial:" + this.InitValue.get("SupportFactorial"+Index));
            System.out.println("LCGDyDx SurprisalFactorial:" + this.InitValue.get("SurprisalFactorial"+Index));
            
}
void PrintMeanValues(int Index){
            System.out.println("LCGDyDx Stats:" + Index + ":Mean=" + this.MeanSigma[0] + " Low Level Mean=" + this.Mean/NoOfBits);
            System.out.println("LCGDyDx Stats:" + Index + ":Sigma=" + this.MeanSigma[1]);
            System.out.println("LCGDyDx Stats:" + Index + ":Sigma(2 bit)=" + this.MeanSigma[2]);
            
}
void PrintFreqStore(int Index){
    for (int i = 0; i < NoOfSlots; i++) {
                System.out.println("LCGDyDx Stats: i=" + i + " " + FreqStore[i] + " Probability=" + GaussianPDFStore[i] + " TheoryProb=" + (100.0 * RandomNumber.Probability(i, MeanSigma[0], MeanSigma[1])) + " Actual=" + 100 * FreqStore[i] / NoOfBits);
            }
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            
}
void PrintFreqMaxStore(int Index){
    int Freq1 = 0;
            double mean=0.0;
            for (int i = 0; i < PatternFreqMaxIndex[Index]; i++) {
                if (this.DataStoreLCGDyDx.get(i).Freq > 0) {
                    Freq1 += this.DataStoreLCGDyDx.get(i).Freq;
                }
                mean += this.DataStoreLCGDyDx.get(i).Xn * this.DataStoreLCGDyDx.get(i).Freq;
                if (this.DataStoreLCGDyDx.get(i).Freq > 0)
                    System.out.println("LCGDyDx Stats:Max:i=" + i + " Number=" + this.DataStoreLCGDyDx.get(i).Xn + " " + this.DataStoreLCGDyDx.get(i).Freq);
            }
            System.out.println("LCGDyDx Stats:" + Index + " :Max:Freq1=" + Freq1 + " mean=" + (mean/NoOfBits) );
            
}
public void TestInfraSingle(int i,long Xn_LCGDyDx,String FileName,String RunNumber){
            WriteToFileInfra(i,  Xn_LCGDyDx, NoOfBuffBitsMax,FileName,RunNumber);
            StatsSingle(0,  Xn_LCGDyDx) ;
            ReadFromFile(0, NoOfBuffBitsMax, Xn_LCGDyDx,RunNumber);
            DetectLCGDyDxPattern(0, NoOfSlots, Xn_LCGDyDx);
            GuessNextIthBit(0, i, Xn_LCGDyDx);
    }
    void GuessNextIthBit(int EntIndex, int i, long IthXn_LCGDyDx){
        this.SearcNFormLCGDyDxPattern(0, IthXn_LCGDyDx);
        this.PatternRepetionTest(i, IthXn_LCGDyDx);
        this.PatternTest(i, IthXn_LCGDyDx);
        this.BufferTest(i, IthXn_LCGDyDx);
    }
    static void WriteToFileInfra(int i, long Xn_LCGDyDx,long NoOfBuffBitsMax,String FileName, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        FileName = Path + FileName + RunNumber;
        String DataLCG = "" + Xn_LCGDyDx + " ";
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
    void StatsSingle(int EntIndex, long Xn_LCGDyDx) {
    int i=EntIndex;        
//for (int i = EntIndex; i < EntPoolIndex; i++) {
            this.FreqStore[(int) Xn_LCGDyDx]++;
            this.UpdateStats(Xn_LCGDyDx);       
        //}
    }
class PatternBits {

        int Xn = 0;
        int Freq = 0;

        public PatternBits(int myXn, int myFreq) {
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
