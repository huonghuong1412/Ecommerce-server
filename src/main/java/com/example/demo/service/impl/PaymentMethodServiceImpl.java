package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.PaymentMethodDto;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.service.PaymentMethodService;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

	@Autowired
	private PaymentMethodRepository repos;
	
	@Override
	public List<PaymentMethodDto> getAll() {
		List<PaymentMethodDto> list = new ArrayList<PaymentMethodDto>();
		List<PaymentMethod> entities = repos.findAll();
		for(PaymentMethod method : entities) {
			PaymentMethodDto dto = new PaymentMethodDto(method);
			list.add(dto);
		}
		return list;
	}

	@Override
	public PaymentMethodDto saveOrUpdate(PaymentMethodDto dto) {
		if (dto != null) {
			PaymentMethod entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new PaymentMethod();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new PaymentMethodDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			repos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public Boolean checkCode(Long id, String code) {
		if(code != null && StringUtils.hasText(code)) {
			Long count = repos.checkCode(code, id);
			return count != 0l;
		}
		return null;
	}

	@Override
	public PaymentMethodDto getOne(Long id) {
		// TODO Auto-generated method stub
		PaymentMethod entity = repos.getOne(id);
		PaymentMethodDto dto = new PaymentMethodDto(entity);
		return dto;
	}

	

}
