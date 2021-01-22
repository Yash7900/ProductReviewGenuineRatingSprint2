package com.prgr.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Feedback;
import com.prgr.main.repository.FeedbackRepository;

@Transactional
@Service
/**
 * FeedbackServiceImpl Class
 * @author Siddhi
 *
 */
public class FeedbackServiceImpl implements FeedbackService {
	private static final Logger LOGGER=LoggerFactory.getLogger(FeedbackServiceImpl.class);
	@Autowired
	private FeedbackRepository feedbackRepo;
	/**
	 * This method adds feedback in the repository.
	 * @param feedback
	 * @return feedback
	 */
	@Override
	public Feedback addFeedback(final Feedback feedback) {
		LOGGER.info("adding Feedback");
		return feedbackRepo.save(feedback);
	}
	/**
	 * This method display all the feedbacks from database.
	 * @return List<Feedback>
	 */
	@Override
	public List<Feedback> viewAllFeedback() {
		LOGGER.info("view all Feedback");
		// TODO Auto-generated method stub
		return feedbackRepo.findAll();
	}

	/**
	 * This method delete feedback by id parameter.
	 * @param 
	 * @return Feedback
	 */
	@Override
	public List<Feedback> deleteFeedback(final int feedbackId) {
		// TODO Auto-generated method stub
		LOGGER.info("deleting Feedback");
		feedbackRepo.deleteById(feedbackId);
		List<Feedback> feedbackList=feedbackRepo.findAll();
		//Feedback feedback=feedbackRepo.getOne(feedbackId);
		return feedbackList;
	}
	/**
	 * This method get feedback from Repository based on Id.
	 * @param feedbackId
	 * @return boolean
	 */
	@Override
	public boolean getFeedbackById(final int feedbackId) {
		// TODO Auto-generated method stub
		LOGGER.info("getFeedbackById()");
		if(feedbackRepo.findById(feedbackId).isPresent()) {
			return true;
		}
	return false;
	}

	
}
