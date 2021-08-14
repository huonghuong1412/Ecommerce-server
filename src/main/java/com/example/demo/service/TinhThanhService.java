package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.tinhthanh.DistrictDto;
import com.example.demo.dto.tinhthanh.ProvinceDto;
import com.example.demo.dto.tinhthanh.WardDto;

@Service
public interface TinhThanhService {

	public List<ProvinceDto> getAllProvince();
	public List<DistrictDto> getAllDistrictByProvinceId(String id);
	public List<WardDto> getAllWardByDistrictId(String id);

}
