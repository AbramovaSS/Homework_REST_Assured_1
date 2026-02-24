package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.StringContains.containsString;

public class WdHubStatusSelenoidTests extends TestBase {

    @Test
    @DisplayName("Успешная авторизация на Selenoid, статус-код 200")
    public void successfulSelenoidAuthorizationTest() {
        given()
                .log().all()
                .when()
                .auth().basic("user1", "1234")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    @DisplayName("Проверка текста \"Selenoid 1.11.3 built\"")
    public void checkTextTest() {
        given()
                .log().all()
                .when()
                .auth().basic("user1", "1234")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value.message", containsString("Selenoid 1.11.3 built"));
    }

    @Test
    @DisplayName("Проверка готовности сервиса к использованию")
    public void checkReadyStatusIsTrueTest() {
        given()
                .log().all()
                .when()
                .auth().basic("user1", "1234")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body("value.ready", is(true));
    }

    @Test
    @DisplayName("Проверка соответствия структуры ответа JSON-схеме")
    public void checkSchemaTest() {
        given()
                .log().all()
                .when()
                .auth().basic("user1", "1234")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/status_response_schema.json"));
    }

    @Test
    @DisplayName("Неуспешная авторизация: ввод неверного пароля")
    public void unsuccessfulSelenoidAuthorizationTest() {
        given()
                .log().all()
                .when()
                .auth().basic("user1", "ertyu")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401);
    }

    @Test
    @DisplayName("Неуспешная авторизация: пустые учетные данные")
    public void unsuccessfulAuthorizationEmptyCredentialsTest() {
        given()
                .log().all()
                .when()
                .auth().basic("", "")
                .get("/wd/hub/status")
                .then()
                .log().all()
                .statusCode(401)
                .body(containsString("Authorization Required"));
    }

}
