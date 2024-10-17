package ItemPackage;

import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Product;

import java.util.ArrayList;

public class ListItem {

    public static ArrayList<Queries> ListQueriesPage1 = new ArrayList<Queries>();
    public static ArrayList<Queries> ListQueriesPage3 = new ArrayList<Queries>();



    public static void NewProduct(Product newProduct, Double discount)
    {
        Queries queryPage1 = new Queries(newProduct.getId(), newProduct.getName(), newProduct.getNbItems());
        Queries queryPage3 = new Queries(newProduct.getId(), discount ,newProduct.getSellPrice(), newProduct.getPurchasePrice());
        //
        ListQueriesPage1.add(queryPage1);
        ListQueriesPage3.add(queryPage3);
        //
        MySQLOperations.addRow(queryPage1); // nécessaire ou pas ?
        MySQLOperations.addRow(queryPage3); // idem ?

    }

}
