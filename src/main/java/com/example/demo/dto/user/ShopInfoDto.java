package com.example.demo.dto.user;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.ShopInfo;

public class ShopInfoDto extends AbstractDTO<ShopInfoDto> {
	private String name;
	private String email;
	private String phone;
	private String address;
	private String image;
	private String description;

	public ShopInfoDto() {
		super();
	}

	public ShopInfoDto(ShopInfo s) {
		super();
		this.setId(s.getId());
		this.name = s.getName();
		this.address = s.getAddress();
		this.email = s.getEmail();
		this.phone = s.getPhone();
		this.image = s.getImage();
		this.description = s.getDescription();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
