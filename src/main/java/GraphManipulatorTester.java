import GraphManipulation.DijkstraSP;
import GraphManipulation.DirectedEdge;
import GraphManipulation.EdgeWeightedDigraph;
import GraphManipulation.utils.FileHandlerGraph;
import java.io.IOException;
import java.util.List;

public class GraphManipulatorTester {
    public static void main(String[] args) throws IOException {
        String nodes_file = "./src/main/resources/sf_nodes_ids.txt";
        String edges_file = "./src/main/resources/sf_edges_data_7131.csv";

        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(nodes_file, edges_file);

        long source = 65301575;
        long target = 65357033;

        EdgeWeightedDigraph graph_reverse = graph.reverse();
        DijkstraSP distFromReverse = new DijkstraSP(graph_reverse, target, 1);

        distFromReverse.getDistTo().entrySet().forEach(entry -> {
            System.out.println("[i]" + entry.getKey() + "--->" + entry.getValue());
        });

    }
}
