package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.user.ShopInfoDto;
import com.example.demo.entity.user.ShopInfo;
import com.example.demo.repository.ShopInfoRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/shop-info")
public class ShopInfoController {

	@Autowired
	private ShopInfoRepository repos;

	@GetMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ShopInfo> getShopInfo() {
		ShopInfo shop = repos.getShopInfo();
		if (shop != null) {
			return new ResponseEntity<ShopInfo>(shop, HttpStatus.OK);
		} else {
			return new ResponseEntity<ShopInfo>(shop, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<ShopInfo> updateShopInfo(@RequestBody ShopInfoDto data, @PathVariable Long id) {
		ShopInfo shop = repos.getById(id);
		shop.setName(data.getName());
		shop.setEmail(data.getEmail());
		shop.setPhone(data.getPhone());
		shop.setImage(data.getImage());
		shop.setDescription(data.getDescription());
		shop.setAddress(data.getAddress());
		return new ResponseEntity<ShopInfo>(shop, HttpStatus.OK);
	}

}
