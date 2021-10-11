package ut4;

/**
 * 仅仅为了举例说明依赖的隔离，这并不是个好的实体模型
 */
public class Order {
    private final float price;

    public Order(float price) {
        this.price=price;
    }

    public float getPrice() {
        return price;
    }
}
