package ut.third.mockito;

import java.util.List;

public class OrderService {

    private OrderDao orderDao;

    public float totalPrice(){
        List<Order> orderList = orderDao.getOrderList();
        float totalPrice=0;
        for (Order order:orderList){
            totalPrice+=order.getPrice();
        }
        return  totalPrice;
    }

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
