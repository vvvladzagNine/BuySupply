package ru.zagshak.buySupply.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.domain.to.requestTO.RequestTO;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.util.RequestUtil;

import java.util.List;


import static ru.zagshak.buySupply.util.ValidationUtil.checkNew;


@RestController
public class RequestRestController {

    @Autowired
    private RequestService requestService;

    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/requests
     */
    @GetMapping("/rest/requests")
    public List<RequestTO> getAll() {
        return RequestUtil.makeTO(requestService.getAll());
    }

    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/offer/100005/requests
     */
    @GetMapping("/rest/offer/{offerId}/requests")
    public List<RequestTO> getAllForOffer(@PathVariable int offerId, @AuthenticationPrincipal User user) {
        return RequestUtil.makeTO(requestService.getAllForOffer(offerId, user.getId()));
    }

    /*
curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/my_requests
curl --user u:p  http://localhost:8080/rest/my_requests?message=like
 */
    @GetMapping("/rest/my_requests")
    public List<RequestTO> getAllForRequester(
            @RequestParam(required = false) Boolean responced,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) Integer offerId,
            @AuthenticationPrincipal User user
    ) {
        if (
                responced == null &&
                        message == null &&
                        offerId == null
        ) {
            return RequestUtil.makeTO(requestService.getAllForRequester(user.getId()));
        }

        else {
            return RequestUtil.filterRequestTO(
                    requestService.getAllForRequester(user.getId()),
                    responced,
                    message,
                    offerId
            );
        }

    }

    /*
curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/requests_for_me
 */
    @GetMapping("/rest/requests_for_me")
    public List<RequestTO> getAllForOfferer(
            @RequestParam(required = false) Boolean responced,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) Integer offerId,
            @AuthenticationPrincipal User user
    ) {
        if (
                responced == null &&
                        message == null &&
                        offerId == null
        ) {
            return RequestUtil.makeTO(requestService.getAllForOfferer(user.getId()));
        }

        else {
            return RequestUtil.filterRequestTO(
                    requestService.getAllForOfferer(user.getId()),
                    responced,
                    message,
                    offerId
            );
        }
    }



    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/my_requests/100008
     */
    @GetMapping("/rest/my_requests/{id}")
    public RequestTO getForRequester(@PathVariable int id,@AuthenticationPrincipal User user) {
        return RequestUtil.makeTO(requestService.get(id, user.getId()));
    }


    /*
    curl --user userS@yandex.ru:passwordS  http://localhost:8080/rest/requests_for_me/100007
     */
    @GetMapping("/rest/requests_for_me/{id}")
    public RequestTO getForOfferer(@PathVariable int id,@AuthenticationPrincipal User user) {
        return RequestUtil.makeTO(requestService.getForOfferer(id, user.getId()));
    }

    /*
    curl --user userS@yandex.ru:passwordS --data '{"id":null,"responced":false,"message":"New Request for buying your coke","offerId":100006,"requesterName":null}' -H "Content-Type: application/json" -X POST http://localhost:8080/rest/requests/
    */
    @PostMapping(value = "rest/requests/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public RequestTO save(@RequestBody RequestTO requestTO, @AuthenticationPrincipal User user) {
        Request r = new Request(requestTO);
        System.out.println("---r: " + r);
        checkNew(r);
        Request created = requestService.create(r,requestTO.getOfferId(),user.getId());

        return new RequestTO(created);
    }

    /*
    curl --user userS@yandex.ru:passwordS -X PUT "http://localhost:8080/rest/my_requests/100008?message=OneMoreTry"
    */
    @PutMapping(value = "rest/my_requests/{id}")
    public void update(@RequestParam String message, @AuthenticationPrincipal User user, @PathVariable int id) {
        Request r = requestService.get(id, user.getId());
        r.setMessage(message);
        requestService.update(r, r.getOffer().getId(), user.getId());
    }

    /*
    curl --user userS@yandex.ru:passwordS -X DELETE http://localhost:8080/rest/requests/100008
     */
    @DeleteMapping("rest/requests/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id,@AuthenticationPrincipal User user) {
        requestService.delete(id, user.getId());
    }


}