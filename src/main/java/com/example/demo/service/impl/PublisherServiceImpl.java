package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.product.PublisherDto;
import com.example.demo.entity.product.Publisher;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.service.PublisherService;

@Service
public class PublisherServiceImpl implements PublisherService {

	@Autowired
	private PublisherRepository repos;

	@Override
	public Page<PublisherDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Publisher> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<PublisherDto> dtos = list.map(tag -> new PublisherDto(tag));

		return dtos;
	}

	@Override
	public PublisherDto saveOrUpdate(PublisherDto dto) {
		if (dto != null) {
			Publisher entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new Publisher();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setDisplay(1);
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
			Publisher entity = repos.getById(id);
			entity.setDisplay(0);
			entity = repos.save(entity);
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
	public PublisherDto getOne(Long id) {
		// TODO Auto-generated method stub
		Publisher entity = repos.getById(id);
		PublisherDto dto = new PublisherDto(entity);
		return dto;
	}

}
