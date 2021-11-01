package task45;

class Rectangle extends task45.Figure implements Movable {
    private int x0;
    private int x1;
    private int y0;
    private int y1;

    public Rectangle (int x0, int y0, int x1, int y1) {
        this.figureType = "R";
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
    }
    public int getX0(){
        return this.x0;
    }
    public int getY0(){

        return this.y0;
    }

    public int getX1(){
        return x1;
    }

    public int getY1(){
        return y1;
    }

    public boolean pointInside(int x, int y){
        return (x <= x1 && x >= x0 && y <= y1 && y >= y0);
    }

    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dy;
        this.x1 += dx;
        this.y1 += dy;
    }
    @Override
    public String toString() {
        return "R (" + this.x0 + ", " + this.y0 + "), (" + this.x1 + ", " + this.y1 + ")" + ": ";
    }
}
