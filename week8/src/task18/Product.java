package task18;

import java.sql.ResultSet;
import java.sql.SQLException;

import static task18.Main.statement;

public class Product {
    private static String productName;
    private static int productPrice;
    private static String articleNum;

    public Product (){

    }

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

        String sql = "SELECT * FROM STUFF WHERE STUFF_ID = '" + productCode + "';";
        ResultSet rs = statement.executeQuery(sql);
        if (!rs.next()) return null;
        Product pr = new Product();

        return pr;
    }

    Product createProduct(Product product) throws SQLException {
        String article = getArticleNum();
        String sql;
        if (findProduct(article) == null){
            sql = "INSERT INTO STUFF VALUES ('" + getProductName() + "', " +
                    getProductPrice() + ", '" + getArticleNum() + "');";
            statement.executeUpdate(sql);
            return product;
        }
        else throw new SQLException("Продукт уже существует");
    }

    Product updateProduct(Product productChange) throws SQLException {
        String article = productChange.getArticleNum();
        String sql;
        if (findProduct(article) != null){

            sql = "UPDATE STUFF SET item_name = '" + productChange.getProductName() + "', price = " + productChange.getProductPrice() +
            "\n WHERE stuff_id = '" +  productChange.getArticleNum() + "';";
            statement.executeUpdate(sql);
            return productChange;
        }
        throw new SQLException("Продукт не существует");
    }

    void deleteProduct(String productCode) throws SQLException {

        String sql;
        if (findProduct(productCode) != null){
            sql = "DELETE FROM SHOP_LIST WHERE ARTICLE = '" + productCode + "'; \n";
            sql += "DELETE FROM STUFF WHERE STUFF_ID = '" + productCode + "';";
            statement.executeUpdate(sql);
        }
        else throw new SQLException("Продукт не существует");
    }
}
