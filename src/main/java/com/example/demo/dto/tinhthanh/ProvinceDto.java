package com.example.demo.dto.tinhthanh;

import com.example.demo.entity.tinhthanh.Province;

public class ProvinceDto {

	private String provinceid;
	private String name;

	public ProvinceDto(Province entity) {
		super();
		this.provinceid = entity.getProvinceid();
		this.name = entity.getName();
	}

	public String getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(String provinceid) {
		this.provinceid = provinceid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
