package tests.petstore;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class DeletePet {

    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void deletePet(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("pet/234567876")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
