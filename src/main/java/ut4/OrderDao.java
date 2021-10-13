package ut4;

import java.util.List;

public interface OrderDao {
    List<Order> getOrdersOfCustomer(long customerId);

    void createSalesReturn(Long orderId);

    boolean isExist(Long orderId);
}
