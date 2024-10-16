package SQLModule;

import java.sql.SQLException;
import java.util.ArrayList;

public class Queries  {
    static final String TABLE_PRODUCT = "projetjavafx.table_products";
    static final String TABLE_PRODUCT_PRICES = "projetjavafx.table_products_prices" ;
    static final String TABLE_MONEY = "projetjavafx.table_money" ;

    //Use to know which table are related to the query
    private String tableName = "projetjavafx.table_money";

    //For table : tableproducts
    private int id,stock;
    private String productName;

    //For table : money
    private Double capital,income,outcome;

    //For table : products_prices
    private int idProducts;
    private Double discount,sellPrice,purchasePrice;

    //Constructor for table :  table_products
    public Queries (int id,String productName,int stock) {
        this.id = id;
        this.productName = productName;
        this.stock = stock;
        this.tableName = "projetjavafx.table_products";
    }

    //Constructor for table :  table_products_prices
    public Queries (int idProducts,Double discount,Double sellPrice,Double purchasePrice) {
        this.idProducts = idProducts;
        this.discount = discount;
        this.sellPrice = sellPrice;
        this.purchasePrice = purchasePrice;
        this.tableName   = "projetjavafx.table_products_prices";
    }

    //Constructor for tavble : table_Money
    public Queries (Double capital,Double income, Double outcome){
        this.capital = capital;
        this.income = income;
        this.outcome = outcome;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public int getIdProducts() {
        return idProducts;
    }

    public void setIdProducts(int idProducts) {
        this.idProducts = idProducts;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getCapital() {
        return capital;
    }

    public void setCapital(Double capital) {
        this.capital = capital;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String add () {
        if (this.getTableName() == Queries.TABLE_PRODUCT ) return " insert into "+this.getTableName()+" (id,product_name,stock) values ("+this.getId()+","+"'"+this.getProductName()+"'"+","+this.getStock()+")";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " insert into "+this.getTableName()  +" (idProducts,discount,sellPrice,purchasePrice) values ("+this.getIdProducts()+"," +this.getDiscount()+","+this.getSellPrice()+","+this.getPurchasePrice()+")";
        else return "";
    }
    public String delete () {
        if (this.getTableName() == Queries.TABLE_PRODUCT ) return " delete from "+this.getTableName()+" where id = "+this.getId()+";";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " delete from "+this.getTableName()+" where id = "+this.getIdProducts()+";";
        else return "";

    }
    //TODO :

    /**
     * Spec :
     * 1- Pouvoir Update une colonne de nimporte quelle Table (3 diff)
     * 2 - update nombre de colonne qu'on veut
     * 3 - Il faut que la méthode soit static
     * 4 - Ne pas oublier de Géré les cas spéciaux (Si c'est possible) et throw une ERREUR
     */
    public <T> String udpate (T changeValue,String columnName) {
        if (this.getTableName() == Queries.TABLE_PRODUCT ) return " update "+this.getTableName()+" set "+columnName+"="+ changeValue +" where id = "+this.getId()+";";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " update "+this.getTableName()+" set "+columnName+"="+ changeValue +" where id_products = "+this.getIdProducts()+";";
        else if (this.getTableName() == Queries.TABLE_MONEY) return " update "+this.getTableName()+" set "+columnName+"="+ changeValue;
        else return "";
    }

    //TODO readTable
    public String read (){
        if (this.getTableName() == Queries.TABLE_PRODUCT )return " select * from "+this.getTableName() +" where id = "+this.getId();
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ) return " select * from " +this.getTableName() +" where id_products = "+this.getIdProducts();
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ) return " select * from " +this.getTableName();
        else return "";
    }
    //TODO HYPER IMPO A21 - Emeric Maybe
    public void updateQueries(){

    }
    public ArrayList<String> getColumn(){
        ArrayList<String> list = new ArrayList<String>();
        if (this.getTableName() == Queries.TABLE_PRODUCT ){
            list.add("id");
            list.add("product_name");
            list.add("stock");
        }
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ){
            list.add("idProducts");
            list.add("discount");
            list.add("sellPrice");
            list.add("purchasePrice");
        }
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ) {
            list.add("capital");
            list.add("income");
            list.add("outcome");
        }

        return list;
    }

}
