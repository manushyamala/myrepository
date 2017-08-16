package com.emaratech.client.usermanagement;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@PropertySources({ @PropertySource("classpath:service_config.properties"),
		@PropertySource("classpath:userservice_config.properties") })
@Configuration
@ComponentScan("com.emaratech.security")
public class ApplicationConfig {

	@Autowired
	Environment env;

	// @Bean
	public FilterRegistrationBean registerFilterRequestBean() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(userManagementRequestFilter());
		registration.addUrlPatterns("/*");
		registration.setName("userManagementRequestFilter");
		registration.setOrder(1);
		return registration;
	}

	// @Bean(name = "userManagementRequestFilter")
	public Filter userManagementRequestFilter() {
		return new UserManagementRequestFilter();
	}

	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfig() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("locale/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

}
