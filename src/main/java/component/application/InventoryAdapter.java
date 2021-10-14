package component.application;

public interface InventoryAdapter {
    void deduct(String sku,int quantity) throws UnderStockException;
}
