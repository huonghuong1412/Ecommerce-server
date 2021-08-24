package com.example.demo.dto.inventory;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.Supplier;

public class SupplierDto extends AbstractDTO<SupplierDto> {

	private String name;
	private String code;
	private String email;
	private String phone;
	private String address;

	public SupplierDto() {
		// TODO Auto-generated constructor stub
	}

	public SupplierDto(Supplier entity) {
		super();
		this.setId(entity.getId());
		this.name = entity.getName();
		this.code = entity.getCode();
		this.email = entity.getEmail();
		this.phone = entity.getPhone();
		this.address = entity.getAddress();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
