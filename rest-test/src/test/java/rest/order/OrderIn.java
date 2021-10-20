package rest.order;


public class OrderIn {
    private String shipmentAddress;
    private String receiverName;
    private String receiverPhone;

    public OrderIn(String shipmentAddress, String receiverName, String receiverPhone) {
        this.shipmentAddress = shipmentAddress;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
    }

    public String getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(String shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}
