package testing_rest;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest {

    public SimpleGetTest() {
        this.getTheIndexRoute();
        this.tryToGetInvalidData();
        this.getResponseHeaders();
    }

    @Test
    public void getTheIndexRoute() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        System.out.println("Response body is => " + response.asString());

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 200, "Correct status code returned");

        System.out.println(statusCode);
    }

    @Test
    public void tryToGetInvalidData() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/users/logs");

        int statusCode = response.getStatusCode();

        String responseBody = response.getBody().asString();
        System.out.println(responseBody);

        Assert.assertEquals(statusCode, 401, "Correct status code returned");

        System.out.println(statusCode);

    }

    @Test
    public void getWeatherStatusLine() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        String statusLine = response.getStatusLine();

        Assert.assertEquals(statusLine, "HTTP/1.1 200 OK", "Correct status code returned");
    }

    @Test
    public void getResponseHeaders() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        String contentType = response.header("Content-Type");
        System.out.println("content type is => " + contentType);

        String serverType = response.header("Server");
        System.out.println("server type is => " + serverType);

        String acceptLanguage = response.header("Content-Encoding");
        System.out.println("accept language is => " + acceptLanguage);

        Headers completeHeader = response.headers();
        System.out.println("complete headers are => " + completeHeader);
    }

    @Test
    public void IteratingOverHeaders() {

    }

}
