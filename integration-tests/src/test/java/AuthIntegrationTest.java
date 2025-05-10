import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Helper;

import static io.restassured.RestAssured.given;

public class AuthIntegrationTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = Helper.API_GATEWAY_URI;
    }

    @Test
    public void shouldReturnOkWithValidToken() {
        String loginPayload = Helper.getCorrectLoginPayload();
        String token = Helper.loginAndGetToken(loginPayload);

        System.out.println("Generated Token: " + token);
    }

    @Test
    public void shouldReturn401OnInvalidToken() {
        String loginPayload = Helper.getLoginPayload("wrong_user@test.com", "wrong_password");
        given()
                .contentType( "application/json")
                .body(loginPayload)
                .when()
                .post("/api/auth/login")
                .then()
                .statusCode(401);
    }
}