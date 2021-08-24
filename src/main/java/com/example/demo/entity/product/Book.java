package com.example.demo.entity.product;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.demo.entity.BaseEntity;

@Entity
@Table(name = "tbl_product_book")
public class Book extends BaseEntity {

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "product_id") // Liên kết với nhau qua khóa ngoại person_id
	private Product product;

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

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

}
