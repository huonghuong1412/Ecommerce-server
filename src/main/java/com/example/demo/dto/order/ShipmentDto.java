package com.example.demo.dto.order;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.order.Shipment;

public class ShipmentDto extends AbstractDTO<ShipmentDto> {

	private Integer type;
	private String name;
	private String code;
	private Double fee;

	public ShipmentDto() {
		// TODO Auto-generated constructor stub
	}

	public ShipmentDto(Shipment entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.code = entity.getCode();
		this.fee = entity.getFee();
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

}
