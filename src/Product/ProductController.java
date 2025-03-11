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

    /**
     * Huvudloop för produkthantering
     * Visar meny och hanterar användarval
     *
     * @param customer den inloggade kunden
     * @throws SQLException vid problem med databasanrop
     */
    public void run(Customer customer) throws SQLException {
        while (true) {
            try {
                System.out.println("=== Produkthantering ===");
                System.out.println("1. Visa alla produkter");
                System.out.println("2. Visa produkt via ...");
                System.out.println("3. Köp produkt");
                System.out.println("4. Visa Orderhistorik");
                System.out.println("5. Updatera produktprise");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");

                int select = scanner.nextInt();
                OrderService orderService = new OrderService();

                switch (select) {
                    case 1:
                        productService.showAllProducts();
                        break;
                    case 2:
                        System.out.println("=== Visa produkt via ... ===");
                        System.out.println("1. Produkt-ID");
                        System.out.println("2. Produkt-namn");
                        System.out.println("3. Produkt-kategori");
                        System.out.println("0. Avsluta");
                        System.out.print("Välj ett alternativ: ");
                        try {
                            int select2 = scanner.nextInt();
                            switch (select2) {
                                case 1:
                                    System.out.print("Ange produkt-ID: ");
                                    int id = scanner.nextInt();
                                    if(id <= 0) throw new Exception("ID måste vara större än 0");
                                    productService.findById(id);
                                    return;
                                case 2:
                                    System.out.print("Ange produkt-namn: ");
                                    scanner.nextLine();
                                    String name = scanner.nextLine().trim();
                                    productService.findByName(name);
                                    return;
                                case 3:
                                    System.out.print("Ange produkt-kategori: ");
                                    String category = scanner.next();
                                    productService.findByCategory(category);
                                    return;
                                case 0:
                                    System.out.println("Avslutar produkthantering");
                                    return;
                                default:
                                    System.out.println("Ogiltigt val, försök igen");
                            }
                        } catch (Exception e) {
                            System.out.println(e.getMessage());


                        }
                    case 3:
                        System.out.print("Ange produkt-ID: ");
                        int productId = scanner.nextInt();
                        System.out.print("Ange antal: ");
                        int quantity = scanner.nextInt();
                        int customerId = customer.getId();
                        orderService.buyProduct(customerId, productId, quantity);
                        break;
                    case 4:
                        orderService.showOrderHistory(customer.getId());
                        break;
                    case 5:
                        System.out.print("Ange produkt-ID: ");
                        int productId1 = scanner.nextInt();
                        System.out.print("Ange ny pris: ");
                        double newPrice = scanner.nextDouble();
                        productService.updateProductPrice(productId1, newPrice);
                        break;
                    case 0:
                        System.out.println("Avslutar produkthantering");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            }
        catch (Exception e) {
            System.out.println(e.getMessage());
            scanner.nextLine();
        }
    }
    }
}