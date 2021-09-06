package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoNew;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class ProductController {

	@Qualifier("productServiceImpl")
	@Autowired
	private ProductService service;

	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductListDto>> searchByPage(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, 
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy, 
			@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue) {
		SearchDto dto = new SearchDto(page, limit, keyword, null, null);
		dto.setSortBy(sortBy);
		dto.setSortValue(sortValue);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/danh-muc/{category}", name = "getByCategory")
	public ResponseEntity<Page<ProductListDto>> searchByPageCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category,
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy, 
			@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, null);
		dto.setSortBy(sortBy);
		dto.setSortValue(sortValue);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/danh-muc/{category}/{subcategory}")
	public ResponseEntity<Page<ProductListDto>> searchByPageSubCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "20") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, 
			@RequestParam(name = "sortBy", defaultValue = "createdDate") String sortBy, 
			@RequestParam(name = "sortValue", defaultValue = "DESC") String sortValue,
			@PathVariable String category,
			@PathVariable String subcategory) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, subcategory);
		dto.setSortBy(sortBy);
		dto.setSortValue(sortValue);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/brand")
	public ResponseEntity<List<ProductListDto>> getProductListByBrandNotExists(@RequestParam Long productId, @RequestParam String brandCode) {
		List<ProductListDto> result = service.getAllByBrandAndNotExists(productId, brandCode);
		return new ResponseEntity<List<ProductListDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/all/{brandCode}")
	public ResponseEntity<List<ProductListDto>> getProductListByBrand(@PathVariable String brandCode) {
		List<ProductListDto> result = service.getAllByBrand(brandCode);
		return new ResponseEntity<List<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/san-pham/{id}", name = "getProductByID")
	public ResponseEntity<ProductDtoNew> getProduct(@PathVariable Long id) {
		ProductDtoNew result = service.getProductById(id);
		return new ResponseEntity<ProductDtoNew>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/detail/{id}")
	public ResponseEntity<ProductDto> getProductByIdToCreate(@PathVariable Long id) {
		ProductDto result = service.getDetailProduct(id);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@PostMapping("/add-product")
	public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/update-product/{id}")
	public ResponseEntity<ProductDto> updateLaptop(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
