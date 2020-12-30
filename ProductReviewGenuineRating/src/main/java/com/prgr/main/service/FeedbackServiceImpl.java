package com.prgr.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.prgr.main.entity.Feedback;
import com.prgr.main.repository.FeedbackRepository;

@Transactional
@Service
public class FeedbackServiceImpl implements FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepo;
	/**
	 * This method adds feedback in the repository.
	 * @param feedback
	 * @return feedback
	 */
	@Override
	public Feedback addFeedback(Feedback feedback) {

		return feedbackRepo.save(feedback);
	}
	/**
	 * This method display all the feedbacks from database.
	 * @return List<Feedback>
	 */
	@Override
	public List<Feedback> viewAllFeedback() {
		// TODO Auto-generated method stub
		return feedbackRepo.findAll();
	}

	/**
	 * This method delete feedback by id parameter.
	 * @param 
	 * @return Feedback
	 */
	@Override
	public Feedback deleteFeedback(int feedbackId) {
		// TODO Auto-generated method stub
		feedbackRepo.deleteById(feedbackId);
		Feedback feedback=feedbackRepo.getOne(feedbackId);
		return feedback;
	}

	
}
