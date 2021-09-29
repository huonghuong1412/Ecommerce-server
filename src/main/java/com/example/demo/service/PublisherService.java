package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.product.PublisherDto;

@Service
public interface PublisherService {
	// Get danh sách client
	public Page<PublisherDto> getList(Integer page, Integer limit, String sortBy);
	
	// Get danh sách client
	public Page<PublisherDto> getListHide(Integer page, Integer limit, String sortBy);
	
	// get danh sách admin
	public Page<PublisherDto> getAll(Integer page, Integer limit, String sortBy);

	public PublisherDto saveOrUpdate(PublisherDto dto);

	public PublisherDto getOne(Long id);

	public Boolean delete(Long id);

	public Boolean checkCode(Long id, String code);
}
