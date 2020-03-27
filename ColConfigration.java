/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DOEMatrixGen;

import org.apache.log4j.Logger;

/**
 *
 * @author Administrator
 */
public class ColConfigration {
    public static Logger log = Logger.getLogger(ColConfigration.class.getName());
    String Name;//Name of the Column(Display)
    int ColNumber=0;//Which Column this data belongs
    int ColType=0; // 0 -Not configured, 1 -Analog, 2 - Digital , 3 - Cyclic , 4 - Time , 5 - Normal
    
    //High Level Equation
    String ColEquations="";// "" -Not configured, [0] contains F(x), 1 contains F'(x), 2 contains F""(x) etc 
    int HighestDerivative=-1;// -1 -Not configured, 0 Unused, Order of the Derivative (1st,2nd etc)
    int HighestIntegral=-1;// -1 -Not configured, 0 Unused, Order of the Derivative (1st,2nd etc)
    int NumDenUsed=0;// -1 -Not configured, 0 -Unused, 1 -Numerator Used , 2 -Deniminator Used , 3 - Both Used
    String ColEqnIndependantVar="";// "" -Not configured, x=x is the Independant Variable
    String ColEqnDependantVar="";// "" -Not configured, y=y is the Dependant Variable
    //Numerator Equation Details
    String ColNumEquations="";// "" -Not configured,
    int HighestNumDerivative=-1;// -1 -Not configured, 0 Unused, Order of the Derivative (1st,2nd etc)
    String ColInternalEqnIndependantVarNum="";// "" -Not configured, x=x is the Independant Variable
    String ColInternalEqnDependantVarNum="";// "" -Not configured, y=y is the Dependant Variable

    //Numerator Section
    String ColInternalEqnNum="";// "" -Not configured,
    int ColEqnIntrnTypeNum=0; // 0 -Not configured, 1 -Schwartz, 2 - Fast Growing Heirarchy , 3 - Steady State Sine 
    String ColEqnIntrnVarNum="";// "" -Not configured, :Any Other String of comma seperated variables 
    
    // Denominator Equation Details
    String ColDenEquations="";// "" -Not configured,
    int HighestDenDerivative=-1;// -1 -Not configured, 0 Unused, Order of the Derivative (1st,2nd etc)
     
    //Denominator Section
    String ColInternalEqnDen="";// "" -Not configured,
    int ColEqnIntrnTypeDen=0; // 0 -Not configured, 1 -Schwartz, 2 - Fast Growing Heirarchy , 3 - Steady State Sine 
    String ColEqnIntrnVarDen="";// "" -Not configured, :Any Other String of comma seperated variables 
    String ColInternalEqnIndependantVarDen="";// "" -Not configured, x=x is the Independant Variable
    String ColInternalEqnDependantVarDen="";// "" -Not configured, y=y is the Dependant Variable

    
    int ColEqnType=0; // 0 -Not configured, 1 -Schwartz, 2 - Fast Growing Heirarchy , 3 - Steady State Sine 
    String ColOutputOperand="";// "" -Not configured, * -Multiply Numerator, / - Divide Denominator , # - No Effect
    int ColOutputCol=-1;// -1 -Not configured, Any Other positive number :Column of ResultArray
    int ColProbDenFunc=0; // 0 -Not configured, 1 -Gaussian, 2 - Uniform DIstribution(Computer) , 3 - Weibull 4 - Poisson 5 - Binomial
    int ColOrderApplyEqn=-1;// -1 -Not configured, Any Other positive number :Order of Application
    String ColEqnVariables="";// "" -Not configured, :Any Other String of comma seperated variables 
    
    int ColEqnInternalType=0;// Type of Waveform:0 -Not configured, 1 -Numerator only, 2 - Denominator only , 3 - Num & Denominator 
    
    MathsContxtLAv0_1_Prod ColEqnDetails;//High Level Equation Applicable
    MathsContxtLAv0_1_Prod ColNumEqnDetails;
    MathsContxtLAv0_1_Prod ColDenEqnDetails;
    
    // Store of Fx,F'x,F''x etc
    String[] ColEqnDetailsStr;//Store of Numerator F'x,F''x etc
    String[] ColNumEqnDetailsStr;//Store of Numerator F'x,F''x etc
    String[] ColDenEqnDetailsStr;//Store of Denominator F'x,F''x etc
    String[] ColEqnDetailsIntegrStr;//Store of Numerator F'x,F''x etc
    
    ColConfigration(int ObjectNum){
        
    }
    
    
}
