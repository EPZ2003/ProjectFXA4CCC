package SQLModule;

import java.sql.SQLException;
import java.util.ArrayList;

public class Queries  {
    public static final String TABLE_PRODUCT = "projetjavafx.table_products";
    public static final String TABLE_PRODUCT_PRICES = "projetjavafx.table_products_prices" ;
    public static final String TABLE_MONEY = "projetjavafx.table_money" ;

    //Use to know which table are related to the query
    private String tableName = "projetjavafx.table_money";

    //For table : tableproducts
    private int id,stock;
    private String productName;
    private Double specialAttribute;

    //For table : money
    private Double capital,income,outcome;

    //For table : products_prices
    private int idProducts;
    private Double sellPrice,purchasePrice;
    private float discount;

    //Constructor for table :  table_products
    public Queries (String productName,int stock, Double specialAttribute) {

        this.productName = productName;
        this.stock = stock;
        this.specialAttribute = specialAttribute;
        this.tableName = "projetjavafx.table_products";
    }

    //Constructor for table :  table_products_prices
    public Queries (float discount,Double sellPrice,Double purchasePrice) {

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
        this.tableName = "projetjavafx.table_money";
    }

    public Double getSpecialAttribute() {
        return specialAttribute;
    }

    public void setSpecialAttribute(Double specialAttribute) {
        this.specialAttribute = specialAttribute;
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

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
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

    public String addRow () {
        if (this.getTableName() == Queries.TABLE_PRODUCT ) return " insert into "+this.getTableName()+" (product_name,stock,specialAttribute) values ("+"'"+this.getProductName()+"'"+","+this.getStock()+","+this.getSpecialAttribute()+")";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " insert into "+this.getTableName()  +" (discount,sellPrice,purchasePrice) values ("+this.getDiscount()+","+this.getSellPrice()+","+this.getPurchasePrice()+")";
        else if (this.getTableName() == Queries.TABLE_MONEY) return " insert into "+this.getTableName()  +" (capital,income,outcome) values ("+this.getCapital()+","+this.getIncome()+","+this.getOutcome()+")";
        else return "";
    }

    public String delete () {
        if (this.getTableName() == Queries.TABLE_PRODUCT ) return " delete from "+this.getTableName()+" where id = "+this.getId()+";";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " delete from "+this.getTableName()+" where id = "+this.getIdProducts()+";";
        else return "";
    }

    public <T> String udpate (T changeValue,String columnName) {
        if (this.getTableName() == Queries.TABLE_PRODUCT )return " update "+this.getTableName()+" set "+"'"+columnName+"'"+"="+"'"+changeValue+"'" +" where id = "+this.getId()+";";
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES) return " update "+this.getTableName()+" set "+"'"+columnName+"'"+"="+ changeValue +" where id_products = "+this.getIdProducts()+";";
        else if (this.getTableName() == Queries.TABLE_MONEY) return " update "+this.getTableName()+" set "+"'"+columnName+"'"+"="+ changeValue;
        else return "";
    }

    public String read (){
        if (this.getTableName() == Queries.TABLE_PRODUCT )return " select * from "+this.getTableName() +" where id = "+this.getId();
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ) return " select * from " +this.getTableName() +" where id_products = "+this.getIdProducts();
        else if (this.getTableName() == Queries.TABLE_MONEY ) return " select * from " +this.getTableName();
        else return "";
    }

    public ArrayList<String> getColumn(){
        ArrayList<String> list = new ArrayList<String>();
        if (this.getTableName() == Queries.TABLE_PRODUCT ){
            list.add("id");
            list.add("product_name");
            list.add("stock");
            list.add("specialAttribute");
        }
        else if (this.getTableName() == Queries.TABLE_PRODUCT_PRICES ){
            list.add("id_products");
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
