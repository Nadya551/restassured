package tests.petstore.openApi;

import org.junit.jupiter.api.Assertions;
import org.testng.annotations.BeforeTest;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Post {
    private static String requestBody = "{\n" +
            "    \"userId\": 1,\n" +
            "    \"id\": 3,\n" +
            "    \"title\": \"foo\" \n" +
            "  }";

    @BeforeTest
    public static void setup() {
        baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void postRequest(){
        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .log()
                .all()
                .extract().response();
        Assertions.assertEquals(201, response.statusCode());
        Assertions.assertEquals("foo", response.jsonPath().getString("title"));
        
    }
}
