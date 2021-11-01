package task45;

public abstract class Figure implements Movable {
    String figureType;

    @Override
    public void move(int dx, int dy){}

    public abstract boolean pointInside(int x, int y);
}
