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
import ru.zagshak.buySupply.repository.requestRepo.RequestJpaRepo;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.service.UserService;
import ru.zagshak.buySupply.util.OfferUtil;

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

    @Autowired
    RequestJpaRepo requestJpaRepo;



    @GetMapping("/offers")
    public String offers(
            Model model,
            @AuthenticationPrincipal User me,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String buy,
            @RequestParam(required = false) String supply,
            @RequestParam(required = false) Integer pricePerUnitFrom,
            @RequestParam(required = false) Integer pricePerUnitTo,
            @RequestParam(required = false) String fragment,
            @RequestParam(required = false) String offererName
    ){
        Boolean isBuyOffer=null;
        if(buy!=null) isBuyOffer=true;
        if(supply!=null) isBuyOffer=false;
        if(buy!=null && supply!=null)isBuyOffer=null;
        List<Offer> ofs = new ArrayList<>();
        ofs = offerService.getAll();
        ofs= OfferUtil.filterOffer(ofs,categoryName,isBuyOffer,pricePerUnitFrom,pricePerUnitTo,fragment,offererName);
        ofs.stream().map(o -> {if(o.getDescription().length()>10)o.setDescription(o.getDescription().substring(0,9)+"...");return  o;}).collect(Collectors.toList());
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        model.addAttribute("types",categoryJPARepo.findAll().stream().map(o->o.getName()).collect(Collectors.toList()));
        return "andrewOffers";
    }





    @GetMapping("/offer/{id}")
    public String offer(Model model, @AuthenticationPrincipal User me, @PathVariable int id){
        Offer f =offerService.get(id);
        model.addAttribute("offer",f);
        model.addAttribute("me",me);
        Request r = requestJpaRepo.getForRequester(f.getId(),me.getId());
        model.addAttribute("req",r);
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
        ofs.stream().map(o -> {if(o.getDescription().length()>10)o.setDescription(o.getDescription().substring(0,9)+"...");return  o;}).collect(Collectors.toList());
        model.addAttribute("offers",ofs);
        model.addAttribute("me",me);
        return "redirect:/offers";
    }

    @GetMapping("/offers/create")
    public String offersCreate(Model model){
        model.addAttribute("categories",categoryJPARepo.findAll());
        return "andrewCreateOffer";
    }



    @PostMapping("/offer/edit/{id}")
    public String offersEditPost(
            Model model,
            @PathVariable int id,
            @RequestParam("description") String description,
            @RequestParam("cost") String cost,
            @RequestParam("amount")String amount,
            @RequestParam("category") String category
    ){
        Offer o = offerService.get(id);
        o.setDescription(description);
        o.setCategory(categoryJPARepo.findByName(category));
        o.setAmount(Integer.parseInt(amount));
        o.setCost(Integer.parseInt(cost));
        offerService.update(o,o.getOfferer().getId(),o.getCategory().getId());

        return "redirect:/offer/"+id;
    }
    @GetMapping("/offer/edit/{id}")
    public String offersEdit(
            Model model,
            @AuthenticationPrincipal User u,
            @PathVariable int id
    ){
        Offer o  = offerService.get(id);
        if(o.getOfferer().getId().equals(u.getId())) {
            model.addAttribute("offer", o);
            model.addAttribute("categories",categoryJPARepo.findAll());
            return "andrewCreateOffer";
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
