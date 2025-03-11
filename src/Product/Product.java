package Product;

/**
 * Klass som representerar en Product i webbshopen
 * Används för att hantera Productdata mellan databasen och applikationen
 */
public class Product{
    public Product(int Id, String name, String description, int price, int stock,String manufacturer) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.manufacturer = manufacturer;
    }

    public Product(int Id, String name, String description, int price, int stock) {
        this.Id = Id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }


    private int Id;
    private String manufacturer;
    private String name;
    private String description;
    private int price;
    private int stock;

    public int getId() {
        return Id;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

}