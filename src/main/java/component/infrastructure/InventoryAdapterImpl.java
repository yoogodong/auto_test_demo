package component.infrastructure;

import component.application.InventoryAdapter;
import component.application.UnderStockException;
import org.springframework.stereotype.Component;

/**
 * 代表要调用库存服务接口
 */
@Component
public class InventoryAdapterImpl implements InventoryAdapter {
    @Override
    public void deduct(String sku, int quantity)throws UnderStockException {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put("url",sku,quantity);
        System.out.println("通过网络，调用库存服务接口");
    }
}
