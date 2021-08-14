package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.SubCategoryDto;

@Service
public interface SubCategoryService {
	
	public List<SubCategoryDto> getAllSubCategory();
	
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode);
	
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto);
	
	public SubCategoryDto getOne(Long id);

	public Boolean deleteSubCategory(Long id);
	
	public Boolean checkCode(Long id, String code);
}
