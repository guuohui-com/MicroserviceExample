package com.css.consumer.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/consumer")
public class Conntroller {

    private final Logger logger = LoggerFactory.getLogger(Conntroller.class);
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private ProductorFeign productorFeign;

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
        return result + "+Conseumer - hell0 - helloFeign";
    }

    public String fallback(HttpServletRequest request) {
        new Thread(() -> {
            System.out.println("Conseumer-error");
        }).start();
        return "Conseumer-error";
    }
}
