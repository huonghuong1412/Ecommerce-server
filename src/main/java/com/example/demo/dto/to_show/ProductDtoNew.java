package com.example.demo.dto.to_show;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.dto.to_entity.ImageDto;
import com.example.demo.dto.to_entity.ProductDetailDto;
import com.example.demo.dto.to_entity.ProductInfoDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.ProductDetail;
import com.example.demo.entity.product.ProductInfo;
import com.example.demo.entity.product.Publisher;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ProductDtoNew extends AbstractDTO<ProductDtoNew> {

	private Integer type; // loai san phamr
	private String name;
	private String sku;
	private String slug;
	private String description;
	private Long price;
	private List<String> images;
	private CategoryDtoNew category;
	private SubcategoryDtoNew subcategory;

	// info
	@JsonInclude(value = Include.NON_EMPTY)
	private List<ProductInforNew> productInfos;

	// sach
	@JsonInclude(value = Include.NON_NULL)
	private Integer publishingYear;

	@JsonInclude(value = Include.NON_NULL)
	private Integer numberOfPages;

	@JsonInclude(value = Include.NON_EMPTY)
	private Set<AuthorDtoNew> authors;

	@JsonInclude(value = Include.NON_NULL)
	private PublisherDtoNew publisher;

	// food
	@JsonInclude(value = Include.NON_NULL)
	private String weight; // trong luong

	@JsonInclude(value = Include.NON_NULL)
	private String ingredients;// thanh phan

	@JsonInclude(value = Include.NON_NULL)
	private String expiredDate; // han su dung

	@JsonInclude(value = Include.NON_NULL)
	private String manual; // Huong dan su dung

	@JsonInclude(value = Include.NON_NULL)
	private String preserve;// huong dan bao quan

	// electric
	@JsonInclude(value = Include.NON_NULL)
	private String screen;

	@JsonInclude(value = Include.NON_NULL)
	private String operatorSystem;

	@JsonInclude(value = Include.NON_NULL)
	private String ram;

	@JsonInclude(value = Include.NON_NULL)
	private String pin;

	@JsonInclude(value = Include.NON_NULL)
	private String design;

	@JsonInclude(value = Include.NON_NULL)
	private String sizeWeight;

	@JsonInclude(value = Include.NON_NULL)
	private String material;

	@JsonInclude(value = Include.NON_NULL)
	private String releaseTime;

	// phone
	@JsonInclude(value = Include.NON_NULL)
	private String frontCamera;

	@JsonInclude(value = Include.NON_NULL)
	private String behindCamera;

	@JsonInclude(value = Include.NON_NULL)
	private String chip;

	@JsonInclude(value = Include.NON_NULL)
	private String internalMemory;

	@JsonInclude(value = Include.NON_NULL)
	private String sim;

	// laptop
	@JsonInclude(value = Include.NON_NULL)
	private String cpu;

	@JsonInclude(value = Include.NON_NULL)
	private String hardWare;

	@JsonInclude(value = Include.NON_NULL)
	private String card;

	@JsonInclude(value = Include.NON_NULL)
	private String special;

	// brand
	@JsonInclude(value = Include.NON_NULL)
	private BrandDtoNew brand;

	@JsonInclude(value = Include.NON_EMPTY)
	private List<ProductDetailDto> product_specs;

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

		this.productInfos = new ArrayList<>();
		for (ProductInfo info : entity.getProduct_infos()) {
			ProductInforNew dto = new ProductInforNew(info);
			this.productInfos.add(dto);
		}

		// sach
		authors = new HashSet<>();
		this.publishingYear = entity.getPublishingYear();
		this.numberOfPages = entity.getNumberOfPages();
		if (this.type == 1) {
			authors = new HashSet<>();
			for (Author author : entity.getAuthors()) {
				AuthorDtoNew dto = new AuthorDtoNew(author);
				authors.add(dto);
			}
			publisher = new PublisherDtoNew();
			if (publisher != null) {
				Publisher item = entity.getPublisher();
				this.publisher = new PublisherDtoNew(item);
			}
		}

		brand = new BrandDtoNew();
		if (brand != null) {
			Brand brandEntity = entity.getBrand();
			brand = new BrandDtoNew(brandEntity);
		}
//			product_specs = new ArrayList<ProductDetailDto>();
//			for (ProductDetail item : entity.getDetails()) {
//				ProductDetailDto dto = new ProductDetailDto(item);
//				product_specs.add(dto);
//			}

		if (this.type == 3 || this.type == 4) {
			product_specs = new ArrayList<ProductDetailDto>();
			try {
				for (ProductDetail item : entity.getDetails()) {
					ProductDetailDto dto = new ProductDetailDto(item);
					product_specs.add(dto);
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}

		// food
		this.weight = entity.getWeight();
		this.ingredients = entity.getIngredients();
		this.expiredDate = entity.getExpiredDate();
		this.manual = entity.getManual();
		this.preserve = entity.getPreserve();

		// electric
		this.screen = entity.getScreen();
		this.operatorSystem = entity.getOperatorSystem();
		this.ram = entity.getRam();
		this.pin = entity.getPin();
		this.design = entity.getDesign();
		this.sizeWeight = entity.getSizeWeight();
		this.material = entity.getMaterial();
		this.releaseTime = entity.getReleaseTime();

		// phone
		this.frontCamera = entity.getFrontCamera();
		this.behindCamera = entity.getBehindCamera();
		this.chip = entity.getChip();
		this.internalMemory = entity.getInternalMemory();
		this.sim = entity.getSim();

		// laptop
		this.cpu = entity.getCpu();
		this.hardWare = entity.getHardWare();
		this.card = entity.getCard();
		this.special = entity.getSpecial();
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

	public Integer getPublishingYear() {
		return publishingYear;
	}

	public void setPublishingYear(Integer publishingYear) {
		this.publishingYear = publishingYear;
	}

	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
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

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getIngredients() {
		return ingredients;
	}

	public void setIngredients(String ingredients) {
		this.ingredients = ingredients;
	}

	public String getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getManual() {
		return manual;
	}

	public void setManual(String manual) {
		this.manual = manual;
	}

	public String getPreserve() {
		return preserve;
	}

	public void setPreserve(String preserve) {
		this.preserve = preserve;
	}

	public String getScreen() {
		return screen;
	}

	public void setScreen(String screen) {
		this.screen = screen;
	}

	public String getOperatorSystem() {
		return operatorSystem;
	}

	public void setOperatorSystem(String operatorSystem) {
		this.operatorSystem = operatorSystem;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getDesign() {
		return design;
	}

	public void setDesign(String design) {
		this.design = design;
	}

	public String getSizeWeight() {
		return sizeWeight;
	}

	public void setSizeWeight(String sizeWeight) {
		this.sizeWeight = sizeWeight;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public String getFrontCamera() {
		return frontCamera;
	}

	public void setFrontCamera(String frontCamera) {
		this.frontCamera = frontCamera;
	}

	public String getBehindCamera() {
		return behindCamera;
	}

	public void setBehindCamera(String behindCamera) {
		this.behindCamera = behindCamera;
	}

	public String getChip() {
		return chip;
	}

	public void setChip(String chip) {
		this.chip = chip;
	}

	public String getInternalMemory() {
		return internalMemory;
	}

	public void setInternalMemory(String internalMemory) {
		this.internalMemory = internalMemory;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getHardWare() {
		return hardWare;
	}

	public void setHardWare(String hardWare) {
		this.hardWare = hardWare;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getSpecial() {
		return special;
	}

	public void setSpecial(String special) {
		this.special = special;
	}

	public BrandDtoNew getBrand() {
		return brand;
	}

	public void setBrand(BrandDtoNew brand) {
		this.brand = brand;
	}

	public List<ProductDetailDto> getProduct_specs() {
		return product_specs;
	}

	public void setProduct_specs(List<ProductDetailDto> product_specs) {
		this.product_specs = product_specs;
	}

	public List<ProductInforNew> getProductInfos() {
		return productInfos;
	}

	public void setProductInfos(List<ProductInforNew> productInfos) {
		this.productInfos = productInfos;
	}

}
