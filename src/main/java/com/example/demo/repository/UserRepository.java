package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	public User findOneByUsername(String username);

	public boolean existsByUsername(String username);

	public boolean existsByEmail(String email);
	
	public User findOneByEmail(String email);

}
