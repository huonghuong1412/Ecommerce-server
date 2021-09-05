package com.example.demo.dto.order;

import com.example.demo.entity.order.OrderDetail;

public class OrderDetailHisDto {
	private Long order_id;
	private Long product_id;
	private String product_name;
	private String category;
	private String sub_category;
	private String mainImage;
	private Integer amount_item;
	private Long price_item;
	private Long total_price;

	public OrderDetailHisDto(OrderDetail entity) {
		this.order_id = entity.getOrder().getId();
		this.product_id = entity.getProduct().getId();
		this.product_name = entity.getProduct().getName();
		this.category = entity.getProduct().getCategory().getName();
		this.sub_category = entity.getProduct().getSubcategory().getName();
		this.amount_item = entity.getAmount();
		this.price_item = entity.getPrice();
		this.mainImage = entity.getProduct().getMainIamge();
		this.total_price = entity.getTotal_price();
	}

	public OrderDetailHisDto() {
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Integer getAmount_item() {
		return amount_item;
	}

	public void setAmount_item(Integer amount_item) {
		this.amount_item = amount_item;
	}

	public Long getPrice_item() {
		return price_item;
	}

	public void setPrice_item(Long price_item) {
		this.price_item = price_item;
	}

}