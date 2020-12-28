package com.prgr.main.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FEEDBACK")
public class Feedback implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "FEEDBACK_ID")
	private int feedbackId;
	@Column(name = "FEEDBACK_ABOUT")
	private String feedbackAbout;
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
