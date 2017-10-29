package graphs.digraph;

public class Vertex<T> {

    private T element;
    private int id;

    Vertex(T element, int id) {
        this.element = element;
        this.id = id;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
