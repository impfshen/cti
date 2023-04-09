package com.shen.springbootwechat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootWechatApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWechatApplication.class, args);
    }

}
