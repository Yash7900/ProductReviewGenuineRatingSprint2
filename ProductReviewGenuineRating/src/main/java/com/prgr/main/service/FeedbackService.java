package com.prgr.main.service;

import java.util.List;

import com.prgr.main.entity.Feedback;

public interface FeedbackService {
	/**
	 * This method adds feedback in the repository.
	 * @param 
	 * @return feedback
	 */
	public Feedback addFeedback(Feedback feedback);
	
	/**
	 * This method display all the feedbacks from database.
	 * @return List<Feedback>
	 */
	public List<Feedback> viewAllFeedback();
	
	/**
	 * This method delete feedback by id parameter.
	 * @param 
	 * @return Feedback
	 */
	public void deleteFeedback(int feedbackId);
	
	public boolean getFeedbackById(int feedbackId);

}
