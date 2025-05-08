package com.pfms.gateway_server;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "PFMS Gateway Server",
                version = "0.0.1",
                description = "PFMS Gateway Server",
                contact = @Contact(
                        name = "PFMS",
                        email = "CkZ4v@example.com",
                        url = "https://pfms.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "PFMS Documentation",
                url = "https://pfms.com"
        )


)
public class GatewayServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);

    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
