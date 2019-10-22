package ru.zagshak.buySupply.controller.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/rest")
public class HelloRestController {


    @GetMapping()
    public String hello(){
        return "{}";
    }
}
