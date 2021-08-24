package com.example.demo.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_address")
public class ShipAddress extends BaseEntity {

	@Column(name = "city")
	private String city;

	@Column(name = "district")
	private String district;

	@Column(name = "ward")
	private String ward;

	@Column(name = "house")
	private String house;

	@OneToOne
	@MapsId
	@JoinColumn(name = "user_id")
	private User user;

	public ShipAddress() {
	}

	public ShipAddress(String city, String district, String ward, String house) {
		super();
		this.city = city;
		this.district = district;
		this.ward = ward;
		this.house = house;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
