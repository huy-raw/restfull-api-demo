package com.huyraw.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Book managerment API", version = "1.0", description = "Book Information"))
public class RestfulApiSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulApiSpringApplication.class, args);
	}

}
