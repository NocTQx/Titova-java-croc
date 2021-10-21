package task45;

class Rectangle extends task45.Figure implements Movable {
    public Rectangle (int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.x1 = x1;
        this.y0 = y0;
        this.y1 = y1;
        this.type = "R";
    }

    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dy;
        this.x1 += dx;
        this.y1 += dy;
    }
}
