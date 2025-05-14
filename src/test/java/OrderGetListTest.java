import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.*;

public class OrderGetListTest extends OrderBaseTest {

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Отправка запроса на получение списка заказов. Ожидается код 200 и непустой массив заказов в ответе.")
    public void testGetOrderListReturnsOrdersArray() {
        // Отправляем GET-запрос для получения списка заказов
        Response response = OrderSteps.getOrders();

        // Проверяем, что статус ответа 200 OK
        // и что массив заказов присутствует и не пустой
        response.then()
                .statusCode(HTTP_OK)
                .body("orders", notNullValue())
                .body("orders", is(not(empty())));
    }
}
