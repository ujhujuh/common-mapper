package com.common;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan("com.common.mapper")
public class CommonMapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonMapperApplication.class, args);
	}
}
