package SQLModule;

import java.sql.*;
import java.util.ArrayList;

public class MySQLOperations {
    static final String DB_URL = "jdbc:mysql://localhost:3306/projetjavafx";
    static final String USER = "root";
    static final String PASS = "1234";

    public static void addRow(Queries sql){
        try{
            if (sql.getTableName() == Queries.TABLE_MONEY){
                throw new Exception("Bad table");
            }

            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement(sql.addRow());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception error) {
            System.out.println("Error of : " +error );
        }

    }

    public static void delete(Queries sql){

        try{
            if (sql.getTableName() == Queries.TABLE_MONEY){
                throw new Exception("Bad table");
            }

            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement(sql.delete());
            preparedStatement.execute();
            preparedStatement.close();

        } catch (Exception error) {
            System.out.println("Error of : " +error );
        }
    }

    public static <T> void update(Queries sql,T changeValue, String columName) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement preparedStatement = conn.prepareStatement(sql.udpate(changeValue,columName));
        preparedStatement.execute();
        preparedStatement.close();
    }
    //Return an arrayList
    public static ArrayList<String> read (Queries sql) throws SQLException{

        ArrayList<String> row = new ArrayList<String>();
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql.read());

        if (rs.next()){
            for (String i : sql.getColumn()) {
                row.add( rs.getString(i) ) ;
            }
        }
        return row;
    }
    //Juste pour le test
    /*public static void main(String[] args) {
        // Open a connection
        //Table table_products
        Queries sql1 = new Queries(3,"pants",34);

        //Table table_products_prices
        Queries sql2 = new Queries(10,0.34,34.3,23.1);
        //Table table_money
        Queries sql3 = new Queries(3.01,23.03,21.93);

        try{
            //MySQLOperations.add(sql2);
            //MySQLOperations.add(sql1);
            //MySQLOperations.update(sql3,34.01,"capital");
            //MySQLOperations.update(sql1,3943,"stock");

            //System.out.println(MySQLOperations.read(sql1));
        } catch (Exception error) {
            System.out.println("Error : "+error);
        }
    }*/
}