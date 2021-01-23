package com.prgr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.prgr.main.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	/**
	 * This method get all product based on category
	 * @param category
	 * @return
	 */
	 List<Product> findAllByCategory(String category);
	/**
	 * This method find a product based on category 
	 * provided.
	 * @param category
	 * @param productId
	 * @return product
	 */
	 Product findByCategoryAndProductId(String category,int productId);
	 
	 @Query("SELECT DISTINCT product.category FROM Product product")
	 List<String>getCategory();
}
