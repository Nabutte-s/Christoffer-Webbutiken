package Order;
import Product.ProductRepository;
import Product.Product;
import Customer.CustomerRepository;
import java.util.ArrayList;

import java.sql.SQLException;

public class OrderService {

    public void buyProduct(int customerId, int productId, int quantity)throws SQLException {
        {
            ProductRepository productRepository = new ProductRepository();
            Product product = productRepository.findById(productId);
            if (product == null) {
                System.out.println("Produkten hittades inte.");
                return;
            }
            if (product.getStock() < quantity) {
                System.out.println("Produkten har inte tillräckligt med lager.");
                return;
            }
            OrderItem orderitem = new OrderItem(product.getId(), quantity, product.getPrice());
            Order order = new Order(customerId, orderitem);
            OrderRepository orderRepository = new OrderRepository();
            orderRepository.buyProduct(order);


        }
    }

    public void showOrderHistory(int customerId) throws SQLException {
        OrderRepository orderRepository = new OrderRepository();
        ArrayList<Order> orders = orderRepository.showOrderHistory(customerId);
        for (Order order : orders) {
            System.out.println("-----------------");
            System.out.println("OrderID: " + order.getCustomerId());
            System.out.println("Datum: " + order.getOrderItem().getOrderDate());
            System.out.println("ProduktID: " + order.getOrderItem().getProductId());
            System.out.println("Antal: " + order.getOrderItem().getQuantity());
            System.out.println("Pris per enhet: " + order.getOrderItem().getUnitPrice());
            System.out.println("-----------------");
        }
    }
}
