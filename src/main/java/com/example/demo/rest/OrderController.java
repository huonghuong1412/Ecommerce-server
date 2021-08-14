package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.to_entity.OrderDetailDto;
import com.example.demo.dto.to_entity.OrderDto;
import com.example.demo.dto.to_entity.OrderHisDto;
import com.example.demo.dto.to_entity.PaymentDto;
import com.example.demo.dto.to_entity.PaymentMethodDto;
import com.example.demo.dto.to_entity.ShipmentDto;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.entity.order.Shipment;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

//	@Autowired
//	private OrderHisConvert orderHisConvert;

	@Autowired
	private PaymentRepository paymentRepos;

	@Autowired
	private ShipmentRepository shipmentRepos;

	@Autowired
	private PaymentMethodRepository paymentMethodRepos;

//	@GetMapping("")
//	public ResponseEntity<List<OrderHisDto>> getAll() {
//		List<Order> result = orderRepository.findAll();
//		List<OrderHisDto> orderDtos = new ArrayList<>();
//		for (Order o : result) {
//			orderDtos.add(orderHisConvert.toDto(o));
//		}
//		return new ResponseEntity<List<OrderHisDto>>(orderDtos, HttpStatus.OK);
//	}

	@GetMapping("/user")
	public ResponseEntity<List<OrderHisDto>> getAllByUser(@RequestParam("username") String username) {
		User user = userRepository.findOneByUsername(username);
		List<Order> result = orderRepository.getAllByUser(user);
		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (Order o : result) {
//			orderDtos.add(orderHisConvert.toDto(o));
			OrderHisDto dto = new OrderHisDto(o);
			orderDtos.add(dto);
		}
		return new ResponseEntity<List<OrderHisDto>>(orderDtos, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<OrderDto> create(@RequestBody OrderDto dto) {
		User user = userRepository.findOneByUsername(dto.getUsername());
		Payment payment = new Payment();
		PaymentDto paymentDto = dto.getPayment();
		PaymentMethodDto methodDto = paymentDto.getMethod();
		PaymentMethod payMethod = paymentMethodRepos.findOneByCode(methodDto.getCode());
		
		ShipmentDto shipDto = dto.getShipment();
		Shipment shipment = shipmentRepos.findOneByCode(shipDto.getCode());

		Order order = new Order();
		order.setCreate_time(dto.getCreate_time());
		order.setOrderInfo(dto.getOrderInfo());
		order.setStatus(0);
		order.setAddress(dto.getAddress());
		order.setTotal_price(dto.getTotal_price());
		order.setUser(user);
		order.setShipment(shipment);

		payment.setBankName(paymentDto.getBankName());
		payment.setDatePayment(paymentDto.getDatePayment());
		payment.setMethod(payMethod);
		payment.setTradingCode(paymentDto.getTradingCode());
		payment.setType(paymentDto.getType());
		payment.setStatus(paymentDto.getStatus());

		order.setPayment(payment);
		payment.setOrder(order);

		order = orderRepository.save(order);
		payment = paymentRepos.save(payment);

		List<OrderDetailDto> orderDetailDtos = dto.getOrderDetailDtos();
		for (OrderDetailDto i : orderDetailDtos) {
			Product product = productRepository.getOne(i.getProduct_id());
			OrderDetail orderDetail = i.toEntity(order, product);
			orderDetailRepository.save(orderDetail);
		}
		return new ResponseEntity<OrderDto>(dto, HttpStatus.OK);
	}

	@GetMapping(value = "/checkCode")
	public ResponseEntity<Boolean> check(@RequestParam("tradingCode") String tradingCode) {
		Long count = 0L;
		if (tradingCode != null && StringUtils.hasText(tradingCode)) {
			count = paymentRepos.checkCode(tradingCode);
		}

		Boolean result = (count != 0L) ? true : false;
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<OrderDto> update(@RequestBody OrderDto dto, @PathVariable Long id) {
		Order order = orderRepository.getOne(id);
		order.setStatus(2);
		orderRepository.save(order);
		return new ResponseEntity<OrderDto>(dto, HttpStatus.OK);
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<OrderDto> cancel(@RequestBody OrderDto dto, @PathVariable Long id) {
		Order order = orderRepository.getOne(id);
		order.setStatus(0);
		orderRepository.save(order);
		return new ResponseEntity<OrderDto>(dto, HttpStatus.OK);
	}

}
