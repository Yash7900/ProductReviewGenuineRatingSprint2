package com.prgr.main.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.prgr.main.entity.Feedback;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace= Replace.NONE)
@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
class FeedbackRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired 
	private FeedbackRepository feedbackRepo;
	
	public Feedback getFeedback()
	{
		Feedback feedback=new Feedback();
		//feedback.setFeedbackId(100);
		feedback.setFeedbackAbout("Product");
		feedback.setFeedbackDescription("Good Product");
		return feedback;
	}
	
	@Test
	@Rollback(false)
	@Order(1)
	public void testSaveFeedback() {
		Feedback feedback=getFeedback();
		Feedback savedFeedback=entityManager.persist(feedback);
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
		entityManager.remove(feedback);
		//Feedback deletedFeedback=feedbackRepo.getOne(feedback.getFeedbackId());
		//Assert.assertNull(deletedFeedback);
		assertThat(feedback.getFeedbackId()).isZero();
	}
	
}
