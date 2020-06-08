package ru.zagshak.buySupply;

import ru.zagshak.buySupply.domain.Role;
import ru.zagshak.buySupply.domain.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static ru.zagshak.buySupply.domain.AbstractBaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER1_ID = START_SEQ;
    public static final int USER2_ID = START_SEQ + 1;
    public static final int ADMIN_ID = START_SEQ + 2;

    public static final User USER1 = new User(USER1_ID, "UserB", "u", "p","Moscow", Role.ROLE_USER);
    public static final User USER2 = new User(USER2_ID, "UserS", "userS@yandex.ru", "passwordS","Moscow", Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com","admin","Moscow",Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered","roles","password","requests","offers","estimates");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered","roles","password","requests","offers","estimates");
    }
}
