package Login;
import Customer.*;
import java.sql.SQLException;

public class LoginService {
    CustomerRepository customerRepository;

    public LoginService() {
        this.customerRepository = new CustomerRepository();
    }

    public boolean loginAsCustomer(String email, String password) throws SQLException {
        //System.out.println("E-postadress: " + email);
        //System.out.println("Lösenord: " + password);

        Customer customer = customerRepository.findByEmail(email);
        //System.out.println("Kunddata: " + customer);

        if (customer != null) {
            //System.out.println("Kunddata finns");
            if (customer.getPassword() != null) {
                //System.out.println("Lösenord finns");
                if (customer.getPassword().equals(password)) {
                    System.out.println("Lösenord matchar");
                    return true;
                } else {
                    System.out.println("Lösenord matchar inte");
                }
            } else {
                System.out.println("Lösenord saknas");
            }
        } else {
            System.out.println("Kunddata saknas");
        }

        return false;
    }
}
