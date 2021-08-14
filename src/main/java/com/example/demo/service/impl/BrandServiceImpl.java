package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.BrandDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.product.Brand;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService {

	@Autowired
	private BrandRepository repos;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public List<BrandDto> getAll() {
		List<BrandDto> list = new ArrayList<>();
		List<Brand> entities = repos.findAll();
		for (Brand entity : entities) {
			BrandDto dto = new BrandDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public BrandDto saveOrUpdate(BrandDto dto) {
		if (dto != null) {
			Category category = categoryRepository.findOneByCode(dto.getCategoryCode());
			
			Brand entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Brand();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setMadeIn(dto.getMadeIn());
			entity.setCategory(category);
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			

			entity = repos.save(entity);

			if (entity != null) {
				return new BrandDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String code) {
		if (code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public BrandDto getOne(Long id) {
		Brand brand = repos.getOne(id);
		BrandDto dto = new BrandDto(brand);
		return dto;
	}

}
