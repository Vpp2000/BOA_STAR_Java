package Entities;

public class Experiment {
    private long source;
    private long destiny;
    private int category;

    public Experiment(long source, long destiny, int category) {
        this.source = source;
        this.destiny = destiny;
        this.category = category;
    }

    public long getSource() {
        return source;
    }

    public long getDestiny() {
        return destiny;
    }

    public int getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Experiment{" +
                "source=" + source +
                ", destiny=" + destiny +
                ", category=" + category +
                '}';
    }
}
