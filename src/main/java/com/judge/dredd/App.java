package com.judge.dredd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App extends SpringBootServletInitializer implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

	@Value(value="${application.name}")
	private String applicationName;
	
	@Value(value="${build.version}")
	private String applicationVersion;
	
	@Value(value="${build.timestamp}")
	private String timestamp;
		
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

	private static Class<App> applicationClass = App.class;

	
	public static void main(String[] args) {
		SpringApplication.run(applicationClass, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		LOGGER.info("========================================");
		LOGGER.info("=  Name:   " + applicationName + "\t           =");
		LOGGER.info("=  Vesion: " + applicationVersion + "\t           =");
		LOGGER.info("=  Date:   " + timestamp + "            =");
		LOGGER.info("========================================");
		LOGGER.info("Judge Dredd System Successfully Started!");
		LOGGER.info("========================================");

	}

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
