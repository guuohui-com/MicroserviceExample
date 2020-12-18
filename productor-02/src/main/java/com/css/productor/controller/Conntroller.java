package com.css.productor.controller;

import brave.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/productor")
public class Conntroller {

    private final Logger logger = LoggerFactory.getLogger(Conntroller.class);
    @Autowired
    private Tracer tracer;

    @RequestMapping("/hello")
    public String hello() {
        logger.info("productor hello");
        logger.info("====tranceId====="+tracer.currentSpan().context().traceIdString());
        logger.info("====spanId====="+tracer.currentSpan().context().spanIdString());
        logger.info("====parentId====="+tracer.currentSpan().context().parentIdString());
        return "Productor - hell0 -02";
    }
}
