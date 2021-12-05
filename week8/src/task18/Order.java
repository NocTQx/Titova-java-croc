package task18;

import java.sql.SQLException;
import java.util.List;
import java.util.ListIterator;

import static task18.Main.statement;

public class Order {
    private String customerName;
    private String articleNum;

    public Order(String customerName, String articleNum){
        this.articleNum = articleNum;
        this.customerName = customerName;
    }

    public String getArticleNum() {
        return articleNum;
    }

    public String getCustomerName() {
        return customerName;
    }

    public static Order createOrder(String userLogin, List<Product> products) throws SQLException {
        String sql = "";
        ListIterator <Product> iterator = products.listIterator();
        while (iterator.hasNext()){
            Order order = new Order(userLogin, iterator.next().getArticleNum());
            sql += "INSERT INTO SHOP_LIST VALUES('" + order.getCustomerName() + "', '" + order.getArticleNum() + "');";
        }
        statement.executeUpdate(sql);
        return null;
    }
}
