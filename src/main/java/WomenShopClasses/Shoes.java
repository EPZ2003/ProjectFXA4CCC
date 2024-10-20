package WomenShopClasses;

public class Shoes extends Product {
    private Double shoeSize;

    public Shoes(String name, Double purchasePrice, Double sellPrice, Double shoeSize) {
        super(name, purchasePrice, sellPrice);
        super.numberTotal ++;
        if (36 > shoeSize || shoeSize > 50)
        {Product.setNumberTotal(Product.getNumberTotal()-1);
            throw new IllegalArgumentException("Wrong shoe size !");
            }
        else{
            this.shoeSize = shoeSize;
        }

    }

    public double getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Double shoeSize) {
        if (36 > shoeSize || shoeSize > 50)
            throw new IllegalArgumentException("Wrong shoe size !");
        else{
            this.shoeSize = shoeSize;
        }
    }

    @Override
    public String toString() {return super.toString() + " Shoe Size : " + shoeSize;}

    public void applyDiscount()
    {
        this.setSellPrice(this.getSellPrice() * 0.8);
    }

    public void stopDiscount()
    {
        this.setSellPrice(this.getSellPrice() / 0.8);
    }

}


