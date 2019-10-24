package ru.zagshak.buySupply.domain.to.UserTO;

import ru.zagshak.buySupply.domain.User;

import java.util.Date;

public class CurrentUserTO extends UserTO {

    private String password;

    public CurrentUserTO() {

    }

    public CurrentUserTO(User u, String password) {
        super(u);
        this.password = password;
    }

    public CurrentUserTO(String name, Integer id, String email, String ava, String city, Date registered, String password) {
        super(name, id, email, ava, city, registered);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
