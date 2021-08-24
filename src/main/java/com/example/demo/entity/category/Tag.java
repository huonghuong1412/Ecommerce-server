package com.example.demo.entity.category;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.product.Product;

@Entity
@Table(name = "tbl_tag")
public class Tag extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
	private List<Product> products; // 1

	public Tag() {
		super();
	}

	public Tag(String name) {
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
