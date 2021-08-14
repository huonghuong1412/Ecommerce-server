package com.example.demo.entity.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.example.demo.entity.BaseEntity;

//@Entity
//@Table(name = "tbl_tag")
public class Tag extends BaseEntity {
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "tbl_book_author", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "author_id"))
	private Set<Product> products; // 1
}
