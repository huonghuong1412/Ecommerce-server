package com.example.demo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.GHN.GHNOrder;
import com.example.demo.entity.order.Order;
import com.example.demo.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/ship")
public class GHNController {

	@Autowired
	private OrderRepository orderRepos;

	@RequestMapping(value = "/create")
	public ResponseEntity<GHNOrder> testShipPost(@RequestHeader(value = "token") String token,
			@RequestHeader(value = "shopid") Integer shopid, @RequestBody GHNOrder order) {
		HttpHeaders headers = new HttpHeaders();
		RestTemplate restTemplate = new RestTemplate();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Content-type", "application/json");
		headers.add("token", token);
		headers.add("shopid", shopid.toString());

		Order o = orderRepos.getById(order.getOrder_id());

		if (o.getPayment().getStatus() == 1) {
			order.setPayment_type_id(1);
			order.setCod_amount(0L);
		} else {
			order.setPayment_type_id(2);
		}

		order.setCod_amount(o.getTotal_price());

		order.setNote("");
		order.setRequired_note("KHONGCHOXEMHANG");
		order.setReturn_phone("0332190158");
		order.setReturn_address("39 NTT");
		order.setReturn_ward_code("");
		order.setReturn_district_id(null);
		order.setClient_order_code("");
		order.setContent("");
		order.setPick_station_id(null);
		order.setOrder_value(o.getTotal_price());
		order.setService_type_id(null);
		order.setService_id(53321);
		HttpEntity<GHNOrder> entity = new HttpEntity<GHNOrder>(order, headers);
		restTemplate.exchange("https://dev-online-gateway.ghn.vn/shiip/public-api/v2/shipping-order/create",
				HttpMethod.POST, entity, GHNOrder.class);
		return new ResponseEntity<GHNOrder>(entity.getBody(), HttpStatus.OK);
	}
}