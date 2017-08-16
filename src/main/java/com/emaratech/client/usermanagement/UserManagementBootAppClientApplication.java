package com.emaratech.client.usermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class UserManagementBootAppClientApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(UserManagementBootAppClientApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(UserManagementBootAppClientApplication.class, args);
	}
}
