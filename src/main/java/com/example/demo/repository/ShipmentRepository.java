package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

	public Shipment findOneByCode(String code);
	
	@Query("select count(entity.id) from Shipment entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from Shipment entity where entity.display = 1")
	public Page<Shipment> getList(Pageable pageable);
	
	@Query("select entity from Shipment entity where entity.display = 0")
	public Page<Shipment> getListHide(Pageable pageable);
}
