package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.ProductInfo;

public class ProductInfoDto extends AbstractDTO<ProductInfoDto> {

	private String attributeName;
	private String attributeValue;

	public ProductInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductInfoDto(ProductInfo entity) {
		super();
		this.setId(entity.getId());
		this.attributeName = entity.getName();
		this.attributeValue = entity.getValue();
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getAttributeValue() {
		return attributeValue;
	}

	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}

}
