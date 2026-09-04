package praktikum;

import java.util.UUID;

public class TestUserData {

    private TestUserData() {
    }

    public static User randomUser() {
        String suffix = UUID.randomUUID().toString().substring(0, 8);
        return new User(
                "praktikum_" + suffix + "@yandex.ru",
                "Pass" + suffix,
                "TestUser");
    }
}
