package pl.edu.agh.miss.med;

import java.util.List;

public class Equation {

    public static final int NO_PARAMETERS_FIRST_EQUATION = 2;
    public static final int NO_PARAMETERS_SECOND_EQUATION = 4;
    public static final int NO_PARAMETERS_THIRD_EQUATION = 12;
    public static final int NO_PARAMETERS_FORTH_EQUATION = 10;
    public static final int NO_PARAMETERS_FIFTH_EQUATION = 9;
    public static final int NO_PARAMETERS_SIXTH_EQUATION = 7;

    private static double calculateFirstEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();

        return (in[5]-in[22]+Math.pow(in[14], in[8])*(in[23]/in[11])*(Math.exp(in[14])
                +(Math.log(in[1])+in[24])-(in[17]/in[23]))
                +Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.log(in[0])))))
                /((c1-in[6]*in[8])/(in[13]*Math.log(in[23]))))
                /(Math.pow(c2, in[13])+(in[12]+Math.log(in[17])*in[9]+in[13]));
    }

    private static double calculateSecondEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();
        double c3 = parameters.get(2).getValue();
        double c4 = parameters.get(3).getValue();

        return Math.log(Math.log(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.exp(Math.sqrt(Math.sqrt(
               Math.exp(Math.sqrt(Math.sqrt(Math.exp(in[1]+Math.exp(in[14])+(Math.log(in[10])-c1*Math.exp((in[13]
               +in[9])*((in[24]+in[9])*(in[11]+(in[24]+in[5])))))))+Math.sqrt(Math.sqrt(Math.sqrt(Math.exp(in[1]
               +in[6]*(-c2*in[8])+(Math.log(Math.log(in[0]))+(in[22]+c3)+in[9]))))))+(Math.sqrt(in[13])
               +Math.log(Math.sqrt(Math.exp(Math.exp(in[11]*in[5]*Math.exp((-c4*in[8]*in[6]+in[1]
               +Math.exp(Math.log(Math.log(Math.log(Math.sqrt(in[13]+in[23]))))*in[5])*(in[5]*Math.exp(in[24]+in[10])))
               *(in[5]+in[11]))+Math.sqrt(Math.sqrt(Math.exp(in[0]))))+in[24]+Math.sqrt(Math.sqrt(Math.exp(Math.log(
               Math.sqrt(Math.sqrt(Math.exp(Math.sqrt(in[23])))))+in[8]*in[5]+in[1]+Math.exp(in[16])*(in[3]*in[22]
               *((Math.log(in[13])+in[5])*in[10]*in[10]))*Math.exp(Math.log(in[24])*(Math.sqrt(in[18])
               +in[5]))))))))))))))))))));
    }

    private static double calculateThirdEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).absValue();
        double c2 = parameters.get(1).getValue();
        double c3 = parameters.get(2).getValue();
        double c4 = parameters.get(3).getValue();
        double c5 = parameters.get(4).getValue();
        double c6 = parameters.get(5).getValue();
        double c7 = parameters.get(6).getValue();
        double c8 = parameters.get(7).getValue();
        double c9 = parameters.get(8).getValue();
        double c10 = parameters.get(9).absValue();
        double c11 = parameters.get(10).getValue();
        double c12 = parameters.get(11).getValue();

        return Math.log(Math.sqrt(Math.sqrt(Math.exp(Math.log(Math.log(Math.sqrt(c1)+Math.exp(Math.sqrt(Math.exp(
               Math.sqrt(in[11])-c2))*(-c3*Math.sqrt(Math.sqrt(Math.exp(in[8]+-c4+in[13]))))+(-c5+(in[9]+in[13]
               +(-c6+Math.sqrt(in[12])))+Math.log(Math.log(in[0]))+(-c7+in[13])))))+(-c8+Math.log(Math.exp(Math.exp(
               Math.sqrt(Math.sqrt(in[23]))+-c9+in[14]))+Math.sqrt(Math.sqrt(Math.exp(in[24]+Math.log(in[24])
               +Math.sqrt(c10)+Math.sqrt(Math.sqrt(in[23]))+in[24]+Math.exp(in[1])+Math.sqrt(Math.sqrt(Math.exp(
               Math.sqrt(in[11]))))*-c11+in[8]*(-c12*in[24])+Math.sqrt(in[23]))))))))));
    }

    private static double calculateForthEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();
        double c3 = parameters.get(2).getValue();
        double c4 = parameters.get(3).getValue();
        double c5 = parameters.get(4).getValue();
        double c6 = parameters.get(5).getValue();
        double c7 = parameters.get(6).getValue();
        double c8 = parameters.get(7).getValue();
        double c9 = parameters.get(8).getValue();
        double c10 = parameters.get(9).getValue();

        return Math.log(Math.log(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.exp(Math.sqrt(Math.sqrt(Math.exp(
               Math.log(Math.sqrt(Math.sqrt(Math.exp(Math.sqrt(in[23])+(in[8]*(-c1*in[24])+(Math.log(in[13]+Math.exp(
               -c2*Math.sqrt(in[11])))+(Math.exp(in[1])+(in[24]+(in[24]+in[24]))))))))+Math.exp(Math.exp(in[14]+(-c3
               +Math.sqrt(Math.sqrt(in[23]))))))+Math.log(Math.log(Math.exp(in[13]+-c4+(Math.sqrt(in[11])+-c5
               +(Math.log(Math.log(in[0]))+(-c6+in[13])))+Math.exp(Math.sqrt(in[11])+-c7)*(Math.sqrt(Math.sqrt(
               Math.exp(-c8+in[8]+in[13])))*-c9))+Math.sqrt(Math.sqrt(c10))))))))))))));
    }

    private static double calculateFifthEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();
        double c3 = parameters.get(2).getValue();
        double c4 = parameters.get(3).getValue();
        double c5 = parameters.get(4).getValue();
        double c6 = parameters.get(5).getValue();
        double c7 = parameters.get(6).getValue();
        double c8 = parameters.get(7).getValue();
        double c9 = parameters.get(8).getValue();

        return Math.log(Math.log(Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.exp(Math.sqrt(Math.sqrt(Math.exp(
               Math.log(Math.log(Math.exp(-c1*Math.sqrt(Math.sqrt(Math.exp(-c2+in[13])))*Math.exp(-c3+Math.sqrt(in[11]))
               +(Math.log(Math.log(in[0]))+(in[13]+-c4)+(-c5+in[13])))+Math.sqrt(Math.sqrt(c6))))+Math.log(Math.sqrt(
               Math.sqrt(Math.exp(Math.sqrt(in[23])+(in[8]*(-c7*in[24])+(Math.log(in[13]+Math.exp(-c8*in[22]))
               +(Math.exp(in[1])+(in[24]+(in[24]+in[24]))))))))+Math.exp(Math.exp(in[14]+(-c9+Math.sqrt(
               Math.sqrt(in[23]))))))))))))))));
    }

    private static double calculateSixthEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();
        double c3 = parameters.get(2).getValue();
        double c4 = parameters.get(3).getValue();
        double c5 = parameters.get(4).getValue();
        double c6 = parameters.get(5).getValue();
        double c7 = parameters.get(6).getValue();

        return Math.sqrt(in[24])-(Math.sqrt(in[0])-(Math.sqrt(in[13])-Math.sqrt(Math.sqrt(in[13]))-c1)-c2)
               -(Math.exp(Math.exp(c3-in[1]-(in[0]-Math.exp(c4))))-Math.exp(in[14]-Math.exp(Math.exp(in[9]))
               -(Math.exp(Math.exp(in[21])-Math.exp(in[3])-(Math.exp(in[24]-in[13])-in[13])-Math.exp(in[13]-in[24])
               -(Math.exp(Math.sqrt(in[0]))-in[13]))-Math.exp(Math.exp(Math.exp(c5))-in[21]-(Math.sqrt(Math.exp(in[14]))
               -Math.sqrt(Math.sqrt(Math.exp(in[9])))))))-(Math.sqrt(Math.sqrt(c6))-in[8])+c7);
    }

    public static double calculateEquation(int equationNumber, double[] in, List<Param> params) {
        switch (equationNumber) {
            case 1: return calculateFirstEquation(in, params);
            case 2: return calculateSecondEquation(in, params);
            case 3: return calculateThirdEquation(in, params);
            case 4: return calculateForthEquation(in, params);
            case 5: return calculateFifthEquation(in, params);
            case 6: return calculateSixthEquation(in, params);
            default: throw new IllegalArgumentException("Equation number + " + equationNumber + " does not exists");
        }
    }

    public static int getNumberOfParameters(int equationNumber) {
        switch (equationNumber) {
            case 1: return NO_PARAMETERS_FIRST_EQUATION;
            case 2: return NO_PARAMETERS_SECOND_EQUATION;
            case 3: return NO_PARAMETERS_THIRD_EQUATION;
            case 4: return NO_PARAMETERS_FORTH_EQUATION;
            case 5: return NO_PARAMETERS_FIFTH_EQUATION;
            case 6: return NO_PARAMETERS_SIXTH_EQUATION;
            default: throw new IllegalArgumentException("Equation number + " + equationNumber + " does not exists");
        }
    }
}
