package rest.order;

import org.junit.jupiter.api.Test;
import rest.BaseRT;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.path.json.JsonPath.from;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 使用 RestAssured API 来做API 测试
 * 优势：
 * 1. 与被测系统使用的语言和框架无关
 * 2. RestAssured API 直观方便
 * 劣势：
 * 1. 不能直接 mock 被测系统对外部的依赖,需要借助于其他技术
 */
public class AnotherOrderRT extends BaseRT {

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
                .get("/another/orders/1")
        .then().log().all()
                .contentType(JSON)
                .statusCode(200)
                .body("totalPrice", equalTo(3250f))
                .body("orderItemList.quantity", hasItems(2, 5))//assertJ ,  Hamcrest
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
                .get("/another/orders/5")
                .then().log().all()
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
                .when().get("/another/orders/1")
                .then().log().body()
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
                .get("/another/orders/5")
                .then()
                .statusCode(404)
                .extract().body().asString();
        String message = from(body).getString("[0]");
        assertEquals("对象不存在", message);
    }
}