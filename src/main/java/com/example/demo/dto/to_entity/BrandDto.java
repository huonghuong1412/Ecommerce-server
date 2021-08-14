package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Brand;

public class BrandDto extends AbstractDTO<BrandDto> {

	private String name;
	private String code;
	private String madeIn;
	private String categoryCode;

	public BrandDto() {
		// TODO Auto-generated constructor stub
	}

	public BrandDto(Brand entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.madeIn = entity.getMadeIn();
		this.categoryCode = entity.getCategory().getCode();
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
