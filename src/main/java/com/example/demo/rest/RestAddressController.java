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

import com.example.demo.dto.to_entity.UserAddressDto;
import com.example.demo.entity.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/address")
public class RestAddressController {

	@Autowired
	private AddressService service;
	
	@Autowired
	private UserRepository repos;
	
	@GetMapping("/customer/{username}")
	public ResponseEntity<List<UserAddressDto>> getAll(@PathVariable String username) {
		List<UserAddressDto> result = service.getAllAddressByUser(username);
		return new ResponseEntity<List<UserAddressDto>>(result, HttpStatus.OK);
	}
	
	@PostMapping("/customer")
	public ResponseEntity<UserAddressDto> create(@RequestBody UserAddressDto dto) {
		UserAddressDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<UserAddressDto>(result, HttpStatus.OK);
	}
	
	@PutMapping(value = "/customer")
	public ResponseEntity<UserAddressDto> update(@RequestBody UserAddressDto dto, @RequestParam String username) {
		User user = repos.findOneByUsername(username);
		dto.setId(user.getId());
		UserAddressDto result = service.saveOrUpdate(dto);
		return new ResponseEntity<UserAddressDto>(result, HttpStatus.OK);
	}

	@DeleteMapping(value = "/customer/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean result = service.delete(id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
