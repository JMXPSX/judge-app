package com.judge.dredd.conf;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig { 
	
	@Value(value="${application.name}")
	private String applicationName;
	

	@Value(value="${build.version}")
	private String applicationVersion;
	

	@Value(value="${build.timestamp}")
	private String timestamp;
	
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())                          
          .build()   
          .apiInfo(metaData());                                          
    }

    private ApiInfo metaData() {
	  	
        String title = "Dredd App API";
		String description = "Dredd REST API documentation for mobile and admin panel";
		String version = applicationName + "-" + applicationVersion + "-" + timestamp;
		String termsOfServiceUrl = "Terms of Service";
		Contact contact = new Contact("Accenture Admin", "www.accenture.com", "support@accenture.com");
		String license = "";
		String licenseUrl = "";
		
		ApiInfo apiInfo = new ApiInfo(title, description, version, termsOfServiceUrl, contact, license, licenseUrl, new ArrayList<VendorExtension>());
		
        return apiInfo;
    }
}