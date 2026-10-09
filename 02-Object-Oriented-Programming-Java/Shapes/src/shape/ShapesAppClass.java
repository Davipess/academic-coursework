package shape;

public class ShapesAppClass implements ShapesApp {

    private final ShapesCollectionClass elem;

    public ShapesAppClass() {
        this.elem = new ShapesCollectionClass();
    }

    public boolean hasShape(String ID){
        return elem.hasElem(ID);
    }

    public void addCircle(String ID, String type, int x, int y, int radius){
        elem.addElem(new CircleClass(ID,type,x,y,radius));
    }

    public void addRectangle(String ID, String type, int x, int y, int height, int width){
        elem.addElem(new RectangleClass(ID,type,x,y,height,width));
    }

    public boolean isEmpty(){
        return elem.isEmpty();
    }

    public void move(String ID, int x, int y){
        elem.getElement(ID).moveShape(x,y);
    }

    public Shapes smallestArea() {
        Shapes minShape = elem.getShapeAt(0);

        for (int i = 1; i < elem.getSize(); i++) {
            if (elem.getShapeAt(i).getArea() <= minShape.getArea()) {
                minShape = elem.getShapeAt(i);
            }
        }
        return minShape;
    }

    @Override
    public Iterator allShapesIterator() {
        return elem.allShapesIterator();
    }

    @Override
    public Iterator allShapesIterator(String type) {
        return elem.allShapesIterator(type);
    }
}