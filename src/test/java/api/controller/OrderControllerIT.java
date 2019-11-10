package api.controller;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class OrderControllerIT {

    @BeforeClass
    public static void setUp() throws Exception {
        baseURI = "http://localhost:8080";
        basePath = "/orders";
    }


    @Test
    public void get_order_when_id_1() {
        given().log().all().get("/1")
                .then()
                .statusCode(200)
                .body("totalPrice", equalTo(3250f))
                .body("orderItemList.quantity", hasItems(2, 5))
                .body("orderItemList.quantity", hasSize(2))
                .body("orderItemList[0].quantity", equalTo(2));
    }

    @Test
    public void should_404_when_id_5() {
        String body = when()
                .get("5")
                .then()
                .statusCode(404)
                .extract().body().asString();
        assertEquals("对象不存在", body);
    }
}