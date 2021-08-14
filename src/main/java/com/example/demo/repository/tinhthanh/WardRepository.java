package com.example.demo.repository.tinhthanh;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.tinhthanh.Ward;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
	public List<Ward> findAllByDistrictid(String id);
}
