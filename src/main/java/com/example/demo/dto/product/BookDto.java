package com.example.demo.dto.product;

import java.util.HashSet;
import java.util.Set;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookDto extends AbstractDTO<BookDto>{

	// sach
	private Integer publishingYear;
	private Integer numberOfPages;
	private Set<String> authorCodes;
	private String publisher;
	
	

	public BookDto() {
		super();
	}

	public BookDto(Book entity) {
		this.setId(entity.getId());
		this.publishingYear = entity.getPublishingYear();
		this.numberOfPages = entity.getNumberOfPages();
		authorCodes = new HashSet<>();
		for (Author author : entity.getAuthors()) {
			AuthorDto dto = new AuthorDto(author);
			authorCodes.add(dto.getCode());
		}
		this.publisher = entity.getPublisher().getCode();

	}

	@JsonIgnore(value = false)
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

}
