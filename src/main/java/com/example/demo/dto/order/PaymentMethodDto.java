package com.example.demo.dto.order;

import java.sql.Timestamp;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.PaymentMethod;

public class PaymentMethodDto extends AbstractDTO<PaymentMethodDto> {

	private String name;
	private String code;
	private String icon;
	private Integer type;

	public PaymentMethodDto() {
		// TODO Auto-generated constructor stub
	}

	public PaymentMethodDto(PaymentMethod entity) {
		super();
		this.setId(entity.getId());
		this.setCreatedDate(new Timestamp(new Date().getTime()).toString());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.icon = entity.getIcon();
		this.type = entity.getType();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
