package component.application;

import component.domain.Order;
import component.domain.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository repository;
    private InventoryAdapter inventoryAdapter;

    public Order getOrder(Long orderId){
        return repository.findById(orderId).orElseThrow(RuntimeException::new);
    }

    public void create(Order order) throws UnderstockedException {
        inventoryAdapter.deduct("234234",1);// 这里省略了从订单中拿到 sku 的逻辑
        Order saved = repository.save(order);
        log.info("保存订单{}",saved);
    }
}
