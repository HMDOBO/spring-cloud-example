package com.springcloud.provider;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientProviderApplication {

    @Resource
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProviderApplication.class, args);
    }

    @GetMapping("/dc")
    public String dc() {
        return String.format("Hello from '%s'!", eurekaClient.getApplication(appName).getName());
    }

}
