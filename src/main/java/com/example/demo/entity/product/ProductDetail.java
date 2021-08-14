package com.example.demo.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_detail")
public class ProductDetail extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "color_id") // 2, 3
	private Color color; // 3,4,1,2

	@Column(name = "quantity")
	private Integer quantity;

	public ProductDetail() {
		super();
	}

	public ProductDetail(Product product, Color color) {
		super();
		this.product = product;
		this.color = color;
	}

	public ProductDetail(Product product, Color color, Integer quantity) {
		super();
		this.product = product;
		this.color = color;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
