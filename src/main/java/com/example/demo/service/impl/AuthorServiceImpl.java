package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.product.AuthorDto;
import com.example.demo.entity.product.Author;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorRepository repos;

	@Override
	public Page<AuthorDto> getList(Integer page, Integer limit, String sortBy) {
		Page<Author> list = repos.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));

		Page<AuthorDto> dtos = list.map(tag -> new AuthorDto(tag));

		return dtos;
	}

	@Override
	public AuthorDto saveOrUpdate(AuthorDto dto) {
		if (dto != null) {
			Author entity = null;
			if (dto.getId() != null) {
				entity = repos.getById(dto.getId());
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
		Author author = repos.getById(id);
		AuthorDto dto = new AuthorDto(author);
		return dto;
	}

}
