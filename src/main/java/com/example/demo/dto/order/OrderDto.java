package com.example.demo.dto.order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;

public class OrderDto extends AbstractDTO<OrderDto> {
	private String createdDate;
	private String username;
	private Long total_price;
	private Long ship_fee;
	private Integer ship_type;
	private Integer total_item;
	private String orderInfo;
	private String order_code; // mã đơn hàng trên giaohannhanh
	private String address;
	private String ward_code;
	private Integer district_id;
	private String phone;
	private String email;
	private String customer_name;
	private Integer send_status;
	private Integer status_order;
	private Integer status_payment;
//	private String shipment;
	private PaymentDto payment;
	private List<OrderDetailDto> order_details;

	public OrderDto() {
	}

	public OrderDto(Order entity) {
		this.setId(entity.getId());
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy hh:mm").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(entity.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.total_price = entity.getTotal_price();
		this.ship_fee = entity.getShip_fee();
		this.ship_type = entity.getShip_type();
		this.total_item = entity.getTotal_item();
		this.username = entity.getUser().getUsername();
		this.order_code = entity.getOrder_code();
		this.orderInfo = entity.getOrderInfo();
		this.status_order = entity.getStatus();
		this.status_payment = entity.getPayment().getStatus();
		this.address = entity.getAddress();
		this.ward_code = entity.getWard_code();
		this.district_id = entity.getDistrict_id();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.customer_name = entity.getCustomer_name();
		this.send_status = entity.getSend_status();
//		this.shipment = entity.getShipment().getCode();
		this.payment = new PaymentDto();
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

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
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

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public Integer getShip_type() {
		return ship_type;
	}

	public void setShip_type(Integer ship_type) {
		this.ship_type = ship_type;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Integer getSend_status() {
		return send_status;
	}

	public void setSend_status(Integer send_status) {
		this.send_status = send_status;
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

//	public String getShipment() {
//		return shipment;
//	}
//
//	public void setShipment(String shipment) {
//		this.shipment = shipment;
//	}

}
