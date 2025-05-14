import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import model.OrderModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import steps.OrderSteps;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static org.hamcrest.Matchers.notNullValue;
import static testData.testValues.*;

@RunWith(Parameterized.class)
public class OrderCreateTest extends OrderBaseTest {

    private final List<String> color;

    // Конструктор для передачи параметров в тест
    public OrderCreateTest(List<String> color) {
        this.color = color;
    }

    // Параметры теста — список возможных значений поля "color"
    @Parameterized.Parameters(name = "Цвета: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {List.of("BLACK")},              // только черный
                {List.of("GREY")},               // только серый
                {List.of("BLACK", "GREY")},      // оба цвета
                {List.of()}                      // без указания цвета
        });
    }

    @Test
    @DisplayName("Создание заказа с разными значениями параметра 'color'")
    @Description("Проверка возможности создания заказа с различными вариантами параметра 'color'. " +
            "Ожидается код 201 и наличие трека заказа в ответе.")
    public void testCreateOrderWithDifferentColors() {
        // Создаем модель заказа с параметром color
        OrderModel order = new OrderModel(
                ORDER_FIRST_NAME,
                ORDER_LAST_NAME,
                ORDER_ADDRESS,
                STATION_BULVAR_ROKOSSOVSKOGO,
                ORDER_PHONE,
                ORDER_RENT_TIME,
                ORDER_DELIVERY_DATE,
                ORDER_COMMENT,
                color
        );

        // Отправляем запрос на создание заказа и сохраняем track для отмены после теста
        track = OrderSteps.createOrder(order)
                .then()
                .statusCode(HTTP_CREATED)
                .body("track", notNullValue())
                .extract()
                .path("track");
    }
}
