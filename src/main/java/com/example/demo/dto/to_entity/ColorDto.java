package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Color;

public class ColorDto extends AbstractDTO<ColorDto> {

	private String color;

	public ColorDto() {
		// TODO Auto-generated constructor stub
	}

	public ColorDto(Color entity) {
		super();
		this.setId(entity.getId());
		this.color = entity.getColor();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

}
