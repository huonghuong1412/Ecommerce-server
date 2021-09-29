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
		Page<Author> list = repos.getList(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<AuthorDto> dtos = list.map(tag -> new AuthorDto(tag));
		return dtos;
	}

	@Override
	public Page<AuthorDto> getListHide(Integer page, Integer limit, String sortBy) {
		Page<Author> list = repos.getListHide(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<AuthorDto> dtos = list.map(tag -> new AuthorDto(tag));
		return dtos;
	}

	@Override
	public Page<AuthorDto> getAll(Integer page, Integer limit, String sortBy) {
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
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
			}
			if (entity == null) {
				entity = new Author();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setDisplay(1);

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
//			repos.deleteById(id);
			Author entity = repos.getById(id);
			if (entity.getDisplay() == 1) {
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
	public Boolean checkCode(Long id, String code) {
		if (code != null && StringUtils.hasText(code)) {
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
