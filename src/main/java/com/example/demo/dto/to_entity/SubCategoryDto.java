package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.SubCategory;

public class SubCategoryDto extends AbstractDTO<SubCategoryDto> {
	private String name;
	private String code;
	private String categoryCode;

	public SubCategoryDto() {
		super();
	}

	public SubCategoryDto(SubCategory entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
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

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

}
