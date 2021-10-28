package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	public Boolean existsByProductAndColor(Product p, Color c);
	
	public List<Inventory> getAllByProductId(Long productId);
	
	public Inventory getOneByProductAndColor(Product p, Color c);
	
}
