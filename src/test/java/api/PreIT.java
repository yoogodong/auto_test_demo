package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

import static io.restassured.config.EncoderConfig.encoderConfig;

public class PreIT {
    @BeforeSuite
    public void init() {
        RestAssured.baseURI = "http://127.0.0.1";
        RestAssured.port = 8080;
        RestAssured.basePath = "/orders";
        RestAssured.config().encoderConfig(encoderConfig().defaultContentCharset("UTF-8"));
    }

}
