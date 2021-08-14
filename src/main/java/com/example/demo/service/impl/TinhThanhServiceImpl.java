package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.tinhthanh.DistrictDto;
import com.example.demo.dto.tinhthanh.ProvinceDto;
import com.example.demo.dto.tinhthanh.WardDto;
import com.example.demo.entity.tinhthanh.District;
import com.example.demo.entity.tinhthanh.Province;
import com.example.demo.entity.tinhthanh.Ward;
import com.example.demo.repository.tinhthanh.DistrictRepository;
import com.example.demo.repository.tinhthanh.ProvinceRepository;
import com.example.demo.repository.tinhthanh.WardRepository;
import com.example.demo.service.TinhThanhService;

@Service
public class TinhThanhServiceImpl implements TinhThanhService {

	@Autowired
	private ProvinceRepository provinceRepos;

	@Autowired
	private DistrictRepository districtRepos;

	@Autowired
	private WardRepository wardRepos;

	@Override
	public List<ProvinceDto> getAllProvince() {
		List<ProvinceDto> list = new ArrayList<ProvinceDto>();
		List<Province> entities = provinceRepos.findAll();
		for (Province entity : entities) {
			ProvinceDto dto = new ProvinceDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<DistrictDto> getAllDistrictByProvinceId(String id) {
		List<DistrictDto> list = new ArrayList<>();
		List<District> entities = districtRepos.findAllByProvinceid(id);
		for (District entity : entities) {
			DistrictDto dto = new DistrictDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<WardDto> getAllWardByDistrictId(String id) {
		List<WardDto> list = new ArrayList<>();
		List<Ward> entities = wardRepos.findAllByDistrictid(id);
		for (Ward entity : entities) {
			WardDto dto = new WardDto(entity);
			list.add(dto);
		}
		return list;
	}

}
