package pl.edu.agh.miss.med;


import java.util.ArrayList;
import java.util.List;

public class Solution implements Comparable<Solution>{

    private double[][] inMatrix;
    private List<Param> params;
    private double fitness;
    private int equationNumber;

    public Solution(int equationNumber, double[][] inMatrix) {
        this.inMatrix = inMatrix;
        this.params = new ArrayList<>();
        for (int i = 0; i < Equation.getNumberOfParameters(equationNumber); i++) {
            params.add(new Param());
        }
        this.equationNumber = equationNumber;
        this.calculateFitness();
    }

    public Solution(int equationNumber, double[][] inMatrix, List<Param> params) {
        this.inMatrix = inMatrix;
        this.params = params;
        this.equationNumber = equationNumber;
        this.calculateFitness();
    }

    public List<Param> getParams() {
        return params;
    }

    public double getFitness() {
        return this.fitness;
    }

    private void calculateFitness() {
        double deviationSum = 0.0;
        for (double[] in : this.inMatrix) {
            deviationSum += Math.abs(in[in.length-1] - Equation.calculateEquation(this.equationNumber, in, this.params));
        }
        this.fitness = deviationSum/inMatrix.length;
    }

    @Override
    public int compareTo(Solution sol) {
        if(this.getFitness() - sol.getFitness() > 0.0) {
            return 1;
        }
        if(this.getFitness() - sol.getFitness() < 0.0) {
            return -1;
        }
        return 0;
    }

}

