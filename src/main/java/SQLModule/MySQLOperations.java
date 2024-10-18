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

    public static void delete(Queries sql) throws SQLException{
        MySQLOperations.readLastId(sql);

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
        MySQLOperations.readLastId(sql);

        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        PreparedStatement preparedStatement = conn.prepareStatement(sql.udpate(changeValue,columName));

        preparedStatement.execute();
        //Update All Attributes
        updateAllColumnAttributes(sql);
        preparedStatement.close();
    }
    //Return an arrayList
    public static ArrayList<String> read (Queries sql) throws SQLException{
        MySQLOperations.readLastId(sql);

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

    public static void readLastId(Queries sql) throws SQLException{
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from "+sql.getTableName());
        int idToReturn = 0;
        while (rs.next()){
            idToReturn = rs.getInt(1);
        }
        sql.setIdProducts(idToReturn);
        sql.setId(idToReturn);
    }

    public static void updateAllColumnAttributes(Queries sql) throws SQLException{
        ArrayList<String> returnRead = MySQLOperations.read(sql);
        if (sql.getTableName() == Queries.TABLE_PRODUCT){
            sql.setId(Integer.parseInt(returnRead.get(0)));
            sql.setProductName(returnRead.get(1));
            sql.setStock(Integer.parseInt(returnRead.get(2)));
            sql.setSpecialAttribute( Double.parseDouble( returnRead.get(3)));
        }
        else if (sql.getTableName() == Queries.TABLE_PRODUCT_PRICES){
            sql.setId(Integer.parseInt(returnRead.get(0)));
            sql.setDiscount(Float.valueOf(returnRead.get(1)));
            sql.setSellPrice(Double.parseDouble( returnRead.get(2)));
            sql.setPurchasePrice(Double.parseDouble( returnRead.get(3)));
        }
        else if(sql.getTableName() == Queries.TABLE_MONEY){
            sql.setCapital(Double.valueOf(returnRead.get(0)));
            sql.setIncome(Double.valueOf(returnRead.get(1)));
            sql.setOutcome(Double.valueOf( returnRead.get(2)));
        }


    }

}