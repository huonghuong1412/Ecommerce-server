package com.example.demo.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.OrderResponse;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.dto.report.ReportCustomer;
import com.example.demo.dto.report.ReportOrderDto;
import com.example.demo.dto.report.ReportProduct;
import com.example.demo.dto.report.ReportProductInventory;
import com.example.demo.entity.user.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import com.example.demo.service.ReportService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/report")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@Autowired
	private OrderService orderService;

	@Qualifier("productServiceImpl")
	@Autowired
	private ProductService productService;

	@Autowired
	private UserRepository userRepos;

	// đếm số lượng đơn hàng theo trạng thái đơn hàng
	@GetMapping("/order/count")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderResponse>> getQuantityByStatus(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "1000") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date,
			@RequestParam(name = "status", defaultValue = "3") Integer status) {
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		dto.setStatus(status);
		Page<OrderHisDto> result = orderService.getAllOrder(dto);

		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_complete = 0, count_shiping = 0, count_wait = 0, count_cancel = 0;
		for (OrderHisDto item : result.toList()) {
			if (item.getStatus_order() == 2) {
				count_complete += 1;
			} else if (item.getStatus_order() == 1) {
				count_shiping += 1;
			} else if (item.getStatus_order() == 0) {
				count_wait += 1;
			} else {
				count_cancel += 1;
			}
		}
		list.add(new OrderResponse("Đã hoàn thành", count_complete));
		list.add(new OrderResponse("Đang giao hàng", count_shiping));
		list.add(new OrderResponse("Đang chờ xác nhận", count_wait));
		list.add(new OrderResponse("Đã huỷ đơn", count_cancel));

//		list.add(new OrderResponse("Đã hoàn thành", orderRepository.countOrderByStatus(2)));
//		list.add(new OrderResponse("Đang giao hàng", orderRepository.countOrderByStatus(1)));
//		list.add(new OrderResponse("Đang chờ xác nhận", orderRepository.countOrderByStatus(0)));
//		list.add(new OrderResponse("Đã huỷ đơn", orderRepository.countOrderByStatus(-1)));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// lấy danh sách sản phẩm ở trong các đơn hàng
	@GetMapping("/product-in-order")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProduct>> reportProductInHistoryOrder(
			@RequestParam(name = "product") Long product_id,
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê các lần bán hàng của sản
																				// phẩm
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportProduct> result = reportService.reportProductByHistoryOrder(product_id, dto);
		return new ResponseEntity<Page<ReportProduct>>(result, HttpStatus.OK);
	}

	// lấy danh sách đặt hàng của khách theo user id
	@GetMapping("/customer_all")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<List<OrderHisDto>> getAllByUserToReport(@RequestParam(name = "user") Long user_id) {
		User u = userRepos.getById(user_id);
		List<OrderHisDto> result = reportService.getAllOrderByUser(u.getUsername());
		return new ResponseEntity<List<OrderHisDto>>(result, HttpStatus.OK);
	}

	// đếm số lượng các loại sản phẩm (đang bán, đã ẩn, đã bán, đã hết hàng)
	@GetMapping(value = "/product/count")
	public ResponseEntity<List<OrderResponse>> countByType(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "1000") int limit,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "sku", defaultValue = "") String sku,
			@RequestParam(name = "category", defaultValue = "") String category,
			@RequestParam(name = "brand", defaultValue = "") String brand,
			@RequestParam(name = "display", defaultValue = "2") Integer display) {
		AdvanceSearchDto dto = new AdvanceSearchDto(page, limit, name, sku, display, brand, category);
		Page<ProductListDto> result = productService.getAllProduct(dto);
		List<OrderResponse> list = new ArrayList<OrderResponse>();
		Integer count_onsale = 0, count_hide = 0, count_inventory = 0;
		Integer sum_sold = 0;
		for (ProductListDto item : result.toList()) {
			sum_sold += item.getSeller_count();
		}
		for (ProductListDto item : result.toList()) {
			if (item.getDisplay() == 1) {
				count_onsale += 1;
			} else if (item.getDisplay() == 0) {
				count_hide += 1;
			}
			if (item.getIn_stock() == 0) {
				count_inventory += 1;
			}
		}
		list.add(new OrderResponse("Đang bán", count_onsale));
		list.add(new OrderResponse("Đã ẩn", count_hide));
		list.add(new OrderResponse("Hết hàng", count_inventory));
		list.add(new OrderResponse("Đã bán", sum_sold));
		return new ResponseEntity<List<OrderResponse>>(list, HttpStatus.OK);
	}

	// thống kê sản phẩm theo đơn hàng (sản phẩm bán chạy)
	@GetMapping("/product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportOrderDto>> reportByProduct(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê theo sản phẩm bán chạy nhất
																				// & doanh thu
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportOrderDto> result = reportService.reportProduct(dto);
		return new ResponseEntity<Page<ReportOrderDto>>(result, HttpStatus.OK);
	}

	// lấy top danh sách khách hàng mua nhiều
	@GetMapping("/customer")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportCustomer>> reportByCustomer(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit,
			@RequestParam(name = "last_date", defaultValue = "0") Integer last_date) { // thống kê theo khách hàng mua nhiều
																				// nhất & doanh thu
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		dto.setLast_date(last_date);
		Page<ReportCustomer> result = reportService.reportCustomer(dto);
		return new ResponseEntity<Page<ReportCustomer>>(result, HttpStatus.OK);
	}

	
	// thống kê danh sách sản phẩm tồn kho
	@GetMapping("/inventory/out-stock")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<Page<ReportProductInventory>> reportProductOutOfStock(
			@RequestParam(name = "page", defaultValue = "0") Integer page,
			@RequestParam(name = "limit", defaultValue = "10") Integer limit) { 
		AdvanceSearchDto dto = new AdvanceSearchDto();
		dto.setPageIndex(page);
		dto.setPageSize(limit);
		Page<ReportProductInventory> result = reportService.reportProductOutOfStock(dto);
		return new ResponseEntity<Page<ReportProductInventory>>(result, HttpStatus.OK);
	}
}
