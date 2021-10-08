package ut.mock;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class OrderServiceTest {

    @Test
    public void should_get_10_if_two_order_with_5() {
        OrderService orderService = new OrderService();
        OrderDao orderDao = mock(OrderDao.class);
        orderService.setOrderDao(orderDao);
        when(orderDao.getOrderList())
                .thenReturn(Arrays.asList(new Order(5),new Order(5)));

        float totalPrice = orderService.totalPrice();

        assertEquals(10,totalPrice,0.001);
        verify(orderDao).getOrderList();
    }
}