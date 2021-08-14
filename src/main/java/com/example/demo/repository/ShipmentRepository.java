package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	public Shipment findOneByCode(String code);
	
	@Query("select count(entity.id) from Shipment entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
}
