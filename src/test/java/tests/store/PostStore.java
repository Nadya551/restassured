package tests.store;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PostStore {
    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void createOrder(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 8,\n" +
                        "  \"petId\": 0,\n" +
                        "  \"quantity\": 0,\n" +
                        "  \"shipDate\": \"2022-01-07T18:00:18.858Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("store/order")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }
}
