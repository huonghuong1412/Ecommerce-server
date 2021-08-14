package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_slide")
public class Slide extends BaseEntity {

	@Column(name = "url")
	private String url;

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

}
