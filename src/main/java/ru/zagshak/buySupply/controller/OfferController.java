package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

@Controller
public class OfferController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;
}
