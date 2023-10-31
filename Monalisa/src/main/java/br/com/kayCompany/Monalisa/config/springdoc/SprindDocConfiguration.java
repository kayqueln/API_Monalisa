package br.com.kayCompany.Monalisa.config.springdoc;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SprindDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title("Monalisa")
                        .description("API Rest da aplicação Monalisa, contendo os endpoints que representam as funcionalidades de um banco digital")
                        .contact(new Contact()
                                .name("Kayque Lima Nunes")
                                .email("kayqueln@gmail.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://monalisa/api/licenca")));
    }
}