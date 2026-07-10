package com.liwu.aillm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.liwu.aillm.mapper")
public class AiLlmApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiLlmApplication.class, args);
    }

}
