package ru.zagshak.buySupply.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTOSave;
import ru.zagshak.buySupply.service.OfferService;
import static ru.zagshak.buySupply.util.ValidationUtil.checkNew;


import java.util.List;

@RestController()
public class OfferRestController {


    @Autowired
    private OfferService offerService;

    @GetMapping("/rest/offer")
    public List<OfferTO> offer(){
        return offerService.getAllTO();
    }

/*
curl --user userS@yandex.ru:passwordS --data '{"buyOffer":false,"description":"Wood234","amount":120,"cost":330,"offererName":"userS@yandex.ru","offererId":null,"dateTime":"2015-03-31T00:00:00","categoryId":100003}' -H "Content-Type: application/json" -X POST http://localhost:8080/rest/offer/100001
 */

    @PostMapping(value = "rest/offer/{offererId}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public OfferTOSave saveOffer(@RequestBody OfferTOSave offerTOSave, @PathVariable int offererId){
        Offer f = new Offer(offerTOSave);
        checkNew(f);
        Offer created = offerService.create(f,offererId,offerTOSave.getCategoryId());


        return new OfferTOSave(created);
    }





}
