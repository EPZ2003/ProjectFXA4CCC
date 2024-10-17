package WomenShopClasses;

public class Accessory extends Product {

    public Accessory(String name, Double purchasePrice, Double sellPrice) {
        super(name, purchasePrice, sellPrice);
        super.numberTotal ++;
    }

    @Override
    public String toString()
    {return super.toString();}

    public void applyDiscount()
    {
        this.setSellPrice(this.getSellPrice() * 0.5);
    }

    public void stopDiscount()
    {
        this.setSellPrice(this.getSellPrice() / 0.5);
    }

}
