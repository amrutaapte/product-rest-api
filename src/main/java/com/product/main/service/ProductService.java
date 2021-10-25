package com.product.main.service;


import com.product.main.model.Product;
import com.product.main.model.ProductDTO;

@FunctionalInterface
public interface ProductService {
	//Method to convert Product to ProductDTO
	public ProductDTO convertToDto(Product product);
}
