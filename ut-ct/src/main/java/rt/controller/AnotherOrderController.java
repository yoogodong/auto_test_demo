package rt.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/another/orders")
public class AnotherOrderController {


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
        String id = "1";//mock
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

        public double getTotalPrice() {
            return this.totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }

        public LocalDateTime getCreatedTime() {
            return this.createdTime;
        }

        public void setCreatedTime(LocalDateTime createdTime) {
            this.createdTime = createdTime;
        }

        public List<OrderItem> getOrderItemList() {
            return this.orderItemList;
        }

        public void setOrderItemList(List<OrderItem> orderItemList) {
            this.orderItemList = orderItemList;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof OrderOut)) return false;
            final OrderOut other = (OrderOut) o;
            if (!other.canEqual((Object) this)) return false;
            if (Double.compare(this.getTotalPrice(), other.getTotalPrice()) != 0) return false;
            final Object this$createdTime = this.getCreatedTime();
            final Object other$createdTime = other.getCreatedTime();
            if (this$createdTime == null ? other$createdTime != null : !this$createdTime.equals(other$createdTime))
                return false;
            final Object this$orderItemList = this.getOrderItemList();
            final Object other$orderItemList = other.getOrderItemList();
            if (this$orderItemList == null ? other$orderItemList != null : !this$orderItemList.equals(other$orderItemList))
                return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof OrderOut;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            final long $totalPrice = Double.doubleToLongBits(this.getTotalPrice());
            result = result * PRIME + (int) ($totalPrice >>> 32 ^ $totalPrice);
            final Object $createdTime = this.getCreatedTime();
            result = result * PRIME + ($createdTime == null ? 43 : $createdTime.hashCode());
            final Object $orderItemList = this.getOrderItemList();
            result = result * PRIME + ($orderItemList == null ? 43 : $orderItemList.hashCode());
            return result;
        }

        public String toString() {
            return "OrderController.OrderOut(totalPrice=" + this.getTotalPrice() + ", createdTime=" + this.getCreatedTime() + ", orderItemList=" + this.getOrderItemList() + ")";
        }
    }

    public class OrderItem {
        int quantity;
        String name;

        public OrderItem(int quantity, String name) {
            this.quantity = quantity;
            this.name = name;
        }

        public int getQuantity() {
            return this.quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean equals(final Object o) {
            if (o == this) return true;
            if (!(o instanceof OrderItem)) return false;
            final OrderItem other = (OrderItem) o;
            if (!other.canEqual((Object) this)) return false;
            if (this.getQuantity() != other.getQuantity()) return false;
            final Object this$name = this.getName();
            final Object other$name = other.getName();
            if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
            return true;
        }

        protected boolean canEqual(final Object other) {
            return other instanceof OrderItem;
        }

        public int hashCode() {
            final int PRIME = 59;
            int result = 1;
            result = result * PRIME + this.getQuantity();
            final Object $name = this.getName();
            result = result * PRIME + ($name == null ? 43 : $name.hashCode());
            return result;
        }

        public String toString() {
            return "OrderController.OrderItem(quantity=" + this.getQuantity() + ", name=" + this.getName() + ")";
        }
    }
}
