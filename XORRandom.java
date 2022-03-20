/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import Calculus.MathsContxtLAv;
import static Calculus.DiffrIntegrSep.MyFuncDiff;
import static Calculus.MathsContxtLAv.MyFuncExpress;
import static Calculus.MathsContxtLAv.MyFuncSimple;
import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import Calculus.PredicateSep;
import static RandomNumber.UserResearchStudy.CompletionTimeOverall;
import static RandomNumber.UserResearchStudy.DebugFlag;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.EntPoolIndex;
import static RandomNumber.UserResearchStudy.InitFlagRecordTime;
import static RandomNumber.UserResearchStudy.InitFlagXORRecordTime;
import static RandomNumber.UserResearchStudy.LCGPatternLastBits;
import static RandomNumber.UserResearchStudy.LowLevelPrintOut;
import static RandomNumber.UserResearchStudy.MaxExponent;
import static RandomNumber.UserResearchStudy.Modulus;
import static RandomNumber.UserResearchStudy.ModulusEncrypt;
import static RandomNumber.UserResearchStudy.NoOfBits;
import static RandomNumber.UserResearchStudy.NoOfBuffBitsMax;
import static RandomNumber.UserResearchStudy.NoOfSlots;
import static RandomNumber.UserResearchStudy.NumberOfStates;
import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.PatternFreqMaxIndex;
import static RandomNumber.UserResearchStudy.PatternLength;
import static RandomNumber.UserResearchStudy.PatternRepLength;
import static RandomNumber.UserResearchStudy.PoolIndexSeries;
import static RandomNumber.UserResearchStudy.RunNumber;
import static RandomNumber.UserResearchStudy.SeedXOR;
import static RandomNumber.UserResearchStudy.StudyType;
import static RandomNumber.UserResearchStudy.UserTweaks;
import static RandomNumber.UserResearchStudy.Xn_Circular;
import static RandomNumber.UserResearchStudy.Xn_LCG;
import static RandomNumber.UserResearchStudy.Xn_LCG3;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx4;
import static RandomNumber.UserResearchStudy.Xn_XOR;
import static RandomNumber.UserResearchStudy.a;
import static RandomNumber.UserResearchStudy.b;
import static RandomNumber.UserResearchStudy.c;
import static RandomNumber.UserResearchStudy.d;
import java.io.BufferedReader;
import java.time.LocalDateTime;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class XORRandom {

    public static Logger log = Logger.getLogger(XORRandom.class.getName());
    public static double XXORmin = 0;
    public static double XXORmax = 0;
    public static double XXORInc = 0;
    // XOR Variables
    private static long Xnminus1XOR = -1;

    //Theory & Source:Wiki Confusion and Diffusion
    public static long ConfuseXOR = 29;
    public static long DiffusionXOR = 37;
    //Theory & Source:Wiki Information Content
    public static long SupportXOR = 13;
    public static long SurprisalXOR = 31;
    
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
    
    // Stats Data Structures
    public double[] FreqStore = null;
    public double[][] FreqMaxStore = null;
    public double[] GaussianPDFStore = null;
    public double[] EntropyPDFStore = null;
    public double[] MeanSigma = null;

    public double Mean = 0.0;
    public double Sigma = 0.0;

    public ArrayList<XORRandom.PatternBits> DataStoreXOR = null;
    
    // Read Write File Control Mechanism
    public String ReadExpireStr = "Number";//Number,FactorNumberOfBits,Linear
    public long ReadExpireFactor = 3;
    public long ReadExpireMax = 1000;
    public long ReadExpireNumber = 0;
    public boolean ReadExpireFlag = false;
    
    XORRandom(){
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
        DataStoreXOR = new ArrayList<XORRandom.PatternBits>();
        if (ReadExpireStr.equalsIgnoreCase("Number")) {
            ReadExpireMax = ReadExpireMax;
        } else if (ReadExpireStr.equalsIgnoreCase("FactorNumberOfBits")) {
            ReadExpireMax = NoOfBits / ReadExpireFactor;
        } else if (ReadExpireStr.equalsIgnoreCase("Linear")) {
            ReadExpireMax = ReadExpireMax + NoOfBits / ReadExpireFactor;
        }
    }
    public static long LCG_XOR_Random(long Xn, long a, long c,long b, long d, int Modulus, int MaxExponent, int ModulusEncrypt) {
        if (InitFlagXORRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]);
            System.out.println("LCG_XOR_Random:Started:Run Started Time:" + current + " ");
        }
        //System.out.println("    LCG_XOR_Random:Initial:Xn_XOR=" + Xn_XOR + " ConfuseXOR=" + ConfuseXOR + " DiffusionXOR=" + DiffusionXOR + " SupportXOR=" + SupportXOR + " SurprisalXOR=" + SurprisalXOR);
        //XOR_Random(String AlgoName, long Xn, String PredicateHigh, String Parameter, String PredicateSOA, String PredicateOSOA, String pExprn, String qExprn, long a, long c,long b, long d, long Modulus)
        Xn = XOR_Random("XOR_Random", Xn, "Xn^2+a^2-2*a*Xn+y^2+c^2-2*c*y >10.0", "", "Xn^2+b^2-2*b*Xn+y^2+d^2-2*d*y >49", "Xn^2+b^2-2*b*Xn+y^2+d^2-2*d*y >10.0", "Xn^2+3^2-2*3*Xn+y^2+11^2-2*11*y >4", "Xn <37",  a,  c,b, d,(int) Modulus);
        ConfuseXOR += (XOR_Random("XOR_Random", ConfuseXOR, "Xn^2+a^2-2*a*Xn+y^2+c^2-2*c*y >10.0", "Xn", "Xn^2+5^2-2*5*Xn+y^2+7^2-2*7*y >49", "(Xn > 0)", "Xn^2+3^2-2*3*Xn+y^2+11^2-2*11*y >4", "(Xn >0)", a,  c,b, d,(int) Modulus) % (ModulusEncrypt));
        DiffusionXOR += (XOR_Random("XOR_Random", DiffusionXOR, "Xn^2+a^2-2*a*Xn+y^2+c^2-2*c*y >10.0", "Xn", "Xn^2+5^2-2*5*Xn+y^2+7^2-2*7*y >49", "(Xn > 0)", "Xn^2+3^2-2*3*Xn+y^2+11^2-2*11*y >4", "(Xn >0)",a,  c,b, d, (int) Modulus) % (ModulusEncrypt));
        SupportXOR -= (XOR_Random("XOR_Random", SupportXOR, "Xn^2+a^2-2*a*Xn+y^2+c^2-2*c*y >10.0", "Xn", "Xn^2+5^2-2*5*Xn+y^2+7^2-2*7*y >49", "(Xn > 0)", "Xn^2+3^2-2*3*Xn+y^2+11^2-2*11*y >4","(Xn >0)", a,  c, b, d, (int) Modulus) % (ModulusEncrypt));
        SurprisalXOR += (XOR_Random("XOR_Random", SurprisalXOR, "Xn^2+a^2-2*a*Xn+y^2+c^2-2*c*y >10.0", "Xn", "Xn^2+5^2-2*5*Xn+y^2+7^2-2*7*y >49", "(Xn > 0)", "Xn^2+3^2-2*3*Xn+y^2+11^2-2*11*y >4", "(Xn >0)", a,  c,b, d,(int) Modulus) % (ModulusEncrypt));
        //System.out.println("    LCG_XOR_Random:Middle:Xn=" + Xn + " ConfuseXOR=" + ConfuseXOR + " DiffusionXOR=" + DiffusionXOR + " SupportXOR=" + SupportXOR + " SurprisalXOR=" + SurprisalXOR);
        Xn_XOR = (Xn_XOR+Xn + ConfuseXOR + DiffusionXOR - SupportXOR + SurprisalXOR) % (Modulus);
        //Xn_XOR=Xn;
        //System.out.println("    LCG_XOR_Random:Final:Xn_XOR=" + Xn_XOR + " ConfuseXOR=" + ConfuseXOR + " DiffusionXOR=" + DiffusionXOR + " SupportXOR=" + SupportXOR + " SurprisalXOR=" + SurprisalXOR);
        if (InitFlagXORRecordTime == false) {
            LocalDateTime current = LocalDateTime.now();
            String[] TimeTaken = current.toString().split(":");
            CompletionTimeOverall = Double.parseDouble(TimeTaken[2]) - CompletionTimeOverall;
            System.out.println("LCG_XOR_Random:Completed:Run Started Time:" + current + " " + " InitFlagRecordTime=" + InitFlagRecordTime + " Modulus=" + Modulus + " MaxExponent=" + MaxExponent + " ModulusEncrypt=" + ModulusEncrypt + " CompletionTimeOverall=" + CompletionTimeOverall * 1000 + " millisecs");
            InitFlagXORRecordTime = true;
        }
        return Xn_XOR;
    }
    public static long XOR_Random(String AlgoName, long Xn, String PredicateHigh, String Parameter, String PredicateSOA, String PredicateOSOA, String pExprn, String qExprn, long a, long c,long b, long d, long Modulus) {
        AlgoName=AlgoName+":XOR_Random";
        long Xnplus1 = 0;
        long Xnplus1Bool = 0;
        Map<String, Double> variables = new HashMap<>();
        boolean PExprn;
        boolean QExprn;
        String DiffWithRespTo = "Xn";
        String SwapStr="";
        PredicateSep.PredicateBool pexpValue;
        PredicateSep.PredicateBool qexpValue;
        PredicateSep.PredicateBool pHighexpValue;
        PredicateSep.PredicateBool pSOAexpValue;
        PredicateSep.PredicateBool pOSOAexpValue;
        MyFuncExpress();
        pExprn = eatAll(pExprn, ' ');
        qExprn = eatAll(qExprn, ' ');
        PredicateHigh = eatAll(PredicateHigh, ' ');
        PredicateSOA = eatAll(PredicateSOA, ' ');
        PredicateOSOA = eatAll(PredicateOSOA, ' ');
        int i = 1;
        //GetXORTrueFalseRange(pExprn, PredicateHigh, Xn, DiffWithRespTo, Modulus, 10);
        for (double x = XXORmin,y=3.0;(i <= 10) && ((i < 3) || (Xn == Xnplus1))&&(Xnplus1 != x)&&(x !=Xnminus1XOR) ; x += XXORInc, i++) {
            if (x >= Modulus) {
                x = XXORmin;
            } else if ((int) x == Xnminus1XOR) {
                x = (x - (int) x) * 100;
                String Temp=""+x;
                String[] xString = Temp.split(".");
                if(xString.length ==3)
                    x = Double.parseDouble(xString[2]);
                x = (x - (int) x) * 100;
            } else if ((int) x == (Xnminus1XOR + 1)) {
                x += XXORInc;
            }else if ((int) x == 0) {
                String Temp=""+XXORInc;
                String[] xString = Temp.split(".");
                if(xString.length ==3)
                    x = Double.parseDouble(xString[2]);
                x = (x - (int) x) * 100;
            }
            variables.put(DiffWithRespTo, (double) x);
            variables.put("a", (double) a);
            variables.put("b", (double) b);
            variables.put("c", (double) c);
            variables.put("d", (double) d);
            variables.put("y", (double) y);
            pexpValue = PredicateSep.parsePredicate(pExprn, variables,"Silent", MathsContxtLAv.LogLevel.Off);
            qexpValue = PredicateSep.parsePredicate(qExprn, variables,"Silent", MathsContxtLAv.LogLevel.Off);
            log.fatal(AlgoName + ":y=" + y + " x(Xn)=" + x + " Xn(orig)=" + Xn + " Xnplus1=" + Xnplus1 + ":Result(qexpValue.eval())=" + qexpValue.eval() + " qExprn=" + qExprn);
            PExprn = (!pexpValue.eval()) || qexpValue.eval();
            QExprn = (pexpValue.eval()) && (!qexpValue.eval());

            pHighexpValue = PredicateSep.parsePredicate(PredicateHigh, variables,"Silent", MathsContxtLAv.LogLevel.Off);
            log.fatal(AlgoName + ":y=" + y + ":x(Xn)=" + x + " Xn(orig)=" + Xn + " Xnplus1=" + Xnplus1 + ":Result(pHighexpValue.eval())=" + pHighexpValue.eval() + " PredicateHigh=" + PredicateHigh);
            pSOAexpValue = PredicateSep.parsePredicate(PredicateSOA, variables,"Silent", MathsContxtLAv.LogLevel.Off);
            log.fatal(AlgoName + ":y=" + y + ":x(Xn)=" + x + " Xn(orig)=" + Xn + " Xnplus1=" + Xnplus1 + ":Result(pSOAexpValue.eval())=" + pSOAexpValue.eval() + " PredicateSOA=" + PredicateSOA);
            pOSOAexpValue = PredicateSep.parsePredicate(PredicateOSOA, variables,"Silent", MathsContxtLAv.LogLevel.Off);
            log.fatal(AlgoName + ":y=" + y + ":x(Xn)=" + x + " Xn(orig)=" + Xn + " Xnplus1=" + Xnplus1 + ":Result(pOSOAexpValue.eval())=" + pOSOAexpValue.eval() + " PredicateOSOA=" + PredicateOSOA);
            if ((pHighexpValue.eval() == false) && (!((x != Xn) && (x != Xnplus1) && ((int) x != Xnminus1XOR) && (Xn != Xnminus1XOR)))) {
                log.fatal(AlgoName + ":2:pHighexpValue:Continuing");
                continue;
            } //else if (pSOAexpValue.eval() == false) {
            //    log.fatal(AlgoName + ":3:Continuing");
            //   continue;
            //} 
            else if ((pOSOAexpValue.eval() == true) && (!((x != Xn) && (x != Xnplus1)))) {
                log.fatal(AlgoName + ":4:pOSOAexpValue:Continuing");
                //System.out.println(AlgoName + ":0:pxExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                continue;
            } else {
                if ((x != Xn) && (x != Xnminus1XOR)
                        && (pHighexpValue.eval() == true)
                        && ((Parameter.equalsIgnoreCase("")) && (pexpValue.eval() == true))
                        && (pSOAexpValue.eval() == true)
                        && (pOSOAexpValue.eval() == false)) {
                    Xnplus1 = (long) x;
                    System.out.println(AlgoName + ":1:pxExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                }
                if ((x != Xn) && (x != Xnminus1XOR)
                        && (pHighexpValue.eval() == true)
                        && ((Parameter.equalsIgnoreCase("")) && (qexpValue.eval() == true))
                        && (pSOAexpValue.eval() == true)
                        && (pOSOAexpValue.eval() == false)) {
                    Xnplus1 = (long) x;
                    log.fatal(AlgoName + ":2:qxExprn:Assigning(Xnplus1)=" + Xnplus1);;
                    System.out.println(AlgoName + ":2:qxExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                }
                if ((x != Xn) && (x != Xnminus1XOR)
                        && (pHighexpValue.eval() == true)
                        && ((Parameter.equalsIgnoreCase("")) && (PExprn == true))
                        && (pSOAexpValue.eval() == true)
                        && (pOSOAexpValue.eval() == false)) {
                    Xnplus1 = (long) x;
                    log.fatal(AlgoName + ":3:PExprn:Assigning(Xnplus1)=" + Xnplus1);
                    System.out.println(AlgoName + ":3:PExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                }
                if ((x != Xn) && (x != Xnminus1XOR)
                        && (pHighexpValue.eval() == true)
                        && ((Parameter.equalsIgnoreCase("")) && (QExprn == true))
                        && (pSOAexpValue.eval() == true)
                        && (pOSOAexpValue.eval() == false)) {
                    Xnplus1 = (long) x;
                    log.fatal(AlgoName + ":4:QExprn:Assigning(Xnplus1)=" + Xnplus1);
                    System.out.println(AlgoName + ":4:QExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                }else{
                    SwapStr=PredicateHigh;
                    PredicateHigh=PredicateSOA;
                    PredicateSOA=SwapStr;
                    Xnplus1 = (long) x;
                    log.fatal(AlgoName + ":5:QExprn:Assigning(Xnplus1)=" + Xnplus1);
                    System.out.println(AlgoName + ":5:QExprn:Assigning(Xnplus1)=" + Xnplus1 + " Xn=" + Xn + " x=" + x + " Xnminus1XOR=" + Xnminus1XOR);
                }
                log.fatal(AlgoName + " x=" + x + " Xn=" + Xn + " Xnplus1=" + Xnplus1 + " Xnplus1Bool=" + Xnplus1Bool);
                //GetXORTrueFalseRange(pExprn, PredicateHigh, Xnplus1, DiffWithRespTo, Modulus, (10 - i));
            }
        }
        log.fatal(AlgoName + ":Result(Xnplus1)=" + Xnplus1);
        //System.out.println(AlgoName + ":Result(Xnplus1)=" + Xnplus1);
        Xnminus1XOR = Xn;
        return Xnplus1;
    }
 public static Double XOR_CircularRandom(String AlgoName, long Xn, long radius, long a, long c,long b, long d, long Modulus) {
     AlgoName=AlgoName+":XOR_CircularRandom";
     Double Xnplus1Output = 0.0;
     Double y = 0.0;
     Map<String, Double> variables = new HashMap<>();
     MathsContxtLAv.Expression expOutputStateValue;
     MathsContxtLAv.Expression expStateEqnValue;
     String DiffWithRespTo = "Xn";
     Double EntropyXn=0.0;
     MyFuncExpress();
     // Ground Zero Settings Begin
     if(Xn >= (Modulus*radius) ){
         Xn=Xn%(Modulus*radius);
     }    
     // Ground Zero Settings End
     variables.put(DiffWithRespTo, (double) Xn);
     variables.put("y", (double) y);
     variables.put("radius", (double) radius);
     variables.put("Modulus", (double) Modulus);
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     EntropyXn=(double)EntPool[0].MySimple.LCG_Value_RandomSimple((long) Xn, (long) a, (long) c, (int) Modulus, (int) 37, (int) Modulus);
     if(DebugFlag)
         PrintInitial(AlgoName, Xn, radius, y, EntropyXn, Xnplus1Output);
    // Add Randomness to Xn
     //Xn=(long)((Xn+EntropyXn)%Modulus);
     variables.put(DiffWithRespTo, (double) Xn); 
    // Compute y from x && radius
     String StateEqnCircular="Modulus^2*radius^2-Xn^2";//y^2=Modulus^2*Radius^2-x^2 for x^2 + y^2=Radius^2;
     expStateEqnValue = parse(StateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     Double DoubleXn=expStateEqnValue.eval()%Modulus;
     y=Math.sqrt(DoubleXn);
     variables.put("y", (double) y);
     if(DebugFlag)
         PrintCompute(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     // Generate Next State Add Randomness to Xn OR Add Randomness to y
     y +=(EntropyXn)%Modulus;
     Xnplus1Output=y%Modulus;   
     if(DebugFlag)
         PrintGenerate(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     
     // Generate Output State
     String OutputStateEqnCircular="Xnplus1Output";
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     expOutputStateValue = parse(OutputStateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     variables.put("Xnplus1Output", (double) expOutputStateValue.eval());
     Xnplus1Output=variables.get("Xnplus1Output");       
     if(DebugFlag)
         PrintFinal(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     return Xnplus1Output;
 }
 public static Double XOR_CounterFlipFlop(String AlgoName, long Xn, long radius, long a, long c,long b, long d, long Modulus) {
     AlgoName=AlgoName+":XOR_CounterFlipFlop";
     Double Xnplus1Output = 0.0;
     Double y = 0.0;
     Map<String, Double> variables = new HashMap<>();
     MathsContxtLAv.Expression expOutputStateValue;
     MathsContxtLAv.Expression expStateEqnValue;
     String DiffWithRespTo = "Xn";
     Double EntropyXn=0.0;
     MyFuncExpress();
     variables.put(DiffWithRespTo, (double) Xn);
     variables.put("y", (double) y);
     variables.put("radius", (double) radius);
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     if(DebugFlag)
         PrintInitial(AlgoName, Xn, radius, y, EntropyXn, Xnplus1Output);
     
     // Compute y from x && radius :Enforce Constraint
     String StateEqnCircular="radius^2-Xn^2";//y^2=Radius^2-x^2 for x^2 + y^2=Radius^2;
     expStateEqnValue = parse(StateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     Double DoubleXn=expStateEqnValue.eval()%radius;
     y=Math.sqrt(DoubleXn);
     variables.put("y", (double) y);
     if(DebugFlag)
         PrintCompute(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
    
     Xnplus1Output=y;       
     if(DebugFlag)
         PrintGenerate(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     
     // Generate Output State:1 is to 1 mapping
     String OutputStateEqnCircular="Xnplus1Output";
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     expOutputStateValue = parse(OutputStateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     variables.put("Xnplus1Output", (double) expOutputStateValue.eval());
     Xnplus1Output=variables.get("Xnplus1Output");       
     if(DebugFlag)
         PrintFinal(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     
     return Xnplus1Output;
 }
 public static Double XOR_ComputerEntropyJavaCalculus(String AlgoName, long Xn, long radius, long a, long c,long b, long d, long Modulus) {
     AlgoName=AlgoName+":XOR_ComputerEntropyJavaCalculus";
     Double Xnplus1Output = 0.0;
     Double y = 0.0;
     Map<String, Double> variables = new HashMap<>();
     MathsContxtLAv.Expression expOutputStateValue;
     MathsContxtLAv.Expression expStateEqnValue;
     String DiffWithRespTo = "Xn";
     Double EntropyXn=0.0;
     MyFuncExpress();
     // Ground Zero Settings Begin
     if(Xn >= (Modulus*radius) ){
         Xn=Xn%(Modulus*radius);
     }    
     // Ground Zero Settings End
     variables.put(DiffWithRespTo, (double) Xn);
     variables.put("y", (double) y);
     variables.put("radius", (double) radius);
     variables.put("Modulus", (double) Modulus);
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     if(DebugFlag)
         PrintInitial(AlgoName, Xn, radius, y, EntropyXn, Xnplus1Output);
     variables.put(DiffWithRespTo, (double) Xn); 
     // Compute y from x && radius
     String StateEqnCircular="Modulus^2*radius^2-Xn^2";//y^2=Modulus^2*Radius^2-x^2 for x^2 + y^2=Radius^2;
     expStateEqnValue = parse(StateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     Double DoubleXn=expStateEqnValue.eval()%Modulus;
     y=Math.sqrt(DoubleXn);
     variables.put("y", (double) y);
     if(DebugFlag)
         PrintCompute(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     // Generate Next State Add Randomness to Xn OR Add Randomness to y
     Xnplus1Output=y%Modulus;   
     if(DebugFlag)
         PrintGenerate(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     
     // Generate Output State
     String OutputStateEqnCircular="Xnplus1Output";
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     expOutputStateValue = parse(OutputStateEqnCircular, variables, DiffWithRespTo, "Silent", MathsContxtLAv.LogLevel.Off);
     variables.put("Xnplus1Output", (double) expOutputStateValue.eval());
     Xnplus1Output=variables.get("Xnplus1Output");       
     if(DebugFlag)
         PrintFinal(AlgoName, Xn, radius, y, EntropyXn, DoubleXn, Xnplus1Output);
     return Xnplus1Output;
 }
 public static Double XOR_ComputerEntropyValues(String AlgoName, long Xn, long radius, long a, long c,long b, long d, long Modulus) {
     AlgoName=AlgoName+":XOR_ComputerEntropyValues";
     Double Xnplus1Output = 0.0;
     Double y = 0.0;
     Map<String, Double> variables = new HashMap<>();
     MathsContxtLAv.Expression expOutputStateValue;
     MathsContxtLAv.Expression expStateEqnValue;
     String DiffWithRespTo = "Xn";
     Double EntropyXn=0.0;
     MyFuncExpress();
     // Ground Zero Settings Begin
     if(Xn >= (Modulus*radius) ){
         Xn=Xn%(Modulus*radius);
     }    
     // Ground Zero Settings End
     variables.put(DiffWithRespTo, (double) Xn);
     variables.put("y", (double) y);
     variables.put("radius", (double) radius);
     variables.put("Modulus", (double) Modulus);
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     if(DebugFlag)
         PrintInitial(AlgoName, Xn, radius, y, EntropyXn, Xnplus1Output);
     variables.put(DiffWithRespTo, (double) Xn); 
     // Compute y from Xn && radius where Xn is random
     Double DoubleXn=((double)(Modulus*Modulus*radius*radius)-Xn*Xn)%Modulus;
     y=Math.sqrt(DoubleXn);
     variables.put("y", (double) y);
     if(DebugFlag)
         PrintCompute(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     // Generate Next State Add Randomness to Xn OR Add Randomness to y
     Xnplus1Output=y%Modulus;   
     if(DebugFlag)
         PrintGenerate(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     
     // Generate Output State
      variables.put("Xnplus1Output", (double) Xnplus1Output);
      Xnplus1Output=variables.get("Xnplus1Output");       
      if(DebugFlag)
          PrintFinal(AlgoName, Xn, radius, y, EntropyXn, DoubleXn,Xnplus1Output);
     return Xnplus1Output;
 }
 public static Double XOR_SandCastle(String AlgoName, long Xn, long radius, long a, long c,long b, long d, long Modulus, int SandCastle) {
     AlgoName=AlgoName+":XOR_SandCastle";
     Double Xnplus1Output = 0.0;
     Double y = 0.0;
     Map<String, Double> variables = new HashMap<>();
     MathsContxtLAv.Expression expOutputStateValue;
     MathsContxtLAv.Expression expStateEqnValue;
     String DiffWithRespTo = "Xn";
     Double EntropyXn=0.0;
     MyFuncExpress();
     // Ground Zero Settings Begin
     if(Xn >= (Modulus*radius) ){
         Xn=Xn%(Modulus*radius);
     }    
     // Ground Zero Settings End
     variables.put(DiffWithRespTo, (double) Xn);
     variables.put("y", (double) y);
     variables.put("radius", (double) radius);
     variables.put("Modulus", (double) Modulus);
     variables.put("Xnplus1Output", (double) Xnplus1Output);
     if(DebugFlag)
         PrintInitial(AlgoName, Xn, radius, y, EntropyXn, Xnplus1Output);
     variables.put(DiffWithRespTo, (double) Xn); 
     // Compute y from Xn && radius where Xn is random
     Double DoubleXn=((double)(Modulus*Modulus*radius*radius)-Xn*Xn)%Modulus;
     y=SandCastleStateFSM(DoubleXn,SandCastle,NumberOfStates);
     variables.put("y", (double) y);
     if(DebugFlag)
         PrintCompute(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     // Generate Next State Add Randomness to Xn OR Add Randomness to y
     Xnplus1Output=y%Modulus;   
     if(DebugFlag)
         PrintGenerate(AlgoName, Xn, radius, y, EntropyXn,DoubleXn, Xnplus1Output);
     
     // Generate Output State
      variables.put("Xnplus1Output", (double) Xnplus1Output);
      Xnplus1Output=variables.get("Xnplus1Output");       
      if(DebugFlag)
          PrintFinal(AlgoName, Xn, radius, y, EntropyXn, DoubleXn,Xnplus1Output);
     return Xnplus1Output;
 }
    public static void GetXORTrueFalseRange(String Exprn, String PredicateHigh, long Xn, String DiffWithRespTo, double Modulus, int NoOfTimes) {
        PredicateSep.PredicateBool pexpValue;
        Map<String, Double> variables = new HashMap<>();
        variables.put(DiffWithRespTo, ((double) Modulus));
        pexpValue = PredicateSep.parsePredicate(PredicateHigh, variables,"Silent", MathsContxtLAv.LogLevel.Off);
        Boolean Ftrue = pexpValue.eval();
        if (Modulus > 0) {
            XXORmax = Modulus;
        } else {
            XXORmax = -Modulus;
        }
        variables.put(DiffWithRespTo, ((double) 0));
        pexpValue = PredicateSep.parsePredicate(Exprn, variables,"Silent", MathsContxtLAv.LogLevel.Off);
        Boolean Ffalse = pexpValue.eval();
        if (Xn > 0) {
            XXORmin = Xn;
        } else {
            XXORmin = 1;
        }
        if (Modulus > 0) {
            XXORInc = NoOfTimes * 2 * 1 / Modulus;
        } else {
            XXORInc = 1 / Modulus;
        }
        log.fatal("GetXORTrueFalseRange:XXORmax=" + XXORmax + " XXORmin=" + XXORmin + " XXORInc=" + XXORInc + " NoOfTimes=" + NoOfTimes + " Modulus=" + Modulus);
        //System.out.println("GetXORTrueFalseRange:XXORmax=" + XXORmax + " XXORmin=" + XXORmin + " XXORInc=" + XXORInc + " NoOfTimes=" + NoOfTimes + " Modulus=" + Modulus + " Xnminus1XOR=" + Xnminus1XOR);
    }

    public static void WriteToFileXOR(int i, long Xn_XOR, long NoOfBuffBitsMax, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        String FileName = Path + "RNDXn_XOR" + RunNumber;
        String DataLCG = "" + Xn_XOR + " ";
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
            log.info("Exception: RND WriteToFile");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
    }
    public void UpdateStats(long Xn_XOR) {
        if ((NextIthLCGPatRecog == Xn_XOR)
                || (NextIthLCGPatternRep == Xn_XOR)
                || (NextIthLCGPattern == Xn_XOR)
                || (NextIthLCGBuffer == Xn_XOR)) {
            GuessLCG++;
            if (NextIthLCGPatRecog == Xn_XOR) {
                GuessLCGPatRecog++;
            }
            if (NextIthLCGPatternRep == Xn_XOR) {
                GuessLCGRep++;
            }
            if (NextIthLCGPattern == Xn_XOR) {
                GuessLCGPattern++;
            }
            if (NextIthLCGBuffer == Xn_XOR) {
                GuessLCGBuff++;
            }
        }
    }
    
    //Detect the Pattern
    public void DetectLCGPattern(int EntIndex, long NoOfSlots, long Xn) {
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
            this.DataStoreXOR.removeAll(DataStoreXOR);
            for (int i = 0; i < PatternFreqMaxIndex[EntIndex]; i++) {
                this.DataStoreXOR.add(new XORRandom.PatternBits(((int) this.FreqMaxStore[i][0]), ((int) this.FreqMaxStore[i][1])));
            }
            Collections.sort(this.DataStoreXOR, new XORRandom.SortbyFreq());
    }
    
    public void SearcNFormXORPattern(int EntIndex, long Xn) {
        int i = 0;
        for (i = 0; (i < PatternFreqMaxIndex[EntIndex]) ; i++)
               if((Xn == this.DataStoreXOR.get(i).Xn) )
                       break;
        //Found PatternBit
        //Constant Pattern
        if ((i < PatternFreqMaxIndex[EntIndex]) && ((i+1) <= DataStoreXOR.size()) ) {
            this.NextIthLCGPatRecog = (long) DataStoreXOR.get(i).Xn;
            //System.out.println("SearcNFormXORPattern:Constant Pattern:Xn=" + Xn + " Found NextIthLCGPatRecog=" + this.NextIthLCGPatRecog);
        }//Random Pattern
        else if ((i < PatternFreqMaxIndex[EntIndex]) && ((i+1)  < DataStoreXOR.size()) ) {
            this.NextIthLCGPatRecog = (long) DataStoreXOR.get(i+1).Xn;
            //System.out.println("SearcNFormXORPattern:Random Pattern:Xn=" + Xn + " Found NextIthLCGPatRecog=" + this.NextIthLCGPatRecog);
        }
    }

    public void PatternRepetionTest(int i, long IthXn_LCG) {
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
            if ((j < LCGPatternLastBits.size()) &&((j+1) <= LCGPatternLastBits.size()) )  {
                this.NextIthLCGPatternRep = (long) LCGPatternLastBits.get(j);
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

    public void PatternTest(int i, long IthXn_LCG) {
        int j = 0;
        for (j = 0; j < LCGPattern.length; j++) {
            if (LCGPattern[j] == IthXn_LCG) {
                break;
            }
        }
        //Found PatternBit
        //Constant Pattern
        if ((j < LCGPattern.length)&&((j+1) <= LCGPattern.length)){
            this.NextIthLCGPattern = (long) LCGPattern[j ];
        }//Random Pattern
        else if (j < LCGPattern.length) {
            this.NextIthLCGPattern = LCGPattern[j + 1];
        }
    }

    public void BufferTest(int i, long IthXn_LCG) {
        int j = 0;
        for (j = 0; j < LCGNextBitDataBuff.size() - 1; j++) {
            if ((long) LCGNextBitDataBuff.get(j) == IthXn_LCG) {
                break;
            }
        }
        //Found PatternBit
        //Constant Pattern
        if ((j < LCGNextBitDataBuff.size())&&((j+1) <= LCGNextBitDataBuff.size()) ){
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j);
        }//Random Pattern
        else if (j < LCGNextBitDataBuff.size() - 1) {
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j + 1);
        }
    }
    public void ReadFromFile(int EntIndex, long NoOfBuffBitsMax, long Xn_XOR, String RunNumber) {
        try {
                String FileName = Path + "RNDXn_XOR" + RunNumber;
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
                this.LCGNextBitDataBuff.add(Xn_XOR);
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
    public void printDocValues(int Index, int NoOfBits) {
            System.out.println("XOR InitValues:" + Index);
            PrintMeanValues(Index);
            System.out.printf("GuessLCG=%d %%=%5.4f\n", EntPool[Index].MyXOR.GuessLCG, (100.0 * (double) EntPool[Index].MyXOR.GuessLCG / (double) NoOfBits));
            System.out.printf("GuessLCGPatRecog=%d %%=%5.4f\n", EntPool[Index].MyXOR.GuessLCGPatRecog, (100.0 * (double) EntPool[Index].MyXOR.GuessLCGPatRecog / (double) NoOfBits));
            System.out.printf("GuessLCGRep=%d %%=%5.4f\n", EntPool[Index].MyXOR.GuessLCGRep, (100.0 * (double) EntPool[Index].MyXOR.GuessLCGRep / (double) NoOfBits));
            System.out.printf("GuessLCGPattern=%d %%=%5.4f\n", EntPool[Index].MyXOR.GuessLCGPattern, (100.0 * (double) EntPool[Index].MyXOR.GuessLCGPattern / (double) NoOfBits));
            System.out.printf("GuessLCGBuff=%d %%=%5.4f\n", EntPool[Index].MyXOR.GuessLCGBuff, (100.0 * (double) EntPool[Index].MyXOR.GuessLCGBuff / (double) NoOfBits));
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            //PrintFreqStore(Index);
            //PrintFreqMaxStore(Index);
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
    }
    public void PrintMeanValues(int Index){
            System.out.println("XOR Stats:" + Index + ":Mean=" + this.MeanSigma[0] + " Low Level Mean=" + this.Mean / NoOfBits);
            System.out.println("XOR Stats:" + Index + ":Sigma=" + this.MeanSigma[1]);
            System.out.println("XOR Stats:" + Index + ":Sigma(2 bit)=" + this.MeanSigma[2]);
            
    }
    public void PrintFreqStore(int Index){
        for (int i = 0; i < NoOfSlots; i++) {
                System.out.println("LCG Stats: i=" + i + " " + FreqStore[i] + " Probability=" + GaussianPDFStore[i] + " TheoryProb=" + (100.0 * RandomNumber.Probability(i, MeanSigma[0], MeanSigma[1])) + " Actual=" + 100 * FreqStore[i] / NoOfBits);
            }
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            
    }
    public void PrintFreqMaxStore(int Index){
        int Freq = 0;
            double mean = 0.0;
            for (int i = 0; i < PatternFreqMaxIndex[Index]; i++) {
                if (this.DataStoreXOR.get(i).Freq > 0) {
                    Freq += this.DataStoreXOR.get(i).Freq;
                }
                mean += this.DataStoreXOR.get(i).Xn * this.DataStoreXOR.get(i).Freq;
                if (this.DataStoreXOR.get(i).Freq > 0) {
                    System.out.println("XOR Stats:" + Index + ":Max:i=" + i + " Number=" + this.DataStoreXOR.get(i).Xn + " " + this.DataStoreXOR.get(i).Freq);
                }
            }
            System.out.println("XOR Stats:Max:Freq=" + Freq + " mean=" + (mean / NoOfBits));
            
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
    public static void main(String[] args) {
        Map<String, Double> variables = new HashMap<>();
        MyFuncExpress();
        MyFuncDiff();
        MyFuncSimple();
        UserTweaks();
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Started Time:" + current + " ");
        }
        EntPool = new Rand_Pool[EntPoolIndex + PoolIndexSeries];
        EntPool[0] = new Rand_Pool("RandomSimpleLCG", 0, NoOfSlots, ModulusEncrypt, Xn_LCG, Xn_LCGDyDx, a, c, b, MaxExponent);
        
        System.out.println("main:Xn_XOR=" + Xn_XOR + " Xn_LCG=" + Xn_LCG + " Xn_LCGDyDx=" + Xn_LCGDyDx + " Xn_LCG3=" + Xn_LCG3 + " Xn_LCGDyDx4=" + Xn_LCGDyDx4);
        System.out.println("main:NoOfBits=" + NoOfBits + " NoOfSlots=" + NoOfSlots + " NoOfBuffBitsMax=" + NoOfBuffBitsMax + " Modulus=" + Modulus + " PatternRepLength=" + PatternRepLength);
        System.out.println("main:Low Level Performance Parameters:MaxExponent=" + MaxExponent + " ModulusEncrypt=" + ModulusEncrypt);
        System.out.println("main:Low Level Performance Parameters:a=" + a + " c=" + c + " SeedXOR=" + SeedXOR);
        String Exprn = "";
        GetXORTrueFalseRange("(Xn^2+2^2-2*2*Xn+y^2+4^2-2*4*y) >100.0", "(Xn^2+5^2-2*5*Xn+y^2+7^2-2*7*y) >490", Xn_XOR, "Xn", Modulus, 10);
        for (int i = 0; i < NoOfBits; i++) {
            //Xn_XOR = (int) LCG_XOR_Random( Xn_LCG,  (int)a,  (int)c, b, d , (int)Modulus,  (int)MaxExponent,  (int)ModulusEncrypt);
            Xn_Circular=XOR_CircularRandom("main", (long) Xn_Circular, (long)5,  a,  c, b,  d,  Modulus);
            //Xn_Circular=XOR_CounterFlipFlop("main", (long) Xn_Circular, (long)5,  a,  c, b,  d,  Modulus);
            
            LowLevelPrintOut(i, StudyType);
            WriteToFileXOR(i, Xn_XOR, NoOfBuffBitsMax,RunNumber);
            WriteToFileXORCircular(i, (long)Xn_Circular, NoOfBuffBitsMax,RunNumber);
           }
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Started Time:" + current + " ");
        }
    }
    public static void WriteToFileXORCircular(int i, long Xn_XOR, long NoOfBuffBitsMax, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        String FileName = Path + "RNDXn_XORCircular" + RunNumber;
        String DataLCG = "" + Xn_XOR + " ";
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
            log.info("Exception: RND WriteToFile");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
    }
    public static void PrintInitial(String AlgoName,long Xn,long radius,Double y,Double EntropyXn,Double Xnplus1Output){
      System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(Xn)=" + Xn);
     System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(Modulus)=" + Modulus);
     System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(radius)=" + radius);
     System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(y)=" + y);
     System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(EntropyXn)=" + EntropyXn);
     System.out.println(AlgoName + ":Initial:XOR_CircularRandom:Result(Xnplus1Output)=" + Xnplus1Output);
    }
    public static void PrintCompute(String AlgoName,long Xn,long radius,Double y,Double EntropyXn, Double DoubleXn,Double Xnplus1Output){
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(Xn)=" + Xn);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(Modulus)=" + Modulus);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(radius)=" + radius);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(y)=" + y);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(DoubleXn)=" + DoubleXn);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(EntropyXn)=" + EntropyXn);
     System.out.println(AlgoName + ":Compute:XOR_CircularRandom:Result(Xnplus1Output)=" + Xnplus1Output);
   }
    public static void PrintGenerate(String AlgoName,long Xn,long radius,Double y,Double EntropyXn,Double DoubleXn, Double Xnplus1Output){
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(Xn)=" + Xn);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(Modulus)=" + Modulus);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(radius)=" + radius);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(y)=" + y);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(DoubleXn)=" + DoubleXn);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(EntropyXn)=" + EntropyXn);
     System.out.println(AlgoName + ":Generate:XOR_CircularRandom:Result(Xnplus1Output)=" + Xnplus1Output);
    }
    public static void PrintFinal(String AlgoName,long Xn,long radius,Double y,Double EntropyXn,Double DoubleXn,Double Xnplus1Output){
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(Xn)=" + Xn);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(Modulus)=" + Modulus);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(radius)=" + radius);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(y)=" + y);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(DoubleXn)=" + DoubleXn);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(EntropyXn)=" + EntropyXn);
     System.out.println(AlgoName + ":Final:XOR_CircularRandom:Result(Xnplus1Output)=" + Xnplus1Output);
    }
    public static Double SandCastleStateFSM(Double DoubleXn,int SandCastle,int NumberOfStates){
     Double OutputY=0.0;
     if(SandCastle==0){
             OutputY=Math.sqrt(DoubleXn);
             if (DebugFlag){
                 PrintSandCastleFSM( "Rule1",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else if(SandCastle==1){
             OutputY=Math.sqrt(DoubleXn);
             if (DebugFlag){
                 PrintSandCastleFSM( "Rule2",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else if((SandCastle > 1) && (SandCastle < (NumberOfStates-3)) ) {
         // if (double) below is removed  power second argument is 0 due to integer division (both SandCastle,  NumberOfState are ints)
             OutputY=Math.pow(DoubleXn, (double)SandCastle/(double)(NumberOfStates));
             if (DebugFlag){
                 PrintSandCastleFSM( "RuleX",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else if((SandCastle==(NumberOfStates-3))){
         OutputY=Math.pow(DoubleXn, 0.333);
         if (DebugFlag){
                 PrintSandCastleFSM( "RuleX-3",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else if(SandCastle==(NumberOfStates-2)){
         OutputY=Math.pow(DoubleXn, 0.25);
         if (DebugFlag){
                 PrintSandCastleFSM( "RuleX-2",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else if(SandCastle==(NumberOfStates-1)){
         OutputY=Math.pow(DoubleXn, 0.20);
         if (DebugFlag){
                 PrintSandCastleFSM( "RuleX-1",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     else {
         OutputY=Math.pow(DoubleXn, 0.2);
         if (DebugFlag){
                 PrintSandCastleFSM( "RuleElse",DoubleXn,  SandCastle,  NumberOfStates,  OutputY);
             }
     }
     return OutputY;
    }
    public static void PrintSandCastleFSM(String Rule,Double DoubleXn, int SandCastle, int NumberOfStates, Double OutputY){
        System.out.println("SandCastleStateFSM: "+ Rule + ":Result(DoubleXn)=" + DoubleXn);
        System.out.println("SandCastleStateFSM: "+ Rule + ":Result(SandCastle)=" + SandCastle);
        System.out.println("SandCastleStateFSM: "+ Rule + ":Result(NumberOfStates)=" + NumberOfStates);
        System.out.println("SandCastleStateFSM: "+ Rule + ":Result(SandCastle/NumberOfStates)=" + (double)SandCastle/(double)NumberOfStates);
        System.out.println("SandCastleStateFSM: "+ Rule + ":Result(OutputY)=" + OutputY);
    }
}
