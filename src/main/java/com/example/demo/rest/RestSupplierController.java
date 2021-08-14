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

import com.example.demo.dto.to_entity.SupplierDto;
import com.example.demo.service.SupplierService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/supplier")
public class RestSupplierController {

	@Autowired
	private SupplierService service;

	@GetMapping("")
	public ResponseEntity<List<SupplierDto>> getAll() {
		List<SupplierDto> result = service.getAll();
		return new ResponseEntity<List<SupplierDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<SupplierDto> getOne(@PathVariable Long id) {
		SupplierDto result = service.getOne(id);
		return new ResponseEntity<SupplierDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<SupplierDto> create(@RequestBody SupplierDto dto) {
		SupplierDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SupplierDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SupplierDto> update(@RequestBody SupplierDto dto, @PathVariable Long id) {
		dto.setId(id);
		SupplierDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SupplierDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value ="id", required = false) Long id, @RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
