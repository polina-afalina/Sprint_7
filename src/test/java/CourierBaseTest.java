import io.restassured.RestAssured;
import model.CourierModel;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import steps.CourierSteps;

import static steps.CourierSteps.*;
import static testData.testValues.*;

public class CourierBaseTest {
    protected CourierModel testCourier;
    protected Integer courierId;

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Before
    public void prepareUniqueLoginAndPassword() {
        String uniqueLogin = COURIER_LOGIN_TEMPLATE + System.currentTimeMillis();

        testCourier = CourierModel.withLoginAndPassword(uniqueLogin, COURIER_PASSWORD);
    }

    @After
    public void tearDown() {
        if (testCourier != null) {
            CourierModel loginCourier = CourierModel.withLoginAndPassword(
                    testCourier.getLogin(), testCourier.getPassword()
            );
            Integer id = CourierSteps.getCourierId(loginCourier);
            if (id != null) {
                CourierSteps.courierDelete(id);
            }
        }
    }
}
