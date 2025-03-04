package Product;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service-klass för kundhantering
 * Innehåller affärslogik mellan controller och repository
 */
public class ProductService {

    // Repository som hanterar alla databasanrop
    ProductRepository ProductRepository;


    public ProductService() {
        this.ProductRepository = new ProductRepository();
    }

    /**
     * Hämtar och visar alla kunder från databasen
     * Service-lagret kan här:
     * - Formatera utskriften
     * - Lägga till affärslogik (t.ex. filtrera bort inaktiva kunder)
     * - Hantera specialfall (t.ex. om listan är tom)
     *
     * @throws SQLException vid problem med databasanrop
     */
    public void showAllProducts() throws SQLException {
        // Hämta alla pruducter från repository-lagret
        ArrayList<Product> Products = ProductRepository.getAllProducts();

        // Kontrollera om vi har några pruducter att visa
        if (Products.isEmpty()) {
            System.out.println("Inga kunder hittades.");
            return;
        }

        // Skriv ut alla pruducter med tydlig formatering
        System.out.println("\n=== Pruductlista ===");
        for (Product Product : Products) {
            System.out.println("PruductID: " + Product.getId());
            System.out.println("Namn: " + Product.getName());
            System.out.println("beskrivning: " + Product.getDescription());
            System.out.println("price: " + Product.getPrice());
            System.out.println("Antal kvar: " + Product.getStock());
            System.out.println("Tillvärkare: " + Product.getManufacturer());
            System.out.println("-----------------");
        }
    }

    public void findById(int id) throws SQLException {
        Product product = ProductRepository.findById(id);
        if (product != null) {
            System.out.println("-----------------");
            System.out.println("ProductID: " + product.getId());
            System.out.println("Namn: " + product.getName());
            System.out.println("Beskrivning: " + product.getDescription());
            System.out.println("Pris: " + product.getPrice());
            System.out.println("Antal kvar: " + product.getStock());
            System.out.println("-----------------");
        } else {
            System.out.println("Produkten hittades inte.");
        }
    }

    public void findByName(String name) throws SQLException {
        Product product = ProductRepository.findByName(name);
        if (product != null) {
            System.out.println("-----------------");
            System.out.println("ProductID: " + product.getId());
            System.out.println("Namn: " + product.getName());
            System.out.println("Beskrivning: " + product.getDescription());
            System.out.println("Pris: " + product.getPrice());
            System.out.println("Antal kvar: " + product.getStock());
            System.out.println("-----------------");
        } else {
            System.out.println("Produkten hittades inte.");
        }
    }

    public void findByCategory(String category) throws SQLException {
        ArrayList<Product> product = ProductRepository.findByCategory(category);
        if (product.isEmpty()) {
            System.out.println("Inga produkter hittades.");
        }
        System.out.println("\n=== Productlista ===" );
        for (Product Product : product) {
            System.out.println("ProductID: " + Product.getId());
            System.out.println("Namn: " + Product.getName());
            System.out.println("Beskrivning: " + Product.getDescription());
            System.out.println("Pris: " + Product.getPrice());
            System.out.println("Antal kvar: " + Product.getStock());
            System.out.println("-----------------");
        }
    }

    public void updateProductPrice(int productId, double newPrice) throws SQLException {
        System.out.println("Ny pris: " + newPrice);
        ProductRepository.updateProductPrice(productId, newPrice);
    }

}