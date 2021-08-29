package com.example.demo.entity.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.order.Order;

@Entity
@Table(name = "tbl_user")
public class User extends BaseEntity {

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private FullName fullname = new FullName();

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Address address = new Address();

//	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	private Store store = new Store();

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Order> orders = new ArrayList<>();

	@ManyToMany
	@JoinTable(name = "tbl_user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

	@OneToMany(mappedBy = "user")
	private List<Comment> comments;

	public User() {
	}

	public User(String phone, String email, String username, String password, String dateOfBirth, FullName fullname,
			Address address) {
		super();
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.fullname = fullname;
		this.address = address;
	}

	public User(String phone, String email, String username, String password, String dateOfBirth, FullName fullname) {
		super();
		this.phone = phone;
		this.email = email;
		this.username = username;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.fullname = fullname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public FullName getFullname() {
		return fullname;
	}

	public void setFullname(FullName fullname) {
		this.fullname = fullname;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

//	public Store getStore() {
//		return store;
//	}
//
//	public void setStore(Store store) {
//		this.store = store;
//	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
