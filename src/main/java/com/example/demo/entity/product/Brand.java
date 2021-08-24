package com.example.demo.entity.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_brand")
public class Brand extends BaseEntity {

	@Column(name = "name")
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "made_in")
	private String madeIn;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonIgnore
	@OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
	private Set<Product> products;

	public Brand() {
		super();
	}

	public Brand(String name) {
		super();
		this.name = name;
	}

	public Brand(String name, Set<Product> products) {
		super();
		this.name = name;
		this.products = products;
	}

	public Brand(String name, String code, String madeIn) {
		super();
		this.name = name;
		this.code = code;
		this.madeIn = madeIn;
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

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

}
