package task11_update;
import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Main {

    public static List<Thread> appThreads = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2021, 11, 6, 23, 7);
        Date endDate = calendar.getTime();

        Auction_lot Lot = new Auction_lot(endDate, "vasya");
        Thread t0 = new Thread(Lot);
        appThreads.add(t0);
        Thread t1 = new Thread(new Auction_lot(endDate, "petya"));
        appThreads.add(t1);
        Thread t2 = new Thread(new Auction_lot(endDate,"nepetya"));
        appThreads.add(t2);
        Thread t3 = new Thread(new Auction_lot(endDate, "ivan"));
        appThreads.add(t3);
        Thread t4 = new Thread(new Auction_lot(endDate,"marya"));
        appThreads.add(t4);
        Thread t5 = new Thread(new Auction_lot(endDate,"mike"));
        appThreads.add(t5);

        for (Thread thread : appThreads) {
            thread.start();
            thread.join();
        }

        System.out.println("Аукцион окончен \n" + "ПОБЕДИТЕЛЬ:" + Lot.getWinnerName());

    }
}