package com.barber;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author will
 */
@SpringBootApplication()
@MapperScan("com.barber.mapper")
public class HaircutApplication {
    public static void main(String[] args) {
        SpringApplication.run(HaircutApplication.class,args);
    }
}
