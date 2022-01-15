package GraphManipulation.utils;

import GraphManipulation.DirectedEdge;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandlerGraph {
    public static List<Long> read_nodes_txt(String filename) throws FileNotFoundException {
        Scanner nodesFile = new Scanner(new File(filename));
        List<Long> nodesIds = new ArrayList<>();

        while(nodesFile.hasNextLong())
        {
            nodesIds.add(nodesFile.nextLong());
        }
        nodesFile.close();

        return nodesIds;
    }

    public static List<DirectedEdge> read_edges_csv(String filename){
        List<DirectedEdge> edges = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            int line = 0;
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                if(line != 0) {
                    //System.out.println("FileHandler Cost 1: " + Double.valueOf(lineInArray[2]) + " Cost 2: " + Double.valueOf(lineInArray[3]));
                    edges.add(new DirectedEdge(
                            Double.valueOf(lineInArray[2]),
                            Double.valueOf(lineInArray[3]),
                            Long.valueOf(lineInArray[0]),
                            Long.valueOf(lineInArray[1]))
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
        return edges;
    }
}
