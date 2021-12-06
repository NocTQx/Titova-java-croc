package task17;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {


    static final String driver = "org.h2.Driver";
    static final String connUrl = "jdbc:h2:~/test";

    public static void main(String[] args){

        Connection connection = null;
        Statement statement = null;

        try {
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
            statement.close();
            connection.close();
        } catch (SQLException se){
            se.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
            } catch (SQLException se2) {
            }
            try {
                if (connection != null) connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        }
    }
