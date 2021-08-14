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

import com.example.demo.dto.to_entity.ShipmentDto;
import com.example.demo.service.ShipmentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/shipment")
public class ShipmentController {

	@Autowired
	private ShipmentService service;

	@GetMapping("")
	public ResponseEntity<List<ShipmentDto>> getAll() {
		List<ShipmentDto> result = service.getAll();
		return new ResponseEntity<List<ShipmentDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ShipmentDto> getOne(@PathVariable Long id) {
		ShipmentDto result = service.getOne(id);
		return new ResponseEntity<ShipmentDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<ShipmentDto> create(@RequestBody ShipmentDto dto) {
		ShipmentDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ShipmentDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ShipmentDto> update(@RequestBody ShipmentDto dto, @PathVariable Long id) {
		dto.setId(id);
		ShipmentDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<ShipmentDto>(result, HttpStatus.OK);
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
