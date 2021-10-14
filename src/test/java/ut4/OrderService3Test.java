package ut4;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 演示使用 spy 的常用场景
 */
@ExtendWith(MockitoExtension.class)
public class OrderService3Test {

    @Mock
    OrderDao orderDao;

    @InjectMocks @Spy
    OrderService orderService;

    @Test
    public void testTotalPrice() {
        when(orderDao.getOrdersOfCustomer(anyLong()))
                .thenReturn(Arrays.asList(new Order(1L, 5),new Order(2L, 50)));
        when(orderService.isSalesReturn(anyLong())).thenReturn(true);

        float totalPrice = orderService.totalPayed(1);

        assertEquals(0,totalPrice,0.001);//注意浮点类型没有精确值
        verify(orderService,times(2)).isSalesReturn(anyLong());
    }
}