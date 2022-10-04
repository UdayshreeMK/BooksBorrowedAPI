package com.onpier.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author Udayshree
 * 
 *         Configuration for Swagger , it generates API Doc by browser UI
 *         http://localhost:9090/swagger-ui/index.html#/
 *
 */
@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

	private static final String BASE_PACKAGE = "com.onpier.task";
	private static final String LICENSE = "Apache 2.0";
	private static final String VERSION = "1.0.0";
	private static final String HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML = "http://www.apache.org/licenses/LICENSE-2.0.html";
	private static final String DEV_TEAM = "Dev-Team";
	private static final String HTTPS_WWW_ONPIER_DE = "https://www.onpier.de/";
	private static final String DEV_TEAM_ONPIER_COM = "dev-team@onpier.com";
	private static final String ONPIER_BOOKS_LIBRARY_AP_IS = "Onpier BooksLibrary APIs";
	private static final String API_ENDPOINT_FOR_BOOKS_LIBRARY_PROJECT = "API Endpoint for Books Library Project";

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage(
						BASE_PACKAGE)).paths(PathSelectors.regex("/.*")).build()
				.apiInfo(apiInfoMetaData());
	}

	private ApiInfo apiInfoMetaData() {

		return new ApiInfoBuilder().title(ONPIER_BOOKS_LIBRARY_AP_IS)
				.description(API_ENDPOINT_FOR_BOOKS_LIBRARY_PROJECT)
				.contact(new Contact(DEV_TEAM, HTTPS_WWW_ONPIER_DE, DEV_TEAM_ONPIER_COM)).license(LICENSE)
				.licenseUrl(HTTP_WWW_APACHE_ORG_LICENSES_LICENSE_2_0_HTML).version(VERSION).build();
	}

}