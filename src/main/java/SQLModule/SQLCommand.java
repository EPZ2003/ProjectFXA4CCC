package SQLModule;

import BackToFrontLinked.PipelineProductListQueries;

import java.sql.*;
import java.util.ArrayList;

public class SQLCommand {
    public static final String DB_URL = "jdbc:mysql://localhost:3306/projetjavafx";
    public static final String USER = "root";
    public static final String PASS = "1234";

    public static  ArrayList<ArrayList<String>> readTableProduct() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from projetjavafx.table_products");
        ArrayList<ArrayList<String>> listToReturn = new ArrayList<>();

        while (rs.next()){
            ArrayList<String> row = new ArrayList<>();
            row.add (rs.getString("id"));
            row.add (rs.getString("product_name"));
            row.add (rs.getString("stock"));
            row.add (rs.getString("specialAttribute"));
            listToReturn.add(row);
        }
        return listToReturn;

    }

    public static  ArrayList<ArrayList<String>> readTablePrices() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from projetjavafx.table_products_prices");
        ArrayList<ArrayList<String>> listToReturn = new ArrayList<>();

        while (rs.next()){
            ArrayList<String> row = new ArrayList<>();
            row.add (rs.getString("id_products"));
            row.add (rs.getString("discount"));
            row.add (rs.getString("sellPrice"));
            row.add (rs.getString("purchasePrice"));
            listToReturn.add(row);
        }
        return listToReturn;

    }

    public static  ArrayList<ArrayList<String>> readTableMoney() throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from projetjavafx.table_money");
        ArrayList<ArrayList<String>> listToReturn = new ArrayList<>();

        while (rs.next()){
            ArrayList<String> row = new ArrayList<>();
            row.add (rs.getString("capital"));
            row.add (rs.getString("income"));
            row.add (rs.getString("outcome"));
            listToReturn.add(row);
        }
        return listToReturn;

    }

    public static <T> void updateTableProduct (int id , String columnName,T value) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String command = "UPDATE projetjavafx.table_products SET " + columnName + " = "+value+" WHERE id = "+id;
        PreparedStatement preparedStatement = conn.prepareStatement(command);

        preparedStatement.execute();
        preparedStatement.close();



    }
    public static <T> void updateTablePrices (int id , String columnName,T value) throws SQLException {

        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String command = "UPDATE projetjavafx.table_products_prices SET " + columnName + " = "+value+" WHERE id_products = "+id;
        PreparedStatement preparedStatement = conn.prepareStatement(command);

        preparedStatement.execute();
        preparedStatement.close();

    }
    public static <T> void updateTableMoney (String columnName,T value) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String command = "UPDATE projetjavafx.table_money SET " + columnName + " = "+value;
        PreparedStatement preparedStatement = conn.prepareStatement(command);

        preparedStatement.execute();
        preparedStatement.close();
    }

    public static void deleteTableProduct(int id ) throws SQLException {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        String command = "Delete from projetjavafx.table_products where id ="+id;
        PreparedStatement preparedStatement = conn.prepareStatement(command);

        preparedStatement.execute();
        preparedStatement.close();
    }

}
