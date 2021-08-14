package com.example.demo.dto.to_entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.Shipment;

public class OrderDto extends AbstractDTO<OrderDto> {
	private String create_time;
	private Double total_price;
	private List<OrderDetailDto> orderDetailDtos;
	private String username;
	private String orderInfo;
	private String address;
	private String name;
	private String phone;
	private Integer status;
	private ShipmentDto shipment;
	private PaymentDto payment;

	public OrderDto() {
	}

	public OrderDto(Order entity) {
		this.setId(entity.getId());
		this.create_time = entity.getCreate_time();
		this.total_price = entity.getTotal_price();
		this.username = entity.getUser().getUsername();
		this.orderInfo = entity.getOrderInfo();
		this.status = entity.getStatus();
		this.address = entity.getAddress();
		this.name = entity.getUsername();
		this.phone = entity.getPhone();
		this.shipment = new ShipmentDto();
		if (shipment != null) {
			Shipment ship = entity.getShipment();
			this.shipment = new ShipmentDto(ship);
		}

		if (payment != null) {
			Payment pay = entity.getPayment();
			this.payment = new PaymentDto(pay);
		}

		orderDetailDtos = new ArrayList<>();
		for (OrderDetail detail : entity.getOrderDetails()) {
			OrderDetailDto dto = new OrderDetailDto(detail);
			orderDetailDtos.add(dto);
		}
	}

//	public Order toEntity(User user) {
//		Order o = new Order();
//		o.setAddress("");
//
////		String date = new Timestamp(new Date().getTime()).toString();
//
//		o.setCreate_time(this.getCreate_time());
//		o.setTotal_price(this.getTotal_price());
//		o.setUser(user);
//		o.setOrderInfo(this.getOrderInfo());
//		o.setStatus(1);
//		o.setShipment(this.getShipment());
//		return o;
//	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public Double getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Double total_price) {
		this.total_price = total_price;
	}

	public List<OrderDetailDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}

	public void setOrderDetailDtos(List<OrderDetailDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public ShipmentDto getShipment() {
		return shipment;
	}

	public void setShipment(ShipmentDto shipment) {
		this.shipment = shipment;
	}

}
