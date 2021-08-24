package com.example.demo.dto.order;

import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.product.Product;

public class OrderDetailDto{

    private Long product_id;
    private Integer amount;
    private Long price;
    private Long total_price;
    private Long order_id;
    
    public OrderDetailDto() {
    }

    public OrderDetailDto(OrderDetail orderDetail) {
        this.product_id = orderDetail.getProduct().getId();
        this.amount = orderDetail.getAmount();
        this.price = orderDetail.getPrice();
        this.total_price = orderDetail.getTotal_price();
    }

    public OrderDetail toEntity(Order order, Product product){
        OrderDetail o = new OrderDetail();
        o.setAmount(this.getAmount());
        o.setPrice(this.getPrice());
        o.setTotal_price(this.getTotal_price());
        o.setOrder(order);
        o.setProduct(product);
        return o;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
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

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
    
    
}
