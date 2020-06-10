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
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.estimateRepo.EstimateJPARepo;
import ru.zagshak.buySupply.repository.offerRepo.OfferJPARepo;
import ru.zagshak.buySupply.repository.requestRepo.RequestJpaRepo;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Autowired
    private RequestJpaRepo requestJpaRepo;

    @Autowired
    private EstimateJPARepo estimateJPARepo;

    @Value("/uploads/")
    private String uploadPath;


    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,Model model) {

        model.addAttribute("error",error);
        return "andrewLogin";
    }

    @GetMapping("/logout")
    public String lout() {

        return "andrewMainPage";
    }

    @GetMapping("/profile/{user}")
    public String profile(

            @PathVariable User user,
            Model model,
            @AuthenticationPrincipal User currentUser

            ){

        List<Request> requests = requestJpaRepo.findAll();
        requests=requests.stream().filter(o->o.isResponced())
                .filter(o->((o.getOffer().getOfferer().getId().equals(currentUser.getId()) && o.getRequester().getId().equals(user.getId()))
                || (o.getOffer().getOfferer().getId().equals(user.getId()) && o.getRequester().getId().equals(currentUser.getId()))))
                .collect(Collectors.toList());



        if(!estimateService.getAllForEstimated(user.getId()).isEmpty())
            model.addAttribute("avg",estimateService.getAllForEstimated(user.getId()).stream().mapToInt(o->o.getStars()).average().getAsDouble());
        model.addAttribute("isHome",currentUser.getId().equals(user.getId()));
        model.addAttribute("user",userService.get(user.getId()));
        model.addAttribute("estimates",estimateService.getAllForEstimated(user.getId()));
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(user.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,6)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",user);
        model.addAttribute("estAv",!requests.isEmpty());
        return "andrewProfile";
    }

    @PostMapping("/profile/{user}")
    public String profileComment(

            @PathVariable User user,
            @RequestParam String comment,
            @RequestParam String stars,
            Model model,
            @AuthenticationPrincipal User currentUser

    ){
        if(estimateJPARepo.getAllByEstimatedIdAndEstimatorId(user.getId(),currentUser.getId())!=null ){
                Estimate est = estimateJPARepo.getAllByEstimatedIdAndEstimatorId(user.getId(),currentUser.getId());
            estimateService.delete(est.getId(),currentUser.getId());
        }
        estimateService.create(new Estimate(Integer.parseInt(stars),comment, LocalDateTime.now()),user.getId(),currentUser.getId());



        List<Request> requests = requestJpaRepo.findAll();
        requests=requests.stream().filter(o->o.isResponced())
                .filter(o->((o.getOffer().getOfferer().getId().equals(currentUser.getId()) && o.getRequester().getId().equals(user.getId()))
                        || (o.getOffer().getOfferer().getId().equals(user.getId()) && o.getRequester().getId().equals(currentUser.getId()))))
                .collect(Collectors.toList());

        if(!estimateService.getAllForEstimated(user.getId()).isEmpty())
            model.addAttribute("avg",estimateService.getAllForEstimated(user.getId()).stream().mapToInt(o->o.getStars()).average().getAsDouble());
        model.addAttribute("isHome",currentUser.getId().equals(user.getId()));
        model.addAttribute("user",userService.get(user.getId()));
        model.addAttribute("estimates",estimateService.getAllForEstimated(user.getId()));
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(user.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,6)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",user);
        model.addAttribute("estAv",requests);


        return "redirect:/profile/"+user.getId();
    }


    @GetMapping("/home")
    public String profile(


            Model model,
            @AuthenticationPrincipal User currentUser

    ){

        model.addAttribute("isHome",true);
        if(!estimateService.getAllForEstimated(currentUser.getId()).isEmpty())
            model.addAttribute("avg",estimateService.getAllForEstimated(currentUser.getId()).stream().mapToInt(o->o.getStars()).average().getAsDouble());
        model.addAttribute("user",currentUser);
        model.addAttribute("estimates",estimateService.getAllForEstimated(currentUser.getId()));
        model.addAttribute("offers",offerJpaRepo.getAllByOffereId(currentUser.getId()).stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,6)+"...");return  o;}).collect(Collectors.toList()));
        model.addAttribute("me",currentUser);
        return "andrewProfile";
    }

    @GetMapping("/home/requests")
    public String myRequests(


            Model model,
            @AuthenticationPrincipal User currentUser

    ){

        model.addAttribute("me",currentUser);
        model.addAttribute("requests",requestService.getAllForOfferer(currentUser.getId()).stream().filter(o -> !o.isResponced()).collect(Collectors.toList()));
        return "requests";
    }
    @GetMapping("/home/requests/{offerId}")
    public String myRequests(

            @PathVariable int offerId,
            Model model,
            @AuthenticationPrincipal User currentUser

    ){

        model.addAttribute("me",currentUser);
        model.addAttribute("requests",requestJpaRepo.getAllRequestedForOffer(offerId,currentUser.getId()));
        model.addAttribute("responsed","responsed");
        return "requests";
    }

    @PostMapping("/home/requests/{offerId}")
    public String myRequestsReject(

            @PathVariable int offerId,
            Model model,
            @RequestParam int requestId,
            @AuthenticationPrincipal User currentUser

    ){

        requestService.delete(requestId,requestService.get(requestId).getRequester().getId());
        model.addAttribute("me",currentUser);
        model.addAttribute("requests",requestJpaRepo.getAllRequestedForOffer(offerId,currentUser.getId()));
        model.addAttribute("responsed","responsed");
        return "requests";
    }


    @GetMapping("/home/supplies")
    public String mySupplies(Model model, @AuthenticationPrincipal User me){
        List<Offer> ofs =offerJpaRepo.getOfferMyRequestedOffersSupply(me.getId());
        ofs.addAll(offerJpaRepo.getOfferByRequestUserSupply(me.getId()));
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        model.addAttribute("sup","sup");
        return "mySupplies";
    }

    @GetMapping("/home/buys")
    public String myBuys(Model model, @AuthenticationPrincipal User me){
        List<Offer> ofs =offerJpaRepo.getOfferMyRequestedOffersBuy(me.getId());
        ofs.addAll(offerJpaRepo.getOfferByRequestUserBuy(me.getId()));
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        return "mySupplies";
    }

    @PostMapping("/home/requests")
    public String myRequests(


            Model model,
            @RequestParam int requestId,
            @RequestParam String act,
            @AuthenticationPrincipal User currentUser

    ){

        if(act.equals("approve")){
            Request r = requestService.get(requestId);
            r.setResponced(true);
            requestService.update(r,r.getOffer().getId(),r.getRequester().getId());
        }
        if(act.equals("reject")){

            requestService.delete(requestId,requestService.get(requestId).getRequester().getId());
        }

        model.addAttribute("me",currentUser);
        model.addAttribute("requests",requestService.getAllForOfferer(currentUser.getId()).stream().filter(o -> !o.isResponced()).collect(Collectors.toList()));
        return "requests";
    }

    @GetMapping("/edit_profile/")
    public String editProfilePage(
            @AuthenticationPrincipal User u,
            Model model){
        model.addAttribute("user",u);
        return "andrewProfileEdit";
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
        model.addAttribute("requests",requestService.getAllForOfferer(currentUser.getId()).stream().filter(o -> !o.isResponced()).collect(Collectors.toList()));
        return "andrewRequests";
    }


}
