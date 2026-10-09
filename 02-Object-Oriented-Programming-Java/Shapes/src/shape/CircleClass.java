package shape;

import java.lang.Math;

class CircleClass extends AbstractShape implements Circle {

    private final int radius;

    protected CircleClass(String id, String type, int x, int y, int radius) {
        super(id, type, x, y);
        this.radius = radius;

    }

    @Override
    public int getRadius() {
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }


}
