package com.example.demo.dto.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;

public class ProductDto extends AbstractDTO<ProductDto> {
	private Integer type; // loai san phamr
	private String name;
	private String sku;
	private String slug;
	private String description;
	private Long price;
	private Long list_price;
	private String mainImage;
	private Integer weight;
	private Integer length;
	private Integer width;
	private Integer height;
	private List<String> images;
	private String category;
	private String subcategory;

	// book
	private Integer publishingYear;

	private Integer numberOfPages;

	private Set<String> authorCodes;

	private String publisher;

	// electric
	private String screen;
	private String operatorSystem;
	private String ram;
	private String pin;
	private String design;
	private String sizeWeight;
	private String material;
	private String releaseTime;

	// phone
	private String frontCamera;
	private String behindCamera;
	private String chip;
	private String internalMemory;
	private String sim;

	// laptop
	private String cpu;
	private String hardWare;
	private String card;
	private String special;

	// brand
	private String brand;

	public ProductDto() {
		super();
	}

	public ProductDto(Product entity) {
		super();
		this.setId(entity.getId());
		this.type = entity.getType();
		this.name = entity.getName();
		this.sku = entity.getSku();
		this.slug = entity.getSlug();
		this.description = entity.getDescription();
		this.price = entity.getPrice();
		this.list_price = entity.getList_price();
		this.mainImage = entity.getMainIamge();
		this.weight = entity.getWeight();
		this.length = entity.getLength();
		this.width = entity.getWidth();
		this.height = entity.getHeight();
		this.category = entity.getCategory().getCode();
		this.subcategory = entity.getSubcategory().getCode();
		this.brand = entity.getBrand().getCode();

		images = new ArrayList<>();
		for (Image image : entity.getImages()) {
			ImageDto dto = new ImageDto(image);
			images.add(dto.getUrl());
		}

		switch (this.type) {
		case 1:
			// sach
			this.publishingYear = entity.getBook().getPublishingYear();
			this.numberOfPages = entity.getBook().getNumberOfPages();
			authorCodes = new HashSet<>();
			for (Author author : entity.getBook().getAuthors()) {
				AuthorDto dto = new AuthorDto(author);
				authorCodes.add(dto.getCode());
			}
			this.publisher = entity.getBook().getPublisher().getCode();
			break;
		case 2:
			// electric
			this.screen = entity.getTechnology().getScreen();
			this.operatorSystem = entity.getTechnology().getOperatorSystem();
			this.ram = entity.getTechnology().getRam();
			this.pin = entity.getTechnology().getPin();
			this.design = entity.getTechnology().getDesign();
			this.sizeWeight = entity.getTechnology().getSizeWeight();
			this.material = entity.getTechnology().getMaterial();
			this.releaseTime = entity.getTechnology().getReleaseTime();
			// phone
			this.frontCamera = entity.getTechnology().getFrontCamera();
			this.behindCamera = entity.getTechnology().getBehindCamera();
			this.chip = entity.getTechnology().getChip();
			this.internalMemory = entity.getTechnology().getInternalMemory();
			this.sim = entity.getTechnology().getSim();

			this.cpu = entity.getTechnology().getCpu();
			this.hardWare = entity.getTechnology().getHardWare();
			this.card = entity.getTechnology().getCard();
			this.special = entity.getTechnology().getSpecial();
			break;
		default:
			break;
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

	public String getMainImage() {
		return mainImage;
	}

	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Set<String> getAuthorCodes() {
		return authorCodes;
	}

	public void setAuthorCodes(Set<String> authorCodes) {
		this.authorCodes = authorCodes;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
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

}
