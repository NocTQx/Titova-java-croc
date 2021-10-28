package task7;

public class ChessPosition{
    private int x;
    private int y;

    public ChessPosition(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String toString(){
        String posX = "a" + x;
        int posY = y + 1;
        return posX + posY;
    }

    public void move(int newX, int newY){
        this.x = newX;
        this.y = newY;
    }

}
