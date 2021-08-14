package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.SlideDto;

@Service
public interface SlideService {
	public List<SlideDto> getAll();

	public SlideDto saveOrUpdate(SlideDto dto);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
