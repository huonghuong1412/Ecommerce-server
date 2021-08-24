package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.category.SlideDto;
import com.example.demo.entity.category.Slide;
import com.example.demo.repository.SlideRepository;
import com.example.demo.service.SlideService;

@Service
public class SlideServiceImpl implements SlideService {

	@Autowired
	private SlideRepository repos;

	@Override
	public List<SlideDto> getAll() {
		List<SlideDto> list = new ArrayList<>();
		List<Slide> entities = repos.findAll();
		for (Slide method : entities) {
			SlideDto dto = new SlideDto(method);
			list.add(dto);
		}
		return list;
	}

	@Override
	public SlideDto saveOrUpdate(SlideDto dto) {
		if (dto != null) {
			Slide entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new Slide();
			}

			entity.setUrl(dto.getUrl());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new SlideDto(entity);
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
		// TODO Auto-generated method stub
		return null;
	}

}
