package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.product.ProductDetail;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {

	List<ProductDetail> findAllByProductId(Long id);

	@Query("select entity.quantity from ProductDetail entity WHERE entity.product.id =?1 And entity.color.id =?2")
	public Integer getQuantityByProductIdAndColorId(Long productId, Long colorId);
	
	@Transactional
	@Modifying
	@Query("Delete from ProductDetail entity WHERE entity.product.id =?1")
	public void deleteByProductId(Long id);

	@Transactional
	@Modifying
	@Query("Delete from ProductDetail entity WHERE entity.color.id =?1")
	public void deleteByColorId(Long id);

}
