package task18;

import java.sql.ResultSet;
import java.sql.SQLException;

import static task18.Main.statement;

public class Product {
    private static String productName;
    private static int productPrice;
    private static String articleNum;

    public Product(String productName, int productPrice, String articleNum){
        this.productName = productName;
        this.productPrice = productPrice;
        this.articleNum = articleNum;
    }

    public static int getProductPrice() {
        return productPrice;
    }

    public static String getArticleNum() {
        return articleNum;
    }

    public static String getProductName(){
        return productName;
    }

    public static Product findProduct(String productCode) throws SQLException{
        String[] s = new String[0];
        String sql = "SELECT * FROM STUFF WHERE'" + productCode + "' IN stuff_id";
        if (statement.executeUpdate(sql) == 0) return null;
        ResultSet rs = statement.executeQuery(sql);
        int columns = rs.getMetaData().getColumnCount();
        while (rs.next()){
            for (int i = 1; i <= columns; i++)
                s[i-1] = (rs.getString(i));
        }
        Product pr = new Product(s[0],Integer.parseInt(s[1]),productCode);
        if (rs != null) rs.close();
        return pr;
    }

    Product createProduct(Product product) throws SQLException {
        String article = product.getArticleNum();
        String sql;
        if (findProduct(article) == null){
            sql = "INSERT INTO STUFF VALUES ('" + product.getProductName() + "', " +
                    product.getProductPrice() + ", '" + product.getArticleNum() + "');";
            statement.executeUpdate(sql);
            return product;
        }
        throw new SQLException("Продукт уже существует");
    }

    Product updateProduct(Product product) throws SQLException {
        String article = product.getArticleNum();
        String sql;
        if (findProduct(article) != null){
            sql = "UPDATE STUFF SET item_name = '" + product.getProductName() + "', price = " + product.getProductPrice() +
            "\n WHERE stuff_id = '" +  product.getArticleNum() + "';";
            statement.executeUpdate(sql);
            return product;
        }
        throw new SQLException("Продукт не существует");
    }

    void deleteProduct(String productCode) throws SQLException {
        String sql;
        sql = "DELETE * FROM SHOP_LIST WHERE '" + productCode + "'";
        statement.executeUpdate(sql);
    }
}
