package ut4;

import java.util.List;

/**
 * 1，单元测试应该隔离依赖，以便聚焦测试范围，避免范围之外的影响
 */
public class OrderService {

    private OrderDao orderDao;

    /**
     * 计算一个客户所有花费
     */
    public float totalPayed(long customerId) {
        orderDao.clearCache();
        List<Order> orderList = orderDao.getOrdersOfCustomer(customerId);
        float totalPrice = 0;
        for (Order order : orderList) {
            if (!isReturned(order))
                totalPrice += order.getPrice();
        }
        return totalPrice;
    }

    /**
     * 检查订单是否被退货
     */
    public boolean isReturned(Order order) {
        //这里省去了实际的逻辑
        return false;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
