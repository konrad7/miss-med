package pl.edu.agh.miss.med;

import java.util.Random;

public class Param {

    private double value;
    private double s;

    public Param() {
        Random random = new Random();
        this.value = random.nextGaussian()/10.0;
        this.s = Math.abs(random.nextGaussian()/10.0);
    }

    public Param(double value, double s) {
        this.value = value;
        this.s = s;
    }

    public double getValue() {
        return value;
    }

    public double absValue() {
         this.value = Math.abs(this.value);
         return this.value;
    }

    public double getS() {
        return s;
    }

}
