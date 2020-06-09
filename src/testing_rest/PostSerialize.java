package testing_rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import sun.awt.X11.XSystemTrayPeer;

import java.io.Serializable;

public class PostSerialize implements Serializable {

    public PostSerialize() {
        this.validateSignupWithDeserialize();

    }

    public void validateSignupWithDeserialize() {

        RestAssured.baseURI = "https://central-de-erros-squad3.herokuapp.com";

        RequestSpecification httpRequest = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Fulano de Tal");
        requestParams.put("email", "fulanodetal1234567@email.com");
        requestParams.put("password", "123456");

        System.out.println("request params => " + requestParams);

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString());
        System.out.println("resquest => " + httpRequest);
        Response response = httpRequest.post("/users/signup");

        int responseStatusCode = response.statusCode();

        ResponseBody body = response.getBody();
        System.out.println("the body => " + body);

        if(responseStatusCode == 200) {

            // Deserialize the response body into SignupSuccessResponse class
            // Now you can use the responseBody variable to apply any assertions that you want
            // or may be pass it as an input to other tests.
            SignupSuccessResponse deserializedResponseBody = body.as(SignupSuccessResponse.class);

            System.out.println("deserialized response => " + deserializedResponseBody.message);
            System.out.println("deserialized response => " + deserializedResponseBody.data);
            Assert.assertEquals("User created successfully", deserializedResponseBody.message);
            Assert.assertEquals("User created successfully", deserializedResponseBody.data);

        } else if(responseStatusCode == 201) {

            SignupSuccessResponse deserializedResponseBody = body.as(SignupSuccessResponse.class);

            System.out.println("deserialized response => " + deserializedResponseBody.message);

            Assert.assertEquals("User email already exists", deserializedResponseBody.message);

        }

    }
}
