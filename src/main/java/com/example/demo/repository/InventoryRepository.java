package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.inventory.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	public Boolean existsByProductId(Long prodductId);
	
	public Inventory getOneByProductId(Long productId);
	
}
