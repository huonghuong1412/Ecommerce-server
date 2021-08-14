package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.to_entity.ProductDto;
import com.example.demo.dto.to_show.ProductDtoNew;
import com.example.demo.dto.to_show.ProductListDto;

@Service
public interface ProductService {

	public Page<ProductDto> searchByPage(SearchDto dto);
	public Page<ProductListDto> productList(SearchDto dto);

	public ProductDtoNew getProductById(Long id);

	public ProductDto saveOrUpdateBook(ProductDto dto);
	public ProductDto saveOrUpdateFood(ProductDto dto);
	public ProductDto saveOrUpdatePhone(ProductDto dto);
	public ProductDto saveOrUpdateLaptop(ProductDto dto);
	
	public ProductDto importBook(ProductDto dto);
	public ProductDto importFood(ProductDto dto);
	public ProductDto importPhone(ProductDto dto);
	public ProductDto importLaptop(ProductDto dto);
	
	public Boolean delete(Long id);

}
