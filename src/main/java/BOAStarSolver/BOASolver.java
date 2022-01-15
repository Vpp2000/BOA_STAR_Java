package BOAStarSolver;

import Entities.Result;
import GraphManipulation.DijkstraSP;
import GraphManipulation.DirectedEdge;
import GraphManipulation.EdgeWeightedDigraph;
import PQUtils.NodeOpen;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class BOASolver {
    private EdgeWeightedDigraph graph;
    private EdgeWeightedDigraph reverseGraph;

    public BOASolver(EdgeWeightedDigraph graph) {
        this.graph = graph;
        this.reverseGraph = graph.reverse();
    }

    public Result solve(double[] eps, long start, long goal, int category, boolean debug_flag){
        List<PathBoa> sols = new ArrayList<>();

        int EXPANDED_NODES = 0;
        int MAX_NODES_IN_OPEN = 0;


        long start_heuristic = System.nanoTime();

        DijkstraSP c1_calculator = new DijkstraSP(this.reverseGraph, goal, 1);
        DijkstraSP c2_calculator = new DijkstraSP(this.reverseGraph, goal, 2);

        long end_heuristic = System.nanoTime();

        long start_process = System.nanoTime();

        Map<Long, Double> g2_min = new HashMap<>();

        for(Long vertex_id: this.graph.getVertexes()){
            g2_min.put(vertex_id, Double.POSITIVE_INFINITY);
        }

        NodeOpen first_node = new NodeOpen(start,
                new double[]{0.0, 0.0},
                //new double[]{0.0, 0.0},
                new double[]{c1_calculator.distTo(start), c2_calculator.distTo(start)},
                null);

        Queue<NodeOpen> openList = new PriorityQueue<>();
        openList.add(first_node);

        while (!openList.isEmpty()){
            NodeOpen x = openList.poll();

            double[] x_f_value = x.getF_value();
            double[] x_g_value = x.getG_value();


            if(x_g_value[1] >= g2_min.get(x.getState()) || (1 + eps[1]) * x_f_value[1] >= g2_min.get(goal)){
                continue;
            }

            g2_min.put(x.getState(), x_g_value[1]);

            if(x.getState() == goal){
                List<Long> ids = new ArrayList<>();
                NodeOpen node_to_traverse = x;

                while(node_to_traverse.getParent() != null){
                    ids.add(node_to_traverse.getState());
                    node_to_traverse = node_to_traverse.getParent();
                }

                double[] total_sol_cost = x.getG_value();
                sols.add(new PathBoa(ids, total_sol_cost[0], total_sol_cost[1]));
                continue;
            }



            for(DirectedEdge edge_out: this.graph.vertexEdgesOut(x.getState())){
                long childId = edge_out.to();
                double c1 = edge_out.getCostByIndex(1);
                double c2 = edge_out.getCostByIndex(2);
                double[] g_child = {x_g_value[0] + c1, x_g_value[1] + c2};
                double[] f_child = {g_child[0] + c1_calculator.distTo(childId), g_child[1] + c2_calculator.distTo(childId)};

                if(g_child[1] >= g2_min.get(childId) || (1 + eps[1]) * f_child[1] >= g2_min.get(goal)){
                    if(x.getState() == childId){
                        //System.out.println(x.getState() + " and " + childId + " is a self-loop");
                    }
                    continue;
                }

                EXPANDED_NODES++;

                openList.add(new NodeOpen(childId, g_child, f_child, x));
            }

            if(openList.size() > MAX_NODES_IN_OPEN){
                MAX_NODES_IN_OPEN = openList.size();
            }
        }

        long end_process = System.nanoTime();

        if(debug_flag == true){
            System.out.println("Found " + sols.size() + " solutions and takes " + (double) (end_process - start_process) / 1_000_000_000 + " seconds");
            for(PathBoa path: sols){
                System.out.println("c1 = " + path.getC1() + " c2: " + path.getC2());
            }

            try {
                FileWriter myWriter = new FileWriter("soluciones.txt");

                for(PathBoa path: sols){
                    myWriter.write(Arrays.toString(path.getPathIds().toArray()) +"\n");
                }

                myWriter.close();
                System.out.println("Las soluciones se guardaron exitosamente.");
            } catch (IOException e) {
                System.out.println("Agg pe.");
                e.printStackTrace();
            }
        }


        return new Result(eps[0],
                category,
                start,
                goal,
                sols.size(),
                (double) (end_process - start_process) / 1_000_000_000,
                EXPANDED_NODES,
                MAX_NODES_IN_OPEN, (double) (end_heuristic - start_heuristic) / 1_000_000_000);
    }
}
