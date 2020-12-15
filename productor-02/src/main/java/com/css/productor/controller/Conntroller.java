package com.css.productor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productor")
public class Conntroller {

    private final Logger logger = LoggerFactory.getLogger(Conntroller.class);

    @RequestMapping("/hello")
    public String hello() {
        logger.info("productor hello");
        return "Productor - hell0 -02";
    }
}
