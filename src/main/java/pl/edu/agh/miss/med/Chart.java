package pl.edu.agh.miss.med;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static pl.edu.agh.miss.med.Main.*;

public class Chart {

    private static final String CHART_FILE = "CHART";
    private static final String CHART_CATALOG = "charts/";


    public static void main(String[] args) {
//        String inputFile = "result_EQUATION_NO=1-FILE_NO=1-POPULATION_NO=15-MUTATIONS_NO=150-ITERATIONS=1000-PROGRAM_RUNS=10-STEP=1_20170522233833";
//        String inputFile = "result_EQUATION_NO=2-FILE_NO=1-POPULATION_NO=15-MUTATIONS_NO=150-ITERATIONS=200-PROGRAM_RUNS=10-STEP=1_20170522235057";
//        String inputFile = "result_EQUATION_NO=2-FILE_NO=1-POPULATION_NO=15-MUTATIONS_NO=400-ITERATIONS=100-PROGRAM_RUNS=10-STEP=1_20170523005602";
        String inputFile = "result_EQUATION_NO=3-FILE_NO=1-POPULATION_NO=15-MUTATIONS_NO=200-ITERATIONS=80-PROGRAM_RUNS=5-STEP=1_20170523085411";

        Map<Integer, Double> mean_errors = new HashMap<>();
        Map<Integer, Double> std_dev_map = new HashMap<>();
        String title = loadData(CATALOG + RESULT_CATALOG + inputFile + FILE_EXT, mean_errors, std_dev_map);

        displayChart(mean_errors, std_dev_map, title, 27, 40);
    }

    private static String loadData(String fileName, Map<Integer, Double> mean_errors, Map<Integer, Double> std_dev_map) {
        String title = "";
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {
            title = br.lines().findFirst().orElse("");
            br.lines().skip(1).forEach(line -> {
                List<String> list = Arrays.stream(line.split("\t")).collect(Collectors.toList());
                for (int i = 0; i < 3; i++) {
                    Integer key = Integer.parseInt(list.get(0));
                    Double mean = Double.parseDouble(list.get(1));
                    Double std_dev = Double.parseDouble(list.get(2));
                    mean_errors.put(key, mean);
                    std_dev_map.put(key, std_dev);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return title;
    }

    public static void displayChart(Map<Integer, Double> mean_errors, Map<Integer, Double> std_dev_map, String title, int iterMin, int iterMax) {
        XYSeriesCollection seriesCollection = new XYSeriesCollection();
        XYSeries meanSeries = new XYSeries("mean");
        XYSeries stdDev1Series = new XYSeries("std-dev +");
        XYSeries stdDev2Series = new XYSeries("std-dev -");

        double minVal = Double.MAX_VALUE;
        double maxVal = Double.MIN_VALUE;

        for (Map.Entry<Integer, Double> entry : mean_errors.entrySet()) {
            double iterNo = entry.getKey();
            if (iterNo >= iterMin && iterNo <= iterMax) {
                double mean = entry.getValue();
                double std_dev = std_dev_map.get(entry.getKey());
                meanSeries.add(iterNo, entry.getValue());
                stdDev1Series.add(iterNo, mean + std_dev);
                stdDev2Series.add(iterNo, mean - std_dev);

                minVal = Math.min(minVal, mean - std_dev);
                maxVal = Math.max(maxVal, mean + std_dev);
            }
        }

        seriesCollection.addSeries(meanSeries);
        seriesCollection.addSeries(stdDev1Series);
        seriesCollection.addSeries(stdDev2Series);

        JFreeChart chart = ChartFactory.createXYLineChart(
                title,"Iteration (" + iterMin + ", " + iterMax + ")",
                "Fitness",
                seriesCollection, PlotOrientation.VERTICAL,
                true,true,false);

        XYPlot plot = chart.getXYPlot();
        ValueAxis iterationAxis = plot.getDomainAxis();
        iterationAxis.setRange(iterMin, iterMax);
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setRange(minVal, maxVal);

        String filename = CHART_FILE + "_" + title.replace(" ", "-") + "_RANGE=" + iterMin + "-" + iterMax + "_"
                + sdf.format(Instant.now().toEpochMilli());

        File lineChart = new File( CATALOG + CHART_CATALOG + filename + ".jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG(lineChart, chart, 1200 ,600);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
