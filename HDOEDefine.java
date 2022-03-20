/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOETest;

import static RandomNumber.UserResearchStudy.Path;
import static RandomNumber.UserResearchStudy.Xn_XOR;
import static RandomNumber.UserResearchStudy.Xn_LCG;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx;
import static RandomNumber.UserResearchStudy.Xn_LCG3;
import static RandomNumber.UserResearchStudy.Xn_LCGDyDx4;
import static RandomNumber.UserResearchStudy.Modulus;
import static RandomNumber.UserResearchStudy.NoOfBits;
import static RandomNumber.UserResearchStudy.MaxExponent;
import static RandomNumber.UserResearchStudy.ModulusEncrypt;
import static RandomNumber.UserResearchStudy.StudyType;
import static RandomNumber.UserResearchStudy.a;
import static RandomNumber.UserResearchStudy.c;
import static RandomNumber.UserResearchStudy.b;
import static RandomNumber.UserResearchStudy.d;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class HDOEDefine {
 public static Logger log=Logger.getLogger(HDOEDefine.class.getName());
 public static void Myfscanf(FileInputStream fp)
{
    String s = new String();
    String[] sTemp = null;
    int param = 0;
    int exp = 0;
    try{
//DataInputStream din=new DataInputStream(fp);

BufferedReader din=new BufferedReader(new InputStreamReader(fp));
Path=ProcessString(din.readLine());

Xn_XOR=Integer.parseInt(ProcessString(din.readLine()));	
Xn_LCG =Integer.parseInt(ProcessString(din.readLine()));
Xn_LCGDyDx =Integer.parseInt(ProcessString(din.readLine()));
Xn_LCG3=Integer.parseInt(ProcessString(din.readLine()));	
Xn_LCGDyDx4=Integer.parseInt(ProcessString(din.readLine()));

Modulus=Integer.parseInt(ProcessString(din.readLine()));
NoOfBits=Integer.parseInt(ProcessString(din.readLine()));
MaxExponent=Integer.parseInt(ProcessString(din.readLine()));
ModulusEncrypt=Integer.parseInt(ProcessString(din.readLine()));


// High Level Variables for Signal Noise Nature
a=Integer.parseInt(ProcessString(din.readLine()));
c=Integer.parseInt(ProcessString(din.readLine()));
b=Integer.parseInt(ProcessString(din.readLine()));
d=Integer.parseInt(ProcessString(din.readLine()));
StudyType=ProcessString(din.readLine());
fp.close();
//log.error("GlblVars.Precision=" + GlblVars.Precision);
} catch(Exception HyperDOEse){ 
                            log.info("Exception: define Myfscanf");
                            System.out.println(HyperDOEse.getStackTrace());
                            System.out.println(HyperDOEse.getMessage());
                            HyperDOEse.printStackTrace();}
}

public static void Myprintf()
{
    try{
System.out.print(System.lineSeparator());
System.out.printf("DOENew General Parameters Begin");
System.out.print(System.lineSeparator());
System.out.printf("Path = %s \n" ,Path);
System.out.printf("Xn_XOR = %d \n" ,Xn_XOR);
System.out.printf("Xn_LCG = %d \n" ,Xn_LCG);
System.out.printf("Xn_LCGDyDx = %d \n" ,Xn_LCGDyDx);		
System.out.printf("Xn_LCG3 = %d \n" ,Xn_LCG3);
System.out.printf("Xn_LCGDyDx4= %d \n",Xn_LCGDyDx4);
System.out.printf("Modulus = %d \n",Modulus);
System.out.printf("NoOfBits = %d \n" ,NoOfBits);
System.out.printf("MaxExponent = %s \n" ,MaxExponent);
System.out.printf("ModulusEncrypt = %d \n",ModulusEncrypt);

System.out.printf("a = %d \n",a);
System.out.printf("c = %d \n",c);
System.out.printf("b = %d \n",b);
System.out.printf("d = %d \n",d);
System.out.printf("StudyType = %s \n",StudyType);
System.out.print(System.lineSeparator());
    } catch(Exception HyperDOEse){ 
                            log.info("Exception: define Myfprintf");
                            System.out.println(HyperDOEse.getStackTrace());
                            System.out.println(HyperDOEse.getMessage());
                            HyperDOEse.printStackTrace();}
}
public static String ProcessString(String input){
    String[] TempDataStr=new String[5];
    String[] TempValuesStr=new String[5];
    String[] Temp=new String[5];
    String[] Temp1=new String[5];
    if ((input.contains("Values("))||(input.contains("="))) {
      if(input.contains("Values(")){
        Temp=input.split("[\\(]");  
        TempValuesStr=Temp[1].split(",");
        Temp1=TempValuesStr[TempValuesStr.length-1].split("[\\)]");
        TempValuesStr[TempValuesStr.length-1]=Temp1[0];
        TempDataStr=input.split("=");
        for(int i=0; i < TempValuesStr.length;i++){
            //if Value is in the permissible range
           if((TempValuesStr[i]!= null)
                   &&(!TempValuesStr[i].equalsIgnoreCase(""))
               &&(TempValuesStr[i].equalsIgnoreCase(TempDataStr[1].trim()))
                   )
            return TempDataStr[1].trim();
        }
        //If Value has a spelling mistake or is outside the permissible range
        //accept new string anyways
        //return null:False
        return null;
      }  
      else {
        TempDataStr=input.split("=");
        // Take 1 Equal to(=) as the one seperating Value of Variable if Length==2
        if((TempDataStr.length)==2){
        //log.error("Read String=" + TempDataStr[1].trim());
        return TempDataStr[1].trim();
        }
        // Take last Equal to(=) as the one seperating Value of Variable
        else {
            return TempDataStr[TempDataStr.length-1].trim();
        }
        }
    }
    else if (!(input.trim().isEmpty())) return input.trim();
    else return null;
  }
public static void Myscanf()
{
    try{
//DataInputStream din=new DataInputStream(fp);
BufferedReader din=new BufferedReader(new InputStreamReader(System.in));
System.out.print(System.lineSeparator());
System.out.printf("DOENew General Parameters Begin");
System.out.print(System.lineSeparator());

String Data;
Data=MyPrompt( din, "Path", Path );
Path=Data;

Data=MyPrompt( din, "Xn_XOR", ("" +Xn_XOR+"") );
Xn_XOR=Integer.parseInt(Data);

Data=MyPrompt( din, "Xn_LCG", (""+Xn_LCG+"") );
Xn_LCG=Integer.parseInt(Data);

Data=MyPrompt( din, "Xn_LCGDyDx ", ("" + Xn_LCGDyDx  + "") );
Xn_LCGDyDx =Integer.parseInt(Data);

Data=MyPrompt( din, "Xn_LCG3", (""+Xn_LCG3+"") );
Xn_LCG3=Integer.parseInt(Data);	

Data = MyPrompt(din, "Xn_LCGDyDx4", ("" + Xn_LCGDyDx4 + ""));
Xn_LCGDyDx4=Integer.parseInt(Data);

Data = MyPrompt(din, "Modulus", ("" + Modulus + ""));
Modulus=Integer.parseInt(Data);

Data=MyPrompt( din, "NoOfBits", ("" + NoOfBits + "") );
NoOfBits=Integer.parseInt(Data);

Data=MyPrompt( din, "MaxExponent", ("" + MaxExponent  + "") );
MaxExponent=Integer.parseInt(Data);

Data = MyPrompt(din, "ModulusEncrypt", ("" + ModulusEncrypt + ""));
ModulusEncrypt=Integer.parseInt(Data);

Data = MyPrompt(din, "a", ("" + a + ""));
a=Integer.parseInt(Data);

Data = MyPrompt(din, "c", ("" + c + ""));
c=Integer.parseInt(Data);

Data = MyPrompt(din, "b", ("" + b + ""));
b=Integer.parseInt(Data);

Data = MyPrompt(din, "d", ("" + d + ""));
d=Integer.parseInt(Data);

Data = MyPrompt(din, "StudyType", ("" + StudyType + ""));
StudyType=Data;

System.out.print(System.lineSeparator());

System.out.print(System.lineSeparator());



} catch(Exception HyperDOEse){ 
                            log.info("Exception: define Myfscanf");
                            System.out.println(HyperDOEse.getStackTrace());
                            System.out.println(HyperDOEse.getMessage());
                            HyperDOEse.printStackTrace();}
}
public static void Myfprintf(String Path)
{
    try{
PrintWriter fpout=null;
if ((fpout = new PrintWriter(Path+"MicroGAinput" )) == null)
	{
		System.out.printf("can't open %s\n", "MicroGAinput");
		System.out.printf("Setup aborted.\n");
		System.exit(1);
	}
fpout.printf("Path = %s \n" ,Path);
fpout.printf("Xn_XOR = %d \n" ,Xn_XOR);
fpout.printf("Xn_LCG = %d \n" ,Xn_LCG);
fpout.printf("Xn_LCGDyDx = %d \n" ,Xn_LCGDyDx);
fpout.printf("Xn_LCG3 = %d \n" ,Xn_LCG3);		
fpout.printf("Xn_LCGDyDx4 = %d \n",Xn_LCGDyDx4);
fpout.printf("Modulus = %d \n",Modulus);
fpout.printf("NoOfBits = %d \n" ,NoOfBits);
fpout.printf("MaxExponent = %d \n" ,MaxExponent);
fpout.printf("ModulusEncrypt = %d \n",ModulusEncrypt);
fpout.printf("a = %d \n",a);
fpout.printf("c = %d \n",c);
fpout.printf("b = %d \n",b);
fpout.printf("d = %d \n",d);
fpout.printf("StudyType = %d \n",StudyType);
fpout.close();
} catch(Exception HyperDOEse){ 
                            log.info("Exception: define Myfprintf");
                            System.out.println(HyperDOEse.getStackTrace());
                            System.out.println(HyperDOEse.getMessage());
                            HyperDOEse.printStackTrace();
}
 }

public static String MyPrompt(BufferedReader din, String Param, String magic ){
    String TempStr="";
    try{
    System.out.print(Param + " [" + magic + "] = " );
    TempStr=ProcessString(din.readLine());
    if (TempStr== null ) return magic.trim();
    else return TempStr.trim();
    } catch(Exception HyperDOEse){ 
                            log.info("Exception: define MyPrompt");
                            System.out.println(HyperDOEse.getStackTrace());
                            System.out.println(HyperDOEse.getMessage());
                            HyperDOEse.printStackTrace();
}
    return null;
}
}
