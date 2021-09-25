package com.example.demo.dto.order;

import com.example.demo.entity.order.Order;

public class OrderHisInfoDto {

	private String create_time;
	private Long total_price;
	private Integer total_item;
	private String orderInfo;
	private String address;

	private Integer status_order;
	private String status_order_name;
	private Integer status_payment;
	private String status_payment_name;

	private String shipment_name;
	private Double shipment_fee;

	private String payment_method;

	public OrderHisInfoDto() {
		// TODO Auto-generated constructor stub
	}

	public OrderHisInfoDto(Order entity) {
		// TODO Auto-generated constructor stub
		this.create_time = entity.getCreate_time();
		this.total_price = entity.getTotal_price();
		this.total_item = entity.getTotal_item();
		this.orderInfo = entity.getOrderInfo();
		this.address = entity.getAddress();
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

		this.payment_method = entity.getPayment().getMethod().getName();
		this.shipment_name = entity.getShipment().getName();
		this.shipment_fee = entity.getShipment().getFee();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public Integer getStatus_order() {
		return status_order;
	}

	public void setStatus_order(Integer status_order) {
		this.status_order = status_order;
	}

	public String getStatus_order_name() {
		return status_order_name;
	}

	public void setStatus_order_name(String status_order_name) {
		this.status_order_name = status_order_name;
	}

	public Integer getStatus_payment() {
		return status_payment;
	}

	public void setStatus_payment(Integer status_payment) {
		this.status_payment = status_payment;
	}

	public String getStatus_payment_name() {
		return status_payment_name;
	}

	public void setStatus_payment_name(String status_payment_name) {
		this.status_payment_name = status_payment_name;
	}

	public String getPayment_method() {
		return payment_method;
	}

	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}

	public String getShipment_name() {
		return shipment_name;
	}

	public void setShipment_name(String shipment_name) {
		this.shipment_name = shipment_name;
	}

	public Double getShipment_fee() {
		return shipment_fee;
	}

	public void setShipment_fee(Double shipment_fee) {
		this.shipment_fee = shipment_fee;
	}

}
