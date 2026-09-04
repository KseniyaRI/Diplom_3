package praktikum.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.BaseTest;
import praktikum.TestUserData;
import praktikum.User;
import praktikum.UserSteps;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.RegisterPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterTest extends BaseTest {

    private final UserSteps userSteps = new UserSteps();
    private User user;

    @AfterEach
    public void deleteCreatedUser() {
        if (user == null) {
            return;
        }
        String accessToken = userSteps.login(user).path("accessToken");
        if (accessToken != null) {
            userSteps.deleteUser(accessToken);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    public void shouldRegisterNewUser() {
        user = TestUserData.randomUser();

        new MainPage(driver).clickPersonalAccountButton();
        new LoginPage(driver).clickRegisterLink();
        new RegisterPage(driver).register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue(new LoginPage(driver).isDisplayed(),
                "После успешной регистрации должна была открыться форма входа");
    }

    @Test
    @DisplayName("Ошибка при пароле короче шести символов")
    public void shouldShowErrorWhenPasswordIsTooShort() {
        new MainPage(driver).clickPersonalAccountButton();
        new LoginPage(driver).clickRegisterLink();

        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.register("Test", "short_" + System.nanoTime() + "@yandex.ru", "12345");

        assertTrue(registerPage.getPasswordErrorText().contains("Некорректный пароль"),
                "Должно отображаться сообщение о некорректном пароле");
    }
}
