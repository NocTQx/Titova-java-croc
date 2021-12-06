package task11_update;

import java.util.Date;
import java.util.Random;

import static task11_update.Main.appThreads;

public class Auction_lot implements Runnable{
    volatile private int currentCost;  // текущая цена
    volatile private String theMostSuccessfulUser;  // имя текущего победителя
    private String usr;
    private Date endTime;  // время конца аукциона
    final Random rand = new Random();

    public Auction_lot(Date endTime, String usr) {

        this.endTime = endTime;
        this.usr = usr;
    }

    public synchronized void NewCost(int cost, String user){
        if (TimeCheck() && (cost > currentCost)){
            this.currentCost = cost;  // устанавливаем новую стоимость
            this.theMostSuccessfulUser = user;  // устанавливаем имя потенциального победителя
        }
    }

    public boolean TimeCheck(){
        return (new Date().before(endTime));
    }

    public String getWinnerName(){
        if (!(TimeCheck()))
            return this.theMostSuccessfulUser;
        else return "Auction continue";
    }
    
    @Override
    public void run() {
            while (TimeCheck()){
                NewCost(currentCost + rand.nextInt(20),this.usr);
            }
            for (Thread thread : appThreads)
                thread.interrupt();
    }
}
