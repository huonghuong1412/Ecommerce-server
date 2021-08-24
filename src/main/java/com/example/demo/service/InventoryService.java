package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.inventory.InventoryDetailDto;
import com.example.demo.dto.inventory.InventoryDto;
import com.example.demo.dto.inventory.InventoryDtoNew;

@Service
public interface InventoryService {

	public Page<InventoryDtoNew> getList(SearchDto dto);
	
	public InventoryDto importOrUpdate(InventoryDto dto);
	
	public List<InventoryDetailDto> getDetailInventoryById(Long id);
	
}
