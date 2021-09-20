package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.order.CartDetailDto;
import com.example.demo.dto.order.CartDto;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CartService;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	private CartRepository cartRepos;

	@Autowired
	private CartDetailRepository cartDetailRepos;

	@Autowired
	private UserRepository userRepos;

	@Autowired
	private ProductRepository productRepos;

	@Override
	public CartDto createCart(CartDto dto) {
		if (dto != null) {
			Cart cart = null;
			User user = userRepos.getById(dto.getUser_id());
			if (cartRepos.existsByUser(user)) {
				List<CartDetailDto> cartDetailDtos = dto.getCart_details();
				List<CartDetail> cartDetails = new ArrayList<>();
				cart = cartRepos.getOneByUser(user);
				for (CartDetailDto item : cartDetailDtos) {
					Product product = productRepos.getById(item.getProduct_id());
					CartDetail cartDetailEntity = null;
					if (cartDetailRepos.existsByProductAndCart(product,cart)) {
						cartDetailEntity = cartDetailRepos.getByProductAndCart(product, cart);
						cartDetailEntity.setQuantity(item.getQuantity() + cartDetailEntity.getQuantity());
					} else {
						cartDetailEntity = new CartDetail();
						cartDetailEntity.setQuantity(item.getQuantity());
					}
					cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
					cartDetailEntity.setProduct(product);
					cartDetailEntity.setCart(cart);
					cartDetails.add(cartDetailEntity);
				}
				cart.setUser(user);
				cart.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				cart.setCart_details(cartDetails);
				user.setCart(cart);

				cartRepos.save(cart);
				for (CartDetail item : cartDetails) {
					cartDetailRepos.save(item);
				}
				return new CartDto(cart);
			} else {
				cart = new Cart();
				List<CartDetailDto> cartDetailDtos = dto.getCart_details();
				List<CartDetail> cartDetails = new ArrayList<>();
				for (CartDetailDto item : cartDetailDtos) {
					Product product = productRepos.getById(item.getProduct_id());
					CartDetail cartDetailEntity = new CartDetail();
					cartDetailEntity.setQuantity(item.getQuantity());
					cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
					cartDetailEntity.setProduct(product);
					cartDetailEntity.setCart(cart);
					cartDetails.add(cartDetailEntity);
				}
				cart.setUser(user);
				cart.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				cart.setCart_details(cartDetails);
				user.setCart(cart);

				cartRepos.save(cart);
				for (CartDetail item : cartDetails) {
					cartDetailRepos.save(item);
				}
				return new CartDto(cart);
			}
		}
		return null;
	}

	@Override
	public CartDto updateCart(CartDto dto) {
		if (dto != null) {
			Cart cart = null;
			User user = userRepos.getById(dto.getUser_id());
			List<CartDetailDto> cartDetailDtos = dto.getCart_details();
			List<CartDetail> cartDetails = new ArrayList<>();
			cart = cartRepos.getOneByUser(user);
			for (CartDetailDto item : cartDetailDtos) {
				Product product = productRepos.getById(item.getProduct_id());
				CartDetail cartDetailEntity = null;
				if (cartDetailRepos.existsByProductAndCart(product, cart)) {
					cartDetailEntity = cartDetailRepos.getByProductAndCart(product, cart);
					cartDetailEntity.setQuantity(item.getQuantity());
				} else {
					cartDetailEntity = new CartDetail();
					cartDetailEntity.setQuantity(item.getQuantity());
				}
				cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				cartDetailEntity.setProduct(product);
				cartDetailEntity.setCart(cart);
				cartDetails.add(cartDetailEntity);
			}
			cart.setUser(user);
			cart.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			cart.setCart_details(cartDetails);
			user.setCart(cart);

			cartRepos.save(cart);
			for (CartDetail item : cartDetails) {
				cartDetailRepos.save(item);
			}
			return new CartDto(cart);
		}
		return null;
	}

	@Override
	public CartDto getCartByUser(Long user_id) {
		// TODO Auto-generated method stub
		if (user_id != null) {
			User user = userRepos.getById(user_id);
			Cart cart = cartRepos.getOneByUser(user);
			CartDto dto = new CartDto(cart);
			return dto;
		}
		return null;
	}

	@Override
	public CartDetail getCartDetailByProductAndCart(Product product, Cart cart) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteCartDetail(Long user_id, Long product_id) {
		// TODO Auto-generated method stub
		User user = userRepos.getById(user_id);
		Cart cart = cartRepos.getOneByUser(user);
		List<CartDetail> cartDetails = cartDetailRepos.findAllByCart(cart);
		for (CartDetail item : cartDetails) {
			Product product = productRepos.getById(product_id);
			if (item.getProduct().getId() == product_id) {
				CartDetail detail = cartDetailRepos.getByProductAndCart(product, cart);
				cartDetailRepos.delete(detail);
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer getQuantityItemByUser(Long user_id) {	// Số lượng sản phẩm trong giỏ hàng (tính theo đầu sản phẩm)
		User user = userRepos.getById(user_id);
		Cart cart = cartRepos.getOneByUser(user);
		return cart.getCart_details().size();
	}
	
	@Override
	public Integer getQuantityProductByUser(Long user_id) {	// số lượng sản phẩm, tính theo quantity mỗi sản phẩm trong giỏ hàng
		User user = userRepos.getById(user_id);
		Cart cart = cartRepos.getOneByUser(user);
		List<CartDetail> cartDetails = cartDetailRepos.findAllByCart(cart);
		Integer quantity = 0;
		for(CartDetail item : cartDetails) {
			quantity += item.getQuantity();
		}
		return quantity;
	}

}
