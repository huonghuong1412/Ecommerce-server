package com.example.demo.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GHN.Item;
import com.example.demo.dto.ship.ShipFeeResponse;
import com.example.demo.dto.ship.ShipInfoOrderResponse;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.repository.OrderRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/services/ship/ghtk")
public class GHTKController {

	public final static String TOKEN = "860fE249A6871265dCc3Eb4Ab3900b5cB0f7bD10";
	public final static String API_ENDPOINT = "https://services.ghtklab.com";
	public final static String CREATE_ORDER_URL = API_ENDPOINT + "/services/shipment/order/?ver=1.5";
	public final static String CALCUATE_SHIP_FEE_URL = API_ENDPOINT + "/services/shipment/fee";
	public final static String CHECKING_STATUS_URL = API_ENDPOINT + "/services/shipment/v2/";
	public final static String PRINT_INVOICE = API_ENDPOINT + "/services/label/";
	public final static String CANCEL_ORDER_URL = API_ENDPOINT + "/services/shipment/cancel/";

	@Autowired
	private OrderRepository orderRepos;
	
	// lên đơn hàng
	@PostMapping(value = "/create-order/{id}")
	public Map<String, Object> createOrder(@PathVariable(name = "id") Long order_id) throws ClientProtocolException, IOException {
		JSONObject json = new JSONObject();
		Order o = orderRepos.getById(order_id);
		JSONObject order = new JSONObject();
		order.put("id", order_id.toString());
		order.put("pick_name", "E-Shop");
		order.put("pick_money", "");
		
		if (o.getPayment().getStatus() == 1) {
			order.put("pick_money", 0L);
		} else {
			order.put("pick_money", o.getTotal_price());
		}
		
		order.put("pick_address", "Số nhà 01 ngõ 46 An Hoà");
		order.put("pick_province", "Hà Nội");
		order.put("pick_district", "Quận Hà Đông");
		order.put("pick_ward", "Phường Mộ Lao");
		order.put("pick_tel", "0393401266");
		order.put("name", o.getShipment().getCustomer_name());
//		String address = o.getShipment().getAddress() + ", " + o.getShipment().getWard() + ", " + o.getShipment().getDistrict() + ", " + o.getShipment().getProvince();
		order.put("address", o.getShipment().getAddress());
		order.put("province", o.getShipment().getProvince());
		order.put("district", o.getShipment().getDistrict());
		order.put("ward", o.getShipment().getWard());
		order.put("hamlet", "Khác");
		order.put("tel", "0393401255");
		order.put("email", "huonghuong@gmail.com");
		order.put("value", o.getTotal_price());
		
		List<Item> list = new ArrayList<>();
		for (OrderDetail detail : o.getOrderDetails()) {
			Double weight = (double) (detail.getProduct().getWeight()/1000);
			list.add(new Item(detail.getProduct().getName().toString(), detail.getProduct().getId().toString(),
					detail.getQuantity(), detail.getPrice(), weight));
		}
		
		json.put("order", order);
		json.put("products", list);
		
		System.out.println(json);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(CREATE_ORDER_URL);
		StringEntity stringEntity = new StringEntity(json.toString());
		post.setHeader("content-type", "application/json");
		post.setHeader("Token", TOKEN);
		post.setEntity(stringEntity);
		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());
		return result.toMap();
	}

	// tính phí ship
	@GetMapping(value = "/ship-fee")
	public ResponseEntity<ShipFeeResponse> calculateShipFee(@RequestParam String pick_province, @RequestParam String pick_district,
			@RequestParam String province, @RequestParam String district, @RequestParam Integer weight,
			@RequestParam(name = "deliver_option", defaultValue = "none") String deliver_option,
			@RequestParam Long value) throws ClientProtocolException, IOException, URISyntaxException {
		JSONObject json = new JSONObject();
		json.put("pick_province", pick_province);
		json.put("pick_district", pick_district);
		json.put("province", province);
		json.put("district", district);
		json.put("weight", weight);
		json.put("deliver_option", "none");
		json.put("value", value);
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(CALCUATE_SHIP_FEE_URL);
		List<NameValuePair> params = new ArrayList<>();
		for (Map.Entry<String, Object> e : json.toMap().entrySet()) {
			params.add(new BasicNameValuePair(e.getKey(), e.getValue().toString()));
		}

		URI uri = new URIBuilder(get.getURI()).setParameters(params).build();
		get.setURI(uri);
		get.setHeader("Token", TOKEN);
		CloseableHttpResponse res = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());
		JSONObject fee = (JSONObject) result.get("fee");
		Long only_ship_fee = Long.parseLong(fee.get("ship_fee_only").toString());
		Long total_ship_fee = Long.parseLong(fee.get("fee").toString());
		Long insurance_fee = Long.parseLong(fee.get("insurance_fee").toString());
		String message = result.get("message").toString();
		Boolean success = (Boolean) result.get("success");
		ShipFeeResponse ship = new ShipFeeResponse(only_ship_fee, total_ship_fee, insurance_fee, message, success);
		return new ResponseEntity<ShipFeeResponse>(ship, HttpStatus.OK);
	}

	// tra cứu thông tin đơn hàng
	@GetMapping(value = "/checking-status/{id}")
	public Map<String, Object> checkingStatusOrder(@PathVariable(name = "id") String order_id)
			throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(CHECKING_STATUS_URL + order_id);
		get.setHeader("Token", TOKEN);
		CloseableHttpResponse res = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());
		System.out.println(result);
		return result.toMap();
	}
	
	@GetMapping(value = "/checkorderinfo/{id}")
	public ResponseEntity<ShipInfoOrderResponse> checkOrderInfo(@PathVariable(name = "id") String order_id)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(CHECKING_STATUS_URL + order_id);
		get.setHeader("Token", TOKEN);
		CloseableHttpResponse res = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());
		JSONObject data = (JSONObject) result.get("order");
		String message = result.get("message").toString();
		Boolean success = (Boolean) result.get("success");;
		String order_code = data.get("label_id").toString();
		String partner_id = data.get("partner_id").toString();
		Integer status = Integer.parseInt(data.get("status").toString());
		String status_name = data.get("status_text").toString();
		String created_date = data.get("created").toString();
		String updated_date = data.get("modified").toString();
		String pick_date = data.get("pick_date").toString();
		String deliver_date = data.get("deliver_date").toString();
		String to_name = data.get("customer_fullname").toString();
		String to_phone = data.get("customer_tel").toString();
		String to_address = data.get("address").toString();
		Integer weight = Integer.parseInt(data.get("weight").toString());
		Integer is_freeship = Integer.parseInt(data.get("is_freeship").toString());
		Long cod_amount = Long.parseLong(data.get("pick_money").toString());
		Long insurance = Long.parseLong(data.get("insurance").toString());
		Long ship_fee = Long.parseLong(data.get("ship_money").toString());
		Long order_value = Long.parseLong(data.get("value").toString());

		ShipInfoOrderResponse info = new ShipInfoOrderResponse(message, success, order_code, partner_id, status, status_name, created_date, updated_date, pick_date, deliver_date, to_name, to_phone, to_address, weight, is_freeship, cod_amount, insurance, ship_fee, order_value);
		return new ResponseEntity<ShipInfoOrderResponse>(info, HttpStatus.OK);
	}

	// in hoá đơn
	@GetMapping(value = "/print-invoice/{id}")
	public String printInvoice(@PathVariable(name = "id") String order_id) throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(PRINT_INVOICE + order_id);
		get.setHeader("Token", TOKEN);

		CloseableHttpResponse res = client.execute(get);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
//		System.out.println(resultJsonStr);
//		JSONObject result = new JSONObject(resultJsonStr.toString());
//		System.out.println(result);
//		return result.toMap();
		return "";
	}

	// huỷ đơn hàng
	@PostMapping(value = "/cancel-order/{id}")
	public Map<String, Object> cancelOrder(@PathVariable(name = "id") String order_id)
			throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(CANCEL_ORDER_URL + order_id);
		post.setHeader("Token", TOKEN);
		CloseableHttpResponse res = client.execute(post);
		BufferedReader rd = new BufferedReader(new InputStreamReader(res.getEntity().getContent()));
		StringBuilder resultJsonStr = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			resultJsonStr.append(line);
		}
		JSONObject result = new JSONObject(resultJsonStr.toString());
		return result.toMap();
	}

}