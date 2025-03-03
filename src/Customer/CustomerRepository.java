package Customer;

import java.sql.*;
import java.util.ArrayList;

/**
 * Repository-klass för kundhantering
 * Hanterar alla databasoperationer för Customer.Customer-entiteten
 * Innehåller även databasanslutning för att göra koden tydligare
 */
public class CustomerRepository {

    /**
     * URL till SQLite-databasen
     * Denna används i varje metod för att ansluta till databasen
     */
    private static final String URL = "jdbc:sqlite:webbutiken.db";

    /**
     * Hämtar alla kunder från databasen
     * Skapar en ny anslutning, hämtar data och stänger anslutning automatiskt
     *
     * @return ArrayList med alla kunder
     * @throws SQLException vid problem med databasanrop
     */
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        ArrayList<Customer> customers = new ArrayList<>();

        // try-with-resources stänger automatiskt Connection, Statement och ResultSet
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers")) {

            // Loopa igenom alla rader från databasen
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("customer_id"),     // Hämta ID från customer_id kolumnen
                        rs.getString("name"),          // Hämta namn
                        rs.getString("email"),         // Hämta email
                        rs.getInt("phone"),
                        rs.getString("address"),
                        rs.getString("password")       // Hämta lösenord
                );
                customers.add(customer);
            }
        }
        return customers;
    }


    public Customer findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM customers WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, email);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone"), rs.getString("address"), rs.getString("password") );
        }
    }

    public Customer findById(int id) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("email"), rs.getInt("phone"), rs.getString("address"), rs.getString("password") );
        }
    }
    public Customer addCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customers (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, customer.getUserName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setInt(3, customer.getPhone());
            pstmt.setString(4, customer.getAddress());
            pstmt.setString(5, customer.getPassword());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return customer;
            } else {
                return null;
            }
        }
    }

    public Customer updateName(int customerId, String newName) throws SQLException {
        String sql = "UPDATE customers SET name = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newName);
            pstmt.setInt(2, customerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return findById(customerId);
            } else {
                return null;
            }
        }
    }

    public Customer updateEmail(int customerId, String newEmail) throws SQLException {
        String sql = "UPDATE customers SET email = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newEmail);
            pstmt.setInt(2, customerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return findById(customerId);
            } else {
                return null;
            }
        }
    }
    public Customer updatePassword(int customerId, String newPassword) throws SQLException {
        String sql = "UPDATE customers SET password = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newPassword);
            pstmt.setInt(2, customerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return findById(customerId);
            } else {
                return null;
            }
        }
    }

    public Customer updateAddress(int customerId, String newAddress) throws SQLException {
        String sql = "UPDATE customers SET address = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newAddress);
            pstmt.setInt(2, customerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return findById(customerId);
            } else {
                return null;
            }
        }
    }

    public Customer updatePhone(int customerId, int newPhone) throws SQLException {
        String sql = "UPDATE customers SET phone = ? WHERE customer_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, newPhone);
            pstmt.setInt(2, customerId);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                return findById(customerId);
            } else {
                return null;
            }
        }
    }




    /**
     * Här kan fler metoder läggas till som t.ex:
     * - addCustomer
     * - getCustomerById
     * - updateCustomer
     * - deleteCustomer
     * - findCustomerByEmail
     *
     * Varje metod kommer följa samma mönster:
     * 1. Skapa Connection med DriverManager.getConnection(URL)
     * 2. Skapa Statement eller PreparedStatement
     * 3. Utför databasoperationen
     * 4. Hantera resultatet
     * 5. Låt try-with-resources stänga alla resurser
     */
}