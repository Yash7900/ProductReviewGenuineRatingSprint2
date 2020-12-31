package com.prgr.main.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FEEDBACK")
/**
 * Feedback entity class
 * @author YASH
 *
 */
public class Feedback implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FEEDBACK_ID")
	private int feedbackId;
	@NotNull(message="feedback about cannot be null")
	@Column(name = "FEEDBACK_ABOUT")
	private String feedbackAbout;
	@NotNull(message="feedback description cannot be null")
	@Column(name = "FEEDBACK_DESCRIPTION")
	private String feedbackDescription;
	
	public Feedback()
	{}
	
	public Feedback(String feedbackAbout,
			String feedbackDescription) {
		super();
		//this.feedbackId = feedbackId;
		this.feedbackAbout = feedbackAbout;
		this.feedbackDescription = feedbackDescription;
	}

	public int getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(int feedbackId) {
		this.feedbackId = feedbackId;
	}
	public String getFeedbackAbout() {
		return feedbackAbout;
	}
	public void setFeedbackAbout(String feedbackAbout) {
		this.feedbackAbout = feedbackAbout;
	}
	public String getFeedbackDescription() {
		return feedbackDescription;
	}
	public void setFeedbackDescription(String feedbackDescription) {
		this.feedbackDescription = feedbackDescription;
	}
	@Override
	public String toString() {
		return "Feedback [feedbackId=" + feedbackId + ", About=" + feedbackAbout
				+", description=" + feedbackDescription + 
				 "]";
	}
}
