package tests.petstore;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class E2E {

    private final HashMap<String, String> dataMap = new HashMap<>();

    @BeforeTest
    public static void setup() {
        baseURI = "https://petstore.swagger.io/v2/pet";
    }

    @Test
    public void createPet(){
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 234567876,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"nameName\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
        dataMap.put("id", "234567876");
    }


    @Test(dependsOnMethods = "createPet")
    public void deletePet(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .delete(dataMap.get("id"))
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
}
