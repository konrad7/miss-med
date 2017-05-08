package pl.edu.agh.miss.med;


import java.util.List;

public class Equation {

    public static double calculateFirstEquation(double[] in, List<Param> parameters) {
        double c1 = parameters.get(0).getValue();
        double c2 = parameters.get(1).getValue();

        double result = (in[5]-in[22]+Math.pow(in[14], in[8])*(in[23]/in[11])*(Math.exp(in[14])
                +(Math.log(in[1])+in[24])-(in[17]/in[23]))
                +Math.sqrt(Math.sqrt(Math.sqrt(Math.sqrt(Math.log(in[0])))))
                /((c1-in[6]*in[8])/(in[13]*Math.log(in[23]))))
                /(Math.pow(c2, in[13])+(in[12]+Math.log(in[17])*in[9]+in[13]));
        return Math.abs(in[in.length-1] - result);
    }
}
