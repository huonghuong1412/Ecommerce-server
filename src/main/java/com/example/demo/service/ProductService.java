package com.example.demo.service;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoNew;
import com.example.demo.dto.product.ProductListDto;

@Service
public interface ProductService {

	public Page<ProductDto> searchByPage(SearchDto dto);
	public Page<ProductListDto> productList(SearchDto dto);

	public ProductDtoNew getProductById(Long id);
	
	public ProductDto getDetailProduct(Long id);
	
	// Them san pham chung
	public ProductDto saveOrUpdate(ProductDto dto);
	
	public Boolean delete(Long id);

}
