package com.github.PedroHenrique_LS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;


@Configuration
public class ObjectMapperConfig {
	
	@Bean
	public ObjectMapper objectMapper() {
		
		ObjectMapper mapper = new ObjectMapper();
		SimpleFilterProvider filter = new SimpleFilterProvider()
				.addFilter("PersonFilter", SimpleBeanPropertyFilter.serializeAllExcept("sensitiveData"));
		mapper.setFilterProvider(filter);
		
		return mapper;
		
	}
	

}
