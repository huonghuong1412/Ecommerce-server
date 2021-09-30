package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoNew;
import com.example.demo.dto.product.ProductListDto;

@Service
public interface ProductService {

	// Lấy các sản phẩm hiển thị lên trang chủ, có trạng thái hiển thị là 1
//	public Page<ProductDto> searchByPage(SearchDto dto);
	
	// Lấy các sản phẩm hiển thị lên trang chủ, có trạng thái hiển thị là 1
	public Page<ProductListDto> productList(SearchDto dto);
	
	// lấy toàn bộ sản phẩm trong csdl
	public Page<ProductListDto> getAllProduct(AdvanceSearchDto dto);
	
	// lấy toàn bộ sản phẩm có trạng thái 1 theo thương hiệu
	public List<ProductListDto> getAllByBrand(Long productId, String brandCode);
	
	// lấy toàn bộ sản phẩm có trạng thái 1 theo thương hiệu, phân trang
	public Page<ProductListDto> getAllProductByBrand(String brandCode, Integer page, Integer limit, String sortBy);
	
	// lấy thông tin sản phẩm theo id
	public ProductDtoNew getProductById(Long id);
	
	// lấy thông tin sản phẩm theo id
	public ProductDto getDetailProduct(Long id);

	// Them hoặc cập nhật san pham
	public ProductDto saveOrUpdate(ProductDto dto);
	
	// xoá mềm sản phẩm
	public Boolean delete(Long id);

}
