package com.example.demo.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.common.Slug;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.to_entity.ProductDto;
import com.example.demo.dto.to_show.ProductDtoNew;
import com.example.demo.dto.to_show.ProductListDto;
import com.example.demo.entity.Category;
import com.example.demo.entity.SubCategory;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Color;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.ProductDetail;
import com.example.demo.entity.product.Publisher;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ColorRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ProductDetailRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private EntityManager manager;

	@Autowired
	private ProductRepository productRepos;

	@Autowired
	private AuthorRepository authorRepos;

	@Autowired
	private PublisherRepository publisherRepos;

	@Autowired
	private CategoryRepository categoryRepos;

	@Autowired
	private SubCategoryRepository subcategoryRepos;

	@Autowired
	private ImageRepository imageRepos;

	@Autowired
	private BrandRepository brandRepos;

	@Autowired
	private ProductDetailRepository productDetailRepos;

	@Autowired
	private ColorRepository colorRepos;

	@Override
	public Page<ProductDto> searchByPage(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.product.ProductDto(entity) from  Product as entity where (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.description LIKE :text )";
		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :category )";
		}
		if (dto.getSubcategory() != null) {
			whereClause += " AND ( entity.subcategory.code LIKE :subcategory )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ProductDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		if (dto.getCategory() != null) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getSubcategory() != null) {
			q.setParameter("subcategory", dto.getSubcategory());
			qCount.setParameter("subcategory", dto.getSubcategory());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductDto> result = new PageImpl<ProductDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			productRepos.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public ProductDtoNew getProductById(Long id) {
		Product product = productRepos.getOne(id);
		ProductDtoNew dto = new ProductDtoNew(product);
		return dto;
	}

	@Override
	public Page<ProductListDto> productList(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.createdDate DESC";
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.to_show.ProductListDto(entity) from  Product as entity where (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text OR entity.description LIKE :text )";
		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :category )";
		}
		if (dto.getSubcategory() != null) {
			whereClause += " AND ( entity.subcategory.code LIKE :subcategory )";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ProductListDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			q.setParameter("text", '%' + dto.getKeyword() + '%');
			qCount.setParameter("text", '%' + dto.getKeyword() + '%');
		}

		if (dto.getCategory() != null) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getSubcategory() != null) {
			q.setParameter("subcategory", dto.getSubcategory());
			qCount.setParameter("subcategory", dto.getSubcategory());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductListDto> entities = q.getResultList();

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductListDto> result = new PageImpl<ProductListDto>(entities, pageable, count);
		return result;
	}

	@Override
	public ProductDto importBook(ProductDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto importFood(ProductDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto importPhone(ProductDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductDto importLaptop(ProductDto dto) {
		if (dto != null) {
			Product entity = null;
			Image image = null;
			Category category = categoryRepos.findOneByCode(dto.getCategory());
			SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategory());
			Publisher publisher = publisherRepos.findOneByCode(dto.getPublisher());
			Brand brand = brandRepos.findOneByCode(dto.getBrand());
			Color color = null;
			ProductDetail productDetail = null;

			// 1 - n product - image
			List<String> imageUrls = dto.getImages();
			List<Image> images = new ArrayList<>();

//			List<ProductDetailDto> listProductColorDtos = dto.getProduct_specs();
			List<ProductDetail> listProductColors = new ArrayList<>();

			if (dto.getId() != null) {
				entity = productRepos.getOne(dto.getId());
				images = imageRepos.findAllByProductId(entity.getId());
				listProductColors = productDetailRepos.findAllByProductId(entity.getId());
//
//				for (ProductDetail detail : listProductColors) {
//					productDetailRepos.deleteByProductId(detail.getProduct().getId());
//				}

			}
			if (entity == null) {
				entity = new Product();

				for (String imageUrl : imageUrls) {
					image = new Image(imageUrl);
					images.add(image);
				}
			}

//			for (ProductDetailDto item : listProductColorDtos) {
//				Integer quantityInStock = productDetailRepos.getQuantityByProductIdAndColorId(30L, 1L);
//				color = colorRepos.findOneByColor(item.getColor().getColor());
//				productDetail = new ProductDetail(entity, color, item.getQuantity());
//				listProductColors.add(productDetail);
//			}

			entity.setType(dto.getType());
			entity.setName(dto.getName());
			entity.setSlug(Slug.makeSlug(dto.getName()));
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			entity.setCategory(category);
			entity.setSubcategory(subcategory);

			// electric
			entity.setBrand(brand);
			entity.setType(dto.getType());
			entity.setScreen(dto.getScreen());
			entity.setOperatorSystem(dto.getOperatorSystem());
			entity.setRam(dto.getRam());
			entity.setPin(dto.getPin());
			entity.setDesign(dto.getDesign());
			entity.setSizeWeight(dto.getSizeWeight());
			entity.setMaterial(dto.getMaterial());
			entity.setReleaseTime(dto.getReleaseTime());

			// laptop
			entity.setCard(dto.getCard());
			entity.setCpu(dto.getCpu());
			entity.setHardWare(dto.getHardWare());
			entity.setSpecial(dto.getSpecial());
			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
			entity.setImages(images);
			for (Image item : images) {
				item.setProduct(entity);
			}

//			for (ProductDetailDto item : listProductColorDtos) {
//				color = colorRepos.findOneByColor(item.getColor().getColor());
//				productDetail.setProduct(entity);
//				productDetail.setColor(color);
//			}

			entity = productRepos.save(entity);

			for (ProductDetail item : listProductColors) {
				productDetail = productDetailRepos.save(item);
			}

			if (image != null) {
				image = imageRepos.save(image);
			}

			if (entity != null) {
				return new ProductDto(entity);
			}
		}
		return null;
	}

	@Override
	public ProductDto saveOrUpdate(ProductDto dto) {
		if (dto != null) {

			Product entity = null;
			Author author = null;
			Image image = null;
			Publisher publisher = null;

			Category category = categoryRepos.findOneByCode(dto.getCategory());
			SubCategory subcategory = subcategoryRepos.findOneByCode(dto.getSubcategory());
			Brand brand = brandRepos.findOneByCode(dto.getBrand());

			Set<String> authorCodes = new HashSet<String>();
			if (dto.getType() == 1 && dto.getAuthorCodes() != null) {
				authorCodes = dto.getAuthorCodes();
			}
			Set<Author> authors = new HashSet<>();

			// 1 - n product - image
			List<String> imageUrls = dto.getImages();
			List<Image> images = new ArrayList<>();

			if (dto.getId() != null) {
				entity = productRepos.getOne(dto.getId());
//				images = imageRepos.findAllByProductId(entity.getId());
//				if (entity.getType() == 1) {
//					publisher = publisherRepos.findOneByCode(entity.getPublisher().getCode());
//					authors = authorRepos.findAllByProducts(entity);
//					for (Author authorTmp : authors) {
//						author = authorTmp;
//					}
//				}
			}
			if (entity == null) {
				entity = new Product();

				for (String imageUrl : imageUrls) {
					image = new Image(imageUrl);
					images.add(image);
				}
			}

			entity.setType(dto.getType());
			entity.setName(dto.getName());
			entity.setSku(dto.getSku());
			entity.setSlug(Slug.makeSlug(dto.getName()));
			entity.setDescription(dto.getDescription());
			entity.setPrice(dto.getPrice());
			entity.setCategory(category);
			entity.setSubcategory(subcategory);
			entity.setBrand(brand);

			switch (dto.getType()) {
			case 1:
				// book
				publisher = publisherRepos.findOneByCode(dto.getPublisher());
				for (String authorCode : authorCodes) {
					author = authorRepos.findOneByCode(authorCode);
					if (author != null) {
						authors.add(author);
					}
				}
				entity.setPublishingYear(dto.getPublishingYear());
				entity.setNumberOfPages(dto.getNumberOfPages());
				entity.setAuthors(authors);
				entity.setPublisher(publisher);
				break;
			case 2:
				// food
				entity.setWeight(dto.getWeight());
				entity.setPreserve(dto.getPreserve());
				entity.setManual(dto.getManual());
				entity.setIngredients(dto.getIngredients());
				entity.setExpiredDate(dto.getExpiredDate());
				break;
			case 3:
				// electric
				entity.setScreen(dto.getScreen());
				entity.setOperatorSystem(dto.getOperatorSystem());
				entity.setRam(dto.getRam());
				entity.setPin(dto.getPin());
				entity.setDesign(dto.getDesign());
				entity.setSizeWeight(dto.getSizeWeight());
				entity.setMaterial(dto.getMaterial());
				entity.setReleaseTime(dto.getReleaseTime());

				// phone
				entity.setBehindCamera(dto.getBehindCamera());
				entity.setChip(dto.getChip());
				entity.setFrontCamera(dto.getFrontCamera());
				entity.setInternalMemory(dto.getInternalMemory());
				entity.setSim(dto.getSim());
				break;
			case 4:
				// electric
				entity.setScreen(dto.getScreen());
				entity.setOperatorSystem(dto.getOperatorSystem());
				entity.setRam(dto.getRam());
				entity.setPin(dto.getPin());
				entity.setDesign(dto.getDesign());
				entity.setSizeWeight(dto.getSizeWeight());
				entity.setMaterial(dto.getMaterial());
				entity.setReleaseTime(dto.getReleaseTime());
				// laptop
				entity.setCard(dto.getCard());
				entity.setCpu(dto.getCpu());
				entity.setHardWare(dto.getHardWare());
				entity.setSpecial(dto.getSpecial());
				break;
			default:
				break;
			}

			entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());

			entity.setImages(images);
			for (Image item : images) {
				item.setProduct(entity);
			}

			entity = productRepos.save(entity);

			if (image != null) {
				image = imageRepos.save(image);
			}

			if (entity != null) {
				return new ProductDto(entity);
			}
		}
		return null;
	}

}
