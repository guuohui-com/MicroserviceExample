package com.css.consumer.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "productor-service", fallback = ProductorClientFallback.class)
public interface ProductorFeign {

    @RequestMapping("/api/productor/hello")
    public String findFindProduct();
}
