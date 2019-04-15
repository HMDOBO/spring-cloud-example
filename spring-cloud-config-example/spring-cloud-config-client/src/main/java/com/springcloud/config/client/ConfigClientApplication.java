package com.springcloud.config.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableDiscoveryClient
public class ConfigClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    @Value("${info.from}")
    String info;
    @Value("${mysql.url}")
    String url;
    @Value("${mysql.username}")
    String username;
    @Value("${mysql.password}")
    String password;

    @RequestMapping(value = "/info")
    public String info() {
        return info;
    }

    @GetMapping(value = "/mysqlinfo")
    public String mysqlinfo() {
        System.out.println("mysql.url=" + url);
        System.out.println("mysql.username=" + username);
        System.out.println("mysql.password=" + password);
        return "mysql.url=" + url + ", mysql.username=" + username + ", mysql.password=" + password;
    }

}
