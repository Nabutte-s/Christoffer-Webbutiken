package Product;
import Customer.Customer;
import Order.OrderService;
import java.sql.SQLException;
import java.util.Scanner;

public class ProductController {
    // Service-lager för kundhantering, hanterar affärslogik
    ProductService productService;

    // Scanner för användarinput
    Scanner scanner;

    public ProductController() {
        // Skapa instanser av nödvändiga objekt
        this.productService = new ProductService();
        this.scanner = new Scanner(System.in);
    }

    public void run(Customer customer) throws SQLException {
        while (true) {
            System.out.println("=== Produkthantering ===");
            System.out.println("1. Visa alla produkter");
            System.out.println("2. Visa produkt via ID");
            System.out.println("3. Visa produkt via namn");
            System.out.println("4. Visa produkt via kategori");
            System.out.println("5. Köp produkt");
            System.out.println("6. Visa Orderhistorik");
            System.out.println("0. Avsluta");
            System.out.print("Välj ett alternativ: ");

            int select = scanner.nextInt();
            OrderService orderService = new OrderService();

            switch (select) {
                case 1:
                    productService.showAllProducts();
                    break;
                case 2:
                    System.out.print("Ange produkt-ID: ");
                    int id = scanner.nextInt();
                    productService.findById(id);
                    break;
                case 3:
                    System.out.print("Ange produkt-namn: ");
                    scanner.nextLine();
                    String name = scanner.nextLine().trim();
                    productService.findByName(name);
                    break;
                case 4:
                    System.out.print("Ange produkt-kategori: ");
                    String category = scanner.next();
                    productService.findByCategory(category);
                    break;
                case 5:
                    System.out.print("Ange produkt-ID: ");
                    int productId = scanner.nextInt();
                    System.out.print("Ange antal: ");
                    int quantity = scanner.nextInt();
                    int customerId = customer.getId();
                    orderService.buyProduct(customerId, productId, quantity);
                    break;
                case 6:
                    orderService.showOrderHistory(customer.getId());
                    break;
                case 0:
                    System.out.println("Avslutar produkthantering");
                    return;
                default:
                    System.out.println("Ogiltigt val, försök igen");
            }
        }
    }

}
