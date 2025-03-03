package Login;
import Customer.*;
import java.util.Scanner;
import java.sql.SQLException;

public class LoginController {
    LoginService loginService;
    Scanner scanner;

    public LoginController() {
        this.loginService = new LoginService();
        this.scanner = new Scanner(System.in);
    }
    public void run() throws SQLException {
        while (true) {
            try {
                System.out.println("\n=== Logga in ===");
                System.out.println("1. Logga in som kund");
                System.out.println("0. Avsluta");
                System.out.print("Välj ett alternativ: ");
                String select = scanner.nextLine();
                switch (select) {
                    case "1":
                        System.out.print("Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Password: ");
                        String password = scanner.nextLine();
                        boolean test = loginService.loginAsCustomer(email, password);
                        if(test) {
                            System.out.println("Loggin lyckades!");
                            CustomerRepository customerRepository = new CustomerRepository();
                            Customer customer = customerRepository.findByEmail(email);
                            CustomerController customerController = new CustomerController();
                            customerController.run(customer);
                        }else{
                                System.out.println("Loggin misslyckades!");
                        }
                        break;
                    case "0":
                        System.out.println("Avslutar logga in...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {
                System.out.println("Ett fel uppstod vid logga in: " + e.getMessage());
            }

        }
    }

}
