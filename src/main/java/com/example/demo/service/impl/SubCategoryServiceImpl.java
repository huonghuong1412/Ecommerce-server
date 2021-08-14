package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.SubCategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	@Autowired
	private SubCategoryRepository repos;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<SubCategoryDto> getAllSubCategory() {
		List<SubCategoryDto> list = new ArrayList<>();
		List<SubCategory> entities = repos.findAll();
		for (SubCategory entity : entities) {
			SubCategoryDto dto = new SubCategoryDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	public List<SubCategoryDto> getSubCategoryByCategory(String categoryCode) {
		List<SubCategoryDto> list = new ArrayList<>();
		List<SubCategory> entities = repos.findAllByCategoryCode(categoryCode);
		for (SubCategory entity : entities) {
			SubCategoryDto dto = new SubCategoryDto(entity);
			list.add(dto);
		}

		return list;
	}

	@Override
	public SubCategoryDto saveOrUpdate(SubCategoryDto dto) {
		
		Category category = categoryRepository.findOneByCode(dto.getCategoryCode());
		
		if (dto != null) {
			SubCategory entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new SubCategory();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCategory(category);
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new SubCategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteSubCategory(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public SubCategoryDto getOne(Long id) {
		// TODO Auto-generated method stub
		SubCategory entity = repos.getOne(id);
		SubCategoryDto dto = new SubCategoryDto(entity);
		return dto;
	}

}
