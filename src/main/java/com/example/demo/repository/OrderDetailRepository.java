package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.order.OrderDetail;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
	public List<OrderDetail> getAllByProductId(Long id);
	
	public List<OrderDetail> getAllByOrderId(Long id);
	
}
