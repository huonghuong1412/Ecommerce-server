package com.example.demo.dto.GHTK;

public class ProductItem {
	private String name;
	private Double weight;
	private Integer quantity;
	private Integer product_code;

	public ProductItem() {
		super();
	}

	public ProductItem(String name, Double weight, Integer quantity) {
		super();
		this.name = name;
		this.weight = weight;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProduct_code() {
		return product_code;
	}

	public void setProduct_code(Integer product_code) {
		this.product_code = product_code;
	}

}
