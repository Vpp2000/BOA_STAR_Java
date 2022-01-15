package ExperimentsUtils;

import BOAStarSolver.BOASolver;
import Entities.Experiment;
import Entities.Result;
import GraphManipulation.EdgeWeightedDigraph;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ExperimentUtils {
    public static void writeToCsv(List<Result> resultsObtained, String filename) throws IOException {
        String working_directory = "src/main/resources/results/include_dict_expanded_modified";
        PrintWriter writer = new PrintWriter(working_directory + "/" + filename + ".csv");

        writer.println(Result.headerForCsv);

        for (Result result : resultsObtained) {
            writer.println(result.toString());
        }

        writer.close();
    }

    public static List<Experiment> read_experiments_from_csv(String pathFile){
        List<Experiment> experimentList = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(pathFile))) {
            int line = 0;
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if(line != 0) {
                    //System.out.println("FileHandler Cost 1: " + Double.valueOf(lineInArray[2]) + " Cost 2: " + Double.valueOf(lineInArray[3]));
                    experimentList.add(new Experiment(Long.valueOf(lineInArray[0]),
                            Long.valueOf(lineInArray[1]),
                            Integer.valueOf(lineInArray[2]))
                    );
                }

                line++;
            }

            // System.out.println("lines: " + line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        return experimentList;
    }

    public static void run_example() throws FileNotFoundException {
        String workingDirectory = "src/main/resources";


        String nodes_file = workingDirectory + "/sf_nodes_ids.txt";
        String edges_file = workingDirectory + "/sf_edges_data_7131.csv";
        long source = 65301575;
        long target = 65357033;


        /*
        String nodes_file = workingDirectory + "/toy_nodes.txt";
        String edges_file = workingDirectory + "/toy_edges.csv";
        long source = 0;
        long target = 4;
         */

        /*
        String nodes_file = workingDirectory + "/chicago_nodes.txt";
        String edges_file = workingDirectory + "/chicago_edges_data_6454.csv";
        long source = 3409732496L;
        long target = 2099948750L;
        */

        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(nodes_file, edges_file);

        BOASolver boaSolver = new BOASolver(graph);

        System.out.println(boaSolver.solve(new double[]{0.0, 0.0}, source, target, 7, true));
    }

    public static void run_all_queries_by_country(String fileName, EdgeWeightedDigraph graph) throws IOException {
        String workingDirectory = "src/main/resources/experiments";
        BOASolver boaSolver = new BOASolver(graph);

        double[] epsValues = {0.0, 0.025, 0.05, 0.075, 0.1, 0.125};
        //double[] epsValues = {0.025};

        List<Experiment> experimentList = read_experiments_from_csv(workingDirectory + "/" + fileName + ".csv");

        List<Result> resultsObtained = new ArrayList<>();

        int exp_set = 1;

        for(double eps: epsValues){
            System.out.println("********************* Set " + exp_set + "  ********************");
            int index = 1;
            for(Experiment experiment: experimentList){
                System.out.println("Experiment " + (index++));
                resultsObtained.add
                        (boaSolver.solve(new double[]{eps, eps},
                                experiment.getSource(),
                                experiment.getDestiny(),
                                experiment.getCategory(), false)
                        );
            }

            exp_set++;
        }

        writeToCsv(resultsObtained, fileName + "_results_2800_multiple_eps_with_heuristics_nano_navidad");

    }
}
