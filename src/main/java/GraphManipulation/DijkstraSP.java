package GraphManipulation;

import PQUtils.PQNode;
import PQUtils.PQRanked;

import java.util.HashMap;

public class DijkstraSP
{
    private HashMap<Long, Double> distTo;
    private HashMap<Long, Long> parents;
    private PQRanked<PQNode> pq;
    private int cost_index;

    public DijkstraSP(EdgeWeightedDigraph G, Long s, int cost_index)
    {
        this.cost_index = cost_index;
        parents = new HashMap<>();
        distTo = new HashMap<>();
        pq = new PQRanked<PQNode>(PQNode.class, G.V());

        for(Long vertex_id: G.getVertexes()){
            distTo.put(vertex_id, Double.POSITIVE_INFINITY);
            parents.put(vertex_id, null);
        }

        distTo.put(s, 0.0);
        pq.push(new PQNode(s, 0.0));

        while (!pq.isEmpty()) {
            relax(G, pq.pop());
        }
    }

    private void relax(EdgeWeightedDigraph G, PQNode v)
    {
        //System.out.println("Extracting v=" + v);
        for (DirectedEdge e : G.vertexEdgesOut(v.getId()))
        {
            long w = e.to();
            //System.out.println("Neighbor w: " + w + " edge: " + e);

            if (distTo.get(w) > distTo.get(v.getId()) + e.getCostByIndex(cost_index))
            {
                //System.out.println("Relaxing value....");
                distTo.put(w, distTo.get(v.getId()) + e.getCostByIndex(cost_index));

                //System.out.println("Modifying parent...");
                parents.put(w,e.from());

                if (pq.contains(new PQNode(w, distTo.get(w))))
                    pq.update(new PQNode(w, distTo.get(w)), new PQNode(w, distTo.get(v.getId()) + e.getCostByIndex(cost_index)));
                else
                    pq.push(new PQNode(w, distTo.get(w)));
            }
        }
    }


    public double distTo(long v){
        return distTo.get(v);
    }

    public boolean hasPathTo(int v){
        return distTo.get(v) < Double.POSITIVE_INFINITY;
    }

    public HashMap<Long, Double> getDistTo() {
        return distTo;
    }

    /*
    public Iterable<GraphManipulation.DirectedEdge> pathTo(int v){
    }*/
}