package integration;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;
import weather.WeatherBroadcast;

import java.net.URI;

/**
 * Интеграционный тест всего приложения
 */
public class allModulesIntegrationTest {

    private static final String ADMIN_API_MAIN_PAGE_HTML = "http://localhost:8080/admin-api/index.jsp?city=";

    private static final String CITY_NAME = "Penza";

    private static final String URL = "http://127.0.0.1:8080/weather-service/weather-service/";

    private static final String SYSTEM_PROPERTY_WEB_DRIVER_NAME = "webdriver.chrome.driver";

    private static final String SYSTEM_PROPERTY_WEB_DRIVER_VALUE = "C:\\Users\\VAZakharov\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe";

    private static final By BY_ID_SEARCH_FORM_OF_FORM = By.cssSelector("form[id=\"search form\"]");

    private static final By BY_TAG_INPUT_OF_NAME_CITY= By.cssSelector("input[name=\"city\"]");

    private static final By BY_TAG_P = By.tagName("p");

    private static final By BY_TAG_INPUT_OF_TYPE_SUBMIT = By.cssSelector("input[type=\"submit\"]");

    private WebDriver driver;

    /**
     * Инициализация драйвера google chrome перед тестом
     */
    @Before
    public void setUp() {
        System.setProperty(
                SYSTEM_PROPERTY_WEB_DRIVER_NAME,
                SYSTEM_PROPERTY_WEB_DRIVER_VALUE);

        driver = new ChromeDriver();
    }

    /**
     * Сворачивание драйвера после теста
     */
    @After
    public void tearDown() {
        driver.close();
    }

    /**
     * Тест
     */
    @Test
    public void test() {
        driver.get(ADMIN_API_MAIN_PAGE_HTML);

        final WebElement searchForm = driver.findElement(BY_ID_SEARCH_FORM_OF_FORM);

        final WebElement inputField = searchForm
                .findElement(BY_TAG_P)
                .findElement(BY_TAG_INPUT_OF_NAME_CITY);
        inputField.sendKeys(CITY_NAME);

        final WebElement submitButton = searchForm
                .findElement(BY_TAG_P)
                .findElement(BY_TAG_INPUT_OF_TYPE_SUBMIT);

        submitButton.click();

        final RestTemplate restTemplate = new RestTemplate();

        final HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);

        final URI url = URI.create(URL + CITY_NAME);

        final RequestEntity<?> requestEntity = new RequestEntity<Object>(
                headers, HttpMethod.GET, url, WeatherBroadcast.class);

        final WeatherBroadcast weatherBroadcast = restTemplate.exchange(requestEntity, WeatherBroadcast.class).getBody();

        Assert.assertEquals(CITY_NAME, weatherBroadcast.getLocation().getCity());
    }
}
