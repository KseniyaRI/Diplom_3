package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By nameInput = By.xpath(".//label[text()='Имя']/following-sibling::input");
    private final By emailInput = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By passwordError = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Заполнить форму регистрации")
    public void fillForm(String name, String email, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
    }

    @Step("Нажать «Зарегистрироваться»")
    public void clickRegisterButton() {
        wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
    }

    @Step("Зарегистрировать пользователя")
    public void register(String name, String email, String password) {
        fillForm(name, email, password);
        clickRegisterButton();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).getText();
    }

    @Step("Нажать «Войти» в форме регистрации")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}
