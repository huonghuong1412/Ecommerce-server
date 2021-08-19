package com.example.demo.dto.to_show;

import com.example.demo.entity.product.ProductInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductInforNew {

	@JsonInclude(value = Include.NON_NULL)
	private String attributeName;
	
	@JsonInclude(value = Include.NON_NULL)
	private String attributeValue;

	public ProductInforNew() {
		// TODO Auto-generated constructor stub
	}

	public ProductInforNew(ProductInfo entity) {
		super();
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
