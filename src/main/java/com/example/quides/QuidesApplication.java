package com.example.quides;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import java.security.spec.EdDSAParameterSpec;

@SpringBootApplication
public class QuidesApplication {

	private static final Logger loggger = (Logger) LoggerFactory.getLogger(QuidesApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(QuidesApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate (RestTemplateBuilder builder ){
		return builder.build();
	}

	@Bean
	@Profile("!test")
	public CommandLineRunner run(RestTemplate restTemplate)  throws Exception{
		return args -> {
			Quotes quotes = restTemplate.getForObject("http://localhost:8080/api/random",Quotes.class);
			loggger.info(quotes.toString());
		};
	}
}

