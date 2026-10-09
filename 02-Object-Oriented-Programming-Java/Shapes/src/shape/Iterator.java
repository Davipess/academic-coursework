package shape;

/**
 * An iterator over a collection.
 */
public interface Iterator {

    /**
     * Returns true if the iteration has more elements.
     *
     * @return true, if the iteration has more elements
     */
    boolean hasNext();

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     * @pre this.hasNext()
     */
    Shapes next();
}