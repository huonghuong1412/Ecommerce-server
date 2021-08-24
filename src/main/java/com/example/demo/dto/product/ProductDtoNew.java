package com.example.demo.dto.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.category.CategoryDtoNew;
import com.example.demo.dto.category.SubcategoryDtoNew;
import com.example.demo.dto.category.TagDto;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.category.Tag;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Technology;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductDtoNew extends AbstractDTO<ProductDtoNew> {

	private Integer type; // loai san phamr

	@JsonInclude(value = Include.NON_NULL)
	private String name;

	@JsonInclude(value = Include.NON_NULL)
	private String sku;

	@JsonInclude(value = Include.NON_NULL)
	private String slug;

	@JsonInclude(value = Include.NON_NULL)
	private String description;

	@JsonInclude(value = Include.NON_NULL)
	private Long price;

	@JsonInclude(value = Include.NON_NULL)
	private Long list_price;

	@JsonInclude(value = Include.NON_NULL)
	private Double percent_discount;

	@JsonInclude(value = Include.NON_NULL)
	private String mainImage;

	@JsonInclude(value = Include.NON_NULL)
	private Integer in_stock;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<String> images;

	@JsonInclude(value = Include.NON_NULL)
	private CategoryDtoNew category;

	@JsonInclude(value = Include.NON_NULL)
	private SubcategoryDtoNew subcategory;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<ProductSpecify> product_specs;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<TagDto> tags;

	// brand
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoNew brand;

	// ----------- BOOK -------------------
	@JsonInclude(value = Include.NON_NULL)
	private BookDto book;

//	@JsonInclude(value = Include.NON_NULL)
//	private Integer publishingYear;

//	@JsonInclude(value = Include.NON_NULL)
//	private Integer numberOfPages;

	@JsonInclude(value = Include.NON_NULL)
	private Set<AuthorDtoNew> authors;

	@JsonInclude(value = Include.NON_NULL)
	private PublisherDtoNew publisher;

	// ---------------- Technology --------------
	@JsonInclude(value = Include.NON_NULL)
	private TechDto technology;

//	@JsonInclude(value = Include.NON_NULL)
//	private String screen;

//	@JsonInclude(value = Include.NON_NULL)
//	private String operatorSystem;

//	@JsonInclude(value = Include.NON_NULL)
//	private String ram;

//	@JsonInclude(value = Include.NON_NULL)
//	private String pin;

//	@JsonInclude(value = Include.NON_NULL)
//	private String design;

//	@JsonInclude(value = Include.NON_NULL)
//	private String sizeWeight;

//	@JsonInclude(value = Include.NON_NULL)
//	private String material;

//	@JsonInclude(value = Include.NON_NULL)
//	private String releaseTime;

	// phone
//	@JsonInclude(value = Include.NON_NULL)
//	private String frontCamera;

//	@JsonInclude(value = Include.NON_NULL)
//	private String behindCamera;

//	@JsonInclude(value = Include.NON_NULL)
//	private String chip;

//	@JsonInclude(value = Include.NON_NULL)
//	private String internalMemory;

//	@JsonInclude(value = Include.NON_NULL)
//	private String sim;

	// laptop
//	@JsonInclude(value = Include.NON_NULL)
//	private String cpu;

//	@JsonInclude(value = Include.NON_NULL)
//	private String hardWare;

//	@JsonInclude(value = Include.NON_NULL)
//	private String card;

//	@JsonInclude(value = Include.NON_NULL)
//	private String special;

	public ProductDtoNew() {
		// TODO Auto-generated constructor stub
	}

	public ProductDtoNew(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.sku = entity.getSku();
		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();

		if (this.price != null && this.list_price != null) {

			Double percent = (this.list_price.doubleValue() - this.price.doubleValue()) / this.list_price.doubleValue()
					* 100;

			this.percent_discount = (double) Math.round(percent * 10) / 10;
		} else {
			this.percent_discount = null;
		}

		this.mainImage = entity.getMainIamge();

		if (entity.getInventory() != null) {
			this.in_stock = entity.getInventory().getQuantity_item();
		}

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

		this.tags = new ArrayList<TagDto>();
		for (Tag tag : entity.getTags()) {
			TagDto dto = new TagDto(tag);
			this.tags.add(dto);
		}

		this.product_specs = new ArrayList<>();

		// sach
		switch (this.type) {
		case 1:
			// sach
			this.book = new BookDto();
			if (this.book != null) {
				this.authors = new HashSet<>();
				for (Author author : entity.getBook().getAuthors()) {
					AuthorDtoNew dto = new AuthorDtoNew(author);
					authors.add(dto);
				}
				this.publisher = new PublisherDtoNew(entity.getBook().getPublisher());
				this.product_specs.add(new ProductSpecify("Số trang", entity.getBook().getNumberOfPages().toString()));
				this.product_specs
						.add(new ProductSpecify("Năm xuất bản", entity.getBook().getPublishingYear().toString()));
				this.product_specs.add(new ProductSpecify("Nhà xuất bản", this.publisher.getName()));
			}
			break;
		case 2:
			// technology
			this.technology = new TechDto();
			if (this.technology != null) {
				Technology item = entity.getTechnology();
				this.product_specs.add(new ProductSpecify("Màn hình", item.getScreen()));
				this.product_specs.add(new ProductSpecify("Hệ điều hành", item.getOperatorSystem()));
				this.product_specs.add(new ProductSpecify("RAM", item.getRam()));
				this.product_specs.add(new ProductSpecify("PIN", item.getPin()));
				this.product_specs.add(new ProductSpecify("Thiết kế", item.getDesign()));
				this.product_specs.add(new ProductSpecify("Trọng lượng & Kích thước", item.getSizeWeight()));
				this.product_specs.add(new ProductSpecify("Thành phần", item.getMaterial()));
				this.product_specs.add(new ProductSpecify("Thời điểm ra mắt", item.getReleaseTime()));
				if(this.category.getCode().equalsIgnoreCase("dien-thoai")) {
					this.product_specs.add(new ProductSpecify("Camera trước", item.getFrontCamera()));
					this.product_specs.add(new ProductSpecify("Camera sau", item.getBehindCamera()));
					this.product_specs.add(new ProductSpecify("Chip", item.getChip()));
					this.product_specs.add(new ProductSpecify("Bộ nhớ trong", item.getInternalMemory()));
					this.product_specs.add(new ProductSpecify("SIM", item.getSim()));
				}
				if(this.category.getCode().equalsIgnoreCase("laptop")) {
					this.product_specs.add(new ProductSpecify("CPU", item.getCpu()));
					this.product_specs.add(new ProductSpecify("Phần cứng", item.getHardWare()));
					this.product_specs.add(new ProductSpecify("Card màn hình", item.getCard()));
					this.product_specs.add(new ProductSpecify("Đặc biệt", item.getSpecial()));
				}

			}
			break;
		default:
			break;
		}

		brand = new BrandDtoNew();
		if (brand != null) {
			Brand brandEntity = entity.getBrand();
			brand = new BrandDtoNew(brandEntity);
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
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

	public List<TagDto> getTags() {
		return tags;
	}

	public void setTags(List<TagDto> tags) {
		this.tags = tags;
	}

	@JsonIgnore
	public BookDto getBook() {
		return book;
	}

	public void setBook(BookDto book) {
		this.book = book;
	}

	@JsonIgnore
	public TechDto getTechnology() {
		return technology;
	}

	public void setTechnology(TechDto technology) {
		this.technology = technology;
	}

	public BrandDtoNew getBrand() {
		return brand;
	}

	public void setBrand(BrandDtoNew brand) {
		this.brand = brand;
	}

	public Set<AuthorDtoNew> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorDtoNew> authors) {
		this.authors = authors;
	}

	public PublisherDtoNew getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherDtoNew publisher) {
		this.publisher = publisher;
	}

	public Integer getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Integer in_stock) {
		this.in_stock = in_stock;
	}

	public List<ProductSpecify> getProduct_specs() {
		return product_specs;
	}

	public void setProduct_specs(List<ProductSpecify> product_specs) {
		this.product_specs = product_specs;
	}

}
