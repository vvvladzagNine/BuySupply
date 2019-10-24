package ru.zagshak.buySupply.controller.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.zagshak.buySupply.domain.User;
import ru.zagshak.buySupply.domain.to.UserTO.CurrentUserTO;
import ru.zagshak.buySupply.domain.to.UserTO.UserTO;
import ru.zagshak.buySupply.service.UserService;

import java.net.URI;

import static ru.zagshak.buySupply.util.ValidationUtil.checkNew;

@RestController
public class UserRestController {



    @Autowired
    private UserService userService;

    /*
        curl --user userS@yandex.ru:passwordS  'http://localhost:8080/rest/user'
    */
    @GetMapping("/rest/user")
    public CurrentUserTO get(@AuthenticationPrincipal User user) {
        return new CurrentUserTO(user,user.getPassword());
    }

    /*
        curl --user userS@yandex.ru:passwordS --data '{"name":"UserS2","id":null,"email":"user2S@yandex.ru","ava":null,"city":"Piter","registered":null,"password":"querty123"}'  -H "Content-Type: application/json" -X PUT 'http://localhost:8080/rest/user'
     */
    @PutMapping(value = "/rest/user", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update( @AuthenticationPrincipal User user,@RequestBody CurrentUserTO userUpdate) {
        userService.update(user,userUpdate);
    }


    /*
        curl --user userS@yandex.ru:passwordS  'http://localhost:8080/rest/user/100000'
    */
    @GetMapping("/rest/user/{user}")
    public UserTO getUser(@PathVariable User user) {
        return new UserTO(user);
    }

    /*
        curl  --data '{"name":"NewDude","id":null,"email":"newdude@yandex.ru","ava":null,"city":"Boston","registered":null,"password":"querty"}'  -H "Content-Type: application/json" -X POST 'http://localhost:8080/rest/sign_up'
    */
    @PostMapping(value = "/rest/sign_up",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CurrentUserTO signUpUser(@RequestBody CurrentUserTO user) {
        User create = new User(user);
        checkNew(create);
        return new CurrentUserTO(userService.create(create),create.getPassword());
    }

}
