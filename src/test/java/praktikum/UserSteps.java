package praktikum;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserSteps {

    @Step("Создать пользователя через API")
    public Response createUser(User user) {
        return RestAssured.given()
                .baseUri(StellarBurgersApi.BASE_URI)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(StellarBurgersApi.REGISTER);
    }

    @Step("Залогинить пользователя через API")
    public Response login(User user) {
        return RestAssured.given()
                .baseUri(StellarBurgersApi.BASE_URI)
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(StellarBurgersApi.LOGIN);
    }

    @Step("Удалить пользователя через API")
    public Response deleteUser(String accessToken) {
        return RestAssured.given()
                .baseUri(StellarBurgersApi.BASE_URI)
                .header("Authorization", accessToken)
                .when()
                .delete(StellarBurgersApi.USER);
    }
}
