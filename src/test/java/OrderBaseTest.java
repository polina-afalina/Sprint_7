import io.restassured.RestAssured;
import org.junit.After;
import org.junit.BeforeClass;

import static java.net.HttpURLConnection.HTTP_OK;
import static org.hamcrest.Matchers.is;
import static testData.testValues.BASE_URL;
import steps.OrderSteps;

public class OrderBaseTest {
    protected Integer track;

    @BeforeClass
    public static void setUpBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }
}