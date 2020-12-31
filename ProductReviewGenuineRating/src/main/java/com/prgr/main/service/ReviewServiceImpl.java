package com.prgr.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Product;
import com.prgr.main.entity.Review;
import com.prgr.main.repository.ProductRepository;
import com.prgr.main.repository.ReviewRepository;

@Service
@Transactional
/**
 * ReviewServiceImpl class
 * @author YASH
 *
 */
public class ReviewServiceImpl implements ReviewService {
	private static final Logger logger=LoggerFactory.getLogger(ReviewServiceImpl.class);
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private ProductRepository productRepo;
	/**
	 * This method return a list of review from repository
	 * @return List<Review>
	 */
	@Override
	public List<Review> viewallReview() {
		// TODO Auto-generated method stub
		logger.info("view all Review");
		return reviewRepo.findAll();
	}
	/**
	 * This method delete review for a product from repository.
	 * @param reviewId
	 * @param productId
	 * @return boolean
	 */
	@Override
	public boolean deleteReviewForProduct(int reviewId,int productId) {
		logger.info("deleteing Review For Product");
		Product product=productRepo.getOne(productId);
		Review review=reviewRepo.findByReviewIdAndProduct(reviewId, product);
		if(review!=null) {
			reviewRepo.delete(review);
			return true;
		}
		else {
		return false;
		}
	}
	/**
	 * This method add review to product in repository.
	 * @param review
	 * @param productId
	 * @param userId
	 * @return Review
	 */
	@Override
	public Review addReviewForProduct(Review review, int productId,int userId) {
		logger.info("adding Review For Product");
		Product product=productRepo.getOne(productId);
		Review rev=new Review();
		rev.setUserId(userId);
		rev.setRate(review.getRate());
		rev.setDescrption(review.getDescrption());
		rev.setProduct(product);
		Review addReview= reviewRepo.save(rev);
		return addReview;
	}
	/**
	 * This method find a particular review for a product 
	 * in database given by a user.
	 * @param userId
	 * @param productId
	 * @return boolean
	 */
	@Override
	public boolean findByUserIdAndProdId(int userId, int productId) {
		logger.info("find Review For Product on based on userid");
		// TODO Auto-generated method stub
		Product product=productRepo.getOne(productId);
		if(reviewRepo.findByUserIdAndProduct(userId, product)!=null) {
			return false;
		}
		else {
		return true;
		}
	}

}
