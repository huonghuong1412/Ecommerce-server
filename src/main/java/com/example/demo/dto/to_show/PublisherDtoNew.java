package com.example.demo.dto.to_show;

import com.example.demo.entity.product.Publisher;

public class PublisherDtoNew {

	private String name;
	private String code;

	public PublisherDtoNew() {
		// TODO Auto-generated constructor stub
	}

	public PublisherDtoNew(Publisher entity) {
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
