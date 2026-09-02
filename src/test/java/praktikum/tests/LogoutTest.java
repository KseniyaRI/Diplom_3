package praktikum.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.BaseTest;
import praktikum.TestUserData;
import praktikum.User;
import praktikum.UserSteps;
import praktikum.pageobjects.LoginPage;
import praktikum.pageobjects.MainPage;
import praktikum.pageobjects.ProfilePage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogoutTest extends BaseTest {
    
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

    @Test
    @DisplayName("Выход из аккаунта")
    public void shouldLogoutFromProfile() {
        new MainPage(driver).clickPersonalAccountButton();
        new LoginPage(driver).login(user.getEmail(), user.getPassword());
        new MainPage(driver).clickPersonalAccountButton();
        new ProfilePage(driver).clickLogoutButton();

        assertTrue(new LoginPage(driver).isDisplayed(),
                "После выхода из аккаунта должна была открыться форма логина с заголовком «Вход»");
    }
}
