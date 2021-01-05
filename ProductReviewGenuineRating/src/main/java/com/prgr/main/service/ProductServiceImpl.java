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
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	@Autowired
	private ProductRepository productRepo;

	
	/**
	 * This method takes product details from controller and add it to the
	 * repository.
	 * 
	 * @param
	 * @return Product Object
	 */
	@Override
	public Product addProduct(final Product product) {
		LOGGER.info("adding product");
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
	public Product deletProduct(final int productId) {
		// TODO Auto-generated method stub
		LOGGER.info("deleting product");
		Product product = productRepo.getOne(productId);
		productRepo.deleteById(productId);
		return product;
	}
	/**
	 * This method takes product details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	@Override
	public Product updateProduct(final Product product) {
		// TODO Auto-generated method stub
		LOGGER.info("updating product");
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
	public Product getProductById(final int productId) {
		// TODO Auto-generated method stub
		LOGGER.info("getProductById()");
		if (productRepo.findById(productId).isPresent()) {
			return productRepo.getOne(productId);
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
		LOGGER.info("view all product");
		return productRepo.findAll();
		

	}
	/**
	 * This method takes category from controller 
	 * and fetch the product based on category from repository.
	 * @param 
	 * @return  List<Product>
	 */
	@Override
	public List<Product> getProductByCategory(final String category) {
		// TODO Auto-generated method stub
		LOGGER.info("view product based on category");
		return productRepo.findAllByCategory(category);
		 
	}

	@Override
	/**
	 * This method compare two products based on same category 
	 * and fetch them from repository and gives the
	 * two products to CompareProduct transfer object.
	 * @return CompareProduct
	 */

	public CompareProduct compareTwoProductBasedOnCategory(final String category,final int productId1,final int productId2) {
		// TODO Auto-generated method stub
		Product product1=productRepo.findByCategoryAndProductId(category,productId1);
		Product product2=productRepo.findByCategoryAndProductId(category,productId2);
		CompareProduct compareProduct=new CompareProduct();
		compareProduct.setProduct1(product1);
		compareProduct.setProduct2(product2);
		return compareProduct;
	}

}
