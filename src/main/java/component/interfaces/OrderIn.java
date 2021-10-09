package component.interfaces;

import component.domain.Order;
import lombok.Data;

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
