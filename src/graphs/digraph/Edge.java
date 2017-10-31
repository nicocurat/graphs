package graphs.digraph;

public class Edge<T> {

    private T weight;
    private int w;

    public Edge(T weight, int w) {
        this.weight = weight;
        this.w = w;
    }

    public T getWeight() {
        return weight;
    }

    public void setWeight(T weight) {
        this.weight = weight;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Edge<?> edge = (Edge<?>) o;

        return w == edge.w;
    }
}
