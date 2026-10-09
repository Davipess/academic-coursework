package shape;

public class RectangleClass extends AbstractShape implements Rectangle {

    private final int height;
    private final int width;

    protected RectangleClass(String id, String type, int x, int y, int height, int width) {
        super(id, type, x, y);
        this.height = height;
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public double getArea() {
        return height * width;
    }
}
