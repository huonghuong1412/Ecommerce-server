package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.CartResponse;
import com.example.demo.dto.order.CartDetailDto;
import com.example.demo.dto.order.CartDto;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.order.CartDetail;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.user.User;
import com.example.demo.repository.CartDetailRepository;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.InventoryRepository;
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

	@Autowired
	private ColorRepository colorRepos;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Override
	public CartResponse createCart(CartDto dto) {
		if (dto != null) {
			Cart cart = null;
			User user = userRepos.findOneByUsername(dto.getUsername());
			if (cartRepos.existsByUser(user)) {
				List<CartDetailDto> cartDetailDtos = dto.getCart_details();
				List<CartDetail> cartDetails = new ArrayList<>();
				cart = cartRepos.getOneByUser(user);
				for (CartDetailDto item : cartDetailDtos) {
					Product product = productRepos.getById(item.getProduct_id());
//					Color color = colorRepos.findOneByName(item.getColor());
					Color color = null;
					if(item.getColor() != null && item.getColor().equalsIgnoreCase("") == false) {
						color = colorRepos.findOneByName(item.getColor());
					} else {
						color = colorRepos.findOneByName(product.getInventories().get(0).getColor().getName());
					}
					CartDetail cartDetailEntity = null;
					Inventory inv = inventoryRepos.getOneByProductAndColor(product, color);
					Integer quantity_in_stock = inv.getQuantity_item();
					if (cartDetailRepos.existsByProductAndCart(product, cart)) {
						cartDetailEntity = cartDetailRepos.getByProductAndCart(product, cart);
						if (cartDetailEntity.getQuantity() + item.getQuantity() > quantity_in_stock) {
							return new CartResponse("Sản phẩm " + product.getName() + " chỉ còn lại "
									+ quantity_in_stock + " sản phẩm");
						} else {
							cartDetailEntity.setQuantity(item.getQuantity() + cartDetailEntity.getQuantity());
						}
					} else {
						cartDetailEntity = new CartDetail();
						if (item.getQuantity() > quantity_in_stock) {
							return new CartResponse("Sản phẩm " + product.getName() + " chỉ còn lại "
									+ quantity_in_stock + " sản phẩm");
						} else {
							cartDetailEntity.setQuantity(item.getQuantity());
						}
					}
					cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
					cartDetailEntity.setProduct(product);
					cartDetailEntity.setCart(cart);
					cartDetailEntity.setColor(color.getName());
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
				return new CartResponse("Thêm vào giỏ hàng thành công");
			} else {
				cart = new Cart();
				List<CartDetailDto> cartDetailDtos = dto.getCart_details();
				List<CartDetail> cartDetails = new ArrayList<>();
				for (CartDetailDto item : cartDetailDtos) {
					Product product = productRepos.getById(item.getProduct_id());
//					Color color = colorRepos.findOneByName(item.getColor());
					Color color = null;
					if(item.getColor() != null && item.getColor().equalsIgnoreCase("") == false) {
						color = colorRepos.findOneByName(item.getColor());
					} else {
						color = colorRepos.findOneByName(product.getInventories().get(0).getColor().getName());
					}
					CartDetail cartDetailEntity = new CartDetail();
					cartDetailEntity.setQuantity(item.getQuantity());
					cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
					cartDetailEntity.setProduct(product);
					cartDetailEntity.setColor(color.getName());
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
				return new CartResponse("Thêm vào giỏ hàng thành công");
			}
		}
		return null;
	}

	@Override
	public CartResponse updateCart(CartDto dto) {
		if (dto != null) {
			Cart cart = null;
			User user = userRepos.findOneByUsername(dto.getUsername());
			List<CartDetailDto> cartDetailDtos = dto.getCart_details();
			List<CartDetail> cartDetails = new ArrayList<>();
			cart = cartRepos.getOneByUser(user);
			for (CartDetailDto item : cartDetailDtos) {
				Product product = productRepos.getById(item.getProduct_id());
//				Color color = colorRepos.findOneByName(item.getColor());
				Color color = null;
				if(item.getColor() != null && item.getColor().equalsIgnoreCase("") == false) {
					color = colorRepos.findOneByName(item.getColor());
				} else {
					color = colorRepos.findOneByName(product.getInventories().get(0).getColor().getName());
				}
				Inventory inv = inventoryRepos.getOneByProductAndColor(product, color);
				CartDetail cartDetailEntity = null;
				Integer quantity_in_stock = inv.getQuantity_item();
				if (cartDetailRepos.existsByProductAndCart(product, cart)) {
					cartDetailEntity = cartDetailRepos.getByProductAndCart(product, cart);
//					cartDetailEntity.setQuantity(item.getQuantity());
					if (quantity_in_stock == 0) {
						return new CartResponse("Sản phẩm " + product.getName() + " tạm hết hàng");
					} else if (item.getQuantity() > quantity_in_stock) {
						return new CartResponse(
								"Sản phẩm " + product.getName() + " chỉ còn lại " + quantity_in_stock + " sản phẩm");
					} else if (item.getQuantity() <= 0) {
						return new CartResponse("Số lượng tổi thiểu là 1.");
					} else {
						cartDetailEntity.setQuantity(item.getQuantity());
					}
				} else {
					cartDetailEntity = new CartDetail();
//					cartDetailEntity.setQuantity(item.getQuantity());
					if (quantity_in_stock == 0) {
						return new CartResponse("Sản phẩm " + product.getName() + " tạm hết hàng");
					} else if (item.getQuantity() > quantity_in_stock) {
						return new CartResponse(
								"Sản phẩm " + product.getName() + " chỉ còn lại " + quantity_in_stock + " sản phẩm");
					} else if (item.getQuantity() <= 0) {
						return new CartResponse("Số lượng tổi thiểu là 1.");
					} else {
						cartDetailEntity.setQuantity(item.getQuantity());
					}
				}
				cartDetailEntity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				cartDetailEntity.setProduct(product);
				cartDetailEntity.setColor(color.getName());
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
			return new CartResponse("SUCCESS");
		}
		return null;
	}

	@Override
	public CartDto getCartByUser(String username) {
		// TODO Auto-generated method stub
		if (username != null) {
			User user = userRepos.findOneByUsername(username);
			Cart cart = cartRepos.getOneByUser(user);
			CartDto dto = new CartDto(cart);
			List<CartDetailDto> details = dto.getCart_details();
			Integer total_weight = 0, total_length = 0, total_width = 0, total_height = 0;
			for (CartDetailDto item : details) {
				Product p = productRepos.getById(item.getProduct_id());
				total_weight += p.getWeight();
				total_length += p.getLength();
				total_width += p.getWidth();
				total_height += p.getHeight();
			}
			dto.setWeight(total_weight);
			dto.setLength(total_length);
			dto.setWidth(total_width);
			dto.setHeight(total_height);
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
	public CartResponse deleteCartDetail(String username, Long product_id) {
		// TODO Auto-generated method stub
		User user = userRepos.findOneByUsername(username);
		Cart cart = cartRepos.getOneByUser(user);
		List<CartDetail> cartDetails = cartDetailRepos.findAllByCart(cart);
		for (CartDetail item : cartDetails) {
			Product product = productRepos.getById(product_id);
//			Color color = colorRepos.findOneByName(item.getColor().getName());
			if (item.getProduct().getId() == product_id) {
				CartDetail detail = cartDetailRepos.getByProductAndCart(product, cart);
				cartDetailRepos.delete(detail);
				return new CartResponse("Xoá sản phẩm thành công!");
			}
		}
		return new CartResponse("Xoá sản phẩm không thành công. Mời thực hiện lại.");
	}

	@Override
	public Integer getQuantityItemByUser(String username) { // Số lượng sản phẩm trong giỏ hàng (tính theo đầu sản phẩm)
		User user = userRepos.findOneByUsername(username);
		Cart cart = cartRepos.getOneByUser(user);
		return cart.getCart_details().size();
	}

	@Override
	public Integer getQuantityProductByUser(String username) { // số lượng sản phẩm, tính theo quantity mỗi sản phẩm
																// trong giỏ hàng
		User user = userRepos.findOneByUsername(username);
		Cart cart = cartRepos.getOneByUser(user);
		List<CartDetail> cartDetails = cartDetailRepos.findAllByCart(cart);
		Integer quantity = 0;
		for (CartDetail item : cartDetails) {
			quantity += item.getQuantity();
		}
		return quantity;
	}

	@Override
	public CartResponse checkItemQuantity(CartDto dto) {
		if (dto != null) {
			Cart cart = null;
			User user = userRepos.findOneByUsername(dto.getUsername());
			List<CartDetailDto> cartDetailDtos = dto.getCart_details();
			cart = cartRepos.getOneByUser(user);
			for (CartDetailDto item : cartDetailDtos) {
				Product product = productRepos.getById(item.getProduct_id());
				Color color = colorRepos.findOneByName(item.getColor());
				if (cartDetailRepos.existsByProductAndCart(product, cart)) {
					Inventory inv = inventoryRepos.getOneByProductAndColor(product, color);
					if (inv.getQuantity_item() == 0) {
						return new CartResponse(
								"Sản phẩm " + product.getName() + " tạm hết hàng. Vui lòng thao tác lại!");
					} else if (item.getQuantity() > inv.getQuantity_item()) {
						return new CartResponse("Sản phẩm " + product.getName() + " chỉ được mua "
								+ inv.getQuantity_item() + " sản phẩm");
					} else {

					}
				}
			}
			return new CartResponse("SUCCESS");
		}
		return null;
	}

	@Override
	public Boolean deleteAllCartDetail(String username) {
		// TODO Auto-generated method stub
		User user = userRepos.findOneByUsername(username);
		Cart cart = cartRepos.getOneByUser(user);
		List<CartDetail> details = cart.getCart_details();
		cartDetailRepos.deleteByCartId(cart.getId());
		if (details.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
