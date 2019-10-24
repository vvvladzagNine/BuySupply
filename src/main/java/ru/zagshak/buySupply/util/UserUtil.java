package ru.zagshak.buySupply.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.domain.to.UserTO.CurrentUserTO;

public class UserUtil {
    public static User updateFromTo(User user, CurrentUserTO userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setCity(userTo.getCity());

        return user;
    }

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder) {
        String password = user.getPassword();
        user.setPassword(StringUtils.hasText(password) ? passwordEncoder.encode(password) : password);
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
