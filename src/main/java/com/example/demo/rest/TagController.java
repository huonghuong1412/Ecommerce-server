package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.example.demo.dto.category.TagDto;
import com.example.demo.service.TagService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/tag")
public class TagController {

	@Autowired
	private TagService service;

	@GetMapping("")
	public ResponseEntity<Page<TagDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy) {
		Page<TagDto> result = service.getList(page, limit, sortBy);
		return new ResponseEntity<Page<TagDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<TagDto>> getAllAdmin(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "sortBy", defaultValue = "id") String sortBy, @RequestParam(name="display", defaultValue = "2") Integer display) {
		Page<TagDto> result = null;
		if(display == 1) {
			result = service.getList(page, limit, sortBy);
		} else if(display == 0) {
			result = service.getListHide(page, limit, sortBy);
		} else {
			result = service.getAll(page, limit, sortBy);
		}
		return new ResponseEntity<Page<TagDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TagDto> getOne(@PathVariable Long id) {
		TagDto result = service.getOne(id);
		return new ResponseEntity<TagDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<TagDto> create(@RequestBody TagDto dto) {
		TagDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<TagDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<TagDto> update(@RequestBody TagDto dto, @PathVariable Long id) {
		dto.setId(id);
		TagDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<TagDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/checkCode")
	public ResponseEntity<Boolean> check(@RequestParam(value = "id", required = false) Long id,
			@RequestParam("code") String code) {
		Boolean result = service.checkCode(id, code);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
