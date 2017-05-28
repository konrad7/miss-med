package pl.edu.agh.miss.med;

import org.apache.commons.lang3.ArrayUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final String CATALOG = "src/main/resources/";
    public static final String RESULT_CATALOG = "results/";
    public static final String RESULT_FILE = "result";
    public static final String FILE_EXT = ".txt";

    private static final String IN_FILE = "25in_IKr_iv_padelall_pIC_no";
    private static final String TEST_FILE = "t-25in_IKr_iv_padelall_pIC_no";

    private static final int EQUATION_NO = 4;
    private static final int FILE_NO = 1;
    private static final int PROGRAM_RUNS = 5;
    private static final int ITERATIONS = 80;
    private static final int STEP = 1;
    private static final int POPULATION_NO = 15;
    private static final int MUTATIONS_NO = 200;


    public static void main(String[] args) {
        double[][] inMatrix = readCSV(CATALOG + IN_FILE + FILE_NO + FILE_EXT);
        Map<Integer, ArrayList<Double>> fitness_errors = new HashMap<>();
        Map<Integer, Double> mean_errors = new HashMap<>();
        Map<Integer, Double> std_dev_map = new HashMap<>();

        for(int i = 1; i <= ITERATIONS; i += STEP){
            fitness_errors.put(i, new ArrayList<>());
        }

        for(int run_no = 1; run_no <= PROGRAM_RUNS; run_no++) {
            List<Solution> solutions = new ArrayList<>();
            for (int i = 0; i < POPULATION_NO; i++) {
                solutions.add(new Solution(EQUATION_NO, inMatrix));
            }

            for (int i = 1; i <= ITERATIONS; i++) {
                //solutions.addAll(mutation(inMatrix, solutions));
                solutions = mutation_second_strategy(inMatrix, solutions);
                Collections.sort(solutions);
                if (solutions.size() > POPULATION_NO) {
                    solutions = solutions.subList(0, POPULATION_NO);
                }

                Solution bestSolution = solutions.get(0);
                if(STEP == 1 || i % STEP == 1 ) {
                    fitness_errors.get(i).add(bestSolution.getFitness());
                }
            }
            /*System.out.println();

            Solution bestSolution = solutions.get(0);
            double[][] testMatrix = readCSV(CATALOG + TEST_FILE + FILE_NO + FILE_EXT);

            System.out.println("Observed\tPredicted");
            for (double[] test : testMatrix) {
                System.out.println(test[test.length - 1] + "\t\t" + Equation.calculateFirstEquation(test, bestSolution.getParams()));
            }*/
            calculate_means_and_std(fitness_errors, mean_errors, std_dev_map);
        }

//        for (int key: fitness_errors.keySet()) {
//            System.out.print(key + ": ");
//            for(double error: fitness_errors.get(key)){
//                System.out.print(error + " ");
//            }
//            System.out.println();
//        }
//        for (int key: mean_errors.keySet()) {
//            System.out.println(key + ": " + mean_errors.get(key));
//        }
//        for (int key: std_dev_map.keySet()) {
//            System.out.println(key + ": " + std_dev_map.get(key));
//        }

        saveResults(mean_errors, std_dev_map);
    }

    private static void saveResults(Map<Integer, Double> mean_errors, Map<Integer, Double> std_dev_map) {
        String title = "EQUATION_NO=" + EQUATION_NO + " FILE_NO=" + FILE_NO + " POPULATION_NO=" + POPULATION_NO
                + " MUTATIONS_NO=" + MUTATIONS_NO + " ITERATIONS=" + ITERATIONS + " PROGRAM_RUNS=" + PROGRAM_RUNS
                + " STEP=" + STEP;

        String filename = RESULT_FILE + "_" + title.replace(" ", "-") + "_"
                + sdf.format(Instant.now().toEpochMilli());

        Path path = Paths.get(CATALOG + RESULT_CATALOG + filename + FILE_EXT);

        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            writer.write(title + "\n");
            for (Map.Entry<Integer, Double> entry : mean_errors.entrySet()) {
                String iteration = String.valueOf(entry.getKey());
                String mean = String.valueOf(entry.getValue());
                String std_dev = String.valueOf(std_dev_map.get(entry.getKey()));
                writer.write(iteration + "\t" + mean + "\t" + std_dev + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculate_means_and_std(Map<Integer, ArrayList<Double>> fitness_errors, Map<Integer, Double> mean_errors, Map<Integer, Double> std_dev_map) {
        double mean, std_dev;
        for (int key: fitness_errors.keySet()) {
            mean = 0;
            std_dev = 0;
            for(double error: fitness_errors.get(key)){
                mean += error;
            }
            mean /= fitness_errors.get(key).size();
            mean_errors.put(key, mean);
            for(double error: fitness_errors.get(key)){
                std_dev += Math.pow(error-mean,2);
            }
            std_dev /= fitness_errors.get(key).size()-1;
            std_dev = Math.sqrt(std_dev);
            std_dev_map.put(key, std_dev);
        }
    }

    // for MUTATIONS_NO << POPULATION_NO
    private static List<Solution> mutation(double[][] inMatrix, List<Solution> solutions) {
        Random random = new Random();
        Set<Solution> solutionsSet = new HashSet<>();

        while (solutionsSet.size() < MUTATIONS_NO) {
            solutionsSet.add(solutions.get(random.nextInt(POPULATION_NO)));
        }

        List<Solution> mutatedSolutions = new ArrayList<>();
        for (Solution solution : solutionsSet) {
            List<Param> mutatedParams = new ArrayList<>();
            List<Param> params = solution.getParams();
            for (int i = 0; i < Equation.getNumberOfParameters(EQUATION_NO); i++) {
                Param param = params.get(i);
                double newValue = param.getValue() + random.nextGaussian() * param.getS();
                while (Math.abs(newValue) > 10.0) {
                    newValue = param.getValue() + random.nextGaussian() * param.getS();
                }
                double newS = param.getS() * Math.pow(Math.E, random.nextGaussian());
                mutatedParams.add(new Param(newValue, newS));
            }
            mutatedSolutions.add(new Solution(EQUATION_NO, inMatrix, mutatedParams));
        }

        return mutatedSolutions;
    }

    private static List<Solution> mutation_second_strategy(double[][] inMatrix, List<Solution> solutions) {
        Random random = new Random();
        Solution solution;
        List<Solution> mutatedSolutions = new ArrayList<>();

        for (int k = 0; k < MUTATIONS_NO; k++) {
            solution = solutions.get(random.nextInt(POPULATION_NO));
            List<Param> mutatedParams = new ArrayList<>();
            List<Param> params = solution.getParams();
            for (int i = 0; i < Equation.getNumberOfParameters(EQUATION_NO); i++) {
                Param param = params.get(i);
                double newValue = param.getValue() + random.nextGaussian() * param.getS();
                while (Math.abs(newValue) > 1000.0) {
                    newValue = param.getValue() + random.nextGaussian() * param.getS();
                }
                double newS = param.getS() * Math.pow(Math.E, random.nextGaussian());
                mutatedParams.add(new Param(newValue, newS));
            }
            mutatedSolutions.add(new Solution(EQUATION_NO, inMatrix, mutatedParams));
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
