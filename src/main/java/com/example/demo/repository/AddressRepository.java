package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.ShipAddress;
import com.example.demo.entity.user.User;

@Repository
public interface AddressRepository extends JpaRepository<ShipAddress, Long>{

	public List<ShipAddress> findAllByUser(User user);
	
}
