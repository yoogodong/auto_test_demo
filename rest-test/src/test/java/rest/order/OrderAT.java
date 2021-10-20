package rest.order;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import rest.BaseAT;

import static io.restassured.RestAssured.given;

public class OrderAT extends BaseAT {

    @Test
    void testGetOrder() {

        given().accept(ContentType.JSON)
                .get("/orders/1")
                .then()
                .body("receiverPhone", Matchers.equalTo("138"))
                .body("receiverName",Matchers.equalTo("张三丰"))
                .body("shipmentAddress",Matchers.equalTo("长沙市开福区"));
    }

}
