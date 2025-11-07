package com.foliageh.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic",
        in = SecuritySchemeIn.HEADER
)
@OpenAPIDefinition(
        info = @Info(
                title = "SHREW - Система управления жилой недвижимостью",
                version = "v1.0.0",
                description = """
            ## Система управления коллекцией квартир и операций с недвижимостью
            
            ### Основные возможности:
            - Управление коллекцией объектов недвижимости (CRUD операции)
            - Фильтрация, сортировка и пагинация объектов
            - Специальные операции с объектами коллекции
            - Дополнительные операции через агентство недвижимости
            
            ### Архитектура:
            - RESTful API
            - Базовая аутентификация
            - Ответы в формате JSON
            """
        ),
        security = @SecurityRequirement(name = "basicAuth")
)
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("public")
                .pathsToMatch("/api/**")
                .build();
    }
}
