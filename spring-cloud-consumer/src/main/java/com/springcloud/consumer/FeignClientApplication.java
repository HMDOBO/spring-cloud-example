package com.springcloud.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class FeignClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignClientApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    LoadBalancerClient loadBalancerClient;  // 负载均衡client

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/dc")
    public String dc() {
        // 负载均衡的choose函数选出spring-cloud-eureka-client的服务实例，该实例基本信息存储在ServiceInstance中
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-cloud-eureka-client");
        // 获取spring-cloud-eureka-client服务实例的主机名和端口，并拼接出服务调用的详细地址
        String url = "http://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + "/dc";
        System.out.println(url);
        // 通过RestTemplate实现对服务提供者接口的调用
        return restTemplate.getForObject(url, String.class);
    }

}
