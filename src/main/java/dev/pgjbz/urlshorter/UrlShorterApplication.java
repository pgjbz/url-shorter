package dev.pgjbz.urlshorter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import dev.pgjbz.urlshorter.infra.configuration.HashidsConfig;

@SpringBootApplication
@ConfigurationPropertiesScan(basePackageClasses = {HashidsConfig.class})
public class UrlShorterApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlShorterApplication.class, args);
	}

}
