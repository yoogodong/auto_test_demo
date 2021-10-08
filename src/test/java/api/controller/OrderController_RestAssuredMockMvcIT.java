package api.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 演示使用 RestAssured 包装 MockMvc 的API ：
 * 优势：
 * 1. 同时兼顾了单独使用 RestAssured / MockMvc 各自的优势。
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderController_RestAssuredMockMvcIT {


    @MockBean
    InvoiceFeign invoiceFeign;

    @Autowired
    OrderController orderController;

    @BeforeEach
    public void setUp() throws Exception {
        RestAssuredMockMvc.standaloneSetup(orderController);
    }


    /**
     * 演示 restAssured 核心API ：
     * 1. GWT 三段式API
     * 2. given()/when（） 是纯粹的语法糖
     * 3. 三个核心类型 RequestSpecification /  Response / ValidatableResponse
     * 4. JsonPath
     */

    @Test
    public void get_order_when_id_1() {
        given()
                .contentType(JSON)
                .when()
                .get("/orders/1")
                .then()
                .contentType(JSON)
                .statusCode(200)
                .body("totalPrice", equalTo(3250f))
                .body("orderItemList.quantity", hasItems(2, 5))
                .body("orderItemList.quantity", hasSize(2))
                .body("orderItemList[0].quantity", equalTo(2));
    }

    /**
     * 演示
     * 对于匿名json/数组， 通过空串代表数组
     */
    @Test
    public void should_404_when_id_5_() {
        given()
                .accept(JSON)
                .when()
                .get("/orders/5")
                .then()
                .statusCode(404)
                .contentType(JSON)
                .body("[0]", equalTo("对象不存在"))
                .body("[1]", equalTo("NullPointException"));
    }


    /**
     * 演示：
     * 通过 find{}/findAll{} 过滤数组的某一个元素
     */
    @Test
    public void testFind() {
        given()
                .contentType(JSON)
                .when().get("/orders/1")
                .then()
                .body("orderItemList.find{it.name=='苹果'}.quantity", equalTo(2));

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


    /*
     *演示两个方面：
     * 1. ValidatableResponse 提供了一个方法获得 Response, 这意味着在验证阶段也可以再提取 Response
     *
     * 2.  from() 方法演示了如何独立使用 JsonPath
     *
     * */
    @Test
    public void show_extract() {
        String body = when()
                .get("/orders/5")
                .then()
                .statusCode(404)
                .extract().body().asString();
        String message = from(body).getString("[0]");
        assertEquals("对象不存在", message);
    }
}