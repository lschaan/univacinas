package com.univacinas.config;

import com.univacinas.common.MockController;
import io.swagger.v3.oas.models.Operation;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.util.Optional;

@Configuration
public class SwaggerConfig {

    @Bean
    public OperationCustomizer mockOperationCustomizer() {
        return (Operation operation, HandlerMethod handler) -> {
            if (handler.getBeanType().isAnnotationPresent(MockController.class)) {
                String orig = Optional.ofNullable(operation.getSummary()).orElse("");
                operation.setSummary("[MOCK] " + orig);
            }
            return operation;
        };
    }
}
