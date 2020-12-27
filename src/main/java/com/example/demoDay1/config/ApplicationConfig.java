package com.example.demoDay1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;

@Configuration
//@EnableConfigurationProperties({ApplicationProperties.class})
public class ApplicationConfig {

    private final ApplicationProperties applicationProperties;

    public ApplicationConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        try {
            System.out.println("path " + applicationProperties.getUploaddir());
            resolver.setUploadTempDir(new FileSystemResource(applicationProperties.getUploaddir()));
        }catch (IOException e){
            e.printStackTrace();
        }
        return resolver;
    }
}
