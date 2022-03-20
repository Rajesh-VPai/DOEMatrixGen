/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import Calculus.ConsoleColors;
import static DOEMatrixGen.MatrixGenerator.LevelCntrlArrayGet;
import static DOEMatrixGen.MatrixGenerator.LevelMaxIndex;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class DOEColumnInputLevel {

    public static Logger log = Logger.getLogger(DOEColumnInputLevel.class.getName());
    public static String ReadSection = "";
    public static String ReadCommSpec = "";
    public static String ReadStartEnd = "";
    public static String ProgramSection = "";
    public static String ProgramStartEnd = "";
    public static String Read_Col_Input;
    public static String Program_Col_Input;

    public static boolean ReadValid = false;
    public static boolean FlagReadCommon = false;

    public static void ProgramDefine(String ProgSec) {
        ProgramSection = ProgSec;
    }

    public static void Myfscanf(FileInputStream fp, int planeNum, String InputGenere, String InputType, int ObjectColNum) {
        String s = new String();
        String[] sTemp = null;
        int param = 0;
        int exp = 0;
        try {
            BufferedReader din = new BufferedReader(new InputStreamReader(fp));
            if (ProgramSection.equalsIgnoreCase("Signal")) {
                Myfscanf_Signal(fp, planeNum, InputGenere, InputType, ObjectColNum);
            } 

        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel Myfscanf");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED + "Exception: DOEColumnInputLevel Myfscanf:File/Folder Not Found" + ConsoleColors.RESET);
        }
    }

    public static void Myprintf(int planeNum, String InputType, int ObjectColNum) {
        try {
            if (ProgramSection.equalsIgnoreCase("Signal")) {
                Myprintf_Signal(planeNum, InputType, ObjectColNum);
            } 
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel Myfprintf");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED + "Exception: DOEColumnInputLevel Myfprintf:File/Folder Not Found" + ConsoleColors.RESET);
        }
    }

    public static String ProcessString(BufferedReader din, String input) {
        String[] TempDataStr = new String[4];
        try {
            //Remove a Comment
            while (input.contains("#")) //Read a next line & check if it is valid
            {
                input = din.readLine();
            }
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel ProcessString");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED + "Exception: DOEColumnInputLevel ProcessString:ReadLine" + ConsoleColors.RESET);
        }
        //Valid Lines
        if (input.contains("=")) {
            TempDataStr = input.split("=");
            //log.error("Read String=" + TempDataStr[1].trim());
            return TempDataStr[1].trim();
        } else if (!(input.trim().isEmpty())) {
            return input.trim();
        } else {
            return null;
        }
    }

    public String MyPrompt(BufferedReader din, String Param, String magic) {
        String TempStr = "";
        try {
            System.out.print(Param + " [" + magic + "] = ");
            TempStr = ProcessString(din, din.readLine());
            if (TempStr == null) {
                return magic.trim();
            } else {
                return TempStr.trim();
            }
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel MyPrompt");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED +"Exception: DOEColumnInputLevel MyPrompt:ReadLine" + ConsoleColors.RESET);
        }
        return null;
    }

    public static boolean ProcessSection(FileInputStream fp, BufferedReader din, String input, String InputGenere) {
        String[] TempDataStr = new String[4];

        try {
            //log.fatal("DOEColumnDefine:ProcessSection:input=" + input);
            //Remove a Comment
            ReadValid = false;
            if ((input != null) && (((ProgramSection.equalsIgnoreCase("Signal"))
                    && (input.startsWith("#Header")))
                    || (((ProgramSection.equalsIgnoreCase("Signal"))
                    || (ProgramSection.equalsIgnoreCase("Nature"))
                    || (ProgramSection.equalsIgnoreCase("Noise")))
                    && (input.startsWith("#"))
                    && ((input.contains("Common_")) || (input.contains("Specific_")))
                    && ((input.contains("_Signal_")) || (input.contains("_Nature_")) || (input.contains("_Noise_")))
                    && ((input.contains("_Start_")) || (input.contains("_End_")))
                    && ((input.endsWith("_" + Read_Col_Input + "#") || (InputGenere.equalsIgnoreCase("Common"))))))) {
                //Valid Section
                //Header Section Start OR End
                if (input.startsWith("#Header")) {
                    ReadSection = "Header";
                    if (input.contains("_Start#")) {
                        ReadStartEnd = "Start";
                    } else if (input.contains("_End#")) {
                        ReadStartEnd = "End";
                    }
                    ReadValid = true;
                }
                //Common OR Specific Section
                if (input.contains("Common_")) {
                    ReadCommSpec = "Common";
                } else if (input.contains("Specific_")) {
                    ReadCommSpec = "Specific";
                }
                //Signal Nature Noise Section
                if (input.contains("_Signal_")) {
                    ReadSection = "Signal";
                } else if (input.contains("_Nature_")) {
                    ReadSection = "Nature";
                } else if (input.contains("_Noise_")) {
                    ReadSection = "Noise";
                }
                //Start OR End
                if ((ReadCommSpec.equalsIgnoreCase("Specific")) && (input.contains("_Start_"))) {
                    ReadStartEnd = "Start";
                    //if (input.endsWith(Read_Col_Input+"#")) {
                    ReadValid = true;
                    //} 
                } else if ((ReadCommSpec.equalsIgnoreCase("Common")) && (input.contains("_Start_"))) {
                    ReadStartEnd = "Start";
                    //if (input.endsWith(Read_Col_Input+"#")) {
                    ReadValid = true;
                    //} 
                } else if (input.contains("_End_")) {
                    ReadStartEnd = "End";
                }

            }
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel ProcessSection");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED +"Exception: DOEColumnInputLevel ProcessSection:" + ConsoleColors.RESET);
        }
        return ReadValid;
    }

    public static void Myfscanf_Signal(FileInputStream fp, int planeNum, String InputGenere, String InputType, int ObjectColNum) {
        String s = new String();
        String[] sTemp = null;
        int param = 0;
        int exp = 0;
        String Name="";

        try {
            log.info("DOEColumnDefine:Myfscanf_Signal:Entered");
            Read_Col_Input = ObjectColNum + "_" + InputType;
            BufferedReader din = new BufferedReader(new InputStreamReader(fp));
            if (ProcessSection(fp, din, din.readLine(), InputGenere)) {

            }
            if ((ReadSection.equalsIgnoreCase("Header")) && (ReadStartEnd.equalsIgnoreCase("Start"))) {
                if (ProcessSection(fp, din, din.readLine(), InputGenere)) {

                }
            }
            String input = "";
            FlagReadCommon = false;
            log.fatal("DOEColumnDefine:Myfscanf_Signal:FlagReadCommon=" + FlagReadCommon + " InputGenere=" + InputGenere + " ReadCommSpec=" + ReadCommSpec);
            if ((FlagReadCommon == false)
                    && (!InputGenere.equalsIgnoreCase(ReadCommSpec))
                    && (InputGenere.equalsIgnoreCase("Specific"))) {
                log.info("DOEColumnDefine:Myfscanf_Signal:Reading Common Section:Entered");
                //Read Line
                while (true) {
                    input = din.readLine();
                    ProcessSection(fp, din, input, InputGenere);
                    if ((InputGenere.equalsIgnoreCase(ReadCommSpec))
                            && (input.endsWith("_" + Read_Col_Input + "#"))) {
                        FlagReadCommon = true;
                        break;
                    } else if (input == null) {
                        FlagReadCommon = false;
                        break;
                    }

                }
                log.info("DOEColumnDefine:Myfscanf_Signal:Reading Common Section:Exited input=" + input);
            }
            log.info("DOEColumnDefine:Myfscanf_Signal:Entered:Locating Read_Col_Input=" + Read_Col_Input + " ObjectColNum=" + ObjectColNum + " InputType=" + InputType + " input=" + input);
            log.info("DOEColumnDefine:Myfscanf_Signal:Entered:Matching Columns & Types:ReadSection=" + ReadSection + " ReadCommSpec=" + ReadCommSpec + " InputGenere=" + InputGenere + " ReadStartEnd=" + ReadStartEnd);
             if (InputType.equalsIgnoreCase("NormalInput")) {
                MatrixGenerator.LevelValueInit[ObjectColNum] = -1;
            }

            if ((ReadSection.equalsIgnoreCase("Signal"))
                    && ((ReadCommSpec.equalsIgnoreCase(InputGenere)) || (InputGenere.equalsIgnoreCase("Common")))
                    && (ReadStartEnd.equalsIgnoreCase("Start"))) {
                log.info("DOEColumnDefine:Myfscanf_Signal:Entered:Matching Columns & Types");
                if (InputType.equalsIgnoreCase("NormalInput")) {
                    if ((FlagReadCommon == true)&&(ReadCommSpec.equalsIgnoreCase("Common"))&&((InputGenere.equalsIgnoreCase("Common")))) {
                        Name=/*ReadSection+ "_" + InputType + "_" + */"Col_" + ObjectColNum + "_";
                    }
                    else if ((FlagReadCommon == true)&&(ReadCommSpec.equalsIgnoreCase("Specific")) && (InputGenere.equalsIgnoreCase("Specific")) ) {
                        Name=ProcessString(din, din.readLine());
                    }
                    else 
                        Name=/*ReadSection+ "_" + InputType + "_" +*/"Col_" + ObjectColNum + "_";
                    MatrixGenerator.NameCol[ObjectColNum] = Name;
                    Integer MyColNum = Integer.parseInt(ProcessString(din, din.readLine()));
                    Integer MyColType = Integer.parseInt(ProcessString(din, din.readLine()));
                    Integer MyColLevels = Integer.parseInt(ProcessString(din, din.readLine()));
                    if (MyColLevels > MatrixGenerator.LEVELS) {
                        System.out.println(ConsoleColors.RED+"LEVELS IN FILE (" + MyColLevels + ")> LEVELS IN " + MatrixGenerator.ConfigFileName + ". Hence Reducing LEVELS to " + MatrixGenerator.LEVELS + ConsoleColors.RESET);
                        MyColLevels=MatrixGenerator.LEVELS;
                    }
                    MatrixGenerator.LevelCntrlArrayPut(planeNum, ObjectColNum, MatrixGenerator.LevelMinIndex,1);
                    MatrixGenerator.LevelCntrlArrayPut(planeNum, ObjectColNum, MatrixGenerator.LevelMaxIndex,MyColLevels);
                    int k = (int) MatrixGenerator.LevelCntrlArrayGet(planeNum, ObjectColNum, MatrixGenerator.LevelMaxIndex);
                    if (((ObjectColNum == MyColNum) && (MyColType == 5)) || (InputGenere.equalsIgnoreCase("Common"))) {
                        if (MyColLevels >= k) {
                            for (int i = 0; (i <= MyColLevels) && (i <= k); i++) {
                                String Temp=ProcessString(din, din.readLine());
                                if((Pattern.matches("[\\-\\+]*[0-9\\.]+", Temp))&&(!Pattern.matches("[\\-\\+]*[a-zA-Z0-9]+", Temp))){
                                    MatrixGenerator.PutLevelValuePair(ObjectColNum, i, Double.parseDouble(Temp));
                                }
                            }
                        } else {
                            for (int i = MyColLevels; i <= k; i++) {
                                MatrixGenerator.PutLevelValuePair(ObjectColNum, i, Double.NaN);
                            }
                        }
                        MatrixGenerator.LevelValueInit[ObjectColNum] = 1;
                    } else {
                        MatrixGenerator.LevelValueInit[ObjectColNum] = -1;
                    }
                } 
                FlagReadCommon = false;
            }
            //fp.close();
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel Myfscanf_Signal");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED +"Exception: DOEColumnInputLevel Myfscanf_Signal:" + ConsoleColors.RESET);
        }
        log.info("DOEColumnDefine:Myfscanf_Signal:Exited");
    }

    
    public static void Myprintf_Signal(int planeNum, String InputType, int ObjectColNum) {
        try {
             if (InputType.equalsIgnoreCase("NormalInput")) {
                System.out.print(System.lineSeparator());
                System.out.printf("DOENew Level Values Begin");
                System.out.print(System.lineSeparator());
                System.out.printf("Name=" + MatrixGenerator.NameCol[ObjectColNum]);
                System.out.print(System.lineSeparator());
                for (int i = 1; i <= MatrixGenerator.LevelCntrlArrayGet(planeNum, ObjectColNum, MatrixGenerator.LevelMaxIndex); i++) {
                    System.out.println("planeNum=" + planeNum + " Signal:" + " " + InputType + " " + "Level=" + (i ) + "=" + MatrixGenerator.getLevelValuePair(ObjectColNum, i));
                }

                System.out.print(System.lineSeparator());
            } 
        } catch (Exception HyperDOEse) {
            log.info("Exception: DOEColumnInputLevel Myprintf_Signal");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED +"Exception: DOEColumnInputLevel Myprintf_Signal:" +ConsoleColors.RESET);
        }
    }

    
    public static void IO(int planeNum, String InputType, String InputGenere, int ObjectColNum) {
        String Path = MatrixGenerator.Path;
        String Path2 = "LevelValues\\";
        String[] FileInputSuffix = new String[2];
        FileInputSuffix = InputType.split("Input");
        try {
            FileInputStream fpinput = null;
            String msg = "";
            if ((fpinput = new FileInputStream(Path + Path2 + MatrixGenerator.LevelFileName + ProgramSection + FileInputSuffix[0])) == null) {
                msg = "Input: can't open " + Path + Path2 + MatrixGenerator.LevelFileName + ProgramSection + FileInputSuffix[0];
                System.out.println(msg);
                log.error(msg);
            }
            log.fatal("GenerateArray:" + ProgramSection + ":Opened File Names: Infile=" + Path + MatrixGenerator.LevelFileName + ProgramSection + FileInputSuffix[0]);

            Myfscanf(fpinput, planeNum, InputGenere, InputType, ObjectColNum);
            fpinput.close();

            log.fatal("Read File Names: Infile=" + Path + Path2 + MatrixGenerator.LevelFileName + ProgramSection + FileInputSuffix[0]);
        } catch (Exception HyperDOEse) {
            log.error("Exception: DOEColumnInputLevel:IO");
            System.out.println(HyperDOEse.getStackTrace());
            System.out.println(HyperDOEse.getMessage());
            if (MatrixGenerator.PrintStackTraceFlag==true) 
                HyperDOEse.printStackTrace();
            else 
                System.out.println(ConsoleColors.RED +"Exception: DOEColumnInputLevel IO:File/Folder Not Found" + ConsoleColors.RESET);
        }
    }
}
