package praktikum;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        String browserName = System.getProperty("browser", "chrome");
        driver = new Browser().getWebDriver(browserName);
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
