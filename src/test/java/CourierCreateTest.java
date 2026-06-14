import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.CourierModel;
import org.junit.Test;

import static java.net.HttpURLConnection.*;
import static org.hamcrest.Matchers.equalTo;
import static steps.CourierSteps.courierCreate;

public class CourierCreateTest extends CourierBaseTest {

    @Test
    @DisplayName("Успешное создание курьера с валидными данными")
    @Description("Создание курьера с корректным логином, паролем и именем. Ожидается код 201 и тело ответа с ok=true.")
    public void testCreateCourierSuccess() {
        // Создаем модель курьера с логином, паролем и именем
        CourierModel courier = new CourierModel(
                testCourier.getLogin(), testCourier.getPassword(), testCourier.getFirstName()
        );

        // Отправляем запрос на создание и проверяем результат
        courierCreate(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание курьера без имени")
    @Description("Создание курьера только с логином и паролем. Имя не является обязательным. Ожидается код 201 и ok=true.")
    public void testCreateCourierWithoutFirstNameSuccess() {
        // Создаем курьера без имени
        CourierModel courier = CourierModel.withLoginAndPassword(
                testCourier.getLogin(), testCourier.getPassword()
        );

        // Отправляем запрос на создание и проверяем результат
        courierCreate(courier)
                .then()
                .statusCode(HTTP_CREATED)
                .body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без логина")
    @Description("Попытка создать курьера без логина. Ожидается код 400 и сообщение о недостаточности данных.")
    public void testCreateCourierWithoutLoginFailure() {
        // Создаем курьера без логина
        CourierModel courier = CourierModel.withPasswordAndFirstName(
                testCourier.getPassword(), testCourier.getFirstName()
        );

        // Отправляем запрос на создание и проверяем сообщение об ошибке
        courierCreate(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Ошибка при создании курьера без пароля")
    @Description("Попытка создать курьера без пароля. Ожидается код 400 и сообщение о недостаточности данных.")
    public void testCreateCourierWithoutPasswordFailure() {
        // Создаем курьера без пароля
        CourierModel courier = CourierModel.withLoginAndFirstName(
                testCourier.getLogin(), testCourier.getFirstName()
        );

        // Отправляем запрос на создание и проверяем сообщение об ошибке
        courierCreate(courier)
                .then()
                .statusCode(HTTP_BAD_REQUEST)
                .body("message", equalTo("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Ошибка при создании курьера с уже существующим логином")
    @Description("Дважды создаем одного и того же курьера. Ожидается код 409 и сообщение о повторяющемся логине.")
    public void testCreateSameCourierFailure() {
        // Создаем модель курьера
        CourierModel courier = new CourierModel(
                testCourier.getLogin(), testCourier.getPassword(), testCourier.getFirstName()
        );

        // Первая попытка создания (успешная)
        courierCreate(courier);

        // Вторая попытка — логин уже существует
        courierCreate(courier)
                .then()
                .statusCode(HTTP_CONFLICT)
                .body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }
}