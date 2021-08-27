package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;

@Service
public interface OrderService {

	
	public Page<OrderHisDto> getAllOrder(Integer page, Integer limit, String sortBy);
	
	public List<OrderDetailHisDto> getDetailOrderById(Long id);
	
	public List<OrderHisDto> getAllOrderByUser(String username);
	
	public OrderHisFullDto getDetailOrder(Long id);
	
	public OrderDto createOrder(OrderDto dto);
	
	public Boolean checkTradingCode(String tradingCode);

}
