package com.example.demo.dto.auth;

import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.User;

public class RegisterDto extends AbstractDTO<RegisterDto> {
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String phone;
//	private String city;
	private String dateOfBirth;
//	private String district;
//	private String ward;

	private Set<String> role;

	public RegisterDto() {
		super();
	}

	public RegisterDto(User entity) {
		super();
		this.setId(entity.getId());
		this.username = entity.getUsername();
		this.password = entity.getPassword();
		this.phone = entity.getPhone();
		this.email = entity.getEmail();
		this.firstName = entity.getFullname().getFirstName();
		this.lastName = entity.getFullname().getLastName();
		this.dateOfBirth = entity.getDateOfBirth();
//		this.city = entity.getAddress().getCity();
//		this.district = entity.getAddress().getDistrict();
//		this.ward = entity.getAddress().getWard();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

//	public String getCity() {
//		return city;
//	}
//
//	public void setCity(String city) {
//		this.city = city;
//	}
//
//	public String getDistrict() {
//		return district;
//	}
//
//	public void setDistrict(String district) {
//		this.district = district;
//	}
//
//	public String getWard() {
//		return ward;
//	}
//
//	public void setWard(String ward) {
//		this.ward = ward;
//	}

}
