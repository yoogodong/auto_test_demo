package ut4;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 演示 mock 对象在单元测试中的两种用途
 * 注意这里的三段式结构
 */
public class OrderServiceTest1 {


    private OrderService orderService;
    private OrderDao orderDao;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        orderDao = mock(OrderDao.class);
        orderService.setOrderDao(orderDao);
        when(orderDao.getOrdersOfCustomer(anyLong()))
                .thenReturn(Arrays.asList(new Order(1L, 5),new Order(2L, 5)));
    }

    /**
     * 展示使用 mock 对象来隔离实际的依赖
     */
    @Test
    void testTotalPrice() {
        float totalPrice = orderService.totalPayed(1);

        assertEquals(10,totalPrice,0.001);//注意浮点类型没有精确值
    }

    /**
     * 展示使用 mock 对象来对没有返回值的方法做行为验证
     */
    @Test
    void testSalesReturn() {
        when(orderDao.isExist(anyLong())).thenReturn(true);

        orderService.salesReturn(anyLong());

        verify(orderDao).createSalesReturn(anyLong());
    }
}