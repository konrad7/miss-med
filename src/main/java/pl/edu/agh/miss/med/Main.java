package pl.edu.agh.miss.med;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final String CATALOG = "src/main/resources/";

    private static final String IN_FILE = "25in_IKr_iv_padelall_pIC_no";
//    private static final String TEST_FILE = "t-25in_IKr_iv_padelall_pIC_no";
    private static final String FILE_EXT = ".txt";

    private static final int FILE_NO = 1;
    private static final int POPULATION_NO = 50;
    private static final int MUTATIONS_NO = 10;
    private static final int ITERATIONS = 50;
    private static final int PARAMETERS_NO = 2;


    public static void main(String[] args) {
        double[][] inMatrix = readCSV(CATALOG + IN_FILE + FILE_NO + FILE_EXT);

        List<Solution> solutions = new ArrayList<>();
        for (int i = 0; i < POPULATION_NO; i++) {
            solutions.add(new Solution(inMatrix, PARAMETERS_NO));
        }

        for (int i = 0; i < ITERATIONS; i++) {
            solutions.addAll(mutation(inMatrix, solutions));
            Collections.sort(solutions);
            solutions = solutions.subList(0, POPULATION_NO);

            for (Solution solution : solutions) {
                System.out.print(solution.getFitness() + " ");
            }
            System.out.println();
        }
    }

    private static List<Solution> mutation(double[][] inMatrix, List<Solution> solutions) {
        Random random = new Random();
        Set<Solution> solutionsSet = new HashSet<>();

        while(solutionsSet.size() < MUTATIONS_NO) {
            solutionsSet.add(solutions.get(random.nextInt(POPULATION_NO)));
        }

        List<Solution> mutatedSolutions = new ArrayList<>();
        for (Solution solution : solutionsSet) {
            List<Param> mutatedParams = new ArrayList<>();
            List<Param> params = solution.getParams();
            for (int i = 0; i < PARAMETERS_NO; i++) {
                Param param = params.get(i);
                double newValue = param.getValue() + random.nextGaussian() * param.getS();
                while(Math.abs(newValue) > 1.0) {
                    newValue = param.getValue() + random.nextGaussian() * param.getS();
                }
                double newS = param.getS() * Math.pow(Math.E, random.nextGaussian());
                mutatedParams.add(new Param(newValue, newS));
            }
            mutatedSolutions.add(new Solution(inMatrix, mutatedParams));
        }

        return mutatedSolutions;
    }

    private static double[][] readCSV(String fileName) {
        List<List<Double>> csvContent = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            csvContent = br.lines()
                    .map(line -> Arrays.stream(line.split("\t"))
                            .map(Double::parseDouble)
                            .collect(Collectors.toList()))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return csvContent.stream()
                .map(u -> u.toArray(new Double[0]))
                .map(ArrayUtils::toPrimitive)
                .toArray(double[][]::new);
    }

}
