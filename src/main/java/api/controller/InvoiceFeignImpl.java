package api.controller;

import org.springframework.stereotype.Component;


/**
 * 仅仅为了占位演示，所以没有使用 @FeignClient 相关的标注
 * 用以表示需要远程调用另一个微服务
 */
@Component
public class InvoiceFeignImpl implements InvoiceFeign {
    @Override
    public String createInvoice(Double totalPrice) {
        return null;
    }
}
