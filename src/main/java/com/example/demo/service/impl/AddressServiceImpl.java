package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.UserAddressDto;
import com.example.demo.entity.user.ShipAddress;
import com.example.demo.entity.user.User;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressRepository addRepos;

	@Autowired
	private UserRepository userRepos;

	@Override
	public UserAddressDto saveOrUpdate(UserAddressDto dto) {
		if (dto != null) {
			ShipAddress entity = null;

			User user = userRepos.findOneByUsername(dto.getUsername());

			if (dto.getId() != null) {
				entity = addRepos.getById(dto.getId());
			}
			if (entity == null) {
				entity = new ShipAddress();
			}

			entity.setCity(dto.getCity());
			entity.setDistrict(dto.getDistrict());
			entity.setWard(dto.getWard());
			entity.setHouse(dto.getHouse());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setUser(user);
			
			user.setAddress(entity);

			entity = addRepos.save(entity);

			if (entity != null) {
				return new UserAddressDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			addRepos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public List<UserAddressDto> getAllAddressByUser(String username) {
		List<UserAddressDto> list = new ArrayList<>();
		User user = userRepos.findOneByUsername(username);
		List<ShipAddress> entities = addRepos.findAllByUser(user);
		for (ShipAddress entity : entities) {
			UserAddressDto dto = new UserAddressDto(entity);
			list.add(dto);
		}
		return list;
	}

}
