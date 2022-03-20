/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import static Calculus.MathsContxtLAv.eatAll;
import static Calculus.MathsContxtLAv.parse;
import Calculus.MathsContxtLAv;
import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.EntPoolIndex;
import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.PoolIndexSeries;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class RandomNumber {

    public static Logger log = Logger.getLogger(RandomNumber.class.getName());

    //User Tweaks End
    //LCG Stats:Mean=2.8178
    //LCG Stats:Sigma=0.6637508706842554
    //LCGDyDx Stats:Mean=2.4864
    //LCGDyDx Stats:Sigma=0.687565217841708
    /**
     * @param args the command line arguments
     */
    static void Stats(int EntIndex, long Xn_LCG, long Xn_LCGDyDx, long Xn_LCG3, long Xn_LCGDyDx4, long Xn_XOR) {
        int i = EntIndex;
//for (int i = EntIndex; i < EntPoolIndex; i++) {
        EntPool[i].MySimple.FreqStore[(int) Xn_LCG]++;
        EntPool[i].MySimple.UpdateStats(Xn_LCG);
        i++;
        EntPool[i].MyFactorial.FreqStore[(int) Xn_LCGDyDx]++;
        EntPool[i].MyFactorial.UpdateStats(Xn_LCGDyDx);
        i++;
        EntPool[i].MySimple.FreqStore[(int) Xn_LCG3]++;
        EntPool[i].MySimple.UpdateStats(Xn_LCG3);
        i++;
        EntPool[i].MyFactorial.FreqStore[(int) Xn_LCGDyDx4]++;
        EntPool[i].MyFactorial.UpdateStats(Xn_LCGDyDx4);
        i++;
        EntPool[i].MyXOR.FreqStore[(int) Xn_XOR]++;
        EntPool[i].MyXOR.UpdateStats(Xn_XOR);
        //}
    }

    static void SeriesStats(int EntIndex, long NoOfSlots, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4, long Xn_Series5) {
        if (Xn_Series0 < EntPool[5].Series1.FreqStore.length) {
            EntPool[5].Series1.FreqStore[(int) Xn_Series0]++;
            EntPool[5].Series1.UpdateSeriesStats(Xn_Series0);
        }

        if (Xn_Series1 < EntPool[6].Series1.FreqStore.length) {
            EntPool[6].Series1.FreqStore[(int) Xn_Series1]++;
            EntPool[6].Series1.UpdateSeriesStats(Xn_Series1);
        }
        if (Xn_Series2 < EntPool[7].Series1.FreqStore.length) {
            EntPool[7].Series1.FreqStore[(int) Xn_Series2]++;
            EntPool[7].Series1.UpdateSeriesStats(Xn_Series2);
        }

        if (Xn_Series3 < EntPool[8].Series1.FreqStore.length) {
            EntPool[8].Series1.FreqStore[(int) Xn_Series3]++;
            EntPool[8].Series1.UpdateSeriesStats(Xn_Series3);
        }

        if (Xn_Series4 < EntPool[9].Series1.FreqStore.length) {
            EntPool[9].Series1.FreqStore[(int) Xn_Series4]++;
            EntPool[9].Series1.UpdateSeriesStats(Xn_Series4);
        }
        
        if (Xn_Series5 < EntPool[10].Series1.FreqStore.length) {
            EntPool[10].Series1.FreqStore[(int) Xn_Series5]++;
            EntPool[10].Series1.UpdateSeriesStats(Xn_Series5);
        }
    }

    static double Probability(double x, double mean, double Sigma) {
        return (1.0 / (Math.sqrt(2.0 * Math.PI)) * (Math.pow(Math.E, -(0.5 * (x - mean) / Sigma * (x - mean) / Sigma))));
    }

    static void InitializePDFStore(int EntIndex, long NoOfSlots) {
        MathsContxtLAv.Expression expValue;
        Map<String, Double> variables = new HashMap<>();
        int j = EntIndex;
        //for (int j = EntIndex; j < EntPoolIndex; j++) {
        String Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + "*exp(-(0.5 * (x - " + EntPool[j].MySimple.MeanSigma[0] + ") / " + EntPool[j].MySimple.MeanSigma[1] + " * (x - " + EntPool[j].MySimple.MeanSigma[0] + ") / " + EntPool[j].MySimple.MeanSigma[1] + "))))";
        Exprn = eatAll(Exprn, ' ');
        System.out.println("InitializePDFStore:LCG:Exprn=" + Exprn);
        //LCG Gaussian PDF
        for (int i = 1; i < NoOfSlots - 1; i++) {
            variables.put("x", ((double) i));
            expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
            Double Fmax = expValue.eval();
            EntPool[j].MySimple.GaussianPDFStore[i] = 100.0 * Fmax;
        }
        //LCG Gaussian PDF
        EntPool[j].MySimple.EntropyPDFStore[0] = 0.0;
        EntPool[j].MySimple.EntropyPDFStore[1] = 24.92;
        EntPool[j].MySimple.EntropyPDFStore[2] = 8.32;
        EntPool[j].MySimple.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[0][3] = 18.4;
//        EntropyPDFStore[0][4] = 0.0;
//        EntropyPDFStore[0][4] = 13.36;
//        EntropyPDFStore[0][5] = 18.3;
//        EntropyPDFStore[0][6] = 6.68;
//        EntropyPDFStore[0][7] = 10.02;
//        EntropyPDFStore[0][8] = 0.0;
        j++;
        //LCGDyDx Gaussian PDF
        Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + " *exp(-(0.5 * (x - " + EntPool[j].MyFactorial.MeanSigma[0] + ") / " + EntPool[j].MyFactorial.MeanSigma[1] + " * (x - " + EntPool[j].MyFactorial.MeanSigma[0] + ") / " + EntPool[j].MyFactorial.MeanSigma[1] + "))))";
        Exprn = eatAll(Exprn, ' ');
        System.out.println("InitializePDFStore:LCGDyDx:Exprn=" + Exprn);
        for (int i = 1; i < NoOfSlots - 1; i++) {
            variables.put("x", ((double) i));
            expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
            Double Fmax = expValue.eval();
            EntPool[j].MyFactorial.GaussianPDFStore[i] = 100.0 * Fmax;
        }
        //LCGDyDx Gaussian PDF
        EntPool[j].MyFactorial.EntropyPDFStore[0] = 0.0;
        EntPool[j].MyFactorial.EntropyPDFStore[1] = 31.64;
        EntPool[j].MyFactorial.EntropyPDFStore[2] = 11.64;
        EntPool[j].MyFactorial.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[1][3] = 11.66;
//        EntropyPDFStore[1][4] = 0.0;
//        EntropyPDFStore[1][4] = 11.68;
//        EntropyPDFStore[1][5] = 8.34;
//        EntropyPDFStore[1][6] = 11.72;
//        EntropyPDFStore[1][7] = 13.32;
//        EntropyPDFStore[1][8] = 0.0;
        j++;
        Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + "*exp(-(0.5 * (x - " + EntPool[j].MySimple.MeanSigma[0] + ") / " + EntPool[j].MySimple.MeanSigma[1] + " * (x - " + EntPool[j].MySimple.MeanSigma[0] + ") / " + EntPool[j].MySimple.MeanSigma[1] + "))))";
        Exprn = eatAll(Exprn, ' ');
        System.out.println("InitializePDFStore:LCG:Exprn=" + Exprn);
        //LCG Gaussian PDF
        for (int i = 1; i < NoOfSlots - 1; i++) {
            variables.put("x", ((double) i));
            expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
            Double Fmax = expValue.eval();
            EntPool[j].MySimple.GaussianPDFStore[i] = 100.0 * Fmax;
        }
        //LCG Gaussian PDF
        EntPool[j].MySimple.EntropyPDFStore[0] = 0.0;
        EntPool[j].MySimple.EntropyPDFStore[1] = 24.92;
        EntPool[j].MySimple.EntropyPDFStore[2] = 8.32;
        EntPool[j].MySimple.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[0][3] = 18.4;
//        EntropyPDFStore[0][4] = 0.0;
//        EntropyPDFStore[0][4] = 13.36;
//        EntropyPDFStore[0][5] = 18.3;
//        EntropyPDFStore[0][6] = 6.68;
//        EntropyPDFStore[0][7] = 10.02;
//        EntropyPDFStore[0][8] = 0.0;
        j++;
        //LCGDyDx Gaussian PDF
        Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + " *exp(-(0.5 * (x - " + EntPool[j].MyFactorial.MeanSigma[0] + ") / " + EntPool[j].MyFactorial.MeanSigma[1] + " * (x - " + EntPool[j].MyFactorial.MeanSigma[0] + ") / " + EntPool[j].MyFactorial.MeanSigma[1] + "))))";
        Exprn = eatAll(Exprn, ' ');
        System.out.println("InitializePDFStore:LCGDyDx:Exprn=" + Exprn);
        for (int i = 1; i < NoOfSlots - 1; i++) {
            variables.put("x", ((double) i));
            expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
            Double Fmax = expValue.eval();
            EntPool[j].MyFactorial.GaussianPDFStore[i] = 100.0 * Fmax;
        }
        //LCGDyDx Gaussian PDF
        EntPool[j].MyFactorial.EntropyPDFStore[0] = 0.0;
        EntPool[j].MyFactorial.EntropyPDFStore[1] = 31.64;
        EntPool[j].MyFactorial.EntropyPDFStore[2] = 11.64;
        EntPool[j].MyFactorial.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[1][3] = 11.66;
//        EntropyPDFStore[1][4] = 0.0;
//        EntropyPDFStore[1][4] = 11.68;
//        EntropyPDFStore[1][5] = 8.34;
//        EntropyPDFStore[1][6] = 11.72;
//        EntropyPDFStore[1][7] = 13.32;
//        EntropyPDFStore[1][8] = 0.0;
        j++;
        //LCGDyDx Gaussian PDF
        Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + " *exp(-(0.5 * (x - " + EntPool[j].MyXOR.MeanSigma[0] + ") / " + EntPool[j].MyXOR.MeanSigma[1] + " * (x - " + EntPool[j].MyXOR.MeanSigma[0] + ") / " + EntPool[j].MyXOR.MeanSigma[1] + "))))";
        Exprn = eatAll(Exprn, ' ');
        System.out.println("InitializePDFStore:LCGDyDx:Exprn=" + Exprn);
        for (int i = 1; i < NoOfSlots - 1; i++) {
            variables.put("x", ((double) i));
            expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
            Double Fmax = expValue.eval();
            EntPool[j].MyXOR.GaussianPDFStore[i] = 100.0 * Fmax;
        }
        //LCGDyDx Gaussian PDF
        EntPool[j].MyXOR.EntropyPDFStore[0] = 0.0;
        EntPool[j].MyXOR.EntropyPDFStore[1] = 31.64;
        EntPool[j].MyXOR.EntropyPDFStore[2] = 11.64;
        EntPool[j].MyXOR.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[1][3] = 11.66;
//        EntropyPDFStore[1][4] = 0.0;
//        EntropyPDFStore[1][4] = 11.68;
//        EntropyPDFStore[1][5] = 8.34;
//        EntropyPDFStore[1][6] = 11.72;
//        EntropyPDFStore[1][7] = 13.32;
//        EntropyPDFStore[1][8] = 0.0;
        // }
    }

    static void InitializePDFStore(long NoOfSlots) {
        MathsContxtLAv.Expression expValue;
        Map<String, Double> variables = new HashMap<>();
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            String Exprn = "(1.0 / " + (Math.sqrt(2.0 * Math.PI)) + "*exp(-(0.5 * (x - " + EntPool[Index].Series1.MeanSigma[0] + ") / " + EntPool[Index].Series1.MeanSigma[1] + " * (x - " + EntPool[Index].Series1.MeanSigma[0] + ") / " + EntPool[Index].Series1.MeanSigma[1] + "))))";
            Exprn = eatAll(Exprn, ' ');
            System.out.println("InitializePDFStore:LCG:Exprn=" + Exprn);
            //LCG Gaussian PDF
            for (int i = 1; i < NoOfSlots - 1; i++) {
                variables.put("x", ((double) i));
                expValue = parse(Exprn, variables, "x", "Silent", MathsContxtLAv.LogLevel.Off);
                Double Fmax = expValue.eval();
                EntPool[Index].Series1.GaussianPDFStore[i] = 100.0 * Fmax;
            }
            //LCG Gaussian PDF
            EntPool[Index].Series1.EntropyPDFStore[0] = 0.0;
            EntPool[Index].Series1.EntropyPDFStore[1] = 24.92;
            EntPool[Index].Series1.EntropyPDFStore[2] = 8.32;
            EntPool[Index].Series1.EntropyPDFStore[3] = 0.0;
//        EntropyPDFStore[0][3] = 18.4;
//        EntropyPDFStore[0][4] = 0.0;
//        EntropyPDFStore[0][4] = 13.36;
//        EntropyPDFStore[0][5] = 18.3;
//        EntropyPDFStore[0][6] = 6.68;
//        EntropyPDFStore[0][7] = 10.02;
//        EntropyPDFStore[0][8] = 0.0;
        }
    }

    static void ComputeMeanandSigma(int EntIndex, long NoOfSlots, long NoOfBits) {
        for (int j = EntIndex; j < EntPoolIndex - 1; j++) {
            // LCG Mean
            for (int i = 1; i < NoOfSlots; i++) {
                EntPool[j].MySimple.MeanSigma[0] += i * EntPool[j].MySimple.FreqStore[i];
            }
            EntPool[j].MySimple.MeanSigma[0] = EntPool[j].MySimple.MeanSigma[0] / NoOfBits;
            System.out.println("ComputeMeanandSigma::Xn_LCG:" + j + ":Mean=" + EntPool[j].MySimple.MeanSigma[0]);
            // LCG Sigma
            double TempSigma = 0.0;
            for (int i = 1; i < NoOfSlots; i++) {
                TempSigma += (i - EntPool[j].MySimple.MeanSigma[0]) * EntPool[j].MySimple.FreqStore[i] / NoOfBits * (i - EntPool[j].MySimple.MeanSigma[0]) * EntPool[j].MySimple.FreqStore[i] / NoOfBits;
            }
            EntPool[j].MySimple.MeanSigma[1] = Math.sqrt(TempSigma);
            System.out.println("ComputeMeanandSigma::Xn_LCG:" + j + ":Sigma=" + EntPool[j].MySimple.MeanSigma[1]);

            // LCG Sigma 2 Bit
            TempSigma = 0.0;
            for (int i = 1; i <= (NoOfSlots - 2); i++) {
                TempSigma += (i - 1) * EntPool[j].MySimple.FreqStore[i] / NoOfBits * (i - 1) * EntPool[j].MySimple.FreqStore[i] / NoOfBits;
            }
            EntPool[j].MySimple.MeanSigma[2] = Math.sqrt(TempSigma);
            j++;
            // LCGDyDx Computation
            // LCGDyDx Mean
            for (int i = 1; i < NoOfSlots; i++) {
                EntPool[j].MyFactorial.MeanSigma[0] += i * EntPool[j].MyFactorial.FreqStore[i];
            }
            EntPool[j].MyFactorial.MeanSigma[0] = EntPool[j].MyFactorial.MeanSigma[0] / NoOfBits;
            System.out.println("ComputeMeanandSigma::LCGDyDx:" + j + ":Mean=" + EntPool[j].MyFactorial.MeanSigma[0]);
            // LCGDyDx Sigma
            TempSigma = 0.0;
            for (int i = 1; i < NoOfSlots; i++) {
                TempSigma += (i - EntPool[j].MyFactorial.MeanSigma[0]) * EntPool[j].MyFactorial.FreqStore[i] / NoOfBits * (i - EntPool[j].MyFactorial.MeanSigma[0]) * EntPool[j].MyFactorial.FreqStore[i] / NoOfBits;
            }
            EntPool[j].MyFactorial.MeanSigma[1] = Math.sqrt(TempSigma);
            System.out.println("ComputeMeanandSigma::LCGDyDx:" + j + ":Sigma=" + EntPool[j].MyFactorial.MeanSigma[1]);

            // LCGDyDx Sigma 2 Bit
            TempSigma = 0.0;
            for (int i = 1; i <= (NoOfSlots - 2); i++) {
                TempSigma += (i - 1) * EntPool[j].MyFactorial.FreqStore[i] / NoOfBits * (i - 1) * EntPool[j].MyFactorial.FreqStore[i] / NoOfBits;
            }
            EntPool[j].MyFactorial.MeanSigma[2] = Math.sqrt(TempSigma);
        }
        int j = 4;
        // LCG Mean
        for (int i = 1; i < NoOfSlots; i++) {
            EntPool[j].MyXOR.MeanSigma[0] += i * EntPool[j].MyXOR.FreqStore[i];
        }
        EntPool[j].MyXOR.MeanSigma[0] = EntPool[j].MyXOR.MeanSigma[0] / NoOfBits;
        System.out.println("ComputeMeanandSigma::Xn_LCG:" + j + ":Mean=" + EntPool[j].MyXOR.MeanSigma[0]);
        // LCG Sigma
        double TempSigma = 0.0;
        for (int i = 1; i < NoOfSlots; i++) {
            TempSigma += (i - EntPool[j].MyXOR.MeanSigma[0]) * EntPool[j].MyXOR.FreqStore[i] / NoOfBits * (i - EntPool[j].MyXOR.MeanSigma[0]) * EntPool[j].MyXOR.FreqStore[i] / NoOfBits;
        }
        EntPool[j].MyXOR.MeanSigma[1] = Math.sqrt(TempSigma);
        System.out.println("ComputeMeanandSigma::Xn_LCG:" + j + ":Sigma=" + EntPool[j].MyXOR.MeanSigma[1]);

        // LCG Sigma 2 Bit
        TempSigma = 0.0;
        for (int i = 1; i <= (NoOfSlots - 2); i++) {
            TempSigma += (i - 1) * EntPool[j].MyXOR.FreqStore[i] / NoOfBits * (i - 1) * EntPool[j].MyXOR.FreqStore[i] / NoOfBits;
        }
        EntPool[j].MyXOR.MeanSigma[2] = Math.sqrt(TempSigma);
    }

    static void ComputeMeanandSigmaSeries(long NoOfSlots, long NoOfBits) {
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            // LCG Mean
            for (int i = 1; i < NoOfSlots; i++) {
                EntPool[Index].Series1.MeanSigma[0] += i * EntPool[Index].Series1.FreqStore[i];
            }
            EntPool[Index].Series1.MeanSigma[0] = EntPool[Index].Series1.MeanSigma[0] / NoOfBits;

            // LCG Sigma
            double TempSigma = 0.0;
            for (int i = 0; i < NoOfSlots; i++) {
                TempSigma += (i - EntPool[Index].Series1.MeanSigma[0]) * EntPool[Index].Series1.FreqStore[i] / NoOfBits * (i - EntPool[Index].Series1.MeanSigma[0]) * EntPool[Index].Series1.FreqStore[i] / NoOfBits;
            }
            EntPool[Index].Series1.MeanSigma[1] = Math.sqrt(TempSigma);
            // LCG Sigma 2 Bit
            TempSigma = 0.0;
            for (int i = 1; i <= (NoOfSlots - 2); i++) {
                TempSigma += (i - 1) * EntPool[Index].Series1.FreqStore[i] / NoOfBits * (i - 1) * EntPool[Index].Series1.FreqStore[i] / NoOfBits;
            }
            EntPool[Index].Series1.MeanSigma[2] = Math.sqrt(TempSigma);
        }
    }

    static void WriteToFile(int i, long Xn_LCG, long Xn_LCGDyDx, long Xn_LCG3, long Xn_LCGDyDx4, long NoOfBuffBitsMax, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        String FileName = Path + "RNDXn_LCG" + RunNumber;
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
            FileName = Path + "RNDXn_LCGDyDx" + RunNumber;
            DataLCG = "" + Xn_LCGDyDx + " ";
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

            //Rand Pool
            FileName = Path + "RNDXn_LCG3" + RunNumber;
            DataLCG = "" + Xn_LCG3 + " ";
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
            FileName = Path + "RNDXn_LCGDyDx4" + RunNumber;
            DataLCG = "" + Xn_LCGDyDx4 + " ";
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
        // }
    }

    static void WriteToFileSeries(int i, long Series0, long Series1, long Series2, long Series3, long Series4, long Series5, long NoOfBuffBitsMax, String RunNumber) {
        //if (i < NoOfBuffBitsMax) {
        FileWriter fp = null;
        BufferedWriter bw = null;
        PrintWriter fpout = null;
        String FileName = Path + "RNDSeries0" + RunNumber;
        String DataLCG = "" + Series0 + " ";
        try {
            //Series 0
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

            //Rand Pool
            //Series 1
            FileName = Path + "RNDSeries1" + RunNumber;
            DataLCG = "" + Series1 + " ";
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

            //Series 2
            FileName = Path + "RNDSeries2" + RunNumber;
            DataLCG = "" + Series2 + " ";
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

            //Rand Pool
            //Series 3
            FileName = Path + "RNDSeries3" + RunNumber;
            DataLCG = "" + Series3 + " ";
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

            //Series 4
            FileName = Path + "RNDSeries4" + RunNumber;
            DataLCG = "" + Series4 + " ";
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
            
            //Series 5
            FileName = Path + "RNDSeries5" + RunNumber;
            DataLCG = "" + Series5 + " ";
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
        // }
    }

    static void ReadFromFile(int EntIndex, long NoOfBuffBitsMax, long Xn_LCG, long Xn_LCGDyDx, long Xn_LCG3, long Xn_LCGDyDx4, long Xn_XOR, String RunNumber) {
        try {
            EntPool[EntIndex].MySimple.ReadFromFile(EntIndex, NoOfBuffBitsMax, Xn_LCG, RunNumber);
            EntIndex++;
            EntPool[EntIndex].MyFactorial.ReadFromFile(EntIndex, NoOfBuffBitsMax, Xn_LCGDyDx, RunNumber);
            EntIndex++;
            EntPool[EntIndex].MySimple.ReadFromFile(EntIndex, NoOfBuffBitsMax, Xn_LCG3, RunNumber);
            EntIndex++;
            EntPool[EntIndex].MyFactorial.ReadFromFile(EntIndex, NoOfBuffBitsMax, Xn_LCGDyDx4, RunNumber);
            EntIndex++;
            EntPool[EntIndex].MyXOR.ReadFromFile(EntIndex, NoOfBuffBitsMax, Xn_XOR, RunNumber);
            EntIndex++;

        } catch (Exception RND) {
            log.info("Exception: RND ReadFromFile");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
    }

    static void ReadFromFileSeries(int EntIndex, long NoOfBuffBitsMax, long Series0, long Series1, long Series2, long Series3, long Series4, long Series5, String RunNumber) {
        try {
            EntIndex = EntPoolIndex;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series0, RunNumber);
            EntIndex++;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series1, RunNumber);
            EntIndex++;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series2, RunNumber);
            EntIndex++;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series3, RunNumber);
            EntIndex++;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series4, RunNumber);
            EntIndex++;
            EntPool[EntIndex].Series1.ReadFromFileSeries(EntIndex, NoOfBuffBitsMax, Series5, RunNumber);
            EntIndex++;
        } catch (Exception RND) {
            log.info("Exception: RND ReadFromFile");
            System.out.println(RND.getStackTrace());
            System.out.println(RND.getMessage());
            RND.printStackTrace();
        }
    }

    static void DetectPattern(int EntIndex, long NoOfSlots, long Xn_LCG, long Xn_LCGDyDx, long Xn_LCG3, long Xn_LCGDyDx4, long Xn_XOR) {
        int Index = EntIndex;
        EntPool[Index].MySimple.DetectLCGPattern(Index, NoOfSlots, Xn_LCG);
        Index++;
        EntPool[Index].MyFactorial.DetectLCGDyDxPattern(Index, NoOfSlots, Xn_LCGDyDx);
        Index++;
        EntPool[Index].MySimple.DetectLCGPattern(Index, NoOfSlots, Xn_LCG3);
        Index++;
        EntPool[Index].MyFactorial.DetectLCGDyDxPattern(Index, NoOfSlots, Xn_LCGDyDx4);
        Index++;
        EntPool[Index].MyXOR.DetectLCGPattern(Index, NoOfSlots, Xn_XOR);
    }

    static void DetectSeriesPattern(int Index, long NoOfSlots, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4 ,long Xn_Series5) {

        Index = EntPoolIndex + Index;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series0);
        Index++;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series1);
        Index++;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series2);
        Index++;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series3);
        Index++;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series4);
        Index++;
        EntPool[Index].Series1.DetectSeriesPatternLow(Index - EntPoolIndex, NoOfSlots, Xn_Series5);
        Index++;
    }

    //Theory & Source:Wiki Next-bit Test And Yao's Test
    static void GuessNextIthBit(int EntIndex, int i, long Xn_Series0, long Xn_Series1, long Xn_Series2, long Xn_Series3, long Xn_Series4) {
        //NextIthLCG
        //NextIthLCGDyDx
        HighLevelProbablityGuess(i, Xn_Series0, Xn_Series1);
        LowLevelDeterministicGuess(EntIndex, i, Xn_Series0, Xn_Series1, Xn_Series2, Xn_Series3, Xn_Series4);
    }

    static void HighLevelProbablityGuess(int i, long IthXn_LCG, long IthXn_LCGDyDx) {

    }

    static void LowLevelDeterministicGuess(int EntIndex, int i, long IthXn_LCG, long IthXn_LCGDyDx, long IthXn_LCG3, long IthXn_LCGDyDx4, long Xn_XOR) {
        PatternRecognitionTest(EntIndex, i, IthXn_LCG, IthXn_LCGDyDx, IthXn_LCG3, IthXn_LCGDyDx4, Xn_XOR);//0.0%
        PatternRepetionTest(i, IthXn_LCG, IthXn_LCGDyDx, IthXn_LCG3, IthXn_LCGDyDx4, Xn_XOR);//0.0%
        PatternTest(i, IthXn_LCG, IthXn_LCGDyDx, IthXn_LCG3, IthXn_LCGDyDx4, Xn_XOR);//0.0%
        BufferTest(i, IthXn_LCG, IthXn_LCGDyDx, IthXn_LCG3, IthXn_LCGDyDx4, Xn_XOR);//49.8200% :91.4000%

    }

    static void PatternRecognitionTest(int EntIndex, int i, long IthXn_LCG, long IthXn_LCGDyDx, long IthXn_LCG3, long IthXn_LCGDyDx4, long Xn_XOR) {
        EntPool[0].MySimple.SearcNFormLCGPattern(0, IthXn_LCG);
        EntPool[1].MyFactorial.SearcNFormLCGDyDxPattern(1, IthXn_LCGDyDx);
        EntPool[2].MySimple.SearcNFormLCGPattern(2, IthXn_LCG);
        EntPool[3].MyFactorial.SearcNFormLCGDyDxPattern(3, IthXn_LCGDyDx);
        EntPool[4].MyXOR.SearcNFormXORPattern(4, Xn_XOR);
    }

    static void PatternRepetionTest(int i, long IthXn_LCG, long IthXn_LCGDyDx, long IthXn_LCG3, long IthXn_LCGDyDx4, long Xn_XOR) {
        int j = 0;
        EntPool[0].MySimple.PatternRepetionTest(i, IthXn_LCG);
        EntPool[1].MyFactorial.PatternRepetionTest(i, IthXn_LCGDyDx);
        EntPool[2].MySimple.PatternRepetionTest(i, IthXn_LCG3);
        EntPool[3].MyFactorial.PatternRepetionTest(i, IthXn_LCGDyDx4);
        EntPool[4].MyXOR.PatternRepetionTest(i, Xn_XOR);
    }

    static void PatternTest(int i, long IthXn_LCG, long IthXn_LCGDyDx, long IthXn_LCG3, long IthXn_LCGDyDx4, long Xn_XOR) {
        int j = 0;
        EntPool[0].MySimple.PatternTest(i, IthXn_LCG);
        EntPool[1].MyFactorial.PatternTest(i, IthXn_LCGDyDx);
        EntPool[2].MySimple.PatternTest(i, IthXn_LCG3);
        EntPool[3].MyFactorial.PatternTest(i, IthXn_LCGDyDx4);
        EntPool[4].MyXOR.PatternTest(i, Xn_XOR);
    }

    static void BufferTest(int i, long IthXn_LCG, long IthXn_LCGDyDx, long IthXn_LCG3, long IthXn_LCGDyDx4, long Xn_XOR) {
        int j = 0;
        EntPool[0].MySimple.BufferTest(i, IthXn_LCG);
        EntPool[1].MyFactorial.BufferTest(i, IthXn_LCGDyDx);
        EntPool[2].MySimple.BufferTest(i, IthXn_LCG3);
        EntPool[3].MyFactorial.BufferTest(i, IthXn_LCGDyDx4);
        EntPool[4].MyXOR.BufferTest(i, Xn_XOR);
    }

    static void PrintOutStats(int EntIndex, long NoOfSlots, long NoOfBits) {
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigma(EntIndex, NoOfSlots, NoOfBits);
        InitializePDFStore(EntIndex, NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntIndex; Index < EntPoolIndex; Index++) {
            EntPool[Index].MySimple.printDocValues(Index, (int) NoOfBits);
            Index++;
            EntPool[Index].MyFactorial.printDocValues(Index, (int) NoOfBits);
            Index++;
            EntPool[Index].MySimple.printDocValues(Index, (int) NoOfBits);
            Index++;
            EntPool[Index].MyFactorial.printDocValues(Index, (int) NoOfBits);
            Index++;
            EntPool[Index].MyXOR.printDocValues(Index, (int) NoOfBits);
            //System.out.print(System.lineSeparator());
            //System.out.print(System.lineSeparator());
        }
    }

    static void PrintOutStatsSeriesValues(long NoOfSlots, long NoOfBits) {
        System.out.println("***************StudyRandomValues*****************");
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigmaSeries(NoOfSlots, NoOfBits);
        InitializePDFStore(NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            switch (Index) {
                case 5:
                    System.out.println("Series:Xn_Series0:Xn_LCG+Xn_LCGDyDx: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 6:
                    System.out.println("Series:Xn_Series1:Xn_LCG-Xn_LCG3: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 7:
                    System.out.println("Series:Xn_Series2:Xn_LCGDyDx-Xn_LCGDyDx4: Values");
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 8:
                    System.out.println("Series:Xn_Series3:Xn_LCG > 64: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 9:
                    System.out.println("Series:Linear:y=mx+c:where m, x,'+' && c are random values:Xn_Series4: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 10:
                    System.out.println("Series:Xn_Series5:XOR_Circular: Values");
                    break;
            }
            EntPool[Index].Series1.printDocValues(Index, (int) NoOfBits);
        }

    }
static void PrintOutStatsSeriesVariables(long NoOfSlots, long NoOfBits) {
        System.out.println("***************StudyRandomVariables*****************");
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigmaSeries(NoOfSlots, NoOfBits);
        InitializePDFStore(NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            switch (Index) {
                case 5:
                    System.out.println("Series:Xn_Series0:Xn_LCG > 64:(Xn_LCG3) % (NoOfSlots): Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 6:
                    System.out.println("Series:Xn_Series1:Xn_LCG > 50:(5000) % (Xn_LCG): Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 7:
                    System.out.println("Series:Xn_Series2:Xn_LCG > 50:(i) % (Xn_LCG): Values");
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 8:
                    System.out.println("Series:Xn_Series3:NOT USED: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 9:
                    System.out.println("Series:Linear:y=mx+c:where m, x,'+' && c are random values:Xn_Series4: Values");
                    System.out.println("Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println("Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 10:
                    System.out.println("Series:Xn_Series5:XOR_Circular: Values");
                    break;
            }
            EntPool[Index].Series1.printDocValues(Index, (int) NoOfBits);
        }

    }
    static void PrintOutStatsSeriesLogic(long NoOfSlots, long NoOfBits) {
        System.out.println("***************StudyLogicValues*****************");
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigmaSeries(NoOfSlots, NoOfBits);
        InitializePDFStore(NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            switch (Index) {
                case 5:
                    System.out.println("Series:Xn_Series0:Xn_LCG > Xn_LCGDyDx: Values");
                    System.out.println(":Xn_Series0:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series0:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 6:
                    System.out.println("Series:Xn_Series1:Xn_LCG < Xn_LCGDyDx: Values");
                    System.out.println(":Xn_Series1:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series1:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 7:
                    System.out.println("Series:Xn_Series2:Xn_LCG == Xn_LCGDyDx: Values");
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 8:
                    System.out.println("Series:Xn_Series3:(Xn_LCG > 50): Values");
                    System.out.println(":Xn_Series3:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series3:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 9:
                    System.out.println("Series:Xn_Series4:Linear:y=mx+c:where m, x,'+' && c are random values:Xn_Series4: Values");
                    System.out.println(":Xn_Series4:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 10:
                    System.out.println("Series:Xn_Series5:XOR_Circular: Values");
                    break;
            }
            EntPool[Index].Series1.printDocValues(Index, (int) NoOfBits);
        }
    }

    static void PrintOutStatsSeriesImplies(long NoOfSlots, long NoOfBits) {
        System.out.println("***************StudyXORLogicValues*****************");
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigmaSeries(NoOfSlots, NoOfBits);
        InitializePDFStore(NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            switch (Index) {
                case 5:
                    System.out.println("Series:Xn_Series0:p=> q= ((p^q)&&(p' v (OR) q'): Values");
                    System.out.println(":Xn_Series0:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series0:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 6:
                    System.out.println("Series:Xn_Series1:NOT ((p=> q)): Values");
                    System.out.println(":Xn_Series1:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series1:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 7:
                    System.out.println("Series:Xn_Series2:Else: Values");
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 8:
                    System.out.println("Series:Xn_Series3:Not Used: Values");
                    System.out.println(":Xn_Series3:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series3:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 9:
                    System.out.println("Series:Xn_Series4:Not Used:Linear:y=mx+c:where m, x,'+' && c are random values:Xn_Series4: Values");
                    System.out.println(":Xn_Series4:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 10:
                    System.out.println("Series:Xn_Series5:XOR_Circular: Values");
                    System.out.println(":Xn_Series5:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series5:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
            }
            EntPool[Index].Series1.printDocValues(Index, (int) NoOfBits);
        }
    }

    static void PrintOutStatsSeriesLinEqn(long NoOfSlots, long NoOfBits) {
        System.out.println("***************StudyRandomLinearEquations*****************");
        System.out.print(System.lineSeparator());
        {
            LocalDateTime current = LocalDateTime.now();
            System.out.println("Run Loop Completed Time:" + current + " ");
        }
        ComputeMeanandSigmaSeries(NoOfSlots, NoOfBits);
        InitializePDFStore(NoOfSlots);
        System.out.print(System.lineSeparator());
        System.out.print(System.lineSeparator());
        for (int Index = EntPoolIndex; Index < (EntPoolIndex + PoolIndexSeries); Index++) {
            switch (Index) {
                case 5:
                    System.out.println("Series:Xn_Series0:i*Xn:  where i is linearly increasing Index && Xn is random:Values");
                    System.out.println(":Xn_Series0:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series0:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 6:
                    System.out.println("Series:Xn_Series1:i*Xn+c: where i is linearly increasing Index && c is a constant value && Xn is random:Values");
                    System.out.println(":Xn_Series1:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series1:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[1]);
                    break;
                case 7:
                    System.out.println("Series:Xn_Series2:m*Xn:where m, Xn are random values: Values");
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series2:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 8:
                    System.out.println("Series:Xn_Series3:m*Xn+c: where m && c are constant values && Xn is random :Values");
                    System.out.println(":Xn_Series3:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series3:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
                case 9:
                    System.out.println("Series:Xn_Series4:m*Xn+c:Linear:y=mXn+c:where m, Xn,'+' && c are random values:Xn_Series4: Values");
                    System.out.println(":Xn_Series4:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCG3:" + EntPool[2].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[2].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series4:Mean:Xn_LCGDyDx4:" + EntPool[3].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[3].MyFactorial.MeanSigma[1]);
                    break;
                case 10:
                    System.out.println("Series:Xn_Series5:XOR_Circular: where m && c are constant values && Xn is random :Values");
                    System.out.println(":Xn_Series5:Mean:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[0] + " Sigma:Xn_LCG:" + EntPool[0].MySimple.MeanSigma[1]);
                    System.out.println(":Xn_Series5:Mean:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[0] + " Sigma:Xn_LCGDyDx:" + EntPool[1].MyFactorial.MeanSigma[1]);
                    break;
            }
            EntPool[Index].Series1.printDocValues(Index, (int) NoOfBits);
        }
    }
}
