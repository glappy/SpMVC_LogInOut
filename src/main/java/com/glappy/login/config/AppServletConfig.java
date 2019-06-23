package com.glappy.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.glappy.login.controller", "com.glappy.login.service" })
@Slf4j
public class AppServletConfig implements WebMvcConfigurer {
	@Bean
	LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	@override
	public void addInterceptors(InterceptorRegistry registry) {
		
	}
}