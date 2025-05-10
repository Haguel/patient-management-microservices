import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import util.Helper;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class PatientIntegrationTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = Helper.API_GATEWAY_URI;
    }

    @Test
    public void shouldReturnPatientsWithValidToken() {
        String loginPayload = Helper.getCorrectLoginPayload();
        String token = Helper.loginAndGetToken(loginPayload);

        given()
                .header("Authorization", "Bearer " + token)
                .when()
                .get("/api/patients")
                .then()
                .statusCode(200)
                .body("patients", notNullValue());

    }
}
