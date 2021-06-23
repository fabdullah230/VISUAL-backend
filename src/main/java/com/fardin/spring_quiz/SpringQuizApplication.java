package com.fardin.spring_quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpringBootApplication
public class SpringQuizApplication {

	private static final Logger LOGGER = LogManager.getLogger(SpringQuizApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringQuizApplication.class, args);

		LOGGER.info("INFO MESSAGE EXAMPLE");
		LOGGER.debug("DEBUG MESSAGE EXAMPLE");
		LOGGER.warn("WARN MESSAGE EXAMPLE");
		LOGGER.error("ERROR MESSAGE EXAMPLE");
	}

}




