package task18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import static java.lang.System.in;
import static task18.Product.findProduct;

public class Main {

    static final String driver = "org.h2.Driver";
    static final String connUrl = "jdbc:h2:~/test";
    public static Connection connection = null;
    public static Statement statement = null;

    public static int app() throws SQLException {

        Scanner sc = new Scanner(in);
        String[] s = sc.nextLine().split(" ");
        if (s[0].equals("ТОВАР")) {
            Product prod = new Product (s[2], Integer.parseInt(s[3]), s[1]);
            prod.createProduct(prod);
        }
        if (s[0].equals("ИЗМЕНИТЬ")){
            Product prod = new Product (s[2], Integer.parseInt(s[3]), s[1]);
            prod.updateProduct(prod);
        }
        if (s[0].equals("УДАЛИТЬ")){
            Product pr = new Product();
            pr.deleteProduct(s[1]);
        }
        if (s[0].equals("ЗАКАЗ")){
            String[] lst = s[2].split(",");
            for(int i = 0; i < lst.length; i++)
            if (findProduct(lst[i]) == null)
                throw new SQLException("Товар с артикулом " + lst[i] + " не существует");

            Order.createOrder(s[1], lst);
        }
        if (s[0].equals("КОНЕЦ")){
            return 0;
        }
        return 1;
    }

    public static void printTable() throws SQLException {
        ResultSet rs = statement.executeQuery("SELECT * FROM STUFF;");
        int columns = rs.getMetaData().getColumnCount();
        while (rs.next())
            for (int i = 1; i <= columns; i++){
                System.out.print(rs.getString(i) + " ");
            }
        System.out.println("\n");
        ResultSet rs2 = statement.executeQuery("SELECT * FROM SHOP_LIST;");
        int col = rs2.getMetaData().getColumnCount();
        while (rs2.next())
            for (int i = 1; i <= col; i++){
                System.out.print(rs2.getString(i) + " ");
            }
        System.out.println("\n");
    }
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {

        Class.forName(driver);
        connection = DriverManager.getConnection(connUrl, "sa", "");
        statement = connection.createStatement();

        statement.executeUpdate("DROP TABLE IF EXISTS STUFF;\n" +
                "DROP TABLE IF EXISTS SHOP_LIST;");

        String sql = "CREATE TABLE STUFF (item_name VARCHAR(255) not NULL, price INTEGER not NULL, stuff_id VARCHAR(255) PRIMARY KEY);";
        sql += "\n CREATE TABLE SHOP_LIST (id INTEGER not NULL, login VARCHAR(255) not NULL, article VARCHAR(255) REFERENCES STUFF(stuff_id));";

        statement.executeUpdate(sql);

        BufferedReader r = new BufferedReader(new FileReader(args[0]));
        String line;
        while ((line = r.readLine()) != null) {
            String[] arrStr = line.split("[,\"]");

            String insertSql = "MERGE INTO STUFF USING (SELECT '" + arrStr[3] + "' STUFF_ID) AS S ON STUFF.STUFF_ID = S.STUFF_ID" +
                    " WHEN NOT MATCHED THEN INSERT VALUES ('" + arrStr[4] + "', " + arrStr[5] + ", '" + arrStr[3] + "');";
            insertSql += "\n INSERT INTO SHOP_LIST VALUES (" + arrStr[1] + ", '" + arrStr[2] + "', '" + arrStr[3] + "');\n";

            statement.executeUpdate(insertSql);
        }
            System.out.println("Введите необходимую инструкцию:");
            System.out.println("ТОВАР <артикул товара> <название> <цена>");
            System.out.println("ИЗМЕНИТЬ <артикул товара> <новое название> <новая_цена>");
            System.out.println("УДАЛИТЬ <артикул товара>");
            System.out.println("ЗАКАЗ <логин_пользователя> <артикул товара_1>[, <артикул_товара_N>]");
            System.out.println("Для завершения введите КОНЕЦ");
            try {
               while (app() != 0){}
                printTable();

            } catch (SQLException e){
                System.err.println(e);
            }

        statement.close();
        connection.close();
        }
}
