package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.to_entity.UserDto;
import com.example.demo.entity.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDto getCurrentUser(Long id) {
		User user = userRepository.getOne(id);
		UserDto dto = new UserDto(user);
		return dto;
	}
	
}
