package com.projects.Invisible;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvisibleApplication {

	public static void main(String[] args) {
		Sentry.capture("Application started");
		SpringApplication.run(InvisibleApplication.class, args);
	}

}
