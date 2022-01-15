package GraphManipulation;

import java.util.HashMap;
import java.util.Map;

public class DirectedEdge {
    private Map<Integer, Double> costs = new HashMap<>();
    private long tail;
    private long head;

    public DirectedEdge(double c1, double c2, long tail, long head) {
        this.costs.put(1, c1);
        this.costs.put(2, c2);
        this.tail = tail;
        this.head = head;
    }

    public double getCostByIndex(int index){
        return this.costs.get(index);
    }

    public long from(){
        return this.tail;
    }

    public long to(){
        return this.head;
    }

    public DirectedEdge reverseEdge(){
        return new DirectedEdge(this.costs.get(1), this.costs.get(2), this.head, this.tail);
    }

    @Override
    public String toString() {
        return "DirectedEdge{" +
                "costs=" + costs +
                ", tail=" + tail +
                ", head=" + head +
                '}';
    }
}
