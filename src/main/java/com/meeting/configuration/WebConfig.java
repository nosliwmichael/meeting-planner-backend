package com.meeting.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.meeting.authentication.JwtInterceptor;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages= "com.meeting")
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(new JwtInterceptor())
			.excludePathPatterns("/login")
			.excludePathPatterns("/register");
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
			.addMapping("/**")
			.allowedOrigins("http://localhost:4200")
			.allowedMethods("*");
	} 
	
}
