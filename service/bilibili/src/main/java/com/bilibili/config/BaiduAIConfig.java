package com.bilibili.config;

import com.baidu.aip.imageclassify.AipImageClassify;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class BaiduAIConfig {
    @Value("${baidu.app-id}")
    private   String APP_ID;
    @Value("${baidu.api-key}")
    private   String API_KEY ;
    @Value("${baidu.secret-key}")
    private   String SECRET_KEY ;


    @Bean
    public AipImageClassify aipImageClassify() {
        if (APP_ID == null || API_KEY == null || SECRET_KEY == null) {
            throw new IllegalStateException("Baidu AI credentials are not properly configured. Check application properties.");
        }
        AipImageClassify client = new AipImageClassify(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        return client;
    }
}
