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
import com.prgr.main.entity.Review;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
/**
 * ReviewRepositoryTest
 * @author Ekta
 *
 */
public class ReviewRepositoryTest {


	
	@Autowired
	private ReviewRepository reviewRepo;

	
	@Test
	@Rollback(false)
	@Order(1)
	public void testAddReview() {
		Review review = getReview();
		Review saveReview = reviewRepo.save(review);
		Review getReview = reviewRepo.getOne(saveReview.getReviewId());
		assertThat(getReview).isEqualTo(saveReview);
	}

	@Test
	@Order(2)
	public void testDeleteReview() {
		Review review = getReview();
		Review saveReview=reviewRepo.save(review);
		reviewRepo.delete(saveReview);
		assertThat(saveReview).isNotNull();
		
	}

	@Test
	@Order(3)
	public void testGetAllReview() {
		List<Review> reviewList = reviewRepo.findAll();
		assertThat(reviewList).size().isGreaterThan(0);
	}

	@Test
	@Order(4)
	public void testFindByReviewIdAndProduct() {
		Review review = getReview();
		reviewRepo.save(review);
		Review foundReview = reviewRepo.findById(review.getReviewId()).get();
		assertThat(foundReview).isEqualTo(review);
	}


	public Review getReview() {
		Review review = new Review();
		Product product = new Product();
		review.setUserId(1);
		review.setRate(5);
		review.setDescription("Good");
		product.setProductId(1);
		review.setProduct(product);
		return review;
	}
}
