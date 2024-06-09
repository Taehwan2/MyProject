package com.example.service.prag.home;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HomeController {

    @GetMapping("/hello")
    public String hello(){
        log.info("hello world");
        return "hello bro";
    }

}