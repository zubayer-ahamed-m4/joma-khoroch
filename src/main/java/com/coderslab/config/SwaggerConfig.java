package com.coderslab.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.coderslab.restcontroller"))
				.build()
				.apiInfo(metaData());
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder()
					.title("Joma Khoroch Rest API")
					.description("A rest API for personal accounting software")
					.version("1.0.0")
					.termsOfServiceUrl("Terms of service url")
					.contact(new Contact("Zubayer Ahamed", "www.techteamsbd.com", "zubayer.ahamed@metafour.com"))
					.license("License")
					.licenseUrl("License URL")
					.build();
	}
}
