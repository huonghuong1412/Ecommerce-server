package com.example.demo.dto.tinhthanh;

import com.example.demo.entity.tinhthanh.District;

public class DistrictDto {

	private String districtid;
	private String name;
	private String provinceid;

	public DistrictDto() {
		// TODO Auto-generated constructor stub
	}

	public DistrictDto(District entity) {
		super();
		this.districtid = entity.getDistrictid();
		this.name = entity.getName();
		this.provinceid = entity.getProvinceid();
	}

	public String getDistrictid() {
		return districtid;
	}

	public void setDistrictid(String districtid) {
		this.districtid = districtid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

}
