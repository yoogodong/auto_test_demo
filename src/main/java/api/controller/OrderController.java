package api.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    InvoiceFeign invoiceFeign;

    @GetMapping("/{1}")
    public OrderOut getOrder(@PathVariable("1") Long id) {
        System.out.println("id = " + id);
        if (id == 5)
            throw new NullPointerException();
        else
            return new OrderOut(3250)
                    .addOrderItem(new OrderItem(2, "苹果"))
                    .addOrderItem(new OrderItem(5, "橘子"));
    }


    @ExceptionHandler({NullPointerException.class})
    public List<String> handle(HttpServletResponse response) {
        response.setStatus(404);
        return Arrays.asList("对象不存在", "NullPointException");
    }


    /**
     * 代表订单保存时调用开发票的服务
     */
    @PostMapping("/save")
    public Map save(@RequestParam("totalPrice") Double totalPrice) {
        String id = invoiceFeign.createInvoice(totalPrice);
        HashMap<String, Object> map = new HashMap<>();

        if (id == null) {
            map.put("status", "fail");
        } else {
            map.put("invoiceId", id);
            map.put("status", "success");
        }
        return map;
    }

    /*
     * 代表应答对象
     * */
    @Data
    public class OrderOut {
        private double totalPrice;
        @JsonFormat(pattern = "yyyy-MM-dd hh-mm-ss")
        private LocalDateTime createdTime;
        private List<OrderItem> orderItemList;


        public OrderOut(double totalPrice) {
            this.totalPrice = totalPrice;
            createdTime = LocalDateTime.now();
            orderItemList = new ArrayList<>();
        }

        public OrderOut addOrderItem(OrderItem orderItem) {
            orderItemList.add(orderItem);
            return this;
        }
    }

    @Data
    public class OrderItem {
        int quantity;
        String name;

        public OrderItem(int quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }
    }
}
