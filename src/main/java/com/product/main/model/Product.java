package com.product.main.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@Entity
@JsonPropertyOrder({ "id", "name", "description", "brand", "tags", "category", "createdAt" })
public class Product {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, unique=true)
    private UUID id;
    
	private String name;
	private String description;
	private String brand;
	
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "product_tag", joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
	private Set<Tag> tags = new HashSet<>();
	
	private String category;
	
	@Transient
	LocalDateTime now = LocalDateTime.now();
	@Transient
	ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
	
	@Column(name = "created_at")
    ZonedDateTime createdAt = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));

	public Product() {}
	
	@JsonCreator
	public Product(String name, String description, String brand,  String category) {
		this.name = name;
		this.description = description;
		this.brand = brand;
		this.category = category;
	}
	
	//Getters and Setters
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

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	public ZonedDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(ZonedDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", brand="
				+ brand + ", tags=" + tags + ", category=" + category + "]";
	}



	

}
