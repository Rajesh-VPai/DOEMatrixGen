/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Calculus;

import static Calculus.MathsContxtLAv.IsLogicalOperator;
import static Calculus.MathsContxtLAv.IsNumber;
import static Calculus.MathsContxtLAv.IsOperator;
import static Calculus.MathsContxtLAv.IsVariable;
import static Calculus.MathsContxtLAv.NormalNotPareFlag;
import static Calculus.MathsContxtLAv.ParenthesisFlag;
import static Calculus.MathsContxtLAv.StrExpr;
import static Calculus.MathsContxtLAv.eatAll;
import java.util.Map;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class PredicateSep {

    public static Logger log = Logger.getLogger(PredicateSep.class.getName());
    // Bool Predicate character Index and Character Variable & String Variable    
    private static int posBool = -1, chBool;
    private static int chNextBool;
    private static String StrBool;
    // Bool3VL Predicate character Index and Character Variable & String Variable   
    private static int posBool3VL = -1, chBool3VL;
    private static int chNextBool3VL;
    private static String StrBool3VL;
    //HghLvlPredicate Expression character Index and Character Variable
    private static int posHghLvl = -1, chHghLvl;
    private static int chNextHghLvl;
    private static String StrHghLvl;

    //Predicate Variables
    private static int PredicateArithOper = -1;
    private static int PredicateArithOperNext = -1;
    private static boolean NegateGotFlag = false;
    private static boolean ZeroTermSignFlag = false;

//Created by Rajesh Pai
// Implements Predicates(Conditions) for F(x) Expressions
    @FunctionalInterface
    public interface PredicateBool {

        boolean eval();
    }

    // Taken from stackoverflow.com:Evaluating a math 
    // expression given in string form [closed]
    //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public static PredicateBool parsePredicate(String str, Map<String, Double> variables, String Silent, MathsContxtLAv.LogLevel MyLogLevel) {

        return new Object() {

            void nextStrCharBool() {
                ++posBool;
                chBool = (posBool < StrBool.length()) ? StrBool.charAt(posBool) : -1;
                if (posBool < (StrBool.length() - 1)) {
                    chNextBool = StrBool.charAt(posBool + 1);
                } else {
                    chNextBool = -1;
                }
                if ((chBool == -1) && (posBool <= StrBool.length())) {
                    posBool = StrBool.length();
                } else if ((chBool == -1) || ((chBool == 0))) {
                    throw new RuntimeException("Expression:parseBase:Unexpected: " + (char) chBool + " int=" + (int) chBool + " Cannot Format:" + StrBool.substring(posBool, StrBool.length()));
                }
            }

            boolean eatBool(int CharToEat) {
                while (chBool == ' ') {
                    nextStrCharBool();
                }
                if (chBool == CharToEat) {
                    nextStrCharBool();
                    return true;
                }
                return false;
            }

            PredicateBool parsePredicate() {
                StrBool=eatAll(str,' ');
                posBool = -1;
                chBool = 0;
                chNextBool = 0;
                PredicateArithOper = -1;
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                if ((StrBool == null) || (StrBool.equalsIgnoreCase(""))) {
                    return (() -> false);
                }
                nextStrCharBool();
                PredicateBool x = parseExpression();
                return x;
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            PredicateBool parseExpression() {
                PredicateBool x = parseFactorPredicate();
                for (;;) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chBool=" + (char) chBool);
                    }
                    if (chBool == ')') {
                        nextStrCharBool();
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseExpression:):Return Value:x.eval()=" + x.eval());
                        }
                        //return x;
                    } else if (chBool == '(') {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseExpression:):(:NegateGotFlag=" + NegateGotFlag);
                        }
                        chBool = chNextBool;
                    } else if ((chBool == '&') && (chNextBool == '&')) {// Logical AND

                        PredicateBool a = x, b = parseFactorPredicate();
                        x = (() -> (a.eval() && b.eval()));
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseExpression:AND:a.eval()=" + a.eval());
                            log.info("PredicateBool:parseExpression:AND:b.eval()=" + b.eval());
                            log.info("PredicateBool:parseExpression:AND:x.eval()=" + x.eval());
                        }
                    } else if ((chBool == '|') && (chNextBool == '|')) { // Logical OR

                        PredicateBool a = x, b = parseFactorPredicate();
                        x = (() -> (a.eval() || b.eval()));
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseExpression:OR:a.eval()=" + a.eval());
                            log.info("PredicateBool:parseExpression:OR:b.eval()=" + b.eval());
                            log.info("PredicateBool:parseExpression:OR:x.eval()=" + x.eval());
                        }
                    } else {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseExpression:Return Value:x.eval()=" + x.eval());
                        }
                        return x;
                    }
                }
            }

            PredicateBool parseFactorPredicate() {
                PredicateBool x = parseTermPredicate();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseFactorPredicate:Initial Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                }
                if ((chBool == '!') || (NegateGotFlag == true)) { // exponentiation
                    final boolean Temp = x.eval();
                    x = (() -> (!Temp));
                }
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseFactorPredicate:Return Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                }
                return x;
            }

            PredicateBool parseTermPredicate() {
                Double x = parseArithExpression();
                PredicateBool y = (() -> false);
                boolean TempInit = true;
                for (;;) {
                    if (chBool == ')') {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:):Initial Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        }
                        if (NegateGotFlag == true) {
                            final boolean Temp = y.eval();
                            y = (() -> !Temp);
                        }
                        NegateGotFlag = false;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:):Return Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        }
                        return y;
                    } else if (chBool == '(') {
                        //nextStrCharBool();
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:(:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '<') && (chNextBool == '=')) { // GreaterThanOREqualTo
                        nextStrCharBool();
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() <= b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() <= b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:<=:a=" + a);
                            log.info("PredicateBool:parseTerm:<=:b=" + b);
                            log.info("PredicateBool:parseTerm:<=:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '<') && (chNextBool != '=')) { // GreaterThan
                        chBool = chNextBool;
                        posBool++;
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() < b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() < b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:<:a=" + a);
                            log.info("PredicateBool:parseTerm:<:b=" + b);
                            log.info("PredicateBool:parseTerm:<:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '>') && (chNextBool == '=')) { // LessThan
                        nextStrCharBool();
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() >= b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() >= b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:>=:a=" + a);
                            log.info("PredicateBool:parseTerm:>=:b=" + b);
                            log.info("PredicateBool:parseTerm:>=:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '>') && (chNextBool != '=')) { // LessThanOREqualTo
                        chBool = chNextBool;
                        posBool++;
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() > b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() > b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:>:a=" + a);
                            log.info("PredicateBool:parseTerm:>:b=" + b);
                            log.info("PredicateBool:parseTerm:>:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '=')) { // EqualTo
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() == b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() == b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:==:a=" + a);
                            log.info("PredicateBool:parseTerm:==:b=" + b);
                            log.info("PredicateBool:parseTerm:==:y.eval()=" + y.eval());
                        }
                    } else if ((chBool == '!') && (chNextBool == '=')) { // Not EqualTo
                        Double a = x;
                        Double b = parseArithExpression();
                        final boolean Temp = y.eval();
                        if (TempInit == false) {
                            y = (() -> Temp && (a.doubleValue() != b.doubleValue()));
                        } else {
                            y = (() -> (a.doubleValue() != b.doubleValue()));
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:==:a=" + a);
                            log.info("PredicateBool:parseTerm:==:b=" + b);
                            log.info("PredicateBool:parseTerm:==:y.eval()=" + y.eval());
                        }
                    } else {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:Return Value:y.eval()=" + y.eval());
                        }
                        return y;
                    }
                }
            }

            Double parseArithExpression() {
                Double x = 0.0;
                int ExprStartPos = posBool;
                int ExprEndPos = -1;
                if (posBool < 0) {
                    ExprStartPos = 0;
                }
                x = parseTerm();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseArithExpression:Initial:x=" + x);
                }
                for (;;) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseArithExpression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chBool=" + (char) chBool + " posBool=" + posBool);
                    }
                    if (eatBool(')')) {
                        return x;
                    } else if ((eatBool('+'))) {// addition
                        Double a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = -1.0 * a + b;
                            ZeroTermSignFlag = false;
                        } else {
                            x = a + b;
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseArithExpression:x=" + x + " a=" + a + "+ b=" + b);
                        }
                    } else if ((eatBool('-'))) { // subtraction
                        Double a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = -1.0 * a - b;
                            ZeroTermSignFlag = false;
                        } else {
                            x = a - b;
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseArithExpression:x=" + x + " a.eval()=" + a + "- b=" + b);
                        }
                    } else {
                        if ((posBool >= 0) && (posBool < StrBool.length())) {
                            ExprEndPos = posBool;
                        } else {
                            ExprEndPos = StrBool.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = StrBool.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseArithExpression:Return Value:x=" + x + " Expr=" + StrBool.substring(ExprStartPos, ExprEndPos));
                            log.warn("PredicateBool:parseArithExpression:Return Value:x=" + x + " Expr=" + StrBool.substring(ExprStartPos, ExprEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseTerm() {
                int TermStartPos = posBool;
                int TermEndPos = -1;
                if (posBool < 0) {
                    TermStartPos = 0;
                }
                Double x = parseFactor();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseTerm:Initial:x=" + x);
                }
                for (;;) {
                    Double a = x;
                    if (eatBool('*')) { // multiplication
                        Double b = parseFactor();
                        x = a * b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:*:a=" + a);
                            log.info("PredicateBool:parseTerm:*:b=" + b);
                            log.info("PredicateBool:parseTerm:*:x=" + x + " a=" + a + "* b=" + b);
                        }
                        int temp = chBool;
                        nextStrCharBool();
                        if (((chBool == '+') || (chBool == '-')) && (temp != '*') && (temp != '/')) {
                            posBool--;
                            chBool = temp;
                            return x;
                        } else if ((chBool != -1) && (chBool != 0)) {
                            posBool--;
                            chBool = temp;
                        }
                    } else if (eatBool('/')) { // division
                        Double b = parseFactor();
                        x = a / b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:/:a.eval=" + a);
                            log.info("PredicateBool:parseTerm:/:b.eval=" + b);
                            log.info("PredicateBool:parseTerm:/:x.eval=" + x + " a=" + a + "/ b=" + b);
                        }
                        int temp = chBool;
                        nextStrCharBool();
                        if (((chBool == '+') || (chBool == '-')) && (temp != '*') && (temp != '/')) {
                            posBool--;
                            chBool = temp;
                            return x;
                        } else if ((chBool != -1) && (chBool != 0)) {
                            posBool--;
                            chBool = temp;
                        }
                    } else if (eatBool('%')) { // Modulus Operator
                        Double b = parseFactor();
                        x = a % b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:%:a=" + a);
                            log.info("PredicateBool:parseTerm:%:b=" + b);
                            log.info("PredicateBool:parseTerm:%:x=" + x + " a=" + a + "% b=" + b);
                        }
                        int temp = chBool;
                        nextStrCharBool();
                        if (((chBool == '+') || (chBool == '-')) && (temp != '*') && (temp != '/')) {
                            posBool--;
                            chBool = temp;
                            return x;
                        } else if ((chBool != -1) && (chBool != 0)) {
                            posBool--;
                            chBool = temp;
                        }
                    } else {
                        if ((posBool >= 0) && (posBool < StrBool.length())) {
                            TermEndPos = posBool;
                        } else {
                            TermEndPos = StrBool.length();
                        }
                        if ((TermStartPos > TermEndPos)) {
                            TermEndPos = StrBool.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseTerm:Return Value:x=" + x + " Term=" + StrBool.substring(TermStartPos, TermEndPos));
                            log.warn("PredicateBool:parseTerm:Return Value:x=" + x + " Term=" + StrBool.substring(TermStartPos, TermEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseFactor() {
                int FactorStartPos = posBool;
                int FactorEndPos = -1;
                if (posBool < 0) {
                    FactorStartPos = 0;
                }
                Double x = parseBasePredicate();
                for (;;) {
                    if (eatBool('^')) { // exponentiation
                        Double a = x, b = parseBasePredicate();
                        x = Math.pow(a, b);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseFactor:x=" + x + " Math.pow(a=" + a + "^ b=" + b + ")");
                        }
                    } else {
                        if ((posBool >= 0) && (posBool < StrBool.length())) {
                            FactorEndPos = posBool;
                        } else {
                            FactorEndPos = StrBool.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = StrBool.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("PredicateBool:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos);
                            log.info("PredicateBool:parseFactor:Return Value:x=" + x + " Factor=" + StrBool.substring(FactorStartPos, FactorEndPos));
                            log.warn("PredicateBool:parseFactor:Return Value:x=" + x + " Factor=" + StrBool.substring(FactorStartPos, FactorEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseBasePredicate() {
                int startPos = posBool;
                chNextBool = StrBool.charAt(posBool);
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseBasePredicate:Initial:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " StrBool=" + StrBool.substring(startPos));
                }
                Double x;
                if (chBool == '(') { //parentheses
                    nextStrCharBool();
                    startPos = posBool;
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:(:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool);
                    }
                    if (chBool == '!') {
                        NegateGotFlag = true;
                    }
                    x = parseBasePredicate();
                    return x;
                }
                Double xx = 1.0;
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("PredicateBool:parseBasePredicate:Number:chBool=" + (char) chBool + " chNexBoolt=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                }
                if (IsNumber(chBool)) { //numbers
                    while (IsNumber(chBool)) {
                        nextStrCharBool();
                        if ((chBool == 'E') || (chBool == 'e')) {
                            nextStrCharBool();
                            if ((chBool == '-') || (chBool == '+')) {
                                nextStrCharBool();
                            }
                        }
                    }

                    if ((StrBool != null) && (!StrBool.equalsIgnoreCase("")) && (!StrBool.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(StrBool.substring(startPos, posBool));
                    }
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:Number:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx);
                    }
                    return xx;
                }
                if (chBool == '=') {
                    //nextStrCharBool();
                }
                if ((chBool == ')') && (posBool == (StrBool.length() - 1))) {
                    chBool = -1;
                    posBool++;
                } else if (chBool == ')') {
                    nextStrCharBool();
                }
                Double xx2 = 1.0;
                startPos = posBool;
                if (IsVariable(chBool)) {//functions & variables
                    while ((IsVariable(chBool)) || (IsNumber(chBool))) {
                        nextStrCharBool();
                    }
                    String name = StrBool.substring(startPos, posBool).trim();
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                        xx2 = variables.get(name);
                    }
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:Variable:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx2=" + xx2 + " name=" + name);
                    }
                    if (chBool == '=') {
                        //nextStrCharBool();
                    }
                    if ((chBool == ')') && (posBool == (StrBool.length() - 1))) {
                        chBool = -1;
                        posBool++;
                    } else if (chBool == ')') {
                        nextStrCharBool();
                    }
                    return xx2;
                } else if ((IsLogicalOperator(chBool)) && (IsLogicalOperator(chNextBool))) {
                    nextStrCharBool();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    return xx;
                } else if ((IsLogicalOperator(chBool))) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    nextStrCharBool();
                    posBool--;
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    return xx;
                } else if ((IsOperator(chBool)) && ((IsNumber(chNextBool)) || (IsVariable(chNextBool)))) {
                    PredicateArithOper = chBool;
                    PredicateArithOperNext = chNextBool;
                    nextStrCharBool();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNextBool=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("PredicateBool:parseBasePredicate:IsLogicalOperator:chBool=" + (char) chBool + " chNext=" + (char) chNextBool + " xx=" + xx + " StrBool=" + StrBool.substring(startPos));
                    }
                    return xx;
                } else {
                    if ((str != null) && (startPos < StrBool.length()) && (startPos < posBool) && (chBool != -1) && (chBool != 0)) {
                        throw new RuntimeException("PredicateBool:parseBasePredicate:Unexpected: " + (char) chBool + " int=" + (int) chBool + " Cannot Format:" + StrBool.substring(posBool, StrBool.length()));
                    } else if (chBool != -1) {
                        throw new RuntimeException("PredicateBool:parseBasePredicate:Unexpected: " + (char) chBool + " int=" + (int) chBool + " Cannot Format:" + StrBool.substring(posBool, StrBool.length()));
                    }
                    return xx;
                }
            }
        }.parsePredicate();
    }
//Created by Rajesh Pai
// Implements Predicates(Conditions) for F(x) Expressions

    @FunctionalInterface
    public interface Predicate3VL {

        String eval();
    }

    // Taken from stackoverflow.com:Evaluating a math 
    // expression given in string form [closed]
    //http://stackoverflow.com/questions/3422673/evaluating-a-math-expression-given-in-string-form
    public static Predicate3VL parsePredicate3VL(String str, Map<String, Double> variables, String Silent, MathsContxtLAv.LogLevel MyLogLevel) {

        return new Object() {

            void nextStrCharBool3VL() {
                ++posBool3VL;
                chBool3VL = (posBool3VL < StrBool3VL.length()) ? StrBool3VL.charAt(posBool3VL) : -1;
                if (posBool3VL < (StrBool3VL.length() - 1)) {
                    chNextBool3VL = StrBool3VL.charAt(posBool3VL + 1);
                } else {
                    chNextBool3VL = -1;
                }
            }

            void prevStrCharBool3VL() {
                --posBool3VL;
                chBool3VL = (posBool3VL < StrBool3VL.length()) ? StrBool3VL.charAt(posBool3VL) : -1;
                if (posBool3VL < (StrBool3VL.length())) {
                    chNextBool3VL = StrBool3VL.charAt(posBool3VL + 1);
                } else {
                    chNextBool3VL = -1;
                }
            }

            boolean eatBool3VL(int CharToEat) {
                while (chBool3VL == ' ') {
                    nextStrCharBool3VL();
                }
                if (chBool3VL == CharToEat) {
                    nextStrCharBool3VL();
                    return true;
                }
                return false;
            }

            String eatStrAll(String Temp, int CharToEat) {
                String MyTempString = "";
                if (Temp.equalsIgnoreCase("")) {
                    Temp = StrBool3VL;
                }
                for (int i = 0; (i < Temp.length());) {
                    if (CharToEat == Temp.charAt(i)) {
                        i++;
                    } else {
                        MyTempString = MyTempString + Temp.charAt(i);
                        i++;
                    }
                }
                return MyTempString;
            }

            Predicate3VL parsePredicate3VL() {
                StrBool3VL = str;
                posBool3VL = -1;
                chBool3VL = 0;
                chNextBool3VL = 0;
                PredicateArithOper = -1;
                ParenthesisFlag = false;
                NormalNotPareFlag = true;
                nextStrCharBool3VL();
                Predicate3VL x = parseExpression();
                return x;
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            Predicate3VL parseExpression() {
                Predicate3VL x = parseFactorPredicate();
                for (;;) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chBool3VL=" + (char) chBool3VL);
                    }
                    if (chBool3VL == ')') {
                        nextStrCharBool3VL();
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseExpression:):Return Value:x.eval()=" + x.eval());
                        }
                        //return x;
                    } else if (chBool3VL == '(') {
                        //nextStrChar();
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseExpression:):(:NegateGotFlag=" + NegateGotFlag);
                        }
                        chBool3VL = chNextBool3VL;
                    } else if ((chBool3VL == '&') && (chNextBool3VL == '&')) {// Logical AND

                        Predicate3VL a = x, b = parseFactorPredicate();
                        if ((a.eval().equalsIgnoreCase("true")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else if ((a.eval().equalsIgnoreCase("true")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("false")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("false")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "Unknown");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("Unknown"))) {
                            x = (() -> "Unknown");
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseExpression:AND:a.eval()=" + a.eval());
                            log.info("Predicate3VL:parseExpression:AND:b.eval()=" + b.eval());
                            log.info("Predicate3VL:parseExpression:AND:x.eval()=" + x.eval());
                        }

                    } else if ((chBool3VL == '|') && (chNextBool3VL == '|')) { // Logical OR

                        Predicate3VL a = x, b = parseFactorPredicate();
                        if ((a.eval().equalsIgnoreCase("true")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else if ((a.eval().equalsIgnoreCase("true")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "true");
                        } else if ((a.eval().equalsIgnoreCase("false")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("false")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("false"))) {
                            x = (() -> "false");
                        } else if ((a.eval().equalsIgnoreCase("Unknown")) && (b.eval().equalsIgnoreCase("Unknown"))) {
                            x = (() -> "Unknown");
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseExpression:OR:a.eval()=" + a.eval());
                            log.info("Predicate3VL:parseExpression:OR:b.eval()=" + b.eval());
                            log.info("Predicate3VL:parseExpression:OR:x.eval()=" + x.eval());
                        }
                    } else {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseExpression:Return Value:x.eval()=" + x.eval());
                        }
                        return x;
                    }
                }
            }

            Predicate3VL parseFactorPredicate() {
                String Data = parseStrTriStateHighExpr();
                Predicate3VL x = (() -> "");
                if (Data.equalsIgnoreCase("true")) {
                    x = (() -> "true");
                } else if (Data.equalsIgnoreCase("false")) {
                    x = (() -> "false");
                } else if (Data.equalsIgnoreCase("Unknown")) {
                    x = (() -> "Unknown");
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.error("Predicate3VL:parseFactorPredicate:GOT Unknown String Value:Data=" + Data + " NegateGotFlag=" + NegateGotFlag);
                    }
                } else {
                    x = (() -> "Error");
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.error("Predicate3VL:parseFactorPredicate:GOT ERROR String Value:Data=" + Data + " NegateGotFlag=" + NegateGotFlag);
                        log.error("Predicate3VL:parseFactorPredicate:GOT ERROR Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                    }
                }
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseFactorPredicate:Initial String Value:Data=" + Data + " NegateGotFlag=" + NegateGotFlag + " chBool3VL=" + (char) chBool3VL);
                    log.info("Predicate3VL:parseFactorPredicate:Initial Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                }
                if ((chBool3VL == '!') || (NegateGotFlag == true)) { // exponentiation
                    final String Temp = x.eval();
                    String y = "" + x.eval();
                    if (y.equalsIgnoreCase("true")) {
                        x = (() -> "false");
                    } else if (y.equalsIgnoreCase("false")) {
                        x = (() -> "true");
                    } else if (y.equalsIgnoreCase("Unknown")) {
                        x = (() -> "Unknown");
                    } else {
                        y = "ERROR";
                        x = (() -> "Error");
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.error("Predicate3VL:parseStrTriStateHighExpr::ERROR String Value:y=" + y + " NegateGotFlag=" + NegateGotFlag);
                            log.error("Predicate3VL:parseStrTriStateHighExpr::ERROR Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                        }
                    }
                }
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseFactorPredicate:Return Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                }
                return x;
            }

            String parseStrTriStateHighExpr() {
                String x = "Unknown";
                boolean TriStateFlag = false;
                if (chBool3VL == '#') {
                    nextStrCharBool3VL();
                    TriStateFlag = true;
                }
                Predicate3VL y = parseTermPredicate();
                if (TriStateFlag == true) {
                    x = y.eval();
                } else {
                    x = "" + y.eval();
                    if (x.equalsIgnoreCase("true"))
                    ; else if (x.equalsIgnoreCase("false"))
                    ; else if (x.equalsIgnoreCase("Unknown"))
                    ; else {
                        x = "ERROR";
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.error("Predicate3VL:parseStrTriStateHighExpr::ERROR Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                            log.error("Predicate3VL:parseStrTriStateHighExpr::ERROR String Value:x=" + x + " NegateGotFlag=" + NegateGotFlag);
                        }
                    }
                }
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseStrTriStateHighExpr::Return Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                    log.info("Predicate3VL:parseStrTriStateHighExpr::Return String Value:x=" + x + " NegateGotFlag=" + NegateGotFlag);
                }
                return x;
            }

            Predicate3VL parseTermPredicate() {
                String DataInit = parseStrTriStateLowExpr();
                Double x = Double.parseDouble(DataInit);
                Predicate3VL y = (() -> "Unknown");
                boolean TempInit = true;
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseTerm:Initial Value:y.eval()=" + y.eval() + " posBool3VL=" + posBool3VL + " StrBool3VL.length=" + StrBool3VL.length() + " chBool3VL=" + (char) chBool3VL + " x=" + x + " chNextBool3VL=" + (char) chNextBool3VL);
                    log.info("Predicate3VL:parseTerm:Initial Value:DataInit=" + DataInit);
                }
                for (;;) {
                    if (chBool3VL == ')') {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:):Initial Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        }
                        if (NegateGotFlag == true) {
                            final String Temp = y.eval();
                            if (Temp.equalsIgnoreCase("true")) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("false")) {
                                y = (() -> "true");
                            } else if (Temp.equalsIgnoreCase("Unknown")) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                            }
                        }
                        NegateGotFlag = false;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:):Return Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        }
                        return y;
                    } else if (chBool3VL == '(') {
                        //nextStrCharBool3VL();
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:(:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '<') && (chNextBool3VL == '=')) { // GreaterThanOREqualTo
                        nextStrCharBool3VL();
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:<=:y.eval()=" + y.eval());
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() <= b.doubleValue())) {
                                y = (() -> "true");
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() <= b.doubleValue())) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() <= b.doubleValue()))) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() <= b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() <= b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:<=:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() <= b.doubleValue())) {
                                y = (() -> "true");
                            } else if (a.doubleValue() > b.doubleValue()) {
                                y = (() -> "false");
                            } else {
                                y = (() -> "Unknown");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:<=:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:<=:a=" + a);
                            log.info("Predicate3VL:parseTerm:<=:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:<=:b=" + b);
                            log.info("Predicate3VL:parseTerm:<=:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '<') && (chNextBool3VL != '=')) { // GreaterThan
                        chBool3VL = chNextBool3VL;
                        posBool3VL++;
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:<:y.eval()=" + y.eval());
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() < b.doubleValue())) {
                                y = (() -> "true");
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() < b.doubleValue())) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() < b.doubleValue()))) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() < b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() < b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:<:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() < b.doubleValue())) {
                                y = (() -> "true");
                            } else if (a.doubleValue() > b.doubleValue()) {
                                y = (() -> "false");
                            } else {
                                y = (() -> "Unknown");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:<:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:<:a=" + a);
                            log.info("Predicate3VL:parseTerm:<:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:<:b=" + b);
                            log.info("Predicate3VL:parseTerm:<:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '>') && (chNextBool3VL == '=')) { // LessThan
                        nextStrCharBool3VL();
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:>=:y.eval()=" + y.eval());
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() >= b.doubleValue())) {
                                y = (() -> "true");
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() >= b.doubleValue())) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() >= b.doubleValue()))) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() >= b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() >= b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:>=:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() >= b.doubleValue())) {
                                y = (() -> "true");
                            } else if (a.doubleValue() < b.doubleValue()) {
                                y = (() -> "false");
                            } else {
                                y = (() -> "Unknown");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:>=:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:>=:a=" + a);
                            log.info("Predicate3VL:parseTerm:>=:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:>=:b=" + b);
                            log.info("Predicate3VL:parseTerm:>=:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '>') && (chNextBool3VL != '=')) { // LessThanOREqualTo
                        chBool3VL = chNextBool3VL;
                        posBool3VL++;
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:>:y.eval()=" + y.eval());
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() >= b.doubleValue())) {
                                y = (() -> "true");
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() > b.doubleValue())) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() > b.doubleValue()))) {
                                y = (() -> "false");
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() > b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() > b.doubleValue()))) {
                                y = (() -> "Unknown");
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:>:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() > b.doubleValue())) {
                                y = (() -> "true");
                            } else if (a.doubleValue() < b.doubleValue()) {
                                y = (() -> "false");
                            } else {
                                y = (() -> "Unknown");
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:>:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:>:a=" + a);
                            log.info("Predicate3VL:parseTerm:>:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:>:b=" + b);
                            log.info("Predicate3VL:parseTerm:>:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '=') && (chNextBool3VL == '=')) { // EqualTo
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() == b.doubleValue())) {
                                y = (() -> "true");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() == b.doubleValue())) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() == b.doubleValue()))) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() == b.doubleValue()))) {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() == b.doubleValue()))) {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:=:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() == b.doubleValue())) {
                                y = (() -> "true");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (a.doubleValue() != b.doubleValue()) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:=:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:==:a=" + a);
                            log.info("Predicate3VL:parseTerm:==:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:==:b=" + b);
                            log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval());
                        }
                    } else if ((chBool3VL == '!') && (chNextBool3VL == '=')) { // Not EqualTo
                        Double a = x;
                        String Data = parseStrTriStateLowExpr();
                        Double b = Double.parseDouble(Data);
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:!=:y.eval()=" + y.eval());
                        }
                        final String Temp = y.eval();
                        if (TempInit == false) {
                            if (Temp.equalsIgnoreCase("true") && (a.doubleValue() != b.doubleValue())) {
                                y = (() -> "true");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("false") && (a.doubleValue() != b.doubleValue())) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("true") && (!(a.doubleValue() != b.doubleValue()))) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("Unknown") && (!(a.doubleValue() != b.doubleValue()))) {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("Unknown") && ((a.doubleValue() != b.doubleValue()))) {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (Temp.equalsIgnoreCase("")) {
                                y = (() -> "Error");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:!=:y.eval()=" + y.eval());
                            }
                        } else {
                            if ((a.doubleValue() != b.doubleValue())) {
                                y = (() -> "true");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else if (a.doubleValue() == b.doubleValue()) {
                                y = (() -> "false");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            } else {
                                y = (() -> "Unknown");
                                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                    log.info("Predicate3VL:parseTerm:==:y.eval()=" + y.eval() + " x=" + x + " a=" + a + " b=" + b + " Data=" + Data);
                                }
                            }
                            if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                                log.info("Predicate3VL:parseTerm:!=:y.eval()=" + y.eval());
                            }
                        }
                        TempInit = false;
                        x = b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:!=:a=" + a);
                            log.info("Predicate3VL:parseTerm:!=:Data=" + Data);
                            log.info("Predicate3VL:parseTerm:!=:b=" + b);
                            log.info("Predicate3VL:parseTerm:!=:y.eval()=" + y.eval());
                        }
                    } else {
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:Return Value:y.eval()=" + y.eval() + " chBool3VL=" + (char) chBool3VL + " x=" + x + " chNextBool3VL=" + (char) chNextBool3VL);
                        }
                        return y;
                    }
                }
            }

            String parseStrTriStateLowExpr() {
                String x = "Unknown";
                x = "" + parseArithExpression();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseStrTriStateLowExpr:Initial:x=" + x + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                }
                return x;
            }

            Double parseArithExpression() {
                Double x = 0.0;
                int ExprStartPos = posBool3VL;
                int ExprEndPos = -1;
                if (posBool3VL < 0) {
                    ExprStartPos = 0;
                }
                x = parseTerm();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseArithExpression:Initial:x=" + x);
                }
                for (;;) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseArithExpression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " chBool3VL=" + (char) chBool3VL + " posBool3VL=" + posBool3VL);
                    }
                    if (eatBool3VL(')')) {
                        return x;
                    } else if ((eatBool3VL('+'))) {// addition
                        Double a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = -1.0 * a + b;
                            ZeroTermSignFlag = false;
                        } else {
                            x = a + b;
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseArithExpression:x=" + x + " a=" + a + "+ b=" + b);
                        }
                    } else if ((eatBool3VL('-'))) { // subtraction
                        Double a = x, b = parseTerm();
                        if (ZeroTermSignFlag == true) {
                            x = -1.0 * a - b;
                            ZeroTermSignFlag = false;
                        } else {
                            x = a - b;
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseArithExpression:x=" + x + " a.eval()=" + a + "- b=" + b);
                        }
                    } else {
                        if ((posBool3VL >= 0) && (posBool3VL < StrBool3VL.length())) {
                            ExprEndPos = posBool3VL;
                        } else {
                            ExprEndPos = StrBool3VL.length();
                        }
                        if ((ExprStartPos > ExprEndPos)) {
                            ExprEndPos = StrBool3VL.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseArithExpression:Return Value:x=" + x + " Expr=" + StrBool3VL.substring(ExprStartPos, ExprEndPos));
                            log.warn("Predicate3VL:parseArithExpression:Return Value:x=" + x + " Expr=" + StrBool3VL.substring(ExprStartPos, ExprEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseTerm() {
                int TermStartPos = posBool3VL;
                int TermEndPos = -1;
                if (posBool3VL < 0) {
                    TermStartPos = 0;
                }
                Double x = parseFactor();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseTerm:Initial:x=" + x + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                }
                for (;;) {
                    Double a = x;
                    if (eatBool3VL('*')) { // multiplication
                        Double b = parseFactor();
                        x = a * b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:*:a=" + a + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                            log.info("Predicate3VL:parseTerm:*:b=" + b);
                            log.info("Predicate3VL:parseTerm:*:x=" + x + " a=" + a + "* b=" + b);
                        }
                    } else if (eatBool3VL('/')) { // division
                        Double b = parseFactor();
                        x = a / b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:/:a.eval=" + a + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                            log.info("Predicate3VL:parseTerm:/:b.eval=" + b);
                            log.info("Predicate3VL:parseTerm:/:x.eval=" + x + " a=" + a + "/ b=" + b);
                        }
                    } else if (eatBool3VL('%')) { // Modulus
                        Double b = parseFactor();
                        x = a % b;
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:%:a=" + a + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                            log.info("Predicate3VL:parseTerm:%:b=" + b);
                            log.info("Predicate3VL:parseTerm:%:x=" + x + " a=" + a + "% b=" + b);
                        }
                    } else {
                        if ((posBool3VL >= 0) && (posBool3VL < StrBool3VL.length())) {
                            TermEndPos = posBool3VL;
                        } else {
                            TermEndPos = StrBool3VL.length();
                        }
                        if ((TermStartPos > TermEndPos)) {
                            TermEndPos = StrBool3VL.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseTerm:Return Value:x=" + x + " Term=" + StrBool3VL.substring(TermStartPos, TermEndPos) + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                            log.warn("Predicate3VL:parseTerm:Return Value:x=" + x + " Term=" + StrBool3VL.substring(TermStartPos, TermEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseFactor() {
                int FactorStartPos = posBool3VL;
                int FactorEndPos = -1;
                if (posBool3VL < 0) {
                    FactorStartPos = 0;
                }
                Double x = parseBasePredicate();
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseFactor:x=" + x + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                }
                for (;;) {
                    if (eatBool3VL('^')) { // exponentiation
                        Double a = x, b = parseBasePredicate();
                        x = Math.pow(a.doubleValue(), b.doubleValue());
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseFactor:x=" + x + " Math.pow(a=" + a + "^ b=" + b + ")" + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                        }
                    } else {
                        if ((posBool3VL >= 0) && (posBool3VL < StrBool3VL.length())) {
                            FactorEndPos = posBool3VL;
                        } else {
                            FactorEndPos = StrBool3VL.length();
                        }
                        if ((FactorStartPos > FactorEndPos)) {
                            FactorEndPos = StrBool3VL.length();
                        }
                        if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                            log.info("Predicate3VL:parseFactor:FactorStartPos=" + FactorStartPos + " FactorEndPos=" + FactorEndPos + " chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                            log.info("Predicate3VL:parseFactor:Return Value:x=" + x + " Factor=" + StrBool3VL.substring(FactorStartPos, FactorEndPos));
                            log.warn("Predicate3VL:parseFactor:Return Value:x=" + x + " Factor=" + StrBool3VL.substring(FactorStartPos, FactorEndPos));
                        }
                        return x;
                    }
                }
            }

            Double parseBasePredicate() {
                int startPos = posBool3VL;
                chNextBool3VL = StrBool3VL.charAt(posBool3VL);
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseBasePredicate:Initial:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " str=" + str.substring(startPos));
                }
                Double x;
                if (chBool3VL == '(') { //parentheses
                    nextStrCharBool3VL();
                    startPos = posBool3VL;
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:(:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL);
                    }
                    if (chBool3VL == '!') {
                        NegateGotFlag = true;
                    }
                    x = parseBasePredicate();
                    return x;
                }
                Double xx = 1.0;
                if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                    log.info("Predicate3VL:parseBasePredicate:Number:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                }
                if (IsNumber(chBool3VL)) { //numbers
                    while (IsNumber(chBool3VL)) {
                        nextStrCharBool3VL();
                        if ((chBool3VL == 'E') || (chBool3VL == 'e')) {
                            nextStrCharBool3VL();
                            if ((chBool3VL == '-') || (chBool3VL == '+')) {
                                nextStrCharBool3VL();
                            }
                        }
                    }

                    if ((str != null) && (!str.equalsIgnoreCase("")) && (!str.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(str.substring(startPos, posBool3VL));
                    }
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:Number:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx);
                    }
                    return xx;
                }
                if (chBool3VL == '=') {
                    //nextStrCharBool3VL();
                }
                if ((chBool3VL == ')') && (posBool3VL == (str.length() - 1))) {
                    chBool3VL = -1;
                    posBool3VL++;
                } else if (chBool3VL == ')') {
                    //nextStrCharBool3VL();
                }
                Double xx2 = 1.0;
                startPos = posBool3VL;
                if (IsVariable(chBool3VL)) {//functions & variables
                    while ((IsVariable(chBool3VL)) || (IsNumber(chBool3VL))) {
                        nextStrCharBool3VL();
                    }
                    String name = str.substring(startPos, posBool3VL).trim();
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                        xx2 = variables.get(name);
                    }
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:Variable:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx2=" + xx2 + " name=" + name);
                    }
                    if (chBool3VL == '=') {
                        //nextStrCharBool3VL();
                    }
                    if ((chBool3VL == ')') && (posBool3VL == (str.length() - 1))) {
                        chBool3VL = -1;
                        posBool3VL++;
                    } else if (chBool3VL == ')') {
                        //nextStrCharBool3VL();
                    }
                    return xx2;
                } else if ((IsLogicalOperator(chBool3VL)) && (IsLogicalOperator(chNextBool3VL))) {
                    nextStrCharBool3VL();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    return xx;
                } else if ((IsLogicalOperator(chBool3VL))) {
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    nextStrCharBool3VL();
                    posBool3VL--;
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    return xx;
                } else if ((IsOperator(chBool3VL)) && ((IsNumber(chNextBool3VL)) || (IsVariable(chNextBool3VL)))) {
                    PredicateArithOper = chBool3VL;
                    PredicateArithOperNext = chNextBool3VL;
                    nextStrCharBool3VL();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    xx = parseBasePredicate();
                    if (MyLogLevel.level >= MathsContxtLAv.LogLevel.Info.level) {
                        log.info("Predicate3VL:parseBasePredicate:IsLogicalOperator:chBool3VL=" + (char) chBool3VL + " chNextBool3VL=" + (char) chNextBool3VL + " xx=" + xx + " str=" + str.substring(startPos));
                    }
                    return xx;
                } else {
                    if ((startPos < str.length()) && (startPos < posBool3VL)) {
                        throw new RuntimeException("Predicate3VL:parseBasePredicate:Unexpected: " + (char) chBool3VL + " int=" + (int) chBool3VL + " Cannot Format:" + str.substring(posBool3VL, str.length()));
                    } else if (chBool3VL != -1) {
                        throw new RuntimeException("Predicate3VL:parseBasePredicate:Unexpected: " + (char) chBool3VL + " int=" + (int) chBool3VL + " Cannot Format:" + str.substring(posBool3VL, str.length()));
                    }
                    return xx;
                }
            }
        }.parsePredicate3VL();
    }
//Created by Rajesh Pai
// Implements Predicates(Conditions) for F(x) Expressions

    @FunctionalInterface

    public interface HghLvlPredicate {

        String evalPredicate();
    }

    public static HghLvlPredicate parsePredicateHghLvl(String str, Map<String, Double> variables) {
        StrHghLvl = str;
        posHghLvl = -1;
        chHghLvl = 0;
        chNextHghLvl = 0;
        ParenthesisFlag = false;
        NormalNotPareFlag = true;

        return new Object() {

            void nextStrChar() {
                ++posHghLvl;
                chHghLvl = (posHghLvl < StrHghLvl.length()) ? StrHghLvl.charAt(posHghLvl) : -1;
                if (posHghLvl < (StrHghLvl.length() - 1)) {
                    chNextHghLvl = StrHghLvl.charAt(posHghLvl + 1);
                } else {
                    chNextHghLvl = -1;
                }
            }

            void prevStrChar() {
                --posHghLvl;
                chHghLvl = (posHghLvl < StrHghLvl.length()) ? StrHghLvl.charAt(posHghLvl) : -1;
                if (posHghLvl < (StrHghLvl.length() - 1)) {
                    chNextHghLvl = StrHghLvl.charAt(posHghLvl + 1);
                } else {
                    chNextHghLvl = -1;
                }
            }

            boolean eatStrChar(int CharToEat, int CharToEatNxt) {
                while (chHghLvl == ' ') {
                    nextStrChar();
                }
                if ((chNextHghLvl != '\0') && (chHghLvl == CharToEat) && (chNextHghLvl == CharToEatNxt)) {
                    nextStrChar();
                    return true;
                } else if ((chNextHghLvl == '\0') && (chHghLvl == CharToEat)) {
                    nextStrChar();
                    return true;
                }
                return false;
            }

            String eatStrAll(String Temp, int CharToEat) {
                String MyTempString = "";
                if (Temp.equalsIgnoreCase("")) {
                    Temp = StrHghLvl;
                }
                for (int i = 0; (i < Temp.length());) {
                    if (CharToEat == Temp.charAt(i)) {
                        i++;
                    } else {
                        MyTempString = MyTempString + Temp.charAt(i);
                        i++;
                    }
                }
                return MyTempString;
            }

            HghLvlPredicate parsePredicate() {
                nextStrChar();
                HghLvlPredicate x = parseExpression();
//                if ((pos != -1)&&(str != null)&&(!str.equalsIgnoreCase(""))&&(str.substring(pos, str.length()).equalsIgnoreCase(""))&&(pos <= str.length())) {
//                    throw new RuntimeException("Expression:parse:Unexpected: " + (char) ch + " int=" + (int) ch + " pos=" + pos + " str=" + str.substring(pos, str.length()));
//                }
                return x;
            }
            //Grammar:
            //expression = term | expression '+' term |expression '-' term
            //term = factor | term '*' factor | term '/' factor
            //factor = base | base ^ base
            //base = '-' base | '+' base | number | identifier |function expression | '(' expression ')'
            //

            HghLvlPredicate parseExpression() {
                HghLvlPredicate x = parseFactorPredicate();
                for (;;) {
                    //log.warn("Predicate:Expression:Top:Inside Loop: parseExpression:ParenthesisFlag=" + ParenthesisFlag + " NormalNotPareFlag=" + NormalNotPareFlag + " ch=" + (char) ch);
                    if (chHghLvl == ')') {
                        nextStrChar();
                        //log.info("Predicate:parseExpression:):Return Value:x.eval()=" + x.eval());
                        //return x;
                    } else if (chHghLvl == '(') {
                        //nextStrChar();
                        //log.info("Predicate:parseExpression:):(:NegateGotFlag=" + NegateGotFlag);
                        chHghLvl = chNextHghLvl;
                    } else if ((chHghLvl == '&') && (chNextHghLvl == '&')) {// Logical AND

                        HghLvlPredicate a = x, b = parseFactorPredicate();
                        //x = (() -> (a.evalPredicate() &&  b.evalPredicate()));
                        if ((a.evalPredicate().equalsIgnoreCase("true")) && (b.evalPredicate().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else {
                            x = (() -> "false");
                        }
                        //log.info("Predicate:parseExpression:AND:a.eval()=" + a.eval());
                        //log.info("Predicate:parseExpression:AND:b.eval()=" + b.eval());
                        //log.info("Predicate:parseExpression:AND:x.eval()=" + x.eval());
                    } else if ((chHghLvl == '|') && (chNextHghLvl == '|')) { // Logical OR

                        HghLvlPredicate a = x, b = parseFactorPredicate();
                        //x = (() -> (a.evalPredicate() || b.evalPredicate()));
                        if ((a.evalPredicate().equalsIgnoreCase("true")) || (b.evalPredicate().equalsIgnoreCase("true"))) {
                            x = (() -> "true");
                        } else {
                            x = (() -> "false");
                        }
                        //log.info("Predicate:parseExpression:OR:a.eval()=" + a.eval());
                        //log.info("Predicate:parseExpression:OR:b.eval()=" + b.eval());
                        //log.info("Predicate:parseExpression:OR:x.eval()=" + x.eval());
                    } else {
                        //log.info("Predicate:parseExpression:Return Value:x.eval()=" + x.eval());
                        return x;
                    }
                }
            }

            HghLvlPredicate parseFactorPredicate() {
                HghLvlPredicate x = parseTermPredicate();
                //log.info("Predicate:parseFactorPredicate:Initial Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                if ((chHghLvl == '!') || (NegateGotFlag == true)) { // exponentiation
                    final String Temp = x.evalPredicate();
                    //x = (() -> (!Temp));
                    if (Temp.equalsIgnoreCase("true")) {
                        x = (() -> "false");
                    } else {
                        x = (() -> "true");
                    }
                }
                //log.info("Predicate:parseFactorPredicate:Return Value:x.eval()=" + x.eval() + " NegateGotFlag=" + NegateGotFlag);
                return x;
            }

            HghLvlPredicate parseTermPredicate() {
                Double x = parseBasePredicate();
                HghLvlPredicate y = (() -> "");
                boolean TempInit = true;
                for (;;) {
                    if (chHghLvl == ')') {
                        //log.info("Predicate:parseTerm:):Initial Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        if (NegateGotFlag == true) {
                            final String Temp = y.evalPredicate();
                            //y = (() -> !Temp);
                            if (Temp.equalsIgnoreCase("true")) {
                                y = (() -> "false");
                            } else {
                                y = (() -> "true");
                            }
                        }
                        NegateGotFlag = false;
                        //log.info("Predicate:parseTerm:):Return Value:y.eval()=" + y.eval() + " NegateGotFlag=" + NegateGotFlag);
                        return y;
                    } else if (chHghLvl == '(') {
                        //nextStrChar();
                        //log.info("Predicate:parseTerm:(:y.eval()=" + y.eval());
                    } else if ((chHghLvl == '<') && (chNextHghLvl == '=')) { // GreaterThanOREqualTo
                        nextStrChar();
                        Double a = x;
                        Double b = parseBasePredicate();
                        final String Temp = y.evalPredicate();
                        if (TempInit == false) {
                            //y = (() -> Temp && (a <= b));
                            if ((Temp.equalsIgnoreCase("true")) && (a.doubleValue() <= b.doubleValue())) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        } else {
                            //y = (() -> (a <= b));
                            if (a.doubleValue() <= b.doubleValue()) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        }
                        TempInit = false;
                        x = b;
                        //log.info("Predicate:parseTerm:<=:a=" + a);
                        //log.info("Predicate:parseTerm:<=:b=" + b);
                        //log.info("Predicate:parseTerm:<=:y.eval()=" + y.eval());
                    } else if ((chHghLvl == '<') && (chNextHghLvl != '=')) { // GreaterThan
                        chHghLvl = chNextHghLvl;
                        posHghLvl++;
                        Double a = x;
                        Double b = parseBasePredicate();
                        final String Temp = y.evalPredicate();
                        if (TempInit == false) {
                            //y = (() -> Temp && (a < b));
                            if ((Temp.equalsIgnoreCase("true")) && (a.doubleValue() < b.doubleValue())) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        } else {
                            //y = (() -> (a < b));
                            if (a.doubleValue() < b.doubleValue()) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        }
                        TempInit = false;
                        x = b;
                        //log.info("Predicate:parseTerm:<:a=" + a);
                        //log.info("Predicate:parseTerm:<:b=" + b);
                        //log.info("Predicate:parseTerm:<:y.eval()=" + y.eval());
                    } else if ((chHghLvl == '>') && (chNextHghLvl == '=')) { // LessThan
                        nextStrChar();
                        Double a = x;
                        Double b = parseBasePredicate();
                        final String Temp = y.evalPredicate();
                        if (TempInit == false) {
                            //y = (() -> Temp && (a >= b));
                            if ((Temp.equalsIgnoreCase("true")) && (a.doubleValue() >= b.doubleValue())) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        } else {
                            //y = (() -> (a >= b));
                            if (a.doubleValue() >= b.doubleValue()) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        }
                        TempInit = false;
                        x = b;
                        //log.info("Predicate:parseTerm:>=:a=" + a);
                        //log.info("Predicate:parseTerm:>=:b=" + b);
                        //log.info("Predicate:parseTerm:>=:y.eval()=" + y.eval());
                    } else if ((chHghLvl == '>') && (chNextHghLvl != '=')) { // LessThanOREqualTo
                        chHghLvl = chNextHghLvl;
                        posHghLvl++;
                        Double a = x;
                        Double b = parseBasePredicate();
                        final String Temp = y.evalPredicate();
                        if (TempInit == false) {
                            //y = (() -> Temp && (a > b));
                            if ((Temp.equalsIgnoreCase("true")) && (a.doubleValue() > b.doubleValue())) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        } else {
                            if (a.doubleValue() > b.doubleValue()) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                            //y = (() -> (a > b));
                        }
                        TempInit = false;
                        x = b;
                        //log.info("Predicate:parseTerm:>:a=" + a);
                        //log.info("Predicate:parseTerm:>:b=" + b);
                        //log.info("Predicate:parseTerm:>:y.eval()=" + y.eval());
                    } else if ((chHghLvl == '=')) { // EqualTo
                        Double a = x;
                        Double b = parseBasePredicate();
                        final String Temp = y.evalPredicate();
                        if (TempInit == false) {
                            //y = (() -> Temp && (a == b));
                            if ((Temp.equalsIgnoreCase("true")) && (a.doubleValue() == b.doubleValue())) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }

                        } else {
                            //y = (() -> (a == b));
                            if (a.doubleValue() == b.doubleValue()) {
                                y = (() -> "true");
                            } else {
                                y = (() -> "false");
                            }
                        }
                        TempInit = false;
                        x = b;
                        //log.info("Predicate:parseTerm:==:a=" + a);
                        //log.info("Predicate:parseTerm:==:b=" + b);
                        //log.info("Predicate:parseTerm:==:y.eval()=" + y.eval());
                    } else {
                        //log.info("Predicate:parseTerm:Return Value:y.eval()=" + y.eval());
                        return y;
                    }
                }
            }

            Double parseBasePredicate() {
                int startPos = posHghLvl;
                Double x;
                if (chHghLvl == '(') { //parentheses
                    nextStrChar();
                    startPos = posHghLvl;
                    //log.info("Predicate:parseBasePredicate:(:ch=" + (char) ch + " chNext=" + (char) chNext);
                    if (chHghLvl == '!') {
                        NegateGotFlag = true;
                    }
                    x = parseBasePredicate();
                    return x;
                }
                Double xx = 1.0;
                if (IsNumber(chHghLvl)) { //numbers
                    while (IsNumber(chHghLvl)) {
                        nextStrChar();
                    }

                    if ((str != null) && (!str.equalsIgnoreCase("")) && (!str.equalsIgnoreCase("NaN"))) {
                        xx = Double.parseDouble(str.substring(startPos, posHghLvl));
                    }
                    //log.info("Predicate:parseBasePredicate:Number:ch=" + (char) ch + " chNext=" + (char) chNext + " xx=" + xx);
                    return xx;
                }
                if (chHghLvl == '=') {
                    //nextStrChar();
                }
                if ((chHghLvl == ')') && (posHghLvl == (str.length() - 1))) {
                    chHghLvl = -1;
                    posHghLvl++;
                } else if (chHghLvl == ')') {
                    nextStrChar();
                }
                Double xx2 = 1.0;
                startPos = posHghLvl;
                if (IsVariable(chHghLvl)) {//functions & variables
                    while (IsVariable(chHghLvl)) {
                        nextStrChar();
                    }
                    String name = str.substring(startPos, posHghLvl).trim();
                    if ((name != null) && (!name.equalsIgnoreCase("")) && (!name.equalsIgnoreCase("NaN")) && (variables.get(name) != null)) {
                        xx2 = variables.get(name);
                    }
                    //log.info("Predicate:parseBasePredicate:Variable:ch=" + (char) ch + " chNext=" + (char) chNext + " xx2=" + xx2 + " name=" + name);
                    if (chHghLvl == '=') {
                        //nextStrChar();
                    }
                    if ((chHghLvl == ')') && (posHghLvl == (str.length() - 1))) {
                        chHghLvl = -1;
                        posHghLvl++;
                    } else if (chHghLvl == ')') {
                        nextStrChar();
                    }
                    return xx2;
                } else if ((IsLogicalOperator(chHghLvl)) && (IsLogicalOperator(chNextHghLvl))) {
                    nextStrChar();
                    nextStrChar();
                    xx = parseBasePredicate();
                    return xx;
                } else if ((IsLogicalOperator(chHghLvl))) {
                    nextStrChar();
                    xx = parseBasePredicate();
                    return xx;
                } else {
                    if ((startPos < str.length()) && (startPos < posHghLvl)) {
                        throw new RuntimeException("Predicate:parseBasePredicate:Unexpected: " + (char) chHghLvl + " int=" + (int) chHghLvl + " Cannot Format:" + str.substring(posHghLvl, str.length()));
                    } else if (chHghLvl != -1) {
                        throw new RuntimeException("Predicate:parseBasePredicate:Unexpected: " + (char) chHghLvl + " int=" + (int) chHghLvl + " Cannot Format:" + str.substring(posHghLvl, str.length()));
                    }
                    return xx;
                }
            }
        }.parsePredicate();
    }
}
