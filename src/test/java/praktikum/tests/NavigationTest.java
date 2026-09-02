package praktikum.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.BaseTest;
import praktikum.TestUserData;
import praktikum.User;
import praktikum.UserSteps;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class NavigationTest extends BaseTest {

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

    //общий шаг: авторизация + клик по кнопке "Личный кабинет"
    private void loginAsTestUser() {
        new MainPage(driver).clickPersonalAccountButton();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        new MainPage(driver).clickPersonalAccountButton();
    }

    @Test
    @DisplayName("Переход в Личный Кабинет со страницы конструктора")
    public void shouldNavigateToProfileFromPersonalAccountButton() {
        loginAsTestUser();
        assertTrue(new ProfilePage(driver).isProfileOpen(),
                "После нажатия на кнопку «Личный Кабинет» должна была открыться страница профиля");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке «Конструктор»")
    public void shouldNavigateToConstructorFromProfile() {
        loginAsTestUser();
        new MainPage(driver).clickConstructorButton();
        assertTrue(new MainPage(driver).isPlaceOrderButtonDisplayed(),
                "После нажатия на кнопку «Конструктор» должна была открыться страница конструктора с кнопкой «Оформить заказ»");
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор по логотипу")
    public void shouldNavigateToConstructorFromLogo() {
        loginAsTestUser();
        new MainPage(driver).clickLogoLink();
        assertTrue(new MainPage(driver).isPlaceOrderButtonDisplayed(),
                "После нажатия на логотип должна была открыться страница конструктора с кнопкой «Оформить заказ»");
    }
}
