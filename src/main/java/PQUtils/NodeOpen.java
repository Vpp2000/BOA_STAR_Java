package PQUtils;

import org.apache.commons.lang3.tuple.Pair;

public class NodeOpen implements Comparable<NodeOpen>{
    private long state;
    private double[] g_value = new double[2];
    private double[] f_value = new double[2];
    private NodeOpen parent;

    public NodeOpen(long state, double[] g_value, double[] f_value, NodeOpen parent) {
        this.state = state;
        this.g_value = g_value;
        this.f_value = f_value;
        this.parent = parent;
    }

    @Override
    public int compareTo(NodeOpen nodeOpen) {
        if (this.f_value[0] < nodeOpen.f_value[0])
            return -1;
        else if(this.f_value[0] == nodeOpen.f_value[0] && this.f_value[1] < nodeOpen.f_value[1])
            return -1;

        return 1;
    }

    public long getState() {
        return state;
    }

    public void setState(long state) {
        this.state = state;
    }

    public double[] getG_value() {
        return g_value;
    }

    public void setG_value(double[] g_value) {
        this.g_value = g_value;
    }

    public double[] getF_value() {
        return f_value;
    }

    public void setF_value(double[] f_value) {
        this.f_value = f_value;
    }

    public NodeOpen getParent() {
        return parent;
    }

    public void setParent(NodeOpen parent) {
        this.parent = parent;
    }
}
