package com.netease.timemachine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:41 2018/7/19
 **/
@SpringBootApplication
@EnableScheduling
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
