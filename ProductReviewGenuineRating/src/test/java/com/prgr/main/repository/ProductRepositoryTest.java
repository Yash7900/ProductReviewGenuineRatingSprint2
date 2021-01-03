package com.prgr.main.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.prgr.main.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
/**
 * ProductRepositoryTest
 * @author Aswathy
 *
 */
class ProductRepositoryTest {

	
	@Autowired
	private ProductRepository productRepo;


	@Test
	@Rollback(false)
	@Order(1)
	public void testSaveProduct() {
		Product product = getProduct();
		Product saveProduct = productRepo.save(product);
		Product getProduct = productRepo.getOne(saveProduct.getProductId());

		assertThat(getProduct).isEqualTo(saveProduct);
	}
	@Test
	@Order(2)
	public void getAllProduct() {
		List<Product> ProductList = productRepo.findAll();
		assertThat(ProductList).size().isGreaterThan(0);
	}

	
	
	
	@Test
	@Order(3)
	public void testUpdateProduct() {
	    Product product=getProduct();
	    product.setPrice(100000L);
	    product.setSellerName("Aswathy");
	     
	    Product savedProduct=productRepo.save(product);
	     
	    Product updatedProduct = productRepo.getOne(savedProduct.getProductId());
	     
	    assertThat(updatedProduct.getPrice()).isEqualTo(100000);
	    assertThat(updatedProduct.getSellerName()).isEqualTo("Aswathy");
	}
	
	@Test
	@Order(4)
	public void testDeleteProduct() {
		Product product = getProduct();
		Product savedProduct=productRepo.save(product);
		productRepo.delete(savedProduct);
		assertThat(savedProduct).isNotNull();
	}
	
	
	public Product getProduct() {
		Product product = new Product();
		product.setProductName("TV");
		product.setCategory("Electronics");
		product.setSellerName("ONIDA");
		product.setDescription("Electronics appliances");
		product.setPrice(50000L);
		return product;
	}

}
