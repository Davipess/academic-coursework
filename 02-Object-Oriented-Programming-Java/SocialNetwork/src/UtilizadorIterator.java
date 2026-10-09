/**
 * Interface for iterating over a collection of Utilizador objects.
 */
public interface UtilizadorIterator {

    /**
     * Initializes or resets the iterator to the beginning of the collection.
     */
    void init();

    /**
     * Checks if there are more elements to iterate over.
     * @return true if there is at least one more element, false otherwise.
     */
    boolean hasNext();

    /**
     * Retrieves the next Utilizador in the collection.
     * @return The next Utilizador object.
     * @pre hasNext() == true
     */
    Utilizador next();
}