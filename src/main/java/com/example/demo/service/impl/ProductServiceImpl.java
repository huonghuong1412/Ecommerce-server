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
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.common.Slug;
import com.example.demo.dto.AdvanceSearchDto;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.product.ProductDto;
import com.example.demo.dto.product.ProductDtoNew;
import com.example.demo.dto.product.ProductListDto;
import com.example.demo.entity.category.Category;
import com.example.demo.entity.category.SubCategory;
import com.example.demo.entity.inventory.Inventory;
import com.example.demo.entity.product.Author;
import com.example.demo.entity.product.Book;
import com.example.demo.entity.product.Brand;
import com.example.demo.entity.product.Image;
import com.example.demo.entity.product.Product;
import com.example.demo.entity.product.Publisher;
import com.example.demo.entity.product.Technology;
import com.example.demo.repository.AuthorRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.BrandRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.InventoryRepository;
import com.example.demo.repository.OrderDetailRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.PublisherRepository;
import com.example.demo.repository.SubCategoryRepository;
import com.example.demo.repository.TechnologyRepository;
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
	private BookRepository bookRepos;

	@Autowired
	private TechnologyRepository techRepos;

	@Autowired
	private InventoryRepository inventoryRepos;

	@Autowired
	private OrderDetailRepository orderDetailRepos;

	@Override
	public Page<ProductListDto> productList(SearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity." + dto.getSortBy() + " " + dto.getSortValue();
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.product.ProductListDto(entity) from  Product as entity where entity.display=1 AND (1=1)  ";
		if (dto.getKeyword() != null && StringUtils.hasText(dto.getKeyword())) {
			whereClause += " AND ( entity.name LIKE :text " + "OR entity.description LIKE :text "
					+ "OR entity.slug LIKE :text " + "OR entity.slug LIKE :text "
					+ "OR entity.category.name LIKE :text " + "OR entity.category.code LIKE :text "
					+ "OR entity.subcategory.name LIKE :text " + "OR entity.subcategory.code LIKE :text )";
		}

		if (dto.getCategory() != null) {
			whereClause += " AND ( entity.category.code LIKE :category )";
		}
		if (dto.getSubcategory() != null) {
			whereClause += " AND ( entity.subcategory.code LIKE :subcategory )";
		}

		if (dto.getBrand() != null && StringUtils.hasText(dto.getBrand())) {
			if (dto.getBrand().contains(",")) {
				String[] s = dto.getBrand().split(",");
				whereClause += " AND ( entity.brand.code = '" + s[0] + "' )";
				for (int i = 1; i < s.length; i++) {
					whereClause += " OR ( entity.brand.code = '" + s[i] + "' )";
				}
			} else {
				whereClause += " AND ( entity.brand.code = :brand )";
			}
		} else {
			whereClause += "";
		}

		if (dto.getPrice() != null && dto.getPrice().equalsIgnoreCase("") == false) {
			String[] s = dto.getPrice().toString().split(",");
			Long begin = Long.parseLong(s[0]);
			Long end = Long.parseLong(s[1]);
			whereClause += " AND ( entity.price BETWEEN " + begin + " AND " + end + " )";
		} else {
			whereClause += "";
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

		if (dto.getBrand() != null && dto.getBrand().length() > 0 && dto.getBrand().contains(",") == false) {
			q.setParameter("brand", dto.getBrand());
			qCount.setParameter("brand", dto.getBrand());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductListDto> entities = q.getResultList();
		Integer seller_count = 0;
		for (ProductListDto item : entities) {
			if(orderDetailRepos.countAllByProductId(item.getId()) != null) {
				seller_count += orderDetailRepos.countAllByProductId(item.getId());
			} else {
				seller_count = 0;
			}
 			item.setSeller_count(seller_count);
		}

		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductListDto> result = new PageImpl<ProductListDto>(entities, pageable, count);
		return result;
	}

	@Override
	public Page<ProductListDto> getAllProduct(AdvanceSearchDto dto) {
		int pageIndex = dto.getPageIndex();
		int pageSize = dto.getPageSize();
		if (pageIndex > 0)
			pageIndex -= 1;
		else
			pageIndex = 0;

		String whereClause = "";
		String orderBy = " ORDER BY entity.id DESC";
		String sqlCount = "select count(entity.id) from  Product as entity where (1=1) ";
		String sql = "select new com.example.demo.dto.product.ProductListDto(entity) from  Product as entity where (1=1) ";

		if (dto.getDisplay() == 0 || dto.getDisplay() == 1) {
			whereClause += " AND ( entity.display = " + dto.getDisplay() + ")";
		} else {
			whereClause += "";
		}

		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			whereClause += " AND ( entity.name LIKE :name " + "OR entity.description LIKE :name "
					+ "OR entity.slug LIKE :name )";
		} else {
			whereClause += "";
		}

		if (dto.getSku() != null && StringUtils.hasText(dto.getSku())) {
			whereClause += " AND ( entity.sku LIKE :sku )";
		}

		if (dto.getCategory() != null && StringUtils.hasText(dto.getCategory())) {
			whereClause += " AND ( entity.category.code = :category )";
		} else {
			whereClause += "";
		}

		if (dto.getBrand() != null && StringUtils.hasText(dto.getBrand())) {
			if (dto.getBrand().contains(",")) {
				String[] s = dto.getBrand().split(",");
				whereClause += " AND ( entity.brand.code = '" + s[0] + "' )";
				for (int i = 1; i < s.length; i++) {
					whereClause += " OR ( entity.brand.code = '" + s[i] + "' )";
				}
			} else {
				whereClause += " AND ( entity.brand.code = :brand )";
			}
		} else {
			whereClause += "";
		}

		sql += whereClause + orderBy;
		sqlCount += whereClause;

		Query q = manager.createQuery(sql, ProductListDto.class);
		Query qCount = manager.createQuery(sqlCount);

		if (dto.getName() != null && StringUtils.hasText(dto.getName())) {
			q.setParameter("name", '%' + dto.getName() + '%');
			qCount.setParameter("name", '%' + dto.getName() + '%');
		}

		if (dto.getSku() != null && StringUtils.hasText(dto.getSku())) {
			q.setParameter("sku", '%' + dto.getSku() + '%');
			qCount.setParameter("sku", '%' + dto.getSku() + '%');
		}

		if (dto.getCategory() != null && dto.getCategory().length() > 0) {
			q.setParameter("category", dto.getCategory());
			qCount.setParameter("category", dto.getCategory());
		}

		if (dto.getBrand() != null && dto.getBrand().length() > 0 && dto.getBrand().contains(",") == false) {
			q.setParameter("brand", dto.getBrand());
			qCount.setParameter("brand", dto.getBrand());
		}

		int startPosition = pageIndex * pageSize;
		q.setFirstResult(startPosition);
		q.setMaxResults(pageSize);

		@SuppressWarnings("unchecked")
		List<ProductListDto> entities = q.getResultList();
		Integer seller_count = 0;
		for (ProductListDto item : entities) {
			if(orderDetailRepos.countAllByProductId(item.getId()) != null) {
				seller_count = orderDetailRepos.countAllByProductId(item.getId());
			} else {
				seller_count = 0;
			}
 			item.setSeller_count(seller_count);
		}


		long count = (long) qCount.getSingleResult();
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		Page<ProductListDto> result = new PageImpl<ProductListDto>(entities, pageable, count);
		return result;
	}

	@Override
	public ProductDto saveOrUpdate(ProductDto dto) {
		if (dto != null) {

			Product entity = null;
			Book book = null;
			Technology tech = null;
			Author author = null;
			Image image = null;
			Publisher publisher = null;
			Inventory inventory = null;

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
				entity = productRepos.getById(dto.getId());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
				
				List<Image> imagesProduct = imageRepos.findAllByProductId(entity.getId());
				for(Image item : imagesProduct) {
					imageRepos.deleteByProductId(item.getProduct().getId());
				}

				for(int i = 0; i < imageUrls.size(); i++) {
					image = new Image(imageUrls.get(i));
					images.add(image);
				}
				switch (entity.getType()) {
				case 1:
					book = bookRepos.findOneByProduct(entity);
					break;
				case 2:
					tech = techRepos.findOneByProduct(entity);
					break;
				default:
					break;
				}
			}
			if (entity == null) {
				entity = new Product();
				entity.setCreatedDate(new Timestamp(new Date().getTime()).toString());
				entity.setUpdatedDate(new Timestamp(new Date().getTime()).toString());
				book = new Book();
				tech = new Technology();
				inventory = new Inventory();
				inventory.setQuantity_item(0);
				inventory.setTotal_import_item(0);
				inventory.setCategory_code(category.getCode());
				inventory.setProduct(entity);
				for(int i = 0; i < imageUrls.size(); i++) {
					image = new Image(imageUrls.get(i));
					images.add(image);
				}
			}

			entity.setType(dto.getType());
			entity.setName(dto.getName());
			entity.setMainIamge(dto.getMainImage());
			entity.setWeight(dto.getWeight());
			entity.setLength(dto.getLength());
			entity.setWidth(dto.getWidth());
			entity.setHeight(dto.getHeight());
			entity.setPrice(dto.getPrice());
			entity.setList_price(dto.getList_price());
			entity.setSku(dto.getSku());
			entity.setSlug(Slug.makeSlug(dto.getName()));
			entity.setDescription(dto.getDescription());
			entity.setDisplay(1);
			// price

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
				book.setPublishingYear(dto.getPublishingYear());
				book.setNumberOfPages(dto.getNumberOfPages());
				book.setAuthors(authors);
				book.setPublisher(publisher);
				book.setProduct(entity);
				break;
			case 2:
				// electric
				tech.setScreen(dto.getScreen());
				tech.setOperatorSystem(dto.getOperatorSystem());
				tech.setRam(dto.getRam());
				tech.setPin(dto.getPin());
				tech.setDesign(dto.getDesign());
				tech.setSizeWeight(dto.getSizeWeight());
				tech.setMaterial(dto.getMaterial());
				tech.setReleaseTime(dto.getReleaseTime());
				tech.setScreen_size(dto.getScreen_size());
				tech.setCamera(dto.getCamera());
				tech.setDisplay_resolution(dto.getDisplay_resolution());
				tech.setChip(dto.getChip());
				
				// phone
				tech.setBehindCamera(dto.getBehindCamera());
				tech.setFrontCamera(dto.getFrontCamera());
				tech.setInternalMemory(dto.getInternalMemory());
				tech.setSim(dto.getSim());
				tech.setNumber_sim(dto.getNumber_sim());
				tech.setAccessory(dto.getAccessory());
				
				// laptop
				tech.setCard(dto.getCard());
				tech.setCpu(dto.getCpu());
				tech.setHardWare(dto.getHardWare());
				tech.setBus(dto.getBus());
				tech.setProduct(entity);
				break;
			default:
				break;
			}

			entity.setImages(images);
			for(int i = 0; i<images.size(); i++) {
				images.get(i).setProduct(entity);
			}
			entity.setBook(book);
			entity.setTechnology(tech);
			entity = productRepos.save(entity);
			switch (dto.getType()) {
			case 1:
				book = bookRepos.save(book);
				break;
			case 2:
				tech = techRepos.save(tech);
				break;
			default:
				break;
			}

			if (inventory != null) {
				inventoryRepos.save(inventory);
			}

			if (entity != null) {
				return new ProductDto(entity);
			}
		}
		return null;
	}

	@Override
	public Boolean delete(Long id) {
		if (id != null) {
			Product entity = productRepos.getById(id);
			if (entity.getDisplay() == 1) {
				entity.setDisplay(0);
			} else {
				entity.setDisplay(1);
			}
			productRepos.save(entity);
			return true;
		}
		return false;
	}

	@Override
	public ProductDtoNew getProductById(Long id) {
		Product product = productRepos.getById(id);
		ProductDtoNew dto = new ProductDtoNew(product);
		return dto;
	}

	@Override
	public ProductDto getDetailProduct(Long id) {
		Product product = productRepos.getById(id);
		ProductDto dto = new ProductDto(product);
		return dto;
	}

	@Override
	public Page<ProductListDto> getAllProductByBrand(String brandCode, Integer page, Integer limit, String sortBy) {
		Brand brand = brandRepos.findOneByCode(brandCode);
		Page<Product> list = productRepos.findAllByBrand(brand,
				PageRequest.of(page, limit, Sort.by(sortBy).descending()));
		Page<ProductListDto> dtoPage = list.map(item -> new ProductListDto(item));
		return dtoPage;
	}

	@Override
	public List<ProductListDto> getAllByBrand(Long productId, String brandCode) {
		Brand brand = brandRepos.findOneByCode(brandCode);
		Page<Product> pages = productRepos.findAllByBrand(brand, PageRequest.of(0, 4, Sort.by("id").descending()));
		List<Product> list = pages.getContent();
		List<ProductListDto> dtos = new ArrayList<>();
		for (Product p : list) {
			if (productId != p.getId()) {
				ProductListDto dto = new ProductListDto(p);
				dtos.add(dto);
			}
		}
		return dtos;
	}

}
