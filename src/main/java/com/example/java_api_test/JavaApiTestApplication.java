package com.example.java_api_test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.java_api_test")
public class JavaApiTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaApiTestApplication.class, args);
    }

}
