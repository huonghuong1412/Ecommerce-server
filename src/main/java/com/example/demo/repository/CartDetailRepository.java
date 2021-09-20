package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Product;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {

	public CartDetail getByProductAndCart(Product product, Cart cart);
	
	public List<CartDetail> findAllByCart(Cart cart);
	
	public Boolean existsByProductAndCart(Product product, Cart cart);
	
}
