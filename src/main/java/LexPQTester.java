import GraphManipulation.DirectedEdge;
import PQUtils.NodeOpen;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LexPQTester {
    public static void main(String[] args) throws FileNotFoundException {
        String pairs_filename = "./src/main/resources/order_lex_this_shit.csv";

        Queue<NodeOpen> nodesPq = new PriorityQueue<>();

        try (CSVReader reader = new CSVReader(new FileReader(pairs_filename))) {
            long line = 0;
            String[] lineInArray;
            while ((lineInArray = reader.readNext()) != null) {
                    nodesPq.add(new NodeOpen(line,
                            new double[]{Double.parseDouble(lineInArray[0]), Double.parseDouble(lineInArray[1])},
                            new double[]{Double.parseDouble(lineInArray[0]), Double.parseDouble(lineInArray[1])},
                            null));
            }

            System.out.println("lines: " + line);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

        while(!nodesPq.isEmpty()){
            System.out.println(Arrays.toString(nodesPq.poll().getF_value()));
        }

    }
}


