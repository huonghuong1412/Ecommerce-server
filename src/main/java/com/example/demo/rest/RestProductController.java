package com.example.demo.rest;

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
import com.example.demo.dto.to_entity.ProductDto;
import com.example.demo.dto.to_show.ProductDtoNew;
import com.example.demo.dto.to_show.ProductListDto;
import com.example.demo.service.ProductService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/product")
public class RestProductController {

	@Qualifier("productServiceImpl")
	@Autowired
	private ProductService service;

	@GetMapping(value = "/search")
	public ResponseEntity<Page<ProductListDto>> searchByPage(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword, null, null);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/danh-muc/{category}", name = "getByCategory")
	public ResponseEntity<Page<ProductListDto>> searchByPageCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, null);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/danh-muc/{category}/{subcategory}")
	public ResponseEntity<Page<ProductListDto>> searchByPageSubCategory(
			@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword, @PathVariable String category,
			@PathVariable String subcategory) {
		SearchDto dto = new SearchDto(page, limit, keyword, category, subcategory);
		Page<ProductListDto> result = service.productList(dto);
		return new ResponseEntity<Page<ProductListDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/san-pham/{id}", name = "getProductByID")
	public ResponseEntity<ProductDtoNew> getProduct(@PathVariable Long id) {
		ProductDtoNew result = service.getProductById(id);
		return new ResponseEntity<ProductDtoNew>(result, HttpStatus.OK);
	}
	
	@PostMapping("/add-book")
	public ResponseEntity<ProductDto> createBook(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdateBook(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PostMapping("/add-food")
	public ResponseEntity<ProductDto> createFood(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdateFood(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PostMapping("/add-phone")
	public ResponseEntity<ProductDto> createPhone(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdatePhone(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PostMapping("/add-laptop")
	public ResponseEntity<ProductDto> createLaptop(@RequestBody ProductDto dto) {
		ProductDto result = service.saveOrUpdateLaptop(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/update-book/{id}")
	public ResponseEntity<ProductDto> updateBook(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdateBook(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update-food/{id}")
	public ResponseEntity<ProductDto> updateFood(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdateFood(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update-phone/{id}")
	public ResponseEntity<ProductDto> updatePhone(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdatePhone(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update-laptop/{id}")
	public ResponseEntity<ProductDto> updateLaptop(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.saveOrUpdateLaptop(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	////////////////////////////// Nhập hàng
	@PutMapping(value = "/import-laptop/{id}")
	public ResponseEntity<ProductDto> importLaptop(@RequestBody ProductDto dto, @PathVariable Long id) {
		dto.setId(id);
		ProductDto result = service.importLaptop(dto);
		return new ResponseEntity<ProductDto>(result, HttpStatus.OK);
	}

}
