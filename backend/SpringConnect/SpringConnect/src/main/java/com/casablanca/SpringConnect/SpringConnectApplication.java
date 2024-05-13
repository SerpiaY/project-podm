package com.casablanca.SpringConnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


 
@SpringBootApplication
public class SpringConnectApplication{
    public static void main(String[] args) {
        SpringApplication.run(SpringConnectApplication.class, args);       
    }
    @Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/signup").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/view_all").allowedOrigins("*").allowedMethods("*");
				registry.addMapping("/login")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/viewall")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/create")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/editrent")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/viewallusers")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/create-car")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/view-vehicles")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/view-avail1")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/view-avail2")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/view-avail3")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/deletebyID")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/view-payment")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/delete-all-rent")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/deleteuserID")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
				registry.addMapping("/deleteallusers")
				.allowedOrigins("*")
				.allowedMethods("*")
				.allowedHeaders("*")
				.exposedHeaders("*")
				.maxAge(10);
			}
		};
	}
}