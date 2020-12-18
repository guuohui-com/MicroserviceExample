package com.css.consumer.controller;


import brave.Tracer;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/consumer")
@RefreshScope
public class Conntroller {

    private final Logger logger = LoggerFactory.getLogger(Conntroller.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ProductorFeign productorFeign;
    @Value("${env}")
    private String env;
    @Autowired
    private Tracer tracer;

    @RequestMapping("/hello")
    public String hello() {
        String rest = restTemplate.getForObject("http://productor-service/api/productor/hello", String.class);
        System.out.println(rest);
        return rest + "Conseumer - hell0 - ribbon";
    }

    @RequestMapping("/helloFeignError")
    public String helloFeignError() {
        logger.info("consumer helloFeignError");
        String result = productorFeign.findFindProduct();
        return result + "+Conseumer - hell0 - helloFeign";
    }

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/helloFeign")
    public String helloFeign(HttpServletRequest request) {
        String result = productorFeign.findFindProduct();
        logger.info("consumer helloFeign");
        String ip = request.getRemoteAddr();
        int port = request.getRemotePort();
        logger.info("env==========================================="+env);
        logger.info("====tranceId====="+tracer.currentSpan().context().traceIdString());
        logger.info("====spanId====="+tracer.currentSpan().context().spanIdString());
        logger.info("====parentId====="+tracer.currentSpan().context().parentIdString());
        return result + "+Conseumer-helloFeign"+port+"env: "+ env;
    }

    public String fallback(HttpServletRequest request) {
        new Thread(() -> {
            System.out.println("Conseumer-error");
        }).start();
        return "Conseumer-error";
    }
}
