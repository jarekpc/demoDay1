package com.example.demoDay1.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@ConfigurationProperties("application")
@Validated
public class ApplicationProperties {

    private String uploaddir;

    @NonNull
    public String getUploaddir() {
        return uploaddir;
    }

    public void setUploaddir(String uploaddir) {
        this.uploaddir = uploaddir;
    }
}
