package com.example.demo.dto.to_entity;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.user.User;

public class OrderHisDto {

	private Long id;
	private String create_time;
	private Double total_price;
	private List<OrderDetailHisDto> orderDetailDtos;
//	private String user_name;
	private UserDto user;
	private String orderInfo;
	private String address;
	private String username;
	private String phone;
	private Integer status;

	public OrderHisDto() {
	}

//	public OrderHisDto(Long id, String create_time, Double total_price, List<OrderDetailHisDto> orderDetailDtos,
//			String user_name, UserDto user, String orderInfo, Integer status) {
//		this.id = id;
//		this.create_time = create_time;
//		this.total_price = total_price;
//		this.orderDetailDtos = orderDetailDtos;
//		this.user_name = user_name;
//		this.user = user;
//		this.orderInfo = orderInfo;
//		this.status = status;
//	}
//	
	public OrderHisDto(Long id, String create_time, Double total_price, List<OrderDetailHisDto> orderDetailDtos,
			String user_name, UserDto user, String orderInfo, Integer status) {
		this.id = id;
		this.create_time = create_time;
		this.total_price = total_price;
		this.orderDetailDtos = orderDetailDtos;
//		this.user_name = user_name;
		this.user = user;
		this.orderInfo = orderInfo;
		this.status = status;
	}

	public OrderHisDto(Order entity) {
		this.setId(entity.getId());
		this.create_time = entity.getCreate_time();
		this.total_price = entity.getTotal_price();

//		this.user_name = entity.getUser().getUsername();
		this.user = new UserDto();
		if (user != null) {
			User u = entity.getUser();
			user = new UserDto(u);
		}
		this.orderInfo = entity.getOrderInfo();
		this.address = entity.getAddress();
		this.phone = entity.getPhone();
		this.username = entity.getUsername();
		this.status = entity.getStatus();
		this.orderDetailDtos = new ArrayList<OrderDetailHisDto>();
		for (OrderDetail detail : entity.getOrderDetails()) {
			OrderDetailHisDto detailDto = new OrderDetailHisDto(detail);
			this.orderDetailDtos.add(detailDto);
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public List<OrderDetailHisDto> getOrderDetailDtos() {
		return orderDetailDtos;
	}

	public void setOrderDetailDtos(List<OrderDetailHisDto> orderDetailDtos) {
		this.orderDetailDtos = orderDetailDtos;
	}

//	public String getUser_name() {
//		return user_name;
//	}
//
//	public void setUser_name(String user_name) {
//		this.user_name = user_name;
//	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
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

}
