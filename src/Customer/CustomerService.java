package Customer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Service-klass för kundhantering
 * Innehåller affärslogik mellan controller och repository
 */
public class CustomerService {

    // Repository som hanterar alla databasanrop
    CustomerRepository customerRepository;


    public CustomerService() {
        this.customerRepository = new CustomerRepository();
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
    public void showAllUsers() throws SQLException {
        // Hämta alla kunder från repository-lagret
        ArrayList<Customer> customers = customerRepository.getAllCustomers();

        // Kontrollera om vi har några kunder att visa
        if (customers.isEmpty()) {
            System.out.println("Inga kunder hittades.");
            return;
        }

        // Skriv ut alla kunder med tydlig formatering
        System.out.println("\n=== Kundlista ===");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getId());
            System.out.println("Namn: " + customer.getUserName());
            System.out.println("Email: " + customer.getEmail());
            System.out.println("address: " + customer.getAddress());
            System.out.println("telefonnummer: " + customer.getPhone());
            System.out.println("-----------------");
        }
    }


    public void addNewCustomer(Customer customer) throws SQLException {
        customerRepository.addCustomer(customer);
    }
    public void updatePassword(int id, String password) throws SQLException {
        if (password == null) {
            System.out.println("lösenord saknas");
            return;
        }
        customerRepository.updatePassword(id, password);
        System.out.println("nytt lösenord: " + password);
    }

    public void updateName(int id, String userName) throws SQLException {
        if (userName == null) {
            System.out.println("namn saknas");
            return;
        }
        customerRepository.updateName(id, userName);
        System.out.println("nytt namn: " + userName);
    }
    public void updateEmail(int id, String email) throws SQLException {
        if (email == null) {
            System.out.println("email saknas");
            return;
        }
        customerRepository.updateEmail(id, email);
        System.out.println("ny email: " + email);
    }
    public void updateAddress(int id, String address) throws SQLException {
        if (address == null) {
            System.out.println("address saknas");
            return;
        }
        customerRepository.updateAddress(id, address);
        System.out.println("new address: " + address);
    }
    public void updatePhone(int id, int phone) throws SQLException {
        if (phone == 0) {
            System.out.println("telefonnummer saknas");
            return;
        }
        customerRepository.updatePhone(id, phone);
        System.out.println("nytt telefonnummer: " + phone);
    }

    /**
     * Här kan man lägga till fler metoder som t.ex:
     * - deleteCustomer
     */
}