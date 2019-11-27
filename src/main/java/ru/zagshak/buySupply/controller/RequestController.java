package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.requestRepo.RequestJpaRepo;

@Controller
public class RequestController {

    @Autowired
    RequestJpaRepo requestJpaRepo;


    @GetMapping("/request/{id}")
    public String requestGet(
            @PathVariable int id,
            @AuthenticationPrincipal User me,
            Model model

    ){
        model.addAttribute("request",requestJpaRepo.getForRequesterByRequest(id));

        return "request";

    }

    @PostMapping("/request/{id}")
    public String requestDel(
            @PathVariable int id,
            @AuthenticationPrincipal User me
    ){
        requestJpaRepo.delete(id,me.getId());

        return "request";

    }
}
