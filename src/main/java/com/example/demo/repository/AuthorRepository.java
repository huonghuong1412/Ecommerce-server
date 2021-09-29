package com.example.demo.repository;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
	public Author findOneByCode(String code);

	public Set<Author> findAllByBooks(Book book);
	
	@Query("select count(entity.id) from Author entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
	Long checkCode(String code, Long id);
	
	@Query("select entity from Author entity where entity.display = 1")
	public Page<Author> getList(Pageable pageable);
	
	@Query("select entity from Author entity where entity.display = 0")
	public Page<Author> getListHide(Pageable pageable);
	
}
