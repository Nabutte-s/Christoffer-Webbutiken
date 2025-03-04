package Order;

public class OrderItem {
    private int productId;
    private int quantity;
    private int unitPrice;
    private String orderDate;

    public OrderItem(int productId, int quantity, int unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    public OrderItem(int productId, int quantity, int unitPrice, String orderDate) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.orderDate = orderDate;
    }

    public String getOrderDate() {
        return orderDate;
    }
    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public double getTotalPrice() {
        return quantity * unitPrice;
    }
}
