package task18;

import org.h2.tools.SimpleResultSet;

import java.sql.ResultSet;
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

    public static Order createOrder(String userLogin, String[] products) throws SQLException {
        String sql = "";
        int max = 0;
        String sqlFindMAX = "SELECT MAX(id) FROM SHOP_LIST";
        ResultSet rs = statement.executeQuery(sqlFindMAX);

        while (rs.next()) {
            max = rs.getInt(1);
        }
        max+=1;

        for(String S : products){
            sql += "INSERT INTO SHOP_LIST VALUES(" + max + ",'" + userLogin + "', '" + S + "'); \n";
        }
        statement.executeUpdate(sql);
        return null;
    }
}
