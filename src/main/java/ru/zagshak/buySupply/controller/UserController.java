package ru.zagshak.buySupply.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

@Controller
public class UserController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private EstimateService estimateService;

    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/profile/{user}")
    public String profile(

            @PathVariable User user,
            Model model,
            @AuthenticationPrincipal User currentUser

            ){

        model.addAttribute("isHome",currentUser.getId().equals(user.getId()));
        model.addAttribute("user",userService.get(user.getId()));
        model.addAttribute("estimates",estimateService.getAllForEstimated(user.getId()));
        return "profile";
    }


    @GetMapping("/home")
    public String profile(


            Model model,
            @AuthenticationPrincipal User currentUser

    ){

        model.addAttribute("isHome",true);
        model.addAttribute("user",currentUser);
        model.addAttribute("estimates",estimateService.getAllForEstimated(currentUser.getId()));
        return "profile";
    }


    @GetMapping("/profile/{user}/requests")
    public String requests(
            @PathVariable User user,
            Model model,
            @AuthenticationPrincipal User currentUser
    ){


        if(!currentUser.getId().equals(user.getId()))
            return "redirect:/profile/{user}";
        model.addAttribute("requests",requestService.getAllForOfferer(user.getId()));
        return "requests";
    }
}
