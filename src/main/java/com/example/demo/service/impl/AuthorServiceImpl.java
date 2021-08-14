package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.AuthorDto;
import com.example.demo.entity.product.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository repos;

	@Override
	public List<AuthorDto> getAll() {
		List<AuthorDto> list = new ArrayList<>();
		List<Author> entities = repos.findAll();
		for (Author entity : entities) {
			AuthorDto dto = new AuthorDto(entity);
			list.add(dto);
		}
		return list;
	}

	@Override
	public AuthorDto saveOrUpdate(AuthorDto dto) {
		if (dto != null) {
			Author entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Author();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new AuthorDto(entity);
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
	public AuthorDto getOne(Long id) {
		Author author = repos.getOne(id);
		AuthorDto dto = new AuthorDto(author);
		return dto;
	}

}
