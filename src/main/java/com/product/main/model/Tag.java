package com.product.main.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Entity(name = "Tag")
@Table(name = "tag")
@JsonPropertyOrder({ "name" })
public class Tag {
 
    @Id
    @GeneratedValue
    private Long tagId;
 
    private String name;
 
    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
    @JsonIgnore
    private Set<Product> product = new HashSet<>();
 
    public Tag() {}
 
    public Tag(String name) {
        this.name = name;
    }
 
    public void addProduct(Product prod) {
    	product.add(prod);
    	prod.getTags().add(this);
    }


    //Getters and Setters
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Product> getProducts() {
		return product;
	}

	public void setProduct(Set<Product> product) {
		this.product = product;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tag tag = (Tag) o;
        return Objects.equals(name, tag.name);
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

	
}