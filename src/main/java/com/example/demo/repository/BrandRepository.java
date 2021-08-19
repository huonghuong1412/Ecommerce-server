package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Product;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
	public Brand findOneByCode(String code);

	public Set<Brand> findAllByProducts(Product food);
	
	@Query("select count(entity.id) from Brand entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
}
