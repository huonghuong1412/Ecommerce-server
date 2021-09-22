package com.example.demo.dto;

public class CartResponse {

	private String message;
	private Integer items_quantity;
	private Integer items_count;

	public CartResponse() {
		// TODO Auto-generated constructor stub
	}

	public CartResponse(String message) {
		super();
		this.message = message;
	}

	public CartResponse(String message, Integer items_quantity, Integer items_count) {
		super();
		this.message = message;
		this.items_quantity = items_quantity;
		this.items_count = items_count;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getItems_quantity() {
		return items_quantity;
	}

	public void setItems_quantity(Integer items_quantity) {
		this.items_quantity = items_quantity;
	}

	public Integer getItems_count() {
		return items_count;
	}

	public void setItems_count(Integer items_count) {
		this.items_count = items_count;
	}

}
