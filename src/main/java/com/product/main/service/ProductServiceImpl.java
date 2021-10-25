package com.product.main.service;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.product.main.model.Product;
import com.product.main.model.ProductDTO;
import com.product.main.model.Tag;
import com.product.main.model.TagRepository;

public class ProductServiceImpl implements ProductService{
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	TagRepository tagRepository;
	
	//This method converts Product to ProductDTO 
	@Override
	public ProductDTO convertToDto(Product product) {
		
			
		ProductDTO productDto = new ProductDTO(product.getId(), product.getName(), product.getDescription(), 
				product.getBrand(), product.getCategory(), product.getCreatedAt());

		logger.debug("After converting to ProductDTO: ID : " + productDto.getId());
		
		productDto.setTags(productDto.getStringOfTags(product.getTags()));
		
	    return productDto;
	}
	
	
	//This method uses this.convertToDto to map page of Product to page of ProductDTO
	public Page<ProductDTO> convertToDtoPage(Page<Product> products){
		
		Page<ProductDTO> productsDto =  products.map(obj -> convertToDto(obj));
		
		return productsDto;
		
	}


	//This method gets tags from Product, checks against Tag table for existence, 
	//then creates a new tag or retrieves existing tag and assigns a newly 
	//generated list of tags to the product object.
	//This method makes sure that only unique tags are saved in the database for normalization 
	public Set<Tag> getNewTags(Product product) {
		
		Set<Tag> tagsFromReq = product.getTags();

		Set<Tag> newTagList = new HashSet<>();
		
		for(Tag tagFromReq : tagsFromReq) { 

			Tag newTag = new Tag();
			
			try {
				//If tag exists in Tag table, retrieve and assign to Product
				if(tagRepository.existsByName(tagFromReq.getName())) {
					logger.debug(tagFromReq.getName() + " exists. Retrieveing now..");
				    newTag = tagRepository.findByName(tagFromReq.getName()); 
				    logger.debug("Tag Id : " + newTag.getTagId());
			
				}//Else, create a new Tag entry and assign the new tag to Product 
				else {
					logger.debug("Tag does not exist. Creating new Tag...");
					newTag = tagRepository.save(tagFromReq);
				    logger.debug("Tag Id : " + newTag.getTagId());
				}
			} catch (RuntimeException e) {
			    logger.debug("Exception: No Tags found. Creating new Tag...");
			    newTag = new Tag(tagFromReq.getName()); // create new
			}
			
			//Add product to the tag object
		    newTag.addProduct(product);

			newTagList.add(newTag);
		}

		return newTagList;
	}



}
