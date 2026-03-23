package se.jensen.miljana.bokasmart.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for OpenAPI (Swagger).
 * Defines API documentation settings and JWT security scheme
 * for authorization in Swagger UI.
 */

@Configuration
@OpenAPIDefinition(
        info = @Info(title = "My API", version = "1.0")
)
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT"
)
public class OpenApiConfig {
}
