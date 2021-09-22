package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.order.OrderDetailDto;
import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;
import com.example.demo.dto.order.PaymentDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PaymentRepository paymentRepos;

//	@Autowired
//	private ShipmentRepository shipmentRepos;

	@Autowired
	private PaymentMethodRepository paymentMethodRepos;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Override
	public Page<OrderHisDto> getAllOrder(Integer page, Integer limit, String sortBy) {
		Page<Order> orders = orderRepository.findAll(PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<OrderHisDto> dtos = orders.map(item -> new OrderHisDto(item));
		return dtos;
	}

	@Override
	public List<OrderHisDto> getAllOrderByUser(String username) {
		User user = userRepository.findOneByUsername(username);
		List<Order> result = orderRepository.getAllByUser(user, Sort.by(Sort.Direction.DESC, "createdDate"));
		
		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (Order o : result) {
			OrderHisDto dto = new OrderHisDto(o);
			orderDtos.add(dto);
		}
		return orderDtos;
	}

	@Override
	public OrderDto createOrder(OrderDto dto) {
		if (dto != null) {
			User user = userRepository.findOneByUsername(dto.getUsername());
			Payment payment = new Payment();
			PaymentDto paymentDto = dto.getPayment();
			PaymentMethod payMethod = paymentMethodRepos.findOneByCode(paymentDto.getMethod_code());

			Order order = new Order();
			order.setCreate_time(dto.getCreate_time());
			order.setOrderInfo(dto.getOrderInfo());
			order.setStatus(0);
			order.setAddress(dto.getAddress());
			order.setPhone(dto.getPhone());
			order.setTotal_price(dto.getTotal_price());
			order.setTotal_item(dto.getTotal_item());
			order.setUser(user);

			payment.setBankName(paymentDto.getBankName());
			payment.setDatePayment(paymentDto.getDatePayment());
			payment.setMethod(payMethod);
			payment.setTradingCode(paymentDto.getTradingCode());
			switch (paymentDto.getStatus()) {
			case 0:
				payment.setStatus(0);
				break;
			case 1:
				payment.setStatus(1);
				break;
			default:
				payment.setStatus(-1);
				break;
			}

			order.setPayment(payment);
			payment.setOrder(order);

			order = orderRepository.save(order);
			payment = paymentRepos.save(payment);

			List<OrderDetailDto> orderDetailDtos = dto.getOrder_details();
			for (OrderDetailDto i : orderDetailDtos) {
				Product product = productRepository.getById(i.getProduct_id());

				if (inventoryRepos.existsByProductId(i.getProduct_id())) {
					Inventory inventory = inventoryRepos.getOneByProductId(i.getProduct_id());
					inventory.setQuantity_item(inventory.getQuantity_item() - i.getAmount());
					inventoryRepos.save(inventory);
				}

				OrderDetail orderDetail = i.toEntity(order, product);
				orderDetailRepository.save(orderDetail);
			}
			return new OrderDto(order);
		}
		return null;
	}

	@Override
	public Boolean checkTradingCode(String tradingCode) {
		Long count = 0L;
		if (tradingCode != null && StringUtils.hasText(tradingCode)) {
			count = paymentRepos.checkCode(tradingCode);
		}

		Boolean result = (count != 0L) ? true : false;
		return result;
	}

	@Override
	public List<OrderDetailHisDto> getDetailOrderById(Long id) {
		// TODO Auto-generated method stub
		
		List<OrderDetail> details = orderDetailRepository.getAllByOrderId(id);
		List<OrderDetailHisDto> dtos = new ArrayList<>();
		for(OrderDetail detail : details) {
			OrderDetailHisDto dto = new OrderDetailHisDto(detail);
			dtos.add(dto);
		}
		
		return dtos;
	}

	@Override
	public OrderHisFullDto getDetailOrder(Long id) {
		// TODO Auto-generated method stub
		if(id != null) {
			Order order = orderRepository.getById(id);
			OrderHisFullDto dto = new OrderHisFullDto(order);
			return dto;
		}
		return null;
	}
	
	@Override
	public Integer getQuantityProductSeller(Long product_id) {
		// TODO Auto-generated method stub
		List<OrderDetail> orders = orderDetailRepository.getAllByProductId(product_id);
		Integer count_seller = 0;
		for(OrderDetail order : orders) {
			count_seller += order.getAmount();
		}
		return count_seller;
	}

}
