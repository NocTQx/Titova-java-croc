package task11;

public class User {
        private String userName;
        private Auction_lot lot;

        public User(String name, Auction_lot lot){
                this.userName = name;
                this.lot = lot;
        }

        public int setNewCost(){
            return lot.getCurrentCost() + 50 + (int)(Math.random()*101);  // генерирует новую цену выше установленной на диапазон от 50 до 150
        }

        public String getUserName(){
                return userName;
        }
}
