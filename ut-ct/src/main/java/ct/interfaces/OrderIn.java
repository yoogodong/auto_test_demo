package ct.interfaces;

import ct.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderIn {
    private String shipmentAddress;
    private String receiverName;
    private String receiverPhone;

    public Order toOrder() {
        return Order.builder()
                .receiverName(receiverName)
                .shipmentAddress(shipmentAddress)
                .receiverPhone(receiverPhone)
                .build();
    }
}
