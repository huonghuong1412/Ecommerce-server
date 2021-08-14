package com.example.demo.dto.to_show;

import com.example.demo.entity.SubCategory;

public class SubcategoryDtoNew {

	private String name;
	private String code;
	private String categoryCode;

	public SubcategoryDtoNew() {
		super();
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

	public SubcategoryDtoNew(SubCategory entity) {
		this.name = entity.getName();
		this.code = entity.getCode();
		this.categoryCode = entity.getCategory().getCode();
	}

}
