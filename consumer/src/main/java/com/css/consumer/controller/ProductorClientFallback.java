package com.css.consumer.controller;

import org.springframework.stereotype.Component;

/*
 * Feign降级处理
 * */
@Component
public class ProductorClientFallback implements ProductorFeign {

    @Override
    public String findFindProduct() {
        System.out.println("ProductorFeign findFindProduct 降级处理");
        return null;
    }
}
