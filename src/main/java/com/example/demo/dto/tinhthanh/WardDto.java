package com.example.demo.dto.tinhthanh;

import com.example.demo.entity.tinhthanh.Ward;

public class WardDto {

	private String wardid;
	private String name;
	private String districtid;

	public WardDto() {
		// TODO Auto-generated constructor stub
	}

	public WardDto(Ward entity) {
		super();
		this.wardid = entity.getWardid();
		this.name = entity.getName();
		this.districtid = entity.getDistrictid();
	}

	public String getWardid() {
		return wardid;
	}

	public void setWardid(String wardid) {
		this.wardid = wardid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

}
