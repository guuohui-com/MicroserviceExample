package com.css.productor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productor")
public class Conntroller {

    @RequestMapping("/hello")
    public String hello() {
        return "Productor - hell0 -01";
    }
}
