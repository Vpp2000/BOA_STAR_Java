package Entities;

public class Result {
    public static String headerForCsv = "eps,category,from,to,cardinality,timeExecution,expandedNodes,maxNodesInOpen,heuristicTime";

    private double eps;
    private int category;
    private long from;
    private long to;
    private int cardinality;
    private double timeExecution;
    private int expandedNodes;
    private int maxNodesInOpen;
    private double heuristicTime;

    public Result(double eps, int category, long from, long to, int cardinality, double timeExecution, int expandedNodes, int maxNodesInOpen, double heuristicTime) {
        this.eps = eps;
        this.category = category;
        this.from = from;
        this.to = to;
        this.cardinality = cardinality;
        this.timeExecution = timeExecution;
        this.expandedNodes = expandedNodes;
        this.maxNodesInOpen = maxNodesInOpen;
        this.heuristicTime = heuristicTime;
    }


    @Override
    public String toString(){
        StringBuilder dataBuilder = new StringBuilder();
        appendFieldValue(dataBuilder, eps);
        appendFieldValue(dataBuilder, category);
        appendFieldValue(dataBuilder, from);
        appendFieldValue(dataBuilder, to);
        appendFieldValue(dataBuilder, cardinality);
        appendFieldValue(dataBuilder, timeExecution);
        appendFieldValue(dataBuilder, expandedNodes);
        appendFieldValue(dataBuilder, maxNodesInOpen);
        appendFieldValue(dataBuilder, heuristicTime);

        return dataBuilder.toString();
    }

    private void appendFieldValue(StringBuilder dataBuilder, Object fieldValue) {
        if(fieldValue != null) {
            dataBuilder.append(fieldValue).append(",");
        } else {
            dataBuilder.append("").append(",");
        }
    }

}
