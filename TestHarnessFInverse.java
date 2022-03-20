/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestHarness;

import Calculus.ConsoleColors;
import Calculus.UsageCalculus;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class TestHarnessFInverse{
public static Logger log = Logger.getLogger(TestHarnessFInverse.class.getName());
    
    //Sanity Test Cases
    public static void SanityTestCases(String AlgoName,int j){
            switch(j){
            case 100:
                TestCase100();
                break;
            case 101:
                TestCase101();
                break;
            case 102:
                TestCase102();
                break;
            case 103:
                TestCase103();
                break;
            case 104:
                TestCase104();
                break;
            case 105:
                TestCase105();
                break;
            case 106:
                TestCase106();
                break;
            case 107:
                TestCase107();
                break;
            case 108:
                TestCase108();
                break;
            case 109:
                TestCase109();
                break;
            case 110:
                TestCase110();
                break;
            case 111:
                TestCase111();
                break;
            case 112:
                TestCase112();
                break;
            case 113:
                TestCase113();
                break;
            case 114:
                TestCase114();
                break;
            case 115:
                TestCase115();
                break;
            default:
                System.out.println(ConsoleColors.RED +"SanityTestCases TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
    }
}
    //Sanity Test Cases
    public static void UnitTestCases(String AlgoName,int j){
            switch(j){
            case 200:
                TestCase200();
                break;
            case 201:
                TestCase201();
                break;
            case 202:
                TestCase202();
                break;
            case 203:
                TestCase203();
                break;
            case 204:
                TestCase204();
                break;
            case 205:
                TestCase205();
                break;
            case 206:
                TestCase206();
                break;
            case 207:
                TestCase207();
                break;
            case 208:
                TestCase208();
                break;
            case 209:
                TestCase209();
                break;
            case 210:
                TestCase210();
                break;
            case 211:
                TestCase211();
                break;
            case 212:
                TestCase212();
                break;
            case 213:
                TestCase213();
                break;
            case 214:
                TestCase214();
                break;
            case 215:
                TestCase215();
                break;
            case 216:
                TestCase216();
                break;
            case 217:
                TestCase217();
                break;
            case 218:
                TestCase218();
                break;
            case 219:
                TestCase219();
                break;
            case 220:
                TestCase220();
                break;
            case 221:
                TestCase221();
                break;
            case 222:
                TestCase222();
                break;
            case 223:
                TestCase223();
                break;
            case 224:
                TestCase224();
                break;
            case 225:
                TestCase225();
                break;
            default:
                System.out.println(ConsoleColors.RED +"UnitTestCases TestCase:j= " + j  + ": Gap" + ConsoleColors.RESET);
                break;
    }
}
    /* **************************************** */
    //Structured Testing
    /* **************************************** */
    /* ********************************************** */
    /* ***************Sanity Tests******************* */
    /* ********************************************** */
    /* ************** == , != , && , || Test Cases  * */
        public static void TestCase100() {
        //Test Case 100
        UsageCalculus.TestCase = 100;
        UsageCalculus.EqnUnderTest = "x";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="y*1.0";
}
        public static void TestCase101() {
        //Test Case 101
        UsageCalculus.TestCase = 101;
        UsageCalculus.EqnUnderTest = "x+25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="y*1.0-25.0";
}
        public static void TestCase102() {
        //Test Case 102
        UsageCalculus.TestCase = 102;
        UsageCalculus.EqnUnderTest = "5*x+25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="(x-25)/5";
}
        public static void TestCase103() {
        //Test Case 103
        UsageCalculus.TestCase = 103;
        UsageCalculus.EqnUnderTest = "x-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="y*1.0+25.0";
}
        public static void TestCase104() {
        //Test Case 104
        UsageCalculus.TestCase = 104;
        UsageCalculus.EqnUnderTest = "25+x";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="-25.0+y*1.0";
}
        public static void TestCase105() {
        //Test Case 105
        UsageCalculus.TestCase = 105;
        UsageCalculus.EqnUnderTest = "1/x";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="1.0/y";
}       
        public static void TestCase106() {
        //Test Case 106
        UsageCalculus.TestCase = 106;
        UsageCalculus.EqnUnderTest = "25-x";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="-25.0-y*1.0";
}
        public static void TestCase107() {
        //Test Case 107
        UsageCalculus.TestCase = 107;
        UsageCalculus.EqnUnderTest = "x*0.0-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="x*0.0-25";
}
        public static void TestCase108() {
        //Test Case 108
        UsageCalculus.TestCase = 108;
        UsageCalculus.EqnUnderTest = "0.0*x-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="x*0.0-25";
}
        public static void TestCase109() {
        //Test Case 109
        UsageCalculus.TestCase = 109;
        UsageCalculus.EqnUnderTest = "1.0*x-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="x*1.0-25";
}
        public static void TestCase110() {
        //Test Case 110
        UsageCalculus.TestCase = 110;
        UsageCalculus.EqnUnderTest = "x*1.0-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="x*1.0-25";
}
        public static void TestCase111() {
        //Test Case 111
        UsageCalculus.TestCase = 111;
        UsageCalculus.EqnUnderTest = "(x-25)/5";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="5*y+25";
}
        public static void TestCase112() {
        //Test Case 112
        UsageCalculus.TestCase = 112;
        UsageCalculus.EqnUnderTest = "a*0.0-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="a*0.0-25";
}
        public static void TestCase113() {
        //Test Case 113
        UsageCalculus.TestCase = 113;
        UsageCalculus.EqnUnderTest = "0.0*a-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="a*0.0-25";
}
        public static void TestCase114() {
        //Test Case 114
        UsageCalculus.TestCase = 114;
        UsageCalculus.EqnUnderTest = "a*1.0-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="a*1.0-25";
}
        public static void TestCase115() {
        //Test Case 115
        UsageCalculus.TestCase = 115;
        UsageCalculus.EqnUnderTest = "1.0*a-25";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="a*1.0-25";
}
        /* ********************************************** */
        /* ************** >= , <= ,<,>,&& ,|| Test Cases  */
        /* ********************************************** */
        public static void TestCase200() {
        //Test Case 200
        UsageCalculus.TestCase = 200;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase201() {
        //Test Case 201
        UsageCalculus.TestCase = 201;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase202() {
        //Test Case 202
        UsageCalculus.TestCase = 202;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase203() {
        //Test Case 203
        UsageCalculus.TestCase = 203;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase204() {
        //Test Case 204
        UsageCalculus.TestCase = 204;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase205() {
        //Test Case 205
        UsageCalculus.TestCase = 205;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}       
        public static void TestCase206() {
        //Test Case 206
        UsageCalculus.TestCase = 206;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
        
}
        public static void TestCase207() {
        //Test Case 207
        UsageCalculus.TestCase = 207;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase208() {
        //Test Case 208
        UsageCalculus.TestCase = 208;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase209() {
        //Test Case 209
        UsageCalculus.TestCase = 209;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase210() {
        //Test Case 210
        UsageCalculus.TestCase = 210;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase211() {
        //Test Case 211
        UsageCalculus.TestCase = 211;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase212() {
        //Test Case 212
        UsageCalculus.TestCase = 212;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase213() {
        //Test Case 213
        UsageCalculus.TestCase = 213;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase214() {
        //Test Case 214
        UsageCalculus.TestCase = 214;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase215() {
        //Test Case 215
        UsageCalculus.TestCase = 215;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase216() {
        //Test Case 216
        UsageCalculus.TestCase = 216;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase217() {
        //Test Case 217
        UsageCalculus.TestCase = 217;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
        }
        public static void TestCase218() {
        //Test Case 218
        UsageCalculus.TestCase = 218;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
        }
        public static void TestCase219() {
        //Test Case 219
        UsageCalculus.TestCase = 219;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase220() {
        //Test Case 220
        UsageCalculus.TestCase = 220;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase221() {
        //Test Case 221
        UsageCalculus.TestCase = 221;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase222() {
        //Test Case 222
        UsageCalculus.TestCase = 222;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase223() {
        //Test Case 223
        UsageCalculus.TestCase = 223;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase224() {
        //Test Case 224
        UsageCalculus.TestCase = 224;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}
        public static void TestCase225() {
        //Test Case 225
        UsageCalculus.TestCase = 225;
        UsageCalculus.EqnUnderTest = "";
        UsageCalculus.TestCaseName = "Simple: Coefficients:0.0";
        UsageCalculus.TestCaseMatrixID="Coefficients:0.0:1 Letter:* Operator:No Exponent Sign:Constant End:Term Sign +&-";
        UsageCalculus.UserTestAnswer ="";
}    
}
