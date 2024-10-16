package WomenShopClasses;

import SQLModule.MySQLOperations;
import SQLModule.Queries;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListItem {

    private static List<Queries> ListQueriesPage1 = new ArrayList<Queries>();
    private static List<Queries> ListQueriesPage3 = new ArrayList<Queries>();
    private static List<List<String>> ListReadPage1 = new ArrayList<List<String>>();
    private static List<List<String>> ListReadPage3 = new ArrayList<List<String>>();

    public static List<Queries> getListQueriesPage1() {
        return ListQueriesPage1;
    }

    public static void setListQueriesPage1(List<Queries> listQueriesPage1) {
        ListQueriesPage1 = listQueriesPage1;
    }

    public static List<List<String>> getListReadPage1() {
        return ListReadPage1;
    }

    public static void setListReadPage1(List<List<String>> listReadPage1) {
        ListReadPage1 = listReadPage1;
    }

    public static List<List<String>> getListReadPage3() {
        return ListReadPage3;
    }

    public static void setListReadPage2(List<List<String>> listReadPage3) {
        ListReadPage3 = listReadPage3;
    }

    public static List<Queries> getListQueriesPage3() {
        return ListQueriesPage3;
    }

    public static void setListQueriesPage3(List<Queries> listQueriesPage3) {
        ListQueriesPage3 = listQueriesPage3;
    }

    public static void NewProduct(Product newProduct, Double discount)
    {
        Queries queryPage1 = new Queries(newProduct.getId(), newProduct.getName(), newProduct.getNbItems());
        ListQueriesPage1.add(queryPage1);
        Queries queryPage3 = new Queries(newProduct.getId(), discount ,newProduct.getSellPrice(), newProduct.getPurchasePrice());
        ListQueriesPage3.add(queryPage3);
        MySQLOperations.addRow(queryPage1); // nécessaire ou pas ?
        MySQLOperations.addRow(queryPage3); // idem ?
        try {
            ListReadPage1.add(MySQLOperations.read(queryPage1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            ListReadPage3.add(MySQLOperations.read(queryPage3));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
