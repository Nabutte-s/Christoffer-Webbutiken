package Order;


public class Order {

    private int customerId;
    private OrderItem orderItem;
    private String orderDate;

    public Order(int customerId, OrderItem orderItem) {
        this.customerId = customerId;
        this.orderItem = orderItem;
    }
    public Order(int customerId, int productId, int quantity, int unitPrice, String orderDate) {
        this.customerId = customerId;
        this.orderItem = new OrderItem(productId, quantity, unitPrice, orderDate);
    }



    public String getOrderDate() {
        return orderDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getUnitPrice() {
        return orderItem.getUnitPrice();
    }

    public int getQuantity() {
        return orderItem.getQuantity();
    }

    public int getProductId() {
        return orderItem.getProductId();
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }
}