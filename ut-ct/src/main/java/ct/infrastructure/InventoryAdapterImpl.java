package ct.infrastructure;

import ct.application.InventoryAdapter;
import ct.application.UnderstockedException;
import org.springframework.stereotype.Component;

/**
 * 代表要调用库存服务接口
 */
@Component
public class InventoryAdapterImpl implements InventoryAdapter {
    @Override
    public void deduct(String sku, int quantity)throws UnderstockedException {
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put("url",sku,quantity);
        System.out.println("通过网络，调用库存服务接口");
    }
}
