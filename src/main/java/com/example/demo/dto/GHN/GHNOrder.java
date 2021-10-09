package com.example.demo.dto.GHN;

import java.util.List;

public class GHNOrder {
	private Long order_id;
	private String order_code;
	private Integer payment_type_id; // Mã người thanh toán phí dịch vụ. 1: Người bán/Người gửi. 2: Người mua/Người
										// nhận.
	private String note; // Người gửi ghi chú cho tài xế.
	private String required_note; // Ghi chú bắt buộc, Bao gồm: CHOTHUHANG, CHOXEMHANGKHONGTHU, KHONGCHOXEMHANG
									// --// Bắt buộc
	private String return_phone; // Số điện thoại trả hàng khi không giao được.
	private String return_address; // Địa chỉ trả hàng khi không giao được.
	private Integer return_district_id; // Quận của người nhận hàng trả.
	private String return_ward_code; // Phường của người nhận hàng trả.
	private String client_order_code; // Mã đơn hàng riêng của khách hàng. Giá trị mặc định: null
	private String to_name; // Tên người nhận hàng. -- Bắt buộc
	private String to_phone; // Số điện thoại người nhận hàng. -- Bắt buộc
	private String to_address; // Địa chỉ Shiper tới giao hàng. -- Bắt buộc
	private String to_ward_code; // Phường của người nhận hàng. -- Bắt buộc
	private Integer to_district_id; // Quận của người nhận hàng. -- Bắt buộc
	private Long cod_amount; // Tiền thu hộ cho người gửi. Maximum :10.000.000 Giá trị mặc định: 0
	private String content; // Nội dung của đơn hàng.
	private Integer weight; // -- Bắt buộc
	private Integer length; // -- Bắt buộc
	private Integer width; // -- Bắt buộc
	private Integer height; // -- Bắt buộc
	private Integer pick_station_id; // Mã bưu cục để gửi hàng tại điểm. Giá trị mặc định : null
	private Long insurance_value; // Giá trị của đơn hàng ( Trường hợp mất hàng , bể hàng sẽ đền theo giá trị của
									// đơn hàng).
	private Integer service_id; // -- Bắt buộc
	private Integer service_type_id; // -- Bắt buộc
	private Long order_value; // Giá trị đơn hàng
	private String coupon; // Mã giảm giá.
	private List<Item> items; // -- Bắt buộc

	public GHNOrder() {
		super();
	}

	public GHNOrder(Integer payment_type_id, String note, String required_note, String return_phone,
			String return_address, Integer return_district_id, String return_ward_code, String client_order_code,
			String to_name, String to_phone, String to_address, String to_ward_code, Integer to_district_id,
			Long cod_amount, String content, Integer weight, Integer length, Integer width, Integer height,
			Integer pick_station_id, Long insurance_value, Integer service_id, Integer service_type_id,
			Long order_value, String coupon, List<Item> items) {
		super();
		this.payment_type_id = payment_type_id;
		this.note = note;
		this.required_note = required_note;
		this.return_phone = return_phone;
		this.return_address = return_address;
		this.return_district_id = return_district_id;
		this.return_ward_code = return_ward_code;
		this.client_order_code = client_order_code;
		this.to_name = to_name;
		this.to_phone = to_phone;
		this.to_address = to_address;
		this.to_ward_code = to_ward_code;
		this.to_district_id = to_district_id;
		this.cod_amount = cod_amount;
		this.content = content;
		this.weight = weight;
		this.length = length;
		this.width = width;
		this.height = height;
		this.pick_station_id = pick_station_id;
		this.insurance_value = insurance_value;
		this.service_id = service_id;
		this.service_type_id = service_type_id;
		this.order_value = order_value;
		this.coupon = coupon;
		this.items = items;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Integer getPayment_type_id() {
		return payment_type_id;
	}

	public void setPayment_type_id(Integer payment_type_id) {
		this.payment_type_id = payment_type_id;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getRequired_note() {
		return required_note;
	}

	public void setRequired_note(String required_note) {
		this.required_note = required_note;
	}

	public String getReturn_phone() {
		return return_phone;
	}

	public void setReturn_phone(String return_phone) {
		this.return_phone = return_phone;
	}

	public String getReturn_address() {
		return return_address;
	}

	public void setReturn_address(String return_address) {
		this.return_address = return_address;
	}

	public Integer getReturn_district_id() {
		return return_district_id;
	}

	public void setReturn_district_id(Integer return_district_id) {
		this.return_district_id = return_district_id;
	}

	public String getReturn_ward_code() {
		return return_ward_code;
	}

	public void setReturn_ward_code(String return_ward_code) {
		this.return_ward_code = return_ward_code;
	}

	public String getClient_order_code() {
		return client_order_code;
	}

	public void setClient_order_code(String client_order_code) {
		this.client_order_code = client_order_code;
	}

	public String getTo_name() {
		return to_name;
	}

	public void setTo_name(String to_name) {
		this.to_name = to_name;
	}

	public String getTo_phone() {
		return to_phone;
	}

	public void setTo_phone(String to_phone) {
		this.to_phone = to_phone;
	}

	public String getTo_address() {
		return to_address;
	}

	public void setTo_address(String to_address) {
		this.to_address = to_address;
	}

	public String getTo_ward_code() {
		return to_ward_code;
	}

	public void setTo_ward_code(String to_ward_code) {
		this.to_ward_code = to_ward_code;
	}

	public Integer getTo_district_id() {
		return to_district_id;
	}

	public void setTo_district_id(Integer to_district_id) {
		this.to_district_id = to_district_id;
	}

	public Long getCod_amount() {
		return cod_amount;
	}

	public void setCod_amount(Long cod_amount) {
		this.cod_amount = cod_amount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Integer getPick_station_id() {
		return pick_station_id;
	}

	public void setPick_station_id(Integer pick_station_id) {
		this.pick_station_id = pick_station_id;
	}

	public Long getInsurance_value() {
		return insurance_value;
	}

	public void setInsurance_value(Long insurance_value) {
		this.insurance_value = insurance_value;
	}

	public Integer getService_id() {
		return service_id;
	}

	public void setService_id(Integer service_id) {
		this.service_id = service_id;
	}

	public Integer getService_type_id() {
		return service_type_id;
	}

	public void setService_type_id(Integer service_type_id) {
		this.service_type_id = service_type_id;
	}

	public Long getOrder_value() {
		return order_value;
	}

	public void setOrder_value(Long order_value) {
		this.order_value = order_value;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
