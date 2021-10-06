package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.order.OrderHisDto;
import com.example.demo.dto.report.ReportCustomer;
import com.example.demo.dto.report.ReportOrderDto;
import com.example.demo.dto.report.ReportProduct;
import com.example.demo.dto.report.ReportProductInventory;

@Service
public interface ReportService {
public List<OrderHisDto> getAllOrderByUser(String username);
	
	// thống kê sản phẩm bản chạy theo khoang thời gian
	public Page<ReportOrderDto> reportProduct(AdvanceSearchDto dto);
	
	// thông ke khách hàng đã mua sản phẩm theo khoảng thời gian
	public Page<ReportCustomer> reportCustomer(AdvanceSearchDto dto);
	
	// thống kê sản phẩn đang nằm trong đơn đặt hàng nào đó
	public Page<ReportProduct> reportProductByHistoryOrder(Long product_id, AdvanceSearchDto dto);

	// thống kê sản phẩm hết hàng
	public Page<ReportProductInventory> reportProductOutOfStock(AdvanceSearchDto dto);
	
}
