package Customer;

import Product.ProductController;

import java.sql.SQLException;
import java.util.Scanner;


/**
 * Controller-klass för kundhantering
 * Hanterar användarinteraktion för kundrelaterade operationer
 */
public class CustomerController {

    // Service-lager för kundhantering, hanterar affärslogik
    CustomerService customerService;

    // Scanner för användarinput
    Scanner scanner;

    /**
     * Konstruktor för Customer.CustomerController
     * Initierar service och scanner
     */
    public CustomerController() {
        // Skapa instanser av nödvändiga objekt
        this.customerService = new CustomerService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Huvudloop för kundhantering
     * Visar meny och hanterar användarval
     */
    public void run(Customer customer) {
        while (true) {
            try {
                // Skriv ut menyalternativ direkt i run-metoden för tydlighet
                System.out.println("\n=== Kundhantering ===");
                System.out.println("1. Visa alla kunder");
                System.out.println("2, Skapa ny kund");
                System.out.println("3. Uppdatera konto");
                System.out.println("4. Varor");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");

                // Läs användarens val
                int select = scanner.nextInt();

                // Hantera användarens val
                switch (select) {
                    case 1:
                        // Anropa service-lagret för att visa alla kunder
                        customerService.showAllUsers();
                        break;
                    case 2:
                        // Anropa service-lagret för att skapa ny kund
                        System.out.println("ange namn");
                        String userName = scanner.next();
                        System.out.println("ange email");
                        String email = scanner.next();
                        System.out.println("ange telefon");
                        int phone = scanner.nextInt();
                        System.out.println("ange address");
                        String address = scanner.next();
                        System.out.println("ange lösenord");
                        String password = scanner.next();
                        Customer customer1 = new Customer (userName, email, phone, address, password);
                        customerService.addNewCustomer(customer);
                        System.out.println("added " + customer);
                        break;
                    case 3:
                        System.out.println("\n=== Uppdatera konto ===");
                        System.out.println("1. Uppdatera namn");
                        System.out.println("2. Uppdatera email");
                        System.out.println("3. Uppdatera telefon");
                        System.out.println("4. Uppdatera address");
                        System.out.println("5. Uppdatera lösenord");
                        System.out.println("0. Avsluta");
                        System.out.print("Välj ett alternativ: ");
                        select = scanner.nextInt();
                      switch (select) {
                        case 1:
                            //uppdatera namn
                            System.out.println("ange nytt namn");
                            String newName = scanner.next();
                            customerService.updateName(customer.getId(),newName);
                            break;
                        case 2:
                            //uppdatera email
                            System.out.println("ange ny email");
                            String newEmail = scanner.next();
                            customerService.updateEmail(customer.getId(), newEmail);
                            break;
                        case 3:
                            //uppdatera telefon
                            System.out.println("ange ny telefon");
                            int newPhone = scanner.nextInt();
                            customerService.updatePhone(customer.getId(), newPhone);
                            break;
                        case 4:
                            //uppdatera address
                            System.out.println("ange ny address");
                            String newAddress = scanner.next();
                            customerService.updateAddress(customer.getId(),newAddress);
                            break;
                        case 5:
                            // uppdatera lösenord
                            System.out.println("ange nytt lösenord");
                            String newPassword = scanner.next();
                            customerService.updatePassword(customer.getId(), newPassword);
                            break;
                        case 0:
                            System.out.println("Avslutar uppdatering...");
                            break;
                        default:
                            System.out.println("Ogiltigt val, försök igen");

                      }
                    case 4:
                        ProductController productController = new ProductController();
                        productController.run(customer);
                    case 0:
                        System.out.println("Avslutar kundhantering...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {
                // Hantera databasfel
                System.out.println("Ett fel uppstod vid databasanrop: " + e.getMessage());
            } catch (Exception e) {
                // Hantera övriga fel (t.ex. felaktig input)
                System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
                scanner.nextLine(); // Rensa scanner-bufferten vid felinmatning
            }
        }
    }
}