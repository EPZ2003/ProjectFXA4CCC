package BackToFrontLinked;

import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;

import java.sql.SQLException;
import java.util.ArrayList;

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
    private static Queries uniqueRow = new Queries(1000.00,100.00,100.00);

    //Have to be called in initilized
    private static void initializeMoneyPipeline(Double capital){
        setCapital(capital);
        MySQLOperations.addRow(uniqueRow);
        listQueriesTableMoney.add(uniqueRow);
    }

    public static void setCapital(Double capital) {
        PipelineProductListQueries.uniqueRow.setCapital(capital);
    }

    public static void PipelineProductListQueries(Product product)throws SQLException {

        if (product instanceof Shoes){
            Queries itemTableProducts = new Queries(product.getName(),product.getNbItems(),((Shoes) product).getShoeSize());
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
        }
        else if (product instanceof Clothes){

            Queries itemTableProducts = new Queries(product.getName(),product.getNbItems(),((Clothes) product).getSize());
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
        }
        else{
            Queries itemTableProducts = new Queries(product.getName(), product.getNbItems(), null);
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
        }

        Queries itemTableProductsPrices = new Queries(1.00f, product.getSellPrice(), product.getPurchasePrice());
        MySQLOperations.addRow(itemTableProductsPrices);
        listQueriesTableProductPrices.add(itemTableProductsPrices);


    }







}
