package praktikum.pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    private final By logoLink = By.xpath(".//a[@href='/' and not(contains(., 'Конструктор'))]");
    private final By bunsTab = By.xpath(".//span[text()='Булки']");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']");
    private final By activeBunsTab = By.xpath(
            ".//div[contains(@class,'tab_tab_type_current')]//span[text()='Булки']");
    private final By activeSaucesTab = By.xpath(
            ".//div[contains(@class,'tab_tab_type_current')]//span[text()='Соусы']");
    private final By activeFillingsTab = By.xpath(
            ".//div[contains(@class,'tab_tab_type_current')]//span[text()='Начинки']");
    private final By orderButton = By.xpath(".//button[text()='Оформить заказ']");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать «Войти в аккаунт»")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Нажать «Личный кабинет»")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Нажать «Конструктор»")
    public void clickConstructorButton() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать логотип")
    public void clickLogoLink() {
        wait.until(ExpectedConditions.elementToBeClickable(logoLink)).click();
    }

    @Step("Нажать «Булки»")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Нажать «Соусы»")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Нажать «Начинки»")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
    }

    @Step("Проверить, что видна кнопка «Оформить заказ»")
    public boolean isPlaceOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    @Step("Проверить, что активна вкладка «Булки»")
    public boolean isBunsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeBunsTab)).isDisplayed();
    }

    @Step("Проверить, что активна вкладка «Соусы»")
    public boolean isSaucesTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeSaucesTab)).isDisplayed();
    }

    @Step("Проверить, что активна вкладка «Начинки»")
    public boolean isFillingsTabActive() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeFillingsTab)).isDisplayed();
    }
}
