package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.to_entity.ColorDto;

@Service
public interface ColorService {

	public Page<ColorDto> getAll(SearchDto dto);

	public ColorDto saveOrUpdate(ColorDto dto);

	public Boolean delete(Long id);

}
