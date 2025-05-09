package com.bilibili;

import cn.dev33.satoken.SaManager;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableDiscoveryClient
//@EnableFeignClients
@MapperScan("com.bilibili.mapper")
@ComponentScan(basePackages = {"*.bilibili.*"})
public class BilibiliApplication {

    public static void main(String[] args) {

        SpringApplication.run(BilibiliApplication.class, args);
        System.out.println("启动成功，Sa-Token 配置如下：" + SaManager.getConfig());


    }
}
