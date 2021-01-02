package com.prgr.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prgr.main.entity.Product;
import com.prgr.main.entity.Review;
import com.prgr.main.repository.ProductRepository;
import com.prgr.main.repository.ReviewRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
//@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class ReviewServiceTest {

	@InjectMocks
	private ReviewServiceImpl reviewService;

	@MockBean
	private ReviewRepository reviewRepo;

	@MockBean
	private ProductRepository productRepo;

	@Test
	public void testaddReviewForProduct() {
		Review review = new Review();
		Product product = new Product();
		review.setUserId(1);
		review.setRate(5);
		review.setDescrption("Good");
		product.setProductId(1);
		review.setProduct(product);
		Review rev=reviewRepo.save(review); 
		Mockito.when(rev).thenReturn(review);
		assertThat(reviewService.addReviewForProduct(review, product.getProductId(), review.getUserId())).isEqualTo(rev);
	
	}

	@Test
	public void testDeleteReviewForProduct() {
		Product product = new Product();
		Review review = new Review();
		review.setReviewId(1);
		product.setProductId(1);
		Mockito.when(reviewRepo.findByReviewIdAndProduct(review.getReviewId(), product)).thenReturn(review);
		Mockito.when(reviewRepo.existsById(review.getReviewId())).thenReturn(false);
		assertFalse(reviewRepo.existsById(review.getReviewId()));
	}

	@Test
	public void testFindByUserIdAndProdId() {
		Review review = new Review();
		Product product = new Product();
		review.setUserId(1);
		product.setProductId(1);
		Mockito.when(reviewRepo.getOne(1)).thenReturn(review);
		assertTrue(reviewService.findByUserIdAndProdId(review.getUserId(), product.getProductId()));
	}

	@Test
	public void testViewAllReview() {
		Review review = new Review();
		review.setRate(5);
		review.setDescrption("Good");

		Review review2 = new Review();
		review2.setRate(2);
		review2.setDescrption("Bad");

		List<Review> reviewList = new ArrayList<>();
		reviewList.add(review);
		reviewList.add(review2);

		Mockito.when(reviewRepo.findAll()).thenReturn(reviewList);
		assertThat(reviewService.viewallReview()).isEqualTo(reviewList);

	}

}
