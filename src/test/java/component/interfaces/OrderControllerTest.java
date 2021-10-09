package component.interfaces;

import component.Application;
import component.domain.Order;
import component.domain.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 */
@SpringBootTest(classes = {Application.class})
@Transactional
class OrderControllerTest {

    @Autowired
    OrderController controller;

    @Autowired
    OrderRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
    }

    @Test
    void searchOrder() {
        Order order = Order.builder()
                .shipmentAddress("address")
                .receiverName("receiver")
                .receiverPhone("13312345678")
                .build();
        Order saved = repository.save(order);

        OrderOut orderOut = controller.getOrder(saved.getId());

        assertThat(orderOut).usingRecursiveComparison().ignoringFields("createdTime")
                .withFailMessage("只应该有一个字段不等").isEqualTo(saved);
        assertThat(orderOut.getCreatedTime().getTime()).isEqualTo(saved.getCreatedTime());
    }
}