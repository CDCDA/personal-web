package com.pw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.pw.**.mapper"})
@SpringBootApplication
public class PWApplication {

    public static void main(String[] args) {
        SpringApplication.run(PWApplication.class, args);
    }

}
