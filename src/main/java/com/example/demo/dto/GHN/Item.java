package com.example.demo.dto.GHN;

public class Item {
	private String name;
	private String code;
	private Integer quantity;
	private Long price;

	public Item() {
		super();
	}

	public Item(String name, String code, Integer quantity, Long price) {
		super();
		this.name = name;
		this.code = code;
		this.quantity = quantity;
		this.price = price;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

}
