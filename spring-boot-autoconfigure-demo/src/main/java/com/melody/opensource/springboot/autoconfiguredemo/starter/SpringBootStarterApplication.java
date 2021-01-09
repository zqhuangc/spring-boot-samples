package com.melody.opensource.springboot.autoconfiguredemo.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;

public class SpringBootStarterApplication {

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootConfiguration.class);
		springApplication.setWebApplicationType(WebApplicationType.SERVLET);
		springApplication.run(args);
	}
}
