package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Product;
import com.prgr.main.toc.CompareProduct;

public interface ProductService {
	
	/**
	 * This method takes product details from controller 
	 * and add it to the repository.
	 * @param 
	 * @return Product Object
	 */
	 Product addProduct(final Product product);
	/**
	 * This method takes product Id from controller 
	 * and delete it from the repository.
	 * @param 
	 * @return Product Object
	 */
	 List<Product> deletProduct(int productId);
	/**
	 * This method takes product details from controller 
	 * and update it to the repository.
	 * @param 
	 * @return Product Object
	 */
	 List<Product> updateProduct(Product product);
	/**
	 * This method takes product Id from controller 
	 * and fetch the product from repository.
	 * @param 
	 * @return Product Object
	 */
	 Product getProductById(int productId);
	/**
	 * This method get calls from controller 
	 * and fetch all the product from repository.
	 * @param 
	 * @return  List<Product>
	 */
	 List<Product> getProductList();
	/**
	 * This method takes category from controller 
	 * and fetch the product based on category from repository.
	 * @param 
	 * @return  List<Product>
	 */
	 List<Product> getProductByCategory(String category);
	/**
	 * This method compare two products based on same category and
	 * fetch them from repository and gives the
	 * two products to CompareProduct transfer object.
	 * @return CompareProduct
	 */
	 CompareProduct compareTwoProductBasedOnCategory(String category,int productId1,int productId2);
}
