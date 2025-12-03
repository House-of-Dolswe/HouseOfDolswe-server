package com.houseofdolswe.HouserOfDolswe_server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI CotatoAPI() {
		Info info = new Info()
			.title("House Of Dolswe API")
			.description("돌쇠의 집 API 명세서")
			.version("1.0.0");


		return new OpenAPI()
			.addServersItem(new Server().url("/"))
			.info(info);
	}
}