package com.netease.timemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:41 2018/7/19
 **/
@SpringBootApplication(scanBasePackages = {"com.netease.timemachine"},exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
@EnableCaching
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
