package pl.edu.agh.miss.med;


import java.util.ArrayList;
import java.util.List;

public class Solution implements Comparable<Solution>{

    private double[][] inMatrix;
    private List<Param> params;
    private double fitness;

    public Solution(double[][] inMatrix, int numberOfParams) {
        this.inMatrix = inMatrix;
        this.params = new ArrayList<>();
        for (int i = 0; i < numberOfParams; i++) {
            params.add(new Param());
        }
        this.calculateFitness();
    }

    public Solution(double[][] inMatrix, List<Param> params) {
        this.inMatrix = inMatrix;
        this.params = params;
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
        for (double[] in : inMatrix) {
            deviationSum += Math.abs(in[in.length-1] - Equation.calculateFirstEquation(in, this.params));
        }
        this.fitness = deviationSum/inMatrix.length;
    }

    @Override
    public int compareTo(Solution sol) {
        if(this.getFitness() - sol.getFitness() > 0)
            return 1;
        else if(this.getFitness() - sol.getFitness() < 0)
            return -1;
        return 0;
    }

}

