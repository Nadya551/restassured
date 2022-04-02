package tests.user;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class E2E {
    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void createUser(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 4546545465,\n" +
                        "  \"username\": \"user123132\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("user")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
