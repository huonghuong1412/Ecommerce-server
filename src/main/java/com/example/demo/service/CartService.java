package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CartResponse;
import com.example.demo.dto.order.CartDto;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Product;

@Service
public interface CartService {

	// tạo giỏ hàng
	public CartResponse createCart(CartDto dto);

	// cập nhật số lượng sản phẩm trong giỏ hàng
	public CartResponse updateCart(CartDto dto);

	// cập nhật số lượng sản phẩm trong giỏ hàng
	public CartResponse checkItemQuantity(CartDto dto);

	// get giỏ hàng theo users
	public CartDto getCartByUser(String username);

	public Integer getQuantityItemByUser(String username);

	public Integer getQuantityProductByUser(String username);

	// Xử lý cart detail
	public CartDetail getCartDetailByProductAndCart(Product product, Cart cart);

	// Xoá 1 sản phẩm trong giỏ hàng
	public CartResponse deleteCartDetail(String username, Long product_id);
	
	// Xoá giỏ hàng sau khi đặt hàng thành công
	public Boolean deleteAllCartDetail(String username);

}
