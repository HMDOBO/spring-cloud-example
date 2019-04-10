package com.springcloud.consumer.feign;

import com.springcloud.consumer.feign.service.DcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients // 开启扫描spring cloud feign客户端
@RestController
public class EurekaClientConsumerFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientConsumerFeignApplication.class, args);
    }

    @Autowired
    private DcClient dcClient;  // 注入 spring cloud feign 客户端，而DcClient绑定了具体的服务提供者REST接口

    @GetMapping("/dc")
    public String dc() {
        String dc = dcClient.consumer();
        System.out.println(dc);
        return dc;
    }

}
