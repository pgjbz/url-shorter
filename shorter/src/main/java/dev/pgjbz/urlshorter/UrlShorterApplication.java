package dev.pgjbz.urlshorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition(info = @Info(contact = @Contact(email = "${spring.application.contact}", 
name = "${spring.application.author}"), 
version = "${spring.application.version}", 
title = "${spring.application.name}"))
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}

}
