package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Book;
import com.example.demo.entity.product.Product;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//	@Query(value = "select * from com.example.demo.entity.product.Book b where b.product.id = ?1", nativeQuery = true)
	public Book findOneByProduct(Product product);
	
}
