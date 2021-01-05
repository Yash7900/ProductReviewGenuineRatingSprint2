package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Review;

public interface ReviewService {
	/**
	 * This method return a list of review from repository
	 * @return List<Review>
	 */
	 List<Review> viewallReview();
	/**
	 * This method delete review for a product from repository.
	 * @param reviewId
	 * @param productId
	 * @return boolean
	 */
	 boolean deleteReviewForProduct(int reviewId,int productId);
	/**
	 * This method add review to product in repository.
	 * @param review
	 * @param productId
	 * @param userId
	 * @return Review
	 */
	 Review addReviewForProduct(Review review, int productId,int userId);
	/**
	 * This method find a particular review for a product 
	 * in database given by a user.
	 * @param userId
	 * @param productId
	 * @return boolean
	 */
	 boolean findByUserIdAndProdId(int userId,int productId);
}
