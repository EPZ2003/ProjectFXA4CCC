package BackToFrontLinked;

import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;

import java.util.ArrayList;

public class PipelineProductListQueries {
    public static ArrayList<Queries> listQueriesTableProduct = new ArrayList<Queries>();
    public static ArrayList<Queries> listQueriesTableProductPrices = new ArrayList<Queries>();
    public static ArrayList<Queries> listQueriesTableMoney  = new ArrayList<Queries>();

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

    public static void PipelineProductListQueries(Product product){
        System.out.println("etner");
        //TODO A MODIF
        if (product instanceof Shoes){
            System.out.println("pb1");
            Queries itemTableProducts = new Queries(product.getId(),product.getName(),product.getNbItems(),((Shoes) product).getShoeSize());
            System.out.println("pb2");
            MySQLOperations.addRow(itemTableProducts);
            System.out.println("pb3");
            listQueriesTableProduct.add(itemTableProducts);
        }
        else if (product instanceof Clothes){
            Queries itemTableProducts = new Queries(product.getId(),product.getName(),product.getNbItems(),((Clothes) product).getSize());
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
        }else{
            Queries itemTableProducts = new Queries(product.getId(),product.getName(),product.getNbItems(),null);
            MySQLOperations.addRow(itemTableProducts);
            listQueriesTableProduct.add(itemTableProducts);
        }

        Queries itemTableProductsPrices = new Queries(product.getId(),1.00, product.getSellPrice(), product.getPurchasePrice());
        MySQLOperations.addRow(itemTableProductsPrices);
        listQueriesTableProductPrices.add(itemTableProductsPrices);

    }







}
