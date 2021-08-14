package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.dto.to_entity.BrandDto;
import com.example.demo.service.BrandService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/brand")
public class RestBrandController {

	@Autowired
	private BrandService service;

	@GetMapping("")
	public ResponseEntity<List<BrandDto>> getAll() {
		List<BrandDto> result = service.getAll();
		return new ResponseEntity<List<BrandDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<BrandDto> getOne(@PathVariable Long id) {
		BrandDto result = service.getOne(id);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<BrandDto> create(@RequestBody BrandDto dto) {
		BrandDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<BrandDto> update(@RequestBody BrandDto dto, @PathVariable Long id) {
		dto.setId(id);
		BrandDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<BrandDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@GetMapping(value = "checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value = "id", required = false) Long id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
