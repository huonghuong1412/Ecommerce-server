package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CartResponse;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.order.CartDto;
import com.example.demo.service.CartService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/carts/mine")
public class CartController {

	@Autowired
	private CartService service;

	// get all info cart
	@GetMapping("/items")
	public ResponseEntity<CartDto> getCartDetail(@RequestParam Long user_id) {
		CartDto result = service.getCartByUser(user_id);
		return new ResponseEntity<CartDto>(result, HttpStatus.OK);
	}

	// get quantity & total price cart
	@GetMapping("/info")
	public ResponseEntity<CartResponse> getCartInfo(@RequestParam Long user_id) {
		Integer items_quantity = service.getQuantityProductByUser(user_id);
		Integer items_count = service.getQuantityItemByUser(user_id);
		return new ResponseEntity<CartResponse>(new CartResponse("SUCCESS", items_count, items_quantity), HttpStatus.OK);
	}

	@PostMapping("/items")
	public ResponseEntity<MessageResponse> create(@RequestBody CartDto dto) {
		service.createCart(dto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Thêm vào giỏ hàng thành công!"), HttpStatus.OK);
	}

	// cartid/quantity
	@PutMapping("/items/update")
	public ResponseEntity<MessageResponse> updateQuantity(@RequestBody CartDto dto) {
		service.updateCart(dto);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Thêm vào giỏ hàng thành công!"), HttpStatus.OK);
	}

	// cartid/quantity
	@DeleteMapping("/items/remove")
	public ResponseEntity<Boolean> deleteItem(@RequestParam Long user_id, @RequestParam Long product_id) {
		Boolean result = service.deleteCartDetail(user_id, product_id);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
