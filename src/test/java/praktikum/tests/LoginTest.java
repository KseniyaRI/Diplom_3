package praktikum.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.BaseTest;
import praktikum.TestUserData;
import praktikum.User;
import praktikum.UserSteps;
import praktikum.pageobjects.ForgotPasswordPage;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest extends BaseTest {

    private final UserSteps userSteps = new UserSteps();
    private User user;
    private String accessToken;

    @BeforeEach
    public void createUser() {
        accessToken = null;
        user = TestUserData.randomUser();
        accessToken = userSteps.createUser(user).path("accessToken");
    }

    @AfterEach
    public void deleteCreatedUser() {
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
            accessToken = null;
        }
    }

    private void assertAuthorizedMainPageOpened() {
        assertTrue(new MainPage(driver).isPlaceOrderButtonDisplayed(),
                "После успешной авторизации должна была открыться главная с кнопкой «Оформить заказ»");
    }

    @Test
    @DisplayName("Вход со страницы конструктора")
    public void shouldLoginFromMainPage() {
        new MainPage(driver).clickLoginButton();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        assertAuthorizedMainPageOpened();
    }

    @Test
    @DisplayName("Вход с формы регистрации")
    public void shouldLoginFromRegisterPage() {
        new MainPage(driver).clickLoginButton();
        new LoginPage(driver).clickRegisterLink();
        new RegisterPage(driver).clickLoginLink();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        assertAuthorizedMainPageOpened();
    }

    @Test
    @DisplayName("Вход с формы восстановления пароля")
    public void shouldLoginFromForgotPasswordPage() {
        new MainPage(driver).clickLoginButton();
        new LoginPage(driver).clickRestorePasswordLink();
        new ForgotPasswordPage(driver).clickLoginLink();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        assertAuthorizedMainPageOpened();
    }

    @Test
    @DisplayName("Вход через кнопку «Личный Кабинет»")
    public void shouldLoginFromPersonalAccountPage() {
        new MainPage(driver).clickPersonalAccountButton();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        assertAuthorizedMainPageOpened();
    }
}
