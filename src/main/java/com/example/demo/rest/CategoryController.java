package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.example.demo.dto.category.CategoryDto;
import com.example.demo.dto.category.CategoryDtoNew;
import com.example.demo.service.CategoryService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

	@Qualifier("categoryServiceImpl")
	@Autowired
	private CategoryService service;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<CategoryDtoNew>> getAllAdmin(@RequestParam(name="display", defaultValue = "2") Integer display) {
		List<CategoryDtoNew> result = new ArrayList<CategoryDtoNew>();
		if(display == 1) {
			result = service.getList();
		} else if(display == 0) {
			result = service.getListHide();
		} else {
			result = service.getAll();
		}
		return new ResponseEntity<List<CategoryDtoNew>>(result, HttpStatus.OK);
	}
	
	@GetMapping("")
	public ResponseEntity<List<CategoryDto>> getAllWithSub() {
		List<CategoryDto> result = service.getAllCategoryWithSub();
		return new ResponseEntity<List<CategoryDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<CategoryDto> getOne(@PathVariable Long id) {
		CategoryDto result = service.getOne(id);
		return new ResponseEntity<CategoryDto>(result, HttpStatus.OK);
	}
	
	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto dto) {
		CategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<CategoryDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto, @PathVariable Long id) {
		dto.setId(id);
		CategoryDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<CategoryDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.deleteCategory(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value ="id", required = false) Long id, @RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/get")
	public ResponseEntity<CategoryDto> getOneByCode(@RequestParam("code") String code) {
		CategoryDto result = service.getOneCategory(code);
		return new ResponseEntity<CategoryDto>(result, HttpStatus.OK);
	}

}
