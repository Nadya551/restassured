package utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import static io.restassured.RestAssured.given;

/**
 * Класс для работы с .properties файлами
 */
public class UtilsMethod {

    /**
     * Возвращает значение поля из файла userData.properties
     *
     * @param value название поля в .properties файле
     * @return возвращает String значение поля
     */
    public static String getValue(String value) {
        Properties properties = new Properties();
        try {
            properties.load(UtilsMethod.class.getClassLoader().getResourceAsStream("userData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String data = properties.getProperty(value);
        try {
            data = new String(data.getBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static HashMap<String, String> getMsVersion() {
        HashMap<String, String> requestParams = new HashMap<String, String>();
        requestParams.put("Accept", "application/json");

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder
                .setBaseUri(getValue("url.admin"))
                .addHeaders(requestParams);
        JsonPath jsonPath = new JsonPath(sentRequestAndGetResponse(requestSpecBuilder, Method.GET).getBody().asString());
        HashMap<String, String> msVersions = new HashMap<>();
        for (int i = 0; i < ((ArrayList) jsonPath.get()).size(); i++) {
            String key = ((HashMap<String, String>) ((ArrayList) jsonPath.get()).get(i)).get("name");
            String value = ((HashMap<String, String>) ((ArrayList) jsonPath.get()).get(i)).get("buildVersion");
            msVersions.put(key, value);
        }
        return msVersions;
    }

    public static Response sentRequestAndGetResponse(RequestSpecBuilder requestSpecBuilder, Method method) {
        return given().spec(requestSpecBuilder.build()).when().request(method);
    }
}
