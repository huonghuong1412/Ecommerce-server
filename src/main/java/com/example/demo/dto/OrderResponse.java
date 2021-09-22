package com.example.demo.dto;

public class OrderResponse {
	private String message;
	private Long order_id;

	public OrderResponse() {
		super();
	}

	public OrderResponse(String message, Long order_id) {
		super();
		this.message = message;
		this.order_id = order_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

}
