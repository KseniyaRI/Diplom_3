package praktikum.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import praktikum.BaseTest;
import praktikum.pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConstructorTest extends BaseTest {

    @Test
    @DisplayName("Переключение на вкладку «Соусы»")
    public void shouldSwitchToSaucesSection() {
        new MainPage(driver).clickSaucesTab();
        assertTrue(new MainPage(driver).isSaucesTabActive(),
                "После нажатия на вкладку «Соусы» должна была открыться страница конструктора с активной вкладкой «Соусы»");
    }

    @Test
    @DisplayName("Переключение на вкладку «Начинки»")
    public void shouldSwitchToFillingsSection() {
        new MainPage(driver).clickFillingsTab();
        assertTrue(new MainPage(driver).isFillingsTabActive(),
                "После нажатия на вкладку «Начинки» должна была открыться страница конструктора с активной вкладкой «Начинки»");
    }

    @Test
    @DisplayName("Переключение на вкладку «Булки»")
    public void shouldSwitchToBunsSection() {
        new MainPage(driver).clickSaucesTab();
        new MainPage(driver).clickBunsTab();
        assertTrue(new MainPage(driver).isBunsTabActive(),
                "После нажатия на вкладку «Булки» должна была открыться страница конструктора с активной вкладкой «Булки»");
    }
}
