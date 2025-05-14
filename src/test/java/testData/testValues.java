package testData;

import java.util.List;

public class testValues {
    // Данные для курьера
    public static final String BASE_URL = "https://qa-scooter.praktikum-services.ru";
    public static final String COURIER_LOGIN_TEMPLATE = "apitester_";
    public static final String COURIER_PASSWORD = "1234";
    public static final String COURIER_FIRSTNAME = "apitester";

    // Данные для заказов
    public static final String ORDER_FIRST_NAME = "Naruto";
    public static final String ORDER_LAST_NAME = "Uchiha";
    public static final String ORDER_ADDRESS = "Konoha, 142 apt.";
    public static final String ORDER_PHONE = "+7 800 355 35 35";
    public static final int ORDER_RENT_TIME = 5;
    public static final String ORDER_DELIVERY_DATE = "2020-06-06";
    public static final String ORDER_COMMENT = "Saske, come back to Konoha";
    public static final List<String> COLOR = List.of("BLACK");

    // Станции метро
    public static final int STATION_BULVAR_ROKOSSOVSKOGO = 1; // Бульвар Рокоссовского
    public static final int STATION_CHERKIZOVSKAYA = 2;       // Черкизовская
    public static final int STATION_KALUZHSKAYA = 110;        // Калужская

    // Параметры фильтрации заказов
    public static final int TEST_LIMIT = 10;
    public static final int TEST_PAGE = 0;
}
