package com.springcloud.hystrix.consumer;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableHystrix  // 开启hystrix服务短路保护功能
//@EnableCircuitBreaker // 注意@EnableCircuitBreaker和@EnableHystrix二选一
@EnableDiscoveryClient
@RestController
//@SpringCloudApplication   // @SpringCloudApplication等于@SpringBootApplication + @EnableCircuitBreaker + @EnableDiscoveryClient
public class HystrixEurekaConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixEurekaConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced   // 负载均衡
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Autowired
    ConsumerService consumerService;

    @GetMapping("/consumer")
    public String dc() {
        return consumerService.consumer();
    }

    @Service
    class ConsumerService {

        @Autowired
        RestTemplate restTemplate;  // 调用服务

        /**
         * 服务降级：当消费者调用服务在一段时间没有响应，那么就主动切断对服务的调用，返回特定的调用失败结果，避免当服务提供者宕机时造成的大量请求堆积当前应用中
         * <p>
         * 大量请求堆积在当前应用中会造成当前应用服务器压力骤增，轻则造成当前应用宕机，重则影响整个系统
         * <p>
         * 这样的机制，对自身服务起到了基础的保护，同时还为异常情况提供了自动的服务降级切换机制
         *
         * @HystrixCommand 注解同时包括服务降级和依赖隔离两种功能
         */
        @HystrixCommand(fallbackMethod = "fallback")    // 服务降级注解，定义服务降级逻辑，fallbackMethod指定降级方法
        public String consumer() {
            return restTemplate.getForObject("http://spring-cloud-eureka-client-provider/dc", String.class);
        }

        public String fallback() {
            return "fallback";
        }

    }

}
