import org.junit.jupiter.api.Test;


import static org.hamcrest.Matchers.is;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class ReqresinTest {
   @Test
    void listUsersTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void listSingleUserTest() {

        given()

                .when()
                .get("https://reqres.in/api/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"));
    }

    @Test
    void createUserTest() {
        String body = "{ \"name\": \"morpheus\", " +
                "\"job\": \"leader\" }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void loginUnsuccesfullTest() {
        String body = "{ \"email\": \"peter@klaven\" }";
        given()
                .log().uri()
                .log().body()
                .body(body)
                .contentType(JSON)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void deleteUserTest() {
                given()
                .when()
                .delete("https://reqres.in/api/users2")
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}

