package com.example.demo.repository;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.user.User;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	public List<Order> getAllByUser(User user, Sort sort);
	
	public Boolean existsByStatus(Long status);
}
