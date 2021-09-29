package com.example.demo.dto.product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Brand;

public class BrandDto extends AbstractDTO<BrandDto> {

	private String name;
	private String code;
	private String madeIn;
	private String categoryCode;
	private Integer display;
	private String createdDate;

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
		this.display = entity.getDisplay();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
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

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
