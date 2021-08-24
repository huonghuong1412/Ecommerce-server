package com.example.demo.entity.product;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_author")
public class Author extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@ManyToMany(mappedBy = "authors", cascade = CascadeType.ALL)
	private List<Book> books;

	public Author() {
		super();
	}

	public Author(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Author(String name, List<Product> products) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
