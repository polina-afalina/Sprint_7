package steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static final String ORDERS_ENDPOINT = "/api/v1/orders";

    @Step("Создание заказа: {order}")
    public static Response createOrder(OrderModel order) {
        return given()
                .contentType(ContentType.JSON)
                .body(order)
                .when()
                .post(ORDERS_ENDPOINT)
                .then()
                .extract().response();
    }

    @Step("Получение списка заказов")
    public static Response getOrders() {
        return RestAssured.given()
                .contentType(ContentType.JSON)
                .when()
                .get(ORDERS_ENDPOINT)
                .then()
                .extract().response();
    }

    @Step("Отмена заказа по треку: {track}")
    public static Response cancelOrder(int track) {
        return given()
                .contentType(ContentType.JSON)
                .queryParam("track", track)
                .body("{\"track\": " + track + "}")
                .when()
                .put(ORDERS_ENDPOINT + "/cancel")
                .then()
                .extract().response();
    }
}