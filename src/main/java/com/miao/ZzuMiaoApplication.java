package com.miao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.miao.mapper")
public class ZzuMiaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZzuMiaoApplication.class, args);
    }

}
