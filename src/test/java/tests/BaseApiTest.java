package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

@Timeout(value = 20, unit = TimeUnit.SECONDS)
public class BaseApiTest {

    public static String BASE_URL;


    @BeforeAll
    public static void setUp() {
        BASE_URL = System.getProperty("BASE_URL", "https://api.kucoin.com/api/v1/market/allTickers");
        RestAssured.baseURI = BASE_URL;
        RestAssured.filters(
                new RequestLoggingFilter(),
                new ResponseLoggingFilter(),
                new AllureRestAssured()
        );
    }
}
