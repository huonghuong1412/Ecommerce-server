package com.example.demo.dto.to_entity;

//import com.example.demo.dto.product.ProductDto;
import com.example.demo.entity.order.OrderDetail;
//import com.example.demo.entity.product.Product;

public class OrderDetailHisDto {
	private String product_name;
	private String category;
	private String sub_category;
	private String image;
	private Integer amount;
	private Long price;
	private Long total_price;
//	private ProductDto productDto;

	public OrderDetailHisDto(String product_name, String category, String sub_category, Integer amount, Long price,
			Long total_price) {
		this.product_name = product_name;
		this.category = category;
		this.sub_category = sub_category;
		this.amount = amount;
		this.price = price;
		this.total_price = total_price;
//		this.productDto = productDto;
	}

	public OrderDetailHisDto(OrderDetail entity) {
		this.product_name = entity.getProduct().getName();
		this.category = entity.getProduct().getCategory().getName();
		this.sub_category = entity.getProduct().getSubcategory().getName();
		this.amount = entity.getAmount();
		this.price = entity.getPrice();
		this.image = entity.getProduct().getImages().get(0).getUrl();
		this.total_price = entity.getTotal_price();
//		this.productDto = new ProductDto();
//		if (productDto != null) {
//			Product pro = entity.getProduct();
//			productDto = new ProductDto(pro);
//		}
	}

	public OrderDetailHisDto() {
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSub_category() {
		return sub_category;
	}

	public void setSub_category(String sub_category) {
		this.sub_category = sub_category;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public String getImage() {
		return image;
	}
//
//	public void setImage(String image) {
//		this.image = image;
//	}
//
//	public ProductDto getProductDto() {
//		return productDto;
//	}
//
//	public void setProductDto(ProductDto productDto) {
//		this.productDto = productDto;
//	}

}
