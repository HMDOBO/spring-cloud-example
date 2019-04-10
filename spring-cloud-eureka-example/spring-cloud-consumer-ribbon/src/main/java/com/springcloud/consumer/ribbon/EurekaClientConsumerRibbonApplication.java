package com.springcloud.consumer.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class EurekaClientConsumerRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerRibbonApplication.class, args);
    }

    @Bean
    @LoadBalanced   // Annotation to mark a RestTemplate bean to be configured to use a LoadBalancerClient
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("dc")
    public String dc() {
        String url = "http://spring-cloud-eureka-client-provider/dc";
        // use a LoadBalancerClient configured RestTemplate Bean
        // spring cloud ribbon 拦截器会针对路径中的服务名称选择对应的服务提供者
        String dc = restTemplate.getForObject(url, String.class);
        System.out.println(dc);
        return dc;
    }

}
