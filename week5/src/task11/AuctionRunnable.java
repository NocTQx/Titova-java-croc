package task11;

public class AuctionRunnable implements Runnable{
    private final User usr;
    private Auction_lot lot;
    private static final Object lock = new Object();

    public AuctionRunnable(User user, Auction_lot lot){
        this.usr = user;
        this.lot = lot;
    }

    public void run(){
        while (true){
            synchronized (lock){  // эксклюзивная критическая секция
                if (!lot.TimeCheck()) break;  // проверка, не закончился ли аукцион
                lot.NewCost(usr);
                System.out.println(lot.getWinnerName());
                System.out.println(lot.getCurrentCost());
            }
             try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}