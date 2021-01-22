package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Feedback;

public interface FeedbackService {
	/**
	 * This method adds feedback in the repository.
	 * @param 
	 * @return feedback
	 */
	 Feedback addFeedback(Feedback feedback);
	
	/**
	 * This method display all the feedbacks from database.
	 * @return List<Feedback>
	 */
	 List<Feedback> viewAllFeedback();
	
	/**
	 * This method delete feedback by id parameter.
	 * @param 
	 * @return Feedback
	 */
	 List<Feedback> deleteFeedback(int feedbackId);
	/**
	 * This method get feedback from Repository based on Id.
	 * @param feedbackId
	 * @return boolean
	 */
	 boolean getFeedbackById(int feedbackId);

}
