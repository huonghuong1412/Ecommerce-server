package com.example.demo.dto.GHN;

public class GHNOrderResponse {
//	"order_code": "Z08OD",
//    "sort_code": "A02-40-08-00",
//    "trans_type": "truck",
//    "ward_encode": "",
//    "district_encode": "",
//    "fee": {
//        "main_service": 41000,
//        "insurance": 0,
//        "station_do": 0,
//        "station_pu": 0,
//        "return": 0,
//        "r2s": 0,
//        "coupon": 0
//    },
//    "total_fee": 41000,
//    "expected_delivery_time": "2021-10-10T23:59:59Z"
	private String order_code;
	private Integer total_fee;

	public GHNOrderResponse() {
		super();
	}

	public GHNOrderResponse(String order_code, Integer total_fee) {
		super();
		this.order_code = order_code;
		this.total_fee = total_fee;
	}

	public String getOrder_code() {
		return order_code;
	}

	public void setOrder_code(String order_code) {
		this.order_code = order_code;
	}

	public Integer getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}

}
