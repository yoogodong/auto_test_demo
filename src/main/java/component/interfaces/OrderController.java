package component.interfaces;

import component.application.OrderService;
import component.application.UnderstockedException;
import component.domain.Order;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {

    private OrderService orderService;

    @PostMapping("/create")
    public void create(@RequestBody OrderIn orderIn) throws UnderstockedException {
        orderService.create(orderIn.toOrder());
    }

    @GetMapping("/{id}")
    public OrderOut getOrder(@PathVariable("id") Long id) {
        Order order = orderService.getOrder(id);
        return new OrderOut(order);
    }

}
