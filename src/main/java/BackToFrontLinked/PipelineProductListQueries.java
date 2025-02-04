package BackToFrontLinked;

import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;

import java.sql.*;
import java.util.ArrayList;

import static SQLModule.MySQLOperations.*;

public class PipelineProductListQueries {
    public static ArrayList<Queries> listQueriesTableProduct = new ArrayList<Queries>();
    public static ArrayList<Queries> listQueriesTableProductPrices = new ArrayList<Queries>();
    public static ArrayList<Queries> listQueriesTableMoney = new ArrayList<Queries>();

    public static void InitializeAllList() throws SQLException{

        listQueriesTableProduct = MySQLOperations.readTableProduct();
        listQueriesTableProductPrices = MySQLOperations.readTableProductPrices();
        listQueriesTableMoney = MySQLOperations.readTableMoney();


    }
    //Initialize table_Money;
    //public static Queries uniqueRow = new Queries(1000.00,100.00,100.00);






    public static void PipelineProductListQueries(Product product)throws SQLException {


        if (product instanceof Shoes){
            Queries itemTableProducts = new Queries(product.getName(),product.getNbItems(),((Shoes) product).getShoeSize());
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
            Queries itemTableProductsPrices = new Queries(0.80f, product.getSellPrice(), product.getPurchasePrice());
            MySQLOperations.addRow(itemTableProductsPrices);
            listQueriesTableProductPrices.add(itemTableProductsPrices);
        }
        else if (product instanceof Clothes){

            Queries itemTableProducts = new Queries(product.getName(),product.getNbItems(),((Clothes) product).getSize());
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
            Queries itemTableProductsPrices = new Queries(0.70f, product.getSellPrice(), product.getPurchasePrice());
            MySQLOperations.addRow(itemTableProductsPrices);
            listQueriesTableProductPrices.add(itemTableProductsPrices);
        }
        else{
            Queries itemTableProducts = new Queries(product.getName(), product.getNbItems(), null);
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
            Queries itemTableProductsPrices = new Queries(0.50f, product.getSellPrice(), product.getPurchasePrice());
            MySQLOperations.addRow(itemTableProductsPrices);
            listQueriesTableProductPrices.add(itemTableProductsPrices);
        }




    }
    public static void pipelineQueries (Queries queries){
        MySQLOperations.addRow(queries);
        if (queries.getTableName() == Queries.TABLE_PRODUCT){
            listQueriesTableProduct.add(queries);
        } else if (queries.getTableName() == Queries.TABLE_PRODUCT_PRICES) {
            listQueriesTableProductPrices.add(queries);
        }
    }

}
