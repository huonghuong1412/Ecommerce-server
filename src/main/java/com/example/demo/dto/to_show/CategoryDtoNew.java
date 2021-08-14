package com.example.demo.dto.to_show;

import com.example.demo.entity.Category;

public class CategoryDtoNew {

	private String name;
	private String code;
	
	public CategoryDtoNew() {
		// TODO Auto-generated constructor stub
	}
	
	

	public CategoryDtoNew(Category entity) {
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
