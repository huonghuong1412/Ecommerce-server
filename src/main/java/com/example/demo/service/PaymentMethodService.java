package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.PaymentMethodDto;

@Service
public interface PaymentMethodService {
	
	public List<PaymentMethodDto> getAll();

	public PaymentMethodDto saveOrUpdate(PaymentMethodDto dto);
	
	public PaymentMethodDto getOne(Long id);

	public Boolean delete(Long id);
	
	public Boolean checkCode(Long id, String code);
}
