/**
 * The ContactIterator class provides an iterator over an 
 * array of Contact objects.
 */
public class ContactIterator {
    private Contact[] contacts; // array of contacts
    private int size; // number of contacts in the array
    private int nextIndex; // index of the next contact

    /**
     * Constructs a ContactIterator with the specified 
     * array of contacts and size.
     *
     * @param contacts the array of contacts to iterate over
     * @param size the number of contacts in the array
     */
    public ContactIterator(Contact[] contacts, int size) {
        this.contacts = contacts;
        this.size = size;
        nextIndex = 0;
    }

    /**
     * Checks if there is a next contact in the iterator.
     *
     * @return true if there is a next contact, false otherwise
     */
    public boolean hasNext() {
        return nextIndex < size;
    }

    /**
     * Gets the next contact in the iterator.
     *
     * @return the next contact
     * @pre hasNext()
     */
    public Contact next() {
        return contacts[nextIndex++];
    }
}
