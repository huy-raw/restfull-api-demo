package com.huyraw.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI  customOpenApi() {
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .addServersItem(
                        new Server().url("http://localhost:9000")
                )
                // info
                .info(new Info().title("Book manager REST API")
                        .description("Sample OpenAPI 3.0")
                        .contact(new Contact()
                                .email("ruacon130@gmail.com")
                                .name("Huy Trieu"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));

    }

}
