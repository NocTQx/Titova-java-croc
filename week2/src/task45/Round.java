package task45;

class Round extends task45.Figure implements Movable {
    public Round(int x0, int y0, int r) {
        this.x0 = x0;
        this.y0 = y0;
        this.r = r;
        this.type = "C";
    }

    @Override
    public void move(int dx, int dy) {
        this.x0 += dx;
        this.y0 += dy;
    }
}
