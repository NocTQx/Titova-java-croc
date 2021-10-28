package task7;

public class IllegalMoveException extends Exception {
    private final String message;
    private final String pos1;
    private final String pos2;


    public IllegalMoveException(String msg, String strOne, String strTwo){
        message = msg;
        pos1 = strOne;
        pos2 = strTwo ;
    }

    public String toString(){
        return message + pos1 + "->" + pos2;
    }

}
