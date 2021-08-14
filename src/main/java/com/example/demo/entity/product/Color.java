package com.example.demo.entity.product;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.example.demo.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_color")
public class Color extends BaseEntity {

	// bang nay luu cac thong tin cua san pham nhu mau sac, kich thươc, kieu dang

	@Column(name = "color")
	private String color;

//	@Column(name = "size")
//	private String size;

//	@Column(name = "style")
//	private String style;

	@JsonIgnore
	@OneToMany(mappedBy = "color", cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private Set<ProductDetail> details; // 3,4,1,2

	public Color() {
		// TODO Auto-generated constructor stub
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<ProductDetail> getDetails() {
		return details;
	}

	public void setDetails(Set<ProductDetail> details) {
		this.details = details;
	}

}
