package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.CourierModel;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class CourierSteps {
    public static final String COURIER_CREATE_ENDPOINT = "/api/v1/courier";
    public static final String COURIER_LOGIN_ENDPOINT = "/api/v1/courier/login";
    public static final String COURIER_DELETE_ENDPOINT = "/api/v1/courier/";

    @Step("Создание курьера: {courier}")
    public static Response courierCreate(CourierModel courier){
        return given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_CREATE_ENDPOINT)
                .then()
                .extract().response();
    }

    @Step("Логин курьера: {courier}")
    public static Response courierLogin(CourierModel courier){
        return given()
                .contentType(ContentType.JSON)
                .body(courier)
                .when()
                .post(COURIER_LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Step("Получение id курьера по логину: {courier}")
    public static Integer getCourierId(CourierModel courier) {
        Response response = courierLogin(courier);
        if (response.statusCode() == HTTP_OK && response.path("id") != null) {
            return response.path("id");
        } else {
            return null;
        }
    }

    @Step("Удаление курьера по id: {id}")
    public static void courierDelete(int id) {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", id);
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .delete(COURIER_DELETE_ENDPOINT + id)
                .then()
                .extract().response();
    }
}
