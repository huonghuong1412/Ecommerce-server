package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto extends AbstractDTO<UserDto> {
	private String username;
	private String email;
	private String fullName;
	private String phone;
	private String dateOfBirth;

	@JsonIgnore
	private UserAddressDto address;

	private String city;
	private String district;
	private String ward;
	private String house;

	public UserDto() {
		super();
	}

	public UserDto(User user) {
		super();
		this.setId(user.getId());
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.fullName = user.getFullname();
		this.phone = user.getPhone();
		this.dateOfBirth = user.getDateOfBirth();
//		this.city = user.getAddress().getCity();
//		this.district = user.getAddress().getDistrict();
//		this.ward = user.getAddress().getWard();
		this.address = new UserAddressDto();
		if (address != null) {
			this.address = new UserAddressDto(user.getAddress());
			this.city = this.address.getCity();
			this.district = this.address.getDistrict();
			this.ward = this.address.getWard();
			this.house = this.address.getHouse();
		}
	}

	public UserAddressDto getAddress() {
		return address;
	}

	public void setAddress(UserAddressDto address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
