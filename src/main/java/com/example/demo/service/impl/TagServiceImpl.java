package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.common.Slug;
import com.example.demo.dto.category.TagDto;
import com.example.demo.entity.category.Tag;
import com.example.demo.repository.TagRepository;
import com.example.demo.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	@Autowired
	private TagRepository repos;

	@Override
	public Page<TagDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Tag> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<TagDto> dtos = list.map(tag -> new TagDto(tag));

		return dtos;
	}
	
	@Override
	public Page<TagDto> getListHide(Integer page, Integer limit, String sortBy) {
		Page<Tag> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<TagDto> dtos = list.map(tag -> new TagDto(tag));

		return dtos;
	}
	
	@Override
	public Page<TagDto> getAll(Integer page, Integer limit, String sortBy) {
		Page<Tag> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<TagDto> dtos = list.map(tag -> new TagDto(tag));

		return dtos;
	}

	@Override
	public TagDto saveOrUpdate(TagDto dto) {
		if (dto != null) {
			Tag entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Tag();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(Slug.makeCode(dto.getName()));
			entity.setDisplay(1);
			entity = repos.save(entity);

			if (entity != null) {
				return new TagDto(entity);
			}
		}
		return null;
	}

	@Override
	public TagDto getOne(Long id) {
		Tag author = repos.getById(id);
		TagDto dto = new TagDto(author);
		return dto;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Tag entity = repos.getById(id);
			if(entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
			entity = repos.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String name) {
		if (name != null && StringUtils.hasText(name)) {
			Long count = repos.checkCode(name, id);
			return count != 0l;
		}
		return null;
	}

}
