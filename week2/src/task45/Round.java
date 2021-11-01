package task45;

class Round extends task45.Figure implements Movable {
    private int x0;
    private int y0;
    private int r;

    public Round(int x0, int y0, int r) {
        this.figureType = "C";
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
    }
    public int getX0() {
        return this.x0;
    }
    public int getY0() {
        return this.y0;
    }
    public int getR(){
        return this.r;
    }

    public boolean pointInside(int x, int y){
        double i = (Math.pow((x - x0),2) + Math.pow((y - y0),2));
        return i <= r*r;
    }

    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dy;
    }
    @Override
    public String toString() {
        return "C (" + this.x0 + ", " + this.y0 + "), " + this.r + ": ";
    }
}
