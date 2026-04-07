package com.test.student.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    // == NEW CHANGE: Defines the "v1" group for the Swagger UI dropdown ==
    @Bean
    public GroupedOpenApi publicApiV1() {
        return GroupedOpenApi.builder()
                .group("v1-students") // The name that appears in the dropdown
                .pathsToMatch("/api/v1/**") // Only show endpoints starting with /api/v1
                .build();
    }
}
