import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Before;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static steps.CourierSteps.courierLogin;
import static steps.CourierSteps.courierCreate;

public class CourierLoginTest extends CourierBaseTest {

    @Before
    public void setUpCourier() {
        CourierModel courier = new CourierModel(
                testCourier.getLogin(), testCourier.getPassword(), testCourier.getFirstName()
        );

        courierCreate(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Успешный логин курьера с валидными данными")
    @Description("Создание курьера и логин с правильными логином и паролем. Ожидается код 200 и наличие поля id.")
    public void testCourierLoginSuccess() {
        CourierModel loginCourier = CourierModel.withLoginAndPassword(testCourier.getLogin(), testCourier.getPassword());
        courierLogin(loginCourier)
                .then()
                .statusCode(HTTP_OK)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Ошибка логина курьера с неверным логином")
    @Description("Создание курьера и попытка входа с неправильным логином. Ожидается код 404 и сообщение об отсутствии учётной записи.")
    public void testCourierLoginWrongLoginFailure() {
        CourierModel loginCourier = CourierModel.withLoginAndPassword(testCourier.getLogin() + "1", testCourier.getPassword());
        courierLogin(loginCourier)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Ошибка логина курьера с неверным паролем")
    @Description("Создание курьера и попытка входа с неправильным паролем. Ожидается код 404 и сообщение об отсутствии учётной записи.")
    public void testCourierLoginWrongPasswordFailure() {
        CourierModel loginCourier = CourierModel.withLoginAndPassword(testCourier.getLogin(), testCourier.getPassword() + "1");
        courierLogin(loginCourier)
                .then()
                .statusCode(HTTP_NOT_FOUND)
                .body("message", equalTo("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Ошибка логина курьера без логина")
    @Description("Создание курьера и попытка входа без указания логина. Ожидается код 400 и сообщение о недостаточности данных для входа.")
    public void testCourierLoginWithoutLoginFailure() {
        CourierModel loginCourier = CourierModel.withPassword(testCourier.getPassword());
        courierLogin(loginCourier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Ошибка логина курьера без пароля")
    @Description("Создание курьера и попытка входа без указания пароля. Ожидается код 400 и сообщение о недостаточности данных для входа.")
    public void testCourierLoginWithoutPasswordFailure() {
        CourierModel loginCourier = CourierModel.withLogin(testCourier.getLogin());
        courierLogin(loginCourier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для входа"));
    }
}