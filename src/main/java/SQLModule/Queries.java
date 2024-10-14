package SQLModule;

public class Queries {
    private static int id = 0;
    private int stock;
    private String name;
    //";

    public Queries (int id, int stock, String name) {
        this.id = id;
        this.stock = stock;
        this.name = name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Queries.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String addProduct (int id, int stock, String name) {
            return "Insert into projetjavafx.tableproducts values (" +id+","+name+","+ stock+")";
    }

}
