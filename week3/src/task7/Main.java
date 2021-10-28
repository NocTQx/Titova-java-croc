package task7;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static task7.ChessPosition parse(String position){
        int y = position.charAt(1) - '0';
        int x = position.charAt(0) - 'a' + 1;

        task7.ChessPosition newPos = new task7.ChessPosition(x, y);

        return newPos;
    }
    public static class inputAndCheck {
        public static String[] CreateStr() throws IOException {
            Scanner in = new Scanner(System.in);
            String[] inpArr = in.nextLine().split(" ");
            int len = inpArr.length;
            for (int i = 0; i < len; i++){
                if ((inpArr[i].charAt(0) > 'h') || (inpArr[i].charAt(0) < 'a') ||
                (inpArr[i].charAt(1) > '8') || (inpArr[i].charAt(1) < '1'))
                        throw new IOException("Неправильные входные данные");
            }
            return inpArr;
        }

        public static boolean checkMove(String[] Positions) throws IllegalMoveException {
            int len = Positions.length;
            ChessPosition pos = parse(Positions[0]);
            for (int i = 1; i < len; i++) {
                ChessPosition posNext = parse(Positions[i]);
                if (((Math.abs(pos.getX() - posNext.getX()) == 2) && (Math.abs(pos.getY() - posNext.getY()) == 1)) ||
                        ((Math.abs(pos.getX() - posNext.getX()) == 1) && (Math.abs(pos.getY() - posNext.getY()) == 2))) {
                    pos.move(posNext.getX(), posNext.getY());
                } else throw new IllegalMoveException("Конь так не ходит: ", Positions[i-1], Positions[i]);
            }
            return true;
        }
    }

    public static void main(String[] args) {
        try {
            inputAndCheck.checkMove(inputAndCheck.CreateStr());
        } catch (IOException e){
            System.err.println(e);
            System.exit(1);
        } catch (IllegalMoveException ex){
            System.out.println(ex);
            System.exit(1);
        }
        System.out.println("OK");
    }
}

