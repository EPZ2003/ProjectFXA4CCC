package WomenShopClasses;

public abstract class Product implements Discount, Comparable<Object> {

private int id;
protected static int numberTotal = 1;
private String name;
private Double purchasePrice;
private Double sellPrice;
//private double discPrice;
private int nbItems;
private static Double capital = 100000.00;
private static Double income = 0.00;
private static Double cost = 0.00;
//private static float income;

    public static Double getCapital() {
        return capital;
    }

    public static Double getIncome() {
        return income;
    }

    public static Double getCost() {
        return cost;
    }
/*
public product(int id, String name, double price, double discPrice, int nbItems) {
    this.name = name;
    this.price = price;
   // this.discPrice = discPrice;
    this.nbItems = nbItems;
    this.id = id +1;
}
*/

    public Product(String name, Double purchasePrice, Double sellPrice) {
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.sellPrice = sellPrice;
        this.nbItems = 0;
        this.id = numberTotal;
        numberTotal ++;
    }
public int getId()
        {return id;}

    public String getName()
    {return name;}

    public static void setNumberTotal(int numberTotal) {
        Product.numberTotal = numberTotal;
    }

    public static int getNumberTotal() {
        return numberTotal;
    }

    public Double getPurchasePrice()
    {
        return purchasePrice;
    }

    public Double getSellPrice()
    {
        return sellPrice;
    }
/*
    public float getDiscPrice()
    {return discPrice;}
*/
    public int getNbItems()
    {
        return nbItems;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setSellPrice(Double sellPrice) {
        this.sellPrice = sellPrice;
    }
/*
    public void setDiscPrice(float discPrice) {
        this.discPrice = discPrice;
    }
*/
    public void setNbItems(int nbItems) {
        this.nbItems = nbItems;
    }

    @Override
    public String toString()
    {return "id : " + id + " name : " + name + " Purchase price : " + purchasePrice + " Sell price : " + sellPrice + " nbItems : " + nbItems;}

   public void sell(int number)
   {
       if (nbItems - number <= 0 && number > 0)
           throw new IllegalArgumentException("Product Unavailable");
       if (number < 0)
           throw new IllegalArgumentException("Negative number");
       else {
           this.nbItems -= number;
           income += number * sellPrice;
           capital += number * sellPrice;
       }

   }

   public void purchase(int number)
   {
       if (purchasePrice < 0)
       throw new IllegalArgumentException("Negative Price");
       if (number < 0)
       throw new IllegalArgumentException("Negative Number");
       else
       {
           this.nbItems += number;
           cost += number * purchasePrice;
           capital -= number * purchasePrice;
       }
   }
   @Override
   public int compareTo(Object other)
   {
       Product p = (Product) other;
    return this.purchasePrice.compareTo(p.purchasePrice);
   }



}
