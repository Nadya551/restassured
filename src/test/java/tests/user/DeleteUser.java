package tests.user;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteUser {
    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/";
    }

    @Test
    public void deleteUser(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete("user/user123132")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
