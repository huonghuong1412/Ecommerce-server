package com.example.demo.dto.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.entity.order.Order;

public class OrderHisDto {
	private Long id;
	private String order_code;
	private String description;
	private String createdDate;
	private Long total_price;
	private Long ship_fee;
	private Integer total_item;
	private String orderInfo;
	private String address;
	private String ward_code;
	private Integer district_id;
	private String username;
	private String user_fullname;
	private String phone;
	private Integer status_order;
	private String status_order_name;
	private Integer status_payment;
	private String status_payment_name;
	private String ship_name;

	public OrderHisDto() {
	}

	public OrderHisDto(Order entity) {
		this.setId(entity.getId());
		this.order_code = entity.getOrder_code();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.total_price = entity.getTotal_price();
		this.ship_fee = entity.getShip_fee();
		this.total_item = entity.getTotal_item();
		this.orderInfo = entity.getOrderInfo();
		this.address = entity.getAddress();
		this.district_id = entity.getDistrict_id();
		this.ward_code = entity.getWard_code();
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
			this.status_payment_name = "Huỷ thanh toán";
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

		Integer ship_type = entity.getShip_type();
		switch (ship_type) {
		case 1:
			this.ship_name = "Giao hàng nhanh";
			break;
		case 2:
			this.ship_name = "Shop giao hàng";
		default:
			break;
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Long getShip_fee() {
		return ship_fee;
	}

	public void setShip_fee(Long ship_fee) {
		this.ship_fee = ship_fee;
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

	public String getWard_code() {
		return ward_code;
	}

	public void setWard_code(String ward_code) {
		this.ward_code = ward_code;
	}

	public Integer getDistrict_id() {
		return district_id;
	}

	public void setDistrict_id(Integer district_id) {
		this.district_id = district_id;
	}

	public String getShip_name() {
		return ship_name;
	}

	public void setShip_name(String ship_name) {
		this.ship_name = ship_name;
	}

}
