package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.Shipper;
import com.example.demo.entity.user.User;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {

	public Shipper findOneByUser(User user);
	
}
