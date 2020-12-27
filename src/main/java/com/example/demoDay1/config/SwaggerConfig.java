package com.example.demoDay1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    private static final String SWAGGER_API_VERSION = "1.0";
    private static final String LICENCE_TEXT = "Licence";
    private static final String title = "EBookStore REST API";
    private static final String description = "Restful API for EbookStore";

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .version(SWAGGER_API_VERSION)
                .description(description)
                .license(LICENCE_TEXT)
                .build();
    }


    @Bean
    public Docket eboostoreDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
