package ut4;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersOfCustomer(long customerId);

    // 仅仅为了举例
    void clearCache();
}
