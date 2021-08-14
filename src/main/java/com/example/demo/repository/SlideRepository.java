package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Slide;

@Repository
public interface SlideRepository extends JpaRepository<Slide, Long> {

	

}
