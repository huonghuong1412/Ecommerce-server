package com.example.demo.dto;

public class SearchDto {
	private int pageIndex;
	private int pageSize;
	private String keyword;
	private String category;
	private String subcategory;
	private Long productId;

	public SearchDto() {
		super();
	}

	public SearchDto(int pageIndex, int pageSize, Long productId) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.productId = productId;
	}

	public SearchDto(int pageIndex, int pageSize, String keyword) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.keyword = keyword;
	}

	public SearchDto(int pageIndex, int pageSize, String keyword, String category, String subcategory) {
		super();
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.keyword = keyword;
		this.category = category;
		this.subcategory = subcategory;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

}
