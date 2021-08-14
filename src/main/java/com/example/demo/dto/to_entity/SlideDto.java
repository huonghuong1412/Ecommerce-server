package com.example.demo.dto.to_entity;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.Slide;

public class SlideDto extends AbstractDTO<SlideDto> {

	private String url;

	public SlideDto() {
		// TODO Auto-generated constructor stub
	}

	public SlideDto(Slide entity) {
		super();
		this.setId(entity.getId());
		this.url = entity.getUrl();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
