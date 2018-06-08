package com.ahuang.bookCornerServer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class AppConfig {
	@Bean
	  public ObjectMapper ObjectMapper(){
	   ObjectMapper objectMapper=new ObjectMapper();
	   return objectMapper;
	  }
}
