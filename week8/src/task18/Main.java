package task18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.in;
import static task18.Product.findProduct;

public class Main {

    static final String driver = "org.h2.Driver";
    static final String connUrl = "jdbc:h2:~/test";
    public static Connection connection = null;
    public static Statement statement = null;

    public int app() throws SQLException {

        Scanner sc = new Scanner(in);
        String[] s = sc.nextLine().split(" ");
        if (s[0].equals("ТОВАР")) {
            Product prod = new Product (s[2], Integer.parseInt(s[3]), s[1]);
            prod.createProduct(prod);
        }
        if (s[0].equals("ИЗМЕНИТЬ")){

        }
        if (s[0].equals("УДАЛИТЬ")){
            Product prod = new Product(s[2], Integer.parseInt(s[3]), s[1]);
            prod.deleteProduct(s[1]);
        }
        if (s[0].equals("ЗАКАЗ")){
            String[] lst = s[1].split(",");
            List<Product> lstProd = new ArrayList<>();
            for(int i = 0; i < lst.length; i++){
                lstProd.add(findProduct(lst[i]));
            }
            Order.createOrder(s[0], lstProd);
        }
        if (s[0].equals("КОНЕЦ")){
            statement.close();
            connection.close();
            return 0;
        }
        return 1;
    }

    public void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Class.forName(driver);
        connection = DriverManager.getConnection(connUrl, "sa", "");
        statement = connection.createStatement();

        statement.executeUpdate("DROP TABLE IF EXISTS STUFF; DROP TABLE IF EXISTS SHOP_LIST;\n");

        String sql = "CREATE TABLE SHOP_LIST (id INTEGER not NULL, login VARCHAR(255) not NULL, article VARCHAR(255) PRIMARY KEY);";
        sql += "\nCREATE TABLE STUFF (item_name VARCHAR(255) not NULL, price INTEGER not NULL, stuff_id VARCHAR(255), foreign key (stuff_id) references SHOP_LIST(article));";
        statement.executeUpdate(sql);

        BufferedReader r = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = r.readLine()) != null) {

            String[] arrStr = line.split("[,\"]");

            String insertSql = "INSERT INTO SHOP_LIST VALUES (" + arrStr[1] + ", '" + arrStr[2] + "', '" + arrStr[3] + "');\n";
            insertSql += "IF NOT EXISTS (SELECT * FROM shop_list s WHERE s.article = '" + arrStr[3] + "') BEGIN INSERT INTO STUFF VALUES ('" + arrStr[4] + "', " + arrStr[5] + ", '" + arrStr[3] + "') END";

            statement.executeUpdate(insertSql);
        }
            System.out.println("Введите необходимую инструкцию:");
            System.out.println("ТОВАР <артикул товара> <название> <цена>");
            System.out.println("ИЗМЕНИТЬ <артикул товара> <новое название> <новая_цена>");
            System.out.println("УДАЛИТЬ <артикул товара>");
            System.out.println("ЗАКАЗ <логин_пользователя> <артикул товара_1>[, <артикул_товара_N>]");
            System.out.println("Для завершения введите КОНЕЦ");

           while (app() != 0){}

        statement.close();
        connection.close();
        }
}
