package api.controller;

import api.PreIT;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

public class OrderControllerIT extends PreIT {


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
                .contentType(ContentType.JSON)
                .when()
                .get("/1")
                .then()
                .contentType(ContentType.JSON)
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
        when()
                .get("/5")
                .then()
                .statusCode(404)
                .contentType(ContentType.JSON)
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
                .contentType(ContentType.JSON)
                .when().get("1")
                .then()
                .body("orderItemList.find{it.name=='苹果'}.quantity", equalTo(2));

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
                .get("5")
                .then()
                .statusCode(404)
                .extract().body().asString();
        String message = from(body).getString("[0]");
        assertEquals("对象不存在", message);
    }
}