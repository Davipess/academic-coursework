package shape;

public class ShapesIterator implements Iterator {

    private Shapes[] elem;
    private int size;
    private int current;

    public ShapesIterator(Shapes[] elem, int size) {
        this.elem = elem;
        this.size = size;
        this.current = 0;
    }

    @Override
    public boolean hasNext() {
        return current < size;
    }

    @Override
    public Shapes next() {
        return elem[current++];
    }
}