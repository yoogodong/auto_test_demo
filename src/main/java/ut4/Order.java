package ut4;

/**
 * 仅仅为了举例说明依赖的隔离，这并不是个好的实体模型
 */
public class Order {
    private final Long orderId;
    private final float price;

    public Order(Long orderId, float price) {
        this.orderId = orderId;
        this.price=price;
    }

    public float getPrice() {
        return price;
    }

    public Long getOrderId() {
        return orderId;
    }
}
