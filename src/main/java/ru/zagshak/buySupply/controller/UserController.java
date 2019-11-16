package ru.zagshak.buySupply.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.offerRepo.OfferJPARepo;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Autowired
    private OfferJPARepo offerJpaRepo;

    @Value("${upload.path}")
    private String uploadPath;


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
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(user.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,8)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",user);
        return "profile";
    }

    @PostMapping("/profile/{user}")
    public String profileComment(

            @PathVariable User user,
            @RequestParam String comment,
            @RequestParam String stars,
            Model model,
            @AuthenticationPrincipal User currentUser

    ){
        estimateService.create(new Estimate(Integer.parseInt(stars),comment, LocalDateTime.now()),user.getId(),currentUser.getId());
        model.addAttribute("isHome",currentUser.getId().equals(user.getId()));
        model.addAttribute("user",userService.get(user.getId()));
        model.addAttribute("estimates",estimateService.getAllForEstimated(user.getId()));
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(user.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,8)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",user);

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
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(currentUser.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,8)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",currentUser);
        return "profile";
    }

    @GetMapping("/edit_profile/")
    public String editProfilePage(
            @AuthenticationPrincipal User u,
            Model model){
        model.addAttribute("user",u);
        return "profileEdit";
    }

    @PostMapping("/edit_profile/")
    public String saveProfile(
            Model model,
            @AuthenticationPrincipal User user,
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam("city") String city,
            @RequestParam("file") MultipartFile file
            ) throws IOException {
        model.addAttribute("user",user);
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            user.setAva(resultFilename);
        }
        user.setEmail(email);
        user.setCity(city);
        user.setName(username);
        userService.save(user);
        return "redirect:/home";
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
