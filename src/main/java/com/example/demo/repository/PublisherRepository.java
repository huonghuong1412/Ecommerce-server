package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	public Publisher findOneByName(String name);

	public Set<Publisher> findAllByProducts(Product book);
	
	@Query("select count(entity.id) from Publisher entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String name, Long id);
}
