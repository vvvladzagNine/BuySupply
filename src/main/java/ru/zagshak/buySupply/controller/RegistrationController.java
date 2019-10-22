package ru.zagshak.buySupply.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.service.UserService;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String login() {
        return "registration";
    }


    @PostMapping("/registration")
    public String regitration(
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
        userService.create(u);
        return "registration";
    }
}
