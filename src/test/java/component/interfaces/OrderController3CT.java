package component.interfaces;

import com.alibaba.fastjson.JSONObject;
import component.BaseCT;
import component.application.InventoryAdapter;
import component.application.UnderstockedException;
import component.domain.Order;
import component.domain.OrderRepository;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

/**
 * 演示通过 rest 接口验证组件
 */
class OrderController3CT extends BaseCT {

    @Autowired
    OrderController controller;
    @Autowired
    OrderRepository repository;

    @MockBean
    InventoryAdapter inventoryAdapter;


    @BeforeEach
    void setUp() {
        repository.deleteAllInBatch();
        RestAssuredMockMvc.standaloneSetup(controller);
    }

    @Test
    void testGetOrder() {
        Order order1 = Order.builder()
                .shipmentAddress("address")
                .receiverName("receiver")
                .receiverPhone("13312345678")
                .build();
        Order saved = repository.save(order1);

        given()
                .accept(ContentType.JSON)
                .when()
                .get("/orders/" + saved.getId())
                .then()
                .contentType(ContentType.JSON)
                .status(HttpStatus.OK)
                .body("shipmentAddress", equalTo(order1.getShipmentAddress()))
                .body("receiverName", equalTo(order1.getReceiverName()))
                .body("receiverPhone", equalTo(order1.getReceiverPhone()))
                .body("createdTime", startsWith("2021-"));
    }

    @Test
    void testCreateOrder() throws UnderstockedException {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doNothing().when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        given().contentType(ContentType.JSON)
                .body(JSONObject.toJSONString(in))
                .when().post("/orders/create")
                .then().status(HttpStatus.OK);


        final List<Order> orders = repository.findAll();
        assertThat(orders).hasSize(1).element(0).usingRecursiveComparison()
                .ignoringFields("id", "createdTime").isEqualTo(in.toOrder());
    }

    @Test
    void testCreateOrder_Understocked() throws UnderstockedException {
        final OrderIn in = OrderIn.builder()
                .receiverName("Alex")
                .shipmentAddress("广东省中山市")
                .receiverPhone("1391101000")
                .build();
        Mockito.doThrow(UnderstockedException.class)
                .when(inventoryAdapter).deduct(Mockito.anyString(), Mockito.anyInt());

        given().contentType(ContentType.JSON)
                .body(JSONObject.toJSONString(in))
                .when().post("/orders/create")
                .then().status(HttpStatus.NOT_ACCEPTABLE);

        assertThat(repository.findAll()).hasSize(0);
    }
}