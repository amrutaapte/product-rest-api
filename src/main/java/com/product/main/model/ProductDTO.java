package com.product.main.model;


import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "id", "name", "description", "brand", "tags", "category", "createdAt" })
public class ProductDTO {
	
	@JsonProperty
    private UUID id;
	 
	@JsonProperty
    private String name;
	
	@JsonProperty
    private String description;
	
	@JsonProperty
    private String brand;
	
	@JsonProperty
    private String category;

	
	@JsonProperty
	private Set<String> tags;
	
	@JsonProperty
	@JsonFormat(shape = JsonFormat.Shape.STRING, timezone="UTC")
	private ZonedDateTime createdAt;
    

	public ProductDTO(UUID id, String name, String description, String brand, String category, ZonedDateTime createdAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.category = category;
		this.createdAt = createdAt;
	}

	public Set<String> getStringOfTags(Set<Tag> setTags){
   	
    	Set<String> tagListOfString = new HashSet<String>();
    	for(Tag tag : setTags) {
    		tagListOfString.add(tag.getName());
    	}
    	return tagListOfString;
    	
    }


	//Getters and Setters
	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "ProductDTO [id=" + id + ", name=" + name + ", description=" + description + ", brand=" + brand
				+ ", category=" + category + ", tags=" + tags + "]";
	}
	
	

}
