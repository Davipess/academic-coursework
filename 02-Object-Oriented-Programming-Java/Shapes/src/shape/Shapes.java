package shape;

public interface Shapes {
    /**
     * @return id of a given shape.
     */
    String getId();

    /**
     * @return x coordinate of a given shape.
     */
    int getXCenter();

    /**
     * @return y coordinate of a given shape.
     */
    int getYCenter();

    /**
     *
     * @param x a new x coordinate the shape will move to.
     * @param y a new y coordinate the shape will move to.
     *
     * Moves a shape to a new position with a given set of (x,y) coordinates.
     */
    void moveShape(int x, int y);

    double getArea();

    String getType();
}
