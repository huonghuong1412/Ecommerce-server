package com.example.demo.dto.product;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Publisher;

public class PublisherDto extends AbstractDTO<PublisherDto> {

	private String name;
	private String code;

	public PublisherDto() {
		// TODO Auto-generated constructor stub
	}

	public PublisherDto(Publisher entity) {
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
