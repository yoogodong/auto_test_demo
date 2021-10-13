package ut4;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 演示使用标注来简化 mock api
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest2 {

    @Mock
    OrderDao orderDao;

    @InjectMocks //会创建实例，并注入 mock 对象，依赖于set方法
    OrderService orderService;

    @Test
    public void testTotalPrice() {
        when(orderDao.getOrdersOfCustomer(anyLong()))
                .thenReturn(Arrays.asList(new Order(1L, 5),new Order(2L, 5)));

        float totalPrice = orderService.totalPayed(1);

        assertEquals(10,totalPrice,0.001);//注意浮点类型没有精确值
        verify(orderDao).clearCache();
    }
}