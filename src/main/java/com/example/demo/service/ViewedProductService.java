package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.user.ViewedProductDto;

@Service
public interface ViewedProductService {

	public List<ViewedProductDto> getListByUser(String username);
	
	public ViewedProductDto saveOrUpdate(ViewedProductDto dto);
}
