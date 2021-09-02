package com.example.demo.repository.tinhthanh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.tinhthanh.District;
import com.example.demo.entity.tinhthanh.Province;

@Repository
public interface DistrictRepository extends JpaRepository<District, String> {
	public List<District> findAllByProvince(Province province);
	public District findOneByDistrictid(String id);
}
