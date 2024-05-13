package com.casablanca.SpringConnect.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.node.ObjectNode;

@Configuration
public class ObjectMapperConfiguration {
	@Bean
	ObjectNode objectNodeBean() {
		return new ObjectNode(null);
	}
}
