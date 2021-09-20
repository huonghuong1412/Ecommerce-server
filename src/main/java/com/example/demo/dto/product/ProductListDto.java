package com.example.demo.dto.product;

import com.example.demo.common.CalculateDiscount;
import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.category.CategoryDtoNew;
import com.example.demo.dto.category.SubcategoryDtoNew;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Product;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductListDto extends AbstractDTO<ProductListDto> {

	private Integer type;
	private String name;
	private String slug;
	private Long price;
	private Long list_price;
	private String mainImage;
	private CategoryDtoNew category;
	private SubcategoryDtoNew subcategory;
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoNew brand;
	
	private Double percent_discount;

	public ProductListDto() {
		// TODO Auto-generated constructor stub
	}

	public ProductListDto(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.slug = entity.getSlug();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();
		if (this.price != null && this.list_price != null) {
			this.percent_discount = CalculateDiscount.countDiscount(this.price, this.list_price);
		} else {
			this.percent_discount = null;
		}
		this.mainImage = entity.getMainIamge();
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

		this.brand = new BrandDtoNew();
		Brand brandEntity = entity.getBrand();
		this.brand = new BrandDtoNew(brandEntity);

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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getList_price() {
		return list_price;
	}

	public void setList_price(Long list_price) {
		this.list_price = list_price;
	}

	public Double getPercent_discount() {
		return percent_discount;
	}

	public void setPercent_discount(Double percent_discount) {
		this.percent_discount = percent_discount;
	}

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
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

}
