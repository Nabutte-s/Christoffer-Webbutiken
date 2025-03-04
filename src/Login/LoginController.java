package Login;
import Customer.*;
import Valdating.*;

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
                System.out.println("2. Skapa ny kund");
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
                    case "2":
                        // Anropa service-lagret för att skapa ny kund
                        CustomerService customerService = new CustomerService();
                        System.out.println("ange namn");
                        String userName = scanner.next();
                        if(!NameValidator.isValidName(userName)) throw new IllegalArgumentException("Invalid name: " + userName);
                        System.out.println("ange email");
                        String email2 = scanner.next();
                        if(!EmailValidator.isValidEmail(email2)) throw new IllegalArgumentException("Invalid email: " + email2);
                        System.out.println("ange telefon");
                        int phone = scanner.nextInt();
                        if(!PhoneValidator.isValidPhone(phone)) throw new IllegalArgumentException("Invalid phone number: " + phone);
                        System.out.println("ange address");
                        String address = scanner.next();
                        if(!AdressValidator.isValidAdress(address)) throw new IllegalArgumentException("Invalid address: " + address);
                        System.out.println("ange lösenord");
                        String password2 = scanner.next();
                        if (!PasswordValidator.isValidPassword(password2)) throw new IllegalArgumentException("Invalid password: " + password2);
                        Customer customer1 = new Customer (userName, email2, phone, address, password2);
                        customerService.addNewCustomer(customer1);
                        System.out.println("added " + customer1);
                        break;
                    case "0":
                        System.out.println("Avslutar logga in...");
                        return;
                    default:
                        System.out.println("Ogiltigt val, försök igen");
                }
            } catch (SQLException e) {
                System.out.println("Ett fel uppstod vid logga in: " + e.getMessage());
            } catch (Exception e) {
                // Hantera övriga fel (t.ex. felaktig input)
                System.out.println("Ett oväntat fel uppstod: " + e.getMessage());
                scanner.nextLine(); // Rensa scanner-bufferten vid felinmatning
            }
        }
    }

}
