package task11;

public class Auction_lot {
    private int currentCost;  // текущая цена
    private String theMostSuccessfulUser;  // имя текущего победителя
    private long endTime;  // время конца аукциона

    public Auction_lot(int currentCost, long endTime){
        this.currentCost = currentCost;
        this.endTime = endTime;
    }

    public void NewCost(User usr){
        this.currentCost = usr.setNewCost();  // устанавливаем новую стоимость
        this.theMostSuccessfulUser = usr.getUserName();  // устанавливаем имя потенциального победителя
    }

    public boolean TimeCheck(){
        return System.nanoTime() < endTime;
    }

    public int getCurrentCost() {
        return currentCost;
    }

    public String getWinnerName(){
        return this.theMostSuccessfulUser;
    }

}
