package PQUtils;

public class PQNode implements Comparable<PQNode> {
    private Long id;
    private Double distToSource;

    public PQNode(Long id, Double distToSource) {
        this.id = id;
        this.distToSource = distToSource;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDistToSource() {
        return distToSource;
    }

    public void setDistToSource(Double distToSource) {
        this.distToSource = distToSource;
    }

    @Override
    public int compareTo(PQNode pqNode) {
        return this.distToSource < pqNode.distToSource ? -1 : (this.distToSource == pqNode.distToSource ? 0 : 1);
    }

    @Override
    public String toString() {
        return "PQUtils.PQNode{" +
                "id=" + id +
                ", distToSource=" + distToSource +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        // if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PQNode pqNode = (PQNode) o;

        return id.equals(pqNode.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
