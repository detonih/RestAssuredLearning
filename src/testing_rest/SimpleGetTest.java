package testing_rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class SimpleGetTest {

    public SimpleGetTest() {
        this.getTheIndexRoute();
    }

    @Test
    public void getTheIndexRoute() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        System.out.println("Response body is => " + response.asString());
    }
}
