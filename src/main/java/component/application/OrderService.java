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
        return repository.findById(orderId).orElseThrow(()->new RuntimeException());
    }

    public void create(Order order){
        inventoryAdapter.deduct("234234",1);
        Order saved = repository.save(order);
        log.info("保存订单{}",saved);
    }


}
