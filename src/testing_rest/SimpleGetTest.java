package testing_rest;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetTest {

    public SimpleGetTest() {
        this.getTheIndexRoute();
        this.tryToGetInvalidData();
        this.getStatusLine();
        this.getResponseHeaders();
        this.IteratingOverHeaders();
        this.getResponseBody();
        this.verifyNodeInJsonResponse();
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
    public void getStatusLine() {
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
        Assert.assertEquals(contentType, "application/json; charset=utf-8");

        String serverType = response.header("Server");
        Assert.assertEquals(serverType, "Cowboy");

        String contentEncoding = response.header("Content-Encoding");
        Assert.assertEquals(contentEncoding, null);

        Headers completeHeader = response.headers();
        System.out.println("complete headers are => " + completeHeader);
    }

    @Test
    public void IteratingOverHeaders() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        Headers allHeaders = response.headers();

        for(Header header : allHeaders) {
            System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
        }
    }

    @Test
    public void getResponseBody() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        ResponseBody responseBody = response.body();

        String bodyAsString = responseBody.asString();
        System.out.println(bodyAsString);
        Assert.assertEquals(bodyAsString.toLowerCase().contains("message"), true);
    }

    public void verifyNodeInJsonResponse() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/");

        JsonPath jsonPathEvaluator = response.jsonPath();

        String message = jsonPathEvaluator.get("message");
        System.out.println(message);

        Assert.assertEquals(message, "Access the documentation", "Correct value received in the Response");

        String docs = jsonPathEvaluator.get("docs");
        System.out.println(docs);

        Assert.assertEquals(docs, "https://central-de-erros-squad3.herokuapp.com/api-docs", "Correct value received in the Response");

        // try this method later to find out if there is a way to iterate through objects or arrays provided by the response body
        // maybe we can use "dot" notation to access over arrays, like [0].properties or object nodes
        // Object otherWayToGetJsonPath = jsonPathEvaluator.getJsonObject("message");

    }

}
