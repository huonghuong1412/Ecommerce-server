package com.example.demo.dto.GHTK;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private OrderInfo order;
	private List<ProductItem> products = new ArrayList<>();

	public Order() {
		super();
	}

	public OrderInfo getOrder() {
		return order;
	}

	public void setOrder(OrderInfo order) {
		this.order = order;
	}

	public List<ProductItem> getProducts() {
		return products;
	}

	public void setProducts(List<ProductItem> products) {
		this.products = products;
	}

}
