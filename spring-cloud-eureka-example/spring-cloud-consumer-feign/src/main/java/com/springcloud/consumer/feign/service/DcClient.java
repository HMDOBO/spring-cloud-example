package com.springcloud.consumer.feign.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 创建一个Feign的客户端接口定义
 */
@FeignClient("spring-cloud-eureka-client-provider") // 指定这个接口需要调用的服务名称
public interface DcClient {

    /**
     * 这里其实就相当于RestTemplate的
     * restTemplate.getForObject("http://spring-cloud-eureka-client-provider/dc", String.class);
     */
    @GetMapping("/dc") // 绑定服务提供方的REST接口
    String consumer();

}
