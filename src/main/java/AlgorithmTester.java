import BOAStarSolver.BOASolver;
import Entities.Experiment;
import ExperimentsUtils.ExperimentUtils;
import GraphManipulation.DirectedEdge;
import GraphManipulation.EdgeWeightedDigraph;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class AlgorithmTester {


    public static void main(String[] args) throws IOException {
        //
        boolean runExample = true;

        if(runExample){
            ExperimentUtils.run_example();
        }

        else{
            String workingDirectory = "src/main/resources";

            String nodes_file = workingDirectory + "/chicago_nodes.txt";
            //String nodes_file = workingDirectory + "/sf_nodes_ids.txt";
            //String nodes_file = workingDirectory + "/philadelphia_nodes_ids.txt";

            String edges_file = workingDirectory + "/chicago_edges_data_6454.csv";
            //String edges_file = workingDirectory + "/sf_edges_data_7131.csv";
            //String edges_file = workingDirectory + "/philadelphia_edges_data_3651.csv";

            EdgeWeightedDigraph graph = new EdgeWeightedDigraph(nodes_file, edges_file);

            ExperimentUtils.run_all_queries_by_country("chicago_epsg_experimentos_10_and_15_km", graph);
        }

    }
}
