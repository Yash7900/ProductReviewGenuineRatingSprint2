package com.prgr.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Product;
import com.prgr.main.repository.ProductRepository;

@Transactional
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	//------------------------ 1. ProductReviewGenuineRating Application --------------------------
		@Override
		public Product addProduct(Product product) {
			// TODO Auto-generated method stub
			productRepo.save(product);
			return product;	
		}
		
		@Override
		public Product deletProduct(int id) {
			// TODO Auto-generated method stub
			Product product=productRepo.getOne(id);
			productRepo.deleteById(id);
			return product;
		}


		
		@Override
		public Product updateProduct(Product product) {
			// TODO Auto-generated method stub
			productRepo.saveAndFlush(product);
			return product;
			
		}

		
		
		@Override
		public Product getProductById(int id) {
			// TODO Auto-generated method stub
			if(productRepo.findById(id).isPresent()) {
				return productRepo.getOne(id); 
			}
			else {
				return null;
			}
		}

		
		@Override
		public List<Product> getProductList() {
			// TODO Auto-generated method stub
			List<Product> productList = productRepo.findAll();
			return productList;

		}

	

		
		@Override
		public List<Product> getProductByCategory(String category) {
			// TODO Auto-generated method stub
			List<Product> product = productRepo.findAllByCategory(category);
			return product;
		}


}
