package util;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class Helper {
    public static final String API_GATEWAY_URI = "http://localhost:4004";

    public static String getCorrectLoginPayload() {
        return getLoginPayload("testuser@test.com", "password123");
    }

    public static String getLoginPayload(String login, String password) {
        return String.format("""
                    {
                        "email": "%s",
                        "password": "%s"
                    }
                """, login, password);
    }

    public static String loginAndGetToken(String payload) {
        Response response = given()
                .contentType( "application/json")
                .body(payload)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(200)
                .body("token", notNullValue())
                .extract()
                .response();

        return response.jsonPath().getString("token");
    }
}
