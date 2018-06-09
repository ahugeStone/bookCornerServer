package com.ahuang.bookCornerServer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication(scanBasePackages="com.ahuang.bookCornerServer")
@MapperScan("com.ahuang.bookCornerServer.mapper")
public class BookCornerServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookCornerServerApplication.class, args);
	}
}
