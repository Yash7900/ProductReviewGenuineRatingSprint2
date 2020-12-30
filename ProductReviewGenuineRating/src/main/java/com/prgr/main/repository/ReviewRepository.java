package com.prgr.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prgr.main.entity.Product;
import com.prgr.main.entity.Review;

public interface ReviewRepository extends JpaRepository<Review,Integer> {

	public Review findByReviewIdAndProduct(int reviewId,Product product);

	public Review findByUserIdAndProduct(int userId,Product product);
}
