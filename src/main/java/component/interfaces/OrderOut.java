package component.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import component.domain.Order;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
public class OrderOut {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime createdTime;
    private String shipmentAddress;
    private String receiverName;
    private String receiverPhone;

    public OrderOut(Order order) {
        BeanUtils.copyProperties(order,this);
    }
}
