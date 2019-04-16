package com.springcloud.provider;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
    @Resource
    private DiscoveryClient discoveryClient;

    @Value("${spring.application.name}")
    private String appName;

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientProviderApplication.class, args);
    }

    @GetMapping("/dc")
    public String dc() throws Exception {
        Thread.sleep(5000L);  // 测试服务降级代码
        String services = "Services: " + discoveryClient.getServices();
        System.out.println(services);
        return services;
    }

}
