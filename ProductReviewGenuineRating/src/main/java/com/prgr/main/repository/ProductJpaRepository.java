package com.prgr.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Product;

public interface ProductJpaRepository extends JpaRepository<Product,Integer>{

	public List<Product> findAllByCategory(String category);
}
