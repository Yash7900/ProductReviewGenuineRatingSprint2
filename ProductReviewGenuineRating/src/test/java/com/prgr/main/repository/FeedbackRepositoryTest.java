package com.prgr.main.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

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

import com.prgr.main.entity.Feedback;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
/**
 * FeedbackRepositoryTest
 * @author Siddhi
 *
 */
class FeedbackRepositoryTest {
	
	@Autowired 
	private FeedbackRepository feedbackRepo;
	
	
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testSaveFeedback() {
		Feedback feedback=getFeedback();
		Feedback savedFeedback=feedbackRepo.save(feedback);
		Feedback getFromDb=feedbackRepo.getOne(savedFeedback.getFeedbackId());
		
		assertThat(getFromDb).isEqualTo(savedFeedback);
	}
	
	@Test
	@Order(2)
	public void testViewAllFeedback()
	{
		List<Feedback> feedbackList=feedbackRepo.findAll();
		assertNotNull(feedbackList);
		assertThat(feedbackList).size().isGreaterThan(0);
	}
	
	//@Ignore("Not Ready Yet")
	@Test
	@Rollback(false)
	@Order(3)
	public void testDeleteFeedback()
	{
		Feedback feedback=getFeedback();
		Feedback saveFeedback=feedbackRepo.save(feedback);
		feedbackRepo.deleteById(saveFeedback.getFeedbackId());
		//Feedback deleteFeedback=feedbackRepo.getOne(saveFeedback.getFeedbackId());
	    assertThat(saveFeedback).isNotNull();
	}
	
	private Feedback getFeedback()
	{
		Feedback feedback=new Feedback();
		feedback.setFeedbackAbout("Product");
		feedback.setFeedbackDescription("Good Product");
		return feedback;
	}
	
}
