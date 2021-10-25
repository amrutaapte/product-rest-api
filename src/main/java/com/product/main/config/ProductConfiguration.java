package com.product.main.config;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.product.main.service.ProductServiceImpl;

@SpringBootConfiguration
public class ProductConfiguration {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
	
	@Bean
	public ProductServiceImpl productService() {
	    return new ProductServiceImpl();
	}
	
	@Bean
	public Logger logger() {
		return LoggerFactory.getLogger(this.getClass());
	}
	
}
