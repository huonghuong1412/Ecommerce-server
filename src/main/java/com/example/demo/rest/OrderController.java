package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;
import com.example.demo.dto.order.PaymentDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.service.OrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private PaymentRepository paymentRepository;

	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<OrderHisDto>> getAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "3") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = service.getAllOrder(dto);
		return new ResponseEntity<Page<OrderHisDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<List<OrderDetailHisDto>> getDetail(@PathVariable Long id) {
		List<OrderDetailHisDto> result = service.getDetailOrderById(id);
		return new ResponseEntity<List<OrderDetailHisDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/detail-full/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<OrderHisFullDto> getDetailFull(@PathVariable Long id) {
		OrderHisFullDto result = service.getDetailOrder(id);
		return new ResponseEntity<OrderHisFullDto>(result, HttpStatus.OK);
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderHisDto>> getAllByUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<OrderHisDto> result = service.getAllOrderByUser(username);

		return new ResponseEntity<List<OrderHisDto>>(result, HttpStatus.OK);
	}

	@PostMapping("")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<OrderDto> create(@Validated @RequestBody OrderDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		OrderDto result = service.createOrder(dto);
		return new ResponseEntity<OrderDto>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/checkCode")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<Boolean> check(@RequestParam("tradingCode") String tradingCode) {
		Boolean result = service.checkTradingCode(tradingCode);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	// xác nhận đơn hàng
	@PutMapping("/confirm/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> confirmOrder(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		if (order.getStatus() == 2) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Bạn đã xác nhận đơn hàng này rồi!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else {
			order.setStatus(2);
//			List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
//			for (OrderDetail i : orderDetails) {
//				if (inventoryRepos.existsByProductId(i.getProduct().getId())) {
//					Inventory inventory = inventoryRepos.getOneByProductId(i.getProduct().getId());
//					inventory.setQuantity_item(inventory.getQuantity_item() - i.getAmount());
//					inventoryRepos.save(inventory);
//				}
//			}
		}

		if (order.getStatus() == -1) {
			payment.setStatus(-1);
		} else if (order.getStatus() == 2) {
			payment.setStatus(1);
		}
		paymentRepository.save(payment);

		orderRepository.save(order);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Xác nhận đơn hàng thành công!"), HttpStatus.OK);
//		return ResponseEntity.ok(new MessageResponse("Xác nhận đơn hàng thành công!"));
	}

	// đang giao hàng
	@PutMapping("/is-shipping/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> shipping(@PathVariable Long id) {
		Order order = orderRepository.getById(id);

		Payment payment = paymentRepository.findOneByOrderId(order.getId());

		if (order.getStatus() == 1) {
			return new ResponseEntity<MessageResponse>(
					new MessageResponse("Đơn hàng đang giao, không cần xác nhận lại!"), HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == -1) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã bị huỷ!"),
					HttpStatus.BAD_REQUEST);
		} else if (order.getStatus() == 2) {
			return new ResponseEntity<MessageResponse>(new MessageResponse("Đơn hàng này đã được xác nhận!"),
					HttpStatus.BAD_REQUEST);
		} else {
			order.setStatus(1);
		}

		if (order.getStatus() == -1) {
			payment.setStatus(-1);
		} else if (order.getStatus() == 2) {
			payment.setStatus(1);
		}
		paymentRepository.save(payment);
		orderRepository.save(order);
		return ResponseEntity.ok(new MessageResponse("Cập nhật trạng thái đơn hàng thành công!"));
	}

	// huỷ đơn hàng
	@PutMapping("/cancel/{id}")
	@PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> cancel(@PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
			if (order.getStatus() == -1) {
				return new ResponseEntity<MessageResponse>(new MessageResponse("Bạn đã huỷ đơn hàng này rồi!"),
						HttpStatus.BAD_REQUEST);
			} else if (order.getStatus() == 2) {
				return new ResponseEntity<MessageResponse>(
						new MessageResponse("Đơn hàng này đã được xác nhận, không thể huỷ!"), HttpStatus.BAD_REQUEST);
			} else if (order.getStatus() == 1) {
				return new ResponseEntity<MessageResponse>(
						new MessageResponse("Đơn hàng này đang được giao, không thể huỷ!"), HttpStatus.BAD_REQUEST);
			} else {
				order.setStatus(-1);
				List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
				for (OrderDetail i : orderDetails) {
					if (inventoryRepos.existsByProductId(i.getProduct().getId())) {
						Inventory inventory = inventoryRepos.getOneByProductId(i.getProduct().getId());
						inventory.setQuantity_item(inventory.getQuantity_item() + i.getAmount());
						inventoryRepos.save(inventory);
					}
				}
			}
		} else {
			if (order.getStatus() == -1) {
				return ResponseEntity.ok(new MessageResponse("Bạn đã huỷ đơn hàng này rồi!"));
			} else if (order.getStatus() == 2) {
				return ResponseEntity.ok(new MessageResponse("Đơn hàng này đã được xác nhận, không thể huỷ!"));
			} else {
				order.setStatus(-1);
				List<OrderDetail> orderDetails = orderDetailRepository.getAllByOrderId(order.getId());
				for (OrderDetail i : orderDetails) {
					if (inventoryRepos.existsByProductId(i.getProduct().getId())) {
						Inventory inventory = inventoryRepos.getOneByProductId(i.getProduct().getId());
						inventory.setQuantity_item(inventory.getQuantity_item() + i.getAmount());
						inventoryRepos.save(inventory);
					}
				}
			}
		}

//		if(order.getPayment().getStatus() == 0) {
//			payment.setStatus(-1);
//		} else if(order.getPayment().getStatus() == 1) {
//			payment.setStatus(2);
//		}

		payment.setStatus(-1);
		paymentRepository.save(payment);

		orderRepository.save(order);
		return ResponseEntity.ok(new MessageResponse("Huỷ đơn hàng thành công!"));
	}

	// Xác nhận thanh toán thành công
	@PutMapping("/pay-success/{id}")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ADMIN')")
	public ResponseEntity<MessageResponse> paySuccess(@RequestBody PaymentDto dto, @PathVariable Long id) {
		Order order = orderRepository.getById(id);
		Payment payment = paymentRepository.findOneByOrderId(order.getId());
		payment.setStatus(1);
		payment.setBankName(dto.getBankName());
		payment.setTradingCode(dto.getTradingCode());
		paymentRepository.save(payment);
		return new ResponseEntity<MessageResponse>(new MessageResponse("Đặt hàng thành công!"), HttpStatus.OK);
	}

	// đang giao hàng
	@PutMapping("/update-order-code/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<MessageResponse> updateOrderCodeGHN(@PathVariable Long id, @RequestBody String order_code) {
		Order order = orderRepository.getById(id);
		order.setOrder_code(order_code);
		orderRepository.save(order);
		return ResponseEntity.ok(new MessageResponse("SUCCESS"));
	}

}
