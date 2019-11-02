package ut.mock;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class OrderServiceTest {

    @Tested
    OrderService orderService;

    @Injectable
    OrderDao orderDao;

    @Test
    public void should_get_10_if_two_order_with_5() {

        final Order order1 = new Order(3);
        final Order order2 = new Order(7);
        new Expectations(){{
            orderDao.getOrderList();result= Arrays.asList(order1,order2);
        }};

        float totalPrice = orderService.totalPrice();

        assertEquals(10,totalPrice,0.001);
    }
}