package com.example.demo.entity.category;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_slide")
public class Slide extends BaseEntity {

	@Column(name = "url")
	private String url;

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	public Slide() {
		// TODO Auto-generated constructor stub
	}

	public Slide(String url) {
		super();
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
	}

}
