package com.prgr.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Product;
import com.prgr.main.repository.ProductRepository;
import com.prgr.main.toc.CompareProduct;

@Transactional
@Service
/**
 * ProductServiceImpl class
 * @author Ekta
 *
 */
public class ProductServiceImpl implements ProductService {
	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepository productRepo;

	// ------------------------ 1. ProductReviewGenuineRating Application// --------------------------
	/**
	 * This method takes product details from controller and add it to the
	 * repository.
	 * 
	 * @param
	 * @return Product Object
	 */
	@Override
	public Product addProduct(Product product) {
		logger.info("adding product");
		// TODO Auto-generated method stub
		productRepo.save(product);
		return product;
	}

	/**
	 * This method takes product Id from controller and delete it from the
	 * repository.
	 * 
	 * @param
	 * @return Product Object
	 */

	@Override
	public Product deletProduct(int id) {
		// TODO Auto-generated method stub
		logger.info("deleting product");
		Product product = productRepo.getOne(id);
		productRepo.deleteById(id);
		return product;
	}
	/**
	 * This method takes product details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		logger.info("updating product");
		productRepo.saveAndFlush(product);
		return product;

	}
	/**
	 * This method takes product Id from controller 
	 * and fetch the product from repository.
	 * @param 
	 * @return Product Object
	 */
	@Override
	public Product getProductById(int id) {
		// TODO Auto-generated method stub
		logger.info("getProductById()");
		if (productRepo.findById(id).isPresent()) {
			return productRepo.getOne(id);
		} else {
			return null;
		}
	}
	/**
	 * This method get calls from controller 
	 * and fetch all the product from repository.
	 * @param 
	 * @return  List<Product>
	 */
	@Override
	public List<Product> getProductList() {
		// TODO Auto-generated method stub
		logger.info("view all product");
		List<Product> productList = productRepo.findAll();
		return productList;

	}
	/**
	 * This method takes category from controller 
	 * and fetch the product based on category from repository.
	 * @param 
	 * @return  List<Product>
	 */
	@Override
	public List<Product> getProductByCategory(String category) {
		// TODO Auto-generated method stub
		logger.info("view product based on category");
		List<Product> product = productRepo.findAllByCategory(category);
		return product;
	}

	@Override
	/**
	 * This method compare two products based on same category and fetch them from repository and gives the
	 * two products to CompareProduct transfer object.
	 * @param category
	 * @param productId1
	 * @param productId2
	 * @return CompareProduct
	 */

	public CompareProduct compareTwoProductBasedOnCategory(String category, int productId1, int productId2) {
		// TODO Auto-generated method stub
		Product product1=productRepo.findByCategoryAndProductId(category,productId1);
		Product product2=productRepo.findByCategoryAndProductId(category,productId2);
		CompareProduct compareProduct=new CompareProduct();
		compareProduct.setProduct1(product1);
		compareProduct.setProduct2(product2);
		return compareProduct;
	}

}
