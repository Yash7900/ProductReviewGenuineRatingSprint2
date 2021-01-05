package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Product;
import com.prgr.main.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

	/**
	 * This method find review for product
	 * @param reviewId
	 * @param product
	 * @return
	 */
	 Review findByReviewIdAndProduct(int reviewId,Product product);
/**
 * This method find review given by user for a product
 * @param userId
 * @param product
 * @return
 */
	 Review findByUserIdAndProduct(int userId,Product product);
}
