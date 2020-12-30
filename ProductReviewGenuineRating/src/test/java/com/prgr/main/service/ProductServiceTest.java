package com.prgr.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prgr.main.entity.Product;
import com.prgr.main.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)


class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;
	
	@MockBean
	private ProductRepository productRepo;
	
	@Test
	public void testAddProduct() {
		Product product=new Product();
		product.setDescription("Electronics");
		product.setCategory("TV");
		Mockito.when(productRepo.save(product)).thenReturn(product);
		assertThat(productService.addProduct(product)).isEqualTo(product);
	}
	
	@Test
	public void testViewAllProduct() {
		Product product1=new Product();
		product1.setDescription("Electronics");
		product1.setCategory("TV");
		
		Product product2= new Product();
		product2.setDescription("Electronics");
		product2.setCategory("TV");
		
		List<Product> productList=new ArrayList<>();
		productList.add(product1);
		productList.add(product2);
		
		Mockito.when(productRepo.findAll()).thenReturn(productList);
		assertThat(productService.getProductList()).isEqualTo(productList);
	}
	
	@Test
	public void testDeleteFeedback()
	{
		Product product=new Product();
		product.setProductId(1);
		product.setDescription("Electronics");
		product.setCategory("TV");
		
		Mockito.when(productRepo.getOne(1)).thenReturn(product);
		Mockito.when(productRepo.existsById(product.getProductId())).thenReturn(false);
		assertFalse(productRepo.existsById(product.getProductId()));
	}
	
}
