package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.to_entity.ShipmentDto;
import com.example.demo.entity.order.Shipment;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService{

	@Autowired
	private ShipmentRepository repos;
	
	@Override
	public List<ShipmentDto> getAll() {
		List<ShipmentDto> list = new ArrayList<>();
		List<Shipment> entities = repos.findAll();
		for(Shipment method : entities) {
			ShipmentDto dto = new ShipmentDto(method);
			list.add(dto);
		}
		return list;
	}

	@Override
	public ShipmentDto saveOrUpdate(ShipmentDto dto) {
		if (dto != null) {
			Shipment entity = null;
			if (dto.getId() != null) {
				entity = repos.getOne(dto.getId());
			}
			if (entity == null) {
				entity = new Shipment();
			}

			entity.setName(dto.getName());
			entity.setCode(dto.getCode());
			entity.setFee(dto.getFee());
			entity.setType(dto.getType());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity = repos.save(entity);

			if (entity != null) {
				return new ShipmentDto(entity);
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
	public ShipmentDto getOne(Long id) {
		// TODO Auto-generated method stub
		Shipment shipment = repos.getOne(id);
		ShipmentDto dto = new ShipmentDto(shipment);
		return dto;
	}

}
