package com.glappy.login.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class JavaMailConfig {
	@Bean
	public static JavaMailSender mailSender() {
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		String host="smtp.naver.com";
		final String username="NAVER ID";
		final String password="NAVER PASS";
		int port=465;
		
		mailSender.setHost(host);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		mailSender.setPort(465);
		mailSender.setDefaultEncoding("UTF-8");
		mailSender.setProtocol("smtp");
		
		Properties props=System.getProperties();
		
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);
		
		mailSender.setJavaMailProperties(props);
		return mailSender();
	}	
}