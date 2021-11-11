package task11;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Auction_lot Lot = new Auction_lot(150, System.nanoTime() + (long) 1e10); // Создаем лот, начальная цена и конечное время

        User us1 = new User("Bob",Lot); // создвем пользователей: имя и лот, в котором участвует
        User us2 = new User("Marlin", Lot);
        User us3 = new User("Mike", Lot);
        User us4 = new User("Helena", Lot);
        User us5 = new User("Ivan", Lot);

        Thread t1 = new Thread(new AuctionRunnable(us1, Lot));
        Thread t2 = new Thread(new AuctionRunnable(us2, Lot));
        Thread t3 = new Thread(new AuctionRunnable(us3, Lot));
        Thread t4 = new Thread(new AuctionRunnable(us4, Lot));
        Thread t5 = new Thread(new AuctionRunnable(us5, Lot));

        t1.start();  t2.start();  t3.start();  t4.start();  t5.start();
        t1.join();  t2.join();  t3.join();  t4.join();  t5.join();

        System.out.println("Аукцион окончен \n" + "ПОБЕДИТЕЛЬ:" + Lot.getWinnerName());




    }
}
