package api.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.*;

/**
 * 演示使用 RestAssured 包装 MockMvc
 * RestAssured 提供的API 更简洁和灵活
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerRestAssuredMockMvcTest {


    @MockBean
    InvoiceFeign invoiceFeign;

    @Autowired
    OrderController orderController;

    @Before
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(orderController);
    }


    @Test
    public void when_input_1() throws Exception {
        given()
                .accept(JSON)
                .when().
                get("/orders/1")
                .then()
                .statusCode(200)
                .body("totalPrice", equalTo(3250f))
                .body("orderItemList.quantity", hasItems(2, 5))
                .body("orderItemList.quantity", hasSize(2))
                .body("orderItemList[0].quantity", equalTo(2));

    }

    @Test
    public void when_input_5() throws Exception {
        given()
                .accept(JSON)
                .when()
                .get("/orders/5")
                .then()
                .statusCode(404)
                .body("[0]", equalTo("对象不存在"));
    }

    @Test
    public void show_how_to_use_mockbean() {
        Mockito.when(invoiceFeign.createInvoice(Mockito.any())).thenReturn("1000");

        given()
                .param("totalPrice", 23d)
                .accept(JSON)
                .contentType(JSON)
                .when()
                .post("/orders/save")
                .then()
                .body("status", equalTo("success"))
                .body("invoiceId", equalTo("1000"));
    }
}