package tests.store;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetStore {

    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void findOrder(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/store/order/8")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

    }
}
