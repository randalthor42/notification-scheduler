package dev.andreisima.notification_scheduler.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Notification Scheduler API",
                version = "1.0",
                description = "Microservice to notify and schedule users"
        )
)
public class OpenApiConfig {}
