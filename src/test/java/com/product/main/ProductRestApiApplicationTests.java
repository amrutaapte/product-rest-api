package com.product.main;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import com.product.main.controller.ProductController;
import com.product.main.model.Product;
import com.product.main.model.ProductRepository;
import com.product.main.model.Tag;

@SpringBootTest
class ProductRestApiApplicationTests {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	private ProductController controller;

	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	

	//Create new Product
	@Test
	public void testAddProduct() {
		
		Product testProduct = new Product("Test Name", "Test Description", "Test Brand",  "Test Category");
		
		Set<Tag> testTags = new HashSet<>();
		
		Tag tag1 = new Tag("Test Tag 1");
		testTags.add(tag1);
		
		Tag tag2 = new Tag("Test Tag 2");
		testTags.add(tag2);
 		
		testProduct.setTags(testTags);
		
		productRepository.save(testProduct);
		
		System.out.println("Test product id " + testProduct.getId() );
		
		assertNotNull(productRepository.findById(testProduct.getId()));

	}
	
	//Find Product by Category
	@Test
	public void testFindProductByCategory() {
		
		Product testProduct = new Product("Test Name", "Test Description", "Test Brand",  "Test Category");
		Set<Tag> testTags = new HashSet<>();
		
		Tag tag1 = new Tag("Test Tag 1");
		testTags.add(tag1);
		
		Tag tag2 = new Tag("Test Tag 2");
		testTags.add(tag2);
 		
		testProduct.setTags(testTags);
		
		productRepository.save(testProduct);
		
		System.out.println("Test product id " + testProduct.getId() );
		
		Page<Product> productList = productRepository.findAllByCategory(null, testProduct.getCategory());
		
		assertThat(productList).size().isGreaterThan(0);
	}
	
	//Logger
	@Test
	public void testLoggerWithLogLevels()
	  throws Exception {
	    org.apache.logging.log4j.Logger logger = LogManager.getLogger(getClass());
	    Exception e = new RuntimeException("This is only a test!");

	    logger.info("This is a simple message at INFO level. " +
	      "It will be hidden.");
	    logger.error("This is a simple message at ERROR level. " +
	    "This is the minimum visible level.", e);
	}
	

}
