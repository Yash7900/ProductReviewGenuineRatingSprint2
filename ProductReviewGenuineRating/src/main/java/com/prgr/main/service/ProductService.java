package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Product;

public interface ProductService {
	public Product addProduct(Product product);
	public Product deletProduct(int id);
	public Product updateProduct(Product product);
	public Product getProductById(int id);
	public List<Product> getProductList();
	public List<Product> getProductByCategory(String category);
}
