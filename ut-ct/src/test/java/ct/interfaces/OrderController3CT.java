package ct.interfaces;

import com.alibaba.fastjson.JSONObject;
import ct.BaseCT;
import ct.application.InventoryAdapter;
import ct.application.UnderstockedException;
import ct.domain.Order;
import ct.domain.OrderRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * 使用 MockMvc 测试 rest 接口
 */
@AutoConfigureMockMvc
class OrderController3CT extends BaseCT {

    @Autowired
    OrderController controller;
    @Autowired
    OrderRepository repository;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    InventoryAdapter inventoryAdapter;


    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
    }

    /**
     * API 结构：mockMvc.perform(x).andExpect(y)
     * x 用于构造请求, 使用 MockMvcRequestBuilder
     * y 用于验证应答, 使用 MockMvcResultMatcher
     */
    @Test
    void testGetOrder() throws Exception {
        Order order1 = Order.builder()
                .shipmentAddress("address")
                .receiverName("receiver")
                .receiverPhone("13312345678")
                .build();
        Order saved = repository.save(order1);

        mockMvc.perform(get("/orders/" + saved.getId()).accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("shipmentAddress").value(order1.getShipmentAddress()))
                .andExpect(jsonPath("receiverName").value(order1.getReceiverName()))
                .andExpect(jsonPath("receiverPhone").value(order1.getReceiverPhone()))
                .andExpect(jsonPath("createdTime").value(Matchers.startsWith("2021-")))
                .andDo(print());
    }

    @Test
    void testCreateOrder() throws Exception {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doNothing().when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        mockMvc.perform(
                        post("/orders/create")
                                .contentType(APPLICATION_JSON)
                                .content(JSONObject.toJSONString(in))
                )
                .andExpect(status().isOk());

        final List<Order> orders = repository.findAll();
        assertThat(orders).hasSize(1).element(0).usingRecursiveComparison()
                .ignoringFields("id", "createdTime").isEqualTo(in.toOrder());
    }

    @Test
    void testCreateOrder_Understocked() throws Exception {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doThrow(UnderstockedException.class)
                .when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        mockMvc.perform(
                        post("/orders/create")
                                .contentType(APPLICATION_JSON)
                                .content(JSONObject.toJSONString(in))
                )
                .andExpect(status().isNotAcceptable());

        assertThat(repository.findAll()).hasSize(0);
    }
}