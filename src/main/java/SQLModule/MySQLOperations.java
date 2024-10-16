package SQLModule;

import java.sql.*;

public class MySQLOperations {
    static final String DB_URL = "jdbc:mysql://localhost:3306/projetjavafx";
    static final String USER = "root";
    static final String PASS = "1234";

    static Queries queri = new Queries(5,"pantaloons",20);
    /*
    static final String add = queri.addProduct();
    static final String delete = Queries.deleteProduct(queri.getId());
    static final String update = Queries.udpateStock(queri.getId());
    static final String read = Queries.readTable();*/
    //TODO Have to take Many queries
    public static void add(Queries sql){
        try{
            if (sql.getTableName() == Queries.TABLE_MONEY){
                throw new Exception("Bad table");
            }

            Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
            PreparedStatement preparedStatement = conn.prepareStatement(sql.add());
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
    /*
    public void update() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement preparedStatement = conn.prepareStatement(update);

        preparedStatement.setString(1,Queries.toString((queri.getStock()) - 1));
        preparedStatement.setString(2,Queries.toString(queri.getId()));
        preparedStatement.execute();
    }*/

    /*public void readAll() throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(read);

        while (rs.next()){
            System.out.println(rs.getString("id") +  rs.getString("productName") + rs.getString("stock"));
        }
    }*/
    public static void main(String[] args) {
        // Open a connection
        Queries sql = new Queries(3,"pants",34);
        Queries sql2 = new Queries(10,0.34,34.3,23.1);
        MySQLOperations.add(sql2);
        MySQLOperations.delete(sql);
        try {
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);


        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}