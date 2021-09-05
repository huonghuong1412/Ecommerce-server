package com.example.demo.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.entity.user.ViewedProduct;

@Repository
public interface ViewedProductRepository extends JpaRepository<ViewedProduct, Long> {
	
	public Boolean existsByProduct(Product product);

	public List<ViewedProduct> getAllByUser(User user, Sort sort);

}
