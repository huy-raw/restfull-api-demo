package com.huyraw.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
//@OpenAPIDefinition(info = @Info(title = "Book managerment API", version = "1.0", description = "Book Information"))
public class RestfulApiSpringApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(RestfulApiSpringApplication.class, args);
	}

}
