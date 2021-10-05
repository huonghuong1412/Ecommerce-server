package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.order.OrderDetailDto;
import com.example.demo.dto.order.OrderDetailHisDto;
import com.example.demo.dto.order.OrderDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.order.OrderHisFullDto;
import com.example.demo.dto.order.PaymentDto;
import com.example.demo.dto.report.ReportCustomer;
import com.example.demo.dto.report.ReportOrderDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.Order;
import com.example.demo.entity.order.OrderDetail;
import com.example.demo.entity.order.Payment;
import com.example.demo.entity.order.PaymentMethod;
import com.example.demo.entity.order.Shipment;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PaymentMethodRepository;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShipmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private EntityManager manager;

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

	@Autowired
	private ShipmentRepository shipmentRepos;

	@Autowired
	private PaymentMethodRepository paymentMethodRepos;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Override
	public Page<OrderHisDto> getAllOrder(AdvanceSearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.createdDate ";
		String sqlCount = "select count(entity.id) from  Order as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.order.OrderHisDto(entity) from  Order as entity where (1=1) ";

		if (dto.getStatus() == -1 || dto.getStatus() == 0 || dto.getStatus() == 1 || dto.getStatus() == 2) {
			whereClause += " AND ( entity.status = " + dto.getStatus() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getLast_date() != null
				&& (dto.getLast_date() == 1 || dto.getLast_date() == 7 || dto.getLast_date() == 30)) {
			whereClause += " AND (TIMESTAMPDIFF(DAY, entity.createdDate, NOW()) <= " + dto.getLast_date() + " )";
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

//		System.out.println(sql);

		Query q = manager.createQuery(sql, OrderHisDto.class);
		Query qCount = manager.createQuery(sqlCount);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<OrderHisDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (OrderHisDto o : entities) {
			Order order = orderRepository.getById(o.getId());
			Integer quantity = order.getOrderDetails().size() - 1;
			if (quantity > 0) {
				o.setDescription(
						order.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				o.setDescription(order.getOrderDetails().get(0).getProduct().getName());
			}
			if (dto != null) {
				orderDtos.add(o);
			}
		}

		Page<OrderHisDto> result = new PageImpl<OrderHisDto>(entities, pageable, count);
		return result;

	}

	@Override
	public List<OrderHisDto> getAllOrderByUser(String username) {
		User user = userRepository.findOneByUsername(username);
		List<Order> result = orderRepository.getAllByUser(user, Sort.by(Sort.Direction.DESC, "createdDate"));

		List<OrderHisDto> orderDtos = new ArrayList<>();
		for (Order o : result) {
			OrderHisDto dto = new OrderHisDto(o);
			Integer quantity = o.getOrderDetails().size() - 1;
			if (quantity > 0) {
				dto.setDescription(
						o.getOrderDetails().get(0).getProduct().getName() + " và " + quantity + " sản phẩm khác");
			} else {
				dto.setDescription(o.getOrderDetails().get(0).getProduct().getName());
			}
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

			String shipCode = dto.getShipment();
			Shipment ship = shipmentRepos.findOneByCode(shipCode);

			Order order = new Order();
			order.setOrderInfo(dto.getOrderInfo());
			order.setStatus(0);
			order.setAddress(dto.getAddress());
			order.setPhone(dto.getPhone());
			order.setTotal_price(dto.getTotal_price());
			order.setTotal_item(dto.getTotal_item());
			order.setShipment(ship);
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
		for (OrderDetail detail : details) {
			OrderDetailHisDto dto = new OrderDetailHisDto(detail);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public OrderHisFullDto getDetailOrder(Long id) {
		// TODO Auto-generated method stub
		if (id != null) {
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
		for (OrderDetail order : orders) {
			if (order.getOrder().getStatus() == 2) {
				count_seller += order.getAmount();
			}
		}
		return count_seller;
	}

	@Override
	public Page<ReportOrderDto> reportOrder(AdvanceSearchDto dto) {
//		SELECT product_id, sum(amount) As MostSold 
//		FROM tbl_order_detail, tbl_order
//		where tbl_order.status=2
//		AND tbl_order_detail.order_id = tbl_order.id
//		Group By product_id
//		ORDER BY MostSold DESC limit 5

//		SELECT p.name as product_name, o.id as order_id, SUM(s.amount) as Quantity 
//		FROM tbl_order_detail s
//		INNER JOIN tbl_product p ON s.product_id = p.id 
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		GROUP BY s.product_id 
//		ORDER BY Quantity DESC limit 5

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = "";
		String sqlCount = "select count(*) from OrderDetail as s " + "INNER JOIN Product p ON s.product.id = p.id "
				+ "INNER JOIN Order o ON o.status = 2 and o.id = s.order.id " + "GROUP BY s.product.id";
		String sql = "select new com.example.demo.dto.report.ReportOrderDto(p.id as id, p.name as product_name, "
				+ "p.sku as product_sku, p.category.name as product_category, p.brand.name as product_brand, "
				+ " o.id as order_id, " + " SUM(s.amount) as quantity_sold, "
				+ " SUM(s.amount * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Product p ON s.product.id = p.id"
				+ " INNER JOIN Order o ON o.status = 2 and o.id = s.order.id"
				+ " GROUP BY s.product.id ORDER BY quantity_sold DESC";
		sql += whereClause;

		Query q = manager.createQuery(sql, ReportOrderDto.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);
		long count = (long) qCount.getResultList().size();

		@SuppressWarnings("unchecked")
		List<ReportOrderDto> entities = q.getResultList();

		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportOrderDto> result = new PageImpl<ReportOrderDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ReportCustomer> reportCustomer(AdvanceSearchDto dto) {

//		SELECT u.fullname, u.phone, o.id as order_id, SUM(s.amount) as Quantity, SUM(s.amount * s.price) as total_price 
//		FROM tbl_order_detail s
//		INNER JOIN tbl_order o ON o.status = 2 and o.id = s.order_id
//		INNER JOIN tbl_user u ON u.id = o.user_id
//		GROUP BY o.user_id
//		ORDER BY Quantity DESC
//		limit 5

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;
		String whereClause = "";
		String sqlCount = "select count(*) from OrderDetail as s "
				+ "INNER JOIN Order o ON o.status = 2 and o.id = s.order.id " + "INNER JOIN User u ON u.id = o.user.id "
				+ "GROUP BY o.user.id";
		String sql = "select new com.example.demo.dto.report.ReportCustomer(u.id as id, u.fullname as csutomer_name, "
				+ "u.phone as customer_phone," + " o.id as order_id, " + " SUM(s.amount) as quantity_buy, "
				+ " SUM(s.amount * s.price) as total_price)" + " from OrderDetail as s "
				+ " INNER JOIN Order o ON o.status = 2 and o.id = s.order.id" + " INNER JOIN User u ON u.id = o.user.id"
				+ " GROUP BY o.user.id ORDER BY quantity_buy DESC";
		sql += whereClause;

		Query q = manager.createQuery(sql, ReportCustomer.class);
		Query qCount = manager.createQuery(sqlCount);
		q.setMaxResults(pageSize);

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ReportCustomer> entities = q.getResultList();
		long count = (long) qCount.getResultList().size();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);

		Page<ReportCustomer> result = new PageImpl<ReportCustomer>(entities, pageable, count);
		return result;
	}

}
