package com.example.demo.dto.product;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Author;

public class AuthorDto extends AbstractDTO<AuthorDto> {

	private String name;
	private String code;

	public AuthorDto() {
		// TODO Auto-generated constructor stub
	}

	public AuthorDto(Author entity) {
		super();
		this.setId(entity.getId());
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
