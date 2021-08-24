package com.example.demo.entity.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_publisher")
public class Publisher extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@JsonIgnore
	@OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL)
	private Set<Book> products;

	public Publisher() {
		super();
	}

	public Publisher(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public Publisher(String name, Set<Product> products) {
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

	public Set<Book> getProducts() {
		return products;
	}

	public void setProducts(Set<Book> products) {
		this.products = products;
	}

}
