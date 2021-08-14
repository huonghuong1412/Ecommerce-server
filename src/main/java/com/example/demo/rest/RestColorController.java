package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.example.demo.dto.to_entity.ColorDto;
import com.example.demo.service.ColorService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/color")
public class RestColorController {

	@Autowired
	private ColorService service;
	
	@GetMapping("")
	public ResponseEntity<Page<ColorDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword);
		Page<ColorDto> result = service.getAll(dto);
		return new ResponseEntity<Page<ColorDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ColorDto> create(@RequestBody ColorDto dto) {
		ColorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ColorDto> update(@RequestBody ColorDto dto, @PathVariable Long id) {
		dto.setId(id);
		ColorDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ColorDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

//	@GetMapping(value = "checkCode")
//	public ResponseEntity<Boolean> check(@RequestParam(value = "id", required = false) Long id,
//			@RequestParam("code") String code) {
//		Boolean result = service.checkCode(id, code);
//		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
//	}

}
