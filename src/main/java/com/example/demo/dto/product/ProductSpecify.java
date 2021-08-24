package com.example.demo.dto.product;

public class ProductSpecify {

	private String attributeName;
	private String attributeValue;

	public ProductSpecify() {
		// TODO Auto-generated constructor stub
	}

	public ProductSpecify(String attributeName, String attributeValue) {
		super();
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
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
