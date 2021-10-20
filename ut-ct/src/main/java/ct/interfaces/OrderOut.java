package ct.interfaces;

import com.fasterxml.jackson.annotation.JsonFormat;
import ct.domain.Order;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class OrderOut {
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;
    private String shipmentAddress;
    private String receiverName;
    private String receiverPhone;

    public OrderOut(Order order) {
        BeanUtils.copyProperties(order,this);
        createdTime=new Date(order.getCreatedTime());
    }
}
