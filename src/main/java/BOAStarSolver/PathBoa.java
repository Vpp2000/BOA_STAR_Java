package BOAStarSolver;

import java.util.List;

public class PathBoa {
    private List<Long> pathIds;
    private double c1;
    private double c2;

    public PathBoa(List<Long> pathIds, double c1, double c2) {
        this.pathIds = pathIds;
        this.c1 = c1;
        this.c2 = c2;
    }

    public List<Long> getPathIds() {
        return pathIds;
    }

    public double getC1() {
        return c1;
    }

    public double getC2() {
        return c2;
    }
}
