package Product;

import java.sql.*;
import java.util.ArrayList;

/**
 * Repository-klass för kundhantering
 * Hanterar alla databasoperationer för Product.Product-entiteten
 * Innehåller även databasanslutning för att göra koden tydligare
 */
public class ProductRepository {

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
    public ArrayList<Product> getAllProducts() throws SQLException {
        ArrayList<Product> Products = new ArrayList<>();

        // try-with-resources stänger automatiskt Connection, Statement och ResultSet
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT p.product_id, p.name, p.description, p.price," +
                     " p.stock_quantity, m.name AS manufacturer_name " +
                     "FROM products p JOIN manufacturers m ON p.manufacturer_id = m.manufacturer_id;")) {


            // Loopa igenom alla rader från databasen
            while (rs.next()) {
                Product Product = new Product(
                        rs.getInt("product_id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("manufacturer_name")

                );
                Products.add(Product);
            }
        }
        return Products;
    }




    public Product findById(int id) throws SQLException {
        String sql = "SELECT * FROM Products WHERE Product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Product(rs.getInt("product_id"), rs.getString("name"),
                    rs.getString("description"), rs.getInt("price"),
                    rs.getInt("stock_quantity"));
        }
    }

    public Product findByName(String name) throws SQLException {
        String sql = "SELECT * FROM Products WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();

            if (!rs.next()) {
                return null;
            }
            return new Product(rs.getInt("product_id"), rs.getString("name"),
                    rs.getString("description"), rs.getInt("price"),
                    rs.getInt("stock_quantity"));
        }
    }

    public ArrayList<Product> findByCategory(String category) throws SQLException {
        ArrayList<Product> products = new ArrayList<>();
        String sql = "SELECT p.product_id, p.name, p.description, p.price, p.stock_quantity, c.name AS category_name FROM products p JOIN products_categories pc ON p.product_id = pc.product_id JOIN categories c ON pc.category_id = c.category_id where c.name = ?";
                try (Connection conn = DriverManager.getConnection(URL);
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

                    pstmt.setString(1, category);

                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        Product product = new Product(
                                rs.getInt("product_id"),
                                rs.getString("name"),
                                rs.getString("description"),
                                rs.getInt("price"),
                                rs.getInt("stock_quantity"),
                                rs.getString("category_name")
                        );
                        products.add(product);
                    }

/*                    if (!rs.next()) {
                        return null;
                    }*/
                    return products;
                }
    }

    public void updateProductPrice(int id, double newprice) throws SQLException {
        String sql = "UPDATE products SET price = ? WHERE product_id = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, newprice);
            pstmt.setInt(2, id);

            pstmt.executeUpdate();
        }
    }



}




