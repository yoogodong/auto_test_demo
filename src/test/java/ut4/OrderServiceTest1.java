package ut4;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 演示 mock 对象在单元测试中的应用
 * 注意这里的三段式结构
 */
public class OrderServiceTest1 {

    @Test
    public void testTotalPrice() {
        OrderService orderService = new OrderService();
        OrderDao orderDao = mock(OrderDao.class);
        orderService.setOrderDao(orderDao);
        when(orderDao.getOrdersOfCustomer(anyLong()))
                .thenReturn(Arrays.asList(new Order(1L, 5),new Order(2L, 5)));

        float totalPrice = orderService.totalPayed(1);

        assertEquals(10,totalPrice,0.001);//注意浮点类型没有精确值
        verify(orderDao).clearCache();
    }
}