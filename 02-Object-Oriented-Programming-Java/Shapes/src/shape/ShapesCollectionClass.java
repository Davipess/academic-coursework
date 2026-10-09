package shape;

public class ShapesCollectionClass implements ShapesCollection {

    private final int LIMIT = 100;

    private String type;
    private String id;
    private Shapes[] elem;
    private int size;


    public ShapesCollectionClass() {
        this.type = type;
        this.id = id;
        this.elem = new Shapes[LIMIT];
        this.size = 0;
    }

    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks if there is a shape with the provided identifier.
     *
     * @param ID The identifier of the shape to check.
     * @return true if there is a shape with the provided identifier, false otherwise.
     * @pre ID != null
     */
    @Override
    public boolean hasElem(String ID) {
        boolean found = false;
        for (int i = 0; i < size && !found; i++) {
            if (elem[i].getId().equals(ID)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Adds a shape to the collection.
     *
     * @param newShape The shape to add.
     * @pre elem != null && this.hasElem(elem.getID())
     */
    @Override
    public void addElem(Shapes newShape) {
        elem[size++] = newShape;
    }

    /**
     * Retrieves the shape with the provided identifier from the collection.
     *
     * @param ID The identifier of the shape to retrieve.
     * @return The shape with the provided identifier.
     * @pre ID != null && this.hasElem(ID)
     */
    @Override
    public Shapes getElement(String ID) {
        Shapes shape = null;
        boolean found = false;
        for (int i = 0; i < size && !found; i++) {
            if (elem[i].getId().equals(ID)) {
                found = true;
                shape = elem[i];
            }
        }
        return shape;
    }

    public int getSize() {
        return size;
    }

    public Shapes getShapeAt(int index) {
        return elem[index];
    }


    @Override
    public Iterator allShapesIterator() {
        return new ShapesIterator(elem, size);
    }

    @Override
    public Iterator allShapesIterator(String type) {
        return new ShapesIterator(elem, size);
    }


}
