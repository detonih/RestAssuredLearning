package testing_rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import java.util.LinkedHashMap;

public class PostTest {

    public PostTest() {

        this.validateSignup();
    }

    public void validateSignup() {
        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Fulano de Tal");
        requestParams.put("email", "fulanodetal12345@email.com");
        requestParams.put("password", "123456");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());

        Response response = httpRequest.post("/users/signup");

        int statusCode = response.getStatusCode();

        Assert.assertEquals(statusCode, 201);

        String successMEssage = response.jsonPath().get("message");
        System.out.println("success message => " + successMEssage);

        Assert.assertEquals(successMEssage,  "User created successfully");

        // If we want to get all the object data returned we need to use type LinkedHashMap
        // but in this case we cannot use dot notation to access the object key value like data.name
        // instead we need use type String to succeed dot notation
        LinkedHashMap dataCreated = response.jsonPath().get("data");
        System.out.println("data created => " + dataCreated);

    }


}
