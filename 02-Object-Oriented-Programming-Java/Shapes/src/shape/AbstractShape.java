package shape;

abstract class AbstractShape implements Shapes {
    private final String id;
    private int xC;
    private int yC;
    private final String type;

    protected AbstractShape(String id, String type, int x, int y) {
        this.id = id;
        this.xC = x;
        this.yC = y;
        this.type = type;
    }

    @Override
    public String getId(){
        return id;
    }

    @Override
    public int getXCenter(){
        return xC;
    }

    @Override
    public int getYCenter(){
        return yC;
    }

    @Override
    public void moveShape (int x, int y){
        xC = x;
        yC = y;
    }

    @Override
    public String getType() {
        return type;
    }

}
