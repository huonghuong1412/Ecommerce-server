package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.CategoryDto;

@Service
public interface CategoryService {
	public List<CategoryDto> getAllCategoryWithSub();
	public List<CategoryDto> getAllCategory(String category);
	public CategoryDto saveOrUpdate(CategoryDto dto);
	
	public CategoryDto getOneCategory(String code);
	public CategoryDto getOne(Long id);
	
	public Boolean deleteCategory(Long id);
	public Boolean checkCode(Long id, String code);
}
