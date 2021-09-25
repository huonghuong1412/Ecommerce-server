package com.example.demo.dto.order;

import com.example.demo.entity.order.Order;

public class OrderHisDto {
	private Long id;
	private String description;
	private String create_time;
	private Long total_price;
	private Integer total_item;
	private String orderInfo;
	private String address;
	private String username;
	private String user_fullname;
	private String phone;
	private Integer status_order;
	private String status_order_name;
	private Integer status_payment;
	private String status_payment_name;

	public OrderHisDto() {
	}

	public OrderHisDto(Order entity) {
		this.setId(entity.getId());
		this.create_time = entity.getCreate_time();
		this.total_price = entity.getTotal_price();
		this.total_item = entity.getTotal_item();
		this.orderInfo = entity.getOrderInfo();
		this.address = entity.getAddress();
		this.phone = entity.getPhone();
		this.username = entity.getUser().getUsername();
		this.user_fullname = entity.getUser().getFullname();
		this.status_order = entity.getStatus();
		this.status_payment = entity.getPayment().getStatus();
		switch (this.status_order) {
		case -1:
			this.status_order_name = "Đã huỷ đơn";
			break;
		case 0:
			this.status_order_name = "Đang chờ xác nhận";
			break;
		case 1:
			this.status_order_name = "Đang giao hàng";
			break;
		case 2:
			this.status_order_name = "Đã nhận hàng";
			break;
		default:
			this.status_order_name = "Đang chờ xác nhận";
			break;
		}

		switch (this.status_payment) {
		case -1:
			this.status_payment_name = "Đã huỷ thanh toán";
			break;
		case 0:
			this.status_payment_name = "Chưa thanh toán";
			break;
		case 1:
			this.status_payment_name = "Đã thanh toán";
			break;
		case 2:
			this.status_payment_name = "Đã hoàn tiền";
			break;
		default:
			this.status_payment_name = "Chưa thanh toán";
			break;
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

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

	public Integer getStatus_order() {
		return status_order;
	}

	public void setStatus_order(Integer status_order) {
		this.status_order = status_order;
	}

	public Integer getStatus_payment() {
		return status_payment;
	}

	public void setStatus_payment(Integer status_payment) {
		this.status_payment = status_payment;
	}

	public String getUser_fullname() {
		return user_fullname;
	}

	public void setUser_fullname(String user_fullname) {
		this.user_fullname = user_fullname;
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

	public String getStatus_order_name() {
		return status_order_name;
	}

	public void setStatus_order_name(String status_order_name) {
		this.status_order_name = status_order_name;
	}

	public String getStatus_payment_name() {
		return status_payment_name;
	}

	public void setStatus_payment_name(String status_payment_name) {
		this.status_payment_name = status_payment_name;
	}

}
