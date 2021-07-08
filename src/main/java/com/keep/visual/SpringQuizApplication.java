package com.keep.visual;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
public class SpringQuizApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringQuizApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringQuizApplication.class, args);

		LOGGER.info("INFO MESSAGE EXAMPLE");
		LOGGER.debug("DEBUG MESSAGE EXAMPLE");
		LOGGER.warn("WARN MESSAGE EXAMPLE");
		LOGGER.error("ERROR MESSAGE EXAMPLE");
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
	   return new WebMvcConfigurerAdapter() {
		  @Override
		  public void addCorsMappings(CorsRegistry registry) {
			 // set to all in DEV, specify allowed url in PROD 
			 // registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			 registry.addMapping("/**").allowedOrigins("*");
		  }
	   };
	}//corsConfigurer

}




