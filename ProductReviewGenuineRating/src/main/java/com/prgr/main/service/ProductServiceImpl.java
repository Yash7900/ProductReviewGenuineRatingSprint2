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
			/*******************************************************************************************
			         - Function Name	:	addProduct
					 - Input Parameters	:	product object(request from client)
					 - Return Type		:	product object
					 - Description		:	adding the product details to database by calling product repository
			    
			 *******************************************************************************************/
		@Override
		public Product addProduct(Product product) {
			// TODO Auto-generated method stub
			productRepo.save(product);
			return product;	
		}
		
		/*******************************************************************************************
	    - Function Name	:	deleteProduct
		 - Input Parameters	:	id object(request from client)
		 - Return Type		:	product object
		 - Description		:	delete the product from database by calling deletProduct() in product repository 
		 *******************************************************************************************/

		@Override
		public Product deletProduct(int id) {
			// TODO Auto-generated method stub
			Product product=productRepo.getOne(id);
			productRepo.deleteById(id);
			return product;
		}


		/*******************************************************************************************
	    - Function Name	:	updateProduct
		 - Input Parameters	:	product object(request from client)
		 - Return Type		:	product object
		 - Description		:	update the product details in database and save it by calling saveAndFlush()

		 *******************************************************************************************/
		
		@Override
		public Product updateProduct(Product product) {
			// TODO Auto-generated method stub
			productRepo.saveAndFlush(product);
			return product;
			
		}

		/*******************************************************************************************
	    - Function Name	:	getProductById
		 - Input Parameters	:	id object(request from client)
		 - Return Type		:	product object
		 - Description		:	get  product from database by calling getOne()


		 *******************************************************************************************/
		
		@Override
		public Product getProductById(int id) {
			// TODO Auto-generated method stub
			Product product = productRepo.getOne(id);
			return product;
		}

		/*******************************************************************************************
	    - Function Name	:	getProductList
	    - Input Parameters	:	empty
		 - Return Type		:	List<product> object
		 - Description		:	get all product details from database by calling findAll()  

		 *******************************************************************************************/
		@Override
		public List<Product> getProductList() {
			// TODO Auto-generated method stub
			List<Product> productList = productRepo.findAll();
			return productList;

		}

		/*******************************************************************************************
	    - Function Name	:	getProductCategory
		 - Input Parameters	:	category object(request from client)
		 - Return Type		:	List<Product> object
		 - Description		:	get  product from database by calling getProduct() based on category

		 *******************************************************************************************/

		
		@Override
		public List<Product> getProductByCategory(String category) {
			// TODO Auto-generated method stub
			List<Product> product = productRepo.findAllByCategory(category);
			return product;
		}


}
