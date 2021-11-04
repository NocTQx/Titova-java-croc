package task10;

public class Record {
    private final String status;
    private final int time;

    Record(String status, int time){
        this.status = status;
        this.time = time;
    }

    public int getTime() {
        return time;
    }

    public String getStatus() {
        return status;
    }
}
