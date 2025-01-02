package com.animore.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "API Definition",
		version = "1.0",
		description = "This is the API documentation with JWT Bearer token support."
	),
	security = @SecurityRequirement(name = "Bearer Authentication")
)
@SecurityScheme(
	name = "Bearer Authentication", // Swagger에서 사용할 인증 스키마 이름
	type = SecuritySchemeType.HTTP,
	scheme = "bearer",
	bearerFormat = "JWT" // JWT 형식을 지정
)
public class OpenApiConfig {
}
