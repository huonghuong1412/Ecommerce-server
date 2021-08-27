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
import com.example.demo.entity.user.Store;
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

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "store_id")
//	private Store store;

	@OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
	private Payment payment;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "shipment_id")
//	private Shipment shipment;

	@Column(name = "total_price")
	private Long total_price;

	@Column(name = "total_item")
	private Integer total_item;

	@Column(name = "order_info")
	private String orderInfo;

	@Column(name = "address")
	private String address;

	@Column(name = "user_name")
	private String user_fullname;

	@Column(name = "phone")
	private String phone;

	@Column(name = "status")
	private Integer status;

	@JsonManagedReference
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderDetail> orderDetails = new ArrayList<>();

	public Order() {
		super();
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

//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Integer getTotal_item() {
		return total_item;
	}

	public void setTotal_item(Integer total_item) {
		this.total_item = total_item;
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

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
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

//	public Shipment getShipment() {
//		return shipment;
//	}
//
//	public void setShipment(Shipment shipment) {
//		this.shipment = shipment;
//	}

}
