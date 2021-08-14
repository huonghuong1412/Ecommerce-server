package com.example.demo.dto.to_show;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.to_entity.ImageDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Publisher;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductListDto extends AbstractDTO<ProductListDto> {

	private Integer type;
	private String name;
	private String slug;
	private String description;
	private Long price;
	private List<String> images;
	private CategoryDtoNew category;
	private SubcategoryDtoNew subcategory;
	
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoNew brand;
	
	@JsonInclude(value = Include.NON_NULL)
	private PublisherDtoNew publisher;

	public ProductListDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductListDto(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		category = new CategoryDtoNew();
		if (category != null) {
			Category item = entity.getCategory();
			this.category = new CategoryDtoNew(item);
		}
		subcategory = new SubcategoryDtoNew();
		if (subcategory != null) {
			SubCategory item = entity.getSubcategory();
			this.subcategory = new SubcategoryDtoNew(item);
		}
		images = new ArrayList<>();
		for (Image image : entity.getImages()) {
			ImageDto dto = new ImageDto(image);
			images.add(dto.getUrl());
		}

		if (this.type == 1) {
			publisher = new PublisherDtoNew();
			Publisher publisher = entity.getPublisher();
			this.publisher = new PublisherDtoNew(publisher);
		}
		if (this.type == 2 || this.type == 3) {
			brand = new BrandDtoNew();
//			if (brand != null) {
			Brand brandEntity = entity.getBrand();
			this.brand = new BrandDtoNew(brandEntity);
//			}
		}

	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSlug() {
		return slug;
	}

	public void setSlug(String slug) {
		this.slug = slug;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public CategoryDtoNew getCategory() {
		return category;
	}

	public void setCategory(CategoryDtoNew category) {
		this.category = category;
	}

	public SubcategoryDtoNew getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubcategoryDtoNew subcategory) {
		this.subcategory = subcategory;
	}

	public BrandDtoNew getBrand() {
		return brand;
	}

	public void setBrand(BrandDtoNew brand) {
		this.brand = brand;
	}

	public PublisherDtoNew getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherDtoNew publisher) {
		this.publisher = publisher;
	}

}
