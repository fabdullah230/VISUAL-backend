package com.fardin.spring_quiz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.apache.logging.log4j.LogManager;

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

}




