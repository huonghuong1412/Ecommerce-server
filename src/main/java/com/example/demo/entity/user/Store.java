package com.example.demo.entity.user;

//import java.util.List;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.MapsId;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
//
//import com.example.demo.entity.BaseEntity;
//import com.example.demo.entity.order.Order;
//import com.example.demo.entity.product.Product;

//@Entity
//@Table(name = "tbl_store")
//public class Store extends BaseEntity {

//	@Column(name = "store_name")
//	private String storeName;
//
//	@Column(name = "store_code")
//	private String storeCode;
//
//	@Column(name = "store_phone")
//	private String storePhone;
//
//	@Column(name = "store_address")
//	private String storeAddress;
//
//	@Column(name = "description")
//	private String description;
//
//	@Column(name = "store_image")
//	private String storeImage;
//
//	@OneToOne
//	@MapsId
//	@JoinColumn(name = "user_id")
//	private User user;
//
//	@OneToMany(mappedBy = "store")
//	private List<Product> products;
//
//	@OneToMany(mappedBy = "store")
//	private List<Order> orders;
//
//	public Store() {
//		// TODO Auto-generated constructor stub
//	}
//
//	public Store(String storeName, String storeCode, String storePhone, String storeAddress, String description,
//			String storeImage) {
//		super();
//		this.storeName = storeName;
//		this.storeCode = storeCode;
//		this.storePhone = storePhone;
//		this.storeAddress = storeAddress;
//		this.description = description;
//		this.storeImage = storeImage;
//	}
//
//	public String getStoreName() {
//		return storeName;
//	}
//
//	public void setStoreName(String storeName) {
//		this.storeName = storeName;
//	}
//
//	public String getStoreCode() {
//		return storeCode;
//	}
//
//	public void setStoreCode(String storeCode) {
//		this.storeCode = storeCode;
//	}
//
//	public String getStorePhone() {
//		return storePhone;
//	}
//
//	public void setStorePhone(String storePhone) {
//		this.storePhone = storePhone;
//	}
//
//	public String getStoreAddress() {
//		return storeAddress;
//	}
//
//	public void setStoreAddress(String storeAddress) {
//		this.storeAddress = storeAddress;
//	}
//
//	public String getStoreImage() {
//		return storeImage;
//	}
//
//	public void setStoreImage(String storeImage) {
//		this.storeImage = storeImage;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public List<Product> getProducts() {
//		return products;
//	}
//
//	public void setProducts(List<Product> products) {
//		this.products = products;
//	}
//
//	public List<Order> getOrders() {
//		return orders;
//	}
//
//	public void setOrders(List<Order> orders) {
//		this.orders = orders;
//	}

//}
