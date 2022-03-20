/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import static RandomNumber.RandomSimpleLCG.log;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.EntPoolIndex;
import static RandomNumber.UserResearchStudy.LCGPatternLastBits;
import static RandomNumber.UserResearchStudy.NoOfBits;
import static RandomNumber.UserResearchStudy.NoOfSlots;
import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.PatternFreqMaxIndexSeries;
import static RandomNumber.UserResearchStudy.PatternLength;
import static RandomNumber.UserResearchStudy.PatternRepLength;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class RandomSeries {
    public static Logger log = Logger.getLogger(RandomSeries.class.getName());
    // Stats Data Structures
    public Double[] FreqStore=null;
    public Double[][] FreqMaxStore=null;
    public Double[] GaussianPDFStore=null;
    public Double[] EntropyPDFStore=null;
    public Double[] MeanSigma=null;
    
    public double Mean=0.0;
    public double Sigma=0.0;
    
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
    public static LinkedList LCGNextBitDataBuff = new LinkedList();
   
    public ArrayList<PatternBits> DataStoreLCG = new ArrayList<PatternBits>();
    
    // Read Write File Control Mechanism
    public String ReadExpireStr = "Linear";//Number,FactorNumberOfBits,Linear
    public long ReadExpireFactor = 3;
    public long ReadExpireMax = 100;
    public long ReadExpireNumber = 0;
    public boolean ReadExpireFlag = false;
    
    public RandomSeries(long Index, long NoOfSlots){
            this.FreqStore = new Double[(int) NoOfSlots];
            this.FreqMaxStore = new Double[PatternLength][2];
            this.GaussianPDFStore = new Double[(int) NoOfSlots];
            this.EntropyPDFStore = new Double[(int) NoOfSlots];
            this.MeanSigma = new Double[3];
            for (int j = 0; j < NoOfSlots; j++) {
                this.FreqStore[j] = 0.0;
                this.GaussianPDFStore[j] = 0.0;
                this.EntropyPDFStore[j] = 0.0;
            }
            for (int j = 0; j < 3; j++) {
                this.MeanSigma[j] = 0.0;
            }
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < PatternLength; k++) {
                    this.FreqMaxStore[k][j] = 0.0;
                }
            }
            if (ReadExpireStr.equalsIgnoreCase("Number")) {
            ReadExpireMax = ReadExpireMax;
        } else if (ReadExpireStr.equalsIgnoreCase("FactorNumberOfBits")) {
            ReadExpireMax = NoOfBits / ReadExpireFactor;
        } else if (ReadExpireStr.equalsIgnoreCase("Linear")) {
            ReadExpireMax = ReadExpireMax + NoOfBits / ReadExpireFactor;
        }
     }
    
    void UpdateSeriesStats(long Xn_LCG){
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
    void DetectSeriesPatternLow(int Index,long NoOfSlots, long Xn) {
        boolean LoopAssignFlag = false;
        
        // Extract Freq Distribution Info from FreqStore
        if ((PatternFreqMaxIndexSeries[Index] < PatternLength)
                && (PatternFreqMaxIndexSeries[Index] == 0)
                && (this.FreqMaxStore[0][0] == 0) && (this.FreqMaxStore[0][1] == 0)) {
            this.FreqMaxStore[0][0] = 1.0*Xn;
            this.FreqMaxStore[0][1] = this.FreqStore[(int) Xn];
            PatternFreqMaxIndexSeries[Index]++;
        } else if ((PatternFreqMaxIndexSeries[Index] < PatternLength)
                && (PatternFreqMaxIndexSeries[Index] == 0)
                && (Xn == this.FreqMaxStore[0][0])
                && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[0][1])) {
            this.FreqMaxStore[0][1] = this.FreqStore[(int) Xn];
        } else if ((PatternFreqMaxIndexSeries[Index] < PatternLength)
                && (PatternFreqMaxIndexSeries[Index] > 0)) {
            for (int i = 0; (i < PatternLength) && (i < PatternFreqMaxIndexSeries[Index]); i++) {
                if ((Xn == this.FreqMaxStore[i][0])) {
                    this.FreqMaxStore[i][1] = this.FreqStore[(int) Xn];
                    LoopAssignFlag = true;
                }
            }
            if ((LoopAssignFlag == false) && (PatternFreqMaxIndexSeries[Index] < PatternLength)
                    && (PatternFreqMaxIndexSeries[Index] > 0)
                    && (this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][0] == 0)
                    && (this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][1] == 0)) {
                this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][0] = 1.0*Xn;
                this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][1] = this.FreqStore[(int) Xn];
                PatternFreqMaxIndexSeries[Index]++;
            }
        }else if ((PatternFreqMaxIndexSeries[Index] < PatternLength)
                && (PatternFreqMaxIndexSeries[Index] > 0)
                && (Xn == this.FreqMaxStore[PatternFreqMaxIndexSeries[0]][0])
                && (this.FreqStore[(int) Xn] >= this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][1])) {
            this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][0] = 1.0*Xn;
            this.FreqMaxStore[PatternFreqMaxIndexSeries[Index]][1] = this.FreqStore[(int) Xn];
            PatternFreqMaxIndexSeries[Index]++;
        } 
        Mean+=Xn;
        //Sort by Frequency
        DataStoreLCG.clear();
        for (int i = 0; i < PatternFreqMaxIndexSeries[Index]; i++) {
            DataStoreLCG.add(new PatternBits(((int)(1.0*this.FreqMaxStore[i][0]) ), ((int)(1.0*this.FreqMaxStore[i][1])) ));
        }
        Collections.sort(DataStoreLCG, new SortbyFreq());
    }
    void SearcNFormSeriesPattern(int Index, long Xn) {
        int i = 0;
        int j = 0;
        for (i = 0; (i < PatternFreqMaxIndexSeries[Index]) && ((Xn != DataStoreLCG.get(i).Xn)); i++)
                ;
        //Found PatternBit
        if ((i < PatternFreqMaxIndexSeries[Index]) && ((i+1) <= DataStoreLCG.size()) ) {
            this.NextIthLCGPatRecog = (long) DataStoreLCG.get(i ).Xn;
        }else if ((i < PatternFreqMaxIndexSeries[Index]) && ((i+1) <= DataStoreLCG.size()) ) {
            this.NextIthLCGPatRecog = (long) DataStoreLCG.get(i+1 ).Xn;
        }
    }
    //Theory & Source:Wiki Next-bit Test And Yao's Test
    static void GuessNextIthBitSeries(int EntIndex, int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        //NextIthLCG
        //NextIthLCGDyDx
        LowLevelDeterministicGuess(EntIndex, i, Xn_Series0, Xn_Series1, Xn_Series2,Xn_Series3, Xn_Series4,Xn_Series5);
    }

    
    static void LowLevelDeterministicGuess(int EntIndex, int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        PatternRecognitionTest(EntIndex, i, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4,Xn_Series5);//0.0%
        PatternRepetionTest(i, Xn_Series0, Xn_Series1, Xn_Series2,Xn_Series3, Xn_Series4,Xn_Series5);//0.0%
        PatternTest(i, Xn_Series0, Xn_Series1, Xn_Series2,Xn_Series3, Xn_Series4, Xn_Series5);//0.0%
        BufferTest(i, Xn_Series0, Xn_Series1, Xn_Series2,Xn_Series3, Xn_Series4,Xn_Series5);//49.8200% :91.4000%

    }

    static void PatternRecognitionTest(int EntIndex, int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        EntPool[(int)EntPoolIndex+0].Series1.SearcNFormSeriesPattern(0, Xn_Series0);
        EntPool[(int)EntPoolIndex+1].Series1.SearcNFormSeriesPattern(0, Xn_Series1);
        EntPool[(int)EntPoolIndex+2].Series1.SearcNFormSeriesPattern(0, Xn_Series2);
        EntPool[(int)EntPoolIndex+3].Series1.SearcNFormSeriesPattern(0, Xn_Series3);
        EntPool[(int)EntPoolIndex+4].Series1.SearcNFormSeriesPattern(0, Xn_Series4);
    }

    static void PatternRepetionTest(int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        int Index = 0;
        EntPool[(int)EntPoolIndex+0].Series1.PatternRepetionTest(i, Xn_Series0);
        EntPool[(int)EntPoolIndex+1].Series1.PatternRepetionTest(i, Xn_Series1);
        EntPool[(int)EntPoolIndex+2].Series1.PatternRepetionTest(i, Xn_Series2);
        EntPool[(int)EntPoolIndex+3].Series1.PatternRepetionTest(i, Xn_Series3);
        EntPool[(int)EntPoolIndex+4].Series1.PatternRepetionTest(i, Xn_Series4);
        EntPool[(int)EntPoolIndex+5].Series1.PatternRepetionTest(i, Xn_Series5);
    }

    static void PatternTest(int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        int j = 0;
        EntPool[(int)EntPoolIndex+0].Series1.PatternTest(i, Xn_Series0);
        EntPool[(int)EntPoolIndex+1].Series1.PatternTest(i, Xn_Series1);
        EntPool[(int)EntPoolIndex+2].Series1.PatternTest(i, Xn_Series2);
        EntPool[(int)EntPoolIndex+3].Series1.PatternTest(i, Xn_Series3);
        EntPool[(int)EntPoolIndex+4].Series1.PatternTest(i, Xn_Series4);
        EntPool[(int)EntPoolIndex+5].Series1.PatternTest(i, Xn_Series5);
    }

    static void BufferTest(int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4,long Xn_Series5) {
        int j = 0;
        EntPool[(int)EntPoolIndex+0].Series1.BufferTest(i, Xn_Series0);
        EntPool[(int)EntPoolIndex+1].Series1.BufferTest(i, Xn_Series1);
        EntPool[(int)EntPoolIndex+2].Series1.BufferTest(i, Xn_Series2);
        EntPool[(int)EntPoolIndex+3].Series1.BufferTest(i, Xn_Series3);
        EntPool[(int)EntPoolIndex+4].Series1.BufferTest(i, Xn_Series4);
        EntPool[(int)EntPoolIndex+5].Series1.BufferTest(i, Xn_Series5);
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
            if ((j < LCGPatternLastBits.size())&&((j+1) <= LCGPatternLastBits.size())) {
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
    void PatternTest(int i, long IthXn_LCG) {
        int j = 0;
        for (j = 0; j < LCGPattern.length; j++) {
            if (LCGPattern[j] == IthXn_LCG) {
                break;
            }
        }
        //Found PatternBit
        //Constant Pattern
        if ((j < LCGPattern.length)&&((j+1) <= LCGPattern.length)) {
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
        if ((j < LCGNextBitDataBuff.size())&&((j+1) <= LCGNextBitDataBuff.size()) )  {
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j);
        }//Random Pattern
        else if (j < LCGNextBitDataBuff.size() - 1) {
            this.NextIthLCGBuffer = (long) LCGNextBitDataBuff.get(j + 1);
        }
    }
    
    void ReadFromFileSeries(int EntIndex, long NoOfBuffBitsMax, long Xn_LCG, String RunNumber) {
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
    
    
    void printDocValues(int Index, int NoOfBits){
            System.out.println("Series Stats:" + Index + ":Mean=" + EntPool[Index].Series1.MeanSigma[0] + " Low Level Mean=" + this.Mean/NoOfBits);
            System.out.println("Series Stats:" + Index + ":Sigma=" + EntPool[Index].Series1.MeanSigma[1]);
            System.out.println("Series Stats:" + Index + ":Sigma(2 bit)=" + EntPool[Index].Series1.MeanSigma[2]);
            System.out.printf("GuessLCG=%d %%=%5.4f\n", EntPool[Index].Series1.GuessLCG, (100.0 * (double) EntPool[Index].Series1.GuessLCG / (double) NoOfBits));
            System.out.printf("GuessLCGPatRecog=%d %%=%5.4f\n", EntPool[Index].Series1.GuessLCGPatRecog, (100.0 * (double) EntPool[Index].Series1.GuessLCGPatRecog / (double) NoOfBits));
            System.out.printf("GuessLCGRep=%d %%=%5.4f\n", EntPool[Index].Series1.GuessLCGRep, (100.0 * (double) EntPool[Index].Series1.GuessLCGRep / (double) NoOfBits));
            System.out.printf("GuessLCGPattern=%d %%=%5.4f\n", EntPool[Index].Series1.GuessLCGPattern, (100.0 * (double) EntPool[Index].Series1.GuessLCGPattern / (double) NoOfBits));
            System.out.printf("GuessLCGBuff=%d %%=%5.4f\n", EntPool[Index].Series1.GuessLCGBuff, (100.0 * (double) EntPool[Index].Series1.GuessLCGBuff / (double) NoOfBits));
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            //PrintFreqStore(Index);
            //PrintFreqMaxStore(Index);
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
    }
    void PrintFreqStore(int Index){
        for (int i = 0; i < NoOfSlots; i++) {
                System.out.println("LCG Stats: i=" + i + " " + FreqStore[i] + " Probability=" + GaussianPDFStore[i] + " TheoryProb=" + (100.0 * RandomNumber.Probability(i, MeanSigma[0], MeanSigma[1])) + " Actual=" + 100 * FreqStore[i] / NoOfBits);
            }
            System.out.print(System.lineSeparator());
            System.out.print(System.lineSeparator());
            
    }
    void PrintFreqMaxStore(int Index){
        int Freq = 0;
            double mean = 0.0;
            for (int i = 0; i < PatternFreqMaxIndexSeries[Index - EntPoolIndex]; i++) {
                if (EntPool[Index].Series1.DataStoreLCG.get(i).Freq >= 1) {
                    Freq += EntPool[Index].Series1.DataStoreLCG.get(i).Freq;
                }
                 mean += EntPool[Index].Series1.DataStoreLCG.get(i).Xn * EntPool[Index].Series1.DataStoreLCG.get(i).Freq;
                if (EntPool[Index].Series1.DataStoreLCG.get(i).Freq > 0)
                    System.out.println("Series Stats:" + Index + ":Max:i=" + i + " Number=" + EntPool[Index].Series1.DataStoreLCG.get(i).Xn + " " + EntPool[Index].Series1.DataStoreLCG.get(i).Freq);
            }
            System.out.println("Series Stats:Max:Freq=" + Freq + " mean=" + (mean / NoOfBits));
            
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
