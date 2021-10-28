package com.example.demo.dto.inventory;

import com.example.demo.dto.AbstractDTO;
import com.example.demo.entity.inventory.Inventory;

public class InventoryDtoNew extends AbstractDTO<InventoryDtoNew> {

	private Integer total_import_item;
	private Integer quantity_item;
	private Integer sold;
	private Long productId;
	private String productName;
	private String productMainImage;
	private String category_code;

	public InventoryDtoNew() {
		// TODO Auto-generated constructor stub
	}

	public InventoryDtoNew(Inventory entity) {
		super();
		this.setId(entity.getId());
		this.total_import_item = entity.getTotal_import_item();
		this.quantity_item = entity.getQuantity_item();
		this.sold = this.total_import_item - this.quantity_item;
		this.productId = entity.getProduct().getId();
		this.productName = entity.getProduct().getName();
		this.productMainImage = entity.getProduct().getMainIamge();
		this.category_code = entity.getCategory_code();
	}

	public Integer getTotal_import_item() {
		return total_import_item;
	}

	public void setTotal_import_item(Integer total_import_item) {
		this.total_import_item = total_import_item;
	}

	public Integer getQuantity_item() {
		return quantity_item;
	}

	public void setQuantity_item(Integer quantity_item) {
		this.quantity_item = quantity_item;
	}

	public Integer getSold() {
		return sold;
	}

	public void setSold(Integer sold) {
		this.sold = sold;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductMainImage() {
		return productMainImage;
	}

	public void setProductMainImage(String productMainImage) {
		this.productMainImage = productMainImage;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

}
