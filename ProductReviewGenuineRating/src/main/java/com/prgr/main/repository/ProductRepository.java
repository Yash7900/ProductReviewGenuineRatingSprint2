package com.prgr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{

	/**
	 * This method get all product based on category
	 * @param category
	 * @return
	 */
	public List<Product> findAllByCategory(String category);
}
