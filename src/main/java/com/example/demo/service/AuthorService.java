package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.AuthorDto;

@Service
public interface AuthorService {
	public Page<AuthorDto> getList(Integer page, Integer limit, String sortBy);
	
	public Page<AuthorDto> getListHide(Integer page, Integer limit, String sortBy);
	
	public Page<AuthorDto> getAll(Integer page, Integer limit, String sortBy);

	public AuthorDto saveOrUpdate(AuthorDto dto);
	
	public AuthorDto getOne(Long id);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
