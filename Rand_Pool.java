/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomNumber;

import static RandomNumber.UserResearchStudy.EntPool;
import static RandomNumber.UserResearchStudy.PatternLength;
import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class Rand_Pool {

    public static Logger log = Logger.getLogger(Rand_Pool.class.getName());

    XORRandom MyXOR;
    public static RandomSimpleLCG MySimple;
    public static RandomFactorialLCGDyDx MyFactorial;
    RandomSeries Series1;

    public Rand_Pool(String Type, int Index, long NoOfSlots, long ModulusEncrypt, long Xn_LCG, long Xn_LCGDyDx, long a, long c, long b, long MaxExponent) {

        if (Type.equalsIgnoreCase("RandomSimpleLCG"))
               MySimple = new RandomSimpleLCG((int) Index, NoOfSlots, ModulusEncrypt, Xn_LCG,  a, c, b, MaxExponent);
             else if (Type.equalsIgnoreCase("RandomFactorialLCGDyDx")) {
               MyFactorial = new RandomFactorialLCGDyDx((int) Index, NoOfSlots, ModulusEncrypt,  Xn_LCGDyDx, a, c, b, MaxExponent);
           } else if (Type.equalsIgnoreCase("Series")) {
               Series1 = new RandomSeries(Index, NoOfSlots);
            }else if (Type.equalsIgnoreCase("XORRandom")) {
               MyXOR = new XORRandom();
            }
        }
}
