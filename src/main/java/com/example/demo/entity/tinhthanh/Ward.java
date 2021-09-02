package com.example.demo.entity.tinhthanh;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ward")
public class Ward {

	@Id
	@Column(name = "wardid")
	private String wardid;

	@Column(name = "name")
	private String name;

//	@Column(name = "districtid")
//	private String districtid;
	@ManyToOne
	@JoinColumn(name = "districtid")
	private District district;

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

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

}
