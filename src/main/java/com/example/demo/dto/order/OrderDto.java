package com.example.demo.dto.order;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;

public class OrderDto extends AbstractDTO<OrderDto> {
	private String create_time;
	private String username;
	private Long total_price;
	private Integer total_item;
	private String orderInfo;
	private String address;
	private String phone;
	private Integer status_order;
	private Integer status_payment;
	private String shipment;
	private PaymentDto payment;
	private List<OrderDetailDto> order_details;

	public OrderDto() {
	}

	public OrderDto(Order entity) {
		this.setId(entity.getId());
		this.create_time = entity.getCreate_time();
		this.total_price = entity.getTotal_price();
		this.total_item = entity.getTotal_item();
		this.username = entity.getUser().getUsername();
		this.orderInfo = entity.getOrderInfo();
		this.status_order = entity.getStatus();
		this.status_payment = entity.getPayment().getStatus();
		this.address = entity.getAddress();
		this.phone = entity.getPhone();
		this.shipment = entity.getShipment().getCode();

		if (payment != null) {
			Payment pay = entity.getPayment();
			this.payment = new PaymentDto(pay);
		}

		order_details = new ArrayList<>();
		for (OrderDetail detail : entity.getOrderDetails()) {
			OrderDetailDto dto = new OrderDetailDto(detail);
			order_details.add(dto);
		}
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public List<OrderDetailDto> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(List<OrderDetailDto> order_details) {
		this.order_details = order_details;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getShipment() {
		return shipment;
	}

	public void setShipment(String shipment) {
		this.shipment = shipment;
	}

}
