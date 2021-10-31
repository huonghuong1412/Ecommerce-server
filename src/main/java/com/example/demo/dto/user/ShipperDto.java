package com.example.demo.dto.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.user.Shipper;

public class ShipperDto extends AbstractDTO<ShipperDto> {

	private String cccd;
	private String shift;
	private Long salary;
	private String createdDate;

	public ShipperDto() {
		super();
	}

	public ShipperDto(Shipper s) {
		super();
		this.setId(s.getId());
		this.cccd = s.getCccd();
		this.shift = s.getShift();
		this.salary = s.getSalary();
		try {
			this.createdDate = new SimpleDateFormat("dd/MM/yyyy").format(
					new Date(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(s.getCreatedDate()).getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

}
