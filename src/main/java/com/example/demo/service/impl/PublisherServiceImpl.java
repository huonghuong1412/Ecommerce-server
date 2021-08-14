package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.PublisherDto;
import com.example.demo.entity.product.Publisher;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository repos;

	@Override
	public List<PublisherDto> getAll() {
		List<PublisherDto> list = new ArrayList<>();
		List<Publisher> entities = repos.findAll();
		for (Publisher entity : entities) {
			PublisherDto dto = new PublisherDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public PublisherDto saveOrUpdate(PublisherDto dto) {
		if (dto != null) {
			Publisher entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Publisher();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new PublisherDto(entity);
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
		if(code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public PublisherDto getOne(Long id) {
		// TODO Auto-generated method stub
		Publisher entity= repos.getOne(id);
		PublisherDto dto = new PublisherDto(entity);
		return dto;
	}

}
