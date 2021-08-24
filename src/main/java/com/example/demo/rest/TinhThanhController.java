package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.tinhthanh.DistrictDto;
import com.example.demo.dto.tinhthanh.ProvinceDto;
import com.example.demo.dto.tinhthanh.WardDto;
import com.example.demo.service.TinhThanhService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/tinhthanh")
public class TinhThanhController {

	@Qualifier("tinhThanhServiceImpl")
	@Autowired
	private TinhThanhService service;

	@GetMapping("/city")
	public ResponseEntity<List<ProvinceDto>> getAllCity() {
		List<ProvinceDto> result = service.getAllProvince();
		return new ResponseEntity<List<ProvinceDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/city/{id}/district")
	public ResponseEntity<List<DistrictDto>> getAllDistrict(@PathVariable String id) {
		List<DistrictDto> result = service.getAllDistrictByProvinceId(id);
		return new ResponseEntity<List<DistrictDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/district/{id}/ward")
	public ResponseEntity<List<WardDto>> getAllWard(@PathVariable String id) {
		List<WardDto> result = service.getAllWardByDistrictId(id);
		return new ResponseEntity<List<WardDto>>(result, HttpStatus.OK);
	}

}
