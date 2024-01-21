package br.com.rem_aya_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

	@Bean
	OpenAPI customOpenApi() {
		return new OpenAPI()
			.info(new Info()
				.title("Rem Aya 2")
				.version("v1")
				.description("RESTful API")
				.termsOfService("https://github.com/do5-5anto5/rem-aya-2/blob/main/README.md")
				.license(
					new License()
					.name("Apache 2.0")
					.url("https://github.com/do5-5anto5/")
					)
				);
	}
}
