package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.SupplierDto;
import com.example.demo.entity.product.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierRepository repos;

	@Override
	public List<SupplierDto> getAll() {
		List<SupplierDto> list = new ArrayList<>();
		List<Supplier> entities = repos.findAll();
		for (Supplier entity : entities) {
			SupplierDto dto = new SupplierDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public SupplierDto saveOrUpdate(SupplierDto dto) {
		if (dto != null) {
			Supplier entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Supplier();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setEmail(dto.getEmail());
			entity.setAddress(dto.getAddress());
			entity.setPhone(dto.getPhone());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new SupplierDto(entity);
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
	public SupplierDto getOne(Long id) {
		Supplier entity = repos.getOne(id);
		SupplierDto dto = new SupplierDto(entity);
		return dto;
	}

}
