package com.example.demo.repository.tinhthanh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.tinhthanh.Province;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, String>{
	public Province findOneByProvinceid(String id);
}
