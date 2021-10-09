package component.application;

import component.domain.Order;
import component.domain.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository repository;
    private InventoryAdapter inventoryService;

    public Order getOrder(Long orderId){
        return repository.findById(orderId).orElseThrow(()->new RuntimeException());
    }

    public void create(Order order){
        repository.save(order);
    }


}
