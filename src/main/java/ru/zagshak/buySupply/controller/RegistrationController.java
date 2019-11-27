package ru.zagshak.buySupply.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.Role;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.service.UserService;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String login() {
        return "andrewRegistration";
    }


    @PostMapping("/registration")
    public String registration(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String city,
            @RequestParam String username
            ) {
        User u = new User();
        u.setPassword(password);
        u.setEmail(email);
        u.setCity(city);
        u.setName(username);
        u.setRoles(Collections.singleton(Role.ROLE_USER));

        userService.create(u);
        return "andrewRegistration";
    }
}
