package com.example.demo.entity.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Category;
import com.example.demo.entity.Comment;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.order.OrderDetail;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

	@Column(name = "type") // book: 1, food: 2, electric: 3, laptop: 3-1, phone: 3-2
	private Integer type;

	@Column(name = "name") // 1, 2, 3
	private String name;

	@Column(name = "slug")
	private String slug;

	@Column(name = "description", columnDefinition = "TEXT") // 1, 2, 3
	private String description;

	@Column(name = "price") // 1, 2, 3
	private Long price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id") // 1, 2, 3
	private SubCategory subcategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id") // 1, 2, 3
	private Category category;

//	----------------	BOOK	--------------------

	@Column(name = "publishing_year")
	private Integer publishingYear; // 1

	@Column(name = "number_of_pages")
	private Integer numberOfPages; // 1

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbl_book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Author> authors; // 1

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "publisher_id") // 1
	private Publisher publisher;

//	----------------	FOOD	--------------------

	@Column(name = "weight") // 2
	private String weight;

	@Column(name = "ingredients") // thanh phan //2
	private String ingredients;

	@Column(name = "expired_date") // han su dung // 2
	private String expiredDate;

	@Column(name = "manual") // Huong dan su dung // 2
	private String manual;

	@Column(name = "preserve") // huong dan bao quan // 2
	private String preserve;

//	----------------	ELECTRIC	--------------------

	@Column(name = "screen") // 3
	private String screen;

	@Column(name = "operator_system") // 3
	private String operatorSystem;

	@Column(name = "ram") // 3
	private String ram;

	@Column(name = "pin") // 3
	private String pin;

	@Column(name = "design") // 3
	private String design;

	@Column(name = "size_weight") // 3
	private String sizeWeight;

	@Column(name = "material") // 3
	private String material;

	@Column(name = "release_time") // 3
	private String releaseTime;

//	----------------	PHONE	--------------------

	@Column(name = "front_camera")
	private String frontCamera;

	@Column(name = "behind_camera")
	private String behindCamera;

	@Column(name = "chip")
	private String chip;

	@Column(name = "internal_memory")
	private String internalMemory;

	@Column(name = "sim")
	private String sim;

//	----------------	LAPTOP	--------------------

	@Column(name = "cpu")
	private String cpu;

	@Column(name = "hard_ware")
	private String hardWare;

	@Column(name = "card")
	private String card;

	@Column(name = "special")
	private String special;

//	----------------	BRAND	--------------------

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id") // 2, 3
	private Brand brand;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Image> images; // 1, 2, 3

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

//	 @OneToMany(mappedBy = "product")
//	 private Set<ProductInfo> productInfos;

	@JsonIgnore
	@OneToMany(mappedBy = "product" ,cascade = CascadeType.ALL)
	@NotFound(action = NotFoundAction.IGNORE)
	private List<ProductDetail> details; // 3,4,1,2

//	@JsonIgnore
//	@OneToMany(cascade = CascadeType.ALL)
//	@NotFound(action = NotFoundAction.IGNORE)
//	@JoinColumn(name = "color_id")
//	private Set<Color> colors; // 3,4,1,2

	public Product() {

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(SubCategory subcategory) {
		this.subcategory = subcategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
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

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
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

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<ProductDetail> getDetails() {
		return details;
	}

	public void setDetails(List<ProductDetail> details) {
		this.details = details;
	}

//	public Set<Color> getColors() {
//		return colors;
//	}
//
//	public void setColors(Set<Color> colors) {
//		this.colors = colors;
//	}

}
