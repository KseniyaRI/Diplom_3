package praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Browser {

    private static final String DEFAULT_YANDEX_BINARY =
            "/Applications/Yandex.app/Contents/MacOS/Yandex";
    private static final String DEFAULT_YANDEX_DRIVER =
            "src/test/resources/yandexdriver";

    public WebDriver getWebDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                return createChromeDriver();
            case "yandex":
                return createYandexDriver();
            default:
                throw new IllegalArgumentException("Unknown browser: " + browserName);
        }
    }

    private WebDriver createChromeDriver() {
        return new ChromeDriver();
    }

    private WebDriver createYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        options.setBinary(System.getProperty("yandex.binary", DEFAULT_YANDEX_BINARY));
        System.setProperty(
                "webdriver.chrome.driver",
                System.getProperty("yandex.driver", DEFAULT_YANDEX_DRIVER));
        return new ChromeDriver(options);
    }
}
