package rest.order;

import com.alibaba.fastjson.JSONObject;
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

    // todo : 直接序列化对象
    @Test
    void testCreateOrder() {
        final OrderIn in = new OrderIn("中国", "张思纯", "89898");
        given().contentType(ContentType.JSON)
                .body(JSONObject.toJSONString(in))
                .post("/orders/create")
                .then()
                .statusCode(200);
    }

    class OrderIn {
        private String shipmentAddress;
        private String receiverName;
        private String receiverPhone;

        public OrderIn(String shipmentAddress, String receiverName, String receiverPhone) {
            this.shipmentAddress = shipmentAddress;
            this.receiverName = receiverName;
            this.receiverPhone = receiverPhone;
        }

        public String getShipmentAddress() {
            return shipmentAddress;
        }

        public String getReceiverName() {
            return receiverName;
        }


        public String getReceiverPhone() {
            return receiverPhone;
        }
    }

}
