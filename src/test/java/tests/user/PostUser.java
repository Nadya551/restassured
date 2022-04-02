package tests.user;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostUser {

    private final HashMap<String, String> dataMap = new HashMap<>();

    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/user/";
    }

    @Test
    public void createUser() {
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 4546545465,\n" +
                        "  \"username\": \"autotestliana\",\n" +
                        "  \"firstName\": \"string\",\n" +
                        "  \"lastName\": \"string\",\n" +
                        "  \"email\": \"string\",\n" +
                        "  \"password\": \"string\",\n" +
                        "  \"phone\": \"string\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .post("")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);

        dataMap.put("username", "autotestliana");
    }

    @Test(dependsOnMethods = "createUser")
    public void deleteUser() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(dataMap.get("username"))
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
