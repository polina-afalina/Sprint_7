import io.restassured.RestAssured;
import org.junit.BeforeClass;
import static testData.testValues.BASE_URL;

public class OrderBaseTest {
    protected Integer track;

    @BeforeClass
    public static void setUpBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }
}