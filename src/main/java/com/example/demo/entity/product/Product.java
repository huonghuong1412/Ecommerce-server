package com.example.demo.entity.product;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.user.Comment;

@Entity
@Table(name = "tbl_product")
public class Product extends BaseEntity {

	@Column(name = "type") // book: 1, food: 2, electric: 3, laptop: 3-1, phone: 3-2
	private Integer type;

	@Column(name = "name") // 1, 2, 3
	private String name;

	@Column(name = "sku") // 1, 2, 3
	private String sku;

	@Column(name = "slug")
	private String slug;

	@Column(name = "description", columnDefinition = "TEXT") // 1, 2, 3
	private String description;

	@Column(name = "price") // 1, 2, 3 // gia thuc te ban ra
	private Long price;

	@Column(name = "list_price") // 1, 2, 3 // gia niem yet
	private Long list_price;

	@Column(name = "main_image")
	private String mainIamge;

	@Column(name = "weight")
	private Integer weight; // -- Bắt buộc, khối lượng đóng gói

	@Column(name = "length")
	private Integer length; // -- Bắt buộc, chiều dài khi đóng gói

	@Column(name = "width")
	private Integer width; // -- Bắt buộc, chiều rộng khi đóng gói

	@Column(name = "height")
	private Integer height; // -- Bắt buộc, chiều cao khi đóng gói

	@Column(name = "display")
	private Integer display; // 1 : show, 0: hidden

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subcategory_id") // 1, 2, 3
	private SubCategory subcategory;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id") // 1, 2, 3
	private Category category;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "store_id") // 1, 2, 3
//	private Store store;

//	----------------	BOOK	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Book book;

//	----------------	ELECTRIC	--------------------

	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Technology technology;

//	----------------	BRAND	--------------------

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "brand_id") // 1, 2, 3
	private Brand brand;

//	----------------	INVENTORY	--------------------
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Inventory inventory;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Image> images; // 1, 2, 3

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<Comment> comments;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderDetail> orderDetails = new ArrayList<>();

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

	public String getMainIamge() {
		return mainIamge;
	}

	public void setMainIamge(String mainIamge) {
		this.mainIamge = mainIamge;
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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}

	public SubCategory getSubcategory() {
		return subcategory;
	}

	public Integer getDisplay() {
		return display;
	}

	public void setDisplay(Integer display) {
		this.display = display;
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

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Technology getTechnology() {
		return technology;
	}

	public void setTechnology(Technology technology) {
		this.technology = technology;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

}
