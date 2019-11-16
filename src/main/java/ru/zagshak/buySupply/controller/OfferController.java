package ru.zagshak.buySupply.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.CategoryJPARepo;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    CategoryJPARepo categoryJPARepo;



    @GetMapping("/offers")
    public String offers(Model model, @AuthenticationPrincipal User me){
        List<Offer> ofs = new ArrayList<>();
        ofs = offerService.getAll();
        ofs.stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,8)+"...");return  o;}).collect(Collectors.toList());
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        return "offers";
    }


    @GetMapping("/offer/{id}")
    public String offer(Model model, @AuthenticationPrincipal User me, @PathVariable int id){
        model.addAttribute("offer",offerService.get(id));
        model.addAttribute("me",me);
        return "offer";
    }

    @PostMapping(value = "/offer/{id}",params={"message"})
    public String makeRequest(
            @AuthenticationPrincipal User me,
            @PathVariable int id,
            @RequestParam String message){
        requestService.create(new Request(message),id,me.getId());
        return "requestSuccess";
    }
    @PostMapping(value = "/offer/{id}",params={})
    public String deleteOffer(
            @AuthenticationPrincipal User me,
            @PathVariable int id
            ){
        offerService.delete(id,me.getId());
        return "redirect:/offers";
    }

    @PostMapping("/offers")
    public String offersDelete(Model model, @AuthenticationPrincipal User me, @RequestParam int offerId){
        offerService.delete(offerId,me.getId());
        List<Offer> ofs = offerService.getAll();
        ofs.stream().map(o -> {if(o.getDescription().length()>6)o.setDescription(o.getDescription().substring(0,8)+"...");return  o;}).collect(Collectors.toList());
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        return "offers";
    }

    @GetMapping("/offers/create")
    public String offersCreate(Model model){
        model.addAttribute("categories",categoryJPARepo.findAll());
        return "offerCreate";
    }
    @GetMapping("/offers/edit/{offerId}")
    public String offersEdit(Model model, @AuthenticationPrincipal User u, @PathVariable int offerId){
        Offer o  = offerService.get(offerId);
        if(o.getOfferer().getId().equals(u.getId())) {
            model.addAttribute("offer", o);
            model.addAttribute("categories",categoryJPARepo.findAll());
            return "offerCreate";
        }
        else {
            return "redirect:/offers";
        }
    }

    @PostMapping("/offers/create")
    public String offersCreatePost(
            Model model,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost,
            @RequestParam("amount")String amount,
            @RequestParam("category") String category,
            @RequestParam(value = "exampleRadios") String bs,
            @AuthenticationPrincipal User u

            ){
        Offer f = new Offer(bs.equals("B"),description,Integer.parseInt(cost), LocalDateTime.now(),Integer.parseInt(amount));
        Offer created = offerService.create(f,u.getId(),categoryJPARepo.findByName(category).getId());
        model.addAttribute("created",created);
        return "createSuccess";
    }
}
