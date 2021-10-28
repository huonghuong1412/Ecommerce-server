package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.inventory.InventoryDetailDto;
import com.example.demo.dto.inventory.InventoryDto;
import com.example.demo.dto.inventory.InventoryDtoNew;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.inventory.InventoryDetail;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.InventoryDetailRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.InventoryService;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private InventoryDetailRepository inventoryDetailRepos;

	@Autowired
	private ProductRepository productRepos;
	
	@Autowired
	private ColorRepository colorRepos;

	@Override
	public Page<InventoryDtoNew> getList(SearchDto dto) {

		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from Inventory as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.inventory.InventoryDtoNew(entity) from Inventory as entity where (1=1)  ";
//		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
//			whereClause += " AND ( entity.name LIKE :text OR entity.description LIKE :text )";
//		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category_code LIKE :category )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, InventoryDtoNew.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		if (dto.getCategory() != null) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<InventoryDtoNew> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<InventoryDtoNew> result = new PageImpl<InventoryDtoNew>(entities, pageable, count);
		return result;
	}

	@Override
	public InventoryDto importOrUpdate(InventoryDto dto) {

		if (dto != null) {
			Inventory inventory = null;
			InventoryDetail inventoryDetail = null;

			Product product = productRepos.getById(dto.getProductId());
			Color color = colorRepos.findOneByName(dto.getColor());

			if (inventoryRepos.existsByProductAndColor(product, color)) {
				inventory = inventoryRepos.getById(dto.getId());
				inventory.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
				List<InventoryDetailDto> inventoryDetailDtos = dto.getInventory_details();
				List<InventoryDetail> inventoryDetails = new ArrayList<>();
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					inventoryDetail = new InventoryDetail();
					inventoryDetail.setImportDate(new Timestamp(new Date().getTime()).toString());
					inventoryDetail.setImport_quantity(detailDto.getImport_quantity());
					inventoryDetail.setNote(detailDto.getNote());
					inventoryDetail.setOriginal_price(detailDto.getOriginal_price());
					inventoryDetails.add(inventoryDetail);
				}

				Integer totalImport = 0;
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					totalImport += detailDto.getImport_quantity();
				}

				inventory.setTotal_import_item(totalImport + inventory.getTotal_import_item());
				inventory.setQuantity_item(inventory.getQuantity_item() + totalImport);

				inventory.setInventory_details(inventoryDetails);
				for (InventoryDetail detail : inventoryDetails) {
					detail.setInventory(inventory);
				}
				inventoryDetail = inventoryDetailRepos.save(inventoryDetail);
			} else {
				inventory = new Inventory();
				inventory.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				inventory.setProduct(product);
				List<InventoryDetailDto> inventoryDetailDtos = dto.getInventory_details();
				List<InventoryDetail> inventoryDetails = new ArrayList<>();
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					inventoryDetail = new InventoryDetail();
					inventoryDetail.setImportDate(new Timestamp(new Date().getTime()).toString());
					inventoryDetail.setImport_quantity(detailDto.getImport_quantity());
					inventoryDetail.setNote(detailDto.getNote());
					inventoryDetail.setOriginal_price(detailDto.getOriginal_price());
					inventoryDetails.add(inventoryDetail);
				}

				Integer totalImport = 0;
				for (InventoryDetailDto detailDto : inventoryDetailDtos) {
					totalImport += detailDto.getImport_quantity();
				}

				inventory.setTotal_import_item(totalImport);
				inventory.setQuantity_item(totalImport);
				inventory.setInventory_details(inventoryDetails);
				for (InventoryDetail detail : inventoryDetails) {
					detail.setInventory(inventory);
				}
				inventoryDetail = inventoryDetailRepos.save(inventoryDetail);
			}
			inventory = inventoryRepos.save(inventory);

			return new InventoryDto(inventory);

		}

		return null;
	}

	@Override
	public List<InventoryDetailDto> getDetailInventoryById(Long id) {

		List<InventoryDetail> details = inventoryDetailRepos.getAllByInventoryId(id);

		List<InventoryDetailDto> dtos = new ArrayList<>();

		for (InventoryDetail detail : details) {
			InventoryDetailDto dto = new InventoryDetailDto(detail);
			dtos.add(dto);
		}

		return dtos;
	}

}
