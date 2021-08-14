package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.BrandDto;

@Service
public interface BrandService {
	public List<BrandDto> getAll();

	public BrandDto getOne(Long id);
	
	public BrandDto saveOrUpdate(BrandDto dto);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);
}
