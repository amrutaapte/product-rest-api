package com.product.main.controller;


import java.util.Set;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.main.model.ProductDTO;
import com.product.main.model.ProductRepository;
import com.product.main.model.Tag;
import com.product.main.model.TagRepository;
import com.product.main.model.Product;
import com.product.main.service.ProductServiceImpl;

@RestController
@RequestMapping("/v1")
public class ProductController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	TagRepository tagRepository;

	@Autowired 
	ModelMapper modelMapper;
	
	@Autowired
	ProductServiceImpl productService;
	

	
	@PostMapping(path = "/products", consumes="application/json")
	public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product) {
		
		//Setup to save unique tags to Tag table
		Set<Tag> newTagList = productService.getNewTags(product);
		product.setTags(newTagList);
	
		productRepository.save(product);
		
		logger.info("Product saved :" + product.toString());
		
		//Convert Product object to ProductDTO
		ProductDTO productDto = productService.convertToDto(product);

		HttpHeaders header = new HttpHeaders();
		header.add("Message-from-API", "Adding new product");

		return ResponseEntity.status(HttpStatus.OK).headers(header).body(productDto);
	}
	
	
	
	@GetMapping(path = "/products/{category}", produces="application/json")
	public ResponseEntity<Page<ProductDTO>> findProductByCategory(@RequestParam(defaultValue = "0") Integer pageNo, 
			@RequestParam(defaultValue = "5") Integer pageSize, @PathVariable("category") String category) {

		//Setup pagination
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		Page<Product> products = productRepository.findAllByCategory(paging, category);
		
		//Map Product to ProductDTO
		Page<ProductDTO> productsDto =  productService.convertToDtoPage(products);
		
		HttpHeaders header = new HttpHeaders();
		header.add("Message-from-API", "Getting products, page " + pageNo);
		
		return ResponseEntity.status(HttpStatus.OK).headers(header).body(productsDto);
		
	}
	

}
