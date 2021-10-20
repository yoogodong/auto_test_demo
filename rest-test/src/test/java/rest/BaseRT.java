package rest;

import io.restassured.RestAssured;

public class BaseRT {
    static {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
    }
}
