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

import com.example.demo.dto.to_entity.PaymentMethodDto;
import com.example.demo.service.PaymentMethodService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/payment-method")
public class RestPaymentMethodController {

	@Autowired
	private PaymentMethodService service;

	@GetMapping("")
	public ResponseEntity<List<PaymentMethodDto>> getAll() {
		List<PaymentMethodDto> result = service.getAll();
		return new ResponseEntity<List<PaymentMethodDto>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PaymentMethodDto> getOne(@PathVariable Long id) {
		PaymentMethodDto result = service.getOne(id);
		return new ResponseEntity<PaymentMethodDto>(result, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<PaymentMethodDto> create(@RequestBody PaymentMethodDto dto) {
		PaymentMethodDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PaymentMethodDto>(result, HttpStatus.OK);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<PaymentMethodDto> update(@RequestBody PaymentMethodDto dto, @PathVariable Long id) {
		dto.setId(id);
		PaymentMethodDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<PaymentMethodDto>(result, HttpStatus.OK);
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
