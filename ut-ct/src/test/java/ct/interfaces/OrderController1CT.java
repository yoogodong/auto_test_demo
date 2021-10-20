package ct.interfaces;

import ct.Application;
import ct.domain.Order;
import ct.domain.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 演示组件测试的基本结构
 * 标注 SpringBootTest 使得 Spring 能管理测试实例
 * 标注 Transactional 保证每个测试后都会回滚事务
 * 测试要独立， 所以各自负责DB数据的初始化或清理
 */
@SpringBootTest(classes = {Application.class})
@Transactional
class OrderController1CT {

    @Autowired
    OrderController controller;
    @Autowired
    OrderRepository repository;

    private Order saved;

    /**
     * 测试运行前清理相关的表，以保证测试独立不受影响
     */
    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();

        Order order1 = Order.builder()
                .shipmentAddress("address")
                .receiverName("receiver")
                .receiverPhone("13312345678")
                .build();
        saved = repository.save(order1);
    }

    @Test
    void testGetOrder() {
        OrderOut orderOut = controller.getOrder(saved.getId());

        assertThat(orderOut).usingRecursiveComparison().ignoringFields("createdTime")
                .withFailMessage("只应该有一个字段不等").isEqualTo(saved);
        assertThat(orderOut.getCreatedTime().getTime()).isEqualTo(saved.getCreatedTime());
    }
}