package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.ProductDetail;

public class ProductDetailDto extends AbstractDTO<ProductDetailDto> {
	// info
	private ColorDto color;
	private Integer quantity;

	public ProductDetailDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductDetailDto(ProductDetail entity) {
		super();
		this.setId(entity.getId());
		this.quantity = entity.getQuantity();
		this.color = new ColorDto(entity.getColor());
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ColorDto getColor() {
		return color;
	}

	public void setColor(ColorDto color) {
		this.color = color;
	}

}
