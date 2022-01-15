package GraphManipulation;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import GraphManipulation.utils.FileHandlerGraph;

public class EdgeWeightedDigraph {
    private Map<Long, List<DirectedEdge>> adj;
    private int edges_quantity = 0;


    public EdgeWeightedDigraph(){
        this.adj = new HashMap<>();
    }

    public EdgeWeightedDigraph(String nodes_file_txt, String edges_file_csv) throws FileNotFoundException {
        this.adj = new HashMap<>();

        List<Long> nodesIds = FileHandlerGraph.read_nodes_txt(nodes_file_txt);
        for(Long id: nodesIds){
            addVertex(id);
        }

        List<DirectedEdge> edges = FileHandlerGraph.read_edges_csv(edges_file_csv);
        for(DirectedEdge edge: edges){
            addEdge(edge);
        }

    }

    private void addVertex(long id_vertex){
        if (this.adj.get(id_vertex) == null) {
            this.adj.put(id_vertex, new ArrayList<>());
        }
    }

    public int V(){
        return this.adj.size();
    }

    public int E(){
        return this.edges_quantity;
    }

    public List<Long> getVertexes(){
        return new ArrayList<>(this.adj.keySet());
    }

    private void addEdge(DirectedEdge edge){
        if (this.adj.get(edge.from()) == null) {
            this.adj.put(edge.from(), new ArrayList<>());
        }


        this.adj.get(edge.from()).add(edge);
        edges_quantity++;
    }

    public List<DirectedEdge> vertexEdgesOut(Long vertex_id){
        return this.adj.get(vertex_id);
    }

    public void print_graph(){
        for (Map.Entry<Long, List<DirectedEdge>> entry : this.adj.entrySet()) {
            System.out.print("Vertex " + entry.getKey() + " [");

            for(DirectedEdge edge : this.adj.get(entry.getKey())){
                System.out.print(edge.to() + " ");
            }

            System.out.println("]");
        }
    }

    public EdgeWeightedDigraph reverse(){
        EdgeWeightedDigraph newGraph = new EdgeWeightedDigraph();

        for(Long idVertex: getVertexes()){
            newGraph.addVertex(idVertex);
        }

        for (Map.Entry<Long, List<DirectedEdge>> entry : this.adj.entrySet()) {
            long id_vertex = entry.getKey();
            for(DirectedEdge edge : this.adj.get(id_vertex)){
                newGraph.addEdge(edge.reverseEdge());
            }
        }

        return newGraph;
    }
}
