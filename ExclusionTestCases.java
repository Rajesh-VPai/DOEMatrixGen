/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ExclusionTestCases {

    public static Logger log = Logger.getLogger(ExclusionTestCases.class.getName());

    public static boolean IntegralGapInfinity(int j) {
//        //Infinite Loops
        if ((j == 1005)) {
            return true;
        } else if ((j == 80016) || (j == 80031)) {
            return true;
        }
        return false;
    }

    public static boolean DifferentialGapInfinity(int j) {
//        //Infinite Loops
        if ((j == 80014) || (j == 80062)) {
            return true;
        }
        return false;
    }

    public static boolean IntegralGapException(int j) {
//        //Exception 
        if ((j == 40046) || (j == 40047) || (j == 40048) || (j == 40049)) {
            return true;
        }
        return false;
    }

    public static boolean DifferentialGapException(int j) {
//        //Exception Loops
        if ((j == 40046) || (j == 40047) || (j == 40048) || (j == 40049)) {
            return true;
        }
        return false;
    }

    public static boolean IntegralGapWrongAnswer(int j) {
        //Exclude the wrong Answer Test Cases from the Reporting
        //Test Cases will however be executed
        // Return False to exclude from reporting

        return false;
    }

    public static boolean DifferentialGapWrongAnswer(int j) {
        //Exclude the wrong Answer Test Cases from the Reporting 
        //Test Cases will however be executed
        // Return False to exclude from reporting

        return false;
    }

}
