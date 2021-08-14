package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.CategoryDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repos;

	@Autowired
	private SubCategoryRepository subRepos;

	@Override
	public List<CategoryDto> getAllCategoryWithSub() {

		List<CategoryDto> list = new ArrayList<>();

		List<SubCategory> listSub = new ArrayList<>();

		List<Category> entities = repos.findAll();
		for (Category entity : entities) {
			SubCategory subDto = subRepos.findOneByCode(entity.getCode());
			listSub.add(subDto);
			CategoryDto dto = new CategoryDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public CategoryDto saveOrUpdate(CategoryDto dto) {
		if (dto != null) {
			Category entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Category();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new CategoryDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean deleteCategory(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<CategoryDto> getAllCategory(String categoryCode) {
		return null;
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
	public CategoryDto getOneCategory(String code) {
		CategoryDto dto = new CategoryDto();
		if(code != null) {
			Category category = repos.findOneByCode(code);
			dto = new CategoryDto(category);
		}
		return dto;
	}

	@Override
	public CategoryDto getOne(Long id) {
		Category category = repos.getOne(id);
		CategoryDto dto = new CategoryDto(category);
		return dto;
	}

}
