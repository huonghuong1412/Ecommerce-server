package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.dto.user.ViewedProductDto;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.entity.user.ViewedProduct;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.ViewedProductRepository;
import com.example.demo.service.ViewedProductService;

@Service
public class ViewedProductServiceImpl implements ViewedProductService {

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private ViewedProductRepository viewedProductRepos;

	@Override
	public List<ViewedProductDto> getListByUser(String username) {
		// TODO Auto-generated method stub
		User user = userRepos.findOneByUsername(username);
		List<ViewedProduct> list = viewedProductRepos.getAllByUser(user, Sort.by("createdDate").descending());
		List<ViewedProductDto> dtos = new ArrayList<>();
		for (ViewedProduct p : list) {
			ViewedProductDto dto = new ViewedProductDto(p);
			dtos.add(dto);
		}

		return dtos;
	}

	@Override
	public ViewedProductDto saveOrUpdate(ViewedProductDto dto) {
		if (dto != null) {
			ViewedProduct entity = null;
			User user = userRepos.findOneByUsername(dto.getUsername());
			Product product = productRepos.getById(dto.getProductId());

			if (viewedProductRepos.existsByProduct(product)) {
				return null;
			} else {
				entity = new ViewedProduct();

				entity.setUser(user);
				entity.setProduct(product);
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			}
			entity = viewedProductRepos.save(entity);

			if (entity != null) {
				return new ViewedProductDto(entity);
			}
		}
		return null;
	}

}
