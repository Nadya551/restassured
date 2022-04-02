package tests.petstore;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class GetPet {

    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void getPetByStatus(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("pet/findByStatus?status=sold")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }

}
