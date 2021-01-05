package com.prgr.main.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.prgr.main.entity.Feedback;
import com.prgr.main.repository.FeedbackRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
/**
 * FeedbackServiceTest
 * @author Siddhi
 *
 */
class FeedbackServiceTest {
	
	@InjectMocks
	private FeedbackServiceImpl feedbackService;
	
	@MockBean 
	private FeedbackRepository feedbackRepo;
	
	@Test
	public void testAddFeedback() {
		Feedback feedback=new Feedback();
		feedback.setFeedbackAbout("Product");
		feedback.setFeedbackDescription("Good Quality Product");
		
		Mockito.when(feedbackRepo.save(feedback)).thenReturn(feedback);
		assertThat(feedbackService.addFeedback(feedback)).isEqualTo(feedback);
	}
	
	@Test
	public void testViewAllFeedback() {
		Feedback feedback1=new Feedback();
		feedback1.setFeedbackAbout("Product");
		feedback1.setFeedbackDescription("Good Product");
		
		Feedback feedback2=new Feedback();
		feedback2.setFeedbackAbout("System");
		feedback2.setFeedbackDescription("Great System");
		
		List<Feedback> feedbackList=new ArrayList<>();
		feedbackList.add(feedback1);
		feedbackList.add(feedback2);
		
		Mockito.when(feedbackRepo.findAll()).thenReturn(feedbackList);
		assertThat(feedbackService.viewAllFeedback()).isEqualTo(feedbackList);
	}
	
	@Test
	public void testDeleteFeedback()
	{
		Feedback feedback=new Feedback();
		feedback.setFeedbackId(1);
		feedback.setFeedbackAbout("Product");
		feedback.setFeedbackDescription("Good Product");
		
		Mockito.when(feedbackRepo.getOne(1)).thenReturn(feedback);
		Mockito.when(feedbackRepo.existsById(feedback.getFeedbackId())).thenReturn(false);
		assertFalse(feedbackRepo.existsById(feedback.getFeedbackId()),"");
	}
	
}
