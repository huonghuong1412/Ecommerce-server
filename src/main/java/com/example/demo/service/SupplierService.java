package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.SupplierDto;

@Service
public interface SupplierService {

	public List<SupplierDto> getAll();

	public SupplierDto saveOrUpdate(SupplierDto dto);
	
	public SupplierDto getOne(Long id); 

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);

}
