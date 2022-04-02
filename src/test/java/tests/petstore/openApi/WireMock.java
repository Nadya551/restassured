package tests.petstore.openApi;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.restassured.http.ContentType;
import org.apache.http.impl.conn.Wire;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.github.tomakehurst.wiremock.junit5.WireMockRuntimeInfo;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

@WireMockTest(httpsEnabled = true)

public class WireMock {
    @BeforeTest
    public static void setup() {
        baseURI = "https://covid-api.mmediagroup.fr/vl/";
    }

    @Test
    public void covidCases(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("casos?country=Germany")
                .then()
                .log()
                .all()
                .assertThat()
                .statusCode(200);
    }
    @Test
    void mockito(){
        com.github.tomakehurst.wiremock.client.WireMock.stubFor(
                com.github.tomakehurst.wiremock.client.WireMock.get("https://covid-api.mmediagroup.fr/vl/casos?country=Germany")
                   .willReturn(
                           com.github.tomakehurst.wiremock.client.WireMock.aResponse()
                           .withStatus(200)
                           .withHeader("Contact-type", "text/html")
                           .withBody("testTestTest")
                   )
        );

    }
}
