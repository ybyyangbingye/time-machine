package com.netease.timemachine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * @author: wqh
 * @description:
 * @Date: Created in 21:41 2018/7/19
 **/
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class App {

    public static void main(String [] args) {
        SpringApplication.run(App.class, args);
    }

}
