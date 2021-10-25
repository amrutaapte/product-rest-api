package com.product.main.model;


import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long>{
	
	boolean existsByName(String tagName);
	
	Tag findByName(String tagName);

}
