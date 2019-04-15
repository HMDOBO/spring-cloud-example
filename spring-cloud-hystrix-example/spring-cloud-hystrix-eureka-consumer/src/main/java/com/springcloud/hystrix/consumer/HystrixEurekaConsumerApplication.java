package com.springcloud.hystrix.consumer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
@EnableHystrix  // 开启hystrix服务短路保护功能
@RestController
public class HystrixEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixEurekaConsumerApplication.class, args);
    }

}
