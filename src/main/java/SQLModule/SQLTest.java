package SQLModule;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLTest {
    static final String DB_URL = "jdbc:mysql://localhost:3306/projetjavafx";
    static final String USER = "root";
    static final String PASS = "1234";
    static final String QUERY = "SELECT id, name, stock FROM projetjavafx.tableprodutcs";

    public static void main(String[] args) {
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);
        ) {
            while(rs.next()){
                //Display values
                System.out.print("ID: " + rs.getInt("id") );
                System.out.println(Integer.parseInt("A"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}