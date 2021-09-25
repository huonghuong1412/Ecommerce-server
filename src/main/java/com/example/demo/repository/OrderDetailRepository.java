package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.product.Product;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	public List<OrderDetail> getAllByProductId(Long id);
	
	public List<OrderDetail> getAllByOrderId(Long id);
	
	public List<OrderDetail> getByOrderAndProduct(Order order, Product product);
	
	@Query("SELECT COUNT(u) FROM OrderDetail u WHERE u.product.id=?1 AND u.order.status=2")
	public Integer countAllByProductId(Long id);
	
}
