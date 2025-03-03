package Customer;
import Super.User;
/**
 * Klass som representerar en kund i webbshopen
 * Används för att hantera kunddata mellan databasen och applikationen
 */
public class Customer{
    public Customer(int Id, String userName, String email, int phone, String address, String password) {
        this.Id = Id;
        this.userName = userName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;
    }

    public Customer(String userName, String email, int phone, String address, String password) {
        this(0, userName, email, phone, address, password);
    }


    // Privata fält för att uppnå inkapsling
    private int Id;
    private String userName;
    private String email;
    private int phone;
    private String address;
    private String password;

    public int getId() {

        return Id;
    }


    public String getUserName() {
        return userName;
    }


    public String getEmail() {
        return email;
    }



    public int getPhone() {
        return phone;
    }



    public String getAddress() {
        return address;
    }



    public String getPassword() {
        return password;
    }


    public String toString() {
        return "Customer.Customer{" +
                "customerId=" + Id +
                ", name='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", password='" + getPassword() + '\'' +
                '}';
    }
}