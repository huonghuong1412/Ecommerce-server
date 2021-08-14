package com.example.demo.entity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.user.User;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tbl_order")
public class Order extends BaseEntity {

	@Column(name = "create_time")
	private String create_time;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;

	@Column(name = "total_price")
	private Double total_price;

	@Column(name = "order_info")
	private String orderInfo;

	@Column(name = "address")
	private String address;

	@Column(name = "user_name")
	private String username;

	@Column(name = "phone")
	private String phone;

	@Column(name = "status")
	private Integer status;

	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order(String create_time, User user, Double total_price, List<OrderDetail> orderDetails) {
		this.create_time = create_time;
		this.user = user;
		this.total_price = total_price;
		this.orderDetails = orderDetails;
	}

	public Order(String create_time, User user, Payment payment, Shipment shipment, Double total_price,
			String orderInfo, String address, String username, String phone, Integer status,
			List<OrderDetail> orderDetails) {
		super();
		this.create_time = create_time;
		this.user = user;
		this.payment = payment;
		this.shipment = shipment;
		this.total_price = total_price;
		this.orderInfo = orderInfo;
		this.address = address;
		this.username = username;
		this.phone = phone;
		this.status = status;
		this.orderDetails = orderDetails;
	}

	public Order() {

	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}

}
