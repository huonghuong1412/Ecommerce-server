package com.example.demo.dto.product;

import com.example.demo.entity.product.Author;

public class AuthorDtoNew {

	private String name;
	private String code;

	public AuthorDtoNew() {
		// TODO Auto-generated constructor stub
	}

	public AuthorDtoNew(Author entity) {
		super();
		this.name = entity.getName();
		this.code = entity.getCode();
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

}
