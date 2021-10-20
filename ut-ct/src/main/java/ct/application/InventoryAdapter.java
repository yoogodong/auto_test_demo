package ct.application;

public interface InventoryAdapter {
    void deduct(String sku,int quantity) throws UnderstockedException;
}
