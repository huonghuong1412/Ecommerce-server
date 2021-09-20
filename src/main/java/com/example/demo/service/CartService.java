package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.order.CartDto;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Product;

@Service
public interface CartService {

	// tạo giỏ hàng
	public CartDto createCart(CartDto dto);

	// cập nhật số lượng sản phẩm trong giỏ hàng
	public CartDto updateCart(CartDto dto);
	
	// get giỏ hàng theo user
	public CartDto getCartByUser(Long user_id);
	
	public Integer getQuantityItemByUser(Long user_id);
	public Integer getQuantityProductByUser(Long user_id);

	// Xử lý cart detail
	public CartDetail getCartDetailByProductAndCart(Product product, Cart cart);

	public Boolean deleteCartDetail(Long user_id, Long product_id);

}
