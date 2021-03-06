package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

@Controller
public class HelloController {


    @Autowired
    private RequestService requestService;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String hello(){
        return "andrewMainPage";
    }






}
