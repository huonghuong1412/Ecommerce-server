package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

import com.example.demo.dto.to_entity.PublisherDto;
import com.example.demo.service.PublisherService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/publisher")
public class RestPublisherController {
	@Qualifier("publisherServiceImpl")
	@Autowired
	private PublisherService service;

	@GetMapping("")
	public ResponseEntity<List<PublisherDto>> getAll() {
		List<PublisherDto> result = service.getAll();
		return new ResponseEntity<List<PublisherDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PublisherDto> getOne(@PathVariable Long id) {
		PublisherDto result = service.getOne(id);
		return new ResponseEntity<PublisherDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<PublisherDto> create(@RequestBody PublisherDto dto) {
		PublisherDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PublisherDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PublisherDto> update(@RequestBody PublisherDto dto, @PathVariable Long id) {
		dto.setId(id);
		PublisherDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PublisherDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value ="id", required = false) Long id, @RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
