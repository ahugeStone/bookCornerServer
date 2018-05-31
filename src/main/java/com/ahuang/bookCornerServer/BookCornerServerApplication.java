package com.ahuang.bookCornerServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ahuang.bookCornerServer.mapper")
public class BookCornerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCornerServerApplication.class, args);
	}
}
