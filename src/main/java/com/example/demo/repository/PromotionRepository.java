package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.other.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {

}
