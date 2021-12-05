package task11_update;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Date endDate = new Date();
        System.out.println(endDate);

        Auction_lot Lot = new Auction_lot(endDate, "vasya");
        Thread t0 = new Thread(Lot);
        Thread t1 = new Thread(new Auction_lot(endDate, "petya"));
        Thread t2 = new Thread(new Auction_lot(endDate,"nepetya"));
        Thread t3 = new Thread(new Auction_lot(endDate, "ivan"));
        Thread t4 = new Thread(new Auction_lot(endDate,"marya"));
        Thread t5 = new Thread(new Auction_lot(endDate,"mike"));
        
        t0.start(); t1.start();  t2.start();  t3.start();  t4.start();  t5.start();
        t0.join(); t1.join();  t2.join();  t3.join(); t4.join(); t5.join();

        System.out.println("Аукцион окончен \n" + "ПОБЕДИТЕЛЬ:" + Lot.getWinnerName());

    }
}