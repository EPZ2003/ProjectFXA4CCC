package WomenShopClasses;

public class Clothes extends Product{
    private Double size;

    public Clothes(String name, Double purchasePrice, Double sellPrice, Double size) {
        super(name, purchasePrice, sellPrice);
        if (34 > size || size > 54)
        {Product.setNumberTotal(Product.getNumberTotal()-1);
            throw new IllegalArgumentException("Wrong size !");}
        else{
        this.size = size;
        }

    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        if (34 > size || size > 54)
            throw new IllegalArgumentException("Wrong size !");
        else{
            this.size = size;
        }
    }

    @Override
    public String toString()
    {
        return super.toString() + " Size : " + size;
    }

    public void applyDiscount()
    {
        this.setSellPrice(this.getSellPrice() * 0.7);
    }

    public void stopDiscount()
    {
        this.setSellPrice(this.getSellPrice() / 0.7);
    }


}
