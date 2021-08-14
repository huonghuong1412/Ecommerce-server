package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Color;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{

	public Color findOneByColor(String color);

	@Query("select count(entity.id) from Color entity where entity.color =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkColor(String color, Long id);
	
}
