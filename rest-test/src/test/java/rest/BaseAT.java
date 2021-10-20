package rest;

import io.restassured.RestAssured;

public class BaseAT {
    static {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=8080;
    }
}
