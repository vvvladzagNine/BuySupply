package ru.zagshak.buySupply.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagshak.buySupply.domain.Category;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.repository.CategoryJPARepo;
import ru.zagshak.buySupply.repository.offerRepo.OfferJPARepo;
import ru.zagshak.buySupply.repository.requestRepo.RequestJpaRepo;
import ru.zagshak.buySupply.repository.userRepo.UserJpaRepo;

@Controller
public class AdminController {

    @Autowired
    CategoryJPARepo categoryJPARepo;

    @Autowired
    UserJpaRepo userJpaRepo;

    @Autowired
    RequestJpaRepo requestJpaRepo;

    @Autowired
    OfferJPARepo offerJPARepo;


    @GetMapping("/admin")
    public String panel(
            @AuthenticationPrincipal User u
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        return "adminPanel";
        else return "redirect:/home";
    }

    @GetMapping("/admin/categories")
    public String categories(
            @AuthenticationPrincipal User u,
            Model model
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            model.addAttribute("cats",categoryJPARepo.findAll());
            return "adminCat";
        }

        else return "redirect:/home";
    }


    @PostMapping("/admin/categories")
    public String categoriesDel(
            @AuthenticationPrincipal User u,
            @RequestParam int id
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            categoryJPARepo.delete(categoryJPARepo.findById(id).get());
            return "adminCat";
        }

        else return "redirect:/home";
    }

    @GetMapping("/admin/categories/{id}")
    public String categories(
            @AuthenticationPrincipal User u,
            Model model,
            @PathVariable int id
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            model.addAttribute("cat",categoryJPARepo.findById(id).get());
            return "adminCatEdit";
        }

        else return "redirect:/home";
    }

    @PostMapping("/admin/categories/{id}")
    public String categoriesEdit(
            @AuthenticationPrincipal User u,
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam String unit,
            @RequestParam String typeOfBatch
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            Category c = categoryJPARepo.findById(id).get();
            c.setTypeOfBatch(typeOfBatch);
            c.setUnit(unit);
            c.setName(name);
            categoryJPARepo.save(c);

            return "redirect:/admin/categories/";
        }

        else return "redirect:/home";
    }

    @GetMapping("/admin/add_category")
    public String categories(
            @AuthenticationPrincipal User u

    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {

            return "adminCatEdit";
        }

        else return "redirect:/home";
    }

    @PostMapping("/admin/add_category")
    public String categoriesAdd(
            @AuthenticationPrincipal User u,
            @RequestParam String name,
            @RequestParam String unit,
            @RequestParam String typeOfBatch
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            Category c = new Category();
            c.setTypeOfBatch(typeOfBatch);
            c.setUnit(unit);
            c.setName(name);
            categoryJPARepo.save(c);

            return "redirect:/admin/categories/";
        }

        else return "redirect:/home";
    }


    @GetMapping("/admin/offers")
    public String offers(
            @AuthenticationPrincipal User u,
            Model model
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            model.addAttribute("offers",offerJPARepo.findAll());
            return "adminOf";
        }
        else return "redirect:/home";
    }
    @PostMapping("/admin/offers")
    public String offersDelete(
            @AuthenticationPrincipal User u,
            @RequestParam int offerId
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            Offer o = offerJPARepo.getOne(offerId);
            offerJPARepo.delete(o.getId(),o.getOfferer().getId());
            return "redirect:/admin/offers";
        }
        else return "redirect:/home";
    }

    @GetMapping("/admin/users")
    public String users(
            @AuthenticationPrincipal User u,
            Model model
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            model.addAttribute("users",userJpaRepo.findAll());
            return "adminUs";
        }

        else return "redirect:/home";
    }
    @PostMapping("/admin/users")
    public String usersBAN(
            @AuthenticationPrincipal User u,
            @RequestParam int id,
            @RequestParam String act
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            if(act.equals("ban")){
                User s =userJpaRepo.findById(id).get();
                s.setEnabled(false);
                userJpaRepo.save(s);
            }
            if(act.equals("unban")){
                User s =userJpaRepo.findById(id).get();
                s.setEnabled(true);
                userJpaRepo.save(s);
            }
            return "redirect:/admin/users";
        }

        else return "redirect:/home";
    }

    @GetMapping("/admin/requests")
    public String requests(
            @AuthenticationPrincipal User u,
            Model model
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            model.addAttribute("reqs",requestJpaRepo.findAll());
            return "adminReq";
        }

        else return "redirect:/home";
    }

    @PostMapping("/admin/requests")
    public String requestsDel(
            @AuthenticationPrincipal User u,
            @RequestParam int id
    ){
        if(u.getEmail().equals("admin@gmail.com"))
        {
            Request r = requestJpaRepo.findById(id).get();
            requestJpaRepo.delete(r.getId(),r.getRequester().getId());
            return "redirect:/admin/requests";
        }

        else return "redirect:/home";
    }

}
