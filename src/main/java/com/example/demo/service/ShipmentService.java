package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.ShipmentDto;

@Service
public interface ShipmentService {
	public List<ShipmentDto> getAll();

	public ShipmentDto saveOrUpdate(ShipmentDto dto);
	
	public ShipmentDto getOne(Long id);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
