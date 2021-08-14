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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.to_entity.SlideDto;
import com.example.demo.service.SlideService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/slide")
public class RestSlideController {

	@Autowired
	private SlideService service;

	@GetMapping("")
	public ResponseEntity<List<SlideDto>> getAll() {
		List<SlideDto> result = service.getAll();
		return new ResponseEntity<List<SlideDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<SlideDto> create(@RequestBody SlideDto dto) {
		SlideDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SlideDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<SlideDto> update(@RequestBody SlideDto dto, @PathVariable Long id) {
		dto.setId(id);
		SlideDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<SlideDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
