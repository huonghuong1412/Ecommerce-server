package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.user.ViewedProductDto;
import com.example.demo.entity.product.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ViewedProductRepository;
import com.example.demo.service.ViewedProductService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/viewed-product")
public class ViewedProductController {

	@Autowired
	private ViewedProductService service;
	
	@Autowired
	private ViewedProductRepository repos;
	
	@Autowired
	private ProductRepository productRepos;

	@GetMapping("")
	public ResponseEntity<List<ViewedProductDto>> getByUserAndProduct(@RequestParam String username) {
		List<ViewedProductDto> result = service.getListByUser(username);
		return new ResponseEntity<List<ViewedProductDto>>(result, HttpStatus.OK);
	}
	

	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody ViewedProductDto dto) {
		Product product = productRepos.getById(dto.getProductId());
		if(repos.existsByProduct(product)) {
			return null;
		} else {
			service.saveOrUpdate(dto);
			return ResponseEntity.ok(new MessageResponse("OK"));
		}
	}
}
