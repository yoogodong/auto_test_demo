package component.interfaces;

import component.BaseCT;
import component.application.InventoryAdapter;
import component.application.UnderstockedException;
import component.domain.Order;
import component.domain.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 演示外部依赖的 mock
 * 标注 MockBean 表达了两个意思，这是一个 bean ， 这是个mock 对象
 */
class OrderController2CT extends BaseCT {

    @Autowired
    OrderController controller;
    @Autowired
    OrderRepository repository;

    @MockBean
    InventoryAdapter inventoryAdapter;


    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
    }

    @Test
    void testGetOrder() {
        Order order1 = Order.builder()
                .shipmentAddress("address")
                .receiverName("receiver")
                .receiverPhone("13312345678")
                .build();
        Order saved = repository.save(order1);
        OrderOut orderOut = controller.getOrder(saved.getId());

        assertThat(orderOut).usingRecursiveComparison().ignoringFields("createdTime")
                .withFailMessage("只应该有一个字段不等").isEqualTo(saved);
        assertThat(orderOut.getCreatedTime().getTime()).isEqualTo(saved.getCreatedTime());
    }

    @Test
    void testCreateOrder() throws UnderstockedException {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doNothing().when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        controller.create(in);

        final List<Order> orders = repository.findAll();
        assertThat(orders).hasSize(1).element(0).usingRecursiveComparison()
                .ignoringFields("id", "createdTime").isEqualTo(in.toOrder());
    }

    @Test
    void testCreateOrder_Understocked() throws UnderstockedException {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doThrow(UnderstockedException.class)
                .when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        assertThrows(UnderstockedException.class, () -> controller.create(in));

        assertThat(repository.findAll()).hasSize(0);
    }
}