package com.task;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfiguration {

	public class springShopOpenAPI{

	    @Bean
	    public OpenAPI springShopOpenAPI() {
	           return new OpenAPI()
	            .info(new Info().title("TaskManager API")
	            .description("Spring shop sample application")
	            .version("v0.0.1")
	            .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	            .externalDocs(new ExternalDocumentation()
	            .description("SpringShop Wiki Documentation")
	            .url("https://test.org/docs"));
	    }
	}
	
	@Bean
	public GroupedOpenApi apiGroupA() {
	    return GroupedOpenApi.builder().group("API A").pathsToMatch("/**") 
	           .packagesToScan("com.task.controller").build();
	}	
	
	
}
