package com.css.productor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@EnableEurekaClient
public class ProductorApplication01 {

    public static void main(String[] args) {
        SpringApplication.run(ProductorApplication01.class, args);
    }


}
