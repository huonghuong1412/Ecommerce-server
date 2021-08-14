package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.PublisherDto;

@Service
public interface PublisherService {
	public List<PublisherDto> getAll();

	public PublisherDto saveOrUpdate(PublisherDto dto);
	
	public PublisherDto getOne(Long id);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
