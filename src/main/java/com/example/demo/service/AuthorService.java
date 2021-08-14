package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.AuthorDto;

@Service
public interface AuthorService {
	public List<AuthorDto> getAll();

	public AuthorDto saveOrUpdate(AuthorDto dto);
	
	public AuthorDto getOne(Long id);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
