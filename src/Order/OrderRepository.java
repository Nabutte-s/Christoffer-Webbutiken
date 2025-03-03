package Order;

import Customer.Customer;
import Order.Order;
import java.sql.*;
import java.util.ArrayList;

public class OrderRepository {

    private static final String URL = "jdbc:sqlite:webbutiken.db";

    public Order buyProduct(Order order) {
        String sql = "INSERT INTO orders (customer_id) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, order.getCustomerId());
            System.out.println("Order added with ID: "+ order.getCustomerId());
            pstmt.executeUpdate();
            String sql2 = "INSERT INTO orders_products (product_id, order_id, quantity, unit_price) VALUES (?, last_insert_rowid(), ?, ?)";
            PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setInt(1, order.getProductId());
            pstmt2.setInt(2, order.getQuantity());
            pstmt2.setInt(3, order.getUnitPrice());
            System.out.println("Product added with ID: "+ order.getProductId());
            String sql3 = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE product_id = ?;";
            PreparedStatement pstmt3 = conn.prepareStatement(sql3);
            pstmt3.setInt(1, order.getQuantity());
            pstmt3.setInt(2, order.getProductId());
            pstmt3.executeUpdate();

            return order;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public ArrayList<Order> showOrderHistory(int customerId) {
        ArrayList<Order> orders = new ArrayList<>();
        String sql = "SELECT op.order_product_id, op.product_id, op.quantity, op.unit_price, o.order_date FROM orders_products op JOIN orders o ON op.order_id = o.order_id WHERE o.customer_id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt("order_product_id"),
                        rs.getInt("product_id"),
                        rs.getInt("quantity"),
                        rs.getInt("unit_price"),
                        rs.getString("order_date")
                );
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orders;
    }



}
//buyProduct(customerId, productId, quantity)