package ru.zagshak.buySupply.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTO;
import ru.zagshak.buySupply.domain.to.OfferTO.OfferTOSave;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.util.OfferUtil;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNew;
import static ru.zagshak.buySupply.util.ValidationUtil.assureIdConsistent;


import java.util.List;

@RestController
public class OfferRestController {


    @Autowired
    private OfferService offerService;


    /*
    curl --user userS@yandex.ru:passwordS  'http://localhost:8080/rest/offer?pricePerUnitTo=6&pricePerUnitFrom=2&isBuyOffer=true&fragment=Piz&offererName=User&categoryName=Food'
    curl --user userS@yandex.ru:passwordS  'http://localhost:8080/rest/offer'

     */
    @GetMapping("/rest/offer")
    public List<OfferTO> offer(
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) Boolean isBuyOffer,
            @RequestParam(required = false) Integer pricePerUnitFrom,
            @RequestParam(required = false) Integer pricePerUnitTo,
            @RequestParam(required = false) String fragment,
            @RequestParam(required = false) String offererName
    ) {
        if (
                (categoryName == null || categoryName.isEmpty()) &&
                        isBuyOffer == null &&
                        pricePerUnitFrom == null &&
                        pricePerUnitTo == null &&
                        (fragment == null || fragment.isEmpty()) &&
                        (offererName == null || offererName.isEmpty())
        ) {
            return offerService.getAllTO();
        } else {
            return offerService.getFilteredTO(
                    categoryName,
                    isBuyOffer,
                    pricePerUnitFrom,
                    pricePerUnitTo,
                    fragment,
                    offererName
            );
        }
    }


    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/offer/100005
     */
    @GetMapping("/rest/offer/{id}")
    public OfferTO getOffer(@PathVariable int id) {
        return offerService.getTO(id);
    }


    /*
    curl --user userS@yandex.ru:passwordS -X DELETE http://localhost:8080/rest/offer/100005
     */
    @DeleteMapping("rest/offer/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable int id,
            @AuthenticationPrincipal User user) {
        offerService.delete(id, user.getId());
    }


    /*
    curl --user userS@yandex.ru:passwordS --data '{"buyOffer":false,"id":null,"description":"Wood234","amount":120,"cost":330,"offererName":"userS@yandex.ru","offererId":null,"dateTime":"2015-03-31T00:00:00","categoryId":100003}' -H "Content-Type: application/json" -X POST http://localhost:8080/rest/offer/
    */
    @PostMapping(value = "rest/offer/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public OfferTOSave saveOffer(
            @RequestBody OfferTOSave offerTOSave,
            @AuthenticationPrincipal User user) {
        Offer f = new Offer(offerTOSave);
        checkNew(f);
        Offer created = offerService.create(f, user.getId(), offerTOSave.getCategoryId());


        return new OfferTOSave(created);
    }


    /*
    curl --user userS@yandex.ru:passwordS --data '{"buyOffer":false,"id":100005,"description":"Rock","amount":130,"cost":430,"offererName":"userS@yandex.ru","offererId":100001,"dateTime":"2015-03-31T00:00:00","categoryId":100004}' -H "Content-Type: application/json" -X PUT http://localhost:8080/rest/offer/100005
    */
    @PutMapping(value = "rest/offer/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateOffer(
            @RequestBody OfferTOSave offerTOSave,
            @AuthenticationPrincipal User user,
            @PathVariable int id) {

        Offer offer = new Offer(offerTOSave, offerTOSave.getId());
        assureIdConsistent(offer, id);

        offerService.update(offer, user.getId(), offerTOSave.getCategoryId());


    }


}
