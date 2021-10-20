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
        List<Order> orderList = orderDao.getOrdersOfCustomer(customerId);
        float totalPrice = 0;
        for (Order order : orderList) {
            if (!isSalesReturn(order.getOrderId()))
                totalPrice += order.getPrice();
        }
        return totalPrice;
    }

    /**
     * 退货
     */
    public void salesReturn(Long orderId) {
        if (orderDao.isExist(orderId))
            orderDao.createSalesReturn(orderId);
        else
            throw new RuntimeException("order dont exist");
    }


    /**
     * 检查订单是否被退货
     *
     * @param orderId
     */
    public boolean isSalesReturn(Long orderId) {
        //这里省去了实际的逻辑
        return false;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
