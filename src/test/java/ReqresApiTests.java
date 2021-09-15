import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ReqresApiTests {
    @Test
    void listUserPage2() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", is(2));
    }

    @Test
    void singleUser() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void singleUserNotFound() {
        given()
                .contentType(JSON)
                .when()
                .get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }

    @Test
    void successfulLogin() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void unsuccessfulLogin() {
        given()
                .contentType(JSON)
                .body("{\"email\": \"peter@klaven\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

}
