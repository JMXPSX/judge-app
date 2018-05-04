package com.judge.dredd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Value(value="${application.name}")
	private String applicationName;
	
	@Value(value="${build.version}")
	private String applicationVersion;
	
	@Value(value="${build.timestamp}")
	private String timestamp;
		
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("========================================");
		LOGGER.info("=  Name:   " + applicationName + "\t \t           =");
		LOGGER.info("=  Vesion: " + applicationVersion + "\t            =");
		LOGGER.info("=  Date:   " + timestamp + "            =");
		LOGGER.info("========================================");
		LOGGER.info("Judge Dredd System Successfully Started!");
		LOGGER.info("========================================");

	}

}
