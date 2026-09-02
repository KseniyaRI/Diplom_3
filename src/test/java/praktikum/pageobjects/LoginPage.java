package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginHeader = By.xpath(".//h2[text()='Вход']");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.xpath(".//a[text()='Зарегистрироваться']");
    private final By restorePasswordLink = By.xpath(".//a[text()='Восстановить пароль']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Заполнить форму логина")
    public void fillForm(String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать «Войти»")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Войти")
    public void login(String email, String password) {
        fillForm(email, password);
        clickLoginButton();
    }

    @Step("Нажать «Зарегистрироваться»")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Нажать «Восстановить пароль»")
    public void clickRestorePasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(restorePasswordLink)).click();
    }

    @Step("Проверить, что открыта форма входа")
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader)).isDisplayed();
    }
}
