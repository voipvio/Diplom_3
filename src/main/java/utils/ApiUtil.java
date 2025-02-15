package utils;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import models.User;
import models.UserLogin;

import static io.restassured.RestAssured.given;

public class ApiUtil {

    public static final String HOST = "https://stellarburgers.nomoreparties.site/api";
    ValidatableResponse response;

    @Step("Регистрация пользователя")
    public ValidatableResponse userRegister(User user) {
        return response = given()
                .spec(baseRequestSpec)
                .body(user)
                .when()
                .post("/auth/register")
                .then()
                .statusCode(200);
    }

    @Step("Логин пользователя")
    public String getAccessToken(UserLogin userLogin) {
        String accessToken;
        response = given()
                .spec(baseRequestSpec)
                .body(userLogin)
                .when()
                .post("/auth/login")
                .then()
                .statusCode(200);
        accessToken = response.extract().path("accessToken");
        return accessToken;
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String accessToken) {
        return response = given()
                .spec(baseRequestSpec)
                .header("Authorization", accessToken)
                .delete("/auth/user")
                .then()
                .statusCode(202);
    }

    private RequestSpecification baseRequestSpec = new RequestSpecBuilder()
            .setBaseUri(HOST)
            .addHeader("Content-Type", "application/json")
            .setRelaxedHTTPSValidation()
            .addFilter(new RequestLoggingFilter())
            .addFilter(new ResponseLoggingFilter())
            .addFilter(new ErrorLoggingFilter())
            .build();
}