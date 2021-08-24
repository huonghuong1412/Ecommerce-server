package com.example.demo.dto.category;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.category.SubCategory;

public class SubCategoryDto extends AbstractDTO<SubCategoryDto> {
	private String name;
	private String code;
	private String categoryCode;
	private String category_name;

	public SubCategoryDto() {
		super();
	}

	public SubCategoryDto(SubCategory entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.categoryCode = entity.getCategory().getCode();
		this.category_name = entity.getCategory().getName();
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

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

}
