package com.sara.backend;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Sara Backend API")
                                .version("1.0")
                                .description("Sara Backend API")

                )
                .addSecurityItem(
                        new SecurityRequirement()
                                .addList("bearerAuth")
                )
                .components(
                        new io.swagger.v3.oas.models.Components()

                                .addSecuritySchemes(

                                        "bearerAuth",

                                        new SecurityScheme()

                                                .name("bearerAuth")

                                                .type(SecurityScheme.Type.HTTP)

                                                .scheme("bearer")

                                                .bearerFormat("JWT")

                                )
                );
    }
}
