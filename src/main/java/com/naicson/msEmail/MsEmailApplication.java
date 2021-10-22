package com.naicson.msEmail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class MsEmailApplication {
	
	@Value("{ads04.swagger.path}")
	private String swaggerPath; //Definido no application.properties

	public static void main(String[] args) {
		SpringApplication.run(MsEmailApplication.class, args);
	}
	
	@Bean
	public Docket allApi() {
		//Adding Header
		ParameterBuilder aPAramBuilder = new ParameterBuilder();
		aPAramBuilder.name("Authorization").modelRef(new ModelRef("string"))
		.parameterType("header")
		.required(false)
		.build();
		
		List<Parameter> aParam = new ArrayList<>();
		aParam.add(aPAramBuilder.build());
		
		Set<String> protocols = new HashSet<>();
		protocols.add("http");
		protocols.add("https");
		
		return new Docket(DocumentationType.SWAGGER_2).host(swaggerPath).groupName("All").apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.any())
				.build()
				.protocols(protocols)
				.ignoredParameterTypes(ApiIgnore.class)
				.enableUrlTemplating(true)
				.globalOperationParameters(aParam);
	}

	private ApiInfo apiInfo() {
		
		return new ApiInfoBuilder()
				.title("Microsservice Envio de Email")
				.description("Microsserviço para envio de email")
				.termsOfServiceUrl("http://localhost:8080")
				.license("")
				.licenseUrl("http://localhost:8080")
				.version("1.0")
				.build();
				
	}
}
