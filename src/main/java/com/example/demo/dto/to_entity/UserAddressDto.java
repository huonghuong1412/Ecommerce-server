package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.Address;

public class UserAddressDto extends AbstractDTO<UserAddressDto> {

	private String username;
	private String city;
	private String district;
	private String ward;
	private String house;

	public UserAddressDto() {
		// TODO Auto-generated constructor stub
	}

	public UserAddressDto(Address entity) {
		super();
		this.setId(entity.getId());
		this.username = entity.getUser().getUsername();
		this.city = entity.getCity();
		this.district = entity.getDistrict();
		this.ward = entity.getWard();
		this.house = entity.getHouse();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

}
