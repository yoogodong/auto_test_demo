package ut4;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * 演示 spy
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceTest3 {

    @Mock
    OrderDao orderDao;

    @InjectMocks @Spy
    OrderService orderService;

    @Test
    public void testTotalPrice() {
        when(orderDao.getOrdersOfCustomer(anyLong()))
                .thenReturn(Arrays.asList(new Order(5),new Order(50)));
        when(orderService.isReturned(any())).thenReturn(true);

        float totalPrice = orderService.totalPayed(1);

        assertEquals(0,totalPrice,0.001);//注意浮点类型没有精确值
        verify(orderService,times(2)).isReturned(any());
    }
}