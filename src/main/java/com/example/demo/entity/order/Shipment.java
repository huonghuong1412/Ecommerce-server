//package com.example.demo.entity.order;
//
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
//import com.example.demo.entity.BaseEntity;
//
//@Entity
//@Table(name = "tbl_shipment")
//public class Shipment extends BaseEntity {
//
//	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Order> orders;
//
//	@Column(name = "type")
//	private Integer type;
//
//	@Column(name = "name")
//	private String name; // 1 giao hang nhanh, 2 giao hang tiet kiem
//
//	@Column(name = "code")
//	private String code;
//
//	@Column(name = "fee")
//	private Double fee;
//
//	@Column(name = "display")
//	private Integer display; // 1 : show, 0: hidden
//
//	public Shipment() {
//		super();
//	}
//
//	public Shipment(Integer type, Double fee) {
//		super();
//		this.type = type;
//		this.fee = fee;
//	}
//
//	public Shipment(List<Order> orders, Integer type, String name, String code, Double fee) {
//		super();
//		this.orders = orders;
//		this.type = type;
//		this.name = name;
//		this.code = code;
//		this.fee = fee;
//	}
//
//	public Integer getType() {
//		return type;
//	}
//
//	public void setType(Integer type) {
//		this.type = type;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public Double getFee() {
//		return fee;
//	}
//
//	public void setFee(Double fee) {
//		this.fee = fee;
//	}
//
//	public Integer getDisplay() {
//		return display;
//	}
//
//	public void setDisplay(Integer display) {
//		this.display = display;
//	}
//
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}
//
//}
